package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import com.github.jpmac26.nbxlite.GenLayer;
import net.minecraft.src.IntCache;
import com.github.jpmac26.nbxlite.BiomeGenBase;

public class GenLayerDownfallMix11 extends GenLayer
{
    private GenLayer field_35507_b;
    private int field_35508_c;

    public GenLayerDownfallMix11(GenLayer genlayer, GenLayer genlayer1, int i)
    {
        super(0L);
        parent = genlayer1;
        field_35507_b = genlayer;
        field_35508_c = i;
    }

    @Override
    public int[] getInts(int i, int j, int k, int l)
    {
        int ai[] = parent.getInts(i, j, k, l);
        int ai1[] = field_35507_b.getInts(i, j, k, l);
        int ai2[] = IntCache.getIntCache(k * l);
        for (int i1 = 0; i1 < k * l; i1++)
        {
            ai2[i1] = ai1[i1] + (BiomeGenBase.biomeList[ai[i1]].getIntRainfall() - ai1[i1]) / (field_35508_c + 1);
        }

        return ai2;
    }
}
