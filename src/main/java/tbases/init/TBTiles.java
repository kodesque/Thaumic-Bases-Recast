package tbases.init;

import net.minecraftforge.fml.common.registry.GameRegistry;
import tbases.common.tiles.TileBraizer;
import tbases.common.tiles.TileOverchanter;

public class TBTiles {
    public TBTiles() {
    }

    public static void setup() {
        GameRegistry.registerTileEntity(TileOverchanter.class, "thaumicbases.overchanter");
        GameRegistry.registerTileEntity(TileBraizer.class, "thaumicbases.brazier");
    }
}
