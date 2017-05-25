package owg.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class OldGenReed extends WorldGenerator
{

    public OldGenReed()
    {
    }

    public boolean generate(World world, Random random, int i, int j, int k)
    {
        for(int l = 0; l < 20; l++)
        {
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = j;
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if(!world.isAirBlock(i1, j1, k1) || world.getBlock(i1 - 1, j1 - 1, k1).getMaterial() != Material.water && world.getBlock(i1 + 1, j1 - 1, k1).getMaterial() != Material.water && world.getBlock(i1, j1 - 1, k1 - 1).getMaterial() != Material.water && world.getBlock(i1, j1 - 1, k1 + 1).getMaterial() != Material.water)
            {
                continue;
            }
            int l1 = 2 + random.nextInt(random.nextInt(3) + 1);
            for(int i2 = 0; i2 < l1; i2++)
            {
                if(canBlockStay(world, i1, j1 + i2, k1))
                {
                    world.setBlock(i1, j1 + i2, k1, Blocks.reeds);
                }
            }

        }

        return true;
    }
    
    public boolean canBlockStay(World world, int i, int j, int k)
    {
        Block l = world.getBlock(i, j - 1, k);
        if(l == Blocks.reeds)
        {
            return true;
        }
        if(l != Blocks.grass && l != Blocks.dirt)
        {
            return false;
        }
        if(world.getBlock(i - 1, j - 1, k).getMaterial() == Material.water)
        {
            return true;
        }
        if(world.getBlock(i + 1, j - 1, k).getMaterial() == Material.water)
        {
            return true;
        }
        if(world.getBlock(i, j - 1, k - 1).getMaterial() == Material.water)
        {
            return true;
        }
        return world.getBlock(i, j - 1, k + 1).getMaterial() == Material.water;
    }
}
