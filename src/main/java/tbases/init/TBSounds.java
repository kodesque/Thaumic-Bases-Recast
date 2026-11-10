package tbases.init;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import tbases.events.RegisterHandlers;

public class TBSounds {
    public static SoundEvent buffing;
    public static SoundEvent calming;
    public static SoundEvent confusion;
    public static SoundEvent electric;
    public static SoundEvent fire_loop;
    public static SoundEvent growth;
    public static SoundEvent knowledge;
    public static SoundEvent love;
    public static SoundEvent resistance;
    public static SoundEvent revolver_no_ammo;
    public static SoundEvent revolver_reload;
    public static SoundEvent revolver_shot;
    public static SoundEvent simple;

    public TBSounds() {
    }

    public static void registerSounds() {
        fire_loop = getRegisteredSoundEvent("thaumicbases:fire_loop");
        revolver_shot = getRegisteredSoundEvent("thaumicbases:revolver_shot");
        revolver_no_ammo = getRegisteredSoundEvent("thaumicbases:revolver_no_ammo");
        revolver_reload = getRegisteredSoundEvent("thaumicbases:revolver_reload");
    }

    private static SoundEvent getRegisteredSoundEvent(String soundNameIn) {
        ResourceLocation resource = new ResourceLocation(soundNameIn);
        SoundEvent sound = (SoundEvent)(new SoundEvent(resource)).setRegistryName(soundNameIn);
        RegisterHandlers.Sounds.SOUNDS.add(sound);
        return sound;
    }
}
