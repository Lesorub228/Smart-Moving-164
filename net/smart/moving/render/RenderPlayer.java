// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render;

public class RenderPlayer extends net.smart.render.RenderPlayer implements IRenderPlayer
{
    private IModelPlayer[] allIModelPlayers;
    private final SmartMovingRender render;
    
    public RenderPlayer() {
        this.render = new SmartMovingRender(this);
    }
    
    @Override
    public net.smart.render.IModelPlayer createModel(final bbj existing, final float f) {
        return new ModelPlayer(f);
    }
    
    @Override
    public void a(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        this.render.renderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    @Override
    public void superRenderRenderPlayer(final beu entityplayer, final double d, final double d1, final double d2, final float f, final float renderPartialTicks) {
        super.a(entityplayer, d, d1, d2, f, renderPartialTicks);
    }
    
    @Override
    protected void a(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        this.render.rotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    @Override
    public void superRenderRotatePlayer(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        super.a(entityplayer, totalTime, actualRotation, f2);
    }
    
    protected void a(final beu entityplayer, final double d, final double d1, final double d2) {
        this.render.renderPlayerAt(entityplayer, d, d1, d2);
    }
    
    @Override
    public void superRenderRenderPlayerAt(final beu entityplayer, final double d, final double d1, final double d2) {
        super.a(entityplayer, d, d1, d2);
    }
    
    protected void b(final of par1EntityLiving, final double par2, final double par4, final double par6) {
        this.render.renderName(par1EntityLiving, par2, par4, par6);
    }
    
    @Override
    public void superRenderRenderName(final of par1EntityLiving, final double par2, final double par4, final double par6) {
        super.b(par1EntityLiving, par2, par4, par6);
    }
    
    @Override
    public bgl getRenderManager() {
        return this.b;
    }
    
    @Override
    public IModelPlayer getPlayerModelBipedMain() {
        return (ModelPlayer)super.getModelBipedMain();
    }
    
    @Override
    public IModelPlayer getPlayerModelArmorChestplate() {
        return (ModelPlayer)super.getModelArmorChestplate();
    }
    
    @Override
    public IModelPlayer getPlayerModelArmor() {
        return (ModelPlayer)super.getModelArmor();
    }
    
    @Override
    public IModelPlayer[] getPlayerModels() {
        if (this.allIModelPlayers == null) {
            this.allIModelPlayers = new IModelPlayer[] { this.getPlayerModelBipedMain(), this.getPlayerModelArmorChestplate(), this.getPlayerModelArmor() };
        }
        return this.allIModelPlayers;
    }
}
