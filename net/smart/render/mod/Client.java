// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.mod;

import net.smart.render.statistics.SmartStatisticsContext;
import net.smart.utilities.ResourcePack;
import net.smart.render.statistics.playerapi.SmartStatisticsFactory;
import java.util.Map;
import net.smart.render.SmartRenderContext;
import java.lang.reflect.Method;
import net.smart.utilities.Reflect;
import net.smart.utilities.Name;
import net.smart.render.statistics.playerapi.SmartStatistics;
import net.smart.utilities.Install;
import java.util.logging.Logger;
import net.smart.utilities.Assert;

public class Client extends Server
{
    private static boolean addRenderer;
    private boolean hasRenderer;
    
    public static Client create(final mod_SmartRender mod) {
        final Logger logger = ModLoader.getLogger();
        Assert.client(logger);
        Assert.clientPlayerAPI(logger);
        return new Client(mod);
    }
    
    public Client(final mod_SmartRender mod) {
        super(mod);
        this.hasRenderer = false;
        this.hasRenderer = Install.hasRenderPlayerAPI;
        SmartStatistics.register();
        if (this.hasRenderer) {
            final Class<?> type = Reflect.LoadClass(BaseMod.class, new Name("net.smart.render.playerapi.SmartRender"), true);
            final Method method = Reflect.GetMethod(type, new Name("register"), (Class<?>[])new Class[0]);
            Reflect.Invoke(method, null, new Object[0]);
        }
    }
    
    public static void doNotAddRenderer() {
        Client.addRenderer = false;
    }
    
    @Override
    public void load() {
        if (!this.hasRenderer && Client.addRenderer) {
            this.hasRenderer = SmartRenderContext.registerAnimation(null);
        }
        ModLoader.setInGameHook((BaseMod)this.mod, true, true);
        SmartStatisticsFactory.initialize();
        ResourcePack.remove(this.mod.getClass().getSimpleName());
    }
    
    @Override
    public void addRenderer(final Map map) {
        if (!this.hasRenderer && Client.addRenderer) {
            this.hasRenderer = SmartRenderContext.registerAnimation(map);
        }
    }
    
    @Override
    public void registerAnimation(final atv minecraft) {
        if (!this.hasRenderer && Client.addRenderer) {
            this.hasRenderer = SmartRenderContext.registerAnimation(null);
        }
    }
    
    @Override
    public boolean onTickInGame(final float f, final atv minecraft) {
        SmartStatisticsContext.onTickInGame();
        return true;
    }
    
    @Override
    public String toString() {
        return "Smart Render 1.1";
    }
    
    static {
        Client.addRenderer = true;
    }
}
