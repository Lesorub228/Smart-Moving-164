// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public interface ISmartMovingClient
{
    float getMaximumExhaustion();
    
    float getMaximumUpJumpCharge();
    
    float getMaximumHeadJumpCharge();
    
    void setMaximumExhaustionValue(final String p0, final float p1);
    
    float getMaximumExhaustionValue(final String p0);
    
    boolean removeMaximumExhaustionValue(final String p0);
    
    void setNativeUserInterfaceDrawing(final boolean p0);
    
    boolean getNativeUserInterfaceDrawing();
}
