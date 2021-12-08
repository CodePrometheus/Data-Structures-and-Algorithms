package search;

import org.junit.Test;

import java.util.Arrays;
import java.util.BitSet;

/**
 * 56. 合并区间
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间。
 * <p>
 * 示例 1：
 * <p>
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例2：
 * <p>
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zzStar
 * @Date: 04-29-2021 11:10
 */
public class BitSetExercise {

    /**
     * 用位图来表示一个区间
     */
    public int[][] merge(int[][] intervals) {
        BitSet bitSet = new BitSet();
        int max = 0;
        for (int[] interval : intervals) {
            int temp = interval[1] * 2 + 1;
            bitSet.set(interval[0] * 2, temp, true);
            max = temp >= max ? temp : max;
        }

        int index = 0, count = 0;
        while (index < max) {
            int start = bitSet.nextSetBit(index);
            int end = bitSet.nextClearBit(start);

            int[] item = {start / 2, (end - 1) / 2};
            intervals[count++] = item;

            index = end;
        }
        int[][] ret = new int[count][2];
        for (int i = 0; i < count; i++) {
            ret[i] = intervals[i];
        }

        return ret;
    }

    @Test
    public void bitSetTest() {
        int[][] tmp = {{1, 4}, {4, 5}};
        System.out.println(Arrays.deepToString(merge(tmp)));
    }

}
