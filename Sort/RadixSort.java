/**
 * 基数排序
 *
 * @Author: zzStar
 * @Date: 10-17-2020 11:14
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {23, 6, 189, 45, 9, 287, 56, 1, 789, 34, 65};
        radixSort(arr);

    }

    public static void radixSort(int[] arr) {
        int max = Integer.MIN_VALUE;
        //拿到数组当中的最大值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        //计算最大数字是几位数
        int maxLength = (max + "").length();
        //根据最大长度的数决定比较的次数
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //把每一个数字分别计算余数
            for (int j = 0; j < arr.length; j++) {
                //计算余数
                int remainder = arr[j] / n % 10;
            }
        }
    }
}
