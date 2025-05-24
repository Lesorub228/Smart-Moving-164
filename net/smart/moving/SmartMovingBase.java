// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.util.List;
import java.util.Iterator;
import java.util.HashSet;
import net.smart.moving.config.SmartMovingOptions;
import java.util.Set;
import java.util.Dictionary;

public abstract class SmartMovingBase extends SmartMovingContext
{
    public final uf sp;
    public final bex esp;
    public final IEntityPlayerSP isp;
    public static final int CollidedPositiveX = 1;
    public static final int CollidedNegativeX = 2;
    public static final int CollidedPositiveY = 4;
    public static final int CollidedNegativeY = 8;
    public static final int CollidedPositiveZ = 16;
    public static final int CollidedNegativeZ = 32;
    
    public SmartMovingBase(final uf sp, final IEntityPlayerSP isp) {
        this.sp = sp;
        this.isp = isp;
        if (sp instanceof bex) {
            this.esp = (bex)sp;
            if (atv.w().h == null) {
                SmartMovingBase.Options.resetForNewGame();
                SmartMovingBase.Config = SmartMovingBase.Options;
            }
        }
        else {
            this.esp = null;
        }
    }
    
    protected void moveFlying(final float moveUpward, final float moveStrafing, final float moveForward, final float speedFactor, final boolean treeDimensional) {
        float diffMotionXStrafing = 0.0f;
        float diffMotionXForward = 0.0f;
        float diffMotionZStrafing = 0.0f;
        float diffMotionZForward = 0.0f;
        float total = ls.c(moveStrafing * moveStrafing + moveForward * moveForward);
        if (total >= 0.01f) {
            if (total < 1.0f) {
                total = 1.0f;
            }
            final float moveStrafingFactor = moveStrafing / total;
            final float moveForwardFactor = moveForward / total;
            final float sin = ls.a(this.sp.A * 3.141593f / 180.0f);
            final float cos = ls.b(this.sp.A * 3.141593f / 180.0f);
            diffMotionXStrafing = moveStrafingFactor * cos;
            diffMotionXForward = -moveForwardFactor * sin;
            diffMotionZStrafing = moveStrafingFactor * sin;
            diffMotionZForward = moveForwardFactor * cos;
        }
        final float rotation = treeDimensional ? (this.sp.B / 57.295776f) : 0.0f;
        final float divingHorizontalFactor = ls.b(rotation);
        final float divingVerticalFactor = -ls.a(rotation) * Math.signum(moveForward);
        final float diffMotionX = diffMotionXForward * divingHorizontalFactor + diffMotionXStrafing;
        final float diffMotionY = ls.c(diffMotionXForward * diffMotionXForward + diffMotionZForward * diffMotionZForward) * divingVerticalFactor + moveUpward;
        final float diffMotionZ = diffMotionZForward * divingHorizontalFactor + diffMotionZStrafing;
        final float total2 = ls.c(ls.c(diffMotionX * diffMotionX + diffMotionZ * diffMotionZ) + diffMotionY * diffMotionY);
        if (total2 > 0.01f) {
            final float factor = speedFactor / total2;
            final uf sp = this.sp;
            sp.x += diffMotionX * factor;
            final uf sp2 = this.sp;
            sp2.y += diffMotionY * factor;
            final uf sp3 = this.sp;
            sp3.z += diffMotionZ * factor;
        }
    }
    
    protected int supportsCeilingClimbing(final int i, final int j, final int k) {
        final int blockId = this.sp.q.a(i, j, k);
        if (blockId <= 0) {
            return -1;
        }
        final Dictionary<Object, Set<Integer>> configuration = SmartMovingBase.Config._ceilingClimbConfigurationObject.value;
        Set<Integer> metaDatas = configuration.get(blockId);
        if (metaDatas == null && blockId >= 0) {
            final aqz block = aqz.s[blockId];
            if (block != null) {
                final String blockName = block.a();
                if (blockName != null && !blockName.isEmpty()) {
                    metaDatas = configuration.get(blockName);
                    if (metaDatas == null && blockName.startsWith("tile.") && blockName.length() > 5) {
                        metaDatas = configuration.get(blockName.substring(5));
                    }
                }
            }
        }
        if (metaDatas == null) {
            return -1;
        }
        if (metaDatas.isEmpty()) {
            return blockId;
        }
        if (metaDatas.contains(this.sp.q.h(i, j, k))) {
            return blockId;
        }
        return -1;
    }
    
