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

public class OldGenTaiga2 extends WorldGenAbstractTree
{

    public OldGenTaiga2()
    {
    	super(false);
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        int l = random.nextInt(4) + 6;
        int i1 = 1 + random.nextInt(2);
        int j1 = l - i1;
        int k1 = 2 + random.nextInt(2);
        boolean flag = true;
        if(j < 1 || j + l + 1 > 128)
        {
            return false;
        }
        for(int l1 = j; l1 <= j + 1 + l && flag; l1++)
        {
            int j2 = 1;
            if(l1 - j < i1)
            {
                j2 = 0;
            } else
            {
                j2 = k1;
            }
            for(int l2 = i - j2; l2 <= i + j2 && flag; l2++)
            {
                for(int j3 = k - j2; j3 <= k + j2 && flag; j3++)
                {
                    if(l1 >= 0 && l1 < 128)
                    {
                        Block k3 = world.getBlock(l2, l1, j3);
                        if(k3 != Blocks.air && k3 != Blocks.leaves)
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
        Block i2 = world.getBlock(i, j - 1, k);
        if(i2 != Blocks.grass && i2 != Blocks.dirt || j >= 128 - l - 1)
        {
            return false;
        }
        world.setBlock(i, j - 1, k, Blocks.dirt);
        int k2 = random.nextInt(2);
        int i3 = 1;
        boolean flag1 = false;
        for(int l3 = 0; l3 <= j1; l3++)
        {
            int j4 = (j + l) - l3;
            for(int l4 = i - k2; l4 <= i + k2; l4++)
            {
                int j5 = l4 - i;
                for(int k5 = k - k2; k5 <= k + k2; k5++)
                {
                    int l5 = k5 - k;
                    if((Math.abs(j5) != k2 || Math.abs(l5) != k2 || k2 <= 0) && !world.getBlock(l4, j4, k5).isOpaqueCube())
                    {
                        world.setBlock(l4, j4, k5, Blocks.leaves, 1, 2);
                    }
                }

            }

            if(k2 >= i3)
            {
                k2 = ((flag1) ? 1 : 0);
                flag1 = true;
                if(++i3 > k1)
                {
                    i3 = k1;
                }
            } else
            {
                k2++;
            }
        }

        int i4 = random.nextInt(3);
        for(int k4 = 0; k4 < l - i4; k4++)
        {
            Block i5 = world.getBlock(i, j + k4, k);
            if(i5 == Blocks.air || i5 == Blocks.leaves)
            {
                world.setBlock(i, j + k4, k, Blocks.log, 1, 2);
            }
        }

        return true;
    }
}
