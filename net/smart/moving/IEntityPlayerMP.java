// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.util.List;
import java.util.logging.Logger;

public interface IEntityPlayerMP extends IPacketSender
{
    void sendPacketToTrackedPlayers(final ea p0);
    
    String getUsername();
    
    void resetFallDistance();
    
    void resetTicksForFloatKick();
    
    Logger getLogger();
    
    void setHeight(final float p0);
    
    double getMinY();
    
    float getHeight();
    
    void setMaxY(final double p0);
    
    boolean localIsEntityInsideOpaqueBlock();
    
    SmartMovingServer getMoving();
    
    IEntityPlayerMP[] getAllPlayers();
    
    float doGetHealth();
    
    asx getBox();
    
    asx expandBox(final asx p0, final double p1, final double p2, final double p3);
    
    List getEntitiesExcludingPlayer(final asx p0);
    
    boolean isDeadEntity(final nn p0);
    
    void onCollideWithPlayer(final nn p0);
    
    void localAddExhaustion(final float p0);
    
    void localAddMovementStat(final double p0, final double p1, final double p2);
    
    void localPlaySound(final String p0, final float p1, final float p2);
}
