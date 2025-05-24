// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render.playerapi;

import api.player.model.ModelPlayer;
import api.player.model.ModelPlayerAPI;
import api.player.model.ModelPlayerBaseSorting;
import api.player.render.RenderPlayerAPI;
import api.player.render.RenderPlayerBaseSorting;

public abstract class SmartMoving
{
    public static final String ID = "Smart Moving";
    
    public static void register() {
        final String[] inferiors = { "Smart Render" };
        final RenderPlayerBaseSorting renderSorting = new RenderPlayerBaseSorting();
        renderSorting.setAfterLocalConstructingInferiors(inferiors);
        renderSorting.setOverrideRenderPlayerInferiors(inferiors);
        renderSorting.setOverrideRotatePlayerInferiors(inferiors);
        renderSorting.setOverrideRenderPlayerSleepInferiors(inferiors);
        RenderPlayerAPI.register("Smart Moving", (Class)SmartMovingRenderPlayerBase.class, renderSorting);
        final ModelPlayerBaseSorting modelSorting = new ModelPlayerBaseSorting();
        modelSorting.setAfterLocalConstructingInferiors(inferiors);
        ModelPlayerAPI.register("Smart Moving", (Class)SmartMovingModelPlayerBase.class, modelSorting);
    }
    
    public static SmartMovingRenderPlayerBase getPlayerBase(final bhj renderPlayer) {
        return (SmartMovingRenderPlayerBase)renderPlayer.getRenderPlayerBase("Smart Moving");
    }
    
    public static SmartMovingModelPlayerBase getPlayerBase(final ModelPlayer modelPlayer) {
        return (SmartMovingModelPlayerBase)modelPlayer.getModelPlayerBase("Smart Moving");
    }
}
