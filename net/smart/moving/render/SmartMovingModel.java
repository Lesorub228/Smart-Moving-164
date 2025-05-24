// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render;

import net.smart.render.ModelRotationRenderer;
import net.smart.render.SmartRenderModel;

public class SmartMovingModel extends SmartRenderContext
{
    public IModelPlayer imp;
    public bbj mp;
    public SmartRenderModel md;
    public boolean isStandard;
    public boolean isClimb;
    public boolean isClimbJump;
    public int feetClimbType;
    public int handsClimbType;
    public boolean isHandsVineClimbing;
    public boolean isFeetVineClimbing;
    public boolean isCeilingClimb;
    public boolean isSwim;
    public boolean isDive;
    public boolean isCrawl;
    public boolean isCrawlClimb;
    public boolean isJump;
    public boolean isHeadJump;
    public boolean isFlying;
    public boolean isSlide;
    public boolean isLevitate;
    public boolean isFalling;
    public boolean isGenericSneaking;
    public boolean isAngleJumping;
    public int angleJumpType;
    public boolean isRopeSliding;
    public float currentHorizontalSpeedFlattened;
    public float smallOverGroundHeight;
    public int overGroundBlockId;
    public int scaleArmType;
    public int scaleLegType;
    
    public SmartMovingModel(final float f, final net.smart.render.IModelPlayer md, final IModelPlayer imp) {
        this.imp = imp;
        this.md = md.getRenderModel();
        this.mp = this.md.mp;
    }
    
