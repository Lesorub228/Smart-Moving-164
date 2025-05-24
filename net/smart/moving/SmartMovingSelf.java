// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import net.smart.utilities.Install;
import net.smart.moving.render.SmartMovingRender;
import net.smart.utilities.Interface;
import net.smart.render.statistics.SmartStatisticsFactory;
import net.smart.utilities.Reflect;
import java.util.List;
import java.util.Random;
import net.smart.utilities.Utilities;
import net.smart.moving.config.SmartMovingOptions;
import net.smart.moving.config.SmartMovingClientConfig;
import java.util.Iterator;
import java.util.HashSet;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class SmartMovingSelf extends SmartMoving implements ISmartMovingSelf
{
    private boolean initialized;
    private int multiPlayerInitialized;
    private int updateCounter;
    private float distanceSwom;
    private static ClimbGap out_handsClimbGap;
    private static ClimbGap out_feetClimbGap;
    private static HandsClimbing[] inout_handsClimbing;
    private static FeetClimbing[] inout_feetClimbing;
    public boolean wantClimbUp;
    public boolean wantClimbDown;
    public boolean wantSprint;
    public boolean wantCrawlNotClimb;
    public boolean wantClimbCeiling;
    public boolean isStanding;
    public boolean wouldIsSneaking;
    public boolean isVineOnlyClimbing;
    public boolean isVineAnyClimbing;
    public boolean isClimbingStill;
    public boolean isClimbHolding;
    public boolean isNeighborClimbing;
    public boolean hasClimbGap;
    public boolean hasClimbCrawlGap;
    public boolean hasNeighborClimbGap;
    public boolean hasNeighborClimbCrawlGap;
    public float dippingDepth;
    public boolean isJumping;
    public boolean isJumpingOutOfWater;
    public boolean isShallowDiveOrSwim;
    public boolean isFakeShallowWaterSneaking;
    public boolean isStillSwimmingJump;
    public boolean isGroundSprinting;
    public boolean isSprintJump;
    public boolean isAerodynamic;
    public int handsEdgeBlockId;
    public int handsEdgeMeta;
    public int feetEdgeBlockId;
    public int feetEdgeMeta;
    public int waterMovementTicks;
    public float exhaustion;
    public float jumpCharge;
    public float headJumpCharge;
    public boolean blockJumpTillButtonRelease;
    public float maxExhaustionForAction;
    public float maxExhaustionToStartAction;
    public float prevMaxExhaustionForAction;
    public float prevMaxExhaustionToStartAction;
    public float foreignExhaustionFactor;
    public float foreignMaxExhaustionForAction;
    public float foreignMaxExhaustionToStartAction;
    public double lastHorizontalCollisionX;
    public double lastHorizontalCollisionZ;
    public float lastHungerIncrease;
    private double beforeMoveEntityPosX;
    private double beforeMoveEntityPosY;
    private double beforeMoveEntityPosZ;
    private float beforeDistanceWalkedModified;
    private float horizontalCollisionAngle;
    boolean wasOnGround;
    boolean wasCapabilitiesIsFlying;
    private float fadingPerspectiveFactor;
    private boolean wasInventory;
    private double jumpMotionX;
    private double jumpMotionZ;
    public double prevMotionX;
    public double prevMotionY;
    public double prevMotionZ;
    public final Button forwardButton;
    public final Button leftButton;
    public final Button rightButton;
    public final Button backButton;
    public final Button jumpButton;
    public final Button sneakButton;
    public final Button grabButton;
    public final Button sprintButton;
    public final Button toggleButton;
    public final Button speedIncreaseButton;
    public final Button speedDecreaseButton;
    public boolean wasRunning;
    public boolean wasLevitating;
    public boolean wasCrawling;
    public boolean wasHeadJumping;
    private boolean contextContinueCrawl;
    private boolean ignoreNextStopSneakButtonPressed;
    private int collidedHorizontallyTickCount;
    private boolean wantWallJumping;
    private boolean continueWallJumping;
    private boolean wasCollidedHorizontally;
    private boolean wasRunningWhenSprintStarted;
    private boolean jumpAvoided;
    private int climbIntoCount;
    private int leftJumpCount;
    private int rightJumpCount;
    private int backJumpCount;
    private int wallJumpCount;
    private int nextClimbDistance;
    public float distanceClimbedModified;
    private boolean sneakToggled;
    private boolean crawlToggled;
    private int lastWorldPlayerEntitiesSize;
    private int lastWorldPlayerLastEnttyId;
    private long prevPacketState;
    public static final Field _attributeValue;
    public static final Field _chatMessageList;
    public static final Method _onLivingJump;
    
    public SmartMovingSelf(final uf sp, final IEntityPlayerSP isp) {
        super(sp, isp);
        this.prevMaxExhaustionForAction = Float.NaN;
        this.prevMaxExhaustionToStartAction = Float.NaN;
        this.foreignMaxExhaustionForAction = Float.MAX_VALUE;
        this.foreignMaxExhaustionToStartAction = Float.MAX_VALUE;
        this.fadingPerspectiveFactor = -1.0f;
        this.forwardButton = new Button();
        this.leftButton = new Button();
        this.rightButton = new Button();
        this.backButton = new Button();
        this.jumpButton = new Button();
        this.sneakButton = new Button();
        this.grabButton = new Button();
        this.sprintButton = new Button();
        this.toggleButton = new Button();
        this.speedIncreaseButton = new Button();
        this.speedDecreaseButton = new Button();
        this.sneakToggled = false;
        this.crawlToggled = false;
        this.lastWorldPlayerEntitiesSize = -1;
        this.lastWorldPlayerLastEnttyId = -1;
        this.initialized = false;
        this.nextClimbDistance = 0;
        this.distanceClimbedModified = 0.0f;
        this.exhaustion = 0.0f;
        this.lastHorizontalCollisionX = 0.0;
        this.lastHorizontalCollisionZ = 0.0;
        this.lastHungerIncrease = -2.0f;
        this.prevPacketState = -1L;
    }
    
    public void moveEntityWithHeading(final float moveStrafing, final float moveForward) {
        if (this.sp.x == 0.0 && this.prevMotionX < 0.005) {
            this.sp.x = this.prevMotionX;
        }
        if (this.sp.z == 0.0 && this.prevMotionZ < 0.005) {
            this.sp.z = this.prevMotionZ;
        }
        if (this.sp.bG.b && !SmartMovingSelf.Config.isFlyingEnabled()) {
            final double d3 = this.sp.y;
            final float f2 = this.sp.aR;
            this.sp.aR = 0.05f;
            this.superMoveEntityWithHeading(moveStrafing, moveForward);
            this.sp.y = d3 * 0.6;
            this.sp.aR = f2;
        }
        else {
            this.superMoveEntityWithHeading(moveStrafing, moveForward);
        }
    }
    
    private void superMoveEntityWithHeading(final float moveStrafing, final float moveForward) {
        if (this.isRunning() && !SmartMovingSelf.Config.isRunningEnabled()) {
            this.sp.c(false);
        }
        final boolean wasShortInWater = this.isSwimming || this.isDiving;
        final boolean wasSwimming = this.isSwimming;
        final boolean wasClimbing = this.isClimbing;
        final boolean wasDiving = this.isDiving;
        final boolean wasCeilingClimbing = this.isCeilingClimbing;
        final boolean wasJumpingOutOfWater = this.isJumpingOutOfWater;
        this.handleJumping();
        final double d_S = this.sp.u;
        final double d1_S = this.sp.v;
        final double d2_S = this.sp.w;
        if (this.sp.G) {
            this.lastHorizontalCollisionX = this.sp.u;
            this.lastHorizontalCollisionZ = this.sp.w;
        }
        final float speedFactor = this.getSpeedFactor(moveForward, moveStrafing);
        final boolean isLiquidClimbing = SmartMovingSelf.Config.isFreeClimbingEnabled() && this.sp.T <= 3.0 && this.wantClimbUp && this.sp.G && !this.isDiving;
        final boolean handledSwimming = this.handleSwimming(moveForward, moveStrafing, speedFactor, wasSwimming, wasDiving, isLiquidClimbing, wasJumpingOutOfWater);
        final boolean handledLava = this.handleLava(moveForward, moveStrafing, handledSwimming, isLiquidClimbing);
        final boolean handledAlternativeFlying = this.handleAlternativeFlying(moveForward, moveStrafing, speedFactor, handledSwimming, handledLava);
        this.handleLand(moveForward, moveStrafing, speedFactor, handledSwimming, handledLava, handledAlternativeFlying, wasShortInWater, wasClimbing, wasCeilingClimbing);
        this.handleWallJumping();
        final double diffX = this.sp.u - d_S;
        final double diffY = this.sp.v - d1_S;
        final double diffZ = this.sp.w - d2_S;
        this.sp.j(diffX, diffY, diffZ);
        this.handleExhaustion(diffX, diffY, diffZ);
    }
    
    private float getSpeedFactor() {
        return SmartMovingSelf.Config.enabled ? (SmartMovingSelf.Config._speedFactor.value * SmartMovingSelf.Config.getUserSpeedFactor() * this.getLandMovementFactor() * 10.0f / (this.sp.ai() ? 1.3f : 1.0f)) : 1.0f;
    }
    
    private float getSpeedFactor(float moveForward, final float moveStrafing) {
        float speedFactor = this.getSpeedFactor();
        if (this.sp.br()) {
            float itemFactor = 0.2f;
            if (SmartMovingSelf.Config.enabled) {
                final yc item = yc.g[this.sp.bp().d];
                if (item instanceof zl) {
                    itemFactor = SmartMovingSelf.Config._usageSwordSpeedFactor.value;
                }
                else if (item instanceof wp) {
                    itemFactor = SmartMovingSelf.Config._usageBowSpeedFactor.value;
                }
                else if (item instanceof xx) {
                    itemFactor = SmartMovingSelf.Config._usageFoodSpeedFactor.value;
                }
                else {
                    itemFactor = SmartMovingSelf.Config._usageSpeedFactor.value;
                }
            }
            speedFactor *= itemFactor;
        }
        if (this.isCrawling || (this.isCrawlClimbing && !this.isClimbCrawling)) {
            speedFactor *= SmartMovingSelf.Config._crawlFactor.value;
        }
        else if (this.isSlow) {
            speedFactor *= SmartMovingSelf.Config._sneakFactor.value;
        }
        if (this.isFast) {
            speedFactor *= SmartMovingSelf.Config._sprintFactor.value;
        }
        if (this.isClimbing) {
            if (moveStrafing != 0.0f || moveForward != 0.0f) {
                speedFactor *= SmartMovingSelf.Config._freeClimbingHorizontalSpeedFactor.value;
            }
            else if (this.wantClimbDown && this.isNeighborClimbing && (Math.abs(this.sp.u - this.lastHorizontalCollisionX) >= 0.05 || Math.abs(this.sp.w - this.lastHorizontalCollisionZ) >= 0.05)) {
                moveForward = 0.3f;
                if (this.isVineOnlyClimbing) {
                    if (this.handsEdgeMeta != 0 && this.feetEdgeMeta != 0) {
                        moveForward = 0.0f;
                    }
                    else {
                        final Orientation orientation = Orientation.getOrientation(this.sp, 45.0f, true, false);
                        if (orientation != null) {
                            final float gap = (float)orientation.getHorizontalBorderGap((nn)this.sp);
                            final float minGap = this.sp.O / 2.0f;
                            final float factor = Math.max(0.0f, gap * (1.0f + minGap) - minGap);
                            moveForward = factor * factor * 0.3f;
                        }
                    }
                }
            }
        }
        if (this.isCeilingClimbing) {
            speedFactor *= SmartMovingSelf.Config._ceilingClimbingSpeedFactor.value;
        }
        return speedFactor;
    }
    
    private boolean handleSwimming(final float moveForward, final float moveStrafing, float speedFactor, final boolean wasSwimming, final boolean wasDiving, final boolean isLiquidClimbing, final boolean wasJumpingOutOfWater) {
        boolean handleSwimmingRejected = false;
        final boolean handleSwimming = !this.isFlying && !isLiquidClimbing && (this.sp.H() || (wasSwimming && this.isInLiquid()) || (SmartMovingSelf.Config.isLavaLikeWaterEnabled() && this.sp.J()));
        if (handleSwimming) {
            this.resetClimbing();
            final float wasHeightOffset = this.heightOffset;
            boolean useStandard = !SmartMovingSelf.Config.isSwimmingEnabled() && !SmartMovingSelf.Config.isDivingEnabled();
            if (this.sp.o != null) {
                this.resetSwimming();
                useStandard = true;
            }
            if (useStandard && this.isCrawling) {
                this.standupIfPossible();
            }
            else {
                this.resetHeightOffset();
            }
            if (!useStandard) {
                this.resetSwimming();
                final int i = ls.c(this.sp.u);
                final int j = ls.c(this.sp.E.b);
                final int k = ls.c(this.sp.w);
                boolean swimming = false;
                boolean diving = false;
                boolean dipping = false;
                final double j_offset = this.sp.E.b - j;
                final double totalSwimWaterBorder = this.getMaxPlayerLiquidBetween(this.sp.E.e - 1.8, this.sp.E.e + 1.2);
                final double minPlayerSwimWaterCeiling = this.getMinPlayerSolidBetween(this.sp.E.e - 1.8, this.sp.E.e + 1.2, 0.0);
                final double realTotalSwimWaterBorder = Math.min(totalSwimWaterBorder, minPlayerSwimWaterCeiling);
                final double minPlayerSwimWaterDepth = totalSwimWaterBorder - this.getMaxPlayerSolidBetween(totalSwimWaterBorder - 2.0, totalSwimWaterBorder, 0.0);
                final double realMinPlayerSwimWaterDepth = totalSwimWaterBorder - this.getMaxPlayerSolidBetween(realTotalSwimWaterBorder - 2.0, realTotalSwimWaterBorder, 0.0);
                final double playerSwimWaterBorder = totalSwimWaterBorder - j - j_offset;
                if (this.isCrawling && playerSwimWaterBorder > 0.6499999761581421) {
                    this.standupIfPossible();
                }
                double motionYDiff = 0.0;
                final boolean couldStandUp = playerSwimWaterBorder >= 0.0 && minPlayerSwimWaterDepth <= 1.5;
                boolean diveUp = this.isp.getIsJumpingField();
                boolean diveDown = this.esp.c.d && SmartMovingSelf.Config._diveDownOnSneak.value;
                boolean swimDown = this.esp.c.d && SmartMovingSelf.Config._swimDownOnSneak.value;
                boolean wantShallowSwim = couldStandUp && (wasSwimming || wasDiving);
                if (wantShallowSwim) {
                    final HashSet<Orientation> orientations = Orientation.getClimbingOrientations(this.sp, true, true);
                    final Iterator<Orientation> iterator = orientations.iterator();
                    while (iterator.hasNext() && (wantShallowSwim &= !iterator.next().isTunnelAhead(this.sp.q, i, j, k))) {}
                }
                if (wasSwimming && wantShallowSwim && swimDown) {
                    swimDown = false;
                    this.isFakeShallowWaterSneaking = true;
                }
                if (this.isDiving && diveUp && diveDown) {
                    diveDown = (diveUp = false);
                }
                if (this.isCrawling || this.isClimbCrawling || this.isCrawlClimbing) {
                    this.isDipping = true;
                }
                else if (playerSwimWaterBorder >= 0.0 && playerSwimWaterBorder <= 2.0) {
                    final double offset = playerSwimWaterBorder + 0.1625;
                    final boolean moveSwim = (this.sp.B < 0.0f && this.esp.c.b > 0.0f) || (this.sp.B > 0.0f && this.esp.c.b < 0.0f);
                    if (diveUp || moveSwim || wantShallowSwim) {
                        if (offset < 1.4) {
                            dipping = true;
                            if (offset < 1.0) {
                                motionYDiff = -0.02;
                            }
                            else {
                                motionYDiff = -0.01;
                            }
                        }
                        else if (offset < 1.9) {
                            swimming = true;
                            if (swimDown) {
                                motionYDiff = -0.05 * (this.isFast ? SmartMovingSelf.Config._sprintFactor.value : 1.0f);
                            }
                            else if (offset < 1.5) {
                                motionYDiff = -0.02;
                            }
                            if (offset < 1.6) {
                                motionYDiff = -0.01;
                            }
                            else if (offset < 1.62) {
                                motionYDiff = -0.005;
                            }
                            else if (offset < 1.64) {
                                motionYDiff = -0.0025;
                            }
                            else if (offset < 1.66) {
                                motionYDiff = -0.00125;
                            }
                            else if (offset < 1.664) {
                                motionYDiff = -6.25E-4;
                            }
                            else if (offset < 1.668) {
                                motionYDiff = 0.0;
                            }
                            else if (offset < 1.672) {
                                motionYDiff = 6.25E-4;
                            }
                            else if (offset < 1.676) {
                                motionYDiff = 0.00125;
                            }
                            else if (offset < 1.68) {
                                motionYDiff = 0.0025;
                            }
                            else if (offset < 1.7) {
                                motionYDiff = 0.005;
                            }
                            else if (offset < 1.8) {
                                motionYDiff = 0.01;
                            }
                            else {
                                motionYDiff = 0.02;
                            }
                        }
                        else {
                            diving = true;
                            if (diveUp) {
                                motionYDiff = 0.05 * (this.isFast ? SmartMovingSelf.Config._sprintFactor.value : 1.0f);
                            }
                            else if (diveDown) {
                                motionYDiff = 0.01 - 0.1 * speedFactor;
                            }
                            else {
                                motionYDiff = (moveSwim ? 0.04 : 0.02);
                            }
                        }
                    }
                    else if (offset < 1.5) {
                        dipping = true;
                        if (offset < 1.0) {
                            motionYDiff = -0.02;
                        }
                        else {
                            motionYDiff = -0.02;
                        }
                    }
                    else {
                        diving = true;
                        if (diveDown) {
                            motionYDiff = 0.01 - 0.1 * speedFactor;
                        }
                        else if (offset < 1.8) {
                            motionYDiff = -0.02;
                        }
                        else if (offset < 1.82) {
                            motionYDiff = -0.01;
                        }
                        else if (offset < 1.84) {
                            motionYDiff = -0.005;
                        }
                        else if (offset < 1.86) {
                            motionYDiff = -0.0025;
                        }
                        else if (offset < 1.864) {
                            motionYDiff = -0.00125;
                        }
                        else if (offset < 1.868) {
                            motionYDiff = 0.0;
                        }
                        else if (offset < 1.872) {
                            motionYDiff = 0.00125;
                        }
                        else if (offset < 1.876) {
                            motionYDiff = 0.0025;
                        }
                        else if (offset < 1.88) {
                            motionYDiff = 0.005;
                        }
                        else if (offset < 1.9) {
                            motionYDiff = 0.01;
                        }
                        else {
                            motionYDiff = 0.01;
                        }
                    }
                }
                else if (playerSwimWaterBorder > 2.0) {
                    diving = true;
                    if (diveUp) {
                        if (this.isFast && playerSwimWaterBorder < 2.5 && this.sp.q.c(i, j + 3, k)) {
                            motionYDiff = 0.11 / SmartMovingSelf.Config._sprintFactor.value;
                        }
                        else {
                            motionYDiff = 0.01 + 0.1 * speedFactor;
                        }
                    }
                    else if (diveDown) {
                        motionYDiff = 0.01 - 0.1 * speedFactor;
                    }
                    else {
                        motionYDiff = 0.01;
                    }
                }
                else {
                    handleSwimmingRejected = true;
                }
                this.dippingDepth = (float)playerSwimWaterBorder;
                final float playerCrawlWaterBorder = this.dippingDepth + wasHeightOffset;
                if ((this.isCrawling || this.isSliding) && playerCrawlWaterBorder < 1.0f) {
                    if (playerCrawlWaterBorder < 0.65f) {
                        this.setHeightOffset(wasHeightOffset);
                        handleSwimmingRejected = true;
                    }
                    else {
                        if (wantShallowSwim) {
                            this.move(0.0, 0.1, 0.0, true);
                        }
                        this.isCrawling = false;
                        this.isDiving = false;
                        this.isSwimming = true;
                        this.isDipping = false;
                    }
                }
                if (!handleSwimmingRejected) {
                    swimming = (!useStandard && swimming && SmartMovingSelf.Config.isSwimmingEnabled());
                    diving = (!useStandard && diving && SmartMovingSelf.Config.isDivingEnabled());
                    dipping = (!useStandard && dipping && SmartMovingSelf.Config.isSwimmingEnabled());
                    useStandard = (!swimming && !diving && !dipping);
                    if (!useStandard) {
                        if (diveUp) {
                            final uf sp = this.sp;
                            sp.y -= 0.03999999910593033;
                        }
                        if (swimming) {
                            final uf sp2 = this.sp;
                            sp2.x *= 0.85;
                            final uf sp3 = this.sp;
                            sp3.y *= 0.85;
                            final uf sp4 = this.sp;
                            sp4.z *= 0.85;
                        }
                        else if (diving) {
                            final uf sp5 = this.sp;
                            sp5.x *= 0.83;
                            final uf sp6 = this.sp;
                            sp6.y *= 0.83;
                            final uf sp7 = this.sp;
                            sp7.z *= 0.83;
                        }
                        else if (dipping) {
                            final uf sp8 = this.sp;
                            sp8.x *= 0.8;
                            final uf sp9 = this.sp;
                            sp9.y *= 0.83;
                            final uf sp10 = this.sp;
                            sp10.z *= 0.8;
                        }
                        else {
                            final uf sp11 = this.sp;
                            sp11.x *= 0.9;
                            final uf sp12 = this.sp;
                            sp12.y *= 0.85;
                            final uf sp13 = this.sp;
                            sp13.z *= 0.9;
                        }
                        boolean moveFlying = true;
                        final boolean levitating = diving && !diveUp && !diveDown && moveStrafing == 0.0f && moveForward == 0.0f;
                        if (diving) {
                            speedFactor *= SmartMovingSelf.Config._diveSpeedFactor.value;
                        }
                        if (swimming) {
                            speedFactor *= SmartMovingSelf.Config._swimSpeedFactor.value;
                        }
                        if (swimming || diving) {
                            ++this.waterMovementTicks;
                        }
                        else {
                            this.waterMovementTicks = 0;
                        }
                        final boolean wantJumpOutOfWater = (moveForward != 0.0f || moveStrafing != 0.0f) && this.sp.G && diveUp && !this.isSlow;
                        this.isJumpingOutOfWater = (wantJumpOutOfWater && (this.waterMovementTicks > 10 || this.sp.F || wasJumpingOutOfWater));
                        if (diving) {
                            if (diveUp || diveDown || levitating) {
                                this.sp.y = (this.sp.y + motionYDiff) * 0.6;
                            }
                            else {
                                this.moveFlying((float)motionYDiff, moveStrafing, moveForward, 0.02f * speedFactor, SmartMovingSelf.Options._diveControlVertical.value);
                            }
                            moveFlying = false;
                        }
                        else if (swimming && swimDown) {
                            this.sp.y = (this.sp.y + motionYDiff) * 0.6;
                        }
                        else if (this.isJumpingOutOfWater) {
                            this.sp.y = 0.30000001192092896;
                        }
                        else {
                            final uf sp14 = this.sp;
                            sp14.y += motionYDiff;
                        }
                        this.isDiving = diving;
                        this.isLevitating = levitating;
                        this.isSwimming = swimming;
                        this.isShallowDiveOrSwim = (couldStandUp && (this.isDiving || this.isSwimming));
                        this.isDipping = dipping;
                        if (this.isDiving || this.isSwimming) {
                            this.setHeightOffset(-1.0f);
                        }
                        if (this.isShallowDiveOrSwim && realMinPlayerSwimWaterDepth < 0.550000011920929) {
                            if (this.isSlow) {
                                this.setHeightOffset(-1.0f);
                                this.isCrawling = true;
                                this.isDiving = false;
                                this.isSwimming = false;
                                this.isShallowDiveOrSwim = false;
                                this.isDipping = true;
                            }
                            else {
                                this.resetHeightOffset();
                                this.sp.d(0.0, this.getMaxPlayerSolidBetween(this.sp.E.b, this.sp.E.e, 0.0) - this.sp.E.b, 0.0);
                                this.isCrawling = false;
                                this.isDiving = false;
                                this.isSwimming = false;
                                this.isShallowDiveOrSwim = false;
                                this.isDipping = true;
                            }
                        }
                        if (moveFlying) {
                            this.sp.a(moveStrafing, moveForward, 0.02f * speedFactor);
                        }
                        this.sp.d(this.sp.x, this.sp.y, this.sp.z);
                    }
                }
            }
            else {
                this.isDiving = false;
                this.isSwimming = false;
                this.isShallowDiveOrSwim = false;
                this.isDipping = false;
                this.isStillSwimmingJump = false;
            }
            if (useStandard) {
                this.resetSwimming();
                if (this.isCrawling) {
                    this.setHeightOffset(wasHeightOffset);
                }
                final double dY = this.sp.v;
                this.sp.a(moveStrafing, moveForward, 0.02f * speedFactor);
                this.sp.d(this.sp.x, this.sp.y, this.sp.z);
                final uf sp15 = this.sp;
                sp15.x *= 0.800000011920929;
                final uf sp16 = this.sp;
                sp16.y *= 0.800000011920929;
                final uf sp17 = this.sp;
                sp17.z *= 0.800000011920929;
                final uf sp18 = this.sp;
                sp18.y -= 0.02;
                if (this.sp.G && this.sp.c(this.sp.x, this.sp.y + 0.6000000238418579 - this.sp.v + dY, this.sp.z)) {
                    this.sp.y = 0.30000001192092896;
                }
            }
        }
        return handleSwimming && !handleSwimmingRejected;
    }
    
    private boolean handleLava(final float moveForward, final float moveStrafing, final boolean handledSwimming, final boolean isLiquidClimbing) {
        final boolean handleLava = !this.isFlying && !handledSwimming && !isLiquidClimbing && this.sp.J();
        if (handleLava) {
            this.standupIfPossible();
            this.resetClimbing();
            this.resetSwimming();
            final double d1 = this.sp.v;
            this.sp.a(moveStrafing, moveForward, 0.02f);
            this.sp.d(this.sp.x, this.sp.y, this.sp.z);
            final uf sp = this.sp;
            sp.x *= 0.5;
            final uf sp2 = this.sp;
            sp2.y *= 0.5;
            final uf sp3 = this.sp;
            sp3.z *= 0.5;
            final uf sp4 = this.sp;
            sp4.y -= 0.02;
            if (this.sp.G && this.sp.c(this.sp.x, this.sp.y + 0.6000000238418579 - this.sp.v + d1, this.sp.z)) {
                this.sp.y = 0.30000001192092896;
            }
        }
        return handleLava;
    }
    
    private boolean handleAlternativeFlying(final float moveForward, final float moveStrafing, final float speedFactor, final boolean handledSwimming, final boolean handledLava) {
        final boolean handleAlternativeFlying = !handledSwimming && !handledLava && this.sp.bG.b && SmartMovingSelf.Config.isFlyingEnabled();
        if (handleAlternativeFlying) {
            this.resetSwimming();
            this.resetClimbing();
            float moveUpward = 0.0f;
            if (this.esp.c.d) {
                final uf sp = this.sp;
                sp.y += 0.15;
                moveUpward -= 0.98f;
            }
            if (this.esp.c.c) {
                final uf sp2 = this.sp;
                sp2.y -= 0.15;
                moveUpward += 0.98f;
            }
            this.moveFlying(moveUpward, moveStrafing, moveForward, speedFactor * 0.05f * SmartMovingSelf.Config._flyingSpeedFactor.value, SmartMovingSelf.Options._flyControlVertical.value);
            this.sp.d(this.sp.x, this.sp.y, this.sp.z);
            final uf sp3 = this.sp;
            sp3.x *= 0.9100000262260437;
            final uf sp4 = this.sp;
            sp4.y *= 0.9100000262260437;
            final uf sp5 = this.sp;
            sp5.z *= 0.9100000262260437;
        }
        return handleAlternativeFlying;
    }
    
    private void handleLand(final float moveForward, final float moveStrafing, final float speedFactor, final boolean handledSwimming, final boolean handledLava, final boolean handledAlternativeFlying, final boolean wasShortInWater, final boolean wasClimbing, final boolean wasCeilingClimbing) {
        if (!handledSwimming && !handledLava && !handledAlternativeFlying) {
            this.resetSwimming();
            if (!this.grabButton.Pressed) {
                this.fromSwimmingOrDiving(wasShortInWater);
            }
            final boolean isOnLadder = this.isOnLadder(this.isClimbCrawling);
            final boolean isOnVine = this.isOnVine(this.isClimbCrawling);
            final float horizontalDamping = this.landMotion(moveForward, moveStrafing, speedFactor, isOnLadder, isOnVine);
            final boolean crawlingThroughWeb = (this.isCrawling || this.isCrawlClimbing) && this.isp.getIsInWebField();
            this.move(this.sp.x, this.sp.y, this.sp.z, crawlingThroughWeb);
            this.handleClimbing(isOnLadder, isOnVine, wasClimbing);
            this.handleCeilingClimbing(wasCeilingClimbing);
            this.setLandMotions(horizontalDamping);
        }
        this.landMotionPost(wasShortInWater);
    }
    
    private void move(final double motionX, final double motionY, final double motionZ, final boolean relocate) {
        final boolean isInWeb = this.isp.getIsInWebField();
        if (relocate) {
            this.isp.setIsInWebField(false);
        }
        this.sp.d(motionX, motionY, motionZ);
        if (relocate) {
            this.isp.setIsInWebField(isInWeb);
        }
    }
    
    private float landMotion(final float moveForward, final float moveStrafing, float speedFactor, final boolean isOnLadder, final boolean isOnVine) {
        float horizontalDamping;
        if (this.sp.F && !this.isJumping) {
            final int i = this.sp.q.a(ls.c(this.sp.u), ls.c(this.sp.E.b) - 1, ls.c(this.sp.w));
            if (i > 0) {
                horizontalDamping = aqz.s[i].cV * 0.91f;
            }
            else {
                horizontalDamping = 0.546f;
            }
            if (this.esp.c.c && this.isFast) {
                final SmartMovingClientConfig config = SmartMovingSelf.Config;
                final SmartMovingClientConfig config2 = SmartMovingSelf.Config;
                final int speed = 0;
                final SmartMovingClientConfig config3 = SmartMovingSelf.Config;
                if (config.isJumpingEnabled(speed, 0)) {
                    speedFactor *= SmartMovingSelf.Config._sprintJumpVerticalFactor.value;
                }
            }
        }
        else {
            horizontalDamping = 0.91f;
        }
        if (this.isClimbing && this.climbingUpIsBlockedByLadder()) {
            this.sp.a(0.0f, -1.0f, 0.07f);
        }
        else if (this.isClimbing && this.climbingUpIsBlockedByTrapDoor()) {
            if (this.sp.e()) {
                this.sp.a(0.0f, -1.0f, 0.09f);
            }
            else {
                this.sp.a(0.0f, -1.0f, 0.09f);
            }
        }
        else if (this.isClimbing && this.climbingUpIsBlockedByCobbleStoneWall()) {
            this.sp.a(0.0f, -1.0f, 0.07f);
        }
        else if (!this.isSliding) {
            if (this.isHeadJumping) {
                speedFactor *= SmartMovingSelf.Config._headJumpControlFactor.value;
            }
            else if (SmartMovingSelf.Config.enabled && !this.sp.F && !this.sp.bG.b && !this.isFlying) {
                speedFactor *= SmartMovingSelf.Config._jumpControlFactor.value;
            }
            final float f3 = 0.1627714f / (horizontalDamping * horizontalDamping * horizontalDamping);
            final float f4 = this.sp.F ? (this.getLandMovementFactor() * f3) : this.sp.aR;
            final float rawSpeed = this.sp.ai() ? (f4 / 1.3f) : f4;
            if (SmartMovingSelf.Config.isRunningEnabled() && this.isRunning() && !this.isFast) {
                speedFactor *= SmartMovingSelf.Config._runFactor.value;
            }
            this.sp.a(moveStrafing, moveForward, rawSpeed * speedFactor);
        }
        if (this.sp.F && !this.isJumping) {
            final int j = this.sp.q.a(ls.c(this.sp.u), ls.c(this.sp.E.b) - 1, ls.c(this.sp.w));
            if (j > 0) {
                final float slipperiness = aqz.s[j].cV;
                if (this.isSliding) {
                    horizontalDamping = 1.0f / ((1.0f / slipperiness - 1.0f) / 25.0f * SmartMovingSelf.Config._slideSlipperinessFactor.value + 1.0f) * 0.98f;
                    if (moveStrafing != 0.0f && SmartMovingSelf.Config._slideControlDegrees.value > 0.0f) {
                        double angle = -Math.atan(this.sp.x / this.sp.z);
                        if (!Double.isNaN(angle)) {
                            if (this.sp.z < 0.0) {
                                angle += 3.141592653589793;
                            }
                            angle -= SmartMovingSelf.Config._slideControlDegrees.value / 57.295776f * Math.signum(moveStrafing);
                            final double hMotion = Math.sqrt(this.sp.x * this.sp.x + this.sp.z * this.sp.z);
                            this.sp.x = hMotion * -Math.sin(angle);
                            this.sp.z = hMotion * Math.cos(angle);
                        }
                    }
                }
                else {
                    horizontalDamping = slipperiness * 0.91f;
                }
            }
            else {
                horizontalDamping = 0.546f;
            }
        }
        else if (this.isAerodynamic) {
            horizontalDamping = 0.999f;
        }
        else {
            horizontalDamping = 0.91f;
        }
        if (isOnLadder || isOnVine) {
            final float f5 = 0.15f;
            if (this.sp.x < -f5) {
                this.sp.x = -f5;
            }
            if (this.sp.x > f5) {
                this.sp.x = f5;
            }
            if (this.sp.z < -f5) {
                this.sp.z = -f5;
            }
            if (this.sp.z > f5) {
                this.sp.z = f5;
            }
            final boolean notTotalFreeClimbing = (!this.isClimbing && isOnLadder && !SmartMovingSelf.Config.isTotalFreeLadderClimb()) || (isOnVine && !SmartMovingSelf.Config.isTotalFreeVineClimb());
            if (notTotalFreeClimbing) {
                this.sp.T = 0.0f;
                this.sp.y = Math.max(this.sp.y, -0.15 * this.getSpeedFactor());
            }
            if (SmartMovingSelf.Config.isFreeBaseClimb()) {
                if (this.esp.c.d && this.sp.y < 0.0 && !this.sp.F && notTotalFreeClimbing) {
                    this.sp.y = 0.0;
                }
            }
            else if (this.isp.localIsSneaking() && this.sp.y < 0.0) {
                this.sp.y = 0.0;
            }
        }
        else if (SmartMovingSelf.Config.isFreeClimbAutoLaddderEnabled() && moveForward > 0.0f) {
            final int j = ls.c(this.sp.E.b);
            final double jGap = this.sp.E.b - j;
            if (jGap < 0.1) {
                final int k = ls.c(this.sp.u);
                final int l = ls.c(this.sp.w);
                if (Orientation.isLadder(this.sp.q.a(k, j - 1, l))) {
                    this.sp.y = Math.max(this.sp.y, 0.0);
                }
            }
        }
        return horizontalDamping;
    }
    
    private void handleClimbing(final boolean isOnLadder, final boolean isOnVine, final boolean wasClimbing) {
        this.resetClimbing();
        final boolean isOnLadderOrVine = isOnLadder || isOnVine;
        if (SmartMovingSelf.Config.isStandardBaseClimb() && this.sp.G && isOnLadderOrVine) {
            this.sp.y = 0.2 * this.getSpeedFactor();
        }
        if (SmartMovingSelf.Config.isSimpleBaseClimb() && this.sp.G && isOnLadderOrVine) {
            final int i = ls.c(this.sp.u);
            final int j = ls.c(this.sp.E.b);
            final int k = ls.c(this.sp.w);
            final boolean feet = Orientation.isClimbable(this.sp.q, i, j, k);
            final boolean hands = Orientation.isClimbable(this.sp.q, i, j + 1, k);
            if (feet && hands) {
                this.sp.y = 0.2;
            }
            else if (feet) {
                this.sp.y = 0.2;
            }
            else if (hands) {
                this.sp.y = 0.1;
            }
            else {
                this.sp.y = 0.0;
            }
            final uf sp = this.sp;
            sp.y *= this.getSpeedFactor();
        }
        if (SmartMovingSelf.Config.isSmartBaseClimb() || SmartMovingSelf.Config.isFreeClimbingEnabled()) {
            final double id = this.sp.u;
            double jd = this.sp.E.b;
            final double kd = this.sp.w;
            final int l = ls.c(id);
            final int m = ls.c(jd);
            final int k2 = ls.c(kd);
            if (SmartMovingSelf.Config.isSmartBaseClimb() && isOnLadderOrVine && this.sp.G) {
                final boolean feet2 = Orientation.isClimbable(this.sp.q, l, m, k2);
                final boolean hands2 = Orientation.isClimbable(this.sp.q, l, m + 1, k2);
                if (feet2 && hands2) {
                    this.sp.y = 0.2;
                }
                else if (feet2) {
                    final boolean handsSubstitute = Orientation.PZ.isHandsLadderSubstitute(this.sp.q, l, m + 1, k2) || Orientation.NZ.isHandsLadderSubstitute(this.sp.q, l, m + 1, k2) || Orientation.ZP.isHandsLadderSubstitute(this.sp.q, l, m + 1, k2) || Orientation.ZN.isHandsLadderSubstitute(this.sp.q, l, m + 1, k2);
                    if (handsSubstitute) {
                        this.sp.y = 0.2;
                    }
                    else {
                        this.sp.y = 0.1;
                    }
                }
                else if (hands2) {
                    final boolean feetSubstitute = Orientation.ZZ.isFeetLadderSubstitute(this.sp.q, l, m, k2) || Orientation.PZ.isFeetLadderSubstitute(this.sp.q, l, m, k2) || Orientation.NZ.isFeetLadderSubstitute(this.sp.q, l, m, k2) || Orientation.ZP.isFeetLadderSubstitute(this.sp.q, l, m, k2) || Orientation.ZN.isFeetLadderSubstitute(this.sp.q, l, m, k2);
                    if (feetSubstitute) {
                        this.sp.y = 0.2;
                    }
                    else {
                        this.sp.y = 0.1;
                    }
                }
                else {
                    this.sp.y = 0.0;
                }
                final uf sp2 = this.sp;
                sp2.y *= this.getSpeedFactor();
            }
            if (SmartMovingSelf.Config.isFreeClimbingEnabled() && this.sp.T <= SmartMovingSelf.Config._freeClimbFallMaximumDistance.value && (!isOnLadderOrVine || SmartMovingSelf.Config.isFreeBaseClimb())) {
                final boolean exhaustionAllowsClimbing = !SmartMovingSelf.Config.isClimbExhaustionEnabled() || (this.exhaustion <= SmartMovingSelf.Config._climbExhaustionStop.value && (wasClimbing || this.exhaustion <= SmartMovingSelf.Config._climbExhaustionStart.value));
                boolean preferClimb = false;
                if (this.wantClimbUp || this.wantClimbDown) {
                    if (SmartMovingSelf.Config.isClimbExhaustionEnabled()) {
                        this.maxExhaustionForAction = Math.min(this.maxExhaustionForAction, SmartMovingSelf.Config._climbExhaustionStop.value);
                        this.maxExhaustionToStartAction = Math.min(this.maxExhaustionToStartAction, SmartMovingSelf.Config._climbExhaustionStart.value);
                    }
                    if (exhaustionAllowsClimbing) {
                        preferClimb = true;
                    }
                }
                if (preferClimb) {
                    final boolean isSmallClimbing = this.isCrawling || this.isSliding;
                    if (this.isClimbCrawling || this.isCrawlClimbing || isSmallClimbing) {
                        --jd;
                    }
                    float rotation = this.sp.A % 360.0f;
                    if (rotation < 0.0f) {
                        rotation += 360.0f;
                    }
                    final double jh = jd * 2.0 + 1.0;
                    HandsClimbing handsClimbing = HandsClimbing.None;
                    FeetClimbing feetClimbing = FeetClimbing.None;
                    SmartMovingSelf.inout_handsClimbing[0] = handsClimbing;
                    SmartMovingSelf.inout_feetClimbing[0] = feetClimbing;
                    SmartMovingSelf.out_handsClimbGap.reset();
                    SmartMovingSelf.out_feetClimbGap.reset();
                    Orientation.PZ.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                    Orientation.NZ.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                    Orientation.ZP.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                    Orientation.ZN.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                    handsClimbing = SmartMovingSelf.inout_handsClimbing[0];
                    feetClimbing = SmartMovingSelf.inout_feetClimbing[0];
                    this.isNeighborClimbing = (handsClimbing != HandsClimbing.None || feetClimbing != FeetClimbing.None);
                    this.hasNeighborClimbGap = (SmartMovingSelf.out_handsClimbGap.CanStand || SmartMovingSelf.out_feetClimbGap.CanStand);
                    this.hasNeighborClimbCrawlGap = (SmartMovingSelf.out_handsClimbGap.MustCrawl || SmartMovingSelf.out_feetClimbGap.MustCrawl);
                    if (!isSmallClimbing) {
                        Orientation.PP.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                        Orientation.NP.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                        Orientation.NN.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                        Orientation.PN.seekClimbGap(rotation, this.sp.q, l, id, jh, k2, kd, this.isClimbCrawling, this.isCrawlClimbing, isSmallClimbing, SmartMovingSelf.inout_handsClimbing, SmartMovingSelf.inout_feetClimbing, SmartMovingSelf.out_handsClimbGap, SmartMovingSelf.out_feetClimbGap);
                    }
                    handsClimbing = SmartMovingSelf.inout_handsClimbing[0];
                    feetClimbing = SmartMovingSelf.inout_feetClimbing[0];
                    this.hasClimbGap = (SmartMovingSelf.out_handsClimbGap.CanStand || SmartMovingSelf.out_feetClimbGap.CanStand);
                    this.hasClimbCrawlGap = (SmartMovingSelf.out_handsClimbGap.MustCrawl || SmartMovingSelf.out_feetClimbGap.MustCrawl);
                    if (handsClimbing == HandsClimbing.BottomHold && Orientation.isLadder(this.sp.q.a(l, m + 2, k2))) {
                        final Orientation ladderOrientation = Orientation.getKnownLadderOrientation(this.sp.q, l, m + 2, k2);
                        final int remote_i = l + ladderOrientation._i;
                        final int remote_k = k2 + ladderOrientation._k;
                        if (!this.sp.q.g(remote_i, m, remote_k).a() && !this.sp.q.g(remote_i, m + 1, remote_k).a()) {
                            handsClimbing = HandsClimbing.None;
                        }
                    }
                    if (!this.grabButton.Pressed && handsClimbing == HandsClimbing.Up && feetClimbing == FeetClimbing.None && !this.sp.G && this.sp.q.c(l, m, k2) && this.sp.q.c(l, m + 1, k2)) {
                        handsClimbing = HandsClimbing.None;
                    }
                    if (feetClimbing.IsRelevant() || handsClimbing.IsRelevant()) {
                        Label_2637: {
                            if (this.wantClimbUp) {
                                if (this.isSliding && handsClimbing.IsRelevant()) {
                                    this.isSliding = false;
                                    this.isCrawling = true;
                                }
                                handsClimbing = handsClimbing.ToUp();
                                if (feetClimbing == FeetClimbing.FastUp && (handsClimbing != HandsClimbing.None || !this.sp.F || SmartMovingSelf.out_feetClimbGap.BlockId == aqz.X.cF)) {
                                    this.setShouldClimbSpeed(0.2, 0, 1);
                                }
                                else if ((this.hasClimbGap || this.hasClimbCrawlGap) && handsClimbing == HandsClimbing.FastUp && (feetClimbing == FeetClimbing.None || feetClimbing == FeetClimbing.BaseWithHands)) {
                                    this.setShouldClimbSpeed((feetClimbing == FeetClimbing.None) ? 0.1 : 0.2, 2, 1);
                                }
                                else if (feetClimbing.IsRelevant() && handsClimbing.IsRelevant() && (feetClimbing != FeetClimbing.BaseHold || handsClimbing != HandsClimbing.Sink) && (handsClimbing != HandsClimbing.Sink || feetClimbing != FeetClimbing.TopWithHands) && (handsClimbing != HandsClimbing.TopHold || feetClimbing != FeetClimbing.TopWithHands)) {
                                    this.setShouldClimbSpeed(0.14, ((this.hasClimbGap || this.hasClimbCrawlGap) && (handsClimbing != HandsClimbing.Sink || feetClimbing != FeetClimbing.BaseWithHands)) ? 2 : 1, 1);
                                }
                                else if (handsClimbing.IsUp()) {
                                    this.setShouldClimbSpeed(0.1);
                                }
                                else if (handsClimbing == HandsClimbing.TopHold || feetClimbing == FeetClimbing.BaseHold || (feetClimbing == FeetClimbing.SlowUpWithHoldWithoutHands && handsClimbing == HandsClimbing.None)) {
                                    if (this.jumpButton.StartPressed) {
                                        int type2;
                                        if (feetClimbing != FeetClimbing.None) {
                                            final SmartMovingClientConfig config = SmartMovingSelf.Config;
                                            type2 = 5;
                                        }
                                        else {
                                            final SmartMovingClientConfig config2 = SmartMovingSelf.Config;
                                            type2 = 6;
                                        }
                                        if (this.isClimbJumping = this.tryJump(type2, null, null, null)) {
                                            break Label_2637;
                                        }
                                    }
                                    if ((handsClimbing == HandsClimbing.Sink && feetClimbing == FeetClimbing.BaseHold) || (handsClimbing == HandsClimbing.TopHold && feetClimbing == FeetClimbing.TopWithHands)) {
                                        this.setShouldClimbSpeed(0.08, 2, 1);
                                    }
                                    else {
                                        this.setShouldClimbSpeed(0.08);
                                    }
                                }
                                else if (handsClimbing == HandsClimbing.Sink || (feetClimbing == FeetClimbing.SlowUpWithSinkWithoutHands && handsClimbing == HandsClimbing.None)) {
                                    this.setShouldClimbSpeed(0.05);
                                }
                            }
                            else if (this.wantClimbDown) {
                                handsClimbing = handsClimbing.ToDown();
                                if (handsClimbing == HandsClimbing.BottomHold && !feetClimbing.IsIndependentlyRelevant()) {
                                    this.setShouldClimbSpeed(0.08);
                                }
                                else if (handsClimbing.IsRelevant()) {
                                    if (feetClimbing == FeetClimbing.FastUp) {
                                        this.setShouldClimbSpeed(0.01, 0, 1);
                                    }
                                    else if (feetClimbing == FeetClimbing.SlowUpWithHoldWithoutHands) {
                                        this.setShouldClimbSpeed(0.01);
                                    }
                                    else if (feetClimbing == FeetClimbing.TopWithHands) {
                                        this.setShouldClimbSpeed(0.01);
                                    }
                                    else if (feetClimbing == FeetClimbing.BaseWithHands || feetClimbing == FeetClimbing.BaseHold) {
                                        if ((handsClimbing != HandsClimbing.None && handsClimbing != HandsClimbing.Up) || (handsClimbing == HandsClimbing.Up && feetClimbing == FeetClimbing.BaseHold)) {
                                            this.setShouldClimbSpeed(0.01);
                                        }
                                        else {
                                            this.setShouldClimbSpeed(0.05);
                                        }
                                    }
                                    else {
                                        this.setShouldClimbSpeed(0.05, (handsClimbing == HandsClimbing.FastUp) ? 2 : 1, 0);
                                    }
                                }
                                if (this.isClimbHolding) {
                                    this.setOnlyShouldClimbSpeed(0.08);
                                    if (this.jumpButton.StartPressed) {
                                        final boolean handsOnly = feetClimbing != FeetClimbing.None;
                                        int n = 0;
                                        Label_2572: {
                                            Label_2552: {
                                                if (SmartMovingSelf.Options._climbJumpBackHeadOnGrab.value) {
                                                    if (!this.grabButton.Pressed) {
                                                        break Label_2552;
                                                    }
                                                }
                                                else if (this.grabButton.Pressed) {
                                                    break Label_2552;
                                                }
                                                if (handsOnly) {
                                                    final SmartMovingClientConfig config3 = SmartMovingSelf.Config;
                                                    n = 9;
                                                    break Label_2572;
                                                }
                                                final SmartMovingClientConfig config4 = SmartMovingSelf.Config;
                                                n = 10;
                                                break Label_2572;
                                            }
                                            if (handsOnly) {
                                                final SmartMovingClientConfig config5 = SmartMovingSelf.Config;
                                                n = 7;
                                            }
                                            else {
                                                final SmartMovingClientConfig config6 = SmartMovingSelf.Config;
                                                n = 8;
                                            }
                                        }
                                        final int type = n;
                                        final float jumpAngle = this.sp.A + 180.0f;
                                        if (this.tryJump(type, null, null, jumpAngle)) {
                                            this.continueWallJumping = !this.isHeadJumping;
                                            this.isClimbing = false;
                                            this.sp.A = jumpAngle;
                                            this.onStartClimbBackJump();
                                        }
                                    }
                                }
                            }
                        }
                        if (this.isClimbing) {
                            this.handleCrash(SmartMovingSelf.Config._freeClimbFallDamageStartDistance.value, SmartMovingSelf.Config._freeClimbFallDamageFactor.value);
                        }
                        if (this.wantClimbUp || this.wantClimbDown) {
                            if (handsClimbing == HandsClimbing.None) {
                                this.actualHandsClimbType = 0;
                            }
                            else if (feetClimbing == FeetClimbing.None) {
                                this.actualFeetClimbType = 0;
                            }
                            this.handsEdgeBlockId = SmartMovingSelf.out_handsClimbGap.BlockId;
                            this.handsEdgeMeta = SmartMovingSelf.out_handsClimbGap.Meta;
                            this.feetEdgeBlockId = SmartMovingSelf.out_feetClimbGap.BlockId;
                            this.feetEdgeMeta = SmartMovingSelf.out_feetClimbGap.Meta;
                        }
                    }
                }
                if (SmartMovingOptions.hasRedPowerWire && !this.isClimbing && this.wantClimbUp && wasClimbing) {
                    this.sp.y = 0.15;
                }
                this.isHandsVineClimbing = (this.isClimbing && this.handsEdgeBlockId == aqz.bz.cF);
                this.isFeetVineClimbing = (this.isClimbing && this.feetEdgeBlockId == aqz.bz.cF);
                this.isVineAnyClimbing = (this.isHandsVineClimbing || this.isFeetVineClimbing);
                this.isVineOnlyClimbing = (this.isVineAnyClimbing && (this.handsEdgeBlockId == -1 || this.handsEdgeBlockId == aqz.bz.cF) && (this.feetEdgeBlockId == -1 || this.feetEdgeBlockId == aqz.bz.cF));
            }
        }
    }
    
    private void handleCeilingClimbing(final boolean wasCeilingClimbing) {
        final boolean exhaustionAllowsClimbCeiling = !SmartMovingSelf.Config.isCeilingClimbExhaustionEnabled() || (this.exhaustion <= SmartMovingSelf.Config._ceilingClimbExhaustionStop.value && (wasCeilingClimbing || this.exhaustion <= SmartMovingSelf.Config._ceilingClimbExhaustionStart.value));
        final boolean climbCeilingCrawlingStartConflict = !SmartMovingSelf.Config.isFreeClimbingEnabled() && this.isCrawling && !this.wasCrawling;
        final boolean couldClimbCeiling = this.wantClimbCeiling && !this.isClimbing && (!this.isCrawling || climbCeilingCrawlingStartConflict) && !this.isCrawlClimbing;
        if (couldClimbCeiling && SmartMovingSelf.Config.isCeilingClimbExhaustionEnabled()) {
            this.maxExhaustionForAction = Math.min(this.maxExhaustionForAction, SmartMovingSelf.Config._ceilingClimbExhaustionStop.value);
            this.maxExhaustionToStartAction = Math.min(this.maxExhaustionToStartAction, SmartMovingSelf.Config._ceilingClimbExhaustionStart.value);
        }
        if (couldClimbCeiling && exhaustionAllowsClimbCeiling) {
            final double id = this.sp.u;
            final double jd = this.sp.E.e + (climbCeilingCrawlingStartConflict ? 1.0f : 0.0f);
            final double kd = this.sp.w;
            final int i = ls.c(id);
            final int j = ls.c(jd);
            final int k = ls.c(kd);
            final int topBlockId = this.supportsCeilingClimbing(i, j, k);
            final int bottomBlockId = this.supportsCeilingClimbing(i, j + 1, k);
            final boolean topCeilingClimbing = topBlockId > -1;
            final boolean bottomCeilingClimbing = bottomBlockId > -1;
            if (topCeilingClimbing || bottomCeilingClimbing) {
                double jgap = 1.0 - jd + j;
                if (bottomCeilingClimbing) {
                    ++jgap;
                }
                final double actuallySolidHeight = this.getMinPlayerSolidBetween(jd, jd + 0.6, 0.2);
                if (jgap < 1.9 && actuallySolidHeight < jd + 0.5) {
                    if (jgap > 1.2) {
                        this.sp.y = 0.12;
                    }
                    else if (jgap > 1.115) {
                        this.sp.y = 0.08;
                    }
                    else {
                        this.sp.y = 0.04;
                    }
                    this.sp.T = 0.0f;
                    this.isCeilingClimbing = true;
                    this.handsEdgeBlockId = (topCeilingClimbing ? topBlockId : bottomBlockId);
                }
            }
        }
        if (this.isCeilingClimbing && climbCeilingCrawlingStartConflict) {
            this.isCrawling = false;
            this.resetHeightOffset();
            this.move(0.0, 1.0, 0.0, true);
        }
    }
    
    private void setLandMotions(final float horizontalDamping) {
        final uf sp = this.sp;
        sp.y -= 0.08;
        final uf sp2 = this.sp;
        sp2.y *= 0.9800000190734863;
        final uf sp3 = this.sp;
        sp3.x *= horizontalDamping;
        final uf sp4 = this.sp;
        sp4.z *= horizontalDamping;
    }
    
    private void handleExhaustion(final double diffX, final double diffY, final double diffZ) {
        float hungerIncrease = 0.0f;
        if (SmartMovingSelf.Config.enabled) {
            final boolean isRunning = this.isRunning();
            final boolean isVerticalStill = Math.abs(diffY) < 0.007;
            final boolean isStill = this.isStanding && isVerticalStill;
            if (this.sp.o == null) {
                final float horizontalMovement = ls.a(diffX * diffX + diffZ * diffZ);
                final float movement = ls.a(horizontalMovement * horizontalMovement + diffY * diffY);
                final int relevantMovementFactor = Math.round(movement * 100.0f);
                if (SmartMovingSelf.Config.isHungerGainEnabled()) {
                    final float hungerGainFactor = SmartMovingSelf.Config.getFactor(true, this.sp.F, this.isStanding, isStill, this.isSlow, isRunning, this.isFast, this.isClimbing, this.isClimbCrawling, this.isCeilingClimbing, this.isDipping, this.isSwimming, this.isDiving, this.isCrawling, this.isCrawlClimbing);
                    hungerIncrease += SmartMovingSelf.Config._alwaysHungerGain.value + relevantMovementFactor * 1.0E-4f * hungerGainFactor;
                }
                float additionalExhaustion = 0.0f;
                if (this.isClimbing && !isStill && SmartMovingSelf.Config.isClimbExhaustionEnabled()) {
                    float climbingExhaustion = SmartMovingSelf.Config._baseExhautionGainFactor.value;
                    if (isVerticalStill) {
                        climbingExhaustion *= SmartMovingSelf.Config._climbStrafeExhaustionGain.value;
                    }
                    else if (!this.isStanding) {
                        if (this.wantClimbUp) {
                            climbingExhaustion *= SmartMovingSelf.Config._climbStrafeUpExhaustionGain.value;
                        }
                        else if (this.wantClimbDown) {
                            climbingExhaustion *= SmartMovingSelf.Config._climbStrafeDownExhaustionGain.value;
                        }
                        else {
                            climbingExhaustion *= 0.0f;
                        }
                    }
                    else if (this.wantClimbUp) {
                        climbingExhaustion *= SmartMovingSelf.Config._climbUpExhaustionGain.value;
                    }
                    else if (this.wantClimbDown) {
                        climbingExhaustion *= SmartMovingSelf.Config._climbDownExhaustionGain.value;
                    }
                    else {
                        climbingExhaustion *= 0.0f;
                    }
                    additionalExhaustion += climbingExhaustion;
                }
                if (this.isCeilingClimbing && !this.isStanding && SmartMovingSelf.Config.isCeilingClimbExhaustionEnabled()) {
                    additionalExhaustion += SmartMovingSelf.Config._baseExhautionGainFactor.value * SmartMovingSelf.Config._ceilingClimbExhaustionGain.value;
                }
                if (this.isFast && SmartMovingSelf.Config.isSprintExhaustionEnabled()) {
                    if (additionalExhaustion == 0.0f) {
                        additionalExhaustion = SmartMovingSelf.Config._baseExhautionGainFactor.value;
                    }
                    additionalExhaustion *= SmartMovingSelf.Config._sprintExhaustionGainFactor.value;
                }
                if (this.isRunning() && SmartMovingSelf.Config.isRunExhaustionEnabled()) {
                    if (additionalExhaustion == 0.0f) {
                        additionalExhaustion = SmartMovingSelf.Config._baseExhautionGainFactor.value;
                    }
                    additionalExhaustion *= SmartMovingSelf.Config._runExhaustionGainFactor.value;
                }
                if (this.foreignExhaustionFactor > 0.0f) {
                    additionalExhaustion += this.foreignExhaustionFactor * SmartMovingSelf.Config._baseExhautionGainFactor.value;
                    if (this.foreignMaxExhaustionForAction == Float.MAX_VALUE) {
                        this.foreignMaxExhaustionForAction = SmartMovingSelf.Client.getMaximumExhaustion();
                    }
                    this.maxExhaustionForAction = Math.min(this.maxExhaustionForAction, this.foreignMaxExhaustionForAction);
                    if (this.foreignMaxExhaustionToStartAction == Float.MAX_VALUE) {
                        this.foreignMaxExhaustionToStartAction = SmartMovingSelf.Client.getMaximumExhaustion();
                    }
                    this.maxExhaustionToStartAction = Math.min(this.maxExhaustionToStartAction, this.foreignMaxExhaustionToStartAction);
                }
                this.exhaustion += additionalExhaustion;
            }
            else {
                hungerIncrease = -1.0f;
            }
            if (this.exhaustion > 0.0f) {
                final boolean exhaustionLossPossible = !SmartMovingSelf.Config.isExhaustionLossHungerEnabled() || this.sp.bI().a() > SmartMovingSelf.Config._exhaustionLossFoodLevelMinimum.value;
                if (exhaustionLossPossible) {
                    final float exhaustionLossFactor = SmartMovingSelf.Config.getFactor(false, this.sp.F, this.isStanding, isStill, this.isSlow, isRunning, this.isFast, this.isClimbing, this.isClimbCrawling, this.isCeilingClimbing, this.isDipping, this.isSwimming, this.isDiving, this.isCrawling, this.isCrawlClimbing);
                    final float exhaustionLoss = 1.0f * exhaustionLossFactor;
                    this.exhaustion -= exhaustionLoss;
                    if (SmartMovingSelf.Config.isExhaustionLossHungerEnabled()) {
                        hungerIncrease += SmartMovingSelf.Config._exhaustionLossHungerFactor.value * exhaustionLoss;
                    }
                }
            }
            if (this.exhaustion < 0.0f) {
                this.exhaustion = 0.0f;
            }
            if (this.exhaustion == 0.0f) {
                final float n = Float.NaN;
                this.maxExhaustionToStartAction = n;
                this.maxExhaustionForAction = n;
            }
            if (this.maxExhaustionForAction == Float.MAX_VALUE) {
                this.maxExhaustionForAction = this.prevMaxExhaustionForAction;
            }
            if (this.maxExhaustionToStartAction == Float.MAX_VALUE) {
                this.maxExhaustionToStartAction = this.prevMaxExhaustionToStartAction;
            }
            this.foreignExhaustionFactor = 0.0f;
            this.foreignMaxExhaustionForAction = Float.MAX_VALUE;
            this.foreignMaxExhaustionToStartAction = Float.MAX_VALUE;
        }
        else {
            hungerIncrease = -1.0f;
        }
        if (hungerIncrease != this.lastHungerIncrease) {
            SmartMovingPacketStream.sendHungerChange(SmartMovingComm.instance, hungerIncrease);
            this.lastHungerIncrease = hungerIncrease;
        }
    }
    
    @Override
    public float getExhaustion() {
        return this.exhaustion;
    }
    
    @Override
    public float getUpJumpCharge() {
        return this.jumpCharge;
    }
    
    @Override
    public float getHeadJumpCharge() {
        return this.headJumpCharge;
    }
    
    @Override
    public void addExhaustion(final float factor) {
        if (!Float.isNaN(factor) && factor > 0.0f) {
            this.foreignExhaustionFactor += factor;
        }
    }
    
    @Override
    public void setMaxExhaustionForAction(final float maxExhaustionForAction) {
        if (!Float.isNaN(maxExhaustionForAction) && maxExhaustionForAction >= 0.0f) {
            this.foreignMaxExhaustionForAction = Math.min(this.foreignMaxExhaustionForAction, maxExhaustionForAction);
        }
    }
    
    @Override
    public void setMaxExhaustionToStartAction(final float maxExhaustionToStartAction) {
        if (!Float.isNaN(maxExhaustionToStartAction) && maxExhaustionToStartAction >= 0.0f) {
            this.foreignMaxExhaustionToStartAction = Math.min(this.foreignMaxExhaustionToStartAction, maxExhaustionToStartAction);
        }
    }
    
    private void landMotionPost(final boolean wasShortInWater) {
        if (this.grabButton.Pressed) {
            this.fromSwimmingOrDiving(wasShortInWater);
        }
        if (this.heightOffset != 0.0f && this.isp.getSleepingField()) {
            this.resetInternalHeightOffset();
        }
    }
    
    private void fromSwimmingOrDiving(final boolean wasShortInWater) {
        final boolean isShortInWater = this.isSwimming || this.isDiving;
        if (wasShortInWater && !isShortInWater && !this.isp.getSleepingField()) {
            this.setHeightOffset(-1.0f);
            final double crawlStandUpBottom = this.getMaxPlayerSolidBetween(this.sp.E.b - 1.0, this.sp.E.b, 0.0);
            final double crawlStandUpLiquidCeiling = this.getMinPlayerLiquidBetween(this.sp.E.e, this.sp.E.e + 1.1);
            final double crawlStandUpCeiling = this.getMinPlayerSolidBetween(this.sp.E.e, this.sp.E.e + 1.1, 0.0);
            this.resetHeightOffset();
            if (crawlStandUpCeiling - crawlStandUpBottom < this.sp.P) {
                this.isCrawling = true;
                this.isDipping = false;
                this.setHeightOffset(-1.0f);
            }
            else if (crawlStandUpLiquidCeiling - crawlStandUpBottom < this.sp.P) {
                this.isCrawling = true;
                this.contextContinueCrawl = true;
                this.isDipping = false;
                this.setHeightOffset(-1.0f);
            }
            else if (crawlStandUpBottom > this.sp.E.b) {
                if (this.isSlow && crawlStandUpBottom > this.sp.E.b + 0.5) {
                    this.isCrawling = true;
                    this.isDipping = false;
                    this.setHeightOffset(-1.0f);
                }
                this.move(0.0, crawlStandUpBottom - this.sp.E.b, 0.0, true);
            }
        }
    }
    
    public boolean canTriggerWalking() {
        return !this.isClimbing && !this.isDiving;
    }
    
    private void resetClimbing() {
        this.isClimbing = false;
        this.isHandsVineClimbing = false;
        this.isFeetVineClimbing = false;
        this.isVineOnlyClimbing = false;
        this.isVineAnyClimbing = false;
        this.isClimbingStill = false;
        this.isNeighborClimbing = false;
        this.actualHandsClimbType = 0;
        this.actualFeetClimbType = 0;
        this.isCeilingClimbing = false;
    }
    
    private void resetSwimming() {
        this.dippingDepth = -1.0f;
        this.isDipping = false;
        this.isSwimming = false;
        this.isDiving = false;
        this.isLevitating = false;
        this.isShallowDiveOrSwim = false;
        this.isFakeShallowWaterSneaking = false;
        this.isJumpingOutOfWater = false;
    }
    
    private void setShouldClimbSpeed(final double value) {
        this.setShouldClimbSpeed(value, 1, 1);
    }
    
    private void setShouldClimbSpeed(final double value, final int handsClimbType, final int feetClimbType) {
        this.setOnlyShouldClimbSpeed(value);
        this.actualHandsClimbType = handsClimbType;
        this.actualFeetClimbType = feetClimbType;
    }
    
    private void setOnlyShouldClimbSpeed(double value) {
        this.isClimbing = true;
        if (this.climbIntoCount > 0) {
            value = 0.08;
        }
        if (value != 0.08) {
            float factor = this.getSpeedFactor();
            if (this.isFast) {
                factor *= SmartMovingSelf.Config._sprintFactor.value;
            }
            if (SmartMovingSelf.Config.isFreeBaseClimb() && value == 0.14) {
                switch (this.getOnLadder(Integer.MAX_VALUE, false, this.isClimbCrawling)) {
                    case 1: {
                        factor *= SmartMovingSelf.Config._freeOneLadderClimbUpSpeedFactor.value;
                        break;
                    }
                    case 2: {
                        factor *= SmartMovingSelf.Config._freeBothLadderClimbUpSpeedFactor.value;
                        break;
                    }
                }
            }
            if (value > 0.08) {
                value = (value - 0.08) * SmartMovingSelf.Config._freeClimbingUpSpeedFactor.value * factor + 0.08;
            }
            else {
                value = 0.08 - (0.08 - value) * SmartMovingSelf.Config._freeClimbingDownSpeedFactor.value * factor;
            }
            if (this.hasClimbCrawlGap && this.isClimbCrawling && value > 0.08) {
                value = Math.min(0.17, value);
            }
        }
        else {
            this.isClimbingStill = true;
        }
        final boolean relevant = value < 0.0 || value > this.sp.y;
        if (relevant) {
            this.sp.y = value;
        }
        this.isClimbJumping = (!relevant && !this.isClimbHolding);
    }
    
    public boolean isOnLadderOrVine() {
        return this.isOnLadderOrVine(this.isClimbCrawling);
    }
    
    public void beforeMoveEntity(final double d, final double d1, final double d2) {
        this.beforeMoveEntityPosX = this.sp.u;
        this.beforeMoveEntityPosY = this.sp.w;
        this.beforeMoveEntityPosZ = this.sp.v;
        if (this.esp.c.d || this.sneakToggled) {
            if (this.isSwimming || this.isDiving || this.isCrawling || this.isClimbing || (!SmartMovingSelf.Config.isSneakingEnabled() && !this.isSneaking())) {
                this.sp.X = 0.0f;
            }
            else if (this.isSlow) {
                this.sp.X = 0.6f;
            }
            else {
                this.sp.X = 0.0f;
            }
        }
        if (this.isSliding || this.isCrawling) {
            this.beforeDistanceWalkedModified = this.sp.R;
            this.sp.R = Float.MIN_VALUE;
        }
        if (this.wantWallJumping) {
            final int collisions = this.calculateSeparateCollisions(d, d1, d2);
            this.horizontalCollisionAngle = Utilities.getHorizontalCollisionangle((collisions & 0x10) != 0x0, (collisions & 0x20) != 0x0, (collisions & 0x1) != 0x0, (collisions & 0x2) != 0x0);
        }
    }
    
    public void moveEntity(final double d, final double d1, final double d2) {
        this.beforeMoveEntity(d, d1, d2);
        this.isp.localMoveEntity(d, d1, d2);
        this.afterMoveEntity(d, d1, d2);
    }
    
    public void afterMoveEntity(final double d, final double d1, final double d2) {
        if (this.isSliding || this.isCrawling) {
            this.sp.R = this.beforeDistanceWalkedModified;
        }
        if (this.heightOffset != 0.0f) {
            this.sp.v += this.heightOffset;
        }
        this.wasOnGround = this.sp.F;
        final double d3 = this.sp.u - this.beforeMoveEntityPosX;
        final double d4 = this.sp.w - this.beforeMoveEntityPosY;
        final double d5 = this.sp.v - this.beforeMoveEntityPosZ;
        final double distance = ls.a(d3 * d3 + d4 * d4 + d5 * d5);
        if (this.isClimbing || this.isCeilingClimbing) {
            this.distanceClimbedModified += (float)(distance * (this.isClimbing ? 1.2 : 0.9));
            if (this.distanceClimbedModified > this.nextClimbDistance) {
                int blockId;
                if (this.isClimbing) {
                    if (this.handsEdgeBlockId == -1) {
                        if (this.feetEdgeBlockId == -1) {
                            blockId = aqz.B.cF;
                        }
                        else {
                            blockId = this.feetEdgeBlockId;
                        }
                    }
                    else if (this.feetEdgeBlockId == -1) {
                        blockId = this.handsEdgeBlockId;
                    }
                    else {
                        blockId = ((this.nextClimbDistance % 2 != 0) ? this.feetEdgeBlockId : this.handsEdgeBlockId);
                    }
                }
                else {
                    blockId = this.handsEdgeBlockId;
                }
                ++this.nextClimbDistance;
                if (blockId >= 0 && blockId < aqz.s.length) {
                    final aqz stepBlock = aqz.s[blockId];
                    if (stepBlock != null) {
                        final ard stepsound = aqz.s[blockId].cS;
                        if (stepsound != null) {
                            this.playSound(stepsound.e(), stepsound.c() * 0.15f, stepsound.d());
                        }
                    }
                }
            }
        }
        if (this.isSwimming) {
            this.distanceSwom += (float)distance;
            if (this.distanceSwom > 1.4285715f) {
                final Random rand = this.sp.aD();
                this.playSound("random.splash", 0.05f, 1.0f + (rand.nextFloat() - rand.nextFloat()) * 0.4f);
                this.distanceSwom -= 1.4285715f;
            }
        }
    }
    
    private void playSound(final String id, final float volume, final float pitch) {
        atv.w().f.a(this.sp.u, this.sp.v - this.sp.N, this.sp.w, id, volume, pitch, false);
        SmartMovingPacketStream.sendSound(SmartMovingComm.instance, id, volume, pitch);
    }
    
    public void beforeSleepInBedAt(final int i, final int j, final int k) {
        if (!this.isp.getSleepingField()) {
            this.updateEntityActionState(true);
        }
    }
    
    public ug sleepInBedAt(final int i, final int j, final int k) {
        this.beforeSleepInBedAt(i, j, k);
        return this.isp.localSleepInBedAt(i, j, k);
    }
    
    private void resetHeightOffset() {
        final asx e = this.sp.E;
        e.b += this.heightOffset;
        final uf sp = this.sp;
        sp.P -= this.heightOffset;
        this.heightOffset = 0.0f;
    }
    
    private void resetInternalHeightOffset() {
        final uf sp = this.sp;
        sp.P -= this.heightOffset;
        this.heightOffset = 0.0f;
    }
    
    private void setHeightOffset(final float offset) {
        this.resetHeightOffset();
        if (offset == 0.0f) {
            return;
        }
        this.heightOffset = offset;
        final asx e = this.sp.E;
        e.b -= this.heightOffset;
        final uf sp = this.sp;
        sp.P += this.heightOffset;
    }
    
    public float getBrightness(final float f) {
        final uf sp = this.sp;
        sp.v -= this.heightOffset;
        final float result = this.isp.localGetBrightness(f);
        final uf sp2 = this.sp;
        sp2.v += this.heightOffset;
        return result;
    }
    
    public int getBrightnessForRender(final float f) {
        final uf sp = this.sp;
        sp.v -= this.heightOffset;
        final int result = this.isp.localGetBrightnessForRender(f);
        final uf sp2 = this.sp;
        sp2.v += this.heightOffset;
        return result;
    }
    
    public boolean pushOutOfBlocks(final double d, final double d1, final double d2) {
        if (this.multiPlayerInitialized > 0) {
            return false;
        }
        boolean top = false;
        if (this.heightOffset != 0.0f) {
            top = (this.sp.P > 1.0f);
        }
        return this.pushOutOfBlocks(d, d1, d2, top);
    }
    
    public void beforeOnUpdate() {
        this.prevMotionX = this.sp.x;
        this.prevMotionY = this.sp.y;
        this.prevMotionZ = this.sp.z;
        this.wasCollidedHorizontally = this.sp.G;
        this.isJumping = false;
        if (this.sp.q.I && this.updateCounter < 10) {
            final List chatMessageList = (List)Reflect.GetField(SmartMovingSelf._chatMessageList, this.isp.getMcField().r.b());
            for (int i = 0; i < chatMessageList.size(); ++i) {
                if (SmartMovingComm.processBlockCode(chatMessageList.get(i).a())) {
                    chatMessageList.remove(i--);
                }
            }
            ++this.updateCounter;
        }
    }
    
    public void afterOnUpdate() {
        this.correctOnUpdate(this.isSwimming || this.isDiving || this.isDipping || this.isCrawling, this.isSwimming);
        this.spawnParticles(this.isp.getMcField(), this.sp.x, this.sp.z);
        final float landMovementFactor = this.getLandMovementFactor();
        if (SmartMovingSelf.Config.enabled) {
            float perspectiveFactor = landMovementFactor;
            if (this.isFast || this.isSprintJump || this.isRunning()) {
                if (this.sp.ai()) {
                    perspectiveFactor /= 1.3f;
                }
                if (this.isFast || this.isSprintJump) {
                    perspectiveFactor *= SmartMovingSelf.Options._perspectiveSprintFactor.value;
                }
                else if (this.isRunning()) {
                    perspectiveFactor *= 1.3f * SmartMovingSelf.Options._perspectiveRunFactor.value;
                }
            }
            if (this.fadingPerspectiveFactor != -1.0f) {
                this.fadingPerspectiveFactor += (perspectiveFactor - this.fadingPerspectiveFactor) * SmartMovingSelf.Options._perspectiveFadeFactor.value;
            }
            else {
                this.fadingPerspectiveFactor = landMovementFactor;
            }
        }
        if (this.sp.bG.a) {
            this.exhaustion = 0.0f;
        }
        if (this.sp.bG.b) {
            this.sp.T = 0.0f;
        }
        if (this.sp.G) {
            ++this.collidedHorizontallyTickCount;
        }
        else {
            this.collidedHorizontallyTickCount = 0;
        }
        this.addToSendQueue();
        if (this.wasInventory) {
            this.sp.aQ = this.sp.aP;
        }
        this.wasInventory = (this.isp.getMcField().n instanceof axv);
    }
    
    public void beforeOnLivingUpdate() {
        this.wasCapabilitiesIsFlying = this.sp.bG.b;
    }
    
    public void afterOnLivingUpdate() {
        if (SmartMovingSelf.Options._flyWhileOnGround.value && (!this.sneakButton.Pressed || !this.grabButton.Pressed) && this.wasCapabilitiesIsFlying && !this.sp.bG.b && this.sp.F) {
            this.sp.bt = 0.0f;
            this.sp.bs = 0.0f;
            this.sp.bG.b = true;
            ((bdi)this.sp).a.c((ey)new fa(this.sp.bG));
        }
    }
    
    public void handleJumping() {
        if (this.blockJumpTillButtonRelease && !this.esp.c.c) {
            this.blockJumpTillButtonRelease = false;
        }
        if (this.isSwimming || this.isDiving) {
            return;
        }
        final boolean jump = this.jumpAvoided && this.sp.F && this.isp.getIsJumpingField() && !this.sp.H() && !this.sp.J();
        if (jump && this.sp.E.b - this.getMaxPlayerSolidBetween(this.sp.E.b - 0.2, this.sp.E.b, 0.0) >= 0.01) {
            return;
        }
        this.jumpMotionX = this.sp.x;
        this.jumpMotionZ = this.sp.z;
        boolean isJumpCharging = false;
        if (SmartMovingSelf.Config.isJumpChargingEnabled()) {
            final boolean isJumpChargingPossible = this.sp.F && this.isStanding;
            isJumpCharging = (isJumpChargingPossible && this.wouldIsSneaking);
            final boolean actualJumpCharging = isJumpChargingPossible && (!SmartMovingSelf.Config._jumpChargeCancelOnSneakRelease.value || this.wouldIsSneaking);
            if (actualJumpCharging) {
                if (this.esp.c.c && (SmartMovingSelf.Config._jumpChargeCancelOnSneakRelease.value || this.wouldIsSneaking)) {
                    ++this.jumpCharge;
                }
                else {
                    if (this.jumpCharge > 0.0f) {
                        final SmartMovingClientConfig config = SmartMovingSelf.Config;
                        this.tryJump(1, null, null, null);
                    }
                    this.jumpCharge = 0.0f;
                }
            }
            else {
                if (this.jumpCharge > 0.0f) {
                    this.blockJumpTillButtonRelease = true;
                }
                this.jumpCharge = 0.0f;
            }
        }
        boolean isHeadJumpCharging = false;
        if (SmartMovingSelf.Config.isHeadJumpingEnabled()) {
            isHeadJumpCharging = (this.grabButton.Pressed && (this.isGroundSprinting || this.isSprintJump || (this.isRunning() && this.sp.F)) && !this.isCrawling);
            if (isHeadJumpCharging) {
                if (this.esp.c.c) {
                    ++this.headJumpCharge;
                }
                else {
                    if (this.headJumpCharge > 0.0f && this.sp.F) {
                        final SmartMovingClientConfig config2 = SmartMovingSelf.Config;
                        this.tryJump(3, null, null, null);
                    }
                    this.headJumpCharge = 0.0f;
                }
            }
            else {
                if (this.headJumpCharge > 0.0f) {
                    this.blockJumpTillButtonRelease = true;
                }
                this.headJumpCharge = 0.0f;
            }
        }
        if (this.esp.c.c && this.sp.H() && this.isDipping && this.sp.v - ls.c(this.sp.v) > (this.isSlow ? 0.37 : 0.6)) {
            final uf sp = this.sp;
            sp.y -= 0.03999999910593033;
            if (!this.isStillSwimmingJump && this.sp.F && this.jumpCharge == 0.0f) {
                final SmartMovingClientConfig config3 = SmartMovingSelf.Config;
                if (this.tryJump(0, true, null, null)) {
                    final Random rand = this.sp.aD();
                    this.playSound("random.splash", 0.05f, 1.0f + (rand.nextFloat() - rand.nextFloat()) * 0.4f);
                }
            }
        }
        if (jump && !this.blockJumpTillButtonRelease && !isJumpCharging && !isHeadJumpCharging && !this.isVineAnyClimbing) {
            final SmartMovingClientConfig config4 = SmartMovingSelf.Config;
            this.tryJump(0, false, null, null);
        }
        int left = 0;
        int back = 0;
        if (this.leftJumpCount == -1) {
            ++left;
        }
        if (this.rightJumpCount == -1) {
            --left;
        }
        if (this.backJumpCount == -1) {
            ++back;
        }
        if (left != 0 || back != 0) {
            int angle;
            if (left > 0) {
                angle = ((back == 0) ? 270 : 225);
            }
            else if (left < 0) {
                angle = ((back == 0) ? 90 : 135);
            }
            else {
                angle = 180;
            }
            final SmartMovingClientConfig config5 = SmartMovingSelf.Config;
            if (this.tryJump(2, null, null, this.sp.A + angle)) {
                this.angleJumpType = (360 - angle) / 45 % 8;
            }
            this.leftJumpCount = 0;
            this.rightJumpCount = 0;
            this.backJumpCount = 0;
        }
    }
    
    public void handleWallJumping() {
        if (!this.wantWallJumping || Double.isNaN(this.horizontalCollisionAngle)) {
            return;
        }
        int jumpType;
        if (this.grabButton.Pressed) {
            if (this.sp.T > SmartMovingSelf.Config._wallHeadJumpFallMaximumDistance.value) {
                return;
            }
            int n;
            if (this.wasCollidedHorizontally) {
                final SmartMovingClientConfig config = SmartMovingSelf.Config;
                n = 14;
            }
            else {
                final SmartMovingClientConfig config2 = SmartMovingSelf.Config;
                n = 12;
            }
            jumpType = n;
        }
        else {
            if (this.sp.T > SmartMovingSelf.Config._wallUpJumpFallMaximumDistance.value) {
                return;
            }
            int n2;
            if (this.wasCollidedHorizontally) {
                final SmartMovingClientConfig config3 = SmartMovingSelf.Config;
                n2 = 13;
            }
            else {
                final SmartMovingClientConfig config4 = SmartMovingSelf.Config;
                n2 = 11;
            }
            jumpType = n2;
        }
        float jumpAngle;
        if (!this.wasCollidedHorizontally) {
            final float movementAngle = Utilities.getAngle(this.jumpMotionZ, -this.jumpMotionX);
            if (Double.isNaN(movementAngle)) {
                return;
            }
            jumpAngle = this.horizontalCollisionAngle * 2.0f - movementAngle + 180.0f;
        }
        else {
            jumpAngle = this.horizontalCollisionAngle;
        }
        while (jumpAngle > 360.0f) {
            jumpAngle -= 360.0f;
        }
        if (SmartMovingSelf.Config._wallUpJumpOrthogonalTolerance.value != 0.0f) {
            float aligned;
            for (aligned = jumpAngle; aligned > 45.0f; aligned -= 90.0f) {}
            if (Math.abs(aligned) < SmartMovingSelf.Config._wallUpJumpOrthogonalTolerance.value) {
                jumpAngle = Math.round(jumpAngle / 90.0f) * 90.0f;
            }
        }
        if (this.tryJump(jumpType, null, null, jumpAngle)) {
            this.continueWallJumping = !this.isHeadJumping;
            this.sp.G = false;
            this.sp.A = jumpAngle;
            this.onStartWallJump(jumpAngle);
        }
    }
    
    public boolean tryJump(int type, final Boolean inWaterOrNull, final Boolean isRunningOrNull, final Float angle) {
        boolean noVertical = false;
        final int n = type;
        final SmartMovingClientConfig config = SmartMovingSelf.Config;
        Label_0052: {
            if (n != 13) {
                final int n2 = type;
                final SmartMovingClientConfig config2 = SmartMovingSelf.Config;
                if (n2 != 14) {
                    break Label_0052;
                }
            }
            final int n3 = type;
            final SmartMovingClientConfig config3 = SmartMovingSelf.Config;
            int n4;
            if (n3 == 13) {
                final SmartMovingClientConfig config4 = SmartMovingSelf.Config;
                n4 = 11;
            }
            else {
                final SmartMovingClientConfig config5 = SmartMovingSelf.Config;
                n4 = 12;
            }
            type = n4;
            noVertical = true;
        }
        final boolean inWater = (inWaterOrNull != null) ? inWaterOrNull : this.isDipping;
        final boolean isRunning = (isRunningOrNull != null) ? isRunningOrNull : this.isRunning();
        final int n5 = type;
        final SmartMovingClientConfig config6 = SmartMovingSelf.Config;
        final boolean charged = n5 == 1;
        final boolean b = type != 0;
        final SmartMovingClientConfig config7 = SmartMovingSelf.Config;
        boolean b3 = false;
        Label_0222: {
            if (b) {
                final boolean b2 = type != 0;
                final SmartMovingClientConfig config8 = SmartMovingSelf.Config;
                if (!b2) {
                    final int n6 = type;
                    final SmartMovingClientConfig config9 = SmartMovingSelf.Config;
                    if (n6 != 3) {
                        final int n7 = type;
                        final SmartMovingClientConfig config10 = SmartMovingSelf.Config;
                        if (n7 != 5) {
                            final int n8 = type;
                            final SmartMovingClientConfig config11 = SmartMovingSelf.Config;
                            if (n8 != 6) {
                                final int n9 = type;
                                final SmartMovingClientConfig config12 = SmartMovingSelf.Config;
                                if (n9 != 7) {
                                    final int n10 = type;
                                    final SmartMovingClientConfig config13 = SmartMovingSelf.Config;
                                    if (n10 != 8) {
                                        final int n11 = type;
                                        final SmartMovingClientConfig config14 = SmartMovingSelf.Config;
                                        if (n11 != 9) {
                                            final int n12 = type;
                                            final SmartMovingClientConfig config15 = SmartMovingSelf.Config;
                                            if (n12 != 10) {
                                                final int n13 = type;
                                                final SmartMovingClientConfig config16 = SmartMovingSelf.Config;
                                                if (n13 != 2) {
                                                    final int n14 = type;
                                                    final SmartMovingClientConfig config17 = SmartMovingSelf.Config;
                                                    if (n14 != 11) {
                                                        final int n15 = type;
                                                        final SmartMovingClientConfig config18 = SmartMovingSelf.Config;
                                                        if (n15 != 12) {
                                                            b3 = false;
                                                            break Label_0222;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            b3 = true;
        }
        final boolean up = b3;
        final int n16 = type;
        final SmartMovingClientConfig config19 = SmartMovingSelf.Config;
        boolean b4 = false;
        Label_0268: {
            if (n16 != 3) {
                final int n17 = type;
                final SmartMovingClientConfig config20 = SmartMovingSelf.Config;
                if (n17 != 9) {
                    final int n18 = type;
                    final SmartMovingClientConfig config21 = SmartMovingSelf.Config;
                    if (n18 != 10) {
                        final int n19 = type;
                        final SmartMovingClientConfig config22 = SmartMovingSelf.Config;
                        if (n19 != 12) {
                            b4 = false;
                            break Label_0268;
                        }
                    }
                }
            }
            b4 = true;
        }
        final boolean head = b4;
        final int speed = getJumpSpeed(this.isStanding, this.isSlow, isRunning, this.isFast, angle);
        final boolean enabled = SmartMovingSelf.Config.isJumpingEnabled(speed, type);
        if (enabled) {
            final boolean exhausionEnabled = SmartMovingSelf.Config.isJumpExhaustionEnabled(speed, type);
            if (exhausionEnabled) {
                final float maxExhausionForJump = SmartMovingSelf.Config.getJumpExhaustionStop(speed, type, this.jumpCharge);
                if (this.exhaustion > maxExhausionForJump) {
                    return false;
                }
                this.maxExhaustionToStartAction = Math.min(this.maxExhaustionToStartAction, maxExhausionForJump);
                this.maxExhaustionForAction = Math.min(this.maxExhaustionForAction, maxExhausionForJump + SmartMovingSelf.Config.getJumpExhaustionGain(speed, type, this.jumpCharge));
            }
            final float jumpFactor = this.sp.a(ni.j) ? (1.0f + (this.sp.b(ni.j).c() + 1) * 0.2f) : 1.0f;
            float horizontalJumpFactor = SmartMovingSelf.Config.getJumpHorizontalFactor(speed, type) * jumpFactor;
            float verticalJumpFactor = SmartMovingSelf.Config.getJumpVerticalFactor(speed, type) * jumpFactor;
            final float jumpChargeFactor = charged ? SmartMovingSelf.Config.getJumpChargeFactor(this.jumpCharge) : 1.0f;
            if (!up) {
                horizontalJumpFactor = ls.c(horizontalJumpFactor * horizontalJumpFactor + verticalJumpFactor * verticalJumpFactor);
                verticalJumpFactor = 0.0f;
            }
            Double maxHorizontalMotion = null;
            double horizontalMotion = ls.a(this.jumpMotionX * this.jumpMotionX + this.jumpMotionZ * this.jumpMotionZ);
            double verticalMotion = -0.078 + 0.498 * verticalJumpFactor * jumpChargeFactor;
            if (horizontalJumpFactor > 1.0f && !this.sp.G) {
                maxHorizontalMotion = SmartMovingSelf.Config.getMaxHorizontalMotion(speed, type, inWater) * (double)this.getSpeedFactor();
            }
            if (head) {
                final double normalAngle = Math.atan(verticalMotion / horizontalMotion);
                final double totalMotion = Math.sqrt(verticalMotion * verticalMotion + horizontalMotion * horizontalMotion);
                final double newAngle = SmartMovingSelf.Config.getHeadJumpFactor(this.headJumpCharge) * normalAngle;
                final double newVerticalMotion = totalMotion * Math.sin(newAngle);
                final double newHorizontalMotion = totalMotion * Math.cos(newAngle);
                if (maxHorizontalMotion != null) {
                    maxHorizontalMotion *= newHorizontalMotion / horizontalMotion;
                }
                verticalMotion = newVerticalMotion;
                horizontalMotion = newHorizontalMotion;
            }
            if (angle != null) {
                final float jumpAngle = angle / 57.295776f;
                final int n20 = type;
                final SmartMovingClientConfig config23 = SmartMovingSelf.Config;
                boolean b5 = false;
                Label_0726: {
                    if (n20 != 11) {
                        final int n21 = type;
                        final SmartMovingClientConfig config24 = SmartMovingSelf.Config;
                        if (n21 != 12) {
                            b5 = false;
                            break Label_0726;
                        }
                    }
                    b5 = true;
                }
                final boolean reset = b5;
                final double horizontal = Math.max(horizontalMotion, horizontalJumpFactor);
                final double moveX = -Math.sin(jumpAngle);
                final double moveZ = Math.cos(jumpAngle);
                this.sp.x = getJumpMoving(this.jumpMotionX, moveX, reset, horizontal, horizontalJumpFactor);
                this.sp.z = getJumpMoving(this.jumpMotionZ, moveZ, reset, horizontal, horizontalJumpFactor);
                horizontalMotion = 0.0;
                verticalMotion = verticalJumpFactor;
            }
            if (horizontalMotion > 0.0) {
                double absoluteMotionX = Math.abs(this.sp.x) * horizontalJumpFactor;
                double absoluteMotionZ = Math.abs(this.sp.z) * horizontalJumpFactor;
                if (maxHorizontalMotion != null) {
                    absoluteMotionX = Math.min(absoluteMotionX, maxHorizontalMotion * (horizontalJumpFactor * (Math.abs(this.sp.x) / horizontalMotion)));
                    absoluteMotionZ = Math.min(absoluteMotionZ, maxHorizontalMotion * (horizontalJumpFactor * (Math.abs(this.sp.z) / horizontalMotion)));
                }
                this.sp.x = Math.signum(this.sp.x) * absoluteMotionX;
                this.sp.z = Math.signum(this.sp.z) * absoluteMotionZ;
            }
            if (up && !noVertical) {
                this.sp.y = verticalMotion;
                this.sp.a(la.u, 1);
                this.isSprintJump = this.isFast;
            }
            if (exhausionEnabled) {
                final float exhaustionFromJump = SmartMovingSelf.Config.getJumpExhaustionGain(speed, type, this.jumpCharge);
                this.exhaustion += exhaustionFromJump;
            }
            if (head) {
                this.isHeadJumping = true;
                this.setHeightOffset(-1.0f);
            }
            this.sp.an = true;
            this.isJumping = true;
            this.onLivingJump();
        }
        return enabled;
    }
    
    private static double getJumpMoving(final double actual, final double move, final boolean reset, final double horizontal, final float horizontalJumpFactor) {
        if (!reset) {
            return actual + move * horizontal;
        }
        if (Math.signum(actual) != Math.signum(move)) {
            return move * horizontalJumpFactor;
        }
        return Math.max(Math.abs(actual), Math.abs(move) * horizontal) * Math.signum(move);
    }
    
    private static int getJumpSpeed(final boolean isStanding, final boolean isSneaking, boolean isRunning, boolean isSprinting, final Float angle) {
        isSprinting &= (angle == null);
        isRunning &= (angle == null);
        if (isSprinting) {
            final SmartMovingClientConfig config = SmartMovingSelf.Config;
            return 0;
        }
        if (isRunning) {
            final SmartMovingClientConfig config2 = SmartMovingSelf.Config;
            return 1;
        }
        if (isSneaking) {
            final SmartMovingClientConfig config3 = SmartMovingSelf.Config;
            return 3;
        }
        if (isStanding) {
            final SmartMovingClientConfig config4 = SmartMovingSelf.Config;
            return 4;
        }
        final SmartMovingClientConfig config5 = SmartMovingSelf.Config;
        return 2;
    }
    
    private void standupIfPossible() {
        if (this.heightOffset >= 0.0f) {
            return;
        }
        final double gapUnderneight = this.getGapUnderneight();
        final boolean groundClose = gapUnderneight < 1.0;
        if (!groundClose) {
            this.resetHeightOffset();
        }
        else {
            final double gapOverneight = groundClose ? this.getGapOverneight() : -1.0;
            final boolean standUpPossible = gapUnderneight + gapOverneight >= 1.0;
            if (standUpPossible) {
                this.standUp(gapUnderneight);
            }
            else {
                this.toSlidingOrCrawling(gapUnderneight);
            }
        }
    }
    
    private void standupIfPossible(final boolean tryLanding, boolean restoreFromFlying) {
        if (this.heightOffset >= 0.0f) {
            return;
        }
        final double gapUnderneight = this.getGapUnderneight();
        final boolean groundClose = gapUnderneight < 1.0;
        final double gapOverneight = groundClose ? this.getGapOverneight() : -1.0;
        final boolean standUpPossible = gapUnderneight + gapOverneight >= 1.0;
        if (tryLanding && groundClose && standUpPossible) {
            this.isFlying = false;
            this.sp.bG.b = false;
            restoreFromFlying = true;
        }
        if (!restoreFromFlying) {
            return;
        }
        if (!groundClose && !this.sneakButton.Pressed) {
            this.resetHeightOffset();
        }
        else if (standUpPossible && (!this.sneakButton.Pressed || !this.grabButton.Pressed)) {
            this.standUp(gapUnderneight);
        }
        else {
            this.toSlidingOrCrawling(gapUnderneight);
        }
    }
    
    private void standUp(final double gapUnderneight) {
        this.move(0.0, 1.0 - gapUnderneight, 0.0, true);
        this.isCrawling = false;
        this.isHeadJumping = false;
        this.resetHeightOffset();
    }
    
    private void toSlidingOrCrawling(final double gapUnderneight) {
        this.move(0.0, -gapUnderneight, 0.0, true);
        if (SmartMovingSelf.Config.isSlidingEnabled() && (this.grabButton.Pressed || this.wasHeadJumping)) {
            this.isSliding = true;
        }
        else {
            this.wasCrawling = this.toCrawling();
        }
    }
    
    private void handleCrash(final float fallDamageStartDistance, final float fallDamageFactor) {
        if (this.sp.T >= 2.0f) {
            this.sp.a(la.n, (int)Math.round(this.sp.T * 100.0));
        }
        if (this.sp.T >= fallDamageStartDistance) {
            this.sp.a(nb.h, (float)(int)Math.ceil((this.sp.T - fallDamageStartDistance) * fallDamageFactor));
            this.distanceClimbedModified = (float)this.nextClimbDistance;
        }
        this.sp.T = 0.0f;
    }
    
    public void beforeSetPositionAndRotation() {
        if (this.sp.q.I) {
            this.initialized = false;
            this.multiPlayerInitialized = 5;
        }
    }
    
    public void updateEntityActionState(final boolean startSleeping) {
        this.jumpAvoided = false;
        this.prevMaxExhaustionForAction = this.maxExhaustionForAction;
        this.prevMaxExhaustionToStartAction = this.maxExhaustionToStartAction;
        this.maxExhaustionForAction = Float.MAX_VALUE;
        this.maxExhaustionToStartAction = Float.MAX_VALUE;
        final boolean isLevitating = this.sp.bG.b && !this.isFlying;
        boolean isRunning = this.isRunning();
        this.toggleButton.update(SmartMovingSelf.Options.keyBindConfigToggle);
        this.speedIncreaseButton.update(SmartMovingSelf.Options.keyBindSpeedIncrease);
        this.speedDecreaseButton.update(SmartMovingSelf.Options.keyBindSpeedDecrease);
        if (this.toggleButton.StartPressed) {
            if (SmartMovingSelf.Config == SmartMovingSelf.Options) {
                SmartMovingSelf.Config.toggle();
            }
            else {
                SmartMovingPacketStream.sendConfigChange(SmartMovingComm.instance);
            }
        }
        if (SmartMovingSelf.Config.isUserSpeedEnabled() && !SmartMovingSelf.Config.isUserSpeedAlwaysDefault() && (this.speedIncreaseButton.StartPressed || this.speedDecreaseButton.StartPressed)) {
            int difference = 0;
            if (this.speedIncreaseButton.StartPressed) {
                ++difference;
            }
            if (this.speedDecreaseButton.StartPressed) {
                --difference;
            }
            if (difference != 0) {
                if (SmartMovingSelf.Config == SmartMovingSelf.Options) {
                    SmartMovingSelf.Config.changeSpeed(difference);
                }
                else {
                    SmartMovingPacketStream.sendSpeedChange(SmartMovingComm.instance, difference, null);
                }
            }
        }
        boolean initializeCrawling = false;
        if (!this.initialized && (!this.sp.q.I || this.multiPlayerInitialized == 0) && !this.sp.ag()) {
            if (this.getMaxPlayerSolidBetween(this.sp.E.b, this.sp.E.e, 0.0) > this.sp.E.b) {
                initializeCrawling = true;
                this.toCrawling();
            }
            this.initialized = true;
        }
        if (this.multiPlayerInitialized > 0) {
            --this.multiPlayerInitialized;
        }
        if (!this.esp.c.c) {
            this.isStillSwimmingJump = false;
        }
        if (!startSleeping) {
            this.isp.localUpdateEntityActionState();
            this.isp.setMoveStrafingField(Math.signum(this.esp.c.a));
            this.isp.setMoveForwardField(Math.signum(this.esp.c.b));
            this.isp.setIsJumpingField(this.esp.c.c && !this.isCrawling && !this.isSliding && (!SmartMovingSelf.Config.isHeadJumpingEnabled() || !this.grabButton.Pressed || !this.sp.ai()) && (!SmartMovingSelf.Config.isJumpChargingEnabled() || !this.wouldIsSneaking || !this.sp.F || !this.isStanding) && !this.blockJumpTillButtonRelease);
        }
        final boolean isRiding = this.sp.o != null;
        final boolean isSleeping = this.isp.getSleepingField();
        final boolean disabled = !SmartMovingSelf.Config.enabled || isRiding || isSleeping || startSleeping;
        final atv minecraft = this.isp.getMcField();
        final aul gameSettings = minecraft.u;
        this.forwardButton.update(gameSettings.I);
        this.leftButton.update(gameSettings.J);
        this.rightButton.update(gameSettings.L);
        this.backButton.update(gameSettings.K);
        this.jumpButton.update(this.esp.c.c);
        this.sprintButton.update(SmartMovingSelf.Options.keyBindSprint);
        this.sneakButton.update(this.esp.c.d);
        this.grabButton.update(SmartMovingSelf.Options.keyBindGrab);
        final double horizontalSpeedSquare = this.sp.x * this.sp.x + this.sp.z * this.sp.z;
        final boolean blocked = minecraft.n != null && !minecraft.n.j;
        boolean mustCrawl = false;
        double crawlStandUpBottom = -1.0;
        if (this.isCrawling || this.isClimbCrawling) {
            crawlStandUpBottom = this.getMaxPlayerSolidBetween(this.sp.E.b - (initializeCrawling ? 0.0 : 1.0), this.sp.E.b, ((boolean)SmartMovingSelf.Config._crawlOverEdge.value) ? 0.0 : -0.05);
            final double crawlStandUpCeiling = this.getMinPlayerSolidBetween(this.sp.E.e, this.sp.E.e + 1.1, 0.0);
            mustCrawl = (crawlStandUpCeiling - crawlStandUpBottom < this.sp.P - this.heightOffset);
        }
        if (this.esp.bG.b && (SmartMovingSelf.Config.isFlyingEnabled() || SmartMovingSelf.Config.isLevitateSmallEnabled())) {
            mustCrawl = false;
        }
        final boolean inputContinueCrawl = SmartMovingSelf.Options.isCrawlToggleEnabled() ? this.crawlToggled : (this.sneakButton.Pressed || (!SmartMovingSelf.Config.isFreeClimbingEnabled() && this.grabButton.Pressed));
        if (this.contextContinueCrawl) {
            if (inputContinueCrawl || this.sp.H() || mustCrawl) {
                this.contextContinueCrawl = false;
            }
            else if (this.isCrawling) {
                final double crawlStandUpLiquidCeiling = this.getMinPlayerLiquidBetween(this.sp.E.e, this.sp.E.e + 1.1);
                if (crawlStandUpLiquidCeiling - crawlStandUpBottom >= this.sp.P + 1.0f) {
                    this.contextContinueCrawl = false;
                }
            }
        }
        final boolean wouldWantCrawl = !this.esp.bG.b && ((this.isCrawling && (inputContinueCrawl || this.contextContinueCrawl)) || (this.grabButton.StartPressed && (this.sneakToggled || this.sneakButton.Pressed) && this.sp.F));
        final boolean wantCrawl = SmartMovingSelf.Config.isCrawlingEnabled() && wouldWantCrawl;
        final boolean canCrawl = !this.isSwimming && !this.isDiving && (!this.isDipping || this.dippingDepth + this.heightOffset < 0.65f) && !this.isClimbing && this.sp.T < SmartMovingSelf.Config._fallingDistanceMinimum.value;
        this.wasCrawling = this.isCrawling;
        if (!(this.isCrawling = (canCrawl && (wantCrawl || mustCrawl)))) {
            this.contextContinueCrawl = false;
        }
        if (this.wasCrawling && !this.isCrawling && this.esp.bG.b) {
            final SmartMovingClientConfig config = SmartMovingSelf.Config;
            this.tryJump(0, null, null, null);
        }
        this.wantCrawlNotClimb = ((this.wantCrawlNotClimb || (this.grabButton.StartPressed && !this.wasCrawling)) && this.grabButton.Pressed && this.esp.c.b > 0.0f && this.isCrawling && this.sp.G);
        final boolean isFacedToSolidVine = this.isFacedToSolidVine(this.isClimbCrawling);
        final boolean wouldWantClimb = (this.grabButton.Pressed || (this.isClimbHolding && this.sneakButton.Pressed) || (SmartMovingSelf.Config.isFreeClimbAutoLaddderEnabled() && this.isFacedToLadder(this.isClimbCrawling)) || (SmartMovingSelf.Config.isFreeClimbAutoVineEnabled() && isFacedToSolidVine)) && (!this.isSliding || (this.grabButton.Pressed && this.esp.c.b > 0.0f)) && !this.isHeadJumping && !this.wantCrawlNotClimb && !disabled;
        final boolean wantClimb = SmartMovingSelf.Config.isFreeClimbingEnabled() && wouldWantClimb;
        if (!wantClimb || this.sp.H) {
            this.isClimbJumping = false;
        }
        if (this.sp.I) {
            this.isClimbBackJumping = false;
        }
        this.wantClimbUp = ((wantClimb && this.esp.c.b > 0.0f) || (this.isVineAnyClimbing && this.jumpButton.Pressed && (!this.sneakButton.Pressed || !isFacedToSolidVine) && (!this.isCrawling || this.sp.G) && (!this.isSliding || this.sp.G)));
        this.wantClimbDown = (wantClimb && this.esp.c.b <= 0.0f && !wantCrawl);
        this.wantClimbCeiling = (SmartMovingSelf.Config.isCeilingClimbingEnabled() && this.grabButton.Pressed && !this.wantCrawlNotClimb && !this.isSneaking() && !disabled);
        boolean restoreFromFlying = false;
        final boolean wasFlying = this.isFlying;
        this.isFlying = (SmartMovingSelf.Config.isFlyingEnabled() && this.sp.bG.b && !this.isSwimming && !this.isDiving);
        if (this.isFlying && !wasFlying) {
            this.setHeightOffset(-1.0f);
        }
        else if (!this.isFlying && wasFlying) {
            restoreFromFlying = true;
        }
        if (!SmartMovingSelf.Config.isFlyingEnabled() && SmartMovingSelf.Config.isLevitateSmallEnabled()) {
            if (isLevitating && !this.wasLevitating) {
                this.setHeightOffset(-1.0f);
            }
            else if (!isLevitating && this.wasLevitating) {
                restoreFromFlying = true;
            }
        }
        this.wasHeadJumping = this.isHeadJumping;
        if (!(this.isHeadJumping = (this.isHeadJumping && !this.sp.F && !this.isSwimming && !this.isDiving && !this.isFlying && !this.sp.bG.b && (!this.sp.I() || this.sp.y >= 0.0) && !this.sp.J()))) {
            this.isAerodynamic = false;
        }
        if (this.wasHeadJumping && !this.isHeadJumping && this.sp.F) {
            this.handleCrash(SmartMovingSelf.Config._headFallDamageStartDistance.value, SmartMovingSelf.Config._headFallDamageFactor.value);
            restoreFromFlying = true;
        }
        final boolean tryLanding = this.isFlying && !SmartMovingSelf.Options._flyCloseToGround.value && horizontalSpeedSquare < 0.003 && this.sp.y > -0.03;
        if (restoreFromFlying || tryLanding) {
            this.standupIfPossible(tryLanding, restoreFromFlying);
        }
        if (this.isSliding && this.sp.T > 0.05f) {
            this.isSliding = false;
            this.isHeadJumping = true;
            this.isAerodynamic = true;
        }
        if (SmartMovingSelf.Config.isSlidingEnabled() && this.grabButton.Pressed && (this.isGroundSprinting || (this.wasRunning && !isRunning && this.sp.F)) && !this.isCrawling && this.sneakButton.StartPressed && !this.isDipping) {
            this.setHeightOffset(-1.0f);
            this.move(0.0, -1.0, 0.0, true);
            final SmartMovingClientConfig config2 = SmartMovingSelf.Config;
            this.tryJump(4, false, this.wasRunning, null);
            this.isSliding = true;
            this.isHeadJumping = false;
            this.isAerodynamic = false;
        }
        if (this.isSliding && (!this.sneakButton.Pressed || horizontalSpeedSquare < SmartMovingSelf.Config._slidingSpeedStopFactor.value * 0.01)) {
            this.isSliding = false;
            this.wasCrawling = this.toCrawling();
        }
        if (this.isSliding && this.sp.T > SmartMovingSelf.Config._fallingDistanceMinimum.value) {
            this.isSliding = false;
            this.wasCrawling = true;
            this.isCrawling = false;
        }
        final boolean sneakContinueInput = SmartMovingSelf.Options.isSneakToggleEnabled() ? (this.sneakToggled || this.sneakButton.StartPressed) : this.sneakButton.Pressed;
        final boolean wouldWantSneak = !this.isFlying && !this.isSliding && !this.isHeadJumping && (!this.isDiving || !SmartMovingSelf.Config._diveDownOnSneak.value) && (!this.isSwimming || !SmartMovingSelf.Config._swimDownOnSneak.value || this.isFakeShallowWaterSneaking) && sneakContinueInput && !wantCrawl && !mustCrawl && (!SmartMovingSelf.Config.isCrawlingEnabled() || !this.grabButton.Pressed);
        final boolean wantSneak = SmartMovingSelf.Config.isSneakingEnabled() && wouldWantSneak;
        final boolean moveButtonPressed = this.esp.c.b != 0.0f || this.esp.c.a != 0.0f;
        final boolean moveForwardButtonPressed = this.esp.c.b > 0.0f;
        this.wantSprint = (SmartMovingSelf.Config.isSprintingEnabled() && !this.isSliding && this.sprintButton.Pressed && (moveForwardButtonPressed || this.isClimbing || (this.isSwimming && (moveButtonPressed || (this.sneakButton.Pressed && SmartMovingSelf.Config._swimDownOnSneak.value))) || (this.isDiving && (moveButtonPressed || this.jumpButton.Pressed || (this.sneakButton.Pressed && SmartMovingSelf.Config._diveDownOnSneak.value))) || (this.isFlying && (moveButtonPressed || this.jumpButton.Pressed || this.sneakButton.Pressed))) && !disabled);
        final boolean exhaustionAllowsRunning = !SmartMovingSelf.Config.isRunExhaustionEnabled() || (this.exhaustion < SmartMovingSelf.Config._runExhaustionStop.value && (this.wasRunning || this.exhaustion < SmartMovingSelf.Config._runExhaustionStart.value));
        if (isRunning && this.sp.F && SmartMovingSelf.Config.isRunExhaustionEnabled()) {
            this.maxExhaustionForAction = Math.min(this.maxExhaustionForAction, SmartMovingSelf.Config._runExhaustionStop.value);
            this.maxExhaustionToStartAction = Math.min(this.maxExhaustionToStartAction, SmartMovingSelf.Config._runExhaustionStart.value);
        }
        if (!exhaustionAllowsRunning && isRunning) {
            this.sp.c(isRunning = false);
        }
        if (!this.sp.F && this.isFast && !this.isClimbing && !this.isCeilingClimbing && !this.isDiving && !this.isSwimming) {
            this.isSprintJump = true;
        }
        final boolean exhaustionAllowsSprinting = !SmartMovingSelf.Config.isSprintExhaustionEnabled() || (this.exhaustion <= SmartMovingSelf.Config._sprintExhaustionStop.value && (this.isFast || this.isSprintJump || this.exhaustion <= SmartMovingSelf.Config._sprintExhaustionStart.value));
        if (this.sp.F || this.isFlying || this.isSwimming || this.isDiving || this.sp.J()) {
            this.isSprintJump = false;
        }
        boolean preferSprint = false;
        if (this.wantSprint && !wantSneak) {
            if (!this.isSprintJump && SmartMovingSelf.Config.isSprintExhaustionEnabled()) {
                this.maxExhaustionForAction = Math.min(this.maxExhaustionForAction, SmartMovingSelf.Config._sprintExhaustionStop.value);
                this.maxExhaustionToStartAction = Math.min(this.maxExhaustionToStartAction, SmartMovingSelf.Config._sprintExhaustionStart.value);
            }
            if (exhaustionAllowsSprinting) {
                preferSprint = true;
            }
        }
        boolean isClimbSprintSpeed = true;
        if (this.isClimbing && preferSprint) {
            double minTickDistance;
            if (this.wantClimbUp) {
                minTickDistance = 0.07 * SmartMovingSelf.Config._freeClimbingUpSpeedFactor.value;
            }
            else if (this.wantClimbDown) {
                minTickDistance = 0.11 * SmartMovingSelf.Config._freeClimbingDownSpeedFactor.value;
            }
            else {
                minTickDistance = 0.07;
            }
            isClimbSprintSpeed = (SmartStatisticsFactory.getInstance(this.sp).getTickDistance() >= minTickDistance);
        }
        final boolean canAnySprint = preferSprint && !this.sp.af() && (SmartMovingSelf.Config._sprintDuringItemUsage.value || !this.sp.br());
        final boolean canVerticallySprint = canAnySprint && !this.sp.H;
        final boolean canHorizontallySprint = canAnySprint && this.collidedHorizontallyTickCount < 3;
        final boolean canAllSprint = canHorizontallySprint && canVerticallySprint;
        final boolean wasGroundSprinting = this.isGroundSprinting;
        this.isGroundSprinting = (canHorizontallySprint && this.sp.F && !this.isSwimming && !this.isDiving && !this.isClimbing);
        final boolean isSwimSprinting = canHorizontallySprint && this.isSwimming;
        final boolean isDiveSprinting = canAllSprint && this.isDiving;
        final boolean isCeilingSprinting = canHorizontallySprint && this.isCeilingClimbing;
        final boolean isFlyingSprinting = canAllSprint && this.isFlying;
        final boolean isClimbSprinting = canAnySprint && this.isClimbing && isClimbSprintSpeed;
        this.isFast = (this.isGroundSprinting || isClimbSprinting || isSwimSprinting || isDiveSprinting || isCeilingSprinting || isFlyingSprinting || isClimbSprinting);
        if (this.isGroundSprinting && !wasGroundSprinting) {
            this.wasRunningWhenSprintStarted = this.sp.ai();
            this.sp.c(this.isStandupSprintingOrRunning());
        }
        else if (wasGroundSprinting && !this.isGroundSprinting) {
            this.sp.c(this.wasRunningWhenSprintStarted);
        }
        this.wouldIsSneaking = (wouldWantSneak && !this.wantSprint && !this.isClimbing);
        final boolean wasSneaking = this.isSlow;
        this.isSlow = (wantSneak && this.wouldIsSneaking);
        final boolean wantClimbHolding = (this.isClimbHolding && this.sneakButton.Pressed) || (this.isClimbing && blocked) || (wantClimb && !this.isSwimming && !this.isDiving && !this.isCrawling && (this.sneakButton.Pressed || this.crawlToggled));
        this.isClimbHolding = (wantClimbHolding && this.isClimbing);
        this.isStanding = (horizontalSpeedSquare < 5.0E-4);
        boolean wasCrawlClimbing = this.isCrawlClimbing;
        this.isCrawlClimbing = ((this.wasCrawling || this.isCrawlClimbing) && this.isClimbing && this.isNeighborClimbing && (this.sneakButton.Pressed || this.crawlToggled) && this.esp.c.b > 0.0f);
        if (this.isCrawlClimbing) {
            final boolean canStandUp = !this.isPlayerInSolidBetween(this.sp.E.b - (this.isClimbCrawling ? 0.95 : 1.0), this.sp.E.b);
            if (canStandUp) {
                wasCrawlClimbing = false;
                this.isCrawlClimbing = false;
                if (!this.isClimbCrawling) {
                    this.resetHeightOffset();
                }
            }
            if (!wasCrawlClimbing) {
                this.wasCrawling = false;
                this.isCrawling = false;
            }
        }
        else if (wasCrawlClimbing) {
            final boolean toCrawling = this.sneakButton.Pressed || this.crawlToggled;
            if (!this.isClimbing) {
                this.wasCrawling = this.toCrawling();
                final double minY = this.sp.E.b;
                this.move(0.0, -minY + Math.floor(minY), 0.0, true);
            }
            else if (this.esp.c.b <= 0.0f) {
                this.wasCrawling = toCrawling;
                this.isCrawling = toCrawling;
                this.wantClimbUp = false;
                this.wantClimbDown = false;
                if (!toCrawling) {
                    this.resetHeightOffset();
                }
                final double minY = this.sp.E.b;
                this.move(0.0, -minY + Math.floor(minY) + (toCrawling ? 0.0f : 1.0f), 0.0, true);
            }
            else if (!toCrawling) {
                this.resetHeightOffset();
                final double minY = this.sp.E.b;
                this.move(0.0, Math.ceil(minY) - minY, 0.0, true);
            }
        }
        final boolean wasClimbCrawling = this.isClimbCrawling;
        final boolean needClimbCrawling = this.hasClimbCrawlGap || (this.hasClimbGap && this.isClimbHolding);
        final boolean canClimbCrawling = wantClimbHolding && this.wantClimbUp;
        if (this.climbIntoCount > 1) {
            --this.climbIntoCount;
        }
        else if (this.isClimbCrawling && !needClimbCrawling && this.climbIntoCount == 0) {
            this.climbIntoCount = 6;
        }
        this.isClimbCrawling = (canClimbCrawling && ((needClimbCrawling && this.climbIntoCount == 0) || this.climbIntoCount > 1));
        if (this.isClimbCrawling && !wasClimbCrawling) {
            this.setHeightOffset(-1.0f);
            final boolean wasCollidedHorizontally = this.sp.G;
            this.move(0.0, 0.05, 0.0, true);
            this.sp.G = wasCollidedHorizontally;
        }
        else if (!this.isClimbCrawling && wasClimbCrawling) {
            this.climbIntoCount = 0;
            if (mustCrawl || this.sneakButton.Pressed || this.crawlToggled) {
                final double gapUnderneight = this.sp.E.b - this.getMaxPlayerSolidBetween(this.sp.E.b - 1.0, this.sp.E.b, 0.0);
                if (gapUnderneight >= 0.0 && gapUnderneight < 1.0) {
                    this.wasCrawling = this.toCrawling();
                    this.move(0.0, -gapUnderneight, 0.0, true);
                }
                else {
                    this.resetHeightOffset();
                }
            }
            else {
                this.resetHeightOffset();
            }
        }
        if (this.wasCrawling && !this.isCrawling && !initializeCrawling && !this.esp.bG.b) {
            this.resetHeightOffset();
            this.move(0.0, crawlStandUpBottom - this.sp.E.b, 0.0, true);
        }
        else if ((this.isCrawling && !this.wasCrawling) || initializeCrawling) {
            this.setHeightOffset(-1.0f);
            if (!initializeCrawling || this.sp.q.I) {
                this.move(0.0, -1.0, 0.0, true);
            }
            if (initializeCrawling) {
                this.wasCrawling = this.toCrawling();
            }
        }
        if (this.grabButton.StartPressed) {
            if (this.isShallowDiveOrSwim && wouldWantClimb) {
                this.resetHeightOffset();
                this.move(0.0, this.getMaxPlayerSolidBetween(this.sp.E.b, this.sp.E.e, 0.0) - this.sp.E.b, 0.0, true);
                if (this.jumpButton.Pressed) {
                    this.isStillSwimmingJump = true;
                }
            }
            else if (this.isDipping && wouldWantCrawl && this.dippingDepth >= 0.55f) {
                if (this.dippingDepth >= 0.6f) {
                    this.setHeightOffset(-1.0f);
                    this.move(0.0, -1.6f + this.dippingDepth, 0.0, true);
                    this.isCrawling = false;
                }
                else {
                    this.setHeightOffset(-1.0f);
                    this.move(0.0, -1.0, 0.0, true);
                    this.wasCrawling = this.toCrawling();
                }
            }
        }
        this.isWallJumping = false;
        if (this.continueWallJumping && (this.sp.F || this.isClimbing || !this.jumpButton.Pressed)) {
            this.continueWallJumping = false;
        }
        final boolean canWallJumping = SmartMovingSelf.Config.isWallJumpEnabled() && !this.isHeadJumping && !this.sp.F && !this.isClimbing && !this.isSwimming && !this.isDiving && !isLevitating && !this.isFlying;
        boolean triggerWallJumping = false;
        if (SmartMovingSelf.Options._wallJumpDoubleClick.value) {
            if (canWallJumping) {
                if (this.jumpButton.StartPressed) {
                    if (this.wallJumpCount == 0) {
                        this.wallJumpCount = SmartMovingSelf.Options.wallJumpDoubleClickTicks();
                    }
                    else {
                        triggerWallJumping = true;
                        this.wallJumpCount = 0;
                    }
                }
                else if (this.wallJumpCount > 0) {
                    --this.wallJumpCount;
                }
            }
            else {
                this.wallJumpCount = 0;
            }
        }
        else {
            triggerWallJumping = this.jumpButton.StartPressed;
        }
        this.wantWallJumping = (canWallJumping && (triggerWallJumping || this.continueWallJumping || (this.wantWallJumping && this.jumpButton.Pressed && !this.sp.G)));
        final boolean canAngleJump = !isSleeping && this.sp.F && !this.isCrawling && !this.isClimbing && !this.isClimbCrawling && !this.isSwimming && !this.isDiving;
        final boolean canSideJump = SmartMovingSelf.Config.isSideJumpEnabled() && canAngleJump;
        final boolean canLeftJump = canSideJump && !this.rightButton.Pressed;
        final boolean canRightJump = canSideJump && !this.leftButton.Pressed;
        final boolean canBackJump = SmartMovingSelf.Config.isBackJumpEnabled() && canAngleJump && !this.forwardButton.Pressed && !this.isStandupSprintingOrRunning();
        if (canLeftJump) {
            if (this.leftButton.StartPressed) {
                if (this.leftJumpCount == 0) {
                    this.leftJumpCount = SmartMovingSelf.Options.angleJumpDoubleClickTicks();
                }
                else {
                    this.leftJumpCount = -1;
                }
            }
            else if (this.leftJumpCount > 0) {
                --this.leftJumpCount;
            }
        }
        else {
            this.leftJumpCount = 0;
        }
        if (canRightJump) {
            if (this.rightButton.StartPressed) {
                if (this.rightJumpCount == 0) {
                    this.rightJumpCount = SmartMovingSelf.Options.angleJumpDoubleClickTicks();
                }
                else {
                    this.rightJumpCount = -1;
                }
            }
            else if (this.rightJumpCount > 0) {
                --this.rightJumpCount;
            }
        }
        else {
            this.rightJumpCount = 0;
        }
        if (canBackJump) {
            if (this.backButton.StartPressed) {
                if (this.backJumpCount == 0) {
                    this.backJumpCount = SmartMovingSelf.Options.angleJumpDoubleClickTicks();
                }
                else {
                    this.backJumpCount = -1;
                }
            }
            else if (this.backJumpCount > 0) {
                --this.backJumpCount;
            }
        }
        else {
            this.backJumpCount = 0;
        }
        if (this.rightJumpCount == -2 && this.backJumpCount <= 0) {
            this.rightJumpCount = -1;
        }
        if (this.leftJumpCount == -2 && this.backJumpCount <= 0) {
            this.leftJumpCount = -1;
        }
        if (this.backJumpCount == -2 && (this.leftJumpCount <= 0 || this.rightJumpCount <= 0)) {
            this.backJumpCount = -1;
        }
        if (this.rightJumpCount == -1 && this.backJumpCount > 0) {
            this.rightJumpCount = -2;
        }
        if (this.leftJumpCount == -1 && this.backJumpCount > 0) {
            this.leftJumpCount = -2;
        }
        if (this.backJumpCount == -1 && (this.leftJumpCount > 0 || this.rightJumpCount > 0)) {
            this.backJumpCount = -2;
        }
        if (this.sp.F || this.sp.H) {
            this.angleJumpType = 0;
        }
        this.isRopeSliding = Interface.isRopeSliding();
        final boolean isSneakToggleEnabled = SmartMovingSelf.Options.isSneakToggleEnabled();
        final boolean isCrawlToggleEnabled = SmartMovingSelf.Options.isCrawlToggleEnabled();
        boolean willStopCrawl = false;
        boolean willStopCrawlStartSneak = false;
        if (isSneakToggleEnabled || isCrawlToggleEnabled) {
            if (this.isCrawling && this.jumpButton.StopPressed) {
                willStopCrawlStartSneak = true;
            }
            if (this.isCrawling && this.sneakButton.StopPressed && !this.ignoreNextStopSneakButtonPressed) {
                willStopCrawlStartSneak = true;
            }
            if (!this.isCrawling && !this.isCrawlClimbing && !this.isClimbCrawling) {
                willStopCrawl = true;
            }
            willStopCrawl |= willStopCrawlStartSneak;
        }
        boolean willStopSneak = false;
        if (isSneakToggleEnabled) {
            if (this.isCrawling && !willStopCrawlStartSneak) {
                willStopSneak = true;
            }
            if (wantSneak && this.wantSprint && this.sneakButton.StartPressed && this.sneakToggled) {
                willStopSneak = true;
                this.ignoreNextStopSneakButtonPressed = true;
            }
            if (wasSneaking && this.sneakButton.StartPressed) {
                willStopSneak = true;
            }
            if (!this.isSwimming && !this.isDiving && this.jumpButton.StopPressed) {
                willStopSneak = true;
            }
        }
        boolean willStartSneak = false;
        if (isSneakToggleEnabled) {
            if (willStopCrawlStartSneak && this.sneakButton.StopPressed) {
                willStartSneak = true;
            }
            if (this.isFast && this.sneakButton.StopPressed && !this.ignoreNextStopSneakButtonPressed) {
                willStartSneak = true;
            }
            if (this.isSlow && !wasSneaking) {
                willStartSneak = true;
            }
        }
        boolean willStartCrawl = false;
        if (isCrawlToggleEnabled) {
            if (this.isCrawling && !this.wasCrawling) {
                willStartCrawl = true;
            }
            if (this.isClimbCrawling && !wasClimbCrawling) {
                willStartCrawl = true;
            }
        }
        if (isSneakToggleEnabled) {
            if (willStartSneak) {
                this.sneakToggled = true;
            }
            if (willStopSneak) {
                this.sneakToggled = false;
            }
        }
        if (isCrawlToggleEnabled) {
            if (willStartCrawl) {
                this.crawlToggled = true;
                this.ignoreNextStopSneakButtonPressed = this.sneakButton.Pressed;
            }
            if (willStopCrawl) {
                this.crawlToggled = false;
            }
        }
        if (this.sneakButton.StopPressed) {
            this.ignoreNextStopSneakButtonPressed = false;
        }
        this.wasRunning = isRunning;
        this.wasLevitating = isLevitating;
    }
    
    private boolean toCrawling() {
        this.isCrawling = true;
        if (SmartMovingSelf.Options.isCrawlToggleEnabled()) {
            this.crawlToggled = true;
        }
        return this.ignoreNextStopSneakButtonPressed = true;
    }
    
    public void addToSendQueue() {
        if (!this.sp.q.I) {
            return;
        }
        final boolean isSmall = this.sp.P < 1.0f;
        long state = 0L;
        state |= (this.isRopeSliding ? 1 : 0);
        state <<= 1;
        state |= (this.isWallJumping ? 1 : 0);
        state <<= 1;
        state |= (this.isFast ? 1 : 0);
        state <<= 1;
        state |= (this.isSlow ? 1 : 0);
        state <<= 1;
        state |= (this.isClimbBackJumping ? 1 : 0);
        state <<= 1;
        state |= (this.isClimbJumping ? 1 : 0);
        state <<= 1;
        state |= (this.isHandsVineClimbing ? 1 : 0);
        state <<= 1;
        state |= (this.isFeetVineClimbing ? 1 : 0);
        state <<= 3;
        state |= this.angleJumpType;
        state <<= 1;
        state |= (this.isSliding ? 1 : 0);
        state <<= 1;
        state |= (this.isHeadJumping ? 1 : 0);
        state <<= 1;
        state |= (this.isLevitating ? 1 : 0);
        state <<= 1;
        state |= (this.isCeilingClimbing ? 1 : 0);
        state <<= 1;
        state |= (this.doFlyingAnimation() ? 1 : 0);
        state <<= 1;
        state |= (this.doFallingAnimation() ? 1 : 0);
        state <<= 1;
        state |= (isSmall ? 1 : 0);
        state <<= 1;
        state |= (this.isClimbing ? 1 : 0);
        state <<= 1;
        state |= (this.isCrawling ? 1 : 0);
        state <<= 1;
        state |= (this.isCrawlClimbing ? 1 : 0);
        state <<= 1;
        state |= (this.isSwimming ? 1 : 0);
        state <<= 1;
        state |= (this.isDipping ? 1 : 0);
        state <<= 1;
        state |= (this.isDiving ? 1 : 0);
        state <<= 1;
        state |= (this.isp.getIsJumpingField() ? 1 : 0);
        state <<= 4;
        state |= this.actualHandsClimbType;
        state <<= 4;
        state |= this.actualFeetClimbType;
        boolean sendStatePacket = state != this.prevPacketState;
        final int currentWorldPlayerEntitiesSize = this.sp.q.h.size();
        if (currentWorldPlayerEntitiesSize == 0) {
            sendStatePacket = false;
            this.lastWorldPlayerEntitiesSize = currentWorldPlayerEntitiesSize;
            this.lastWorldPlayerLastEnttyId = -1;
        }
        else {
            final int currentWorldPlayerLastEnttyId = this.sp.q.h.get(currentWorldPlayerEntitiesSize - 1).k;
            if (currentWorldPlayerEntitiesSize != this.lastWorldPlayerEntitiesSize) {
                if (currentWorldPlayerEntitiesSize > this.lastWorldPlayerEntitiesSize) {
                    sendStatePacket = true;
                }
                this.lastWorldPlayerEntitiesSize = currentWorldPlayerEntitiesSize;
                this.lastWorldPlayerLastEnttyId = currentWorldPlayerLastEnttyId;
            }
            else if (currentWorldPlayerLastEnttyId != this.lastWorldPlayerLastEnttyId) {
                sendStatePacket = true;
                this.lastWorldPlayerLastEnttyId = currentWorldPlayerLastEnttyId;
            }
        }
        if (sendStatePacket) {
            SmartMovingPacketStream.sendState(SmartMovingComm.instance, this.sp.k, state);
            this.prevPacketState = state;
        }
    }
    
    @Override
    public boolean isSneaking() {
        return (this.isSlow && (this.sp.F || this.isp.getIsInWebField())) || (!SmartMovingSelf.Config._sneak.value && this.wouldIsSneaking && this.jumpCharge > 0.0f) || ((this.sp.o != null || !SmartMovingSelf.Config.enabled) && this.isp.localIsSneaking()) || (!SmartMovingSelf.Config._crawlOverEdge.value && this.isCrawling && !this.isClimbing);
    }
    
    public boolean isStandupSprintingOrRunning() {
        return (this.isFast || this.sp.ai()) && this.sp.F && !this.isSliding && !this.isCrawling;
    }
    
    public boolean isRunning() {
        return this.sp.ai() && !this.isFast && this.sp.F;
    }
    
    public void beforeGetSleepTimer() {
        SmartMovingRender.renderGuiIngame(this.isp.getMcField());
    }
    
    public void jump() {
        this.jumpAvoided = true;
    }
    
    public void writeEntityToNBT(final by nBTTagCompound) {
        this.isp.localWriteEntityToNBT(nBTTagCompound);
        final by abilities = nBTTagCompound.l("abilities");
        if (abilities != null && abilities.b("flying")) {
            abilities.a("flying", this.sp.bG.b);
        }
    }
    
    @Override
    public boolean isJumping() {
        return this.isp.getIsJumpingField();
    }
    
    @Override
    public boolean doFlyingAnimation() {
        return (SmartMovingSelf.Config.isFlyingEnabled() || SmartMovingSelf.Config.isLevitationAnimationEnabled()) && this.sp.bG.b;
    }
    
    @Override
    public boolean doFallingAnimation() {
        return SmartMovingSelf.Config.isFallAnimationEnabled() && !this.sp.F && this.sp.T > SmartMovingSelf.Config._fallAnimationDistanceMinimum.value;
    }
    
    public void onLivingJump() {
        if (SmartMovingSelf._onLivingJump == null) {
            return;
        }
        try {
            Reflect.Invoke(SmartMovingSelf._onLivingJump, null, this.sp);
        }
        catch (final Throwable t) {
            t.printStackTrace();
        }
    }
    
    public float getFOVMultiplier() {
        if (!SmartMovingSelf.Config.enabled) {
            return this.isp.localGetFOVMultiplier();
        }
        final float landMovmentFactor = this.getLandMovementFactor();
        this.setLandMovementFactor(this.fadingPerspectiveFactor);
        final float result = this.isp.localGetFOVMultiplier();
        this.setLandMovementFactor(landMovmentFactor);
        return result;
    }
    
    public float getLandMovementFactor() {
        return this.sp.bg();
    }
    
    public void setLandMovementFactor(final float landMovementFactor) {
        Reflect.SetField(SmartMovingSelf._attributeValue, this.sp.a(tp.d), landMovementFactor);
    }
    
    static {
        SmartMovingSelf.out_handsClimbGap = new ClimbGap();
        SmartMovingSelf.out_feetClimbGap = new ClimbGap();
        SmartMovingSelf.inout_handsClimbing = new HandsClimbing[1];
        SmartMovingSelf.inout_feetClimbing = new FeetClimbing[1];
        _attributeValue = Reflect.GetField(ox.class, Install.ModifiableAttributeInstance_attributeValue, false);
        _chatMessageList = Reflect.GetField(auu.class, Install.GuiNewChat_chatMessageList, false);
        _onLivingJump = Reflect.GetMethod(Reflect.LoadClass(atv.class, Install.ForgeHooks, false), Install.ForgeHooks_onLivingJump, false, og.class);
    }
}
