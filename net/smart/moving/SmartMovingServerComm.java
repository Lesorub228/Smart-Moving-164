// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public class SmartMovingServerComm implements IPacketReceiver
{
    public static ILocalUserNameProvider localUserNameProvider;
    public static final SmartMovingServerComm instance;
    
    @Override
    public boolean processStatePacket(final ea packet, final IEntityPlayerMP player, final int entityId, final long state) {
        player.getMoving().processStatePacket(packet, state);
        return true;
    }
    
    @Override
    public boolean processConfigInfoPacket(final ea packet, final IEntityPlayerMP player, final String info) {
        player.getMoving().processConfigPacket(info);
        return true;
    }
    
    @Override
    public boolean processConfigContentPacket(final ea packet, final IEntityPlayerMP player, final String[] content, final String username) {
        return false;
    }
    
    @Override
    public boolean processConfigChangePacket(final ea packet, final IEntityPlayerMP player) {
        player.getMoving().processConfigChangePacket((SmartMovingServerComm.localUserNameProvider != null) ? SmartMovingServerComm.localUserNameProvider.getLocalConfigUserName() : null);
        return true;
    }
    
    @Override
    public boolean processSpeedChangePacket(final ea packet, final IEntityPlayerMP player, final int difference, final String username) {
        player.getMoving().processSpeedChangePacket(difference, (SmartMovingServerComm.localUserNameProvider != null) ? SmartMovingServerComm.localUserNameProvider.getLocalSpeedUserName() : null);
        return true;
    }
    
    @Override
    public boolean processHungerChangePacket(final ea packet, final IEntityPlayerMP player, final float hunger) {
        player.getMoving().processHungerChangePacket(hunger);
        return true;
    }
    
    @Override
    public boolean processSoundPacket(final ea packet, final IEntityPlayerMP player, final String soundId, final float volume, final float pitch) {
        player.getMoving().processSoundPacket(soundId, volume, pitch);
        return true;
    }
    
    static {
        SmartMovingServerComm.localUserNameProvider = null;
        instance = new SmartMovingServerComm();
    }
}
