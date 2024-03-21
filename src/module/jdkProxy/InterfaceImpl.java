package module.jdkProxy;


/**
 * @author bk
 */
public class InterfaceImpl implements AbstractInterface{

    @Override
    public void sayMessage(String msg) {
        System.out.println("调用实现类：" + this.getClass().getSimpleName());
        System.out.println(msg);
    }

}
