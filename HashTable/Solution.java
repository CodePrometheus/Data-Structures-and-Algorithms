/**
 * @Description: 找到第一个不重复的字符，并返回它的索引
 * @Author: zzStar
 * @Date: 2020/11/29 08:34
 */
class Solution {

    public int firstUniqChar(String s) {

        // 用数组对应26个字母 哈希表
        int[] freq = new int[26];
        // 每个字符都和一个索引相对应 O(1)的查找操作
        for (int i = 0; i < s.length(); i++) {
            // 减去ASCII值，计算出每个字符出现的频率
            freq[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            // 判断频率值
            if (freq[s.charAt(i) - 'a'] == 1)
                return i;
        }

        return -1;
    }
}