    protected boolean isLava(final int blockId) {
        if (blockId == aqz.I.cF || blockId == aqz.H.cF) {
            return true;
        }
        final aqz block = (blockId > 0) ? aqz.s[blockId] : null;
        return block != null && block.cU == akc.i;
    }
    
    protected float getLiquidBorder(final int i, final int j, final int k) {
        final int blockId = this.sp.q.a(i, j, k);
        if (blockId == aqz.G.cF || blockId == aqz.F.cF) {
            return this.getNormalWaterBorder(i, j, k);
        }
        final float finiteLiquidBorder;
        if (SmartMovingOptions.hasFiniteLiquid && (finiteLiquidBorder = this.getFiniteLiquidWaterBorder(i, j, k, blockId)) > 0.0f) {
            return finiteLiquidBorder;
        }
        if (blockId == aqz.I.cF || blockId == aqz.H.cF) {
            return SmartMovingBase.Config._lavaLikeWater.value ? this.getNormalWaterBorder(i, j, k) : 0.0f;
        }
        final akc material = this.sp.q.g(i, j, k);
        if (material == null || material == akc.i) {
            return SmartMovingBase.Config._lavaLikeWater.value ? 1.0f : 0.0f;
        }
        if (material == akc.h) {
            return this.getNormalWaterBorder(i, j, k);
        }
        if (material.d()) {
            return 1.0f;
        }
        return 0.0f;
    }
    
    protected float getNormalWaterBorder(final int i, final int j, final int k) {
        final int blockMetaData = this.sp.q.h(i, j, k);
        if (blockMetaData >= 8) {
            return 1.0f;
        }
        if (blockMetaData != 0) {
            return (8 - blockMetaData) / 8.0f;
        }
        if (this.sp.q.c(i, j + 1, k)) {
            return 0.8875f;
        }
        return 1.0f;
    }
    
    protected float getFiniteLiquidWaterBorder(final int i, final int j, final int k, final int blockId) {
        final int type;
        if ((type = Orientation.getFiniteLiquidWater(blockId)) > 0) {
            if (type == 2) {
                return 1.0f;
            }
            if (type == 1) {
                final int aboveBlockId = this.sp.q.a(i, j + 1, k);
                if (Orientation.getFiniteLiquidWater(aboveBlockId) > 0) {
                    return 1.0f;
                }
                return (this.sp.q.h(i, j, k) + 1) / 16.0f;
            }
        }
        return 0.0f;
    }
    
    public boolean isFacedToLadder(final boolean isSmall) {
        return this.getOnLadder(1, true, isSmall) > 0;
    }
    
    public boolean isFacedToSolidVine(final boolean isSmall) {
        return this.getOnVine(1, true, isSmall) > 0;
    }
    
    public boolean isOnLadderOrVine(final boolean isSmall) {
        return this.getOnLadderOrVine(1, false, isSmall) > 0;
    }
    
    public boolean isOnVine(final boolean isSmall) {
        return this.getOnLadderOrVine(1, false, false, true, isSmall) > 0;
    }
    
    public boolean isOnLadder(final boolean isSmall) {
        return this.getOnLadderOrVine(1, false, true, false, isSmall) > 0;
    }
    
    protected int getOnLadder(final int maxResult, final boolean faceOnly, final boolean isSmall) {
        return this.getOnLadderOrVine(maxResult, faceOnly, true, false, isSmall);
    }
    
