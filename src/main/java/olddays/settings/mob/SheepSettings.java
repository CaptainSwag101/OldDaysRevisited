package olddays.settings.mob;

import olddays.settings.GlobalBoolConfig;

/**
 * Created by James Pelster on 5/18/17.
 */
public class SheepSettings
{
    public static boolean sheepOldHealth = false;
    public static boolean sheepPunchToShear = true;

    public SheepSettings()
    {
        new GlobalBoolConfig("Punch to Shear", this.getClass(), "sheepPunchToShear", sheepPunchToShear, "OldDaysRevisited.cfg");
        new GlobalBoolConfig("Old Health", this.getClass(), "sheepOldHealth", sheepOldHealth, "OldDaysRevisited.cfg");
    }
}
