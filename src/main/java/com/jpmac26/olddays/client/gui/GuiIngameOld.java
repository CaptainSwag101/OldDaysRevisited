package com.jpmac26.olddays.client.gui;


import com.jpmac26.olddays.settings.GameplaySettings;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.util.FoodStats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/**
 * Created by James Pelster on 5/31/2017.
 */
public class GuiIngameOld extends GuiIngame
{
    public static boolean hideXp = false;
    public static boolean hideHunger = false;
    public static boolean noDebug = false;
    public static boolean fallbackTex = false;
    public static boolean score = false;
    public static String version = "OFF";
    protected static final ResourceLocation customArmorResource = new ResourceLocation("olddays:textures/icons.png");

    public GuiIngameOld(Minecraft mcIn)
    {
        super(mcIn);
    }

    @Override
    protected void renderPlayerStats(ScaledResolution scaledRes)
    {
        if (this.mc.getRenderViewEntity() instanceof EntityPlayer)
        {
            EntityPlayer entityplayer = (EntityPlayer)this.mc.getRenderViewEntity();
            int newPlayerHealth = MathHelper.ceil(entityplayer.getHealth());
            boolean flag = this.healthUpdateCounter > (long)this.updateCounter && (this.healthUpdateCounter - (long)this.updateCounter) / 3L % 2L == 1L;

            if (newPlayerHealth < this.playerHealth && entityplayer.hurtResistantTime > 0)
            {
                this.lastSystemTime = Minecraft.getSystemTime();
                this.healthUpdateCounter = (long)(this.updateCounter + 20);
            }
            else if (newPlayerHealth > this.playerHealth && entityplayer.hurtResistantTime > 0)
            {
                this.lastSystemTime = Minecraft.getSystemTime();
                this.healthUpdateCounter = (long)(this.updateCounter + 10);
            }

            if (Minecraft.getSystemTime() - this.lastSystemTime > 1000L)
            {
                this.playerHealth = newPlayerHealth;
                this.lastPlayerHealth = newPlayerHealth;
                this.lastSystemTime = Minecraft.getSystemTime();
            }

            this.playerHealth = newPlayerHealth;
            int lastPlayerHealth = this.lastPlayerHealth;
            this.rand.setSeed((long)(this.updateCounter * 312871));
            FoodStats foodstats = entityplayer.getFoodStats();
            int foodLevel = foodstats.getFoodLevel();
            int hotbarLeft = scaledRes.getScaledWidth() / 2 - 91;
            int hotbarRight = scaledRes.getScaledWidth() / 2 + 91;
            int hotbarTop = scaledRes.getScaledHeight() - 39;
            float playerMaxHealth = (float)entityplayer.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).getAttributeValue();
            int playerAbsorptionAmount = MathHelper.ceil(entityplayer.getAbsorptionAmount());
            int healthBarCount = MathHelper.ceil((playerMaxHealth + (float)playerAbsorptionAmount) / 2.0F / 10.0F);
            int healthBarTop = Math.max(10 - (healthBarCount - 2), 3);
            int armorBarTop = hotbarTop - (healthBarCount - 1) * healthBarTop - 10;
            int k2 = hotbarTop - 10;
            int l2 = playerAbsorptionAmount;
            int playerArmorValue = entityplayer.getTotalArmorValue();
            int j3 = -1;

            if (entityplayer.isPotionActive(MobEffects.REGENERATION))
            {
                j3 = this.updateCounter % MathHelper.ceil(playerMaxHealth + 5.0F);
            }

            this.mc.mcProfiler.startSection("armor");

            for (int iconNum = 0; iconNum < 10; ++iconNum)
            {
                /*
                if (playerArmorValue > 0)
                {
                    int l3 = hotbarLeft + iconNum * 8;

                    if(hideHunger)
                    {
                        l3 -= 9;
                        armorBarTop += 10;
                    }

                    if (iconNum * 2 + 1 < playerArmorValue)
                    {
                        this.drawTexturedModalRect(l3, armorBarTop, 34, 9, 9, 9);
                    }

                    if (iconNum * 2 + 1 == playerArmorValue)
                    {
                        if (hideHunger)
                        {
                            this.mc.getTextureManager().bindTexture(customArmorResource);
                            this.drawTexturedModalRect(l3 , armorBarTop, 0, 0, 9, 9);
                            this.mc.getTextureManager().bindTexture(ICONS);
                        }
                        else
                        {
                            this.drawTexturedModalRect(l3, armorBarTop, 25, 9, 9, 9);
                        }
                    }

                    if (iconNum * 2 + 1 > playerArmorValue)
                    {
                        this.drawTexturedModalRect(l3, armorBarTop, 16, 9, 9, 9);
                    }
                }
                */


                if (entityplayer.getTotalArmorValue() > 0)
                {
                    //int drawX = hotbarLeft + iconNum * 8;
                    int drawX = hotbarRight - iconNum * 8 - 9;

                    //draw full armor icon
                    if (iconNum * 2 + 1 < playerArmorValue)
                    {
                        this.drawTexturedModalRect(drawX, hotbarTop, 34, 9, 9, 9);
                    }

                    //draw half armor icon
                    if (iconNum * 2 + 1 == playerArmorValue)
                    {
                        this.drawTexturedModalRect(drawX, hotbarTop, 25, 9, 9, 9);
                    }

                    //draw empty armor icon
                    if (iconNum * 2 + 1 > playerArmorValue)
                    {
                       this.drawTexturedModalRect(drawX, hotbarTop, 16, 9, 9, 9);
                    }
                }
            }

            this.mc.mcProfiler.endStartSection("health");

            for (int j5 = MathHelper.ceil((playerMaxHealth + (float)playerAbsorptionAmount) / 2.0F) - 1; j5 >= 0; --j5)
            {
                int k5 = 16;

                if (entityplayer.isPotionActive(MobEffects.POISON))
                {
                    k5 += 36;
                }
                else if (entityplayer.isPotionActive(MobEffects.WITHER))
                {
                    k5 += 72;
                }

                int i4 = 0;

                if (flag)
                {
                    i4 = 1;
                }

                int j4 = MathHelper.ceil((float)(j5 + 1) / 10.0F) - 1;
                int k4 = hotbarLeft + j5 % 10 * 8;
                int l4 = hotbarTop - j4 * healthBarTop;

                if (newPlayerHealth <= 4)
                {
                    l4 += this.rand.nextInt(2);
                }

                if (l2 <= 0 && j5 == j3)
                {
                    l4 -= 2;
                }

                int i5 = 0;

                if (entityplayer.world.getWorldInfo().isHardcoreModeEnabled())
                {
                    i5 = 5;
                }

                this.drawTexturedModalRect(k4, l4, 16 + i4 * 9, 9 * i5, 9, 9);

                if (flag)
                {
                    if (j5 * 2 + 1 < lastPlayerHealth)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 54, 9 * i5, 9, 9);
                    }

                    if (j5 * 2 + 1 == lastPlayerHealth)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 63, 9 * i5, 9, 9);
                    }
                }

                if (l2 > 0)
                {
                    if (l2 == playerAbsorptionAmount && playerAbsorptionAmount % 2 == 1)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 153, 9 * i5, 9, 9);
                        --l2;
                    }
                    else
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 144, 9 * i5, 9, 9);
                        l2 -= 2;
                    }
                }
                else
                {
                    if (j5 * 2 + 1 < newPlayerHealth)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 36, 9 * i5, 9, 9);
                    }

                    if (j5 * 2 + 1 == newPlayerHealth)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 45, 9 * i5, 9, 9);
                    }
                }
            }

            Entity entity = entityplayer.getRidingEntity();

            if ((GameplaySettings.hungerMode != GameplaySettings.HungerMode.DISABLED || GameplaySettings.hungerMode != GameplaySettings.HungerMode.BETA_LIKE)
                    && (entity == null || !(entity instanceof EntityLivingBase)))
            {
                this.mc.mcProfiler.endStartSection("food");

                for (int l5 = 0; l5 < 10; ++l5)
                {
                    int j6 = hotbarTop;
                    int l6 = 16;
                    int j7 = 0;

                    if (entityplayer.isPotionActive(MobEffects.HUNGER))
                    {
                        l6 += 36;
                        j7 = 13;
                    }

                    if (entityplayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (foodLevel * 3 + 1) == 0)
                    {
                        j6 = hotbarTop + (this.rand.nextInt(3) - 1);
                    }

                    int l7 = hotbarRight - l5 * 8 - 9;
                    this.drawTexturedModalRect(l7, j6, 16 + j7 * 9, 27, 9, 9);

                    if (l5 * 2 + 1 < foodLevel)
                    {
                        this.drawTexturedModalRect(l7, j6, l6 + 36, 27, 9, 9);
                    }

                    if (l5 * 2 + 1 == foodLevel)
                    {
                        this.drawTexturedModalRect(l7, j6, l6 + 45, 27, 9, 9);
                    }
                }
            }

            this.mc.mcProfiler.endStartSection("air");

            if (entityplayer.isInsideOfMaterial(Material.WATER))
            {
                int i6 = this.mc.player.getAir();
                int k6 = MathHelper.ceil((double)(i6 - 2) * 10.0D / 300.0D);
                int i7 = MathHelper.ceil((double)i6 * 10.0D / 300.0D) - k6;

                for (int k7 = 0; k7 < k6 + i7; ++k7)
                {
                    if (k7 < k6)
                    {
                        this.drawTexturedModalRect(hotbarRight - k7 * 8 - 9, k2, 16, 18, 9, 9);
                    }
                    else
                    {
                        this.drawTexturedModalRect(hotbarRight - k7 * 8 - 9, k2, 25, 18, 9, 9);
                    }
                }
            }

            this.mc.mcProfiler.endSection();
        }
    }
}
