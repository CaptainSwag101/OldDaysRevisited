package com.github.jpmac26.olddays.settings;

import com.github.jpmac26.olddays.entities.enderman.EntityODEnderman;

/**
 * Created by James Pelster on 7/14/2016.
 */
public class EyecandySettings {

    public static void setOldEndermen(boolean value)
    {
        EntityODEnderman.smoke = value;
        EntityODEnderman.oldEyes = value;
    }
}
