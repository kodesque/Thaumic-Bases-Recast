package tbases.network.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tbases.client.render.HerobrinesScytheMH;
import tbases.client.render.RenderCampfire;
import tbases.client.render.RenderOverchanter;
import tbases.common.tiles.TileCampfire;
import tbases.common.tiles.TileOverchanter;
import tbases.core.TBCore;
import tbases.init.TBBlocks;
import tbases.init.TBItems;

public class TBClient extends TBServer {


    @Override
    public void preInit(FMLPreInitializationEvent e) {





        ModelLoader.setCustomStateMapper(TBBlocks.pyrofluid,new StateMap.Builder().ignore(BlockFluidBase.LEVEL).build());
    }



    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
        OBJLoader.INSTANCE.addDomain(TBCore.modid);
    }


    @Override
    public void Renders() {
        TBBlocks.Render();
        TBItems.Renders();

}

    @SuppressWarnings({ "unchecked", "deprecation" })
    @Override
    public void registerRenderInformation()
    {
       ClientRegistry.bindTileEntitySpecialRenderer(TileOverchanter.class, new RenderOverchanter());
       ClientRegistry.bindTileEntitySpecialRenderer(TileCampfire.class, new RenderCampfire());
       MinecraftForge.EVENT_BUS.register(new HerobrinesScytheMH());

      /*  RenderAccessLibrary.registerItemRenderingHandler(TBItems.ukulele, new UkuleleRenderer());
        RenderAccessLibrary.registerItemRenderingHandler(TBItems.revolver, new RenderRevolver());

        RenderAccessLibrary.registerItemRenderingHandler(TBItems.nodeFoci, new NodeFociRenderer());
        RenderAccessLibrary.registerItemRenderingHandler(TBItems.spawnerCompass, new SpawnerCompassRenderer());


       */

    }


    @Override
    public boolean fancyGraphicsEnable() {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }
}
