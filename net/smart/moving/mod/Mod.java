// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.mod;

import net.smart.moving.SmartMovingContext;
import net.smart.moving.SmartMovingClient;
import net.smart.moving.SmartMoving;
import java.util.Map;

public abstract class Mod
{
    protected final mod_SmartMoving mod;
    
    protected Mod(final mod_SmartMoving mod) {
        this.mod = mod;
    }
    
    public void load() {
    }
    
    public void modsLoaded() {
    }
    
    public void addRenderer(final Map map) {
    }
    
    public void registerAnimation(final atv minecraft) {
    }
    
    public boolean onTickInGame(final float f, final atv minecraft) {
        return false;
    }
    
    public boolean onTickInGUI(final float f, final atv minecraft, final awe guiscreen) {
        return false;
    }
    
    public void clientCustomPayload(final bcw netclienthandler, final ea packet) {
    }
    
    public void serverCustomPayload(final ka netserverhandler, final ea packet) {
    }
    
    public void receiveCustomPacket(final ea packet) {
    }
    
    public void onPacket250Received(final uf source, final ea payload) {
    }
    
    public String getName() {
        return "Smart Moving";
    }
    
    public String getVersion() {
        return "14.5";
    }
    
    @Override
    public String toString() {
        return "Smart Moving 14.5";
    }
    
    public SmartMoving getInstance(final uf entityPlayer) {
        return null;
    }
    
    public SmartMovingClient getClient() {
        return SmartMovingContext.Client;
    }
}
