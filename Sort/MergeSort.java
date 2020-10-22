
//归并排序 (merge sort) 是一类与插入排序、交换排序、选择排序不同的另一种排序方法。
// 归并的含义是将两个或两个以上的有序表合并成一个新的有序表
//归并排序是一种概念上最简单的排序算法，与快速排序一样，归并排序也是基于分治法的。
// 归并排序将待排序的元素序列分成两个长度相等的子序列，为每一个子序列排序，然后再将他们合并成一个子序列。
// 合并两个子序列的过程也就是两路归并
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
        int j = low, k = mid + 1;  //左边序列和右边序列起始索引
        while (j <= mid && k <= high) {
            if (arr[j] < arr[k]) {
                tmp[i++] = arr[j++];
            } else {
                tmp[i++] = arr[k++];
            }
        }
        //若左边序列还有剩余，则将其全部拷贝进tmp[]中
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


}
