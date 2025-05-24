// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import org.lwjgl.opengl.GL11;

public class ModelCapeRenderer extends ModelSpecialRenderer
{
    private final ModelRotationRenderer outer;
    private uf entityplayer;
    private float setFactor;
    
    public ModelCapeRenderer(final bbo modelBase, final int i, final int j, final ModelRotationRenderer baseRenderer, final ModelRotationRenderer outerRenderer) {
        super(modelBase, i, j, baseRenderer);
        this.outer = outerRenderer;
    }
    
    public void beforeRender(final uf entityplayer, final float factor) {
        this.entityplayer = entityplayer;
        this.setFactor = factor;
        super.beforeRender(true);
    }
    
    @Override
    public void preTransform(final float factor, final boolean push) {
        super.preTransform(factor, push);
        final double d = this.entityplayer.bw + (this.entityplayer.bz - this.entityplayer.bw) * this.setFactor - (this.entityplayer.r + (this.entityplayer.u - this.entityplayer.r) * this.setFactor);
        final double d2 = this.entityplayer.bx + (this.entityplayer.bA - this.entityplayer.bx) * this.setFactor - (this.entityplayer.s + (this.entityplayer.v - this.entityplayer.s) * this.setFactor);
        final double d3 = this.entityplayer.by + (this.entityplayer.bB - this.entityplayer.by) * this.setFactor - (this.entityplayer.t + (this.entityplayer.w - this.entityplayer.t) * this.setFactor);
        final float f1 = this.entityplayer.aO + (this.entityplayer.aN - this.entityplayer.aO) * this.setFactor;
        final double d4 = ls.a(f1 * 3.141593f / 180.0f);
        final double d5 = -ls.b(f1 * 3.141593f / 180.0f);
        float f2 = (float)d2 * 10.0f;
        if (f2 < -6.0f) {
            f2 = -6.0f;
        }
        if (f2 > 32.0f) {
            f2 = 32.0f;
        }
        float f3 = (float)(d * d4 + d3 * d5) * 100.0f;
        final float f4 = (float)(d * d5 - d3 * d4) * 100.0f;
        if (f3 < 0.0f) {
            f3 = 0.0f;
        }
        final float f5 = this.entityplayer.bs + (this.entityplayer.bt - this.entityplayer.bs) * this.setFactor;
        f2 += ls.a((this.entityplayer.Q + (this.entityplayer.R - this.entityplayer.Q) * this.setFactor) * 6.0f) * 32.0f * f5;
        final float localAngle = 6.0f + f3 / 2.0f + f2;
        final float localAngleMax = Math.max(70.523f - this.outer.f * 57.295776f, 6.0f);
        final float realLocalAngle = Math.min(localAngle, localAngleMax);
        GL11.glRotatef(realLocalAngle, 1.0f, 0.0f, 0.0f);
        GL11.glRotatef(f4 / 2.0f, 0.0f, 0.0f, 1.0f);
        GL11.glRotatef(-f4 / 2.0f, 0.0f, 1.0f, 0.0f);
        GL11.glRotatef(180.0f, 0.0f, 1.0f, 0.0f);
    }
    
    @Override
    public boolean canBeRandomBoxSource() {
        return false;
    }
}
