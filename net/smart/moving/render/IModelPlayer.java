// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render;

public interface IModelPlayer
{
    SmartMovingModel getMovingModel();
    
    void superAnimateHeadRotation(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateSleeping(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateArmSwinging(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateRiding(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateLeftArmItemHolding(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateRightArmItemHolding(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateWorkingBody(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateWorkingArms(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateSneaking(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superApplyAnimationOffsets(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void superAnimateBowAiming(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
}
