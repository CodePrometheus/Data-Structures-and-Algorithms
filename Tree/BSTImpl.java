import java.util.ArrayList;

/**
 * @Author: zzStar
 * @Date: 11-29-2020 10:50
 *
 * 树结构本身是一种天然的的组织结构，用树存储数据能更加高效地搜索。
 *
 * 二叉树：和链表一样，动态数据结构。
 * 1）、对于每一个节点，最多能分成2个节点，即左孩子和右孩子。
 * 2）、没有孩子的节点称为叶子节点。
 * 3）、每一个孩子节点最多只能有一个父亲节点。
 * 4）、二叉树具有天然的递归结构，即每个节点的左右子树都是二叉树。
 *
 * 注意：一个节点也是二叉树、空也是二叉树。
 *
 * 二叉树的分类：
 * 1）、满二叉树：除了叶子节点外，每个节点都有两个子节点。
 *
 *
 * 二分搜索树：
 * 1）、二分搜索树是一个二叉树，且其每一颗子树也是二分搜索树。
 * 2）、二分搜索树的每个节点的值大于其左子树的所有节点的值，小于其右子树的所有节点的值。
 * 3）、存储的元素必须有可比较性。
 * 4）、通常来说，二分搜索树不包含重复元素。如果想包含重复元素的话，只需定义：
 *     左子树小于等于节点；或者右子树大于等于节点。注意：数组和链表可以有重复元素。
 *
 什么是遍历操作？
 * 1）、遍历就是把所有的节点都访问一遍。
 * 2）、访问的原因和业务相关。
 * 3）、在线性结构下，遍历是极其容易的，但是在树结构中，遍历会稍微有点难度。
 *
 * 如何对二叉树进行遍历？
 *     对于遍历操作，两颗子树都要顾及。
 *
 * 前序遍历：最自然和常用的遍历方式。规律：根左右
 * 中序遍历：规律：左根右
 * 后序遍历：中序遍历的结果就是我们在二叉搜索树中所有数据排序后的结果。规律：左右根。应用：为二分搜索树释放内存。
 *
 * 心算出二叉搜索树的前中后序遍历：每一个二叉树都会被访问三次，从根节点出发，
 *      前序遍历：当一个节点被访问第一次就记录它。
 *      中序遍历：当一个节点被访问第二次的时候记录它。
 *      后序遍历：当一个节点被访问第三次的时候才记录它。
 *
 * 前序遍历的非递归实现（深度优先遍历）：需要使用栈记录下一步被访问的元素。
 * 对于二叉搜索树的非递归实现一般有两种写法：
 * 1）、经典教科书写法。
 * 2）、完全模拟系统调用栈的写法。
 *
 * 层序遍历（广度优先遍历）：需要使用队列记录当前出队元素的左右子节点。
 * 广度优先遍历的意义：
 * 1）、在于快速地查询要搜索的元素。
 * 2）、更快地找到问题的解。
 * 3）、常用于算法设计中——无权图最短路径。
 * 4）、联想对比图的深度优先遍历与广度优先遍历。
 *
 * 从二分搜索树中删除最小值与最大值：
 * 往左走的最后一个节点即是存有最小值的节点，往右走的最后一个节点即是存有最大值的节点。
 *
 * 删除二分搜索树种的任意元素：
 * 1）、删除只有左孩子的节点。
 * 2）、删除只有右孩子的节点。
 * 3）、删除具有左右孩子的节点：
 *      1、找到 s = min(d->right)
 *      s 是 d 的后继(successor)节点，也即 d 的右子树中的最小节点。
 *      s->right = delMin(d->right)
 *      s->left = d->left
 *      删除 d，s 是新的子树的根。
 *      2、找到 p = max(d->left)
 *      p 是 d 的前驱(predecessor)节点。
 *
 * 如何高效实现 rank（E 是排名第几的元素）？
 * 如何高效实现 select（查找排名第10的元素）？
 * 最好的方式是实现一个维护 size 的二分搜索树：
 *      给 Node 节点添加新的成员变量 size。
 * 维护 depth 的二分搜索树。
 * 维护 count 支持重复元素的二分搜索树。 */
public class BSTImpl<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTImpl() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加新的元素(key, value)
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以node为根的二分搜索树中插入元素(key, value)，递归算法
    // 返回插入新节点后二分搜索树的根
    private Node add(Node node, K key, V value) {

        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else // key.compareTo(node.key) == 0
            node.value = value;

        return node;
    }

    // 返回以node为根节点的二分搜索树中，key所在的节点
    private Node getNode(Node node, K key) {

        if (node == null)
            return null;

        if (key.equals(node.key))
            return node;
        else if (key.compareTo(node.key) < 0)
            return getNode(node.left, key);
        else // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key) {

        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    // 返回以node为根的二分搜索树的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key) {

        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key) {

        if (node == null)
            return null;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            return node;
        } else {   // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 待删除节点左右子树均不为空的情况

            // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            BSTImpl<String, Integer> map = new BSTImpl<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }

        System.out.println();
    }

}
