@file:Suppress("unused")
package net.wyvest.timer.config

import club.sk1er.vigilance.Vigilant
import club.sk1er.vigilance.data.Property
import club.sk1er.vigilance.data.PropertyType
import java.awt.Color
import java.io.File

object TimerConfig : Vigilant(File("./config/timerhud.toml"))  {

    @Property(type = PropertyType.TEXT, name = "Info", description = "You are using TimerHUD Version 1.5.1, made by Wyvest.", category = "General")
    var paragraph = ""

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Toggle Mod", description = "Toggle the mod.", category = "General")
    var modToggled = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Render Nothing", description = "Render nothing when the timer is off.", category = "Render")
    var renderNothing = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.COLOR, name = "Text Color", description = "Change the text color for the HUD.", category = "Render", subcategory = "Color")
    var color = Color.WHITE

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Turn on Chroma", description = "Turn on Chroma. This overrides Text Color.", category = "Render", subcategory = "Color")
    var chroma = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Make Chroma Text One Color", description = "Make the Chroma text one color that changes instead of each character being a different color.", category = "Render", subcategory = "Color")
    var oneColorChroma = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Display Background", description = "Toggle the background of the HUD.", category = "Render")
    var displayBackground = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Render Shadow", description = "Toggle the shadow of text in the timer.", category = "Render")
    var renderShadow = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Show in GUIs", description = "Show in GUIs instead of hiding in GUIs.", category = "Render")
    var showinGui = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Reset Timer When Exiting Worlds", description = "Reset the timer when a world is exited.", category = "General")
    var resetWhenWorldExit = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Turn on Timer When Entering Worlds", description = "Turn on the timer when entering worlds.", category = "General")
    var turnOnTimerWhenWorldEnter = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.NUMBER, name = "x", category = "Render", hidden = true)
    var x = 0

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.NUMBER, name = "y", category = "Render", hidden = true)
    var y = 0

    @Property(type = PropertyType.PARAGRAPH, name = "Credits", description = "This mod would not be possible without OSS projects and other forms of help. This page lists the people who helped make this mod.", category = "Credits")
    var credits = ""

    @Property(type = PropertyType.TEXT, name = "LlamaLad7 and conor", description = "For helping on how to count.", category = "Credits")
    var credits1 = ""

    @Property(type = PropertyType.TEXT, name = "1fxe", description = "For many parts of the GUI.", category = "Credits")
    var credits2 = ""

    @Property(type = PropertyType.TEXT, name = "pinkulu", description = "For the update checker.", category = "Credits")
    var credits3 = ""

    @Property(type = PropertyType.TEXT, name = "chachy", description = "For fixing parts of the build.gradle in v1.5.0.", category = "Credits")
    var credits4 = ""

}