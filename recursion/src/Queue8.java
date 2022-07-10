public class Queue8 {
    int max = 8;    //皇后的最大个数
    int[] array = new int[max];     //用一个一维数组来表示棋盘，下标（i + 1）表示行，值（array[i] + 1）表示列
    static int count = 0;

    public static void main(String[] args) {
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println(count);
    }


    //放置第n个皇后
    public void check(int n) {
        if (n == max) {
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {     //一共放入八个皇后，i < max
            array[n] = i;       //首先将当前皇后放到第一列，
            if (judge(n)) {       //判断第n个皇后与之前皇后有误冲突
                //没有冲突的话，放置下一个皇后
                check(n + 1);
            }
            //如果有冲突的话，需要回溯到上一个皇后在本行后移一列  array[n] = i;
        }
    }


    /**
     * 判断每个皇后之间是否冲突
     *
     * @param n 第n个皇后
     * @return true为没有冲突，false为右冲突
     */
    public boolean judge(int n) {
        if (n == 8) {     //n从下标0开始，当n等于8时，棋盘中已经有了八个皇后，此时是第九个皇后
            return true;
        }
        for (int i = 0; i < n; i++) {
            //array[n] == array[i] 判断当前皇后（即第n个皇后）与之前n-1个皇后是否处于同一列

            //Math.abs(n - i) == Math.abs(array[n] - array[i]) 判断当前皇后（即第n个皇后）
            // 与之前n-1个皇后是否处于同一条斜线，棋盘为正方形，当横纵坐标之差的绝对值相等即斜率
            // 为 1 / -1 时两皇后在同一条斜线上

            //没有必要判断是否在同一行
            if (array[n] == array[i] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }


        return true;
    }


    //输出每一种成功的结果
    public void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println();
    }
}
