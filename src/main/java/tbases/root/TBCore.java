package tbases.root;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import tbases.common.entities.EntityRevolverBullet;
import tbases.events.EnchantmentHandler;
import tbases.events.RegisterHandlers;
import tbases.init.TBBlocks;
import tbases.init.TBEnchant;
import tbases.init.TBFluids;
import tbases.init.TBGuiHandler;
import tbases.init.TBItems;
import tbases.init.TBResearch;
import tbases.init.TBThaumonomicon;
import tbases.init.TBTiles;
import tbases.network.proxy.TBServer;
import tbases.util.RevolverUpgrade;
import thaumcraft.api.research.ResearchCategory;

@Mod(
        modid = "thaumicbases",
        name = "Thaumic Bases",
        version = "3.6.023.0",
        dependencies = "required-after:thaumcraft@[6.1.BETA26,)"
)
public class TBCore {
    public static final String modid = "thaumicbases";
    public static final String name = "Thaumic Bases";
    public static final String version = "3.6.023.0";
    public static final String dependencies = "required-after:thaumcraft@[6.1.BETA26,)";
    public static final String serverProxy = "tbases.network.proxy.TBServer";
    public static final String clientProxy = "tbases.network.proxy.TBClient";
    @SidedProxy(
            serverSide = "tbases.network.proxy.TBServer",
            clientSide = "tbases.network.proxy.TBClient"
    )
    public static TBServer proxy;
    public static SimpleNetworkWrapper network;
    public static TBCore instance;
    public static ResearchCategory RES_CAT;

    public TBCore() {
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        instance = this;
        TBFluids.init.call();
        TBBlocks.init();
        TBBlocks.InGameRegister();
        TBItems.init();
        TBItems.InGameRegistr();
        EntityRegistry.registerModEntity(new ResourceLocation("thaumicbases:bullet"), EntityRevolverBullet.class, "revolver_bullet", 0, instance, 32, 1, true);
        TBTiles.setup();
        TBEnchant.setupEnchatments();
        proxy.preInit(e);
        FMLInterModComms.sendMessage("Wailla", "register", "thaumicbases");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new TBGuiHandler());
        MinecraftForge.EVENT_BUS.register(new EnchantmentHandler());
        TBThaumonomicon.setup();
        TBResearch.registerResearch();
        network = NetworkRegistry.INSTANCE.newSimpleChannel("thaumbases");
        RegisterHandlers.init();
        RevolverUpgrade.initConflictingMappings();
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.Renders();
        network = NetworkRegistry.INSTANCE.newSimpleChannel("thaumicbases");
        proxy.postInit(e);
    }

    static {
        FluidRegistry.enableUniversalBucket();
    }
}
