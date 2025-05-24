import java.util.Map;
import net.smart.render.mod.Server;
import net.smart.render.mod.Client;
import net.smart.utilities.Install;
import net.smart.render.mod.None;
import net.smart.utilities.Assert;
import net.smart.render.mod.Mod;

// 
// Decompiled by Procyon v0.6.0
// 

public class mod_SmartRender extends BaseMod
{
    private final Mod mod;
    
    public mod_SmartRender() {
        if (!Assert.singleton(mod_SmartRender.class, "Smart Render", ModLoader.getLogger())) {
            this.mod = None.create(this);
        }
        else if (Install.hasClient) {
            this.mod = Client.create(this);
        }
        else {
            this.mod = Server.create(this);
        }
    }
    
    public static void doNotAddRenderer() {
        Client.doNotAddRenderer();
    }
    
    public void load() {
        this.mod.load();
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
    
    public String getName() {
        return this.mod.getName();
    }
    
    public String getVersion() {
        return this.mod.getVersion();
    }
    
    public String toString() {
        return this.mod.toString();
    }
}
