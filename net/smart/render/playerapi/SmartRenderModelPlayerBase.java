// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.playerapi;

import java.util.Random;
import api.player.model.ModelPlayerAPI;
import net.smart.render.SmartRenderModel;
import net.smart.render.IModelPlayer;
import api.player.model.ModelPlayerBase;

public class SmartRenderModelPlayerBase extends ModelPlayerBase implements IModelPlayer
{
    private SmartRenderModel model;
    
    public SmartRenderModelPlayerBase(final ModelPlayerAPI modelplayerapi) {
        super(modelplayerapi);
    }
    
    public void afterLocalConstructing(final float f) {
        this.model = new SmartRenderModel(f, (bbj)this.modelPlayer, this);
    }
    
    public SmartRenderModel getRenderModel() {
        return this.model;
    }
    
    public void render(final nn entity, final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.render(entity, totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void superRender(final nn entity, final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.render(entity, totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void initialize(final bcu bipedBody, final bcu bipedCloak, final bcu bipedHead, final bcu bipedEars, final bcu bipedHeadwear, final bcu bipedRightArm, final bcu bipedLeftArm, final bcu bipedRightLeg, final bcu bipedLeftLeg) {
        this.modelPlayer.e = bipedBody;
        this.modelPlayer.k = bipedCloak;
        this.modelPlayer.c = bipedHead;
        this.modelPlayer.j = bipedEars;
        this.modelPlayer.d = bipedHeadwear;
        this.modelPlayer.f = bipedRightArm;
        this.modelPlayer.g = bipedLeftArm;
        this.modelPlayer.h = bipedRightLeg;
        this.modelPlayer.i = bipedLeftLeg;
    }
    
    public void setRotationAngles(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor, final nn entity) {
        this.model.setRotationAngles(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor, entity);
    }
    
    public void superSetRotationAngles(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor, final nn entity) {
        super.setRotationAngles(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor, entity);
    }
    
    public void renderCloak(final float f) {
        this.model.renderCloak(f);
    }
    
    public void superRenderCloak(final float factor) {
        super.renderCloak(factor);
    }
    
    public bcu getRandomModelBox(final Random random) {
        return this.model.getRandomBox(random);
    }
    
    public bcu getOuter() {
        return this.model.bipedOuter;
    }
    
    public bcu getTorso() {
        return this.model.bipedTorso;
    }
    
    public bcu getBody() {
        return this.model.bipedBody;
    }
    
    public bcu getBreast() {
        return this.model.bipedBreast;
    }
    
    public bcu getNeck() {
        return this.model.bipedNeck;
    }
    
    public bcu getHead() {
        return this.model.bipedHead;
    }
    
    public bcu getHeadwear() {
        return this.model.bipedHeadwear;
    }
    
    public bcu getRightShoulder() {
        return this.model.bipedRightShoulder;
    }
    
    public bcu getRightArm() {
        return this.model.bipedRightArm;
    }
    
    public bcu getLeftShoulder() {
        return this.model.bipedLeftShoulder;
    }
    
    public bcu getLeftArm() {
        return this.model.bipedLeftArm;
    }
    
    public bcu getPelvic() {
        return this.model.bipedPelvic;
    }
    
    public bcu getRightLeg() {
        return this.model.bipedRightLeg;
    }
    
    public bcu getLeftLeg() {
        return this.model.bipedLeftLeg;
    }
    
    public bcu getEars() {
        return this.model.bipedEars;
    }
    
    public bcu getCloak() {
        return this.model.bipedCloak;
    }
    
    public void animateHeadRotation(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateHeadRotation", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateHeadRotation(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateHeadRotation(viewHorizontalAngelOffset, viewVerticalAngelOffset);
    }
    
    public void animateSleeping(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateSleeping", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateSleeping(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateSleeping();
    }
    
    public void animateArmSwinging(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateArmSwinging", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateArmSwinging(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateArmSwinging(totalHorizontalDistance, currentHorizontalSpeed);
    }
    
    public void animateRiding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateRiding", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateRiding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateRiding();
    }
    
    public void animateLeftArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateLeftArmItemHolding", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateLeftArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateLeftArmItemHolding();
    }
    
    public void animateRightArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateRightArmItemHolding", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateRightArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateRightArmItemHolding();
    }
    
    public void animateWorkingBody(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateWorkingBody", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateWorkingBody(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateWorkingBody();
    }
    
    public void animateWorkingArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateWorkingArms", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateWorkingArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateWorkingArms();
    }
    
    public void animateSneaking(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateSneaking", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateSneaking(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateSneaking();
    }
    
    public void animateArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateArms", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateArms(totalTime);
    }
    
    public void animateBowAiming(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.modelPlayer.dynamic("animateBowAiming", new Object[] { totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor });
    }
    
    public void dynamicVirtualAnimateBowAiming(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateBowAiming(totalTime);
    }
}
