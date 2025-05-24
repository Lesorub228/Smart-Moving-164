// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.mod;

public class None extends Mod
{
    public static None create(final mod_SmartMoving mod) {
        return new None(mod);
    }
    
    protected None(final mod_SmartMoving mod) {
        super(mod);
    }
    
    @Override
    public String toString() {
        return super.toString() + " (disabled)";
    }
}
