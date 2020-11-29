import java.util.ArrayList;
import java.util.Random;

/**
 * 测试无序添加的性能
 * ---
 * 对于查询较多的情况，AVL较优
 * 对于完全随机的数据，普通二分搜索树较优，但极端情况退化成链表（或者高度不平衡）
 * 而红黑树牺牲了平衡性（2LogN的高度），但统计性能更优（综合CURD所有的操作）
 *
 * @Author: zzStar
 * @Date: 11-28-2020 23:26
 */
public class CompareAdd {
    public static void main(String[] args) {
        int n = 100000;

        Random random = new Random();

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(random.nextInt(Integer.MAX_VALUE));
        }

        // test AVL
        long startTime = System.nanoTime();

        AVLTree<Integer, Integer> avlTree = new AVLTree<>();
        for (Integer x : arrayList) {
            avlTree.add(x, null);
        }

        long endTime = System.nanoTime();

        double avl = (endTime - startTime) / 1000000000.0;

        System.out.println("AVL: " + avl + " s");

        // test RBT
        startTime = System.nanoTime();

        RBTree<Integer, Integer> rbTree = new RBTree<>();
        for (Integer x : arrayList) {
            rbTree.add(x, null);
        }

        endTime = System.nanoTime();

        double rbt = (endTime - startTime) / 1000000000.0;

        System.out.println("RBT: " + rbt + " s");

        // test BST
        startTime = System.nanoTime();

        BSTImpl<Integer, Integer> bst = new BSTImpl<>();
        for (Integer x : arrayList) {
            bst.add(x, null);
        }

        endTime = System.nanoTime();

        double bstT = (endTime - startTime) / 1000000000.0;

        System.out.println("bst: " + bstT + " s");

    }
}
