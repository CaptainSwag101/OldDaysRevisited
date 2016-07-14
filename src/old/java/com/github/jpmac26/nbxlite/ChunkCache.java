package com.github.jpmac26.nbxlite;

public class ChunkCache implements IBlockAccess
{
    private int chunkX;
    private int chunkZ;
    private Chunk chunkArray[][];

    /** True if the chunk cache is empty. */
    private boolean isEmpty;

    /** Reference to the World object. */
    private World worldObj;

    public ChunkCache(World par1World, int par2, int par3, int par4, int par5, int par6, int par7, int par8)
    {
        worldObj = par1World;
        chunkX = par2 - par8 >> 4;
        chunkZ = par4 - par8 >> 4;
        int i = par5 + par8 >> 4;
        int j = par7 + par8 >> 4;
        chunkArray = new Chunk[(i - chunkX) + 1][(j - chunkZ) + 1];
        isEmpty = true;

        for (int k = chunkX; k <= i; k++)
        {
            for (int i1 = chunkZ; i1 <= j; i1++)
            {
                Chunk chunk = par1World.getChunkFromChunkCoords(k, i1);

                if (chunk != null)
                {
                    chunkArray[k - chunkX][i1 - chunkZ] = chunk;
                }
            }
        }

        for (int l = par2 >> 4; l <= par5 >> 4; l++)
        {
            for (int j1 = par4 >> 4; j1 <= par7 >> 4; j1++)
            {
                Chunk chunk1 = chunkArray[l - chunkX][j1 - chunkZ];

                if (chunk1 != null && !chunk1.getAreLevelsEmpty(par3, par6))
                {
                    isEmpty = false;
                }
            }
        }
    }

    /**
     * set by !chunk.getAreLevelsEmpty
     */
    public boolean extendedLevelsInChunkCache()
    {
        return isEmpty;
    }

