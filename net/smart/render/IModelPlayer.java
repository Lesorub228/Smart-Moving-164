// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

public interface IModelPlayer
{
    SmartRenderModel getRenderModel();
    
    void initialize(final bcu p0, final bcu p1, final bcu p2, final bcu p3, final bcu p4, final bcu p5, final bcu p6, final bcu p7, final bcu p8);
    
    void superRender(final nn p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6);
    
    void superSetRotationAngles(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5, final nn p6);
    
    void superRenderCloak(final float p0);
    
    bcu getOuter();
    
    bcu getTorso();
    
    bcu getBody();
    
    bcu getBreast();
    
    bcu getNeck();
    
    bcu getHead();
    
    bcu getHeadwear();
    
    bcu getRightShoulder();
    
    bcu getRightArm();
    
    bcu getLeftShoulder();
    
    bcu getLeftArm();
    
    bcu getPelvic();
    
    bcu getRightLeg();
    
    bcu getLeftLeg();
    
    bcu getEars();
    
    bcu getCloak();
    
    void animateHeadRotation(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateSleeping(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateArmSwinging(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateRiding(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateLeftArmItemHolding(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateRightArmItemHolding(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateWorkingBody(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateWorkingArms(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateSneaking(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateArms(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
    
    void animateBowAiming(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5);
}
