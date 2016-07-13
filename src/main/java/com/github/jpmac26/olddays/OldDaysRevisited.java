package com.github.jpmac26.olddays;

import com.github.jpmac26.olddays.entities.enderman.EntityODEnderman;
import com.github.jpmac26.olddays.entities.enderman.RenderODEnderman;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.internal.EntitySpawnHandler;

import static net.minecraftforge.fml.common.registry.EntityRegistry.registerModEntity;

@Mod(modid = OldDaysRevisited.MODID, version = OldDaysRevisited.VERSION)
public class OldDaysRevisited
{
    public static final String MODID = "olddays";
    public static final String VERSION = "0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event) {
        //register custom stuff
        registerModEntity(EntityODEnderman.class, "Enderman", 0, this, 64, 1, true);
        EntityEventHandler entityEvents = new EntityEventHandler();
        FMLCommonHandler.instance().bus().register(entityEvents);
        registerRenderThings();
    }

    public void registerRenderThings()
    {
        RenderingRegistry.registerEntityRenderingHandler(EntityODEnderman.class, new RenderODEnderman(Minecraft.getMinecraft().getRenderManager()));
    }
}
