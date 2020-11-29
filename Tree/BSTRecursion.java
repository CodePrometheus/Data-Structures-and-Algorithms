import java.util.*;

/**
 * 二叉搜索树存储的元素必须要有可比较性
 *
 * @Author: zzStar
 * @Date: 10-20-2020 17:17
 */
public class BSTRecursion<E extends Comparable<E>> {

    public static void main(String[] args) {
        BSTRecursion<Integer> bstRecursion = new BSTRecursion<>();
//        int[] nums = {5, 3, 6, 8, 4, 2};
//        for (int num : nums) {
//            bstRecursion.add(num);
//        }
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2   4    8  //
        /////////////////
//        bstRecursion.preOrder();
//        System.out.println("==============");
//
//        bstRecursion.preOrderNR();
//        System.out.println("==============");
//
//        bstRecursion.levelOrder();
//        System.out.println("==============");
//
//        System.out.println(bstRecursion);
//        System.out.println("==============");
//
//        bstRecursion.inOrder();
//        System.out.println();
//
//        System.out.println("==============");
//        bstRecursion.postOrder();
//
//        System.out.println("==============");
//        System.out.println(bstRecursion.minimum());
//        System.out.println(bstRecursion.maximum());
        Random random = new Random();
        int n = 20;
        for (int i = 0; i < n; i++) {
            bstRecursion.add(random.nextInt(1000));
        }
        // 考虑重复的元素
        ArrayList<Integer> nums = new ArrayList<>();
        while (!bstRecursion.isEmpty()) {
            nums.add(bstRecursion.removeMin());
        }
        System.out.println(nums);
        for (int i = 1; i < nums.size(); i++) {
            if (nums.get(i - 1) > nums.get(i)) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("completed");
    }

    // 隐藏细节
    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    //根结点
    private Node root;
    private int size;

    //显示的表示出逻辑，默认就为此
    public BSTRecursion() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

/*
    // 添加新的元素e
    public void add(E e) {
        if (root == null) {
            root = new Node(e);
            size++;
        } else {
            // 否则就调用下面私有的add方法
            add(root, e);
        }
    }
*/


/*
    // 向以node为根的二分搜索树中插入元素E，递归算法
    // 注意，这里新的元素e是作为node的子结点，跟上面add逻辑不统一！
    // 此外，空本身也是一棵二叉树，下面的递归中，并没有递归到null
    private void add(Node node, E e) {
        // 如果已经存在
        if (e.equals(node.e)) {
            return;
        } else if (e.compareTo(node.e) < 0 && node.left == null) {
            // 满足就创建
            node.left = new Node(e);
            size++;
            return;
        } else if (e.compareTo(node.e) > 0 && node.right == null) {
            node.right = new Node(e);
            size++;
            return;
        }

        // 如果结点要插入的地方不为空，则递归调用，往下创建一个位置
        if (e.compareTo(node.e) < 0) {
            add(node.left, e);
        } else {
            add(node.right, e);
        }
    }
*/

    // 返回插入新结点后二分搜索树的根
    private Node add(Node node, E e) {
        // 空的话返回的就为结点本身
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    // 初始操作就不需要再对root做判断了
    public void add(E e) {
        // 向root中插入e
        root = add(root, e);
    }

    // 二分搜索树中是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 以node为根的二分搜索树中是否包含元素e, 递归算法
    private boolean contains(Node node, E e) {
        // 空树
        if (node == null) {
            return false;
        }

        // 找到
        if (e.compareTo(node.e) == 0) {
            return true;
            // 在左子树
        } else if (e.compareTo(node.e) < 0) {
            // 递归回去，去左子树找
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    // 前序遍历 DLR
    public void preOrder() {
        preOrder(root);
    }

    // 递归算法，前序遍历以node为根的二分搜索树
    private void preOrder(Node node) {

        if (node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 非递归前序遍历 -> 深度优先遍历
    public void preOrderNR() {

        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        // 初始
        stack.push(root);
        while (!stack.empty()) {
            Node pop = stack.pop();
            System.out.println(pop.e);
            // 先进后出
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
    }


    // 中序遍历 LDR 等同于大小顺序排序
    public void inOrder() {
        inOrder(root);
    }

    // 递归中序遍历
    private void inOrder(Node node) {

        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);

    }


    // 后序遍历 LRD
    public void postOrder() {
        postOrder(root);
    }

    // 后序遍历以node为根的二分搜索树, 递归实现
    private void postOrder(Node node) {

        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);

    }


    // 层序遍历 借助队列 -> 广度优先
    public void levelOrder() {

        if (root == null) {
            return;
        }

        // 用链表实现队列这个接口
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            // 入队  先进先出
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }
    }

    // 寻找最小元素
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).e;
    }

    // 返回以node为根的BST的最小值所在的结点
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 寻找最大元素
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root).e;
    }

    // 返回以node为根的BST的最大值所在的结点
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }


    // 删除最小值所在的结点，返回最小值
    public E removeMin() {
        E min = minimum();
        root = removeMin(root);
        return min;
    }

    // 删除以node为根的BST的最小结点，返回删除结点后的新的BST的根
    private Node removeMin(Node node) {

        if (node.left == null) {
            // 可能有右子树
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        // 存在左子树的情况
        node.left = removeMin(node.left);
        return node;
    }


    // 删除最大值所在的结点，返回最大值
    public E removeMax() {
        E max = maximum();
        root = removeMax(root);
        return max;
    }

    // 删除以node为根的BST的最大结点，返回删除结点后的新的BST的根
    private Node removeMax(Node node) {

        if (node.right == null) {
            // 可能有左子树
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        // 存在左子树的情况
        node.right = removeMax(node.right);
        return node;
    }


    // 从BST中删除元素为e的结点
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除逻辑，找到后继
    private Node remove(Node node, E e) {

        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {
            if (node.left == null) {
                // 断开关系
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 右子树为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }


            // 待删除结点左右均不为空，寻找后继，比其大的最小结点
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }


    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        generateBSTString(root, 0, stringBuffer);
        return stringBuffer.toString();
    }

    // 生成以node为根结点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuffer stringBuffer) {
        if (node == null) {
            stringBuffer.append(generateDepthString(depth) + "null\n");
            return;
        }
        stringBuffer.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth + 1, stringBuffer);
        generateBSTString(node.right, depth + 1, stringBuffer);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
//        res.append("--".repeat(Math.max(0, depth)));
        return res.toString();
    }
}