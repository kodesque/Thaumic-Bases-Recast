package tbases.common.recipes;

import akka.japi.Pair;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.world.World;
import net.minecraftforge.common.util.RecipeMatcher;
import tbases.common.items.ItemRevolver;
import tbases.init.TBItems;
import tbases.init.custom.RevolverUpgrade;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.capabilities.ThaumcraftCapabilities;
import thaumcraft.api.crafting.InfusionRecipe;

import java.util.ArrayList;
import java.util.List;

public class RevolverInfusionRecipe extends InfusionRecipe {
    RevolverUpgrade upgrade;
    ItemStack revolverBase;
    ItemStack revolverUpgraded;

    public RevolverInfusionRecipe(String research, RevolverUpgrade output, int inst, AspectList aspects2, Object... recipe) {
        super(research, new ItemStack(TBItems.revolver, 1, 32767), inst, aspects2, new ItemStack(TBItems.revolver, 1, 32767), recipe);
        this.revolverBase = new ItemStack(TBItems.revolver, 1, 32767);
        this.revolverUpgraded = this.revolverBase.copy();
        this.upgrade = output;
        ItemRevolver.addUpgrade(this.revolverUpgraded, this.upgrade, ItemRevolver.getUpgradeLevel(this.revolverUpgraded, this.upgrade) + 1);
    }

    public boolean matches(List<ItemStack> input, ItemStack central, World world, EntityPlayer player) {
        for(Pair<RevolverUpgrade, Integer> p : ItemRevolver.getAllUpgradesFor(central)) {
            RevolverUpgrade up = (RevolverUpgrade)p.first();
            if (up.conflictsWith(this.upgrade)) {
                return false;
            }
        }

        if (ItemRevolver.getUpgradeLevel(central, this.upgrade) + 1 > this.upgrade.getMaxLevel()) {
            return false;
        } else if (this.getRecipeInput() == null) {
            return false;
        } else if (!ThaumcraftCapabilities.getKnowledge(player).isResearchKnown(this.research)) {
            return false;
        } else {
            ItemStack i2 = central.copy();
            if ((this.getRecipeInput() == Ingredient.EMPTY || this.getRecipeInput().apply(central)) && RecipeMatcher.findMatches(input, this.getComponents()) != null) {
                i2.setItemDamage(32767);
            }

            ArrayList<ItemStack> ii = new ArrayList();

            for(ItemStack is : input) {
                ii.add(is.copy());
            }

            for(Object o : this.getComponents()) {
                if (o instanceof ItemStack) {
                    ItemStack comp = (ItemStack)o;
                    boolean b = false;

                    for(int a = 0; a < ii.size(); ++a) {
                        i2 = ((ItemStack)ii.get(a)).copy();
                        if (comp.getItemDamage() == 32767) {
                            i2.setItemDamage(32767);
                        }

                        if (ThaumcraftApiHelper.areItemStacksEqualForCrafting(i2, comp)) {
                            ii.remove(a);
                            b = true;
                            break;
                        }
                    }

                    if (!b) {
                        return false;
                    }
                }
            }

            return (this.getRecipeInput() == Ingredient.EMPTY || this.getRecipeInput().apply(central)) && RecipeMatcher.findMatches(input, this.getComponents()) != null;
        }
    }

    public Object getRecipeOutput() {
        return this.revolverUpgraded;
    }

    public Object getRecipeOutput(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
        ItemStack istk = input.copy();
        ItemRevolver.addUpgrade(istk, this.upgrade, ItemRevolver.getUpgradeLevel(istk, this.upgrade) + 1);
        return istk;
    }

    public AspectList getAspects(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
        AspectList retAsp = this.aspects.copy();

        for(int i = 0; i < ItemRevolver.getUpgradeLevel(input, this.upgrade); ++i) {
            for(int j = 0; j < retAsp.size(); ++j) {
                retAsp.add(retAsp.getAspects()[j], retAsp.getAmount(retAsp.getAspects()[j]));
            }
        }

        return retAsp;
    }

    public int getInstability(EntityPlayer player, ItemStack input, List<ItemStack> comps) {
        return super.getInstability(player, input, comps) + this.upgrade.getInstabilityForLevel(ItemRevolver.getUpgradeLevel(input, this.upgrade));
    }
}
