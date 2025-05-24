// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.playerapi;

import api.player.render.RenderPlayerAPI;
import net.smart.render.SmartRenderRender;
import net.smart.render.IModelPlayer;
import api.player.model.ModelPlayer;
import net.smart.render.IRenderPlayer;
import api.player.render.RenderPlayerBase;

public class SmartRenderRenderPlayerBase extends RenderPlayerBase implements IRenderPlayer
{
    private ModelPlayer[] allModelPlayers;
    private IModelPlayer[] allIModelPlayers;
    private SmartRenderRender render;
    
    public SmartRenderRenderPlayerBase(final RenderPlayerAPI renderPlayerAPI) {
        super(renderPlayerAPI);
    }
    
    public void afterLocalConstructing() {
        this.render = new SmartRenderRender(this);
    }
    
    public IModelPlayer createModel(final bbj existing, final float f) {
        return SmartRender.getPlayerBase((ModelPlayer)existing);
    }
    
    public void initialize(final bbj modelBipedMain, final bbj modelArmorChestplate, final bbj modelArmor, final float shadowSize) {
        this.renderPlayer.setMainModelField((bbo)modelBipedMain);
        this.renderPlayer.setShadowSizeField(0.5f);
        this.renderPlayer.setModelBipedMainField(modelBipedMain);
        this.renderPlayer.setModelArmorChestplateField(modelArmorChestplate);
        this.renderPlayer.setModelArmorField(modelArmor);
    }
    
    public void renderPlayer(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        this.render.renderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    public void superRenderPlayer(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        super.renderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    public void renderFirstPersonArm(final uf entityPlayer) {
        this.render.drawFirstPersonHand(entityPlayer);
    }
    
    public void superDrawFirstPersonHand(final uf entityPlayer) {
        super.renderFirstPersonArm(entityPlayer);
    }
    
    public void rotatePlayer(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        this.render.rotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    public void superRotatePlayer(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        super.rotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    public void renderSpecials(final beu entityplayer, final float f) {
        this.render.renderSpecials(entityplayer, f);
    }
    
    public void superRenderSpecials(final beu entityplayer, final float f) {
        super.renderSpecials(entityplayer, f);
    }
    
    public void beforeHandleRotationFloat(final of entityliving, final float f) {
        this.render.beforeHandleRotationFloat(entityliving, f);
    }
    
    public void afterHandleRotationFloat(final of entityliving, final float f) {
        this.render.afterHandleRotationFloat(entityliving, f);
    }
    
    public bgl getRenderManager() {
        return this.renderPlayer.getRenderManagerField();
    }
    
    public bbj getModelBipedMain() {
        return this.renderPlayer.getModelBipedMainField();
    }
    
    public bbj getModelArmorChestplate() {
        return this.renderPlayer.getModelArmorChestplateField();
    }
    
    public bbj getModelArmor() {
        return this.renderPlayer.getModelArmorField();
    }
    
    public IModelPlayer[] getRenderModels() {
        final ModelPlayer[] modelPlayers = ModelPlayer.getAllInstances();
        if (this.allModelPlayers != null && (this.allModelPlayers == modelPlayers || (modelPlayers.length == 0 && this.allModelPlayers.length == 0))) {
            return this.allIModelPlayers;
        }
        this.allModelPlayers = modelPlayers;
        this.allIModelPlayers = new IModelPlayer[modelPlayers.length];
        for (int i = 0; i < this.allIModelPlayers.length; ++i) {
            this.allIModelPlayers[i] = SmartRender.getPlayerBase(this.allModelPlayers[i]);
        }
        return this.allIModelPlayers;
    }
}
