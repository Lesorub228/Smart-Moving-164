// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.render;

import net.smart.moving.SmartMovingSelf;
import org.lwjgl.opengl.GL11;
import net.smart.render.statistics.SmartStatistics;
import net.smart.moving.SmartMoving;
import net.smart.render.statistics.SmartStatisticsFactory;
import net.smart.moving.SmartMovingFactory;

public class SmartMovingRender extends SmartRenderContext
{
    public IRenderPlayer irp;
    public final SmartMovingModel modelBipedMain;
    private static int _iOffset;
    private static int _jOffset;
    private static atv _minecraft;
    
    public SmartMovingRender(final IRenderPlayer irp) {
        this.irp = irp;
        this.modelBipedMain = irp.getPlayerModelBipedMain().getMovingModel();
        final SmartMovingModel modelArmorChestplate = irp.getPlayerModelArmorChestplate().getMovingModel();
        final SmartMovingModel modelArmor = irp.getPlayerModelArmor().getMovingModel();
        this.modelBipedMain.scaleArmType = 0;
        this.modelBipedMain.scaleLegType = 0;
        modelArmorChestplate.scaleArmType = 1;
        modelArmorChestplate.scaleLegType = 2;
        modelArmor.scaleArmType = 1;
        modelArmor.scaleLegType = 0;
    }
    
    public void renderPlayer(final beu entityplayer, final double d, double d1, final double d2, final float f, final float renderPartialTicks) {
        IModelPlayer[] modelPlayers = null;
        final SmartMoving moving = SmartMovingFactory.getInstance((uf)entityplayer);
        if (moving != null) {
            final boolean isInventory = d == 0.0 && d1 == 0.0 && d2 == 0.0 && f == 0.0f && renderPartialTicks == 1.0f;
            final boolean isClimb = moving.isClimbing && !moving.isCrawling && !moving.isCrawlClimbing && !moving.isClimbJumping;
            final boolean isClimbJump = moving.isClimbJumping;
            final int handsClimbType = moving.actualHandsClimbType;
            final int feetClimbType = moving.actualFeetClimbType;
            final boolean isHandsVineClimbing = moving.isHandsVineClimbing;
            final boolean isFeetVineClimbing = moving.isFeetVineClimbing;
            final boolean isCeilingClimb = moving.isCeilingClimbing;
            final boolean isSwim = moving.isSwimming && !moving.isDipping;
            final boolean isDive = moving.isDiving;
            final boolean isLevitate = moving.isLevitating;
            final boolean isCrawl = moving.isCrawling && !moving.isClimbing;
            final boolean isCrawlClimb = moving.isCrawlClimbing || (moving.isClimbing && moving.isCrawling);
            final boolean isJump = moving.isJumping();
            final boolean isHeadJump = moving.isHeadJumping;
            final boolean isFlying = moving.doFlyingAnimation();
            final boolean isSlide = moving.isSliding;
            final boolean isFalling = moving.doFallingAnimation();
            final boolean isGenericSneaking = moving.isSlow;
            final boolean isAngleJumping = moving.isAngleJumping();
            final int angleJumpType = moving.angleJumpType;
            final boolean isRopeSliding = moving.isRopeSliding;
            final SmartStatistics statistics = SmartStatisticsFactory.getInstance((uf)entityplayer);
            final float currentHorizontalSpeedFlattened = (statistics != null) ? statistics.getCurrentHorizontalSpeedFlattened(renderPartialTicks, -1) : Float.NaN;
            final float smallOverGroundHeight = (isCrawlClimb || isHeadJump) ? ((float)moving.getOverGroundHeight(5.0)) : 0.0f;
            final int overGroundBlockId = (isHeadJump && smallOverGroundHeight < 5.0f) ? moving.getOverGroundBlockId(smallOverGroundHeight) : -1;
            modelPlayers = this.irp.getPlayerModels();
            for (int i = 0; i < modelPlayers.length; ++i) {
                final SmartMovingModel modelPlayer = modelPlayers[i].getMovingModel();
                modelPlayer.isClimb = isClimb;
                modelPlayer.isClimbJump = isClimbJump;
                modelPlayer.handsClimbType = handsClimbType;
                modelPlayer.feetClimbType = feetClimbType;
                modelPlayer.isHandsVineClimbing = isHandsVineClimbing;
                modelPlayer.isFeetVineClimbing = isFeetVineClimbing;
                modelPlayer.isCeilingClimb = isCeilingClimb;
                modelPlayer.isSwim = isSwim;
                modelPlayer.isDive = isDive;
                modelPlayer.isCrawl = isCrawl;
                modelPlayer.isCrawlClimb = isCrawlClimb;
                modelPlayer.isJump = isJump;
                modelPlayer.isHeadJump = isHeadJump;
                modelPlayer.isSlide = isSlide;
                modelPlayer.isFlying = isFlying;
                modelPlayer.isLevitate = isLevitate;
                modelPlayer.isFalling = isFalling;
                modelPlayer.isGenericSneaking = isGenericSneaking;
                modelPlayer.isAngleJumping = isAngleJumping;
                modelPlayer.angleJumpType = angleJumpType;
                modelPlayer.isRopeSliding = isRopeSliding;
                modelPlayer.currentHorizontalSpeedFlattened = currentHorizontalSpeedFlattened;
                modelPlayer.smallOverGroundHeight = smallOverGroundHeight;
                modelPlayer.overGroundBlockId = overGroundBlockId;
            }
            if (!isInventory && entityplayer.ah() && !(entityplayer instanceof bex) && isCrawl) {
                d1 += 0.125;
            }
        }
        this.irp.superRenderRenderPlayer(entityplayer, d, d1, d2, f, renderPartialTicks);
        if (moving != null && moving.isLevitating) {
            for (int j = 0; j < modelPlayers.length; ++j) {
                modelPlayers[j].getMovingModel().md.currentHorizontalAngle = modelPlayers[j].getMovingModel().md.currentCameraAngle;
            }
        }
    }
    
