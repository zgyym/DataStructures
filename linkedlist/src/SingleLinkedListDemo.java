
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");
        HeroNode hero5 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero6 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero7 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero8 = new HeroNode(4, "林冲", "豹子头");

        //创建要给链表
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        SingleLinkedList singleLinkedList3 = new SingleLinkedList();
        //加入
        singleLinkedList1.addByOrder(hero1);
        singleLinkedList1.addByOrder(hero3);
        singleLinkedList1.addByOrder(hero4);
        singleLinkedList1.getList();

        System.out.println("=========================");


        singleLinkedList2.addByOrder(hero5);
        singleLinkedList2.addByOrder(hero6);

        singleLinkedList2.addByOrder(hero8);
        singleLinkedList2.getList();

        System.out.println("=========================");
        singleLinkedList3.head = SingleLinkedList.merge(singleLinkedList1.head, singleLinkedList2.head);
        singleLinkedList3.getList();

        /*
        singleLinkedList.getList();
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("===========");
        singleLinkedList.getList();
        singleLinkedList.update(hero);
        singleLinkedList.getList();
        System.out.println(singleLinkedList.getLength(singleLinkedList.head));
        System.out.println(singleLinkedList.findLastNode(singleLinkedList.head,10));*/
    }
}

//定义SingleLinkedList管理英雄
class SingleLinkedList {
    //头节点，头节点不要动, 不存放具体的数据
    HeroNode head = new HeroNode(0, "", "");

