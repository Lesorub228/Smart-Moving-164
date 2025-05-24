// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import net.smart.utilities.Install;
import net.smart.utilities.Name;
import net.smart.utilities.Reflect;
import net.smart.moving.config.SmartMovingOptions;
import java.lang.reflect.Method;
import java.util.HashSet;

public class Orientation extends SmartMovingContext
{
    public static final Orientation ZZ;
    public static final Orientation PZ;
    public static final Orientation ZP;
    public static final Orientation NZ;
    public static final Orientation ZN;
    public static final Orientation PP;
    public static final Orientation NN;
    public static final Orientation PN;
    public static final Orientation NP;
    public static final int DefaultMeta = -1;
    public static final int VineFrontMeta = 0;
    public static final int VineSideMeta = 1;
    private static final int top = 2;
    private static final int middle = 1;
    private static final int base = 0;
    private static final int sub = -1;
    private static final int subSub = -2;
    private static final int NoGrab = 0;
    private static final int HalfGrab = 1;
    private static final int AroundGrab = 2;
    public static final HashSet<Orientation> Orthogonals;
    protected int _i;
    protected int _k;
    private boolean _isDiagonal;
    private float _directionAngle;
    private float _mimimumClimbingAngle;
    private float _maximumClimbingAngle;
    private static HashSet<Orientation> _getClimbingOrientationsHashSet;
    private static final float _handClimbingHoldGap;
    private static ClimbGap _climbGapTemp;
    private static ClimbGap _climbGapOuterTemp;
    private static abw world;
    private static double base_jhd;
    private static double jh_offset;
    private static int all_j;
    private static int all_offset;
    private static int base_i;
    private static int base_k;
    private static double base_id;
    private static double base_kd;
    private static int remote_i;
    private static int remote_k;
    private static boolean crawl;
    private static int local_halfOffset;
    private static int local_half;
    private static int local_offset;
    private static boolean grabRemote;
    private static int grabType;
    private static int grabBlockId;
    private static int grabMeta;
    private static final Method _isLadder;
    private static final Method _canConnectFenceTo;
    private static final aqz[] _knownFanceGateBlocks;
    private static final aqz[] _knownFenceBlocks;
    private static final aqz[] _knownWallBlocks;
    private static final aqz[] _knownHalfBlocks;
    private static final aqz[] _knownCompactStairBlocks;
    private static final aqz[] _knownTrapDoorBlocks;
    private static final aqz[] _knownThinWallBlocks;
    private static final Class<?>[] _ladderKitLadderTypes;
    
    private Orientation(final int i, final int k) {
        this._i = i;
        this._k = k;
        this._isDiagonal = (this._i != 0 && this._k != 0);
        this.setClimbingAngles();
    }
    
    public Orientation rotate(final int angle) {
        switch (angle) {
            case 45: {
                if (this == Orientation.PZ) {
                    return Orientation.PP;
                }
                if (this == Orientation.PP) {
                    return Orientation.ZP;
                }
                if (this == Orientation.ZP) {
                    return Orientation.NP;
                }
                if (this == Orientation.NP) {
                    return Orientation.NZ;
                }
                if (this == Orientation.NZ) {
                    return Orientation.NN;
                }
                if (this == Orientation.NN) {
                    return Orientation.ZN;
                }
                if (this == Orientation.ZN) {
                    return Orientation.PN;
                }
                if (this == Orientation.PN) {
                    return Orientation.PZ;
                }
                return null;
            }
            case -45: {
                if (this == Orientation.PZ) {
                    return Orientation.PN;
                }
                if (this == Orientation.PN) {
                    return Orientation.ZN;
                }
                if (this == Orientation.ZN) {
                    return Orientation.NN;
                }
                if (this == Orientation.NN) {
                    return Orientation.NZ;
                }
                if (this == Orientation.NZ) {
                    return Orientation.NP;
                }
                if (this == Orientation.NP) {
                    return Orientation.ZP;
                }
                if (this == Orientation.ZP) {
                    return Orientation.PP;
                }
                if (this == Orientation.PP) {
                    return Orientation.PZ;
                }
                return null;
            }
            case 90: {
                return this.rotate(45).rotate(45);
            }
            case -90: {
                return this.rotate(-45).rotate(-45);
            }
            case 135: {
                return this.rotate(180).rotate(-45);
            }
            case -135: {
                return this.rotate(-180).rotate(45);
            }
            case -180:
            case 180: {
                if (this == Orientation.PZ) {
                    return Orientation.NZ;
                }
                if (this == Orientation.PN) {
                    return Orientation.NP;
                }
                if (this == Orientation.ZN) {
                    return Orientation.ZP;
                }
                if (this == Orientation.NN) {
                    return Orientation.PP;
                }
                if (this == Orientation.NZ) {
                    return Orientation.PZ;
                }
                if (this == Orientation.NP) {
                    return Orientation.PN;
                }
                if (this == Orientation.ZP) {
                    return Orientation.ZN;
                }
                if (this == Orientation.PP) {
                    return Orientation.NN;
                }
                break;
            }
        }
        throw new RuntimeException("angle not supported");
    }
    
    public static Orientation getOrientation(final uf p, final float tolerance, final boolean orthogonals, final boolean diagonals) {
        float rotation = p.A % 360.0f;
        if (rotation < 0.0f) {
            rotation += 360.0f;
        }
        float minimumRotation = rotation - tolerance;
        if (minimumRotation < 0.0f) {
            minimumRotation += 360.0f;
        }
        float maximumRotation = rotation + tolerance;
        if (maximumRotation >= 360.0f) {
            maximumRotation -= 360.0f;
        }
        if (orthogonals) {
            if (Orientation.NZ.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.NZ;
            }
            if (Orientation.PZ.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.PZ;
            }
            if (Orientation.ZN.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.ZN;
            }
            if (Orientation.ZP.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.ZP;
            }
        }
        if (diagonals) {
            if (Orientation.NP.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.NP;
            }
            if (Orientation.PN.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.PN;
            }
            if (Orientation.NN.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.NN;
            }
            if (Orientation.PP.isWithinAngle(minimumRotation, maximumRotation)) {
                return Orientation.PP;
            }
        }
        return null;
    }
    
    public double getHorizontalBorderGap(final nn entity) {
        return this.getHorizontalBorderGap(entity.u, entity.w);
    }
    
    private double getHorizontalBorderGap() {
        return this.getHorizontalBorderGap(Orientation.base_id, Orientation.base_kd);
    }
    
    private double getHorizontalBorderGap(final double i, final double k) {
        if (this == Orientation.NZ) {
            return i % 1.0;
        }
        if (this == Orientation.PZ) {
            return 1.0 - i % 1.0;
        }
        if (this == Orientation.ZN) {
            return k % 1.0;
        }
        if (this == Orientation.ZP) {
            return 1.0 - k % 1.0;
        }
        return 0.0;
    }
    
    public boolean isTunnelAhead(final abw world, final int i, final int j, final int k) {
        final int remoteId = world.a(i + this._i, j + 1, k + this._k);
        if (isFullEmpty(remoteId)) {
            final akc aboveMaterial = world.g(i + this._i, j + 2, k + this._k);
            if (aboveMaterial != null && isSolid(aboveMaterial)) {
                return true;
            }
        }
        return false;
    }
    
    public static HashSet<Orientation> getClimbingOrientations(final uf p, final boolean orthogonals, final boolean diagonals) {
        float rotation = p.A % 360.0f;
        if (rotation < 0.0f) {
            rotation += 360.0f;
        }
        if (Orientation._getClimbingOrientationsHashSet == null) {
            Orientation._getClimbingOrientationsHashSet = new HashSet<Orientation>();
        }
        else {
            Orientation._getClimbingOrientationsHashSet.clear();
        }
        if (orthogonals) {
            Orientation.NZ.addTo(rotation);
            Orientation.PZ.addTo(rotation);
            Orientation.ZN.addTo(rotation);
            Orientation.ZP.addTo(rotation);
        }
        if (diagonals) {
            Orientation.NP.addTo(rotation);
            Orientation.PN.addTo(rotation);
            Orientation.NN.addTo(rotation);
            Orientation.PP.addTo(rotation);
        }
        return Orientation._getClimbingOrientationsHashSet;
    }
    
    private void addTo(final float rotation) {
        if (this.isRotationForClimbing(rotation)) {
            Orientation._getClimbingOrientationsHashSet.add(this);
        }
    }
    
    public boolean isFeetLadderSubstitute(final abw world, final int bi, final int j, final int bk) {
        final int i = bi + this._i;
        final int k = bk + this._k;
        return this.isLadderSubstitute(world, i, j, k, 1) > 0 || this.isLadderSubstitute(world, i, j, k, 0) > 0;
    }
    
    public boolean isHandsLadderSubstitute(final abw world, final int bi, final int j, final int bk) {
        final int i = bi + this._i;
        final int k = bk + this._k;
        return this.isLadderSubstitute(world, i, j, k, 1) > 0 || this.isLadderSubstitute(world, i, j, k, 0) > 0 || this.isLadderSubstitute(world, i, j, k, -1) > 0;
    }
    
    private int isLadderSubstitute(final abw worldObj, final int i, final int j, final int k, final int halfOffset) {
        Orientation.world = worldObj;
        Orientation.remote_i = i;
        Orientation.all_j = j;
        Orientation.remote_k = k;
        Orientation.all_offset = 0;
        return this.isLadderSubstitute(halfOffset, null);
    }
    
    public void seekClimbGap(final float rotation, final abw world, final int i, final double id, final double jhd, final int k, final double kd, final boolean isClimbCrawling, final boolean isCrawlClimbing, final boolean isCrawling, final HandsClimbing[] inout_handsClimbing, final FeetClimbing[] inout_feetClimbing, final ClimbGap out_handsClimbGap, final ClimbGap out_feetClimbGap) {
        if (this.isRotationForClimbing(rotation)) {
            this.initialize(world, i, id, jhd, k, kd);
            inout_handsClimbing[0] = inout_handsClimbing[0].max(this.handsClimbing(isClimbCrawling, isCrawlClimbing, isCrawling, Orientation._climbGapOuterTemp), out_handsClimbGap, Orientation._climbGapOuterTemp);
            inout_feetClimbing[0] = inout_feetClimbing[0].max(this.feetClimbing(isClimbCrawling, isCrawlClimbing, isCrawling, Orientation._climbGapOuterTemp), out_feetClimbGap, Orientation._climbGapOuterTemp);
        }
    }
    
    private HandsClimbing handsClimbing(final boolean isClimbCrawling, final boolean isCrawlClimbing, final boolean isCrawling, final ClimbGap out_climbGap) {
        out_climbGap.reset();
        Orientation._climbGapTemp.reset();
        initializeOffset(3.0, isClimbCrawling, isCrawlClimbing, isCrawling);
        HandsClimbing result = HandsClimbing.None;
        int gap;
        if ((gap = this.isLadderSubstitute(1, Orientation._climbGapTemp)) > 0) {
            if (Orientation.jh_offset > 1.0 - Orientation._handClimbingHoldGap) {
                result = result.max(HandsClimbing.Up, out_climbGap, Orientation._climbGapTemp);
            }
            else {
                result = result.max(HandsClimbing.None, out_climbGap, Orientation._climbGapTemp);
            }
        }
        if ((gap = this.isLadderSubstitute(0, Orientation._climbGapTemp)) > 0) {
            if (Orientation.jh_offset < Orientation._handClimbingHoldGap) {
                result = result.max(HandsClimbing.BottomHold, out_climbGap, Orientation._climbGapTemp);
            }
            else {
                result = result.max(HandsClimbing.Up, out_climbGap, Orientation._climbGapTemp);
            }
        }
        Orientation._climbGapTemp.SkipGaps = (isClimbCrawling || isCrawlClimbing);
        if ((gap = this.isLadderSubstitute(-1, Orientation._climbGapTemp)) > 0 && (!isCrawling || gap <= 1)) {
            if (!isClimbCrawling && gap > 2) {
                result = result.max(HandsClimbing.FastUp, out_climbGap, Orientation._climbGapTemp);
            }
            else if (isClimbCrawling && gap > 1) {
                result = result.max(HandsClimbing.FastUp, out_climbGap, Orientation._climbGapTemp);
            }
            else if (Orientation.jh_offset < Orientation._handClimbingHoldGap) {
                if (Orientation.grabType == 2) {
                    result = result.max(HandsClimbing.Up, out_climbGap, Orientation._climbGapTemp);
                }
                else {
                    result = result.max(HandsClimbing.TopHold, out_climbGap, Orientation._climbGapTemp);
                }
            }
            else if (Orientation.grabType == 2) {
                result = result.max(HandsClimbing.TopHold, out_climbGap, Orientation._climbGapTemp);
            }
            else {
                result = result.max(HandsClimbing.Sink, out_climbGap, Orientation._climbGapTemp);
            }
        }
        if ((gap = this.isLadderSubstitute(-2, Orientation._climbGapTemp)) > 0 && !isCrawling && ((gap > 2 && !isCrawlClimbing) || Orientation.grabType == 2 || (gap > 1 && isClimbCrawling))) {
            if (Orientation.jh_offset < Orientation._handClimbingHoldGap && !isClimbCrawling) {
                result = result.max(HandsClimbing.TopHold, out_climbGap, Orientation._climbGapTemp);
            }
            else if (isClimbCrawling) {
                result = result.max(HandsClimbing.FastUp, out_climbGap, Orientation._climbGapTemp);
            }
            else {
                result = result.max(HandsClimbing.Sink, out_climbGap, Orientation._climbGapTemp);
            }
        }
        return result;
    }
    
