package tbases.utils.dummy;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Core {
    public static final ArrayList<DCMod> registeredMods = new ArrayList();
    public static File mcDir;

    public Core() {
    }

    public static boolean isModRegistered(Class<?> mod) {
        for(DCMod dcm : registeredMods) {
            if (dcm.modClass.equals(mod)) {
                return true;
            }
        }

        return false;
    }

    public static DCMod getModFromClass(Class<?> c) {
        for(DCMod dcm : registeredMods) {
            if (dcm.modClass.equals(c)) {
                return dcm;
            }
        }

        return null;
    }

    private static void registerMod(Class<?> c, String name, boolean addCreativeTabs) {
        if (!isModRegistered(c)) {
            DCMod mod = new DCMod(c, name);

            registeredMods.add(mod);
        } else {
            Notifier.notifyError(name + "[classPath:" + c + "]Already has a DummyCore mod associated with it(is already registered), ignoring");
        }

    }

    private static void registerConfigurationFileForMod(Class<?> c, String path) {
        try {
            if (!isModRegistered(c)) {
            } else {
                DCMod mod = getModFromClass(c);
                File file = new File(path, mod.ufName + ".cfg");
                if (file.isDirectory()) {
                    file.delete();
                }

                if (!file.exists()) {
                    file.createNewFile();
                }

                Configuration cfg = new Configuration(file, true);
                cfg.save();
                mod.injectFMLConfig(cfg);
                Notifier.notifySimple("Configuration File for mod " + mod + " was successfully created with path " + path + mod.ufName + ".cfg");
            }
        } catch (IOException e) {
            DCMod mod = getModFromClass(c);
        }

    }

    public static void registerModAbsolute(Class<?> c, String modname, String configPath, IDummyConfig config) {
        registerModAbsolute(c, modname, configPath, config, true);
    }

    public static void registerModAbsolute(Class<?> c, String modname, String configPath, IDummyConfig config, boolean addCreativeTabs) {
        registerMod(c, modname, addCreativeTabs);
        registerConfigurationFileForMod(c, configPath);
        registerConfigurationHandler(config, c);
        loadConfigForMod(c);
    }

    public static Configuration getConfigFileForMod(Class<?> c) {
        if (!isModRegistered(c)) {
            Notifier.notifyError("Catched an attempt to get configuration for a not registered mod, things are about go wery wrong! Offendor: " + c);
            return null;
        } else {
            return getModFromClass(c).fmlCfg;
        }
    }

    private static void registerConfigurationHandler(IDummyConfig config, Class<?> c) {
        if (!isModRegistered(c)) {
        } else {
            DCMod mod = getModFromClass(c);
            mod.injectConfig(config);
            Notifier.notifySimple("IDummyConfing for mod " + mod + " was successfully created");
        }
    }

    public static void loadConfigForMod(Class<?> c) {
        if (!isModRegistered(c)) {
        } else {
            DCMod mod = getModFromClass(c);
            mod.cfg.load(mod.fmlCfg);
            mod.fmlCfg.save();
        }

    }
}