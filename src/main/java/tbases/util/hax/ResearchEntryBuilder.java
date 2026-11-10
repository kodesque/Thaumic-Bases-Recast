package tbases.util.hax;

import net.minecraft.item.ItemStack;
import thaumcraft.api.research.ResearchAddendum;
import thaumcraft.api.research.ResearchEntry;
import thaumcraft.api.research.ResearchStage;

public class ResearchEntryBuilder {
    private ResearchEntry entry = new ResearchEntry();

    public ResearchEntryBuilder() {
    }

    public static ResearchEntryBuilder start() {
        return new ResearchEntryBuilder();
    }

    public ResearchEntryBuilder setBaseInfo(String key, String category, String name, int x, int y, Object... icons) {
        return this.setKey(key).setCategory(category).setName(name).setPosition(x, y).setIcons(icons);
    }

    public ResearchEntryBuilder setKey(String key) {
        this.entry.setKey(key);
        return this;
    }

    public ResearchEntryBuilder setCategory(String category) {
        this.entry.setCategory(category);
        return this;
    }

    public ResearchEntryBuilder setPosition(int x, int y) {
        this.entry.setDisplayColumn(x);
        this.entry.setDisplayRow(y);
        return this;
    }

    public ResearchEntryBuilder setIcons(Object... icons) {
        this.entry.setIcons(icons);
        return this;
    }

    public ResearchEntryBuilder setMeta(ResearchEntry.EnumResearchMeta... metas) {
        this.entry.setMeta(metas);
        return this;
    }

    public ResearchEntryBuilder setAddenda(ResearchAddendum... addenda) {
        this.entry.setAddenda(addenda);
        return this;
    }

    public ResearchEntryBuilder setName(String name) {
        this.entry.setName(name);
        return this;
    }

    public ResearchEntryBuilder setParents(String... parents) {
        this.entry.setParents(parents);
        return this;
    }

    public ResearchEntryBuilder setRewardItems(ItemStack... rewards) {
        this.entry.setRewardItem(rewards);
        return this;
    }

    public ResearchEntryBuilder setRewardKnow(ResearchStage.Knowledge... rewardKnow) {
        this.entry.setRewardKnow(rewardKnow);
        return this;
    }

    public ResearchEntryBuilder setSiblings(String... siblings) {
        this.entry.setSiblings(siblings);
        return this;
    }

    public ResearchEntryBuilder setStages(ResearchStage... stages) {
        this.entry.setStages(stages);
        return this;
    }

    public ResearchEntry buildAndRegister() {
        if (this.entry == null) {
            throw new IllegalStateException("Already built!");
        } else {
            ResearchEntry re = this.entry;
            this.entry = null;
            return re;
        }
    }
}