    protected int getOnVine(final int maxResult, final boolean faceOnly, final boolean isSmall) {
        return this.getOnLadderOrVine(maxResult, faceOnly, false, true, isSmall);
    }
    
    protected int getOnLadderOrVine(final int maxResult, final boolean faceOnly, final boolean isSmall) {
        return this.getOnLadderOrVine(maxResult, faceOnly, true, true, isSmall);
    }
    
    protected int getOnLadderOrVine(final int maxResult, final boolean faceOnly, final boolean ladder, final boolean vine, final boolean isSmall) {
        final int i = ls.c(this.sp.u);
        int minj = ls.c(this.sp.E.b);
        final int k = ls.c(this.sp.w);
        if (!SmartMovingBase.Config.isStandardBaseClimb()) {
            if (isSmall) {
                --minj;
            }
            HashSet<Orientation> facedOnlyTo = null;
            if (faceOnly) {
                facedOnlyTo = Orientation.getClimbingOrientations(this.sp, true, false);
            }
            int result = 0;
            for (int maxj = ls.c(this.sp.E.b + Math.ceil(this.sp.E.e - this.sp.E.b)) - 1, j = minj; j <= maxj; ++j) {
                final int blockId = this.sp.q.a(i, j, k);
                if (ladder) {
                    final boolean localLadder = Orientation.isKnownLadder(blockId);
                    Orientation localLadderOrientation = null;
                    if (localLadder) {
                        localLadderOrientation = Orientation.getKnownLadderOrientation(this.sp.q, i, j, k);
                        if (facedOnlyTo == null || facedOnlyTo.contains(localLadderOrientation)) {
                            ++result;
                        }
                    }
                    for (final Orientation direction : (facedOnlyTo != null) ? facedOnlyTo : Orientation.Orthogonals) {
                        if (result >= maxResult) {
                            return result;
                        }
                        if (direction == localLadderOrientation) {
                            continue;
                        }
                        final int remoteBlockId = this.sp.q.a(i + direction._i, j, k + direction._k);
                        if (!Orientation.isKnownLadder(remoteBlockId)) {
                            continue;
                        }
                        final Orientation remoteLadderOrientation = Orientation.getKnownLadderOrientation(this.sp.q, i + direction._i, j, k + direction._k);
                        if (remoteLadderOrientation.rotate(180) != direction) {
                            continue;
                        }
                        ++result;
                    }
                }
                if (result >= maxResult) {
                    return result;
                }
                if (vine && Orientation.isVine(blockId)) {
                    if (facedOnlyTo == null) {
                        ++result;
                    }
                    else {
                        for (final Orientation climbOrientation : facedOnlyTo) {
                            if (climbOrientation.hasVineOrientation(this.sp.q, i, j, k) && climbOrientation.isRemoteSolid(this.sp.q, i, j, k)) {
                                ++result;
                                break;
                            }
                        }
                    }
                }
                if (result >= maxResult) {
                    return result;
                }
            }
            return result;
        }
        final int blockId2 = this.sp.q.a(i, minj, k);
        if (!ladder) {
            return (vine && blockId2 == aqz.bz.cF && Orientation.isClimbable(this.sp.q, i, minj, k)) ? 1 : 0;
        }
        if (vine) {
            return Orientation.isClimbable(this.sp.q, i, minj, k) ? 1 : 0;
        }
        return (blockId2 != aqz.bz.cF && Orientation.isClimbable(this.sp.q, i, minj, k)) ? 1 : 0;
    }
    
    public boolean climbingUpIsBlockedByLadder() {
        if (this.sp.G && this.sp.H && !this.sp.F && this.esp.c.b > 0.0f) {
            final Orientation orientation = Orientation.getOrientation(this.sp, 20.0f, true, false);
            if (orientation != null) {
                final int i = ls.c(this.sp.u);
                final int j = ls.c(this.sp.E.e);
                final int k = ls.c(this.sp.w);
                if (Orientation.isLadder(this.sp.q.a(i, j, k))) {
                    return Orientation.getKnownLadderOrientation(this.sp.q, i, j, k) == orientation;
                }
            }
        }
        return false;
    }
    
