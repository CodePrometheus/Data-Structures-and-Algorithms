/**
 * @Description: UnionFind 基于数组
 * @Author: zzStar
 * @Date: 2020/11/22 20:19
 */
public class UnionFind implements UF {

    // 数组记录数据之间的关系
    private int[] id;

    public UnionFind(int size) {
        id = new int[size];

        for (int i = 0; i < id.length; i++) {
            // 每个元素都所属不同的集合，没有任意两个元素是相连的
            id[i] = i;
        }

    }


    /**
     * 查找元素p对应的集合编号 quickFind
     *
     * @param p
     * @return
     */
    private int find(int p) {
        if (p < 0 && p >= id.length) {
            throw new IllegalArgumentException("p is out of bound");
        }
        return id[p];
    }

    // 查看元素p，q 是否所属同一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public int getSize() {
        return id.length;
    }

    // 合并元素p，q所属的集合
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
    }

}