    private FeetClimbing feetClimbing(final boolean isClimbCrawling, final boolean isCrawlClimbing, final boolean isCrawling, final ClimbGap out_climbGap) {
        out_climbGap.reset();
        Orientation._climbGapTemp.reset();
        initializeOffset(0.0, isClimbCrawling, isCrawlClimbing, isCrawling);
        FeetClimbing result = FeetClimbing.None;
        int gap;
        if ((gap = this.isLadderSubstitute(2, Orientation._climbGapTemp)) > 0) {
            result = result.max(FeetClimbing.None, out_climbGap, Orientation._climbGapTemp);
        }
        Orientation._climbGapTemp.SkipGaps = (isClimbCrawling || isCrawlClimbing);
        if ((gap = this.isLadderSubstitute(1, Orientation._climbGapTemp)) > 0 && !isCrawling) {
            if (gap > 3 && !isClimbCrawling) {
                if (!isCrawlClimbing) {
                    result = result.max(FeetClimbing.FastUp, out_climbGap, Orientation._climbGapTemp);
                }
                else {
                    result = result.max(FeetClimbing.None, out_climbGap, Orientation._climbGapTemp);
                }
            }
            else if ((isClimbCrawling || isCrawlClimbing) && gap > 1) {
                if (isCrawlClimbing) {
                    result = result.max(FeetClimbing.BaseWithHands, out_climbGap, Orientation._climbGapTemp);
                }
                else {
                    result = result.max(FeetClimbing.FastUp, out_climbGap, Orientation._climbGapTemp);
                }
            }
            else if (gap > 2) {
                if (!isClimbCrawling) {
                    result = result.max(FeetClimbing.SlowUpWithHoldWithoutHands, out_climbGap, Orientation._climbGapTemp);
                }
                else {
                    result = result.max(FeetClimbing.None, out_climbGap, Orientation._climbGapTemp);
                }
            }
            else {
                result = result.max(FeetClimbing.TopWithHands, out_climbGap, Orientation._climbGapTemp);
            }
        }
        if ((gap = this.isLadderSubstitute(0, Orientation._climbGapTemp)) > 0) {
            if (gap > 3 && !isCrawling && !isCrawlClimbing) {
                result = result.max(FeetClimbing.FastUp, out_climbGap, Orientation._climbGapTemp);
            }
            else if (gap > 2 && !isCrawling) {
                if (!isClimbCrawling) {
                    if (Orientation.jh_offset < Orientation._handClimbingHoldGap) {
                        result = result.max(FeetClimbing.SlowUpWithHoldWithoutHands, out_climbGap, Orientation._climbGapTemp);
                    }
                    else {
                        result = result.max(FeetClimbing.SlowUpWithSinkWithoutHands, out_climbGap, Orientation._climbGapTemp);
                    }
                }
                else {
                    result = result.max(FeetClimbing.None, out_climbGap, Orientation._climbGapTemp);
                }
            }
            else if (Orientation.jh_offset < 1.0 - Orientation._handClimbingHoldGap) {
                result = result.max(FeetClimbing.BaseWithHands, out_climbGap, Orientation._climbGapTemp);
            }
            else {
                result = result.max(FeetClimbing.BaseHold, out_climbGap, Orientation._climbGapTemp);
            }
        }
        if (this.isLadderSubstitute(-1, Orientation._climbGapTemp) > 0) {
            result = result.max(FeetClimbing.None, out_climbGap, Orientation._climbGapTemp);
        }
        if (isCrawlClimbing || isCrawling) {
            result = result.max(FeetClimbing.BaseWithHands, out_climbGap, Orientation._climbGapTemp);
        }
        return result;
    }
    
    private int isLadderSubstitute(final int local_Offset, final ClimbGap out_climbGap) {
        initializeLocal(local_Offset);
        int gap;
        if (Orientation.local_half == 1) {
            if (this.hasHalfHold()) {
                if (!Orientation.grabRemote) {
                    final boolean overLadder = this.isOnLadderOrVine(0) || this.isOnOpenTrapDoor(0) || this.isRope(0) || this.isOnWallRope(0);
                    final boolean overOverLadder = this.isOnLadderOrVine(1) || this.isOnOpenTrapDoor(1) || this.isRope(1) || this.isOnWallRope(1);
                    final boolean overAccessible = this.isBaseAccessible(1, false, true);
                    final boolean overOverAccessible = this.isBaseAccessible(2, false, true);
                    final boolean overFullAccessible = overAccessible && this.isFullAccessible(1, Orientation.grabRemote);
                    final boolean overOverFullAccessible = overAccessible && this.isFullExtentAccessible(2, Orientation.grabRemote);
                    if (overLadder) {
                        if (overOverLadder) {
                            gap = 1;
                        }
                        else if (overOverAccessible) {
                            gap = 1;
                        }
                        else {
                            gap = 1;
                        }
                    }
                    else if (overAccessible) {
                        if (overFullAccessible) {
                            if (overOverFullAccessible) {
                                gap = 5;
                            }
                            else {
                                gap = (Orientation.crawl ? 3 : 5);
                            }
                        }
                        else if (overOverLadder) {
                            gap = 5;
                        }
                        else {
                            gap = 1;
                        }
                    }
                    else {
                        gap = 1;
                    }
                }
                else if (this.isBaseAccessible(0)) {
                    if (this.isUpperHalfFrontEmpty(Orientation.remote_i, 0, Orientation.remote_k)) {
                        if (this.isFullAccessible(1, Orientation.grabRemote)) {
                            if (this.isFullExtentAccessible(2, Orientation.grabRemote)) {
                                gap = 5;
                            }
                            else if (this.isJustLowerHalfExtentAccessible(2)) {
                                gap = 4;
                            }
                            else {
                                gap = 3;
                            }
                        }
                        else {
                            gap = 1;
                        }
                    }
                    else {
                        gap = 1;
                    }
                }
                else {
                    gap = 0;
                }
            }
            else {
                gap = 0;
            }
        }
        else if (this.hasBottomHold()) {
            if (!Orientation.grabRemote) {
                final boolean overLadder = this.isOnLadderOrVine(0) || this.isOnOpenTrapDoor(0) || this.isRope(0) || this.isOnWallRope(0);
                final boolean overOverLadder = this.isOnLadderOrVine(1) || this.isOnOpenTrapDoor(1) || this.isRope(1) || this.isOnWallRope(0);
                final boolean overAccessible = this.isBaseAccessible(0, false, true);
                final boolean overOverAccessible = this.isBaseAccessible(1, false, true);
                final boolean overFullAccessible = overAccessible && this.isFullAccessible(0, Orientation.grabRemote);
                final boolean overOverFullAccessible = overAccessible && this.isFullExtentAccessible(1, Orientation.grabRemote);
                if (overLadder) {
                    if (overOverLadder) {
                        gap = 1;
                    }
                    else if (overOverAccessible) {
                        gap = 1;
                    }
                    else {
                        gap = 1;
                    }
                }
                else if (overAccessible) {
                    if (overFullAccessible) {
                        if (overOverAccessible) {
                            if (overOverFullAccessible) {
                                gap = 4;
                            }
                            else {
                                gap = (Orientation.crawl ? 2 : 4);
                            }
                        }
                        else {
                            gap = 2;
                        }
                    }
                    else if (overOverLadder) {
                        gap = 2;
                    }
                    else {
                        gap = 1;
                    }
                }
                else {
                    gap = 1;
                }
            }
            else if (this.isBaseAccessible(0)) {
                if (this.isFullAccessible(0, Orientation.grabRemote)) {
                    if (this.isFullExtentAccessible(1, Orientation.grabRemote)) {
                        gap = 4;
                    }
                    else {
                        gap = 2;
                    }
                }
                else {
                    gap = 1;
                }
            }
            else {
                gap = 0;
            }
        }
        else {
            gap = 0;
        }
        if (out_climbGap != null && gap > 0) {
            out_climbGap.BlockId = Orientation.grabBlockId;
            out_climbGap.Meta = Orientation.grabMeta;
            out_climbGap.CanStand = (gap > 3);
            out_climbGap.MustCrawl = (gap > 1 && gap < 4);
            out_climbGap.Direction = this;
        }
        return gap;
    }
    
    private boolean hasHalfHold() {
        if (Orientation.Config.isFreeBaseClimb()) {
            if (this.isOnLadder(0) && this.isOnLadderFront(0)) {
                return this.setHalfGrabType(2, getBaseBlockId(0), false);
            }
            if (this.remoteLadderClimbing(0)) {
                return this.setHalfGrabType(2, getRemoteBlockId(0), true);
            }
        }
        if (SmartMovingOptions.hasBetterThanWolves || SmartMovingOptions.hasRopesPlus) {
            int id;
            if ((id = this.getRopeId(0)) >= 0 && this.isHeadedToRope()) {
                return this.setHalfGrabType(2, id, false);
            }
            if ((id = this.getAnchorId(0)) >= 0 && this.isOnAnchorFront(0)) {
                return this.setHalfGrabType(1, id, false);
            }
        }
        final int remoteId = getRemoteBlockId(0);
        if (this.isEmpty(Orientation.base_i, 0, Orientation.base_k) && remoteId == aqz.bu.cF && this.headedToFrontWall(Orientation.remote_i, 0, Orientation.remote_k, remoteId)) {
            return this.setHalfGrabType(1, remoteId);
        }
        final int wallId = getWallBlockId(Orientation.base_i, 0, Orientation.base_k);
        if (wallId == aqz.bu.cF && this.headedToBaseWall(0, wallId)) {
            return this.setHalfGrabType(1, wallId, false);
        }
        if (Orientation.Config._freeFenceClimbing.value) {
            if (isFence(remoteId, Orientation.remote_i, 0, Orientation.remote_k) && this.headedToFrontWall(Orientation.remote_i, 0, Orientation.remote_k, remoteId)) {
                if (!isFence(getBaseBlockId(0), Orientation.base_i, 0, Orientation.base_k)) {
                    return this.setHalfGrabType(1, remoteId);
                }
                if (this.headedToFrontSideWall(Orientation.remote_i, 0, Orientation.remote_k, remoteId)) {
                    return this.setHalfGrabType(1, remoteId);
                }
            }
            final int remoteBelowId = getRemoteBlockId(-1);
            if (isFence(remoteBelowId, Orientation.remote_i, -1, Orientation.remote_k) && this.headedToFrontWall(Orientation.remote_i, -1, Orientation.remote_k, remoteBelowId)) {
                if (!isFence(getBaseBlockId(-1), Orientation.remote_i, -1, Orientation.remote_k)) {
                    return this.setHalfGrabType(1, remoteId);
                }
                if (this.headedToFrontSideWall(Orientation.remote_i, -1, Orientation.remote_k, remoteBelowId)) {
                    return this.setHalfGrabType(1, remoteId);
                }
            }
            if (isFence(wallId, Orientation.base_i, 0, Orientation.base_k) && this.headedToBaseWall(0, wallId)) {
                return this.setHalfGrabType(1, wallId, false);
            }
            final int belowWallId = getWallBlockId(Orientation.base_i, -1, Orientation.base_k);
            if (isFence(belowWallId, Orientation.base_i, -1, Orientation.base_k) && this.headedToBaseWall(-1, belowWallId)) {
                return this.setHalfGrabType(1, belowWallId, false);
            }
            if (remoteId == aqz.cg.cF && !this.headedToRemoteFlatWall(remoteId, 0)) {
                return this.setHalfGrabType(1, remoteId);
            }
            if (remoteBelowId == aqz.cg.cF && !this.headedToRemoteFlatWall(remoteBelowId, -1)) {
                return this.setHalfGrabType(1, remoteBelowId);
            }
        }
        final int remoteMetadata = getRemoteBlockMetadata(0);
        if (isBottomHalfBlock(remoteId, remoteMetadata) || (isStairCompact(remoteId) && this.isBottomStairCompactNotBack(remoteMetadata) && (!isStairCompact(getBaseBlockId(-1)) || !this.isBottomStairCompactFront(getBaseBlockMetadata(-1))))) {
            return this.setHalfGrabType(1, remoteId);
        }
        if (isTrapDoor(remoteId) && isClosedTrapDoor(getRemoteBlockMetadata(0))) {
            return this.setHalfGrabType(1, remoteId);
        }
        final int baseId = getBaseBlockId(0);
        if (isTrapDoor(baseId) && !isClosedTrapDoor(getBaseBlockMetadata(0))) {
            return this.setHalfGrabType(1, baseId, false);
        }
        if ((SmartMovingOptions.hasASGrapplingHook && Orientation.Config._replaceRopeClimbing.value) || SmartMovingOptions.hasRopesPlus) {
            if (isASRope(baseId) && this.isASGrapplingHookFront(getBaseBlockMetadata(0))) {
                return this.setHalfGrabType(1, baseId, false);
            }
            if (isASRope(remoteId) && this.rotate(180).isASGrapplingHookFront(remoteMetadata)) {
                return this.setHalfGrabType(1, remoteId, true);
            }
        }
        if (Orientation.Config.isFreeBaseClimb()) {
            int meta = this.baseVineClimbing(0);
            if (meta > -1) {
                return this.setHalfGrabType(1, aqz.bz.cF, false, meta);
            }
            meta = this.remoteVineClimbing(0);
            if (meta > -1) {
                return this.setHalfGrabType(1, aqz.bz.cF, false, meta);
            }
        }
        return this.setHalfGrabType(0, 0);
    }
    
