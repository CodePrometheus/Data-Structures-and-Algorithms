/**
 * @Description: 基于AVL的map
 * @Author: zzStar
 * @Date: 2020/11/25 19:05
 */
public class AVLMap<K extends Comparable<K>, V> implements MapUsage<K, V> {

    private AVLTree<K, V> avl;

    public AVLMap() {
        avl = new AVLTree<>();
    }

    @Override
    public int getSize() {
        return avl.getSize();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }

    @Override
    public void add(K key, V value) {
        avl.add(key, value);
    }

    @Override
    public boolean contains(K key) {
        return avl.contains(key);
    }

    @Override
    public V get(K key) {
        return avl.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        avl.set(key, newValue);
    }

    @Override
    public V remove(K key) {
        return avl.remove(key);
    }
}