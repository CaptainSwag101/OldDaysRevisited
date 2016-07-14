package com.github.jpmac26.nbxlite.nbxlite.spawners;

import java.util.Random;
import com.github.jpmac26.nbxlite.Chunk;
import net.minecraft.src.ChunkPosition;
import net.minecraft.src.EnumCreatureType;
import com.github.jpmac26.nbxlite.World;

public class OldSpawnerMonsters extends OldSpawnerAnimals
{
    public OldSpawnerMonsters(int i, EnumCreatureType type)
    {
        super(i, type);
    }

    @Override
    protected ChunkPosition func_1151_a(World world, int i, int j)
    {
        int k = i + world.rand.nextInt(16);
        int l = world.rand.nextInt(world.rand.nextInt(getWorldHeight(world, i, j) - 8) + 8);
        int i1 = j + world.rand.nextInt(16);
        return new ChunkPosition(k, l, i1);
    }
}
