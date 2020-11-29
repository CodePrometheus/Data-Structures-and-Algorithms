/**
 * 基于结点个数的大小进行合并  优化
 * 其目的也就是在于合并两棵树的时候避免新树深度每次都增加
 *
 * @Author: zzStar
 * @Date: 11-23-2020 22:00
 */
public class UnionFindSize implements UF {

    private int[] parent;

    // sz[i]表示以i为根的集合中元素个数
    private int[] sz;

    public UnionFindSize(int size) {
        parent = new int[size];
        sz = new int[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
            sz[i] = 1;
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
         * 对树的深度进行优化
         * 根据两个元素所在树的元素个数不同判断合并方向
         * 将元素个数少的集合合并到元素个数多的集合上
         */
        if (sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    // 查找元素p所对应的集合编号 0(h)复杂度，h为树的高度
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
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
