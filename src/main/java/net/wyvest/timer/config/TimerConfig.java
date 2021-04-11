package net.wyvest.timer.config;

import club.sk1er.vigilance.Vigilant;
import club.sk1er.vigilance.data.Property;
import club.sk1er.vigilance.data.PropertyType;

import java.io.File;

@SuppressWarnings("unused")
public class TimerConfig extends Vigilant {

    @Property(
            type = PropertyType.SWITCH, name = "Toggle Mod",
            description = "Toggle the mod.",
            category = "General"
    )
    public static boolean modToggled;

    @Property(
            type = PropertyType.SWITCH, name = "Render Nothing",
            description = "Render nothing when the timer is off.",
            category = "Render"
    )
    public static boolean renderNothing;

    @Property(
            type = PropertyType.SELECTOR, name = "Text Color",
            description = "Change the text color for the HUD.",
            category = "Render",
            options = {"White", "Light Gray", "Gray", "Dark Gray", "Black", "Red", "Pink", "Orange", "Yellow", "Green", "Magenta", "Cyan", "Blue", "Chroma"}
    )
    public static int textColor;

    @Property(
            type = PropertyType.SWITCH, name = "Display Background",
            description = "Toggle the background of the HUD.",
            category = "Render"
    )
    public static boolean displayBackground;

    @Property(
            type = PropertyType.SWITCH, name = "Render Shadow",
            description = "Toggle the shadow of text in the timer.",
            category = "Render"
    )
    public static boolean renderShadow;

    @Property(
            type = PropertyType.SWITCH, name = "Pad Sides",
            description = "Pad the sides of the HUD.",
            category = "Render")
    public static boolean hasPadding;

    @Property(type = PropertyType.NUMBER, name = "x", category = "Render", hidden = true)
    public static int x;

    @Property(type = PropertyType.NUMBER, name = "y", category = "Render", hidden = true)
    public static int y;

    public TimerConfig() {
        super(new File("./config/timer.toml"));
        initialize();
    }
}
