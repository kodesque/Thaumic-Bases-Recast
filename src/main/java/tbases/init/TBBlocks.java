package tbases.init;

import com.google.common.base.Strings;
import com.google.common.collect.ObjectArrays;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;
import tbases.common.blocks.plants.BlockPrimalShroom;
import tbases.common.blocks.plants.BlockAureliaLeaf;
import tbases.common.blocks.plants.BlockAureliaPlant;
import tbases.common.blocks.machines.BlockBraizer;
import tbases.common.blocks.machines.BlockOverchanter;
import tbases.common.blocks.rest.BlockPyrofluid;
import tbases.common.blocks.rest.BlockPyrosolid;
import tbases.common.blocks.plants.BlockRainbowCactus;
import tbases.common.blocks.plants.BlockBriarBush;
import tbases.common.blocks.machines.BlockThaumicAnvil;
import tbases.common.blocks.plants.BlockTobacco;
import tbases.common.blocks.machines.BlockVoidAnvil;
import tbases.common.blocks.plants.BlockVoidPlant;
import tbases.common.blocks.rest.BlockCrystalSolid;
import tbases.common.templates.TBBlock;
import tbases.root.TBCreativeTabs;

import java.lang.reflect.Constructor;

public class TBBlocks {
    public static Block pyrofluid;
    public static Block pyrosolid;
    public static Block crystalblockair;
    public static Block crystalblockfire;
    public static Block crystalblockwater;
    public static Block crystalblockearth;
    public static Block crystalblockorder;
    public static Block crystalblockentropy;
    public static Block crystalblockmixed;
    public static Block crystalblocktainted;
    public static Block quicksilverblock;
    public static Block quicksilverbrick;
    public static Block dustblock;
    public static Block blockthauminite;
    public static Block irongreatwood;
    public static Block eldritchark;
    public static Block oldcobble;
    public static Block oldcobblemossy;
    public static Block oldgravel;
    public static Block oldbrick;
    public static Block oldlapis;
    public static Block oldiron;
    public static Block oldgold;
    public static Block olddiamond;
    public static Block sweed;
    public static Block plax;
    public static Block rainbowcactus;
    public static Block aurelia;
    public static Block aureliapetalb;
    public static Block briar;
    public static Block voidplant;
    public static Block tobacco;
    public static Block overchanter;
    public static Block braizer;
    public static Block ashroom;
    public static Block thaumicAnvil;
    public static Block voidAnvil;

    public TBBlocks() {
    }

