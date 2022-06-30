
public class LinkedStackDemo {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        LinkedSatck linkedSatck = new LinkedSatck();
        linkedSatck.push(node1);
        linkedSatck.push(node2);
        linkedSatck.push(node3);
        linkedSatck.push(node4);
        linkedSatck.push(node5);
        linkedSatck.push(node6);
        linkedSatck.list();

        System.out.println("==============================");
        System.out.println(linkedSatck.pop());
    }
}

class LinkedSatck {
    Node head = new Node();

    //判断栈空
    public boolean isEmpty() {
        return head.next == null;
    }

    //入栈
    public void push(Node node) {
        Node temp = head;
        //循环找到链表的最后一个节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    //出栈，即删除最后一个节点
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        int value = temp.next.data;
        temp.next = null;
        return value;
    }

    //遍历栈
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
            return;
        }
        Node temp = head.next;
        while (temp != null) {          //temp不到最后一个节点的时候不停
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}

class Node {
    int data;
    Node next;

    public Node() {
    }

    public Node(int data) {
        this.data = data;
    }
}