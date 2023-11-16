package data_struct;

import java.util.*;

/**
 * @author bk
 */
public class GraphTableDemo {

    public Map<Vertex, HashSet<Vertex>> adjList;

    public GraphTableDemo(Vertex[][] vertices) {
        this.adjList = new HashMap<>();
        for (Vertex[] vertex : vertices) {

        }
    }

    public void addVertex(Vertex vertex) {
        if (this.adjList.containsKey(vertex)) {
            return;
        }
        this.adjList.put(vertex,new HashSet<>());
    }

    public void addEdge(Vertex a, Vertex b) {
        if (!adjList.containsKey(a) || !adjList.containsKey(b) || a == b) {
            throw new IllegalArgumentException();
        }
        adjList.get(a).add(b);
        adjList.get(b).add(a);
    }

    public void removeVertex(Vertex a) {
        if (!adjList.containsKey(a)) {
            throw new IllegalArgumentException();
        }
        adjList.remove(a);
        for (HashSet<Vertex> value : adjList.values()) {
            value.remove(a);
        }
    }

    public List<Vertex> graphBFS(Map<Vertex,HashSet<Vertex>> adjList,Vertex start) {
        List<Vertex> res = new ArrayList<>();
        Set<Vertex> visited = new HashSet<>();
        visited.add(start);
        Queue<Vertex> que = new PriorityQueue<>();
        que.offer(start);
        while (!que.isEmpty()) {
            Vertex poll = que.poll();
            res.add(poll);
            for (Vertex vertex : adjList.get(poll)) {
                if (visited.contains(vertex)) {
                    continue;
                }
                que.offer(vertex);
                visited.add(vertex);
            }
        }
        return res;
    }


    public List<Vertex> graphDeep(Map<Vertex,HashSet<Vertex>> adjList,Vertex start) {
        List<Vertex> res = new ArrayList<>();
        HashSet<Vertex> visited = new HashSet<>();
        dfs(adjList,visited,res,start);
        return res;
    }

    private void dfs(Map<Vertex,HashSet<Vertex>> graph, Set<Vertex> visited, List<Vertex> res, Vertex vet) {
        res.add(vet);
        visited.add(vet);
        for (Vertex vertex : graph.get(vet)) {
            if (visited.contains(vertex)) {
                continue;
            }
            dfs(graph,visited,res,vertex);
        }
    }

    /**
     * 移除边，即
     * @param a
     * @param b
     */
    public void removeEdge(Vertex a,Vertex b) {
        if (!adjList.containsKey(a) || !adjList.containsKey(b)) {
            throw new IllegalArgumentException();
        }
        adjList.get(a).remove(b);
        adjList.get(b).remove(a);
    }

    public static void main(String[] args) {

    }
}


class Vertex {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}