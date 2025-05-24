import net.smart.moving.SmartMovingClient;
import net.smart.moving.SmartMoving;
import java.util.Map;
import net.smart.moving.mod.Server;
import net.smart.moving.mod.Client;
import net.smart.utilities.Install;
import net.smart.moving.mod.None;
import net.smart.utilities.Assert;
import net.smart.moving.mod.Mod;

// 
// Decompiled by Procyon v0.6.0
// 

public class mod_SmartMoving extends BaseMod
{
    private final Mod mod;
    
    public mod_SmartMoving() {
        if (!Assert.singleton(mod_SmartMoving.class, "Smart Moving", ModLoader.getLogger())) {
            this.mod = None.create(this);
        }
        else if (Install.hasClient) {
            this.mod = Client.create(this);
        }
        else {
            this.mod = Server.create(this);
        }
    }
    
    public void load() {
        this.mod.load();
    }
    
    public void modsLoaded() {
        this.mod.modsLoaded();
    }
    
    public void addRenderer(final Map map) {
        this.mod.addRenderer(map);
    }
    
    public void registerAnimation(final atv minecraft) {
        this.mod.registerAnimation(minecraft);
    }
    
    public boolean onTickInGame(final float f, final atv minecraft) {
        return this.mod.onTickInGame(f, minecraft);
    }
    
    public boolean onTickInGUI(final float f, final atv minecraft, final awe guiscreen) {
        return this.mod.onTickInGUI(f, minecraft, guiscreen);
    }
    
    public String getName() {
        return this.mod.getName();
    }
    
    public String getVersion() {
        return this.mod.getVersion();
    }
    
    public void clientCustomPayload(final bcw netclienthandler, final ea packet) {
        this.mod.clientCustomPayload(netclienthandler, packet);
    }
    
    public void serverCustomPayload(final ka netserverhandler, final ea packet) {
        this.mod.serverCustomPayload(netserverhandler, packet);
    }
    
    public void receiveCustomPacket(final ea packet) {
        this.mod.receiveCustomPacket(packet);
    }
    
    public void onPacket250Received(final uf source, final ea payload) {
        this.mod.onPacket250Received(source, payload);
    }
    
    public String toString() {
        return this.mod.toString();
    }
    
    public SmartMoving getInstance(final uf entityPlayer) {
        return this.mod.getInstance(entityPlayer);
    }
    
    public SmartMovingClient getClient() {
        return this.mod.getClient();
    }
}