    public boolean climbingUpIsBlockedByTrapDoor() {
        if (this.sp.G && this.sp.H && !this.sp.F && this.esp.c.b > 0.0f) {
            final Orientation orientation = Orientation.getOrientation(this.sp, 20.0f, true, false);
            if (orientation != null) {
                final int i = ls.c(this.sp.u);
                final int j = ls.c(this.sp.E.e);
                final int k = ls.c(this.sp.w);
                if (Orientation.isTrapDoor(this.sp.q.a(i, j, k))) {
                    return Orientation.getOpenTrapDoorOrientation(this.sp.q, i, j, k) == orientation;
                }
            }
        }
        return false;
    }
    
    public boolean climbingUpIsBlockedByCobbleStoneWall() {
        if (this.sp.G && this.sp.H && !this.sp.F && this.esp.c.b > 0.0f) {
            final Orientation orientation = Orientation.getOrientation(this.sp, 20.0f, true, false);
            if (orientation != null) {
                final int i = ls.c(this.sp.u);
                final int j = ls.c(this.sp.E.e);
                final int k = ls.c(this.sp.w);
                if (this.sp.q.a(i, j, k) == aqz.cg.cF) {
                    return !((arn)aqz.cg).d((acf)this.sp.q, i - orientation._i, j, k - orientation._k);
                }
            }
        }
        return false;
    }
    
    private List<?> getPlayerSolidBetween(final double yMin, final double yMax, final double horizontalTolerance) {
        final double minY = this.sp.E.b;
        final double maxY = this.sp.E.e;
        this.sp.E.b = yMin;
        this.sp.E.e = yMax;
        final List<?> result = this.sp.q.a((nn)this.sp, (horizontalTolerance == 0.0) ? this.sp.E : this.sp.E.e(-horizontalTolerance, 0.0, -horizontalTolerance));
        this.sp.E.b = minY;
        this.sp.E.e = maxY;
        return result;
    }
    
    protected boolean isPlayerInSolidBetween(final double yMin, final double yMax) {
        return this.getPlayerSolidBetween(yMin, yMax, 0.0).size() > 0;
    }
    
    protected double getMaxPlayerSolidBetween(final double yMin, final double yMax, final double horizontalTolerance) {
        final List<?> solids = this.getPlayerSolidBetween(yMin, yMax, horizontalTolerance);
        double result = yMin;
        for (int i = 0; i < solids.size(); ++i) {
            final asx box = (asx)solids.get(i);
            if (this.isCollided(box, yMin, yMax, horizontalTolerance)) {
                result = Math.max(result, box.e);
            }
        }
        return Math.min(result, yMax);
    }
    
    protected double getMinPlayerSolidBetween(final double yMin, final double yMax, final double horizontalTolerance) {
        final List<?> solids = this.getPlayerSolidBetween(yMin, yMax, horizontalTolerance);
        double result = yMax;
        for (int i = 0; i < solids.size(); ++i) {
            final asx box = (asx)solids.get(i);
            if (this.isCollided(box, yMin, yMax, horizontalTolerance)) {
                result = Math.min(result, box.b);
            }
        }
        return Math.max(result, yMin);
    }
    
    protected boolean isInLiquid() {
        return this.getMaxPlayerLiquidBetween(this.sp.E.b, this.sp.E.e) != this.sp.E.b || this.getMinPlayerLiquidBetween(this.sp.E.b, this.sp.E.e) != this.sp.E.e;
    }
    
