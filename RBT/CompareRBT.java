import java.util.ArrayList;

/**
 * 性能比较
 * 红黑树的优势在于添加删除
 *
 * @Author: zzStar
 * @Date: 11-28-2020 16:53
 */
public class CompareRBT {

    public static void main(String[] args) {

        System.out.println("pride-and-prejudice");
        System.out.println("===========");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // test RBT
            long startTime = System.nanoTime();

            RBTree<String, Integer> rbTree = new RBTree<>();
            for (String word : words) {
                if (rbTree.contains(word)) {
                    rbTree.set(word, rbTree.get(word) + 1);
                } else {
                    rbTree.add(word, 1);
                }
            }

            for (String word : words) {
                rbTree.contains(word);
            }

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;

            System.out.println("RBT: " + time + " s");

            System.out.println("Total different words: " + rbTree.getSize());
            System.out.println("Frequency of PRIDE: " + rbTree.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + rbTree.get("prejudice"));

            System.out.println("=========================");

            // test AVL
            startTime = System.nanoTime();

            AVLTree<String, Integer> avlTree = new AVLTree<>();
            for (String word : words) {
                if (avlTree.contains(word)) {
                    avlTree.set(word, avlTree.get(word) + 1);
                } else {
                    avlTree.add(word, 1);
                }
            }

            for (String word : words) {
                avlTree.contains(word);
            }

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;

            System.out.println("avlTree: " + time + " s");

            System.out.println("Total different words: " + avlTree.getSize());
            System.out.println("Frequency of PRIDE: " + avlTree.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + avlTree.get("prejudice"));
        }

        System.out.println("====================");

        // test BST
        long startTime = System.nanoTime();

        BSTImpl<String, Integer> bst = new BSTImpl<>();
        for (String word : words) {
            if (bst.contains(word)) {
                bst.set(word, bst.get(word) + 1);
            } else {
                bst.add(word, 1);
            }
        }

        for (String word : words) {
            bst.contains(word);
        }

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        System.out.println("BST: " + time + " s");

        System.out.println("Total different words: " + bst.getSize());
        System.out.println("Frequency of PRIDE: " + bst.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + bst.get("prejudice"));

    }
}

