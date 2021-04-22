package sort;

//归并排序 (merge sort) 是一类与插入排序、交换排序、选择排序不同的另一种排序方法。
// 归并的含义是将两个或两个以上的有序表合并成一个新的有序表
// 归并排序是一种概念上最简单的排序算法，与快速排序一样，归并排序也是基于分治法的。
// 归并排序将待排序的元素序列分成两个长度相等的子序列，为每一个子序列排序，然后再将他们合并成一个子序列。
// 合并两个子序列的过程也就是两路归并

import org.junit.Test;

import java.util.Arrays;

/**
 * 1.确定分界点
 * 2.递归排序
 * 3.合二为一
 * 归并排序的主要问题是：需要一个与原待排序数组一样大的辅助数组空间
 * 归并排序不依赖于原始序列，因此其最好情形、平均情形和最差情形时间复杂度都一样
 * 时间复杂度：最好情形O(n)，平均情形O(n^2)，最差情形O(n^2)
 * 空间复杂度：O(n)
 * 稳 定 性：稳定
 */
public class MergeSort {

    public static void main(String[] args) {
//        int[] arr = {11, 44, 23, 67, 88, 65, 34, 48, 9, 12};
        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int) (Math.random() * 100000);//i被赋值为[0,80000)中的随机数
        }

        int[] tmp = new int[arr.length];    //新建一个临时数组存放
        mergeSort(arr, 0, arr.length - 1, tmp);

        long start = System.currentTimeMillis();
        System.out.println("排序前的时间" + start);

        mergeSort(arr, 0, arr.length - 1, tmp);

        long end = System.currentTimeMillis();
        System.out.println("排序后的时间" + end);

        System.out.println(end - start);


    }

    public static void merge(int[] arr, int low, int mid, int high, int[] tmp) {
        int i = 0;
        // 左边序列和右边序列起始索引
        int j = low, k = mid + 1;
        while (j <= mid && k <= high) {
            if (arr[j] < arr[k]) {
                tmp[i++] = arr[j++];
            } else {
                tmp[i++] = arr[k++];
            }
        }
        // 若左边序列还有剩余，则将其全部拷贝进tmp[]中
        while (j <= mid) {
            tmp[i++] = arr[j++];
        }

        while (k <= high) {
            tmp[i++] = arr[k++];
        }

        for (int t = 0; t < i; t++) {
            arr[low + t] = tmp[t];
        }
    }

    public static void mergeSort(int[] arr, int low, int high, int[] tmp) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(arr, low, mid, tmp); //对左边序列进行归并排序
            mergeSort(arr, mid + 1, high, tmp);  //对右边序列进行归并排序
            merge(arr, low, mid, high, tmp);    //合并两个有序序列
        }
    }

    /**
     * 两路归并算法
     *
     * @param target 用于存储归并结果
     * @param left   第一个有序表的第一个元素所在位置
     * @param mid    第一个有序表的最后一个元素所在位置
     * @param right  第二个有序表的最后一个元素所在位置
     * @return
     */
    public static int[] merge2(int[] target, int left, int mid, int right) {
        // 需要一个与原待排序数组一样大的辅助数组空间
        int[] tmp = Arrays.copyOf(target, target.length);

        // s1,s2是检查指针，index 是存放指针
        int s1 = left;
        int s2 = mid + 1;
        int index = left;

        // 两个表都未检查完，两两比较，也即检查指针分别在两个有序表之前
        while (s1 <= mid && s2 <= right) {
            if (tmp[s1] <= tmp[s2]) {
                target[index++] = tmp[s1++];
            } else {
                target[index++] = tmp[s2++];
            }
        }

        // 若第一个表未检查完，复制
        while (s1 <= mid) {
            target[index++] = tmp[s1++];
        }

        // 若第二个表未检查完，复制
        while (s2 <= right) {
            target[index++] = tmp[s2++];
        }

        return target;
    }

    public int[] testMergeSort(int[] target, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            testMergeSort(target, left, mid);
            testMergeSort(target, mid + 1, right);
            return merge2(target, left, mid, right);
        }
        return target;
    }

    @Test
    public void testMS() {
        int[] target = {11, 44, 23, 67, 88, 65, 34, 48, 9, 12};
        int left = 0, right = target.length - 1;
        System.out.println(Arrays.toString(testMergeSort(target, left, right)));
    }

}
