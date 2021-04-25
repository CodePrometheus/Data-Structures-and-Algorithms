import java.util.ArrayList;

/**
 * 红黑树的实现  与2-3等价
 *
 * @param <K>
 * @param <V>
 * @Author: zzStar
 * @Date: 11-28-2020 16:43
 * <p>
 * 算法导论中给出的5条红黑树的定义太生硬，并不能让人理解到底什么才是红黑树。
 * 而算法4的作者 Robert Sedgewick 是红黑树的发明人之一，而他正是现代计算机科学之父
 * Donald Knuth 的弟子，可以挑战下 Donald Knuth 的两大著作：《计算机编程的艺术》
 * <p>
 * 红黑树与2-3树的等价性。理解了2-3树，对我们理解红黑树与B类树有巨大帮助。
 * <p>
 * 2-3树：
 * 1、满足二分搜索树的基本性质。
 * 2、节点可以存放一个元素或两个元素。
 * 3、每个节点有2个或者3个孩子——这正是2-3树名称的由来。
 * 4、存放一个元素二个孩子的节点称为2节点，存放二个元素三个孩子的节点成为3节点。
 * 5、2-3树是一颗绝对平衡（对于任意一个节点来说，左右子树的高度一定是相同的）的数。
 * <p>
 * 2-3树如何维持绝对的平衡？
 * 1、添加节点永远不会添加到一个空节点，而是跟最后的一个叶子节点做融合。
 * 2、暂时形成的一层4节点会分裂成两层2节点，暂时形成的两层4节点会分裂成三层2节点。
 * 3、生成的新的树中根节点会跟顶部根节点做融合（形成3节点或4节点），以保持绝对平衡。
 * <p>
 * 红黑树与2-3树的等价性：
 * 1、红黑树就是只有一个元素节点的2-3树。
 * 2、红黑的连接线表示相连节点原先是同在一个3节点。
 * 此时红黑的节点与它相连的黑色父节点一起表示之前的一个3节点。
 * 所以所有红黑的节点一定都是向左倾斜的。
 * 即3节点的左侧的节点在红黑树中是一个红色的节点。
 * 3、2节点的根是黑色的，3节点的右侧节点是红色的，左侧是黑色的。
 * 红黑树：
 * 1、初始化的根节点为红色。
 * 2、红黑树是保持"黑平衡"的二叉树，它的本质就是在于2-3树是一个保持绝对平衡的二叉树。
 * 由于是"黑平衡"，所以严格意义上，不是平衡二叉树。
 * 3、最大高度 2logn：即每一个节点都是一个3节点，3节点对应一个黑色与红色连接的树模板。
 * 但它的时间复杂度依然是 O(logn)的，虽然比 AVL 树（偏查询）总体性能要慢，但它的重要性在于其添加
 * 元素与删除元素相比于 AVL 树来说要快速一些。
 * <p>
 * 理解了红黑树与2-3树的等价性，我们就可以很容易理解《算法导论》中对于红黑树的5点定义：
 * 1、每一个节点可能是红色或黑色。
 * 2、根节点是黑色的。
 * 3、每一个叶子节点（最后的空节点）一定是黑色的。
 * 4、如果一个节点是红色的，那么他的孩子节点一定都是黑色。
 * 5、从任意一个节点到叶子节点，经过的黑色节点一定是一样多的。
 * <p>
 * 它们可以简化为：
 * 1、所有节点非红即黑。
 * 2、根节点为黑色。
 * 3、最后的 NULL 节点为黑。
 * 4、红节点的孩子一定为黑。（红色性质）
 * 5、黑平衡。（黑色性质）
 * <p>
 * 对于红黑树，任何不平衡都会在三次旋转内解决。红黑树不仅能和2-3树等价，
 * 还和2-4树等价，但是4-node只能以RBR的形状表示。
 * 用2-4树去理解《算法导论》中红黑树的实现。
 * （特殊的红黑树：左倾红黑树）手写红黑树添加节点的逻辑，只要在二分搜索树实现的基础上对添加逻辑进行修改即可：
 * 1、永远添加红色节点：Node 构造器中初始化 color 为 RED。
 * 2、保持根节点为黑色节点。
 * 3、如果红色节点在右侧，需要进行左旋转到左侧。
 * （注意：左旋转这个子过程产生的树结构并不一定满足红黑树的性质）
 * 4、在2-3树中的2节点添加一个新节点后会进行颜色翻转（flipColors），即根黑子红变为根红子黑。
 * 5、如果红色节点在左侧，需要进行右旋转到右侧。
 * 6、当前节点表示跟父节点融合在一起时需要设置为红色。
 * 7、添加时的最复杂的情况：在2-3树的3节点中添加一个中间值节点，此时需要
 * 维护红黑树的逻辑链条——左旋转、右旋转、颜色翻转。而且它的组成过程正符合其它的添加情况，
 * 在实现时可以直接复用。red_black_add.png
 * 8、红黑树性质的维护时机：和 AVL 树一样，添加节点后回溯向上维护。
 * 红黑树的性能总结：
 * 1、对于完全随机的数据，普通的二分搜索树即可。
 * 但它的缺点在于极端情况下会退化成链表或者是高度不平衡。
 * 2、对于查询较多的情况，AVL 树更好。
 * 3、而红黑树综合统计性能（综合增删改查所有操作）更优，
 * 尤其是添加、删除操作，但是红黑树牺牲了平衡性（2logn 的高度）。
 * 4、手写红黑树删除节点的逻辑需要考虑的请求比添加逻辑更复杂，更琐碎，仅适合装逼。
 * 右倾红黑树实现？
 * 另一种统计性能优秀的树结构：Splay Tree（伸展树），
 * 利用了局部性原理——刚被访问的内容下次高概率被再次访问。
 * <p>
 * 基于红黑树的 Map 和 Set：java.util 中的 TreeMap 和 TreeSet 就是基于红黑树实现的。
 * 红黑树的其它实现：查阅算法导论中的红黑树的实现（添加与删除）。
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