    public void rotatePlayer(final beu entityplayer, final float totalTime, final float actualRotation, final float f2) {
        final SmartMoving moving = SmartMovingFactory.getInstance((uf)entityplayer);
        if (moving != null) {
            final boolean isInventory = f2 == 1.0f && moving.isp != null && moving.isp.getMcField().n instanceof axv;
            if (!isInventory) {
                final float forwardRotation = entityplayer.C + (entityplayer.A - entityplayer.C) * f2;
                if (moving.isClimbing || moving.isClimbCrawling || moving.isCrawlClimbing || moving.isFlying || moving.isSwimming || moving.isDiving || moving.isCeilingClimbing || moving.isHeadJumping || moving.isSliding || moving.isAngleJumping()) {
                    entityplayer.aN = forwardRotation;
                }
            }
        }
        this.irp.superRenderRotatePlayer(entityplayer, totalTime, actualRotation, f2);
    }
    
    public void renderPlayerAt(final beu entityplayer, final double d, double d1, final double d2) {
        if (entityplayer instanceof bey) {
            final SmartMoving moving = SmartMovingFactory.getOtherSmartMoving(entityplayer.k);
            if (moving != null && moving.heightOffset != 0.0f) {
                d1 += moving.heightOffset;
            }
        }
        this.irp.superRenderRenderPlayerAt(entityplayer, d, d1, d2);
    }
    
    public void renderName(final of entityPlayer, final double d, double d1, final double d2) {
        boolean changedIsSneaking = false;
        boolean originalIsSneaking = false;
        if (atv.r() && entityPlayer != this.irp.getRenderManager().h) {
            final SmartMoving moving = (entityPlayer instanceof uf) ? SmartMovingFactory.getInstance((uf)entityPlayer) : null;
            if (moving != null) {
                boolean temporaryIsSneaking;
                originalIsSneaking = (temporaryIsSneaking = entityPlayer.ah());
                if (moving.isCrawling && !moving.isClimbing) {
                    temporaryIsSneaking = !SmartMovingRender.Config._crawlNameTag.value;
                }
                else if (originalIsSneaking) {
                    temporaryIsSneaking = !SmartMovingRender.Config._sneakNameTag.value;
                }
                changedIsSneaking = (temporaryIsSneaking != originalIsSneaking);
                if (changedIsSneaking) {
                    entityPlayer.b(temporaryIsSneaking);
                }
                if (moving.heightOffset == -1.0f) {
                    d1 -= 0.20000000298023224;
                }
                else if (originalIsSneaking && !temporaryIsSneaking) {
                    d1 -= 0.05000000074505806;
                }
            }
        }
        this.irp.superRenderRenderName(entityPlayer, d, d1, d2);
        if (changedIsSneaking) {
            entityPlayer.b(originalIsSneaking);
        }
    }
    
