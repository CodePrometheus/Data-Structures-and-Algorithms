import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 邻接矩阵
 * 建图 O(E)
 * 查看两点是否相邻 O(1)
 * 求一点的相邻节点 O(V)
 * 空间复杂度 O(V^2)
 * 查找点的所有临边 O(V)
 *
 * @Author: zzStar
 * @Date: 04-22-2021 11:02
 */
public class AdjMatrix {

    private int V;

    private int E;

    private int[][] adj;

    public AdjMatrix(String filename) throws FileNotFoundException {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        V = scanner.nextInt();
        if (V < 0) {
            throw new IllegalArgumentException("V must be non-negative");
        }
        adj = new int[V][V];
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
            if (adj[a][b] == 1) {
                throw new IllegalArgumentException("Parallel Edges are Detected");
            }
            adj[a][b] = 1;
            adj[b][a] = 1;
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
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                stringBuilder.append(String.format("%d ", adj[i][j]));
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
        return adj[v][w] == 1;
    }

    public ArrayList<Integer> adj(int v) {
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[v][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    // 度
    public int degree(int v) {
        return adj(v).size();
    }

    public static void main(String[] args) throws FileNotFoundException {
        AdjMatrix matrix = new AdjMatrix("g.txt");
        System.out.println(matrix);
    }
}
