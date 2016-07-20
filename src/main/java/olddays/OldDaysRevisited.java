package olddays;

import olddays.entities.enderman.EntityODEnderman;
import olddays.entities.enderman.RenderODEnderman;
import olddays.client.gui.GuiHandler;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;

/**
 * Created by James Pelster on 7/11/2016.
 */
@Mod(modid = OldDaysRevisited.MODID, version = OldDaysRevisited.VERSION)
public class OldDaysRevisited
{
    public static final String MODID = "olddays";
    public static final String VERSION = "0.1";
    protected KeyHandler keyHandler;
    protected GuiHandler guiHandler;
    protected EntityEventHandler entityEventHandler;
    @Mod.Instance("olddays")
    public static OldDaysRevisited olddays;
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //register custom stuff
        registerModEntity(EntityODEnderman.class, "Enderman", 0, this, 64, 1, true);

    }

    @EventHandler
    public void load(FMLInitializationEvent event) {
        entityEventHandler = new EntityEventHandler();
        FMLCommonHandler.instance().bus().register(entityEventHandler);

        registerRenderThings();

        guiHandler = new GuiHandler();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, guiHandler);
        FMLCommonHandler.instance().bus().register(guiHandler);

        keyHandler = new KeyHandler();
        FMLCommonHandler.instance().bus().register(keyHandler);
    }


    @SideOnly(Side.CLIENT)
    public void registerRenderThings()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityODEnderman.class, new RenderODEnderman(FMLClientHandler.instance().getClient().getRenderManager()));
    }
}
