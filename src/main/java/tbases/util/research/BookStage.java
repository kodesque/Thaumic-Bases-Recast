package tbases.util.research;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.tuple.Triple;
import thaumcraft.api.capabilities.IPlayerKnowledge;
import thaumcraft.api.research.ResearchCategories;
import thaumcraft.api.research.ResearchStage;
import thaumcraft.common.lib.research.ResearchManager;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BookStage {
    private  ResearchStage stage;
    private BookStage instance;

    public BookStage start() {
        stage = new ResearchStage();
        instance = new BookStage();

        return instance;
    }

    public ResearchStage addSimple() {
        return new ResearchStage();
    }

    public BookStage setText(String text) {
        stage.setText(text);
        return instance;
    }

    public BookStage setRecipes(String... recipes) {
        stage.setRecipes((ResourceLocation[])((List) Arrays.stream(recipes).map(ResourceLocation::new).collect(Collectors.toList())).toArray(new ResourceLocation[0]));

        return instance;
    }

    public BookStage setRequiredCraft(ItemStack... items) {
        stage.setCraft(items);
        if (stage.getCraft() != null && stage.getCraft().length > 0) {
            int[] refs = new int[stage.getCraft().length];
            int q = 0;

            for(Object stack : stage.getCraft()) {
                int code = ResearchManager.createItemStackHash((ItemStack)stack);
                ResearchManager.craftingReferences.add(code);
                refs[q] = code;
                ++q;
            }

            stage.setCraftReference(refs);
        }

        return instance;
    }

    public BookStage setWarp(int warp) {
        stage.setWarp(warp);
        return instance;
    }

    public BookStage setConsumedItems(ItemStack... obtain) {
        stage.setObtain((Object[])obtain);
        return instance;
    }

    public BookStage setCraftReference(int... craftReference) {
        stage.setCraftReference(craftReference);
        return instance;
    }

    public BookStage setKnowledge(Triple<KnowledgeType, String, Integer>... know) {

        IPlayerKnowledge.EnumKnowledgeType typeActual[] = null;
        ResearchStage.Knowledge knowActual[] = null;

        for (int i = 0; i < know.length; i++) {

            typeActual = new IPlayerKnowledge.EnumKnowledgeType[know.length];
            knowActual = new ResearchStage.Knowledge[know.length];

             if (know[i].getLeft() == KnowledgeType.OBSERVATION) {
                 typeActual[i] = IPlayerKnowledge.EnumKnowledgeType.OBSERVATION;
             } else {
                 typeActual[i] = IPlayerKnowledge.EnumKnowledgeType.THEORY;
             }

             knowActual[i] = new ResearchStage.Knowledge(typeActual[i], ResearchCategories.getResearchCategory(know[i].getMiddle()), know[i].getRight());
        }

        if (knowActual != null) {
            stage.setKnow(knowActual);
        }

        return instance;
    }

    public BookStage setResearch(String... research) {
        stage.setResearch(research);
        return instance;
    }

    public ResearchStage write() {
        if (stage == null) {
            throw new IllegalStateException("Already built!");
        } else {
            ResearchStage re = stage;
            stage = null;
            instance = null;
            return re;
        }
    }
}
