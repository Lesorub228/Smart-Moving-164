// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.config;

public class SmartMovingClientConfig extends SmartMovingConfig
{
    public static final int Sprinting = 0;
    public static final int Running = 1;
    public static final int Walking = 2;
    public static final int Sneaking = 3;
    public static final int Standing = 4;
    public static final int Up = 0;
    public static final int ChargeUp = 1;
    public static final int Angle = 2;
    public static final int HeadUp = 3;
    public static final int SlideDown = 4;
    public static final int ClimbUp = 5;
    public static final int ClimbUpHandsOnly = 6;
    public static final int ClimbBackUp = 7;
    public static final int ClimbBackUpHandsOnly = 8;
    public static final int ClimbBackHead = 9;
    public static final int ClimbBackHeadHandsOnly = 10;
    public static final int WallUp = 11;
    public static final int WallHead = 12;
    public static final int WallUpSlide = 13;
    public static final int WallHeadSlide = 14;
    
    public boolean isSneakingEnabled() {
        return this._sneak.value || !this.enabled;
    }
    
    public boolean isStandardBaseClimb() {
        return this._isStandardBaseClimb.value || !this.enabled;
    }
    
    public boolean isSimpleBaseClimb() {
        return this._isSimpleBaseClimb.value && this.enabled;
    }
    
    public boolean isSmartBaseClimb() {
        return this._isSmartBaseClimb.value && this.enabled;
    }
    
    public boolean isFreeBaseClimb() {
        return this._isFreeBaseClimb.value && this.enabled;
    }
    
    public boolean isTotalFreeLadderClimb() {
        return this.isFreeBaseClimb() && this._freeBaseLadderClimb.value;
    }
    
    public boolean isTotalFreeVineClimb() {
        return this.isFreeBaseClimb() && this._freeBaseVineClimb.value;
    }
    
    public boolean isFreeClimbAutoLaddderEnabled() {
        return this._freeClimbingAutoLaddder.value && this.enabled;
    }
    
    public boolean isFreeClimbAutoVineEnabled() {
        return this._freeClimbingAutoVine.value && this.enabled;
    }
    
    public boolean isFreeClimbingEnabled() {
        return this._freeClimb.value && this.enabled;
    }
    
    public boolean isCeilingClimbingEnabled() {
        return this._ceilingClimbing.value && this.enabled;
    }
    
    public boolean isSwimmingEnabled() {
        return this._swim.value && this.enabled;
    }
    
    public boolean isDivingEnabled() {
        return this._dive.value && this.enabled;
    }
    
    public boolean isLavaLikeWaterEnabled() {
        return this._lavaLikeWater.value && this.enabled;
    }
    
    public boolean isFlyingEnabled() {
        return this._fly.value && this.enabled;
    }
    
    public boolean isLevitateSmallEnabled() {
        return this._levitateSmall.value && this.enabled;
    }
    
    public boolean isRunningEnabled() {
        return this._run.value || !this.enabled;
    }
    
    public boolean isRunExhaustionEnabled() {
        return this._runExhaustion.value && this.enabled;
    }
    
    public boolean isClimbExhaustionEnabled() {
        return this._climbExhaustion.value && this.enabled;
    }
    
    public boolean isCeilingClimbExhaustionEnabled() {
        return this._ceilingClimbExhaustion.value && this.enabled;
    }
    
    public boolean isSprintingEnabled() {
        return this._sprint.value && this.enabled;
    }
    
    public boolean isSprintExhaustionEnabled() {
        return this._sprintExhaustion.value && this.enabled;
    }
    
    public boolean isJumpChargingEnabled() {
        return this._jumpCharge.value && this.enabled;
    }
    
    public boolean isHeadJumpingEnabled() {
        return this._headJump.value && this.enabled;
    }
    
    public boolean isSlidingEnabled() {
        return this._slide.value && this.enabled;
    }
    
    public boolean isCrawlingEnabled() {
        return this._crawl.value && this.enabled;
    }
    
    public boolean isExhaustionLossHungerEnabled() {
        return this._exhaustionLossHunger.value && this._hungerGain.value && this.enabled;
    }
    
    public boolean isHungerGainEnabled() {
        return this._hungerGain.value || !this.enabled;
    }
    