    private void setRotationAngles(final float totalHorizontalDistance, float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        final float FrequenceFactor = 0.6662f;
        this.isStandard = false;
        final float currentCameraAngle = this.md.currentCameraAngle;
        final float currentHorizontalAngle = this.md.currentHorizontalAngle;
        final float currentVerticalAngle = this.md.currentVerticalAngle;
        final float forwardRotation = this.md.forwardRotation;
        final float currentVerticalSpeed = this.md.currentVerticalSpeed;
        final float totalVerticalDistance = this.md.totalVerticalDistance;
        final float totalDistance = this.md.totalDistance;
        final double horizontalDistance = this.md.horizontalDistance;
        final float currentSpeed = this.md.currentSpeed;
        if (!Float.isNaN(this.currentHorizontalSpeedFlattened)) {
            currentHorizontalSpeed = this.currentHorizontalSpeedFlattened;
        }
        final ModelRotationRenderer bipedOuter = this.md.bipedOuter;
        final ModelRotationRenderer bipedTorso = this.md.bipedTorso;
        final ModelRotationRenderer bipedBody = this.md.bipedBody;
        final ModelRotationRenderer bipedBreast = this.md.bipedBreast;
        final ModelRotationRenderer bipedHead = this.md.bipedHead;
        final ModelRotationRenderer bipedRightShoulder = this.md.bipedRightShoulder;
        final ModelRotationRenderer bipedRightArm = this.md.bipedRightArm;
        final ModelRotationRenderer bipedLeftShoulder = this.md.bipedLeftShoulder;
        final ModelRotationRenderer bipedLeftArm = this.md.bipedLeftArm;
        final ModelRotationRenderer bipedPelvic = this.md.bipedPelvic;
        final ModelRotationRenderer bipedRightLeg = this.md.bipedRightLeg;
        final ModelRotationRenderer bipedLeftLeg = this.md.bipedLeftLeg;
        if (this.isRopeSliding) {
            final float time = totalTime * 0.15f;
            bipedHead.h = Between(-0.3926991f, 0.3926991f, Normalize(currentCameraAngle - currentHorizontalAngle));
            bipedHead.f = 0.7853982f;
            bipedHead.d = 2.0f;
            bipedOuter.fadeRotateAngleY = false;
            bipedOuter.g = currentHorizontalAngle;
            bipedTorso.f = 0.3926991f + 0.09817477f * ls.b(time);
            final ModelRotationRenderer modelRotationRenderer = bipedLeftArm;
            final ModelRotationRenderer modelRotationRenderer2 = bipedRightArm;
            final float n = 3.1415927f - bipedTorso.f;
            modelRotationRenderer2.f = n;
            modelRotationRenderer.f = n;
            bipedRightArm.h = 0.5890486f;
            bipedLeftArm.h = -0.5890486f;
            final ModelRotationRenderer modelRotationRenderer3 = bipedRightArm;
            final ModelRotationRenderer modelRotationRenderer4 = bipedLeftArm;
            final float n2 = -2.0f;
            modelRotationRenderer4.d = n2;
            modelRotationRenderer3.d = n2;
            bipedPelvic.f = bipedTorso.f;
            bipedLeftLeg.h = -0.19634955f;
            bipedRightLeg.h = 0.19634955f;
            bipedLeftLeg.f = 0.09817477f * ls.b(time + 1.5707964f);
            bipedRightLeg.f = 0.09817477f * ls.b(time - 1.5707964f);
        }
        else if (this.isClimb || this.isCrawlClimb) {
            bipedOuter.g = forwardRotation / 57.295776f;
            bipedHead.g = 0.0f;
            bipedHead.f = viewVerticalAngelOffset / 57.295776f;
            bipedLeftLeg.rotationOrder = ModelRotationRenderer.YZX;
            bipedRightLeg.rotationOrder = ModelRotationRenderer.YZX;
            int handsClimbType = this.handsClimbType;
            if (this.isHandsVineClimbing && handsClimbType == 2) {
                handsClimbType = 1;
            }
            final float verticalSpeed = Math.min(0.5f, currentVerticalSpeed);
            final float horizontalSpeed = Math.min(0.5f, currentHorizontalSpeed);
            float handsFrequenceSideFactor = 0.0f;
            float handsDistanceSideFactor = 0.0f;
            float handsDistanceSideOffset = 0.0f;
            float handsFrequenceUpFactor = 0.0f;
            float handsDistanceUpFactor = 0.0f;
            float handsDistanceUpOffset = 0.0f;
            switch (handsClimbType) {
                case 2: {
                    handsFrequenceSideFactor = 0.6662f;
                    handsDistanceSideFactor = 1.0f;
                    handsDistanceSideOffset = 0.0f;
                    handsFrequenceUpFactor = 0.6662f;
                    handsDistanceUpFactor = 2.0f;
                    handsDistanceUpOffset = -1.5707964f;
                    break;
                }
                case 1: {
                    handsFrequenceSideFactor = 0.6662f;
                    handsDistanceSideFactor = 1.0f;
                    handsDistanceSideOffset = 0.0f;
                    handsFrequenceUpFactor = 0.6662f;
                    handsDistanceUpFactor = 2.0f;
                    handsDistanceUpOffset = -2.5f;
                    break;
                }
                default: {
                    handsFrequenceSideFactor = 0.6662f;
                    handsDistanceSideFactor = 1.0f;
                    handsDistanceSideOffset = 0.0f;
                    handsFrequenceUpFactor = 0.6662f;
                    handsDistanceUpFactor = 0.0f;
                    handsDistanceUpOffset = -0.5f;
                    break;
                }
            }
            float feetFrequenceUpFactor = 0.0f;
            float feetDistanceUpFactor = 0.0f;
            float feetDistanceUpOffset = 0.0f;
            float feetFrequenceSideFactor = 0.0f;
            float feetDistanceSideFactor = 0.0f;
            float feetDistanceSideOffset = 0.0f;
            switch (this.feetClimbType) {
                case 1: {
                    feetFrequenceUpFactor = 0.6662f;
                    feetDistanceUpFactor = 0.3f / verticalSpeed;
                    feetDistanceUpOffset = -0.3f;
                    feetFrequenceSideFactor = 0.6662f;
                    feetDistanceSideFactor = 0.5f;
                    feetDistanceSideOffset = 0.0f;
                    break;
                }
                default: {
                    feetFrequenceUpFactor = 0.6662f;
                    feetDistanceUpFactor = 0.0f;
                    feetDistanceUpOffset = 0.0f;
                    feetFrequenceSideFactor = 0.6662f;
                    feetDistanceSideFactor = 0.0f;
                    feetDistanceSideOffset = 0.0f;
                    break;
                }
            }
            bipedRightArm.f = ls.b(totalVerticalDistance * handsFrequenceUpFactor + 3.1415927f) * verticalSpeed * handsDistanceUpFactor + handsDistanceUpOffset;
            bipedLeftArm.f = ls.b(totalVerticalDistance * handsFrequenceUpFactor) * verticalSpeed * handsDistanceUpFactor + handsDistanceUpOffset;
            bipedRightArm.g = ls.b(totalHorizontalDistance * handsFrequenceSideFactor + 1.5707964f) * horizontalSpeed * handsDistanceSideFactor + handsDistanceSideOffset;
            bipedLeftArm.g = ls.b(totalHorizontalDistance * handsFrequenceSideFactor) * horizontalSpeed * handsDistanceSideFactor + handsDistanceSideOffset;
            if (this.isHandsVineClimbing) {
                final ModelRotationRenderer modelRotationRenderer5 = bipedLeftArm;
                modelRotationRenderer5.g *= 1.0f + handsFrequenceSideFactor;
                final ModelRotationRenderer modelRotationRenderer6 = bipedRightArm;
                modelRotationRenderer6.g *= 1.0f + handsFrequenceSideFactor;
                final ModelRotationRenderer modelRotationRenderer7 = bipedLeftArm;
                modelRotationRenderer7.g += 0.7853982f;
                final ModelRotationRenderer modelRotationRenderer8 = bipedRightArm;
                modelRotationRenderer8.g -= 0.7853982f;
                this.setArmScales(Math.abs(ls.b(bipedRightArm.f)), Math.abs(ls.b(bipedLeftArm.f)));
            }
            if (!this.isFeetVineClimbing) {
                bipedRightLeg.f = ls.b(totalVerticalDistance * feetFrequenceUpFactor) * feetDistanceUpFactor * verticalSpeed + feetDistanceUpOffset;
                bipedLeftLeg.f = ls.b(totalVerticalDistance * feetFrequenceUpFactor + 3.1415927f) * feetDistanceUpFactor * verticalSpeed + feetDistanceUpOffset;
            }
            bipedRightLeg.h = -(ls.b(totalHorizontalDistance * feetFrequenceSideFactor) - 1.0f) * horizontalSpeed * feetDistanceSideFactor + feetDistanceSideOffset;
            bipedLeftLeg.h = -(ls.b(totalHorizontalDistance * feetFrequenceSideFactor + 1.5707964f) + 1.0f) * horizontalSpeed * feetDistanceSideFactor + feetDistanceSideOffset;
            if (this.isFeetVineClimbing) {
                final float total = (ls.b(totalDistance + 3.1415927f) + 1.0f) * 0.19634955f + 0.3926991f;
                bipedRightLeg.f = -total;
                bipedLeftLeg.f = -total;
                final float difference = Math.max(0.0f, ls.b(totalDistance - 1.5707964f)) * 0.09817477f;
                final ModelRotationRenderer modelRotationRenderer9 = bipedLeftLeg;
                modelRotationRenderer9.h += -difference;
                final ModelRotationRenderer modelRotationRenderer10 = bipedRightLeg;
                modelRotationRenderer10.h += difference;
                this.setLegScales(Math.abs(ls.b(bipedRightLeg.f)), Math.abs(ls.b(bipedLeftLeg.f)));
            }
            if (this.isCrawlClimb) {
                final float height = this.smallOverGroundHeight + 0.25f;
                final float bodyLength = 0.7f;
                final float legLength = 0.55f;
                float bodyAngleX;
                float legAngleX;
                float legAngleZ;
                if (height < bodyLength) {
                    bodyAngleX = Math.max(0.0f, (float)Math.acos(height / bodyLength));
                    legAngleX = 1.5707964f - bodyAngleX;
                    legAngleZ = 0.19634955f;
                }
                else if (height < bodyLength + legLength) {
                    bodyAngleX = 0.0f;
                    legAngleX = Math.max(0.0f, (float)Math.acos((height - bodyLength) / legLength));
                    legAngleZ = 0.19634955f * (legAngleX / 1.537f);
                }
                else {
                    bodyAngleX = 0.0f;
                    legAngleX = 0.0f;
                    legAngleZ = 0.0f;
                }
                bipedTorso.f = bodyAngleX;
                bipedRightShoulder.f = -bodyAngleX;
                bipedLeftShoulder.f = -bodyAngleX;
                bipedHead.f = -bodyAngleX;
                bipedRightLeg.f = legAngleX;
                bipedLeftLeg.f = legAngleX;
                bipedRightLeg.h = legAngleZ;
                bipedLeftLeg.h = -legAngleZ;
            }
            if (handsClimbType == 0 && this.feetClimbType != 0) {
                bipedTorso.f = 0.5f;
                final ModelRotationRenderer modelRotationRenderer11 = bipedHead;
                modelRotationRenderer11.f -= 0.5f;
                final ModelRotationRenderer modelRotationRenderer12 = bipedPelvic;
                modelRotationRenderer12.f -= 0.5f;
                bipedTorso.e = -6.0f;
            }
        }
        else if (this.isClimbJump) {
            bipedRightArm.f = 3.5342917f;
            bipedLeftArm.f = 3.5342917f;
            bipedRightArm.h = -0.19634955f;
            bipedLeftArm.h = 0.19634955f;
        }
        else if (this.isCeilingClimb) {
            final float distance = totalHorizontalDistance * 0.7f;
            final float walkFactor = Factor(currentHorizontalSpeed, 0.0f, 0.12951545f);
            final float standFactor = Factor(currentHorizontalSpeed, 0.12951545f, 0.0f);
            final float horizontalAngle = (horizontalDistance < 0.014999999664723873) ? currentCameraAngle : currentHorizontalAngle;
            bipedLeftArm.f = (ls.b(distance) * 0.52f + 3.1415927f) * walkFactor + 3.1415927f * standFactor;
            bipedRightArm.f = (ls.b(distance + 3.1415927f) * 0.52f - 3.1415927f) * walkFactor - 3.1415927f * standFactor;
            bipedLeftLeg.f = -ls.b(distance) * 0.12f * walkFactor;
            bipedRightLeg.f = -ls.b(distance + 3.1415927f) * 0.32f * walkFactor;
            final float rotateY = ls.b(distance) * 0.44f * walkFactor;
            bipedOuter.g = rotateY + horizontalAngle;
            final ModelRotationRenderer modelRotationRenderer13 = bipedRightArm;
            final ModelRotationRenderer modelRotationRenderer14 = bipedLeftArm;
            final float n3 = -rotateY;
            modelRotationRenderer14.g = n3;
            modelRotationRenderer13.g = n3;
            final ModelRotationRenderer modelRotationRenderer15 = bipedRightLeg;
            final ModelRotationRenderer modelRotationRenderer16 = bipedLeftLeg;
            final float n4 = -rotateY;
            modelRotationRenderer16.g = n4;
            modelRotationRenderer15.g = n4;
            bipedHead.g = -rotateY;
        }
        else if (this.isSwim) {
            final float distance = totalHorizontalDistance;
            final float walkFactor = Factor(currentHorizontalSpeed, 0.15679921f, 0.52264464f);
            final float sneakFactor = Math.min(Factor(currentHorizontalSpeed, 0.0f, 0.15679921f), Factor(currentHorizontalSpeed, 0.52264464f, 0.15679921f));
            final float standFactor2 = Factor(currentHorizontalSpeed, 0.15679921f, 0.0f);
            final float standSneakFactor = standFactor2 + sneakFactor;
            final float horizontalAngle2 = (horizontalDistance < (this.isGenericSneaking ? 0.005 : 0.014999999664723873)) ? currentCameraAngle : currentHorizontalAngle;
            bipedHead.rotationOrder = ModelRotationRenderer.YXZ;
            bipedHead.g = ls.b(distance / 2.0f - 1.5707964f) * walkFactor;
            bipedHead.f = -0.7853982f * standSneakFactor;
            bipedHead.e = -2.0f;
            bipedOuter.fadeRotateAngleX = true;
            bipedOuter.f = 1.5707964f - 0.3926991f * standSneakFactor;
            bipedOuter.g = horizontalAngle2;
            final ModelRotationRenderer modelRotationRenderer17 = bipedBreast;
            final ModelRotationRenderer modelRotationRenderer18 = bipedBody;
            final float n5 = ls.b(distance / 2.0f - 1.5707964f) * walkFactor;
            modelRotationRenderer18.g = n5;
            modelRotationRenderer17.g = n5;
            bipedRightArm.rotationOrder = ModelRotationRenderer.YZX;
            bipedLeftArm.rotationOrder = ModelRotationRenderer.YZX;
            bipedRightArm.h = 2.3561945f + ls.b(totalTime * 0.1f) * standSneakFactor * 0.8f;
            bipedLeftArm.h = -2.3561945f - ls.b(totalTime * 0.1f) * standSneakFactor * 0.8f;
            bipedRightArm.f = (distance * 0.5f % 6.2831855f - 3.1415927f) * walkFactor + 0.3926991f * standSneakFactor;
            bipedLeftArm.f = ((distance * 0.5f + 3.1415927f) % 6.2831855f - 3.1415927f) * walkFactor + 0.3926991f * standSneakFactor;
            bipedRightLeg.f = ls.b(distance) * 0.52264464f * walkFactor;
            bipedLeftLeg.f = ls.b(distance + 3.1415927f) * 0.52264464f * walkFactor;
            final float rotateFeetAngleZ = 0.3926991f * standSneakFactor + ls.b(totalTime * 0.1f) * 0.4f * (standFactor2 - sneakFactor);
            bipedRightLeg.h = rotateFeetAngleZ;
            bipedLeftLeg.h = -rotateFeetAngleZ;
            if (this.scaleLegType != 1) {
                this.setLegScales(1.0f + (ls.b(totalTime * 0.1f + 1.5707964f) - 1.0f) * 0.15f * sneakFactor, 1.0f + (ls.b(totalTime * 0.1f + 1.5707964f) - 1.0f) * 0.15f * sneakFactor);
            }
            if (this.scaleArmType != 1) {
                this.setArmScales(1.0f + (ls.b(totalTime * 0.1f - 1.5707964f) - 1.0f) * 0.15f * sneakFactor, 1.0f + (ls.b(totalTime * 0.1f - 1.5707964f) - 1.0f) * 0.15f * sneakFactor);
            }
        }
        else if (this.isDive) {
            final float distance = totalDistance * 0.7f;
            final float walkFactor = Factor(currentSpeed, 0.0f, 0.15679921f);
            final float standFactor = Factor(currentSpeed, 0.15679921f, 0.0f);
            final float horizontalAngle = (totalDistance < (this.isGenericSneaking ? 0.005 : 0.014999999664723873)) ? currentCameraAngle : currentHorizontalAngle;
            bipedHead.f = -0.7853982f;
            bipedHead.e = -2.0f;
            bipedOuter.fadeRotateAngleX = true;
            bipedOuter.f = (this.isLevitate ? 1.1780972f : (this.isJump ? 0.0f : (1.5707964f - currentVerticalAngle)));
            bipedOuter.g = horizontalAngle;
            bipedRightLeg.h = (ls.b(distance) + 1.0f) * 0.52264464f * walkFactor + 0.3926991f * standFactor;
            bipedLeftLeg.h = (ls.b(distance + 3.1415927f) - 1.0f) * 0.52264464f * walkFactor - 0.3926991f * standFactor;
            if (this.scaleLegType != 1) {
                this.setLegScales(1.0f + (ls.b(distance - 1.5707964f) - 1.0f) * 0.25f * walkFactor, 1.0f + (ls.b(distance - 1.5707964f) - 1.0f) * 0.25f * walkFactor);
            }
            bipedRightArm.h = (ls.b(distance + 3.1415927f) * 0.52264464f * 2.5f + 1.5707964f) * walkFactor + 2.3561945f * standFactor;
            bipedLeftArm.h = (ls.b(distance) * 0.52264464f * 2.5f - 1.5707964f) * walkFactor - 2.3561945f * standFactor;
            if (this.scaleArmType != 1) {
                this.setArmScales(1.0f + (ls.b(distance + 1.5707964f) - 1.0f) * 0.15f * walkFactor, 1.0f + (ls.b(distance + 1.5707964f) - 1.0f) * 0.15f * walkFactor);
            }
        }
        else if (this.isCrawl) {
            final float distance = totalHorizontalDistance * 1.3f;
            final float walkFactor = Factor(this.currentHorizontalSpeedFlattened, 0.0f, 0.12951545f);
            final float standFactor = Factor(this.currentHorizontalSpeedFlattened, 0.12951545f, 0.0f);
            bipedHead.h = -viewHorizontalAngelOffset / 57.295776f;
            bipedHead.f = -0.7853982f;
            bipedHead.e = -2.0f;
            bipedTorso.rotationOrder = ModelRotationRenderer.YZX;
            bipedTorso.f = 1.3744469f;
            bipedTorso.d = 3.0f;
            bipedTorso.h = ls.b(distance + 1.5707964f) * 0.09817477f * walkFactor;
            bipedBody.g = ls.b(distance + 3.1415927f) * 0.09817477f * walkFactor;
            bipedRightLeg.f = (ls.b(distance - 1.5707964f) * 0.09817477f + 0.19634955f) * walkFactor + 0.19634955f * standFactor;
            bipedLeftLeg.f = (ls.b(distance - 3.1415927f - 1.5707964f) * 0.09817477f + 0.19634955f) * walkFactor + 0.19634955f * standFactor;
            bipedRightLeg.h = (ls.b(distance - 1.5707964f) + 1.0f) * 0.25f * walkFactor + 0.19634955f * standFactor;
            bipedLeftLeg.h = (ls.b(distance - 1.5707964f) - 1.0f) * 0.25f * walkFactor - 0.19634955f * standFactor;
            if (this.scaleLegType != 1) {
                this.setLegScales(1.0f + (ls.b(distance + 1.5707964f - 1.5707964f) - 1.0f) * 0.25f * walkFactor, 1.0f + (ls.b(distance - 1.5707964f - 1.5707964f) - 1.0f) * 0.25f * walkFactor);
            }
            bipedRightArm.rotationOrder = ModelRotationRenderer.YZX;
            bipedLeftArm.rotationOrder = ModelRotationRenderer.YZX;
            bipedRightArm.f = 3.926991f;
            bipedLeftArm.f = 3.926991f;
            bipedRightArm.h = (ls.b(distance + 3.1415927f) * 0.09817477f + 0.19634955f) * walkFactor + 0.3926991f * standFactor;
            bipedLeftArm.h = (ls.b(distance + 3.1415927f) * 0.09817477f - 0.19634955f) * walkFactor - 0.3926991f * standFactor;
            bipedRightArm.g = -1.5707964f;
            bipedLeftArm.g = 1.5707964f;
            if (this.scaleArmType != 1) {
                this.setArmScales(1.0f + (ls.b(distance + 1.5707964f) - 1.0f) * 0.15f * walkFactor, 1.0f + (ls.b(distance - 1.5707964f) - 1.0f) * 0.15f * walkFactor);
            }
        }
        else if (this.isSlide) {
            final float distance = totalHorizontalDistance * 0.7f;
            final float walkFactor = Factor(currentHorizontalSpeed, 0.0f, 1.0f) * 0.8f;
            bipedHead.h = -viewHorizontalAngelOffset / 57.295776f;
            bipedHead.f = -1.1780972f;
            bipedHead.e = -2.0f;
            bipedOuter.fadeRotateAngleY = false;
            bipedOuter.g = currentHorizontalAngle;
            bipedOuter.d = 5.0f;
            bipedOuter.f = 1.5707964f;
            bipedBody.rotationOrder = ModelRotationRenderer.YXZ;
            bipedBody.p = -0.4f;
            bipedBody.d = 6.5f;
            bipedBody.f = ls.b(distance - 0.7853982f) * 0.09817477f * walkFactor;
            bipedBody.g = ls.b(distance + 0.7853982f) * 0.09817477f * walkFactor;
            bipedRightLeg.f = ls.b(distance + 3.1415927f) * 0.09817477f * walkFactor + 0.09817477f;
            bipedLeftLeg.f = ls.b(distance + 1.5707964f) * 0.09817477f * walkFactor + 0.09817477f;
            bipedRightLeg.h = 0.19634955f;
            bipedLeftLeg.h = -0.19634955f;
            bipedRightArm.rotationOrder = ModelRotationRenderer.YZX;
            bipedLeftArm.rotationOrder = ModelRotationRenderer.YZX;
            bipedRightArm.f = ls.b(distance + 1.5707964f) * 0.09817477f * walkFactor + 3.1415927f - 0.09817477f;
            bipedLeftArm.f = ls.b(distance - 3.1415927f) * 0.09817477f * walkFactor + 3.1415927f - 0.09817477f;
            bipedRightArm.h = 0.3926991f;
            bipedLeftArm.h = -0.3926991f;
            bipedRightArm.g = -1.5707964f;
            bipedLeftArm.g = 1.5707964f;
        }
        else if (this.isFlying) {
            final float distance = totalDistance * 0.08f;
            final float walkFactor = Factor(currentSpeed, 0.0f, 1.0f);
            final float standFactor = Factor(currentSpeed, 1.0f, 0.0f);
            final float time2 = totalTime * 0.15f;
            final float verticalAngle = this.isJump ? Math.abs(currentVerticalAngle) : currentVerticalAngle;
            final float horizontalAngle2 = (horizontalDistance < 0.05000000074505806) ? currentCameraAngle : currentHorizontalAngle;
            bipedOuter.fadeRotateAngleX = true;
            bipedOuter.f = (1.5707964f - verticalAngle) * walkFactor;
            bipedOuter.g = horizontalAngle2;
            bipedHead.f = -bipedOuter.f / 2.0f;
            bipedRightArm.rotationOrder = ModelRotationRenderer.XZY;
            bipedLeftArm.rotationOrder = ModelRotationRenderer.XZY;
            bipedRightArm.g = ls.b(time2) * 0.3926991f * standFactor;
            bipedLeftArm.g = ls.b(time2) * 0.3926991f * standFactor;
            bipedRightArm.h = (ls.b(distance + 3.1415927f) * 0.09817477f + 2.7488937f) * walkFactor + 1.5707964f * standFactor;
            bipedLeftArm.h = (ls.b(distance) * 0.09817477f - 2.7488937f) * walkFactor - 1.5707964f * standFactor;
            bipedRightLeg.f = ls.b(distance) * 0.09817477f * walkFactor + ls.b(time2 + 3.1415927f) * 0.09817477f * standFactor;
            bipedLeftLeg.f = ls.b(distance + 3.1415927f) * 0.09817477f * walkFactor + ls.b(time2) * 0.09817477f * standFactor;
            bipedRightLeg.h = 0.09817477f;
            bipedLeftLeg.h = -0.09817477f;
        }
        else if (this.isHeadJump) {
            bipedOuter.fadeRotateAngleX = true;
            bipedOuter.f = 1.5707964f - currentVerticalAngle;
            bipedOuter.g = currentHorizontalAngle;
            bipedHead.f = -bipedOuter.f / 2.0f;
            final float bendFactor = Math.min(Factor(currentVerticalAngle, 1.5707964f, 0.0f), Factor(currentVerticalAngle, -1.5707964f, 0.0f));
            bipedRightArm.f = bendFactor * -0.7853982f;
            bipedLeftArm.f = bendFactor * -0.7853982f;
            bipedRightLeg.f = bendFactor * -0.7853982f;
            bipedLeftLeg.f = bendFactor * -0.7853982f;
            float armFactorZ = Factor(currentVerticalAngle, 1.5707964f, -1.5707964f);
            if (this.overGroundBlockId > 0 && aqz.s[this.overGroundBlockId].cU.a()) {
                armFactorZ = Math.min(armFactorZ, this.smallOverGroundHeight / 5.0f);
            }
            bipedRightArm.h = 2.7488937f + armFactorZ * 0.7853982f;
            bipedLeftArm.h = -2.7488937f - armFactorZ * 0.7853982f;
            final float legFactorZ = Factor(currentVerticalAngle, -1.5707964f, 1.5707964f);
            bipedRightLeg.h = 0.09817477f * legFactorZ;
            bipedLeftLeg.h = -0.09817477f * legFactorZ;
        }
        else if (this.isFalling) {
            final float distance = totalDistance * 0.1f;
            bipedRightArm.rotationOrder = ModelRotationRenderer.XZY;
            bipedLeftArm.rotationOrder = ModelRotationRenderer.XZY;
            bipedRightArm.g = ls.b(distance + 1.5707964f) * 0.7853982f;
            bipedLeftArm.g = ls.b(distance + 1.5707964f) * 0.7853982f;
            bipedRightArm.h = ls.b(distance) * 0.7853982f + 1.5707964f;
            bipedLeftArm.h = ls.b(distance) * 0.7853982f - 1.5707964f;
            bipedRightLeg.f = ls.b(distance + 3.1415927f + 1.5707964f) * 0.3926991f + 0.19634955f;
            bipedLeftLeg.f = ls.b(distance + 1.5707964f) * 0.3926991f + 0.19634955f;
            bipedRightLeg.h = ls.b(distance) * 0.3926991f + 0.19634955f;
            bipedLeftLeg.h = ls.b(distance) * 0.3926991f - 0.19634955f;
        }
        else {
            this.isStandard = true;
        }
    }
    
