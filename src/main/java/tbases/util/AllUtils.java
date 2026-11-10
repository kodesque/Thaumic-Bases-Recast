package tbases.util;

import com.google.common.collect.Streams;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import tbases.root.TBCore;
import tbases.util.dummydata.DummyPacketHandler;
import tbases.util.dummydata.DummyPacketIMSG;
import tbases.util.dummydata.DummyPacketIMSG_Tile;
import tbases.util.dummydata.ITEHasGameData;

public class AllUtils {
    public AllUtils() {
    }

    public static void createNBTTag(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            NBTTagCompound itemTag = new NBTTagCompound();
            stack.setTagCompound(itemTag);
        }
    }

    public static NBTTagCompound getStackTag(ItemStack stack) {
        createNBTTag(stack);
        return stack.getTagCompound();
    }

    /** @deprecated */
    @Deprecated
    public static Enchantment[] enchantmentList() {
        return (Enchantment[]) Streams.stream(Enchantment.REGISTRY).toArray((x$0) -> new Enchantment[x$0]);
    }

    public static void syncTileEntity(ITEHasGameData t, Side s) {
        String dataString = "||mod:DummyCore.TileSync" + t.getPosition() + t.getData();
        DummyPacketIMSG simplePacket = new DummyPacketIMSG(dataString);
        if (s == Side.CLIENT) {
            DummyPacketHandler.sendToAll(simplePacket);
        }

        if (s == Side.SERVER) {
            DummyPacketHandler.sendToServer(simplePacket);
        }

    }

    public static void syncTileEntity(NBTTagCompound tileTag, int packetID) {
        DummyPacketIMSG_Tile simplePacket = new DummyPacketIMSG_Tile(tileTag);
        TBCore.network.sendToAll(simplePacket);
    }
}
