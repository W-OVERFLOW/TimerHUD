package net.wyvest.timerhud

import gg.essential.api.utils.Multithreading
import net.wyvest.timerhud.TimerHUD.mc
import java.awt.event.ActionListener
import javax.swing.Timer


object TimerTask {

    var secondsPassed = 0
    var running = false
    private var timerTask = ActionListener { secondsPassed += 1 }
    private var timer = Timer(1000, timerTask)

    fun toggleTimer() {
        Multithreading.runAsync {
            if (!running) {
                if (mc.theWorld == null) return@runAsync
                secondsPassed = 0
                timer.start()
                running = true
            } else {
                timer.stop()
                secondsPassed = 0
                running = false
            }
        }
    }

    fun setTimer(status: Boolean) {
        Multithreading.runAsync {
            if (status) {
                secondsPassed = 0
                timer.start()
                running = true
            } else {
                timer.stop()
                secondsPassed = 0
                running = false
            }
        }
    }
}