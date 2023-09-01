package module.jdkProxy;

import java.lang.reflect.InvocationHandler;

/**
 * @author bk
 */
public interface AbstractInterface {

    void sayMessage(String msg, InvocationHandler invocationHandler);
}
