/**
 * @Description: 并查集接口实现
 * @Author: zzStar
 * @Date: 2020/11/22 20:14
 */
public interface IUF {

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);

    int getSize();

}
