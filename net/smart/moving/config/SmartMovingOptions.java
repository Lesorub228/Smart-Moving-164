// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.config;

import net.smart.properties.Value;
import net.smart.utilities.Reflect;
import net.smart.utilities.Install;
import net.smart.moving.Info;
import net.smart.moving.SmartMovingContext;
import net.smart.properties.Properties;
import java.lang.reflect.Field;
import java.io.File;
import net.smart.properties.Property;

public class SmartMovingOptions extends SmartMovingClientConfig
{
    public final Property<Boolean> _localUserHasChangeConfigRight;
    public final Property<Boolean> _localUserHasChangeSpeedRight;
    public final Property<Float> _perspectiveFadeFactor;
    public final Property<Float> _perspectiveRunFactor;
    public final Property<Float> _perspectiveSprintFactor;
    public final Property<Float> _angleJumpDoubleClickTicks;
    public final Property<Boolean> _wallJumpDoubleClick;
    public final Property<Float> _wallJumpDoubleClickTicks;
    public final Property<Boolean> _climbJumpBackHeadOnGrab;
    public final Property<Boolean> _displayExhaustionBar;
    public final Property<Boolean> _displayJumpChargeBar;
    public final Property<Boolean> _sneakToggle;
    public final Property<Boolean> _crawlToggle;
    public final Property<Boolean> _flyCloseToGround;
    public final Property<Boolean> _flyWhileOnGround;
    public final Property<Boolean> _flyControlVertical;
    public final Property<Boolean> _diveControlVertical;
    private final Property<Integer> _old_toggleKeyCode;
    private final Property<String> _defaultConfigToggleKeyName;
    private final Property<String> _defaultGrabKeyName;
    private final Property<String> _defaultSprintKeyName;
    private final Property<String> _speedIncreaseKeyName;
    private final Property<String> _speedDecreaseKeyName;
    public final Property<Integer> _defaultConfigToggleKeyCode;
    public final Property<Integer> _defaultGrabKeyCode;
    public final Property<Integer> _defaultSprintKeyCode;
    public final Property<Integer> _defaultSpeedIncreaseKeyCode;
    public final Property<Integer> _defaultSpeedDecreaseKeyCode;
    public final Property<Boolean> _configChat;
    public final Property<Boolean> _configChatInit;
    public final Property<Boolean> _configChatInitHelp;
    public final Property<Boolean> _configChatServer;
    public final Property<Boolean> _speedChat;
    public final Property<Boolean> _speedChatInit;
    public final Property<Boolean> _speedChatInitHelp;
    public final Property<Boolean> _speedChatServer;
    public ats keyBindGrab;
    public ats keyBindSprint;
    public ats keyBindConfigToggle;
    public ats keyBindSpeedIncrease;
    public ats keyBindSpeedDecrease;
    public static final File optionsPath;
    public static boolean hasRedPowerWire;
    public static boolean hasBuildCraftTransportation;
    public static boolean hasFiniteLiquid;
    public static boolean hasBetterThanWolves;
    public static boolean hasSinglePlayerCommands;
    public static boolean hasRopesPlus;
    public static boolean hasASGrapplingHook;
    public static boolean hasBetterMisc;
    public static boolean hasMoreKeyBindingGui;
    public int gameType;
    private static Field _currentGameType;
    
