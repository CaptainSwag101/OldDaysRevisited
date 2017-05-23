package com.jpmac26.olddays.settings.mob;

import com.jpmac26.olddays.ConfigHandler;
import com.jpmac26.olddays.entities.EntityODSheep;

/**
 * Created by James Pelster on 5/18/17.
 */
public class SheepSettings
{
    public static void syncConfig()
    {
        String category = "mobs.sheep";
        ConfigHandler.config.get(
                category,
                "punchToShear",
                true,
                "Causes sheep to drop their wool when attacked, instead of requiring shears."
        ).set(EntityODSheep.punchToShear);

        //EntityODSheep.oldHealth = ConfigHandler.config.getBoolean("oldHealth", category, false, "Causes Endermen to have 40 health instead of 20, like in the 1.9 prereleases.");
        EntityODSheep.punchToShear = ConfigHandler.config.getBoolean("punchToShear", category, true, "Causes sheep to drop their wool when attacked, instead of requiring shears.");
    }
}
