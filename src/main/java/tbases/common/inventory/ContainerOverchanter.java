package tbases.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;
import tbases.util.ContainerInventory;

public class ContainerOverchanter extends ContainerInventory {
    public ContainerOverchanter(InventoryPlayer playerInv, TileEntity tileInv) {
        super(playerInv, tileInv);
    }

    public void setupSlots() {
        this.addSlotToContainer(new Slot(this.inv, 0, 80, 20));
        this.setupPlayerInventory();
    }

    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.inv.isUsableByPlayer(playerIn);
    }
}
