package tbases.events;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import tbases.init.TBItems;

@Mod.EventBusSubscriber(
        value = {Side.CLIENT},
        modid = "thaumicbases"
)
public class ModelRegistrationEvents {
    public ModelRegistrationEvents() {
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        OBJLoader.INSTANCE.addDomain("thaumicbases");

        for(Item item : ForgeRegistries.ITEMS.getValues()) {
            if (item.equals(TBItems.ukulele)) {
                for(int i = 0; i < 9; ++i) {
                    ModelLoader.setCustomModelResourceLocation(item, i, new ModelResourceLocation(item.getRegistryName(), "inventory"));
                }
            }
        }

    }
}