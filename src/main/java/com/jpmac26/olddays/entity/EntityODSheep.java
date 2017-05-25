package com.jpmac26.olddays.entity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

/**
 * Created by James Pelster on 7/23/2016.
 */
public class EntityODSheep extends EntitySheep {
    public static boolean oldHealth;
    public static boolean punchToShear;

    public EntityODSheep(World worldIn)
    {
        super(worldIn);
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if (punchToShear && source.getSourceOfDamage() instanceof EntityPlayer && !this.getSheared() && !this.isChild())
        {
            if (!this.world.isRemote)
            {
                this.setSheared(true);
                int i = 1 + this.rand.nextInt(3);

                for (int j = 0; j < i; ++j)
                {
                    EntityItem entityitem = this.entityDropItem(new ItemStack(Item.getItemFromBlock(Blocks.WOOL), 1, this.getFleeceColor().getMetadata()), 1.0F);
                    entityitem.motionY += (double)(this.rand.nextFloat() * 0.05F);
                    entityitem.motionX += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                    entityitem.motionZ += (double)((this.rand.nextFloat() - this.rand.nextFloat()) * 0.1F);
                }
            }
        }
        return super.attackEntityFrom(source, amount);
    }
}
