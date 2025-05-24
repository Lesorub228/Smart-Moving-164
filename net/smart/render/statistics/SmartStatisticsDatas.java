// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics;

public class SmartStatisticsDatas
{
    public final SmartStatisticsData horizontal;
    public final SmartStatisticsData vertical;
    public final SmartStatisticsData all;
    private float renderPartialTicks;
    
    public SmartStatisticsDatas() {
        this.horizontal = new SmartStatisticsData();
        this.vertical = new SmartStatisticsData();
        this.all = new SmartStatisticsData();
    }
    
    public float getTotalHorizontalDistance() {
        return this.horizontal.getTotalDistance(this.renderPartialTicks);
    }
    
    public float getTotalVerticalDistance() {
        return this.vertical.getTotalDistance(this.renderPartialTicks);
    }
    
    public float getTotalDistance() {
        return this.all.getTotalDistance(this.renderPartialTicks);
    }
    
    public float getCurrentHorizontalSpeed() {
        return this.horizontal.getCurrentSpeed(this.renderPartialTicks);
    }
    
    public float getCurrentVerticalSpeed() {
        return this.vertical.getCurrentSpeed(this.renderPartialTicks);
    }
    
    public float getCurrentSpeed() {
        return this.all.getCurrentSpeed(this.renderPartialTicks);
    }
    
    public void setReady(final float renderPartialTicks) {
        this.renderPartialTicks = renderPartialTicks;
    }
    
    public boolean isReady() {
        return !Float.isNaN(this.renderPartialTicks);
    }
    
    public void initialize(final SmartStatisticsDatas previous) {
        this.renderPartialTicks = Float.NaN;
        this.horizontal.initialize(previous.horizontal);
        this.vertical.initialize(previous.vertical);
        this.all.initialize(previous.all);
    }
}
