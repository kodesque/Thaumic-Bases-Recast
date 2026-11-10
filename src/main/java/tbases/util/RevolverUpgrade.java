package tbases.util;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.entities.IEldritchMob;
import thaumcraft.api.entities.ITaintedMob;
import thaumcraft.api.potions.PotionFluxTaint;

import java.util.ArrayList;
import java.util.Hashtable;

public class RevolverUpgrade {
    public static RevolverUpgrade[] allUpgrades = new RevolverUpgrade[24];
    public static Hashtable<String, Integer> textToIntMapping = new Hashtable();
    public static Hashtable<Integer, String> intToTextMapping = new Hashtable();
    public int maxLevel;
    public final int id;
    public final String text_id;
    public int instability;
    public static final ResourceLocation eldritchTextures = new ResourceLocation("thaumicbases", "textures/items/revolver/revolverdarkmetaleldritch.png");
    public static final ResourceLocation primalTextures = new ResourceLocation("thaumicbases", "textures/items/revolver/revolvergunprimaluv.png");
    public static final ResourceLocation taintedTextures = new ResourceLocation("thaumicbases", "textures/items/revolver/revolverguntainteduv.png");
    public static final ResourceLocation voidTextures = new ResourceLocation("thaumicbases", "textures/items/revolver/revolverhandlevoiduv.png");
    public final ArrayList<RevolverUpgrade> conflicts = new ArrayList();
    public static final RevolverUpgrade power = (new RevolverUpgrade(0, "POWER")).setMaxLevel(5).setInstabilityPerLevel(2);
    public static final RevolverUpgrade accuracy = (new RevolverUpgrade(1, "ACCURACY")).setMaxLevel(3).setInstabilityPerLevel(1);
    public static final RevolverUpgrade silver = (new RevolverUpgrade(2, "SILVER")).setMaxLevel(5).setInstabilityPerLevel(3);
    public static final RevolverUpgrade poisoned = (new RevolverUpgrade(3, "POISONED")).setMaxLevel(3).setInstabilityPerLevel(1);
    public static final RevolverUpgrade atropodsBane = (new RevolverUpgrade(4, "BANE_OF_ATROPODS")).setMaxLevel(5).setInstabilityPerLevel(3);
    public static final RevolverUpgrade eldritchBane = (new RevolverUpgrade(5, "BANE_OF_ELDRITCH")).setMaxLevel(5).setInstabilityPerLevel(3);
    public static final RevolverUpgrade piercig = (new RevolverUpgrade(6, "PIERCING")).setMaxLevel(1).setInstabilityPerLevel(12);
    public static final RevolverUpgrade dueling = (new RevolverUpgrade(7, "DUELING")).setMaxLevel(5).setInstabilityPerLevel(3);
    public static final RevolverUpgrade tainted = (new RevolverUpgrade(8, "TAINTED")).setMaxLevel(3).setInstabilityPerLevel(5);
    public static final RevolverUpgrade primal = (new RevolverUpgrade(9, "PRIMAL")).setMaxLevel(1).setInstabilityPerLevel(12);
    public static final RevolverUpgrade speed = (new RevolverUpgrade(10, "SPEED")).setMaxLevel(5).setInstabilityPerLevel(2);
    public static final RevolverUpgrade efficiency = (new RevolverUpgrade(11, "EFFICIENCY")).setMaxLevel(5).setInstabilityPerLevel(4);
    public static final RevolverUpgrade eldritch = (new RevolverUpgrade(12, "ELDRITCH")).setMaxLevel(2).setInstabilityPerLevel(7);
    public static final RevolverUpgrade uvoid = (new RevolverUpgrade(13, "VOID")).setMaxLevel(3).setInstabilityPerLevel(4);
    public static final RevolverUpgrade knowledge = (new RevolverUpgrade(14, "KNOWLEDGE")).setMaxLevel(5).setInstabilityPerLevel(1);
    public static final RevolverUpgrade heavy = (new RevolverUpgrade(15, "HEAVY")).setMaxLevel(5).setInstabilityPerLevel(1);

    public RevolverUpgrade(int id, String name) {
        this.text_id = name;
        this.id = id;
        if (id < allUpgrades.length && allUpgrades[id] != null) {
            FMLLog.warning("[TB]Attempting to register revolver upgrade " + name + "[" + id + "], but the ID is already occupied by " + allUpgrades[id].text_id + "[" + allUpgrades[id].id + "], ignoring.", new Object[0]);
        } else {
            if (id >= allUpgrades.length) {
                RevolverUpgrade[] copied = new RevolverUpgrade[allUpgrades.length + 1];
                System.arraycopy(allUpgrades, 0, copied, 0, allUpgrades.length);
                allUpgrades = copied;
            }

            allUpgrades[id] = this;
        }
    }

    public String getName() {
        return I18n.translateToLocal("revolverUpgrade." + this.text_id + ".name");
    }

    public int getID() {
        return this.id;
    }

    public String getTextID() {
        return this.text_id;
    }

    public int getMaxLevel() {
        return this.maxLevel;
    }

    public RevolverUpgrade setMaxLevel(int i) {
        this.maxLevel = i;
        return this;
    }

    public boolean conflictsWith(RevolverUpgrade upgrade) {
        return this.conflicts.contains(upgrade);
    }

    public RevolverUpgrade addConflict(RevolverUpgrade... upgrade) {
        for(RevolverUpgrade rUpgr : upgrade) {
            if (!this.conflicts.contains(rUpgr)) {
                this.conflicts.add(rUpgr);
            }

            if (!rUpgr.conflicts.contains(this)) {
                rUpgr.conflicts.add(this);
            }
        }

        return this;
    }

