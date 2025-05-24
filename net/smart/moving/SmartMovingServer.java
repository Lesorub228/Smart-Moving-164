// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.util.List;
import net.smart.utilities.Assert;
import java.util.logging.Logger;
import java.io.File;
import net.smart.properties.Property;
import net.smart.moving.config.SmartMovingServerOptions;
import net.smart.moving.config.SmartMovingConfig;

public class SmartMovingServer
{
    public static final float SmallSizeItemGrabHeight = 0.25f;
    protected final IEntityPlayerMP mp;
    private boolean resetFallDistance;
    private boolean resetTicksForFloatKick;
    private boolean initialized;
    private boolean withinOnLivingUpdate;
    public boolean crawlingInitialized;
    public int crawlingCooldown;
    public boolean isCrawling;
    public boolean isSmall;
    public float hunger;
    private int disableAddExhaustionDepth;
    private boolean disableAddExhaustion;
    public static SmartMovingConfig Options;
    private static SmartMovingServerOptions optionsHandler;
    
    public SmartMovingServer(final IEntityPlayerMP mp, final boolean onTheFly) {
        this.resetFallDistance = false;
        this.resetTicksForFloatKick = false;
        this.initialized = false;
        this.withinOnLivingUpdate = false;
        this.mp = mp;
        if (onTheFly) {
            this.initialize(true);
        }
    }
    
    public void initialize(final boolean alwaysSendMessage) {
        if (SmartMovingServer.Options._globalConfig.value) {
            SmartMovingPacketStream.sendConfigContent(this.mp, SmartMovingServer.optionsHandler.writeToProperties(), null);
        }
        else if (SmartMovingServer.Options._serverConfig.value) {
            SmartMovingPacketStream.sendConfigContent(this.mp, SmartMovingServer.optionsHandler.writeToProperties(this.mp, false), null);
        }
        else if (alwaysSendMessage) {
            SmartMovingPacketStream.sendConfigContent(this.mp, (String[])(SmartMovingServer.Options.enabled ? new String[0] : null), null);
        }
        this.initialized = true;
    }
    
    public void processStatePacket(final ea packet, final long state) {
        if (!this.initialized) {
            this.initialize(false);
        }
        final boolean isCrawling = (state >>> 13 & 0x1L) != 0x0L;
        this.setCrawling(isCrawling);
        final boolean isSmall = (state >>> 15 & 0x1L) != 0x0L;
        this.setSmall(isSmall);
        final boolean isClimbing = (state >>> 14 & 0x1L) != 0x0L;
        final boolean isCrawlClimbing = (state >>> 12 & 0x1L) != 0x0L;
        final boolean isCeilingClimbing = (state >>> 18 & 0x1L) != 0x0L;
        final boolean isWallJumping = (state >>> 31 & 0x1L) != 0x0L;
        this.resetFallDistance = (isClimbing || isCrawlClimbing || isCeilingClimbing || isWallJumping);
        this.resetTicksForFloatKick = (isClimbing || isCrawlClimbing || isCeilingClimbing);
        this.mp.sendPacketToTrackedPlayers(packet);
    }
    
    public void processConfigPacket(final String clientConfigurationVersion) {
        boolean warn = true;
        String type = "unknown";
        if (clientConfigurationVersion != null) {
            for (int i = 0; i < SmartMovingConfig._all.length; ++i) {
                if (clientConfigurationVersion.equals(SmartMovingConfig._all[i])) {
                    warn = (i > 0);
                    type = (warn ? "outdated" : "matching");
                    break;
                }
            }
        }
        String message = "Smart Moving player \"" + this.mp.getUsername() + "\" connected with " + type + " configuration system";
        if (clientConfigurationVersion != null) {
            message = message + " version \"" + clientConfigurationVersion + "\"";
        }
        if (warn) {
            this.mp.getLogger().warning(message);
        }
        else {
            this.mp.getLogger().info(message);
        }
    }
    
    public void processConfigChangePacket(final String localUserName) {
        if (!SmartMovingServer.Options._globalConfig.value) {
            this.toggleSingleConfig();
            return;
        }
        final String username = this.mp.getUsername();
        if (localUserName == username) {
            this.toggleConfig();
            return;
        }
        final String[] rightPlayerNames = SmartMovingServer.Options._usersWithChangeConfigRights.value;
        for (int i = 0; i < rightPlayerNames.length; ++i) {
            if (rightPlayerNames[i].equals(username)) {
                this.toggleConfig();
                return;
            }
        }
        SmartMovingPacketStream.sendConfigChange(this.mp);
    }
    
    public void processSpeedChangePacket(final int difference, final String localUserName) {
        if (!SmartMovingServer.Options._globalConfig.value) {
            this.changeSingleSpeed(difference);
            return;
        }
        if (!this.hasRight(localUserName, SmartMovingServer.Options._usersWithChangeSpeedRights)) {
            SmartMovingPacketStream.sendSpeedChange(this.mp, 0, null);
        }
        else {
            this.changeSpeed(difference);
        }
    }
    
    public void processHungerChangePacket(final float hunger) {
        this.hunger = hunger;
    }
    
    public void processSoundPacket(final String soundId, final float volume, final float pitch) {
        this.mp.localPlaySound(soundId, volume, pitch);
    }
    
