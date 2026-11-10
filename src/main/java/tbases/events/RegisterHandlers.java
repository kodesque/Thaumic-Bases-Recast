package tbases.events;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tbases.init.TBSounds;

import java.util.LinkedList;
import java.util.List;

public class RegisterHandlers {
    public RegisterHandlers() {
    }

    public static void init() {
        TBSounds.registerSounds();
    }

    @Mod.EventBusSubscriber(
            modid = "thaumicbases"
    )
    public static class Sounds {
        public static final List<SoundEvent> SOUNDS = new LinkedList();

        public Sounds() {
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<SoundEvent> event) {
            SOUNDS.stream().forEach((sound) -> event.getRegistry().register(sound));
        }
    }
}
