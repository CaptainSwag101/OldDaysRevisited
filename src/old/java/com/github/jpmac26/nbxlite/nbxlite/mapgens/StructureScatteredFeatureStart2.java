package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import java.util.LinkedList;
import java.util.Random;

import com.github.jpmac26.nbxlite.nbxlite.oldbiomes.OldBiomeGenBase;
import net.minecraft.src.*;
import com.github.jpmac26.nbxlite.nbxlite.oldbiomes.OldBiomeGenBase;

public class StructureScatteredFeatureStart2 extends StructureStart
{
    public StructureScatteredFeatureStart2()
    {
    }

    public StructureScatteredFeatureStart2(World par1World, Random par2Random, int par3, int par4)
    {
        super(par3, par4);
        if (par1World.getWorldChunkManager().oldGetBiomeGenAt(par3 * 16 + 8, par4 * 16 + 8) == OldBiomeGenBase.rainforest)
        {
            ComponentScatteredFeatureJunglePyramid componentscatteredfeaturejunglepyramid = new ComponentScatteredFeatureJunglePyramid(par2Random, par3 * 16, par4 * 16);
            components.add(componentscatteredfeaturejunglepyramid);
        }
        else
        {
            ComponentScatteredFeatureDesertPyramid componentscatteredfeaturedesertpyramid = new ComponentScatteredFeatureDesertPyramid(par2Random, par3 * 16, par4 * 16);
            components.add(componentscatteredfeaturedesertpyramid);
        }

        updateBoundingBox();
    }
}
