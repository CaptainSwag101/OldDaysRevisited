package com.github.jpmac26.olddays;

import com.github.jpmac26.olddays.entities.enderman.EntityODEnderman;
import com.github.jpmac26.olddays.entities.enderman.RenderODEnderman;
import com.github.jpmac26.olddays.gui.GuiHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.main.Main;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

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
