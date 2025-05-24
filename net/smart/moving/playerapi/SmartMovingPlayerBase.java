// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.playerapi;

import net.smart.moving.SmartMoving;
import api.player.client.ClientPlayerAPI;
import net.smart.moving.IEntityPlayerSP;
import api.player.client.ClientPlayerBase;

public class SmartMovingPlayerBase extends ClientPlayerBase implements IEntityPlayerSP
{
    public SmartMovingSelf moving;
    
    public static void registerPlayerBase() {
        ClientPlayerAPI.register("Smart Moving", (Class)SmartMovingPlayerBase.class);
    }
    
    public static SmartMovingPlayerBase getPlayerBase(final bex player) {
        return (SmartMovingPlayerBase)player.getClientPlayerBase("Smart Moving");
    }
    
    public SmartMovingPlayerBase(final ClientPlayerAPI playerApi) {
        super(playerApi);
        this.moving = new SmartMovingSelf((uf)this.player, this);
    }
    
    public void beforeMoveEntity(final double d, final double d1, final double d2) {
        this.moving.beforeMoveEntity(d, d1, d2);
    }
    
    public void afterMoveEntity(final double d, final double d1, final double d2) {
        this.moving.afterMoveEntity(d, d1, d2);
    }
    
    public void localMoveEntity(final double d, final double d1, final double d2) {
        super.moveEntity(d, d1, d2);
    }
    
    public void beforeSleepInBedAt(final int i, final int j, final int k) {
        this.moving.beforeSleepInBedAt(i, j, k);
    }
    
    public ug localSleepInBedAt(final int i, final int j, final int k) {
        return super.sleepInBedAt(i, j, k);
    }
    
    public float getBrightness(final float f) {
        return this.moving.getBrightness(f);
    }
    
    public float localGetBrightness(final float f) {
        return super.getBrightness(f);
    }
    
    public int getBrightnessForRender(final float f) {
        return this.moving.getBrightnessForRender(f);
    }
    
    public int localGetBrightnessForRender(final float f) {
        return super.getBrightnessForRender(f);
    }
    
    public boolean pushOutOfBlocks(final double d, final double d1, final double d2) {
        return this.moving.pushOutOfBlocks(d, d1, d2);
    }
    
    public void beforeOnUpdate() {
        this.moving.beforeOnUpdate();
    }
    
    public void afterOnUpdate() {
        this.moving.afterOnUpdate();
    }
    
    public void beforeOnLivingUpdate() {
        this.moving.beforeOnLivingUpdate();
    }
    
    public void afterOnLivingUpdate() {
        this.moving.afterOnLivingUpdate();
    }
    
    public boolean getSleepingField() {
        return this.player.getSleepingField();
    }
    
    public boolean getIsInWebField() {
        return this.player.getIsInWebField();
    }
    
    public void setIsInWebField(final boolean newIsInWeb) {
        this.player.setIsInWebField(newIsInWeb);
    }
    
    public boolean getIsJumpingField() {
        return this.player.getIsJumpingField();
    }
    
    public atv getMcField() {
        return this.player.getMcField();
    }
    
    public void moveEntityWithHeading(final float f, final float f1) {
        this.moving.moveEntityWithHeading(f, f1);
    }
    
    public boolean canTriggerWalking() {
        return this.moving.canTriggerWalking();
    }
    
    public boolean isOnLadder() {
        return this.moving.isOnLadderOrVine();
    }
    
    public SmartMovingSelf getMoving() {
        return this.moving;
    }
    
    public void updateEntityActionState() {
        this.moving.updateEntityActionState(false);
    }
    
    public void localUpdateEntityActionState() {
        super.updateEntityActionState();
    }
    
    public void setIsJumpingField(final boolean flag) {
        this.player.setIsJumpingField(flag);
    }
    
    public void setMoveForwardField(final float f) {
        this.player.bf = f;
    }
    
    public void setMoveStrafingField(final float f) {
        this.player.be = f;
    }
    
    public boolean isInsideOfMaterial(final akc material) {
        return this.moving.isInsideOfMaterial(material);
    }
    
    public boolean localIsInsideOfMaterial(final akc material) {
        return super.isInsideOfMaterial(material);
    }
    
    public void writeEntityToNBT(final by nBTTagCompound) {
        this.moving.writeEntityToNBT(nBTTagCompound);
    }
    
    public void localWriteEntityToNBT(final by nBTTagCompound) {
        super.writeEntityToNBT(nBTTagCompound);
    }
    
    public boolean isSneaking() {
        return this.moving.isSneaking();
    }
    
    public boolean localIsSneaking() {
        return super.isSneaking();
    }
    
    public float getFOVMultiplier() {
        return this.moving.getFOVMultiplier();
    }
    
    public float localGetFOVMultiplier() {
        return this.player.localGetFOVMultiplier();
    }
    
    public void beforeSetPositionAndRotation(final double d, final double d1, final double d2, final float f, final float f1) {
        this.moving.beforeSetPositionAndRotation();
    }
    
    public void beforeGetSleepTimer() {
        this.moving.beforeGetSleepTimer();
    }
    
    public void jump() {
        this.moving.jump();
    }
}
