package tbases.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import tbases.events.TBEventHandler;
import tbases.util.MathUtils;
import thaumcraft.api.aura.AuraHelper;
import thaumcraft.api.items.IRechargable;
import thaumcraft.api.items.RechargeHelper;
import thaumcraft.client.fx.FXDispatcher;
import thaumcraft.common.lib.SoundsTC;
import thaumcraft.common.lib.potions.PotionWarpWard;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ItemUkulele extends Item implements IRechargable {
    public static final String[] types = new String[]{"simple", "knowledge", "calming", "electric", "resistance", "buffing", "confusion", "growth", "love"};
    public static final int[] costs = new int[]{0, 1, 1, 0, 0, 20, 1, 0, 0};
    public static final int[] soundDelays = new int[]{500, 80, 400, 160, 180, 200, 320, 160, 200};

    public ItemUkulele() {
    }

    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedNameInefficiently(stack) + ".name." + this.getDamage(stack));
    }

    public EnumRarity getRarity(ItemStack itemstack) {
        return EnumRarity.RARE;
    }

    public void onUsingTick(ItemStack stack, EntityLivingBase player, int count) {
        Vec3d lookVec = player.getLookVec();
        int cost = (int)((double)costs[Math.min(stack.getItemDamage(), costs.length - 1)] * (double)0.75F);
        player.world.spawnParticle(EnumParticleTypes.NOTE, player.posX + lookVec.x / (double)5.0F + MathUtils.randomDouble(itemRand) / (double)2.0F, player.posY + (double)1.0F + lookVec.y / (double)2.0F + MathUtils.randomDouble(itemRand) / (double)10.0F + 0.1, player.posZ + lookVec.z / (double)2.0F + MathUtils.randomDouble(itemRand) / (double)5.0F, itemRand.nextDouble(), itemRand.nextDouble(), itemRand.nextDouble(), new int[0]);
        if (player.world.isRemote && TBEventHandler.clientUkuleleSoundPlayDelay <= 0) {
            TBEventHandler.clientUkuleleSoundPlayDelay = soundDelays[Math.min(stack.getItemDamage(), soundDelays.length - 1)];
            player.world.playSound((EntityPlayer)null, player.getPosition(), new SoundEvent(new ResourceLocation("thaumicbases", "guitar." + types[Math.min(stack.getItemDamage(), types.length - 1)])), SoundCategory.PLAYERS, 1.0F, 0.5F);
        }

        if (stack.getItemDamage() == 1 && RechargeHelper.getCharge(stack) > 0 && count % 80 == 0) {
            AxisAlignedBB aabb = new AxisAlignedBB(player.posX - (double)8.0F, player.posY - (double)8.0F, player.posZ - (double)8.0F, player.posX + (double)8.0F, player.posY + (double)8.0F, player.posZ + (double)8.0F);

            for(EntityPlayer p : player.world.getEntitiesWithinAABB(EntityPlayer.class, aabb)) {
                boolean addAspect = p == player ? true : p.world.rand.nextBoolean();
                if (addAspect) {
                    int amount = p.world.rand.nextInt(3) + 1;
                    if (!player.world.isRemote && RechargeHelper.consumeCharge(stack, player, cost)) {
                        EntityXPOrb xp = new EntityXPOrb(player.world, player.posX, player.posY, player.posZ, amount);
                        player.world.spawnEntity(xp);
                    }
                }
            }
        }

        if (stack.getItemDamage() == 2 && RechargeHelper.getCharge(stack) > 0 && count % 20 == 0) {
            AxisAlignedBB aabb = new AxisAlignedBB(player.posX - (double)4.0F, player.posY - (double)4.0F, player.posZ - (double)4.0F, player.posX + (double)4.0F, player.posY + (double)4.0F, player.posZ + (double)4.0F);

            for(EntityPlayer p : player.world.getEntitiesWithinAABB(EntityPlayer.class, aabb)) {
                if (RechargeHelper.consumeCharge(stack, player, cost)) {
                    boolean hasEffect = p.getActivePotionEffect(PotionWarpWard.instance) != null;
                    if (!hasEffect) {
                        if (!p.world.isRemote) {
                            p.addPotionEffect(new PotionEffect(PotionWarpWard.instance, 200, 0, true, false));
                        }
                    } else {
                        PotionEffect effect = p.getActivePotionEffect(PotionWarpWard.instance);

                        try {
                            Field dur = PotionEffect.class.getDeclaredFields()[2];
                            dur.setAccessible(true);
                            dur.setInt(effect, dur.getInt(effect) + 120);
                            dur.setAccessible(false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        if (stack.getItemDamage() == 3 && RechargeHelper.getCharge(stack) > 0) {
            double dx = player.posX + MathUtils.randomDouble(itemRand) * (double)16.0F;
            double dy = player.posY + MathUtils.randomDouble(itemRand) * (double)16.0F;
            double dz = player.posZ + MathUtils.randomDouble(itemRand) * (double)16.0F;
            if (player.world.isRemote && player.world.rand.nextDouble() <= 0.1) {
                (new FXDispatcher()).arcLightning(player.posX, player.posY + (double)0.5F, player.posZ, dx, dy, dz, 0.2F, 0.5F, 1.0F, 1.0F);
            }

            label274:
            for(int i = 1; i <= 16; ++i) {
                double px = lookVec.x * (double)i + player.posX;
                double py = lookVec.y * (double)i + player.posY + (double)player.getEyeHeight();
                double pz = lookVec.z * (double)i + player.posZ;
                AxisAlignedBB aabb = new AxisAlignedBB(px - (double)0.5F, py - (double)0.5F, pz - (double)0.5F, px + (double)0.5F, py + (double)0.5F, pz + (double)0.5F);

                for(EntityLivingBase e : player.world.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
                    if (e != player && !e.isDead && e.hurtTime <= 0) {
                        boolean attack = true;
                        if (!RechargeHelper.consumeCharge(stack, player, cost)) {
                            attack = false;
                        }

                        if (attack) {
                            e.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), 6.0F);
                            if (player.world.isRemote) {
                                (new FXDispatcher()).arcLightning(player.posX, player.posY + (double)0.5F, player.posZ, e.posX, e.posY, e.posZ, 0.2F, 0.5F, 1.0F, 1.0F);
                            }

                            player.world.playSound(player.posX, player.posY, player.posZ, SoundsTC.jacobs, SoundCategory.PLAYERS, 1.0F, player.world.rand.nextFloat() * 2.0F, false);
                            player.world.playSound(e.posX, e.posY, e.posZ, SoundsTC.jacobs, SoundCategory.PLAYERS, 1.0F, player.world.rand.nextFloat() * 2.0F, false);
                        }
                        break label274;
                    }
                }
            }
        }

        if (stack.getItemDamage() == 4 && RechargeHelper.getCharge(stack) > 0 && count % 10 == 0) {
            if (stack.hasTagCompound() && stack.getTagCompound().hasKey("playerhealth")) {
                if (RechargeHelper.consumeCharge(stack, player, cost)) {
                    if ((double)player.getHealth() < stack.getTagCompound().getDouble("playerhealth")) {
                        player.setHealth((float)stack.getTagCompound().getDouble("playerhealth"));
                    } else {
                        stack.getTagCompound().setDouble("playerhealth", (double)player.getHealth());
                    }
                }
            } else {
                if (!stack.hasTagCompound()) {
                    stack.setTagCompound(new NBTTagCompound());
                }

                stack.getTagCompound().setDouble("playerhealth", (double)player.getHealth());
            }
        }

        if (stack.getItemDamage() == 5 && RechargeHelper.getCharge(stack) > 0 && count % 100 == 0) {
            AxisAlignedBB aabb = new AxisAlignedBB(player.posX - (double)4.0F, player.posY - (double)4.0F, player.posZ - (double)4.0F, player.posX + (double)4.0F, player.posY + (double)4.0F, player.posZ + (double)4.0F);

            for(EntityPlayer p : player.world.getEntitiesWithinAABB(EntityPlayer.class, aabb)) {
                if (RechargeHelper.consumeCharge(stack, player, cost)) {
                    Potion[] pots = new Potion[]{MobEffects.STRENGTH, MobEffects.SPEED, MobEffects.HASTE, MobEffects.NIGHT_VISION, MobEffects.WATER_BREATHING, MobEffects.REGENERATION};

                    for(Potion pp : pots) {
                        boolean hasEffect = p.getActivePotionEffect(pp) != null;
                        if (!hasEffect) {
                            if (!p.world.isRemote) {
                                p.addPotionEffect(new PotionEffect(pp, 600, 0, true, false));
                            }
                        } else {
                            PotionEffect effect = p.getActivePotionEffect(pp);

                            try {
                                Field dur = PotionEffect.class.getDeclaredFields()[2];
                                dur.setAccessible(true);
                                dur.setInt(effect, dur.getInt(effect) + 600);
                                dur.setAccessible(false);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        if (stack.getItemDamage() == 6 && RechargeHelper.getCharge(stack) > 0 && count % 20 == 0) {
            label230:
            for(int i = 1; i <= 8; ++i) {
                double px = lookVec.x * (double)i + player.posX;
                double py = lookVec.y * (double)i + player.posY + (double)player.getEyeHeight();
                double pz = lookVec.z * (double)i + player.posZ;
                AxisAlignedBB aabb = new AxisAlignedBB(px - (double)0.5F, py - (double)0.5F, pz - (double)0.5F, px + (double)0.5F, py + (double)0.5F, pz + (double)0.5F);

                for(EntityLivingBase e : player.world.getEntitiesWithinAABB(EntityLivingBase.class, aabb)) {
                    if (e != player && !e.isDead) {
                        boolean attack = true;
                        if (!RechargeHelper.consumeCharge(stack, player, cost)) {
                            attack = false;
                        }

                        if (attack) {
                            if (e instanceof EntityAnimal) {
                                ((EntityAnimal)EntityAnimal.class.cast(e)).attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer)player), 0.0F);
                            }

                            if (e instanceof IMob) {
                                AxisAlignedBB nearbyMobs = new AxisAlignedBB(e.posX - (double)16.0F, e.posY - (double)6.0F, e.posZ - (double)16.0F, e.posX + (double)16.0F, e.posY + (double)6.0F, e.posZ + (double)16.0F);
                                List<EntityLivingBase> nMobs = player.world.getEntitiesWithinAABB(EntityLivingBase.class, nearbyMobs);
                                if (nMobs.contains(player)) {
                                    nMobs.remove(player);
                                }

                                if (nMobs.contains(e)) {
                                    nMobs.remove(e);
                                }

                                if (!nMobs.isEmpty()) {
                                    EntityLivingBase base = (EntityLivingBase)nMobs.get(player.world.rand.nextInt(nMobs.size()));
                                    e.setRevengeTarget(base);
                                    e.setLastAttackedEntity(base);
                                }
                            }

                            if (e instanceof EntityPlayer) {
                                e.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 100, 0, true, false));
                                e.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 0, true, false));
                            }
                        }
                        break label230;
                    }
                }
            }
        }

        if (stack.getItemDamage() == 7 && RechargeHelper.getCharge(stack) > 0 && count % 10 == 0 && RechargeHelper.consumeCharge(stack, player, cost)) {
            for(int i = 0; i < 64; ++i) {
                int dx = MathHelper.floor(player.posX + MathUtils.randomDouble(itemRand) * (double)6.0F);
                int dy = MathHelper.floor(player.posY);
                int dz = MathHelper.floor(player.posZ + MathUtils.randomDouble(itemRand) * (double)6.0F);
                Block b = player.world.getBlockState(new BlockPos(dx, dy, dz)).getBlock();
                if (!b.isAir(player.world.getBlockState(new BlockPos(dx, dy, dz)), player.world, player.getPosition()) && b instanceof IPlantable) {
                    b.randomTick(player.world, new BlockPos(dx, dy, dz), player.world.getBlockState(new BlockPos(dx, dy, dz)), player.world.rand);
                }
            }
        }

        if (stack.getItemDamage() == 8 && RechargeHelper.getCharge(stack) > 0 && count % 10 == 0 && RechargeHelper.consumeCharge(stack, player, cost)) {
            AxisAlignedBB aabb = new AxisAlignedBB(player.posX - (double)16.0F, player.posY - (double)4.0F, player.posZ - (double)16.0F, player.posX + (double)16.0F, player.posY + (double)4.0F, player.posZ + (double)16.0F);
            List<EntityAnimal> animals = player.world.getEntitiesWithinAABB(EntityAnimal.class, aabb);
            List<EntityAnimal> toRemove = new ArrayList();

            for(EntityAnimal a : animals) {
                if (a.isInLove()) {
                    toRemove.add(a);
                }

                if (a.isDead) {
                    toRemove.add(a);
                }
            }

            animals.removeAll(toRemove);
            if (!animals.isEmpty()) {
                ((EntityAnimal)animals.get(player.world.rand.nextInt(animals.size()))).setInLove((EntityPlayer)player);
            }
        }

    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        player.setActiveHand(hand);
        return super.onItemRightClick(world, player, hand);
    }

    public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityLivingBase player, int count) {
        if (!itemstack.hasTagCompound()) {
            itemstack.setTagCompound(new NBTTagCompound());
        } else {
            itemstack.getTagCompound().removeTag("playerhealth");
        }

    }

    public EnumAction getItemUseAction(ItemStack par1ItemStack) {
        return EnumAction.NONE;
    }

    public int getMaxItemUseDuration(ItemStack itemstack) {
        return Integer.MAX_VALUE;
    }

    public void addInformation(ItemStack stack, World player, List<String> list, ITooltipFlag par4) {
        list.add(I18n.translateToLocal("tb.ukulele.type." + types[Math.min(stack.getItemDamage(), types.length - 1)]));
        list.add("");
    }

    public void onUpdate(ItemStack is, World w, Entity e, int slot, boolean currentItem) {
        if (!w.isRemote && e instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)e;
            if (player.ticksExisted % 5 == 0) {
                int cr = (int) AuraHelper.getVis(w, player.getPosition());
                if (cr > 0) {
                    RechargeHelper.rechargeItem(player.world, is, new BlockPos(player), player, 1);
                }
            }
        }

    }

    public int getMaxCharge(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return 300;
    }

    public IRechargable.EnumChargeDisplay showInHud(ItemStack itemStack, EntityLivingBase entityLivingBase) {
        return EnumChargeDisplay.NORMAL;
    }
}