    protected double getMaxPlayerLiquidBetween(final double yMin, final double yMax) {
        final int i = ls.c(this.sp.u);
        final int jMin = ls.c(yMin);
        final int jMax = ls.c(yMax);
        final int k = ls.c(this.sp.w);
        for (int j = jMax; j >= jMin; --j) {
            final float swimWaterBorder = this.getLiquidBorder(i, j, k);
            if (swimWaterBorder > 0.0f) {
                return j + swimWaterBorder;
            }
        }
        return yMin;
    }
    
    protected double getMinPlayerLiquidBetween(final double yMin, final double yMax) {
        final int i = ls.c(this.sp.u);
        final int jMin = ls.c(yMin);
        final int jMax = ls.c(yMax);
        final int k = ls.c(this.sp.w);
        for (int j = jMin; j <= jMax; ++j) {
            final float swimWaterBorder = this.getLiquidBorder(i, j, k);
            if (swimWaterBorder > 0.0f) {
                if (j > yMin) {
                    return j;
                }
                if (j + swimWaterBorder > yMin) {
                    return yMin;
                }
            }
        }
        return yMax;
    }
    
    public boolean isCollided(final asx box, final double yMin, final double yMax, final double horizontalTolerance) {
        return box.d >= this.sp.E.a - horizontalTolerance && box.a <= this.sp.E.d + horizontalTolerance && box.e >= yMin && box.b <= yMax && box.f >= this.sp.E.c - horizontalTolerance && box.c <= this.sp.E.f + horizontalTolerance;
    }
    
    private boolean isBlockTranslucent(final int i, final int j, final int k) {
        return this.sp.q.u(i, j, k);
    }
    
    public boolean pushOutOfBlocks(final double d, final double d1, final double d2, final boolean top) {
        final int i = ls.c(d);
        final int j = ls.c(d1);
        final int k = ls.c(d2);
        final double d3 = d - i;
        final double d4 = d2 - k;
        if (this.isBlockTranslucent(i, j, k) || (top && this.isBlockTranslucent(i, j + 1, k))) {
            final boolean flag = !this.isBlockTranslucent(i - 1, j, k) && (!top || !this.isBlockTranslucent(i - 1, j + 1, k));
            final boolean flag2 = !this.isBlockTranslucent(i + 1, j, k) && (!top || !this.isBlockTranslucent(i + 1, j + 1, k));
            final boolean flag3 = !this.isBlockTranslucent(i, j, k - 1) && (!top || !this.isBlockTranslucent(i, j + 1, k - 1));
            final boolean flag4 = !this.isBlockTranslucent(i, j, k + 1) && (!top || !this.isBlockTranslucent(i, j + 1, k + 1));
            byte byte0 = -1;
            double d5 = 9999.0;
            if (flag && d3 < d5) {
                d5 = d3;
                byte0 = 0;
            }
            if (flag2 && 1.0 - d3 < d5) {
                d5 = 1.0 - d3;
                byte0 = 1;
            }
            if (flag3 && d4 < d5) {
                d5 = d4;
                byte0 = 4;
            }
            if (flag4 && 1.0 - d4 < d5) {
                byte0 = 5;
            }
            final float f = 0.1f;
            if (byte0 == 0) {
                this.sp.x = -f;
            }
            if (byte0 == 1) {
                this.sp.x = f;
            }
            if (byte0 == 4) {
                this.sp.z = -f;
            }
            if (byte0 == 5) {
                this.sp.z = f;
            }
        }
        return false;
    }
    
    public boolean isInsideOfMaterial(final akc material) {
        if (!SmartMovingOptions.hasFiniteLiquid || material != akc.h) {
            return this.isp.localIsInsideOfMaterial(material);
        }
        final double d = this.sp.v + this.sp.f();
        final int i = ls.c(this.sp.u);
        final int j = ls.d((float)ls.c(d));
        final int k = ls.c(this.sp.w);
        final int l = this.sp.q.a(i, j, k);
        final float border;
        if (l != 0 && (border = this.getFiniteLiquidWaterBorder(i, j, k, l)) > 0.0f) {
            final float f = 1.0f - border - 0.1111111f;
            final float f2 = j + 1 - f;
            return d < f2;
        }
        return false;
    }
    
