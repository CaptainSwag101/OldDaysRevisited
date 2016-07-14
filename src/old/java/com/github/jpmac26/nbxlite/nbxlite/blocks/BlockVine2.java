package com.github.jpmac26.nbxlite.nbxlite.blocks;

import com.github.jpmac26.nbxlite.ODNBXlite;
import net.minecraft.src.BlockVine;
import net.minecraft.src.IBlockAccess;

public class BlockVine2 extends BlockVine
{
    public BlockVine2(int i)
    {
        super(i);
    }

    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return ODNBXlite.GetFoliageColorAtCoords(iblockaccess, i, j, k, false, false, false);
    }
}
