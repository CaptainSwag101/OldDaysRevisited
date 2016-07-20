package olddays.client.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

/**
 * Created by James Pelster on 7/14/2016.
 */
public class GuiHandler implements IGuiHandler {
    public static final int GUI_CONFIG_MAIN = 0;

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == GUI_CONFIG_MAIN)
            return new GuiConfigMain();
        return null;
    }
}
