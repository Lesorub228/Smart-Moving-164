// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render.statistics.playerapi;

import api.player.client.ClientPlayerAPI;
import net.smart.render.statistics.SmartStatistics;
import net.smart.render.statistics.IEntityPlayerSP;
import api.player.client.ClientPlayerBase;

public class SmartStatisticsPlayerBase extends ClientPlayerBase implements IEntityPlayerSP
{
    public SmartStatistics statistics;
    
    public SmartStatisticsPlayerBase(final ClientPlayerAPI playerApi) {
        super(playerApi);
        this.statistics = new SmartStatistics((uf)this.player);
    }
    
    public void afterMoveEntityWithHeading(final float f, final float f1) {
        this.statistics.calculateAllStats();
    }
    
    public void afterUpdateRidden() {
        this.statistics.calculateRiddenStats();
    }
    
    public SmartStatistics getStatistics() {
        return this.statistics;
    }
}
