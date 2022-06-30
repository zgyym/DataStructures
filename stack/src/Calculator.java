public class Calculator {
    public static void main(String[] args) {
/*

        准备两个栈，一个是数栈，用来存放数，另一个是符号栈用来存放运算符

        1. 通过一个index值（索引）来遍历表达式

        2. 如果扫描到的是一个数字，直接入数栈

        3. 如果扫描到的是一个运算符

            1) 如果符号栈为空，就直接入栈
            2) 如果符号栈不为空，就进行比较（该处需要将当前运算符与符号栈内的每一个运算符比较），
            如果当前的运算符的优先级小于或等于符号栈内的运算符的优先级
            就需要从栈内弹出两个数字和一个运算符进行运算，将结果压入数栈，然后将当前的运算符压入符号栈。
            如果当前的运算符的优先级大于符号栈内的运算符的优先级，直接入符号栈

        4. 当表达式扫描完成后，按顺序弹出两个数字和一个运算符进行运算，将得到的结果压入数栈

        5. 重复步骤4，知道数栈中只有一个数时，这个数就是该表达式的结果

*/

        Stack numStack = new Stack(20);
        Stack operStack = new Stack(20);
        String expression = "7*2*3*2-5+1-5+3-4";  //表达式
        int index = 0;
        int num1 = 0, num2 = 0;      //两个运算的数
        int oper = 0;    //运算符号
        int result = 0;  //用于存放结果
        char ch = ' ';      //将每次扫描到的数字或符号保存到ch中
        //开始扫描
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                /*如果扫描到的是一个运算符
                1) 如果符号栈为空，就直接入栈
                2) 如果符号栈不为空，就进行比较（该处需要将当前运算符与符号栈内的每一个运算符比较），
                如果当前的运算符的优先级小于或等于符号栈内的运算符的优先级
                就需要从栈内弹出两个数字和一个运算符进行运算，将结果压入数栈，然后将当前的运算符压入符号栈。
                如果当前的运算符的优先级大于符号栈内的运算符的优先级，直接入符号栈*/
                if (!operStack.isEmpty()) {
                    //判断运算符优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        result = numStack.cal(num1, num2, oper);    //计算
                        numStack.push(result);  //把运算的结果如数栈
                        operStack.push(ch);     //然后将当前的操作符入符号栈

                    }else{
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }
            } else {
                numStack.push(ch - 48);    //char类型的数字为ASC码，但是其真正的数应该时 ch - 48，比如’1‘的ASC码是49
            }
            index++;
            if(index == expression.length()){
                break;
            }
        }
        while(true){
            if(operStack.isEmpty()){        //符号栈为空，数栈只有一个数时，计算结束
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1,num2,oper);
            numStack.push(result);
        }
        System.out.println(numStack.pop());
    }
}


class Stack {
    int maxSize;    //栈的大小
    int[] stack;    //数组模拟栈
    int top = -1;   //栈顶指针，初始化为-1

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    //判断栈空
    public boolean isFull() {
        return top == maxSize - 1;
    }

    //判断栈满
    public boolean isEmpty() {
        return top == -1;
    }

    //入栈
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        /*
        top++;
        stack[top] = value;
        */
        stack[++top] = value;

    }

    //出栈
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空");
        }
        /*
        int value = stack[top];
        top--;
        return value;
         */
        return stack[top--];
    }

    //遍历栈,从栈顶开始显示数据
    public void list() {
        if (isEmpty()) {
            System.out.println("栈空");
        }
        for (int i = top; i > -1; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    //查看栈顶元素
    public int peek() {
        return stack[top];
    }

    //定义运算符的优先级
    //数字越大优先级越高
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char value) {
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //运算
    public int cal(int num1, int num2, int oper) {
        int result = 0;
        switch (oper) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;

        }
        return result;
    }

}
