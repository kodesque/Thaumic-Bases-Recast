package tbases.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketTB implements IMessage {
    int id;
    NBTTagCompound sent;

    public PacketTB() {
    }

    public PacketTB(NBTTagCompound tag, int i) {
        this.id = i;
        this.sent = tag;
    }

    public void fromBytes(ByteBuf buf) {
        this.id = ByteBufUtils.readVarInt(buf, 1);
        this.sent = ByteBufUtils.readTag(buf);
    }

    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeVarInt(buf, this.id, 1);
        ByteBufUtils.writeTag(buf, this.sent);
    }
}
