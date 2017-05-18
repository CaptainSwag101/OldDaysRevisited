package olddays.settings.mob;

import olddays.settings.GlobalBoolConfig;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class EndermanSettings
{
    public static boolean endermanOldBlockStealing = true;
    public static boolean endermanOldHealth = true;
    public static boolean endermanOldAppearance = true;
    public static boolean endermanOldSounds = true;


    public EndermanSettings()
    {
        new GlobalBoolConfig("Old Appearance", this.getClass(), "endermanOldAppearance", endermanOldAppearance, "OldDaysRevisited.cfg");
        new GlobalBoolConfig("Old Block Stealing", this.getClass(), "endermanOldBlockStealing", endermanOldBlockStealing, "OldDaysRevisited.cfg");
        new GlobalBoolConfig("Old Health", this.getClass(), "endermanOldHealth", endermanOldHealth, "OldDaysRevisited.cfg");
        new GlobalBoolConfig("Old Sounds", this.getClass(), "endermanOldSounds", endermanOldSounds, "OldDaysRevisited.cfg");
    }
}
