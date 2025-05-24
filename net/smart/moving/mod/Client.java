// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.mod;

import net.smart.moving.IEntityPlayerMP;
import net.smart.moving.IPacketReceiver;
import net.smart.moving.SmartMovingComm;
import java.util.List;
import net.smart.moving.config.SmartMovingOptions;
import net.smart.utilities.ResourcePack;
import net.smart.moving.playerapi.SmartMovingFactory;
import net.smart.moving.SmartMovingPacketStream;
import java.util.Map;
import net.smart.moving.SmartMovingContext;
import java.lang.reflect.Method;
import net.smart.moving.SmartMovingServerComm;
import net.smart.moving.LocalUserNameProvider;
import net.smart.utilities.Reflect;
import net.smart.utilities.Name;
import net.smart.moving.playerapi.SmartMoving;
import net.smart.utilities.Install;
import java.util.logging.Logger;
import net.smart.utilities.Assert;

public class Client extends Server
{
    private boolean hasRenderer;
    
    public static Client create(final mod_SmartMoving mod) {
        final Logger logger = ModLoader.getLogger();
        Assert.client(logger);
        Assert.clientPlayerAPI(logger);
        Assert.serverPlayerAPI(logger);
        return new Client(mod);
    }
    
    public Client(final mod_SmartMoving mod) {
        super(mod);
        this.hasRenderer = false;
        this.hasRenderer = Install.hasRenderPlayerAPI;
        SmartMoving.register();
        if (this.hasRenderer) {
            final Class<?> type = Reflect.LoadClass(BaseMod.class, new Name("net.smart.moving.render.playerapi.SmartMoving"), true);
            final Method method = Reflect.GetMethod(type, new Name("register"), (Class<?>[])new Class[0]);
            Reflect.Invoke(method, null, new Object[0]);
        }
        else {
            net.smart.render.mod.Client.doNotAddRenderer();
        }
        SmartMovingServerComm.localUserNameProvider = new LocalUserNameProvider();
    }
    
    @Override
    public void load() {
        if (!this.hasRenderer) {
            this.hasRenderer = SmartMovingContext.registerAnimation(null);
        }
        ModLoader.setInGameHook((BaseMod)this.mod, true, true);
        ModLoader.registerPacketChannel((BaseMod)this.mod, SmartMovingPacketStream.Id);
        SmartMovingFactory.initialize();
        this.checkForPresentModsAndInitializeOptions();
        SmartMovingContext.initialize(atv.w().u, false, ModLoader.getLogger());
        ResourcePack.remove(this.mod.getClass().getSimpleName());
    }
    
    @Override
    public void modsLoaded() {
    }
    
    @Override
    public void addRenderer(final Map map) {
        if (!this.hasRenderer) {
            this.hasRenderer = SmartMovingContext.registerAnimation(map);
        }
    }
    
    @Override
    public void registerAnimation(final atv minecraft) {
        if (!this.hasRenderer) {
            this.hasRenderer = SmartMovingContext.registerAnimation(null);
        }
    }
    
    @Override
    public boolean onTickInGame(final float f, final atv minecraft) {
        SmartMovingContext.onTickInGame();
        return true;
    }
    
    public void checkForPresentModsAndInitializeOptions() {
        final List<?> modList = ModLoader.getLoadedMods();
        boolean hasRedPowerWiring = false;
        boolean hasBuildCraftTransport = false;
        boolean hasFiniteLiquid = false;
        boolean hasBetterThanWolves = false;
        boolean hasSinglePlayerCommands = false;
        boolean hasRopesPlus = false;
        boolean hasASGrapplingHook = false;
        boolean hasBetterMisc = false;
        for (int i = 0; i < modList.size(); ++i) {
            final BaseMod mod = (BaseMod)modList.get(i);
            final String name = mod.getClass().getSimpleName();
            if (name.equals("mod_RedPowerWiring")) {
                hasRedPowerWiring = true;
            }
            else if (name.equals("mod_BuildCraftTransport")) {
                hasBuildCraftTransport = true;
            }
            else if (name.equals("mod_Liquid")) {
                hasFiniteLiquid = true;
            }
            else if (name.equals("mod_FCBetterThanWolves")) {
                hasBetterThanWolves = true;
            }
            else if (name.equals("mod_SinglePlayerCommands")) {
                hasSinglePlayerCommands = true;
            }
            else if (name.equals("mod_ASGrapplingHook")) {
                hasASGrapplingHook = true;
            }
            else if (name.equals("mod_BetterMisc")) {
                hasBetterMisc = true;
            }
        }
        hasRopesPlus = Reflect.CheckClasses(BaseMod.class, Install.RopesPlusCore);
        SmartMovingOptions.initialize(hasRedPowerWiring, hasBuildCraftTransport, hasFiniteLiquid, hasBetterThanWolves, hasSinglePlayerCommands, hasRopesPlus, hasASGrapplingHook, hasBetterMisc);
    }
    
    @Override
    public void clientCustomPayload(final bcw netclienthandler, final ea packet) {
        SmartMovingPacketStream.receivePacket(packet, SmartMovingComm.instance, null);
    }
    
    @Override
    public void receiveCustomPacket(final ea packet) {
        SmartMovingPacketStream.receivePacket(packet, SmartMovingComm.instance, null);
    }
    
    @Override
    public void onPacket250Received(final uf source, final ea payload) {
        SmartMovingPacketStream.receivePacket(payload, SmartMovingServerComm.instance, SmartMoving.getServerPlayerBase(source));
    }
    
    @Override
    public net.smart.moving.SmartMoving getInstance(final uf entityPlayer) {
        return net.smart.moving.SmartMovingFactory.getInstance(entityPlayer);
    }
}
