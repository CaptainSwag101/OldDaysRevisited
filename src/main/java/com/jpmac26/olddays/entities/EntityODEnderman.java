package com.jpmac26.olddays.entities;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

/**
 * Created by James Pelster on 7/11/2016.
 */
public class EntityODEnderman extends EntityEnderman {
    public static boolean oldAppearance;
    public static boolean oldBlockStealing;
    public static boolean oldHealth;
    public static boolean oldSounds;

    public EntityODEnderman(World worldIn)
    {
        super(worldIn);
    }

    @Override
    protected void initEntityAI()
    {
        super.initEntityAI();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        if (oldHealth)
            this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    }

    @Override
    public void playEndermanSound()
    {
        if (this.ticksExisted >= this.lastCreepySound + 400)
        {
            this.lastCreepySound = this.ticksExisted;

            if (!this.isSilent() && !oldSounds)
            {
                this.world.playSound(this.posX, this.posY + (double)this.getEyeHeight(), this.posZ, SoundEvents.ENTITY_ENDERMEN_STARE, this.getSoundCategory(), 2.5F, 1.0F, false);
            }
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    @Override
    public void onLivingUpdate()
    {
        if (this.world.isRemote)
        {
            for (int i = 0; i < 2; ++i)
            {
                if (oldAppearance) {
                    this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, 0.0D, 0.0D, 0.0D);
                } else {
                    this.world.spawnParticle(EnumParticleTypes.PORTAL, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D, new int[0]);
                }
            }
        }

        this.isJumping = false;
        super.onLivingUpdate();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return !oldSounds ? (this.isScreaming() ? SoundEvents.ENTITY_ENDERMEN_SCREAM : SoundEvents.ENTITY_ENDERMEN_AMBIENT) : SoundEvents.ENTITY_ZOMBIE_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound()
    {
        return !oldSounds ? SoundEvents.ENTITY_ENDERMEN_HURT : SoundEvents.ENTITY_ZOMBIE_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return !oldSounds ? SoundEvents.ENTITY_ENDERMEN_DEATH : SoundEvents.ENTITY_ZOMBIE_DEATH;
    }

    /**
     * Drop the equipment for this entity.
     */
    @Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
    {
        super.dropEquipment(wasRecentlyHit, lootingModifier);
        IBlockState iblockstate = this.getHeldBlockState();

        if (iblockstate != null)
        {
            Item item = Item.getItemFromBlock(iblockstate.getBlock());

            if (item != null)
            {
                int i = item.getHasSubtypes() ? iblockstate.getBlock().getMetaFromState(iblockstate) : 0;
                this.entityDropItem(new ItemStack(item, 1, i), 0.0F);
            }
        }
    }

    @Nullable
    @Override
    protected ResourceLocation getLootTable()
    {
        return LootTableList.ENTITIES_ENDERMAN;
    }

    static
    {
        if (oldBlockStealing) {
            CARRIABLE_BLOCKS.clear();
            CARRIABLE_BLOCKS.add(Blocks.GRASS);
            CARRIABLE_BLOCKS.add(Blocks.DIRT);
            CARRIABLE_BLOCKS.add(Blocks.SAND);
            CARRIABLE_BLOCKS.add(Blocks.GRAVEL);
            CARRIABLE_BLOCKS.add(Blocks.YELLOW_FLOWER);
            CARRIABLE_BLOCKS.add(Blocks.RED_FLOWER);
            CARRIABLE_BLOCKS.add(Blocks.BROWN_MUSHROOM);
            CARRIABLE_BLOCKS.add(Blocks.RED_MUSHROOM);
            CARRIABLE_BLOCKS.add(Blocks.TNT);
            CARRIABLE_BLOCKS.add(Blocks.CACTUS);
            CARRIABLE_BLOCKS.add(Blocks.CLAY);
            CARRIABLE_BLOCKS.add(Blocks.PUMPKIN);
            CARRIABLE_BLOCKS.add(Blocks.MELON_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.MYCELIUM);
            CARRIABLE_BLOCKS.add(Blocks.NETHERRACK);
            CARRIABLE_BLOCKS.add(Blocks.STONE);
            CARRIABLE_BLOCKS.add(Blocks.COBBLESTONE);
            CARRIABLE_BLOCKS.add(Blocks.PLANKS);
            CARRIABLE_BLOCKS.add(Blocks.COAL_ORE);
            CARRIABLE_BLOCKS.add(Blocks.COAL_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.DIAMOND_ORE);
            CARRIABLE_BLOCKS.add(Blocks.DIAMOND_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.EMERALD_ORE);
            CARRIABLE_BLOCKS.add(Blocks.EMERALD_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.GOLD_ORE);
            CARRIABLE_BLOCKS.add(Blocks.GOLD_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.IRON_ORE);
            CARRIABLE_BLOCKS.add(Blocks.IRON_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.REDSTONE_ORE);
            CARRIABLE_BLOCKS.add(Blocks.REDSTONE_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.LOG);
            CARRIABLE_BLOCKS.add(Blocks.LEAVES);
            CARRIABLE_BLOCKS.add(Blocks.SPONGE);
            CARRIABLE_BLOCKS.add(Blocks.GLASS);
            CARRIABLE_BLOCKS.add(Blocks.LAPIS_ORE);
            CARRIABLE_BLOCKS.add(Blocks.LAPIS_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.SANDSTONE);
            CARRIABLE_BLOCKS.add(Blocks.WOOL);
            CARRIABLE_BLOCKS.add(Blocks.BROWN_MUSHROOM_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.RED_MUSHROOM_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.BRICK_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.BOOKSHELF);
            CARRIABLE_BLOCKS.add(Blocks.MOSSY_COBBLESTONE);
            CARRIABLE_BLOCKS.add(Blocks.CRAFTING_TABLE);
            CARRIABLE_BLOCKS.add(Blocks.ICE);
            CARRIABLE_BLOCKS.add(Blocks.SOUL_SAND);
            CARRIABLE_BLOCKS.add(Blocks.GLOWSTONE);
            CARRIABLE_BLOCKS.add(Blocks.LIT_PUMPKIN);
            CARRIABLE_BLOCKS.add(Blocks.STONEBRICK);
        } else {
            CARRIABLE_BLOCKS.clear();
            CARRIABLE_BLOCKS.add(Blocks.GRASS);
            CARRIABLE_BLOCKS.add(Blocks.DIRT);
            CARRIABLE_BLOCKS.add(Blocks.SAND);
            CARRIABLE_BLOCKS.add(Blocks.GRAVEL);
            CARRIABLE_BLOCKS.add(Blocks.YELLOW_FLOWER);
            CARRIABLE_BLOCKS.add(Blocks.RED_FLOWER);
            CARRIABLE_BLOCKS.add(Blocks.BROWN_MUSHROOM);
            CARRIABLE_BLOCKS.add(Blocks.RED_MUSHROOM);
            CARRIABLE_BLOCKS.add(Blocks.TNT);
            CARRIABLE_BLOCKS.add(Blocks.CACTUS);
            CARRIABLE_BLOCKS.add(Blocks.CLAY);
            CARRIABLE_BLOCKS.add(Blocks.PUMPKIN);
            CARRIABLE_BLOCKS.add(Blocks.MELON_BLOCK);
            CARRIABLE_BLOCKS.add(Blocks.MYCELIUM);
            CARRIABLE_BLOCKS.add(Blocks.NETHERRACK);
        }
    }
}