    public int calculateSeparateCollisions(double par1, double par3, double par5) {
        float ySize = this.sp.X;
        final double posX = this.sp.u;
        final double posZ = this.sp.w;
        boolean isInWeb = this.isp.getIsInWebField();
        double motionX = this.sp.x;
        double motionY = this.sp.y;
        double motionZ = this.sp.z;
        final asx boundingBox = this.sp.E.c();
        final boolean onGround = this.sp.F;
        final abw worldObj = this.sp.q;
        final nn _this = (nn)this.sp;
        final boolean field_9293_aM = this.sp.L;
        final float stepHeight = this.sp.Y;
        ySize *= 0.4f;
        final double d = posX;
        final double d2 = posZ;
        if (isInWeb) {
            isInWeb = false;
            par1 *= 0.25;
            par3 *= 0.05000000074505806;
            par5 *= 0.25;
            motionX = 0.0;
            motionY = 0.0;
            motionZ = 0.0;
        }
        double d3 = par1;
        final double d4 = par3;
        double d5 = par5;
        final asx axisalignedbb = boundingBox.c();
        final boolean flag = onGround && this.isSneaking();
        if (flag) {
            final double d6 = 0.05;
            while (par1 != 0.0 && worldObj.a(_this, boundingBox.c(par1, -1.0, 0.0)).size() == 0) {
                if (par1 < d6 && par1 >= -d6) {
                    par1 = 0.0;
                }
                else if (par1 > 0.0) {
                    par1 -= d6;
                }
                else {
                    par1 += d6;
                }
                d3 = par1;
            }
            while (par5 != 0.0 && worldObj.a(_this, boundingBox.c(0.0, -1.0, par5)).size() == 0) {
                if (par5 < d6 && par5 >= -d6) {
                    par5 = 0.0;
                }
                else if (par5 > 0.0) {
                    par5 -= d6;
                }
                else {
                    par5 += d6;
                }
                d5 = par5;
            }
            while (par1 != 0.0 && par5 != 0.0 && worldObj.a(_this, boundingBox.c(par1, -1.0, par5)).size() == 0) {
                if (par1 < d6 && par1 >= -d6) {
                    par1 = 0.0;
                }
                else if (par1 > 0.0) {
                    par1 -= d6;
                }
                else {
                    par1 += d6;
                }
                if (par5 < d6 && par5 >= -d6) {
                    par5 = 0.0;
                }
                else if (par5 > 0.0) {
                    par5 -= d6;
                }
                else {
                    par5 += d6;
                }
                d3 = par1;
                d5 = par5;
            }
        }
        final List list = worldObj.a(_this, boundingBox.a(par1, par3, par5));
        for (int i = 0; i < list.size(); ++i) {
            par3 = list.get(i).b(boundingBox, par3);
        }
        boundingBox.d(0.0, par3, 0.0);
        if (!field_9293_aM && d4 != par3) {
            par3 = (par1 = (par5 = 0.0));
        }
        final boolean flag2 = onGround || (d4 != par3 && d4 < 0.0);
        for (int j = 0; j < list.size(); ++j) {
            par1 = list.get(j).a(boundingBox, par1);
        }
        boundingBox.d(par1, 0.0, 0.0);
        if (!field_9293_aM && d3 != par1) {
            par3 = (par1 = (par5 = 0.0));
        }
        for (int k = 0; k < list.size(); ++k) {
            par5 = list.get(k).c(boundingBox, par5);
        }
        boundingBox.d(0.0, 0.0, par5);
        if (!field_9293_aM && d5 != par5) {
            par3 = (par1 = (par5 = 0.0));
        }
        if (stepHeight > 0.0f && flag2 && (flag || ySize < 0.05f) && (d3 != par1 || d5 != par5)) {
            final double d7 = par1;
            final double d8 = par3;
            final double d9 = par5;
            par1 = d3;
            par3 = stepHeight;
            par5 = d5;
            final asx axisalignedbb2 = boundingBox.c();
            boundingBox.d(axisalignedbb);
            final List list2 = worldObj.a(_this, boundingBox.a(par1, par3, par5));
            for (int j2 = 0; j2 < list2.size(); ++j2) {
                par3 = list2.get(j2).b(boundingBox, par3);
            }
            boundingBox.d(0.0, par3, 0.0);
            if (!field_9293_aM && d4 != par3) {
                par3 = (par1 = (par5 = 0.0));
            }
            for (int k2 = 0; k2 < list2.size(); ++k2) {
                par1 = list2.get(k2).a(boundingBox, par1);
            }
            boundingBox.d(par1, 0.0, 0.0);
            if (!field_9293_aM && d3 != par1) {
                par3 = (par1 = (par5 = 0.0));
            }
            for (int l2 = 0; l2 < list2.size(); ++l2) {
                par5 = list2.get(l2).c(boundingBox, par5);
            }
            boundingBox.d(0.0, 0.0, par5);
            if (!field_9293_aM && d5 != par5) {
                par3 = (par1 = (par5 = 0.0));
            }
            if (!field_9293_aM && d4 != par3) {
                par3 = (par1 = (par5 = 0.0));
            }
            else {
                par3 = -stepHeight;
                for (int i2 = 0; i2 < list2.size(); ++i2) {
                    par3 = list2.get(i2).b(boundingBox, par3);
                }
                boundingBox.d(0.0, par3, 0.0);
            }
            if (d7 * d7 + d9 * d9 >= par1 * par1 + par5 * par5) {
                par1 = d7;
                par3 = d8;
                par5 = d9;
                boundingBox.d(axisalignedbb2);
            }
            else {
                final double d10 = boundingBox.b - (int)boundingBox.b;
                if (d10 > 0.0) {
                    ySize += (float)(d10 + 0.01);
                }
            }
        }
        final boolean isCollidedPositiveX = d3 > par1;
        final boolean isCollidedNegativeX = d3 < par1;
        final boolean isCollidedPositiveY = d4 > par3;
        final boolean isCollidedNegativeY = d4 < par3;
        final boolean isCollidedPositiveZ = d5 > par5;
        final boolean isCollidedNegativeZ = d5 < par5;
        int result = 0;
        if (isCollidedPositiveX) {
            ++result;
        }
        if (isCollidedNegativeX) {
            result += 2;
        }
        if (isCollidedPositiveY) {
            result += 4;
        }
        if (isCollidedNegativeY) {
            result += 8;
        }
        if (isCollidedPositiveZ) {
            result += 16;
        }
        if (isCollidedNegativeZ) {
            result += 32;
        }
        return result;
    }
    
