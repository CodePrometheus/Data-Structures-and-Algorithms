package twopointers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 * <p>
 *
 * <p>
 * 示例：
 * <p>
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *
 * <p>
 * 提示：
 * <p>
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zzStar
 * @Date: 04-25-2021 16:30
 */
public class TwoPointersExercise1 {

    public List<Integer> partitionLabels(String s) {
        int[] map = new int[26];
        int start = 0;
        int end = 0;
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            // 将每个字母的下标索引赋值给这个数组，假设S[1]='a'，就有last[0] = 1，也就是字母a目前最后一次出现的下标是1，以此类推
            char tmp = s.charAt(i);// i就是当前字母的下标，
            map[tmp - 'a'] = i;// 不断地更新最远的下标值
        }

        // 由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, map[s.charAt(i) - 'a']);// end获取最远的下标值
            // 说明所有的重复元素到此截止 之前的所有元素，都仅在 i之前出现，可以记录结果
            if (end == i) {
                // 返回一个表示每个字符串片段的长度的列表
                res.add(end - start + 1);
                // 下一个
                start = end + 1;
            }
        }
        return res;
    }


    @Test
    public void partitionLabelsTest() {
        String s = "ababcbacadefegdehijhklij";
        System.out.println(partitionLabels(s));
    }
}
