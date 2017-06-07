package com.jpmac26.olddays.settings;

import com.jpmac26.olddays.OldDaysRevisited;
import com.jpmac26.olddays.client.gui.GuiIngameOld;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import squeek.applecore.api.AppleCoreAPI;
import squeek.applecore.api.food.FoodEvent;
import squeek.applecore.api.hunger.ExhaustionEvent;
import squeek.applecore.api.hunger.HealthRegenEvent;
import squeek.applecore.api.hunger.StarvationEvent;

import static com.jpmac26.olddays.client.gui.GuiIngameOld.hideHunger;

/**
 * Created by James Pelster on 7/20/2016.
 */
public class GameplaySettings
{
    public enum HungerMode {
        VANILLA_1_11,
        VANILLA_1_9,
        VANILLA_BETA_1_8,
        DISABLED,
        BETA_LIKE
    }

    public static HungerMode hungerMode = HungerMode.DISABLED;   // 0 = vanilla version 1.11.2, 2 = vanilla 1.10.2 to 1.9?, 3 = vanilla 1.8.9? and earlier, 4 = disabled, 5 = beta-ish (instant healing, but applies potion effects like vanilla)
    public static boolean experienceEnabled;

    protected static final ResourceLocation customArmorResource = new ResourceLocation("olddays:textures/icons.png");
    private static final Minecraft minecraft = Minecraft.getMinecraft();


    public static class EventHandler
    {
        // UI-related events

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public void onPreRenderOverlay(RenderGameOverlayEvent.Pre event)
        {
            /*
            ScaledResolution resolution = event.getResolution();
            int width = resolution.getScaledWidth();
            int height = resolution.getScaledHeight();
            EntityPlayerSP player = Minecraft.getMinecraft().player;

            if (minecraft.playerController.gameIsSurvivalOrAdventure())
            {
                //drawArmorInsteadOfHunger(width, height, player.getTotalArmorValue());
                if (!(minecraft.ingameGUI instanceof GuiIngameOld))
                    minecraft.ingameGUI = new GuiIngameOld(minecraft);
            }
            */
        }


        // Hunger-related events

        /**
         * Edit the held item so that we can eat food all the time.<br/>
         * Even though we're intentionally throwing compatibility with other mods out the window, there's probably a better way to do this.
         */
        @SubscribeEvent
        public void onPlayerUseItemFood(PlayerInteractEvent event)
        {
            if (!(event.getItemStack().getItem() instanceof ItemFood)) return;

            //ItemFood food = (ItemFood) event.getItemStack().getItem();
            //EntityPlayer player = event.getEntityPlayer();

            if (event instanceof PlayerInteractEvent.RightClickBlock || event instanceof PlayerInteractEvent.RightClickItem)
            {
                if ((hungerMode == HungerMode.DISABLED || hungerMode == HungerMode.BETA_LIKE)
                    //&& (player.getHealth() + player.getAbsorptionAmount()) < (player.getMaxHealth() + player.getAbsorptionAmount())
                        )
                {
                    ((ItemFood) event.getItemStack().getItem()).setAlwaysEdible();
                }

                if (hungerMode == HungerMode.DISABLED)
                {
                    // Beta 1.7.3 and earlier didn't have potion effects on food, so remove them here
                    ((ItemFood) event.getItemStack().getItem()).setPotionEffect(null, 0);
                }
            }
        }

        @SubscribeEvent
        public void onFoodEaten(FoodEvent.FoodEaten event)
        {
            OldDaysRevisited.LOGGER.debug("onFoodEaten(): Player " + event.player.getName() + " ate food item " + event.food.getUnlocalizedName() + ".");

            ItemFood food = (ItemFood) event.food.getItem();

            if (hungerMode == HungerMode.DISABLED || GameplaySettings.hungerMode == HungerMode.BETA_LIKE)
            {
                if (food == Items.GOLDEN_APPLE)
                {
                    event.player.heal(42);
                }
                else
                {
                    event.player.heal(event.foodValues.hunger);
                }
            }

            if (GameplaySettings.hungerMode == HungerMode.DISABLED)
            {
                // Beta 1.7.3 and earlier didn't have potion effects on food, so just damage the player directly instead
                if (food == Items.SPIDER_EYE || food == Items.ROTTEN_FLESH || food == Items.POISONOUS_POTATO)
                {
                    if (event.player.getHealth() > event.foodValues.hunger)
                    {
                        event.player.attackEntityFrom(DamageSource.STARVE, event.foodValues.hunger);
                    }
                    else
                    {
                        event.player.attackEntityFrom(DamageSource.STARVE, event.player.getHealth() - 1);
                    }
                }
            }
        }


        @SubscribeEvent
        public void allowHealthRegen(HealthRegenEvent.AllowRegen event) {
            if (hungerMode == HungerMode.DISABLED || hungerMode == HungerMode.BETA_LIKE) {
                event.setResult(Event.Result.DENY);
            } else {
                event.setResult(Event.Result.ALLOW);
            }
        }

        @SubscribeEvent
        public void allowSaturatedHealthRegen(HealthRegenEvent.AllowSaturatedRegen event) {
            if (hungerMode == HungerMode.DISABLED || hungerMode == HungerMode.BETA_LIKE) {
                event.setResult(Event.Result.DENY);
            } else {
                event.setResult(Event.Result.ALLOW);
            }
        }

        @SubscribeEvent
        public void allowExhaustion(ExhaustionEvent.AllowExhaustion event) {
            if (hungerMode == HungerMode.DISABLED || hungerMode == HungerMode.BETA_LIKE) {
                event.setResult(Event.Result.DENY);
            } else {
                event.setResult(Event.Result.ALLOW);
            }
        }

        @SubscribeEvent
        public void allowStarvation(StarvationEvent.AllowStarvation event) {
            if (hungerMode == HungerMode.DISABLED || hungerMode == HungerMode.BETA_LIKE) {
                event.setResult(Event.Result.DENY);
            } else {
                event.setResult(Event.Result.ALLOW);
            }
        }

        @SubscribeEvent
        public void setRegenTickPeriod(HealthRegenEvent.GetRegenTickPeriod event) {
            if (hungerMode == HungerMode.DISABLED) {
                event.regenTickPeriod = 1;
            } else {
                event.regenTickPeriod = 80;
            }
        }

        @SubscribeEvent
        public void setSaturatedRegenTickPeriod(HealthRegenEvent.GetSaturatedRegenTickPeriod event) {
            if (hungerMode == HungerMode.DISABLED || hungerMode == HungerMode.BETA_LIKE) {
                event.regenTickPeriod = 1;
            } else {
                event.regenTickPeriod = 10;
            }
        }
    }
}
