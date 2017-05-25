package com.jpmac26.olddays.entity.enderman;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by James Pelster on 5/24/2017.
 */
public class RenderODEndermanFactory implements IRenderFactory<EntityEnderman>
{
    public static final RenderODEndermanFactory INSTANCE = new RenderODEndermanFactory();

    @Override
    @SideOnly(Side.CLIENT)
    public Render<EntityEnderman> createRenderFor(RenderManager manager) {
        return new RenderODEnderman(manager);
    }
}
