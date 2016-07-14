package com.github.jpmac26.nbxlite.nbxlite.chunkproviders;

import net.minecraft.src.*;

public class ChunkProviderClassic extends ChunkProviderBaseFinite{
    public ChunkProviderClassic(World world, long l){
        super(world, l);
    }

    @Override
    public void generateFiniteLevel(){
        ODNBXlite.generateClassicLevel(seed);
    }
}
