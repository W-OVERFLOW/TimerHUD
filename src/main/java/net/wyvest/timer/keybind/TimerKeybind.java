package net.wyvest.timer.keybind;

import net.wyvest.lib.util.betterkeybinds.KeyBind;
import net.wyvest.timer.TimerHUD;
import org.lwjgl.input.Keyboard;

public class TimerKeybind extends KeyBind {

    public TimerKeybind() {
        super("Toggle Timer", Keyboard.KEY_B, "TimerHUD");
    }
    @Override
    public void press() {
        TimerHUD.INSTANCE.toggleRunning();
    }

    @Override
    public void hold() {

    }
}
