package tbases.util.dummydata;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class DummyPacketIMSG_Tile implements IMessage {
    public NBTTagCompound dataTag;

    public DummyPacketIMSG_Tile() {
    }

    public DummyPacketIMSG_Tile(NBTTagCompound data, int id) {
        this.dataTag = data;
        this.dataTag.setInteger("packetID", id);
    }

    public DummyPacketIMSG_Tile(NBTTagCompound data) {
        this.dataTag = data;
    }

    public void fromBytes(ByteBuf buf) {
        this.dataTag = ByteBufUtils.readTag(buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.dataTag);
    }
}