    public boolean isSneaking() {
        return this.sp.ah();
    }
    
    public void correctOnUpdate(final boolean isSmall, final boolean reverseMaterialAcceleration) {
        final double d = this.sp.u - this.sp.r;
        final double d2 = this.sp.w - this.sp.t;
        final float f = ls.a(d * d + d2 * d2);
        if (f < 0.05f && f > 0.02 && isSmall) {
            float f2 = this.sp.aN;
            float f3 = 0.0f;
            f3 = f * 3.0f;
            f2 = (float)Math.atan2(d2, d) * 180.0f / 3.141593f - 90.0f;
            if (this.sp.aE > 0.0f) {
                f2 = this.sp.A;
            }
            float f4;
            for (f4 = f2 - this.sp.aN; f4 < -180.0f; f4 += 360.0f) {}
            while (f4 >= 180.0f) {
                f4 -= 360.0f;
            }
            final float x = this.sp.aN + f4 * 0.3f;
            float f5;
            for (f5 = this.sp.A - x; f5 < -180.0f; f5 += 360.0f) {}
            while (f5 >= 180.0f) {
                f5 -= 360.0f;
            }
            final boolean flag = f5 < -90.0f || f5 >= 90.0f;
            if (f5 < -75.0f) {
                f5 = -75.0f;
            }
            if (f5 >= 75.0f) {
                f5 = 75.0f;
            }
            this.sp.aN = this.sp.A - f5;
            if (f5 * f5 > 2500.0f) {
                final uf sp = this.sp;
                sp.aN += f5 * 0.2f;
            }
            if (flag) {
                f3 *= -1.0f;
            }
            while (this.sp.aN - this.sp.aO < -180.0f) {
                final uf sp2 = this.sp;
                sp2.aO -= 360.0f;
            }
            while (this.sp.aN - this.sp.aO >= 180.0f) {
                final uf sp3 = this.sp;
                sp3.aO += 360.0f;
            }
        }
        if (reverseMaterialAcceleration) {
            this.reverseHandleMaterialAcceleration();
        }
    }
    
