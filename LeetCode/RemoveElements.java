/**
 * LeetCode203
 *
 * @Author: zzStar
 * @Date: 10-31-2020 11:03
 */
public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {

        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        // 等于的话返回head.next,head被删除
        return head.val == val ? head.next : head;

    }

    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }

        public ListNode(int[] nums) {
            if (nums == null || nums.length == 0) {
                throw new IllegalArgumentException("empty");
            }
            this.val = nums[0];
            ListNode cur = this;
            for (int i = 1; i < nums.length; i++) {
                cur.next = new ListNode(nums[i]);
                cur = cur.next;
            }
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            ListNode cur = this;
            while (cur != null) {
                res.append(cur.val).append("->");
                cur = cur.next;
            }
            res.append("NULL");
            return res.toString();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 2};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new RemoveElements()).removeElements(head, 2);
        System.out.println(res);
    }
}
