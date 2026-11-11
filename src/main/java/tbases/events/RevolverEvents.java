package tbases.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import tbases.common.items.ItemRevolver;
import tbases.init.custom.RevolverUpgrade;

@Mod.EventBusSubscriber(
        modid = "thaumicbases"
)
public class RevolverEvents {
    public RevolverEvents() {
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public static void renderZoomEvent(FOVUpdateEvent event) {
        if (Minecraft.getMinecraft().player.isSneaking() && !Minecraft.getMinecraft().player.getHeldItemMainhand().isEmpty() && Minecraft.getMinecraft().player.getHeldItemMainhand().getItem() instanceof ItemRevolver) {
            event.setNewfov(0.9F / (float)(ItemRevolver.getUpgradeLevel(Minecraft.getMinecraft().player.getHeldItemMainhand(), RevolverUpgrade.accuracy) + 1));
        }

    }
}
