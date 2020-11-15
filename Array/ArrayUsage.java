/**
 * 测试自定义Array
 *
 * @Author: zzStar
 * @Date: 11-13-2020 13:26
 */
public class ArrayUsage {
    public static void main(String[] args) {
        Array<Integer> array = new Array<>();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array);

        array.add(0,100);
        System.out.println(array);

//        array.removeFirst();
//        System.out.println(array);
//
//        array.removeElement(9);
//        System.out.println(array);
    }
}
