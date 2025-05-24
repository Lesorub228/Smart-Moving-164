// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render;

public interface IRenderPlayer
{
    void superRenderRenderPlayer(final beu p0, final double p1, final double p2, final double p3, final float p4, final float p5);
    
    void superRenderRotatePlayer(final beu p0, final float p1, final float p2, final float p3);
    
    void superRenderRenderPlayerAt(final beu p0, final double p1, final double p2, final double p3);
    
    void superRenderRenderName(final of p0, final double p1, final double p2, final double p3);
    
    bgl getRenderManager();
    
    IModelPlayer getPlayerModelBipedMain();
    
    IModelPlayer getPlayerModelArmorChestplate();
    
    IModelPlayer getPlayerModelArmor();
    
    IModelPlayer[] getPlayerModels();
}
