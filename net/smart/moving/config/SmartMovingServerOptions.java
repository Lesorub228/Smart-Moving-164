// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.config;

import java.util.Iterator;
import net.smart.properties.Properties;
import net.smart.moving.IEntityPlayerMP;
import java.util.Map;
import net.smart.properties.Property;
import java.util.logging.Logger;
import java.io.File;

public class SmartMovingServerOptions
{
    public final SmartMovingConfig config;
    public final File optionsPath;
    public final Logger logger;
    private final Property<Map<String, String>> _userConfigKeys;
    
    public SmartMovingServerOptions(final SmartMovingConfig config, final File optionsPath, final Logger logger, final int gameType) {
        this.config = config;
        this.logger = logger;
        config.loadFromOptionsFile(this.optionsPath = optionsPath);
        config.saveToOptionsFile(optionsPath);
        Property<String> configKey = null;
        Property<String[]> configKeys = null;
        switch (gameType) {
            default: {
                configKey = config._survivalDefaultConfigKey;
                configKeys = config._survivalConfigKeys;
                this._userConfigKeys = config._survivalDefaultConfigUserKeys;
                break;
            }
            case 1: {
                configKey = config._creativeDefaultConfigKey;
                configKeys = config._creativeConfigKeys;
                this._userConfigKeys = config._creativeDefaultConfigUserKeys;
                break;
            }
            case 2: {
                configKey = config._adventureDefaultConfigKey;
                configKeys = config._adventureConfigKeys;
                this._userConfigKeys = config._adventureDefaultConfigUserKeys;
                break;
            }
        }
        config.setKeys(configKeys.value);
        config.setCurrentKey((configKey != null && !configKey.value.isEmpty()) ? configKey.value : null);
        this.logConfigState(config, null, false);
    }
    
    public void toggle(final IEntityPlayerMP player) {
        this.config.toggle();
        this.config.saveToOptionsFile(this.optionsPath);
        this.logConfigState(this.config, player.getUsername(), true);
    }
    
    public void changeSpeed(final int difference, final IEntityPlayerMP player) {
        this.config.changeSpeed(difference);
        this.config.saveToOptionsFile(this.optionsPath);
        this.logSpeedState(this.config, player.getUsername());
    }
    
    private void logConfigState(final SmartMovingConfig config, final String username, final boolean reconfig) {
        String message = "Smart Moving ";
        if (config._globalConfig.value) {
            if (!reconfig) {
                this.logger.info(message + "overrides client configurations");
            }
            final String postfix = getPostfix(username);
            if (config.enabled) {
                final String currentKey = config.getCurrentKey();
                message += (reconfig ? "changed to " : "uses ");
                if (currentKey == null) {
                    this.logger.info(message + "default server configuration" + postfix);
                }
                else {
                    final String configName = config._configKeyName.value;
                    message += "server configuration ";
                    if (configName.isEmpty()) {
                        this.logger.info(message + "with key \"" + currentKey + "\"" + postfix);
                    }
                    else {
                        this.logger.info(message + "\"" + configName + "\"" + postfix);
                    }
                }
            }
            else {
                this.logger.info(message + "disabled" + postfix);
            }
        }
        else {
            this.logger.info(message + "allows client configurations");
        }
    }
    
    private void logSpeedState(final SmartMovingConfig config, final String username) {
        this.logger.info("Smart Moving speed set to " + config.getSpeedPercent() + "%" + getPostfix(username));
    }
    
    private static String getPostfix(final String username) {
        if (username == null) {
            return "";
        }
        return " by user '" + username + "'";
    }
    
    public String[] writeToProperties() {
        return this.writeToProperties(null, null);
    }
    
    public String[] writeToProperties(final IEntityPlayerMP mp, final String key) {
        Label_0054: {
            if (key == null) {
                if (this.config.enabled) {
                    break Label_0054;
                }
            }
            else if (key != "disabled") {
                break Label_0054;
            }
            return new String[] { this.config._globalConfig.getCurrentKey(), this.config._globalConfig.getValueString() };
        }
        final Properties properties = new Properties();
        this.config.write(properties, key);
        final String[] result = new String[properties.size() * 2];
        final Iterator<Map.Entry<Object, Object>> keys = properties.entrySet().iterator();
        final String speedUserExponentKey = (mp != null) ? this.config._speedUserExponent.getCurrentKey() : null;
        int i = 0;
        while (keys.hasNext()) {
            final Map.Entry<Object, Object> entry = keys.next();
            final String[] array = result;
            final int n = i++;
            final String string = entry.getKey().toString();
            array[n] = string;
            final String propertyKey = string;
            if (mp != null && propertyKey.equals(speedUserExponentKey)) {
                final Integer userExponent = this.config._speedUsersExponents.value.get(mp.getUsername());
                if (userExponent != null) {
                    entry.setValue(this.config._speedUserExponent.getValueString(userExponent));
                }
            }
            result[i++] = entry.getValue().toString();
        }
        return result;
    }
    
    public void changeSingleSpeed(final IEntityPlayerMP player, final int difference) {
        Integer exponent = this.getPlayerSpeedExponent(player);
        if (exponent == null) {
            exponent = this.config._speedUserExponent.value;
        }
        exponent += difference;
        this.setPlayerSpeedExponent(player, exponent);
    }
    
    public Integer getPlayerSpeedExponent(final IEntityPlayerMP player) {
        return this.config._speedUsersExponents.value.get(player.getUsername());
    }
    
    public synchronized void setPlayerSpeedExponent(final IEntityPlayerMP player, final Integer exponent) {
        this.config._speedUsersExponents.value.put(player.getUsername(), exponent);
        this.config.saveToOptionsFile(this.optionsPath);
    }
    
    public String[] writeToProperties(final IEntityPlayerMP player, final boolean toggle) {
        String key = this.getPlayerConfigurationKey(player);
        if (key == null || !this.config.hasKey(key)) {
            key = this.config.getCurrentKey();
            this.setPlayerConfigurationKey(player, key);
        }
        if (toggle) {
            key = this.config.getNextKey(key);
            this.setPlayerConfigurationKey(player, key);
        }
        return this.writeToProperties(player, key);
    }
    
    public String getPlayerConfigurationKey(final IEntityPlayerMP player) {
        return this._userConfigKeys.value.get(player.getUsername());
    }
    
    public synchronized void setPlayerConfigurationKey(final IEntityPlayerMP player, final String key) {
        this._userConfigKeys.value.put(player.getUsername(), key);
        this.config.saveToOptionsFile(this.optionsPath);
    }
}
