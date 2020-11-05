/**
 * 基于链表实现的set集合
 *
 * @Author: zzStar
 * @Date: 11-04-2020 21:40
 */
public class LinkedListSet<E> implements SetUsage<E> {

    private LinkedListRealization<E> list;

    public LinkedListSet() {
        list = new LinkedListRealization<>();
    }

    @Override
    public void add(E e) {
        // 不做重复
        if (!list.contains(e))
            list.addFirst(e);
    }

    @Override
    public void remove(E e) {
        list.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }


}