    private boolean hasRight(final String localUserName, final Property<String[]> rights) {
        final String username = this.mp.getUsername();
        if (localUserName == username) {
            return true;
        }
        final String[] rightPlayerNames = rights.value;
        for (int i = 0; i < rightPlayerNames.length; ++i) {
            if (rightPlayerNames[i].equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    public void toggleSingleConfig() {
        SmartMovingPacketStream.sendConfigContent(this.mp, SmartMovingServer.optionsHandler.writeToProperties(this.mp, true), this.mp.getUsername());
    }
    
    public void toggleConfig() {
        SmartMovingServer.optionsHandler.toggle(this.mp);
        final String[] config = SmartMovingServer.optionsHandler.writeToProperties();
        final IEntityPlayerMP[] players = this.mp.getAllPlayers();
        for (int n = 0; n < players.length; ++n) {
            SmartMovingPacketStream.sendConfigContent(players[n], config, this.mp.getUsername());
        }
    }
    
    public void changeSingleSpeed(final int difference) {
        SmartMovingServer.optionsHandler.changeSingleSpeed(this.mp, difference);
        SmartMovingPacketStream.sendSpeedChange(this.mp, difference, this.mp.getUsername());
    }
    
    public void changeSpeed(final int difference) {
        SmartMovingServer.optionsHandler.changeSpeed(difference, this.mp);
        final IEntityPlayerMP[] players = this.mp.getAllPlayers();
        for (int n = 0; n < players.length; ++n) {
            SmartMovingPacketStream.sendSpeedChange(players[n], difference, this.mp.getUsername());
        }
    }
    
    public void afterOnUpdate() {
        if (this.resetFallDistance) {
            this.mp.resetFallDistance();
        }
        if (this.resetTicksForFloatKick) {
            this.mp.resetTicksForFloatKick();
        }
    }
    
    public static void initialize(final File optionsPath, final Logger logger, final int gameType, final boolean standalone) {
        if (standalone) {
            Assert.server(logger);
        }
        initialize(optionsPath, logger, gameType, new SmartMovingConfig());
    }
    
    public static void initialize(final File optionsPath, final Logger logger, final int gameType, final SmartMovingConfig config) {
        SmartMovingServer.Options = config;
        SmartMovingServer.optionsHandler = new SmartMovingServerOptions(SmartMovingServer.Options, optionsPath, logger, gameType);
        logger.info("Smart Moving uses communication protocol 2.3");
    }
    
    public void setCrawling(final boolean crawling) {
        if (!crawling && this.isCrawling) {
            this.crawlingCooldown = 10;
        }
        this.isCrawling = crawling;
    }
    
    public void setSmall(final boolean isSmall) {
        this.mp.setHeight(isSmall ? 0.8f : 1.8f);
        this.isSmall = isSmall;
    }
    
    public void afterSetPosition(final double d, final double d1, final double d2) {
        if (!this.crawlingInitialized) {
            this.mp.setMaxY(this.mp.getMinY() + this.mp.getHeight() - 1.0);
        }
    }
    
    public void beforeIsPlayerSleeping() {
        if (!this.crawlingInitialized) {
            this.mp.setMaxY(this.mp.getMinY() + this.mp.getHeight());
            this.crawlingInitialized = true;
        }
    }
    
    public void beforeOnUpdate() {
        if (this.crawlingCooldown > 0) {
            --this.crawlingCooldown;
        }
    }
    
    public void beforeOnLivingUpdate() {
        this.withinOnLivingUpdate = true;
    }
    
    public void afterOnLivingUpdate() {
        this.withinOnLivingUpdate = false;
        if (!this.isSmall) {
            return;
        }
        if (this.mp.doGetHealth() <= 0.0f) {
            return;
        }
        final double offset = 0.25;
        asx box = this.mp.expandBox(this.mp.getBox(), 1.0, offset, 1.0);
        final List offsetEntities = this.mp.getEntitiesExcludingPlayer(box);
        if (offsetEntities != null && offsetEntities.size() > 0) {
            final Object[] offsetEntityArray = offsetEntities.toArray();
            box = this.mp.expandBox(box, 0.0, -offset, 0.0);
            final List standardEntities = this.mp.getEntitiesExcludingPlayer(box);
            for (int i = 0; i < offsetEntityArray.length; ++i) {
                final nn offsetEntity = (nn)offsetEntityArray[i];
                if (standardEntities == null || !standardEntities.contains(offsetEntity)) {
                    if (!this.mp.isDeadEntity(offsetEntity)) {
                        this.mp.onCollideWithPlayer(offsetEntity);
                    }
                }
            }
        }
    }
    
    public boolean isEntityInsideOpaqueBlock() {
        return this.crawlingCooldown <= 0 && this.mp.localIsEntityInsideOpaqueBlock();
    }
    
    public void addMovementStat(final double var1, final double var3, final double var5) {
        this.beforeAddMovingHungerBatch();
        this.mp.localAddMovementStat(var1, var3, var5);
        if (this.disableAddExhaustion && this.hunger != 0.0f && !this.withinOnLivingUpdate) {
            this.mp.localAddExhaustion(this.hunger);
        }
        this.afterAddMovingHungerBatch();
    }
    
    public void beforeAddMovingHungerBatch() {
        ++this.disableAddExhaustionDepth;
        if (this.hunger != -1.0f) {
            this.disableAddExhaustion = true;
        }
    }
    
    public void addExhaustion(final float exhaustion) {
        if (!this.disableAddExhaustion) {
            this.mp.localAddExhaustion(exhaustion);
        }
    }
    
    public void afterAddMovingHungerBatch() {
        --this.disableAddExhaustionDepth;
        if (this.disableAddExhaustionDepth == 0) {
            this.disableAddExhaustion = false;
        }
    }
    
    static {
        SmartMovingServer.Options = null;
        SmartMovingServer.optionsHandler = null;
    }
}
