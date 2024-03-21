package module.thread;

/**
 * @author bk
 */
public class demo32 {

    private static volatile demo32 d32;

    private demo32() {

    }

    public static demo32 getInstance() {
        if (d32 == null) {
            synchronized (demo32.class) {
                if (d32 == null) {
                    d32 = new demo32();
                }
            }
        }
        return d32;
    }
}