    public boolean isLevitationAnimationEnabled() {
        return this._levitateAnimation.value && this.enabled;
    }
    
    public boolean isFallAnimationEnabled() {
        return this._fallAnimation.value && this.enabled;
    }
    
    public boolean isJumpingEnabled(final int speed, final int type) {
        if (!this.enabled) {
            return true;
        }
        if (type == 1) {
            return this._jumpCharge.value;
        }
        if (type == 4) {
            return this._slide.value;
        }
        if (type == 5 || type == 6) {
            return this._climbUpJump.value;
        }
        if (type == 7 || type == 8) {
            return this._climbBackUpJump.value;
        }
        if (type == 9 || type == 10) {
            return this._climbBackHeadJump.value;
        }
        if (type == 11) {
            return this._wallUpJump.value;
        }
        if (type == 12) {
            return this._wallHeadJump.value;
        }
        if (speed == 0) {
            return this._sprintJump.value;
        }
        if (speed == 1) {
            return this._runJump.value;
        }
        if (speed == 2) {
            return this._walkJump.value;
        }
        if (speed == 3) {
            return this._sneakJump.value;
        }
        return speed != 4 || this._standJump.value;
    }
    
    public boolean isSideJumpEnabled() {
        return this.enabled && this._angleJumpSide.value;
    }
    
    public boolean isBackJumpEnabled() {
        return this.enabled && this._angleJumpBack.value;
    }
    
    public boolean isWallJumpEnabled() {
        return this.enabled && this._wallUpJump.value;
    }
    
    public boolean isJumpExhaustionEnabled(final int speed, final int type) {
        if (!this.enabled) {
            return false;
        }
        boolean result = this._jumpExhaustion.value;
        if (type == 4) {
            return result && this._jumpSlideExhaustion.value;
        }
        if (type == 2) {
            result &= this._angleJumpExhaustion.value;
        }
        else if (type == 5 || type == 6) {
            result &= this._climbJumpUpExhaustion.value;
        }
        else if (type == 7 || type == 8) {
            result &= this._climbJumpBackUpExhaustion.value;
        }
        else if (type == 9 || type == 10) {
            result &= this._climbJumpBackHeadExhaustion.value;
        }
        else if (type == 11) {
            result &= this._wallUpJumpExhaustion.value;
        }
        else if (type == 12) {
            result &= this._wallHeadJumpExhaustion.value;
        }
        else {
            result &= this._upJumpExhaustion.value;
        }
        if (type == 5 || type == 6 || type == 7 || type == 8 || type == 9 || type == 10) {
            return result && this._climbJumpExhaustion.value;
        }
        if (type == 11 || type == 12) {
            return result && this._wallJumpExhaustion.value;
        }
        if (speed == 0) {
            result &= this._sprintJumpExhaustion.value;
        }
        else if (speed == 1) {
            result &= this._runJumpExhaustion.value;
        }
        else if (speed == 2) {
            result &= this._walkJumpExhaustion.value;
        }
        else if (speed == 3) {
            result &= this._sneakJumpExhaustion.value;
        }
        else if (speed == 4) {
            result &= this._standJumpExhaustion.value;
        }
        if (type == 1) {
            result |= this._jumpChargeExhaustion.value;
        }
        return result;
    }
    
