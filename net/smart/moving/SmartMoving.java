// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import net.smart.render.RendererData;
import net.smart.render.SmartRenderRender;

public abstract class SmartMoving extends SmartMovingBase
{
    public boolean isSlow;
    public boolean isFast;
    public boolean isClimbing;
    public boolean isHandsVineClimbing;
    public boolean isFeetVineClimbing;
    public boolean isClimbJumping;
    public boolean isClimbBackJumping;
    public boolean isWallJumping;
    public boolean isClimbCrawling;
    public boolean isCrawlClimbing;
    public boolean isCeilingClimbing;
    public boolean isRopeSliding;
    public boolean isDipping;
    public boolean isSwimming;
    public boolean isDiving;
    public boolean isLevitating;
    public boolean isHeadJumping;
    public boolean isCrawling;
    public boolean isSliding;
    public boolean isFlying;
    public int actualHandsClimbType;
    public int actualFeetClimbType;
    public int angleJumpType;
    public float heightOffset;
    private float spawnSlindingParticle;
    private float spawnSwimmingParticle;
    
    public SmartMoving(final uf sp, final IEntityPlayerSP isp) {
        super(sp, isp);
    }
    
    public boolean isAngleJumping() {
        return this.angleJumpType > 1 && this.angleJumpType < 7;
    }
    
    public abstract boolean isJumping();
    
    public abstract boolean doFlyingAnimation();
    
    public abstract boolean doFallingAnimation();
    
    protected void spawnParticles(final atv minecraft, final double playerMotionX, final double playerMotionZ) {
        float horizontalSpeedSquare = 0.0f;
        if (this.isSliding || this.isSwimming) {
            horizontalSpeedSquare = (float)(playerMotionX * playerMotionX + playerMotionZ * playerMotionZ);
        }
        if (this.isSliding) {
            final int i = ls.c(this.sp.u);
            final int j = ls.c(this.sp.E.b - 0.10000000149011612);
            final int k = ls.c(this.sp.w);
            final int blockId = this.sp.q.a(i, j, k);
            if (blockId > 0) {
                final double posY = this.sp.E.b + 0.1;
                final double motionX = -playerMotionX * 4.0;
                final double motionY = 1.5;
                final double motionZ = -playerMotionZ * 4.0;
                this.spawnSlindingParticle += horizontalSpeedSquare;
                final float maxSpawnSlindingParticle = SmartMoving.Config._slideParticlePeriodFactor.value * 0.1f;
                while (this.spawnSlindingParticle > maxSpawnSlindingParticle) {
                    final double posX = this.sp.u + this.getSpawnOffset();
                    final double posZ = this.sp.w + this.getSpawnOffset();
                    final int metaData = this.sp.q.h(i, j, k);
                    this.sp.q.a("tilecrack_" + blockId + "_" + metaData, posX, posY, posZ, motionX, motionY, motionZ);
                    this.spawnSlindingParticle -= maxSpawnSlindingParticle;
                }
            }
        }
        if (this.isSwimming) {
            final float posY2 = ls.c(this.sp.E.b) + 1.0f;
            final int l = (int)Math.floor(this.sp.u);
            final int m = (int)Math.floor(posY2 - 0.5);
            final int k2 = (int)Math.floor(this.sp.w);
            final int blockId2 = this.sp.q.a(l, m, k2);
            final aqz block = (blockId2 > 0) ? aqz.s[blockId2] : null;
            final boolean isLava = block != null && this.isLava(block.cF);
            this.spawnSwimmingParticle += horizontalSpeedSquare;
            final float maxSpawnSwimmingParticle = (isLava ? SmartMoving.Config._lavaSwimParticlePeriodFactor.value : SmartMoving.Config._swimParticlePeriodFactor.value) * 0.01f;
            while (this.spawnSwimmingParticle > maxSpawnSwimmingParticle) {
                final double posX2 = this.sp.u + this.getSpawnOffset();
                final double posZ2 = this.sp.w + this.getSpawnOffset();
                final beg splash = (beg)(isLava ? new bee(this.sp.q, posX2, (double)posY2, posZ2) : new beo(this.sp.q, posX2, (double)posY2, posZ2, 0.0, 0.0, 0.0));
                splash.x = 0.0;
                splash.y = 0.2;
                splash.z = 0.0;
                minecraft.k.a(splash);
                this.spawnSwimmingParticle -= maxSpawnSwimmingParticle;
            }
        }
    }
    
    private float getSpawnOffset() {
        return (this.sp.aD().nextFloat() - 0.5f) * 2.0f * this.sp.O;
    }
    
    protected void onStartClimbBackJump() {
        final RendererData previousRendererData = SmartRenderRender.getPreviousRendererData(this.sp);
        previousRendererData.rotateAngleY += (this.isHeadJumping ? 3.1415927f : 1.5707964f);
        this.isClimbBackJumping = true;
    }
    
    protected void onStartWallJump(final Float angle) {
        if (angle != null) {
            SmartRenderRender.getPreviousRendererData(this.sp).rotateAngleY = angle / 57.295776f;
        }
        this.isWallJumping = true;
        this.sp.T = 0.0f;
    }
}
