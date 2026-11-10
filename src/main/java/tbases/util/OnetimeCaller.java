package tbases.util;

public class OnetimeCaller {
    public Runnable call;

    public OnetimeCaller(Runnable run) {
        this.call = run;
    }

    public void call() {
        if (this.call != null) {
            this.call.run();
        }

        this.call = null;
    }
}