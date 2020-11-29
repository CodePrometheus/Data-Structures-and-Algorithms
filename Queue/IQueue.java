/**
 * @Description: 模拟实现Queue接口
 * @Author: zzStar
 * @Date: 2020/10/22 19:20
 */
public interface IQueue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();

}
