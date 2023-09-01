package module.cglibProxy;

/**
 * @author bk
 */
public class Test {


    public static void main(String[] args) {
        ProxyClazz proxyClazz = new ProxyClazz();
        ServiceClazz clazz = (ServiceClazz) proxyClazz.getClazz(ServiceClazz.class);
        clazz.say("hello world");
    }

}
