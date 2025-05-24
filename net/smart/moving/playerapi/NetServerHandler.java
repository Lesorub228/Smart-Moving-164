// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.playerapi;

import net.smart.utilities.Install;
import java.util.List;
import net.smart.utilities.Reflect;
import net.minecraft.server.MinecraftServer;
import java.lang.reflect.Field;

public class NetServerHandler extends ka
{
    private static final Field _minecraftServer;
    private static final Field _playerList;
    
    public NetServerHandler(final MinecraftServer minecraftserver, final cm networkmanager, final jv entityplayer) {
        super(minecraftserver, networkmanager, entityplayer);
    }
    
    public void a(final eu par1Packet10Flying) {
        final SmartMovingServerPlayerBase playerBase = SmartMovingServerPlayerBase.getPlayerBase(this.c);
        playerBase.moving.beforeAddMovingHungerBatch();
        super.a(par1Packet10Flying);
        playerBase.moving.afterAddMovingHungerBatch();
    }
    
    public void a(final gk packet15place) {
        if (packet15place.h() == 255) {
            final ye itemstack = this.c.bn.h();
            if (itemstack != null) {
                final float offset = 1.62f - this.c.f();
                final jv c = this.c;
                c.N += offset;
                super.a(packet15place);
                final jv c2 = this.c;
                c2.N -= offset;
            }
        }
        else {
            super.a(packet15place);
        }
    }
    
    public static boolean replace(final jv player) {
        final ka original = player.a;
        final MinecraftServer server = (MinecraftServer)Reflect.GetField(NetServerHandler._minecraftServer, original);
        final List playerList = (List)Reflect.GetField(NetServerHandler._playerList, server.ag());
        for (int i = 0; i < playerList.size(); ++i) {
            final Object element = playerList.get(i);
            if (element instanceof ka && ((ka)element).c == player) {
                final NetServerHandler replacement = new NetServerHandler(server, original.a, player);
                Reflect.copyFields(ka.class, original, replacement);
                Reflect.copyFields(ez.class, original, replacement);
                playerList.set(i, replacement);
                return true;
            }
        }
        return false;
    }
    
    static {
        _minecraftServer = Reflect.GetField(ka.class, Install.NetServerHandler_minecraftServer);
        _playerList = Reflect.GetField(kd.class, Install.NetworkListenThread_playerList);
    }
}
