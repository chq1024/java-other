package module.thread;

/**
 * @author bk
 */
public class demo39 {

    public void run() {
        synchronized (this) {
            System.out.println("one");
            synchronized (this) {
                System.out.println("two");
            }
        }
    }

    public static void main(String[] args) {
        demo39 demo39 = new demo39();
        demo39.run();
    }
}
