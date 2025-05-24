// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import org.lwjgl.opengl.GL11;

public class ModelEarsRenderer extends ModelSpecialRenderer
{
    private int _i;
    
    public ModelEarsRenderer(final bbo modelBase, final int i, final int j, final ModelRotationRenderer baseRenderer) {
        super(modelBase, i, j, baseRenderer);
        this._i = 0;
    }
    
    public void beforeRender() {
        super.beforeRender(true);
    }
    
    @Override
    public void doRender(final float f, final boolean useParentTransformations) {
        this.reset();
        super.doRender(f, useParentTransformations);
    }
    
    @Override
    public void preTransform(final float factor, final boolean push) {
        super.preTransform(factor, push);
        final int i = this._i++ % 2;
        GL11.glTranslatef(0.375f * (i * 2 - 1), 0.0f, 0.0f);
        GL11.glTranslatef(0.0f, -0.375f, 0.0f);
        GL11.glScalef(1.333333f, 1.333333f, 1.333333f);
    }
    
    @Override
    public boolean canBeRandomBoxSource() {
        return false;
    }
}
