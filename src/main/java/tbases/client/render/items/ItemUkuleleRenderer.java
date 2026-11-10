package tbases.client.render.items;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tbases.common.items.ItemUkulele;
import thaumcraft.client.lib.obj.AdvancedModelLoader;
import thaumcraft.client.lib.obj.IModelCustom;

public class ItemUkuleleRenderer extends TileEntityItemStackRenderer {
    public static ItemCameraTransforms.TransformType transform;
    public static final IModelCustom model;
    public static final ResourceLocation base;
    public static final ResourceLocation strings;
    public static final ResourceLocation handle;

    public ItemUkuleleRenderer() {
    }

    public void renderByItem(ItemStack stack) {
        if (stack != null && stack.getItem() instanceof ItemUkulele) {
            GlStateManager.pushMatrix();
            RenderHelper.disableStandardItemLighting();
            if (transform == ItemCameraTransforms.TransformType.GUI) {
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(45.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate((double)0.5F, -2.6, (double)0.0F);
                GlStateManager.scale((double)1.0F, (double)1.0F, 0.7);
            }

            if (transform == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transform == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
                GlStateManager.scale(1.8, 1.8, 1.8);
                GlStateManager.translate(2.0F, 3.0F, 2.0F);
                GlStateManager.rotate(210.0F, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(10.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.pushMatrix();
                Minecraft.getMinecraft().getTextureManager().bindTexture(Minecraft.getMinecraft().player.getLocationSkin());
                Render<?> render = Minecraft.getMinecraft().getRenderManager().getEntityRenderObject(Minecraft.getMinecraft().player);
                RenderPlayer renderplayer = (RenderPlayer)render;
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, 1.0F, 2.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate((double)0.0F, -1.3, (double)0.0F);
                GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-20.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.scale((double)1.5F, (double)1.5F, (double)1.5F);
                if (Minecraft.getMinecraft().player.isHandActive()) {
                    float mOffset = (float)(Minecraft.getMinecraft().player.ticksExisted % 30 * 12);
                    float nOffset = (float)(Minecraft.getMinecraft().player.ticksExisted % 20 * 18);
                    float oOffset = (float)(Minecraft.getMinecraft().player.ticksExisted % 10 * 36);
                    GlStateManager.translate(0.3 + Math.sin(Math.toRadians((double)mOffset)) / (double)6.0F, (double)0.0F, (double)0.0F);
                    GlStateManager.translate((double)0.0F, Math.sin(Math.toRadians((double)nOffset)) / (double)10.0F, (double)0.0F);
                    GlStateManager.translate((double)0.0F, (double)0.0F, Math.sin(Math.toRadians((double)oOffset)) / (double)20.0F);
                }

                renderplayer.renderRightArm(Minecraft.getMinecraft().player);
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.translate((double)-0.5F, 0.8, (double)-1.0F);
                GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.translate((double)0.0F, -1.3, (double)0.0F);
                GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
                GlStateManager.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
                GlStateManager.scale((double)1.5F, (double)3.0F, (double)1.5F);
                if (Minecraft.getMinecraft().player.isHandActive()) {
                    float mOffset = (float)(System.currentTimeMillis() / 40L % 20L * 18L);
                    float oOffset = (float)(Minecraft.getMinecraft().player.ticksExisted % 10 * 36);
                    GlStateManager.translate(Math.sin(Math.toRadians((double)(-mOffset))) / (double)6.0F, (double)0.0F, (double)0.0F);
                    GlStateManager.translate((double)0.0F, (double)0.0F, -0.03 + Math.sin(Math.toRadians((double)(-oOffset))) / (double)100.0F);
                }

                renderplayer.renderRightArm(Minecraft.getMinecraft().player);
                GlStateManager.popMatrix();
                GlStateManager.popMatrix();
            }

            Minecraft.getMinecraft().renderEngine.bindTexture(base);
            model.renderPart("base_Cube.001");
            Minecraft.getMinecraft().renderEngine.bindTexture(strings);
            model.renderPart("strings_Cube.003");
            Minecraft.getMinecraft().renderEngine.bindTexture(handle);
            model.renderPart("hand_Cube.002");
            RenderHelper.enableStandardItemLighting();
            GlStateManager.popMatrix();
        }
    }

    static {
        transform = ItemCameraTransforms.TransformType.GUI;
        model = AdvancedModelLoader.loadModel(new ResourceLocation("thaumicbases", "models/ukulele/ukulele.obj"));
        base = new ResourceLocation("thaumicbases", "textures/items/ukulele/ukulelebase.png");
        strings = new ResourceLocation("thaumicbases", "textures/items/ukulele/ukulelestrings.png");
        handle = new ResourceLocation("thaumicbases", "textures/items/ukulele/ukulelehandle.png");
    }
}