/**
 * 实现链表
 *
 * @Author: zzStar
 * @Date: 10-25-2020 19:03

 * 为什么链表很重要？
 *      不同于 动态数组、栈、队列的实现：其底层是依托静态数组，靠 resize 解决固定容量问题，
 *      链表是真正的动态数据结构，也是最简单的动态数据结构。
 *      能够帮助我们更深入地理解引用（指针）与递归。
 *      优势：真正的动态，不需要处理固定容量的问题。
 *      逆势：不同于数组其底层的数据是连续分布的，链表底层的数据分布是随机的，
 *      紧靠next（pre）指针连接，因此链表相对于数组丧失了随机访问的能力。
 *
 * 数组和链表的区别？
 *      数组最好被应用于索引有语义的情况，例如 Students[1]
 *      最大的优势：支持动态查询。
 *
 *      链表不能被应用于索引有语义的情况。
 *      最大的优势：动态。
 *
 * 链表的时间复杂度：
 *      增： O(n)
 *      删： O(n)
 *      改： O(n)
 *      查： O(n)
 *
 *      总结：链表不适合去修改，且只适合 增、删、查 链表头的元素，此时时间复杂度为 O(1)。
 */
public class LinkedListImpl<E> {

    // 私有的结点类，隐藏底层实现，对外部屏蔽
    private class Node {

        public E e;
        public Node next;

        // 传来的数据赋值 注意这里写了next
        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    //    private Node head;
    // 常用的是创建一个虚拟的头节点
    private Node dummyHead;
    private int size;

    public LinkedListImpl() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    // 获取链表中元素的个数
    public int getSize() {
        return size;
    }

    // 是否为空
    public boolean isEmpty() {
        return size == 0;
    }


    // 因为选择链表通常不使用索引了，这里练习在链表的index（0-based）位置上添加新的元素，非常用操作
    public void add(int index, E e) {

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed,Illegal index");
        }

        // 链表头加入，调用上面方法
//        if (index == 0) {
//            addFirst(e);
//        } else {
        Node pre = dummyHead;
        // 注意❗ 遍历，找到待插入结点的前一个结点
//        for (int i = 0; i < index - 1; i++) {
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }

/*
            // 现在将新建的node挂接在链表中
            Node node = new Node(e);
            // 下面两行注意顺序
            node.next = pre.next;
            pre.next = node;
*/
        pre.next = new Node(e, pre.next);
        size++;
    }

    // 在链表头添加新的元素e
    public void addFirst(E e) {

/*
        Node node = new Node(e);
        node.next = head;
        // 再把链表头设置为node
        head = node;
*/
        // 也可以这样写，新添加的这个e直接指向head
/*
        head = new Node(e, head);
        size++;
*/
        add(0, e);
    }


    // 在链表末尾添加新的元素
    public void addList(E e) {
        add(size, e);
    }

    // 练习在index位置获取元素的值
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed,Illegal index");
        }

        // 从索引为0的位置开始遍历
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    // 获得链表的第一个元素
    public E getLast() {
        return get(size - 1);
    }

    // 获得链表的第一个元素
    public E getFirst() {
        return get(0);
    }

    // 修改index的元素
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Add failed,Illegal index");
        }

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
    }

    // 查找链表中是否有元素e
    public boolean contains(E e) {
        Node cur = dummyHead.next;

        // 有效结点
        while (cur != null) {
            if (cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    // 从链表中删除index(0-based)位置的元素, 返回删除的元素
    // 在链表中不是一个常用的操作，练习用
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        Node prev = dummyHead;
        for (int i = 0; i < index; i++) {
            // 此时prev存的是待删除结点的前一个结点
            prev = prev.next;
        }

        // 要返回的结点
        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size--;

        return retNode.e;
    }


    // 从链表中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从链表中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }


    // 从链表中删除元素e
    public void removeElement(E e) {

        Node prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.e.equals(e)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
        }
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur + "->");
        }
        res.append("NULL");

        return res.toString();
    }

    public static void main(String[] args) {
        LinkedListImpl<Integer> linkedList = new LinkedListImpl<>();
        for (int i = 0; i < 5; i++) {
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }
        linkedList.add(2, 666);
        System.out.println(linkedList);

        linkedList.remove(2);
        System.out.println(linkedList);

        linkedList.removeElement(1);
        System.out.println(linkedList);
    }
}