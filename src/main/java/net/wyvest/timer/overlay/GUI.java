package net.wyvest.timer.overlay;

import net.minecraft.client.gui.GuiScreen;
import net.wyvest.timer.TimerMod;
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
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        updatePos(mouseX, mouseY);
        UI.drawTimer(1);
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
        TimerMod.getInstance().config.markDirty();
        TimerMod.getInstance().config.writeData();
        super.onGuiClosed();
    }

}