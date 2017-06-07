package com.jpmac26.olddays.client.gui;

import com.jpmac26.olddays.client.gui.settings.*;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

/**
 * Created by James Pelster on 7/14/2016.
 */
@SideOnly(Side.CLIENT)
public class GuiMainSettings extends GuiScreen {
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

    @Override
    /**
     * Draws either a gradient over the background screen (when it exists) or a flat gradient over background.png
     */
    public void drawDefaultBackground()
    {
        this.drawWorldBackground(0); // tint is always 0 as of 1.2.2
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(new net.minecraftforge.client.event.GuiScreenEvent.BackgroundDrawnEvent(this));
    }

    public void drawWorldBackground(int tint)
    {
        if (this.mc.world != null)
        {
            this.drawGradientRect(0, 0, this.width, this.height, -1072689136, -804253680);
            this.drawBackground(tint);
        }
        else
        {
            this.drawBackground(tint);
        }
    }

    /**
     * Draws the background (i is always 0 as of 1.2.2)
     */
    @Override
    public void drawBackground(int tint)
    {
        GlStateManager.disableLighting();
        GlStateManager.disableFog();
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        this.mc.getTextureManager().bindTexture(OPTIONS_BACKGROUND);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        float f = 32.0F;
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        vertexbuffer.pos(0.0D, (double)this.height, 0.0D).tex(0.0D, (double)((float)this.height / 32.0F + (float)tint)).color(64, 64, 64, 255).endVertex();
        vertexbuffer.pos((double)this.width, (double)this.height, 0.0D).tex((double)((float)this.width / 32.0F), (double)((float)this.height / 32.0F + (float)tint)).color(64, 64, 64, 255).endVertex();
        vertexbuffer.pos((double)this.width, 0.0D, 0.0D).tex((double)((float)this.width / 32.0F), (double)tint).color(64, 64, 64, 255).endVertex();
        vertexbuffer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, (double)tint).color(64, 64, 64, 255).endVertex();
        tessellator.draw();
    }

    /**
     * Adds the buttons (and other controls) to the screen in question. Called when the GUI is displayed and when the
     * window resizes, the buttonList is cleared beforehand.
     */
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
            this.mc.displayGuiScreen(new GuiActionSettings(this));
        }
        if (button.id == 2) {
            this.mc.displayGuiScreen(new GuiGameplaySettings(this));
        }
        if (button.id == 3) { //Mobs Button
            this.mc.displayGuiScreen(new GuiMobSettings(this));
        }
        if (button.id == 4) {
            this.mc.displayGuiScreen(new GuiEyecandySettings(this));
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiSoundSettings(this));
        }
        if (button.id == 6) {
            this.mc.displayGuiScreen(new GuiCraftingSettings(this));
        }
        if (button.id == 7) {
            this.mc.displayGuiScreen(new GuiTextureSettings(this));
        }
        if (button.id == 8) {
            this.mc.displayGuiScreen(new GuiWorldSettings(this));
        }
        if (button.id == 9) {
            this.mc.displayGuiScreen(null);
            if (this.mc.currentScreen == null)
                this.mc.setIngameFocus();
        }
    }
}
