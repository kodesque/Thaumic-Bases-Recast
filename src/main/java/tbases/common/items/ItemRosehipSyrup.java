package tbases.common.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.items.ItemsTC;

import java.util.ArrayList;
import java.util.List;

public class ItemRosehipSyrup extends Item {
    public ItemRosehipSyrup() {
        this.setMaxStackSize(16);
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (!worldIn.isRemote) {
            entityLiving.curePotionEffects(stack);
        }

        if (entityLiving instanceof EntityPlayer) {
            removeNegativeEffect(entityLiving);
            ThaumcraftApi.internalMethods.addWarpToPlayer((EntityPlayer)entityLiving, -4, IPlayerWarp.EnumWarpType.TEMPORARY);
        }

        if (entityLiving instanceof EntityPlayer && !((EntityPlayer)entityLiving).capabilities.isCreativeMode && !worldIn.isRemote) {
            EntityItem item = new EntityItem(worldIn, entityLiving.posX, entityLiving.posY, entityLiving.posZ, new ItemStack(ItemsTC.phial));
            stack.shrink(1);
            worldIn.spawnEntity(item);
        }

        return super.onItemUseFinish(stack, worldIn, entityLiving);
    }

    public static void removeNegativeEffect(EntityLivingBase entity) {
        List<Potion> potions = new ArrayList(entity.getActivePotionMap().keySet());
        potions.stream().filter(Potion::isBadEffect).forEach(entity::removeActivePotionEffect);
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 32;
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        playerIn.setActiveHand(handIn);
        return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
    }

    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    public ItemStack getContainerItem(ItemStack itemStack) {
        return new ItemStack(ItemsTC.phial);
    }

    public Item setContainerItem(Item containerItem) {
        return ItemsTC.phial;
    }
}