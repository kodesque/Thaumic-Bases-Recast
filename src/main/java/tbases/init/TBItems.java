package tbases.init;

import com.google.common.base.Strings;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import tbases.common.items.ItemBloodyArmor;
import tbases.common.items.ItemHerobrinesScythe;
import tbases.common.items.ItemKnowledgeShard;
import tbases.common.items.ItemRevolver;
import tbases.common.items.ItemRosehipSyrup;
import tbases.common.items.ItemSmokingPipe;
import tbases.common.items.ItemTobacco;
import tbases.common.items.ItemUkulele;
import tbases.common.items.ItemVoidFlintAndSteel;
import tbases.common.items.ItemVoidShears;
import tbases.root.TBCreativeTabs;

public class TBItems {
    public static ItemArmor.ArmorMaterial bloodyA;
    public static Item nuggetthauminite;
    public static Item thauminite_ingot;
    public static Item thauminite_plate;
    public static Item bloodycloth;
    public static Item voidshears;
    public static Item voidfas;
    public static Item pyrobucket;
    public static Item aureliapetal;
    public static Item briar_seedbag;
    public static Item voidseed;
    public static Item bloodychest;
    public static Item bloodylegs;
    public static Item bloodyboots;
    public static Item herobrinesscythe;
    public static Item tobaccoseed;
    public static Item tobacco_pile;
    public static Item tobacco_eldritch;
    public static Item tobacco_fighting;
    public static Item tobacco_hunger;
    public static Item tobacco_knowledge;
    public static Item tobacco_mining;
    public static Item tobacco_sanity;
    public static Item tobacco_tainted;
    public static Item tobacco_wispy;
    public static Item tobacco_leaves;
    public static Item greatwoodpipe;
    public static Item silverwoodpipe;
    public static Item rosehipsyrup;
    public static Item revolver;
    public static Item ukulele;
    public static Item knowledge_shard;
    public static Item lapis_shard;
    public static Block[] crystalBlock;

    public TBItems() {
    }

    public static void init() {
        nuggetthauminite = (new Item()).setTranslationKey("nuggetthauminite").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        thauminite_ingot = (new Item()).setTranslationKey("thauminite_ingot").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        thauminite_plate = (new Item()).setTranslationKey("thauminite_plate").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        bloodycloth = (new Item()).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("bloodycloth");
        voidshears = (new ItemVoidShears()).setTranslationKey("voidshears").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        voidfas = (new ItemVoidFlintAndSteel()).setTranslationKey("voidfas").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobaccoseed = (new ItemSeeds(TBBlocks.tobacco, Blocks.FARMLAND)).setTranslationKey("tobaccoseed").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        aureliapetal = (new Item()).setTranslationKey("aureliapetal").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        voidseed = (new ItemSeeds(TBBlocks.voidplant, Blocks.FARMLAND)).setTranslationKey("voidseed").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        briar_seedbag = (new Item()).setTranslationKey("briar_seedbag").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        knowledge_shard = (new ItemKnowledgeShard()).setTranslationKey("knowledge_shard").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        bloodychest = (new ItemBloodyArmor(bloodyA, 0, 1, EntityEquipmentSlot.CHEST)).setTranslationKey("bloodychest").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        bloodylegs = (new ItemBloodyArmor(bloodyA, 0, 2, EntityEquipmentSlot.LEGS)).setTranslationKey("bloodylegs").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        bloodyboots = (new ItemBloodyArmor(bloodyA, 0, 3, EntityEquipmentSlot.FEET)).setTranslationKey("bloodyboots").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        herobrinesscythe = (new ItemHerobrinesScythe()).setTranslationKey("herobrinesscythe").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_pile = (new ItemTobacco()).setTranslationKey("tobacco_pile").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_eldritch = (new ItemTobacco()).setTranslationKey("tobacco_eldritch").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_fighting = (new ItemTobacco()).setTranslationKey("tobacco_fighting").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_hunger = (new ItemTobacco()).setTranslationKey("tobacco_hunger").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_knowledge = (new ItemTobacco()).setTranslationKey("tobacco_knowledge").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_mining = (new ItemTobacco()).setTranslationKey("tobacco_mining").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_sanity = (new ItemTobacco()).setTranslationKey("tobacco_sanity").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_tainted = (new ItemTobacco()).setTranslationKey("tobacco_tainted").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_wispy = (new ItemTobacco()).setTranslationKey("tobacco_wispy").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        tobacco_leaves = (new Item()).setTranslationKey("tobacco_leaves").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        greatwoodpipe = (new ItemSmokingPipe(false)).setTranslationKey("greatwoodpipe").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        silverwoodpipe = (new ItemSmokingPipe(true)).setTranslationKey("silverwoodpipe").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        rosehipsyrup = (new ItemRosehipSyrup()).setTranslationKey("rosehipsyrup").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        revolver = (new ItemRevolver()).setTranslationKey("revolver").setMaxStackSize(1).setMaxDamage(1561).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        ukulele = (new ItemUkulele()).setTranslationKey("ukulele").setHasSubtypes(true).setMaxStackSize(1).setMaxDamage(0).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        lapis_shard = (new Item()).setTranslationKey("lapis_shard").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
    }

