// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public interface IPacketReceiver
{
    boolean processStatePacket(final ea p0, final IEntityPlayerMP p1, final int p2, final long p3);
    
    boolean processConfigInfoPacket(final ea p0, final IEntityPlayerMP p1, final String p2);
    
    boolean processConfigContentPacket(final ea p0, final IEntityPlayerMP p1, final String[] p2, final String p3);
    
    boolean processConfigChangePacket(final ea p0, final IEntityPlayerMP p1);
    
    boolean processSpeedChangePacket(final ea p0, final IEntityPlayerMP p1, final int p2, final String p3);
    
    boolean processHungerChangePacket(final ea p0, final IEntityPlayerMP p1, final float p2);
    
    boolean processSoundPacket(final ea p0, final IEntityPlayerMP p1, final String p2, final float p3, final float p4);
}
