/**
 * 更为适用的，基于rank的优化
 * rank[i] 表示根节点为i的树的高度
 *
 * @Author: zzStar
 * @Date: 11-23-2020 22:40
 */
public class UnionFindRank implements UF {

    private int[] parent;

    // 表示树的层数
    private int[] rank;

    public UnionFindRank(int size) {
        parent = new int[size];
        rank = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            // 初始的时候层数自然也是1
            rank[i] = 1;
        }
    }

    // 查看元素p，q 是否所属同一个集合  0(h)
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    // 合并元素p，q所属的集合
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        /**
         * 根据两个元素所在的树的rank不同判断合并方向
         * 将rank低的集合并到rank高的集合上
         */
        if (rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
            // 注意 -> 这里不需要维护rank数组 pRoot这棵树的高度最多 = qRoot的子树的高度
            // rank[qRoot] += rank[pRoot];
        } else if (rank[qRoot] < rank[pRoot]) {
            parent[qRoot] = pRoot;
        } else {
            // 两棵树高度相等，合并层数就多个1
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找元素p所对应的集合编号 0(h)复杂度，h为树的高度
    private int find(int p) {
        if (p < 0 && p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound");
        }

        // 不是根节点
        while (p != parent[p]) {
            // 就不断的去找p的根节点
            p = parent[p];
        }
        return p;
    }

}