    private boolean hasBottomHold() {
        if (Orientation.Config.isFreeBaseClimb()) {
            if (this.isOnLadder(-1) && this.isOnLadderFront(-1)) {
                return this.setBottomGrabType(2, getBaseBlockId(-1), false);
            }
            if (this.isOnLadder(0) && this.isOnLadderFront(0)) {
                return this.setBottomGrabType(2, getBaseBlockId(0), false);
            }
            if (this.remoteLadderClimbing(-1)) {
                return this.setBottomGrabType(2, getRemoteBlockId(-1), true);
            }
            if (this.remoteLadderClimbing(0)) {
                return this.setBottomGrabType(2, getRemoteBlockId(0), true);
            }
        }
        if (SmartMovingOptions.hasBetterThanWolves || SmartMovingOptions.hasRopesPlus) {
            int id;
            if (((id = this.getRopeId(-1)) >= 0 && this.isHeadedToRope()) || ((id = this.getRopeId(0)) >= 0 && this.isHeadedToRope())) {
                return this.setBottomGrabType(2, id, false);
            }
            if (((id = this.getAnchorId(-1)) >= 0 && this.isOnAnchorFront(-1)) || ((id = this.getAnchorId(0)) >= 0 && this.isOnAnchorFront(0))) {
                return this.setBottomGrabType(1, id, false);
            }
        }
        final int remoteId = getRemoteBlockId(0);
        final int remoteBelowId = getRemoteBlockId(-1);
        final boolean remoteLowerHalfEmpty = this.isLowerHalfFrontFullEmpty(Orientation.remote_i, 0, Orientation.remote_k);
        if (SmartMovingOptions.hasRedPowerWire) {
            if (isRedPowerWire(remoteBelowId)) {
                final int coverSides = getRpCoverSides(Orientation.remote_i, -1, Orientation.remote_k);
                if ((this.isRedPowerWireFullFront(coverSides) || isRedPowerWireTop(coverSides)) && remoteLowerHalfEmpty) {
                    return this.setBottomGrabType(1, remoteBelowId);
                }
            }
            if (isRedPowerWire(remoteId)) {
                final int coverSides = getRpCoverSides(Orientation.remote_i, 0, Orientation.remote_k);
                if (isRedPowerWireBottom(coverSides) && remoteLowerHalfEmpty) {
                    return this.setBottomGrabType(1, remoteBelowId);
                }
            }
            final int baseId = getBaseBlockId(-1);
            if (isRedPowerWire(baseId)) {
                final int coverSides2 = getRpCoverSides(Orientation.base_i, -1, Orientation.base_k);
                if (this.isRedPowerWireFullBack(coverSides2) && remoteLowerHalfEmpty) {
                    return this.setBottomGrabType(1, remoteBelowId);
                }
            }
            if (isRedPowerWire(remoteBelowId)) {
                return false;
            }
        }
        if (this.isEmpty(Orientation.base_i, -1, Orientation.base_k) && remoteBelowId == aqz.bu.cF && this.headedToFrontWall(Orientation.remote_i, -1, Orientation.remote_k, remoteBelowId)) {
            return this.setBottomGrabType(1, remoteBelowId);
        }
        if (Orientation.Config._freeFenceClimbing.value) {
            final int baseBelowBlockId = getBaseBlockId(-1);
            if (isFence(remoteBelowId, Orientation.remote_i, -1, Orientation.remote_k) && this.headedToFrontWall(Orientation.remote_i, -1, Orientation.remote_k, remoteBelowId)) {
                if (!isFence(baseBelowBlockId, Orientation.base_i, -1, Orientation.base_k)) {
                    return this.setBottomGrabType(1, remoteBelowId);
                }
                if (this.headedToFrontSideWall(Orientation.remote_i, -1, Orientation.remote_k, remoteBelowId)) {
                    return this.setBottomGrabType(1, remoteBelowId);
                }
            }
            if (remoteBelowId == aqz.cg.cF && !this.headedToRemoteFlatWall(remoteBelowId, -1)) {
                return this.setHalfGrabType(1, remoteBelowId);
            }
            if (remoteId == aqz.cg.cF && !this.headedToRemoteFlatWall(remoteId, 0)) {
                return this.setHalfGrabType(1, remoteId);
            }
        }
        final int belowWallBlockId = getWallBlockId(Orientation.base_i, -1, Orientation.base_k);
        if (belowWallBlockId != -1) {
            if (this.isEmpty(Orientation.base_i - this._i, 0, Orientation.base_k - this._k) && this.isEmpty(Orientation.base_i - this._i, -1, Orientation.base_k - this._k)) {
                if (belowWallBlockId == aqz.bu.cF && this.headedToBaseWall(-1, belowWallBlockId)) {
                    return this.setBottomGrabType(1, belowWallBlockId, false);
                }
                if (this.headedToBaseGrabWall(-1, belowWallBlockId)) {
                    return this.setBottomGrabType(1, belowWallBlockId, false);
                }
            }
            return Orientation.Config._freeFenceClimbing.value && isFence(belowWallBlockId, Orientation.base_i, -1, Orientation.base_k) && this.headedToBaseWall(-1, belowWallBlockId) && this.setBottomGrabType(1, belowWallBlockId, false);
        }
        final int remoteBelowMetadata = getRemoteBlockMetadata(-1);
        if (remoteLowerHalfEmpty && this.isBaseAccessible(-1, true, false) && this.isUpperHalfFrontAnySolid(Orientation.remote_i, -1, Orientation.remote_k) && !isBottomHalfBlock(remoteBelowId, remoteBelowMetadata) && (!isStairCompact(remoteBelowId) || !this.isBottomStairCompactFront(remoteBelowMetadata)) && (!isDoor(remoteBelowId) || isDoorTop(remoteBelowMetadata)) && (!isDoor(getBaseBlockId(0)) || !this.isDoorFrontBlocked(Orientation.base_i, 0, Orientation.base_k)) && (Orientation.Config._freeFenceClimbing.value || !this.isFence(Orientation.remote_i, -1, Orientation.remote_k))) {
            return this.setBottomGrabType(1, remoteBelowId);
        }
        if (isStairCompact(remoteId)) {
            final int remoteMetadata = getRemoteBlockMetadata(0);
            if (isTopStairCompact(remoteMetadata) && !this.isTopStairCompactBack(remoteMetadata) && this.isUpperHalfFrontFullSolid(Orientation.remote_i, -1, Orientation.remote_k)) {
                return this.setBottomGrabType(1, remoteBelowId);
            }
        }
        final int baseBelowId = getBaseBlockId(-1);
        final int baseBelowMetadata = getBaseBlockMetadata(-1);
        if (isTrapDoor(baseBelowId) && !isClosedTrapDoor(getBaseBlockMetadata(-1))) {
            return this.setBottomGrabType(1, baseBelowId, false);
        }
        if (isDoor(baseBelowId) && isDoorTop(baseBelowMetadata) && this.isDoorFrontBlocked(Orientation.base_i, -1, Orientation.base_k) && this.isBaseAccessible(0)) {
            return this.setBottomGrabType(1, baseBelowId, false);
        }
        if ((SmartMovingOptions.hasASGrapplingHook && Orientation.Config._replaceRopeClimbing.value) || SmartMovingOptions.hasRopesPlus) {
            if (isASRope(baseBelowId) && this.isASGrapplingHookFront(getBaseBlockMetadata(0))) {
                return this.setBottomGrabType(1, baseBelowId, false);
            }
            final int baseId2 = getBaseBlockId(0);
            if (isASRope(baseId2) && this.isASGrapplingHookFront(getBaseBlockMetadata(0))) {
                return this.setBottomGrabType(1, baseId2, false);
            }
            if (isASRope(remoteBelowId) && this.rotate(180).isASGrapplingHookFront(remoteBelowMetadata)) {
                return this.setHalfGrabType(1, remoteBelowId, true);
            }
            if (isASRope(remoteId) && this.rotate(180).isASGrapplingHookFront(getRemoteBlockMetadata(0))) {
                return this.setHalfGrabType(1, remoteId, true);
            }
        }
        if (Orientation.Config.isFreeBaseClimb()) {
            int meta = this.baseVineClimbing(-1);
            if (meta != -1) {
                return this.setHalfGrabType(1, aqz.bz.cF, false, meta);
            }
            meta = this.baseVineClimbing(0);
            if (meta != -1) {
                return this.setHalfGrabType(1, aqz.bz.cF, false, meta);
            }
            meta = this.remoteVineClimbing(-1);
            if (meta != -1) {
                return this.setHalfGrabType(1, aqz.bz.cF, false, meta);
            }
            meta = this.remoteVineClimbing(0);
            if (meta != -1) {
                return this.setHalfGrabType(1, aqz.bz.cF, false, meta);
            }
        }
        return this.setBottomGrabType(0, 0);
    }
    
    private boolean setHalfGrabType(final int type, final int blockId) {
        return this.setHalfGrabType(type, blockId, true);
    }
    
    private boolean setHalfGrabType(final int type, final int blockId, final boolean remote) {
        return this.setHalfGrabType(type, blockId, remote, -1);
    }
    
    private boolean setHalfGrabType(final int type, final int blockId, final boolean remote, final int metaClimb) {
        boolean hasGrab = type != 0;
        if (hasGrab && remote && this._isDiagonal) {
            final boolean edgeConnectCCW = this.rotate(90).isUpperHalfFrontEmpty(Orientation.base_i, 0, Orientation.remote_k);
            final boolean edgeConnectCW = this.rotate(-90).isUpperHalfFrontEmpty(Orientation.remote_i, 0, Orientation.base_k);
            hasGrab &= (edgeConnectCCW && edgeConnectCW);
        }
        return setGrabType(type, blockId, remote, hasGrab, metaClimb);
    }
    
    private boolean setBottomGrabType(final int type, final int blockId) {
        return this.setBottomGrabType(type, blockId, true);
    }
    
    private boolean setBottomGrabType(final int type, final int blockId, final boolean remote) {
        return this.setBottomGrabType(type, blockId, remote, -1);
    }
    
