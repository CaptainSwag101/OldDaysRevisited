package com.jpmac26.olddays.settings;

import com.jpmac26.olddays.block.BlockFarmlandOld;
import net.minecraft.block.Block;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.GetCollisionBoxesEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by James Pelster on 7/20/2016.
 */
public class ActionSettings
{
    public static boolean oldCropBreaking = true;
    public static boolean punchTNT = false;
    public static BlockFarmlandOld blockFarmlandOld = new BlockFarmlandOld();

    //private static Map<EntityPlayer, BlockPos> playerPositionTracker;


    public static void load()
    {

    }

    public static void save()
    {

    }


    public static class EventHandler
    {
        @SubscribeEvent
        public void registerBlocks(RegistryEvent.Register<Block> event) {
            event.getRegistry().registerAll(blockFarmlandOld);
        }

        /**
         * Note: We may want to only process this event every 10 ticks or so, ot improve performance.
         * Then again, entities aren't updated every tick anyway, so maybe it's fine as-is.
         * @param event The caught event.
         */
        @SubscribeEvent
        public void onEntityWalk(LivingEvent.LivingUpdateEvent event)
        {
            EntityLivingBase entity = event.getEntityLiving();
            World world = entity.world;

            if (entity instanceof EntityPlayer)
            {
                if (entity.velocityChanged || (Math.abs(entity.motionX) > 0.005F || Math.abs(entity.motionZ) > 0.005F) || (entity.posX != entity.prevPosX || entity.posZ != entity.prevPosZ) && entity.onGround && !entity.isSneaking())
                {
                    BlockPos pos = new BlockPos(entity.posX, entity.getEntityBoundingBox().minY, entity.posZ); // The 0.2F is arbitrary, we may want to change this
                    IBlockState blockState = findBlockAt(pos, world);

                    if (blockState.getBlock() instanceof BlockFarmland && oldCropBreaking && world.rand.nextInt(4) == 0)
                    {
                        turnToDirt(world, pos); // Trample the farmland
                    }
                }
            }
            else
            {
                if (world.isRemote) return;

                if ((Math.abs(entity.motionX) > 0.005F || Math.abs(entity.motionZ) > 0.005F) && entity.onGround && !entity.isSneaking())
                {
                    BlockPos pos = new BlockPos(entity.posX, entity.getEntityBoundingBox().minY, entity.posZ); // The 0.2F is arbitrary, we may want to change this
                    IBlockState blockState = findBlockAt(pos, world);

                    if (blockState.getBlock() instanceof BlockFarmland && oldCropBreaking && world.rand.nextInt(4) == 0)
                    {
                        blockState.getBlock().onFallenUpon(world, pos, entity, 5.0F); // Trample the farmland
                    }
                }
            }
        }

        private void turnToDirt(World worldIn, BlockPos pos)
        {
            IBlockState iblockstate = Blocks.DIRT.getDefaultState();
            worldIn.setBlockState(pos, iblockstate);
            AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(worldIn, pos).offset(pos);

            for (Entity entity : worldIn.getEntitiesWithinAABBExcludingEntity((Entity)null, axisalignedbb))
            {
                entity.setPosition(entity.posX, axisalignedbb.maxY, entity.posZ);
            }
        }

        //@SubscribeEvent
        public void onPlayerWalk(TickEvent.PlayerTickEvent event)
        {
            if (event.player.world.isRemote) return;

            EntityPlayer entity = event.player;
            World world = entity.world;

            if (entity.velocityChanged || (Math.abs(entity.motionX) > 0.005F || Math.abs(entity.motionZ) > 0.005F) || (entity.posX != entity.prevPosX || entity.posZ != entity.prevPosZ) && entity.onGround && !entity.isSneaking())
            {
                BlockPos pos = new BlockPos(entity.posX, entity.getEntityBoundingBox().minY, entity.posZ); // The 0.2F is arbitrary, we may want to change this
                IBlockState blockState = findBlockAt(pos, world);

                if (blockState.getBlock() instanceof BlockFarmland && oldCropBreaking && world.rand.nextInt(4) == 0)
                {
                    blockState.getBlock().onFallenUpon(world, pos, entity, 5.0F); // Trample the farmland
                }
            }
        }

        //@SubscribeEvent
        public void onEntityCollideWithBlock(GetCollisionBoxesEvent event)
        {
            Entity entity = event.getEntity();
        }
    }

    public static IBlockState findBlockAt(BlockPos pos, World world)
    {
        return world.getBlockState(pos);
    }
}
