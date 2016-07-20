package olddays.client.gui;

import com.typesafe.config.ConfigException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import olddays.settings.MobSettings;

import java.io.IOException;

/**
 * Created by James Pelster on 7/19/2016.
 */
public class GuiConfigMobs extends GuiScreen {

    private final GuiScreen parentGuiScreen;

    public GuiConfigMobs(GuiScreen parent) {
        this.parentGuiScreen = parent;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void initGui() {
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(1, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, I18n.format("Un-nerfed Endermen") + ": " + (MobSettings.getUnnerfedEndermen() ? "On" : "Off")));
        this.buttonList.add(new GuiButton(2, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, I18n.format("Old Endermen") + ": " + (MobSettings.getOldEndermen() ? "On" : "Off")));
        this.buttonList.add(new GuiButton(3, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, I18n.format("Old Mob Health") + ": " + (MobSettings.getOldMobHealth() ? "On" : "Off")));
        this.buttonList.add(new GuiButton(4, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, I18n.format("Old Enderman Sounds") + ": " + (MobSettings.getOldEndermanSounds() ? "On" : "Off")));
        this.buttonList.add(new GuiButton(5, this.width / 2 - 100, this.height / 6 + 180 - 6, I18n.format("Back to Menu")));

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 1) {
            MobSettings.toggleUnnerfedEndermen();
            this.buttonList.get(0).displayString = I18n.format("Un-nerfed Endermen") + ": " + (MobSettings.getUnnerfedEndermen() ? "On" : "Off");
        }
        if (button.id == 2) {
            MobSettings.toggleOldEndermen();
            this.buttonList.get(1).displayString = I18n.format("Old Endermen") + ": " + (MobSettings.getOldEndermen() ? "On" : "Off");
        }
        if (button.id == 3) {
            MobSettings.toggleOldMobHealth();
            this.buttonList.get(2).displayString = I18n.format("Old Mob Health") + ": " + (MobSettings.getOldMobHealth() ? "On" : "Off");
        }
        if (button.id == 4) {
            MobSettings.toggleOldEndermanSounds();
            this.buttonList.get(3).displayString = I18n.format("Old Enderman Sounds") + ": " + (MobSettings.getOldEndermanSounds() ? "On" : "Off");
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(parentGuiScreen);
        }
    }
}
