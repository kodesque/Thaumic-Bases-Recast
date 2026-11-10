package tbases.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentElderKnowledge extends Enchantment {
    public EnchantmentElderKnowledge(Enchantment.Rarity rarityIn) {
        super(rarityIn, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
    }

    public int getMaxLevel() {
        return 5;
    }

    public int getMinEnchantability(int lvl) {
        return 1 + lvl * 5;
    }

    public boolean canApplyTogether(Enchantment ench) {
        return !(ench instanceof EnchantmentElderKnowledge) && !(ench instanceof EnchantmentTainted);
    }

    public boolean isAllowedOnBooks() {
        return true;
    }
}
