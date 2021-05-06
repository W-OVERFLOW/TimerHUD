@file:Suppress("unused")
package net.wyvest.timer.config

import club.sk1er.vigilance.Vigilant
import club.sk1er.vigilance.data.Category
import club.sk1er.vigilance.data.Property
import club.sk1er.vigilance.data.PropertyType
import club.sk1er.vigilance.data.SortingBehavior
import java.awt.Color
import java.io.File

/**
 * @author Wyvest
 */

object TimerConfig : Vigilant(File("./config/timerhud.toml"), "TimerHUD", sortingBehavior = ConfigSorting)  {

    @Property(type = PropertyType.PARAGRAPH, name = "Info", description = "You are using TimerHUD Version 2.0.0, made by Wyvest.", category = "Information")
    var paragraph = ""

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Toggle Mod", description = "Toggle the mod.", category = "General")
    var modToggled = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Render Nothing", description = "Render nothing when the timer is off.", category = "Render")
    var renderNothing = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.COLOR, name = "Text Color", description = "Change the text color for the HUD.", category = "Render", subcategory = "Color")
    var color: Color = Color.WHITE

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Turn on Chroma", description = "Turn on Chroma. This overrides Text Color.", category = "Render", subcategory = "Color")
    var chroma = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Display Background", description = "Toggle the background of the HUD.", category = "Render")
    var displayBackground = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Render Shadow", description = "Toggle the shadow of text in the timer.", category = "Render")
    var renderShadow = true

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Show in GUIs", description = "Show in GUIs instead of hiding in GUIs.", category = "Render")
    var showinGui = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.SWITCH, name = "Turn on Timer When Entering Worlds", description = "Turn on the timer when entering worlds.", category = "General")
    var turnOnTimerWhenWorldEnter = false

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.NUMBER, name = "x", category = "Render", hidden = true)
    var x = 0

    @kotlin.jvm.JvmField
    @Property(type = PropertyType.NUMBER, name = "y", category = "Render", hidden = true)
    var y = 0

    @Property(type = PropertyType.PARAGRAPH, name = "LlamaLad7 and conor", description = "For helping on how to count.", category = "Information", subcategory = "Credits")
    var credits1 = ""

    @Property(type = PropertyType.PARAGRAPH, name = "1fxe", description = "For many parts of the GUI.", category = "Information", subcategory = "Credits")
    var credits2 = ""

    @Property(type = PropertyType.PARAGRAPH, name = "pinkulu", description = "For the update checker.", category = "Information", subcategory = "Credits")
    var credits3 = ""

    @Property(type = PropertyType.PARAGRAPH, name = "chachy", description = "For fixing parts of the build.gradle in v1.5.0.", category = "Information", subcategory = "Credits")
    var credits4 = ""

    init {
        initialize()
        setCategoryDescription(
            "General",
            "This category is for configuring general parts of the timer."
        )
        setCategoryDescription(
            "Render",
            "This category is for configuring the HUD of the timer."
        )
        setCategoryDescription(
            "Information",
            "This category is for general information about the mod."
        )
        setSubcategoryDescription(
            "Render",
            "Color",
            "This subcategory is for configuring the color of the HUD."
        )
        setSubcategoryDescription(
            "Information",
            "Credits",
            "This mod would not be possible without OSS projects and other forms of help. This page lists the people who helped make this mod."
        )
    }

    private object ConfigSorting : SortingBehavior() {
        override fun getCategoryComparator(): Comparator<in Category> {
            return Comparator { o1, o2 ->
                if (o1.name == "General") return@Comparator -1
                if (o2.name == "General") return@Comparator 1
                else compareValuesBy(o2,o1) {
                    it.name
                }
            }
        }
    }

}