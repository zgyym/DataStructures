public class Calculator2 {
    public static void main(String[] args) {
        Stack numStack = new Stack(20);
        Stack operStack = new Stack(20);
        String expression = "70-70*70/2";  //表达式
        int index = 0;
        int num1 = 0, num2 = 0;      //两个运算的数
        int oper = 0;    //运算符号
        int result = 0;  //用于存放结果
        char ch = ' ';      //将每次扫描到的数字或符号保存到ch中
        String keepNum = "";      //用于拼接多位数字符串
        //开始扫描
        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);
            if (operStack.isOper(ch)) {
                if (!operStack.isEmpty()) {
                    //判断运算符优先级
                    if (operStack.priority(ch) <= operStack.priority(operStack.peek())) {
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();

                        result = numStack.cal(num1, num2, oper);    //计算
                        numStack.push(result);  //把运算的结果如数栈
                        operStack.push(ch);     //然后将当前的操作符入符号栈]
                    } else {
                        operStack.push(ch);
                    }
                } else {
                    operStack.push(ch);
                }

                index++;
            } else {
                keepNum = "";
                while (index < expression.length() && (ch = expression.charAt(index)) >= 48 && (ch = expression.charAt(index)) <= 57){
                    keepNum += ch;
                    index++;
                }
                numStack.push(Integer.parseInt(keepNum));

/*

                keepNum += ch;
                //如果ch已经是表达式的最后一位，直接入栈
                //index指向ch
                if (index == expression.length() - 1) {
                    numStack.push(Integer.parseInt(keepNum));
                } else {
                    //如果ch不是最后一位，看ch的下一位是什么
                    //如果是表达式--->入栈
                    //如果是数字---->继续扫描
                    //注意是看后一位，不是index++
                    if (operStack.isOper(expression.substring(index + 1, index + 2).charAt(0))) {
                        numStack.push(Integer.parseInt(keepNum));
                        //重要的!!!!!!, keepNum清空
                        keepNum = "";
                    }

                }*/
            }
            if (index == expression.length()) {
                break;
            }
        }
        while (true) {
            if (operStack.isEmpty()) {        //符号栈为空，数栈只有一个数时，计算结束
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            result = numStack.cal(num1, num2, oper);
            numStack.push(result);
        }
        System.out.println(numStack.pop());
    }
}

