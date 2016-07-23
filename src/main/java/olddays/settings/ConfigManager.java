package olddays.settings;

import olddays.entities.enderman.EntityODEnderman;

public class ConfigManager {

	public ConfigManager()
	{
	    new GlobalBoolConfig("Old Endermen", EntityODEnderman.class, "oldAppearance", EntityODEnderman.oldAppearance, "olddays_config.cfg");
	    new GlobalBoolConfig("Old Mob Health", MobSettings.class, "oldMobHealth", MobSettings.getOldMobHealth(), "olddays_config.cfg");
        new GlobalBoolConfig("Un-nerfed Endermen", EntityODEnderman.class, "oldPicking", EntityODEnderman.oldPicking, "olddays_config.cfg");
	}
	
}
