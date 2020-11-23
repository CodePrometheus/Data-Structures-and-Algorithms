import java.util.Random;

/**
 * @Description: 比较合并与查询操作
 * @Author: zzStar
 * @Date: 2020/11/23 20:30
 */
public class CompareUF {

    private static double testUF(UF uf, int m) {

        int size = uf.getSize();
        Random random = new Random();
        long startTime = System.nanoTime();

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.unionElements(a, b);
        }

        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            uf.isConnected(a, b);
        }

        long endTime = System.nanoTime();

        return (endTime - startTime) / 1000000000.0;
    }

    public static void main(String[] args) {

        int size = 10000000;
        int m = 10000000;

//        UnionFindArray uf = new UnionFindArray(size);
//        System.out.println("uf = " + testUF(uf, m) + " s");
//
//        QuickNode quickNode = new QuickNode(size);
//        System.out.println("quickNode = " + testUF(quickNode, m) + " s");

        UnionFindSize unionFindSize = new UnionFindSize(size);
        System.out.println("unionFindSize = " + testUF(unionFindSize, m) + " s");

        UnionFindRank unionFindRank = new UnionFindRank(size);
        System.out.println("unionFindRank = " + testUF(unionFindRank, m) + " s");

        UnionFindCompression unionFindCompression = new UnionFindCompression(size);
        System.out.println("unionFindCompression = " + testUF(unionFindCompression, m) + " s");

        UnionFindRecursion unionFindRecursion = new UnionFindRecursion(size);
        System.out.println("unionFindRecursion = " + testUF(unionFindRecursion, m) + " s");

    }
}
