package search;

/**
 * leetcode 724. 寻找数组的中心索引
 * 题目描述
 * <p>
 * 给定一个整数类型的数组 nums，请编写一个能够返回数组 “中心索引” 的方法。
 * <p>
 * 我们是这样定义数组 中心索引 的：数组中心索引的左侧所有元素相加的和等于右侧所有元素相加的和。
 * <p>
 * 如果数组不存在中心索引，那么我们应该返回 -1。如果数组有多个中心索引，那么我们应该返回最靠近左边的那一个。
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * <p>
 * 解释：
 * 索引 3 (nums[3] = 6) 的左侧数之和 (1 + 7 + 3 = 11)，与右侧数之和 (5 + 6 = 11) 相等。
 * 同时, 3 也是第一个符合要求的中心索引。
 * <p>
 * 示例 2：
 * <p>
 * 输入：
 * nums = [1, 2, 3]
 * 输出：-1
 *
 * @Author: zzStar
 * @Date: 04-25-2021 13:45
 */
public class PrefixExercise1 {

    /**
     * 利用前缀和的思想
     */
    public int pivotIndex(int[] nums) {
        int pre = 0;
        // 先把所有的数字求和
        for (int num : nums) {
            pre += num;
        }
        // 左和
        int leftSum = 0;
        // 在对数组进行遍历
        for (int i = 0; i < nums.length; i++) {
            // 右和就是总和-当前这个数-左和
            if (leftSum == pre - nums[i] - leftSum) {
                return i;
            }
            // 否则for循环，左和加，往右走
            leftSum += nums[i];
        }
        return -1;
    }
}
