import java.util.TreeMap;

/**
 * HashTable实现 可看成TreeMap数组 底层红黑树
 * 均摊复杂度为O(1) 牺牲了顺序性
 *
 * @Author: zzStar
 * @Date: 11-29-2020 11:08
 */
public class HashTableImpl<K, V> {

    // 根据提供的建议采用的素数
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    // N / M >= upperTol 扩容
    private static final int upperTol = 10;
    private static final int lowerTol = 2;
    private int capacityIndex = 0;


    private TreeMap<K, V>[] hashTable;
    // 取模的这个素数
    private int M;
    private int size;

    public HashTableImpl() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashTable = new TreeMap[M];

        // 每一个都初始化
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }

    }

//    // 注意hash冲突
//    public HashTableImpl() {
//        this();
//    }

    /**
     * 辅助方法，先hashCode()转为整型，再抹去整型对应的符号，最后取模，结果是将key这个值转变为当前hash表中对应的索引值
     *
     * @param key
     * @return
     */
    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {

        // 空间换时间，对多次计算hash做出优化
        TreeMap<K, V> map = hashTable[hash(key)];

        // 根据得到的索引查看是否包含了该key
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            // 没有的话 同样是put 然后维护size
            map.put(key, value);
            size++;

            // 进入判断 越界则无法扩容
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }


    private void resize(int newM) {
        // 创建新的HT
        TreeMap<K, V>[] newHT = new TreeMap[newM];

        // 每个初始化
        for (int i = 0; i < newM; i++) {
            newHT[i] = new TreeMap<>();
        }

        // 注意，这里要提前替换
        int oldM = M;
        this.M = newM;

        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet()) {
                // 要根据现在新的newHT来计算hash值，所以前面要进行一个替换
                newHT[hash(key)].put(key, map.get(key));
            }
        }
        this.hashTable = newHT;
    }

    // 返回key所对应的值
    public V remove(K key) {
        TreeMap<K, V> map = hashTable[hash(key)];
        V ret = null;

        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
        }

        if (size < lowerTol * M && capacityIndex - 1 >= 0) {
            capacityIndex--;
            resize(capacity[capacityIndex]);
        }

        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];

        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + "doesn't exist");
        }
        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }


}
