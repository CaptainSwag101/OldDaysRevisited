package com.jpmac26.olddays.client.gui.settings;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

/**
 * Created by James Pelster on 7/20/2016.
 */
public class GuiGameplaySettings extends GuiScreen {

    private final GuiScreen parentGuiScreen;

    public GuiGameplaySettings(GuiScreen parent) {
        this.parentGuiScreen = parent;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void initGui() {
        this.buttonList.clear();

        //this.buttonList.add(new GuiButton(1, this.width / 2 - 155, this.height / 6 + 24 - 6, 150, 20, I18n.format("Old EndermanSettings Sounds") + ": " + (SoundSettings.getOldEndermanSounds() ? "On" : "Off")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 180 - 6, I18n.format("Back to Menu")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 3) {
            this.mc.displayGuiScreen(parentGuiScreen);
        }
    }
}