    public static void InGameRegistr() {
        registerItem(nuggetthauminite, nuggetthauminite.getTranslationKey().substring(5));
        registerItem(thauminite_ingot, thauminite_ingot.getTranslationKey().substring(5));
        registerItem(thauminite_plate, thauminite_plate.getTranslationKey().substring(5));
        registerItem(bloodycloth, bloodycloth.getTranslationKey().substring(5));
        registerItem(voidshears, voidshears.getTranslationKey().substring(5));
        registerItem(voidfas, voidfas.getTranslationKey().substring(5));
        registerItem(aureliapetal, aureliapetal.getTranslationKey().substring(5));
        registerItem(briar_seedbag, briar_seedbag.getTranslationKey().substring(5));
        registerItem(voidseed, voidseed.getTranslationKey().substring(5));
        registerItem(bloodychest, bloodychest.getTranslationKey().substring(5));
        registerItem(bloodylegs, bloodylegs.getTranslationKey().substring(5));
        registerItem(bloodyboots, bloodyboots.getTranslationKey().substring(5));
        registerItem(herobrinesscythe, herobrinesscythe.getTranslationKey().substring(5));
        registerItem(tobacco_eldritch, tobacco_eldritch.getTranslationKey().substring(5));
        registerItem(tobacco_fighting, tobacco_fighting.getTranslationKey().substring(5));
        registerItem(tobacco_hunger, tobacco_hunger.getTranslationKey().substring(5));
        registerItem(tobacco_knowledge, tobacco_knowledge.getTranslationKey().substring(5));
        registerItem(tobacco_mining, tobacco_mining.getTranslationKey().substring(5));
        registerItem(tobacco_pile, tobacco_pile.getTranslationKey().substring(5));
        registerItem(tobacco_sanity, tobacco_sanity.getTranslationKey().substring(5));
        registerItem(tobacco_tainted, tobacco_tainted.getTranslationKey().substring(5));
        registerItem(tobacco_wispy, tobacco_wispy.getTranslationKey().substring(5));
        registerItem(tobaccoseed, tobaccoseed.getTranslationKey().substring(5));
        registerItem(tobacco_leaves, tobacco_leaves.getTranslationKey().substring(5));
        registerItem(greatwoodpipe, greatwoodpipe.getTranslationKey().substring(5));
        registerItem(silverwoodpipe, silverwoodpipe.getTranslationKey().substring(5));
        registerItem(rosehipsyrup, rosehipsyrup.getTranslationKey().substring(5));
        registerItem(revolver, revolver.getTranslationKey().substring(5));
        registerItem(ukulele, ukulele.getTranslationKey().substring(5));
        registerItem(knowledge_shard, knowledge_shard.getTranslationKey().substring(5));
        registerItem(lapis_shard, lapis_shard.getTranslationKey().substring(5));
    }

    /** @deprecated */
    @Deprecated
    public static void registerItem(Item item, String name) {
        if (item.getRegistryName() == null && Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Attempted to register a item with no name: " + item);
        } else if (item.getRegistryName() != null && !item.getRegistryName().toString().equals(name)) {
            throw new IllegalArgumentException("Attempted to register a item with conflicting names. Old: " + item.getRegistryName() + " New: " + name);
        } else {
            ForgeRegistries.ITEMS.register(item.getRegistryName() == null ? (Item)item.setRegistryName(name) : item);
        }
    }

    public static void Renders() {
        renderItems(nuggetthauminite);
        renderItems(thauminite_ingot);
        renderItems(thauminite_plate);
        renderItems(bloodycloth);
        renderItems(aureliapetal);
        renderItems(briar_seedbag);
        renderItems(voidseed);
        renderItems(bloodychest);
        renderItems(bloodylegs);
        renderItems(bloodyboots);
        renderItems(herobrinesscythe);
        renderItems(tobacco_eldritch);
        renderItems(tobacco_fighting);
        renderItems(tobacco_hunger);
        renderItems(tobacco_knowledge);
        renderItems(tobacco_mining);
        renderItems(tobacco_pile);
        renderItems(tobacco_sanity);
        renderItems(tobacco_tainted);
        renderItems(tobacco_wispy);
        renderItems(tobaccoseed);
        renderItems(tobacco_leaves);
        renderItems(greatwoodpipe);
        renderItems(silverwoodpipe);
        renderItems(rosehipsyrup);
        renderItems(voidfas);
        renderItems(voidshears);
        renderItems(knowledge_shard);
        renderItems(revolver);
        renderItems(lapis_shard);
    }

    public static void renderItems(Item i) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation("thaumicbases:" + i.getTranslationKey().substring(5), "inventory"));
    }

    static {
        bloodyA = EnumHelper.addArmorMaterial("TBBLOODY", "thaumicbases:textures/items/armor/bloody/bloody", 21, new int[]{2, 6, 5, 2}, 21, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F).setRepairItem(new ItemStack(bloodycloth));
        crystalBlock = new Block[]{TBBlocks.crystalblocktainted, TBBlocks.crystalblockentropy, TBBlocks.crystalblockorder, TBBlocks.crystalblockearth, TBBlocks.crystalblockfire, TBBlocks.crystalblockair, TBBlocks.crystalblockwater, TBBlocks.crystalblockmixed};
    }
}
