// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public interface IEntityPlayerSP
{
    SmartMoving getMoving();
    
    boolean getSleepingField();
    
    boolean getIsJumpingField();
    
    boolean getIsInWebField();
    
    void setIsInWebField(final boolean p0);
    
    atv getMcField();
    
    void setMoveForwardField(final float p0);
    
    void setMoveStrafingField(final float p0);
    
    void setIsJumpingField(final boolean p0);
    
    void localMoveEntity(final double p0, final double p1, final double p2);
    
    ug localSleepInBedAt(final int p0, final int p1, final int p2);
    
    float localGetBrightness(final float p0);
    
    int localGetBrightnessForRender(final float p0);
    
    void localUpdateEntityActionState();
    
    boolean localIsInsideOfMaterial(final akc p0);
    
    void localWriteEntityToNBT(final by p0);
    
    boolean localIsSneaking();
    
    float localGetFOVMultiplier();
}
