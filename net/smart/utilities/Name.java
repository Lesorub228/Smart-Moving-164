// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.utilities;

public class Name
{
    public final String obfuscated;
    public final String forgefuscated;
    public final String deobfuscated;
    
    public Name(final String name) {
        this(name, null);
    }
    
    public Name(final String deobfuscatedName, final String obfuscatedName) {
        this(deobfuscatedName, null, obfuscatedName);
    }
    
    public Name(final String deobfuscatedName, final String forgefuscatedName, final String obfuscatedName) {
        this.deobfuscated = deobfuscatedName;
        this.forgefuscated = forgefuscatedName;
        this.obfuscated = obfuscatedName;
    }
}
