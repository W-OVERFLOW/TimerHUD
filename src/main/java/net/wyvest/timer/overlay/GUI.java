package net.wyvest.timer.overlay;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumChatFormatting;
import net.wyvest.lib.util.GuiHelper;
import net.wyvest.timer.Timer;
import net.wyvest.timer.config.TimerConfig;

import java.io.IOException;

/**
 * @author Filip, Wyvest
 */

public class GUI extends GuiScreen {
    private boolean dragging;
    private int prevX, prevY;

    @Override
    public void initGui() {
        this.buttonList.add(new GuiButton(0, this.width / 2 - 50, this.height - 20, 100, 20, "Close"));
        this.buttonList.add(new GuiButton(1, this.width - 80, 0, 80, 20, "Config Editor"));
        super.initGui();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        switch (button.id) {
            case 0:
                Minecraft.getMinecraft().displayGuiScreen(null);
                break;
            case 1:
                GuiHelper.open(TimerConfig.INSTANCE.gui());
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        updatePos(mouseX, mouseY);
        HUD.drawTimer(Timer.instance.secondsPassed);
        int scale = 3;
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 0);
        drawCenteredString(this.fontRendererObj, EnumChatFormatting.GREEN + "TimerHUD", width / 2 / scale, 5 / scale + 10, -1);
        GlStateManager.popMatrix();
        scale = 1;
        GlStateManager.pushMatrix();
        GlStateManager.scale(scale, scale, 0);
        drawCenteredString(this.fontRendererObj, EnumChatFormatting.WHITE + "(drag hud to edit position!)", width / 2 / scale, 5 / scale + 55, -1);
        GlStateManager.popMatrix();

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        this.prevX = mouseX;
        this.prevY = mouseY;
        if (mouseButton == 0) {
            this.dragging = true;
        }
    }

    private void updatePos(int x, int y) {
        if (this.dragging) {
            TimerConfig.x = this.prevX - 10;
            TimerConfig.y = this.prevY - 10;
        }
        this.prevX = x;
        this.prevY = y;
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        super.mouseReleased(mouseX, mouseY, state);
        this.dragging = false;
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    @Override
    public void onGuiClosed() {
        TimerConfig.INSTANCE.markDirty();
        TimerConfig.INSTANCE.writeData();
        super.onGuiClosed();
    }

}