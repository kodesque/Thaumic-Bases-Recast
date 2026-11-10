package tbases.client.render.items;

import akka.japi.Pair;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tbases.common.items.ItemRevolver;
import tbases.util.RevolverUpgrade;
import thaumcraft.client.lib.obj.AdvancedModelLoader;
import thaumcraft.client.lib.obj.IModelCustom;

public class RenderRevolver extends TileEntityItemStackRenderer {
    public static ItemCameraTransforms.TransformType transform;
    public static final IModelCustom model;
    public static final ResourceLocation handle;
    public static final ResourceLocation barrel;
    public static final ResourceLocation metal;
    public static final ResourceLocation gun;
    public static final ResourceLocation press;

    public RenderRevolver() {
    }

    public void renderByItem(ItemStack item) {
        GlStateManager.pushMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        double scale = 0.2;
        GlStateManager.scale(scale, scale, scale);
        GlStateManager.translate(5.0F, -1.0F, 5.0F);
        if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(2.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(4.0F, 9.0F, 2.0F);
            GlStateManager.scale(1.2, 1.2, 1.2);
            if (Minecraft.getMinecraft().player.isSneaking()) {
                GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(-6.2, (double)2.0F, (double)3.0F);
            }
        }

        if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
            GlStateManager.rotate(-45.0F, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(88.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.translate(4.0F, 9.0F, 2.0F);
            GlStateManager.scale(1.2, 1.2, 1.2);
            if (Minecraft.getMinecraft().player.isSneaking()) {
                GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.translate(6.2, (double)2.0F, (double)3.0F);
            }
        }

        ResourceLocation handle = RenderRevolver.handle;
        ResourceLocation barrel = RenderRevolver.barrel;
        ResourceLocation metal = RenderRevolver.metal;
        ResourceLocation gun = RenderRevolver.gun;
        ResourceLocation press = RenderRevolver.press;

        for(Pair<RevolverUpgrade, Integer> p : ItemRevolver.getAllUpgradesFor(item)) {
            RevolverUpgrade ru = (RevolverUpgrade)p.first();
            handle = ru.getOverridePartTexture(item, 0, (Integer)p.second()) == null ? handle : ru.getOverridePartTexture(item, 0, (Integer)p.second());
            barrel = ru.getOverridePartTexture(item, 1, (Integer)p.second()) == null ? barrel : ru.getOverridePartTexture(item, 1, (Integer)p.second());
            metal = ru.getOverridePartTexture(item, 2, (Integer)p.second()) == null ? metal : ru.getOverridePartTexture(item, 2, (Integer)p.second());
            gun = ru.getOverridePartTexture(item, 3, (Integer)p.second()) == null ? gun : ru.getOverridePartTexture(item, 3, (Integer)p.second());
            press = ru.getOverridePartTexture(item, 4, (Integer)p.second()) == null ? press : ru.getOverridePartTexture(item, 4, (Integer)p.second());
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(handle);
        model.renderPart("Cylinder");
        model.renderPart("Plane");
        double rotation = item.hasTagCompound() ? item.getTagCompound().getDouble("renderedRotation") : (double)0.0F;
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)0.0F, 2.9, (double)0.0F);
        GlStateManager.translate((double)0.0F, -Math.cos(Math.toRadians(rotation)) * 2.8, (double)0.0F);
        GlStateManager.translate(Math.sin(Math.toRadians(rotation)) * 2.8, (double)0.0F, (double)0.0F);
        GlStateManager.rotate((float)rotation, 0.0F, 0.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(barrel);
        model.renderPart("rBarrel_Cube.001");
        GlStateManager.popMatrix();
        Minecraft.getMinecraft().renderEngine.bindTexture(metal);
        model.renderPart("Cube.003_Cube.004");
        model.renderPart("Cube.002_Cube.003");
        model.renderPart("Cube_Cube.001");
        Minecraft.getMinecraft().renderEngine.bindTexture(gun);
        model.renderPart("Cylinder.002");
        model.renderPart("Plane.001");
        Minecraft.getMinecraft().renderEngine.bindTexture(press);
        model.renderPart("Cube.001_Cube.005");
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    static {
        transform = ItemCameraTransforms.TransformType.GUI;
        model = AdvancedModelLoader.loadModel(new ResourceLocation("thaumicbases", "models/revolver/revolver.obj"));
        handle = new ResourceLocation("thaumicbases", "textures/items/revolver/revolver_handle_uv.png");
        barrel = new ResourceLocation("thaumicbases", "textures/items/revolver/revolver_barrel_uv.png");
        metal = new ResourceLocation("thaumicbases", "textures/items/revolver/revolver_dark_metal.png");
        gun = new ResourceLocation("thaumicbases", "textures/items/revolver/revolver_gun_uv.png");
        press = new ResourceLocation("thaumicbases", "textures/items/revolver/revolver_press_uv.png");
    }
}