package tbases.common.handlers;

import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import tbases.common.libs.TBSounds;
import tbases.core.TBCore;

import java.util.LinkedList;
import java.util.List;

public class RegisterHandlers {

    @Mod.EventBusSubscriber(modid = TBCore.modid)
    public static class Sounds
    {
        public static final List<SoundEvent> SOUNDS = new LinkedList<>();

        static void add(SoundEvent sound)
        {
            SOUNDS.add(sound);
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<SoundEvent> event)
        {
            SOUNDS.stream().forEach(sound -> event.getRegistry().register(sound));
        }
    }

    public static void init(){
        TBSounds.registerSounds();
    }

}
