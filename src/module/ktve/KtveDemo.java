package module.ktve;

import java.util.List;

/**
 * @author bk
 */
public class KtveDemo<T> {

    public <E extends T> void method1(E parma) {

    }

    public void method2(List<? super T> param) {

    }

    public static void main(String[] args) {

    }
}