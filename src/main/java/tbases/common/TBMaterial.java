package tbases.common;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import tbases.init.TBItems;

public class TBMaterial {

    public static Item.ToolMaterial

     thauminite = EnumHelper.addToolMaterial("thauminite", 2, 964, 8F, 4.8F, 25).setRepairItem(new ItemStack(TBItems.thauminite_ingot));

}