    public float getJumpExhaustionGain(final int speed, final int type, final float jumpCharge) {
        if (!this.enabled) {
            return 0.0f;
        }
        float result = this._baseExhautionGainFactor.value * this._jumpExhaustionGainFactor.value;
        if (type == 4) {
            return result * this._jumpSlideExhaustionGainFactor.value;
        }
        if (type == 2) {
            result *= this._angleJumpExhaustionGainFactor.value;
        }
        else if (type == 5 || type == 6) {
            result *= this._climbJumpUpExhaustionGainFactor.value;
        }
        else if (type == 7 || type == 8) {
            result *= this._climbJumpBackUpExhaustionGainFactor.value;
        }
        else if (type == 9 || type == 10) {
            result *= this._climbJumpBackHeadExhaustionGainFactor.value;
        }
        else if (type == 11) {
            result *= this._wallUpJumpExhaustionGainFactor.value;
        }
        else if (type == 12) {
            result *= this._wallHeadJumpExhaustionGainFactor.value;
        }
        else {
            result *= this._upJumpExhaustionGainFactor.value;
        }
        if (type == 5 || type == 6 || type == 7 || type == 8 || type == 9 || type == 10) {
            return result * this._climbJumpExhaustionGainFactor.value;
        }
        if (type == 11 || type == 12) {
            return result * this._wallJumpExhaustionGainFactor.value;
        }
        if (speed == 0) {
            result *= this._sprintJumpExhaustionGainFactor.value;
        }
        else if (speed == 1) {
            result *= this._runJumpExhaustionGainFactor.value;
        }
        else if (speed == 2) {
            result *= this._walkJumpExhaustionGainFactor.value;
        }
        else if (speed == 3) {
            result *= this._sneakJumpExhaustionGainFactor.value;
        }
        else if (speed == 4) {
            result *= this._standJumpExhaustionGainFactor.value;
        }
        if (type == 1) {
            if (!this.isJumpExhaustionEnabled(speed, 0)) {
                result = 0.0f;
            }
            result += this._baseExhautionGainFactor.value * this._jumpExhaustionGainFactor.value * this._upJumpExhaustionGainFactor.value * this._jumpChargeExhaustionGainFactor.value * Math.min(jumpCharge, this._jumpChargeMaximum.value) / this._jumpChargeMaximum.value;
        }
        return result;
    }
    
    public float getJumpExhaustionStop(final int speed, final int type, final float jumpCharge) {
        float result = this._jumpExhaustionStopFactor.value;
        if (type == 4) {
            return result * this._jumpSlideExhaustionStopFactor.value;
        }
        if (type == 2) {
            result *= this._angleJumpExhaustionStopFactor.value;
        }
        else if (type == 5 || type == 6) {
            result *= this._climbJumpUpExhaustionStopFactor.value;
        }
        else if (type == 7 || type == 8) {
            result *= this._climbJumpBackUpExhaustionStopFactor.value;
        }
        else if (type == 9 || type == 10) {
            result *= this._climbJumpBackHeadExhaustionStopFactor.value;
        }
        else if (type == 11) {
            result *= this._wallUpJumpExhaustionStopFactor.value;
        }
        else if (type == 12) {
            result *= this._wallHeadJumpExhaustionStopFactor.value;
        }
        else {
            result *= this._upJumpExhaustionStopFactor.value;
        }
        if (type == 5 || type == 6 || type == 7 || type == 8 || type == 9 || type == 10) {
            return result * this._climbJumpExhaustionStopFactor.value;
        }
        if (type == 11 || type == 12) {
            return result * this._wallJumpExhaustionStopFactor.value;
        }
        if (speed == 0) {
            result *= this._sprintJumpExhaustionStopFactor.value;
        }
        else if (speed == 1) {
            result *= this._runJumpExhaustionStopFactor.value;
        }
        else if (speed == 2) {
            result *= this._walkJumpExhaustionStopFactor.value;
        }
        else if (speed == 3) {
            result *= this._sneakJumpExhaustionStopFactor.value;
        }
        else if (speed == 4) {
            result *= this._standJumpExhaustionStopFactor.value;
        }
        if (type == 1) {
            if (!this.isJumpExhaustionEnabled(speed, 0)) {
                result += this.getJumpExhaustionGain(speed, 0, 0.0f);
            }
            result -= this._jumpExhaustionStopFactor.value * this._upJumpExhaustionStopFactor.value * this._jumpChargeExhaustionStopFactor.value * Math.min(jumpCharge, this._jumpChargeMaximum.value) / this._jumpChargeMaximum.value;
        }
        return result;
    }
    
    public float getJumpChargeFactor(float jumpCharge) {
        if (!this.enabled || !this._jumpCharge.value) {
            return 1.0f;
        }
        jumpCharge = Math.min(jumpCharge, this._jumpChargeMaximum.value);
        return 1.0f + jumpCharge / this._jumpChargeMaximum.value * (this._jumpChargeFactor.value - 1.0f);
    }
    
    public float getHeadJumpFactor(float headJumpCharge) {
        if (!this.enabled || !this._headJump.value) {
            return 1.0f;
        }
        headJumpCharge = Math.min(headJumpCharge, this._headJumpChargeMaximum.value);
        return (headJumpCharge - 1.0f) / (this._headJumpChargeMaximum.value - 1.0f);
    }
    
