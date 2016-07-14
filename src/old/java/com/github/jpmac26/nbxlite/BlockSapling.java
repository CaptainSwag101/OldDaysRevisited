package com.github.jpmac26.nbxlite;

import java.util.List;
import java.util.Random;

public class BlockSapling extends BlockFlower
{
    public static int mode = 1;

    public static final String WOOD_TYPES[] =
    {
        "oak", "spruce", "birch", "jungle"
    };
    private Icon saplingIcon[];

    protected BlockSapling(int par1)
    {
        super(par1);
        float f = 0.4F;
        setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    /**
     * Ticks the block if it's been scheduled
     */
    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        if (par1World.isRemote)
        {
            return;
        }

        super.updateTick(par1World, par2, par3, par4, par5Random);

        if (par1World.getBlockLightValue(par2, par3 + 1, par4) >= 9 && par5Random.nextInt(7) == 0)
        {
            markOrGrowMarked(par1World, par2, par3, par4, par5Random);
        }
    }

    /**
     * From the specified side and block metadata retrieves the blocks texture. Args: side, metadata
     */
    public Icon getIcon(int par1, int par2)
    {
        par2 &= 3;
        return saplingIcon[par2];
    }

    public void markOrGrowMarked(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int i = par1World.getBlockMetadata(par2, par3, par4);

        if ((i & 8) == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, i | 8, 4);
        }
        else
        {
            growTree(par1World, par2, par3, par4, par5Random);
        }
    }

    /**
     * Attempts to grow a sapling into a tree
     */
    public void growTree(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        int i = par1World.getBlockMetadata(par2, par3, par4) & 3;
        Object obj = null;
        int j = 0;
        int k = 0;
        boolean flag = false;

        if (i == 1)
        {
            obj = new WorldGenTaiga2(true);
        }
        else if (i == 2)
        {
            obj = new WorldGenForest(true);
        }
        else if (i == 3)
        {
            j = 0;

            do
            {
                if (j < -1)
                {
                    break;
                }

                k = 0;

                do
                {
                    if (k < -1)
                    {
                        break;
                    }

                    if (isSameSapling(par1World, par2 + j, par3, par4 + k, 3) && isSameSapling(par1World, par2 + j + 1, par3, par4 + k, 3) && isSameSapling(par1World, par2 + j, par3, par4 + k + 1, 3) && isSameSapling(par1World, par2 + j + 1, par3, par4 + k + 1, 3))
                    {
                        obj = new WorldGenHugeTrees(true, 10 + par5Random.nextInt(20), 3, 3);
                        flag = true;
                        break;
                    }

                    k--;
                }
                while (true);

                if (obj != null)
                {
                    break;
                }

                j--;
            }
            while (true);

            if (obj == null)
            {
                j = k = 0;
                obj = new WorldGenTrees(true, 4 + par5Random.nextInt(7), 3, 3, false);
            }
        }
        else
        {
            obj = new WorldGenTrees(true);

            if (mode == 1 || (mode > 1 && par5Random.nextInt(10) == 0))
            {
                obj = new WorldGenBigTree(true);
            }
        }

        if (flag)
        {
            par1World.setBlock(par2 + j, par3, par4 + k, 0, 0, 4);
            par1World.setBlock(par2 + j + 1, par3, par4 + k, 0, 0, 4);
            par1World.setBlock(par2 + j, par3, par4 + k + 1, 0, 0, 4);
            par1World.setBlock(par2 + j + 1, par3, par4 + k + 1, 0, 0, 4);
        }
        else
        {
            par1World.setBlock(par2, par3, par4, 0, 0, 4);
        }

        if (!((WorldGenerator)(obj)).generate(par1World, par5Random, par2 + j, par3, par4 + k))
        {
            if (flag)
            {
                par1World.setBlock(par2 + j, par3, par4 + k, blockID, i, 4);
                par1World.setBlock(par2 + j + 1, par3, par4 + k, blockID, i, 4);
                par1World.setBlock(par2 + j, par3, par4 + k + 1, blockID, i, 4);
                par1World.setBlock(par2 + j + 1, par3, par4 + k + 1, blockID, i, 4);
            }
            else
            {
                par1World.setBlock(par2, par3, par4, blockID, i, 4);
            }
        }
    }

    /**
     * Determines if the same sapling is present at the given location.
     */
    public boolean isSameSapling(World par1World, int par2, int par3, int par4, int par5)
    {
        return par1World.getBlockId(par2, par3, par4) == blockID && (par1World.getBlockMetadata(par2, par3, par4) & 3) == par5;
    }

    /**
     * Determines the damage on the item the block drops. Used in cloth and wood.
     */
    public int damageDropped(int par1)
    {
        return par1 & 3;
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        par3List.add(new ItemStack(par1, 1, 0));
        par3List.add(new ItemStack(par1, 1, 1));
        par3List.add(new ItemStack(par1, 1, 2));
        par3List.add(new ItemStack(par1, 1, 3));
    }

    /**
     * When this method is called, your block should register all the icons it needs with the given IconRegister. This
     * is the only chance you get to register icons.
     */
    public void registerIcons(IconRegister par1IconRegister)
    {
        saplingIcon = new Icon[WOOD_TYPES.length];

        for (int i = 0; i < saplingIcon.length; i++)
        {
            saplingIcon[i] = par1IconRegister.registerIcon((new StringBuilder()).append(getTextureName()).append("_").append(WOOD_TYPES[i]).toString());
        }
    }
}
