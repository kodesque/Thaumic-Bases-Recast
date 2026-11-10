package tbases.common.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.init.Enchantments;
import net.minecraft.inventory.EntityEquipmentSlot;

public class EnchantmentEldritchBane extends Enchantment {
    public EnchantmentEldritchBane(Enchantment.Rarity rarityIn) {
        super(rarityIn, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[]{EntityEquipmentSlot.MAINHAND});
    }

    public int getMaxLevel() {
        return 5;
    }

    public int getMinEnchantability(int enchantmentLevel) {
        return 1 + enchantmentLevel * 5;
    }

    public boolean canApplyTogether(Enchantment ench) {
        return this != ench && ench != Enchantments.SMITE && ench != Enchantments.BANE_OF_ARTHROPODS && ench != Enchantments.SHARPNESS && !(ench instanceof EnchantmentDamage);
    }
}
