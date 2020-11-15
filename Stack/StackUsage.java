/**
 * 栈的基本用法 -> 后进先出LIFO
 * 同数组一样，栈也是一种线性结构，栈对应的操作是数组的子集
 * @Author: zzStar
 * @Date: 10-17-2020 10:03
 */
public class StackUsage {

    //栈底用数组来存储
    int[] elements;

    public StackUsage() {
        elements = new int[0];
    }

    //压入元素
    public void push(int element) {
        //创建一个新的数组
        int[] newArr = new int[elements.length + 1];
        //把原数组中的元素复制到新数组中
        for (int i = 0; i < elements.length; i++) {
            newArr[i] = elements[i];
        }
        //把添加的元素放入新数组中
        newArr[elements.length] = element;
        //新数组替换旧数组
        elements = newArr;
    }

    // 取出栈顶元素
    public int pop() {
        //首先判断是否为空
        if (elements.length == 0) {
            throw new RuntimeException("stack is empty");
        }
        //取出数组的最后一个元素
        int element = elements[elements.length - 1];
        //创建一个新的数组
        int[] newArr = new int[elements.length - 1];
        //原数组中除了最后一个元素的其他元素都放入新的数组当中
        for (int i = 0; i < elements.length - 1; i++) {
            newArr[i] = elements[i];
        }
        //继续替换数组
        elements = newArr;
        //返回栈顶元素
        return element;
    }

    //不取出，查看栈顶元素
    public int peek() {
        if (elements.length == 0) {
            throw new RuntimeException("stack is empty");
        }
        return elements[elements.length - 1];
    }

    //判断栈是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }


    public static void main(String[] args) {
        StackUsage stackUsage = new StackUsage();
        stackUsage.push(8);
        stackUsage.push(7);
        stackUsage.push(6);
        stackUsage.push(5);

        System.out.println(stackUsage.peek());//5
        System.out.println(stackUsage.pop());//5
        System.out.println(stackUsage.pop());
        System.out.println(stackUsage.pop());
        System.out.println(stackUsage.pop());//8

        System.out.println(stackUsage.isEmpty());
    }
}
