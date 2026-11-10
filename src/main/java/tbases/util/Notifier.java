package tbases.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Notifier {
    public static final String info = "[INFO]";
    public static final String warning = "[WARNING]";
    public static final String error = "[ERROR]";
    public static final String fatal = "[FATAL]";
    public static final String severe = "[SEVERE]";
    public static final String dev = "[DEV]";
    public static final String debug = "[DEBUG]";
    public static final String stdout = "[STDOUT]";
    public static final String system = "[SYSTEM]";
    public static final String mod = "[DummyCore]";
    public static final Logger logger = LogManager.getLogger();

    public Notifier() {
    }

    public static void notify(String... s) {
        notify(Level.TRACE, s);
    }

    public static void notify(Level l, String... s) {
        publish(l, buildString("[DCNotifier]", s[1], s[2], s[3]));
    }

    public static void notifyDev(String s) {
        notify(Level.DEBUG, "", "[DEV]", "[DummyCore]", s);
    }

    public static void notifyDevCustomMod(String modname, String s) {
        modname = "[" + modname + "]";
        notify(Level.DEBUG, "", "[DEV]", modname, s);
    }

    public static void notifyDebugCustomMod(String modname, String s) {
        modname = "[" + modname + "]";
        notify(Level.DEBUG, "", "[DEBUG]", modname, s);
    }

    public static void notifyDebug(String s) {
        notify(Level.DEBUG, "", "[DEBUG]", "[DummyCore]", s);
    }

    public static void notifyWarnCustomMod(String modname, String s) {
        modname = "[" + modname + "]";
        notify(Level.WARN, "", "[WARNING]", modname, s);
    }

    public static void notifyWarn(String s) {
        notify(Level.WARN, "", "[WARNING]", "[DummyCore]", s);
    }

    public static void notifyErrorCustomMod(String modname, String s) {
        modname = "[" + modname + "]";
        notify(Level.ERROR, "", "[ERROR]", modname, s);
    }

    public static void notifyError(String s) {
        notify(Level.ERROR, "", "[ERROR]", "[DummyCore]", s);
    }

    public static void notifyFatalCustomMod(String modname, String s) {
        modname = "[" + modname + "]";
        notify(Level.FATAL, "", "[FATAL]", modname, s);
    }

    public static void notifyFatal(String s) {
        notify(Level.FATAL, "", "[FATAL]", "[DummyCore]", s);
    }

    public static void notifySystem(String s) {
        notify(Level.TRACE, "", "", "[SYSTEM]", s);
    }

    public static void notifySimple(String s) {
        notify(Level.TRACE, "", "", "[DummyCore]", s);
    }

    public static void notifyInfo(String s) {
        notify(Level.INFO, "", "", "[DummyCore]", s);
    }

    public static void notifyCustomMod(String modname, String s) {
        modname = "[" + modname + "]";
        notify(Level.INFO, "", "", modname, s);
    }

    public static String buildString(String... strings) {
        return strings[0] + " " + strings[1] + " " + strings[2] + " " + strings[3] + " ";
    }

    public static void publish(Level l, String s) {
        logger.log(l, s);
    }

    public static void publish(String s) {
        publish(Level.TRACE, s);
    }
}