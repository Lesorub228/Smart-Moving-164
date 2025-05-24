// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.io.PrintStream;

public class HandsClimbing
{
    public static final int MiddleGrab = 2;
    public static final int UpGrab = 1;
    public static final int NoGrab = 0;
    public static HandsClimbing None;
    public static HandsClimbing Sink;
    public static HandsClimbing TopHold;
    public static HandsClimbing BottomHold;
    public static HandsClimbing Up;
    public static HandsClimbing FastUp;
    private int _value;
    
    private HandsClimbing(final int value) {
        this._value = value;
    }
    
    public boolean IsRelevant() {
        return this._value > HandsClimbing.None._value;
    }
    
    public boolean IsUp() {
        return this == HandsClimbing.Up || this == HandsClimbing.FastUp;
    }
    
    public HandsClimbing ToUp() {
        if (this == HandsClimbing.BottomHold) {
            return HandsClimbing.Up;
        }
        return this;
    }
    
    public HandsClimbing ToDown() {
        if (this == HandsClimbing.TopHold) {
            return HandsClimbing.Sink;
        }
        return this;
    }
    
    public HandsClimbing max(final HandsClimbing other, final ClimbGap inout_thisClimbGap, final ClimbGap otherClimbGap) {
        if (!otherClimbGap.SkipGaps) {
            inout_thisClimbGap.CanStand |= otherClimbGap.CanStand;
            inout_thisClimbGap.MustCrawl |= otherClimbGap.MustCrawl;
        }
        if (this._value < other._value) {
            inout_thisClimbGap.BlockId = otherClimbGap.BlockId;
            inout_thisClimbGap.Meta = otherClimbGap.Meta;
            inout_thisClimbGap.Direction = otherClimbGap.Direction;
        }
        return get(Math.max(this._value, other._value));
    }
    
    @Override
    public String toString() {
        if (this._value <= HandsClimbing.None._value) {
            return "None";
        }
        if (this._value == HandsClimbing.Sink._value) {
            return "Sink";
        }
        if (this._value == HandsClimbing.BottomHold._value) {
            return "BottomHold";
        }
        if (this._value == HandsClimbing.TopHold._value) {
            return "TopHold";
        }
        if (this._value == HandsClimbing.Up._value) {
            return "Up";
        }
        return "FastUp";
    }
    
    public void print(final String name) {
        final PrintStream stream = System.err;
        if (name != null) {
            stream.print(name + " = ");
        }
        stream.println(this);
    }
    
    private static HandsClimbing get(final int value) {
        if (value <= HandsClimbing.None._value) {
            return HandsClimbing.None;
        }
        if (value == HandsClimbing.Sink._value) {
            return HandsClimbing.Sink;
        }
        if (value == HandsClimbing.BottomHold._value) {
            return HandsClimbing.BottomHold;
        }
        if (value == HandsClimbing.TopHold._value) {
            return HandsClimbing.TopHold;
        }
        if (value == HandsClimbing.Up._value) {
            return HandsClimbing.Up;
        }
        return HandsClimbing.FastUp;
    }
    
    static {
        HandsClimbing.None = new HandsClimbing(-3);
        HandsClimbing.Sink = new HandsClimbing(-2);
        HandsClimbing.TopHold = new HandsClimbing(-1);
        HandsClimbing.BottomHold = new HandsClimbing(0);
        HandsClimbing.Up = new HandsClimbing(1);
        HandsClimbing.FastUp = new HandsClimbing(2);
    }
}
