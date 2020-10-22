
//插入式排序属于内部排序法，是对于欲排序的元素以插入的方式找寻该元素的适当位置，以达到排序的目的
//把 n个待排序的元素看成为一个有序表和一个无序表
//开始时有序表中只包含一个元素，无序表中包含有n-1个元素
//排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中的适当位置，使之成为新的有序表
//插入比选择快
public class InsertSort {

    public static void main(String[] args) {
//        int[] arr = {101,34,119,1,-2,999,-666};
//        insertSort(arr);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000];
        for (int i = 0; i < 8000; i++) {
            arr[i] = (int) (Math.random() * 100000);//i被赋值为[0,80000)中的随机数
        }

        long start = System.currentTimeMillis();
        System.out.println("排序前的时间" + start);

        insertSort(arr);

        long end = System.currentTimeMillis();
        System.out.println("排序后的时间" + end);

        System.out.println(end - start);

    }


    public static void insertSort(int[] arr) {

////        定义待插入的数{101,34,119,1}101相当于有序表
//        int insertVal = arr[1];
//        int insertIndex = 1 - 1;//表示34前面这个数101的下标
//
////        给insertVal找到插入的位置
////        1保证在找插入位置时不越界   2待插入的数还没有找到插入的位置 也就是说此时还是{101，34}
////        就需要将 arr[insertIndex]后移
//        while (insertIndex >= 0 && insertVal < arr[insertIndex]){
//            arr[insertIndex + 1] = arr[insertIndex];
//            insertIndex--;
//        }
////        退出while循环，此时插入位置正确，insertIndex + 1
//        arr[insertIndex + 1] = insertVal;
//
//        System.out.println("第一轮后" + Arrays.toString(arr));

//        同样是从第一个数开始去与后面的数比较，不需减一，因为i就是从1开始
        int insertVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            insertVal = arr[i];
            insertIndex = i - 1;

            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {//改变排列顺序只需要改变这个符号<即可
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
//            优化，判断是否需要赋值
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertVal;
            }
        }


    }

}
