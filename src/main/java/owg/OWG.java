package owg;

import com.jpmac26.olddays.world.biome.BiomeList;
import owg.config.ConfigOWG;
import owg.support.Support;
import com.jpmac26.olddays.world.WorldTypeOld;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="OWG", name="OldWorldGen", version="1.0.3")
public class OWG
{	
	@Instance("OWG")
	public static OWG instance;
	
	public static final WorldTypeOld worldtype = (new WorldTypeOld("OWG"));
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		ConfigOWG.init(event);
		BiomeList.init();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		Support.init();
	}
}