    private boolean isBounds(int x, int y, int z){
        if (ODNBXlite.Generator==ODNBXlite.GEN_BIOMELESS && (worldObj.provider==null || worldObj.provider.dimensionId==0)){
            if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INDEV){
                if(x<=0 || x>=ODNBXlite.IndevWidthX-1 || z<=0 || z>=ODNBXlite.IndevWidthZ-1 || y<0){
                    return true;
                }
            }
            if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_CLASSIC){
                if(x<0 || x>=ODNBXlite.IndevWidthX || z<0 || z>=ODNBXlite.IndevWidthZ || y<0){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the block ID at coords x,y,z
     */
    public int getBlockId(int par1, int par2, int par3)
    {
        if (isBounds(par1, par2, par3)){
            return ODNBXlite.getBlockIdInBounds(par2);
        }
        if (par2 < 0)
        {
            return 0;
        }

        if (par2 >= 256)
        {
            return 0;
        }

        int i = (par1 >> 4) - chunkX;
        int j = (par3 >> 4) - chunkZ;

        if (i < 0 || i >= chunkArray.length || j < 0 || j >= chunkArray[i].length)
        {
            return 0;
        }

        Chunk chunk = chunkArray[i][j];

        if (chunk == null)
        {
            return 0;
        }
        else
        {
            return chunk.getBlockID(par1 & 0xf, par2, par3 & 0xf);
        }
    }

    /**
     * Returns the TileEntity associated with a given block in X,Y,Z coordinates, or null if no TileEntity exists
     */
    public TileEntity getBlockTileEntity(int par1, int par2, int par3)
    {
        int i = (par1 >> 4) - chunkX;
        int j = (par3 >> 4) - chunkZ;
        return chunkArray[i][j].getChunkBlockTileEntity(par1 & 0xf, par2, par3 & 0xf);
    }

    public float getBrightness(int par1, int par2, int par3, int par4)
    {
        if (isBounds(par1, par2, par3)){
            if (worldObj.provider.dimensionId == 1){
                return 0.22F + worldObj.provider.lightBrightnessTable[ODNBXlite.getLightInBounds2(par2)] * 0.75F;
            }
            return worldObj.provider.lightBrightnessTable[ODNBXlite.getLightInBounds2(par2)];
        }
        int i = getLightValue(par1, par2, par3);

        if (i < par4)
        {
            i = par4;
        }

        if (worldObj.provider.dimensionId == 1){
            return 0.22F + worldObj.provider.lightBrightnessTable[i] * 0.75F;
        }
        if (Minecraft.getMinecraft().thePlayer.isPotionActive(Potion.nightVision))
        {
            float light = worldObj.provider.lightBrightnessTable[i];
            light += ((0.5F - light) * (0.7F * light)) + 0.5F;
            if (worldObj.provider.dimensionId == 1){
                return 0.22F + light * 0.75F;
            }
            return light;
        }
        return worldObj.provider.lightBrightnessTable[i];
    }

    /**
     * Any Light rendered on a 1.8 Block goes through here
     */
    public int getLightBrightnessForSkyBlocks(int par1, int par2, int par3, int par4)
    {
        if (isBounds(par1, par2, par3)){
            return ODNBXlite.getLightInBounds(par2);
        }
        int i = getSkyBlockTypeBrightness(EnumSkyBlock.Sky, par1, par2, par3);
        int j = getSkyBlockTypeBrightness(EnumSkyBlock.Block, par1, par2, par3);

        if (j < par4)
        {
            j = par4;
        }

        return i << 20 | j << 4;
    }

    /**
     * Returns how bright the block is shown as which is the block's light value looked up in a lookup table (light
     * values aren't linear for brightness). Args: x, y, z
     */
    public float getLightBrightness(int par1, int par2, int par3)
    {
        if (isBounds(par1, par2, par3)){
            if (worldObj.provider.dimensionId == 1){
                return 0.22F + worldObj.provider.lightBrightnessTable[ODNBXlite.getLightInBounds2(par2)] * 0.75F;
            }
            if (Minecraft.getMinecraft().thePlayer.isPotionActive(Potion.nightVision))
            {
                float light = worldObj.provider.lightBrightnessTable[ODNBXlite.getLightInBounds2(par2)];
                light += ((0.5F - light) * (0.7F * light)) + 0.5F;
                if (worldObj.provider.dimensionId == 1){
                    return 0.22F + light * 0.75F;
                }
                return light;
            }
            return worldObj.provider.lightBrightnessTable[ODNBXlite.getLightInBounds2(par2)];
        }
        if (worldObj.provider.dimensionId == 1){
            return 0.22F + worldObj.provider.lightBrightnessTable[getLightValue(par1, par2, par3)] * 0.75F;
        }
        if (Minecraft.getMinecraft().thePlayer.isPotionActive(Potion.nightVision))
        {
            float light = worldObj.provider.lightBrightnessTable[getLightValue(par1, par2, par3)];
            light += ((0.5F - light) * (0.7F * light)) + 0.5F;
            if (worldObj.provider.dimensionId == 1){
                return 0.22F + light * 0.75F;
            }
            return light;
        }
        return worldObj.provider.lightBrightnessTable[getLightValue(par1, par2, par3)];
    }

    /**
     * Gets the light value of the specified block coords. Args: x, y, z
     */
    public int getLightValue(int par1, int par2, int par3)
    {
        return getLightValueExt(par1, par2, par3, true);
    }

    /**
     * Get light value with flag
     */
    public int getLightValueExt(int par1, int par2, int par3, boolean par4)
    {
        if (par1 < 0xfe363c80 || par3 < 0xfe363c80 || par1 >= 0x1c9c380 || par3 > 0x1c9c380)
        {
            return 15;
        }

        if (par4)
        {
            int i = getBlockId(par1, par2, par3);

            if (i == Block.stoneSingleSlab.blockID || i == Block.woodSingleSlab.blockID || i == Block.tilledField.blockID || i == Block.stairsWoodOak.blockID || i == Block.stairsCobblestone.blockID)
            {
                int l = getLightValueExt(par1, par2 + 1, par3, false);
                int j1 = getLightValueExt(par1 + 1, par2, par3, false);
                int k1 = getLightValueExt(par1 - 1, par2, par3, false);
                int l1 = getLightValueExt(par1, par2, par3 + 1, false);
                int i2 = getLightValueExt(par1, par2, par3 - 1, false);

                if (j1 > l)
                {
                    l = j1;
                }

                if (k1 > l)
                {
                    l = k1;
                }

                if (l1 > l)
                {
                    l = l1;
                }

                if (i2 > l)
                {
                    l = i2;
                }

                return l;
            }
        }

        if (par2 < 0)
        {
            return 0;
        }

        if (par2 >= 256)
        {
            int j = 15 - worldObj.skylightSubtracted;

            if (j < 0)
            {
                j = 0;
            }

            return j;
        }
        else
        {
            int k = (par1 >> 4) - chunkX;
            int i1 = (par3 >> 4) - chunkZ;
            return chunkArray[k][i1].getBlockLightValue(par1 & 0xf, par2, par3 & 0xf, worldObj.skylightSubtracted);
        }
    }

    /**
     * Returns the block metadata at coords x,y,z
     */
    public int getBlockMetadata(int par1, int par2, int par3)
    {
        if (par2 < 0)
        {
            return 0;
        }

        if (par2 >= 256)
        {
            return 0;
        }
        else
        {
            int i = (par1 >> 4) - chunkX;
            int j = (par3 >> 4) - chunkZ;
            return chunkArray[i][j].getBlockMetadata(par1 & 0xf, par2, par3 & 0xf);
        }
    }

    /**
     * Returns the block's material.
     */
    public Material getBlockMaterial(int par1, int par2, int par3)
    {
        int i = getBlockId(par1, par2, par3);

        if (i == 0)
        {
            return Material.air;
        }
        else
        {
            return Block.blocksList[i].blockMaterial;
        }
    }

    /**
     * Gets the biome for a given set of x/z coordinates
     */
    public BiomeGenBase getBiomeGenForCoords(int par1, int par2)
    {
        return worldObj.getBiomeGenForCoords(par1, par2);
    }

    /**
     * Returns true if the block at the specified coordinates is an opaque cube. Args: x, y, z
     */
    public boolean isBlockOpaqueCube(int par1, int par2, int par3)
    {
        Block block = Block.blocksList[getBlockId(par1, par2, par3)];

        if (block == null)
        {
            return false;
        }
        else
        {
            return block.isOpaqueCube();
        }
    }

    /**
     * Indicate if a material is a normal solid opaque cube.
     */
    public boolean isBlockNormalCube(int par1, int par2, int par3)
    {
        Block block = Block.blocksList[getBlockId(par1, par2, par3)];

        if (block == null)
        {
            return false;
        }
        else
        {
            return block.blockMaterial.blocksMovement() && block.renderAsNormalBlock();
        }
    }

    /**
     * Returns true if the block at the given coordinate has a solid (buildable) top surface.
     */
    public boolean doesBlockHaveSolidTopSurface(int par1, int par2, int par3)
    {
        Block block = Block.blocksList[getBlockId(par1, par2, par3)];
        return worldObj.isBlockTopFacingSurfaceSolid(block, getBlockMetadata(par1, par2, par3));
    }

    /**
     * Return the Vec3Pool object for this world.
     */
    public Vec3Pool getWorldVec3Pool()
    {
        return worldObj.getWorldVec3Pool();
    }

    /**
     * Returns true if the block at the specified coordinates is empty
     */
    public boolean isAirBlock(int par1, int par2, int par3)
    {
        Block block = Block.blocksList[getBlockId(par1, par2, par3)];
        return block == null;
    }

    /**
     * Brightness for SkyBlock.Sky is clear white and (through color computing it is assumed) DEPENDENT ON DAYTIME.
     * Brightness for SkyBlock.Block is yellowish and independent.
     */
    public int getSkyBlockTypeBrightness(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4)
    {
        if (par3 < 0)
        {
            par3 = 0;
        }

        if (par3 >= 256)
        {
            par3 = 255;
        }

        if (par3 < 0 || par3 >= 256 || par2 < 0xfe363c80 || par4 < 0xfe363c80 || par2 >= 0x1c9c380 || par4 > 0x1c9c380)
        {
            return par1EnumSkyBlock.defaultLightValue;
        }

        if (par1EnumSkyBlock == EnumSkyBlock.Sky && worldObj.provider.hasNoSky)
        {
            return 0;
        }

        if (Block.useNeighborBrightness[getBlockId(par2, par3, par4)])
        {
            int i = getSpecialBlockBrightness(par1EnumSkyBlock, par2, par3 + 1, par4);
            int k = getSpecialBlockBrightness(par1EnumSkyBlock, par2 + 1, par3, par4);
            int i1 = getSpecialBlockBrightness(par1EnumSkyBlock, par2 - 1, par3, par4);
            int j1 = getSpecialBlockBrightness(par1EnumSkyBlock, par2, par3, par4 + 1);
            int k1 = getSpecialBlockBrightness(par1EnumSkyBlock, par2, par3, par4 - 1);

            if (k > i)
            {
                i = k;
            }

            if (i1 > i)
            {
                i = i1;
            }

            if (j1 > i)
            {
                i = j1;
            }

            if (k1 > i)
            {
                i = k1;
            }

            return i;
        }
        else
        {
            int j = (par2 >> 4) - chunkX;
            int l = (par4 >> 4) - chunkZ;
            return chunkArray[j][l].getSavedLightValue(par1EnumSkyBlock, par2 & 0xf, par3, par4 & 0xf);
        }
    }

    /**
     * is only used on stairs and tilled fields
     */
    public int getSpecialBlockBrightness(EnumSkyBlock par1EnumSkyBlock, int par2, int par3, int par4)
    {
        if (par3 < 0)
        {
            par3 = 0;
        }

        if (par3 >= 256)
        {
            par3 = 255;
        }

        if (par3 < 0 || par3 >= 256 || par2 < 0xfe363c80 || par4 < 0xfe363c80 || par2 >= 0x1c9c380 || par4 > 0x1c9c380)
        {
            return par1EnumSkyBlock.defaultLightValue;
        }
        else
        {
            int i = (par2 >> 4) - chunkX;
            int j = (par4 >> 4) - chunkZ;
            return chunkArray[i][j].getSavedLightValue(par1EnumSkyBlock, par2 & 0xf, par3, par4 & 0xf);
        }
    }

    /**
     * Returns current world height.
     */
    public int getHeight()
    {
        return 256;
    }

    /**
     * Is this block powering in the specified direction Args: x, y, z, direction
     */
    public int isBlockProvidingPowerTo(int par1, int par2, int par3, int par4)
    {
        int i = getBlockId(par1, par2, par3);

        if (i == 0)
        {
            return 0;
        }
        else
        {
            return Block.blocksList[i].isProvidingStrongPower(this, par1, par2, par3, par4);
        }
    }
}