    public SmartMovingOptions() {
        this._localUserHasChangeConfigRight = Properties.Unmodified("move.global.config.right.local.user", new String[0]).comment("Whether the current local user has the right to change the global configuration in-game (despite of the names listed in \"move.global.config.right.user.names\"").section(new String[0]);
        this._localUserHasChangeSpeedRight = Properties.Unmodified("move.global.speed.right.local.user", new String[0]).comment("Whether the current local user has the right to change the global speed in-game (despite of the names listed in \"move.global.config.right.user.names\"");
        this._perspectiveFadeFactor = Properties.PositiveFactor("move.perspective.fade.factor", new String[0]).values(0.5f, 0.1f, 1.0f).comment("Fading speed factor between the different perspectives (>= 0.1, <= 1, set to '1' to switch off)").book("Viewpoint perspective", "Below you find the options to manipulate the viewpoint perspective");
        this._perspectiveRunFactor = Properties.Float("move.perspective.run.factor", new String[0]).key("move.run.perspective.factor", SmartMovingOptions._pre_sm_2_1).defaults(1.0f, new String[0]).comment("Standard sprinting perspective (set to '0' to switch off)");
        this._perspectiveSprintFactor = Properties.Float("move.perspective.sprint.factor", new String[0]).key("move.sprint.perspective.factor", SmartMovingOptions._pre_sm_2_1).defaults(1.5f, new String[0]).comment("Smart on ground sprinting perspective (set to '0' to switch off)");
        this._angleJumpDoubleClickTicks = Properties.Positive("move.jump.angle.double.click.ticks", new String[0]).singular().up(3.0f, 2.0f).comment("The maximum number of ticks between two clicks to trigger a side or back jump (>= 2)").book("User interface", "Below you find the options to manipulate Smart Moving's user interface");
        this._wallJumpDoubleClick = Properties.Unmodified("move.jump.wall.double.click", new String[0]).singular().comment("Whether wall jumping should be triggered by single or double clicking (and then press and holding) the jump button").section(new String[0]);
        this._wallJumpDoubleClickTicks = Properties.Positive("move.jump.wall.double.click.ticks", new String[0]).singular().up(3.0f, 2.0f).comment("The maximum number of ticks between two clicks to trigger a wall jump (>= 2, depends on \"move.jump.wall.double.click\")");
        this._climbJumpBackHeadOnGrab = Properties.Unmodified("move.jump.climb.back.head.on.grab", new String[0]).singular().comment("Whether pressing or not pressing the grab button while climb jumping back results in a head jump").section(new String[0]);
        this._displayExhaustionBar = Properties.Unmodified("move.gui.exhaustion.bar", new String[0]).singular().comment("Whether to display the exhaustion bar in the game overlay").section(new String[0]);
        this._displayJumpChargeBar = Properties.Unmodified("move.gui.jump.charge.bar", new String[0]).singular().comment("Whether to display the jump charge bar in the game overlay");
        this._sneakToggle = Properties.Modified("move.sneak.toggle", new String[0]).comment("To switch on/off sneak toggling").section(new String[0]);
        this._crawlToggle = Properties.Modified("move.crawl.toggle", new String[0]).comment("To switch on/off crawl toggling");
        this._flyCloseToGround = Properties.Modified("move.fly.ground.close", new String[0]).comment("To switch on/off flying close to the ground").section(new String[0]);
        this._flyWhileOnGround = Properties.Modified("move.fly.ground.collide", new String[0]).depends(this._flyCloseToGround).comment("To switch on/off flying while colliding with the grond (Relevant only if \"move.fly.ground.close\" is true)");
        this._flyControlVertical = Properties.Unmodified("move.fly.control.vertical", new String[0]).comment("Whether flying control also depends on where the player looks vertically.").section(new String[0]);
        this._diveControlVertical = Properties.Unmodified("move.dive.control.vertical", new String[0]).comment("Whether diving control also depends on where the player looks vertically.");
        this._old_toggleKeyCode = Properties.Integer("move.toggle.key", SmartMovingOptions._pre_sm_1_7).singular().defaults(67, new String[0]);
        this._defaultConfigToggleKeyName = Properties.String("move.config.toggle.default.key.name", new String[0]).key("move.toggle.key.name", SmartMovingOptions._pre_sm_3_2).singular().defaults("F9", new String[0]).source(this._old_toggleKeyCode.toKeyName(), SmartMovingOptions._pre_sm_1_7).singular().comment("Key name to toggle Smart Moving features in-game (default: \"F9\")").section(new String[0]);
        this._defaultGrabKeyName = Properties.String("move.grab.default.key.name", new String[0]).singular().defaults("LCONTROL", new String[0]).singular().comment("Default key name to \"grab\" (default: \"LCONTROL\")");
        this._defaultSprintKeyName = Properties.String("move.sprint.default.key.name", new String[0]).singular().defaults("TAB", new String[0]).singular().comment("Default key name to \"sprint\" (default: \"TAB\")");
        this._speedIncreaseKeyName = Properties.String("move.speed.increase.default.key.name", new String[0]).key("move.speed.increase.key.name", SmartMovingOptions._pre_sm_3_2).singular().defaults("O", new String[0]).singular().comment("Key name to increase the moving speed ingame (default: \"O\")");
        this._speedDecreaseKeyName = Properties.String("move.speed.decrease.default.key.name", new String[0]).key("move.speed.decrease.key.name", SmartMovingOptions._pre_sm_3_2).singular().defaults("I", new String[0]).singular().comment("Key name to decrease the moving speed ingame (default: \"I\")");
        this._defaultConfigToggleKeyCode = this._defaultConfigToggleKeyName.toKeyCode(67);
        this._defaultGrabKeyCode = this._defaultGrabKeyName.toKeyCode(29);
        this._defaultSprintKeyCode = this._defaultSprintKeyName.toKeyCode(15);
        this._defaultSpeedIncreaseKeyCode = this._speedIncreaseKeyName.toKeyCode(24);
        this._defaultSpeedDecreaseKeyCode = this._speedDecreaseKeyName.toKeyCode(23);
        this._configChat = Properties.Unmodified("move.config.chat", new String[0]).singular().comment("To switch on/off option status messages via chat system").book("Message Management", "Below you find the options to define in which case Smart Moving should write messages about its current behavior to the ingame chat");
        this._configChatInit = Properties.Unmodified("move.config.chat.init", new String[0]).depends(this._configChat).singular().comment("To switch on/off the initial option status message when starting a game (Relevant only if \"move.config.chat\" is not false)");
        this._configChatInitHelp = Properties.Unmodified("move.config.chat.init.help", new String[0]).depends(this._configChatInit).singular().comment("To switch on/off the initial option help message (Relevant only if \"move.config.chat.init\" is not false and no improved keybinding GUI (Minecraft Forge or the Macros/Keybind mod) is installed)");
        this._configChatServer = Properties.Unmodified("move.config.chat.server", new String[0]).depends(this._configChat).singular().comment("To switch on/off the server config overridden status message when joining a multiplayer game (Relevant only if \"move.config.chat\" is not false)");
        this._speedChat = Properties.Unmodified("move.speed.chat", new String[0]).singular().comment("To switch on/off speed messages via chat system").section(new String[0]);
        this._speedChatInit = Properties.Unmodified("move.speed.chat.init", new String[0]).depends(this._speedChat).singular().comment("To switch on/off the intial speed message when starting a game (Relevant only if \"move.speed.chat\" is not false)");
        this._speedChatInitHelp = Properties.Unmodified("move.speed.chat.init.help", new String[0]).depends(this._speedChatInit).singular().comment("To switch on/off the initial speed help message (Relevant only if \"move.speed.chat.init\" is not false and no improved keybinding GUI (Minecraft Forge or the Macros/Keybind mod) is installed))");
        this._speedChatServer = Properties.Unmodified("move.config.chat.server", new String[0]).depends(this._speedChat).singular().comment("To switch on/off the server speed change message when joining a multiplayer game (Relevant only if \"move.speed.chat\" is not false)");
        this.loadFromOptionsFile(SmartMovingOptions.optionsPath);
        this.saveToOptionsFile(SmartMovingOptions.optionsPath);
        this.keyBindGrab = new ats("key.climb", (int)this._defaultGrabKeyCode.value);
        this.keyBindSprint = new ats("key.sprint", (int)this._defaultSprintKeyCode.value);
        this.keyBindConfigToggle = new ats("key.config.toggle", (int)this._defaultConfigToggleKeyCode.value);
        this.keyBindSpeedIncrease = new ats("key.speed.increase", (int)this._defaultSpeedIncreaseKeyCode.value);
        this.keyBindSpeedDecrease = new ats("key.speed.decrease", (int)this._defaultSpeedDecreaseKeyCode.value);
    }
    
