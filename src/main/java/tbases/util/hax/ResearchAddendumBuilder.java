package tbases.util.hax;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchAddendum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ResearchAddendumBuilder {
    private ResearchAddendum entry = new ResearchAddendum();

    public ResearchAddendumBuilder() {
    }

    public static ResearchAddendumBuilder start() {
        return new ResearchAddendumBuilder();
    }

    public ResearchAddendumBuilder setText(String text) {
        this.entry.setText(text);
        return this;
    }

    public ResearchAddendumBuilder setRecipes(String... recipes) {
        return this.setRecipes((ResourceLocation[])((List) Arrays.stream(recipes).map(ResourceLocation::new).collect(Collectors.toList())).toArray(new ResourceLocation[0]));
    }

    public ResearchAddendumBuilder setRecipes(ResourceLocation... recipes) {
        this.entry.setRecipes(recipes);
        return this;
    }

    public ResearchAddendumBuilder setResearch(String... research) {
        this.entry.setResearch(research);
        return this;
    }

    public ResearchAddendum build() {
        if (this.entry == null) {
            throw new IllegalStateException("Already built!");
        } else {
            ResearchAddendum re = this.entry;
            this.entry = null;
            return re;
        }
    }
}
