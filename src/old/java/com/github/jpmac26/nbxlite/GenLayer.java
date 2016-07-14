package com.github.jpmac26.nbxlite;

import net.minecraft.src.nbxlite.mapgens.*;

public abstract class GenLayer
{
    /** seed from World#getWorldSeed that is used in the LCG prng */
    private long worldGenSeed;

    /** parent GenLayer that was provided via the constructor */
    protected GenLayer parent;

    /**
     * final part of the LCG prng that uses the chunk X, Z coords along with the other two seeds to generate
     * pseudorandom numbers
     */
    private long chunkSeed;

    /** base seed to the LCG prng provided via the constructor */
    private long baseSeed;

    /**
     * the first array item is a linked list of the bioms, the second is the zoom function, the third is the same as the
     * first.
     */
    public static GenLayer[] initializeAllBiomeGenerators(long par0, WorldType par2WorldType)
    {
        GenLayer obj = new GenLayerIsland(1L);
        obj = new GenLayerFuzzyZoom(2000L, ((GenLayer)(obj)));
        if (ODNBXlite.MapFeatures>ODNBXlite.FEATURES_BETA181){
            obj = new GenLayerAddIsland(1L, ((GenLayer)(obj)));
        }else{
            obj = new GenLayerIsland18(1L, ((GenLayer)(obj)));
        }
        obj = new GenLayerZoom(2001L, ((GenLayer)(obj)));
        if (ODNBXlite.MapFeatures>ODNBXlite.FEATURES_BETA181){
            obj = new GenLayerAddIsland(2L, ((GenLayer)(obj)));
            obj = new GenLayerAddSnow(2L, ((GenLayer)(obj)));
        }else{
            obj = new GenLayerIsland18(2L, ((GenLayer)(obj)));
        }
        obj = new GenLayerZoom(2002L, ((GenLayer)(obj)));
        if (ODNBXlite.MapFeatures>ODNBXlite.FEATURES_BETA181){
            obj = new GenLayerAddIsland(3L, ((GenLayer)(obj)));
        }else{
            obj = new GenLayerIsland18(3L, ((GenLayer)(obj)));
        }
        obj = new GenLayerZoom(2003L, ((GenLayer)(obj)));
        if (ODNBXlite.MapFeatures>ODNBXlite.FEATURES_BETA181){
            obj = new GenLayerAddIsland(4L, ((GenLayer)(obj)));
            obj = new GenLayerAddMushroomIsland(5L, ((GenLayer)(obj)));
        }else{
            obj = new GenLayerIsland18(3L, ((GenLayer)(obj)));
            obj = new GenLayerZoom(2004L, ((GenLayer)(obj)));
            obj = new GenLayerIsland18(3L, ((GenLayer)(obj)));
        }
        byte byte0 = 4;

        if (par2WorldType == WorldType.LARGE_BIOMES)
        {
            byte0 = 6;
        }

        GenLayer obj1 = obj;
        obj1 = GenLayerZoom.magnify(1000L, ((GenLayer)(obj1)), 0);
        obj1 = new GenLayerRiverInit(100L, ((GenLayer)(obj1)));
        obj1 = GenLayerZoom.magnify(1000L, ((GenLayer)(obj1)), byte0 + 2);
        if (ODNBXlite.MapFeatures<=ODNBXlite.FEATURES_12){
            obj1 = new GenLayerRiver12(1L, ((GenLayer)(obj1)));
        }else{
            obj1 = new GenLayerRiver(1L, ((GenLayer)(obj1)));
        }
        obj1 = new GenLayerSmooth(1000L, ((GenLayer)(obj1)));
        GenLayer obj2 = obj;
        obj2 = GenLayerZoom.magnify(1000L, ((GenLayer)(obj2)), 0);
        if (ODNBXlite.MapFeatures>=ODNBXlite.FEATURES_13){
            obj2 = new GenLayerBiome(200L, ((GenLayer)(obj2)), par2WorldType);
        }else if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_12){
            obj2 = new GenLayerBiome12(200L, ((GenLayer)(obj2)), par2WorldType);
        }else if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_11 || ODNBXlite.MapFeatures==ODNBXlite.FEATURES_10){
            obj2 = new GenLayerVillageLandscape11(200L, ((GenLayer)(obj2)));
        }else{
            obj2 = new GenLayerVillageLandscape18(200L, ((GenLayer)(obj2)));
        }
        obj2 = GenLayerZoom.magnify(1000L, ((GenLayer)(obj2)), 2);
        if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_11 || ODNBXlite.MapFeatures==ODNBXlite.FEATURES_12){
            obj2 = new GenLayerHills12(1000L, ((GenLayer)(obj2)));
        }else if (ODNBXlite.MapFeatures>=ODNBXlite.FEATURES_13){
            obj2 = new GenLayerHills(1000L, ((GenLayer)(obj2)));
        }
        GenLayer obj3 = null;
        GenLayer obj4 = null;
        if (ODNBXlite.MapFeatures<ODNBXlite.FEATURES_12){
            obj3 = new GenLayerTemperature11(((GenLayer)(obj2)));
            obj4 = new GenLayerDownfall11(((GenLayer)(obj2)));
        }
        for(int i = 0; i < byte0; i++)
        {
            obj2 = new GenLayerZoom(1000 + i, ((GenLayer)(obj2)));
            if(i == 0)
            {
                if (ODNBXlite.MapFeatures>ODNBXlite.FEATURES_BETA181){
                    obj2 = new GenLayerAddIsland(3L, ((GenLayer)(obj2)));
                }else{
                    obj2 = new GenLayerIsland18(3L, ((GenLayer)(obj2)));
                }
            }
            if(ODNBXlite.MapFeatures==ODNBXlite.FEATURES_10 && i == 0)
            {
                obj2 = new GenLayerShore10(1000L, ((GenLayer)(obj2)));
            }
            if (ODNBXlite.MapFeatures>=ODNBXlite.FEATURES_11 && i == 1)
            {
                obj2 = new GenLayerShore(1000L, ((GenLayer)(obj2)));
                if (ODNBXlite.MapFeatures>=ODNBXlite.FEATURES_13){
                    obj2 = new GenLayerSwampRivers(1000L, ((GenLayer)(obj2)));
                }else{
                    obj2 = new GenLayerSwampRivers12(1000L, ((GenLayer)(obj2)));
                }
            }
            if (ODNBXlite.MapFeatures<ODNBXlite.FEATURES_12){
                obj3 = new GenLayerSmoothZoom11(1000 + i, ((GenLayer)(obj3)));
                obj3 = new GenLayerTemperatureMix11(((GenLayer)(obj3)), ((GenLayer)(obj2)), i);
                obj4 = new GenLayerSmoothZoom11(1000 + i, ((GenLayer)(obj4)));
                obj4 = new GenLayerDownfallMix11(((GenLayer)(obj4)), ((GenLayer)(obj2)), i);
            }
        }
        obj2 = new GenLayerSmooth(1000L, ((GenLayer)(obj2)));
        if (ODNBXlite.MapFeatures>=ODNBXlite.FEATURES_12){
            obj2 = new GenLayerRiverMix(100L, ((GenLayer)(obj2)), ((GenLayer)(obj1)));
            GenLayerRiverMix genlayerrivermix = ((GenLayerRiverMix)(obj2));
            GenLayerVoronoiZoom genlayervoronoizoom = new GenLayerVoronoiZoom(10L, ((GenLayer)(obj2)));
            ((GenLayer)(obj2)).initWorldGenSeed(par0);
            genlayervoronoizoom.initWorldGenSeed(par0);
            return (new GenLayer[]{obj2, genlayervoronoizoom, genlayerrivermix});
        }else if (ODNBXlite.MapFeatures>ODNBXlite.FEATURES_BETA181){
            obj2 = new GenLayerRiverMix(100L, ((GenLayer) (obj2)), ((GenLayer) (obj1)));
            GenLayerRiverMix genlayerrivermix = ((GenLayerRiverMix) (obj2));
            obj3 = GenLayerSmoothZoom11.func_35517_a(1000L, ((GenLayer) (obj3)), 2);
            obj4 = GenLayerSmoothZoom11.func_35517_a(1000L, ((GenLayer) (obj4)), 2);
            GenLayerVoronoiZoom genlayerzoomvoronoi = new GenLayerVoronoiZoom(10L, ((GenLayer) (obj2)));
            ((GenLayer) (obj2)).initWorldGenSeed(par0);
            ((GenLayer) (obj3)).initWorldGenSeed(par0);
            ((GenLayer) (obj4)).initWorldGenSeed(par0);
            genlayerzoomvoronoi.initWorldGenSeed(par0);
            return (new GenLayer[]{obj2, genlayerzoomvoronoi, obj3, obj4, genlayerrivermix});
        }else{
            obj2 = new GenLayerRiverMix18(100L, ((GenLayer) (obj2)), ((GenLayer) (obj1)));
            obj3 = GenLayerSmoothZoom11.func_35517_a(1000L, ((GenLayer) (obj3)), 2);
            obj4 = GenLayerSmoothZoom11.func_35517_a(1000L, ((GenLayer) (obj4)), 2);
            GenLayerVoronoiZoom genlayerzoomvoronoi = new GenLayerVoronoiZoom(10L, ((GenLayer) (obj2)));
            ((GenLayer) (obj2)).initWorldGenSeed(par0);
            ((GenLayer) (obj3)).initWorldGenSeed(par0);
            ((GenLayer) (obj4)).initWorldGenSeed(par0);
            genlayerzoomvoronoi.initWorldGenSeed(par0);
            return (new GenLayer[]{obj2, genlayerzoomvoronoi, obj3, obj4});
        }
    }

    public GenLayer(long par1)
    {
        baseSeed = par1;
        baseSeed *= baseSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        baseSeed += par1;
        baseSeed *= baseSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        baseSeed += par1;
        baseSeed *= baseSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        baseSeed += par1;
    }

    /**
     * Initialize layer's local worldGenSeed based on its own baseSeed and the world's global seed (passed in as an
     * argument).
     */
    public void initWorldGenSeed(long par1)
    {
        worldGenSeed = par1;

        if (parent != null)
        {
            parent.initWorldGenSeed(par1);
        }

        worldGenSeed *= worldGenSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        worldGenSeed += baseSeed;
        worldGenSeed *= worldGenSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        worldGenSeed += baseSeed;
        worldGenSeed *= worldGenSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        worldGenSeed += baseSeed;
    }

    /**
     * Initialize layer's current chunkSeed based on the local worldGenSeed and the (x,z) chunk coordinates.
     */
    public void initChunkSeed(long par1, long par3)
    {
        chunkSeed = worldGenSeed;
        chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        chunkSeed += par1;
        chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        chunkSeed += par3;
        chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        chunkSeed += par1;
        chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        chunkSeed += par3;
    }

    /**
     * returns a LCG pseudo random number from [0, x). Args: int x
     */
    protected int nextInt(int par1)
    {
        int i = (int)((chunkSeed >> 24) % (long)par1);

        if (i < 0)
        {
            i += par1;
        }

        chunkSeed *= chunkSeed * 0x5851f42d4c957f2dL + 0x14057b7ef767814fL;
        chunkSeed += worldGenSeed;
        return i;
    }

    /**
     * Returns a list of integer values generated by this layer. These may be interpreted as temperatures, rainfall
     * amounts, or biomeList[] indices based on the particular GenLayer subclass.
     */
    public abstract int[] getInts(int i, int j, int k, int l);
}
