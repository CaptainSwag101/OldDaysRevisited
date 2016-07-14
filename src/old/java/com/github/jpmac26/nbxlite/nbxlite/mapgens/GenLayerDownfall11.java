package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import com.github.jpmac26.nbxlite.GenLayer;
import net.minecraft.src.IntCache;
import com.github.jpmac26.nbxlite.BiomeGenBase;

public class GenLayerDownfall11 extends GenLayer
{
    public GenLayerDownfall11(GenLayer genlayer)
    {
        super(0L);
        parent = genlayer;
    }

    @Override
    public int[] getInts(int i, int j, int k, int l)
    {
        int ai[] = parent.getInts(i, j, k, l);
        int ai1[] = IntCache.getIntCache(k * l);
        for (int i1 = 0; i1 < k * l; i1++)
        {
            ai1[i1] = BiomeGenBase.biomeList[ai[i1]].getIntRainfall();
        }

        return ai1;
    }
}
