// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.mod;

import java.util.Map;

public class Mod
{
    protected final mod_SmartRender mod;
    
    protected Mod(final mod_SmartRender mod) {
        this.mod = mod;
    }
    
    public void load() {
    }
    
    public void addRenderer(final Map map) {
    }
    
    public void registerAnimation(final atv minecraft) {
    }
    
    public boolean onTickInGame(final float f, final atv minecraft) {
        return false;
    }
    
    public String getName() {
        return "Smart Render";
    }
    
    public String getVersion() {
        return "1.1";
    }
    
    @Override
    public String toString() {
        return "Smart Render 1.1";
    }
}
