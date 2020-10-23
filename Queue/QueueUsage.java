/**
 * 队列基本用法
 *
 * @Author: zzStar
 * @Date: 10-17-2020 10:26
 */
public class QueueUsage {
    int[] elements;

    public QueueUsage() {
        elements = new int[0];
    }

    //入队
    public void add(int element) {
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

    //出队
    public int poll() {
        if (elements.length == 0) {
            throw new RuntimeException("queue is empty");
        }
        //把数组中第0个元素取出来
        int element = elements[0];
        //创建一个新的数组
        int[] newArr = new int[elements.length - 1];
        //复制原数组中的元素到新数组去
        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = elements[i + 1];
        }
        elements = newArr;
        return element;
    }

    //判断是否为空
    public boolean isEmpty() {
        return elements.length == 0;
    }


    public static void main(String[] args) {
        QueueUsage queueUsage = new QueueUsage();

        queueUsage.add(1);
        queueUsage.add(2);
        queueUsage.add(3);
        System.out.println(queueUsage.poll());
        System.out.println(queueUsage.poll());
        System.out.println(queueUsage.poll());
        System.out.println(queueUsage.isEmpty());
        System.out.println(queueUsage.poll());
    }
}
