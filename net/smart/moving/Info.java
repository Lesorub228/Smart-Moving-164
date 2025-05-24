// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public class Info
{
    public static final byte StatePacketId = 0;
    public static final byte ConfigInfoPacketId = 1;
    public static final byte ConfigContentPacketId = 2;
    public static final byte ConfigChangePacketId = 3;
    public static final byte SpeedChangePacketId = 4;
    public static final byte HungerChangePacketId = 5;
    public static final byte SoundPacketId = 6;
    public static final String ModName = "Smart Moving";
    public static final String ModVersion = "14.5";
    public static final String ModComVersion = "2.3";
    public static final String ModComMessage = "Smart Moving uses communication protocol 2.3";
    public static final String ModComId;
    public static final String ModString = "Smart Moving 14.5";
    public static final int ConfigChatId;
    public static final int SpeedChatId;
    
    static {
        ModComId = "Smart Moving".replace(" ", "") + " " + "2.3";
        ConfigChatId = "Smart Moving".hashCode();
        SpeedChatId = "Smart Moving".hashCode() + 1;
    }
}
