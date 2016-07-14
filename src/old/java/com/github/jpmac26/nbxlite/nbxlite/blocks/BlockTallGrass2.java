package com.github.jpmac26.nbxlite.nbxlite.blocks;

import com.github.jpmac26.nbxlite.ODNBXlite;
import net.minecraft.src.BlockTallGrass;
import net.minecraft.src.IBlockAccess;

public class BlockTallGrass2 extends BlockTallGrass
{
    public BlockTallGrass2(int i)
    {
        super(i);
    }

    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        int l = iblockaccess.getBlockMetadata(i, j, k);
        if (l == 0)
        {
            return 0xffffff;
        }
        else{
            return ODNBXlite.GetGrassColorAtCoords(iblockaccess, i, j, k, false, false, true);
        }
    }
}
