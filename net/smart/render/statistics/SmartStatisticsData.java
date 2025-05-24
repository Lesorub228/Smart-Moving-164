// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics;

public class SmartStatisticsData
{
    public float prevLegYaw;
    public float legYaw;
    public float total;
    
    public float getCurrentSpeed(final float renderPartialTicks) {
        return Math.min(1.0f, this.prevLegYaw + (this.legYaw - this.prevLegYaw) * renderPartialTicks);
    }
    
    public float getTotalDistance(final float renderPartialTicks) {
        return this.total - this.legYaw * (1.0f - renderPartialTicks);
    }
    
    public void initialize(final SmartStatisticsData previous) {
        this.prevLegYaw = previous.legYaw;
        this.legYaw = previous.legYaw;
        this.total = previous.total;
    }
    
    public float calcualte(float distance) {
        distance *= 4.0f;
        this.legYaw += (distance - this.legYaw) * 0.4f;
        this.total += this.legYaw;
        return distance;
    }
    
    public void apply(final uf sp) {
        sp.aF = this.prevLegYaw;
        sp.aG = this.legYaw;
        sp.aH = this.total;
    }
}
