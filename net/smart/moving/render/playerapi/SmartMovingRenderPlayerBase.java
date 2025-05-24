// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render.playerapi;

import api.player.render.RenderPlayerAPI;
import net.smart.moving.render.SmartMovingRender;
import net.smart.moving.render.IModelPlayer;
import api.player.model.ModelPlayer;
import net.smart.moving.render.IRenderPlayer;
import api.player.render.RenderPlayerBase;

public class SmartMovingRenderPlayerBase extends RenderPlayerBase implements IRenderPlayer
{
    private ModelPlayer[] allModelPlayers;
    private IModelPlayer[] allIModelPlayers;
    private SmartMovingRender render;
    
    public SmartMovingRenderPlayerBase(final RenderPlayerAPI renderPlayerAPI) {
        super(renderPlayerAPI);
    }
    
    public void afterLocalConstructing() {
        this.render = new SmartMovingRender(this);
    }
    
    public void renderPlayer(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        this.render.renderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    public void superRenderRenderPlayer(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        super.renderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    public void superDrawFirstPersonHand(final uf entityPlayer) {
        super.renderFirstPersonArm(entityPlayer);
    }
    
    public void rotatePlayer(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        this.render.rotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    public void superRenderRotatePlayer(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        super.rotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    public void renderPlayerSleep(final beu entityplayer, final double d, final double d1, final double d2) {
        this.render.renderPlayerAt(entityplayer, d, d1, d2);
    }
    
    public void superRenderRenderPlayerAt(final beu entityplayer, final double d, final double d1, final double d2) {
        super.renderPlayerSleep(entityplayer, d, d1, d2);
    }
    
    public void passSpecialRender(final of entityliving, final double d, final double d1, final double d2) {
        this.render.renderName(entityliving, d, d1, d2);
    }
    
    public void superRenderRenderName(final of entityplayer, final double d, final double d1, final double d2) {
        super.passSpecialRender(entityplayer, d, d1, d2);
    }
    
    public void superRenderSpecials(final beu entityplayer, final float f) {
        super.renderSpecials(entityplayer, f);
    }
    
    public bgl getRenderManager() {
        return this.renderPlayer.getRenderManagerField();
    }
    
    public boolean isRenderedWithBodyTopAlwaysInAccelerateDirection() {
        return this.render.modelBipedMain.isFlying || this.render.modelBipedMain.isSwim || this.render.modelBipedMain.isDive || this.render.modelBipedMain.isHeadJump;
    }
    
    public IModelPlayer getPlayerModelArmor() {
        return SmartMoving.getPlayerBase((ModelPlayer)this.renderPlayer.getModelArmorField());
    }
    
    public IModelPlayer getPlayerModelArmorChestplate() {
        return SmartMoving.getPlayerBase((ModelPlayer)this.renderPlayer.getModelArmorChestplateField());
    }
    
    public IModelPlayer getPlayerModelBipedMain() {
        return SmartMoving.getPlayerBase((ModelPlayer)this.renderPlayer.getModelBipedMainField());
    }
    
    public IModelPlayer[] getPlayerModels() {
        final ModelPlayer[] modelPlayers = ModelPlayer.getAllInstances();
        if (this.allModelPlayers != null && (this.allModelPlayers == modelPlayers || (modelPlayers.length == 0 && this.allModelPlayers.length == 0))) {
            return this.allIModelPlayers;
        }
        this.allModelPlayers = modelPlayers;
        this.allIModelPlayers = new IModelPlayer[modelPlayers.length];
        for (int i = 0; i < this.allIModelPlayers.length; ++i) {
            this.allIModelPlayers[i] = SmartMoving.getPlayerBase(this.allModelPlayers[i]);
        }
        return this.allIModelPlayers;
    }
}
