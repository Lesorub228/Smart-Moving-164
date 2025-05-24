// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics;

public abstract class SmartStatisticsContext
{
    protected static boolean calculateHorizontalStats;
    
    public static void setCalculateHorizontalStats(final boolean flag) {
        SmartStatisticsContext.calculateHorizontalStats = flag;
    }
    
    public static void onTickInGame() {
        final atv minecraft = atv.w();
        if (minecraft.f != null && minecraft.f.I) {
            SmartStatisticsFactory.handleMultiPlayerTick(minecraft);
        }
    }
    
    static {
        SmartStatisticsContext.calculateHorizontalStats = false;
    }
}