    public float getJumpVerticalFactor(final int speed, final int type) {
        if (!this.enabled) {
            return 1.0f;
        }
        float result = this._jumpVerticalFactor.value;
        if (type == 2) {
            return result * this._angleJumpVerticalFactor.value;
        }
        if (type == 5 || type == 6) {
            result *= this._climbUpJumpVerticalFactor.value;
        }
        if (type == 6) {
            result *= this._climbUpJumpHandsOnlyVerticalFactor.value;
        }
        if (type == 7 || type == 8) {
            result *= this._climbBackUpJumpVerticalFactor.value;
        }
        if (type == 8) {
            result *= this._climbBackUpJumpHandsOnlyVerticalFactor.value;
        }
        if (type == 9 || type == 10) {
            result *= this._climbBackHeadJumpVerticalFactor.value;
        }
        if (type == 10) {
            result *= this._climbBackHeadJumpHandsOnlyVerticalFactor.value;
        }
        if (type == 11 || type == 12) {
            result *= this._wallUpJumpVerticalFactor.value;
        }
        if (type == 12) {
            result *= this._wallHeadJumpVerticalFactor.value;
        }
        if (type == 2 || type == 5 || type == 6 || type == 7 || type == 8 || type == 9 || type == 10 || type == 11 || type == 12) {
            return result;
        }
        if (speed == 0) {
            result *= this._sprintJumpVerticalFactor.value;
        }
        else if (speed == 1) {
            result *= this._runJumpVerticalFactor.value;
        }
        else if (speed == 2) {
            result *= this._walkJumpVerticalFactor.value;
        }
        else if (speed == 3) {
            result *= this._sneakJumpVerticalFactor.value;
        }
        else if (speed == 4) {
            result *= this._standJumpVerticalFactor.value;
        }
        return result;
    }
    
    public float getJumpHorizontalFactor(final int speed, final int type) {
        if (!this.enabled) {
            return (speed == 1) ? 2.0f : 1.0f;
        }
        float result = this._jumpHorizontalFactor.value;
        if (type == 2) {
            result *= this._angleJumpHorizontalFactor.value;
        }
        if (type == 7 || type == 8) {
            result *= this._climbBackUpJumpHorizontalFactor.value;
        }
        if (type == 8) {
            result *= this._climbBackUpJumpHandsOnlyHorizontalFactor.value;
        }
        if (type == 9 || type == 10) {
            result *= this._climbBackHeadJumpHorizontalFactor.value;
        }
        if (type == 10) {
            result *= this._climbBackHeadJumpHandsOnlyHorizontalFactor.value;
        }
        if (type == 11) {
            result *= this._wallUpJumpHorizontalFactor.value;
        }
        if (type == 12) {
            result *= this._wallHeadJumpHorizontalFactor.value;
        }
        if (type == 2 || type == 5 || type == 6 || type == 7 || type == 8 || type == 9 || type == 10 || type == 11 || type == 12) {
            return result;
        }
        if (speed == 0) {
            result *= this._sprintJumpHorizontalFactor.value;
        }
        else if (speed == 1) {
            result *= this._runJumpHorizontalFactor.value;
        }
        else if (speed == 2) {
            result *= this._walkJumpHorizontalFactor.value;
        }
        else if (speed == 3) {
            result *= this._sneakJumpHorizontalFactor.value;
        }
        else if (speed == 4 && type != 7 && type != 8 && type != 9 && type != 10) {
            result *= 0.0f;
        }
        return result;
    }
    
    public float getMaxHorizontalMotion(final int speed, final int type, final boolean inWater) {
        float maxMotion = 0.11785204f;
        if (!this.enabled) {
            return (speed == 1) ? (maxMotion * 1.3f) : maxMotion;
        }
        if (inWater) {
            maxMotion = 0.07839603f;
        }
        if (speed == 0) {
            maxMotion *= this._sprintFactor.value;
        }
        else if (speed == 1) {
            maxMotion *= this._runFactor.value;
        }
        else if (speed == 3) {
            maxMotion *= this._sneakFactor.value;
        }
        return maxMotion;
    }
    
