/**
 * @Description: 集合实现
 * @Author: zzStar
 * @Date: 2020/11/04 21:45
 */
public interface ISet<E> {
    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();
}
