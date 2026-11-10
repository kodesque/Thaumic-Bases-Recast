package tbases.util.dummydata;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class DummyPacketIMSG implements IMessage {
    public String dataStr;

    public DummyPacketIMSG() {
    }

    public DummyPacketIMSG(String data) {
        this.dataStr = data;
    }

    public void fromBytes(ByteBuf buf) {
        this.dataStr = ByteBufUtils.readUTF8String(buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, this.dataStr);
    }
}