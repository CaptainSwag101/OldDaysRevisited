package com.jpmac26.olddays;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.passive.EntitySheep;
import com.jpmac26.olddays.entities.EntityODEnderman;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import com.jpmac26.olddays.entities.EntityODSheep;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class ODEventHandler {
	@SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityEnderman && !(event.getEntity() instanceof EntityODEnderman))
        {
            EntityODEnderman e = new EntityODEnderman(event.getWorld());
            e.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
            System.out.println("Replacing vanilla Enderman with OldDays Enderman");
            event.getWorld().spawnEntity(e);
            event.setCanceled(true);
        }
        if (event.getEntity() instanceof EntitySheep && !(event.getEntity() instanceof EntityODSheep))
        {
            EntityODSheep e = new EntityODSheep(event.getWorld());
            e.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
            System.out.println("Replacing vanilla Sheep with OldDays Sheep");
            event.getWorld().spawnEntity(e);
            event.setCanceled(true);
        }
    }

    /*
    @SubscribeEvent
    public void onSound(PlaySoundEvent event)
    {
    	if(event.getName() == "block.stone.step")
    	{
            SoundEvent soundevent = SoundEvent.REGISTRY.getObject(new ResourceLocation("block.sand.step"));
    		Minecraft.getMinecraft().player.playSound(soundevent, Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.BLOCKS), 0.0F );
    	}
    }
    */
}