    private boolean isWorking() {
        return this.mp.p > 0.0f;
    }
    
    private void animateAngleJumping() {
        final float angle = this.angleJumpType * 0.7853982f;
        final ModelRotationRenderer bipedPelvic = this.md.bipedPelvic;
        bipedPelvic.g -= this.md.bipedOuter.g;
        final ModelRotationRenderer bipedPelvic2 = this.md.bipedPelvic;
        bipedPelvic2.g += this.md.currentCameraAngle;
        final float backness = 1.0f - Math.abs(angle - 3.1415927f) / 1.5707964f;
        final float leftness = -Math.min(angle - 3.1415927f, 0.0f) / 1.5707964f;
        final float rightness = Math.max(angle - 3.1415927f, 0.0f) / 1.5707964f;
        this.md.bipedLeftLeg.f = 0.19634955f * (1.0f + rightness);
        this.md.bipedRightLeg.f = 0.19634955f * (1.0f + leftness);
        this.md.bipedLeftLeg.g = -angle;
        this.md.bipedRightLeg.g = -angle;
        this.md.bipedLeftLeg.h = 0.19634955f * backness;
        this.md.bipedRightLeg.h = -0.19634955f * backness;
        this.md.bipedLeftLeg.rotationOrder = ModelRotationRenderer.ZXY;
        this.md.bipedRightLeg.rotationOrder = ModelRotationRenderer.ZXY;
        this.md.bipedLeftArm.h = -0.3926991f * rightness;
        this.md.bipedRightArm.h = 0.3926991f * leftness;
        this.md.bipedLeftArm.f = -0.7853982f * backness;
        this.md.bipedRightArm.f = -0.7853982f * backness;
    }
    
