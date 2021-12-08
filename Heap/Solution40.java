/**
 * @Author: zzStar
 * @Date: 04-25-2021 10:10
 */
public class Solution40 {

    /**
     * 最大堆实现
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] getLeastNumbers(int[] arr, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            queue.enqueue(arr[i]);
        }

        for (int i = k; i < arr.length; i++) {
            if (queue.isEmpty() && arr[i] < queue.getFront()) {
                queue.dequeue();
                queue.enqueue(arr[i]);
            }
        }

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.dequeue();
        }
        return res;
    }
}
