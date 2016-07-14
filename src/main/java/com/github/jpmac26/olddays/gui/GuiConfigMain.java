package com.github.jpmac26.olddays.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

/**
 * Created by James Pelster on 7/14/2016.
 */
public class GuiConfigMain extends GuiScreen {
    private GuiButton ActionsButton;
    private GuiButton BugsButton;
    private GuiButton GameplayButton;
    private GuiButton MobsButton;
    private GuiButton EyecandyButton;
    private GuiButton SoundsButton;
    private GuiButton CraftingButton;
    private GuiButton TexturesButton;
    private GuiButton WorldButton;

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void initGui() {
        this.buttonList.clear();

        //initialize buttons leading to sub-menus
        ActionsButton = new GuiButton(1, this.width / 2 - 155 + 1 % 2 * 160, this.height / 6 - 12 + 24 * (1 >> 1), I18n.format("olddays:menu.ActionButton", new Object[0]));
        BugsButton = new GuiButton(2, this.width / 2 - 155 + 2 % 2 * 160, this.height / 6 - 12 + 24 * (2 >> 1), I18n.format("olddays:menu.BugsButton", new Object[0]));
        GameplayButton = new GuiButton(3, this.width / 2 - 155 + 3 % 2 * 160, this.height / 6 - 12 + 24 * (3 >> 1), I18n.format("olddays:menu.GameplayButton", new Object[0]));
        MobsButton = new GuiButton(4, this.width / 2 - 155 + 4 % 2 * 160, this.height / 6 - 12 + 24 * (4 >> 1), I18n.format("olddays:menu.MobsButton", new Object[0]));
        EyecandyButton = new GuiButton(5, this.width / 2 - 155 + 5 % 2 * 160, this.height / 6 - 12 + 24 * (5 >> 1), I18n.format("olddays:menu.EyecandyButton", new Object[0]));
        SoundsButton = new GuiButton(6, this.width / 2 - 155 + 6 % 2 * 160, this.height / 6 - 12 + 24 * (6 >> 1), I18n.format("olddays:menu.SoundsButton", new Object[0]));
        CraftingButton = new GuiButton(7, this.width / 2 - 155 + 7 % 2 * 160, this.height / 6 - 12 + 24 * (7 >> 1), I18n.format("olddays:menu.CraftingButton", new Object[0]));
        TexturesButton = new GuiButton(8, this.width / 2 - 155 + 8 % 2 * 160, this.height / 6 - 12 + 24 * (8 >> 1), I18n.format("olddays:menu.TexturesButton", new Object[0]));
        WorldButton = new GuiButton(9, this.width / 2 - 155 + 9 % 2 * 160, this.height / 6 - 12 + 24 * (9 >> 1), I18n.format("olddays:menu.WorldButton", new Object[0]));

        this.buttonList.add(ActionsButton);
        this.buttonList.add(BugsButton);
        this.buttonList.add(GameplayButton);
        this.buttonList.add(MobsButton);
        this.buttonList.add(EyecandyButton);
        this.buttonList.add(SoundsButton);
        this.buttonList.add(CraftingButton);
        this.buttonList.add(TexturesButton);
        this.buttonList.add(WorldButton);

        //initialize "vanilla" buttons
        this.buttonList.add(new GuiButton(10, this.width / 2 - 100, this.height / 4 + 120 + -16, I18n.format("menu.returnToMenu", new Object[0])));

        if (!this.mc.isIntegratedServerRunning())
        {
            this.buttonList.get(9).displayString = I18n.format("menu.disconnect", new Object[0]);
        }

        this.buttonList.add(new GuiButton(11, this.width / 2 - 100, this.height / 4 + 24 + -16, I18n.format("menu.returnToGame", new Object[0])));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 2) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 3) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 4) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 5) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 6) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 7) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 8) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 9) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 10) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 11) {
            //Main.packetHandler.sendToServer(...);
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
    }
}
