package com.github.jpmac26.nbxlite.nbxlite.format;

import net.minecraft.src.ChunkCoordIntPair;
import net.minecraft.src.NBTTagCompound;

class ThreadedChunkLoaderPending
{
    public final ChunkCoordIntPair field_40739_a;
    public final NBTTagCompound field_40738_b;

    public ThreadedChunkLoaderPending(ChunkCoordIntPair chunkcoordintpair, NBTTagCompound nbttagcompound)
    {
        field_40739_a = chunkcoordintpair;
        field_40738_b = nbttagcompound;
    }
}