    public static void renderGuiIngame(final atv minecraft) {
        if (!SmartMovingRender.Client.getNativeUserInterfaceDrawing()) {
            return;
        }
        if (!GL11.glGetBoolean(3008)) {
            return;
        }
        final SmartMovingSelf moving = (SmartMovingSelf)SmartMovingFactory.getInstance((uf)minecraft.h);
        if (moving != null && SmartMovingRender.Config.enabled && (SmartMovingRender.Options._displayExhaustionBar.value || SmartMovingRender.Options._displayJumpChargeBar.value)) {
            final awf scaledresolution = new awf(minecraft.u, minecraft.d, minecraft.e);
            final int width = scaledresolution.a();
            final int height = scaledresolution.b();
            if (minecraft.c.b()) {
                final float maxExhaustion = SmartMovingRender.Client.getMaximumExhaustion();
                final float exhaustion = Math.min(moving.exhaustion, maxExhaustion);
                final boolean drawExhaustion = exhaustion > 0.0f && exhaustion <= maxExhaustion;
                final float maxStillJumpCharge = SmartMovingRender.Config._jumpChargeMaximum.value;
                final float stillJumpCharge = Math.min(moving.jumpCharge, maxStillJumpCharge);
                final float maxRunJumpCharge = SmartMovingRender.Config._headJumpChargeMaximum.value;
                final float runJumpCharge = Math.min(moving.headJumpCharge, maxRunJumpCharge);
                final boolean drawJumpCharge = stillJumpCharge > 0.0f || runJumpCharge > 0.0f;
                final float maxJumpCharge = (stillJumpCharge > runJumpCharge) ? maxStillJumpCharge : maxRunJumpCharge;
                final float jumpCharge = Math.max(stillJumpCharge, runJumpCharge);
                if (drawExhaustion || drawJumpCharge) {
                    GL11.glPushAttrib(262144);
                    minecraft.J().a(new bjo("SmartMoving", "gui/icons.png"));
                    GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
                    SmartMovingRender._minecraft = minecraft;
                }
                if (drawExhaustion) {
                    final float maxExhaustionForAction = Math.min(moving.maxExhaustionForAction, maxExhaustion);
                    final float maxExhaustionToStartAction = Math.min(moving.maxExhaustionToStartAction, maxExhaustion);
                    final float fitness = maxExhaustion - exhaustion;
                    final float minFitnessForAction = Float.isNaN(maxExhaustionForAction) ? 0.0f : (maxExhaustion - maxExhaustionForAction);
                    final float minFitnessToStartAction = Float.isNaN(maxExhaustionToStartAction) ? 0.0f : (maxExhaustion - maxExhaustionToStartAction);
                    final float maxFitnessDrawn = Math.max(Math.max(minFitnessToStartAction, fitness), minFitnessForAction);
                    final int halfs = (int)Math.floor(maxFitnessDrawn / maxExhaustion * 21.0f);
                    final int fulls = halfs / 2;
                    final int half = halfs % 2;
                    final int fitnessHalfs = (int)Math.floor(fitness / maxExhaustion * 21.0f);
                    final int fitnessFulls = fitnessHalfs / 2;
                    final int fitnessHalf = fitnessHalfs % 2;
                    final int minFitnessForActionHalfs = (int)Math.floor(minFitnessForAction / maxExhaustion * 21.0f);
                    final int minFitnessForActionFulls = minFitnessForActionHalfs / 2;
                    final int minFitnessForActionHalf = minFitnessForActionHalfs % 2;
                    final int minFitnessToStartActionHalfs = (int)Math.floor(minFitnessToStartAction / maxExhaustion * 21.0f);
                    final int minFitnessToStartActionFulls = minFitnessToStartActionHalfs / 2;
                    SmartMovingRender._jOffset = height - 39 - 10 - (minecraft.h.a(akc.h) ? 10 : 0);
                    for (int i = 0; i < Math.min(fulls + half, 10); ++i) {
                        SmartMovingRender._iOffset = width / 2 + 90 - (i + 1) * 8;
                        if (i < fitnessFulls) {
                            if (i < minFitnessForActionFulls) {
                                drawIcon(2, 2);
                            }
                            else if (i == minFitnessForActionFulls && minFitnessForActionHalf > 0) {
                                drawIcon(3, 2);
                            }
                            else {
                                drawIcon(0, 0);
                            }
                        }
                        else if (i == fitnessFulls && fitnessHalf > 0) {
                            if (i < minFitnessForActionFulls) {
                                drawIcon(1, 2);
                            }
                            else if (i == minFitnessForActionFulls && minFitnessForActionHalf > 0) {
                                if (i < minFitnessToStartActionFulls) {
                                    drawIcon(3, 1);
                                }
                                else {
                                    drawIcon(4, 2);
                                }
                            }
                            else if (i < minFitnessToStartActionFulls) {
                                drawIcon(1, 1);
                            }
                            else {
                                drawIcon(1, 0);
                            }
                        }
                        else if (i < minFitnessForActionFulls) {
                            drawIcon(0, 2);
                        }
                        else if (i == minFitnessForActionFulls && minFitnessForActionHalf > 0) {
                            if (i < minFitnessToStartActionFulls) {
                                drawIcon(2, 1);
                            }
                            else {
                                drawIcon(5, 2);
                            }
                        }
                        else if (i < minFitnessToStartActionFulls) {
                            drawIcon(0, 1);
                        }
                        else {
                            drawIcon(4, 1);
                        }
                    }
                }
                if (drawJumpCharge) {
                    final boolean max = jumpCharge == maxJumpCharge;
                    final int fulls2 = max ? 10 : ((int)Math.ceil((jumpCharge - 2.0f) * 10.0 / maxJumpCharge));
                    final int half2 = max ? 0 : ((int)Math.ceil(jumpCharge * 10.0 / maxJumpCharge) - fulls2);
                    SmartMovingRender._jOffset = height - 39 - 10 - ((minecraft.h.aQ() > 0) ? 10 : 0);
                    for (int j = 0; j < fulls2 + half2; ++j) {
                        SmartMovingRender._iOffset = width / 2 - 91 + j * 8;
                        drawIcon((j < fulls2) ? 2 : 3, 0);
                    }
                }
                if (drawExhaustion || drawJumpCharge) {
                    GL11.glPopAttrib();
                }
            }
        }
    }
    
    private static void drawIcon(final int x, final int y) {
        SmartMovingRender._minecraft.r.b(SmartMovingRender._iOffset, SmartMovingRender._jOffset, x * 9, y * 9, 9, 9);
    }
}
