// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.utilities;

import java.lang.reflect.Field;

public class Interface
{
    private static Class<?> ropesPlusClient;
    private static Field onZipLine;
    
    public static boolean isRopeSliding() {
        return Interface.onZipLine != null && Reflect.GetField(Interface.onZipLine, null) != null;
    }
    
    static {
        Interface.ropesPlusClient = Reflect.LoadClass(Install.class, Install.RopesPlusClient, false);
        Interface.onZipLine = ((Interface.ropesPlusClient != null) ? Reflect.GetField(Interface.ropesPlusClient, Install.RopesPlusClient_onZipLine, false) : null);
    }
}
