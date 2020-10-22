
//希尔排序是希尔（Donald Shell）于1959年提出的一种排序算法。
//在插入排序中，当需要插入的是较小的数时，后移的次数明显增多，影响效率
// 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序。
// 希尔排序是非稳定排序算法。
//希尔排序是把记录按下标的一定增量分组，对每组使用直接插入排序算法排序；先分组，再排序
// 随着增量逐渐减少，每组包含的关键词越来越多，当增量减至1时，整个文件恰被分成一组，算法便终止
public class ShellSort {

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int) (Math.random() * 100000);//i被赋值为[0,80000)中的随机数
        }
        long start = System.currentTimeMillis();
        System.out.println("排序前的时间" + start);

//        shellSort(arr);
        shellSort2(arr);

        long end = System.currentTimeMillis();
        System.out.println("排序后的时间" + end);

        System.out.println(end - start);

    }


    //    逐步推导交换法
    public static void shellSort(int[] arr) {
        int temp = 0;
////        第一轮 将10个数分成了5组
//        for (int i = 5; i < arr.length; i++) {
////            遍历各组中所有的元素，共5组，每组2个元素，步长为5
//            for (int j = i - 5; j >= 0; j -= 5) {
////                若当前元素大于加上步长后的那个元素，表明要交换
//                if (arr[j] > arr[j + 5]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 5];
//                    arr[j + 5] = temp;
//                }
//            }
//        }
//        System.out.println("No.1" + Arrays.toString(arr));//No.1[3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
//
//
//        //        第二轮 2组,在第一轮排序分组的基础上5组在除以2等于2组，即将10个数据分成了2组
//        for (int i = 2; i < arr.length; i++) {
////            遍历各组中所有的元素，共5组，每组2个元素，步长为2
//            for (int j = i - 2; j >= 0; j -= 2) {
////                若当前元素大于加上步长后的那个元素，表明要交换
//                if (arr[j] > arr[j + 2]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 2];
//                    arr[j + 2] = temp;
//                }
//            }
//        }
//        System.out.println("No.2" + Arrays.toString(arr));//No.2[0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
//
//        //        第三轮 1组
//        for (int i = 1; i < arr.length; i++) {
////            遍历各组中所有的元素，共5组，每组2个元素，步长为2
//            for (int j = i - 1; j >= 0; j -= 1) {
////                若当前元素大于加上步长后的那个元素，表明要交换
//                if (arr[j] > arr[j + 1]) {
//                    temp = arr[j];
//                    arr[j] = arr[j + 1];
//                    arr[j + 1] = temp;
//                }
//            }
//        }
//        System.out.println("No.3" + Arrays.toString(arr));

//        gap表示分组数
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    //        发现一个交换一个大大影响效率，改进成移位法,结合插入法
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
//            从gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;//将数组下标i赋值给变量j
                int temp = arr[j];//把数组下标为j的值存储到一个临时变量
                if (arr[j] < arr[j - gap]) {//没有找到位置
                    while (j - gap >= 0 && temp < arr[j - gap]) {
//                        移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
//                    退出while循环后，说明给temp找到了插入的位置
                    arr[j] = temp;
                }
            }
        }


    }
}
