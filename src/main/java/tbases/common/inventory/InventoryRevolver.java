package tbases.common.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.blocks.essentia.BlockJarItem;

public class InventoryRevolver implements IInventory {
    public ItemStack[] inventory = new ItemStack[1];
    public Container revolverContainer;

    public InventoryRevolver(Container revolver) {
        this.revolverContainer = revolver;
    }

    public int getSizeInventory() {
        return 1;
    }

    public boolean isEmpty() {
        return false;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.inventory[0];
    }

    public ItemStack decrStackSize(int slot, int amount) {
        if (this.inventory[0] != ItemStack.EMPTY) {
            if (this.inventory[0].getCount() <= amount) {
                ItemStack stk = this.inventory[0];
                this.inventory[0] = ItemStack.EMPTY;
                this.markDirty();
                return stk;
            } else {
                ItemStack var3 = this.inventory[0].splitStack(amount);
                if (this.inventory[0].getCount() == 0) {
                    this.inventory[0] = ItemStack.EMPTY;
                }

                this.markDirty();
                return var3;
            }
        } else {
            return ItemStack.EMPTY;
        }
    }

    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.inventory[0] = stack;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isUsableByPlayer(EntityPlayer player) {
        return true;
    }

    public boolean isItemValidForSlot(int slot, ItemStack jar) {
        if (!jar.isEmpty() && jar.getItem() instanceof BlockJarItem && jar.hasTagCompound()) {
            AspectList aspects = ((BlockJarItem)jar.getItem()).getAspects(jar);
            if (aspects != null && aspects.size() > 0 && aspects.getAmount(Aspect.AVERSION) > 0) {
                return true;
            }
        }

        return false;
    }

    public String getInventoryName() {
        return "container.revolver";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public void openInventory() {
    }

    public void closeInventory() {
    }

    public void markDirty() {
    }

    public String getName() {
        return this.getInventoryName();
    }

    public boolean hasCustomName() {
        return false;
    }

    public ITextComponent getDisplayName() {
        return new TextComponentString(this.getName());
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    public int getField(int id) {
        return 0;
    }

    public void setField(int id, int value) {
    }

    public int getFieldCount() {
        return 0;
    }

    public void clear() {
        this.inventory = new ItemStack[1];
    }

    public ItemStack removeStackFromSlot(int index) {
        if (this.inventory[0] != ItemStack.EMPTY) {
            ItemStack stk = this.inventory[0];
            this.inventory[0] = ItemStack.EMPTY;
            return stk;
        } else {
            return ItemStack.EMPTY;
        }
    }
}
