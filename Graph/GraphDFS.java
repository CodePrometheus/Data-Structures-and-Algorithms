import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * 深度优先遍历
 * 复杂度 O(V+E)
 *
 * @Author: zzStar
 * @Date: 04-22-2021 13:21
 */
public class GraphDFS {

    private Graph g;

    // 是否被遍历过
    private boolean[] memo;

    // 先序
    private ArrayList<Integer> pre = new ArrayList<>();
    // 后序
    private ArrayList<Integer> post = new ArrayList<>();

    public GraphDFS(Graph g) {
        this.g = g;
        memo = new boolean[g.V()];
        // 保证是否联通对结果无影响
        for (int v = 0; v < g.V(); v++) {
            if (!memo[v]) {
                dfs(v);
            }
        }
        dfs(0);
    }

    /**
     * 深度优先遍历
     *
     * @param v
     */
    private void dfs(int v) {
        // 遍历记录
        memo[v] = true;
        pre.add(v);
        // 遍历所有的相邻节点
        for (int w : g.adj(v)) {
            // 如果没有在备忘录里，递归遍历
            if (!memo[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }


    public static void main(String[] args) throws FileNotFoundException {
        Graph graph = new Graph("g.txt");
        GraphDFS graphDFS = new GraphDFS(graph);
        System.out.println(graphDFS.pre);
        System.out.println(graphDFS.post);
    }
}
