/**
 * 实现Map
 *
 * @Author: zzStar
 * @Date: 11-05-2020 10:45
 */
public interface IMap<K, V> {
    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
