// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.utilities;

import java.util.HashSet;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;

public class Assert
{
    public static final String messageBorder = "========================================";
    public static final String messageSeparator = "----------------------------------------";
    private static Set<Class<?>> singles;
    
    public static boolean singleton(final Class<?> type, final String modName, final Logger logger) {
        final boolean isSingleton = !Assert.singles.contains(type);
        if (isSingleton) {
            Assert.singles.add(type);
        }
        else {
            warn(logger, getSingleMessage(modName));
        }
        return isSingleton;
    }
    
    public static void client(final Logger logger) {
        if (!Install.hasClient) {
            throw new RuntimeException(error(logger, getSideMessage(true)));
        }
    }
    
    public static void server(final Logger logger) {
        if (Install.hasClient && (!Install.hasBukkit || !Install.hasMinecraftForge)) {
            throw new RuntimeException(error(logger, getSideMessage(false)));
        }
    }
    
    public static void clientPlayerAPI(final Logger logger) {
        if (!Install.hasClientPlayerAPI) {
            throw new RuntimeException(error(logger, getPlayerAPIMessage(true)));
        }
    }
    
    public static void serverPlayerAPI(final Logger logger) {
        if (!Install.hasServerPlayerAPI) {
            throw new RuntimeException(error(logger, getPlayerAPIMessage(false)));
        }
    }
    
    private static String[] getSideMessage(final boolean client) {
        return getMessage("Smart Moving could" + (client ? " not" : "") + " find client classes!", "Don't use this Smart Moving " + (client ? "Client" : "Server") + " installation package on a" + (client ? " dedicate" : "") + " Minecraft " + (client ? "server" : "client") + ".", "That's what Smart Moving " + (client ? "Server" : "Client") + " installation packages are good for.");
    }
    
    private static String[] getPlayerAPIMessage(final boolean client) {
        return getMessage("Smart Moving could not find the required API \"" + getPlayerAPIPrefix(client) + " Player\"!", "Download Player API " + getPlayerAPIProductPostfix() + " from:", "\thttp://www.minecraftforum.net/topic/738498-/", "and install it on your system to fix this specific problem.");
    }
    
    private static String[] getSingleMessage(final String modName) {
        return getMessage(modName + " has been created more than once!", "That is usually being caused by multiple installations of the " + modName + " mod at different locations.", "Clean up your \"mods\" folder and/or your \"minecraft" + (Install.hasClient ? "" : "_server") + ".jar\"");
    }
    
    private static String[] getMessage(final String header, final String... lines) {
        final String[] message = new String[lines.length + 4];
        int i = 0;
        message[i++] = "========================================";
        message[i++] = header;
        message[i++] = "----------------------------------------";
        for (int n = 0; n < lines.length; ++n) {
            message[i++] = lines[n];
        }
        message[i] = "========================================";
        return message;
    }
    
    private static String getPlayerAPIPrefix(final boolean client) {
        return client ? "Client" : "Server";
    }
    
    private static String getPlayerAPIProductPostfix() {
        return Install.hasBukkit ? (Install.hasMinecraftForge ? "MCPC+" : "bukkit") : (Install.hasMinecraftForge ? "forge" : "vanilla");
    }
    
    public static String error(final Logger logger, final String... lines) {
        return log(logger, Level.SEVERE, System.err, lines);
    }
    
    public static String warn(final Logger logger, final String... lines) {
        return log(logger, Level.WARNING, System.err, lines);
    }
    
    private static String log(final Logger logger, final Level level, final PrintStream stream, final String... lines) {
        String message = "\n";
        for (int i = 0; i < lines.length; ++i) {
            final String line = lines[i];
            stream.println(line);
            message = message + "\n\t" + line;
        }
        message += "\n";
        if (logger != null) {
            logger.log(level, message);
        }
        return message;
    }
    
    static {
        Assert.singles = new HashSet<Class<?>>();
    }
}
