// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.playerapi;

import net.smart.moving.IEntityPlayerMP;
import net.smart.moving.IEntityPlayerSP;

public abstract class SmartMoving
{
    public static final String SPC_ID = "Single Player Commands";
    
    public static void register() {
        SmartMovingPlayerBase.registerPlayerBase();
        SmartMovingServerPlayerBase.registerPlayerBase();
    }
    
    public static IEntityPlayerSP getPlayerBase(final uf entityPlayer) {
        if (entityPlayer instanceof bex) {
            return SmartMovingPlayerBase.getPlayerBase((bex)entityPlayer);
        }
        return null;
    }
    
    public static IEntityPlayerMP getServerPlayerBase(final uf entityPlayer) {
        if (entityPlayer instanceof jv) {
            return SmartMovingServerPlayerBase.getPlayerBase(entityPlayer);
        }
        return null;
    }
}
