package com.github.jpmac26.nbxlite.nbxlite.oldbiomes;

import java.util.List;
import java.util.Random;
import com.github.jpmac26.nbxlite.nbxlite.spawners.SpawnListEntryBeta;
import com.github.jpmac26.nbxlite.nbxlite.mapgens.OldWorldGenForest;
import com.github.jpmac26.nbxlite.nbxlite.mapgens.OldWorldGenTrees;
import com.github.jpmac26.nbxlite.WorldGenBigTree;
import net.minecraft.src.WorldGenerator;

public class OldBiomeGenForest extends OldBiomeGenBase
{
    public OldBiomeGenForest()
    {
        spawnableCreatureList.add(new SpawnListEntryBeta(net.minecraft.src.EntityWolf.class, 2));
    }

    @Override
    public WorldGenerator getRandomWorldGenForTrees(Random random)
    {
        if(random.nextInt(5) == 0)
        {
            return new OldWorldGenForest(false);
        }
        if(random.nextInt(3) == 0)
        {
            return new WorldGenBigTree(false);
        } else
        {
            return new OldWorldGenTrees(false);
        }
    }
}
