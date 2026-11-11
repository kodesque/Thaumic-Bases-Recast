package tbases.init;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchCategory;

public class TBResearch {

    public static final String CAT_NAME = "THAUMICBASES";
    public static ResearchCategory RES_CAT;

    public static void registerCategory() {
        ResearchCategories.registerCategory("THAUMICBASES", "FIRSTSTEPS", new AspectList(), new ResourceLocation("thaumicbases", "textures/thaumonomicon/bases.png"), new ResourceLocation("thaumicbases", "textures/thaumonomicon/background.png"), new ResourceLocation("thaumcraft", "textures/gui/gui_research_back_over.png"));
        ThaumcraftApi.registerResearchLocation(new ResourceLocation("thaumicbases", "research/researchtb.json"));
    }

}
