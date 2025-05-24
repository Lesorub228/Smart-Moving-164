// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.mod;

public class None extends Mod
{
    public static None create(final mod_SmartRender mod) {
        return new None(mod);
    }
    
    public None(final mod_SmartRender mod) {
        super(mod);
    }
    
    @Override
    public String toString() {
        return "Smart Render 1.1 (disabled)";
    }
}
