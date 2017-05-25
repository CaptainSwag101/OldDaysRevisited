package com.jpmac26.olddays.world.biome;


import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BiomeClassic extends Biome
{
    public BiomeClassic(Biome.BiomeProperties properties)
    {
        super(properties);
        spawnableCreatureList.add(new SpawnListEntry(EntityWolf.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 2, 1, 1));
    }
    
    @SideOnly(Side.CLIENT)
    public int getGrassColor(BlockPos pos)
    {
        return 0xABFF67;
    }
    
    @SideOnly(Side.CLIENT)
    public int getFoliageColor(BlockPos pos)
    {
        return 0x4FFF2B;
    }  
}
