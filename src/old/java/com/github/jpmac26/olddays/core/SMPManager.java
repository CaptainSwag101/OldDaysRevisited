package com.github.jpmac26.olddays.gui;

public class SMPManager{
    public static final int PACKET_C2S_PROP = 0;
    public static final int PACKET_C2S_REQUEST = 1;
    public static final int PACKET_S2C_PROP = 0;
    public static final int PACKET_S2C_MODULE = 1;
    public static final int PACKET_S2C_SEED = 2;

    public mod_OldDays core;

    public SMPManager(mod_OldDays c){
        core = c;
    }
}