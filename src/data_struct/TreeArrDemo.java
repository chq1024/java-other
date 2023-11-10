package data_struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bk
 */
public class TreeArrDemo {

    public static void main(String[] args) {

    }



}
class TreeArr {
    private List<Integer> arr = new ArrayList<>();
    public TreeArr(List<Integer> arr) {
        this.arr.addAll(arr);
    }
    public int size() {
        return this.arr.size();
    }
    public Integer val(int index) {
        return this.arr.get(index);
    }
    public int left(int index) {
        return 2 * index + 1;
    }
    public int right(int index) {
        return 2 * index + 2;
    }
    public int parent(int index) {
        return (index - 1) /2;
    }
    public List<Integer> levelOrder() {
        List<Integer> valArr = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            if (val(i) != null) {
                valArr.add(val(i));
            }
        }
        return valArr;
    }
    public void dfs(int index,String order,List<Integer> res) {
        if (val(index) == null) {
            return;
        }
        if ("pre".equals(order)) {
            res.add(val(index));
            dfs(left(index),"pre",res);
            dfs(right(index),"pre",res);
        } else if ("in".equals(order)) {
            dfs(left(index),"in",res);
            res.add(val(index));
            dfs(right(index),"in",res);
        } else if ("post".equals(order)) {
            dfs(left(index),"post",res);
            dfs(right(index),"post",res);
            res.add(val(index));
        }
    }
    public List<Integer> preOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0,"pre",res);
        return res;
    }
    public List<Integer> inOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0,"in" +
                "",res);
        return res;
    }
    public List<Integer> postOrder() {
        List<Integer> res = new ArrayList<>();
        dfs(0,"post",res);
        return res;
    }
}