    private boolean setBottomGrabType(final int type, final int blockId, final boolean remote, final int metaClimb) {
        boolean hasGrab = type != 0;
        if (hasGrab && remote && this._isDiagonal) {
            final boolean edgeConnectCCW = this.rotate(90).isLowerHalfFrontFullEmpty(Orientation.base_i, 0, Orientation.remote_k);
            final boolean edgeConnectCW = this.rotate(-90).isLowerHalfFrontFullEmpty(Orientation.remote_i, 0, Orientation.base_k);
            hasGrab &= (edgeConnectCCW && edgeConnectCW);
        }
        return setGrabType(type, blockId, remote, hasGrab, metaClimb);
    }
    
    private static boolean setGrabType(final int type, final int blockId, final boolean remote, final boolean hasGrab, final int metaClimb) {
        Orientation.grabRemote = remote;
        Orientation.grabType = (hasGrab ? type : 0);
        Orientation.grabBlockId = blockId;
        Orientation.grabMeta = metaClimb;
        return hasGrab;
    }
    
    private boolean setClimbingAngles() {
        Label_0200: {
            switch (this._i) {
                case -1: {
                    switch (this._k) {
                        case -1: {
                            return this.setClimbingAngles(135.0f);
                        }
                        case 0: {
                            return this.setClimbingAngles(90.0f);
                        }
                        case 1: {
                            return this.setClimbingAngles(45.0f);
                        }
                        default: {
                            break Label_0200;
                        }
                    }
                    break;
                }
                case 0: {
                    switch (this._k) {
                        case -1: {
                            return this.setClimbingAngles(180.0f);
                        }
                        case 0: {
                            return this.setClimbingAngles(0.0f, 360.0f);
                        }
                        case 1: {
                            return this.setClimbingAngles(0.0f);
                        }
                        default: {
                            break Label_0200;
                        }
                    }
                    break;
                }
                case 1: {
                    switch (this._k) {
                        case -1: {
                            return this.setClimbingAngles(225.0f);
                        }
                        case 0: {
                            return this.setClimbingAngles(270.0f);
                        }
                        case 1: {
                            return this.setClimbingAngles(315.0f);
                        }
                        default: {
                            break Label_0200;
                        }
                    }
                    break;
                }
            }
        }
        return false;
    }
    
    private boolean setClimbingAngles(final float directionAngle) {
        this._directionAngle = directionAngle;
        final float halfAreaAngle = (this._isDiagonal ? Orientation.Config._freeClimbingDiagonalDirectionAngle.value : Orientation.Config._freeClimbingOrthogonalDirectionAngle.value) / 2.0f;
        return this.setClimbingAngles(directionAngle - halfAreaAngle, directionAngle + halfAreaAngle);
    }
    
    private boolean setClimbingAngles(float mimimumClimbingAngle, float maximumClimbingAngle) {
        if (mimimumClimbingAngle < 0.0f) {
            mimimumClimbingAngle += 360.0f;
        }
        if (maximumClimbingAngle > 360.0f) {
            maximumClimbingAngle -= 360.0f;
        }
        this._mimimumClimbingAngle = mimimumClimbingAngle;
        this._maximumClimbingAngle = maximumClimbingAngle;
        return mimimumClimbingAngle != maximumClimbingAngle;
    }
    
    private boolean isWithinAngle(final float minimumRotation, final float maximumRotation) {
        return this.isWithinAngle(this._directionAngle, minimumRotation, maximumRotation);
    }
    
    private boolean isRotationForClimbing(final float rotation) {
        return this.isWithinAngle(rotation, this._mimimumClimbingAngle, this._maximumClimbingAngle);
    }
    
    private boolean isWithinAngle(final float rotation, final float minimumRotation, final float maximumRotation) {
        if (minimumRotation > maximumRotation) {
            return rotation >= minimumRotation || rotation <= maximumRotation;
        }
        return rotation >= minimumRotation && rotation <= maximumRotation;
    }
    
    private int baseVineClimbing(final int j_offset) {
        boolean result = this.isOnVine(j_offset);
        if (result) {
            result = this.isOnVineFront(j_offset);
            if (result) {
                return 0;
            }
            if (this.baseVineClimbing(j_offset, Orientation.PZ) || this.baseVineClimbing(j_offset, Orientation.NZ) || this.baseVineClimbing(j_offset, Orientation.ZP) || this.baseVineClimbing(j_offset, Orientation.ZN)) {
                return 1;
            }
        }
        return -1;
    }
    
    private boolean baseVineClimbing(final int j_offset, final Orientation orientation) {
        return orientation != this && orientation.rotate(180).hasVineOrientation(Orientation.world, Orientation.base_i, Orientation.local_offset + j_offset, Orientation.base_k) && orientation.getHorizontalBorderGap() >= 0.65;
    }
    
    private boolean remoteLadderClimbing(final int j_offset) {
        return this.isBehindLadder(j_offset) && this.isOnLadderBack(j_offset);
    }
    
    private int remoteVineClimbing(final int j_offset) {
        if (this.isBehindVine(j_offset) && this.isOnVineBack(j_offset)) {
            return 0;
        }
        if (this.remoteVineClimbing(j_offset, Orientation.PZ) || this.remoteVineClimbing(j_offset, Orientation.NZ) || this.remoteVineClimbing(j_offset, Orientation.ZP) || this.remoteVineClimbing(j_offset, Orientation.ZN)) {
            return 1;
        }
        return -1;
    }
    
    private boolean remoteVineClimbing(final int j_offset, final Orientation orientation) {
        if (orientation == this) {
            return false;
        }
        final int i = Orientation.base_i - orientation._i;
        final int k = Orientation.base_k - orientation._k;
        return isVine(getBlockId(i, j_offset, k)) && orientation.hasVineOrientation(Orientation.world, i, Orientation.local_offset + j_offset, k) && orientation.getHorizontalBorderGap() >= 0.6499999761581421;
    }
    
    private boolean isOnLadder(final int j_offset) {
        final int blockId = getBaseBlockId(j_offset);
        return isLadder(blockId) || (!isVine(blockId) && isClimbable(Orientation.world, Orientation.base_i, Orientation.local_offset + j_offset, Orientation.base_k));
    }
    
    private boolean isBehindLadder(final int j_offset) {
        final int blockId = getRemoteBlockId(j_offset);
        return isLadder(blockId) || (!isVine(blockId) && isClimbable(Orientation.world, Orientation.remote_i, Orientation.local_offset + j_offset, Orientation.remote_k));
    }
    
    private boolean isOnVine(final int j_offset) {
        return isVine(getBaseBlockId(j_offset));
    }
    
    private boolean isBehindVine(final int j_offset) {
        return isVine(getRemoteBlockId(j_offset));
    }
    
    private boolean isOnLadderOrVine(final int j_offset) {
        return isLadderOrVine(getBaseBlockId(j_offset)) || isVine(Orientation.grabBlockId);
    }
    
    public static boolean isLadder(final int blockId) {
        return blockId == aqz.aK.cF;
    }
    
    public static boolean isVine(final int blockId) {
        return blockId == aqz.bz.cF;
    }
    
    public static boolean isLadderOrVine(final int blockId) {
        return isLadder(blockId) || isVine(blockId) || isBlockIdOfType(blockId, Orientation._ladderKitLadderTypes);
    }
    
    public static boolean isKnownLadder(final int blockId) {
        return isLadder(blockId) || isBlockIdOfType(blockId, Orientation._ladderKitLadderTypes);
    }
    
    public static boolean isClimbable(final abw world, final int i, final int j, final int k) {
        final int blockId = world.a(i, j, k);
        if (Orientation._isLadder != null) {
            return blockId > 0 && (boolean)Reflect.Invoke(Orientation._isLadder, aqz.s[blockId], world, i, j, k, atv.w().h);
        }
        return isLadderOrVine(blockId);
    }
    
    private boolean isOnLadderFront(final int j_offset) {
        return this.hasLadderOrientation(Orientation.world, Orientation.base_i, Orientation.local_offset + j_offset, Orientation.base_k);
    }
    
    private boolean isOnLadderBack(final int j_offset) {
        return this.rotate(180).hasLadderOrientation(Orientation.world, Orientation.remote_i, Orientation.local_offset + j_offset, Orientation.remote_k);
    }
    
    private boolean isOnVineFront(final int j_offset) {
        return this.hasVineOrientation(Orientation.world, Orientation.base_i, Orientation.local_offset + j_offset, Orientation.base_k);
    }
    
    private boolean isOnVineBack(final int j_offset) {
        return this.rotate(180).hasVineOrientation(Orientation.world, Orientation.remote_i, Orientation.local_offset + j_offset, Orientation.remote_k);
    }
    
    public static Orientation getKnownLadderOrientation(final abw world, final int i, final int j, final int k) {
        final int blockId = world.a(i, j, k);
        final int metadata = world.h(i, j, k);
        if (isBlockIdOfType(blockId, Orientation._ladderKitLadderTypes)) {
            switch (metadata & 0x3) {
                case 1: {
                    return Orientation.NZ;
                }
                case 3: {
                    return Orientation.PZ;
                }
                case 0: {
                    return Orientation.ZP;
                }
                case 2: {
                    return Orientation.ZN;
                }
                default: {
                    return null;
                }
            }
        }
        else {
            switch (metadata & 0x7) {
                case 5: {
                    return Orientation.NZ;
                }
                case 4: {
                    return Orientation.PZ;
                }
                case 2: {
                    return Orientation.ZP;
                }
                case 3: {
                    return Orientation.ZN;
                }
                default: {
                    return null;
                }
            }
        }
    }
    
    public boolean hasVineOrientation(final abw world, final int i, final int j, final int k) {
        final int metaData = world.h(i, j, k);
        if (this == Orientation.NZ) {
            return (metaData & 0x2) != 0x0;
        }
        if (this == Orientation.PZ) {
            return (metaData & 0x8) != 0x0;
        }
        if (this == Orientation.ZP) {
            return (metaData & 0x1) != 0x0;
        }
        return this == Orientation.ZN && (metaData & 0x4) != 0x0;
    }
    
    private boolean hasLadderOrientation(final abw world, final int i, final int j, final int k) {
        final int blockId = world.a(i, j, k);
        int metadata = world.h(i, j, k);
        if (isBlockIdOfType(blockId, Orientation._ladderKitLadderTypes)) {
            metadata &= 0x3;
            if (this == Orientation.NZ) {
                return metadata == 1;
            }
            if (this == Orientation.PZ) {
                return metadata == 3;
            }
            if (this == Orientation.ZP) {
                return metadata == 0;
            }
            return this == Orientation.ZN && metadata == 2;
        }
        else {
            metadata &= 0x7;
            if (this == Orientation.NZ) {
                return metadata == 5;
            }
            if (this == Orientation.PZ) {
                return metadata == 4;
            }
            if (this == Orientation.ZP) {
                return metadata == 2;
            }
            return this == Orientation.ZN && metadata == 3;
        }
    }
    
    public boolean isRemoteSolid(final abw world, final int i, final int j, final int k) {
        return isSolid(world.g(i + this._i, j, k + this._k));
    }
    
    public static Orientation getOpenTrapDoorOrientation(final abw world, final int i, final int j, final int k) {
        final int metadata = world.h(i, j, k);
        if (!isClosedTrapDoor(metadata)) {
            switch (metadata & 0x3) {
                case 0: {
                    return Orientation.ZP;
                }
                case 1: {
                    return Orientation.ZN;
                }
                case 2: {
                    return Orientation.PZ;
                }
                case 3: {
                    return Orientation.NZ;
                }
            }
        }
        return null;
    }
    
    private boolean isHeadedToRope() {
        final int iTriple = getTriple(Orientation.base_id, Orientation.base_kd);
        final int kTriple = getTriple(Orientation.base_kd, Orientation.base_id);
        if (iTriple > 0) {
            if (kTriple > 0) {
                return this == Orientation.NN;
            }
            if (kTriple < 0) {
                return this == Orientation.NP;
            }
            return this == Orientation.NZ;
        }
        else if (iTriple < 0) {
            if (kTriple > 0) {
                return this == Orientation.PN;
            }
            if (kTriple < 0) {
                return this == Orientation.PP;
            }
            return this == Orientation.PZ;
        }
        else {
            if (kTriple > 0) {
                return this == Orientation.ZN;
            }
            if (kTriple < 0) {
                return this == Orientation.ZP;
            }
            return this == Orientation.ZZ;
        }
    }
    
