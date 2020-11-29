public class CircleLinkedListImpl {
    public static void main(String[] args) {

        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addPersonNode(9);
        circleLinkedList.selectPersonNode();

        circleLinkedList.countPerson(1, 2, 9);

    }
}

class CircleLinkedList {

    //    创建一个first节点，没有值
    private PersonNode first = null;

    //    添加环形节点，count表示节点数量
    public void addPersonNode(int count) {
        if (count < 1) {
            System.out.println("count的值不正确");
            return;
        }
//        定义一个临时节点，帮助构建环形链表
        PersonNode currentNode = null;
//        用for循环创建环形链表
        for (int i = 1; i < count; i++) {
//            根据编号创建人员节点
            PersonNode personNode = new PersonNode(i);
//            如果创建的是第一个节点
            if (i == 1) {
                first = personNode;//给第一个节点赋值
                first.setNext(first);//第一个节点的next域指向第一个节点，构成一个节点的环形链表
                currentNode = first;//因为第一个节点固定，不能移动，所以把第一个节点赋值给临时节点
            } else {//之后的节点
                currentNode.setNext(personNode);//当前节点后指向新创建的人员节点,链路先创建
                personNode.setNext(first);//新创建的人员节点指向first节点，构成一个环形
                currentNode = personNode;//当前节点后移，为了创建下一个新的节点，构成一个新的环形
            }
        }
    }

    //        遍历当前环形链表
    public void selectPersonNode() {
        if (first == null) {
            System.out.println("空的环形链表");
            return;
        }
//            仍然因为空的指针不能动，因此需要使用一个辅助指针完成遍历
        PersonNode currentNode = first;//定义一个临时节点
        while (true) {
            System.out.printf("人员节点编号%d \n", currentNode.getNum());
            if (currentNode.getNext() == first) {//说明遍历结束
                break;
            }
            currentNode = currentNode.getNext();//没有满足上面条件则临时节点后移
        }
    }

    //        根据用户的输入，计算人员出题的顺序
//    三个参数分别表示从第几个人员开始，数的次数，最初有多少人员在圈中
    public void countPerson(int startNode, int countNum, int nums) {
//       判断数据
        if (first == null || startNode < 1 || startNode > nums) {
            System.out.println("error");
            return;
        }
//        创建辅助指针，帮助完成人员出圈
        PersonNode helper = first;
//        创建的这个辅助helper，事先应该指向链表的最后这个节点
        while (true) {
            if (helper.getNext() == first) {//指向了最后
                break;
            }
            helper = helper.getNext();
        }
//        报数前，先让first和helper移动k-1次
        for (int j = 0; j < startNode - 1; j++) {
            first = first.getNext();
            helper = helper.getNext();
        }
//        报数时，同时移动m-1次，然后出圈
        while (true) {
            if (helper == first) {
//                此时圈中只有一个人员
                break;
            }
//            同时移动countNum-1,然后出圈
            for (int j = 0; j < countNum - 1; j++) {
                first = first.getNext();
                helper = helper.getNext();
            }
//            此时first指向的节点就是要出圈的节点
            System.out.printf("%d出圈", first.getNum());
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩为%d\n", helper.getNum());
    }


}


//定义一个节点类，每一个PersonNode对象就是一个节点
class PersonNode {
    private int num;
    private PersonNode next;

    public PersonNode(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public PersonNode getNext() {
        return next;
    }

    public void setNext(PersonNode next) {
        this.next = next;
    }
}