package olddays;

import olddays.entities.enderman.EntityODEnderman;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStone;
import net.minecraft.block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.PlaySoundAtEntityEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class ODEventHandler {

    private EntityPlayer x;
	private double y;
	private double z;

	@SubscribeEvent
    public void onEntitySpawn(EntityJoinWorldEvent event)
    {
        if (event.getEntity() instanceof EntityEnderman) //&& !(event.getEntity() instanceof EntityODEnderman))
        {
            EntityODEnderman spawnEntity = new EntityODEnderman(event.getWorld());
            spawnEntity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
            System.out.println("Replacing vanilla Enderman with OldDays Enderman");
            event.getWorld().spawnEntityInWorld(spawnEntity);
            event.setCanceled(true);
        }
    }
    
    /* @SubscribeEvent
    public void onSound (PlaySoundEvent eventsound)
    {
    	if(eventsound.getName() == "block.stone.step")
    	{
            SoundEvent soundevent = (SoundEvent)SoundEvent.REGISTRY.getObject(new ResourceLocation("block.sand.step"));
    		Minecraft.getMinecraft().thePlayer.playSound(soundevent, Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.BLOCKS), 0.0F );
    	}
    	
    } */
    
}
