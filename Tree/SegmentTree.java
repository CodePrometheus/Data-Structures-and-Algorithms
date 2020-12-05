/**
 * 线段树实现，线段树不是完全二叉树，而是平衡二叉树
 *
 * @Author: zzStar
 * @Date: 11-03-2020 23:29
 * 为什么要使用 线段树？
 *  1、区间染色：m 次操作后，我们可以在区间 [i,j] 内看见多少种颜色？
 *                      使用数组实现      线段树
 *  染色操作（更新操作）      O（n)        O（logn）
 *  查询操作（查询区间）      O（n)        O（logn）
 *
 *  2、区间查询：查询一个区间的 最大值、最小值、后者区间数字和。
 *  更新：更新区间中一个元素或者一个区间的值。
 *     实质：基于区间的统计查询。
 *
 *     segment_tree.png
 *
 * 线段树不是完全二叉树，但是线段树和堆都是平衡二叉树。
 * 我们可以把线段树看作是一个满二叉树，对于最后一层不存在的节点，
 * 看作是 null 即可，所以我们可以使用数组来表示满二叉树。
 *
 * 对于满二叉树：
 * 1）、h 层，一共有 2^h - 1 个节点（大约是 2^h）
 * 2）、最后一层（h - 1层），有 2^(h - 1) 个节点。
 * 3）、最后一层的节点数大致等于前面所有层节点之和。
 * 如果区间有 n 个元素，数组表示需要有多少节点？
 *  区间固定时，需要 4n 的空间。
 *
 * 线段树的区间查询、指定 index 位置的值更新
 *
 * 线段树的区间更新：使用指定 index 位置的值更新套路需要更新叶子节点的值，时间复杂度从 O（logn）提升到 O（n），
 * 可以使用 懒惰更新：使用 Lazy 数组记录未更新的内容。
 *
 * 上述都是一维线段树，二维线段树中每一个节点都有4个孩子节点，三维线段树中每一个节点都有8个孩子节点。
 *
 * 使用链表实现的动态线段树，可以避免数组实现的线段树中要多存储 null 节点的问题。
 *
 * 区间操作相关的另一个重要的数据结构：树状数组（Binary Index Tree）。
 *
 * 区间相关的问题被归类为 RMQ（Range Minimum Query）问题 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];

        // 4n承载所有线段树
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {

        if (l > r) {
            return;
        }

        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // int mid = (l + r) / 2;
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子结点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子结点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // 返回区间[queryL, queryR]的值
    public E query(int queryL, int queryR) {
        // 边界检查，须为有效区间
        if (queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) return tree[treeIndex];

        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (queryL >= mid + 1)
            // 可以忽略左区间
            return query(rightTreeIndex, mid + 1, r, queryL, queryR);
        else if (queryR <= mid)
            return query(leftTreeIndex, l, mid, queryL, queryR);

        E leftResult = query(leftTreeIndex, l, mid, queryL, mid);
        E rightResult = query(rightTreeIndex, mid + 1, r, mid + 1, queryR);
        return merger.merge(leftResult, rightResult);
    }

    // 更新对index -> e
    public void set(int index, E e) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is illegal");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, E e) {

        // 找到
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        // treeIndex的节点分为[l...mid]和[mid+1...r]两部分
        int mid = l + (r - l) / 2;

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        if (index >= mid + 1)
            // 则去右子树
            set(rightTreeIndex, mid + 1, r, index, e);
        else // index <= mid
            set(leftTreeIndex, l, mid, index, e);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i]);
            else
                res.append("null");

            if (i != tree.length - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    private abstract static class Merger<E> {

        abstract E merge(E a, E b);
    }

    public static void main(String[] args) {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> segTree = new SegmentTree(nums,
                new Merger<Integer>() {
                    @Override
                    Integer merge(Integer a, Integer b) {
                        return a + b;
                    }
                });
        System.out.println(segTree);

        // 求和
        System.out.println(segTree.query(0, 2));
        System.out.println(segTree.query(2, 5));
        System.out.println(segTree.query(0, 5));
    }
}
