package search;

import org.junit.Test;

import java.util.HashMap;

/**
 * leetcode 560. 和为K的子数组
 * 题目描述
 * <p>
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * <p>
 * 示例 1 :
 * <p>
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。*
 *
 * @Author: zzStar
 * @Date: 04-25-2021 13:50
 */
public class PrefixExercise2 {

    /**
     * 暴力双重循环，输出个数即可
     */
    public int subarraySum(int[] nums, int k) {
        int length = nums.length;
        int sum = 0;
        int res = 0;

        for (int i = 0; i < length; ++i) {
            for (int j = i; j < length; ++j) {
                sum = sum + nums[j];
                if (sum == k) {
                    res++;
                }
            }
            // 最外面这层i循环里每结束一次j循环，要把sum归零，进行下一次双for
            sum = 0;
        }
        return res;
    }


    /**
     * 前缀和 O(n^2)
     */
    public int subarraySum2(int[] nums, int k) {
        // 前缀和数组
        int[] pre = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            // 前缀和是pre[1]开始填充
            pre[i + 1] = nums[i] + pre[i];
        }

        int res = 0;
        // 下面的做法形同于上面的双for循环
        for (int i = 0; i < nums.length; ++i) {
            for (int j = i; j < nums.length; ++j) {
                // 注意偏移 num[i,j]区间内的和 = pre[j+1] - pre[i]
                // (nums[2]到nums[4]等于presum[5]-presum[2])
                if (pre[j + 1] - pre[i] == k) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 优化 pre + HashMap
     */
    public int subarraySum3(int[] nums, int k) {
        if (nums.length == 0) {
            return 0;
        }
        // 将所有的前缀和以及该前缀和出现的次数存进map里，而不是双for循环
        HashMap<Integer, Integer> map = new HashMap<>();
        // 这里需要预存前缀和为 0 的情况，会漏掉前几位就满足的情况
        // 例如输入[1,1,0]，k = 2 如果没有这行代码，则会返回0,漏掉了1+1=2，和1+1+0=2的情况
        map.put(0, 1);

        int res = 0;
        int pre = 0;
        for (int num : nums) {
            pre = pre + num;
            // 先走判断
            if (map.containsKey(pre - k)) {
                res = res + map.get(pre - k);
            }
            // update
            map.put(pre, map.getOrDefault(pre, 0) + 1);
        }
        return res;
    }


    @Test
    public void subarraySumTest() {
        int[] nums = {1, 1, 1};
        int k = 2;
        System.out.println(subarraySum(nums, k));
        System.out.println(subarraySum2(nums, k));
        System.out.println(subarraySum3(nums, k));
    }

}
