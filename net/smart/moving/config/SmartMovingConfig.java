// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.config;

import java.io.PrintWriter;
import java.io.File;
import java.util.List;
import net.smart.properties.Properties;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;
import java.util.Dictionary;
import net.smart.properties.Property;

public class SmartMovingConfig extends SmartMovingProperties
{
    private static final String _smartLadderClimbingSpeedPropertiesFileName = "smart_ladder_climbing_speed_options.txt";
    private static final String _smartClimbingPropertiesFileName = "smart_climbing_options.txt";
    private static final String _smartMovingPropertiesFileName = "smart_moving_options.txt";
    private static final String _smartMovingClientServerPropertiesFileName = "smart_moving_server_options.txt";
    private static final String _slcs = "0.1";
    private static final String _sc = "0.2";
    private static final String _sm_1_0 = "1.0";
    private static final String _sm_1_1 = "1.1";
    private static final String _sm_1_2 = "1.2";
    private static final String _sm_1_3 = "1.3";
    private static final String _sm_1_4 = "1.4";
    private static final String _sm_1_5 = "1.5";
    private static final String _sm_1_6 = "1.6";
    private static final String _sm_1_7 = "1.7";
    private static final String _sm_1_8 = "1.8";
    private static final String _sm_1_9 = "1.9";
    private static final String _sm_1_10 = "1.10";
    private static final String _sm_1_11 = "1.11";
    public static final String _sm_2_0 = "2.0";
    public static final String _sm_2_1 = "2.1";
    public static final String _sm_2_2 = "2.2";
    public static final String _sm_2_3 = "2.3";
    public static final String _sm_2_4 = "2.4";
    public static final String _sm_2_5 = "2.5";
    public static final String _sm_2_6 = "2.6";
    public static final String _sm_3_0 = "3.0";
    public static final String _sm_3_1 = "3.1";
    public static final String _sm_3_2 = "3.2";
    public static final String _sm_current = "3.2";
    private static final String[] _all_sm;
    private static final String[] _all_old;
    protected static final String[] _pre_sm_1_3;
    protected static final String[] _pre_sm_1_4;
    protected static final String[] _pre_sm_1_5;
    protected static final String[] _pre_sm_1_6;
    protected static final String[] _pre_sm_1_7;
    protected static final String[] _pre_sm_1_8;
    protected static final String[] _pre_sm_1_9;
    protected static final String[] _pre_sm_1_10;
    protected static final String[] _pre_sm_1_11;
    protected static final String[] _pre_sm_2_0;
    protected static final String[] _pre_sm_2_1;
    protected static final String[] _pre_sm_2_2;
    protected static final String[] _pre_sm_2_3;
    protected static final String[] _pre_sm_2_4;
    protected static final String[] _pre_sm_2_5;
    protected static final String[] _pre_sm_2_6;
    protected static final String[] _pre_sm_3_0;
    protected static final String[] _pre_sm_3_1;
    protected static final String[] _pre_sm_3_2;
    private static final String[] _pre_sm_1_7_post_1_4;
    private static final String[] _pre_sm_1_7_post_1_0;
    public static final String[] _all;
    public final Property<Float> _speedFactor;
    public final Property<Boolean> _speedUser;
    public final Property<Float> _speedUserFactor;
    public final Property<Integer> _speedUserExponent;
    public final Property<String> _baseClimb;
    public final Property<Boolean> _freeClimb;
    public final Property<Boolean> _freeBaseLadderClimb;
    public final Property<Boolean> _freeBaseVineClimb;
    public final Property<Boolean> _isFreeBaseClimb;
    public final Property<Boolean> _isSmartBaseClimb;
    public final Property<Boolean> _isSimpleBaseClimb;
    public final Property<Boolean> _isStandardBaseClimb;
    public final Property<Float> _freeClimbingUpSpeedFactor;
    public final Property<Float> _freeClimbingDownSpeedFactor;
    public final Property<Float> _freeClimbingHorizontalSpeedFactor;
    public final Property<Float> _freeClimbingOrthogonalDirectionAngle;
    public final Property<Float> _freeClimbingDiagonalDirectionAngle;
    public final Property<Boolean> _freeClimbingAutoLaddder;
    public final Property<Boolean> _freeClimbingAutoVine;
    public final Property<Float> _freeOneLadderClimbUpSpeedFactor;
    public final Property<Float> _freeBothLadderClimbUpSpeedFactor;
    public final Property<Boolean> _freeFenceClimbing;
    public final Property<Float> _freeClimbFallDamageStartDistance;
    public final Property<Float> _freeClimbFallDamageFactor;
    public final Property<Float> _freeClimbFallMaximumDistance;
    public final Property<Boolean> _climbExhaustion;
    public final Property<Float> _climbExhaustionStart;
    public final Property<Float> _climbExhaustionStop;
    public final Property<Float> _climbStrafeExhaustionGain;
    public final Property<Float> _climbUpExhaustionGain;
    public final Property<Float> _climbDownExhaustionGain;
    public final Property<Float> _climbStrafeUpExhaustionGain;
    public final Property<Float> _climbStrafeDownExhaustionGain;
    public final Property<Boolean> _ceilingClimbing;
    public final Property<Float> _ceilingClimbingSpeedFactor;
    public final Property<String[]> _ceilingClimbConfigurationString;
    public final Property<Dictionary<Object, Set<Integer>>> _ceilingClimbConfigurationObject;
    public final Property<Boolean> _ceilingClimbExhaustion;
    public final Property<Float> _ceilingClimbExhaustionStart;
    public final Property<Float> _ceilingClimbExhaustionStop;
    public final Property<Float> _ceilingClimbExhaustionGain;
    public final Property<Boolean> _swim;
    public final Property<Float> _swimSpeedFactor;
    public final Property<Boolean> _swimDownOnSneak;
    public final Property<Float> _swimParticlePeriodFactor;
    public final Property<Boolean> _dive;
    public final Property<Float> _diveSpeedFactor;
    public final Property<Boolean> _diveDownOnSneak;
    public final Property<Boolean> _lavaLikeWater;
    public final Property<Float> _lavaSwimParticlePeriodFactor;
    public final Property<Boolean> _run;
    public final Property<Float> _runFactor;
    public final Property<Boolean> _runExhaustion;
    public final Property<Float> _runExhaustionStart;
    public final Property<Float> _runExhaustionStop;
    public final Property<Float> _runExhaustionGainFactor;
    public final Property<Boolean> _sprint;
    public final Property<Float> _sprintFactor;
    public final Property<Boolean> _sprintExhaustion;
    public final Property<Float> _sprintExhaustionStart;
    public final Property<Float> _sprintExhaustionStop;
    public final Property<Float> _sprintExhaustionGainFactor;
    public final Property<Boolean> _sneak;
    public final Property<Float> _sneakFactor;
    public final Property<Boolean> _sneakNameTag;
    public final Property<Boolean> _crawl;
    public final Property<Float> _crawlFactor;
    public final Property<Boolean> _crawlNameTag;
    public final Property<Boolean> _crawlOverEdge;
    public final Property<Boolean> _slide;
    public final Property<Float> _slideControlDegrees;
    public final Property<Float> _slideSlipperinessFactor;
    public final Property<Float> _slidingSpeedStopFactor;
    public final Property<Float> _slideParticlePeriodFactor;
    public final Property<Boolean> _fly;
    public final Property<Float> _flyingSpeedFactor;
    public final Property<Boolean> _levitateSmall;
    public final Property<Boolean> _levitateAnimation;
    public final Property<Float> _fallingDistanceMinimum;
    public final Property<Boolean> _fallAnimation;
    public final Property<Float> _fallAnimationDistanceMinimum;
    public final Property<Boolean> _jump;
    public final Property<Float> _jumpControlFactor;
    public final Property<Float> _jumpHorizontalFactor;
    public final Property<Float> _jumpVerticalFactor;
    public final Property<Boolean> _standJump;
    public final Property<Float> _standJumpVerticalFactor;
    public final Property<Boolean> _sneakJump;
    public final Property<Float> _sneakJumpHorizontalFactor;
    public final Property<Float> _sneakJumpVerticalFactor;
    public final Property<Boolean> _walkJump;
    public final Property<Float> _walkJumpHorizontalFactor;
    public final Property<Float> _walkJumpVerticalFactor;
    public final Property<Boolean> _runJump;
    public final Property<Float> _runJumpHorizontalFactor;
    public final Property<Float> _runJumpVerticalFactor;
    public final Property<Boolean> _sprintJump;
    public final Property<Float> _sprintJumpHorizontalFactor;
    public final Property<Float> _sprintJumpVerticalFactor;
    public final Property<Boolean> _jumpCharge;
    public final Property<Float> _jumpChargeMaximum;
    public final Property<Float> _jumpChargeFactor;
    public final Property<Boolean> _jumpChargeCancelOnSneakRelease;
    public final Property<Boolean> _headJump;
    public final Property<Float> _headJumpControlFactor;
    public final Property<Float> _headJumpChargeMaximum;
    public final Property<Float> _headFallDamageStartDistance;
    public final Property<Float> _headFallDamageFactor;
    public final Property<Boolean> _angleJumpSide;
    public final Property<Boolean> _angleJumpBack;
    public final Property<Float> _angleJumpHorizontalFactor;
    public final Property<Float> _angleJumpVerticalFactor;
    public final Property<Boolean> _climbUpJump;
    public final Property<Float> _climbUpJumpVerticalFactor;
    public final Property<Float> _climbUpJumpHandsOnlyVerticalFactor;
    public final Property<Boolean> _climbBackUpJump;
    public final Property<Float> _climbBackUpJumpVerticalFactor;
    public final Property<Float> _climbBackUpJumpHorizontalFactor;
    public final Property<Float> _climbBackUpJumpHandsOnlyVerticalFactor;
    public final Property<Float> _climbBackUpJumpHandsOnlyHorizontalFactor;
    public final Property<Boolean> _climbBackHeadJump;
    public final Property<Float> _climbBackHeadJumpVerticalFactor;
    public final Property<Float> _climbBackHeadJumpHorizontalFactor;
    public final Property<Float> _climbBackHeadJumpHandsOnlyVerticalFactor;
    public final Property<Float> _climbBackHeadJumpHandsOnlyHorizontalFactor;
    public final Property<Boolean> _wallUpJump;
    public final Property<Float> _wallUpJumpVerticalFactor;
    public final Property<Float> _wallUpJumpHorizontalFactor;
    public final Property<Float> _wallUpJumpFallMaximumDistance;
    public final Property<Float> _wallUpJumpOrthogonalTolerance;
    public final Property<Boolean> _wallHeadJump;
    public final Property<Float> _wallHeadJumpVerticalFactor;
    public final Property<Float> _wallHeadJumpHorizontalFactor;
    public final Property<Float> _wallHeadJumpFallMaximumDistance;
    private final Property<Boolean> _old_jumpExhaustion;
    private final Property<Float> _old_jumpExhaustionGain;
    private final Property<Float> _old_jumpExhaustionStop;
    private final Property<Boolean> _old_sneakJumpExhaustion;
    private final Property<Float> _old_sneakJumpExhaustionGain;
    private final Property<Float> _old_sneakJumpExhaustionStop;
    private final Property<Boolean> _old_runJumpExhaustion;
    private final Property<Float> _old_runJumpExhaustionGain;
    private final Property<Float> _old_runJumpExhaustionStop;
    private final Property<Boolean> _old_sprintJumpExhaustion;
    private final Property<Float> _old_sprintJumpExhaustionGain;
    private final Property<Float> _old_sprintJumpExhaustionStop;
    public final Property<Boolean> _jumpExhaustion;
    public final Property<Float> _jumpExhaustionGainFactor;
    public final Property<Float> _jumpExhaustionStopFactor;
    public final Property<Boolean> _upJumpExhaustion;
    public final Property<Float> _upJumpExhaustionGainFactor;
    public final Property<Float> _upJumpExhaustionStopFactor;
    public final Property<Boolean> _climbJumpExhaustion;
    public final Property<Float> _climbJumpExhaustionGainFactor;
    public final Property<Float> _climbJumpExhaustionStopFactor;
    public final Property<Boolean> _climbJumpUpExhaustion;
    public final Property<Float> _climbJumpUpExhaustionGainFactor;
    public final Property<Float> _climbJumpUpExhaustionStopFactor;
    public final Property<Boolean> _climbJumpBackUpExhaustion;
    public final Property<Float> _climbJumpBackUpExhaustionGainFactor;
    public final Property<Float> _climbJumpBackUpExhaustionStopFactor;
    public final Property<Boolean> _climbJumpBackHeadExhaustion;
    public final Property<Float> _climbJumpBackHeadExhaustionGainFactor;
    public final Property<Float> _climbJumpBackHeadExhaustionStopFactor;
    public final Property<Boolean> _angleJumpExhaustion;
    public final Property<Float> _angleJumpExhaustionGainFactor;
    public final Property<Float> _angleJumpExhaustionStopFactor;
    public final Property<Boolean> _wallJumpExhaustion;
    public final Property<Float> _wallJumpExhaustionGainFactor;
    public final Property<Float> _wallJumpExhaustionStopFactor;
    public final Property<Boolean> _wallUpJumpExhaustion;
    public final Property<Float> _wallUpJumpExhaustionGainFactor;
    public final Property<Float> _wallUpJumpExhaustionStopFactor;
    public final Property<Boolean> _wallHeadJumpExhaustion;
    public final Property<Float> _wallHeadJumpExhaustionGainFactor;
    public final Property<Float> _wallHeadJumpExhaustionStopFactor;
    public final Property<Boolean> _standJumpExhaustion;
    public final Property<Float> _standJumpExhaustionGainFactor;
    public final Property<Float> _standJumpExhaustionStopFactor;
    public final Property<Boolean> _sneakJumpExhaustion;
    public final Property<Float> _sneakJumpExhaustionGainFactor;
    public final Property<Float> _sneakJumpExhaustionStopFactor;
    public final Property<Boolean> _walkJumpExhaustion;
    public final Property<Float> _walkJumpExhaustionGainFactor;
    public final Property<Float> _walkJumpExhaustionStopFactor;
    public final Property<Boolean> _runJumpExhaustion;
    public final Property<Float> _runJumpExhaustionGainFactor;
    public final Property<Float> _runJumpExhaustionStopFactor;
    public final Property<Boolean> _sprintJumpExhaustion;
    public final Property<Float> _sprintJumpExhaustionGainFactor;
    public final Property<Float> _sprintJumpExhaustionStopFactor;
    public final Property<Boolean> _jumpChargeExhaustion;
    public final Property<Float> _jumpChargeExhaustionGainFactor;
    public final Property<Float> _jumpChargeExhaustionStopFactor;
    public final Property<Boolean> _jumpSlideExhaustion;
    public final Property<Float> _jumpSlideExhaustionGainFactor;
    public final Property<Float> _jumpSlideExhaustionStopFactor;
    public final Property<Float> _baseExhautionGainFactor;
    public final Property<Float> _baseExhautionLossFactor;
    public final Property<Float> _sprintingExhautionLossFactor;
    public final Property<Float> _runningExhautionLossFactor;
    public final Property<Float> _walkingExhautionLossFactor;
    public final Property<Float> _sneakingExhautionLossFactor;
    public final Property<Float> _standingExhautionLossFactor;
    public final Property<Float> _fallExhautionLossFactor;
    public final Property<Float> _ceilClimbingExhaustionLossFactor;
    public final Property<Float> _climbingExhaustionLossFactor;
    public final Property<Float> _crawlingExhaustionLossFactor;
    public final Property<Float> _dippingExhaustionLossFactor;
    public final Property<Float> _swimmingExhaustionLossFactor;
    public final Property<Float> _divingExhaustionLossFactor;
    public final Property<Float> _normalExhaustionLossFactor;
    public final Property<Boolean> _exhaustionLossHunger;
    public final Property<Float> _exhaustionLossHungerFactor;
    public final Property<Float> _exhaustionLossFoodLevelMinimum;
    public final Property<Boolean> _hungerGain;
    public final Property<Float> _baseHungerGainFactor;
    public final Property<Float> _sprintingHungerGainFactor;
    public final Property<Float> _runningHungerGainFactor;
    public final Property<Float> _walkingHungerGainFactor;
    public final Property<Float> _sneakingHungerGainFactor;
    public final Property<Float> _standingHungerGainFactor;
    public final Property<Float> _climbingHungerGainFactor;
    public final Property<Float> _crawlingHungerGainFactor;
    public final Property<Float> _ceilClimbingHungerGainFactor;
    public final Property<Float> _swimmingHungerGainFactor;
    public final Property<Float> _divingHungerGainFactor;
    public final Property<Float> _dippingHungerGainFactor;
    public final Property<Float> _normalHungerGainFactor;
    public final Property<Float> _alwaysHungerGain;
    public final Property<Float> _usageSpeedFactor;
    public final Property<Float> _usageSwordSpeedFactor;
    public final Property<Float> _usageBowSpeedFactor;
    public final Property<Float> _usageFoodSpeedFactor;
    public final Property<Boolean> _sprintDuringItemUsage;
    public final Property<Boolean> _replaceRopeClimbing;
    public final Property<String[]> _survivalConfigKeys;
    public final Property<String> _survivalDefaultConfigKey;
    public final Property<String[]> _creativeConfigKeys;
    public final Property<String> _creativeDefaultConfigKey;
    public final Property<String[]> _adventureConfigKeys;
    public final Property<String> _adventureDefaultConfigKey;
    public final Property<String> _configKeyName;
    public final Property<Boolean> _serverConfig;
    public final Property<Map<String, String>> _survivalDefaultConfigUserKeys;
    public final Property<Map<String, String>> _creativeDefaultConfigUserKeys;
    public final Property<Map<String, String>> _adventureDefaultConfigUserKeys;
    public final Property<Map<String, Integer>> _speedUsersExponents;
    public final Property<Boolean> _globalConfig;
    public final Property<String[]> _usersWithChangeConfigRights;
    public final Property<String[]> _usersWithChangeSpeedRights;
    protected static final int Unknown = -1;
    protected static final int Survival = 0;
    protected static final int Creative = 1;
    protected static final int Adventure = 2;
    private static final DecimalFormat formatter;
    private static final BigDecimal ten;
    public static final Object defaultSpeedPercent;
    
