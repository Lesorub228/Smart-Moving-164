// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import net.smart.utilities.Reflect;
import net.smart.utilities.Install;
import java.util.Map;
import net.smart.utilities.Utilities;

public abstract class SmartRenderContext extends Utilities
{
    public static boolean registerAnimation(final Map map) {
        return registerAnimation(map, RenderPlayer.class);
    }
    
    public static boolean registerAnimation(Map map, final Class<?> type) {
        if (map == null && bgl.a != null) {
            map = (Map)Reflect.GetField(bgl.class, bgl.a, Install.RenderManager_entityRenderMap);
        }
        if (map == null) {
            return false;
        }
        bgm render;
        try {
            render = (bgm)type.newInstance();
        }
        catch (final Exception e) {
            return false;
        }
        map.put(bex.class, render);
        map.put(bey.class, render);
        render.a(bgl.a);
        return true;
    }
}
