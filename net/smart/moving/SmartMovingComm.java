// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import net.smart.properties.Property;
import net.minecraft.server.MinecraftServer;

public class SmartMovingComm extends SmartMovingContext implements IPacketReceiver, IPacketSender
{
    public static final SmartMovingComm instance;
    
    @Override
    public boolean processStatePacket(final ea packet, final IEntityPlayerMP player, final int entityId, final long state) {
        final nn entity = atv.w().f.a(entityId);
        if (entity == null) {
            return true;
        }
        final SmartMovingOther moving = SmartMovingFactory.getOtherSmartMoving((bey)entity);
        if (moving != null) {
            moving.processStatePacket(state);
        }
        return true;
    }
    
    @Override
    public boolean processConfigInfoPacket(final ea packet, final IEntityPlayerMP player, final String info) {
        return false;
    }
    
    @Override
    public boolean processConfigContentPacket(final ea packet, final IEntityPlayerMP player, final String[] content, final String username) {
        processConfigPacket(content, username, false);
        return true;
    }
    
    @Override
    public boolean processConfigChangePacket(final ea packet, final IEntityPlayerMP player) {
        SmartMovingComm.Options.writeNoRightsToChangeConfigMessageToChat(isConnectedToRemoteServer());
        return true;
    }
    
    @Override
    public boolean processSpeedChangePacket(final ea packet, final IEntityPlayerMP player, final int difference, final String username) {
        if (difference == 0) {
            SmartMovingComm.Options.writeNoRightsToChangeSpeedMessageToChat(isConnectedToRemoteServer());
        }
        else {
            SmartMovingComm.Config.changeSpeed(difference);
            SmartMovingComm.Options.writeServerSpeedMessageToChat(username, SmartMovingComm.Config._globalConfig.value);
        }
        return true;
    }
    
    @Override
    public boolean processHungerChangePacket(final ea packet, final IEntityPlayerMP player, final float hunger) {
        return false;
    }
    
    @Override
    public boolean processSoundPacket(final ea packet, final IEntityPlayerMP player, final String soundId, final float distance, final float pitch) {
        return false;
    }
    
    private static boolean isConnectedToRemoteServer() {
        return MinecraftServer.F() == null || atv.w().C() == null || !atv.w().C().K();
    }
    
    public static void processConfigPacket(String[] content, final String username, final boolean blockCode) {
        boolean isGloballyConfigured = false;
        if (content != null && content.length == 2 && SmartMovingComm.Options._globalConfig.getCurrentKey().equals(content[0])) {
            isGloballyConfigured = "true".equals(content[1]);
            content = null;
        }
        final boolean wasEnabled = SmartMovingComm.Config.enabled;
        final boolean first = SmartMovingComm.Config != SmartMovingComm.ServerConfig;
        if (first) {
            SmartMovingComm.ServerConfig.reset();
        }
        if (content != null) {
            if (content.length == 0) {
                SmartMovingComm.Config = SmartMovingComm.Options;
                SmartMovingComm.Options.writeServerDeconfigMessageToChat();
                return;
            }
            SmartMovingComm.ServerConfig.loadFromProperties(content, blockCode);
            isGloballyConfigured = SmartMovingComm.ServerConfig._globalConfig.value;
        }
        else {
            SmartMovingComm.ServerConfig.load(false);
            SmartMovingComm.ServerConfig.setCurrentKey(null);
        }
        SmartMovingComm.ServerConfig._globalConfig.value = isGloballyConfigured;
        if (!first) {
            SmartMovingComm.Options.writeServerReconfigMessageToChat(wasEnabled, username, isGloballyConfigured);
            return;
        }
        SmartMovingComm.Config = SmartMovingComm.ServerConfig;
        SmartMovingComm.Options.writeServerConfigMessageToChat();
        if (!blockCode) {
            SmartMovingPacketStream.sendConfigInfo(SmartMovingComm.instance, "3.2");
        }
    }
    
    @Override
    public void sendPacket(final byte[] data) {
        final ea packet = new ea();
        packet.a = SmartMovingPacketStream.Id;
        packet.c = data;
        packet.b = data.length;
        atv.w().q().c((ey)packet);
    }
    
    public static boolean processBlockCode(final String text) {
        if (!text.startsWith("§0§1") || !text.endsWith("§f§f")) {
            return false;
        }
        final String codes = text.substring(4, text.length() - 4);
        processBlockCode(codes, "§0", SmartMovingComm.Options._baseClimb, "standard");
        processBlockCode(codes, "§1", SmartMovingComm.Options._freeClimb, new String[0]);
        processBlockCode(codes, "§2", SmartMovingComm.Options._ceilingClimbing, new String[0]);
        processBlockCode(codes, "§3", SmartMovingComm.Options._swim, new String[0]);
        processBlockCode(codes, "§4", SmartMovingComm.Options._dive, new String[0]);
        processBlockCode(codes, "§5", SmartMovingComm.Options._crawl, new String[0]);
        processBlockCode(codes, "§6", SmartMovingComm.Options._slide, new String[0]);
        processBlockCode(codes, "§7", SmartMovingComm.Options._fly, new String[0]);
        processBlockCode(codes, "§8", SmartMovingComm.Options._jumpCharge, new String[0]);
        processBlockCode(codes, "§9", SmartMovingComm.Options._headJump, new String[0]);
        processBlockCode(codes, "§a", SmartMovingComm.Options._angleJumpSide, new String[0]);
        processBlockCode(codes, "§b", SmartMovingComm.Options._angleJumpBack, new String[0]);
        return true;
    }
    
    private static void processBlockCode(final String text, final String blockCode, final Property<?> property, final String... value) {
        if (text.contains(blockCode)) {
            processConfigPacket(new String[] { property.getCurrentKey(), (value.length > 0) ? value[0] : "false" }, null, true);
        }
    }
    
    static {
        instance = new SmartMovingComm();
    }
}
