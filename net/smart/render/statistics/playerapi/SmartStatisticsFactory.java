// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics.playerapi;

import net.smart.render.statistics.IEntityPlayerSP;
import net.smart.render.statistics.SmartStatistics;

public class SmartStatisticsFactory extends net.smart.render.statistics.SmartStatisticsFactory
{
    public static void initialize() {
        if (!net.smart.render.statistics.SmartStatisticsFactory.isInitialized()) {
            new SmartStatisticsFactory();
        }
    }
    
    @Override
    protected SmartStatistics doGetInstance(final uf entityPlayer) {
        final SmartStatistics statistics = super.doGetInstance(entityPlayer);
        if (statistics != null) {
            return statistics;
        }
        final IEntityPlayerSP playerBase = net.smart.render.statistics.playerapi.SmartStatistics.getPlayerBase(entityPlayer);
        if (playerBase != null) {
            return playerBase.getStatistics();
        }
        return null;
    }
}
