package tbases.common.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.stats.StatList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import tbases.init.TBItems;
import tbases.root.TBCore;
import thaumcraft.api.items.IWarpingGear;
import thaumcraft.common.lib.SoundsTC;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class ItemHerobrinesScythe extends ItemSword implements IWarpingGear {
    public ItemHerobrinesScythe() {
        super(ToolMaterial.DIAMOND);
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.UNCOMMON;
    }

    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(ChatFormatting.ITALIC + "Well, they're nothing...");
    }

    public void onUpdate(ItemStack stk, World w, Entity entity, int slot, boolean held) {
        super.onUpdate(stk, w, entity, slot, held);
        if (stk.isItemDamaged() && entity.ticksExisted % 20 == 0 && entity instanceof EntityLivingBase) {
            stk.damageItem(-1, (EntityLivingBase)entity);
        }

    }

    public static void attack(EntityPlayer attacker, List<EntityLivingBase> doNotAttack, EntityLivingBase attacked) {
        AxisAlignedBB aabb = (new AxisAlignedBB(attacked.posX - (double)1.0F, attacked.posY - (double)1.0F, attacked.posZ - (double)1.0F, attacked.posX + (double)1.0F, attacked.posY + (double)1.0F, attacked.posZ + (double)1.0F)).grow((double)6.0F, (double)6.0F, (double)6.0F);
        List<EntityLivingBase> mobs = attacked.world.getEntitiesWithinAABB(EntityLivingBase.class, aabb);
        Random rnd = attacker.world.rand;
        mobs.removeAll(doNotAttack);
        if (!mobs.isEmpty()) {
            while(!mobs.isEmpty()) {
                int index = rnd.nextInt(mobs.size());
                if (mobs.get(index) != null && ((EntityLivingBase)mobs.get(index)).isEntityAlive() && mobs.get(index) instanceof IMob && !(mobs.get(index) instanceof EntityPlayer)) {
                    performPlayerAttackAt(attacker, (Entity)mobs.get(index));
                    TBCore.proxy.lightning(attacker.world, attacked.posX, attacked.posY + rnd.nextDouble() * (double)attacked.getEyeHeight(), attacked.posZ, ((EntityLivingBase)mobs.get(index)).posX, ((EntityLivingBase)mobs.get(index)).posY + rnd.nextDouble() * (double)((EntityLivingBase)mobs.get(index)).getEyeHeight(), ((EntityLivingBase)mobs.get(index)).posZ, 20, 2.0F, 10, 0);
                    attacker.world.playSound((EntityPlayer)null, attacked.getPosition(), SoundsTC.zap, SoundCategory.AMBIENT, 1.0F, 0.8F);
                    doNotAttack.add(mobs.get(index));
                    attack(attacker, doNotAttack, (EntityLivingBase)mobs.get(index));
                    break;
                }

                mobs.remove(index);
            }
        }

    }

    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity) {
        if (entity.isEntityAlive() && entity instanceof IMob && player.getCooledAttackStrength(1.0F) == 1.0F) {
            attack(player, new ArrayList(), (EntityLivingBase)entity);
        }

        return super.onLeftClickEntity(stack, player, entity);
    }

    private static void performPlayerAttackAt(EntityPlayer attacker, Entity entityLivingBase) {
        if (!MinecraftForge.EVENT_BUS.post(new AttackEntityEvent(attacker, entityLivingBase))) {
            if (entityLivingBase.canBeAttackedWithItem() && !entityLivingBase.hitByEntity(attacker)) {
                float f = (float)attacker.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();
                int i = 0;
                float f1 = 0.0F;
                if (entityLivingBase instanceof EntityLivingBase) {
                    f1 = EnchantmentHelper.getModifierForCreature(attacker.getHeldItem(EnumHand.MAIN_HAND), ((EntityLivingBase)entityLivingBase).getCreatureAttribute());
                } else {
                    f1 = EnchantmentHelper.getModifierForCreature(attacker.getHeldItem(EnumHand.MAIN_HAND), EnumCreatureAttribute.UNDEFINED);
                }

                if (attacker.isSprinting()) {
                    ++i;
                }

                if (f > 0.0F || f1 > 0.0F) {
                    boolean flag = attacker.fallDistance > 0.0F && !attacker.onGround && !attacker.isOnLadder() && !attacker.isInWater() && !attacker.isPotionActive(MobEffects.BLINDNESS) && attacker.getRidingEntity() == null && entityLivingBase instanceof EntityLivingBase;
                    if (flag && f > 0.0F) {
                        f *= 1.5F;
                    }

                    f += f1;
                    boolean flag1 = false;
                    int j = EnchantmentHelper.getFireAspectModifier(attacker);
                    if (entityLivingBase instanceof EntityLivingBase && j > 0 && !attacker.isBurning()) {
                        flag1 = true;
                        entityLivingBase.setFire(1);
                    }

                    boolean flag2 = entityLivingBase.attackEntityFrom(DamageSource.causePlayerDamage(attacker), f);
                    if (flag2) {
                        if (i > 0) {
                            entityLivingBase.addVelocity((double)(-MathHelper.sin(attacker.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F), 0.1, (double)(MathHelper.cos(attacker.rotationYaw * (float)Math.PI / 180.0F) * (float)i * 0.5F));
                            attacker.motionX *= 0.6;
                            attacker.motionZ *= 0.6;
                            attacker.setSprinting(false);
                        }

                        if (flag) {
                            attacker.onCriticalHit(entityLivingBase);
                        }

                        if (f1 > 0.0F) {
                            attacker.onEnchantmentCritical(entityLivingBase);
                        }

                        attacker.setLastAttackedEntity(entityLivingBase);
                        if (entityLivingBase instanceof EntityLivingBase) {
                            EnchantmentHelper.applyThornEnchantments((EntityLivingBase)entityLivingBase, attacker);
                        }

                        EnchantmentHelper.applyArthropodEnchantments(attacker, entityLivingBase);
                        ItemStack is = attacker.getHeldItemMainhand();
                        Object object = entityLivingBase;
                        if (entityLivingBase instanceof EntityDragon) {
                            IEntityMultiPart entityMultiPart = (IEntityMultiPart)((EntityDragon)entityLivingBase).dragonPartBody;
                            object = entityMultiPart;
                        }

                        if (!is.isEmpty() && object instanceof EntityLivingBase) {
                            is.hitEntity((EntityLivingBase)object, attacker);
                        }

                        if (entityLivingBase instanceof EntityLivingBase) {
                            attacker.addStat(StatList.DAMAGE_DEALT, Math.round(f * 10.0F));
                            if (j > 0) {
                                attacker.setFire(j * 4);
                            }
                        } else if (flag1) {
                            attacker.extinguish();
                        }
                    }
                }
            }

        }
    }

    public int getWarp(ItemStack itemStack, EntityPlayer entityPlayer) {
        return 3;
    }

    public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot slot, ItemStack stack) {
        Multimap attribs = HashMultimap.create();
        if (stack.getItem() == TBItems.herobrinesscythe && slot == EntityEquipmentSlot.MAINHAND) {
            attribs.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double)14.5F, 0));
            attribs.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", (double)-3.0F, 0));
            attribs.put(SharedMonsterAttributes.MOVEMENT_SPEED.getName(), new AttributeModifier(UUID.fromString("CB3F55D3-645C-4F38-A497-9C13A33DB5CE"), "MOVEMENT SPEED", (double)0.5F, 2));
        }

        return attribs;
    }
}
