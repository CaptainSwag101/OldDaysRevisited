package com.github.jpmac26.nbxlite;

import java.util.List;
import java.util.Random;

abstract class ComponentVillage extends StructureComponent
{
    protected int field_143015_k;

    /** The number of villagers that have been spawned in this component. */
    private int villagersSpawned;
    private boolean field_143014_b;

    public ComponentVillage()
    {
        field_143015_k = -1;
    }

    protected ComponentVillage(ComponentVillageStartPiece par1ComponentVillageStartPiece, int par2)
    {
        super(par2);
        field_143015_k = -1;

        if (par1ComponentVillageStartPiece != null)
        {
            field_143014_b = par1ComponentVillageStartPiece.inDesert;
        }
    }

    protected void func_143012_a(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setInteger("HPos", field_143015_k);
        par1NBTTagCompound.setInteger("VCount", villagersSpawned);
        par1NBTTagCompound.setBoolean("Desert", field_143014_b);
    }

    protected void func_143011_b(NBTTagCompound par1NBTTagCompound)
    {
        field_143015_k = par1NBTTagCompound.getInteger("HPos");
        villagersSpawned = par1NBTTagCompound.getInteger("VCount");
        field_143014_b = par1NBTTagCompound.getBoolean("Desert");
    }

    /**
     * Gets the next village component, with the bounding box shifted -1 in the X and Z direction.
     */
    protected StructureComponent getNextComponentNN(ComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
    {
        switch (coordBaseMode)
        {
            case 2:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX - 1, boundingBox.minY + par4, boundingBox.minZ + par5, 1, getComponentType());
            case 0:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX - 1, boundingBox.minY + par4, boundingBox.minZ + par5, 1, getComponentType());
            case 1:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.minZ - 1, 2, getComponentType());
            case 3:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.minZ - 1, 2, getComponentType());
        }

        return null;
    }

    /**
     * Gets the next village component, with the bounding box shifted +1 in the X and Z direction.
     */
    protected StructureComponent getNextComponentPP(ComponentVillageStartPiece par1ComponentVillageStartPiece, List par2List, Random par3Random, int par4, int par5)
    {
        switch (coordBaseMode)
        {
            case 2:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.maxX + 1, boundingBox.minY + par4, boundingBox.minZ + par5, 3, getComponentType());
            case 0:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.maxX + 1, boundingBox.minY + par4, boundingBox.minZ + par5, 3, getComponentType());
            case 1:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.maxZ + 1, 0, getComponentType());
            case 3:
                return StructureVillagePieces.getNextStructureComponent(par1ComponentVillageStartPiece, par2List, par3Random, boundingBox.minX + par5, boundingBox.minY + par4, boundingBox.maxZ + 1, 0, getComponentType());
        }

        return null;
    }

    /**
     * Discover the y coordinate that will serve as the ground level of the supplied BoundingBox. (A median of all the
     * levels in the BB's horizontal rectangle).
     */
    protected int getAverageGroundLevel(World par1World, StructureBoundingBox par2StructureBoundingBox)
    {
        int i = 0;
        int j = 0;

        for (int k = boundingBox.minZ; k <= boundingBox.maxZ; k++)
        {
            for (int l = boundingBox.minX; l <= boundingBox.maxX; l++)
            {
                if (par2StructureBoundingBox.isVecInside(l, 64, k))
                {
                    i += Math.max(par1World.getTopSolidOrLiquidBlock(l, k), ODNBXlite.lowerVillages() ? 63 : par1World.provider.getAverageGroundLevel());
                    j++;
                }
            }
        }

        if (j == 0)
        {
            return -1;
        }
        else
        {
            return i / j;
        }
    }

    protected static boolean canVillageGoDeeper(StructureBoundingBox par0StructureBoundingBox)
    {
        return par0StructureBoundingBox != null && par0StructureBoundingBox.minY > 10;
    }

    /**
     * Spawns a number of villagers in this component. Parameters: world, component bounding box, x offset, y offset, z
     * offset, number of villagers
     */
    protected void spawnVillagers(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6)
    {
        if (!ODNBXlite.villagers()){
            return;
        }
        if (villagersSpawned >= par6)
        {
            return;
        }

        int i = villagersSpawned;

        do
        {
            if (i >= par6)
            {
                break;
            }

            int j = getXWithOffset(par3 + i, par5);
            int k = getYWithOffset(par4);
            int l = getZWithOffset(par3 + i, par5);

            if (!par2StructureBoundingBox.isVecInside(j, k, l))
            {
                break;
            }

            villagersSpawned++;
            EntityVillager entityvillager = new EntityVillager(par1World, getVillagerType(i));
            entityvillager.setLocationAndAngles((double)j + 0.5D, k, (double)l + 0.5D, 0.0F, 0.0F);
            par1World.spawnEntityInWorld(entityvillager);
            i++;
        }
        while (true);
    }

    /**
     * Returns the villager type to spawn in this component, based on the number of villagers already spawned.
     */
    protected int getVillagerType(int par1)
    {
        return 0;
    }

    /**
     * Gets the replacement block for the current biome
     */
    protected int getBiomeSpecificBlock(int par1, int par2)
    {
        if (field_143014_b && ODNBXlite.desertVillages())
        {
            if (par1 == Block.wood.blockID)
            {
                return Block.sandStone.blockID;
            }

            if (par1 == Block.cobblestone.blockID)
            {
                return Block.sandStone.blockID;
            }

            if (par1 == Block.planks.blockID)
            {
                return Block.sandStone.blockID;
            }

            if (par1 == Block.stairsWoodOak.blockID)
            {
                return Block.stairsSandStone.blockID;
            }

            if (par1 == Block.stairsCobblestone.blockID)
            {
                return Block.stairsSandStone.blockID;
            }

            if (par1 == Block.gravel.blockID)
            {
                return Block.sandStone.blockID;
            }
        }

        return par1;
    }

    /**
     * Gets the replacement block metadata for the current biome
     */
    protected int getBiomeSpecificBlockMetadata(int par1, int par2)
    {
        if (field_143014_b && ODNBXlite.desertVillages())
        {
            if (par1 == Block.wood.blockID)
            {
                return 0;
            }

            if (par1 == Block.cobblestone.blockID)
            {
                return 0;
            }

            if (par1 == Block.planks.blockID)
            {
                return 2;
            }
        }

        return par2;
    }

    /**
     * current Position depends on currently set Coordinates mode, is computed here
     */
    protected void placeBlockAtCurrentPosition(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
    {
        int i = getBiomeSpecificBlock(par2, par3);
        int j = getBiomeSpecificBlockMetadata(par2, par3);
        super.placeBlockAtCurrentPosition(par1World, i, j, par4, par5, par6, par7StructureBoundingBox);
    }

    /**
     * arguments: (World worldObj, StructureBoundingBox structBB, int minX, int minY, int minZ, int maxX, int maxY, int
     * maxZ, int placeBlockId, int replaceBlockId, boolean alwaysreplace)
     */
    protected void fillWithBlocks(World par1World, StructureBoundingBox par2StructureBoundingBox, int par3, int par4, int par5, int par6, int par7, int par8, int par9, int par10, boolean par11)
    {
        int i = getBiomeSpecificBlock(par9, 0);
        int j = getBiomeSpecificBlockMetadata(par9, 0);
        int k = getBiomeSpecificBlock(par10, 0);
        int l = getBiomeSpecificBlockMetadata(par10, 0);
        super.fillWithMetadataBlocks(par1World, par2StructureBoundingBox, par3, par4, par5, par6, par7, par8, i, j, k, l, par11);
    }

    /**
     * Overwrites air and liquids from selected position downwards, stops at hitting anything else.
     */
    protected void fillCurrentPositionBlocksDownwards(World par1World, int par2, int par3, int par4, int par5, int par6, StructureBoundingBox par7StructureBoundingBox)
    {
        int i = getBiomeSpecificBlock(par2, par3);
        int j = getBiomeSpecificBlockMetadata(par2, par3);
        super.fillCurrentPositionBlocksDownwards(par1World, i, j, par4, par5, par6, par7StructureBoundingBox);
    }

    @Override
    protected boolean generateStructureChestContents(World par1World, StructureBoundingBox par2StructureBoundingBox, Random par3Random, int par4, int par5, int par6, WeightedRandomChestContent par7ArrayOfWeightedRandomChestContent[], int par8){
        if (!ODNBXlite.villageChests()){
            return true;
        }
        return super.generateStructureChestContents(par1World, par2StructureBoundingBox, par3Random, par4, par5, par6, par7ArrayOfWeightedRandomChestContent, par8);
    }
}
