package module.cglibProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author bk
 */
public class ProxyClazz implements MethodInterceptor {

    private final Enhancer enhancer = new Enhancer();

    public Object getClazz(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("进入代理类处理");
        Object res = methodProxy.invokeSuper(o, args);
        System.out.println("执行完毕");
        return res;
    }
}
