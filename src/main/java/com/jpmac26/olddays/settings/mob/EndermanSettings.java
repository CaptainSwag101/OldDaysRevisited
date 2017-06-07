package com.jpmac26.olddays.settings.mob;

import com.jpmac26.olddays.ConfigHandler;
import com.jpmac26.olddays.entity.EntityEndermanOld;

/**
 * Created by James Pelster on 5/23/17.
 */
public class EndermanSettings
{
    public static void load()
    {
        String category = "mobs.enderman";

        EntityEndermanOld.oldAppearance =
            ConfigHandler.config.get(
                category,
                "oldAppearance",
                false,
                "Causes Endermen to appear as they did in the 1.9 prereleases."
            ).getBoolean();

        EntityEndermanOld.oldBlockStealing =
            ConfigHandler.config.get(
                category,
                "oldBlockStealing",
                false,
                "Allow Endermen to pick up a larger variety of blocks, like in the 1.9 prereleases."
            ).getBoolean();

        EntityEndermanOld.oldHealth =
            ConfigHandler.config.get(
                category,
                "oldHealth",
                false,
                "Causes Endermen to have 40 health instead of 20, like in the 1.9 prereleases."
            ).getBoolean();

        EntityEndermanOld.oldSounds =
            ConfigHandler.config.get(
                category,
                "oldSounds",
                false,
                "Causes Endermen to make Zombie noises, like in the 1.9 prereleases."
            ).getBoolean();
    }

    public static void save()
    {
        String category = "mobs.enderman";

        ConfigHandler.config.get(
            category,
            "oldAppearance",
            false,
            "Causes Endermen to appear as they did in the 1.9 prereleases."
        ).set(EntityEndermanOld.oldAppearance);

        ConfigHandler.config.get(
            category,
            "oldBlockStealing",
            false,
            "Allow Endermen to pick up a larger variety of blocks, like in the 1.9 prereleases."
        ).set(EntityEndermanOld.oldBlockStealing);

        ConfigHandler.config.get(
            category,
            "oldHealth",
            false,
            "Causes Endermen to have 40 health instead of 20, like in the 1.9 prereleases."
        ).set(EntityEndermanOld.oldHealth);

        ConfigHandler.config.get(
            category,
            "oldSounds",
            false,
            "Causes Endermen to make Zombie noises, like in the 1.9 prereleases."
        ).set(EntityEndermanOld.oldSounds);
    }
}
