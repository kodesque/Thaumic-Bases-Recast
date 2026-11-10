package tbases.util.dummydata;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.Side;

public class DummyEvent_OnPacketRecieved extends Event {
    public final Side effectiveSide;
    public final String recievedData;
    public final EntityPlayer recievedEntity;

    public DummyEvent_OnPacketRecieved(Side s, String str, EntityPlayer pl) {
        this.effectiveSide = s;
        this.recievedData = str;
        this.recievedEntity = pl;
    }
}