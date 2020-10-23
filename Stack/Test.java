/**
 * @Author: zzStar
 * @Date: 10-22-2020 18:46
 */
public class Test {
    public static void main(String[] args) {
        ArrayStack<Object> stack = new ArrayStack<>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
            System.out.println(stack);
        }
        stack.pop();
        System.out.println(stack);
    }
}

