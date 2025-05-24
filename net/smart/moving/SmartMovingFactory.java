// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.util.Iterator;
import java.util.Hashtable;

public class SmartMovingFactory extends SmartMovingContext
{
    private static SmartMovingFactory factory;
    private Hashtable<Integer, SmartMovingOther> otherSmartMovings;
    
    public SmartMovingFactory() {
        if (SmartMovingFactory.factory != null) {
            throw new RuntimeException("FATAL: Can only create one instance of type 'SmartMovingFactory'");
        }
        SmartMovingFactory.factory = this;
    }
    
    protected static boolean isInitialized() {
        return SmartMovingFactory.factory != null;
    }
    
    public static void initialize() {
        if (!isInitialized()) {
            new SmartMovingFactory();
        }
    }
    
    public static void handleMultiPlayerTick(final atv minecraft) {
        SmartMovingFactory.factory.doHandleMultiPlayerTick(minecraft);
    }
    
    public static SmartMoving getInstance(final uf entityPlayer) {
        return SmartMovingFactory.factory.doGetInstance(entityPlayer);
    }
    
    public static SmartMoving getOtherSmartMoving(final int entityId) {
        return SmartMovingFactory.factory.doGetOtherSmartMoving(entityId);
    }
    
    public static SmartMovingOther getOtherSmartMoving(final bey entity) {
        return SmartMovingFactory.factory.doGetOtherSmartMoving(entity);
    }
    
    protected void doHandleMultiPlayerTick(final atv minecraft) {
        for (final nn player : minecraft.f.h) {
            if (player instanceof bey) {
                final bey otherPlayer = (bey)player;
                final SmartMovingOther moving = this.doGetOtherSmartMoving(otherPlayer);
                moving.spawnParticles(minecraft, otherPlayer.u - otherPlayer.r, otherPlayer.w - otherPlayer.t);
                moving.foundAlive = true;
            }
        }
        if (this.otherSmartMovings == null || this.otherSmartMovings.isEmpty()) {
            return;
        }
        final Iterator<Integer> entityIds = this.otherSmartMovings.keySet().iterator();
        while (entityIds.hasNext()) {
            final Integer entityId = entityIds.next();
            final SmartMovingOther moving = this.otherSmartMovings.get(entityId);
            if (moving.foundAlive) {
                moving.foundAlive = false;
            }
            else {
                entityIds.remove();
            }
        }
    }
    
    protected SmartMoving doGetInstance(final uf entityPlayer) {
        if (entityPlayer instanceof bey) {
            return this.doGetOtherSmartMoving(entityPlayer.k);
        }
        if (entityPlayer instanceof IEntityPlayerSP) {
            return ((IEntityPlayerSP)entityPlayer).getMoving();
        }
        return null;
    }
    
    protected SmartMoving doGetOtherSmartMoving(final int entityId) {
        SmartMoving moving = this.tryGetOtherSmartMoving(entityId);
        if (moving == null) {
            final nn entity = atv.w().f.a(entityId);
            if (entity != null && entity instanceof bey) {
                moving = this.addOtherSmartMoving((bey)entity);
            }
        }
        return moving;
    }
    
    protected SmartMovingOther doGetOtherSmartMoving(final bey entity) {
        SmartMovingOther moving = this.tryGetOtherSmartMoving(entity.k);
        if (moving == null) {
            moving = this.addOtherSmartMoving(entity);
        }
        return moving;
    }
    
    protected final SmartMovingOther tryGetOtherSmartMoving(final int entityId) {
        if (this.otherSmartMovings == null) {
            this.otherSmartMovings = new Hashtable<Integer, SmartMovingOther>();
        }
        return this.otherSmartMovings.get(entityId);
    }
    
    protected final SmartMovingOther addOtherSmartMoving(final bey entity) {
        final SmartMovingOther moving = new SmartMovingOther(entity);
        this.otherSmartMovings.put(entity.k, moving);
        return moving;
    }
}
