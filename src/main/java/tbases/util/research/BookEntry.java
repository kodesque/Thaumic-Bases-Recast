package tbases.util.research;

import net.minecraft.item.ItemStack;
import tbases.init.TBResearch;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;

public class BookEntry {
    private  ResearchEntry entry;
    private BookEntry instance;

    public BookEntry start(String key, int x, int y, ResearchIcon... icons) {
        entry = new ResearchEntry();
        instance = new BookEntry();

        Object[] iconsActual = new Object[icons.length];

        for (int i = 0; i < icons.length; i++) {
            iconsActual[i] = icons[i].getIcon();
        }

        entry.setKey(key);
        entry.setCategory(TBResearch.CAT_NAME);
        entry.setName("research." + key.toLowerCase() + ".title");
        entry.setDisplayColumn(x);
        entry.setDisplayRow(y);
        entry.setIcons(iconsActual);
        return instance;
    }

    public ResearchEntry addSimple() {
        return new ResearchEntry();
    }

    public BookEntry setMeta(ResearchEntry.EnumResearchMeta... metas) {
        entry.setMeta(metas);
        return instance;
    }

    public BookEntry setAddenda(ResearchAddendum... addenda) {
        entry.setAddenda(addenda);
        return instance;
    }

    public BookEntry setParents(String... parents) {
        entry.setParents(parents);
        return instance;
    }

    public BookEntry setRewardItems(ItemStack... rewards) {
        entry.setRewardItem(rewards);
        return instance;
    }

    public BookEntry setRewardKnowledge(ResearchStage.Knowledge... rewardKnow) {
        entry.setRewardKnow(rewardKnow);
        return instance;
    }

    public BookEntry setSiblings(String... siblings) {
        entry.setSiblings(siblings);
        return instance;
    }

    public BookEntry setStages(ResearchStage... stages) {
        entry.setStages(stages);
        return instance;
    }

    public void write() {
        if (entry == null) {
            throw new IllegalStateException("Already built!");
        } else {

            ResearchUtils.addTextContent(entry);
            ResearchUtils.addResearch(entry);

            entry = null;
            instance = null;
//            ResearchUtils.addResearchToCategory(re);
        }
    }
}
