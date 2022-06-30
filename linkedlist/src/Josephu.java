import jdk.nashorn.internal.ir.WhileNode;

public class Josephu {
    public static void main(String[] args) {
        CircleSingleLinkedLIst circleSingleLinkedLIst = new CircleSingleLinkedLIst();
        circleSingleLinkedLIst.addBoy(5);
        circleSingleLinkedLIst.showBoy();
        System.out.println("=================");
        circleSingleLinkedLIst.countBoy(5, 1, 2);
    }
}


class CircleSingleLinkedLIst {
    //创建一个first指针，当前为空
    Boy first = null;

    //往链表中添加节点，num为添加的节点个数
    public void addBoy(int num) {
        //校验num的值
        if (num < 1) {
            System.out.println("num的值不正确");
            return;
        }
        Boy curBoy = null; //辅助指针，帮助构建循环链表
        for (int i = 1; i <= num; i++) {
            //创建节点
            Boy boy = new Boy(i);
            if (i == 1) {
                //如果添加的是第一个节点，需要构建循环结构
                first = boy;
                boy.setNext(first); //环结构
                curBoy = first;     //辅助指针
            } else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前的循环链表
    public void showBoy() {
        if (first == null) {
            System.out.println("当前链表为空");
            return;
        }
        Boy curBoy = first;
        while (true) {
            System.out.printf("小孩的编号 %d \n", curBoy.getNo());
            if (curBoy.getNext() == first) {
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * @param n 循环链表中最初的小孩个数
     * @param k 从第k个小孩开始报数
     * @param m 表示数几个
     */
    public void countBoy(int n, int k, int m) {
        //参数校验
        if (first == null || k < 1 || k > n) {
            System.out.println("参数错误");
        }
        //辅助指针
        Boy helper = first;
        //为了方便出圈，helper应始终处于first后一位,直到链表中只有一个节点
        //找到helper的初始位置
        while (true) {
            if (helper.getNext() == first) {
                break;
            }
            helper = helper.getNext();
        }

        //在开始报数前，要先找到第k个小孩，即让初始 first 和  helper 移动 k - 1次
        for (int i = 0; i < k - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //开始出圈
        while (true) {
            //当链表中只剩一个节点的时候停止出圈
            if (helper == first) {
                break;
            }
            //报数。让first 和  helper 移动 m - 1次
            for (int i = 0; i < m - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //将first指向的小孩节点出圈
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("小孩%d出圈\n", first.getNo());
    }

}


class Boy {
    private int no;
    private Boy next;

    public Boy() {
    }

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
