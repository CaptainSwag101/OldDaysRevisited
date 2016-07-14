package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import java.util.Random;
import com.github.jpmac26.nbxlite.ODNBXlite;
import net.minecraft.src.Block;
import net.minecraft.src.Material;
import com.github.jpmac26.nbxlite.World;
import net.minecraft.src.WorldGenerator;

public class OldWorldGenReed extends WorldGenerator
{
    public OldWorldGenReed()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        for (int l = 0; l < 20; l++)
        {
            int i1 = (i + random.nextInt(4)) - random.nextInt(4);
            int j1 = j;
            int k1 = (k + random.nextInt(4)) - random.nextInt(4);
            if (!world.isAirBlock(i1, j1, k1) || world.getBlockMaterial(i1 - 1, j1 - 1, k1) != Material.water && world.getBlockMaterial(i1 + 1, j1 - 1, k1) != Material.water && world.getBlockMaterial(i1, j1 - 1, k1 - 1) != Material.water && world.getBlockMaterial(i1, j1 - 1, k1 + 1) != Material.water)
            {
                continue;
            }
            int l1 = 2 + random.nextInt(random.nextInt(3) + 1);
            for (int i2 = 0; i2 < l1; i2++)
            {
                if (Block.reed.canBlockStay(world, i1, j1 + i2, k1))
                {
                    if(ODNBXlite.Generator==ODNBXlite.GEN_NEWBIOMES || (world.getBlockId(i1, j1 + i2 - 1, k1) != Block.sand.blockID)){
                        world.setBlock(i1, j1 + i2, k1, Block.reed.blockID);
                    }
                }
            }
        }

        return true;
    }
}