    private boolean isOnAnchorFront(final int j_offset) {
        switch (getBaseBlockMetadata(j_offset)) {
            case 0: {
                return false;
            }
            case 1: {
                return false;
            }
            case 2: {
                return this._k == 1;
            }
            case 3: {
                return this._k == -1;
            }
            case 4: {
                return this._i == 1;
            }
            case 5: {
                return this._i == -1;
            }
            default: {
                return false;
            }
        }
    }
    
    private int getRopeId(final int j_offset) {
        final int id = getBaseBlockId(j_offset);
        if (isRopeId(id)) {
            return id;
        }
        return -1;
    }
    
    private boolean isRope(final int j_offset) {
        return this.getRopeId(j_offset) >= 0;
    }
    
    private static boolean isRopeId(final int id) {
        return (SmartMovingOptions.hasBetterThanWolves && hasBlockName(id, "tile.fcRopeBlock")) || (SmartMovingOptions.hasRopesPlus && hasBlockName(id, "tile.blockRopeCentral"));
    }
    
    private int getAnchorId(final int j_offset) {
        final int id = getBaseBlockId(j_offset);
        if (isAnchorId(id)) {
            return id;
        }
        return -1;
    }
    
    private static boolean isAnchorId(final int id) {
        return hasBlockName(id, "tile.fcAnchor");
    }
    
    private boolean isOnWallRope(final int j_offset) {
        return (SmartMovingOptions.hasASGrapplingHook || SmartMovingOptions.hasRopesPlus) && isASRope(getBaseBlockId(j_offset));
    }
    
    private static boolean isASRope(final int id) {
        return hasBlockName(id, "tile.blockRope");
    }
    
    private static boolean isASGrapplingHook(final int id) {
        return hasBlockName(id, "tile.blockGrHk");
    }
    
    private boolean isASGrapplingHookFront(final int metaData) {
        final boolean kPos = metaData % 2 != 0;
        final boolean iNeg = metaData / 2 % 2 != 0;
        final boolean kNeg = metaData / 4 % 2 != 0;
        final boolean iPos = metaData / 8 % 2 != 0;
        if ((this._i > 0 && iPos) || (this._i < 0 && iNeg)) {
            if (this._k > 0) {
                return kPos;
            }
            return this._k >= 0 || kNeg;
        }
        else {
            if ((this._k <= 0 || !kPos) && (this._k >= 0 || !kNeg)) {
                return false;
            }
            if (this._i > 0) {
                return iPos;
            }
            return this._i >= 0 || iNeg;
        }
    }
    
    private boolean isOnOpenTrapDoor(final int j_offset) {
        return isTrapDoor(getBaseBlockId(j_offset)) && !isClosedTrapDoor(getBaseBlockMetadata(j_offset));
    }
    
    private boolean isTrapDoorFront(final int trapDoorMetadata) {
        if (this == Orientation.NZ) {
            return (trapDoorMetadata & 0x3) == 0x3;
        }
        if (this == Orientation.PZ) {
            return (trapDoorMetadata & 0x3) == 0x2;
        }
        if (this == Orientation.ZP) {
            return (trapDoorMetadata & 0x3) == 0x0;
        }
        if (this == Orientation.ZN) {
            return (trapDoorMetadata & 0x3) == 0x1;
        }
        if (this == Orientation.PN) {
            return (trapDoorMetadata & 0x3) == 0x2 || (trapDoorMetadata & 0x3) == 0x1;
        }
        if (this == Orientation.PP) {
            return (trapDoorMetadata & 0x3) == 0x2 || (trapDoorMetadata & 0x3) == 0x0;
        }
        if (this == Orientation.NN) {
            return (trapDoorMetadata & 0x3) == 0x3 || (trapDoorMetadata & 0x3) == 0x1;
        }
        return this == Orientation.NP && ((trapDoorMetadata & 0x3) == 0x3 || (trapDoorMetadata & 0x3) == 0x0);
    }
    
    private boolean isBottomStairCompactNotBack(final int stairMetadata) {
        return !isTopStairCompact(stairMetadata) && !this.isStairCompactBack(stairMetadata);
    }
    
    private boolean isBottomStairCompactFront(final int stairMetadata) {
        return !isTopStairCompact(stairMetadata) && this.isStairCompactFront(stairMetadata);
    }
    
    private boolean isTopStairCompactFront(final int stairMetadata) {
        return isTopStairCompact(stairMetadata) && this.isStairCompactFront(stairMetadata);
    }
    
    private boolean isTopStairCompactBack(final int stairMetadata) {
        return isTopStairCompact(stairMetadata) && this.isStairCompactBack(stairMetadata);
    }
    
    private boolean isStairCompactFront(int stairMetadata) {
        stairMetadata &= 0x3;
        if (this == Orientation.NZ) {
            return stairMetadata == 1;
        }
        if (this == Orientation.PZ) {
            return stairMetadata == 0;
        }
        if (this == Orientation.ZP) {
            return stairMetadata == 2;
        }
        if (this == Orientation.ZN) {
            return stairMetadata == 3;
        }
        if (this == Orientation.PN) {
            return stairMetadata == 0 || stairMetadata == 3;
        }
        if (this == Orientation.PP) {
            return stairMetadata == 0 || stairMetadata == 2;
        }
        if (this == Orientation.NN) {
            return stairMetadata == 1 || stairMetadata == 3;
        }
        return this == Orientation.NP && (stairMetadata == 1 || stairMetadata == 2);
    }
    
    private boolean isStairCompactBack(int stairMetadata) {
        stairMetadata &= 0x3;
        if (this == Orientation.NZ) {
            return stairMetadata == 0;
        }
        if (this == Orientation.PZ) {
            return stairMetadata == 1;
        }
        if (this == Orientation.ZP) {
            return stairMetadata == 3;
        }
        if (this == Orientation.ZN) {
            return stairMetadata == 2;
        }
        if (this == Orientation.PN) {
            return stairMetadata == 1 || stairMetadata == 2;
        }
        if (this == Orientation.PP) {
            return stairMetadata == 1 || stairMetadata == 3;
        }
        if (this == Orientation.NN) {
            return stairMetadata == 0 || stairMetadata == 2;
        }
        return this == Orientation.NP && (stairMetadata == 0 || stairMetadata == 3);
    }
    
    private static boolean isTopStairCompact(final int stairMetadata) {
        return (stairMetadata & 0x4) != 0x0;
    }
    
    private static boolean isRedPowerWireTop(final int coverSides) {
        return (coverSides >> 1) % 2 == 1;
    }
    
    private static boolean isRedPowerWireBottom(final int coverSides) {
        return (coverSides >> 0) % 2 == 1;
    }
    
    private boolean isRedPowerWireFullFront(final int coverSides) {
        if (this == Orientation.NZ) {
            return (coverSides >> 5) % 2 == 1;
        }
        if (this == Orientation.PZ) {
            return (coverSides >> 4) % 2 == 1;
        }
        if (this == Orientation.ZP) {
            return (coverSides >> 2) % 2 == 1;
        }
        if (this == Orientation.ZN) {
            return (coverSides >> 3) % 2 == 1;
        }
        if (this == Orientation.PN) {
            return Orientation.PZ.isRedPowerWireFullFront(coverSides) && Orientation.ZN.isRedPowerWireFullFront(coverSides);
        }
        if (this == Orientation.PP) {
            return Orientation.PZ.isRedPowerWireFullFront(coverSides) && Orientation.ZP.isRedPowerWireFullFront(coverSides);
        }
        if (this == Orientation.NN) {
            return Orientation.NZ.isRedPowerWireFullFront(coverSides) && Orientation.ZN.isRedPowerWireFullFront(coverSides);
        }
        return this == Orientation.NP && Orientation.NZ.isRedPowerWireFullFront(coverSides) && Orientation.ZP.isRedPowerWireFullFront(coverSides);
    }
    
    private boolean isRedPowerWireAnyFront(final int coverSides) {
        if (this == Orientation.NZ) {
            return (coverSides >> 5) % 2 == 1;
        }
        if (this == Orientation.PZ) {
            return (coverSides >> 4) % 2 == 1;
        }
        if (this == Orientation.ZP) {
            return (coverSides >> 2) % 2 == 1;
        }
        if (this == Orientation.ZN) {
            return (coverSides >> 3) % 2 == 1;
        }
        if (this == Orientation.PN) {
            return Orientation.PZ.isRedPowerWireFullFront(coverSides) || Orientation.ZN.isRedPowerWireFullFront(coverSides);
        }
        if (this == Orientation.PP) {
            return Orientation.PZ.isRedPowerWireFullFront(coverSides) || Orientation.ZP.isRedPowerWireFullFront(coverSides);
        }
        if (this == Orientation.NN) {
            return Orientation.NZ.isRedPowerWireFullFront(coverSides) || Orientation.ZN.isRedPowerWireFullFront(coverSides);
        }
        return this == Orientation.NP && (Orientation.NZ.isRedPowerWireFullFront(coverSides) || Orientation.ZP.isRedPowerWireFullFront(coverSides));
    }
    
    private boolean isRedPowerWireFullBack(final int coverSides) {
        if (this == Orientation.NZ) {
            return (coverSides >> 4) % 2 == 1;
        }
        if (this == Orientation.PZ) {
            return (coverSides >> 5) % 2 == 1;
        }
        if (this == Orientation.ZP) {
            return (coverSides >> 3) % 2 == 1;
        }
        if (this == Orientation.ZN) {
            return (coverSides >> 2) % 2 == 1;
        }
        if (this == Orientation.PN) {
            return Orientation.PZ.isRedPowerWireFullBack(coverSides) && Orientation.ZN.isRedPowerWireFullBack(coverSides);
        }
        if (this == Orientation.PP) {
            return Orientation.PZ.isRedPowerWireFullBack(coverSides) && Orientation.ZP.isRedPowerWireFullBack(coverSides);
        }
        if (this == Orientation.NN) {
            return Orientation.NZ.isRedPowerWireFullBack(coverSides) && Orientation.ZN.isRedPowerWireFullBack(coverSides);
        }
        return this == Orientation.NP && Orientation.NZ.isRedPowerWireFullBack(coverSides) && Orientation.ZP.isRedPowerWireFullBack(coverSides);
    }
    
    private boolean isRedPowerWireAnyBack(final int coverSides) {
        if (this == Orientation.NZ) {
            return (coverSides >> 4) % 2 == 1;
        }
        if (this == Orientation.PZ) {
            return (coverSides >> 5) % 2 == 1;
        }
        if (this == Orientation.ZP) {
            return (coverSides >> 3) % 2 == 1;
        }
        if (this == Orientation.ZN) {
            return (coverSides >> 2) % 2 == 1;
        }
        if (this == Orientation.PN) {
            return Orientation.PZ.isRedPowerWireFullBack(coverSides) || Orientation.ZN.isRedPowerWireFullBack(coverSides);
        }
        if (this == Orientation.PP) {
            return Orientation.PZ.isRedPowerWireFullBack(coverSides) || Orientation.ZP.isRedPowerWireFullBack(coverSides);
        }
        if (this == Orientation.NN) {
            return Orientation.NZ.isRedPowerWireFullBack(coverSides) || Orientation.ZN.isRedPowerWireFullBack(coverSides);
        }
        return this == Orientation.NP && (Orientation.NZ.isRedPowerWireFullBack(coverSides) || Orientation.ZP.isRedPowerWireFullBack(coverSides));
    }
    
    private boolean isFenceGateFront(final int metaData) {
        final int direction = metaData % 4;
        if (this == Orientation.NZ) {
            return direction == 0 || direction == 2;
        }
        if (this == Orientation.PZ) {
            return direction == 0 || direction == 2;
        }
        if (this == Orientation.ZP) {
            return direction == 1 || direction == 3;
        }
        return this == Orientation.ZN && (direction == 1 || direction == 3);
    }
    
