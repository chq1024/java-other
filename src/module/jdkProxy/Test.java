package module.jdkProxy;

import java.lang.reflect.Proxy;

/**
 * @author bk
 */
public class Test {


    public static void main(String[] args) {
        module.jdkProxy.InterfaceImpl imp = new module.jdkProxy.InterfaceImpl();
        module.jdkProxy.DemoHandler demoHandler = new module.jdkProxy.DemoHandler(imp);
        module.jdkProxy.AbstractInterface proxyInstance = (module.jdkProxy.AbstractInterface) Proxy.newProxyInstance(
                module.jdkProxy.InterfaceImpl.class.getClassLoader(), module.jdkProxy.InterfaceImpl.class.getInterfaces(),
                demoHandler);
        proxyInstance.sayMessage("hello world", demoHandler);
    }

}
