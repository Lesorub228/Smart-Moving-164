// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.mod;

public class Server extends Mod
{
    public static Server create(final mod_SmartRender mod) {
        return new Server(mod);
    }
    
    public Server(final mod_SmartRender mod) {
        super(mod);
    }
    
    @Override
    public String toString() {
        return "Smart Render 1.1 (disabled)";
    }
}
