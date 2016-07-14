package com.github.jpmac26.nbxlite.nbxlite.format;

import java.io.*;
import java.util.*;
import com.github.jpmac26.nbxlite.Chunk;
import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.CompressedStreamTools;
import net.minecraft.src.IChunkLoader;
import net.minecraft.src.IThreadedFileIO;
import net.minecraft.src.NBTTagCompound;
import net.minecraft.src.ThreadedFileIOBase;
import com.github.jpmac26.nbxlite.World;

public class ThreadedChunkLoader
    implements IThreadedFileIO, IChunkLoader
{
    private List pendingChunkList;
    private Set pendingChunkCoords;
    private Object chunkSaveLock;
    private final File chunkSaveLocation;

    public ThreadedChunkLoader(File file)
    {
        pendingChunkList = new ArrayList();
        pendingChunkCoords = new HashSet();
        chunkSaveLock = new Object();
        chunkSaveLocation = file;
    }

    public Chunk loadChunk(World world, int i, int j)
    throws IOException
    {
        NBTTagCompound nbttagcompound = null;
        ChunkCoordIntPair chunkcoordintpair = new ChunkCoordIntPair(i, j);
        synchronized (chunkSaveLock)
        {
            if (pendingChunkCoords.contains(chunkcoordintpair))
            {
                int k = 0;
                do
                {
                    if (k >= pendingChunkList.size())
                    {
                        break;
                    }
                    if (((ThreadedChunkLoaderPending)pendingChunkList.get(k)).field_40739_a.equals(chunkcoordintpair))
                    {
                        nbttagcompound = ((ThreadedChunkLoaderPending)pendingChunkList.get(k)).field_40738_b;
                        break;
                    }
                    k++;
                }
                while (true);
            }
        }
        if (nbttagcompound == null)
        {
            java.io.DataInputStream datainputstream = RegionFileCache2.getChunkInputStream(chunkSaveLocation, i, j);
            if (datainputstream != null)
            {
                nbttagcompound = CompressedStreamTools.read(datainputstream);
            }
            else
            {
                return null;
            }
        }
        if (!nbttagcompound.hasKey("Level"))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is missing level data, skipping").toString());
            return null;
        }
        if (!nbttagcompound.getCompoundTag("Level").hasKey("Blocks"))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is missing block data, skipping").toString());
            return null;
        }
        Chunk chunk = OldChunkLoader.loadChunkIntoWorldFromCompound(world, nbttagcompound.getCompoundTag("Level"));
        if (!chunk.isAtLocation(i, j))
        {
            System.out.println((new StringBuilder()).append("Chunk file at ").append(i).append(",").append(j).append(" is in the wrong location; relocating. (Expected ").append(i).append(", ").append(j).append(", got ").append(chunk.xPosition).append(", ").append(chunk.zPosition).append(")").toString());
            nbttagcompound.setInteger("xPos", i);
            nbttagcompound.setInteger("zPos", j);
            chunk = OldChunkLoader.loadChunkIntoWorldFromCompound(world, nbttagcompound.getCompoundTag("Level"));
        }
//         chunk.removeUnknownBlocks();
        return chunk;
    }

    public void saveChunk(World world, Chunk chunk)
    {
        try
        {
            world.checkSessionLock();
            NBTTagCompound nbttagcompound = new NBTTagCompound();
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            nbttagcompound.setTag("Level", nbttagcompound1);
            OldChunkLoader.storeChunkInCompound(chunk, world, nbttagcompound1);
            queueChunkMap(chunk.getChunkCoordIntPair(), nbttagcompound);
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void queueChunkMap(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound)
    {
        synchronized (chunkSaveLock)
        {
            if (pendingChunkCoords.contains(chunkcoordintpair))
            {
                for (int i = 0; i < pendingChunkList.size(); i++)
                {
                    if (((ThreadedChunkLoaderPending)pendingChunkList.get(i)).field_40739_a.equals(chunkcoordintpair))
                    {
                        pendingChunkList.set(i, new ThreadedChunkLoaderPending(chunkcoordintpair, nbttagcompound));
                        return;
                    }
                }
            }
            pendingChunkList.add(new ThreadedChunkLoaderPending(chunkcoordintpair, nbttagcompound));
            pendingChunkCoords.add(chunkcoordintpair);
            ThreadedFileIOBase.threadedIOInstance.queueIO(this);
            return;
        }
    }

    public boolean writeNextIO()
    {
        ThreadedChunkLoaderPending threadedchunkloaderpending = null;
        synchronized (chunkSaveLock)
        {
            if (pendingChunkList.size() > 0)
            {
                threadedchunkloaderpending = (ThreadedChunkLoaderPending)pendingChunkList.remove(0);
                pendingChunkCoords.remove(threadedchunkloaderpending.field_40739_a);
            }
            else
            {
                return false;
            }
        }
        if (threadedchunkloaderpending != null)
        {
            try
            {
                writeChunk(threadedchunkloaderpending);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
        }
        return true;
    }

    public void writeChunk(ThreadedChunkLoaderPending threadedchunkloaderpending)
    throws IOException
    {
        DataOutputStream dataoutputstream = RegionFileCache2.getChunkOutputStream(chunkSaveLocation, threadedchunkloaderpending.field_40739_a.chunkXPos, threadedchunkloaderpending.field_40739_a.chunkZPos);
        CompressedStreamTools.write(threadedchunkloaderpending.field_40738_b, dataoutputstream);
        dataoutputstream.close();
    }

    public void saveExtraChunkData(World world, Chunk chunk)
    {
    }

    public void chunkTick()
    {
    }

    public void saveExtraData()
    {
    }
}
