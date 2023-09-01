package module.jdkProxy;

import java.lang.reflect.InvocationHandler;

/**
 * @author bk
 */
public class InterfaceImpl implements AbstractInterface{

    @Override
    public void sayMessage(String msg, InvocationHandler invocationHandler) {
        System.out.println("调用实现类：" + this.getClass().getSimpleName());
        System.out.println(msg);
    }

}
