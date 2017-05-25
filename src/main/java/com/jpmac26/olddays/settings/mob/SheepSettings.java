package com.jpmac26.olddays.settings.mob;

import com.jpmac26.olddays.ConfigHandler;
import com.jpmac26.olddays.entity.EntityODSheep;

/**
 * Created by James Pelster on 5/18/17.
 */
public class SheepSettings
{
    public static void load()
    {
        String category = "mobs.sheep";

        EntityODSheep.punchToShear =
            ConfigHandler.config.get(
                category,
                "punchToShear",
                false,
                "Causes sheep to drop their wool when attacked, instead of requiring shears."
            ).getBoolean();
    }

    public static void save()
    {
        String category = "mobs.sheep";

        ConfigHandler.config.get(
            category,
            "punchToShear",
            false,
            "Causes sheep to drop their wool when attacked, instead of requiring shears."
        ).set(EntityODSheep.punchToShear);
    }
}
