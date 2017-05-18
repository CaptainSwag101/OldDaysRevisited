package olddays;

import net.minecraft.entity.passive.EntitySheep;
import olddays.entities.EntityODEnderman;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import olddays.entities.EntityODSheep;

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
        if (event.getEntity() instanceof EntitySheep && !(event.getEntity() instanceof EntityODSheep))
        {
            EntityODSheep spawnEntity = new EntityODSheep(event.getWorld());
            spawnEntity.setPosition(event.getEntity().posX, event.getEntity().posY, event.getEntity().posZ);
            System.out.println("Replacing vanilla Sheep with OldDays Sheep");
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
