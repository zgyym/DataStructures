import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//逆波兰计算器
public class PolandNotation {
    public static void main(String[] args) {
        //说明为了方便，逆波兰表达式 的数字和符号使用空格隔开
        String suffixExpression = "3 4 + 5 * 6 -";   //29
        List<String> list = getList(suffixExpression);
        System.out.println(list);
        int result = cal(list);
        System.out.println(result);

    }

    //将suffixExpression放入到一个ArrayList中
    public static List<String> getList(String suffixExpression) {
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String str : split) {
            list.add(str);
        }
        return list;
    }

    //计算
    public static int cal(List<String> list) {
        Stack<String> stack = new Stack<String>();
        //遍历list
        for (String str : list) {
            if (str.matches("\\d+")) {      //正则表达式匹配多位数
                stack.push(str);
            } else {        //str为运算符
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int result = 0;
                if ("+".equals(str)) {
                    result = num1 + num2;
                } else if ("-".equals(str)) {
                    result = num1 - num2;
                } else if ("*".equals(str)) {
                    result = num1 * num2;
                } else if ("/".equals(str)) {
                    result = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把结果压入栈中
                stack.push(String.valueOf(result));
            }
        }
        //最后留在栈中的数就是结果
        return Integer.parseInt(stack.pop());
    }


    //将中缀表达式转换成后缀表达式

    @Test
    public void test() {
        String s = "1+((2+3)*4)-5";
        List<String> list = toInfixExpressionList(s);
        System.out.println(list);
        List<String> suffixExpreesionList = parseSuffixExpreesionList(list);
        System.out.println(suffixExpreesionList);
        int cal = cal(suffixExpreesionList);
        System.out.println(cal);
    }

    //将中缀表达式对应的List转换为后缀表达式对应的List
    /*
            1) 初始化两个栈，运算符栈s1和储存中间结果的栈s2；
            2) 从左至右扫描中缀表达式；
            3) 遇到操作数时，将其压入s2；
            4) 遇到运算符时，比较其与s1栈顶运算符的优先级；
                1. 如果s1为空，或者栈顶运算符为左括号”（“，则直接将此运算符压入s1；
                2. 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                3. 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到步骤（4-1）与s1中新的栈顶运算符相比较；
            5) 遇到括号时：
                1. 如果是左括号“(”，则直接压入s1；
                2. 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃；
            6) 重复步骤2至5，直到表达式的最右边；
            7) 将s1中剩余的运算符依次弹出并压入s2；
            8) 依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式；
            */
    public List<String> parseSuffixExpreesionList(List<String> infixList) {
        Stack<String> s1 = new Stack<>(); //符号栈
        //因为s2这个栈，在整个转换过程中没有弹栈pop的操作，而且在最后还需要将其逆序输出,
        // 因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2

        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈s2
        List<String> s2 = new ArrayList<>();    // 储存中间结果的List s2

        //扫描中缀表达式
        for (String item : infixList) {
            if (item.matches("\\d+")) {   //遇到操作数时，将其加入s2；
                s2.add(item);
            } else if ("(".equals(item)) {      //如果是左括号“(”，则直接压入s1；
                s1.push(item);
            } else if (")".equals(item)) {         //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃；
                while (!"(".equals(s1.peek())) {
                    s2.add(s1.pop());
                }
                s1.pop();       //将这一对括号丢弃！！！
            } else {
              /*  4) 遇到运算符时，比较其与s1栈顶运算符的优先级；
                    1. 如果s1为空，或者栈顶运算符为左括号”（“，则直接将此运算符压入s1；
                    2. 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                    3. 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到步骤（4-1）与s1中新的栈顶运算符相比较；*/

                /*
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
                 */
                while(true){
                    if(s1.isEmpty() || "(".equals(s1.peek())){
                        s1.push(item);
                        break;
                    }else if(Operation.getValue(item) > Operation.getValue(s1.peek())){
                        s1.push(item);
                        break;
                    }else{
                        s2.add(s1.pop());
                    }
                }

            }
        }
        // 7) 将s1中剩余的运算符依次弹出并压入s2；
        s2.add(s1.pop());
        return s2;

    }

    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String expression) {
        List<String> list = new ArrayList<>();
        int index = 0; //相当于一个指针，用来遍历表达式字符串
        String str = ""; //用于多位数的拼接
        char ch = ' ';  //每遍历到一个字符，就放入到ch

        //遍历表达式字符串
        while (true) {
            if (index == expression.length()) {
                break;
            }
            if ((ch = expression.charAt(index)) < 48 || (ch = expression.charAt(index)) > 57) {
                //如果取出的ch为符号，直接放到list中
                list.add("" + ch);
                index++;
            } else {
                //如果取出的ch是数字，则需要考虑多位数的情况
                str = "";   //首先清空字符串
                //当index不到字符串尾部(不是字符串最后一位字符，是最后一位字符的后面)，且ch为数字时，拼接字符串
                //即index指针将字符串遍历完或者取出的ch不是数字时就不进行多位数拼接了
                while (index < expression.length() && (ch = expression.charAt(index)) >= 48 && (ch = expression.charAt(index)) <= 57) {
                    str += ch;
                    index++;
                }
                list.add(str);
            }
        }
        return list;

    }

}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符" + operation);
                break;
        }
        return result;
    }

}

