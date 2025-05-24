// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import org.lwjgl.opengl.GL11;

public class ModelSpecialRenderer extends ModelRotationRenderer
{
    public boolean doPopPush;
    
    public ModelSpecialRenderer(final bbo modelBase, final int i, final int j, final ModelRotationRenderer baseRenderer) {
        super(modelBase, i, j, baseRenderer);
        this.ignoreRender = true;
    }
    
    public void beforeRender(final boolean popPush) {
        this.doPopPush = popPush;
        this.ignoreRender = false;
    }
    
    @Override
    public void doRender(final float f, final boolean useParentTransformations) {
        if (this.doPopPush) {
            GL11.glPopMatrix();
            GL11.glPushMatrix();
        }
        super.doRender(f, true);
    }
    
    public void afterRender() {
        this.ignoreRender = true;
        this.doPopPush = false;
    }
}
