import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 将链表优化为TreeSet
 * 建图 O(E logV)
 * 是否相邻 O(logV)
 * 查找点的所有临边 O(degree(v)) O(V)
 *
 * @Author: zzStar
 * @Date: 04-22-2021 11:26
 */
public class Graph {

    private int V;

    private int E;

    private TreeSet<Integer>[] adj;

    public Graph(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        V = scanner.nextInt();
        if (V < 0) {
            throw new IllegalArgumentException("V must be non-negative");
        }
        adj = new TreeSet[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new TreeSet<>();
        }
        E = scanner.nextInt();
        if (E < 0) {
            throw new IllegalArgumentException("E must be non-negative");
        }
        for (int i = 0; i < E; i++) {
            int a = scanner.nextInt();
            validateVertex(a);
            int b = scanner.nextInt();
            validateVertex(b);

            // 自环边
            if (a == b) {
                throw new IllegalArgumentException("Self Loop is Detected");
            }

            // 平行边
            if (adj[a].contains(b)) {
                throw new IllegalArgumentException("Parallel Edges are Detected");
            }
            adj[a].add(b);
            adj[b].add(a);
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex " + v + " is invalid");
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("V = %d, E = %d \n", V, E));
        for (int v = 0; v < V; v++) {
            stringBuilder.append(String.format("%d :", v));
            for (int w : adj[v]) {
                stringBuilder.append(String.format(" %d ", w));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // 度
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Graph list = new Graph("g.txt");
        System.out.println(list);
    }
}
