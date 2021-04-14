import java.util.ArrayList;
import java.util.List;

/**
 * 基于链表的Map
 *
 * @Author: zzStar
 * @Date: 11-05-2020 10:45
 * <p>
 * 映射 Map
 * 1）、存储 Key：value 数据对的数据结构。
 * 2）、根据 Key，寻找 Value。
 * <p>
 * 非常容易使用链表或者二分搜索树来实现。
 * LinkedListMap  BSTMap   平均     最差
 * add、remove、set、get、contains     O(n)          O(h)   O(logn)  O(n)
 */
public class LinkedListMap<K, V> implements IMap<K, V> {

    private Node dummyHead;
    private int size;

    private class Node {
        public K key;
        public V value;
        public Node next;


        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node == null ? null : node.value;
    }

    @Override
    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key can't be null!");
        }
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else
            node.value = value;
    }

    @Override
    public void set(K key, V newValue) {
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException(key + " doesn't exist!");

        node.value = newValue;
    }

    @Override
    public V remove(K key) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }

        return null;
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");
        List<String> words = new ArrayList<>();
        if (FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());
        }
        LinkedListMap<String, Integer> map = new LinkedListMap<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of PRIDE: " + map.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        System.out.println("===over===");
    }

}
