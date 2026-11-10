package tbases.client.render.tiles;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;
import tbases.common.tiles.TileOverchanter;

@SideOnly(Side.CLIENT)
public class RenderOverchanter extends TileEntitySpecialRenderer<TileOverchanter> {
    public RenderOverchanter() {
    }

    public void render(TileOverchanter ped, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(ped, x, y, z, partialTicks, destroyStage, alpha);
        if (!ped.isEmpty() && !ped.inventory.isEmpty()) {
            float ticks = (float) Minecraft.getMinecraft().getRenderViewEntity().ticksExisted + partialTicks;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)x + 0.5F, (float)y + 0.75F, (float)z + 0.5F);
            GL11.glScaled((double)1.25F, (double)1.25F, (double)1.25F);
            GL11.glRotatef(ticks % 360.0F, 0.0F, 1.0F, 0.0F);
            ItemStack is = ped.inventory.copy();
            is.setCount(1);
            EntityItem entityitem = new EntityItem(Minecraft.getMinecraft().world, (double)0.0F, (double)0.0F, (double)0.0F, is);
            entityitem.hoverStart = 0.0F;
            RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
            rendermanager.renderEntity(entityitem, (double)0.0F, (double)0.0F, (double)0.0F, 0.0F, 0.0F, false);
            GL11.glPopMatrix();
        }

    }
}
