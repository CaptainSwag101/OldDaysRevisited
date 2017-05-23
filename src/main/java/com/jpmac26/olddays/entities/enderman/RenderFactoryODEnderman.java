package com.jpmac26.olddays.entities.enderman;

import com.jpmac26.olddays.entities.EntityODEnderman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

/**
 * Created by James Pelster on 5/23/17.
 */
public class RenderFactoryODEnderman implements IRenderFactory<EntityODEnderman>
{
    public Render<EntityODEnderman> createRenderFor(RenderManager manager) {
        return new RenderODEnderman(manager);
    }
}
