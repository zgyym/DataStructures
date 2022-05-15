import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {

        //测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleArrayQueue queue = new CircleArrayQueue(4); //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
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
class CircleArrayQueue {
    private int maxSize;//表示数组的最大容量
    private int front;//表示头指针，指向队头
    private int rear;//表示尾指针，指向队尾后一位
    private int[] arr;//该数组模拟队列，用于存放数据

    //创建链表，即初始化链表
    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
        front = 0;
        rear = 0;
    }

    //判断链表是否为空
    public boolean isEmpty() {
        return front == rear;
    }

    //判断队列是否已满
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    //添加数据到队尾
    public void addQueue(int n) {
        //判断是否为满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        arr[rear] = n;
        rear = (rear + 1) % maxSize;
    }

    //从队头取出数据
    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列中的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        //遍历时从front开始，在遍历过程中i的值可能会越界，所以需要取模
        for (int i = front; i < front + getSize(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    public int getSize() {
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的头数据， 注意不是取出数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}
