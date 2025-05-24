// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import java.util.List;
import java.util.Random;

public class SmartRenderModel extends SmartRenderContext
{
    public IModelPlayer imp;
    public bbj mp;
    public boolean isInventory;
    public int scaleArmType;
    public int scaleLegType;
    public float totalVerticalDistance;
    public float currentVerticalSpeed;
    public float totalDistance;
    public float currentSpeed;
    public double distance;
    public double verticalDistance;
    public double horizontalDistance;
    public float currentCameraAngle;
    public float currentVerticalAngle;
    public float currentHorizontalAngle;
    public float actualRotation;
    public float forwardRotation;
    public float workingAngle;
    public ModelRotationRenderer bipedOuter;
    public ModelRotationRenderer bipedTorso;
    public ModelRotationRenderer bipedBody;
    public ModelRotationRenderer bipedBreast;
    public ModelRotationRenderer bipedNeck;
    public ModelRotationRenderer bipedHead;
    public ModelRotationRenderer bipedHeadwear;
    public ModelRotationRenderer bipedRightShoulder;
    public ModelRotationRenderer bipedRightArm;
    public ModelRotationRenderer bipedLeftShoulder;
    public ModelRotationRenderer bipedLeftArm;
    public ModelRotationRenderer bipedPelvic;
    public ModelRotationRenderer bipedRightLeg;
    public ModelRotationRenderer bipedLeftLeg;
    public ModelEarsRenderer bipedEars;
    public ModelCapeRenderer bipedCloak;
    public boolean disabled;
    public boolean attemptToCallRenderCape;
    public RendererData prevOuterRenderData;
    public boolean isSleeping;
    public boolean firstPerson;
    
