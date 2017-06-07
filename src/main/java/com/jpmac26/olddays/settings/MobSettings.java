package com.jpmac26.olddays.settings;

import com.jpmac26.olddays.OldDaysRevisited;
import com.jpmac26.olddays.entity.EntityEndermanOld;
import com.jpmac26.olddays.entity.EntitySheepOld;
import com.jpmac26.olddays.settings.mob.EndermanSettings;
import com.jpmac26.olddays.settings.mob.SheepSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by James Pelster on 5/23/17.
 */
public class MobSettings
{
    public static void load()
    {
        EndermanSettings.load();
        SheepSettings.load();
    }

    public static void save()
    {
        EndermanSettings.save();
        SheepSettings.save();
    }

    public static class EventHandler
    {
        @SubscribeEvent
        public void onEntitySpawn (EntityJoinWorldEvent event)
        {
            if (event.getEntity() instanceof EntityEnderman && !(event.getEntity() instanceof EntityEndermanOld))
            {
                Entity old = event.getEntity();
                EntityEndermanOld e = new EntityEndermanOld(event.getWorld());
                e.setPosition(old.posX, old.posY, old.posZ);
                e.setPositionAndRotation(old.posX, old.posY, old.posZ, old.rotationYaw, old.rotationPitch);
                OldDaysRevisited.LOGGER.debug("Replacing vanilla Enderman with OldDays Enderman");
                event.getWorld().spawnEntity(e);
                event.setCanceled(true);
            }

            if (event.getEntity() instanceof EntitySheep && !(event.getEntity() instanceof EntitySheepOld))
            {
                Entity old = event.getEntity();
                EntitySheepOld e = new EntitySheepOld(event.getWorld());
                e.setPositionAndRotation(old.posX, old.posY, old.posZ, old.rotationYaw, old.rotationPitch);
                OldDaysRevisited.LOGGER.debug("Replacing vanilla Sheep with OldDays Sheep");
                event.getWorld().spawnEntity(e);
                event.setCanceled(true);
            }
        }
    }
}
