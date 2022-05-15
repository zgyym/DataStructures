public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组
        int[][] chessArr = new int[11][11];
        //1表示黑子，2表示蓝子，0表示没有棋子
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[3][4] = 1;
        printChessArray(chessArr);
        System.out.println("===========================================");
        int[][] sparseArray = getSparseArray(chessArr);
        printSparseArray(sparseArray);
        System.out.println("===========================================");
        int[][] chessArray = getChessArray(sparseArray);
        printChessArray(chessArray);

    }
    //输出二维数组
    public static void printChessArray(int[][] chessArr){
        for(int[] row : chessArr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }
    //将二维数组转换成稀疏数组
    public static int[][] getSparseArray(int[][] chessArr){
        //1、先遍历二维数组
        int sum = 0;//sum用来记录每一个有效的数据(非0)
        int row = 0,num = 0;//row用来记录行数，num记录循环次数,列数就等于 num/rows
        for (int i = 0; i < chessArr.length; i++) {
            row++;
            for (int j = 0; j < chessArr[i].length; j++) {
                num++;
                if(chessArr[i][j] != 0){
                    sum++;
                }
            }
        }
        //2、创建稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = row;
        sparseArr[0][1] = num/row;
        sparseArr[0][2] = sum;
        //3、将数据存入稀疏数组，遍历二维数组，将非0的值存放到 sparseArr中
        int count = 0;//count用来记录是第几个非0的数据
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j] != 0){
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }
        return sparseArr;
    }

    //输出稀疏数组
    public static void printSparseArray(int[][] sparseArr){
        for(int[] row : sparseArr){
            for(int data : row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
    }

    //将稀疏数组转化为原始的二维数组
    public static int[][] getChessArray(int[][] sparseArr){
        //获取稀疏数组的第一行数据，并根据第一行数据创建原始二维数组
        int[][] chessArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        //将稀疏数组第二行以后的数据存入原始二维数组中
        //循环从第二行开始
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return chessArr;
    }
}
