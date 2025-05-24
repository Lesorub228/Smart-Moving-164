// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.smart.render.statistics.SmartStatistics;
import net.smart.render.statistics.SmartStatisticsFactory;
import java.util.Map;

public class SmartRenderRender extends SmartRenderContext
{
    public IRenderPlayer irp;
    private static Map<uf, RendererData> previousRendererData;
    private static int previousRendererDataAccessCounter;
    public final SmartRenderModel modelBipedMain;
    
    public SmartRenderRender(final IRenderPlayer irp) {
        this.irp = irp;
        this.modelBipedMain = irp.createModel(irp.getModelBipedMain(), 0.0f).getRenderModel();
        final SmartRenderModel modelArmorChestplate = irp.createModel(irp.getModelArmorChestplate(), 1.0f).getRenderModel();
        final SmartRenderModel modelArmor = irp.createModel(irp.getModelArmor(), 0.5f).getRenderModel();
        irp.initialize(this.modelBipedMain.mp, modelArmorChestplate.mp, modelArmor.mp, 0.5f);
    }
    
    public void renderPlayer(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        final SmartStatistics statistics = SmartStatisticsFactory.getInstance((uf)entityplayer);
        if (statistics != null) {
            final boolean isInventory = d == 0.0 && d1 == 0.0 && d2 == 0.0 && f == 0.0f && renderPartialTicks == 1.0f;
            final boolean isSleeping = entityplayer.bh();
            final float totalVerticalDistance = statistics.getTotalVerticalDistance(renderPartialTicks);
            final float currentVerticalSpeed = statistics.getCurrentVerticalSpeed(renderPartialTicks);
            final float totalDistance = statistics.getTotalDistance(renderPartialTicks);
            final float currentSpeed = statistics.getCurrentSpeed(renderPartialTicks);
            double distance = 0.0;
            double verticalDistance = 0.0;
            double horizontalDistance = 0.0;
            float currentCameraAngle = 0.0f;
            float currentVerticalAngle = 0.0f;
            float currentHorizontalAngle = 0.0f;
            if (!isInventory) {
                final double xDiff = entityplayer.u - entityplayer.r;
                final double yDiff = entityplayer.v - entityplayer.s;
                final double zDiff = entityplayer.w - entityplayer.t;
                verticalDistance = Math.abs(yDiff);
                horizontalDistance = Math.sqrt(xDiff * xDiff + zDiff * zDiff);
                distance = Math.sqrt(horizontalDistance * horizontalDistance + verticalDistance * verticalDistance);
                currentCameraAngle = entityplayer.A / 57.295776f;
                currentVerticalAngle = (float)Math.atan(yDiff / horizontalDistance);
                if (Float.isNaN(currentVerticalAngle)) {
                    currentVerticalAngle = 1.5707964f;
                }
                currentHorizontalAngle = (float)(-Math.atan(xDiff / zDiff));
                if (Float.isNaN(currentHorizontalAngle)) {
                    if (Float.isNaN(statistics.prevHorizontalAngle)) {
                        currentHorizontalAngle = currentCameraAngle;
                    }
                    else {
                        currentHorizontalAngle = statistics.prevHorizontalAngle;
                    }
                }
                else if (zDiff < 0.0) {
                    currentHorizontalAngle += 3.1415927f;
                }
                statistics.prevHorizontalAngle = currentHorizontalAngle;
            }
            final IModelPlayer[] modelPlayers = this.irp.getRenderModels();
            for (int i = 0; i < modelPlayers.length; ++i) {
                final SmartRenderModel modelPlayer = modelPlayers[i].getRenderModel();
                modelPlayer.isInventory = isInventory;
                modelPlayer.totalVerticalDistance = totalVerticalDistance;
                modelPlayer.currentVerticalSpeed = currentVerticalSpeed;
                modelPlayer.totalDistance = totalDistance;
                modelPlayer.currentSpeed = currentSpeed;
                modelPlayer.distance = distance;
                modelPlayer.verticalDistance = verticalDistance;
                modelPlayer.horizontalDistance = horizontalDistance;
                modelPlayer.currentCameraAngle = currentCameraAngle;
                modelPlayer.currentVerticalAngle = currentVerticalAngle;
                modelPlayer.currentHorizontalAngle = currentHorizontalAngle;
                modelPlayer.prevOuterRenderData = getPreviousRendererData((uf)entityplayer);
                modelPlayer.isSleeping = isSleeping;
            }
        }
        this.irp.superRenderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    public void drawFirstPersonHand(final uf entityPlayer) {
        this.modelBipedMain.firstPerson = true;
        this.irp.superDrawFirstPersonHand(entityPlayer);
        this.modelBipedMain.firstPerson = false;
    }
    
    public void rotatePlayer(final beu entityplayer, final float totalTime, float actualRotation, final float f2) {
        final boolean isLocal = entityplayer instanceof bex;
        final boolean isInventory = f2 == 1.0f && isLocal && atv.w().n instanceof axv;
        if (!isInventory) {
            float forwardRotation = entityplayer.C + (entityplayer.A - entityplayer.C) * f2;
            if (entityplayer.bh()) {
                actualRotation = 0.0f;
                forwardRotation = 0.0f;
            }
            final atv minecraft = atv.w();
            float workingAngle;
            if (!isLocal) {
                workingAngle = -entityplayer.A;
                workingAngle += minecraft.i.A;
            }
            else {
                workingAngle = actualRotation - getPreviousRendererData((uf)entityplayer).rotateAngleY * 57.295776f;
            }
            if (minecraft.u.aa == 2 && !minecraft.i.bh()) {
                workingAngle += 180.0f;
            }
            final IModelPlayer[] modelPlayers = this.irp.getRenderModels();
            for (int i = 0; i < modelPlayers.length; ++i) {
                final SmartRenderModel modelPlayer = modelPlayers[i].getRenderModel();
                modelPlayer.actualRotation = actualRotation;
                modelPlayer.forwardRotation = forwardRotation;
                modelPlayer.workingAngle = workingAngle;
            }
            actualRotation = 0.0f;
        }
        this.irp.superRotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    public void renderSpecials(final beu entityplayer, final float f) {
        this.modelBipedMain.bipedEars.beforeRender();
        this.modelBipedMain.bipedCloak.beforeRender((uf)entityplayer, f);
        this.irp.superRenderSpecials(entityplayer, f);
        this.modelBipedMain.bipedCloak.afterRender();
        this.modelBipedMain.bipedEars.afterRender();
    }
    
    public void beforeHandleRotationFloat(final of entityliving, final float f) {
        if (entityliving instanceof uf) {
            final SmartStatistics statistics = SmartStatisticsFactory.getInstance((uf)entityliving);
            if (statistics != null) {
                entityliving.ac += statistics.ticksRiding;
            }
        }
    }
    
    public void afterHandleRotationFloat(final of entityliving, final float f) {
        if (entityliving instanceof uf) {
            final SmartStatistics statistics = SmartStatisticsFactory.getInstance((uf)entityliving);
            if (statistics != null) {
                entityliving.ac -= statistics.ticksRiding;
            }
        }
    }
    
    public static RendererData getPreviousRendererData(final uf entityplayer) {
        if (++SmartRenderRender.previousRendererDataAccessCounter > 1000) {
            final List players = atv.w().f.h;
            final Iterator<uf> iterator = SmartRenderRender.previousRendererData.keySet().iterator();
            while (iterator.hasNext()) {
                if (!players.contains(iterator.next())) {
                    iterator.remove();
                }
            }
            SmartRenderRender.previousRendererDataAccessCounter = 0;
        }
        RendererData result = SmartRenderRender.previousRendererData.get(entityplayer);
        if (result == null) {
            SmartRenderRender.previousRendererData.put(entityplayer, result = new RendererData());
        }
        return result;
    }
    
    static {
        SmartRenderRender.previousRendererData = new HashMap<uf, RendererData>();
        SmartRenderRender.previousRendererDataAccessCounter = 0;
    }
}
