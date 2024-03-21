package module.spi;

/**
 * @author bk
 */
public class Japan implements IPerson{
    @Override
    public void sto(String message) {
        System.out.println("Japan say: " + message);
    }
}
