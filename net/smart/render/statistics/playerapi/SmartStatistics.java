// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics.playerapi;

import net.smart.render.statistics.IEntityPlayerSP;
import api.player.client.ClientPlayerAPI;

public abstract class SmartStatistics
{
    public static final String ID = "Smart Render";
    
    public static void register() {
        ClientPlayerAPI.register("Smart Render", (Class)SmartStatisticsPlayerBase.class);
    }
    
    public static IEntityPlayerSP getPlayerBase(final uf entityPlayer) {
        if (entityPlayer instanceof bex) {
            return (SmartStatisticsPlayerBase)((bex)entityPlayer).getClientPlayerBase("Smart Render");
        }
        return null;
    }
}
