package tbases.util.hax;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResearchStageBuilder {
    private ResearchStage entry = new ResearchStage();

    public ResearchStageBuilder() {
    }

    public static ResearchStageBuilder start() {
        return new ResearchStageBuilder();
    }

    public ResearchStageBuilder setText(String text) {
        this.entry.setText(text);
        return this;
    }

    public ResearchStageBuilder setRecipes(String... recipes) {
        return this.setRecipes((ResourceLocation[])((List) Arrays.stream(recipes).map(ResourceLocation::new).collect(Collectors.toList())).toArray(new ResourceLocation[0]));
    }

    public ResearchStageBuilder setRequiredCraft(ItemStack... items) {
        this.entry.setCraft(items);
        if (this.entry.getCraft() != null && this.entry.getCraft().length > 0) {
            int[] refs = new int[this.entry.getCraft().length];
            int q = 0;

            for(Object stack : this.entry.getCraft()) {
                int code = ResearchManager.createItemStackHash((ItemStack)stack);
                ResearchManager.craftingReferences.add(code);
                refs[q] = code;
                ++q;
            }

            this.entry.setCraftReference(refs);
        }

        return this;
    }

    public ResearchStageBuilder setRecipes(ResourceLocation... recipes) {
        this.entry.setRecipes(recipes);
        return this;
    }

    public ResearchStageBuilder setWarp(int warp) {
        this.entry.setWarp(warp);
        return this;
    }

    public ResearchStageBuilder setConsumedItems(ItemStack... obtain) {
        this.entry.setObtain((Object[])obtain);
        return this;
    }

    public ResearchStageBuilder setCraftReference(int... craftReference) {
        this.entry.setCraftReference(craftReference);
        return this;
    }

    public ResearchStageBuilder setKnow(ResearchStage.Knowledge... know) {
        this.entry.setKnow(know);
        return this;
    }

    public ResearchStageBuilder setResearch(String... research) {
        this.entry.setResearch(research);
        return this;
    }

    public ResearchStage build() {
        if (this.entry == null) {
            throw new IllegalStateException("Already built!");
        } else {
            ResearchStage re = this.entry;
            this.entry = null;
            return re;
        }
    }
}
