// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.mod;

import net.smart.moving.IEntityPlayerMP;
import net.smart.moving.IPacketReceiver;
import net.smart.moving.SmartMovingServerComm;
import net.smart.moving.playerapi.SmartMovingServerPlayerBase;
import net.smart.moving.SmartMovingServer;
import net.smart.utilities.Install;
import net.minecraft.server.MinecraftServer;
import java.io.File;
import net.smart.moving.SmartMovingPacketStream;
import java.util.logging.Logger;
import net.smart.utilities.Assert;

public class Server extends Mod
{
    public static Server create(final mod_SmartMoving mod) {
        final Logger logger = ModLoader.getLogger();
        Assert.server(logger);
        Assert.serverPlayerAPI(logger);
        return new Server(mod);
    }
    
    protected Server(final mod_SmartMoving mod) {
        super(mod);
    }
    
    @Override
    public void load() {
        ModLoader.registerPacketChannel((BaseMod)this.mod, SmartMovingPacketStream.Id);
        SmartMovingServer.initialize(new File("."), Install.getLogger(MinecraftServer.F().an()), ModLoader.getMinecraftServerInstance().h().a(), false);
    }
    
    @Override
    public void modsLoaded() {
        SmartMovingServerPlayerBase.registerPlayerBase();
    }
    
    @Override
    public void serverCustomPayload(final ka netserverhandler, final ea packet) {
        SmartMovingPacketStream.receivePacket(packet, SmartMovingServerComm.instance, SmartMovingServerPlayerBase.getPlayerBase(netserverhandler.c));
    }
}
