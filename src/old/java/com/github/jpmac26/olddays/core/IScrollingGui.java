package com.github.jpmac26.olddays.client.gui;

import net.minecraft.client.gui.GuiButton;

public interface IScrollingGui{
    public int getContentHeight();

    public void scrolled();

    public int getTop();

    public int getBottom();

    public int getLeft();

    public int getRight();

    public void actionPerformedScrolling(GuiButton guibutton);
}