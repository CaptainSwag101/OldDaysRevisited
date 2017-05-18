package olddays.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import olddays.settings.sound.SoundSettings;

import java.io.IOException;

/**
 * Created by James Pelster on 7/20/2016.
 */
public class GuiConfigSounds extends GuiScreen {

    private final GuiScreen parentGuiScreen;

    public GuiConfigSounds(GuiScreen parent) {
        this.parentGuiScreen = parent;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    public void initGui() {
        this.buttonList.clear();

        this.buttonList.add(new GuiButton(3, this.width / 2 - 100, this.height / 6 + 180 - 6, I18n.format("Back to Menu")));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {

        if (button.id == 3) {
            this.mc.displayGuiScreen(parentGuiScreen);
        }
    }
}
