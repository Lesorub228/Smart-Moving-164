// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.io.PrintStream;

public class FeetClimbing
{
    public static final int DownStep = 1;
    public static final int NoStep = 0;
    public static FeetClimbing None;
    public static FeetClimbing BaseHold;
    public static FeetClimbing BaseWithHands;
    public static FeetClimbing TopWithHands;
    public static FeetClimbing SlowUpWithHoldWithoutHands;
    public static FeetClimbing SlowUpWithSinkWithoutHands;
    public static FeetClimbing FastUp;
    private int _value;
    
    private FeetClimbing(final int value) {
        this._value = value;
    }
    
    public boolean IsRelevant() {
        return this._value > FeetClimbing.None._value;
    }
    
    public boolean IsIndependentlyRelevant() {
        return this._value > FeetClimbing.BaseWithHands._value;
    }
    
    public boolean IsUp() {
        return this == FeetClimbing.SlowUpWithHoldWithoutHands || this == FeetClimbing.SlowUpWithSinkWithoutHands || this == FeetClimbing.FastUp;
    }
    
    public FeetClimbing max(final FeetClimbing other, final ClimbGap inout_thisClimbGap, final ClimbGap otherClimbGap) {
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
        if (this._value <= FeetClimbing.None._value) {
            return "None";
        }
        if (this._value == FeetClimbing.BaseHold._value) {
            return "BaseHold";
        }
        if (this._value == FeetClimbing.BaseWithHands._value) {
            return "BaseWithHands";
        }
        if (this._value == FeetClimbing.TopWithHands._value) {
            return "TopWithHands";
        }
        if (this._value == FeetClimbing.SlowUpWithHoldWithoutHands._value) {
            return "SlowUpWithHoldWithoutHands";
        }
        if (this._value == FeetClimbing.SlowUpWithSinkWithoutHands._value) {
            return "SlowUpWithSinkWithoutHands";
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
    
    private static FeetClimbing get(final int value) {
        if (value <= FeetClimbing.None._value) {
            return FeetClimbing.None;
        }
        if (value == FeetClimbing.BaseHold._value) {
            return FeetClimbing.BaseHold;
        }
        if (value == FeetClimbing.BaseWithHands._value) {
            return FeetClimbing.BaseWithHands;
        }
        if (value == FeetClimbing.TopWithHands._value) {
            return FeetClimbing.TopWithHands;
        }
        if (value == FeetClimbing.SlowUpWithHoldWithoutHands._value) {
            return FeetClimbing.SlowUpWithHoldWithoutHands;
        }
        if (value == FeetClimbing.SlowUpWithSinkWithoutHands._value) {
            return FeetClimbing.SlowUpWithSinkWithoutHands;
        }
        return FeetClimbing.FastUp;
    }
    
    static {
        FeetClimbing.None = new FeetClimbing(-3);
        FeetClimbing.BaseHold = new FeetClimbing(-2);
        FeetClimbing.BaseWithHands = new FeetClimbing(-1);
        FeetClimbing.TopWithHands = new FeetClimbing(0);
        FeetClimbing.SlowUpWithHoldWithoutHands = new FeetClimbing(1);
        FeetClimbing.SlowUpWithSinkWithoutHands = new FeetClimbing(2);
        FeetClimbing.FastUp = new FeetClimbing(3);
    }
}
