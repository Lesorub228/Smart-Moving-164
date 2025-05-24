// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import net.smart.moving.render.RenderPlayer;
import net.smart.moving.config.SmartMovingConfig;
import net.smart.utilities.ResourcePack;
import java.util.Map;
import net.smart.render.statistics.SmartStatisticsContext;
import net.smart.utilities.Install;
import net.smart.utilities.Assert;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;
import java.util.Set;
import net.smart.moving.config.SmartMovingClientConfig;
import net.smart.moving.config.SmartMovingServerConfig;
import net.smart.moving.config.SmartMovingOptions;
import net.smart.render.SmartRenderContext;

public abstract class SmartMovingContext extends SmartRenderContext
{
    public static final float ClimbPullMotion = 0.3f;
    public static final double FastUpMotion = 0.2;
    public static final double MediumUpMotion = 0.14;
    public static final double SlowUpMotion = 0.1;
    public static final double HoldMotion = 0.08;
    public static final double SinkDownMotion = 0.05;
    public static final double ClimbDownMotion = 0.01;
    public static final double CatchCrawlGapMotion = 0.17;
    public static final float SwimCrawlWaterMaxBorder = 1.0f;
    public static final float SwimCrawlWaterTopBorder = 0.65f;
    public static final float SwimCrawlWaterMediumBorder = 0.6f;
    public static final float SwimCrawlWaterBottomBorder = 0.55f;
    public static final float HorizontalGroundDamping = 0.546f;
    public static final float HorizontalAirDamping = 0.91f;
    public static final float HorizontalAirodynamicDamping = 0.999f;
    public static final float SwimSoundDistance = 1.4285715f;
    public static final float SlideToHeadJumpingFallDistance = 0.05f;
    public static final SmartMovingClient Client;
    public static final SmartMovingOptions Options;
    public static final SmartMovingServerConfig ServerConfig;
    public static SmartMovingClientConfig Config;
    private static boolean wasInitialized;
    private static Set<String> translateKeys;
    private static MinecraftServer lastMinecraftServer;
    
    public static void onTickInGame() {
        final atv minecraft = atv.w();
        if (minecraft.f != null && minecraft.f.I) {
            SmartMovingFactory.handleMultiPlayerTick(minecraft);
        }
        SmartMovingContext.Options.initializeForGameIfNeccessary();
        initializeServerIfNecessary();
    }
    
    public static void initialize(final aul gameSettings, final boolean standalone, final Logger logger) {
        if (!SmartMovingContext.wasInitialized) {
            if (standalone) {
                Assert.client(logger);
                if (Install.hasMinecraftForge) {
                    Assert.warn(logger, getModLoaderMessage("Minecraft Forge"));
                }
                else if (Install.hasModLoader) {
                    Assert.warn(logger, getModLoaderMessage("ModLoader"));
                }
            }
            SmartStatisticsContext.setCalculateHorizontalStats(true);
        }
        final ats[] previous = gameSettings.W;
        final int length = previous.length;
        final SmartMovingOptions options = SmartMovingContext.Options;
        final ats[] w = new ats[length + (SmartMovingOptions.hasMoreKeyBindingGui ? 5 : 2)];
        gameSettings.W = w;
        final ats[] current = w;
        for (int i = 0, n = 0; i < previous.length; ++i, ++n) {
            final ats binding = previous[i];
            if (binding == gameSettings.Q) {
                current[n++] = gameSettings.Q;
                current[n++] = SmartMovingContext.Options.keyBindGrab;
                current[n++] = SmartMovingContext.Options.keyBindSprint;
                final SmartMovingOptions options2 = SmartMovingContext.Options;
                if (SmartMovingOptions.hasMoreKeyBindingGui) {
                    current[n++] = SmartMovingContext.Options.keyBindConfigToggle;
                    current[n++] = SmartMovingContext.Options.keyBindSpeedIncrease;
                    current[n++] = SmartMovingContext.Options.keyBindSpeedDecrease;
                }
                else {
                    releaseKeyBinding(SmartMovingContext.Options.keyBindConfigToggle);
                    releaseKeyBinding(SmartMovingContext.Options.keyBindSpeedIncrease);
                    releaseKeyBinding(SmartMovingContext.Options.keyBindSpeedDecrease);
                }
                --n;
            }
            else {
                current[n] = binding;
            }
        }
        if (SmartMovingContext.wasInitialized) {
            return;
        }
        if (standalone) {
            registerAnimation(null);
        }
        SmartMovingContext.wasInitialized = true;
        ResourcePack.add("SmartMoving", "net/smart/resources", SmartMovingContext.class);
        System.out.println("Smart Moving uses communication protocol 2.3");
        if (logger != null) {
            logger.fine("Smart Moving uses communication protocol 2.3");
        }
    }
    
    private static void releaseKeyBinding(final ats binding) {
        ats.a.remove(binding);
        ats.b.d(binding.d);
    }
    
    private static String[] getModLoaderMessage(final String modName) {
        return new String[] { "========================================", "Smart Moving detected " + modName + "!", "----------------------------------------", "This Smart Moving standalone installation package should really not be used together with " + modName + ".", "The Smart Moving installation package:", "\t\"Smart Moving Client for ModLoader or Minecraft Forge\"", "would be a much better choice for Smart Moving on a " + modName + " Client.", "========================================" };
    }
    
    public static void initializeServerIfNecessary() {
        final MinecraftServer currentMinecraftServer = MinecraftServer.F();
        if (currentMinecraftServer != null && currentMinecraftServer != SmartMovingContext.lastMinecraftServer) {
            SmartMovingServer.initialize(SmartMovingOptions.optionsPath, Install.getLogger(MinecraftServer.F().an()), currentMinecraftServer.h().a(), SmartMovingContext.Options);
        }
        SmartMovingContext.lastMinecraftServer = currentMinecraftServer;
    }
    
    public static boolean registerAnimation(final Map map) {
        return SmartRenderContext.registerAnimation(map, RenderPlayer.class);
    }
    
    static {
        Client = new SmartMovingClient();
        Options = new SmartMovingOptions();
        ServerConfig = new SmartMovingServerConfig();
        SmartMovingContext.Config = SmartMovingContext.Options;
        SmartMovingContext.lastMinecraftServer = null;
    }
}
