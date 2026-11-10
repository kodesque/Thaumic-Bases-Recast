package tbases.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface ITobacco {
    void performTobaccoEffect(EntityPlayer var1, ItemStack var2, boolean var3);
}