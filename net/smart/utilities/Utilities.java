// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.utilities;

public abstract class Utilities
{
    public static final float Whole = 6.2831855f;
    public static final float Half = 3.1415927f;
    public static final float Quarter = 1.5707964f;
    public static final float Eighth = 0.7853982f;
    public static final float Sixteenth = 0.3926991f;
    public static final float Thirtytwoth = 0.19634955f;
    public static final float Sixtyfourth = 0.09817477f;
    public static final float RadiantToAngle = 57.295776f;
    
    public static float getHorizontalCollisionangle(final boolean isCollidedPositiveX, final boolean isCollidedNegativeX, final boolean isCollidedPositiveZ, final boolean isCollidedNegativeZ) {
        if (isCollidedPositiveX) {
            if (isCollidedNegativeX) {
                if (isCollidedPositiveZ) {
                    if (!isCollidedNegativeZ) {
                        return 90.0f;
                    }
                }
                else if (isCollidedNegativeZ) {
                    return 270.0f;
                }
            }
            else if (isCollidedPositiveZ) {
                if (isCollidedNegativeZ) {
                    return 0.0f;
                }
                return 45.0f;
            }
            else {
                if (isCollidedNegativeZ) {
                    return 315.0f;
                }
                return 0.0f;
            }
        }
        else if (isCollidedNegativeX) {
            if (isCollidedPositiveZ) {
                if (isCollidedNegativeZ) {
                    return 180.0f;
                }
                return 135.0f;
            }
            else {
                if (isCollidedNegativeZ) {
                    return 225.0f;
                }
                return 180.0f;
            }
        }
        else if (isCollidedPositiveZ) {
            if (!isCollidedNegativeZ) {
                return 90.0f;
            }
        }
        else if (isCollidedNegativeZ) {
            return 270.0f;
        }
        return Float.NaN;
    }
    
    public static float getAngle(final double x, final double y) {
        if (x == 0.0) {
            if (y == 0.0) {
                return Float.NaN;
            }
            if (y < 0.0) {
                return 270.0f;
            }
            return 90.0f;
        }
        else if (y == 0.0) {
            if (x < 0.0) {
                return 180.0f;
            }
            return 0.0f;
        }
        else {
            final float angle = (float)Math.atan(y / x) * 57.295776f;
            if (x < 0.0) {
                return 180.0f + angle;
            }
            if (y < 0.0 && x > 0.0) {
                return 360.0f + angle;
            }
            return angle;
        }
    }
}
