package com.github.jpmac26.nbxlite.nbxlite.oldbiomes;

import java.util.List;
import com.github.jpmac26.nbxlite.nbxlite.spawners.SpawnListEntryBeta;

public class OldBiomeGenSky extends OldBiomeGenBase
{
    public OldBiomeGenSky()
    {
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableAmbientCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntryBeta(net.minecraft.src.EntityChicken.class, 10));
    }

    @Override
    public int getSkyColorByTemp(float f)
    {
        return 0xc0c0ff;
    }
}
