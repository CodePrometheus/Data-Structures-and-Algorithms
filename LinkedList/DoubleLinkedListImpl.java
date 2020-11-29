
//双向链表
//遍历方法和单链表一样，只不过双向链表既可以向后，也可以向前
//修改与单向一样
//可以实现自我删除某个节点，直接找到temp.pre.next=temp.next  temp.next.pre=temp.pre

public class DoubleLinkedListImpl {

    public static void main(String[] args) {

        //双向链表测试
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "李逵", "黑旋风");
        HeroNode2 hero3 = new HeroNode2(3, "卢俊义", "玉麒麟");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        //创建单向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

//        System.out.println("创建的链表");
//        doubleLinkedList.list();

        //加入方法2 按照编号的顺序
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        System.out.println("添加之后链表");
        doubleLinkedList.list();


//        修改
        HeroNode2 newHeroNode = new HeroNode2(4, "bruce", "lee");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.list();

////        删除
//        doubleLinkedList.del(3);
//        System.out.println("删除后的链表情况");
//        doubleLinkedList.list();
    }
}


//创建一个双向链表的类
//类里面定义curd方法
class DoubleLinkedList{

//    先初始化一个头节点，保持不动，也不存放具体的数据
    private HeroNode2 head = new HeroNode2(0,"","");

//    返回头节点
    public HeroNode2 getHead(){
        return head;
    }

//    遍历双向链表
//    显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将temp后移，否则为死循环
            temp = temp.next;
        }
    }


//    添加方法
    public void add(HeroNode2 heroNode) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
//        形成一个双向链表
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
        boolean flag = false; //添加的编号是否存在，默认不存在为false
        while (true) {
            if (temp.next == null) {//说明temp已经是链表最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) {
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移，遍历当前链表
        }
        //判断flag的值
        if (flag) {
            System.out.printf("准备插入的人物的编号%d已经存在，不能加入\n" + heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
//        当退出while循环时，temp就指向了链表的最后
//        将最后这个节点temp的next指向新的节点,新节点的pre指向最后个节点temp
        temp.next = heroNode;
        heroNode.pre = temp;
}



    //修改节点信息，根据no编号来修改，即no编号不动  no即Node
    //双向修改与单向一样
    public void update(HeroNode2 newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;//遍历完链表
            }
            if (temp.no == newHeroNode.no) {
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        } else {
            System.out.printf("没有找到编号%d的节点，不能修改\n", newHeroNode.no);
        }
    }


//    删除节点
//    对于双向，直接找到即可，找到之后自我删除既可
    public void del(int no){

//        是否为空
        if (head.next == null){
            System.out.println("链表为空");
        }

        HeroNode2 temp = head.next;//辅助变量（指针）
        boolean flag = false;//标志是否找到待删除节点的
        while (true){
            if (temp == null){//到链表最后
                break;
            }
            if (temp.no == no){
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
//        判断flag
        if (flag){
//            找到删除
            temp.pre.next = temp.next;
            if (temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }


    }



}




//定义HeroNode2，每个对象即一个节点
class HeroNode2 {
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;//指向下一个节点   默认为null
    public HeroNode2 pre;//指向前一个节点  默认为null

    //构造器
    public HeroNode2(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}