// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class SmartMovingClient extends SmartMovingContext implements ISmartMovingClient
{
    private final Map<String, Float> maximumExhaustionValues;
    private boolean nativeUserInterfaceDrawing;
    
    public SmartMovingClient() {
        this.maximumExhaustionValues = new HashMap<String, Float>();
        this.nativeUserInterfaceDrawing = true;
    }
    
    @Override
    public float getMaximumExhaustion() {
        float maxExhaustion = SmartMovingClient.Config.getMaxExhaustion();
        if (this.maximumExhaustionValues.size() > 0) {
            final Iterator<Float> iterator = this.maximumExhaustionValues.values().iterator();
            while (iterator.hasNext()) {
                maxExhaustion = Math.max(iterator.next(), maxExhaustion);
            }
        }
        return maxExhaustion;
    }
    
    @Override
    public float getMaximumUpJumpCharge() {
        return SmartMovingClient.Config._jumpChargeMaximum.value;
    }
    
    @Override
    public float getMaximumHeadJumpCharge() {
        return SmartMovingClient.Config._headJumpChargeMaximum.value;
    }
    
    @Override
    public void setMaximumExhaustionValue(final String key, final float value) {
        this.maximumExhaustionValues.put(key, value);
    }
    
    @Override
    public float getMaximumExhaustionValue(final String key) {
        return this.maximumExhaustionValues.get(key);
    }
    
    @Override
    public boolean removeMaximumExhaustionValue(final String key) {
        return this.maximumExhaustionValues.remove(key) != null;
    }
    
    @Override
    public void setNativeUserInterfaceDrawing(final boolean value) {
        this.nativeUserInterfaceDrawing = value;
    }
    
    @Override
    public boolean getNativeUserInterfaceDrawing() {
        return this.nativeUserInterfaceDrawing;
    }
}
