package twopointers;

/**
 * @Author: zzStar
 * @Date: 04-25-2021 15:38
 */
public class TwoPointers {

    /**
     * 朴素做法 O(n^2)
     */
    public void twoPointers1() {
        int n = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                //if (check(j, i)) {
                //   res = max(res, i - j + 1);
            }
        }
    }

    /**
     * 双指针
     */
    public void twoPointers2() {
        int n = 0;
        for (int i = 0, j = 0; i < n; i++) {
            //while (j <= i && check(j, i)) {
            j++;
        }
        //res = max(res, i - j + 1);
    }
}

