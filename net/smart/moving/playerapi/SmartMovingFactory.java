// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.playerapi;

import net.smart.moving.IEntityPlayerSP;
import net.smart.moving.SmartMoving;

public class SmartMovingFactory extends net.smart.moving.SmartMovingFactory
{
    public static void initialize() {
        if (!net.smart.moving.SmartMovingFactory.isInitialized()) {
            new SmartMovingFactory();
        }
    }
    
    @Override
    protected SmartMoving doGetInstance(final uf entityPlayer) {
        final SmartMoving moving = super.doGetInstance(entityPlayer);
        if (moving != null) {
            return moving;
        }
        final IEntityPlayerSP playerBase = net.smart.moving.playerapi.SmartMoving.getPlayerBase(entityPlayer);
        if (playerBase != null) {
            return playerBase.getMoving();
        }
        return null;
    }
}
