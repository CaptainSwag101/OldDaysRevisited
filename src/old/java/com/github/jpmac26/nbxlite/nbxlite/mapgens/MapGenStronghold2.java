package com.github.jpmac26.nbxlite.nbxlite.mapgens;

import java.io.PrintStream;
import java.util.*;
import net.minecraft.src.*;

public class MapGenStronghold2 extends MapGenStructure
{
    private BiomeGenBase allowedBiomeGenBases[];
    private BiomeGenBase allowedBiomeGenBasesOld[];

    /**
     * is spawned false and set true once the defined BiomeGenBases were compared with the present ones
     */
    private boolean ranBiomeCheck;
    private ChunkCoordIntPair structureCoords[];

    public MapGenStronghold2()
    {
        allowedBiomeGenBases = (new BiomeGenBase[]
                {
                    BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.taiga, BiomeGenBase.icePlains, BiomeGenBase.iceMountains, BiomeGenBase.desertHills, BiomeGenBase.forestHills, BiomeGenBase.extremeHillsEdge,
                    BiomeGenBase.jungle, BiomeGenBase.jungleHills
                });
        allowedBiomeGenBasesOld = (new BiomeGenBase[]
                {
                    BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland
                });
        structureCoords = new ChunkCoordIntPair[3];
    }

    @Override
    public String func_143025_a()
    {
        return "Stronghold";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int par1, int par2)
    {
        if (!ranBiomeCheck)
        {
            Random random = new Random();
            random.setSeed(worldObj.getSeed());
            double d = random.nextDouble() * Math.PI * 2D;

            for (int k = 0; k < structureCoords.length; k++)
            {
                double d1 = (1.25D + random.nextDouble()) * 32D;
                int l = (int)Math.round(Math.cos(d) * d1);
                int i1 = (int)Math.round(Math.sin(d) * d1);
                ArrayList arraylist = new ArrayList();
                Collections.addAll(arraylist, (ODNBXlite.oldStrongholds() ? allowedBiomeGenBasesOld : allowedBiomeGenBases));
                ChunkPosition chunkposition = worldObj.getWorldChunkManager().findBiomePosition((l << 4) + 8, (i1 << 4) + 8, 112, arraylist, random);

                if (chunkposition != null)
                {
                    l = chunkposition.x >> 4;
                    i1 = chunkposition.z >> 4;
                }
                else
                {
                    System.out.println((new StringBuilder()).append("Placed stronghold in INVALID biome at (").append(l).append(", ").append(i1).append(")").toString());
                }

                structureCoords[k] = new ChunkCoordIntPair(l, i1);
                d += (Math.PI * 2D) / (double)structureCoords.length;
            }

            ranBiomeCheck = true;
        }

        ChunkCoordIntPair achunkcoordintpair[] = structureCoords;
        int i = achunkcoordintpair.length;

        for (int j = 0; j < i; j++)
        {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[j];

            if (par1 == chunkcoordintpair.chunkXPos && par2 == chunkcoordintpair.chunkZPos)
            {
                System.out.println((new StringBuilder()).append(par1).append(", ").append(par2).toString());
                return true;
            }
        }

        return false;
    }

    @Override
    protected List getCoordList()
    {
        ArrayList arraylist = new ArrayList();
        ChunkCoordIntPair achunkcoordintpair[] = structureCoords;
        int i = achunkcoordintpair.length;

        for (int j = 0; j < i; j++)
        {
            ChunkCoordIntPair chunkcoordintpair = achunkcoordintpair[j];

            if (chunkcoordintpair != null)
            {
                arraylist.add(chunkcoordintpair.getChunkPosition(64));
            }
        }

        return arraylist;
    }

    @Override
    protected StructureStart getStructureStart(int par1, int par2)
    {
        if (ODNBXlite.oldStrongholds()){
            return new StructureStrongholdStart2(worldObj, rand, par1, par2);
        }
        StructureStrongholdStart2 structurestrongholdstart;

        for (structurestrongholdstart = new StructureStrongholdStart2(worldObj, rand, par1, par2); structurestrongholdstart.getComponents().isEmpty() || ((ComponentStrongholdStairs2)structurestrongholdstart.getComponents().get(0)).strongholdPortalRoom == null; structurestrongholdstart = new StructureStrongholdStart2(worldObj, rand, par1, par2)) { }

        return structurestrongholdstart;
    }
}
