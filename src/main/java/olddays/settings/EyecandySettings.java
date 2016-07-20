package olddays.settings;

import olddays.entities.enderman.EntityODEnderman;

/**
 * Created by James Pelster on 7/14/2016.
 */
public class EyecandySettings {

    public static void toggleOldEndermen() { EntityODEnderman.oldAppearance = !EntityODEnderman.oldAppearance; }

    public static boolean getOldEndermen() { return EntityODEnderman.oldAppearance; }
}
