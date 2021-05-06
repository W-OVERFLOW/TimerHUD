package net.wyvest.timer;

import club.sk1er.mods.core.universal.UMinecraft;
import net.wyvest.lib.util.Multithreading;

import java.awt.event.ActionListener;

/**
 * @author Wyvest
 */

public class Timer {
    int delay = 1000; //milliseconds
    public int secondsPassed;
    public boolean running;
    public static Timer instance = new Timer();
    ActionListener timerTask = evt -> secondsPassed += 1;
    javax.swing.Timer timer = new javax.swing.Timer(delay, timerTask);

    public void toggleTimer() {
        Multithreading.runAsync(()-> {
            if (!running) {
                if (UMinecraft.getWorld() == null) return;
                timer.start();
                running = true;
            } else {
                timer.stop();
                secondsPassed = 0;
                running = false;
            }
        });
    }

    public void setTimer(boolean status) {
        Multithreading.runAsync(()-> {
            if (status) {
                timer.start();
                running = true;
            } else {
                timer.stop();
                secondsPassed = 0;
                running = false;
            }
        });
    }

}
