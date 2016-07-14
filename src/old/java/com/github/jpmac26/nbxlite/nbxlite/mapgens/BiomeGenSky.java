package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import com.github.jpmac26.nbxlite.BiomeGenBase;
import net.minecraft.src.SpawnListEntry;
import net.minecraft.src.EntityChicken;

public class BiomeGenSky extends BiomeGenBase
{
    public BiomeGenSky(int i)
    {
        super(i);
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
        spawnableCreatureList.add(new SpawnListEntry(net.minecraft.src.EntityChicken.class, 10, 4, 4));
    }

    public int getSkyColorByTemp(float f)
    {
        return 0xc0c0ff;
    }
}
