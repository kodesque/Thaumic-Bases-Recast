package tbases.events;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import tbases.root.TBCore;

public class TBEventHandler {
    public static int clientUkuleleSoundPlayDelay = 0;

    public TBEventHandler() {
    }

    @SubscribeEvent
    public void clientWorldTickEvent(TickEvent.ClientTickEvent event) {
        World world = TBCore.proxy.clientWorld();
        if (event.side == Side.CLIENT && event.phase == TickEvent.Phase.END && world != null && world.provider != null) {
            --clientUkuleleSoundPlayDelay;
        }

    }
}
