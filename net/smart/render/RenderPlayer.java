// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import net.smart.utilities.Reflect;
import net.smart.utilities.Install;

public class RenderPlayer extends bhj implements IRenderPlayer
{
    private bbj g;
    private bbj h;
    private IModelPlayer[] allIModelPlayers;
    private final SmartRenderRender render;
    
    public RenderPlayer() {
        this.render = new SmartRenderRender(this);
    }
    
    public IModelPlayer createModel(final bbj existing, final float f) {
        return new ModelPlayer(f);
    }
    
    public void initialize(final bbj modelBipedMain, final bbj modelArmorChestplate, final bbj modelArmor, final float shadowSize) {
        this.i = (bbo)modelBipedMain;
        this.d = shadowSize;
        Reflect.SetField(bhj.class, this, Install.RenderPlayer_modelBipedMain, modelBipedMain);
        Reflect.SetField(bhj.class, this, Install.RenderPlayer_modelArmorChestplate, this.g = modelArmorChestplate);
        Reflect.SetField(bhj.class, this, Install.RenderPlayer_modelArmor, this.h = modelArmor);
    }
    
    public void a(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        this.render.renderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    public void superRenderPlayer(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        super.a(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    public void a(final uf entityPlayer) {
        this.render.drawFirstPersonHand(entityPlayer);
    }
    
    public void superDrawFirstPersonHand(final uf entityPlayer) {
        super.a(entityPlayer);
    }
    
    protected void a(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        this.render.rotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    public void superRotatePlayer(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        super.a(entityplayer, totalTime, actualRotation, f2);
    }
    
    protected void a(final beu entityplayer, final float f) {
        this.render.renderSpecials(entityplayer, f);
    }
    
    public void superRenderSpecials(final beu entityplayer, final float f) {
        super.a(entityplayer, f);
    }
    
    protected float b(final of entityliving, final float f) {
        this.render.beforeHandleRotationFloat(entityliving, f);
        final float result = super.b(entityliving, f);
        this.render.afterHandleRotationFloat(entityliving, f);
        return result;
    }
    
    public bgl getRenderManager() {
        return this.b;
    }
    
    public bbj getModelBipedMain() {
        return (bbj)this.i;
    }
    
    public bbj getModelArmorChestplate() {
        return this.g;
    }
    
    public bbj getModelArmor() {
        return this.h;
    }
    
    public IModelPlayer getRenderModelBipedMain() {
        return (ModelPlayer)this.getModelBipedMain();
    }
    
    public IModelPlayer getRenderModelArmorChestplate() {
        return (ModelPlayer)this.getModelArmorChestplate();
    }
    
    public IModelPlayer getRenderModelArmor() {
        return (ModelPlayer)this.getModelArmor();
    }
    
    public IModelPlayer[] getRenderModels() {
        if (this.allIModelPlayers == null) {
            this.allIModelPlayers = new IModelPlayer[] { this.getRenderModelBipedMain(), this.getRenderModelArmorChestplate(), this.getRenderModelArmor() };
        }
        return this.allIModelPlayers;
    }
}