    public SmartMovingConfig() {
        this._speedFactor = Properties.PositiveFactor("move.speed.factor", new String[0]).defaults(1.0f, new String[0]).comment("Global player speed factor (>= 0)").book("Global Speed", "Below you find the options to manipulate the global speed applied to all speeds.");
        this._speedUser = this.Creative("move.speed.user", new String[0]).defaults(true, SmartMovingConfig._pre_sm_3_2).comment("To switch on/off in-game speed manipulation");
        this._speedUserFactor = Properties.PositiveFactor("move.speed.user.factor", new String[0]).singular().defaults(0.2f, new String[0]).min(1.0E-4f).comment("The factor for in-game speed manipulation (>= 0.0001)");
        this._speedUserExponent = Properties.Integer("move.speed.user.exponent", new String[0]).singular().defaults(0, new String[0]).comment("The exponent for in-game speed manipulation");
        this._baseClimb = Properties.String().defaults("free", new String[0]).key("move.climb.base", new String[0]).key("move.climb.ladder", SmartMovingConfig._pre_sm_1_10).key("climbing.ladder", SmartMovingConfig._all_old).comment("To manipulate the ladder and vine climbing mode (possible values are \"free\", \"smart\", \"simple\" and \"standard\")").book("Climbing", "Below you find all free and ladder climbing options except those for ceiling climbing.");
        this._freeClimb = Properties.Unmodified().key("move.climb.free", new String[0]).key("climbing.free", SmartMovingConfig._all_old).comment("To switch on/off free climbing");
        this._freeBaseLadderClimb = Properties.Modified().key("move.climb.free.base.ladder", new String[0]).comment("To switch on/off remaining base climbing behavior on ladders while free climbing is enabled for ladders (also see \"move.climb.base\")");
        this._freeBaseVineClimb = Properties.Modified().key("move.climb.free.base.vine", new String[0]).defaults(true, SmartMovingConfig._pre_sm_1_11).comment("To switch on/off remaining base climbing behavior on vines while free climbing is enabled for vines (also see \"move.climb.base\")");
        this._isFreeBaseClimb = this._baseClimb.is("free").and(this._freeClimb);
        this._isSmartBaseClimb = this._baseClimb.is("smart").andNot(this._isFreeBaseClimb);
        this._isSimpleBaseClimb = this._baseClimb.is("simple").andNot(this._isFreeBaseClimb).andNot(this._isSmartBaseClimb);
        this._isStandardBaseClimb = this._isFreeBaseClimb.not().andNot(this._isSmartBaseClimb).andNot(this._isSimpleBaseClimb);
        this._freeClimbingUpSpeedFactor = Properties.PositiveFactor("move.climb.free.up.speed.factor", new String[0]).comment("Climbing up speed factor relative to default climbing up speed (>= 0)").chapter(new String[0]);
        this._freeClimbingDownSpeedFactor = Properties.PositiveFactor("move.climb.free.down.speed.factor", new String[0]).comment("Climbing down speed factor relative to default climbing down speed (>= 0)");
        this._freeClimbingHorizontalSpeedFactor = Properties.PositiveFactor("move.climb.free.horizontal.speed.factor", new String[0]).comment("Climbing horizontal speed factor relative to default climbing horizontal speed (>= 0)");
        this._freeClimbingOrthogonalDirectionAngle = Properties.Positive("move.climb.free.direction.orthogonal.angle", new String[0]).values(90.0f, 90.0f, 180.0f).comment("Climbing N,S,E,W grabbing angle in degrees");
        this._freeClimbingDiagonalDirectionAngle = Properties.Positive("move.climb.free.direction.diagonal.angle", new String[0]).values(80.0f, 45.0f, 180.0f).comment("Climbing NW,SW,SE,NE grabbing angle in degrees");
        this._freeClimbingAutoLaddder = Properties.Unmodified("move.climb.free.ladder.auto", new String[0]).depends(this._isFreeBaseClimb).comment("Whether the \"grab\" button will automatically be triggered while being on ladders and looking in the right direction").section(new String[0]);
        this._freeClimbingAutoVine = Properties.Unmodified("move.climb.free.vine.auto", new String[0]).depends(this._isFreeBaseClimb).comment("Whether the \"grab\" button will automatically be triggered while being on standard climbable vines and looking in the right direction");
        this._freeOneLadderClimbUpSpeedFactor = Properties.PositiveFactor("move.climb.free.ladder.one.up.speed.factor", new String[0]).defaults(1.0153f, new String[0]).defaults(0.71f, SmartMovingConfig._pre_sm_2_4).comment("Additional speed factor when climbing straight up on one ladder block (>= 0)");
        this._freeBothLadderClimbUpSpeedFactor = Properties.IncreasingFactor("move.climb.free.ladder.two.up.speed.factor", new String[0]).defaults(1.43f, new String[0]).comment("Additional speed factor when climbing straight up on two ladder blocks (>= 1)");
        this._freeFenceClimbing = Properties.Unmodified("move.climb.free.fence", new String[0]).comment("Climbing over fences");
        this._freeClimbFallDamageStartDistance = Properties.Positive("move.climb.fall.damage.start.distance", new String[0]).values(2.0f, 1.0f, 3.0f).comment("Distance in blocks to fall before suffering fall damage when starting to climb (>= 1, <= 3)").section(new String[0]);
        this._freeClimbFallDamageFactor = Properties.IncreasingFactor("move.climb.fall.damage.factor", new String[0]).defaults(2.0f, new String[0]).comment("Damage factor applied to the remaining distance (>= 1)");
        this._freeClimbFallMaximumDistance = Properties.Positive("move.climb.fall.maximum.distance", new String[0]).defaults(3.0f, new String[0]).min(this._freeClimbFallDamageStartDistance).comment("Distance in blocks to fall to block all climbing attempts (>= \"move.climb.fall.damage.start.distance\")");
        this._climbExhaustion = this.Hard("move.climb.exhaustion", new String[0]).comment("To switch on/off exhaustion while climbing").section(new String[0]);
        this._climbExhaustionStart = Properties.Positive("move.climb.exhaustion.start", new String[0]).defaults(60.0f, new String[0]).defaults(0.0f, SmartMovingConfig._pre_sm_1_4).comment("Maximum exhaustion to start climbing along ceilings (>= 0)");
        this._climbExhaustionStop = Properties.Positive("move.climb.exhaustion.stop", new String[0]).up(80.0f, this._climbExhaustionStart).defaults(100.0f, SmartMovingConfig._pre_sm_1_4).comment("Maximum exhaustion to climb (>= 0)");
        this._climbStrafeExhaustionGain = Properties.Positive("move.climb.strafe.exhaustion.gain", new String[0]).defaults(1.1f, new String[0]).defaults(0.75f, SmartMovingConfig._pre_sm_1_4).comment("Exhaustion added every tick while climbing horizontally (>= 0)");
        this._climbUpExhaustionGain = Properties.Positive("move.climb.up.exhaustion.gain", new String[0]).defaults(1.2f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_1_4).comment("Exhaustion added every tick while climbing up (>= 0)");
        this._climbDownExhaustionGain = Properties.Positive("move.climb.down.exhaustion.gain", new String[0]).defaults(1.05f, new String[0]).defaults(0.5f, SmartMovingConfig._pre_sm_1_4).comment("Exhaustion added every tick while climbing down (>= 0)");
        this._climbStrafeUpExhaustionGain = Properties.Positive("move.climb.strafe.up.exhaustion.gain", new String[0]).defaults(1.3f, new String[0]).comment("Exhaustion added every tick while climbing diagonally up (>= 0)");
        this._climbStrafeDownExhaustionGain = Properties.Positive("move.climb.strafe.down.exhaustion.gain", new String[0]).defaults(1.25f, new String[0]).comment("Exhaustion added every tick while climbing diagonally down (>= 0)");
        this._ceilingClimbing = Properties.Unmodified("move.climb.ceiling", new String[0]).comment("To switch on/off climbing along ceilings").book("Ceiling climbing", "Below you find all ceiling climbing options");
        this._ceilingClimbingSpeedFactor = Properties.PositiveFactor("move.climb.ceiling.speed.factor", new String[0]).defaults(0.2f, new String[0]).comment("Speed factor while climbing along ceilings (>= 0, relative to default movement speed)");
        this._ceilingClimbConfigurationString = Properties.Strings("move.climb.ceiling.configuration", new String[0]).defaults(new String[] { "tile.fenceIron", "tile.trapdoor/0/1/2/3", "tile.trapdoor_iron/0/1/2/3" }, new String[0]).defaults(new String[] { "tile.fenceIron", "tile.trapdoor/0/1/2/3" }, SmartMovingConfig._pre_sm_2_5).comment("To define which blocks are ceiling climbable (syntax: '<blockId/blockName>(/<metadata>)*' seperator: ',')");
        this._ceilingClimbConfigurationObject = this._ceilingClimbConfigurationString.toBlockConfig();
        this._ceilingClimbExhaustion = this.Hard("move.climb.ceiling.exhaustion", new String[0]).comment("To switch on/off exhaustion while climbing along ceilings").section(new String[0]);
        this._ceilingClimbExhaustionStart = Properties.Positive("move.climb.ceiling.exhaustion.start", new String[0]).defaults(40.0f, new String[0]).defaults(0.0f, SmartMovingConfig._pre_sm_1_4).comment("Maximum exhaustion to start climbing along ceilings (>= 0)");
        this._ceilingClimbExhaustionStop = Properties.Positive("move.climb.ceiling.exhaustion.stop", new String[0]).up(60.0f, this._ceilingClimbExhaustionStart).defaults(100.0f, SmartMovingConfig._pre_sm_1_4).comment("Maximum exhaustion to climbing along ceilings (>= \"move.climb.ceiling.exhaustion.start\")");
        this._ceilingClimbExhaustionGain = Properties.Positive("move.climb.ceiling.exhaustion.gain", new String[0]).defaults(1.3f, new String[0]).defaults(2.0f, SmartMovingConfig._pre_sm_1_4).comment("Exhaustion added every tick while climbing along ceilings (>= 0)");
        this._swim = Properties.Unmodified().key("move.swim", new String[0]).key("climbing.swim", SmartMovingConfig._all_old).comment("To switch on/off swimming").book("Swimming", "Below you find all swimming options");
        this._swimSpeedFactor = Properties.PositiveFactor("move.swim.speed.factor", new String[0]).comment("Speed factor while swimming (>= 0, relative to default movement speed)");
        this._swimDownOnSneak = Properties.Unmodified().key("move.swim.down.sneak", new String[0]).defaults(false, SmartMovingConfig._pre_sm_1_6).comment("To switch on/off diving down instead of swimming slow on sneaking while swimming");
        this._swimParticlePeriodFactor = Properties.PositiveFactor("move.swim.particle.period.factor", new String[0]).comment("Swim particle spawning period factor (>= 0)");
        this._dive = Properties.Unmodified().key("move.dive", new String[0]).key("climbing.dive", SmartMovingConfig._all_old).comment("To switch on/off diving").book("Diving", "Below you find all diving options");
        this._diveSpeedFactor = Properties.PositiveFactor("move.dive.speed.factor", new String[0]).comment("Speed factor while diving (>= 0, relative to default movement speed)");
        this._diveDownOnSneak = Properties.Unmodified().key("move.dive.down.sneak", new String[0]).defaults(false, SmartMovingConfig._pre_sm_1_6).comment("To switch on/off diving down instead of diving slow on sneaking while diving");
        this._lavaLikeWater = this.Creative("move.lava.water", new String[0]).comment("To switch on/off swimming and diving in lava").book("Lava", "Below you find all lava movement options");
        this._lavaSwimParticlePeriodFactor = Properties.PositiveFactor("move.lava.swim.particle.period.factor", new String[0]).defaults(4.0f, new String[0]).comment("Lava swim particle spawning period factor (>= 0)");
        this._run = Properties.Unmodified("move.run", new String[0]).comment("To switch on/off standard sprinting").book("Standard sprinting", "Below you find the options for standard vanilla Minecraft sprinting (sometimes referred as \"running\" here)");
        this._runFactor = Properties.PositiveFactor("move.run.factor", new String[0]).defaults(1.3f, new String[0]).min(1.1f).comment("Standard sprinting factor (>= 1.1)");
        this._runExhaustion = this.Hard("move.run.exhaustion", new String[0]).depends(this._run).comment("To switch on/off standard sprinting exhaustion");
        this._runExhaustionStart = Properties.Positive("move.exhaustion.run.start", new String[0]).defaults(75.0f, new String[0]).comment("Maximum exhaustion to start a standard sprint (>= 0)").section(new String[0]);
        this._runExhaustionStop = Properties.Positive("move.exhaustion.run.stop", new String[0]).up(100.0f, this._runExhaustionStart).comment("Maximum exhaustion to continue a standard sprint (>= \"move.exhaustion.run.start\")");
        this._runExhaustionGainFactor = Properties.Positive("move.exhaustion.run.gain.factor", new String[0]).defaults(1.5f, new String[0]).comment("Exhaustion gain factor while standard sprinting (>= 0)");
        this._sprint = Properties.Unmodified("move.sprint", new String[0]).comment("To switch on/off generic sprinting").book("Generic sprinting", "Below you find the options for Smart Moving's generic sprinting available for many different smart movings plus standard walking");
        this._sprintFactor = Properties.PositiveFactor("move.sprint.factor", new String[0]).defaults(1.5f, new String[0]).min(this._run.eitherOr(this._runFactor.plus(0.1f), 1.1f)).comment("Generic sprinting factor (>= 1.1 AND >= 'move.run.factor' + 0.1 if relevant)");
        this._sprintExhaustion = this.Medium("move.sprint.exhaustion", new String[0]).depends(this._sprint).comment("To switch on/off sprinting exhaustion");
        this._sprintExhaustionStart = Properties.Positive("move.exhaustion.sprint.start", new String[0]).defaults(50.0f, new String[0]).comment("Maximum exhaustion to start a sprint (>= 0)").section(new String[0]);
        this._sprintExhaustionStop = Properties.Positive("move.exhaustion.sprint.stop", new String[0]).up(100.0f, this._sprintExhaustionStart).comment("Maximum exhaustion to continue a sprint (>= \"move.exhaustion.sprint.start\")");
        this._sprintExhaustionGainFactor = Properties.IncreasingFactor("move.exhaustion.sprint.gain.factor", new String[0]).defaults(2.0f, new String[0]).comment("Exhaustion gain factor while sprinting (>= 0)");
        this._sneak = Properties.Unmodified("move.sneak", new String[0]).comment("To switch on/off standard sneaking").book("Generic sneaking", "Below you find the options for Smart Moving's generic sneaking available for many different smart movings. These options also apply to standard sneaking!");
        this._sneakFactor = Properties.DecreasingFactor("move.sneak.factor", new String[0]).defaults(0.3f, new String[0]).comment("Speed factor while sneaking (>= 0, <= 1, relative to default movement speed)");
        this._sneakNameTag = Properties.Modified("move.sneak.name", new String[0]).comment("Whether to display a name tag above other standard sneaking players");
        this._crawl = Properties.Unmodified("move.crawl", new String[0]).comment("To switch on/off crawling").book("Crawling", "Below you find all crawling options.");
        this._crawlFactor = Properties.DecreasingFactor("move.crawl.factor", new String[0]).defaults(0.15f, new String[0]).comment("Speed factor while crawling (>= 0, <= 1, relative to default movement speed)");
        this._crawlNameTag = Properties.Modified("move.crawl.name", new String[0]).comment("Whether to display a name tag above other crawling players");
        this._crawlOverEdge = Properties.Unmodified("move.crawl.edge", new String[0]).comment("Whether to allow crawling over edges");
        this._slide = Properties.Unmodified("move.slide", new String[0]).comment("To switch on/off sliding").book("Sliding", "Below you find all sliding options.");
        this._slideControlDegrees = Properties.PositiveFactor("move.slide.control.angle", new String[0]).defaults(1.0f, new String[0]).comment("Sliding control movement factor (>= 0, in degrees per tick)");
        this._slideSlipperinessFactor = Properties.PositiveFactor("move.slide.glide.factor", new String[0]).comment("Slipperiness factor while sliding (>= 0)");
        this._slidingSpeedStopFactor = Properties.PositiveFactor("move.slide.speed.stop.factor", new String[0]).comment("Sliding to crawling transition speed factor (>= 0)");
        this._slideParticlePeriodFactor = Properties.PositiveFactor("move.slide.particle.period.factor", new String[0]).defaults(0.5f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_1_6).comment("Sliding particle spawning period factor (>= 0)");
        this._fly = Properties.Unmodified("move.fly", new String[0]).comment("To switch on/off smart flying").book("Smart flying", "Below you find all options for Smart Moving's own flying mode.");
        this._flyingSpeedFactor = Properties.PositiveFactor("move.fly.speed.factor", new String[0]).comment("To manipulate smart flying speed (>= 0)");
        this._levitateSmall = Properties.Unmodified("move.levitate.small", new String[0]).comment("To switch on/off standard flying small size").book("Standard flying", "Below you find the options for standard vanilla Minecraft flying (sometimes referred as \"levitating\" here)");
        this._levitateAnimation = Properties.Unmodified("move.levitate.animation", new String[0]).key("move.fly.animation", new String[0]).comment("To switch on/off standard flying animation");
        this._fallingDistanceMinimum = Properties.Positive("move.fall.distance.minimum", new String[0]).defaults(3.0f, new String[0]).comment("Minimum fall distance for stopping ground based moves like crawling or sliding (>= 0)").book("Falling", "Below you find the options for Smart Moving's falling");
        this._fallAnimation = Properties.Unmodified("move.fall.animation", new String[0]).comment("To switch on/off smart falling animation");
        this._fallAnimationDistanceMinimum = Properties.Positive("move.fall.animation.distance.minimum", new String[0]).min(this._fallingDistanceMinimum).defaults(3.0f, SmartMovingConfig._pre_sm_1_6).comment("Minimum fall distance for the smart falling animation (>= 0, >= \"move.fall.animation.distance.minimum\"");
        this._jump = Properties.Unmodified("move.jump", new String[0]).comment("To switch on/off jumping").book("Jumping", "Below you find the options for all Smart Moving's different jump types. These options also apply to standard jumping!");
        this._jumpControlFactor = Properties.DecreasingFactor("move.jump.control.factor", new String[0]).defaults(1.0f, new String[0]).comment("Jumping control movement factor (>= 0, <= 1, relative to default air movement speed)");
        this._jumpHorizontalFactor = Properties.IncreasingFactor("move.jump.horizontal.factor", new String[0]).comment("Horizontal jumping factor relative to actual horizontal movement (>= 1)").section(new String[0]);
        this._jumpVerticalFactor = Properties.PositiveFactor("move.jump.vertical.factor", new String[0]).comment("Vertical jumping factor relative to default jump height (>= 0)");
        this._standJump = Properties.Unmodified("move.jump.stand", new String[0]).depends(this._jump).comment("To switch on/off jumping while standing (Relevant only if \"move.jump\" is true)").chapter(new String[0]);
        this._standJumpVerticalFactor = Properties.PositiveFactor("move.jump.stand.vertical.factor", new String[0]).comment("Vertical stand jumping factor relative to default jump height (>= 0)");
        this._sneakJump = Properties.Unmodified("move.jump.sneak", new String[0]).key("move.sneak.jump", SmartMovingConfig._pre_sm_1_7).depends(this._sneak, this._jump).comment("To switch on/off jumping while sneaking (Relevant only if nether \"move.sneak\" nor \"move.jump\" are false)").chapter(new String[0]);
        this._sneakJumpHorizontalFactor = Properties.IncreasingFactor("move.jump.sneak.horizontal.factor", new String[0]).key("move.sneak.jump.horizontal.factor", SmartMovingConfig._pre_sm_1_7).comment("Horizontal sneak jumping factor relative to actual horizontal movement (>= 1)");
        this._sneakJumpVerticalFactor = Properties.PositiveFactor("move.jump.sneak.vertical.factor", new String[0]).key("move.sneak.jump.vertical.factor", SmartMovingConfig._pre_sm_1_7).comment("Vertical sneak jumping factor relative to default jump height (>= 0)");
        this._walkJump = Properties.Unmodified("move.jump.walk", new String[0]).depends(this._jump).comment("To switch on/off jumping while walking (Relevant only if \"move.jump\" is true)").chapter(new String[0]);
        this._walkJumpHorizontalFactor = Properties.IncreasingFactor("move.jump.walk.horizontal.factor", new String[0]).comment("Horizontal walk jumping factor relative to actual horizontal movement (>= 1)");
        this._walkJumpVerticalFactor = Properties.PositiveFactor("move.jump.walk.vertical.factor", new String[0]).comment("Vertical walk jumping factor relative to default jump height (>= 0)");
        this._runJump = Properties.Unmodified("move.jump.run", new String[0]).key("move.run.jump", SmartMovingConfig._pre_sm_1_7).depends(this._run, this._jump).comment("To switch on/off jumping while running (Relevant only if nether \"move.run\" nor \"move.jump\" are false)").chapter(new String[0]);
        this._runJumpHorizontalFactor = Properties.IncreasingFactor("move.jump.run.horizontal.factor", new String[0]).key("move.run.jump.horizontal.factor", SmartMovingConfig._pre_sm_1_7).defaults(2.0f, new String[0]).comment("Horizontal run jumping factor relative to actual horizontal movement (>= 1)");
        this._runJumpVerticalFactor = Properties.PositiveFactor("move.jump.run.vertical.factor", new String[0]).key("move.run.jump.vertical.factor", SmartMovingConfig._pre_sm_1_7).comment("Vertical run jumping factor relative to default jump height (>= 0)");
        this._sprintJump = Properties.Unmodified("move.jump.sprint", new String[0]).key("move.sprint.jump", SmartMovingConfig._pre_sm_1_7).depends(this._sprint, this._jump).comment("To switch on/off jumping while sprinting (Relevant only if nether \"move.sprint\" nor \"move.jump\" are false)").chapter(new String[0]);
        this._sprintJumpHorizontalFactor = Properties.IncreasingFactor("move.jump.sprint.horizontal.factor", new String[0]).key("move.sprint.jump.horizontal.factor", SmartMovingConfig._pre_sm_1_7).defaults(2.0f, new String[0]).comment("Horizontal sprint jumping factor relative to actual horizontal movement (>= 1)");
        this._sprintJumpVerticalFactor = Properties.PositiveFactor("move.jump.sprint.vertical.factor", new String[0]).key("move.sprint.jump.vertical.factor", SmartMovingConfig._pre_sm_1_7).comment("Vertical sprint jumping factor relative to default jump height (>= 0)");
        this._jumpCharge = Properties.Unmodified("move.jump.charge", new String[0]).depends(this._jump).comment("Relevant only if \"move.jump\" is not false").chapter("Charged jumping", "Below you find all charged jump specific options except those for exhaustion.");
        this._jumpChargeMaximum = Properties.Positive("move.jump.charge.maximum", new String[0]).defaults(20.0f, new String[0]).comment("Maximum jump charge (counts up one per tick) (>= 0)");
        this._jumpChargeFactor = Properties.IncreasingFactor("move.jump.charge.factor", new String[0]).defaults(1.3f, new String[0]).comment("Jump speed factor when completely charged (>= 1)");
        this._jumpChargeCancelOnSneakRelease = Properties.Modified("move.jump.charge.sneak.release.cancel", new String[0]).comment("To switch between charged jump and charge cancel on sneak button release while jump charging");
        this._headJump = Properties.Unmodified("move.jump.head.charge", new String[0]).key("move.forward.jump.charge", SmartMovingConfig._pre_sm_1_8).depends(this._jump).comment("Relevant only if \"move.jump\" is not false").chapter("Head jumping", "Below you find all head jump and fall specific options except those for exhaustion.");
        this._headJumpControlFactor = Properties.DecreasingFactor("move.jump.head.control.factor", new String[0]).key("move.forward.jump.control.factor", SmartMovingConfig._pre_sm_1_8).defaults(0.2f, new String[0]).comment("Head jump control movement factor (>= 0, <= 1, relative to default air movement speed)");
        this._headJumpChargeMaximum = Properties.Positive("move.jump.head.charge.maximum", new String[0]).key("move.forward.jump.charge.maximum", SmartMovingConfig._pre_sm_1_8).defaults(10.0f, new String[0]).comment("Maximum head jump charge (counts up one per tick) (>= 0)");
        this._headFallDamageStartDistance = Properties.Positive("move.fall.head.damage.start.distance", new String[0]).key("move.forward.fall.damage.start.distance", SmartMovingConfig._pre_sm_1_8).values(2.0f, 1.0f, 3.0f).comment("Distance in blocks to fall head ahead before suffering fall damage (>= 1, <= 3)").section(new String[0]);
        this._headFallDamageFactor = Properties.IncreasingFactor("move.fall.head.damage.factor", new String[0]).key("move.forward.fall.damage.factor", SmartMovingConfig._pre_sm_1_8).defaults(2.0f, new String[0]).comment("Damage factor applied to the remaining distance when impacting head ahead (>= 1)");
        this._angleJumpSide = Properties.Unmodified("move.jump.angle.side", new String[0]).comment("To switch on/off side jumping").chapter("Side and Back jumping", "Below you find all side and back jump specific options except those for exhaustion.");
        this._angleJumpBack = Properties.Unmodified("move.jump.angle.back", new String[0]).comment("To switch on/off back jumping");
        this._angleJumpHorizontalFactor = Properties.PositiveFactor("move.jump.angle.horizontal.factor", new String[0]).defaults(0.3f, new String[0]).defaults(0.4f, "1.3").comment("Horizontal jump speed factor for side and back jumps (>= 0)");
        this._angleJumpVerticalFactor = Properties.PositiveFactor("move.jump.angle.vertical.factor", new String[0]).defaults(0.2f, new String[0]).comment("Vertical jump speed factor for side and back jumps (>= 0)");
        this._climbUpJump = Properties.Unmodified("move.jump.climb.up", new String[0]).comment("To switch on/off jumping up while climbing").chapter("Climb jumping", "Below you find all climb up jump specific options except those for exhaustion.");
        this._climbUpJumpVerticalFactor = Properties.DecreasingFactor("move.jump.climb.up.vertical.factor", new String[0]).comment("Vertical jump speed factor for jumping while climbing (>= 0, <= 1)");
        this._climbUpJumpHandsOnlyVerticalFactor = Properties.DecreasingFactor("move.jump.climb.up.hands.only.vertical.factor", new String[0]).defaults(0.8f, new String[0]).comment("Additional vertical jump speed factor for jumping while climbing with hands only (>= 0, <= 1)");
        this._climbBackUpJump = Properties.Unmodified("move.jump.climb.back.up", new String[0]).comment("To switch on/off jumping back while climbing").chapter("Climb back jumping", "Below you find all climb back jump specific options except those for exhaustion.");
        this._climbBackUpJumpVerticalFactor = Properties.DecreasingFactor("move.jump.climb.back.up.vertical.factor", new String[0]).defaults(0.2f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("Vertical jump speed factor for jumping back while climbing (>= 0, <= 1)");
        this._climbBackUpJumpHorizontalFactor = Properties.DecreasingFactor("move.jump.climb.back.up.horizontal.factor", new String[0]).defaults(0.3f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("Horizontal jump speed factor for jumping back while climbing (>= 0, <= 1)");
        this._climbBackUpJumpHandsOnlyVerticalFactor = Properties.DecreasingFactor("move.jump.climb.back.up.hands.only.vertical.factor", new String[0]).defaults(0.8f, new String[0]).comment("Additional vertical jump speed factor for jumping back while climbing with hands only (>= 0, <= 1)");
        this._climbBackUpJumpHandsOnlyHorizontalFactor = Properties.DecreasingFactor("move.jump.climb.back.up.hands.only.horizontal.factor", new String[0]).comment("Additional horizontal jump speed factor for jumping back while climbing with hands only (>= 0, <= 1)");
        this._climbBackHeadJump = Properties.Unmodified("move.jump.climb.back.head", new String[0]).comment("To switch on/off head jumping back while climbing").chapter("Climb back head jumping", "Below you find all climb back head jump specific options except those for exhaustion.");
        this._climbBackHeadJumpVerticalFactor = Properties.DecreasingFactor("move.jump.climb.back.head.vertical.factor", new String[0]).defaults(0.2f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("Additional vertical jump speed factor for head jumping back while climbing(>= 0, <= 1)");
        this._climbBackHeadJumpHorizontalFactor = Properties.DecreasingFactor("move.jump.climb.back.head.horizontal.factor", new String[0]).defaults(0.3f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("Additional horizontal jump speed factor for head jumping back while climbing(>= 0, <= 1)");
        this._climbBackHeadJumpHandsOnlyVerticalFactor = Properties.DecreasingFactor("move.jump.climb.back.head.hands.only.vertical.factor", new String[0]).defaults(0.8f, new String[0]).comment("Additional vertical jump speed factor for head jumping while climbing with hands only (>= 0, <= 1)");
        this._climbBackHeadJumpHandsOnlyHorizontalFactor = Properties.DecreasingFactor("move.jump.climb.back.head.hands.only.horizontal.factor", new String[0]).comment("Additional horizontal jump speed factor for head jumping while climbing with hands only (>= 0, <= 1)");
        this._wallUpJump = Properties.Unmodified("move.jump.wall", new String[0]).comment("To switch on/off wall jumping").chapter("Wall jumping", "Below you find all wall jump specific options except those for exhaustion.");
        this._wallUpJumpVerticalFactor = Properties.DecreasingFactor("move.jump.wall.vertical.factor", new String[0]).defaults(0.4f, new String[0]).comment("Vertical jump speed factor for wall jumping (>= 0, <= 1)");
        this._wallUpJumpHorizontalFactor = Properties.DecreasingFactor("move.jump.wall.horizontal.factor", new String[0]).defaults(0.15f, new String[0]).comment("Horizontal jump speed factor for wall jumping (>= 0, <= 1)");
        this._wallUpJumpFallMaximumDistance = Properties.Positive("move.jump.wall.fall.maximum.distance", new String[0]).defaults(2.0f, new String[0]).comment("Distance in blocks to fall to block all wall jumping attempts");
        this._wallUpJumpOrthogonalTolerance = Properties.Positive("move.jump.wall.orthogonal.tolerance", new String[0]).defaults(5.0f, new String[0]).comment("Tolerance angle in degree for wall jumping orthogonally (>= 0, <= 45)");
        this._wallHeadJump = Properties.Unmodified("move.jump.wall.head", new String[0]).comment("To switch on/off wall head jumping").chapter("Wall head jumping", "Below you find all wall head jump specific options except those for exhaustion.");
        this._wallHeadJumpVerticalFactor = Properties.DecreasingFactor("move.jump.wall.head.vertical.factor", new String[0]).defaults(0.3f, new String[0]).comment("Vertical jump speed factor for wall head jumping (>= 0, <= 1)");
        this._wallHeadJumpHorizontalFactor = Properties.DecreasingFactor("move.jump.wall.head.horizontal.factor", new String[0]).defaults(0.15f, new String[0]).comment("Horizontal jump speed factor for wall head jumping (>= 0, <= 1)");
        this._wallHeadJumpFallMaximumDistance = Properties.Positive("move.jump.wall.head.fall.maximum.distance", new String[0]).defaults(3.0f, new String[0]).min(this._wallUpJumpFallMaximumDistance).comment("Distance in blocks to fall to block all wall head jumping attempts (>= \"move.jump.wall.fall.maximum.distance\")");
        this._old_jumpExhaustion = this.Hard("move.jump.exhaustion", SmartMovingConfig._pre_sm_1_7);
        this._old_jumpExhaustionGain = Properties.Positive("move.jump.exhaustion.gain", SmartMovingConfig._pre_sm_1_7_post_1_0).key("move.exhaustion.jump.gain", "1.0").defaults(40.0f, new String[0]).defaults(10.0f, SmartMovingConfig._pre_sm_1_4);
        this._old_jumpExhaustionStop = Properties.Positive("move.jump.exhaustion.stop", SmartMovingConfig._pre_sm_1_7_post_1_0).key("move.exhaustion.jump.stop", "1.0").defaults(60.0f, new String[0]).defaults(100.0f, SmartMovingConfig._pre_sm_1_4);
        this._old_sneakJumpExhaustion = this.Hard("move.jump.sneak.exhaustion", SmartMovingConfig._pre_sm_1_7_post_1_4).key("move.sneak.jump.exhaustion", SmartMovingConfig._pre_sm_1_4);
        this._old_sneakJumpExhaustionGain = Properties.Positive("move.sneak.jump.exhaustion.gain", SmartMovingConfig._pre_sm_1_7).down(45.0f, this._old_jumpExhaustionGain).defaults(10.0f, SmartMovingConfig._pre_sm_1_4);
        this._old_sneakJumpExhaustionStop = Properties.Positive("move.sneak.jump.exhaustion.stop", SmartMovingConfig._pre_sm_1_7).up(55.0f, this._old_jumpExhaustionStop).defaults(100.0f, SmartMovingConfig._pre_sm_1_4);
        this._old_runJumpExhaustion = this.Medium("move.jump.run.exhaustion", SmartMovingConfig._pre_sm_1_7_post_1_4).key("move.run.jump.exhaustion", SmartMovingConfig._pre_sm_1_4);
        this._old_runJumpExhaustionGain = Properties.Positive("move.run.jump.exhaustion.gain", SmartMovingConfig._pre_sm_1_7).defaults(60.0f, new String[0]).defaults(20.0f, SmartMovingConfig._pre_sm_1_4);
        this._old_runJumpExhaustionStop = Properties.Positive("move.run.jump.exhaustion.stop", SmartMovingConfig._pre_sm_1_7).defaults(40.0f, new String[0]).defaults(100.0f, SmartMovingConfig._pre_sm_1_4);
        this._old_sprintJumpExhaustion = this.Medium("move.jump.sprint.exhaustion", SmartMovingConfig._pre_sm_1_7_post_1_4).key("move.sprint.jump.exhaustion", SmartMovingConfig._pre_sm_1_4);
        this._old_sprintJumpExhaustionGain = Properties.Positive("move.sprint.jump.exhaustion.gain", SmartMovingConfig._pre_sm_1_7_post_1_0).key("move.exhaustion.sprint.jump.gain", "1.0").up(65.0f, this._old_runJumpExhaustionGain).defaults(20.0f, SmartMovingConfig._pre_sm_1_4);
        this._old_sprintJumpExhaustionStop = Properties.Positive("move.sprint.jump.exhaustion.stop", SmartMovingConfig._pre_sm_1_7_post_1_0).key("move.exhaustion.sprint.jump.stop", "1.0").down(35.0f, this._old_runJumpExhaustionStop).defaults(100.0f, SmartMovingConfig._pre_sm_1_4);
        this._jumpExhaustion = Properties.Unmodified("move.jump.exhaustion", new String[0]).comment("To switch on/off jump exhaustion").chapter("Jump exhaustion", "Below you find the exhaustion options for the different jump types. At runtime all relevant options are combined together to form the specific exhaustion value");
        this._jumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.exhaustion.gain.factor", new String[0]).comment("To manipulate the exhaustion increase by a jump (>= 0)");
        this._jumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.exhaustion.stop.factor", new String[0]).comment("To manipulate maximum exhaustion to jump (>= 0)");
        this._upJumpExhaustion = Properties.Unmodified("move.jump.up.exhaustion", new String[0]).depends(this._jumpExhaustion).comment("To switch on/off up jump exhaustion").chapter(new String[0]);
        this._upJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.up.exhaustion.gain.factor", new String[0]).comment("To manipulate the exhaustion increase by a jump up (>= 0)");
        this._upJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.up.exhaustion.stop.factor", new String[0]).comment("To manipulate maximum exhaustion to jump up (>= 0)");
        this._climbJumpExhaustion = this.Hard("move.jump.climb.exhaustion", new String[0]).depends(this._jumpExhaustion).comment("To switch on/off climb jump exhaustion").chapter(new String[0]);
        this._climbJumpExhaustionGainFactor = Properties.PositiveFactor("move.climb.jump.exhaustion.gain.factor", new String[0]).comment("To manipulate the exhaustion increase by jumping while climbing (>= 0)");
        this._climbJumpExhaustionStopFactor = Properties.PositiveFactor("move.climb.jump.exhaustion.stop.factor", new String[0]).comment("To manipulate maximum exhaustion to jumping while climbing (>= 0)");
        this._climbJumpUpExhaustion = Properties.Unmodified("move.jump.climb.up.exhaustion", new String[0]).depends(this._climbJumpExhaustion).comment("To switch on/off climb up jump exhaustion").section(new String[0]);
        this._climbJumpUpExhaustionGainFactor = Properties.PositiveFactor("move.jump.climb.up.exhaustion.gain.factor", new String[0]).defaults(40.0f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("To manipulate the exhaustion increase by a jump up while climbing (>= 0)");
        this._climbJumpUpExhaustionStopFactor = Properties.PositiveFactor("move.jump.climb.up.exhaustion.stop.factor", new String[0]).defaults(60.0f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("To manipulate maximum exhaustion to jump up while climbing (>= 0)");
        this._climbJumpBackUpExhaustion = Properties.Unmodified("move.jump.climb.back.up.exhaustion", new String[0]).depends(this._climbJumpExhaustion).comment("To switch on/off climb back jump exhaustion").section(new String[0]);
        this._climbJumpBackUpExhaustionGainFactor = Properties.PositiveFactor("move.jump.climb.back.up.exhaustion.gain.factor", new String[0]).defaults(40.0f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("To manipulate the exhaustion increase by a jump back while climbing (>= 0)");
        this._climbJumpBackUpExhaustionStopFactor = Properties.PositiveFactor("move.jump.climb.back.up.exhaustion.stop.factor", new String[0]).defaults(60.0f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("To manipulate maximum exhaustion to jump back while climbing (>= 0)");
        this._climbJumpBackHeadExhaustion = Properties.Unmodified("move.jump.climb.back.head.exhaustion", new String[0]).depends(this._climbJumpExhaustion).comment("To switch on/off back climb head jump exhaustion").section(new String[0]);
        this._climbJumpBackHeadExhaustionGainFactor = Properties.PositiveFactor("move.jump.climb.back.head.exhaustion.gain.factor", new String[0]).defaults(20.0f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("To manipulate the exhaustion increase by a head jump back while climbing (>= 0)");
        this._climbJumpBackHeadExhaustionStopFactor = Properties.PositiveFactor("move.jump.climb.back.head.exhaustion.stop.factor", new String[0]).defaults(80.0f, new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_3_1).comment("To manipulate maximum exhaustion to head jump back while climbing (>= 0)");
        this._angleJumpExhaustion = Properties.Unmodified("move.jump.angle.exhaustion", new String[0]).depends(this._jumpExhaustion).comment("To switch on/off angle jump exhaustion").chapter(new String[0]);
        this._angleJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.angle.exhaustion.gain.factor", new String[0]).comment("To manipulate the exhaustion increase by a jump to the side or back (>= 0)");
        this._angleJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.angle.exhaustion.stop.factor", new String[0]).comment("To manipulate maximum exhaustion to jump to the side or back (>= 0)");
        this._wallJumpExhaustion = this.Medium("move.jump.wall.exhaustion", new String[0]).depends(this._jumpExhaustion).comment("To switch on/off wall jump exhaustion").chapter(new String[0]);
        this._wallJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.wall.exhaustion.gain.factor", new String[0]).comment("To manipulate the exhaustion increase by a wall jump (>= 0)");
        this._wallJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.wall.exhaustion.stop.factor", new String[0]).comment("To manipulate maximum exhaustion to wall jump (>= 0)");
        this._wallUpJumpExhaustion = Properties.Unmodified("move.jump.wall.up.exhaustion", new String[0]).depends(this._wallJumpExhaustion).comment("To switch on/off wall up jump exhaustion").section(new String[0]);
        this._wallUpJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.wall.up.exhaustion.gain.factor", new String[0]).defaults(40.0f, new String[0]).comment("To manipulate the exhaustion increase by a wall up jump (>= 0)");
        this._wallUpJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.wall.up.exhaustion.stop.factor", new String[0]).defaults(60.0f, new String[0]).comment("To manipulate maximum exhaustion to wall up jump (>= 0)");
        this._wallHeadJumpExhaustion = Properties.Unmodified("move.jump.wall.head.exhaustion", new String[0]).depends(this._wallJumpExhaustion).comment("To switch on/off wall head jump exhaustion").section(new String[0]);
        this._wallHeadJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.wall.head.exhaustion.gain.factor", new String[0]).defaults(20.0f, new String[0]).comment("To manipulate the exhaustion increase by a wall head jump (>= 0)");
        this._wallHeadJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.wall.head.exhaustion.stop.factor", new String[0]).defaults(80.0f, new String[0]).comment("To manipulate maximum exhaustion to wall head jump (>= 0)");
        this._standJumpExhaustion = this.Hard("move.jump.stand.exhaustion", new String[0]).depends(this._jumpExhaustion).source(this._old_jumpExhaustion, SmartMovingConfig._pre_sm_1_7).comment("To switch on/off stand jump exhaustion").chapter(new String[0]);
        this._standJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.stand.exhaustion.gain.factor", new String[0]).defaults(40.0f, new String[0]).source(this._old_jumpExhaustionGain, SmartMovingConfig._pre_sm_1_7).comment("To manipulate the exhaustion increase by a jump while standing (>= 0)");
        this._standJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.stand.exhaustion.stop.factor", new String[0]).defaults(60.0f, new String[0]).source(this._old_jumpExhaustionStop, SmartMovingConfig._pre_sm_1_7).comment("To manipulate maximum exhaustion to jump while standing (>= 0)");
        this._sneakJumpExhaustion = this.Hard("move.jump.sneak.exhaustion", new String[0]).depends(this._jumpExhaustion).source(this._old_sneakJumpExhaustion, SmartMovingConfig._pre_sm_1_7).comment("To switch on/off sneak jump exhaustion").section(new String[0]);
        this._sneakJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.sneak.exhaustion.gain.factor", new String[0]).up(40.0f, this._standJumpExhaustionGainFactor).source(this._old_sneakJumpExhaustionGain, SmartMovingConfig._pre_sm_1_7).comment("To manipulate the exhaustion increase by a jump while sneaking (>= \"move.jump.stand.exhaustion.gain.factor\")");
        this._sneakJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.sneak.exhaustion.stop.factor", new String[0]).down(60.0f, this._standJumpExhaustionStopFactor).source(this._old_sneakJumpExhaustionStop, SmartMovingConfig._pre_sm_1_7).comment("To manipulate maximum exhaustion to jump while sneaking (>= 0, <= \"move.jump.stand.exhaustion.stop.factor\")");
        this._walkJumpExhaustion = this.Hard("move.jump.walkexhaustion", new String[0]).depends(this._jumpExhaustion).source(this._old_jumpExhaustion, SmartMovingConfig._pre_sm_1_7).comment("To switch on/off walk jump exhaustion").section(new String[0]);
        this._walkJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.walk.exhaustion.gain.factor", new String[0]).up(45.0f, this._sneakJumpExhaustionGainFactor).source(this._old_jumpExhaustionGain, SmartMovingConfig._pre_sm_1_7).comment("To manipulate the exhaustion increase by a jump while walking (>= \"move.jump.sneak.exhaustion.gain.factor\")");
        this._walkJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.walk.exhaustion.stop.factor", new String[0]).down(55.0f, this._sneakJumpExhaustionStopFactor).source(this._old_jumpExhaustionStop, SmartMovingConfig._pre_sm_1_7).comment("To manipulate maximum exhaustion to jump while walking (>= 0, <= \"move.jump.sneak.exhaustion.stop.factor\")");
        this._runJumpExhaustion = this.Medium("move.jump.run.exhaustion", new String[0]).depends(this._jumpExhaustion).source(this._old_runJumpExhaustion, SmartMovingConfig._pre_sm_1_7).comment("To switch on/off run jump exhaustion").section(new String[0]);
        this._runJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.run.exhaustion.gain.factor", new String[0]).up(60.0f, this._walkJumpExhaustionGainFactor).source(this._old_runJumpExhaustionGain, SmartMovingConfig._pre_sm_1_7).comment("To manipulate the exhaustion increase by a jump while running (>= \"move.jump.walk.exhaustion.gain.factor\")");
        this._runJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.run.exhaustion.stop.factor", new String[0]).down(40.0f, this._walkJumpExhaustionStopFactor).source(this._old_runJumpExhaustionStop, SmartMovingConfig._pre_sm_1_7).comment("To manipulate maximum exhaustion to jump while running (>= 0, <= \"move.jump.walk.exhaustion.stop.factor\")");
        this._sprintJumpExhaustion = this.Medium("move.jump.sprint.exhaustion", new String[0]).depends(this._jumpExhaustion).source(this._old_sprintJumpExhaustion, SmartMovingConfig._pre_sm_1_7).comment("To switch on/off sprint jump exhaustion").section(new String[0]);
        this._sprintJumpExhaustionGainFactor = Properties.PositiveFactor("move.jump.sprint.exhaustion.gain.factor", new String[0]).up(65.0f, this._runJumpExhaustionGainFactor).source(this._old_sprintJumpExhaustionGain, SmartMovingConfig._pre_sm_1_7).comment("To manipulate the exhaustion increase by a jump while sprinting (>= \"move.jump.run.exhaustion.gain.factor\")");
        this._sprintJumpExhaustionStopFactor = Properties.PositiveFactor("move.jump.sprint.exhaustion.stop.factor", new String[0]).down(35.0f, this._runJumpExhaustionStopFactor).source(this._old_sprintJumpExhaustionStop, SmartMovingConfig._pre_sm_1_7).comment("To manipulate maximum exhaustion to jump while sprinting (>= 0, <= \"move.jump.run.exhaustion.stop.factor\")");
        this._jumpChargeExhaustion = this.Medium("move.jump.charge.exhaustion", new String[0]).depends(this._jumpExhaustion).defaults(true, SmartMovingConfig._pre_sm_1_11).comment("To switch on/off up additional jump charge exhaustion").section(new String[0]);
        this._jumpChargeExhaustionGainFactor = Properties.PositiveFactor("move.jump.charge.exhaustion.gain.factor", new String[0]).defaults(this.Value(30.0f), new String[0]).comment("To manipulate the additional exhaustion for the higher jump (>= 0, is multiplied with the actual charge factor)");
        this._jumpChargeExhaustionStopFactor = Properties.PositiveFactor("move.jump.charge.exhaustion.stop.factor", new String[0]).defaults(this.Value(30.0f), new String[0]).comment("To manipulate the subtractional maximum exhaustion to jump higher (>= 0, is multiplied with the actual charge factor)");
        this._jumpSlideExhaustion = this.Medium("move.jump.slide.exhaustion", new String[0]).depends(this._jumpExhaustion).defaults(true, SmartMovingConfig._pre_sm_1_11).comment("To switch on/off slide jump exhaustion").section(new String[0]);
        this._jumpSlideExhaustionGainFactor = Properties.PositiveFactor("move.jump.slide.exhaustion.gain.factor", new String[0]).defaults(this.Value(10.0f), new String[0]).comment("To manipulate the exhaustion increase by a slide jump (>= 0)");
        this._jumpSlideExhaustionStopFactor = Properties.PositiveFactor("move.jump.slide.exhaustion.stop.factor", new String[0]).defaults(this.Value(90.0f), new String[0]).comment("To manipulate maximum exhaustion to slide jump (>= 0)");
        this._baseExhautionGainFactor = Properties.PositiveFactor("move.exhaustion.gain.factor", new String[0]).comment("Exhaustion gain base factor, set to '0' to disable exhaustion (>= 0)").book("Exhaustion", "Below you find the options for the continuous exhaustion gain/loss factors.");
        this._baseExhautionLossFactor = Properties.PositiveFactor("move.exhaustion.loss.factor", new String[0]).defaults(this.Value(1.0f).e(1.2f).h(0.8f), new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_1_5).comment("Exhaustion loss base factor (>= 0)");
        this._sprintingExhautionLossFactor = Properties.PositiveFactor("move.exhaustion.sprint.loss.factor", new String[0]).defaults(0.0f, new String[0]).comment("Smart sprinting exhaustion loss factor (>= 0)").section(new String[0]);
        this._runningExhautionLossFactor = Properties.PositiveFactor("move.exhaustion.run.loss.factor", new String[0]).up(0.5f, this._sprintingExhautionLossFactor).comment("Standard sprinting exhaustion loss factor while  (>= 0, >= \"move.exhaustion.sprint.loss.factor\")");
        this._walkingExhautionLossFactor = Properties.PositiveFactor("move.exhaustion.walk.loss.factor", new String[0]).up(1.0f, this._runningExhautionLossFactor).comment("Walking exhaustion loss factor (>= 0, >= \"move.exhaustion.run.loss.factor\")");
        this._sneakingExhautionLossFactor = Properties.PositiveFactor("move.exhaustion.sneak.loss.factor", new String[0]).up(1.5f, this._walkingExhautionLossFactor).defaults(1.0f, "1.1").comment("Sneaking exhaustion loss factor (>= 0, >= \"move.exhaustion.walk.loss.factor\")");
        this._standingExhautionLossFactor = Properties.PositiveFactor("move.exhaustion.stand.loss.factor", new String[0]).up(2.0f, this._sneakingExhautionLossFactor.maximum(1.0f)).comment("Standing exhaustion loss factor (>= 1, >= \"move.exhaustion.sneak.loss.factor\")");
        this._fallExhautionLossFactor = Properties.PositiveFactor("move.exhaustion.fall.loss.factor", new String[0]).up(2.5f, this._standingExhautionLossFactor).comment("Falling exhaustion loss factor (>= \"move.exhaustion.stand.loss.factor\")");
        this._ceilClimbingExhaustionLossFactor = Properties.PositiveFactor("move.exhaustion.climb.ceiling.loss.factor", new String[0]).comment("Ceiling climbing exhaustion loss factor (>= 0)").section(new String[0]);
        this._climbingExhaustionLossFactor = Properties.PositiveFactor("move.exhaustion.climb.loss.factor", new String[0]).comment("Climbing exhaustion loss factor (>= 0)");
        this._crawlingExhaustionLossFactor = Properties.PositiveFactor("move.exhaustion.crawl.loss.factor", new String[0]).comment("Crawling exhaustion loss factor (>= 0)");
        this._dippingExhaustionLossFactor = Properties.PositiveFactor("move.exhaustion.dip.loss.factor", new String[0]).comment("Water walking exhaustion loss factor (>= 0)");
        this._swimmingExhaustionLossFactor = Properties.PositiveFactor("move.exhaustion.swim.loss.factor", new String[0]).comment("Swimming exhaustion loss factor (>= 0)");
        this._divingExhaustionLossFactor = Properties.PositiveFactor("move.exhaustion.dive.loss.factor", new String[0]).comment("Diving exhaustion loss factor (>= 0)");
        this._normalExhaustionLossFactor = Properties.PositiveFactor("move.exhaustion.normal.loss.factor", new String[0]).comment("Normal movement exhaustion loss factor (>= 0)");
        this._exhaustionLossHunger = Properties.Unmodified("move.exhaustion.hunger", new String[0]).comment("Whether exhaustion loss increases hunger").section(new String[0]);
        this._exhaustionLossHungerFactor = Properties.PositiveFactor("move.exhaustion.hunger.factor", new String[0]).defaults(this.Value(0.05f).e(0.02f).h(0.08f), new String[0]).defaults(0.05f, SmartMovingConfig._pre_sm_1_5).comment("How much hunger is generated for exhaustion loss (>= 0)");
        this._exhaustionLossFoodLevelMinimum = Properties.Positive("move.exhaustion.food.minimum", new String[0]).defaults(4.0f, new String[0]).comment("Until which food level exhaustion is continuously reduced");
        this._hungerGain = this.Medium("move.hunger.gain", new String[0]).comment("To switch on/off hunger generation").book("Hunger", "Below you find all hunger gain options.");
        this._baseHungerGainFactor = Properties.PositiveFactor("move.hunger.gain.factor", new String[0]).defaults(this.Value(1.0f).e(0.8f).h(1.2f), new String[0]).defaults(1.0f, SmartMovingConfig._pre_sm_1_5).comment("Hunger generation base factor (>= 0)");
        this._sprintingHungerGainFactor = Properties.PositiveFactor("move.hunger.sprint.gain.factor", new String[0]).key("move.hunger.gain.sprint.factor", SmartMovingConfig._pre_sm_1_3).comment("Smart sprinting hunger generation factor (>= 0)").section(new String[0]);
        this._runningHungerGainFactor = Properties.PositiveFactor("move.hunger.run.gain.factor", new String[0]).key("move.hunger.gain.run.factor", SmartMovingConfig._pre_sm_1_3).defaults(10.0f, new String[0]).comment("Standard sprinting hunger generation factor (>= 0)");
        this._walkingHungerGainFactor = Properties.PositiveFactor("move.hunger.walk.gain.factor", new String[0]).key("move.hunger.gain.walk.factor", SmartMovingConfig._pre_sm_1_3).comment("Standard speed movement hunger generation factor (>= 0)");
        this._sneakingHungerGainFactor = Properties.PositiveFactor("move.hunger.sneak.gain.factor", new String[0]).key("move.hunger.gain.sneak.factor", SmartMovingConfig._pre_sm_1_3).comment("Sneaking hunger generation factor (>= 0)");
        this._standingHungerGainFactor = Properties.PositiveFactor("move.hunger.stand.gain.factor", new String[0]).defaults(0.0f, new String[0]).comment("Sneaking hunger generation factor (>= 0)");
        this._climbingHungerGainFactor = Properties.PositiveFactor("move.hunger.climb.gain.factor", new String[0]).key("move.hunger.gain.climb.factor", SmartMovingConfig._pre_sm_1_3).comment("Climbing hunger generation factor (>= 0)").section(new String[0]);
        this._crawlingHungerGainFactor = Properties.PositiveFactor("move.hunger.crawl.gain.factor", new String[0]).key("move.hunger.gain.crawl.factor", SmartMovingConfig._pre_sm_1_3).comment("Crawling hunger generation factor (>= 0)");
        this._ceilClimbingHungerGainFactor = Properties.PositiveFactor("move.hunger.climb.gain.ceiling.factor", new String[0]).key("move.hunger.gain.climb.ceiling.factor", SmartMovingConfig._pre_sm_1_3).comment("Ceiling climbing hunger generation factor (>= 0)");
        this._swimmingHungerGainFactor = Properties.PositiveFactor("move.hunger.swim.gain.factor", new String[0]).key("move.hunger.gain.swim.factor", SmartMovingConfig._pre_sm_1_3).defaults(1.5f, new String[0]).comment("Swimming hunger generation factor (>= 0)");
        this._divingHungerGainFactor = Properties.PositiveFactor("move.hunger.dive.gain.factor", new String[0]).key("move.hunger.gain.dive.factor", SmartMovingConfig._pre_sm_1_3).defaults(1.5f, new String[0]).comment("Diving hunger generation factor (>= 0)");
        this._dippingHungerGainFactor = Properties.PositiveFactor("move.hunger.dip.gain.factor", new String[0]).key("move.hunger.gain.dip.factor", SmartMovingConfig._pre_sm_1_3).defaults(1.5f, new String[0]).comment("Water walking hunger generation factor (>= 0)");
        this._normalHungerGainFactor = Properties.PositiveFactor("move.hunger.normal.gain.factor", new String[0]).key("move.hunger.gain.ground.factor", SmartMovingConfig._pre_sm_1_3).comment("Normal movement hunger generation factor (>= 0)");
        this._alwaysHungerGain = Properties.Positive("move.hunger.always.gain", new String[0]).defaults(this.Value(0.0f).h(0.005f), new String[0]).defaults(0.0f, SmartMovingConfig._pre_sm_1_5).comment("Basic hunger per tick (>= 0)").section(new String[0]);
        this._usageSpeedFactor = Properties.DecreasingFactor("move.usage.speed.factor", new String[0]).defaults(0.2f, new String[0]).comment("Speed factor while using an item, if not defined otherwise (>= 0 AND <= 1)").book("Item Usage", "Below you find the options to manipulate the speed factors additionally applied while using an item.");
        this._usageSwordSpeedFactor = Properties.DecreasingFactor("move.usage.sword.speed.factor", new String[0]).defaults(this._usageSpeedFactor, new String[0]).comment("Speed factor while blocking with a sword (>= 0 AND <= 1, defaults to \"move.usage.speed.factor\" when not present)");
        this._usageBowSpeedFactor = Properties.DecreasingFactor("move.usage.bow.speed.factor", new String[0]).defaults(this._usageSpeedFactor, new String[0]).comment("Speed factor while pulling back a bow (>= 0 AND <= 1, defaults to \"move.usage.speed.factor\" when not present)");
        this._usageFoodSpeedFactor = Properties.DecreasingFactor("move.usage.food.speed.factor", new String[0]).defaults(this._usageSpeedFactor, new String[0]).comment("Speed factor while eating food (>= 0 AND <= 1, defaults to \"move.usage.speed.factor\" when not present)");
        this._sprintDuringItemUsage = Properties.Modified("move.usage.sprint", new String[0]).comment("To switch on/off generic sprinting while using an item").section(new String[0]);
        this._replaceRopeClimbing = Properties.Unmodified("move.mod.rope.replace.climb.rope", new String[0]).comment("Whether to replace the rope climbing of mod pack 'Ropes+'").book("Mod compatibility", "Below you find the options to manipulate how Smart Moving should interact with other mods");
        this._survivalConfigKeys = Properties.Strings("move.config.survival.keys", new String[0]).singular().defaults(new String[] { "e", "m", "h" }, new String[0]).comment("A list of survival option configuration keys, entries seperated by ','").book("Configuration Management", "Below you find the options to define multiple configuration option sets");
        this._survivalDefaultConfigKey = Properties.String("move.config.survival.keys.default", new String[0]).singular().defaults("m", new String[0]).comment("The option configuration to start the next minecraft survival game with (can also be modified via in-game option configuration toggling)");
        this._creativeConfigKeys = Properties.Strings("move.config.creative.keys", new String[0]).singular().defaults(new String[] { "c" }, new String[0]).defaults(new String[0], SmartMovingConfig._pre_sm_2_3).comment("A list of creative option configuration keys, entries seperated by ','").section(new String[0]);
        this._creativeDefaultConfigKey = Properties.String("move.config.creative.keys.default", new String[0]).singular().defaults("c", new String[0]).defaults("", SmartMovingConfig._pre_sm_2_3).comment("The option configuration to start the next minecraft creative game with (can also be modified via in-game option configuration toggling)");
        this._adventureConfigKeys = Properties.Strings("move.config.adventure.keys", new String[0]).singular().defaults(new String[] { "e", "m", "h" }, new String[0]).comment("A list of adventure option configuration keys, entries seperated by ','").section(new String[0]);
        this._adventureDefaultConfigKey = Properties.String("move.config.adventure.keys.default", new String[0]).singular().defaults("m", new String[0]).comment("The option configuration to start the next minecraft adventure game with (can also be modified via in-game option configuration toggling)");
        this._configKeyName = Properties.String("move.config.key.name", new String[0]).defaults(this.Value((String)null).e("Easy").m("Medium").h("Hard"), new String[0]).comment("The display names of the option configuration keys").section(new String[0]);
        this._serverConfig = Properties.Modified("move.server.config", new String[0]).singular().comment("Whether the players on this server should be forced to use the configurations on this server").book("Server Management", "Below you find the options to manage your server");
        this._survivalDefaultConfigUserKeys = Properties.StringMap("move.server.config.survival.user.keys", new String[0]).singular().comment("The option configuration to start the player's next minecraft survival game with (can also be modified via in-game option configuration toggling)").section(new String[0]);
        this._creativeDefaultConfigUserKeys = Properties.StringMap("move.server.config.creative.user.keys", new String[0]).singular().comment("The option configuration to start the player's next minecraft creative game with (can also be modified via in-game option configuration toggling)");
        this._adventureDefaultConfigUserKeys = Properties.StringMap("move.server.config.adventure.user.keys", new String[0]).singular().comment("The option configuration to start the player's next minecraft adventure game with (can also be modified via in-game option configuration toggling)");
        this._speedUsersExponents = Properties.IntegerMap("move.server.speed.user.exponents", new String[0]).singular().comment("The user specific exponents for in-game speed manipulation");
        this._globalConfig = Properties.Modified("move.global.config", new String[0]).key("move.config.overwrite", SmartMovingConfig._pre_sm_3_0).key("move.config.send", SmartMovingConfig._pre_sm_2_2).singular().depends(this._serverConfig).comment("Whether all players on this server should be forced to use the same global configuration (Relevant only if \"move.server.config\" is true)").section(new String[0]);
        this._usersWithChangeConfigRights = Properties.Strings("move.global.config.right.user.names", new String[0]).singular().comment("The names of the users that have the right to change the global configuraton in-game, entries seperated by ','").section(new String[0]);
        this._usersWithChangeSpeedRights = Properties.Strings("move.global.speed.right.user.names", new String[0]).singular().comment("The names of the users that have the right to change the global speed in-game, entries seperated by ','");
    }
    
    public void changeSpeed(final int difference) {
        this._speedUserExponent.setValue(this._speedUserExponent.value + difference);
    }
    
    public boolean isUserSpeedEnabled() {
        return this.enabled && this._speedUser.value;
    }
    
    public boolean isUserSpeedAlwaysDefault() {
        return !this._speedUser.value || this._speedUserFactor.value == 1.0f;
    }
    
    public float getUserSpeedFactor() {
        if (this.isUserSpeedAlwaysDefault() || this._speedUserExponent.value == 0) {
            return 1.0f;
        }
        return (float)Math.pow(1.0f + this._speedUserFactor.value, this._speedUserExponent.value);
    }
    
    protected void loadFromProperties(final Properties properties) {
        try {
            this.load(properties);
        }
        catch (final Exception e) {
            throw new RuntimeException("Could not load Smart Moving properties from properties", e);
        }
    }
    
    protected void writeToProperties(final Properties properties, final List<Property> except) {
        try {
            this.write(properties);
            if (except != null) {
                for (int i = 0; i < except.size(); ++i) {
                    final String key = except.get(i).getCurrentKey();
                    if (key != null) {
                        properties.remove(key);
                    }
                }
            }
        }
        catch (final Exception e) {
            throw new RuntimeException("Could not write Smart Moving properties to properties", e);
        }
    }
    
    protected void loadFromOptionsFile(final File optionsPath) {
        final Properties slcs_properties = new Properties("0.1", new File(optionsPath, "smart_ladder_climbing_speed_options.txt"));
        final Properties sc_properties = new Properties("0.2", new File(optionsPath, "smart_climbing_options.txt"));
        final Properties sm_cs_properties = new Properties(new File(optionsPath, "smart_moving_server_options.txt"));
        final Properties properties = new Properties(new File(optionsPath, "smart_moving_options.txt"));
        try {
            this.load(properties, sm_cs_properties, sc_properties, slcs_properties);
        }
        catch (final Exception e) {
            throw new RuntimeException("Could not load Smart Moving properties from file", e);
        }
    }
    
    protected void saveToOptionsFile(final File optionsPath) {
        try {
            this.save(new File(optionsPath, "smart_moving_options.txt"), "3.2", true, true);
        }
        catch (final Exception e) {
            throw new RuntimeException("Could not save Smart Moving properties to file", e);
        }
    }
    
    @Override
    protected void printHeader(final PrintWriter printer) {
        printer.println("#######################################################################");
        printer.println("#");
        printer.println("# Smart Moving mod configuration file");
        printer.println("# -----------------------------------");
        printer.println("#");
        printer.println("# Modify the values behind the keys in this file to configure the");
        printer.println("# Smart Moving behavior as you like it.");
        printer.println("#");
        printer.println("# * All options you leave at their default value will be automatically");
        printer.println("#   updated when the default value is updated with a new version of");
        printer.println("#   Smart Moving options.");
        printer.println("#");
        printer.println("# * All options you modify will stay the same over the upgrade cycles");
        printer.println("#   as long as they still fall in the allowed range.");
        printer.println("#");
        printer.println("# * The character '!' will be used after the value's text's end to");
        printer.println("#   mark a value that has originally been modified but became the");
        printer.println("#   default value at some point in the update process to avoid it being");
        printer.println("#   updated too with the next default value change.");
        printer.println("#");
        printer.println("# * The '!' mark can also be used to create a 'modified' and so not");
        printer.println("#   automatically updated value identical to the current default value");
        printer.println("#");
        printer.println("# Additionally you can create multiple option configurations and define");
        printer.println("# separate option values for each configuration.");
        printer.println("#");
        printer.println("# * key-value separator is ':'");
        printer.println("# * key-value-pair separator is ';'");
        printer.println("# * a value without key will become the default value");
        printer.println("#");
        printer.println("#######################################################################");
        printer.println();
    }
    
    @Override
    protected void printVersion(final PrintWriter printer, final String version, final boolean comments) {
        if (comments) {
            printer.println("# The current version of this Smart Moving options file");
        }
        printer.print("move.options.version");
        printer.print(":");
        printer.print(version);
        printer.println();
    }
    
    private Property<Boolean> Creative(final String key, final String... versions) {
        return Properties.Modified(key, versions).defaults(this.Value(false).c(true), new String[0]);
    }
    
    private Property<Boolean> Hard(final String key, final String... versions) {
        return Properties.Modified(key, versions).defaults(this.Value(false).h(true), new String[0]).defaults(false, SmartMovingConfig._pre_sm_1_5);
    }
    
    private Property<Boolean> Medium(final String key, final String... versions) {
        return Properties.Unmodified(key, versions).defaults(this.Value(true).e(false), new String[0]).defaults(true, SmartMovingConfig._pre_sm_1_5);
    }
    
    public Object getSpeedPercent() {
        float factor = this.getUserSpeedFactor() * 100.0f;
        int fraction = 0;
        while (factor < 100.0f) {
            ++fraction;
            factor *= 10.0f;
        }
        final int significant = Math.round(factor);
        BigDecimal decimal = new BigDecimal(significant);
        while (fraction-- > 0) {
            decimal = decimal.divide(SmartMovingConfig.ten);
        }
        return SmartMovingConfig.formatter.format(decimal);
    }
    
    static {
        _all_sm = new String[] { "3.2", "3.1", "3.0", "2.6", "2.5", "2.4", "2.3", "2.2", "2.1", "2.0", "1.11", "1.10", "1.9", "1.8", "1.7", "1.6", "1.5", "1.4", "1.3", "1.2", "1.1", "1.0" };
        _all_old = new String[] { "0.2", "0.1" };
        _pre_sm_1_3 = new String[] { "1.2", "1.1", "1.0" };
        _pre_sm_1_4 = Properties.concat("1.3", SmartMovingConfig._pre_sm_1_3);
        _pre_sm_1_5 = Properties.concat("1.4", SmartMovingConfig._pre_sm_1_4);
        _pre_sm_1_6 = Properties.concat("1.5", SmartMovingConfig._pre_sm_1_5);
        _pre_sm_1_7 = Properties.concat("1.6", SmartMovingConfig._pre_sm_1_6);
        _pre_sm_1_8 = Properties.concat("1.7", SmartMovingConfig._pre_sm_1_7);
        _pre_sm_1_9 = Properties.concat("1.8", SmartMovingConfig._pre_sm_1_8);
        _pre_sm_1_10 = Properties.concat("1.9", SmartMovingConfig._pre_sm_1_9);
        _pre_sm_1_11 = Properties.concat("1.10", SmartMovingConfig._pre_sm_1_10);
        _pre_sm_2_0 = Properties.concat("1.11", SmartMovingConfig._pre_sm_1_11);
        _pre_sm_2_1 = Properties.concat("2.0", SmartMovingConfig._pre_sm_2_0);
        _pre_sm_2_2 = Properties.concat("2.1", SmartMovingConfig._pre_sm_2_1);
        _pre_sm_2_3 = Properties.concat("2.2", SmartMovingConfig._pre_sm_2_2);
        _pre_sm_2_4 = Properties.concat("2.3", SmartMovingConfig._pre_sm_2_3);
        _pre_sm_2_5 = Properties.concat("2.4", SmartMovingConfig._pre_sm_2_4);
        _pre_sm_2_6 = Properties.concat("2.5", SmartMovingConfig._pre_sm_2_5);
        _pre_sm_3_0 = Properties.concat("2.6", SmartMovingConfig._pre_sm_2_6);
        _pre_sm_3_1 = Properties.concat("3.0", SmartMovingConfig._pre_sm_3_0);
        _pre_sm_3_2 = Properties.concat("3.1", SmartMovingConfig._pre_sm_3_1);
        _pre_sm_1_7_post_1_4 = new String[] { "1.6", "1.5" };
        _pre_sm_1_7_post_1_0 = new String[] { "1.6", "1.5", "1.4", "1.3", "1.2", "1.1" };
        _all = Properties.concat(SmartMovingConfig._all_sm, SmartMovingConfig._all_old);
        formatter = new DecimalFormat("0.############################################################");
        ten = new BigDecimal(10);
        defaultSpeedPercent = "100";
    }
}
