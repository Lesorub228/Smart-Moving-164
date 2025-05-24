// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

public class LocalUserNameProvider extends SmartMovingContext implements ILocalUserNameProvider
{
    @Override
    public String getLocalConfigUserName() {
        return LocalUserNameProvider.Options._localUserHasChangeConfigRight.value ? atv.w().h.an() : null;
    }
    
    @Override
    public String getLocalSpeedUserName() {
        return LocalUserNameProvider.Options._localUserHasChangeSpeedRight.value ? atv.w().h.an() : null;
    }
}