    //添加节点到单链表
    public void add(HeroNode heroNode) {
        //不考虑编号顺序时
        //先遍历单链表找到最后一个节点，将最后这个节点的 next 指向新的节点

        //头节点不能动，所以需要一个临时变量
        HeroNode temp = head;
        //遍历列表，找到最后节点（最后的temp就是最后节点）
        while (true) {
            if (temp.getNext() == null) {
                break;
            }
            //如果没有找到最后, 将temp后移
            temp = temp.getNext();
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的 next 指向新的节点
        temp.setNext(heroNode);
    }

    //按编号顺序进行添加
    public void addByOrder(HeroNode heroNode) {
        //因为头节点不能动，因此我们仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为单链表，因为我们找的temp 是位于添加位置的前一个节点，否则插入不了
        HeroNode temp = head;
        Boolean flag = false;   //flag用来标志要添加的节点的编号是否已经存在，默认为false

        while (true) {
            if (temp.getNext() == null) {     //temp已是最后一个节点，新节点插入末尾
                break;
            }
            if (temp.getNext().getNo() > heroNode.getNo()) {      //位置找到，新节点插入temp的下一个位置
                //因为从头开始找，编号递增，在temp.getNext().getNo()第一次大于heroNode.getNo()时退出循环,不退出循环的话，temp的值只会指向最后一个节点
                break;
            } else if (temp.getNext().getNo() == heroNode.getNo()) {       //要插入的新节点编号已经存在,退出循环，不退出循环的话，temp的值只会指向最后一个节点
                flag = true;
                break;
            }
            temp = temp.getNext();  //temp后移，遍历当前链表
        }
        //已经找到了新节点要插入的位置
        //判断flag 的值
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.getNo());
        } else {
            //插入到链表中, temp的后面
            heroNode.setNext(temp.getNext());
            temp.setNext(heroNode);
        }
    }


    //逆序打印单链表（方式二）
    public void reversePrint(HeroNode head) {
        if (head.getNext() == null) {
            return;
        }
        //创建一个栈
        Stack<HeroNode> stack = new Stack<>();
        //辅助变量
        HeroNode temp = head.getNext();
        //压栈
        while (temp != null) {
            stack.push(temp);
            temp = temp.getNext();
        }
        //弹栈输出
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }

    }

    //修改节点数据
    //根据编号来修改，但是编号不能修改
    public void update(HeroNode heroNode) {
        //判断链表是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空~~");
            return;
        }
        //定义一个辅助变量
        HeroNode temp = head;
        Boolean flag = false;       //flag用来标志是否找到要修改的节点
        //遍历
        while (true) {
            if (temp.getNext() == null) {     //temp指向最后一个节点,链表已经遍历完
                break;
            }
            if (temp.getNext().getNo() == heroNode.getNo()) {     //找到要修改节点(temp.getNext()),退出循环，不退出循环的话，temp的值只会指向最后一个节点
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        //根据flag 判断是否找到要修改的节点
        if (flag) {
            temp.getNext().setName(heroNode.getName());
            temp.getNext().setNickName(heroNode.getNickName());
        } else {      //没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.getNo());

        }
    }

    //反转单链表
    public void reverseList(HeroNode head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.getNext() == null || head.getNext().getNext() == null) {
            return;
        }
        HeroNode reverseHead = new HeroNode(0, "", "");
        HeroNode curr = head.getNext();//这里curr从第一个有效节点开始，不是头节点，用来遍历原链表
        //因为这个方法不是复制节点进行反转，而是将原来的节点重新连接，所以辅助变量curr开始不能指向头节点，否则会找不到出第一个外的其他节点
        //如果进行修改指针域的话，会影响反转后的链表的顺序
        HeroNode next = null;//指向当前节点[curr]的下一个节点
        while (curr != null) {
            next = curr.getNext();//先暂时保存当前节点的下一个节点，因为后面需要使用
            curr.setNext(reverseHead.getNext());//将curr的下一个节点指向新的链表的第一个节点
            reverseHead.setNext(curr);//将curr连接到新的链表上
            curr = next;//curr后移
        }
        //将head.next 指向 reverseHead.next , 实现单链表的反转
        head.setNext(reverseHead.getNext());
    }

    //删除节点。传入节点编号
    public void del(int no) {
        //判断链表是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空~~");
            return;
        }
        //定义一个辅助变量
        HeroNode temp = head;
        Boolean flag = false;       //flag用来标志是否找到待删除的节点
        //遍历
        while (true) {
            if (temp.getNext() == null) {     //已经到链表的最后
                break;
            }
            if (temp.getNext().getNo() == no) {
                //找到的待删除节点的前一个节点temp,退出循环，不退出循环的话，temp的值只会指向最后一个节点
                flag = true;
                break;
            }
            temp = temp.getNext();      //temp后移，遍历
        }
        //判断flag
        if (flag) {
            //可以删除
            temp.setNext(temp.getNext().getNext());
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //遍历时有两种方式

    //temp指向头节点的下一个节点
    /*
    public void getList(){
        if(head.getNext() == null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = head.getNext();
        while(true){
            if(temp == null){
                break;
            }
            System.out.println(temp);
            temp = temp.getNext();
        }

    }
*/
    //遍历输出单链表
    public void getList() {
        //临时变量temp
        HeroNode temp = head;
        //判断链表是否为空
        if (temp.getNext() == null) {
            System.out.println("链表为空");
            return;
        }
        //遍历输出每一个节点
        while (true) {
            //判断是否是最后一个节点
            if (temp.getNext() == null) {
                break;
            }
            //如果temp不在最后一个节点，需要将这个节点输出并将temp后移
            //temp刚开始时指向头节点，所以输出时要输出temp的下一个节点
            System.out.println(temp.getNext());
            temp = temp.getNext();
        }
    }


    /**
     * 获取链表的长度，不包括头节点
     */
    public int getLength(HeroNode head) {
        if (head.getNext() == null) {     //链表尾空
            return 0;
        }
        HeroNode curr = head;
        int length = 0;
        while (curr.getNext() != null) {
            length++;
            curr = curr.getNext();
        }
        return length;
    }

    //查找单链表中的倒数第k个结点
    public HeroNode findLastNode(HeroNode head, int k) {
        if (head.getNext() == null) {     //空链表
            return null;
        }
        int length = getLength(head);
        HeroNode temp = head;
        //对给定数进行校验
        if (k <= 0 || k > length) {
            return null;
        }
        for (int i = 0; i < length - k; i++) {      //length - k 不是要找第length - k个节点，而是辅助变量tenp移动多少次
            temp = temp.getNext();
        }
        return temp.getNext();
    }

    //合并两个有序链表
    public static HeroNode merge(HeroNode head1, HeroNode head2) {
        //如果有一个链表为空，直接返回另外一个链表
        if (head1.getNext() == null) {
            return head2;
        } else if (head2.getNext() == null) {
            return head1;
        }

        //创建一个新的链表
        HeroNode newListHead = new HeroNode(0, "", "");
        //辅助变量
        HeroNode temp1 = head1.getNext();//不指向头节点
        HeroNode temp2 = head2.getNext();
        HeroNode newTemp = newListHead;//指向头节点
        //遍历两个链表，当两个链表都没有到最后一个节点时，判断两个链表各个节点的编号大小
        while (temp1 != null && temp2 != null) {
            //如果链表1的当前节点小于将链表2的当前节点的，将链表2的该节点插入到新链表的末尾
            if (temp1.getNo() > temp2.getNo()) {
                newTemp.setNext(temp2);     //新链表的辅助指针指向要插入的节点
                newTemp = newTemp.getNext();    //将新链表的辅助指针项后移动，便于下次插入
                temp2 = temp2.getNext();    //链表2的辅助指针向后移
            } else if (temp1.getNo() < temp2.getNo()) {
                newTemp.setNext(temp1);
                newTemp = newTemp.getNext();
                temp1 = temp1.getNext();
            } else {
                //如果两个节点相等的话，随便一个插入新链表末尾，但是两个链表的辅助指针都要后移
                newTemp.setNext(temp1);
                newTemp = newTemp.getNext();
                temp1 = temp1.getNext();
                temp2 = temp2.getNext();
            }
        }
        //如果有一个链表遍历完了，将另一个剩余的节点直接插入到新链表的末尾
        if (temp1 == null) {
            newTemp.setNext(temp2);
        }
        if (temp2 == null) {
            newTemp.setNext(temp1);
        }
        return newListHead;
    }

}

//定义HeroNode ， 每个HeroNode 对象就是一个节点
class HeroNode {
    private int no;
    private String name;
    private String nickName;
    private HeroNode next;   //指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    //为显示方便，重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}