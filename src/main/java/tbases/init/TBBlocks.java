package tbases.init;


import com.google.common.base.Strings;
import com.google.common.collect.ObjectArrays;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderException;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import org.apache.logging.log4j.Level;
import tbases.client.creativetabs.TBCreativeTabs;
import tbases.common.block.BlockAureliaLeaf;
import tbases.common.block.BlockAureliaPlant;
import tbases.common.block.BlockBraizer;
import tbases.common.block.BlockCampfire;
import tbases.common.block.BlockFlaxium;
import tbases.common.block.BlockGlieonia;
import tbases.common.block.BlockIronSpike;
import tbases.common.block.BlockLazullia;
import tbases.common.block.BlockLucritePlant;
import tbases.common.block.BlockMetalleat;
import tbases.common.block.BlockOverchanter;
import tbases.common.block.BlockPlax;
import tbases.common.block.BlockPyrofluid;
import tbases.common.block.BlockPyrosolid;
import tbases.common.block.BlockRainbowCactus;
import tbases.common.block.BlockRedlonStem;
import tbases.common.block.BlockSweed;
import tbases.common.block.BlockTBBush;
import tbases.common.block.BlockTBLeaves;
import tbases.common.block.BlockTBLog;
import tbases.common.block.BlockTBPlanks;
import tbases.common.block.BlockTBSapling;
import tbases.common.block.BlockThaumSpike;
import tbases.common.block.BlockTobacco;
import tbases.common.block.BlockVoidPlant;
import tbases.common.block.BlockVoidSpike;
import tbases.common.block.TBBlock;
import tbases.common.block.TBBlockCrytal;
import tbases.common.block.TBSidedBlock;
import tbases.core.TBCore;

import java.lang.reflect.Constructor;


public class TBBlocks {
    
    //Fluid
    public static Block pyrofluid;
    public static Block pyrosolid;
    //TB Blocks
    public static Block crystalblockair;
    public static Block crystalblockfire;
    public static Block crystalblockwater;
    public static Block crystalblockearth;
    public static Block crystalblockorder;
    public static Block crystalblockentropy;
    public static Block crystalblockmixed;
    public static Block crystalblocktainted;





    //ThaumcraftBlocks
    public static Block quicksilverblock;
    public static Block quicksilverbrick;
    public static Block dustblock;
    public static Block blockthauminite;
    public static Block irongreatwood;
    public static Block eldritchark;
    //Old Style
    public static Block oldcobble;
    public static Block oldcobblemossy;
    public static Block oldgravel;
    public static Block oldbrick;
    public static Block oldlapis;
    public static Block oldiron;
    public static Block oldgold;
    public static Block olddiamond;

    //Plant
    public static Block sweed;
    public static Block plax;
    public static Block rainbowcactus;
    public static Block aurelia;
    public static Block aureliapetalb;
    public static Block metalleat;
    public static Block lazullia;
    public static Block redlonstem;
    public static Block lucrite;
    public static Block flaxium;
    public static Block briar;
    public static Block glieonia;
    public static Block voidplant;
    public static Block tobacco;
    //Spike
    public static Block ironspike;
    public static Block thaumspike;
    public static Block voidspike;

    //Trees TB
    public static Block goldenlogs;
    public static Block goldenleaves;
    public static Block goldensapling;
    public static Block goldenplanks;


    public static Block netherlogs;
    public static Block netherleaves;
    public static Block nethersapling;
    public static Block netherplanks;

    public static Block enderlogs;
    public static Block enderleaves;
    public static Block endersapling;
    public static Block enderplanks;
    
    //Main Mechanism TB
    public static Block overchanter;
    public static Block campfire;
    public static Block braizer;
    
