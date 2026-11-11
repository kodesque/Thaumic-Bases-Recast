package tbases.network.proxy;

import net.minecraft.block.properties.IProperty;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.BlockFluidBase;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import tbases.client.render.entities.RenderBullet;
import tbases.client.render.items.RenderHerobrinesScythe;
import tbases.client.render.items.RenderRevolver;
import tbases.client.render.tiles.RenderOverchanter;
import tbases.common.entities.EntityRevolverBullet;
import tbases.common.tiles.TileOverchanter;
import tbases.init.TBBlocks;
import tbases.init.TBItems;

public class TBClient extends TBServer {
    public TBClient() {
    }

    public void preInit(FMLPreInitializationEvent e) {
        ModelLoader.setCustomStateMapper(TBBlocks.pyrofluid, (new StateMap.Builder()).ignore(new IProperty[]{BlockFluidBase.LEVEL}).build());
        TBItems.revolver.setTileEntityItemStackRenderer(new RenderRevolver());
    }

    public void init(FMLInitializationEvent e) {
        super.init(e);
        this.registerRenderInformation();
    }

    public void Renders() {
        TBBlocks.Render();
        TBItems.Renders();
    }

    public void registerRenderInformation() {
        ClientRegistry.bindTileEntitySpecialRenderer(TileOverchanter.class, new RenderOverchanter());
        MinecraftForge.EVENT_BUS.register(new RenderHerobrinesScythe());
        RenderingRegistry.registerEntityRenderingHandler(EntityRevolverBullet.class, new RenderBullet());
    }

    public boolean fancyGraphicsEnable() {
        return Minecraft.getMinecraft().gameSettings.fancyGraphics;
    }
}
