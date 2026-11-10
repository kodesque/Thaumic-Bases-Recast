package tbases.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public abstract class ContainerInventory extends Container {
    public final IInventory inv;
    public final InventoryPlayer pInv;
    public final TileEntity tile;
    public final Entity entity;
    public final EntityPlayer player;
    public int sizeInventory;
    public int pInvOffsetX;
    public int pInvOffsetZ;

    public ContainerInventory(InventoryPlayer playerInv, TileEntity tileInv) {
        this.inv = (IInventory)tileInv;
        this.pInv = playerInv;
        this.tile = tileInv;
        this.player = playerInv.player;
        this.entity = null;
        this.sizeInventory = this.inv.getSizeInventory();
        this.inv.openInventory(playerInv.player);
        this.setupSlots();
    }

    public ContainerInventory(InventoryPlayer playerInv, Entity entityInv) {
        this.inv = (IInventory)entityInv;
        this.pInv = playerInv;
        this.entity = entityInv;
        this.player = playerInv.player;
        this.tile = null;
        this.sizeInventory = this.inv.getSizeInventory();
        this.inv.openInventory(playerInv.player);
        this.setupSlots();
    }

    public ContainerInventory(InventoryPlayer playerInv, IInventory inv) {
        this.inv = inv;
        this.pInv = playerInv;
        this.player = playerInv.player;
        this.tile = null;
        this.entity = null;
        this.sizeInventory = inv.getSizeInventory();
        inv.openInventory(playerInv.player);
        this.setupSlots();
    }

    public ContainerInventory(EntityPlayer player, TileEntity tileInv) {
        this(player.inventory, tileInv);
    }

    public ContainerInventory(EntityPlayer player, Entity entityInv) {
        this(player.inventory, entityInv);
    }

    public ContainerInventory(EntityPlayer player, IInventory inv) {
        this(player.inventory, inv);
    }

    public abstract void setupSlots();

    public void setupPlayerInventory() {
        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(this.pInv, j + i * 9 + 9, 8 + j * 18 + this.pInvOffsetX, 84 + i * 18 + this.pInvOffsetZ));
            }
        }

        for(int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(this.pInv, i, 8 + i * 18 + this.pInvOffsetX, 142 + this.pInvOffsetZ));
        }

    }

    public boolean canInteractWith(EntityPlayer player) {
        return this.inv.isUsableByPlayer(player);
    }

    public void onContainerClosed(EntityPlayer player) {
        super.onContainerClosed(player);
        this.inv.closeInventory(player);
    }

    public ItemStack transferStackInSlot(EntityPlayer entityPlayer, int slotIndex) {
        ItemStack newItemStack = ItemStack.EMPTY;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack = slot.getStack();
            newItemStack = itemStack.copy();
            if (slotIndex < this.sizeInventory) {
                if (!this.mergeItemStack(itemStack, this.sizeInventory, this.inventorySlots.size(), false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemStack, 0, this.sizeInventory, false)) {
                return ItemStack.EMPTY;
            }

            if (itemStack.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return newItemStack;
    }

    public boolean mergeItemStack(ItemStack itemStack, int slotMin, int slotMax, boolean ascending) {
        boolean slotFound = false;
        int currentSlotIndex = ascending ? slotMax - 1 : slotMin;
        if (itemStack.isStackable()) {
            for(; itemStack.getCount() > 0 && (!ascending && currentSlotIndex < slotMax || ascending && currentSlotIndex >= slotMin); currentSlotIndex += ascending ? -1 : 1) {
                Slot slot = (Slot)this.inventorySlots.get(currentSlotIndex);
                ItemStack stackInSlot = slot.getStack();
                if (slot.isItemValid(itemStack) && equalsIgnoreStackSize(itemStack, stackInSlot)) {
                    int combinedStackSize = stackInSlot.getCount() + itemStack.getCount();
                    int slotStackSizeLimit = Math.min(stackInSlot.getMaxStackSize(), slot.getSlotStackLimit());
                    if (combinedStackSize <= slotStackSizeLimit) {
                        itemStack.setCount(0);
                        stackInSlot.setCount(combinedStackSize);
                        slot.onSlotChanged();
                        slotFound = true;
                    } else if (stackInSlot.getCount() < slotStackSizeLimit) {
                        itemStack.shrink(slotStackSizeLimit - stackInSlot.getCount());
                        stackInSlot.setCount(slotStackSizeLimit);
                        slot.onSlotChanged();
                        slotFound = true;
                    }
                }
            }
        }

        if (itemStack.getCount() > 0) {
            for(int var11 = ascending ? slotMax - 1 : slotMin; !ascending && var11 < slotMax || ascending && var11 >= slotMin; var11 += ascending ? -1 : 1) {
                Slot slot = (Slot)this.inventorySlots.get(var11);
                ItemStack stackInSlot = slot.getStack();
                if (slot.isItemValid(itemStack) && stackInSlot.isEmpty()) {
                    slot.putStack(cloneItemStack(itemStack, Math.min(itemStack.getCount(), slot.getSlotStackLimit())));
                    slot.onSlotChanged();
                    if (!slot.getStack().isEmpty()) {
                        itemStack.shrink(slot.getStack().getCount());
                        slotFound = true;
                    }
                    break;
                }
            }
        }

        return slotFound;
    }

    public static ItemStack cloneItemStack(ItemStack itemStack, int stackSize) {
        ItemStack clonedItemStack = itemStack.copy();
        clonedItemStack.setCount(stackSize);
        return clonedItemStack;
    }

    public static boolean equalsIgnoreStackSize(ItemStack itemStack1, ItemStack itemStack2) {
        if (!itemStack1.isEmpty() && !itemStack2.isEmpty() && itemStack1.getItem().equals(itemStack2.getItem()) && itemStack1.getItemDamage() == itemStack2.getItemDamage()) {
            if (itemStack1.hasTagCompound() && itemStack2.hasTagCompound()) {
                if (ItemStack.areItemStackTagsEqual(itemStack1, itemStack2)) {
                    return true;
                }
            } else if (!itemStack1.hasTagCompound() && !itemStack2.hasTagCompound()) {
                return true;
            }
        }

        return false;
    }
}
