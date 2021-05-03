
package net.wyvest.timer.overlay;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.wyvest.lib.util.ChromaUtils;
import net.wyvest.timer.TimerHUD;
import net.wyvest.timer.config.TimerConfig;


/**
 * @author Filip, Wyvest
 */
@Getter
public class HUD {
    @Getter public static HUD instance;
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final FontRenderer fontRenderer = mc.fontRendererObj;


    public HUD() {
        instance = this;
    }

    public static void drawTimer(Integer seconds) {
        GlStateManager.pushMatrix();
        int x = TimerConfig.x;
        int y = TimerConfig.y + 7;
        int height = 10;
        String text;

        if (!TimerConfig.modToggled) {
            return;
        }

        if (!TimerHUD.INSTANCE.isRunning()) {
            if (TimerConfig.renderNothing) {
                text = "";
            } else {
                text = "0s";
            }
        } else {
            text = seconds + "s";
        }



        if (TimerConfig.modToggled) {
            if (TimerConfig.oneColorChroma) {
                ChromaUtils.drawChromaString(fontRenderer, text, Float.parseFloat(String.valueOf(x + 5)), Float.parseFloat(String.valueOf(y)), TimerConfig.renderShadow);
            }else {
                fontRenderer.drawString(text, x + 5,
                        y,
                        TimerConfig.chroma ? ChromaUtils.timeChroma() : TimerConfig.color.getRGB(), TimerConfig.renderShadow);

            }
        }


        if (TimerConfig.displayBackground) {
            GlStateManager.translate(1.0, 1.0, -100);
            Gui.drawRect(x - 1, y - 3, x + fontRenderer.getStringWidth(text) + 8, y + height, Integer.MIN_VALUE);
            GlStateManager.translate(1.0, 1.0, 0);
        }


        GlStateManager.popMatrix();
    }




}