    public SmartRenderModel(final float f, final bbj mp, final IModelPlayer imp) {
        this.imp = imp;
        this.mp = mp;
        mp.r.clear();
        (this.bipedOuter = new ModelRotationRenderer((bbo)mp, -1, -1, null)).a(0.0f, 0.0f, 0.0f);
        this.bipedOuter.fadeEnabled = true;
        (this.bipedTorso = new ModelRotationRenderer((bbo)mp, 16, 16, this.bipedOuter)).a(0.0f, 0.0f, 0.0f);
        (this.bipedBody = new ModelRotationRenderer((bbo)mp, 16, 16, this.bipedTorso)).a(-4.0f, 0.0f, -2.0f, 8, 12, 4, f);
        this.bipedBody.a(0.0f, 0.0f, 0.0f);
        (this.bipedBreast = new ModelRotationRenderer((bbo)mp, -1, -1, this.bipedTorso)).a(0.0f, 0.0f, 0.0f);
        (this.bipedNeck = new ModelRotationRenderer((bbo)mp, -1, -1, this.bipedBreast)).a(0.0f, 0.0f, 0.0f);
        (this.bipedCloak = new ModelCapeRenderer((bbo)mp, 0, 0, this.bipedBreast, this.bipedOuter)).a(-5.0f, 0.0f, -1.0f, 10, 16, 1, f);
        this.bipedCloak.a(0.0f, 0.0f, 2.0f);
        (this.bipedHead = new ModelRotationRenderer((bbo)mp, 0, 0, this.bipedNeck)).a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f);
        this.bipedHead.a(0.0f, 0.0f, 0.0f);
        (this.bipedEars = new ModelEarsRenderer((bbo)mp, 24, 0, this.bipedHead)).a(-3.0f, -6.0f, -1.0f, 6, 6, 1, f);
        this.bipedEars.a(0.0f, 0.0f, 0.0f);
        (this.bipedHeadwear = new ModelRotationRenderer((bbo)mp, 32, 0, this.bipedHead)).a(-4.0f, -8.0f, -4.0f, 8, 8, 8, f + 0.5f);
        this.bipedHeadwear.a(0.0f, 0.0f, 0.0f);
        (this.bipedRightShoulder = new ModelRotationRenderer((bbo)mp, 40, 16, this.bipedBreast)).a(-5.0f, 2.0f, 0.0f);
        (this.bipedRightArm = new ModelRotationRenderer((bbo)mp, 40, 16, this.bipedRightShoulder)).a(-3.0f, -2.0f, -2.0f, 4, 12, 4, f);
        this.bipedLeftShoulder = new ModelRotationRenderer((bbo)mp, -1, -1, this.bipedBreast);
        this.bipedLeftShoulder.i = true;
        this.bipedLeftShoulder.a(5.0f, 2.0f, 0.0f);
        this.bipedLeftArm = new ModelRotationRenderer((bbo)mp, 40, 16, this.bipedLeftShoulder);
        this.bipedLeftArm.i = true;
        this.bipedLeftArm.a(-1.0f, -2.0f, -2.0f, 4, 12, 4, f);
        (this.bipedPelvic = new ModelRotationRenderer((bbo)mp, -1, -1, this.bipedTorso)).a(0.0f, 12.0f, 0.0f);
        (this.bipedRightLeg = new ModelRotationRenderer((bbo)mp, 0, 16, this.bipedPelvic)).a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        this.bipedRightLeg.a(-2.0f, 0.0f, 0.0f);
        this.bipedLeftLeg = new ModelRotationRenderer((bbo)mp, 0, 16, this.bipedPelvic);
        this.bipedLeftLeg.i = true;
        this.bipedLeftLeg.a(-2.0f, 0.0f, -2.0f, 4, 12, 4, f);
        this.bipedLeftLeg.a(2.0f, 0.0f, 0.0f);
        imp.initialize(this.bipedBody, this.bipedCloak, this.bipedHead, this.bipedEars, this.bipedHeadwear, this.bipedRightArm, this.bipedLeftArm, this.bipedRightLeg, this.bipedLeftLeg);
    }
    
    public void render(final nn entity, final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        final ModelRotationRenderer bipedBody = this.bipedBody;
        final ModelRotationRenderer bipedHead = this.bipedHead;
        final ModelRotationRenderer bipedHeadwear = this.bipedHeadwear;
        final ModelRotationRenderer bipedRightArm = this.bipedRightArm;
        final ModelRotationRenderer bipedLeftArm = this.bipedLeftArm;
        final ModelRotationRenderer bipedRightLeg = this.bipedRightLeg;
        final ModelRotationRenderer bipedLeftLeg = this.bipedLeftLeg;
        final boolean ignoreRender = true;
        bipedLeftLeg.ignoreRender = ignoreRender;
        bipedRightLeg.ignoreRender = ignoreRender;
        bipedLeftArm.ignoreRender = ignoreRender;
        bipedRightArm.ignoreRender = ignoreRender;
        bipedHeadwear.ignoreRender = ignoreRender;
        bipedHead.ignoreRender = ignoreRender;
        bipedBody.ignoreRender = ignoreRender;
        this.imp.superRender(entity, totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        final ModelRotationRenderer bipedBody2 = this.bipedBody;
        final ModelRotationRenderer bipedHead2 = this.bipedHead;
        final ModelRotationRenderer bipedHeadwear2 = this.bipedHeadwear;
        final ModelRotationRenderer bipedRightArm2 = this.bipedRightArm;
        final ModelRotationRenderer bipedLeftArm2 = this.bipedLeftArm;
        final ModelRotationRenderer bipedRightLeg2 = this.bipedRightLeg;
        final ModelRotationRenderer bipedLeftLeg2 = this.bipedLeftLeg;
        final boolean ignoreRender2 = false;
        bipedLeftLeg2.ignoreRender = ignoreRender2;
        bipedRightLeg2.ignoreRender = ignoreRender2;
        bipedLeftArm2.ignoreRender = ignoreRender2;
        bipedRightArm2.ignoreRender = ignoreRender2;
        bipedHeadwear2.ignoreRender = ignoreRender2;
        bipedHead2.ignoreRender = ignoreRender2;
        bipedBody2.ignoreRender = ignoreRender2;
        this.bipedOuter.a(factor);
        this.bipedOuter.renderIgnoreBase(factor);
        this.bipedTorso.renderIgnoreBase(factor);
        this.bipedBody.renderIgnoreBase(factor);
        this.bipedBreast.renderIgnoreBase(factor);
        this.bipedNeck.renderIgnoreBase(factor);
        this.bipedHead.renderIgnoreBase(factor);
        this.bipedHeadwear.renderIgnoreBase(factor);
        this.bipedRightShoulder.renderIgnoreBase(factor);
        this.bipedRightArm.renderIgnoreBase(factor);
        this.bipedLeftShoulder.renderIgnoreBase(factor);
        this.bipedLeftArm.renderIgnoreBase(factor);
        this.bipedPelvic.renderIgnoreBase(factor);
        this.bipedRightLeg.renderIgnoreBase(factor);
        this.bipedLeftLeg.renderIgnoreBase(factor);
    }
    
    public void setRotationAngles(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor, final nn entity) {
        this.reset();
        if (this.firstPerson || this.isInventory) {
            this.bipedBody.ignoreBase = true;
            this.bipedHead.ignoreBase = true;
            this.bipedHeadwear.ignoreBase = true;
            this.bipedEars.ignoreBase = true;
            this.bipedCloak.ignoreBase = true;
            this.bipedRightArm.ignoreBase = true;
            this.bipedLeftArm.ignoreBase = true;
            this.bipedRightLeg.ignoreBase = true;
            this.bipedLeftLeg.ignoreBase = true;
            this.bipedBody.forceRender = this.firstPerson;
            this.bipedHead.forceRender = this.firstPerson;
            this.bipedHeadwear.forceRender = this.firstPerson;
            this.bipedEars.forceRender = this.firstPerson;
            this.bipedCloak.forceRender = this.firstPerson;
            this.bipedRightArm.forceRender = this.firstPerson;
            this.bipedLeftArm.forceRender = this.firstPerson;
            this.bipedRightLeg.forceRender = this.firstPerson;
            this.bipedLeftLeg.forceRender = this.firstPerson;
            this.bipedRightArm.a(-5.0f, 2.0f, 0.0f);
            this.bipedLeftArm.a(5.0f, 2.0f, 0.0f);
            this.bipedRightLeg.a(-2.0f, 12.0f, 0.0f);
            this.bipedLeftLeg.a(2.0f, 12.0f, 0.0f);
            this.imp.superSetRotationAngles(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor, entity);
            return;
        }
        if (this.isSleeping) {
            this.prevOuterRenderData.rotateAngleX = 0.0f;
            this.prevOuterRenderData.rotateAngleY = 0.0f;
            this.prevOuterRenderData.rotateAngleZ = 0.0f;
        }
        this.bipedOuter.previous = this.prevOuterRenderData;
        this.bipedOuter.g = this.actualRotation / 57.295776f;
        this.bipedOuter.fadeRotateAngleY = !(entity.o instanceof ry);
        this.imp.animateHeadRotation(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        if (this.isSleeping) {
            this.imp.animateSleeping(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        this.imp.animateArmSwinging(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        if (this.mp.q) {
            this.imp.animateRiding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        if (this.mp.l != 0) {
            this.imp.animateLeftArmItemHolding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        if (this.mp.m != 0) {
            this.imp.animateRightArmItemHolding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        if (this.mp.p > -9990.0f) {
            this.imp.animateWorkingBody(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
            this.imp.animateWorkingArms(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        if (this.mp.n) {
            this.imp.animateSneaking(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        this.imp.animateArms(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        if (this.mp.o) {
            this.imp.animateBowAiming(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        if (this.bipedOuter.previous != null && !this.bipedOuter.fadeRotateAngleX) {
            this.bipedOuter.previous.rotateAngleX = this.bipedOuter.f;
        }
        if (this.bipedOuter.previous != null && !this.bipedOuter.fadeRotateAngleY) {
            this.bipedOuter.previous.rotateAngleY = this.bipedOuter.g;
        }
        this.bipedOuter.fadeIntermediate(totalTime);
        this.bipedOuter.fadeStore(totalTime);
        this.bipedCloak.ignoreBase = false;
        this.bipedCloak.f = 0.09817477f;
    }
    
    public void animateHeadRotation(final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset) {
        this.bipedNeck.ignoreBase = true;
        this.bipedHead.g = (this.actualRotation + viewHorizontalAngelOffset) / 57.295776f;
        this.bipedHead.f = viewVerticalAngelOffset / 57.295776f;
    }
    
    public void animateSleeping() {
        this.bipedNeck.ignoreBase = false;
        this.bipedHead.g = 0.0f;
        this.bipedHead.f = 0.7853982f;
        this.bipedTorso.e = -17.0f;
    }
    
    public void animateArmSwinging(final float totalHorizontalDistance, final float currentHorizontalSpeed) {
        this.bipedRightArm.f = ls.b(totalHorizontalDistance * 0.6662f + 3.1415927f) * 2.0f * currentHorizontalSpeed * 0.5f;
        this.bipedLeftArm.f = ls.b(totalHorizontalDistance * 0.6662f) * 2.0f * currentHorizontalSpeed * 0.5f;
        this.bipedRightLeg.f = ls.b(totalHorizontalDistance * 0.6662f) * 1.4f * currentHorizontalSpeed;
        this.bipedLeftLeg.f = ls.b(totalHorizontalDistance * 0.6662f + 3.1415927f) * 1.4f * currentHorizontalSpeed;
    }
    
    public void animateRiding() {
        final ModelRotationRenderer bipedRightArm = this.bipedRightArm;
        bipedRightArm.f -= 0.6283185f;
        final ModelRotationRenderer bipedLeftArm = this.bipedLeftArm;
        bipedLeftArm.f -= 0.6283185f;
        this.bipedRightLeg.f = -1.256637f;
        this.bipedLeftLeg.f = -1.256637f;
        this.bipedRightLeg.g = 0.3141593f;
        this.bipedLeftLeg.g = -0.3141593f;
    }
    
    public void animateLeftArmItemHolding() {
        this.bipedLeftArm.f = this.bipedLeftArm.f * 0.5f - 0.3141593f * this.mp.l;
    }
    
    public void animateRightArmItemHolding() {
        this.bipedRightArm.f = this.bipedRightArm.f * 0.5f - 0.3141593f * this.mp.m;
    }
    
    public void animateWorkingBody() {
        final float angle = ls.a(ls.c(this.mp.p) * 6.2831855f) * 0.2f;
        final ModelRotationRenderer bipedBreast = this.bipedBreast;
        final ModelRotationRenderer bipedBody = this.bipedBody;
        final float n = bipedBody.g + angle;
        bipedBody.g = n;
        bipedBreast.g = n;
        final ModelRotationRenderer bipedBreast2 = this.bipedBreast;
        final ModelRotationRenderer bipedBody2 = this.bipedBody;
        final int yxz = ModelRotationRenderer.YXZ;
        bipedBody2.rotationOrder = yxz;
        bipedBreast2.rotationOrder = yxz;
        final ModelRotationRenderer bipedLeftArm = this.bipedLeftArm;
        bipedLeftArm.f += angle;
    }
    
    public void animateWorkingArms() {
        float f6 = 1.0f - this.mp.p;
        f6 = 1.0f - f6 * f6 * f6;
        final float f7 = ls.a(f6 * 3.1415927f);
        final float f8 = ls.a(this.mp.p * 3.1415927f) * -(this.bipedHead.f - 0.7f) * 0.75f;
        final ModelRotationRenderer bipedRightArm = this.bipedRightArm;
        bipedRightArm.f -= (float)(f7 * 1.2 + f8);
        final ModelRotationRenderer bipedRightArm2 = this.bipedRightArm;
        bipedRightArm2.g += ls.a(ls.c(this.mp.p) * 6.2831855f) * 0.4f;
        final ModelRotationRenderer bipedRightArm3 = this.bipedRightArm;
        bipedRightArm3.h -= ls.a(this.mp.p * 3.1415927f) * 0.4f;
    }
    
    public void animateSneaking() {
        final ModelRotationRenderer bipedTorso = this.bipedTorso;
        bipedTorso.f += 0.5f;
        final ModelRotationRenderer bipedRightLeg = this.bipedRightLeg;
        bipedRightLeg.f -= 0.5f;
        final ModelRotationRenderer bipedLeftLeg = this.bipedLeftLeg;
        bipedLeftLeg.f -= 0.5f;
        final ModelRotationRenderer bipedRightArm = this.bipedRightArm;
        bipedRightArm.f -= 0.1f;
        final ModelRotationRenderer bipedLeftArm = this.bipedLeftArm;
        bipedLeftArm.f -= 0.1f;
        this.bipedPelvic.p = -0.137f;
        this.bipedPelvic.q = -0.051f;
        this.bipedBreast.p = -0.014f;
        this.bipedBreast.q = -0.057f;
        this.bipedNeck.p = 0.0621f;
    }
    
    public void animateArms(final float totalTime) {
        final ModelRotationRenderer bipedRightArm = this.bipedRightArm;
        bipedRightArm.h += ls.b(totalTime * 0.09f) * 0.05f + 0.05f;
        final ModelRotationRenderer bipedLeftArm = this.bipedLeftArm;
        bipedLeftArm.h -= ls.b(totalTime * 0.09f) * 0.05f + 0.05f;
        final ModelRotationRenderer bipedRightArm2 = this.bipedRightArm;
        bipedRightArm2.f += ls.a(totalTime * 0.067f) * 0.05f;
        final ModelRotationRenderer bipedLeftArm2 = this.bipedLeftArm;
        bipedLeftArm2.f -= ls.a(totalTime * 0.067f) * 0.05f;
    }
    
    public void animateBowAiming(final float totalTime) {
        this.bipedRightArm.h = 0.0f;
        this.bipedLeftArm.h = 0.0f;
        this.bipedRightArm.g = -0.1f + this.bipedHead.g - this.bipedOuter.g;
        this.bipedLeftArm.g = 0.1f + this.bipedHead.g + 0.4f - this.bipedOuter.g;
        this.bipedRightArm.f = -1.570796f + this.bipedHead.f;
        this.bipedLeftArm.f = -1.570796f + this.bipedHead.f;
        final ModelRotationRenderer bipedRightArm = this.bipedRightArm;
        bipedRightArm.h += ls.b(totalTime * 0.09f) * 0.05f + 0.05f;
        final ModelRotationRenderer bipedLeftArm = this.bipedLeftArm;
        bipedLeftArm.h -= ls.b(totalTime * 0.09f) * 0.05f + 0.05f;
        final ModelRotationRenderer bipedRightArm2 = this.bipedRightArm;
        bipedRightArm2.f += ls.a(totalTime * 0.067f) * 0.05f;
        final ModelRotationRenderer bipedLeftArm2 = this.bipedLeftArm;
        bipedLeftArm2.f -= ls.a(totalTime * 0.067f) * 0.05f;
    }
    
    public void reset() {
        this.bipedOuter.reset();
        this.bipedTorso.reset();
        this.bipedBody.reset();
        this.bipedBreast.reset();
        this.bipedNeck.reset();
        this.bipedHead.reset();
        this.bipedHeadwear.reset();
        this.bipedEars.reset();
        this.bipedCloak.reset();
        this.bipedRightShoulder.reset();
        this.bipedRightArm.reset();
        this.bipedLeftShoulder.reset();
        this.bipedLeftArm.reset();
        this.bipedPelvic.reset();
        this.bipedRightLeg.reset();
        this.bipedLeftLeg.reset();
        this.bipedRightShoulder.a(-5.0f, 2.0f, 0.0f);
        this.bipedLeftShoulder.a(5.0f, 2.0f, 0.0f);
        this.bipedPelvic.a(0.0f, 12.0f, 0.0f);
        this.bipedRightLeg.a(-2.0f, 0.0f, 0.0f);
        this.bipedLeftLeg.a(2.0f, 0.0f, 0.0f);
        this.bipedCloak.a(0.0f, 0.0f, 2.0f);
    }
    
    public void renderCloak(final float f) {
        this.attemptToCallRenderCape = true;
        if (!this.disabled) {
            this.imp.superRenderCloak(f);
        }
    }
    
    public bcu getRandomBox(final Random par1Random) {
        final List boxList = this.mp.r;
        final int size = boxList.size();
        int renderersWithBoxes = 0;
        for (int i = 0; i < size; ++i) {
            final bcu renderer = boxList.get(i);
            if (canBeRandomBoxSource(renderer)) {
                ++renderersWithBoxes;
            }
        }
        if (renderersWithBoxes != 0) {
            final int random = par1Random.nextInt(renderersWithBoxes);
            renderersWithBoxes = -1;
            for (int j = 0; j < size; ++j) {
                final bcu renderer2 = boxList.get(j);
                if (canBeRandomBoxSource(renderer2)) {
                    ++renderersWithBoxes;
                }
                if (renderersWithBoxes == random) {
                    return renderer2;
                }
            }
        }
        return null;
    }
    
    private static boolean canBeRandomBoxSource(final bcu renderer) {
        return renderer.l != null && renderer.l.size() > 0 && (!(renderer instanceof ModelRotationRenderer) || ((ModelRotationRenderer)renderer).canBeRandomBoxSource());
    }
}
