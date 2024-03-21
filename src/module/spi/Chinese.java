package module.spi;

/**
 * @author bk
 */
public class Chinese implements IPerson{
    @Override
    public void sto(String message) {
        System.out.println("Chinese say: " + message);
    }
}
