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

        System.out.println("LICENSE");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("LICENSE", words)) {
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
            System.out.println("Frequency of but: " + rbTree.get("but"));
            System.out.println("Frequency of you: " + rbTree.get("you"));

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
            System.out.println("Frequency of but: " + avlTree.get("but"));
            System.out.println("Frequency of you: " + avlTree.get("you"));
        }


    }
}