    public float getMaxExhaustion() {
        float result = 0.0f;
        if (this._run.value && this._runExhaustion.value) {
            result = this.max(result, this._runExhaustionStop.value);
        }
        if (this._sprint.value && this._sprintExhaustion.value) {
            result = this.max(result, this._sprintExhaustionStop.value);
        }
        if (this._jump.value) {
            for (int i = 0; i <= 4; ++i) {
                for (int n = 0; n <= 14; ++n) {
                    if (this.isJumpExhaustionEnabled(i, n)) {
                        for (int t = 0; t <= 1; ++t) {
                            result = this.max(result, this.getJumpExhaustionStop(i, n, (float)t) + this.getJumpExhaustionGain(i, n, (float)t));
                        }
                    }
                }
            }
        }
        if (this._freeClimb.value && this._climbExhaustion.value) {
            result = this.max(result, this._climbExhaustionStop.value);
        }
        if (this._ceilingClimbing.value && this._ceilingClimbExhaustion.value) {
            result = this.max(result, this._ceilingClimbExhaustionStop.value);
        }
        return result;
    }
    
    private float max(final float value, final float valueOrInfinite) {
        return (valueOrInfinite == java.lang.Float.POSITIVE_INFINITY) ? value : Math.max(value, valueOrInfinite);
    }
    
    public float getFactor(final boolean hunger, final boolean onGround, boolean isStanding, final boolean isStill, boolean isSneaking, final boolean isRunning, final boolean isSprinting, boolean isClimbing, final boolean isClimbCrawling, final boolean isCeilingClimbing, final boolean isDipping, final boolean isSwimming, final boolean isDiving, boolean isCrawling, final boolean isCrawlClimbing) {
        isClimbing |= isClimbCrawling;
        isCrawling |= isCrawlClimbing;
        final boolean actionOverGound = isClimbing || isCeilingClimbing || isDiving || isSwimming;
        final boolean airBorne = !onGround && !actionOverGound;
        isStanding = (actionOverGound ? isStill : isStanding);
        isSneaking &= !isStanding;
        float factor = hunger ? this._baseHungerGainFactor.value : this._baseExhautionLossFactor.value;
        if (airBorne) {
            factor *= (hunger ? 0.0f : this._fallExhautionLossFactor.value);
        }
        else if (isSprinting) {
            factor *= (hunger ? this._sprintingHungerGainFactor.value : this._sprintingExhautionLossFactor.value);
        }
        else if (isRunning) {
            factor *= (hunger ? this._runningHungerGainFactor.value : this._runningExhautionLossFactor.value);
        }
        else if (isSneaking) {
            factor *= (hunger ? this._sneakingHungerGainFactor.value : this._sneakingExhautionLossFactor.value);
        }
        else if (isStanding) {
            factor *= (hunger ? this._standingHungerGainFactor.value : this._standingExhautionLossFactor.value);
        }
        else {
            factor *= (hunger ? this._walkingHungerGainFactor.value : this._walkingExhautionLossFactor.value);
        }
        if (isClimbing) {
            factor *= (hunger ? this._climbingHungerGainFactor.value : this._climbingExhaustionLossFactor.value);
        }
        else if (isCrawling) {
            factor *= (hunger ? this._crawlingHungerGainFactor.value : this._crawlingExhaustionLossFactor.value);
        }
        else if (isCeilingClimbing) {
            factor *= (hunger ? this._ceilClimbingHungerGainFactor.value : this._ceilClimbingExhaustionLossFactor.value);
        }
        else if (isSwimming) {
            factor *= (hunger ? this._swimmingHungerGainFactor.value : this._swimmingExhaustionLossFactor.value);
        }
        else if (isDiving) {
            factor *= (hunger ? this._divingHungerGainFactor.value : this._divingExhaustionLossFactor.value);
        }
        else if (isDipping) {
            factor *= (hunger ? this._dippingHungerGainFactor.value : this._dippingExhaustionLossFactor.value);
        }
        else if (onGround) {
            factor *= (hunger ? this._normalHungerGainFactor.value : this._normalExhaustionLossFactor.value);
        }
        else {
            factor *= (hunger ? this._normalHungerGainFactor.value : this._normalExhaustionLossFactor.value);
        }
        return factor;
    }
}