    private boolean headedToFrontWall(final int i, final int j_offset, final int k, final int id) {
        final aqz block = aqz.s[id];
        boolean zn = this.getWallFlag(Orientation.ZN, i, j_offset, k, block);
        boolean zp = this.getWallFlag(Orientation.ZP, i, j_offset, k, block);
        boolean nz = this.getWallFlag(Orientation.NZ, i, j_offset, k, block);
        boolean pz = this.getWallFlag(Orientation.PZ, i, j_offset, k, block);
        final boolean allOnNone = this.getAllWallsOnNoWall(block);
        if (allOnNone && !zn && !zp && !nz && !pz) {
            zp = (zn = (nz = (pz = true)));
        }
        return this.headedToWall(Orientation.NZ, pz) || this.headedToWall(Orientation.PZ, nz) || this.headedToWall(Orientation.ZN, zp) || this.headedToWall(Orientation.ZP, zn);
    }
    
    private boolean headedToFrontSideWall(final int i, final int j_offset, final int k, final int id) {
        final aqz block = aqz.s[id];
        boolean zn = this.getWallFlag(Orientation.ZN, i, j_offset, k, block);
        boolean zp = this.getWallFlag(Orientation.ZP, i, j_offset, k, block);
        boolean nz = this.getWallFlag(Orientation.NZ, i, j_offset, k, block);
        boolean pz = this.getWallFlag(Orientation.PZ, i, j_offset, k, block);
        final boolean allOnNone = this.getAllWallsOnNoWall(block);
        if (allOnNone && !zn && !zp && !nz && !pz) {
            zp = (zn = (nz = (pz = true)));
        }
        final boolean iTop = isTopHalf(Orientation.base_id);
        final boolean kTop = isTopHalf(Orientation.base_kd);
        if (iTop) {
            if (kTop) {
                return this.headedToWall(Orientation.NZ, zp) || this.headedToWall(Orientation.PZ, zp) || this.headedToWall(Orientation.ZN, pz) || this.headedToWall(Orientation.ZP, pz);
            }
            return this.headedToWall(Orientation.NZ, zn) || this.headedToWall(Orientation.PZ, zn) || this.headedToWall(Orientation.ZN, pz) || this.headedToWall(Orientation.ZP, pz);
        }
        else {
            if (kTop) {
                return this.headedToWall(Orientation.NZ, zp) || this.headedToWall(Orientation.PZ, zp) || this.headedToWall(Orientation.ZN, nz) || this.headedToWall(Orientation.ZP, nz);
            }
            return this.headedToWall(Orientation.NZ, zn) || this.headedToWall(Orientation.PZ, zn) || this.headedToWall(Orientation.ZN, nz) || this.headedToWall(Orientation.ZP, nz);
        }
    }
    
    private boolean headedToWall(final Orientation base, final boolean result) {
        return (this == base || this == base.rotate(45) || this == base.rotate(-45)) && result;
    }
    
    private boolean headedToBaseWall(final int j_offset, final int id) {
        final aqz block = aqz.s[id];
        boolean zn = this.getWallFlag(Orientation.ZN, Orientation.base_i, j_offset, Orientation.base_k, block);
        boolean zp = this.getWallFlag(Orientation.ZP, Orientation.base_i, j_offset, Orientation.base_k, block);
        boolean nz = this.getWallFlag(Orientation.NZ, Orientation.base_i, j_offset, Orientation.base_k, block);
        boolean pz = this.getWallFlag(Orientation.PZ, Orientation.base_i, j_offset, Orientation.base_k, block);
        final boolean allOnNone = this.getAllWallsOnNoWall(block);
        if (allOnNone && !zn && !zp && !nz && !pz) {
            zp = (zn = (nz = (pz = true)));
        }
        final boolean leaf = zn || zp || nz || pz;
        final boolean coreOnly = !allOnNone && !leaf;
        final boolean iTop = isTopHalf(Orientation.base_id);
        final boolean kTop = isTopHalf(Orientation.base_kd);
        if (iTop) {
            if (kTop) {
                return this.headedToBaseWall(Orientation.NN, Orientation.NZ, Orientation.ZN, zp, nz, pz, zn, coreOnly, leaf);
            }
            return this.headedToBaseWall(Orientation.NP, Orientation.NZ, Orientation.ZP, zn, nz, pz, zp, coreOnly, leaf);
        }
        else {
            if (kTop) {
                return this.headedToBaseWall(Orientation.PN, Orientation.PZ, Orientation.ZN, zp, pz, nz, zn, coreOnly, leaf);
            }
            return this.headedToBaseWall(Orientation.PP, Orientation.PZ, Orientation.ZP, zn, pz, nz, zp, coreOnly, leaf);
        }
    }
    
    private boolean headedToBaseWall(final Orientation diagonal, final Orientation left, final Orientation right, final boolean leftFront, final boolean rightFrontOpposite, final boolean rightFront, final boolean leftFrontOpposite, final boolean co, final boolean leaf) {
        if (this == diagonal) {
            return leaf || co;
        }
        if (this == left) {
            return this.headedToBaseWall(leftFront, rightFrontOpposite, rightFront, leftFrontOpposite, co);
        }
        return this == right && this.headedToBaseWall(rightFront, leftFrontOpposite, leftFront, rightFrontOpposite, co);
    }
    
    private boolean headedToBaseWall(final boolean front, final boolean sideOpposite, final boolean side, final boolean frontOpposite, final boolean coreOnly) {
        return front || (sideOpposite && !side) || (frontOpposite && !front && !side) || coreOnly;
    }
    
    private boolean headedToBaseGrabWall(final int j_offset, final int id) {
        final aqz block = aqz.s[id];
        boolean zn = this.getWallFlag(Orientation.ZN, Orientation.base_i, j_offset, Orientation.base_k, block);
        boolean zp = this.getWallFlag(Orientation.ZP, Orientation.base_i, j_offset, Orientation.base_k, block);
        boolean nz = this.getWallFlag(Orientation.NZ, Orientation.base_i, j_offset, Orientation.base_k, block);
        boolean pz = this.getWallFlag(Orientation.PZ, Orientation.base_i, j_offset, Orientation.base_k, block);
        final boolean allOnNone = this.getAllWallsOnNoWall(block);
        if (allOnNone && !zn && !zp && !nz && !pz) {
            zp = (zn = (nz = (pz = true)));
        }
        final int aboveId = getBlockId(Orientation.base_i, j_offset + 1, Orientation.base_k);
        boolean apz;
        boolean anz;
        boolean azn;
        boolean azp;
        if (isFullEmpty(aboveId)) {
            azp = (azn = (anz = (apz = false)));
        }
        else if (isWallBlock(aboveId, Orientation.base_i, j_offset + 1, Orientation.base_k)) {
            final aqz aboveBlock = aqz.s[aboveId];
            azn = this.getWallFlag(Orientation.ZN, Orientation.base_i, j_offset + 1, Orientation.base_k, aboveBlock);
            azp = this.getWallFlag(Orientation.ZP, Orientation.base_i, j_offset + 1, Orientation.base_k, aboveBlock);
            anz = this.getWallFlag(Orientation.NZ, Orientation.base_i, j_offset + 1, Orientation.base_k, aboveBlock);
            apz = this.getWallFlag(Orientation.PZ, Orientation.base_i, j_offset + 1, Orientation.base_k, aboveBlock);
            final boolean aboveAllOnNone = this.getAllWallsOnNoWall(aboveBlock);
            if (aboveAllOnNone && !azn && !azp && !anz && !apz) {
                azp = (azn = (anz = (apz = true)));
            }
        }
        else {
            azp = (azn = (anz = (apz = true)));
        }
        final boolean iTop = isTopHalf(Orientation.base_id);
        final boolean kTop = isTopHalf(Orientation.base_kd);
        if (iTop) {
            if (kTop) {
                return this.headedToBaseGrabWall(-this._i, -this._k, zp, pz, nz, zn, azp, apz, anz, azn);
            }
            return this.headedToBaseGrabWall(-this._i, this._k, pz, zn, zp, nz, apz, azn, azp, anz);
        }
        else {
            if (kTop) {
                return this.headedToBaseGrabWall(this._i, -this._k, nz, zp, zn, pz, anz, azp, azn, apz);
            }
            return this.headedToBaseGrabWall(this._i, this._k, zn, nz, pz, zp, azn, anz, apz, azp);
        }
    }
    
    private boolean headedToBaseGrabWall(final int i, final int k, final boolean front, final boolean side, final boolean frontOpposite, final boolean sideOpposite, final boolean aboveFront, final boolean aboveSide, final boolean aboveFrontOpposite, final boolean aboveSideOpposite) {
        return (sideOpposite && !aboveSideOpposite && !front && !aboveFront && i == 1) || (frontOpposite && !aboveFrontOpposite && !side && !aboveSide && k == 1) || (side && !aboveSide && k >= 0) || (front && !aboveFront && k >= 0) || (frontOpposite && !aboveFrontOpposite && !aboveFront && i == 1 && k >= 0) || (sideOpposite && !aboveSideOpposite && !aboveSide && k == 1 && i >= 0);
    }
    
    private boolean headedToRemoteFlatWall(final int id, final int j_offset) {
        final aqz block = aqz.s[id];
        return !this.getWallFlag(this, Orientation.remote_i, j_offset, Orientation.remote_k, block) && this.getWallFlag(this.rotate(90), Orientation.remote_i, j_offset, Orientation.remote_k, block) && !this.getWallFlag(this.rotate(180), Orientation.remote_i, j_offset, Orientation.remote_k, block) && this.getWallFlag(this.rotate(-90), Orientation.remote_i, j_offset, Orientation.remote_k, block);
    }
    
    private boolean getWallFlag(final Orientation direction, final int i, final int j_offset, final int k, final aqz block) {
        if (block instanceof aqy) {
            return ((aqy)block).d(getBlockId(i + direction._i, j_offset, k + direction._k));
        }
        if (isFenceBase(block.cF)) {
            if (block instanceof aoh) {
                return ((aoh)block).d((acf)Orientation.world, i + direction._i, Orientation.local_offset + j_offset, k + direction._k);
            }
            if (block instanceof arn) {
                return ((arn)block).d((acf)Orientation.world, i + direction._i, Orientation.local_offset + j_offset, k + direction._k);
            }
            if (SmartMovingOptions.hasBetterMisc && Orientation._canConnectFenceTo != null) {
                return (boolean)Reflect.Invoke(Orientation._canConnectFenceTo, block, Orientation.world, i + direction._i, Orientation.local_offset + j_offset, k + direction._k);
            }
        }
        else if (isFenceGate(block.cF)) {
            final int metaData = getBlockMetadata(i, j_offset, k);
            return isClosedFenceGate(metaData) && this.isFenceGateFront(metaData);
        }
        return false;
    }
    
    private boolean getAllWallsOnNoWall(final aqz block) {
        return block instanceof aqy;
    }
    
    private static boolean isTopHalf(final double d) {
        return (int)Math.abs(Math.floor(d * 2.0)) % 2 == 1;
    }
    
    private static int getTriple(double primary, double secondary) {
        primary = primary - Math.floor(primary) - 0.5;
        secondary = secondary - Math.floor(secondary) - 0.5;
        if (Math.abs(primary) * 2.0 < Math.abs(secondary)) {
            return 0;
        }
        if (primary > 0.0) {
            return 1;
        }
        if (primary < 0.0) {
            return -1;
        }
        return 0;
    }
    
    private static boolean isBottomHalfBlock(final int id, final int metadata) {
        return (isHalfBlock(id) && isHalfBlockBottomMetaData(metadata)) || id == aqz.X.cF || (SmartMovingOptions.hasBetterThanWolves && isAnchorId(id) && metadata == 1);
    }
    
    private static boolean isTopHalfBlock(final int id, final int metadata) {
        return isHalfBlock(id) && isHalfBlockTopMetaData(metadata);
    }
    
    private static boolean isHalfBlockBottomMetaData(final int metadata) {
        return (metadata & 0x8) == 0x0;
    }
    
    private static boolean isHalfBlockTopMetaData(final int metadata) {
        return (metadata & 0x8) != 0x0;
    }
    
    private static boolean isHalfBlock(final int id) {
        return isBlock(id, aop.class, Orientation._knownHalfBlocks) && !((aop)aqz.s[id]).c();
    }
    
    private static boolean isStairCompact(final int id) {
        return isBlock(id, aqp.class, Orientation._knownCompactStairBlocks);
    }
    
