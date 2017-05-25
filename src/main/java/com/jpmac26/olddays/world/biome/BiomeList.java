package com.jpmac26.olddays.world.biome;

import owg.config.ConfigOWG;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

public class BiomeList 
{
	public static Biome OLDrainforest;
	public static Biome OLDswampland;
	public static Biome OLDseasonalForest;
	public static Biome OLDforest;
	public static Biome OLDsavanna;
	public static Biome OLDshrubland;
	public static Biome OLDtaiga;
	public static Biome OLDdesert;
	public static Biome OLDplains;
	public static Biome OLDtundra;
	
	public static Biome CLASSICnormal;
	public static Biome CLASSICsnow;	
	
	public static void init()
	{
		OLDdesert = (new BiomeBeta(ConfigOWG.biomeIDs[0], 7)).getFoliageColor(16421912, 16421912, 16421912);
		OLDdesert.setBiomeName("owg_desert").setTemperatureRainfall(0.95F, 0.1F).setDisableRain();
		OLDforest = (new BiomeBeta(ConfigOWG.biomeIDs[1], 3)).setColor(353825).setBiomeName("owg_forest").setTemperatureRainfall(0.8F, 0.6F);
		OLDplains = (new BiomeBeta(ConfigOWG.biomeIDs[2], 8)).setColor(353825).setBiomeName("owg_plains").setTemperatureRainfall(0.95F, 0.35F);
		OLDrainforest = (new BiomeBeta(ConfigOWG.biomeIDs[3], 0)).setColor(353825).setBiomeName("owg_rainforest").setTemperatureRainfall(0.95F, 0.95F);
		OLDsavanna = (new BiomeBeta(ConfigOWG.biomeIDs[4], 4)).setColor(16421912).setBiomeName("owg_savanna").setTemperatureRainfall(0.7F, 0.1F);
		OLDseasonalForest = (new BiomeBeta(ConfigOWG.biomeIDs[5], 2)).setColor(353825).setBiomeName("owg_seasonalForest").setTemperatureRainfall(0.95F, 0.7F);
		OLDshrubland = (new BiomeBeta(ConfigOWG.biomeIDs[6], 5)).setColor(353825).setBiomeName("owg_shrubland").setTemperatureRainfall(0.7F, 0.3F);
		OLDswampland = (new BiomeBeta(ConfigOWG.biomeIDs[7], 1)).setColor(353825).setBiomeName("owg_swampland").setTemperatureRainfall(0.55F, 0.65F);
		OLDtaiga = (new BiomeBeta(ConfigOWG.biomeIDs[8], 6)).setColor(16777215).setBiomeName("owg_taiga").setTemperatureRainfall(0.1F, 0.35F).setEnableSnow();
		OLDtundra = (new BiomeBeta(ConfigOWG.biomeIDs[9], 9)).setColor(16777215).setBiomeName("owg_tundra").setTemperatureRainfall(0.1F, 0.1F).setEnableSnow();
		
		CLASSICnormal = (new BiomeClassic(ConfigOWG.biomeIDs[10])).setColor(353825).setBiomeName("owg_Classic");
		CLASSICsnow = (new BiomeClassic(ConfigOWG.biomeIDs[11])).setColor(353825).setBiomeName("owg_Classic_Snow").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F);
		
		BiomeDictionary.registerBiomeType(OLDdesert, Type.HOT, Type.SPARSE, Type.DRY, Type.SANDY);
		BiomeDictionary.registerBiomeType(OLDforest, Type.DENSE, Type.FOREST);
		BiomeDictionary.registerBiomeType(OLDplains, Type.SPARSE, Type.PLAINS, Type.MOUNTAIN);
		BiomeDictionary.registerBiomeType(OLDrainforest, Type.DENSE, Type.WET, Type.JUNGLE, Type.FOREST, Type.SWAMP);
		BiomeDictionary.registerBiomeType(OLDsavanna, Type.HOT, Type.SPARSE, Type.SAVANNA);
		BiomeDictionary.registerBiomeType(OLDseasonalForest, Type.DENSE, Type.FOREST);
		BiomeDictionary.registerBiomeType(OLDshrubland, Type.HOT, Type.SPARSE, Type.DRY, Type.PLAINS);
		BiomeDictionary.registerBiomeType(OLDswampland, Type.DENSE, Type.WET, Type.SWAMP);
		BiomeDictionary.registerBiomeType(OLDtaiga, Type.COLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY);
		BiomeDictionary.registerBiomeType(OLDtundra, Type.COLD, Type.SPARSE, Type.PLAINS, Type.MOUNTAIN, Type.SNOWY);
		
		BiomeDictionary.registerBiomeType(CLASSICnormal, Type.HOT, Type.PLAINS);
		BiomeDictionary.registerBiomeType(CLASSICsnow, Type.COLD, Type.SNOWY);
	}
}
