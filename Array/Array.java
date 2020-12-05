/**
 * @Description: 数组的使用
 * @Author: zzStar
 * @Date: 2020/10/22 18:34
 */
/**
 * 数组最大的优点：快速查询
 * 数组最好应用于"索引有语义"的情况（但并非所有有语义的索引都适用于数组，例如身份证号码）
 * 我们需要额外处理索引有语义的情况
 * 数组的容量：capacity，数组的大小：size，初始为0
 *
 * 1、实现基本功能：增删改查、各种判断方法等等
 * 2、使用 泛型 让我们的数据结构可以放置 "任何"（不可以是基本
 * 数据类型，只能是类对象，好在每一个基本类型都有一个对应的
 * 包装类，它们之间可以自动进行装箱和拆箱的操作） 的数据类型
 * 3、数组的扩容与缩容
 *
 * 手写时的易错点：
 *          1、注意数组的下标
 *          2、注意 size 与 capacity 的区别
 * 简单的时间复杂度分析：
 *      为什么要用大O，叫做大O(n)？
 *          忽略了常数，实际时间 T = c1 * n + c2
 *      为甚不加上其中每一个常数项，而是要忽略它？
 *          比如说把一个数组中的元素取出来这个操作，很有可能不同的语音基于不同的实现，它实际运行的时间是不等的。
 *          就算转换成机器码，它对应的那个机器码的指令数也有可能是不同的。就算指令数是相同的，同样一个指令在 CPU
 *          的底层，你使用的 CPU 不同，很有可能执行的操作也是不同的。所以，在实际上我们大概能说出来这个 c1
 *          是多少，但是很难准确判断其具体的值。
 *      大O的表示的是渐进时间复杂度，当 n 趋近于无穷大的时候，其算法谁快谁慢。
 *      增：O（n)
 *      删：O（n）
 *      改：已知索引 O（1）；未知索引 O（n）
 *      查：已知索引 O（1）；未知索引 O（n）
 *
 * 均摊时间复杂度分析：
 *      假设 capacity = n，n + 1 次 addLast/removeLast，触发 resize，总共进行 2n + 1 次基本操作
 *      平均来看，每次 addLast/removeLast 操作，进行 2 次基本操作
 *      均摊计算，时间复杂度为 O（1）
 *
 * 复杂度震荡：
 *      当反复先后调用 addLast/removeLast 进行操作时，会不断地进行 扩容/缩容，此时时间复杂度为 O（n）
 *      出现问题的原因：removeLast 时 resize 过于着急
 *      解决方案：Lazy （在这里是 Lazy 缩容）
 *      等到 size == capacity / 4 时，才将 capacity 减半
 *
 * Array 泛型中盛放的是 E 类型的数据 */
public class Array<E> {

    private E[] data;
    private int size;

    // 构造函数，传入数组的容量capacity构造Array
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    public Array(E[] arr) {
        data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        size = arr.length;
    }

    // 无参数的构造函数，默认数组的容量capacity=10
    public Array() {
        this(10);
    }

    // 获取数组的容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize() {
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E e) {

        // 先判断
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        // 扩容
        if (size == data.length)
            resize(2 * data.length);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;

        size++;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e) {
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    public void addFirst(E e) {
        add(0, e);
    }

    // 获取index索引位置的元素
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    // 查找数组中是否有元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        // 移动
        for (int i = index + 1; i < size; i++)
            data[i - 1] = data[i];

//        if (size - index + 1 >= 0) System.arraycopy(data, index + 1, data, index + 1 - 1, size - index + 1);

        size--;
        data[size] = null; // loitering(闲散) objects != memory leak(内存泄漏)

        // 满足条件缩小容量，防止复杂度震荡
        if (size == data.length / 4 && data.length / 2 != 0)
            resize(data.length / 2);
        return ret;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst() {
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 从数组中删除元素e
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    // 堆中交换元素
    public void swap(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size)
            throw new IllegalArgumentException("Index is illegal");
        E t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(", ");
        }
        res.append(']');
        return res.toString();
    }

    // 将数组空间的容量变成newCapacity大小，对外不可见
    private void resize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++)
            newData[i] = data[i];
        data = newData;
    }
}