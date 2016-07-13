package com.github.jpmac26.olddays;

import com.github.jpmac26.olddays.entities.enderman.EntityODEnderman;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class EntityEventHandler {

    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityEnderman) //&& !(event.getEntity() instanceof EntityODEnderman))
        {
            EntityODEnderman spawnEntity = new EntityODEnderman(event.getWorld());
            spawnEntity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
            System.out.println("Replacing vanilla Enderman with OldDays Enderman");
            event.getWorld().spawnEntityInWorld(spawnEntity);
        }
    }
}
