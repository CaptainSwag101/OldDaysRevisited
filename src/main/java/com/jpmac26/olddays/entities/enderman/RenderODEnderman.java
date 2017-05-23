package com.jpmac26.olddays.entities.enderman;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import com.jpmac26.olddays.entities.EntityODEnderman;

import java.util.Random;

/**
 * Created by James Pelster on 7/11/2016.
 */
@SideOnly(Side.CLIENT)
public class RenderODEnderman extends RenderLiving<EntityODEnderman>
{
    private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
    /** The model of the enderman */
    private final ModelEnderman endermanModel;
    private final Random rnd = new Random();

    public RenderODEnderman(RenderManager renderManagerIn)
    {
        super(renderManagerIn, new ModelEnderman(0.0F), 0.5F);
        this.endermanModel = (ModelEnderman)super.mainModel;
        this.addLayer(new LayerODEndermanEyes(this));
        this.addLayer(new LayerODHeldBlock(this));
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    @Override
    public void doRender(EntityODEnderman entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        IBlockState iblockstate = entity.getHeldBlockState();
        this.endermanModel.isCarrying = iblockstate != null;
        this.endermanModel.isAttacking = entity.isScreaming();

        if (entity.isScreaming())
        {
            double d0 = 0.02D;
            x += this.rnd.nextGaussian() * d0;
            z += this.rnd.nextGaussian() * d0;
        }

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Override
    protected ResourceLocation getEntityTexture(EntityODEnderman entity)
    {
        return ENDERMAN_TEXTURES;
    }
}