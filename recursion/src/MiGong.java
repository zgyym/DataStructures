public class MiGong {
    public static void main(String[] args) {
        int[][] map = getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "    ");
            }
            System.out.println();
        }
        System.out.println("============================");
        setWay(map,1,1);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "    ");
            }
            System.out.println();
        }
    }


    public static int[][] getMap() {
        int[][] map = new int[8][7];
        //用1表示墙
        //将四周的墙围上
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //添加挡板
        map[3][1] = 1;
        map[3][2] = 1;

        return map;
    }



    /*
    思路：
        以下的setWay方法，起始位置为map[1][1],开始map[1][1]为0。先向下走，map[2][1]也为0。
        继续向下map[3][1]为1，setWay(map, 3, 1)为false，setWay(map, 2, 2)为true，向右走，以此类推，直到到达终点

        向周围走用if(setWay(map,i,j))来判断，在修改地图的值的同时进行判断能否往这个方向走
    */

    /**
     * @param map 地图
     * @param i   小球开始出发的行
     * @param j   小球开始出发的列
     * @return    小球在map[i][j]处是否可以走通，该点为1的时候表示碰到了墙，为2的时候表示已经走过了，为3的时候表示该处走不通，为0的时候小球没有走过
     *            下右上左有一个为0时可以继续走，返回ture，否则为false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {     //表示终点处已经走过了，不需要继续走了
            return true;
        }
        if (map[i][j] == 0) {     //如果该点没有走过
            map[i][j] = 2;       //假设该点可以走通，对该点的上下左右进行判断
            //按照策略 下->右->上->左  走
            if(setWay(map, i+1, j)) {//向下走
                return true;
            } else if (setWay(map, i, j+1)) { //向右走
                return true;
            } else if (setWay(map, i-1, j)) { //向上
                return true;
            } else if (setWay(map, i, j-1)){ // 向左走
                return true;
            } else {    //说明该点上下左右走不通，是死路
                map[i][j] = 3;
                return false;
            }
        }else{
            //map[i][j] 等于 1，2，3时
            return false;
        }
    }
}