    public boolean isSneakToggleEnabled() {
        return this._sneakToggle.value && this.enabled;
    }
    
    public boolean isCrawlToggleEnabled() {
        return this._crawlToggle.value && this.enabled;
    }
    
    public int angleJumpDoubleClickTicks() {
        return (int)Math.ceil(this._angleJumpDoubleClickTicks.value);
    }
    
    public int wallJumpDoubleClickTicks() {
        return (int)Math.ceil(this._wallJumpDoubleClickTicks.value);
    }
    
    @Override
    public void toggle() {
        super.toggle();
        if (this._configChat.value) {
            this.writeClientConfigMessageToChat(false);
        }
        Property<String> defaultKey = null;
        switch (this.gameType) {
            case 0: {
                defaultKey = this._survivalDefaultConfigKey;
                break;
            }
            case 1: {
                defaultKey = this._creativeDefaultConfigKey;
                break;
            }
        }
        if (defaultKey != null) {
            final String currentKey = this.getCurrentKey();
            defaultKey.setValue(currentKey);
            this.saveToOptionsFile(SmartMovingOptions.optionsPath);
        }
    }
    
    @Override
    public void changeSpeed(final int difference) {
        super.changeSpeed(difference);
        this.writeClientSpeedMessageToChat(false);
        this.saveToOptionsFile(SmartMovingOptions.optionsPath);
    }
    
