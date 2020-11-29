/**
 * 优先队列的实现
 *
 * @Author: zzStar
 * @Date: 11-03-2020 19:16
 */
public class PriorityQueue<E extends Comparable<E>> implements IQueue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.size();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extractMax();
    }
}
