// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.utilities;

import java.util.logging.Logger;
import java.lang.reflect.Field;

public class Install
{
    public static final Name Main;
    public static final Name ModLoader;
    public static final Name ForgeHooks;
    public static final Name Bukkit;
    public static final Name RopesPlusCore;
    public static final Name ModBlockFence;
    public static final Name MacroModCore;
    public static final Name BlockSturdyLadder;
    public static final Name BlockRopeLadder;
    public static final Name ForgeHooksClient;
    public static final Name RopesPlusClient;
    public static final Name RopesPlusClient_onZipLine;
    public static final Name NetServerHandler_minecraftServer;
    public static final Name NetServerHandler_ticksForFloatKick;
    public static final Name NetworkListenThread_playerList;
    public static final Name Minecraft_resourcePacks;
    public static final Name GuiNewChat_chatMessageList;
    public static final Name PlayerControllerMP_currentGameType;
    public static final Name ModelRenderer_compiled;
    public static final Name ModelRenderer_compileDisplayList;
    public static final Name ModelRenderer_displayList;
    public static final Name RenderPlayer_modelBipedMain;
    public static final Name RenderPlayer_modelArmorChestplate;
    public static final Name RenderPlayer_modelArmor;
    public static final Name RenderManager_entityRenderMap;
    public static final Name LogAgent_logger;
    public static final Name ForgeHooks_onLivingJump;
    public static final Name ModifiableAttributeInstance_attributeValue;
    public static final boolean hasClient;
    public static final boolean hasClientPlayerAPI;
    public static final boolean hasServerPlayerAPI;
    public static final boolean hasRenderPlayerAPI;
    public static final boolean hasModLoader;
    public static final boolean hasMinecraftForge;
    public static final boolean hasBukkit;
    public final String obfuscatedName;
    public final String deobfuscatedName;
    private static final Field _logger;
    
    private static boolean hasAPI(final String prefix) {
        final String fullPrefix = "api.player." + prefix.substring(0, 1).toLowerCase() + prefix.substring(1) + "." + prefix + "Player";
        return Reflect.CheckClasses(Install.class, new Name(fullPrefix + "API"), new Name(fullPrefix + "BaseSorter"));
    }
    
    public Install(final String name) {
        this(name, null);
    }
    
    public Install(final String deobfuscatedName, final String obfuscatedName) {
        this.deobfuscatedName = deobfuscatedName;
        this.obfuscatedName = obfuscatedName;
    }
    
    public static Logger getLogger(final lp logAgent) {
        return (logAgent instanceof lc) ? Reflect.GetField(Install._logger, logAgent) : null;
    }
    
    static {
        Main = new Name("net.minecraft.client.main.Main");
        ModLoader = new Name("net.minecraft.src.ModLoader", "ModLoader");
        ForgeHooks = new Name("net.minecraftforge.common.ForgeHooks");
        Bukkit = new Name("org.bukkit.Bukkit");
        RopesPlusCore = new Name("atomicstryker.ropesplus.common.RopesPlusCore");
        ModBlockFence = new Name("net.minecraft.src.modBlockFence", "modBlockFence");
        MacroModCore = new Name("net.eq2online.macros.core.MacroModCore");
        BlockSturdyLadder = new Name("mods.chupmacabre.ladderKit.sturdyLadders.BlockSturdyLadder");
        BlockRopeLadder = new Name("mods.chupmacabre.ladderKit.ropeLadders.BlockRopeLadder");
        ForgeHooksClient = new Name("net.minecraftforge.client.ForgeHooksClient");
        RopesPlusClient = new Name("atomicstryker.ropesplus.client.RopesPlusClient");
        RopesPlusClient_onZipLine = new Name("onZipLine");
        NetServerHandler_minecraftServer = new Name("mcServer", "field_72573_d", "d");
        NetServerHandler_ticksForFloatKick = new Name("ticksForFloatKick", "field_72572_g", "f");
        NetworkListenThread_playerList = new Name("connections", "field_71748_d", "c");
        Minecraft_resourcePacks = new Name("defaultResourcePacks", "field_110449_ao", "aq");
        GuiNewChat_chatMessageList = new Name("chatLines", "field_73771_c", "c");
        PlayerControllerMP_currentGameType = new Name("currentGameType", "field_78779_k", "k");
        ModelRenderer_compiled = new Name("compiled", "field_78812_q", "t");
        ModelRenderer_compileDisplayList = new Name("compileDisplayList", "func_78788_d", "d");
        ModelRenderer_displayList = new Name("displayList", "field_78811_r", "u");
        RenderPlayer_modelBipedMain = new Name("modelBipedMain", "field_77109_a", "f");
        RenderPlayer_modelArmorChestplate = new Name("modelArmorChestplate", "field_77108_b", "g");
        RenderPlayer_modelArmor = new Name("modelArmor", "field_77111_i", "h");
        RenderManager_entityRenderMap = new Name("entityRenderMap", "field_78729_o", "q");
        LogAgent_logger = new Name("serverLogger", "field_98242_a", "a");
        ForgeHooks_onLivingJump = new Name("onLivingJump");
        ModifiableAttributeInstance_attributeValue = new Name("field_111139_h", "field_111139_h", "h");
        hasClient = Reflect.CheckClasses(Install.class, Install.Main);
        hasClientPlayerAPI = hasAPI("Client");
        hasServerPlayerAPI = hasAPI("Server");
        hasRenderPlayerAPI = hasAPI("Render");
        hasModLoader = Reflect.CheckClasses(Install.class, Install.ModLoader);
        hasMinecraftForge = Reflect.CheckClasses(Install.class, Install.ForgeHooks);
        hasBukkit = Reflect.CheckClasses(Install.class, Install.Bukkit);
        _logger = Reflect.GetField(lc.class, Install.LogAgent_logger);
    }
}
