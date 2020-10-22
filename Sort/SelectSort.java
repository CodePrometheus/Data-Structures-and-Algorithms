
//第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换
//第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换
//第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换
//第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换
//得到一个按排序码从小到大排列的有序序列
//每一轮排序就是一个循环，先假定当前为最小，与后面比较，若有更小的，重新确定最小数得到下标
//当遍历到数组的最后时，就得到最小数和下标
//时间复杂度为O(n^2)
//经过测试，显然，选择排序要比冒泡快
public class SelectSort {

    public static void main(String[] args) {
//        int[] arr = {101,34,119,1,-8,666,1212,-2};
//        System.out.println("排序前" + Arrays.toString(arr));
//        selectSort(arr);
//        System.out.println("排序后" + Arrays.toString(arr));

        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int) (Math.random() * 100000);//i被赋值为[0,80000)中的随机数
        }
        long start = System.currentTimeMillis();
        System.out.println("排序前的时间" + start);

        selectSort(arr);

        long end = System.currentTimeMillis();
        System.out.println("排序后的时间" + end);

        System.out.println(end - start);



    }


    public static void selectSort(int[] arr){

////        第一轮先假定
//        int minIndex = 0;
//        int min = arr[0];
//
////        将假定的数与后面比较，也就是从1开始
//        for (int j = 0 + 1;j < arr.length;j++){
//            if (min > arr[j]){//说明不是最小,重置
//                min = arr[j];
//                minIndex = j;
//            }
//        }
////        之后将最小值放在arr[0],即交换,可优化,当假定的数就是当前最小的本轮不进行
//        if (minIndex != 0) {
//            arr[minIndex] = arr[0];
//            arr[0] = min;
//        }
//        System.out.println("第一轮后");
//        System.out.println(Arrays.toString(arr));
//
//
//        //        第二轮先假定
//        minIndex = 1;
//        min = arr[1];
//
////        将假定的数与后面比较，也就是从1开始
//        for (int j = 1 + 1;j < arr.length;j++){
//            if (min > arr[j]){//说明不是最小,重置
//                min = arr[j];
//                minIndex = j;
//            }
//        }
////        之后将最小值放在arr[0],即交换
//        if (minIndex != 1) {
//            arr[minIndex] = arr[1];
//            arr[1] = min;
//        }
//        System.out.println("第二轮后");
//        System.out.println(Arrays.toString(arr));



//        由上面的推导，得出
//        总轮数应为长度-1,因为j就是从1开始,与后面进行比较
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1;j < arr.length;j++){
                if (min > arr[j]){//从大到小还是从小到大，只改变这里的符号即可
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }



    }
}