    private void writeClientConfigMessageToChat(final boolean everyone) {
        final String prefix = this.getClientEveryonePrefix("move.config.chat.client", everyone);
        if (SmartMovingContext.Config.enabled) {
            String name = SmartMovingContext.Config._configKeyName.value;
            if (name.isEmpty()) {
                name = null;
            }
            final boolean unnamed = name == null;
            if (unnamed) {
                name = this.getCurrentKey();
            }
            if (name == "enabled" || (unnamed && this.getKeyCount() == 1)) {
                writeToChat(prefix + "enabled", Info.ConfigChatId, new Object[0]);
            }
            else {
                writeToChat(prefix + (unnamed ? "unnamed" : "named"), Info.ConfigChatId, name);
            }
        }
        else {
            writeToChat(prefix + "disabled", Info.ConfigChatId, new Object[0]);
        }
    }
    
    public void writeClientSpeedMessageToChat(final boolean everyone) {
        if (!this._speedChat.value) {
            return;
        }
        final Object percent = SmartMovingContext.Config.getSpeedPercent();
        final String prefix = this.getClientEveryonePrefix("move.speed.chat.client", everyone);
        final String key = prefix + (percent.equals(SmartMovingConfig.defaultSpeedPercent) ? "reset" : "change");
        writeToChat(key, Info.SpeedChatId, percent);
    }
    
    private String getClientEveryonePrefix(final String base, final boolean everyone) {
        String result = base + ".";
        if (everyone) {
            result += "everyone.";
        }
        return result;
    }
    
    public void writeServerConfigMessageToChat() {
        if (!this._configChatServer.value) {
            return;
        }
        if (SmartMovingContext.Config.enabled) {
            final String configName = SmartMovingContext.Config._configKeyName.value;
            if (configName != null && !configName.isEmpty()) {
                writeToChat("move.config.chat.server.global.named", configName);
            }
            else {
                writeToChat("move.config.chat.server.global.unnamed", new Object[0]);
            }
        }
        else {
            writeToChat("move.config.chat.server.disable", new Object[0]);
        }
    }
    
