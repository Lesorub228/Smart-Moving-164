// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.utilities;

import java.util.Iterator;
import java.util.List;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class ResourcePack implements bjr
{
    private final String name;
    private final String path;
    private final Class source;
    
    private ResourcePack(final String name, final String path, final Class source) {
        this.name = name;
        this.path = path;
        this.source = source;
    }
    
    public BufferedImage a() throws IOException {
        return null;
    }
    
    public Set c() {
        final Set result = new HashSet();
        result.add(this.name);
        return result;
    }
    
    public boolean b(final bjo var1) {
        try {
            return this.a(var1) != null;
        }
        catch (final IOException ioe) {
            return false;
        }
    }
    
    public InputStream a(final bjo var1) throws IOException {
        return this.getClass().getResourceAsStream("/" + this.path + "/" + var1.a());
    }
    
    public String b() {
        return this.name;
    }
    
    public bkg a(final bki var1, final String var2) throws IOException {
        return null;
    }
    
    public static void remove(final String name) {
        final List resourcePacks = getResourcePacks();
        final Iterator iterator = resourcePacks.iterator();
        while (iterator.hasNext()) {
            final Object resourcePack = iterator.next();
            if (resourcePack instanceof bjr && name.equals(((bjr)resourcePack).b())) {
                iterator.remove();
            }
        }
    }
    
    public static void add(final String name, final String path, final Class source) {
        getResourcePacks().add(new ResourcePack(name, path, source));
    }
    
    private static List getResourcePacks() {
        return (List)Reflect.GetField(atv.class, atv.w(), Install.Minecraft_resourcePacks);
    }
}