    private boolean isLowerHalfFrontFullEmpty(final int i, final int j_offset, final int k) {
        final int id = getBlockId(i, j_offset, k);
        boolean empty = isFullEmpty(id);
        if (!empty && SmartMovingOptions.hasRedPowerWire && isRedPowerWire(id)) {
            final int coverSides = getRpCoverSides(i, j_offset, k);
            if (!this.isRedPowerWireAnyFront(coverSides)) {
                empty = true;
            }
        }
        if (!empty && SmartMovingOptions.hasBetterThanWolves && isAnchorId(id)) {
            empty = (getBlockMetadata(i, j_offset, k) == 0);
        }
        if (!empty && isStairCompact(id) && this.isTopStairCompactFront(getBlockMetadata(i, j_offset, k))) {
            empty = true;
        }
        if (!empty && isHalfBlock(id) && isHalfBlockTopMetaData(getBlockMetadata(i, j_offset, k))) {
            empty = true;
        }
        if (!empty && isWallBlock(id, i, j_offset, k) && !this.headedToFrontWall(i, j_offset, k, id)) {
            empty = true;
        }
        if (!empty && isDoor(id) && !this.rotate(180).isDoorFrontBlocked(i, j_offset, k)) {
            empty = true;
        }
        if (!empty && (SmartMovingOptions.hasASGrapplingHook || SmartMovingOptions.hasRopesPlus) && isASRope(id) && !this.rotate(180).isASGrapplingHookFront(getRemoteBlockMetadata(j_offset))) {
            empty = true;
        }
        if (empty && isBlockIdOfType(id, Orientation._ladderKitLadderTypes) && this.rotate(180).hasLadderOrientation(Orientation.world, i, Orientation.local_offset + j_offset, k)) {
            empty = false;
        }
        return empty;
    }
    
    private boolean isUpperHalfFrontAnySolid(final int i, final int j_offset, final int k) {
        final int id = getBlockId(i, j_offset, k);
        boolean solid = this.isUpperHalfFrontFullSolid(i, j_offset, k);
        if (solid && isWallBlock(id, i, j_offset, k) && !this.headedToFrontWall(i, j_offset, k, id)) {
            solid = false;
        }
        return solid;
    }
    
    private boolean isUpperHalfFrontFullSolid(final int i, final int j_offset, final int k) {
        final int id = getBlockId(i, j_offset, k);
        if (id <= 0) {
            return false;
        }
        final aqz block = aqz.s[id];
        boolean solid = isSolid(block.cU);
        if (solid && id == aqz.aI.cF) {
            solid = false;
        }
        if (solid && id == aqz.aN.cF) {
            solid = false;
        }
        if (solid && block instanceof apw) {
            solid = false;
        }
        if (solid && isTrapDoor(id)) {
            solid = false;
        }
        if (solid && SmartMovingOptions.hasASGrapplingHook && isASGrapplingHook(id)) {
            solid = false;
        }
        if (solid && this.isOpenFenceGate(id, getBlockMetadata(i, j_offset, k))) {
            solid = false;
        }
        return solid;
    }
    
    private static boolean isFullEmpty(final int id) {
        if (id <= 0) {
            return true;
        }
        final aqz block = aqz.s[id];
        boolean empty = !isSolid(block.cU);
        if (!empty && id == aqz.aI.cF) {
            empty = true;
        }
        if (!empty && id == aqz.aN.cF) {
            empty = true;
        }
        if (!empty && block instanceof apw) {
            empty = true;
        }
        if (!empty && (SmartMovingOptions.hasASGrapplingHook || SmartMovingOptions.hasRopesPlus) && isASGrapplingHook(id)) {
            empty = true;
        }
        if (empty && (SmartMovingOptions.hasASGrapplingHook || SmartMovingOptions.hasRopesPlus) && isASRope(id)) {
            empty = false;
        }
        return empty;
    }
    
    private static boolean isFenceBase(final int id) {
        return isBlock(id, aoh.class, Orientation._knownFenceBlocks) || isBlock(id, arn.class, Orientation._knownWallBlocks);
    }
    
    private static boolean isFence(final int id, final int i, final int j_offset, final int k) {
        return isFenceBase(id) || isClosedFenceGate(id, getBlockMetadata(i, j_offset, k));
    }
    
    private boolean isFence(final int i, final int j_offset, final int k) {
        return this.getFenceId(i, j_offset, k) != -1;
    }
    
    private int getFenceId(final int i, final int j_offset, final int k) {
        final int id = getBlockId(i, j_offset, k);
        if (isFenceBase(id) || isClosedFenceGate(id, getBlockMetadata(i, j_offset, k))) {
            return id;
        }
        return -1;
    }
    
    private static boolean isClosedFenceGate(final int id, final int metdata) {
        return isFenceGate(id) && isClosedFenceGate(metdata);
    }
    
    private static boolean isFenceGate(final int id) {
        return isBlock(id, aog.class, Orientation._knownFanceGateBlocks);
    }
    
    private boolean isOpenFenceGate(final int id, final int metdata) {
        return isFenceGate(id) && !isClosedFenceGate(metdata);
    }
    
    private static boolean isClosedFenceGate(final int metdata) {
        return (metdata & 0x4) == 0x0;
    }
    
    private boolean isOpenTrapDoor(final int i, final int j_offset, final int k) {
        return isTrapDoor(i, j_offset, k) && !isClosedTrapDoor(getBlockMetadata(i, j_offset, k));
    }
    
    private static boolean isClosedTrapDoor(final int i, final int j_offset, final int k) {
        return isTrapDoor(i, j_offset, k) && isClosedTrapDoor(getBlockMetadata(i, j_offset, k));
    }
    
    private static boolean isTrapDoor(final int i, final int j_offset, final int k) {
        return isTrapDoor(getBlockId(i, j_offset, k));
    }
    
    public static boolean isTrapDoor(final int id) {
        return isBlock(id, ari.class, Orientation._knownTrapDoorBlocks);
    }
    
    private static boolean isBlock(final int id, final Class<?> type, final aqz[] baseBlocks) {
        if (id < 1) {
            return false;
        }
        if (type != null && baseBlocks.length > 1 && isBlockType(id, type)) {
            return true;
        }
        for (int i = 0; i < baseBlocks.length; ++i) {
            if (baseBlocks[i] != null && id == baseBlocks[i].cF) {
                return true;
            }
        }
        if (type != null && isBlockType(id, type)) {
            return true;
        }
        final aqz block = aqz.s[id];
        if (block == null) {
            return false;
        }
        final Class<?> blockType = block.getClass();
        for (int j = 0; j < baseBlocks.length; ++j) {
            if (baseBlocks[j] != null && baseBlocks[j].getClass().isAssignableFrom(blockType)) {
                return true;
            }
        }
        return false;
    }
    
    private static boolean isBlockType(final int id, final Class<?> type) {
        final aqz block = aqz.s[id];
        return block != null && type.isAssignableFrom(block.getClass());
    }
    
    public static boolean isClosedTrapDoor(final int metaData) {
        return (metaData & 0x4) == 0x0;
    }
    
    private static boolean isDoor(final int id) {
        return id == aqz.aJ.cF || id == aqz.aQ.cF;
    }
    
    private static boolean isDoorTop(final int metaData) {
        return metaData == 8;
    }
    
    private boolean isDoorFrontBlocked(final int i, final int j_offset, final int k) {
        final int metaData = getBlockMetadata(i, j_offset, k);
        switch (metaData) {
            case 8: {
                return this.isDoorFrontBlocked(i, j_offset - 1, k);
            }
            case 1:
            case 4: {
                return this._k < 0;
            }
            case 2:
            case 5: {
                return this._i > 0;
            }
            case 3:
            case 6: {
                return this._k > 0;
            }
            case 0:
            case 7: {
                return this._i < 0;
            }
            default: {
                return true;
            }
        }
    }
    
    private static int getWallBlockId(final int i, final int j_offset, final int k) {
        final int id = getBlockId(i, j_offset, k);
        if (isWallBlock(id, i, j_offset, k)) {
            return id;
        }
        return -1;
    }
    
    private static boolean isWallBlock(final int id, final int i, final int j_offset, final int k) {
        return isBlock(id, aqy.class, Orientation._knownThinWallBlocks) || isFence(id, i, j_offset, k);
    }
    
    private boolean isBaseAccessible(final int j_offset) {
        return this.isBaseAccessible(j_offset, false, false);
    }
    
    private boolean isBaseAccessible(final int j_offset, final boolean bottom, final boolean full) {
        final int id = getBaseBlockId(j_offset);
        boolean accessible = this.isEmpty(Orientation.base_i, j_offset, Orientation.base_k);
        if (SmartMovingOptions.hasRedPowerWire && !accessible && isRedPowerWire(id)) {
            final int coverSides = getRpCoverSides(Orientation.base_i, j_offset, Orientation.base_k);
            accessible = !isRedPowerWireBottom(coverSides);
            final int lowerId = getBaseBlockId(j_offset - 1);
            if (isRedPowerWire(lowerId)) {
                final int lowerCoverSides = getRpCoverSides(Orientation.base_i, j_offset - 1, Orientation.base_k);
                accessible &= !isRedPowerWireTop(lowerCoverSides);
            }
        }
        if (!accessible && isFullEmpty(id)) {
            accessible = true;
        }
        if (!accessible && this.isOpenTrapDoor(Orientation.base_i, j_offset, Orientation.base_k)) {
            accessible = true;
        }
        if (!accessible && bottom && isClosedTrapDoor(Orientation.base_i, j_offset, Orientation.base_k)) {
            accessible = true;
        }
        if (!accessible && !full && isWallBlock(id, Orientation.base_i, j_offset, Orientation.base_k)) {
            accessible = true;
        }
        if (!accessible && !full && (SmartMovingOptions.hasASGrapplingHook || SmartMovingOptions.hasRopesPlus) && isASRope(id)) {
            accessible = true;
        }
        if (!accessible && isDoor(id)) {
            accessible = true;
        }
        return accessible;
    }
    
    private boolean isRemoteAccessible(final int j_offset) {
        boolean accessible = this.isEmpty(Orientation.remote_i, j_offset, Orientation.remote_k);
        if (SmartMovingOptions.hasRedPowerWire && !accessible) {
            final int id = getRemoteBlockId(j_offset);
            if (isRedPowerWire(id)) {
                final int coverSides = getRpCoverSides(Orientation.remote_i, j_offset, Orientation.remote_k);
                accessible = !this.isRedPowerWireAnyFront(coverSides);
                final int baseId = getBaseBlockId(j_offset);
                if (isRedPowerWire(baseId)) {
                    final int baseCoverSides = getRpCoverSides(Orientation.base_i, j_offset, Orientation.base_k);
                    accessible &= !this.isRedPowerWireAnyBack(baseCoverSides);
                }
            }
        }
        if (accessible) {
            final int id = getBaseBlockId(j_offset);
            if (isTrapDoor(id)) {
                accessible = !this.isTrapDoorFront(getBlockMetadata(Orientation.base_i, j_offset, Orientation.base_k));
            }
            if (accessible && isDoor(id)) {
                accessible = !this.isDoorFrontBlocked(Orientation.base_i, j_offset, Orientation.base_k);
            }
            if (this.remoteLadderClimbing(j_offset)) {
                accessible = false;
            }
        }
        if (!accessible && isTrapDoor(Orientation.remote_i, j_offset, Orientation.remote_k)) {
            accessible = isClosedTrapDoor(getRemoteBlockMetadata(j_offset));
        }
        if (!accessible) {
            final int id = getRemoteBlockId(j_offset);
            if (isWallBlock(id, Orientation.remote_i, j_offset, Orientation.remote_k) && !this.headedToFrontWall(Orientation.remote_i, j_offset, Orientation.remote_k, id) && !this.isFence(Orientation.remote_i, j_offset - 1, Orientation.remote_k)) {
                accessible = true;
            }
            final int belowId = getRemoteBlockId(j_offset - 1);
            if (!accessible && isFence(belowId, Orientation.remote_i, j_offset - 1, Orientation.remote_k) && (!this.headedToFrontWall(Orientation.remote_i, j_offset - 1, Orientation.remote_k, belowId) || isWallBlock(getBaseBlockId(j_offset - 1), Orientation.base_i, j_offset - 1, Orientation.base_k)) && (belowId != aqz.cg.cF || this.headedToRemoteFlatWall(belowId, -1))) {
                accessible = true;
            }
            if (!accessible && isDoor(id) && !this.rotate(180).isDoorFrontBlocked(Orientation.remote_i, j_offset, Orientation.remote_k)) {
                accessible = true;
            }
            if ((SmartMovingOptions.hasASGrapplingHook || SmartMovingOptions.hasRopesPlus) && isASRope(id) && !this.rotate(180).isASGrapplingHookFront(getRemoteBlockMetadata(j_offset))) {
                accessible = true;
            }
        }
        return accessible;
    }
    
