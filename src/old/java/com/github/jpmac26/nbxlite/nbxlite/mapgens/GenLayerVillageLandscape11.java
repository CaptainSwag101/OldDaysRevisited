package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import com.github.jpmac26.nbxlite.GenLayer;
import net.minecraft.src.IntCache;
import com.github.jpmac26.nbxlite.BiomeGenBase;

public class GenLayerVillageLandscape11 extends GenLayer
{
    private BiomeGenBase allowedBiomes[];

    public GenLayerVillageLandscape11(long l, GenLayer genlayer)
    {
        super(l);
        allowedBiomes = (new BiomeGenBase[]
                {
                    BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga
                });
        parent = genlayer;
    }

    @Override
    public int[] getInts(int i, int j, int k, int l)
    {
        int ai[] = parent.getInts(i, j, k, l);
        int ai1[] = IntCache.getIntCache(k * l);
        for (int i1 = 0; i1 < l; i1++)
        {
            for (int j1 = 0; j1 < k; j1++)
            {
                initChunkSeed(j1 + i, i1 + j);
                int k1 = ai[j1 + i1 * k];
                if (k1 == 0)
                {
                    ai1[j1 + i1 * k] = 0;
                    continue;
                }
                if (k1 == BiomeGenBase.mushroomIsland.biomeID)
                {
                    ai1[j1 + i1 * k] = k1;
                    continue;
                }
                if (k1 == 1)
                {
                    ai1[j1 + i1 * k] = allowedBiomes[nextInt(allowedBiomes.length)].biomeID;
                }
                else
                {
                    ai1[j1 + i1 * k] = BiomeGenBase.icePlains.biomeID;
                }
            }
        }

        return ai1;
    }
}
