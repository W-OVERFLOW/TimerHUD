package net.wyvest.timerhud.commands

import gg.essential.api.EssentialAPI
import gg.essential.api.commands.Command
import gg.essential.api.commands.DefaultHandler
import gg.essential.api.commands.SubCommand
import net.wyvest.timerhud.TimerHUD
import net.wyvest.timerhud.config.TimerConfig
import net.wyvest.timerhud.gui.HUDGui

@Suppress("unused")
object TimerHUDCommand : Command(TimerHUD.ID, true) {

    @DefaultHandler
    fun handle() {
        EssentialAPI.getGuiUtil().openScreen(TimerConfig.gui())
    }

    @SubCommand("config", description = "Opens the config GUI for " + TimerHUD.NAME)
    fun config() {
        EssentialAPI.getGuiUtil().openScreen(TimerConfig.gui())
    }

    @SubCommand("gui", description = "Open the HUD editor for TimerHUD")
    fun gui() {
        EssentialAPI.getGuiUtil().openScreen(HUDGui())
    }

    @SubCommand("hud", description = "Open the HUD editor for TimerHUD")
    fun hud() {
        EssentialAPI.getGuiUtil().openScreen(HUDGui())
    }
}