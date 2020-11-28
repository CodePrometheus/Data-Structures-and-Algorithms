import java.util.ArrayList;

/**
 * 有序的添加性能测试
 * 有序时红黑树较优  而这也是TreeMap TreeSet底层使用RBT的原因
 *
 * @Author: zzStar
 * @Date: 11-28-2020 23:35
 */
public class CompareAddOrderly {
    public static void main(String[] args) {
        int n = 10000000;

        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(i);
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

    }

}
