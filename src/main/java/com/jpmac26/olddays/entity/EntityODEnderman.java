package com.jpmac26.olddays.entity;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Set;
import javax.annotation.Nullable;

import com.google.common.collect.Sets;
import com.jpmac26.olddays.OldDaysRevisited;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

/**
 * Created by James Pelster on 7/11/2016.
 */
public class EntityODEnderman extends EntityEnderman {
    public static Set<Block> CARRIABLE_BLOCKS;
    public static Field superCarriableBlocks;
    public static Field lastCreepySound;

    public static boolean oldAppearance;
    public static boolean oldBlockStealing;
    public static boolean oldHealth;
    public static boolean oldSounds;

    // static and final are important, they ensure this can be optimized by the JVM
    protected static final MethodHandle mobLivingUpdate;

    static {
        try {
            // With "in(EntityMob.class)" we obtain access to things that are "private" in EntityMob.
            // findSpecial invokes the method in EntityMob.class without checking for overriding methods (such as the one in EntityEnderman)
            Constructor<MethodHandles.Lookup> c = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class);
            c.setAccessible(true);
            MethodHandles.Lookup lookup = c.newInstance(EntityMob.class);

            if (OldDaysRevisited.isSrg)
                mobLivingUpdate = lookup.in(EntityMob.class).findSpecial(EntityMob.class, "func_70636_d", MethodType.methodType(void.class), EntityMob.class);
            else
                mobLivingUpdate = lookup.in(EntityMob.class).findSpecial(EntityMob.class, "onLivingUpdate", MethodType.methodType(void.class), EntityMob.class);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public EntityODEnderman(World worldIn)
    {
        super(worldIn);


        Class clz;
        try {
            clz = EntityEnderman.class;
            this.superCarriableBlocks = clz.getDeclaredField("field_70827_d"); //CARRIABLE_BLOCKS
            this.superCarriableBlocks.setAccessible(true);
            this.lastCreepySound = clz.getDeclaredField("field_184720_bx"); //lastCreepySound
            this.lastCreepySound.setAccessible(true);

        } catch (NoSuchFieldException e) {
            try {
                clz = EntityEnderman.class;
                this.superCarriableBlocks = clz.getDeclaredField("CARRIABLE_BLOCKS"); //CARRIABLE_BLOCKS
                this.superCarriableBlocks.setAccessible(true);
                this.lastCreepySound = clz.getDeclaredField("lastCreepySound"); //lastCreepySound
                this.lastCreepySound.setAccessible(true);

            } catch(Exception e2) {
                OldDaysRevisited.LOGGER.error("Reflection error: Failed to modify EntityEnderman.CARRIABLE_BLOCKS. This will likely result in a crash.");
                //OldDaysRevisited.LOGGER.error(e);

            }

        } catch (Exception e) {
            OldDaysRevisited.LOGGER.error("Reflection error: Failed to modify EntityEnderman.CARRIABLE_BLOCKS. This will likely result in a crash.");

        }

        try {
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

            superCarriableBlocks.set(this, this.CARRIABLE_BLOCKS);

        } catch (Exception e) {

        }
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
        try {
            if (this.ticksExisted >= this.lastCreepySound.getInt(this) + 400) {
                this.lastCreepySound.setInt(this, this.ticksExisted);

                if (!this.isSilent() && !oldSounds) {
                    this.world.playSound(this.posX, this.posY + (double) this.getEyeHeight(), this.posZ, SoundEvents.ENTITY_ENDERMEN_STARE, this.getSoundCategory(), 2.5F, 1.0F, false);
                }
            }
        } catch (Exception e) {

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

        try {
            // invokeExact is important, it ensures that this can be optimized by the JVM
            mobLivingUpdate.invokeExact((EntityMob) this);
        } catch (Throwable x) {
            throw new RuntimeException(x);
        }
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
}
