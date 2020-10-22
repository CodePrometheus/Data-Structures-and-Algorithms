

public class BubbleSort {
    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, 20};
//
//        System.out.println(Arrays.toString(arr));
//        bubbleSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int) (Math.random() * 100000);//i被赋值为[0,80000)中的随机数
        }

        //当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数。
        long start = System.currentTimeMillis();
        System.out.println("排序前的时间" + start);

        bubbleSort(arr);

        long end = System.currentTimeMillis();
        System.out.println("排序后的时间" + end);

        System.out.println(end - start);

    }




//    将在main测试的冒泡排序封装成一个方法
    public static void bubbleSort(int[] arr){
        //        没有优化的话，冒泡排序次数 = 数组大小 - 1
        int temp = 0;//临时变量
//        如果发现在某趟排序中，没有发生一次交换，就可以提前结束冒泡排序
        boolean flag = false;//标识变量,表示是否进行过交换,false表没有交换
        for (int i = 0; i < arr.length - 1 ; i++) {
//            通过注释代码的分析可以看出，代码只是在-1-()这里不同，所以采用嵌套for循环时间复杂度为O(n^2)
            for (int j = 0; j < arr.length - 1 - i; j++) {
//            如果前面的数比后面的大，则交换，也就是大的数向后走
                if (arr[j] < arr[j + 1]) {//改变排列顺序只需要改变这个符号即可
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i + 1) + "趟");
//            System.out.println(Arrays.toString(arr));

            if (!flag){//一次交换都没有发生过 !flag -> flag == false
                break;
            }else {//每次结束后要重置flag，进行下一次判断
                flag = false;
            }
        }

//
////        第二趟,就是将第二大的数排在倒数第二位
//        for (int j = 0; j < arr.length - 1 - 1; j++) {
////            如果前面的数比后面的大，则交换，也就是大的数向后走
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//            System.out.println("第二趟");
//            System.out.println(Arrays.toString(arr));
//        }
//
//
//        for (int j = 0; j < arr.length - 1 - 2; j++) {
////            如果前面的数比后面的大，则交换，也就是大的数向后走
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//            System.out.println("第三趟");
//            System.out.println(Arrays.toString(arr));
//        }
//
//        for (int j = 0; j < arr.length - 1 - 3; j++) {
////            如果前面的数比后面的大，则交换，也就是大的数向后走
//            if (arr[j] > arr[j + 1]) {
//                temp = arr[j];
//                arr[j] = arr[j + 1];
//                arr[j + 1] = temp;
//            }
//            System.out.println("第四趟");
//            System.out.println(Arrays.toString(arr));
//        }

    }

}
