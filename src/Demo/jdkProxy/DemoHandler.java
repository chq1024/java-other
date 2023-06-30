package Demo.jdkProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author bk
 */
public class DemoHandler implements InvocationHandler{

    private Object realProxy;
    public DemoHandler(Object proxy) {
        this.realProxy = proxy;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("进入处理类");
        // 调用方法前
        preHandler();
        method.invoke(realProxy,args);
        afterHandler();
        return null;
    }

    private void preHandler() {
        System.out.println("调用前");
    }

    private void afterHandler() {
        System.out.println("调用后");
    }

}