    public static void init() {

        //Old Style Blocks
        oldcobble = new TBBlock(Material.ROCK, false).setTranslationKey("oldcobble").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldcobble.setHarvestLevel("piclaxe", 0);
        oldcobblemossy = new TBBlock(Material.ROCK, false).setTranslationKey("oldcobblemossy").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldcobblemossy.setHarvestLevel("piclaxe", 0);
        oldgravel = new TBBlock(Material.SAND, false).setSoundType(SoundType.GROUND).setTranslationKey("oldgravel").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldgravel.setHarvestLevel("shovel", 0);
        oldbrick = new TBBlock(Material.ROCK, false).setTranslationKey("oldbrick").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldbrick.setHarvestLevel("piclaxe", 0);
        oldlapis = new TBBlock(Material.ROCK, false).setTranslationKey("oldlapis").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldlapis.setHarvestLevel("piclaxe", 0);
        oldiron = new TBSidedBlock(Material.IRON, false).setTranslationKey("oldiron").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldiron.setHarvestLevel("piclaxe", 0);
        oldgold = new TBSidedBlock(Material.IRON, false).setTranslationKey("oldgold").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        oldgold.setHarvestLevel("piclaxe", 0);
        olddiamond = new TBSidedBlock(Material.IRON, false).setTranslationKey("olddiamond").setHardness(1).setResistance(1).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        olddiamond.setHarvestLevel("piclaxe", 0);
        //Thaumcraft DecoBlock
        quicksilverblock = new TBBlock(Material.ROCK, false).stabilise().setTranslationKey("quicksilverBlock").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        quicksilverblock.setHarvestLevel("pickaxe", 0);
        quicksilverbrick = new TBBlock(Material.ROCK, false).stabilise().setTranslationKey("quicksilverBrick").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        quicksilverbrick.setHarvestLevel("pickaxe", 0);
        dustblock = new TBBlock(Material.SAND, false).stabilise().setTranslationKey("dustBlock").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        dustblock.setHarvestLevel("pickaxe", 0);
        blockthauminite = new TBBlock(Material.SAND, false).stabilise().setTranslationKey("blockthauminite").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        blockthauminite.setHarvestLevel("pickaxe", 0);
        irongreatwood = new TBBlock(Material.SAND, false).stabilise().setTranslationKey("irongreatwood").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        irongreatwood.setHarvestLevel("pickaxe", 0);
        eldritchark = new TBBlock(Material.SAND, false).stabilise().setTranslationKey("eldritchark").setHardness(0.5F).setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        eldritchark.setHarvestLevel("pickaxe", 0);
        //TB Blocks
        crystalblockair = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblockair").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockair.setHarvestLevel("pickaxe", 0);
        crystalblockfire = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblockfire").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockfire.setHarvestLevel("pickaxe", 0);
        crystalblockwater = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblockwater").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockwater.setHarvestLevel("pickaxe", 0);
        crystalblockearth = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblockearth").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockearth.setHarvestLevel("pickaxe", 0);
        crystalblockorder = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblockorder").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockorder.setHarvestLevel("pickaxe", 0);
        crystalblockentropy = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblockentropy").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockentropy.setHarvestLevel("pickaxe", 0);
        crystalblockmixed = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblockmixed").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblockmixed.setHarvestLevel("pickaxe", 0);
        crystalblocktainted = new TBBlockCrytal(Material.GLASS, false).setTranslationKey("crystalblocktainted").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setHardness(0.5F);
        crystalblocktainted.setHarvestLevel("pickaxe", 0);
        pyrofluid = new BlockPyrofluid();
        pyrosolid = new BlockPyrosolid().setTranslationKey("pyrosolid");
        //Plant
        sweed = new BlockSweed(4,4,false).setTranslationKey("sweed");
        plax = new BlockPlax(7, 4, true).setTranslationKey("plax");
        aurelia = new BlockAureliaPlant().setTranslationKey("aurelia").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        aureliapetalb = new BlockAureliaLeaf().setTranslationKey("aureliapetalb");
        tobacco = new BlockTobacco(7,4,true).setTranslationKey("tobacco");
        metalleat = new BlockMetalleat(7,8,true).setTranslationKey("metalleat");
        lazullia = new BlockLazullia(7,16,true).setTranslationKey("lazullia");
        redlonstem = new BlockRedlonStem(Blocks.REDSTONE_BLOCK).setTranslationKey("redlonstem");
        lucrite = new BlockLucritePlant(7,16,true).setTranslationKey("lucrite");
        flaxium = new BlockFlaxium().setTranslationKey("flaxium");
        briar = new BlockTBBush().setTranslationKey("briar");
        glieonia = new BlockGlieonia(3,12,false).setTranslationKey("glieonia");
        voidplant = new BlockVoidPlant(3,32,true).setTranslationKey("voidplant");
        //Tree
        goldenlogs = new BlockTBLog().setTranslationKey("goldenlogs").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        goldenleaves = new BlockTBLeaves(0).setTranslationKey("goldenleaves").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        goldenplanks = new BlockTBPlanks().setTranslationKey("goldenplanks").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        goldensapling = new BlockTBSapling(0).setTranslationKey("goldensapling").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        nethersapling = new BlockTBSapling(1).setTranslationKey("nethersapling").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        netherleaves = new BlockTBLeaves(1).setTranslationKey("netherleaves").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        netherplanks = new BlockTBPlanks().setTranslationKey("netherplanks").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        netherlogs = new BlockTBLog().setTranslationKey("netherlogs").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        endersapling = new BlockTBSapling(2).setTranslationKey("endersapling").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        enderleaves = new BlockTBLeaves(2).setTranslationKey("enderleaves").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        enderplanks = new BlockTBPlanks().setTranslationKey("enderplanks").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        enderlogs = new BlockTBLog().setTranslationKey("enderlogs").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        //Spike
        ironspike = new BlockIronSpike().setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("ironspike");
        thaumspike = new BlockThaumSpike().setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("thaumspike");
        voidspike = new BlockVoidSpike().setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("voidspike");
        //Main Mechanism TB
        overchanter = new BlockOverchanter().setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("overchanter");
        campfire = new BlockCampfire().setTranslationKey("campfire").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);
        braizer = new BlockBraizer().setTranslationKey("braizer").setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs);

        //Cactus ALONE :<
        rainbowcactus = new BlockRainbowCactus().setCreativeTab(TBCreativeTabs.TB_CREATIVEtabs).setTranslationKey("rainbowcactus");
    }


    public static void InGameRegister() {


        TBBlocks.registerBlock(oldcobble, oldcobble.getTranslationKey().substring(5));
        TBBlocks.registerBlock(oldcobblemossy, oldcobblemossy.getTranslationKey().substring(5));
        TBBlocks.registerBlock(oldgravel, oldgravel.getTranslationKey().substring(5));
        TBBlocks.registerBlock(oldbrick, oldbrick.getTranslationKey().substring(5));
        TBBlocks.registerBlock(oldlapis, oldlapis.getTranslationKey().substring(5));
        TBBlocks.registerBlock(oldiron, oldiron.getTranslationKey().substring(5));
        TBBlocks.registerBlock(oldgold, oldgold.getTranslationKey().substring(5));
        TBBlocks.registerBlock(olddiamond, olddiamond.getTranslationKey().substring(5));


        TBBlocks.registerBlock(quicksilverblock, quicksilverblock.getTranslationKey().substring(5));
        TBBlocks.registerBlock(quicksilverbrick, quicksilverbrick.getTranslationKey().substring(5));
        TBBlocks.registerBlock(dustblock, dustblock.getTranslationKey().substring(5));
        TBBlocks.registerBlock(blockthauminite, blockthauminite.getTranslationKey().substring(5));
        TBBlocks.registerBlock(irongreatwood, irongreatwood.getTranslationKey().substring(5));
        TBBlocks.registerBlock(eldritchark, eldritchark.getTranslationKey().substring(5));


        TBBlocks.registerBlock(crystalblockair, crystalblockair.getTranslationKey().substring(5));
        TBBlocks.registerBlock(crystalblockfire, crystalblockfire.getTranslationKey().substring(5));
        TBBlocks.registerBlock(crystalblockwater, crystalblockwater.getTranslationKey().substring(5));
        TBBlocks.registerBlock(crystalblockearth, crystalblockearth.getTranslationKey().substring(5));
        TBBlocks.registerBlock(crystalblockorder, crystalblockorder.getTranslationKey().substring(5));
        TBBlocks.registerBlock(crystalblockentropy, crystalblockentropy.getTranslationKey().substring(5));
        TBBlocks.registerBlock(crystalblockmixed, crystalblockmixed.getTranslationKey().substring(5));
        TBBlocks.registerBlock(crystalblocktainted, crystalblocktainted.getTranslationKey().substring(5));


        TBBlocks.registerBlock(pyrofluid,pyrofluid.getTranslationKey().substring(5));
        TBBlocks.registerBlock(pyrosolid,pyrosolid.getTranslationKey().substring(5));


        TBBlocks.registerBlock(rainbowcactus,rainbowcactus.getTranslationKey().substring(5));
        TBBlocks.registerBlock(plax,plax.getTranslationKey().substring(5));
        TBBlocks.registerBlock(sweed,sweed.getTranslationKey().substring(5));
        TBBlocks.registerBlock(aurelia,aurelia.getTranslationKey().substring(5));
        TBBlocks.registerBlock(aureliapetalb,aureliapetalb.getTranslationKey().substring(5));
        TBBlocks.registerBlock(tobacco,tobacco.getTranslationKey().substring(5));
        TBBlocks.registerBlock(metalleat,metalleat.getTranslationKey().substring(5));
        TBBlocks.registerBlock(lazullia,lazullia.getTranslationKey().substring(5));
        TBBlocks.registerBlock(redlonstem,redlonstem.getTranslationKey().substring(5));
        TBBlocks.registerBlock(lucrite,lucrite.getTranslationKey().substring(5));
        TBBlocks.registerBlock(flaxium,flaxium.getTranslationKey().substring(5));
        TBBlocks.registerBlock(briar,briar.getTranslationKey().substring(5));
        TBBlocks.registerBlock(glieonia,glieonia.getTranslationKey().substring(5));
        TBBlocks.registerBlock(voidplant,voidplant.getTranslationKey().substring(5));


        TBBlocks.registerBlock(ironspike,ironspike.getTranslationKey().substring(5));
        TBBlocks.registerBlock(thaumspike,thaumspike.getTranslationKey().substring(5));
        TBBlocks.registerBlock(voidspike,voidspike.getTranslationKey().substring(5));


        TBBlocks.registerBlock(goldenlogs,goldenlogs.getTranslationKey().substring(5));
        TBBlocks.registerBlock(goldenleaves,goldenleaves.getTranslationKey().substring(5));
        TBBlocks.registerBlock(goldensapling,goldensapling.getTranslationKey().substring(5));
        TBBlocks.registerBlock(goldenplanks,goldenplanks.getTranslationKey().substring(5));


        TBBlocks.registerBlock(endersapling,endersapling.getTranslationKey().substring(5));
        TBBlocks.registerBlock(enderlogs,enderlogs.getTranslationKey().substring(5));
        TBBlocks.registerBlock(enderleaves,enderleaves.getTranslationKey().substring(5));
        TBBlocks.registerBlock(enderplanks,enderplanks.getTranslationKey().substring(5));


        TBBlocks.registerBlock(nethersapling,nethersapling.getTranslationKey().substring(5));
        TBBlocks.registerBlock(netherlogs,netherlogs.getTranslationKey().substring(5));
        TBBlocks.registerBlock(netherleaves,netherleaves.getTranslationKey().substring(5));
        TBBlocks.registerBlock(netherplanks,netherplanks.getTranslationKey().substring(5));


        TBBlocks.registerBlock(overchanter,overchanter.getTranslationKey().substring(5));
        TBBlocks.registerBlock(campfire,campfire.getTranslationKey().substring(5));
        TBBlocks.registerBlock(braizer,braizer.getTranslationKey().substring(5));

    }

    @Deprecated
    public static Block registerBlock(Block block) {
        ForgeRegistries.BLOCKS.register(block);
        ForgeRegistries.ITEMS.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        return block;
    }

    @Deprecated
    public static Block registerBlock(Block block, String name) {
        if (block.getRegistryName() == null && Strings.isNullOrEmpty(name))
            throw new IllegalArgumentException("Attempted to register a Block with no name: " + block);

        return registerBlock(block.getRegistryName() != null ? block : block.setRegistryName(name));
    }

    @Deprecated
    public static Block registerBlock(Block block, Class<? extends ItemBlock> itemclass, String name, Object... itemCtorArgs) {
        if (Strings.isNullOrEmpty(name)) {
            throw new IllegalArgumentException("Attempted to register a block with no name: " + block);
        }
        if (Loader.instance().isInState(LoaderState.CONSTRUCTING)) {
            FMLLog.warning("The mod %s is attempting to register a block whilst it it being constructed. This is bad modding practice - please use a proper mod lifecycle event.", Loader.instance().activeModContainer());
        }
        try {
            assert block != null : "registerBlock: block cannot be null";
            if (block.getRegistryName() != null && !block.getRegistryName().toString().equals(name))
                throw new IllegalArgumentException("Attempted to register a Block with conflicting names. Old: " + block.getRegistryName() + " New: " + name);
            ItemBlock i = null;
            if (itemclass != null) {
                Class<?>[] ctorArgClasses = new Class<?>[itemCtorArgs.length + 1];
                ctorArgClasses[0] = Block.class;
                for (int idx = 1; idx < ctorArgClasses.length; idx++) {
                    ctorArgClasses[idx] = itemCtorArgs[idx - 1].getClass();
                }
                Constructor<? extends ItemBlock> itemCtor = itemclass.getConstructor(ctorArgClasses);
                i = itemCtor.newInstance(ObjectArrays.concat(block, itemCtorArgs));
            }
            // block registration has to happen first
            ForgeRegistries.BLOCKS.register(block.getRegistryName() == null ? block.setRegistryName(name) : block);
            if (i != null)
                ForgeRegistries.ITEMS.register(i.setRegistryName(name));
            return block;
        } catch (Exception e) {
            FMLLog.log(Level.ERROR, e, "Caught an exception during block registration");
            throw new LoaderException(e);
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
        registerRender(aureliapetalb);
        registerRender(tobacco);
        registerRender(metalleat);
        registerRender(lazullia);
        registerRender(lucrite);
        registerRender(redlonstem);
        registerRender(flaxium);
        registerRender(briar);
        registerRender(glieonia);
        registerRender(voidplant);


        registerRender(ironspike);
        registerRender(thaumspike);
        registerRender(voidspike);


        registerRender(goldensapling);
        renderBlockItems(Item.getItemFromBlock(goldensapling));
        registerRender(goldenplanks);
        registerRender(goldenleaves);
        registerRender(goldenlogs);


        registerRender(endersapling);
        renderBlockItems(Item.getItemFromBlock(endersapling));
        registerRender(enderlogs);
        registerRender(enderplanks);
        registerRender(enderleaves);


        registerRender(nethersapling);
        renderBlockItems(Item.getItemFromBlock(nethersapling));
        registerRender(netherlogs);
        registerRender(netherleaves);
        registerRender(netherplanks);


        registerRender(overchanter);
        registerRender(campfire);
        registerRender(braizer);
    }

    public static void registerRender(Block block) {
        Item item = Item.getItemFromBlock(block);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(TBCore.modid + ":" + item.getTranslationKey().substring(5), "inventory"));
    }
    public static void renderBlockItems(Item i){

        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(i, 0, new ModelResourceLocation(
                TBCore.modid + ":" + i.getTranslationKey().substring(5), "inventory"));
    }
}