    public static void init() {
        oldcobble = (new TBBlock(Material.ROCK, false)).setTranslationKey("oldcobble").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldcobble.setHarvestLevel("piclaxe", 0);
        oldcobblemossy = (new TBBlock(Material.ROCK, false)).setTranslationKey("oldcobblemossy").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldcobblemossy.setHarvestLevel("piclaxe", 0);
        oldgravel = (new TBBlock(Material.SAND, false)).setSoundType(SoundType.GROUND).setTranslationKey("oldgravel").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldgravel.setHarvestLevel("shovel", 0);
        oldbrick = (new TBBlock(Material.ROCK, false)).setTranslationKey("oldbrick").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldbrick.setHarvestLevel("piclaxe", 0);
        oldlapis = (new TBBlock(Material.ROCK, false)).setTranslationKey("oldlapis").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldlapis.setHarvestLevel("piclaxe", 0);
        oldiron = (new TBBlock(Material.IRON, false)).setTranslationKey("oldiron").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldiron.setHarvestLevel("piclaxe", 0);
        oldgold = (new TBBlock(Material.IRON, false)).setTranslationKey("oldgold").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldgold.setHarvestLevel("piclaxe", 0);
        olddiamond = (new TBBlock(Material.IRON, false)).setTranslationKey("olddiamond").setHardness(1.0F).setResistance(1.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        olddiamond.setHarvestLevel("piclaxe", 0);
        quicksilverblock = (new TBBlock(Material.ROCK, false)).stabilise().setTranslationKey("quicksilverBlock").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        quicksilverblock.setHarvestLevel("pickaxe", 0);
        quicksilverbrick = (new TBBlock(Material.ROCK, false)).stabilise().setTranslationKey("quicksilverBrick").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        quicksilverbrick.setHarvestLevel("pickaxe", 0);
        dustblock = (new TBBlock(Material.SAND, false)).stabilise().setTranslationKey("dustBlock").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        dustblock.setHarvestLevel("pickaxe", 0);
        blockthauminite = (new TBBlock(Material.SAND, false)).stabilise().setTranslationKey("blockthauminite").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        blockthauminite.setHarvestLevel("pickaxe", 0);
        irongreatwood = (new TBBlock(Material.SAND, false)).stabilise().setTranslationKey("irongreatwood").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        irongreatwood.setHarvestLevel("pickaxe", 0);
        eldritchark = (new TBBlock(Material.SAND, false)).stabilise().setTranslationKey("eldritchark").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        eldritchark.setHarvestLevel("pickaxe", 0);
        crystalblockair = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblockair").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockair.setHarvestLevel("pickaxe", 0);
        crystalblockfire = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblockfire").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockfire.setHarvestLevel("pickaxe", 0);
        crystalblockwater = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblockwater").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockwater.setHarvestLevel("pickaxe", 0);
        crystalblockearth = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblockearth").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockearth.setHarvestLevel("pickaxe", 0);
        crystalblockorder = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblockorder").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockorder.setHarvestLevel("pickaxe", 0);
        crystalblockentropy = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblockentropy").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockentropy.setHarvestLevel("pickaxe", 0);
        crystalblockmixed = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblockmixed").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockmixed.setHarvestLevel("pickaxe", 0);
        crystalblocktainted = (new BlockCrystalSolid(Material.GLASS, false)).setTranslationKey("crystalblocktainted").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblocktainted.setHarvestLevel("pickaxe", 0);
        pyrofluid = new BlockPyrofluid();
        pyrosolid = (new BlockPyrosolid()).setTranslationKey("pyrosolid");
        braizer = (new BlockBraizer()).setTranslationKey("braizer").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        rainbowcactus = (new BlockRainbowCactus()).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("rainbowcactus");
        thaumicAnvil = (new BlockThaumicAnvil()).setHardness(5.0F).setResistance(2000.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("thaumic_anvil");
        voidAnvil = (new BlockVoidAnvil()).setHardness(5.0F).setResistance(2000.0F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("void_anvil");

        aurelia = (new BlockAureliaPlant()).setTranslationKey("aurelia").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        aureliapetalb = (new BlockAureliaLeaf()).setTranslationKey("aureliapetalb");
        tobacco = (new BlockTobacco(7, 4, true)).setTranslationKey("tobacco");
        briar = (new BlockBriarBush()).setTranslationKey("briar").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        ashroom = (new BlockPrimalShroom(3, 12, false)).setTranslationKey("ashroom").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        voidplant = (new BlockVoidPlant(7, 32, true)).setTranslationKey("voidplant");
        overchanter = (new BlockOverchanter()).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("overchanter");
    }

    public static void InGameRegister() {
        registerBlock(oldcobble, oldcobble.getTranslationKey().substring(5));
        registerBlock(oldcobblemossy, oldcobblemossy.getTranslationKey().substring(5));
        registerBlock(oldgravel, oldgravel.getTranslationKey().substring(5));
        registerBlock(oldbrick, oldbrick.getTranslationKey().substring(5));
        registerBlock(oldlapis, oldlapis.getTranslationKey().substring(5));
        registerBlock(oldiron, oldiron.getTranslationKey().substring(5));
        registerBlock(oldgold, oldgold.getTranslationKey().substring(5));
        registerBlock(olddiamond, olddiamond.getTranslationKey().substring(5));
        registerBlock(quicksilverblock, quicksilverblock.getTranslationKey().substring(5));
        registerBlock(quicksilverbrick, quicksilverbrick.getTranslationKey().substring(5));
        registerBlock(dustblock, dustblock.getTranslationKey().substring(5));
        registerBlock(blockthauminite, blockthauminite.getTranslationKey().substring(5));
        registerBlock(irongreatwood, irongreatwood.getTranslationKey().substring(5));
        registerBlock(eldritchark, eldritchark.getTranslationKey().substring(5));
        registerBlock(crystalblockair, crystalblockair.getTranslationKey().substring(5));
        registerBlock(crystalblockfire, crystalblockfire.getTranslationKey().substring(5));
        registerBlock(crystalblockwater, crystalblockwater.getTranslationKey().substring(5));
        registerBlock(crystalblockearth, crystalblockearth.getTranslationKey().substring(5));
        registerBlock(crystalblockorder, crystalblockorder.getTranslationKey().substring(5));
        registerBlock(crystalblockentropy, crystalblockentropy.getTranslationKey().substring(5));
        registerBlock(crystalblockmixed, crystalblockmixed.getTranslationKey().substring(5));
        registerBlock(crystalblocktainted, crystalblocktainted.getTranslationKey().substring(5));
        registerBlock(thaumicAnvil, thaumicAnvil.getTranslationKey().substring(5));
        registerBlock(voidAnvil, voidAnvil.getTranslationKey().substring(5));
        registerBlockWithoutItem(pyrofluid, pyrofluid.getTranslationKey().substring(5));
        registerBlock(pyrosolid, pyrosolid.getTranslationKey().substring(5));
        registerBlock(rainbowcactus, rainbowcactus.getTranslationKey().substring(5));
        registerBlock(aurelia, aurelia.getTranslationKey().substring(5));
        registerBlockWithoutItem(aureliapetalb, aureliapetalb.getTranslationKey().substring(5));
        registerBlockWithoutItem(tobacco, tobacco.getTranslationKey().substring(5));
        registerBlock(briar, briar.getTranslationKey().substring(5));
        registerBlock(ashroom, ashroom.getTranslationKey().substring(5));
        registerBlockWithoutItem(voidplant, voidplant.getTranslationKey().substring(5));
        registerBlock(overchanter, overchanter.getTranslationKey().substring(5));
        registerBlock(braizer, braizer.getTranslationKey().substring(5));
    }

    /** @deprecated */
    @Deprecated
    public static Block registerBlock(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register((new ItemBlock(block)).setRegistryName(block.getRegistryName()));
        return block;
    }

    public static Block registerBlockWithoutItem(Block block, String name) {
        block.setRegistryName(name);
        ForgeRegistries.BLOCKS.register(block);
        return block;
    }

    /** @deprecated */
    @Deprecated
    public static Block registerBlock(Block block, String name) {
        if (block.getRegistryName() == null && Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Attempted to register a Block with no name: " + block);
        } else {
            return registerBlock(block.getRegistryName() != null ? block : (Block)block.setRegistryName(name));
        }
    }

    /** @deprecated */
    @Deprecated
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, Object... itemCtorArgs) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Attempted to register a block with no name: " + block);
        } else {
            if (Loader.instance().isInState(LoaderState.CONSTRUCTING)) {
                FMLLog.warning("The mod %s is attempting to register a block whilst it it being constructed. This is bad modding practice - please use a proper mod lifecycle event.", new Object[]{Loader.instance().activeModContainer()});
            }

            try {
                assert block != null : "registerBlock: block cannot be null";

                if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name)) {
                    throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
                } else {
                    ItemBlock i = null;
                    if (itemclass != null) {
                        Class<?>[] ctorArgClasses = new Class[itemCtorArgs.length + 1];
                        ctorArgClasses[0] = Block.class;

                        for(int idx = 1; idx < ctorArgClasses.length; ++idx) {
                            ctorArgClasses[idx] = itemCtorArgs[idx - 1].getClass();
                        }

                        Constructor<? extends ItemBlock> itemCtor = itemclass.getConstructor(ctorArgClasses);
                        i = (ItemBlock)itemCtor.newInstance(ObjectArrays.concat(block, itemCtorArgs));
                    }

                    ForgeRegistries.BLOCKS.register(block.getRegistryName() == null ? (Block)block.setRegistryName(name) : block);
                    if (i != null) {
                        ForgeRegistries.ITEMS.register(i.setRegistryName(name));
                    }

                    return block;
                }
            } catch (Exception e) {
                FMLLog.log(Level.ERROR, e, "Caught an exception during block registration", new Object[0]);
                throw new LoaderException(e);
            }
        }
    }

    public static void Render() {
        registerRender(oldcobble);
        registerRender(oldcobblemossy);
        registerRender(oldgravel);
        registerRender(oldbrick);
        registerRender(oldlapis);
        registerRender(oldiron);
        registerRender(oldgold);
        registerRender(olddiamond);
        registerRender(quicksilverblock);
        registerRender(quicksilverbrick);
        registerRender(dustblock);
        registerRender(blockthauminite);
        registerRender(irongreatwood);
        registerRender(eldritchark);
        registerRender(crystalblockair);
        registerRender(crystalblockfire);
        registerRender(crystalblockwater);
        registerRender(crystalblockearth);
        registerRender(crystalblockorder);
        registerRender(crystalblockentropy);
        registerRender(crystalblockmixed);
        registerRender(crystalblocktainted);
        registerRender(pyrofluid);
        registerRender(pyrosolid);
        registerRender(rainbowcactus);
        registerRender(plax);
        registerRender(sweed);
        registerRender(aurelia);
        renderBlockItems(Item.getItemFromBlock(aurelia));
        registerRender(aureliapetalb);
        registerRender(tobacco);
        registerRender(briar);
        registerAnvilRender(briar, 1);
        registerRender(voidplant);
        registerRender(ashroom);
        registerRender(overchanter);
        registerRender(braizer);
        registerAnvilRender(thaumicAnvil, 0);
        registerAnvilRender(thaumicAnvil, 1);
        registerAnvilRender(thaumicAnvil, 2);
        registerAnvilRender(voidAnvil, 0);
        registerAnvilRender(voidAnvil, 1);
        registerAnvilRender(voidAnvil, 2);
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation("thaumicbases:" + item.getTranslationKey().substring(5), "inventory"));
    }

    public static void registerAnvilRender(Block block, int meta) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation("thaumicbases:" + item.getTranslationKey().substring(5), "inventory"));
    }

    public static void renderBlockItems(Item i) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation("thaumicbases:" + i.getTranslationKey().substring(5), "inventory"));
    }
}
