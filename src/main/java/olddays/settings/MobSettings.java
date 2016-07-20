package olddays.settings;

import olddays.entities.enderman.EntityODEnderman;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class MobSettings {

    private static boolean oldMobHealth = true;

    public static void toggleOldMobHealth()
    {
        oldMobHealth = !oldMobHealth;
        EntityODEnderman.oldHealth = oldMobHealth;
    }

    public static boolean getOldMobHealth() { return oldMobHealth; }

    public static void toggleUnnerfedEndermen() { EntityODEnderman.oldPicking = !EntityODEnderman.oldPicking; }

    public static boolean getUnnerfedEndermen() { return EntityODEnderman.oldPicking; }

    public static void toggleOldEndermen() { EntityODEnderman.oldAppearance = !EntityODEnderman.oldAppearance; }

    public static boolean getOldEndermen() { return EntityODEnderman.oldAppearance; }

    public static void toggleOldEndermanSounds() { EntityODEnderman.oldSounds = !EntityODEnderman.oldSounds; }

    public static boolean getOldEndermanSounds() { return EntityODEnderman.oldSounds; }
}
