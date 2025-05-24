// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.playerapi;

import net.smart.utilities.Install;
import net.minecraft.server.MinecraftServer;
import java.util.logging.Logger;
import net.smart.utilities.Reflect;
import net.smart.moving.SmartMovingPacketStream;
import java.util.List;
import api.player.server.ServerPlayerAPI;
import java.lang.reflect.Field;
import net.smart.moving.SmartMovingServer;
import net.smart.moving.IEntityPlayerMP;
import api.player.server.ServerPlayerBase;

public class SmartMovingServerPlayerBase extends ServerPlayerBase implements IEntityPlayerMP
{
    private boolean _hasReplacedNetServerHandler;
    public final SmartMovingServer moving;
    private static final Field _ticksForFloatKick;
    
    public static void registerPlayerBase() {
        ServerPlayerAPI.register("Smart Moving", (Class)SmartMovingServerPlayerBase.class);
    }
    
    public static SmartMovingServerPlayerBase getPlayerBase(final Object player) {
        return (SmartMovingServerPlayerBase)((jv)player).getServerPlayerBase("Smart Moving");
    }
    
    public SmartMovingServerPlayerBase(final ServerPlayerAPI playerApi) {
        super(playerApi);
        this.moving = new SmartMovingServer(this, false);
    }
    
    public float getHeight() {
        return this.player.P;
    }
    
    public double getMinY() {
        return this.player.E.b;
    }
    
    public void setMaxY(final double maxY) {
        this.player.E.e = maxY;
    }
    
    public void afterSetPosition(final double d, final double d1, final double d2) {
        this.moving.afterSetPosition(d, d1, d2);
    }
    
    public void beforeIsPlayerSleeping() {
        this.moving.beforeIsPlayerSleeping();
    }
    
    public void beforeOnUpdate() {
        if (!this._hasReplacedNetServerHandler) {
            this._hasReplacedNetServerHandler = NetServerHandler.replace(this.player);
        }
        this.moving.beforeOnUpdate();
    }
    
    public void afterOnUpdate() {
        this.moving.afterOnUpdate();
    }
    
    public void beforeOnLivingUpdate() {
        this.moving.beforeOnLivingUpdate();
    }
    
    public void afterOnLivingUpdate() {
        this.moving.afterOnLivingUpdate();
    }
    
    public float doGetHealth() {
        return this.player.aN();
    }
    
    public asx getBox() {
        return this.player.E;
    }
    
    public asx expandBox(final asx box, final double x, final double y, final double z) {
        return box.b(x, y, z);
    }
    
    public List getEntitiesExcludingPlayer(final asx box) {
        return this.player.q.b((nn)this.player, box);
    }
    
    public boolean isDeadEntity(final nn entity) {
        return entity.M;
    }
    
    public void onCollideWithPlayer(final nn entity) {
        entity.b_((uf)this.player);
    }
    
    public float getEyeHeight() {
        return this.player.P - 0.18f;
    }
    
    public boolean isEntityInsideOpaqueBlock() {
        return this.moving.isEntityInsideOpaqueBlock();
    }
    
    public boolean localIsEntityInsideOpaqueBlock() {
        return super.isEntityInsideOpaqueBlock();
    }
    
    public void addExhaustion(final float exhaustion) {
        this.moving.addExhaustion(exhaustion);
    }
    
    public void localAddExhaustion(final float exhaustion) {
        super.addExhaustion(exhaustion);
    }
    
    public void addMovementStat(final double x, final double y, final double z) {
        this.moving.addMovementStat(x, y, z);
    }
    
    public void localAddMovementStat(final double x, final double y, final double z) {
        super.addMovementStat(x, y, z);
    }
    
    public void localPlaySound(final String soundId, final float volume, final float pitch) {
        this.player.a(soundId, volume, pitch);
    }
    
    public void beforeUpdatePotionEffects() {
        this.moving.afterAddMovingHungerBatch();
    }
    
    public void afterUpdatePotionEffects() {
        this.moving.beforeAddMovingHungerBatch();
    }
    
    public void setHeight(final float height) {
        this.player.P = height;
    }
    
    public void sendPacket(final byte[] data) {
        final ea packet = new ea();
        packet.a = SmartMovingPacketStream.Id;
        packet.c = data;
        packet.b = data.length;
        this.sendPacket(packet);
    }
    
    public void sendPacket(final ea packet) {
        this.player.a.b((ey)packet);
    }
    
    public String getUsername() {
        return this.player.an();
    }
    
    public void resetFallDistance() {
        this.player.T = 0.0f;
        this.player.y = 0.08;
    }
    
    public void resetTicksForFloatKick() {
        Reflect.SetField(SmartMovingServerPlayerBase._ticksForFloatKick, this.player.a, 0);
    }
    
    public void sendPacketToTrackedPlayers(final ea packet) {
        this.player.b.a(this.player.ar).q().a((nn)this.player, (ey)packet);
    }
    
    public Logger getLogger() {
        return Install.getLogger(MinecraftServer.F().an());
    }
    
    public SmartMovingServer getMoving() {
        return this.moving;
    }
    
    public IEntityPlayerMP[] getAllPlayers() {
        final List playerEntityList = this.player.b.af().a;
        final IEntityPlayerMP[] result = new IEntityPlayerMP[playerEntityList.size()];
        for (int i = 0; i < playerEntityList.size(); ++i) {
            result[i] = (IEntityPlayerMP)playerEntityList.get(i).getServerPlayerBase("Smart Moving");
        }
        return result;
    }
    
    static {
        _ticksForFloatKick = Reflect.GetField(ka.class, Install.NetServerHandler_ticksForFloatKick);
    }
}
