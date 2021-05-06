package net.wyvest.timer.keybind;

import net.wyvest.lib.util.betterkeybinds.KeyBind;
import net.wyvest.timer.Timer;
import org.lwjgl.input.Keyboard;

/**
 * @author Wyvest
 */

public class TimerKeybind extends KeyBind {

    public TimerKeybind() {
        super("Toggle Timer", Keyboard.KEY_B, "TimerHUD");
    }
    @Override
    public void press() {
        Timer.instance.toggleTimer();
    }

    @Override
    public void hold() {

    }
}
