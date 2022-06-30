public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        // 测试
        System.out.println("双向链表的测试");
        // 先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");
        // 创建一个双向链表
        /*
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        doubleLinkedList.getList();

        // 修改
        HeroNode2 newHeroNode = new HeroNode2(4, "公孙胜", "入云龙");
        doubleLinkedList.update(newHeroNode);
        System.out.println("修改后的链表情况");
        doubleLinkedList.getList();

        // 删除
        doubleLinkedList.del(3);
        System.out.println("删除后的链表情况~~");
        doubleLinkedList.getList();*/
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        System.out.println("=========================");
        doubleLinkedList.getList();
    }
}

class DoubleLinkedList {
    //头节点
    public HeroNode2 head = new HeroNode2();

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void getList() {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //添加到末尾
    public void add(HeroNode2 heroNode) {
        HeroNode2 temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //按编号顺序添加
    public void addByOrder(HeroNode2 heroNode){
        HeroNode2 temp = head;
        boolean flag = false;
        while(temp.next != null){
            if(heroNode.no < temp.next.no ){
                break;
            }else if(heroNode.no == temp.next.no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) { //不能添加，说明编号存在
            System.out.printf("准备插入的英雄的编号 %d 已经存在了, 不能加入\n", heroNode.no);
        } else {
            //插入到链表中, temp的后面
            heroNode.next = temp.next;
            temp.next = heroNode;

        }
    }

    //修改
    public void update(HeroNode2 heroNode) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = head.next;
        Boolean flag = false;
        while (temp != null) {
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("没有找到 编号 %d 的节点，不能修改\n", heroNode.no);
        }
    }

    public void del(int no) {
        //判断链表是否为空
        if (head.next == null) {
            System.out.println("链表为空~~");
            return;
        }
        HeroNode2 temp = head.next;
        Boolean flag = false;
        while (temp != null) {
            if (temp.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            // 下面我们的代码有问题?
            // 如果是最后一个节点，就不需要执行下面这句话，否则出现空指针
            // temp.next为null
            // temp.next.pre = temp.pre;
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }
}


class HeroNode2 {
    public int no;
    public String name;
    public String nickName;

    public HeroNode2 pre;   //指向前一个节点，默认为null
    public HeroNode2 next;  //指向下一个节点，默认为null

    public HeroNode2() {
    }

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }
    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }

}


