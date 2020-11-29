/**
 * 基于二分搜索树实现的set
 *
 * @Author: zzStar
 * @Date: 11-04-2020 21:48
 */
public class BSTSet<E extends Comparable<E>> implements ISet<E> {

    private BSTRecursion<E> bstRecursion;

    public BSTSet() {
        bstRecursion = new BSTRecursion<>();
    }

    @Override
    public void add(E e) {
        bstRecursion.add(e);
    }

    @Override
    public void remove(E e) {
        bstRecursion.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bstRecursion.contains(e);
    }

    @Override
    public int getSize() {
        return bstRecursion.size();
    }

    @Override
    public boolean isEmpty() {
        return bstRecursion.isEmpty();
    }
}