    public int getInstabilityForLevel(int level) {
        return this.instability * level;
    }

    public RevolverUpgrade setInstabilityPerLevel(int newValue) {
        this.instability = newValue;
        return this;
    }

    public float modifyDamage_start(EntityLivingBase base, ItemStack revolver, float currentModification, int modLevel) {
        if (base != null) {
            if (this == power) {
                return (float)((double)currentModification * Math.pow((double)1.1F, (double)modLevel));
            }

            if (this == silver && (base.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD || this.isBMEntity(base) || base instanceof EntityPlayer && this.isWerewolf((EntityPlayer)base))) {
                return (float)((double)currentModification * Math.pow((double)1.2F, (double)modLevel));
            }

            if (this == atropodsBane && base.getCreatureAttribute() == EnumCreatureAttribute.ARTHROPOD) {
                return (float)((double)currentModification * Math.pow((double)1.2F, (double)modLevel));
            }

            if (this == eldritchBane && (base instanceof IEldritchMob || base instanceof EntityEnderman)) {
                return (float)((double)currentModification * Math.pow((double)1.2F, (double)modLevel));
            }

            if (this == dueling && base instanceof EntityPlayer) {
                return (float)((double)currentModification * Math.pow((double)1.2F, (double)modLevel));
            }

            if (this == eldritch) {
                return currentModification / 1.2F;
            }

            if (this == piercig) {
                return currentModification * 1.1F;
            }

            if (this == tainted) {
                if (base instanceof ITaintedMob) {
                    return currentModification / 1.6F;
                }

                return (float)((double)currentModification * Math.pow((double)1.2F, (double)modLevel));
            }
        }

        return currentModification;
    }

    public float modifyDamage_end(EntityLivingBase base, ItemStack revolver, float currentModification, int modLevel) {
        return this == primal ? currentModification * 2.0F : currentModification;
    }

    public boolean afterhit(EntityLivingBase base, EntityPlayer user, ItemStack revolver, float damageDealt, int modLevel) {
        if (this == tainted && base.world.rand.nextDouble() <= 0.1) {
            base.addPotionEffect(new PotionEffect(PotionFluxTaint.instance, 200, 1, true, false));
        }

        if (this == poisoned) {
            base.addPotionEffect(new PotionEffect(MobEffects.POISON, 200, 1, true, false));
        }

        if (this == knowledge && base.getHealth() <= 0.0F && user != null && user.world.rand.nextInt(Math.max(1, 7 - modLevel)) == 0) {
            AspectList aspectsCompound = AspectHelper.getEntityAspects(base);
            if (aspectsCompound != null && aspectsCompound.size() > 0) {
                aspectsCompound = AspectHelper.reduceToPrimals(aspectsCompound);
                Aspect[] al = aspectsCompound.getAspects();

                for(int i = 0; i < al.length; ++i) {
                    EntityXPOrb xp = new EntityXPOrb(user.world, user.posX, user.posY, user.posZ, aspectsCompound.getAmount(al[i]));
                    if (!user.world.isRemote) {
                        user.world.spawnEntity(xp);
                    }

                    if (user.world.rand.nextBoolean()) {
                        break;
                    }
                }
            }
        }

        return this != piercig;
    }

    public boolean bulletNoclip(EntityPlayer user, ItemStack revolver, int modLevel) {
        return this == primal;
    }

    public int modifyShots(EntityPlayer user, ItemStack revolver, int modLevel, int originalLevel, boolean hasPrimal) {
        if (this == primal) {
            return -1;
        } else if (this == efficiency) {
            return hasPrimal ? (modLevel == 5 ? 2 : -1) : originalLevel + modLevel;
        } else {
            return originalLevel;
        }
    }

    public double modifySpeed(EntityPlayer user, ItemStack revolver, float origSpeed, int modLevel) {
        return this == speed ? (double)origSpeed * Math.pow((double)1.09F, (double)modLevel) : (double)origSpeed;
    }

    public ResourceLocation getOverridePartTexture(ItemStack revolver, int part, int level) {
        if (this == eldritch && part == 2) {
            return eldritchTextures;
        } else if (this == primal && part == 3) {
            return primalTextures;
        } else if (this == tainted && part == 3) {
            return taintedTextures;
        } else {
            return this == uvoid && part == 0 ? voidTextures : null;
        }
    }

    public boolean isBMEntity(EntityLivingBase base) {
        if (Loader.isModLoaded("AWWayofTime")) {
            try {
                Class<?> demonClass = Class.forName("WayofTime.alchemicalWizardry.common.IDemon");
                if (demonClass.isInstance(base)) {
                    return true;
                }
            } catch (Exception var3) {
                return false;
            }
        }

        return false;
    }

    public boolean isWerewolf(EntityPlayer p) {
        return false;
    }

    public static void initConflictingMappings() {
        power.addConflict(silver, atropodsBane, eldritchBane, dueling, eldritch);
        silver.addConflict(atropodsBane, eldritchBane, dueling, eldritch);
        atropodsBane.addConflict(eldritchBane, dueling, eldritch);
        eldritchBane.addConflict(dueling, eldritch);
        dueling.addConflict(eldritch);
        accuracy.addConflict(speed, efficiency);
        speed.addConflict(efficiency);
        poisoned.addConflict(primal, tainted, knowledge);
        primal.addConflict(tainted, knowledge);
        tainted.addConflict(knowledge);
    }
}