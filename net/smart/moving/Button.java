// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;

public class Button extends SmartMovingContext
{
    public boolean Pressed;
    public boolean WasPressed;
    public boolean StartPressed;
    public boolean StopPressed;
    
    public void update(final ats binding) {
        this.update(atv.w().A && isKeyDown(binding));
    }
    
    public void update(final int keyCode) {
        this.update(atv.w().A && isKeyDown(keyCode));
    }
    
    public void update(final boolean pressed) {
        this.WasPressed = this.Pressed;
        this.Pressed = pressed;
        this.StartPressed = (!this.WasPressed && this.Pressed);
        this.StopPressed = (this.WasPressed && !this.Pressed);
    }
    
    private static boolean isKeyDown(final ats keyBinding) {
        return isKeyDown(keyBinding, keyBinding.e);
    }
    
    private static boolean isKeyDown(final ats keyBinding, final boolean wasDown) {
        final awe currentScreen = atv.w().n;
        if (currentScreen == null || currentScreen.j) {
            return isKeyDown(keyBinding.d);
        }
        return wasDown;
    }
    
    private static boolean isKeyDown(final int keyCode) {
        if (keyCode >= 0) {
            return Keyboard.isKeyDown(keyCode);
        }
        return Mouse.isButtonDown(keyCode + 100);
    }
}
