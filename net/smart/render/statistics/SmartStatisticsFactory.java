// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics;

import java.util.Iterator;
import java.util.Hashtable;

public class SmartStatisticsFactory
{
    private static SmartStatisticsFactory factory;
    private Hashtable<Integer, SmartStatisticsOther> otherStatistics;
    
    public SmartStatisticsFactory() {
        if (SmartStatisticsFactory.factory != null) {
            throw new RuntimeException("FATAL: Can only create one instance of type 'StatisticsFactory'");
        }
        SmartStatisticsFactory.factory = this;
    }
    
    protected static boolean isInitialized() {
        return SmartStatisticsFactory.factory != null;
    }
    
    public static void initialize() {
        if (!isInitialized()) {
            new SmartStatisticsFactory();
        }
    }
    
    public static void handleMultiPlayerTick(final atv minecraft) {
        SmartStatisticsFactory.factory.doHandleMultiPlayerTick(minecraft);
    }
    
    public static SmartStatistics getInstance(final uf entityPlayer) {
        return SmartStatisticsFactory.factory.doGetInstance(entityPlayer);
    }
    
    public static SmartStatisticsOther getOtherStatistics(final int entityId) {
        return SmartStatisticsFactory.factory.doGetOtherStatistics(entityId);
    }
    
    public static SmartStatisticsOther getOtherStatistics(final bey entity) {
        return SmartStatisticsFactory.factory.doGetOtherStatistics(entity);
    }
    
    protected void doHandleMultiPlayerTick(final atv minecraft) {
        for (final nn player : minecraft.f.h) {
            if (player instanceof bey) {
                final bey otherPlayer = (bey)player;
                final SmartStatisticsOther statistics = this.doGetOtherStatistics(otherPlayer);
                statistics.calculateAllStats();
                statistics.foundAlive = true;
            }
        }
        if (this.otherStatistics == null || this.otherStatistics.isEmpty()) {
            return;
        }
        final Iterator<Integer> entityIds = this.otherStatistics.keySet().iterator();
        while (entityIds.hasNext()) {
            final Integer entityId = entityIds.next();
            final SmartStatisticsOther statistics = this.otherStatistics.get(entityId);
            if (statistics.foundAlive) {
                statistics.foundAlive = false;
            }
            else {
                entityIds.remove();
            }
        }
    }
    
    protected SmartStatistics doGetInstance(final uf entityPlayer) {
        if (entityPlayer instanceof bey) {
            return this.doGetOtherStatistics(entityPlayer.k);
        }
        if (entityPlayer instanceof IEntityPlayerSP) {
            return ((IEntityPlayerSP)entityPlayer).getStatistics();
        }
        return null;
    }
    
    protected SmartStatisticsOther doGetOtherStatistics(final int entityId) {
        SmartStatisticsOther statistics = this.tryGetOtherStatistics(entityId);
        if (statistics == null) {
            final nn entity = atv.w().f.a(entityId);
            if (entity != null && entity instanceof bey) {
                statistics = this.addOtherStatistics((bey)entity);
            }
        }
        return statistics;
    }
    
    protected SmartStatisticsOther doGetOtherStatistics(final bey entity) {
        SmartStatisticsOther statistics = this.tryGetOtherStatistics(entity.k);
        if (statistics == null) {
            statistics = this.addOtherStatistics(entity);
        }
        return statistics;
    }
    
    protected final SmartStatisticsOther tryGetOtherStatistics(final int entityId) {
        if (this.otherStatistics == null) {
            this.otherStatistics = new Hashtable<Integer, SmartStatisticsOther>();
        }
        return this.otherStatistics.get(entityId);
    }
    
    protected final SmartStatisticsOther addOtherStatistics(final bey entity) {
        final SmartStatisticsOther statistics = new SmartStatisticsOther(entity);
        this.otherStatistics.put(entity.k, statistics);
        return statistics;
    }
}
