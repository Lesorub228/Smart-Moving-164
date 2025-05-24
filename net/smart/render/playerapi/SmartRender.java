// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.playerapi;

import api.player.model.ModelPlayer;
import api.player.model.ModelPlayerAPI;
import api.player.render.RenderPlayerAPI;

public abstract class SmartRender
{
    public static final String ID = "Smart Render";
    
    public static void register() {
        RenderPlayerAPI.register("Smart Render", (Class)SmartRenderRenderPlayerBase.class);
        ModelPlayerAPI.register("Smart Render", (Class)SmartRenderModelPlayerBase.class);
    }
    
    public static SmartRenderRenderPlayerBase getPlayerBase(final bhj renderPlayer) {
        return (SmartRenderRenderPlayerBase)renderPlayer.getRenderPlayerBase("Smart Render");
    }
    
    public static SmartRenderModelPlayerBase getPlayerBase(final ModelPlayer renderPlayer) {
        return (SmartRenderModelPlayerBase)renderPlayer.getModelPlayerBase("Smart Render");
    }
}
