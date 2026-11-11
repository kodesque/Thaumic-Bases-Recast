package tbases.util.research;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tbases.root.TBCore;

public class ResearchIcon {

    Object icon;

    ResearchIcon(ResourceLocation loc) {
        icon = loc;
    }

    ResearchIcon(ItemStack stack) {
        icon = stack;
    }

    ResearchIcon(Item item) {
        icon = item;
    }

    ResearchIcon(Block block) {
        icon = block;
    }

    public Object getIcon() {
        return icon;
    }

    public static ResourceLocation getNative(String asset) {
        return new ResourceLocation(TBCore.modid, "textures/research/" + asset + ".png");
    }
}