    protected double getGapUnderneight() {
        return this.sp.E.b - this.getMaxPlayerSolidBetween(this.sp.E.b - 1.1, this.sp.E.b, 0.0);
    }
    
    protected double getGapOverneight() {
        return this.getMinPlayerSolidBetween(this.sp.E.e, this.sp.E.e + 1.1, 0.0) - this.sp.E.e;
    }
    
    public double getOverGroundHeight(final double maximum) {
        if (this.esp != null) {
            return this.sp.E.b - this.getMaxPlayerSolidBetween(this.sp.E.b - maximum, this.sp.E.b, 0.0);
        }
        return this.sp.E.b + 1.0 - this.getMaxPlayerSolidBetween(this.sp.E.b - maximum + 1.0, this.sp.E.b + 1.0, 0.1);
    }
    
    public int getOverGroundBlockId(final double distance) {
        final int x = ls.c(this.sp.u);
        int y = ls.c(this.sp.E.b);
        final int z = ls.c(this.sp.w);
        int minY = y - (int)Math.ceil(distance);
        if (this.esp == null) {
            ++y;
            ++minY;
        }
        while (y >= minY) {
            final int id = this.sp.q.a(x, y, z);
            if (id > 0) {
                return id;
            }
            --y;
        }
        return -1;
    }
    
    public void reverseHandleMaterialAcceleration() {
        final asx axisalignedbb = this.sp.E.b(0.0, -0.4000000059604645, 0.0).e(0.001, 0.001, 0.001);
        final akc material = akc.h;
        final nn entity = (nn)this.sp;
        final int i = ls.c(axisalignedbb.a);
        final int j = ls.c(axisalignedbb.d + 1.0);
        final int k = ls.c(axisalignedbb.b);
        final int l = ls.c(axisalignedbb.e + 1.0);
        final int i2 = ls.c(axisalignedbb.c);
        final int j2 = ls.c(axisalignedbb.f + 1.0);
        if (!entity.q.e(i, k, i2, j, l, j2)) {
            return;
        }
        atc vec3d = atc.a(0.0, 0.0, 0.0);
        for (int k2 = i; k2 < j; ++k2) {
            for (int l2 = k; l2 < l; ++l2) {
                for (int i3 = i2; i3 < j2; ++i3) {
                    final aqz block = aqz.s[entity.q.a(k2, l2, i3)];
                    if (block != null) {
                        if (block.cU == material) {
                            final double d1 = l2 + 1 - apc.d(entity.q.h(k2, l2, i3));
                            if (l >= d1) {
                                block.a(entity.q, k2, l2, i3, entity, vec3d);
                            }
                        }
                    }
                }
            }
        }
        if (vec3d.b() > 0.0) {
            vec3d = vec3d.a();
            final double d2 = -0.014;
            final nn nn = entity;
            nn.x += vec3d.c * d2;
            final nn nn2 = entity;
            nn2.y += vec3d.d * d2;
            final nn nn3 = entity;
            nn3.z += vec3d.e * d2;
        }
    }
}
