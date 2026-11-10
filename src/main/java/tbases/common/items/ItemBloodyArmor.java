package tbases.common.items;

import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import tbases.init.TBItems;
import thaumcraft.api.items.IVisDiscountGear;

import javax.annotation.Nullable;
import java.util.UUID;

public class ItemBloodyArmor extends ItemArmor implements IVisDiscountGear {
    int aType;
    static final int[] discount = new int[]{6, 5, 4, 3};

    public ItemBloodyArmor(ItemArmor.ArmorMaterial mat, int renderIndexIn, int aType, EntityEquipmentSlot equipmentSlotIn) {
        super(mat, 0, equipmentSlotIn);
        TBItems.bloodyA = mat;
        this.aType = aType;
    }

    public int getVisDiscount(ItemStack itemStack, EntityPlayer entityPlayer) {
        return discount[this.aType];
    }

    @Nullable
    public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type) {
        if (stack.getItem() != TBItems.bloodychest && stack.getItem() != TBItems.bloodyboots) {
            if (stack.getItem() == TBItems.bloodylegs) {
                return type == null ? "thaumicbases:textures/items/armor/bloody/bloody_2.png" : "thaumcraft:textures/entity/armor/robes_2_overlay.png";
            } else {
                return type == null ? "thaumicbases:textures/items/armor/bloody/bloody_1.png" : "thaumcraft:textures/entity/armor/robes_1_overlay.png";
            }
        } else {
            return type == null ? "thaumicbases:textures/items/armor/bloody/bloody_1.png" : "thaumcraft:textures/entity/armor/robes_1_overlay.png";
        }
    }

    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) {
        return super.getIsRepairable(toRepair, repair) || repair.getItem() == TBItems.bloodycloth;
    }

    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap<String, AttributeModifier> map = super.getAttributeModifiers(slot, stack);
        if (stack.getItem() == TBItems.bloodychest && slot == EntityEquipmentSlot.CHEST) {
            map.put(SharedMonsterAttributes.MAX_HEALTH.getName(), new AttributeModifier(UUID.fromString("96042c45-dfe3-4366-b93b-84663c4d828d"), "MAX HEALTH", (double)0.4F, 2));
        }

        if (stack.getItem() == TBItems.bloodylegs && slot == EntityEquipmentSlot.LEGS) {
            map.put(SharedMonsterAttributes.KNOCKBACK_RESISTANCE.getName(), new AttributeModifier(UUID.fromString("e4e1d8b2-87f2-44f5-8f24-e1876060a04c"), "KNOCKBACK RESISTANCE", (double)1.0F, 2));
        }

        if (stack.getItem() == TBItems.bloodyboots && slot == EntityEquipmentSlot.FEET) {
            map.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID.fromString("f6d1384c-74c3-4cce-9a80-11b91dbd4ff4"), "MOVEMENT SPEED", (double)0.5F, 2));
        }

        return map;
    }
}
