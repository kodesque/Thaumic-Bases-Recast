package tbases.init;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tbases.common.enchantments.EnchantmentElderKnowledge;
import tbases.common.enchantments.EnchantmentEldritchBane;
import tbases.common.enchantments.EnchantmentTainted;

public class TBEnchant {
    public static Enchantment tainted;
    public static Enchantment elderKnowledge;
    public static Enchantment eldritchBane;

    public TBEnchant() {
    }

    public static void setupEnchatments() {
        ForgeRegistries.ENCHANTMENTS.register(eldritchBane);
        ForgeRegistries.ENCHANTMENTS.register(elderKnowledge);
        ForgeRegistries.ENCHANTMENTS.register(tainted);
    }

    static {
        tainted = (Enchantment)(new EnchantmentTainted(Enchantment.Rarity.RARE)).setName("tainted").setRegistryName(new ResourceLocation("thaumicbases", "thaumicbases.tainted"));
        elderKnowledge = (Enchantment)(new EnchantmentElderKnowledge(Enchantment.Rarity.RARE)).setName("elderKnowledge").setRegistryName(new ResourceLocation("thaumicbases", "thaumicbases.elderKnowledge"));
        eldritchBane = (Enchantment)(new EnchantmentEldritchBane(Enchantment.Rarity.RARE)).setName("eldritchBane").setRegistryName(new ResourceLocation("thaumicbases", "thaumicbases.eldritchBane"));
    }
}
