// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics;

public class SmartStatistics extends SmartStatisticsContext
{
    private final uf sp;
    private float tickDistance;
    public int ticksRiding;
    public float prevHorizontalAngle;
    private static final SmartStatisticsDatas dummy;
    private final SmartStatisticsDatas[] datas;
    private int currentDataIndex;
    
    public SmartStatistics(final uf sp) {
        this.prevHorizontalAngle = Float.NaN;
        this.datas = new SmartStatisticsDatas[10];
        this.currentDataIndex = -1;
        this.sp = sp;
    }
    
    public void calculateAllStats() {
        final double diffX = this.sp.u - this.sp.r;
        final double diffY = this.sp.v - this.sp.s;
        final double diffZ = this.sp.w - this.sp.t;
        final SmartStatisticsDatas previous = this.get();
        ++this.currentDataIndex;
        if (this.currentDataIndex >= this.datas.length) {
            this.currentDataIndex = 0;
        }
        SmartStatisticsDatas data = this.datas[this.currentDataIndex];
        if (data == null) {
            final SmartStatisticsDatas[] datas = this.datas;
            final int currentDataIndex = this.currentDataIndex;
            final SmartStatisticsDatas smartStatisticsDatas = new SmartStatisticsDatas();
            datas[currentDataIndex] = smartStatisticsDatas;
            data = smartStatisticsDatas;
        }
        data.initialize(previous);
        data.horizontal.calcualte(ls.a(diffX * diffX + diffZ * diffZ));
        data.vertical.calcualte((float)Math.abs(diffY));
        this.tickDistance = data.all.calcualte(ls.a(diffX * diffX + diffY * diffY + diffZ * diffZ));
        if (SmartStatistics.calculateHorizontalStats) {
            data.horizontal.apply(this.sp);
        }
    }
    
    public void calculateRiddenStats() {
        ++this.ticksRiding;
    }
    
    public float getHorizontalPrevLegYaw() {
        return this.sp.aF;
    }
    
    public float getHorizontalLegYaw() {
        return this.sp.aG;
    }
    
    public float getHorizontalTotal() {
        return this.sp.aH;
    }
    
    public float getVerticalPrevLegYaw() {
        return this.datas[this.currentDataIndex].vertical.prevLegYaw;
    }
    
    public float getVerticalLegYaw() {
        return this.datas[this.currentDataIndex].vertical.legYaw;
    }
    
    public float getVerticalTotal() {
        return this.datas[this.currentDataIndex].vertical.total;
    }
    
    public float getAllPrevLegYaw() {
        return this.datas[this.currentDataIndex].all.prevLegYaw;
    }
    
    public float getAllLegYaw() {
        return this.datas[this.currentDataIndex].all.legYaw;
    }
    
    public float getAllTotal() {
        return this.datas[this.currentDataIndex].all.total;
    }
    
    public float getTickDistance() {
        return this.tickDistance;
    }
    
    public float getTotalHorizontalDistance(final float renderPartialTicks) {
        return this.get(renderPartialTicks).getTotalHorizontalDistance();
    }
    
    public float getTotalVerticalDistance(final float renderPartialTicks) {
        return this.get(renderPartialTicks).getTotalVerticalDistance();
    }
    
    public float getTotalDistance(final float renderPartialTicks) {
        return this.get(renderPartialTicks).getTotalDistance();
    }
    
    public float getCurrentHorizontalSpeed(final float renderPartialTicks) {
        return this.get(renderPartialTicks).getCurrentHorizontalSpeed();
    }
    
    public float getCurrentVerticalSpeed(final float renderPartialTicks) {
        return this.get(renderPartialTicks).getCurrentVerticalSpeed();
    }
    
    public float getCurrentSpeed(final float renderPartialTicks) {
        return this.get(renderPartialTicks).getCurrentSpeed();
    }
    
    private SmartStatisticsDatas get() {
        return (this.currentDataIndex == -1) ? SmartStatistics.dummy : this.datas[this.currentDataIndex];
    }
    
    private SmartStatisticsDatas get(final float renderPartialTicks) {
        final SmartStatisticsDatas data = this.get();
        data.setReady(renderPartialTicks);
        return data;
    }
    
    public float getCurrentHorizontalSpeedFlattened(final float renderPartialTicks, int strength) {
        strength = Math.min(strength, this.datas.length);
        if (strength < 0) {
            strength = this.datas.length;
        }
        this.get(renderPartialTicks);
        float sum = 0.0f;
        int count = 0;
        for (int i = 0, dataIndex = this.currentDataIndex; i < strength; ++i, --dataIndex) {
            if (dataIndex < 0) {
                dataIndex = this.datas.length - 1;
            }
            final SmartStatisticsDatas data = this.datas[dataIndex];
            if (data == null) {
                break;
            }
            if (!data.isReady()) {
                break;
            }
            sum += data.getCurrentHorizontalSpeed();
            ++count;
        }
        return sum / count;
    }
    
    public float getCurrentVerticalSpeedFlattened(final float renderPartialTicks, int strength) {
        strength = Math.min(strength, this.datas.length);
        if (strength < 0) {
            strength = this.datas.length;
        }
        this.get(renderPartialTicks);
        float sum = 0.0f;
        int count = 0;
        for (int i = 0, dataIndex = this.currentDataIndex; i < strength; ++i, --dataIndex) {
            if (dataIndex < 0) {
                dataIndex = this.datas.length - 1;
            }
            final SmartStatisticsDatas data = this.datas[dataIndex];
            if (data == null) {
                break;
            }
            if (!data.isReady()) {
                break;
            }
            sum += data.getCurrentVerticalSpeed();
            ++count;
        }
        return sum / count;
    }
    
    public float getCurrentSpeedFlattened(final float renderPartialTicks, int strength) {
        strength = Math.min(strength, this.datas.length);
        if (strength < 0) {
            strength = this.datas.length;
        }
        this.get(renderPartialTicks);
        float sum = 0.0f;
        int count = 0;
        for (int i = 0, dataIndex = this.currentDataIndex; i < strength; ++i, --dataIndex) {
            if (dataIndex < 0) {
                dataIndex = this.datas.length - 1;
            }
            final SmartStatisticsDatas data = this.datas[dataIndex];
            if (data == null) {
                break;
            }
            if (!data.isReady()) {
                break;
            }
            sum += data.getCurrentSpeed();
            ++count;
        }
        return sum / count;
    }
    
    static {
        dummy = new SmartStatisticsDatas();
    }
}