    private void animateNonStandardWorking(final float viewVerticalAngelOffset) {
        this.md.bipedRightShoulder.ignoreSuperRotation = true;
        this.md.bipedRightShoulder.f = viewVerticalAngelOffset / 57.295776f;
        this.md.bipedRightShoulder.g = this.md.workingAngle / 57.295776f;
        this.md.bipedRightShoulder.h = 3.1415927f;
        this.md.bipedRightShoulder.rotationOrder = ModelRotationRenderer.ZYX;
        this.md.bipedRightArm.reset();
    }
    
    private void animateNonStandardBowAiming(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.md.bipedRightShoulder.ignoreSuperRotation = true;
        this.md.bipedRightShoulder.g = this.md.workingAngle / 57.295776f;
        this.md.bipedRightShoulder.h = 3.1415927f;
        this.md.bipedRightShoulder.rotationOrder = ModelRotationRenderer.ZYX;
        this.md.bipedLeftShoulder.ignoreSuperRotation = true;
        this.md.bipedLeftShoulder.g = this.md.workingAngle / 57.295776f;
        this.md.bipedLeftShoulder.h = 3.1415927f;
        this.md.bipedLeftShoulder.rotationOrder = ModelRotationRenderer.ZYX;
        this.md.bipedRightArm.reset();
        this.md.bipedLeftArm.reset();
        final float headRotateAngleY = this.md.bipedHead.g;
        final float outerRotateAngleY = this.md.bipedOuter.g;
        final float headRotateAngleX = this.md.bipedHead.f;
        this.md.bipedHead.g = 0.0f;
        this.md.bipedOuter.g = 0.0f;
        this.md.bipedHead.f = 0.0f;
        this.imp.superAnimateBowAiming(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        this.md.bipedHead.g = headRotateAngleY;
        this.md.bipedOuter.g = outerRotateAngleY;
        this.md.bipedHead.f = headRotateAngleX;
    }
    
    public void animateHeadRotation(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.setRotationAngles(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        if (this.isStandard) {
            this.imp.superAnimateHeadRotation(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateSleeping(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            this.imp.superAnimateSleeping(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateArmSwinging(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            if (this.isAngleJumping) {
                this.animateAngleJumping();
            }
            else {
                this.imp.superAnimateArmSwinging(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
            }
        }
    }
    
    public void animateRiding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            this.imp.superAnimateRiding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateLeftArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            this.imp.superAnimateLeftArmItemHolding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateRightArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            this.imp.superAnimateRightArmItemHolding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateWorkingBody(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            this.imp.superAnimateWorkingBody(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        else if (this.isWorking()) {
            this.animateNonStandardWorking(viewVerticalAngelOffset);
        }
    }
    
    public void animateWorkingArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard || this.isWorking()) {
            this.imp.superAnimateWorkingArms(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateSneaking(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard && !this.isAngleJumping) {
            this.imp.superAnimateSneaking(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            this.imp.superApplyAnimationOffsets(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    public void animateBowAiming(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        if (this.isStandard) {
            this.imp.superAnimateBowAiming(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
        else {
            this.animateNonStandardBowAiming(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
        }
    }
    
    private void setArmScales(final float rightScale, final float leftScale) {
        if (this.scaleArmType == 0) {
            this.md.bipedRightArm.scaleY = rightScale;
            this.md.bipedLeftArm.scaleY = leftScale;
        }
        else if (this.scaleArmType == 2) {
            final ModelRotationRenderer bipedRightArm = this.md.bipedRightArm;
            bipedRightArm.p -= (1.0f - rightScale) * 0.5f;
            final ModelRotationRenderer bipedLeftArm = this.md.bipedLeftArm;
            bipedLeftArm.p -= (1.0f - leftScale) * 0.5f;
        }
    }
    
    private void setLegScales(final float rightScale, final float leftScale) {
        if (this.scaleLegType == 0) {
            this.md.bipedRightLeg.scaleY = rightScale;
            this.md.bipedLeftLeg.scaleY = leftScale;
        }
        else if (this.scaleLegType == 2) {
            final ModelRotationRenderer bipedRightLeg = this.md.bipedRightLeg;
            bipedRightLeg.p -= (1.0f - rightScale) * 0.5f;
            final ModelRotationRenderer bipedLeftLeg = this.md.bipedLeftLeg;
            bipedLeftLeg.p -= (1.0f - leftScale) * 0.5f;
        }
    }
    
    private static float Factor(final float x, final float x0, final float x1) {
        if (x0 > x1) {
            if (x <= x1) {
                return 1.0f;
            }
            if (x >= x0) {
                return 0.0f;
            }
            return (x0 - x) / (x0 - x1);
        }
        else {
            if (x >= x1) {
                return 1.0f;
            }
            if (x <= x0) {
                return 0.0f;
            }
            return (x - x0) / (x1 - x0);
        }
    }
    
    private static float Between(final float min, final float max, final float value) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
    
    private static float Normalize(float radiant) {
        while (radiant > 3.1415927f) {
            radiant -= 6.2831855f;
        }
        while (radiant < -3.1415927f) {
            radiant += 6.2831855f;
        }
        return radiant;
    }
}
