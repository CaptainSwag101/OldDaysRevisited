package com.github.jpmac26.olddays.settings;

import com.github.jpmac26.olddays.entities.enderman.EntityODEnderman;
import com.github.jpmac26.olddays.entities.enderman.LayerODEndermanEyes;

/**
 * Created by James Pelster on 7/13/2016.
 */
public class MobSettings {

    public static void setOldMobHealth(boolean value)
    {
        EntityODEnderman.oldhealth = value;
    }

    public static void setUnnerfedEndermen(boolean value)
    {
        EntityODEnderman.oldPicking = value;
    }
}
