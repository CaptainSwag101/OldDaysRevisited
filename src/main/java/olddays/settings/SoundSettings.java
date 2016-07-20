package olddays.settings;

import olddays.entities.enderman.EntityODEnderman;

/**
 * Created by James Pelster on 7/20/2016.
 */
public class SoundSettings {

    public static void toggleOldEndermanSounds() { EntityODEnderman.oldSounds = !EntityODEnderman.oldSounds; }

    public static boolean getOldEndermanSounds() { return EntityODEnderman.oldSounds; }
}
