
//快速排序（Quicksort）是对冒泡排序的一种改进。
//通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
// 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
//先从最右位置往左开始找直到找到一个小于基准数的值，记录下该值的位置（记作 i）。
//再从最左位置往右找直到找到一个大于基准数的值，记录下该值的位置（记作 j）。
//如果位置i<j，则交换i和j两个位置上的值，然后继续从(j-1)的位置往前和(i+1)的位置往后重复上面比对基准数然后交换的步骤。
//如果执行到i==j，表示本次比对已经结束，将最后i的位置的值与基准数做交换，
// 此时基准数就找到了临界点的位置k，位置k两边的数组都比当前位置k上的基准值或都更小或都更大。
//快速排序之所以比较快，是因为相比冒泡排序，每次的交换都是跳跃式的，
// 每次设置一个基准值，将小于基准值的都交换到左边，大于基准值的都交换到右边，
// 这样不会像冒泡一样每次都只交换相邻的两个数，因此比较和交换的此数都变少了，速度自然更高。
// 当然，也有可能出现最坏的情况，就是仍可能相邻的两个数进行交换。
//快速排序基于分治思想，它的时间平均复杂度很容易计算得到为O(NlogN)。
public class QuickSort {
    public static void main(String[] args) {
//        int[] array = {-9, 78, 0, 23, -567, 70, -1, 900};
//        quickSort(array);
//        System.out.println(Arrays.toString(array));

        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int) (Math.random() * 100000);//i被赋值为[0,80000)中的随机数
        }
        long start = System.currentTimeMillis();
        System.out.println("排序前的时间" + start);

        quickSort(arr);

        long end = System.currentTimeMillis();
        System.out.println("排序后的时间" + end);

        System.out.println(end - start);


    }


    public static void quickSort(int[] array) {
        int len;
        if (array == null
                || (len = array.length) == 0
                || len == 1) {
            return;
        }
        sort(array, 0, len - 1);
    }

    public static void sort(int[] array, int left, int right) {
        if (left > right) {
            return;
        }
        // base中存放基准数
        int base = array[left];
        int i = left, j = right;
        while (i != j) {
            // 顺序很重要，先从右边开始往左找，直到找到比base值小的数
            while (array[j] >= base && i < j) {
                j--;
            }

            // 再从左往右边找，直到找到比base值大的数
            while (array[i] <= base && i < j) {
                i++;
            }

            // 上面的循环结束表示找到了位置或者(i>=j)了，交换两个数在数组中的位置
            if (i < j) {
                int tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
            }
        }

        // 将基准数放到中间的位置（基准数归位）
        array[left] = array[i];
        array[i] = base;

        // 递归，继续向基准的左右两边执行和上面同样的操作
        // i的索引处为上面已确定好的基准值的位置，无需再处理
        sort(array, left, i - 1);
        sort(array, i + 1, right);
    }

}

