package tbases.common.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tbases.api.ITobacco;

import javax.annotation.Nullable;

public class ItemSmokingPipe extends Item {
    public boolean isSilverwood;

    public ItemSmokingPipe(boolean silverwood) {
        this.isSilverwood = silverwood;
        this.setFull3D();
        this.setMaxStackSize(1);
        this.addPropertyOverride(new ResourceLocation("pull"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                if (entityIn == null) {
                    return 0.0F;
                } else {
                    return !(entityIn.getActiveItemStack().getItem() instanceof ItemSmokingPipe) ? 0.0F : (float)(stack.getMaxItemUseDuration() - entityIn.getItemInUseCount()) / 20.0F;
                }
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), new IItemPropertyGetter() {
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
                return entityIn != null && entityIn.isHandActive() && entityIn.getActiveItemStack() == stack ? 1.0F : 0.0F;
            }
        });
    }

    public ItemStack findTobacco(EntityPlayer smoker) {
        if (this.isTobacco(smoker.getHeldItem(EnumHand.OFF_HAND))) {
            return smoker.getHeldItem(EnumHand.OFF_HAND);
        } else if (this.isTobacco(smoker.getHeldItem(EnumHand.MAIN_HAND))) {
            return smoker.getHeldItem(EnumHand.MAIN_HAND);
        } else {
            for(int i = 0; i < smoker.inventory.getSizeInventory(); ++i) {
                ItemStack itemstack = smoker.inventory.getStackInSlot(i);
                if (this.isTobacco(itemstack)) {
                    return itemstack;
                }
            }

            return ItemStack.EMPTY;
        }
    }

    protected boolean isTobacco(ItemStack stack) {
        return stack.getItem() instanceof ItemTobacco;
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer player, EnumHand handIn) {
        ItemStack itemstack = player.getHeldItem(handIn);
        boolean flag = !this.findTobacco(player).isEmpty();
        if (!flag) {
            return new ActionResult(EnumActionResult.FAIL, itemstack);
        } else {
            player.setActiveHand(handIn);
            return new ActionResult(EnumActionResult.SUCCESS, itemstack);
        }
    }

    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase entityLiving) {
        if (entityLiving instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)entityLiving;
            ItemStack tobacco = this.findTobacco(player);
            if (!tobacco.isEmpty()) {
                ITobacco t = (ITobacco)tobacco.getItem();
                t.performTobaccoEffect(player, tobacco, this.isSilverwood);
                tobacco.shrink(1);
                Vec3d look = entityLiving.getLookVec();

                for(int j = 0; j < 100; ++j) {
                    double x = player.posX + look.x / (double)5.0F;
                    double y = player.posY + (double)player.getEyeHeight() + look.y / (double)5.0F;
                    double z = player.posZ + look.z / (double)5.0F;
                    player.world.spawnParticle(this.isSilverwood ? EnumParticleTypes.EXPLOSION_NORMAL : EnumParticleTypes.SMOKE_NORMAL, x, y, z, look.x / (double)10.0F, look.y / (double)10.0F, look.z / (double)10.0F, new int[0]);
                }
            }
        }

        return stack;
    }

    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BOW;
    }

    public int getMaxItemUseDuration(ItemStack stack) {
        return 64;
    }

    public void onUsingTick(ItemStack stack, EntityLivingBase entityLiving, int count) {
        Vec3d look = entityLiving.getLookVec();
        double x = entityLiving.posX + look.x / (double)5.0F;
        double y = entityLiving.posY + (double)entityLiving.getEyeHeight() + look.y / (double)5.0F;
        double z = entityLiving.posZ + look.z / (double)5.0F;
        if (count < 32) {
            entityLiving.world.spawnParticle(this.isSilverwood ? EnumParticleTypes.EXPLOSION_NORMAL : EnumParticleTypes.SMOKE_NORMAL, x, y, z, look.x / (double)10.0F, look.y / (double)10.0F, look.z / (double)10.0F, new int[0]);
        }

    }
}
