package tbases.init;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import tbases.client.gui.GuiRevolver;
import tbases.client.gui.GuiThaumicAnvil;
import tbases.common.inventory.ContainerOverchanter;
import tbases.common.inventory.ContainerRevolver;
import tbases.common.inventory.ContainerThaumicAnvil;
import tbases.common.inventory.ContainerVoidAnvil;
import tbases.util.gui.GuiCommon;
import tbases.util.gui.GuiVoidAnvil;

public class TBGuiHandler implements IGuiHandler {
    public static int OVERCHANTER = 0;
    public static int THAUM_ANVIL = 1;
    public static int REVOLVER = 2;
    public static int VOID_ANVIL = 3;

    public TBGuiHandler() {
    }

    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == OVERCHANTER) {
            return new ContainerOverchanter(player.inventory, world.getTileEntity(new BlockPos(x, y, z)));
        } else if (ID == THAUM_ANVIL) {
            return new ContainerThaumicAnvil(player.inventory, world, new BlockPos(x, y, z), player);
        } else if (ID == REVOLVER) {
            return new ContainerRevolver(player.inventory, world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
        } else {
            return ID == VOID_ANVIL ? new ContainerVoidAnvil(player.inventory, world, new BlockPos(x, y, z), player) : null;
        }
    }

    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == OVERCHANTER) {
            return new GuiCommon(new ContainerOverchanter(player.inventory, world.getTileEntity(new BlockPos(x, y, z))));
        } else if (ID == THAUM_ANVIL) {
            return new GuiThaumicAnvil(player.inventory, world, x, y, z);
        } else if (ID == REVOLVER) {
            return new GuiRevolver(player.inventory, world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ());
        } else {
            return ID == VOID_ANVIL ? new GuiVoidAnvil(player.inventory, world, player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()) : null;
        }
    }
}
