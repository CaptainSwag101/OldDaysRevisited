package olddays.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;

import java.io.IOException;

/**
 * Created by James Pelster on 7/14/2016.
 */
public class GuiConfigMain extends GuiScreen {
    private GuiButton ActionsButton;
    //private GuiButton BugsButton;
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
        /* ActionsButton = new GuiButton(1, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("module.actions", new Object[0]));
        BugsButton = new GuiButton(2, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, I18n.format("module.bugs", new Object[0]));
        GameplayButton = new GuiButton(3, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format("module.gameplay", new Object[0]));
        MobsButton = new GuiButton(4, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("module.mobs", new Object[0]));
        EyecandyButton = new GuiButton(5, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("module.eyecandy", new Object[0]));
        SoundsButton = new GuiButton(6, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("module.sounds", new Object[0]));
        CraftingButton = new GuiButton(7, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, I18n.format("module.crafting", new Object[0]));
        TexturesButton = new GuiButton(8, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, I18n.format("module.textures", new Object[0]));
        WorldButton = new GuiButton(9, this.width / 2 - 155 + 9 % 2 * 160, this.height / 6 - 12 + 24 * (9 >> 1), I18n.format("module.nbxlite", new Object[0])); */
        ActionsButton = new GuiButton(1, this.width / 2 - 155, this.height / 6 + 24 - 6, 150, 20, I18n.format("module.actions", new Object[0]));
        GameplayButton = new GuiButton(2, this.width / 2 + 5, this.height / 6 + 24 - 6, 150, 20, I18n.format("module.gameplay", new Object[0]));
        MobsButton = new GuiButton(3, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("module.mobs", new Object[0]));
        EyecandyButton = new GuiButton(4, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, I18n.format("module.eyecandy", new Object[0]));
        SoundsButton = new GuiButton(5, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format("module.sounds", new Object[0]));
        CraftingButton = new GuiButton(6, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("module.crafting", new Object[0]));
        TexturesButton = new GuiButton(7, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, I18n.format("module.textures", new Object[0]));
        WorldButton = new GuiButton(8, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, I18n.format("module.nbxlite", new Object[0]));

        this.buttonList.add(ActionsButton);
        //this.buttonList.add(BugsButton);
        this.buttonList.add(GameplayButton);
        this.buttonList.add(MobsButton);
        this.buttonList.add(EyecandyButton);
        this.buttonList.add(SoundsButton);
        this.buttonList.add(CraftingButton);
        this.buttonList.add(TexturesButton);
        this.buttonList.add(WorldButton);

        //initialize "vanilla" buttons
        this.buttonList.add(new GuiButton(9, this.width / 2 - 100, this.height / 6 + 180 - 6, I18n.format("menu.returnToGame", new Object[0])));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
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
        if (button.id == 3) { //Mobs Button
            this.mc.displayGuiScreen(new GuiConfigMobs(this));
        }
        if (button.id == 4) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 6) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 7) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 8) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
        if (button.id == 9) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
    }
}
