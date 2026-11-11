package tbases.util.research;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.research.ResearchAddendum;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookAddendum {
    private ResearchAddendum add;
    private BookAddendum instance;

    public BookAddendum start(String... requiredResearch) {
        add = new ResearchAddendum();
        instance = new BookAddendum();

        add.setResearch(requiredResearch);

        return instance;
    }

    public ResearchAddendum addSimple() {
        return new ResearchAddendum();
    }

    public BookAddendum setRecipes(String... recipes) {
        add.setRecipes((ResourceLocation[])((List) Arrays.stream(recipes).map(ResourceLocation::new).collect(Collectors.toList())).toArray(new ResourceLocation[0]));
        return instance;
    }

    public BookAddendum setRecipes(ResourceLocation... recipes) {
        add.setRecipes(recipes);
        return instance;
    }

    public ResearchAddendum write() {
        if (add == null) {
            throw new IllegalStateException("Already built!");
        } else {
            ResearchAddendum re = add;
            add = null;
            instance = null;
            return re;
        }
    }
}
