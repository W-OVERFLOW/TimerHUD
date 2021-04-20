
package net.wyvest.timer.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.wyvest.timer.TimerMod;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.others.Color;


/**
 * @author Filip, Wyvest
 */

public class UI {
    private static final int textPadding = 5;
    public static UI instance;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final FontRenderer fontRenderer = mc.fontRendererObj;


    public UI() {
        instance = this;
    }

    public static void drawTimer(Integer seconds) {
        GlStateManager.pushMatrix();
        int x = TimerConfig.x;
        int y = TimerConfig.y + 7;
        int height = 10;
        String text;
        int color;

        if (!TimerConfig.modToggled) {
            return;
        }

        if (!TimerMod.getInstance().isRunning()) {
            if (TimerConfig.renderNothing) {
                text = "";
            } else {
                text = "No timer running";
            }
        } else {
            text = String.valueOf(seconds);
        }

        switch (TimerConfig.textColor) {
            default:
            case 0:
                color = Color.WHITE;
                break;
            case 1:
                color = Color.LIGHT_GRAY;
                break;
            case 2:
                color = Color.GRAY;
                break;
            case 3:
                color = Color.DARK_GRAY;
                break;
            case 4:
                color = Color.BLACK;
                break;
            case 5:
                color = Color.RED;
                break;
            case 6:
                color = Color.PINK;
                break;
            case 7:
                color = Color.ORANGE;
                break;
            case 8:
                color = Color.YELLOW;
                break;
            case 9:
                color = Color.GREEN;
                break;
            case 10:
                color = Color.MAGENTA;
                break;
            case 11:
                color = Color.CYAN;
                break;
            case 12:
                color = Color.BLUE;
                break;
            case 13:
                //honestly i have no idea why but chroma doesn't work if i put it in the interface
                color = java.awt.Color.HSBtoRGB(System.currentTimeMillis() % 2000L / 2000.0F, 0.8F, 0.8F);
        }

        if (TimerConfig.modToggled) {
                fontRenderer.drawString(text, x + textPadding,
                        y,
                        color, TimerConfig.renderShadow);

        }


        if (TimerConfig.displayBackground) {
            GlStateManager.translate(1.0, 1.0, -100);
            Gui.drawRect(x - 1, y - 3, x + fontRenderer.getStringWidth(text) + 8, y + height, Integer.MIN_VALUE);
            GlStateManager.translate(1.0, 1.0, 0);
        }


        GlStateManager.popMatrix();
    }




}



