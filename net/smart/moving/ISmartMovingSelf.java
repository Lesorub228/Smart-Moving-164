// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public interface ISmartMovingSelf
{
    float getExhaustion();
    
    float getUpJumpCharge();
    
    float getHeadJumpCharge();
    
    void addExhaustion(final float p0);
    
    void setMaxExhaustionForAction(final float p0);
    
    void setMaxExhaustionToStartAction(final float p0);
}
