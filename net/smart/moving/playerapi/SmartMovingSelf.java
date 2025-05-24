// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.playerapi;

import api.player.client.ClientPlayerBase;
import net.smart.utilities.Reflect;
import net.smart.utilities.Name;
import net.smart.moving.config.SmartMovingOptions;
import net.smart.moving.IEntityPlayerSP;
import java.lang.reflect.Field;

public class SmartMovingSelf extends net.smart.moving.SmartMovingSelf
{
    private static Field playerHelperField;
    private static Field flyingField;
    
    public SmartMovingSelf(final uf sp, final SmartMovingPlayerBase playerBase) {
        super(sp, playerBase);
    }
    
    @Override
    public boolean doFlyingAnimation() {
        return (SmartMovingOptions.hasSinglePlayerCommands && isSPCFlying(this.esp)) || super.doFlyingAnimation();
    }
    
    public static boolean isSPCFlying(final bex entityPlayer) {
        if (!SmartMovingOptions.hasSinglePlayerCommands) {
            return false;
        }
        final ClientPlayerBase spcPlayerBase = entityPlayer.getClientPlayerBase("Single Player Commands");
        if (spcPlayerBase == null) {
            return false;
        }
        if (SmartMovingSelf.playerHelperField == null) {
            SmartMovingSelf.playerHelperField = Reflect.GetField(spcPlayerBase.getClass(), new Name("ph"), false);
        }
        if (SmartMovingSelf.playerHelperField == null) {
            return false;
        }
        final Object playerHelper = Reflect.GetField(SmartMovingSelf.playerHelperField, spcPlayerBase);
        if (playerHelper == null) {
            return false;
        }
        if (SmartMovingSelf.flyingField == null) {
            SmartMovingSelf.flyingField = Reflect.GetField(playerHelper.getClass(), new Name("flying"), false);
        }
        if (SmartMovingSelf.flyingField == null) {
            return false;
        }
        final Object isFlying = Reflect.GetField(SmartMovingSelf.flyingField, playerHelper);
        return isFlying != null && isFlying instanceof Boolean && (boolean)isFlying;
    }
    
    static {
        SmartMovingSelf.playerHelperField = null;
        SmartMovingSelf.flyingField = null;
    }
}
