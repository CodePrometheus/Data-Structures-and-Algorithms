package search;

import static sun.nio.ch.IOStatus.check;

/**
 * 假设目标值在闭区间[l, r]中， 每次将区间长度缩小一半，当l = r时，我们就找到了目标值。
 *
 * @Author: zzStar
 * @Date: 04-25-2021 13:03
 */
public class BinarySearch {

    /**
     * 二分模板 - 1
     * 如果在else后写的是l，就加1，上面的mid就不用加1
     */
    public int binary1(int l, int r) {
        while (l < r) {
            int mid = l + (r - l) / 2;
            // check()判断mid是否满足性质
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        // 二分到最后一个，即l==r时，为所求
        return l;
    }

    /**
     * 二分模板 - 1
     * 在else 后写的是r 就要减1，然后在上面的mid那里+1
     */
    public int binary2(int l, int r) {
        while (l < r) {
            int mid = l + (r - l) / 2 + 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        // 二分到最后一个，即l==r时，为所求
        return l;
    }


}
