package search;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * leetcode1248. 统计「优美子数组」
 * <p>
 * 题目描述
 * <p>
 * 给你一个整数数组 nums 和一个整数 k。
 * <p>
 * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
 * <p>
 * 请返回这个数组中「优美子数组」的数目。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,1,2,1,1], k = 3
 * 输出：2
 * 解释：包含 3 个奇数的子数组是 [1,1,2,1] 和 [1,2,1,1] 。
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [2,4,6], k = 1
 * 输出：0
 * 解释：数列中不包含任何奇数，所以不存在优美子数组。
 * <p>
 * 示例 3：
 * <p>
 * 输入：nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * 输出：16
 * <p>
 *
 * @Author: zzStar
 * @Date: 04-25-2021 14:29
 */
public class PrefixExercise3 {

    /**
     * 沿着前缀和 + hashMap存储的思路
     * 将前缀区间的奇数个数保存到区间内即可
     * 将 sum += x 改成了判断奇偶的语句
     * key 代表的是含有 1 个奇数的前缀区间，value 代表这种子区间的个数
     */
    public int numberOfSubarrays(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        // 统计奇数个数，相当于我们的pre
        int oddNum = 0;
        int res = 0;
        // 同样的，put进去垫个底
        map.put(0, 1);

        for (int num : nums) {
            // 统计奇数个数  num & 1是奇数就加上一个1
            oddNum = oddNum + (num & 1);
            // if
            if (map.containsKey(oddNum - k)) {
                res = res + map.get(oddNum - k);
            }
            // 存入, get获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值
            map.put(oddNum, map.getOrDefault(oddNum, 0) + 1);
        }
        return res;
    }

    /**
     * 统计奇数的个数，数组中的奇数个数肯定不会超过原数组的长度
     * 可以用数组来模拟 HashMap ，用数组的索引来模拟 HashMap 的 key，用值来模拟哈希表的 value
     */
    public int numberOfSubarrays2(int[] nums, int k) {
        int length = nums.length;
        int[] map = new int[length + 1];
        map[0] = 1;
        int oddNum = 0;
        int res = 0;
        for (int i = 0; i < length; ++i) {
            oddNum += nums[i] & 1;
            if (oddNum - k >= 0) {
                res = res + map[oddNum - k];
            }
            // 更新索引对应的value
            map[oddNum]++;
        }
        return res;
    }


    @Test
    public void numberOfSubarraysTest() {
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        System.out.println(numberOfSubarrays(nums, k));
        System.out.println(numberOfSubarrays2(nums, k));
    }

}
