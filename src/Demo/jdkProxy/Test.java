package Demo.jdkProxy;

import java.lang.reflect.Proxy;

/**
 * @author bk
 */
public class Test {


    public static void main(String[] args) {
        Demo.jdkProxy.InterfaceImpl imp = new Demo.jdkProxy.InterfaceImpl();
        Demo.jdkProxy.DemoHandler demoHandler = new Demo.jdkProxy.DemoHandler(imp);
        Demo.jdkProxy.AbstractInterface proxyInstance = (Demo.jdkProxy.AbstractInterface) Proxy.newProxyInstance(
                Demo.jdkProxy.InterfaceImpl.class.getClassLoader(), Demo.jdkProxy.InterfaceImpl.class.getInterfaces(),
                demoHandler);
        proxyInstance.sayMessage("hello world", demoHandler);
    }

}
