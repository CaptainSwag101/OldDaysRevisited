// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package owg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

// Referenced classes of package net.minecraft.src:
//            WorldGenerator, World, Block, BlockLeaves, 
//            BlockGrass

public class OldGenForest extends WorldGenAbstractTree
{

    public OldGenForest()
    {
    	super(false);
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(3) + 5;
        boolean flag = true;
        if(j < 1 || j + l + 1 > 128)
        {
            return false;
        }
        for(int i1 = j; i1 <= j + 1 + l; i1++)
        {
            byte byte0 = 1;
            if(i1 == j)
            {
                byte0 = 0;
            }
            if(i1 >= (j + 1 + l) - 2)
            {
                byte0 = 2;
            }
            for(int i2 = i - byte0; i2 <= i + byte0 && flag; i2++)
            {
                for(int l2 = k - byte0; l2 <= k + byte0 && flag; l2++)
                {
                    if(i1 >= 0 && i1 < 128)
                    {
                        Block j3 = world.getBlock(i2, i1, l2);
                        if(j3 != Blocks.air && j3 != Blocks.leaves)
                        {
                            flag = false;
                        }
                    } else
                    {
                        flag = false;
                    }
                }

            }

        }

        if(!flag)
        {
            return false;
        }
        Block j1 = world.getBlock(i, j - 1, k);
        if(j1 != Blocks.grass && j1 != Blocks.dirt || j >= 128 - l - 1)
        {
            return false;
        }
        world.setBlock(i, j - 1, k, Blocks.dirt);
        for(int k1 = (j - 3) + l; k1 <= j + l; k1++)
        {
            int j2 = k1 - (j + l);
            int i3 = 1 - j2 / 2;
            for(int k3 = i - i3; k3 <= i + i3; k3++)
            {
                int l3 = k3 - i;
                for(int i4 = k - i3; i4 <= k + i3; i4++)
                {
                    int j4 = i4 - k;
                    if((Math.abs(l3) != i3 || Math.abs(j4) != i3 || random.nextInt(2) != 0 && j2 != 0) && !world.getBlock(k3, k1, i4).isOpaqueCube())
                    {
                        world.setBlock(k3, k1, i4, Blocks.leaves, 2, 2);
                    }
                }

            }

        }

        for(int l1 = 0; l1 < l; l1++)
        {
            Block k2 = world.getBlock(i, j + l1, k);
            if(k2 == Blocks.air || k2 == Blocks.leaves)
            {
                world.setBlock(i, j + l1, k, Blocks.log, 2, 2);
            }
        }

        return true;
    }
}
