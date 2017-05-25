package com.jpmac26.olddays.settings.mob;

import com.jpmac26.olddays.ConfigHandler;
import com.jpmac26.olddays.entity.EntityODEnderman;

/**
 * Created by James Pelster on 5/23/17.
 */
public class EndermanSettings
{
    public static void load() {
        String category = "mobs.enderman";

        EntityODEnderman.oldAppearance =
            ConfigHandler.config.get(
                category,
                "oldAppearance",
                false,
                "Causes Endermen to appear as they did in the 1.9 prereleases."
            ).getBoolean();

        EntityODEnderman.oldBlockStealing =
            ConfigHandler.config.get(
                category,
                "oldBlockStealing",
                false,
                "Allow Endermen to pick up a larger variety of blocks, like in the 1.9 prereleases."
            ).getBoolean();

        EntityODEnderman.oldHealth =
            ConfigHandler.config.get(
                category,
                "oldHealth",
                false,
                "Causes Endermen to have 40 health instead of 20, like in the 1.9 prereleases."
            ).getBoolean();

        EntityODEnderman.oldSounds =
            ConfigHandler.config.get(
                category,
                "oldSounds",
                false,
                "Causes Endermen to make Zombie noises, like in the 1.9 prereleases."
            ).getBoolean();
    }

    public static void save() {
        String category = "mobs.enderman";

        ConfigHandler.config.get(
            category,
            "oldAppearance",
            false,
            "Causes Endermen to appear as they did in the 1.9 prereleases."
        ).set(EntityODEnderman.oldAppearance);

        ConfigHandler.config.get(
            category,
            "oldBlockStealing",
            false,
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
    }
}