    public void writeServerReconfigMessageToChat(final boolean wasEnabled, final String username, final boolean everyone) {
        if (atv.w().h.an().equals(username)) {
            this.writeClientConfigMessageToChat(everyone);
        }
        else if (this._configChatServer.value) {
            if (SmartMovingContext.Config.enabled) {
                final String configname = SmartMovingContext.Config._configKeyName.value;
                final boolean hasConfigName = configname != null && !configname.isEmpty();
                if (wasEnabled) {
                    if (hasConfigName) {
                        if (username != null) {
                            writeToChat("move.config.chat.server.update.named.user", Info.ConfigChatId, configname, username);
                        }
                        else {
                            writeToChat("move.config.chat.server.update.named", Info.ConfigChatId, configname);
                        }
                    }
                    else if (username != null) {
                        writeToChat("move.config.chat.server.update.unnamed.user", Info.ConfigChatId, username);
                    }
                    else {
                        writeToChat("move.config.chat.server.update.unnamed", Info.ConfigChatId, new Object[0]);
                    }
                }
                else if (hasConfigName) {
                    if (username != null) {
                        writeToChat("move.config.chat.server.update.named.user", Info.ConfigChatId, configname, username);
                    }
                    else {
                        writeToChat("move.config.chat.server.update.named", Info.ConfigChatId, configname);
                    }
                }
                else if (username != null) {
                    writeToChat("move.config.chat.server.enable.user", Info.ConfigChatId, username);
                }
                else {
                    writeToChat("move.config.chat.server.enable", Info.ConfigChatId, new Object[0]);
                }
            }
            else if (wasEnabled) {
                if (username != null) {
                    writeToChat("move.config.chat.server.disable.user", Info.ConfigChatId, username);
                }
                else {
                    writeToChat("move.config.chat.server.disable", Info.ConfigChatId, new Object[0]);
                }
            }
        }
    }
    
    public void writeServerDeconfigMessageToChat() {
        if (this._configChatServer.value) {
            writeToChat("move.config.chat.server.local", new Object[0]);
        }
    }
    
    public void writeServerSpeedMessageToChat(final String username, final boolean everyone) {
        if (atv.w().h.an().equals(username)) {
            this.writeClientSpeedMessageToChat(everyone);
        }
        else if (this._speedChatServer.value) {
            final Object percent = SmartMovingContext.Config.getSpeedPercent();
            final String prefix = "move.speed.chat.server.";
            if (percent.equals(SmartMovingConfig.defaultSpeedPercent)) {
                writeToChat(prefix + "reset", Info.SpeedChatId, username);
            }
            else {
                writeToChat(prefix + "change", Info.SpeedChatId, percent, username);
            }
        }
    }
    
    public void writeNoRightsToChangeConfigMessageToChat(final boolean isRemote) {
        writeToChat("move.config.chat.server.illegal." + (isRemote ? "remote" : "local"), Info.ConfigChatId, new Object[0]);
    }
    
    public void writeNoRightsToChangeSpeedMessageToChat(final boolean isRemote) {
        writeToChat("move.speed.chat.server.illegal." + (isRemote ? "remote" : "local"), Info.SpeedChatId, new Object[0]);
    }
    
    private static void writeToChat(final String key, final Object... parameters) {
        writeToChat(key, 0, parameters);
    }
    
    private static void writeToChat(final String key, final int id, final Object... parameters) {
        final String message = (parameters == null || parameters.length == 0) ? bkb.a(key) : bkb.a(key, parameters);
        final auu guiChat = atv.w().r.b();
        if (id != 0) {
            for (int i = 0; i < 5; ++i) {
                guiChat.c(id);
            }
        }
        guiChat.a(message, id);
    }
    
