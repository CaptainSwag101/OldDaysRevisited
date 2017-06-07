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
            int i = MathHelper.ceil(entityplayer.getHealth());
            boolean flag = this.healthUpdateCounter > (long)this.updateCounter && (this.healthUpdateCounter - (long)this.updateCounter) / 3L % 2L == 1L;

            if (i < this.playerHealth && entityplayer.hurtResistantTime > 0)
            {
                this.lastSystemTime = Minecraft.getSystemTime();
                this.healthUpdateCounter = (long)(this.updateCounter + 20);
            }
            else if (i > this.playerHealth && entityplayer.hurtResistantTime > 0)
            {
                this.lastSystemTime = Minecraft.getSystemTime();
                this.healthUpdateCounter = (long)(this.updateCounter + 10);
            }

            if (Minecraft.getSystemTime() - this.lastSystemTime > 1000L)
            {
                this.playerHealth = i;
                this.lastPlayerHealth = i;
                this.lastSystemTime = Minecraft.getSystemTime();
            }

            this.playerHealth = i;
            int j = this.lastPlayerHealth;
            this.rand.setSeed((long)(this.updateCounter * 312871));
            FoodStats foodstats = entityplayer.getFoodStats();
            int k = foodstats.getFoodLevel();
            IAttributeInstance iattributeinstance = entityplayer.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
            int l = scaledRes.getScaledWidth() / 2 - 91;
            int i1 = scaledRes.getScaledWidth() / 2 + 91;
            int j1 = scaledRes.getScaledHeight() - 39;
            float f = (float)iattributeinstance.getAttributeValue();
            int k1 = MathHelper.ceil(entityplayer.getAbsorptionAmount());
            int l1 = MathHelper.ceil((f + (float)k1) / 2.0F / 10.0F);
            int i2 = Math.max(10 - (l1 - 2), 3);
            int j2 = j1 - (l1 - 1) * i2 - 10;
            int k2 = j1 - 10;
            int l2 = k1;
            int i3 = entityplayer.getTotalArmorValue();
            int j3 = -1;

            if (entityplayer.isPotionActive(MobEffects.REGENERATION))
            {
                j3 = this.updateCounter % MathHelper.ceil(f + 5.0F);
            }

            this.mc.mcProfiler.startSection("armor");

            for (int k3 = 0; k3 < 10; ++k3)
            {
                if (i3 > 0)
                {
                    int l3 = l + k3 * 8;

                    if(hideHunger)
                    {
                        l3 -= 9;
                        j2 += 10;
                    }

                    if (k3 * 2 + 1 < i3)
                    {
                        this.drawTexturedModalRect(l3, j2, 34, 9, 9, 9);
                    }

                    if (k3 * 2 + 1 == i3)
                    {
                        if (hideHunger)
                        {
                            this.mc.getTextureManager().bindTexture(customArmorResource);
                            this.drawTexturedModalRect(l3 , j2, 0, 0, 9, 9);
                            this.mc.getTextureManager().bindTexture(ICONS);
                        }
                        else
                        {
                            this.drawTexturedModalRect(l3, j2, 25, 9, 9, 9);
                        }
                    }

                    if (k3 * 2 + 1 > i3)
                    {
                        this.drawTexturedModalRect(l3, j2, 16, 9, 9, 9);
                    }
                }
            }

            this.mc.mcProfiler.endStartSection("health");

            for (int j5 = MathHelper.ceil((f + (float)k1) / 2.0F) - 1; j5 >= 0; --j5)
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
                int k4 = l + j5 % 10 * 8;
                int l4 = j1 - j4 * i2;

                if (i <= 4)
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
                    if (j5 * 2 + 1 < j)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 54, 9 * i5, 9, 9);
                    }

                    if (j5 * 2 + 1 == j)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 63, 9 * i5, 9, 9);
                    }
                }

                if (l2 > 0)
                {
                    if (l2 == k1 && k1 % 2 == 1)
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
                    if (j5 * 2 + 1 < i)
                    {
                        this.drawTexturedModalRect(k4, l4, k5 + 36, 9 * i5, 9, 9);
                    }

                    if (j5 * 2 + 1 == i)
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
                    int j6 = j1;
                    int l6 = 16;
                    int j7 = 0;

                    if (entityplayer.isPotionActive(MobEffects.HUNGER))
                    {
                        l6 += 36;
                        j7 = 13;
                    }

                    if (entityplayer.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (k * 3 + 1) == 0)
                    {
                        j6 = j1 + (this.rand.nextInt(3) - 1);
                    }

                    int l7 = i1 - l5 * 8 - 9;
                    this.drawTexturedModalRect(l7, j6, 16 + j7 * 9, 27, 9, 9);

                    if (l5 * 2 + 1 < k)
                    {
                        this.drawTexturedModalRect(l7, j6, l6 + 36, 27, 9, 9);
                    }

                    if (l5 * 2 + 1 == k)
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
                        this.drawTexturedModalRect(i1 - k7 * 8 - 9, k2, 16, 18, 9, 9);
                    }
                    else
                    {
                        this.drawTexturedModalRect(i1 - k7 * 8 - 9, k2, 25, 18, 9, 9);
                    }
                }
            }

            this.mc.mcProfiler.endSection();
        }
    }
}
