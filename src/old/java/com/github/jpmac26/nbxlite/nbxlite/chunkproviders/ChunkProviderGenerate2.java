package com.github.jpmac26.nbxlite.nbxlite.chunkproviders;

import java.util.List;
import net.minecraft.src.*;

public class ChunkProviderGenerate2
    implements IChunkProvider
{
    public ChunkProviderBaseFinite indevGen;
    public ChunkProviderBaseFinite classicGen;
    public ChunkProviderBaseInfinite infdev227Gen;
    public ChunkProviderBaseInfinite infdev415Gen;
    public ChunkProviderBaseInfinite infdev420Gen;
    public ChunkProviderBaseInfinite infdev618Gen;
    public ChunkProviderBaseInfinite alphaGen;
    public ChunkProviderBaseInfinite betaGen;
    public ChunkProviderBaseInfinite betaGenSky;
    public ChunkProviderBaseInfinite releaseGen;

    public ChunkProviderGenerate2(World world, long l, boolean flag)
    {
        infdev227Gen = new ChunkProviderGenerateInfdev227(world, l, flag);
        infdev415Gen = new ChunkProviderGenerateInfdev415(world, l, flag);
        infdev420Gen = new ChunkProviderGenerateInfdev420(world, l, flag);
        infdev618Gen = new ChunkProviderGenerateInfdev618(world, l, flag);
        indevGen = new ChunkProviderIndev(world, l);
        classicGen = new ChunkProviderClassic(world, l);
        alphaGen = new ChunkProviderGenerateAlpha(world, l, flag);
        betaGen = new ChunkProviderGenerateBeta(world, l, flag);
        betaGenSky = new ChunkProviderSky(world, l, flag);
        releaseGen = new ChunkProviderGenerateRelease(world, l, flag);
    }

    private IChunkProvider getCurrentProvider(){
        if(ODNBXlite.Generator==ODNBXlite.GEN_BIOMELESS){
            if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_ALPHA11201){
                return alphaGen;
            }else if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0618){
                return infdev618Gen;
            }else if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0420 || ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0608){
                return infdev420Gen;
            }else if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0415 || ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0327){
                return infdev415Gen;
            }else if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INFDEV0227){
                return infdev227Gen;
            }else if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_INDEV){
                return indevGen;
            }
            return classicGen;
        } else if(ODNBXlite.Generator==ODNBXlite.GEN_OLDBIOMES){
            if (ODNBXlite.MapFeatures==ODNBXlite.FEATURES_SKY){
                return betaGenSky;
            }
            return betaGen;
        }
        return releaseGen;
    }

    @Override
    public Chunk loadChunk(int i, int j)
    {
        return provideChunk(i, j);
    }

    @Override
    public Chunk provideChunk(int i, int j)
    {
        return getCurrentProvider().provideChunk(i, j);
    }

    @Override
    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    @Override
    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        getCurrentProvider().populate(ichunkprovider, i, j);
    }

    @Override
    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    @Override
    public boolean unloadQueuedChunks()
    {
        return false;
    }

    @Override
    public boolean canSave()
    {
        return true;
    }

    @Override
    public String makeString()
    {
        return "RandomLevelSource";
    }

    @Override
    public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k)
    {
        return releaseGen.getPossibleCreatures(enumcreaturetype, i, j, k);
    }

    @Override
    public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k)
    {
        return getCurrentProvider().findClosestStructure(world, s, i, j, k);
    }

    @Override
    public int getLoadedChunkCount()
    {
        return 0;
    }

    @Override
    public void recreateStructures(int par1, int par2)
    {
        getCurrentProvider().recreateStructures(par1, par2);
    }

    @Override
    public void saveExtraData()
    {
    }
}
