package tbases.utils.dummy;

import net.minecraftforge.common.config.Configuration;

public class DummyConfig implements IDummyConfig {
    public int MultiItemUID;
    public int MultiBlockUID;
    public static int dummyCoreSyncTimer;
    private static int mainMenuID;
    public boolean removeMissingTexturesErrors;
    public boolean allowCustomMainMenu;
    public static boolean enableNotifierLogging;
    public static boolean shouldChangeImage;
    public static boolean displaySorryNotificationUponRMReload;
    public static Configuration cfg;

    public DummyConfig() {
    }

    public void load(Configuration config) {
        cfg = config;
        this.removeMissingTexturesErrors = config.getBoolean("removeMissingTexturesErrors", "GLOBAL", true, "");
        enableNotifierLogging = config.getBoolean("enableNotifierLogging", "GLOBAL", true, "");
        shouldChangeImage = config.getBoolean("shouldChangeImageInCreativeTabs", "GLOBAL", true, "");
        this.allowCustomMainMenu = config.getBoolean("allowCustomMainMenu", "GLOBAL", true, "");
        displaySorryNotificationUponRMReload = config.getBoolean("displaySorryNotificationUponRMReload", "GLOBAL", true, "");
        mainMenuID = config.getInt("mainMenuID", "GLOBAL", 0, 0, Integer.MAX_VALUE, "");
        dummyCoreSyncTimer = config.getInt("syncTimer", "GLOBAL", 100, 10, 1000, "Time inbetween syncing of data. The more the number is, the worse the sync is going to be, hwever, the less packets will be sent, and, therefore, the less annoying the server lag will be.");
    }

    public static void setMainMenu(int i) {
        cfg.load();
        cfg.get("GLOBAL", "mainMenuID", 0, "").set(i);
        mainMenuID = i;
        cfg.save();
    }

    public static int getMainMenu() {
        return mainMenuID;
    }
}
