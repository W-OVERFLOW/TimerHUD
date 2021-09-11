package xyz.queffe.timerhud.config

import gg.essential.api.EssentialAPI
import gg.essential.vigilance.Vigilant
import gg.essential.vigilance.data.Category
import gg.essential.vigilance.data.Property
import gg.essential.vigilance.data.PropertyType
import gg.essential.vigilance.data.SortingBehavior
import xyz.queffe.timerhud.TimerHUD
import xyz.queffe.timerhud.TimerHUD.NAME
import xyz.queffe.timerhud.TimerHUD.mc
import xyz.queffe.timerhud.gui.DownloadConfirmGui
import xyz.queffe.timerhud.gui.HUDGui
import xyz.queffe.timerhud.utils.Updater
import java.awt.Color
import java.io.File

object TimerConfig : Vigilant(File(TimerHUD.modDir, "${TimerHUD.ID}.toml"), NAME, sortingBehavior = ConfigSorting) {

    @Property(
        type = PropertyType.SWITCH,
        name = "Turn Timer On When World Entered",
        description = "Automatically turn the timer on when entering a world.",
        category = "General"
    )
    var turnTimerWorldEnter = true

    @Property(
        type = PropertyType.SWITCH,
        name = "Turn Timer Off When World Leave",
        description = "Automatically turn the timer off when leaving a world.",
        category = "General"
    )
    var turnTimerWorldLeave = true

    @Property(
        type = PropertyType.BUTTON,
        name = "Edit HUD",
        description = "Edit the position of the Timer HUD.",
        category = "Render"
    )
    fun openHUD() {
        EssentialAPI.getGuiUtil().openScreen(HUDGui(gui()))
    }

    @Property(
        type = PropertyType.SWITCH,
        name = "Render Nothing",
        description = "Render nothing when the timer is off.",
        category = "Render"
    )
    var renderNothing = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Show In GUIs",
        description = "Show the Timer when a GUI is opened.",
        category = "Render"
    )
    var showInGuis = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Show In Debug HUD",
        description = "Show the Timer when the Debug HUD (F3) is opened.",
        category = "Render"
    )
    var showInDebug = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Show In Chat",
        description = "Show the Timer when the chat is opened.",
        category = "Render"
    )
    var showInChat = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Render Shadow",
        description = "Toggle the shadow of text in the timer.",
        category = "Render",
        subcategory = "Text"
    )
    var renderShadow = true

    @Property(
        type = PropertyType.COLOR,
        name = "Text Color",
        description = "Change the text color for the HUD.",
        category = "Render",
        subcategory = "Text"
    )
    var color: Color = Color.WHITE

    @Property(
        type = PropertyType.SWITCH,
        name = "Toggle Chroma",
        description = "Turn on Chroma. This overrides Text Color when enabled.",
        category = "Render",
        subcategory = "Text"
    )
    var chroma = false

    @Property(
        type = PropertyType.SWITCH,
        name = "Display Background",
        description = "Toggle the background of the HUD.",
        category = "Render",
        subcategory = "Background"
    )
    var displayBackground = false

    @Property(
        type = PropertyType.NUMBER,
        name = "Padding Amount",
        description = "Change the amount of padding added to the background.",
        category = "Render",
        subcategory = "Background",
        min = 0,
        max = 10
    )
    var padding = 2

    @Property(
        type = PropertyType.COLOR,
        name = "Background Color",
        description = "Change the text color for the HUD.",
        category = "Render",
        subcategory = "Background"
    )
    var backgroundColor: Color = Color(0, 0, 0, 128)

    @Property(type = PropertyType.NUMBER, name = "x", category = "Render", hidden = true)
    var x = 0

    @Property(type = PropertyType.NUMBER, name = "y", category = "Render", hidden = true)
    var y = 0

    @Property(
        type = PropertyType.SWITCH,
        name = "Show Update Notification",
        description = "Show a notification when you start Minecraft informing you of new updates.",
        category = "Updater"
    )
    var showUpdateNotification = true

    @Property(
        type = PropertyType.BUTTON,
        name = "Update Now",
        description = "Update $NAME by clicking the button.",
        category = "Updater"
    )
    fun update() {
        if (Updater.shouldUpdate) EssentialAPI.getGuiUtil()
            .openScreen(DownloadConfirmGui(mc.currentScreen)) else EssentialAPI.getNotifications()
            .push(NAME, "No update had been detected at startup, and thus the update GUI has not been shown.")
    }

    init {
        initialize()
    }

    private object ConfigSorting : SortingBehavior() {
        override fun getCategoryComparator(): Comparator<in Category> {
            return Comparator { o1, o2 ->
                if (o1.name == "General") return@Comparator -1
                if (o2.name == "General") return@Comparator 1
                else compareValuesBy(o1, o2) {
                    it.name
                }
            }
        }
    }
}