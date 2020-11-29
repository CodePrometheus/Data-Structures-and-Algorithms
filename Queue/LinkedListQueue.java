/**
 * 链表实现队列（引入尾指针）
 *
 * @Author: zzStar
 * @Date: 10-30-2020 11:04
 */
public class LinkedListQueue<E> implements IQueue<E> {

    private class Node {
        public E e;
        public Node next;


        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    // 从head端删除元素 出队，从tail端插入元素 入队
    private Node head, tail;
    private int size;

    @Override
    public int getSize() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    // 入队，从队尾进行
    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    // 出队，由队首负责
    @Override
    public E dequeue() {
        if (isEmpty()) throw new IllegalArgumentException("Cant dequeue  from an empty queue");

        // 断开关系
        Node retNode = head;
        head = head.next;
        retNode.next = null;

        // 只有一个元素，也就是head.next为空
        if (head == null) tail = null;
        size--;
        return retNode.e;
    }

    @Override
    public E getFront() {
        if (isEmpty()) throw new IllegalArgumentException("Cant dequeue  from an empty queue");
        return head.e;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append("Queue: front ");

        Node cur = head;
        while (cur != null) {
            res.append(cur + "->");
            cur = cur.next;
        }

        res.append("NULL tail");
        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }


}
