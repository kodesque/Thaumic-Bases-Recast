package tbases.root;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import tbases.init.TBBlocks;

public class TBCreativeTabs extends CreativeTabs {
    public static TBCreativeTabs TB_CREATIVEtabs = new TBCreativeTabs();

    public TBCreativeTabs() {
        super("thaumicbases");
    }

    public ItemStack createIcon() {
        return new ItemStack(TBBlocks.overchanter);
    }

    public void displayAllRelevantItems(NonNullList<ItemStack> itemStacks) {
        super.displayAllRelevantItems(itemStacks);
    }
}
