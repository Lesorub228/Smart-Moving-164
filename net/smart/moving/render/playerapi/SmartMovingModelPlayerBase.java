// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render.playerapi;

import net.smart.render.playerapi.SmartRender;
import api.player.model.ModelPlayerAPI;
import net.smart.moving.render.SmartMovingModel;
import net.smart.moving.render.IModelPlayer;
import api.player.model.ModelPlayerBase;

public class SmartMovingModelPlayerBase extends ModelPlayerBase implements IModelPlayer
{
    private SmartMovingModel model;
    
    public SmartMovingModelPlayerBase(final ModelPlayerAPI modelplayerapi) {
        super(modelplayerapi);
    }
    
    public void afterLocalConstructing(final float f) {
        this.model = new SmartMovingModel(f, SmartRender.getPlayerBase(this.modelPlayer), this);
    }
    
    public SmartMovingModel getMovingModel() {
        return this.model;
    }
    
    public void dynamicOverrideAnimateHeadRotation(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateHeadRotation(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateSleeping(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateSleeping(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateArmSwinging(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateArmSwinging(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateRiding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateRiding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateLeftArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateLeftArmItemHolding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateRightArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateRightArmItemHolding(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateWorkingBody(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateWorkingBody(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateWorkingArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateWorkingArms(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateSneaking(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateSneaking(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateArms(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void dynamicOverrideAnimateBowAiming(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateBowAiming(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void superAnimateHeadRotation(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateHeadRotation", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateSleeping(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateSleeping", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateArmSwinging(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateArmSwinging", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateRiding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateRiding", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateLeftArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateLeftArmItemHolding", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateRightArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateRightArmItemHolding", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateWorkingBody(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateWorkingBody", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateWorkingArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateWorkingArms", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateSneaking(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateSneaking", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superApplyAnimationOffsets(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateArms", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void superAnimateBowAiming(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.dynamic("animateBowAiming", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    @Deprecated
    public bcu getOuter() {
        return this.model.md.bipedOuter;
    }
    
    @Deprecated
    public bcu getTorso() {
        return this.model.md.bipedTorso;
    }
    
    @Deprecated
    public bcu getBody() {
        return this.model.md.bipedBody;
    }
    
    @Deprecated
    public bcu getBreast() {
        return this.model.md.bipedBreast;
    }
    
    @Deprecated
    public bcu getNeck() {
        return this.model.md.bipedNeck;
    }
    
    @Deprecated
    public bcu getHead() {
        return this.model.md.bipedHead;
    }
    
    @Deprecated
    public bcu getHeadwear() {
        return this.model.md.bipedHeadwear;
    }
    
    @Deprecated
    public bcu getRightShoulder() {
        return this.model.md.bipedRightShoulder;
    }
    
    @Deprecated
    public bcu getRightArm() {
        return this.model.md.bipedRightArm;
    }
    
    @Deprecated
    public bcu getLeftShoulder() {
        return this.model.md.bipedLeftShoulder;
    }
    
    @Deprecated
    public bcu getLeftArm() {
        return this.model.md.bipedLeftArm;
    }
    
    @Deprecated
    public bcu getPelvic() {
        return this.model.md.bipedPelvic;
    }
    
    @Deprecated
    public bcu getRightLeg() {
        return this.model.md.bipedRightLeg;
    }
    
    @Deprecated
    public bcu getLeftLeg() {
        return this.model.md.bipedLeftLeg;
    }
    
    @Deprecated
    public bcu getEars() {
        return this.model.md.bipedEars;
    }
    
    @Deprecated
    public bcu getCloak() {
        return this.model.md.bipedCloak;
    }
}
