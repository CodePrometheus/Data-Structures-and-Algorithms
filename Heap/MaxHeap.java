import java.util.Arrays;
import java.util.Random;

/**
 * 最大堆，使用数组来表示完全二叉树
 *
 * @Author: zzStar
 * @Date: 11-01-2020 19:41
 */
public class MaxHeap<E extends Comparable<E>> {

    /**
     * 一个堆的高度为 logN，因此在堆中插入元素和删除最大元素的复杂度都为 logN。
     * 对于堆排序，由于要对 N 个节点进行下沉操作，因此复杂度为 NlogN。
     * 堆排序是一种原地排序，没有利用额外的空间。
     * 现代操作系统很少使用堆排序，因为它无法利用局部性原理进行缓存，
     * 也就是数组元素很少和相邻的元素进行比较和交换。
     */

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    // 将数组转化为堆
    public MaxHeap(E[] arr) {

        data = new Array<>(arr);
//        for (int i = parent(arr.length - 1); i >= 0; i--) {
//            siftDown(i);
//        }
        if (arr.length != 1) {
            for (int i = parent(arr.length - 1); i >= 0; i--) {
                siftDown(i);
            }
        }

    }

    public int size() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 一个索引所表示的元素的父亲节点的索引
    private int parent(int index) {
        if (index == 0) throw new IllegalArgumentException("index-o doesn't have parent");
        return (index - 1) / 2;
    }

    // 左孩子
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    // 右孩子
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    // 添加
    public void add(E e) {
        data.addLast(e);
        // 要上浮的索引
        siftUp(data.getSize() - 1);
    }

    // 索引上浮的过程
    private void siftUp(int k) {
        // 不能是根节点，且与父节点做一个比较，达到条件交换
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            // 继续
            k = parent(k);
        }
    }

    // 查看最大的元素
    public E findMax() {
        if (data.getSize() == 0)
            throw new IllegalArgumentException("Cant findMax when heap is empty");
        return data.get(0);
    }

    // 取出堆中最大的元素
    public E extractMax() {
        E ret = findMax();
        // 删除
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        // 下沉操作
        siftDown(0);
        return ret;
    }

    /**
     * 下沉操作：
     * 1、仅当当前节点有左子节点时进入while循环体。
     * 2、设立下沉后的位置为j，默认为左子节点的位置。
     * 3、如果当前节点有右子节点且左子节点小于右子节点时，下沉后的位置j取右子节点的位置（j++）。
     * 4、如果当前节点的位置k小于下沉后的位置j时，交换k与j的值，完成这一次的下沉操作。
     * 5、更新当前节点的位置为j，如果当前节点还有左子节点则又会进入while循环体进行上述的下沉操作。
     *
     * @param k
     */
    private void siftDown(int k) {
        // 判断条件，
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);

            // 存在右孩子，且符合堆
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(k);

            // 此时，data[j]是leftChild和rightChild中的最大值
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            // 否则违反堆的性质
            data.swap(k, j);
            // 进行下一轮循环
            k = j;
        }
    }

    // 取出堆中最大的元素，并且替换成元素e
    public E replace(E e) {
        E ret = findMax();
        // 堆顶的元素替换
        data.set(0, e);
        siftDown(0);
        return ret;
    }


    public static void main(String[] args) {
        int n = 100000;
//        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        Integer[] testData1 = new Integer[n];
        for (int i = 0; i < n; i++) {
            testData1[i] = random.nextInt(Integer.MAX_VALUE);
        }
/*
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }
        // 检验
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap Completed");
*/
        Integer[] testData2 = Arrays.copyOf(testData1, n);

        double time1 = testHeap(testData1, false);
        System.out.println("Without heapify:" + time1 + " s");

        double time2 = testHeap(testData2, true);
        System.out.println("With heapify:" + time2 + " s");
    }

    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.nanoTime();

        MaxHeap<Integer> maxHeap;
        if (isHeapify) {
            maxHeap = new MaxHeap<>(testData);
        } else {
            maxHeap = new MaxHeap<>();
            for (int num : testData) {
                maxHeap.add(num);
            }
        }
        int[] arr = new int[testData.length];
        for (int i = 0; i < testData.length; i++) {
            arr[i] = maxHeap.extractMax();
        }
        // 检验
        for (int i = 1; i < testData.length; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }
        System.out.println("Test MaxHeap Completed");

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }

}
