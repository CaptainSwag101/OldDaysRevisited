package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import java.util.Random;
import net.minecraft.src.*;

public class OldWorldGenFlowers extends WorldGenerator
{
    /** The ID of the plant block used in this plant generator. */
    private int plantBlockId;

    public OldWorldGenFlowers(int par1)
    {
        plantBlockId = par1;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        for (int i = 0; i < 64; i++)
        {
            int j = (par3 + par2Random.nextInt(8)) - par2Random.nextInt(8);
            int k = (par4 + par2Random.nextInt(4)) - par2Random.nextInt(4);
            int l = (par5 + par2Random.nextInt(8)) - par2Random.nextInt(8);

            boolean old1 = ODNBXlite.MapFeatures < ODNBXlite.FEATURES_14 || ODNBXlite.Generator < ODNBXlite.GEN_NEWBIOMES;
            boolean old2 = ODNBXlite.MapFeatures < ODNBXlite.FEATURES_13 || ODNBXlite.Generator < ODNBXlite.GEN_NEWBIOMES;
            boolean oldc = ((BlockFlower)Block.blocksList[plantBlockId]).canBlockStay(par1World, j, k, l);
            boolean newc = Block.blocksList[plantBlockId].canBlockStay(par1World, j, k, l);
            if (par1World.isAirBlock(j, k, l) && (old1 || !par1World.provider.hasNoSky || k < 127) && ((newc && !old2) || (old2 && oldc))){
                par1World.setBlock(j, k, l, plantBlockId);
            }
        }

        return true;
    }
}
