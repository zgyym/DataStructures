import java.util.Scanner;

public class ArrayQueueDemo {
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }
}


class ArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//表示头指针，指向队头前一位
    private int rear;//表示尾指针，指向队尾
    private int[] arr;//该数组模拟队列，用于存放数据

    //创建链表，即初始化链表
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = -1;//指向队头，但不包括第一个数据，即指向队列头的前一个位置
        rear = -1;//指向队尾，包含最后一个数据，即就是队列最后一个数据
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull() {
        return rear == maxSize - 1;
    }

    //添加数据到队尾
    public void addQueue(int n) {
        //判断是否为满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        rear++; //让rear后移
        arr[rear] = n;//添加数据
    }

    //从队头取出数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        front++;//让front后移
        return arr[front];
    }

    //显示队列中的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        //遍历
        //第一个数据应从队头开始（所以从front + 1开始）,以队尾结束（所以以 < rear + 1结束）
        //rear == front + size
        for (int i = front + 1; i < rear + 1; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    //显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front + 1];
    }
}