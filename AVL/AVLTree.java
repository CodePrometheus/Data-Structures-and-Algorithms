import java.util.ArrayList;

/**
 * AVL实现 自平衡机制
 *
 * @Author: zzStar
 * @Date: 11-24-2020 21:16

 * AVL 的自平衡机制：
 *      1、左旋转
 *      2、右旋转
 *
 * 在什么时候维护平衡？
 *      加入节点后，沿着节点向上维护平衡性。
 *
 * 时间复杂度 O(logn)
 *
 * AVL 优化：在维护每一个节点之前，都需要对 AVL 的高度进行重新的计算，
 * 但是如果我们重新计算出的这个节点的高度与原先的高度相等的话，对于这个
 * 节点的祖先节点就不再需要维护平衡的操作了。这是因为这个节点的高度和原先
 * 一样，从它的父亲节点和祖先节点来看，它的子树的高度并没有发送变化，
 * 所以不需要维护平衡了。
 *
 * AVL 树的局限性：红黑树的旋转操作相对更少，性能更优一些。
 *
 * @param <K> key
 * @param <V> value
 */
public class AVLTree<K extends Comparable<K>, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            // 对于一个新结点来说，都是一个叶子结点
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    /**
     * 判断是否是一棵二叉搜索树
     * 利用BST中序遍历得到的结果是顺序的
     *
     * @return
     */
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        // 判断是否为升序的数组
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为一棵平衡二叉树
     *
     * @return
     */
    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {

        // 空树也满足
        if (node == null) {
            return true;
        }

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        // 分别判断左右子树
        return isBalanced(node.left) && isBalanced(node.right);

    }


    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 获取结点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }


    /**
     * RR 右旋转
     * 对节点y进行向右旋转操作，返回旋转后新的根节点x
     * //        y                              x
     * //       / \                           /   \
     * //      x   T4     向右旋转 (y)        z     y
     * //     / \       - - - - - - - ->    / \   / \
     * //    z   T3                       T1  T2 T3 T4
     * //   / \
     * // T1   T2
     */
    private Node rightRotate(Node y) {
        // 暂存
        Node x = y.left;
        Node T3 = x.right;

        // 向右旋转
        x.right = y;
        y.left = T3;

        // 更新height 只需对x，y即可
        // 并且，首先应更新y，因为x会和新的y相关的
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    /**
     * LL 左旋转
     * 对节点y进行向左旋转操作，返回旋转后新的根节点x
     * //    y                             x
     * //  /  \                          /   \
     * // T1   x      向左旋转 (y)       y     z
     * //     / \   - - - - - - - ->   / \   / \
     * //   T2  z                     T1 T2 T3 T4
     * //      / \
     * //     T3 T4
     */
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;

        return x;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        // 这里需要更新height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

        // 不满足平衡二叉树的条件
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("balanceFactor = " + balanceFactor);
//        }

        /**
         * 在这里维护平衡性
         */

        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            // 右旋转
            return rightRotate(node);
        }

        // 也即该树整体向右倾斜
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // LR   当前结点的左孩子的右子树比左子树要高
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            // 对左孩子进行左旋转
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {
            return getNode(node.right, key);
        }

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
        if (node == null) {
            return null;
        }

        // 存储删除后需要返回的新的结点
        Node retNode;

        // 判断大小，选择方向进行删除
        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
            // return node; 注意这里不直接返回
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
            // return node;
        } else {
            // 待删除节点左子树为空的情况
            if (node.left == null) {
                // 断开关系
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            //  待删除节点右子树为空的情况
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 待删除结点左右均不为空，寻找后继，比其大的最小结点
                Node successor = minimum(node.right);

                // 注意，直接删除可能破环平衡性
                successor.right = remove(node.right, successor.key);
                // successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        // 避免空指针 直接返回null，不需要进入后面的维护平衡性逻辑
        if (retNode == null) {
            return null;
        }

        // 接下来进入判断 看是否需要维护retNode的平衡性

        // 这里需要更新height
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);


        /**
         * 在这里维护平衡性
         */

        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            // 右旋转
            return rightRotate(retNode);
        }

        // 也即该树整体向右倾斜
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // LR   当前结点的左孩子的右子树比左子树要高
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            // 对左孩子进行左旋转
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;

    }

    // 返回以node为根的BST的最小值所在的结点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
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

    public static void main(String[] args) {
        System.out.println(" LICENSE ");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("LICENSE", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of you: " + map.get("you"));
            System.out.println("Frequency of can: " + map.get("can"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());

            // 检查是否打破了平衡性
            for (String word : words) {
                map.remove(word);
                if (!map.isBST() || !map.isBalanced()) {
                    throw new RuntimeException("Error");
                }
            }
        }

        System.out.println("========");

    }

}
