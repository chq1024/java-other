package module;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bk
 */
public class SolveHanota {

    public void move(List<Integer> src,List<Integer> tg) {
        Integer remove = src.remove(src.size() - 1);
        tg.add(remove);
    }

    public void dfs(int n,List<Integer> source,List<Integer> buf,List<Integer> target) {
        if (n == 1) {
            move(source,target);
            return;
        }
        dfs(n-1,source,target,buf);
        System.out.println(n);
        move(source,target);
        dfs(n-1,buf,source,target);
    }


    public static void main(String[] args) {
        List<Integer> A = new ArrayList<>();
        A.add(1);
        A.add(2);
        A.add(3);
        A.add(4);
        A.add(5);
        A.add(6);
        // buf
        List<Integer> B = new ArrayList<>();
        // target
        List<Integer> C = new ArrayList<>();
        SolveHanota solveHanota = new SolveHanota();
        solveHanota.dfs(A.size(),A,B,C);
        System.out.println(C.toString());
    }
}
