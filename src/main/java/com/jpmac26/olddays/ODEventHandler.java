package com.jpmac26.olddays;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import com.jpmac26.olddays.entity.EntityODEnderman;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.jpmac26.olddays.entity.EntityODSheep;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class ODEventHandler
{
    @SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event) {
        if (event.getEntity() instanceof EntityEnderman && !(event.getEntity() instanceof EntityODEnderman))
        {
            Entity old = event.getEntity();
            EntityODEnderman e = new EntityODEnderman(event.getWorld());
            e.setPosition(old.posX, old.posY, old.posZ);
            e.setPositionAndRotation(old.posX, old.posY, old.posZ, old.rotationYaw, old.rotationPitch);
            OldDaysRevisited.LOGGER.debug("Replacing vanilla Enderman with OldDays Enderman");
            event.getWorld().spawnEntity(e);
            event.setCanceled(true);
        }
        if (event.getEntity() instanceof EntitySheep && !(event.getEntity() instanceof EntityODSheep))
        {
            Entity old = event.getEntity();
            EntityODSheep e = new EntityODSheep(event.getWorld());
            e.setPositionAndRotation(old.posX, old.posY, old.posZ, old.rotationYaw, old.rotationPitch);
            OldDaysRevisited.LOGGER.debug("Replacing vanilla Sheep with OldDays Sheep");
            event.getWorld().spawnEntity(e);
            event.setCanceled(true);
        }
    }

    /*
    @SubscribeEvent
    public void onSound(PlaySoundEvent event) {
    	if(event.getName() == "block.stone.step")
    	{
            SoundEvent soundevent = SoundEvent.REGISTRY.getObject(new ResourceLocation("block.sand.step"));
    		Minecraft.getMinecraft().player.playSound(soundevent, Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.BLOCKS), 0.0F );
    	}
    }
    */
}
