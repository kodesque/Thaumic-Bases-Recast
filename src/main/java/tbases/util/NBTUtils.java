package tbases.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTUtils {

    public static void createNBTTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound itemTag = new NBTTagCompound();
            stack.setTagCompound(itemTag);
        }
    }

    public static NBTTagCompound getStackTag(ItemStack stack) {
        createNBTTag(stack);
        return stack.getTagCompound();
    }

}
