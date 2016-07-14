package com.github.jpmac26.nbxlite.nbxlite.oldbiomes;

import java.util.List;

import com.github.jpmac26.nbxlite.nbxlite.spawners.SpawnListEntryBeta;
import com.github.jpmac26.nbxlite.nbxlite.spawners.SpawnListEntryBeta;

public class OldBiomeGenHell extends OldBiomeGenBase
{
    public OldBiomeGenHell()
    {
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableAmbientCreatureList.clear();
        spawnableMonsterList.add(new SpawnListEntryBeta(net.minecraft.src.EntityGhast.class, 10));
        spawnableMonsterList.add(new SpawnListEntryBeta(net.minecraft.src.EntityPigZombie.class, 10));
    }
}