    public static void initialize(final boolean redPowerWiring, final boolean buildCraftTransportation, final boolean finiteLiquid, final boolean betterThanWolves, final boolean singlePlayerCommands, final boolean ropesPlus, final boolean aSGrapplingHook, final boolean betterMisc) {
        SmartMovingOptions.hasRedPowerWire = redPowerWiring;
        SmartMovingOptions.hasBuildCraftTransportation = buildCraftTransportation;
        SmartMovingOptions.hasFiniteLiquid = finiteLiquid;
        SmartMovingOptions.hasBetterThanWolves = betterThanWolves;
        SmartMovingOptions.hasSinglePlayerCommands = singlePlayerCommands;
        SmartMovingOptions.hasRopesPlus = ropesPlus;
        SmartMovingOptions.hasASGrapplingHook = aSGrapplingHook;
        SmartMovingOptions.hasBetterMisc = betterMisc;
        final boolean hasMacroKeybind = Reflect.LoadClass(atv.class, Install.MacroModCore, false) != null;
        final boolean hasMinecraftForge = Reflect.LoadClass(atv.class, Install.ForgeHooksClient, false) != null;
        SmartMovingOptions.hasMoreKeyBindingGui = (hasMacroKeybind || hasMinecraftForge);
    }
    
    public void resetForNewGame() {
        this.gameType = -1;
    }
    
    public void initializeForGameIfNeccessary() {
        final int currentGameType = ((ace)Reflect.GetField(SmartMovingOptions._currentGameType, atv.w().c)).a();
        if (currentGameType == this.gameType) {
            return;
        }
        this.gameType = currentGameType;
        String[] keys = null;
        String defaultKey = null;
        switch (this.gameType) {
            case 0: {
                keys = this._survivalConfigKeys.value;
                defaultKey = this._survivalDefaultConfigKey.value;
                break;
            }
            case 1: {
                keys = this._creativeConfigKeys.value;
                defaultKey = this._creativeDefaultConfigKey.value;
                break;
            }
            case 2: {
                keys = this._adventureConfigKeys.value;
                defaultKey = this._adventureDefaultConfigKey.value;
                break;
            }
            default: {
                defaultKey = "";
                break;
            }
        }
        this.setKeys(keys);
        if (!defaultKey.isEmpty()) {
            this.setCurrentKey(defaultKey);
        }
        if (this._configChatInit.value) {
            this.writeClientConfigMessageToChat(false);
        }
        if (this._configChatInitHelp.value && !SmartMovingOptions.hasMoreKeyBindingGui) {
            writeToChat("move.config.chat.client.help", Value.toKeyName(this.keyBindConfigToggle.d));
        }
        if (this.isUserSpeedEnabled() && this._speedChatInit.value) {
            final Object speedPercent = this.getSpeedPercent();
            if (!speedPercent.equals(SmartMovingOptions.defaultSpeedPercent)) {
                writeToChat("move.speed.chat.client.init", speedPercent);
            }
            if (this._speedChatInitHelp.value && !SmartMovingOptions.hasMoreKeyBindingGui && !this.isUserSpeedAlwaysDefault()) {
                writeToChat("move.speed.chat.client.help", Value.toKeyName(this.keyBindSpeedIncrease.d), Value.toKeyName(this.keyBindSpeedDecrease.d));
            }
        }
    }
    
    static {
        optionsPath = atv.w().x;
        SmartMovingOptions.hasRedPowerWire = false;
        SmartMovingOptions.hasBuildCraftTransportation = false;
        SmartMovingOptions.hasFiniteLiquid = false;
        SmartMovingOptions.hasBetterThanWolves = false;
        SmartMovingOptions.hasSinglePlayerCommands = false;
        SmartMovingOptions.hasRopesPlus = false;
        SmartMovingOptions.hasASGrapplingHook = false;
        SmartMovingOptions.hasBetterMisc = false;
        SmartMovingOptions.hasMoreKeyBindingGui = false;
        SmartMovingOptions._currentGameType = Reflect.GetField(bdc.class, Install.PlayerControllerMP_currentGameType);
    }
}
