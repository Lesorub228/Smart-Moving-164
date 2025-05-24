// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import java.util.Random;

public class ModelPlayer extends bbj implements IModelPlayer
{
    private final SmartRenderModel model;
    
    public ModelPlayer(final float f) {
        super(f);
        this.model = new SmartRenderModel(f, this, this);
    }
    
    public void a(final nn entity, final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.render(entity, totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public void superRender(final nn entity, final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        super.a(entity, totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor);
    }
    
    public SmartRenderModel getRenderModel() {
        return this.model;
    }
    
    public void initialize(final bcu bipedBody, final bcu bipedCloak, final bcu bipedHead, final bcu bipedEars, final bcu bipedHeadwear, final bcu bipedRightArm, final bcu bipedLeftArm, final bcu bipedRightLeg, final bcu bipedLeftLeg) {
        this.e = bipedBody;
        this.k = bipedCloak;
        this.c = bipedHead;
        this.j = bipedEars;
        this.d = bipedHeadwear;
        this.f = bipedRightArm;
        this.g = bipedLeftArm;
        this.h = bipedRightLeg;
        this.i = bipedLeftLeg;
    }
    
    public void a(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor, final nn entity) {
        this.model.setRotationAngles(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor, entity);
    }
    
    public void superSetRotationAngles(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor, final nn entity) {
        super.a(totalHorizontalDistance, currentHorizontalSpeed, totalTime, viewHorizontalAngelOffset, viewVerticalAngelOffset, factor, entity);
    }
    
    public void c(final float f) {
        this.model.renderCloak(f);
    }
    
    public void superRenderCloak(final float f) {
        super.c(f);
    }
    
    public bcu a(final Random random) {
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
        this.model.animateHeadRotation(viewHorizontalAngelOffset, viewVerticalAngelOffset);
    }
    
    public void animateSleeping(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateSleeping();
    }
    
    public void animateArmSwinging(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateArmSwinging(totalHorizontalDistance, currentHorizontalSpeed);
    }
    
    public void animateRiding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateRiding();
    }
    
    public void animateLeftArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateLeftArmItemHolding();
    }
    
    public void animateRightArmItemHolding(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateRightArmItemHolding();
    }
    
    public void animateWorkingBody(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateWorkingBody();
    }
    
    public void animateWorkingArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateWorkingArms();
    }
    
    public void animateSneaking(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateSneaking();
    }
    
    public void animateArms(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateArms(totalTime);
    }
    
    public void animateBowAiming(final float totalHorizontalDistance, final float currentHorizontalSpeed, final float totalTime, final float viewHorizontalAngelOffset, final float viewVerticalAngelOffset, final float factor) {
        this.model.animateBowAiming(totalTime);
    }
}
