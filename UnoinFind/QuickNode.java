/**
 * 将每个元素，看作是一个结点
 * 孩子指向父亲
 *
 * @Author: zzStar
 * @Date: 11-23-2020 20:09
 */
public class QuickNode implements UF {

    // 数组记录数据之间的关系
    private int[] parent;

    public QuickNode(int size) {
        parent = new int[size];

        // 初始化，每个结点指向自己，每个结点独立的是一个数，未形成连接关系
        for (int i = 0; i < size; i++) {
            parent[i] = i;
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

        // 指向q的根结点
        parent[pRoot] = qRoot;
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
