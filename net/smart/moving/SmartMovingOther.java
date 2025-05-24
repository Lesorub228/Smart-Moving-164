// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public class SmartMovingOther extends SmartMoving
{
    public boolean foundAlive;
    private boolean _isJumping;
    private boolean _doFlyingAnimation;
    private boolean _doFallingAnimation;
    
    public SmartMovingOther(final bey sp) {
        super((uf)sp, null);
        this._isJumping = false;
        this._doFlyingAnimation = false;
        this._doFallingAnimation = false;
    }
    
    public void processStatePacket(long state) {
        this.actualFeetClimbType = (int)(state & 0xFL);
        state >>>= 4;
        this.actualHandsClimbType = (int)(state & 0xFL);
        state >>>= 4;
        this._isJumping = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isDiving = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isDipping = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isSwimming = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isCrawlClimbing = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isCrawling = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isClimbing = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        final boolean isSmall = (state & 0x1L) != 0x0L;
        this.heightOffset = (isSmall ? -1.0f : 0.0f);
        this.sp.P = 1.8f + this.heightOffset;
        state >>>= 1;
        this._doFallingAnimation = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this._doFlyingAnimation = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isCeilingClimbing = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isLevitating = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isHeadJumping = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isSliding = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.angleJumpType = (int)(state & 0x7L);
        state >>>= 3;
        this.isFeetVineClimbing = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isHandsVineClimbing = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isClimbJumping = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        final boolean wasClimbBackJumping = this.isClimbBackJumping;
        this.isClimbBackJumping = ((state & 0x1L) != 0x0L);
        if (!wasClimbBackJumping && this.isClimbBackJumping) {
            this.onStartClimbBackJump();
        }
        state >>>= 1;
        this.isSlow = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        this.isFast = ((state & 0x1L) != 0x0L);
        state >>>= 1;
        final boolean wasWallJumping = this.isWallJumping;
        this.isWallJumping = ((state & 0x1L) != 0x0L);
        if (!wasWallJumping && this.isWallJumping) {
            this.onStartWallJump(null);
        }
        state >>>= 1;
        this.isRopeSliding = ((state & 0x1L) != 0x0L);
    }
    
    @Override
    public boolean isJumping() {
        return this._isJumping;
    }
    
    @Override
    public boolean doFlyingAnimation() {
        return this._doFlyingAnimation;
    }
    
    @Override
    public boolean doFallingAnimation() {
        return this._doFallingAnimation;
    }
}
