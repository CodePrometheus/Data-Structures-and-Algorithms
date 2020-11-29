public class SingleLinkedListImpl {
    public static void main(String[] args) {
        //进行测试
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "李逵", "黑旋风");
        HeroNode hero3 = new HeroNode(3, "卢俊义", "玉麒麟");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建单向链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        //加入方法1
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //加入方法2 按照编号的顺序
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        //显示修改前
        System.out.println("修改前链表情况");
        singleLinkedList.list();

        //测试修改节点
        HeroNode newHeroNode = new HeroNode(2, "吴用", "智多星");
        singleLinkedList.update(newHeroNode);

        //显示
        System.out.println("修改后链表情况");
        singleLinkedList.list();

        //删除
        singleLinkedList.del(1);
        System.out.println("删除后链表情况");
        singleLinkedList.list();
    }
}

//管理
class SingleLinkedList {
    //初始化一个头节点，不存放具体的数据
    private HeroNode head = new HeroNode(0, "", "");

    //添加节点到单向链表
    //不考虑编号顺序时，将最后这个节点的next指向新的节点
    public void add(HeroNode heroNode) {
        //因为head节点不能动，因此需要一个辅助遍历temp
        HeroNode temp = head;
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
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;
    }


    //第二种方式在添加时，根据排名将人物插入到  指定的位置
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode) {
        //同样，头节点不能动，仍要通过一个辅助指针(变量)来帮助找到添加的位置
        //单链表，找的 temp是  位于 添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        boolean flag = false;//添加的编号是否存在，默认为false
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
    }

    //修改节点信息，根据no编号来修改，即no编号不动  no即Node
    public void update(HeroNode newHeroNode) {
        //判断是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
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


    //删除节点
    //头节点不动，temp辅助节点找到待删除节点的前一个节点  将temp.next=temp.next.next
    //比较时，是两节点no比较
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false;
        while (true) {
            if (temp.next == null) {
                break;//到链表最后退出
            }
            if (temp.next.no == no) {
                //找到待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;//temp后移，遍历
        }
        //判断flag
        if (flag) {
            //找到可以删除的节点
            temp.next = temp.next.next;
        } else {
            System.out.printf("要删除的%d节点不存在\n", no);
        }
    }


    //显示链表[遍历]
    public void list() {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，需要一个辅助变量来遍历
        HeroNode temp = head.next;
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

}

//定义，每个HeroNode对象就是一个节点
class HeroNode {
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int hNo, String hName, String hNickname) {
        this.no = hNo;
        this.name = hName;
        this.nickname = hNickname;
    }

    //为显示方法，重新toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}