package tbases.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import tbases.api.ITobacco;
import tbases.init.TBItems;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.capabilities.IPlayerWarp;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;
import thaumcraft.common.entities.monster.EntityWisp;
import thaumcraft.common.lib.potions.PotionDeathGaze;

import java.util.ArrayList;
import java.util.Collection;

public class ItemTobacco extends Item implements ITobacco {
    public ItemTobacco() {
    }

    public void performTobaccoEffect(EntityPlayer smoker, ItemStack tobbaco, boolean isSilverwood) {
        if (tobbaco.getItem() == TBItems.tobacco_pile) {
            if (isSilverwood && smoker.world.rand.nextFloat() <= 0.3F) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, -1, IPlayerWarp.EnumWarpType.TEMPORARY);
            }

            if (!isSilverwood && smoker.world.rand.nextFloat() <= 0.1F) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, -1, IPlayerWarp.EnumWarpType.TEMPORARY);
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_eldritch && !smoker.world.isRemote) {
            smoker.addPotionEffect(new PotionEffect(PotionDeathGaze.instance, 2000, 0, true, false));
            if (!isSilverwood) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, 3, IPlayerWarp.EnumWarpType.TEMPORARY);
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_fighting && !smoker.world.isRemote) {
            smoker.addPotionEffect(new PotionEffect(MobEffects.STRENGTH, 8000, 1, true, false));
            if (isSilverwood) {
                smoker.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 8000, 0, true, false));
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_hunger) {
            smoker.getFoodStats().addStats(3, 3.0F);
            if (!isSilverwood) {
                smoker.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 500, 0, true, false));
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_knowledge) {
            int oProg = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION.getProgression();
            int tProg = IPlayerKnowledge.EnumKnowledgeType.THEORY.getProgression();
            ResearchCategory[] rc = (ResearchCategory[]) ResearchCategories.researchCategories.values().toArray(new ResearchCategory[0]);
            if (!smoker.world.isRemote) {
                for(int i = 0; i < (isSilverwood ? 20 : 10); ++i) {
                    ThaumcraftApi.internalMethods.addKnowledge(smoker, IPlayerKnowledge.EnumKnowledgeType.OBSERVATION, rc[smoker.getRNG().nextInt(rc.length)], MathHelper.getInt(smoker.getRNG(), oProg / 4, oProg / 3));
                    ThaumcraftApi.internalMethods.addKnowledge(smoker, IPlayerKnowledge.EnumKnowledgeType.THEORY, rc[smoker.getRNG().nextInt(rc.length)], MathHelper.getInt(smoker.getRNG(), tProg / 8, tProg / 6));
                }

                if (isSilverwood) {
                    smoker.addExperienceLevel(1);
                } else {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, 2, IPlayerWarp.EnumWarpType.TEMPORARY);
                    ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, 1, IPlayerWarp.EnumWarpType.NORMAL);
                }
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_mining && !smoker.world.isRemote) {
            smoker.addPotionEffect(new PotionEffect(MobEffects.HASTE, 8000, 1, true, false));
            if (isSilverwood) {
                smoker.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 8000, 0, true, false));
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_sanity && !smoker.world.isRemote) {
            ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, -1, IPlayerWarp.EnumWarpType.TEMPORARY);
            if (isSilverwood) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, -1, IPlayerWarp.EnumWarpType.NORMAL);
                ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, -1, IPlayerWarp.EnumWarpType.TEMPORARY);
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_tainted && !smoker.world.isRemote) {
            if (!isSilverwood) {
                ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, 1 + smoker.world.rand.nextInt(3), IPlayerWarp.EnumWarpType.TEMPORARY);
                if (smoker.world.rand.nextFloat() <= 0.4F) {
                    ThaumcraftApi.internalMethods.addWarpToPlayer(smoker, 1, IPlayerWarp.EnumWarpType.NORMAL);
                }
            } else {
                ItemStack stk = smoker.getHeldItem(smoker.getActiveHand());
                if (stk.getItem() == TBItems.silverwoodpipe) {
                    smoker.renderBrokenItemStack(stk);
                    stk.shrink(1);
                }
            }
        }

        if (tobbaco.getItem() == TBItems.tobacco_wispy) {
            EntityWisp wisp = new EntityWisp(smoker.world);
            wisp.setPositionAndRotation(smoker.posX, smoker.posY + (double)1.0F, smoker.posZ, 0.0F, 0.0F);
            Collection<Aspect> pa = Aspect.aspects.values();
            ArrayList<Aspect> aspects = new ArrayList(pa);
            if (isSilverwood) {
                EntityWisp wisp1 = new EntityWisp(smoker.world);
                wisp1.setPositionAndRotation(smoker.posX, smoker.posY + (double)1.0F, smoker.posZ, 0.0F, 0.0F);
                if (!smoker.world.isRemote) {
                    wisp.setType(((Aspect)aspects.get(smoker.world.rand.nextInt(aspects.size()))).getTag());
                    smoker.world.spawnEntity(wisp1);
                    aspects.remove(Aspect.FLUX);
                }
            } else if (!smoker.world.isRemote) {
                smoker.world.spawnEntity(wisp);
            }
        }

    }
}
