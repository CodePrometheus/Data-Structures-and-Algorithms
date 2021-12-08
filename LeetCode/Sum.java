/**
 * @Author: zzStar
 * @Date: 11-18-2020 19:58
 */
public class Sum {

    public static void main(String[] args) {
        int[] arr = {6, 10};
        int i = sum(arr, 0);
        System.out.println("i = " + i);
    }

    public static int sum(int[] arr, int l) {

        if (l == arr.length) return 0;
//        int x = sum(arr, l + 1);
//        int res = arr[l] + x;
        return arr[l] + sum(arr, l + 1);
    }
}
