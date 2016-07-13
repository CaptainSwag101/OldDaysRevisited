package com.github.jpmac26.olddays.settings;

import com.github.jpmac26.olddays.entities.enderman.EntityODEnderman;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class MobSettings {

    public static void setOldEndermen(boolean value)
    {
        EntityODEnderman.smoke = value;
        EntityODEnderman.oldPicking = value;
        EntityODEnderman.oldhealth = value;
    }
}