    private boolean isAccessAccessible(final int j_offset) {
        return !this._isDiagonal || (this.isEmpty(Orientation.remote_i, j_offset, Orientation.base_k) && this.isEmpty(Orientation.base_i, j_offset, Orientation.remote_k));
    }
    
    private boolean isFullExtentAccessible(final int j_offset, final boolean grabRemote) {
        boolean accessible = this.isFullAccessible(j_offset, grabRemote);
        if (SmartMovingOptions.hasRedPowerWire && accessible) {
            final int topId = getRemoteBlockId(j_offset);
            if (isRedPowerWire(topId)) {
                final int coverSides = getRpCoverSides(Orientation.remote_i, j_offset, Orientation.remote_k);
                if (isRedPowerWireBottom(coverSides)) {
                    accessible = false;
                }
            }
            final int bottomId = getRemoteBlockId(j_offset - 1);
            if (isRedPowerWire(bottomId)) {
                final int coverSides2 = getRpCoverSides(Orientation.remote_i, j_offset - 1, Orientation.remote_k);
                if (isRedPowerWireTop(coverSides2)) {
                    accessible = false;
                }
            }
        }
        return accessible;
    }
    
    private boolean isJustLowerHalfExtentAccessible(final int j_offset) {
        final int remoteId = getRemoteBlockId(j_offset);
        final int remoteMetaData = getRemoteBlockMetadata(j_offset);
        boolean accessible = false;
        if (!accessible) {
            accessible = isTopHalfBlock(remoteId, remoteMetaData);
        }
        if (!accessible) {
            accessible = (isStairCompact(remoteId) && this.isTopStairCompactFront(remoteMetaData));
        }
        return accessible;
    }
    
    private boolean isFullAccessible(final int j_offset, final boolean grabRemote) {
        if (grabRemote) {
            return this.isBaseAccessible(j_offset) && this.isRemoteAccessible(j_offset) && this.isAccessAccessible(j_offset);
        }
        return this.isEmpty(Orientation.base_i, j_offset, Orientation.base_k);
    }
    
    private boolean isEmpty(final int i, final int j_offset, final int k) {
        return isFullEmpty(getBlockId(i, j_offset, k)) && !this.isFence(i, j_offset - 1, k);
    }
    
    private boolean isUpperHalfFrontEmpty(final int i, final int j_offset, final int k) {
        final int id = getBlockId(i, j_offset, k);
        boolean empty = isFullEmpty(id);
        if (!empty) {
            final int metadata = getBlockMetadata(i, j_offset, k);
            if (isBottomHalfBlock(id, metadata)) {
                empty = true;
            }
            if (!empty && isStairCompact(id) && this.isBottomStairCompactFront(metadata)) {
                empty = true;
            }
        }
        if (SmartMovingOptions.hasRedPowerWire && !empty && isRedPowerWire(id)) {
            final int coverSides = getRpCoverSides(i, j_offset, k);
            if (!this.isRedPowerWireAnyFront(coverSides)) {
                empty = true;
            }
        }
        if (!empty && isTrapDoor(id)) {
            empty = true;
        }
        if (!empty) {
            final int wallId = getWallBlockId(i, j_offset, k);
            if (wallId > 0 && (!this.headedToFrontWall(i, j_offset, k, wallId) || isWallBlock(getBlockId(i - this._i, j_offset, k - this._k), i - this._i, j_offset, k - this._k))) {
                empty = true;
            }
        }
        if (empty && isBlockIdOfType(id, Orientation._ladderKitLadderTypes) && this.rotate(180).hasLadderOrientation(Orientation.world, i, Orientation.local_offset + j_offset, k)) {
            empty = false;
        }
        return empty;
    }
    
    private static int getRpCoverSides(final int i, final int j_offset, final int k) {
        final asp tileEntity = getBlockTileEntity(i, j_offset, k);
        Class<?> tileEntityClass;
        for (tileEntityClass = tileEntity.getClass(); !tileEntityClass.getSimpleName().equals("TileCovered"); tileEntityClass = tileEntityClass.getSuperclass()) {}
        return (int)Reflect.GetField(tileEntityClass, tileEntity, new Name("CoverSides"));
    }
    
    private static boolean isRedPowerWire(final int id) {
        return hasBlockName(id, "tile.rpwire");
    }
    
    public static int getFiniteLiquidWater(final int id) {
        final String blockName = getBlockName(id);
        if (blockName == null) {
            return 0;
        }
        if (blockName.equals("tile.nocean")) {
            return 2;
        }
        if (blockName.equals("tile.nwater_still")) {
            return 1;
        }
        return 0;
    }
    
    private static boolean isSolid(final akc material) {
        return material.a() && material.c();
    }
    
    private static int getBlockId(final int i, final int j_offset, final int k) {
        return Orientation.world.a(i, Orientation.local_offset + j_offset, k);
    }
    
    private static int getBlockMetadata(final int i, final int j_offset, final int k) {
        return Orientation.world.h(i, Orientation.local_offset + j_offset, k);
    }
    
    private static asp getBlockTileEntity(final int i, final int j_offset, final int k) {
        return Orientation.world.r(i, Orientation.local_offset + j_offset, k);
    }
    
    private static int getBaseBlockId(final int j_offset) {
        return Orientation.world.a(Orientation.base_i, Orientation.local_offset + j_offset, Orientation.base_k);
    }
    
    private static int getBaseBlockMetadata(final int j_offset) {
        return Orientation.world.h(Orientation.base_i, Orientation.local_offset + j_offset, Orientation.base_k);
    }
    
    private static boolean isBaseBlockOfType(final int j_offset, final Class<?>... types) {
        return isBlockIdOfType(getBaseBlockId(j_offset), types);
    }
    
    private static boolean isRemoteBlockOfType(final int j_offset, final Class<?>... types) {
        return isBlockIdOfType(getRemoteBlockId(j_offset), types);
    }
    
    private static boolean isBlockIdOfType(final int blockId, final Class<?>... types) {
        if (types == null) {
            return false;
        }
        if (blockId <= 0) {
            return false;
        }
        final aqz block = aqz.s[blockId];
        if (block == null) {
            return false;
        }
        final Class<?> blockType = block.getClass();
        for (final Class<?> type : types) {
            if (type.isAssignableFrom(blockType)) {
                return true;
            }
        }
        return false;
    }
    
    private static int getRemoteBlockId(final int j_offset) {
        return Orientation.world.a(Orientation.remote_i, Orientation.local_offset + j_offset, Orientation.remote_k);
    }
    
    private static int getRemoteBlockMetadata(final int j_offset) {
        return Orientation.world.h(Orientation.remote_i, Orientation.local_offset + j_offset, Orientation.remote_k);
    }
    
    private static boolean hasBlockName(final int blockId, final String name) {
        final String blockName = getBlockName(blockId);
        return blockName != null && blockName.equals(name);
    }
    
    private static String getBlockName(final int blockId) {
        if (blockId <= 0) {
            return null;
        }
        return aqz.s[blockId].a();
    }
    
    private void initialize(final abw w, final int i, final double id, final double jhd, final int k, final double kd) {
        Orientation.world = w;
        Orientation.base_i = i;
        Orientation.base_id = id;
        Orientation.base_jhd = jhd;
        Orientation.base_k = k;
        Orientation.base_kd = kd;
        Orientation.remote_i = i + this._i;
        Orientation.remote_k = k + this._k;
    }
    
    private static void initializeOffset(final double offset_halfs, final boolean isClimbCrawling, final boolean isCrawlClimbing, final boolean isCrawling) {
        Orientation.crawl = (isClimbCrawling || isCrawlClimbing || isCrawling);
        final double offset_jhd = Orientation.base_jhd + offset_halfs;
        final int offset_jh = ls.c(offset_jhd);
        Orientation.jh_offset = offset_jhd - offset_jh;
        Orientation.all_j = offset_jh / 2;
        Orientation.all_offset = offset_jh % 2;
    }
    
    private static void initializeLocal(final int localOffset) {
        Orientation.local_halfOffset = localOffset + Orientation.all_offset;
        Orientation.local_half = Math.abs(Orientation.local_halfOffset) % 2;
        Orientation.local_offset = Orientation.all_j + (Orientation.local_halfOffset - Orientation.local_half) / 2;
    }
    
    @Override
    public String toString() {
        if (this == Orientation.ZZ) {
            return "ZZ";
        }
        if (this == Orientation.NZ) {
            return "NZ";
        }
        if (this == Orientation.PZ) {
            return "PZ";
        }
        if (this == Orientation.ZP) {
            return "ZP";
        }
        if (this == Orientation.ZN) {
            return "ZN";
        }
        if (this == Orientation.PN) {
            return "PN";
        }
        if (this == Orientation.PP) {
            return "PP";
        }
        if (this == Orientation.NN) {
            return "NN";
        }
        if (this == Orientation.NP) {
            return "NP";
        }
        return "UNKNOWN";
    }
    
    static {
        ZZ = new Orientation(0, 0);
        PZ = new Orientation(1, 0);
        ZP = new Orientation(0, 1);
        NZ = new Orientation(-1, 0);
        ZN = new Orientation(0, -1);
        PP = new Orientation(1, 1);
        NN = new Orientation(-1, -1);
        PN = new Orientation(1, -1);
        NP = new Orientation(-1, 1);
        (Orthogonals = new HashSet<Orientation>()).add(Orientation.PZ);
        Orientation.Orthogonals.add(Orientation.ZP);
        Orientation.Orthogonals.add(Orientation.NZ);
        Orientation.Orthogonals.add(Orientation.ZN);
        Orientation._getClimbingOrientationsHashSet = null;
        _handClimbingHoldGap = Math.min(0.25f, 0.06f * Math.max(Orientation.Config._freeClimbingUpSpeedFactor.value, Orientation.Config._freeClimbingDownSpeedFactor.value));
        Orientation._climbGapTemp = new ClimbGap();
        Orientation._climbGapOuterTemp = new ClimbGap();
        _isLadder = Reflect.GetMethod(aqz.class, new Name("isLadder"), false, abw.class, Integer.TYPE, Integer.TYPE, Integer.TYPE, of.class);
        final Class<?> modFenceBlock = Reflect.LoadClass(aqz.class, Install.ModBlockFence, false);
        _canConnectFenceTo = ((modFenceBlock != null) ? Reflect.GetMethod(modFenceBlock, new Name("canConnectFenceTo"), false, acf.class, Integer.TYPE, Integer.TYPE, Integer.TYPE) : null);
        _knownFanceGateBlocks = new aqz[] { aqz.bA };
        _knownFenceBlocks = new aqz[] { aqz.be, aqz.bG };
        _knownWallBlocks = new aqz[] { aqz.cg };
        _knownHalfBlocks = new aqz[] { (aqz)aqz.ap, (aqz)aqz.ao, (aqz)aqz.bT, (aqz)aqz.bS };
        _knownCompactStairBlocks = new aqz[] { aqz.aM, aqz.ay, aqz.bB, aqz.bH, aqz.bV, aqz.bC, aqz.cc, aqz.cd, aqz.cb, aqz.cx };
        _knownTrapDoorBlocks = new aqz[] { aqz.bp };
        _knownThinWallBlocks = new aqz[] { aqz.bu, aqz.bv };
        final Class<?> blockRopeLadder = Reflect.LoadClass(aqz.class, Install.BlockRopeLadder, false);
        final Class<?> blockSturdyLadder = Reflect.LoadClass(aqz.class, Install.BlockSturdyLadder, false);
        if (blockRopeLadder != null) {
            if (blockSturdyLadder != null) {
                _ladderKitLadderTypes = new Class[] { blockRopeLadder, blockSturdyLadder };
            }
            else {
                _ladderKitLadderTypes = new Class[] { blockRopeLadder };
            }
        }
        else if (blockSturdyLadder != null) {
            _ladderKitLadderTypes = new Class[] { blockSturdyLadder };
        }
        else {
            _ladderKitLadderTypes = null;
        }
    }
}
