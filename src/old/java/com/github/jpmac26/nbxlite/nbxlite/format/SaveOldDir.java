package com.github.jpmac26.nbxlite.nbxlite.format;

import java.io.File;
import java.util.List;
import net.minecraft.src.IChunkLoader;
import net.minecraft.src.SaveHandler;
import com.github.jpmac26.nbxlite.WorldInfo;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldProviderEnd;
import net.minecraft.src.WorldProviderHell;

public class SaveOldDir extends SaveHandler
{
    public SaveOldDir(File file, String s, boolean flag)
    {
        super(file, s, flag);
    }
}
