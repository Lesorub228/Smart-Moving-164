// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public class ClimbGap
{
    public int BlockId;
    public int Meta;
    public boolean CanStand;
    public boolean MustCrawl;
    public Orientation Direction;
    public boolean SkipGaps;
    
    public ClimbGap() {
        this.reset();
    }
    
    public void reset() {
        this.BlockId = -1;
        this.Meta = -1;
        this.CanStand = false;
        this.MustCrawl = false;
        this.Direction = null;
        this.SkipGaps = false;
    }
}
