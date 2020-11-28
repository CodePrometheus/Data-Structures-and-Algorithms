import java.util.ArrayList;

/**
 * 红黑树的实现  与2-3等价
 *
 * @Author: zzStar
 * @Date: 11-28-2020 16:43
 */
public class RBTree<K extends Comparable<K>, V> {

    /**
     * 1.每个节点或者是红色的，或者是黑色的
     * 2.根节点是黑色的
     * 3.每一个叶子节点（最后的空节点）是黑色的
     * 4.如果一个节点是红色的，那么他的孩子节点都是黑色的（因为红节点对应的是2-3树中的3节点的左节点，其孩子为左节点或者是中间节点）
     * 5.从任意一个节点到叶子节点，经过的黑色节点是一样多的（’黑平衡‘）
     * <p>
     * 最大高度2logN 红黑树的增删改查复杂度都是O(logN)
     */
    private final static boolean RED = true;
    private final static boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        // 维护一个颜色的布尔值
        public boolean color;


        public Node(K kay, V value) {
            this.key = kay;
            this.value = value;
            left = null;
            right = null;
            color = RED;
        }
    }


    private Node root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判断结点的颜色
    private boolean isRed(Node node) {
        if (node == null) {
            return BLACK;
        }
        return node.color;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {

        Node x = node.right;
        // 左 👈
        node.right = x.left;
        x.left = node;

        // 同时维护颜色
        x.color = node.color;
        node.color = RED;

        return x;
    }

    //     node                   x
    //    /   \     右旋转       /  \
    //   x    T2   ------->   y   node
    //  / \                       /  \
    // y  T1                     T1  T2
    private Node rightRotate(Node node) {

        Node x = node.left;
        // 右 👉
        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    // 颜色翻转 辅助维护添加新元素
    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    /**
     * 接下来的逻辑与AVT同理
     */
    // 向RBT中添加新的元素(key, value)
    // 添加等价于在2-3树的3节点上融合一个新元素
    public void add(K key, V value) {
        root = add(root, key, value);
        // 最终根结点为黑色结点
        root.color = BLACK;
    }

    //     node                 node                 T1
    //    /         左旋转       /       右旋转       /  \         颜色翻转
    //   x         ------->    T1     --------->   x    node     -------->
    //    \                    /
    //    T1                  x
    // 向以node为根的RBT中插入元素(key, value)，递归算法
    // 返回插入新节点后RBT的根
    private Node add(Node node, K key, V value) {

        if (node == null) {
            size++;
            // 默认插入红色节点
            return new Node(key, value);
        }

        // 确定位置
        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else // key.compareTo(node.key) == 0
        {
            node.value = value;
        }

        /**
         * 同样，在这样维护红黑树的性质
         */

        // 右孩子是红色，而左孩子不是，进行左翻转
        if (isRed(node.right) && !isRed(node.left)) {
            node = leftRotate(node);
        }

        // 在一个黑节点左侧有连续两个红节点，进行右翻转
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rightRotate(node);
        }

        // 左右都是红节点，进行颜色的翻转
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    // 返回以node为根节点的RBT中，key所在的节点
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

    // 返回以node为根的RBT的最小值所在的节点
    private Node minimum(Node node) {
        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    // 删除掉以node为根的RBT中的最小节点
    // 返回删除节点后新的RBT的根
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

    // 从RBT中删除键为key的节点
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

        System.out.println("===LICENSE===");

        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("LICENSE", words)) {
            System.out.println("Total words: " + words.size());

            RBTree<String, Integer> map = new RBTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of a: " + map.get("a"));
            System.out.println("Frequency of you: " + map.get("you"));
        }

        System.out.println();
    }

}
