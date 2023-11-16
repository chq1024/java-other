package data_struct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bk
 */
public class GraphDemo {

    List<Integer> vertices;
    List<List<Integer>> adjMat;

    public GraphDemo(int[] verticesArr,int[][] adjMatArr) {
        this.vertices = new ArrayList<>();
        this.adjMat = new ArrayList<>();
        for (int ve : verticesArr) {
            addVertices(ve);
        }
        for (int[] ints : adjMatArr) {
            addAdjMat(ints[0],ints[1]);
        }
    }

    public int size() {
        return vertices.size();
    }

    public void addVertices(int ve) {
        int n = size();
        vertices.add(ve);
        // 添加行
        List<Integer> newRows = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            newRows.add(0);
        }
        adjMat.add(newRows);
        // 添加列
        for (List<Integer> rows : adjMat) {
            rows.add(0);
        }
    }

    public void addAdjMat(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException();
        }
        adjMat.get(i).set(j,1);
        adjMat.get(j).set(i,1);
    }

    public void removeVertices(int index) {
        if (index > size()) {
            throw new IndexOutOfBoundsException();
        }
        vertices.remove(index);
        adjMat.remove(index);
        for (List<Integer> rows : adjMat) {
            rows.remove(index);
        }
    }

    public void removeAdjMat(int i,int j) {
        // 索引越界与相等处理
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException();
        }
        adjMat.get(i).set(j, 0);
        adjMat.get(j).set(i, 0);
    }

    /* 打印邻接矩阵 */
    public void print() {
        System.out.print("顶点列表 = ");
        System.out.println(vertices);
        System.out.println("邻接矩阵 =");
        for (List<Integer> arr : adjMat) {
            for (Integer item : arr) {
                System.out.print(item);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[] ve = new int[] {1,3,2,5,4};
//        int[][] aj = new int[][] {{1,2},{1,3},{1,5},{2,3},{2,4},{2,5},{4,5}};
        int[][] aj = new int[][] {{0,2},{0,1},{0,3},{2,1},{2,4},{2,3},{4,3}};
        GraphDemo demo = new GraphDemo(ve,aj);
        demo.print();
    }


}
