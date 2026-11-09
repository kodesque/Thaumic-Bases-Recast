package tbases.utils.dummy;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModContainer;

public class DCMod {
    public Object modInstance;
    public Class<?> modClass;
    public ModContainer modContainer;
    public String modid;
    public String version;
    public String ufName;
    public IDummyConfig cfg;
    public Configuration fmlCfg;

    public DCMod(Class<?> mod, String uName) {
        this.ufName = uName;
        this.modClass = mod;
        this.modContainer = Loader.instance().activeModContainer();
        this.modInstance = this.modContainer.getMod();
        this.modid = this.modContainer.getModId();
        this.version = this.modContainer.getVersion();
        if (Loader.instance().hasReachedState(LoaderState.INITIALIZATION)) {
            Notifier.notifyWarn(String.format("Mod %s is being registered in %s state! This can cause all kinds of unexpected behaviour!", this.ufName + "[modid:" + this.modid + ",classpath:" + mod + "]", Loader.instance().getLoaderState().toString()));
        }

    }

    public DCMod injectConfig(IDummyConfig cfg) {
        this.cfg = cfg;
        return this;
    }

    public DCMod injectFMLConfig(Configuration cfg) {
        this.fmlCfg = cfg;
        return this;
    }

    public String toString() {
        return this.ufName + "[modid:" + this.modid + ",classPath:" + this.modClass + "]";
    }
}