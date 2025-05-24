// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

public interface IRenderPlayer
{
    IModelPlayer createModel(final bbj p0, final float p1);
    
    void initialize(final bbj p0, final bbj p1, final bbj p2, final float p3);
    
    void superRenderPlayer(final beu p0, final double p1, final double p2, final double p3, final float p4, final float p5);
    
    void superDrawFirstPersonHand(final uf p0);
    
    void superRotatePlayer(final beu p0, final float p1, final float p2, final float p3);
    
    void superRenderSpecials(final beu p0, final float p1);
    
    bgl getRenderManager();
    
    bbj getModelBipedMain();
    
    bbj getModelArmorChestplate();
    
    bbj getModelArmor();
    
    IModelPlayer[] getRenderModels();
}
