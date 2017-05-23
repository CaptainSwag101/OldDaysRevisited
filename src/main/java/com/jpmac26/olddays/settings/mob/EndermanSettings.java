package com.jpmac26.olddays.settings.mob;

import com.jpmac26.olddays.ConfigHandler;
import com.jpmac26.olddays.entities.EntityODEnderman;

/**
 * Created by James Pelster on 5/23/17.
 */
public class EndermanSettings
{
    public static void syncConfig() {
        String category = "mobs.enderman";

        ConfigHandler.config.get(
                category,
                "oldAppearance",
                true,
                "Causes Endermen to appear as they did in the 1.9 prereleases."
        ).set(EntityODEnderman.oldAppearance);

        ConfigHandler.config.get(
                category,
                "oldBlockStealing",
                true,
                "Allow Endermen to pick up a larger variety of blocks, like in the 1.9 prereleases."
        ).set(EntityODEnderman.oldBlockStealing);

        ConfigHandler.config.get(
                category,
                "oldHealth",
                false,
                "Causes Endermen to have 40 health instead of 20, like in the 1.9 prereleases."
        ).set(EntityODEnderman.oldHealth);

        ConfigHandler.config.get(
                category,
                "oldSounds",
                false,
                "Causes Endermen to make Zombie noises, like in the 1.9 prereleases."
        ).set(EntityODEnderman.oldSounds);


        EntityODEnderman.oldAppearance = ConfigHandler.config.getBoolean("oldAppearance", category, true, "Causes Endermen to appear as they did in the 1.9 prereleases.");
        EntityODEnderman.oldBlockStealing = ConfigHandler.config.getBoolean("oldBlockStealing", category, true, "Allow Endermen to pick up a larger variety of blocks, like in the 1.9 prereleases.");
        EntityODEnderman.oldHealth = ConfigHandler.config.getBoolean("oldHealth", category, false, "Causes Endermen to have 40 health instead of 20, like in the 1.9 prereleases.");
        EntityODEnderman.oldSounds = ConfigHandler.config.getBoolean("oldSounds", category, false, "Causes Endermen to make Zombie noises, like in the 1.9 prereleases.");
    }
}
