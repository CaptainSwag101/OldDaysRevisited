package com.github.jpmac26.nbxlite.nbxlite.chunkproviders;

import net.minecraft.src.*;

public class ChunkProviderIndev extends ChunkProviderBaseFinite{
    public ChunkProviderIndev(World world, long l){
        super(world, l);
    }

    @Override
    public void generateFiniteLevel(){
        ODNBXlite.generateIndevLevel(seed);
    }
}
