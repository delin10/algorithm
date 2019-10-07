package nil.ed.sample.algorithm.core.graph;

import java.util.Scanner;

/**
 * @author delin10
 */
public class ZOJ_TempterOfTheBone {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        while (scanner.hasNext() && oneCall()){};
    }

    static boolean oneCall(){
        int n, m, t;
        n = scanner.nextInt();
        if (!scanner.hasNext()){
            return false;
        }
        m = scanner.nextInt();
        if (!scanner.hasNext()){
            return false;
        }
        t = scanner.nextInt();
        if (n == 0 && m == 0 && t == 0){
            return false;
        }

        /*
        消耗换行符
         */
        scanner.nextLine();
        char[][] mazes = new char[n][m];
        String input;

        int dogI = 0, dogJ = 0, wallCount = 0,doorI = 0, doorJ = 0;

        for (int i = 0; i < n; ++i){
            input = scanner.nextLine();
            for (int j =0; j < m; ++j){
                mazes[i][j] = input.charAt(j);
                if (mazes[i][j] == 'S'){
                    dogI = i;
                    dogJ = j;
                }

                if (mazes[i][j] == 'D'){
                    doorI = i;
                    doorJ = j;
                }

                if (mazes[i][j] == 'X'){
                    wallCount++;
                }
            }
        }

        /*
        因为不能回访已经访问过的格子，
        所以空的格数必须大于或者等于t
         */
        if (n*m - wallCount < t){
            System.out.println("NO");
            return true;
        }

        int minStep = Math.abs(dogI - doorI) + Math.abs(dogJ - doorJ);

        /*
        最短距离 -- 曼哈顿距离：
        如果最短距离都比t大，那么无论怎么走都不可能到达目的地
         */
        if (minStep > t){
            System.out.println("NO");
            return true;
        }

        /*
        奇偶剪枝
         */
        if (((t - minStep) & 1) == 1){
            System.out.println("NO");
            return true;
        }
        /*
        正常深搜
         */
        if (dfs(mazes, dogI, dogJ, t, 0)){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
        return true;
    }

    /*
    表示方向
     */
    static int[][] direction = {{-1,0},{1,0},{0,1},{0,-1}};
    static boolean dfs(char[][] mazes, int i, int j, int t, int count){
        /*
        这里需要考虑剪枝：
        1、如果深度达到了t，但是还是没到门口，继续深搜都是不符合条件的
        2、如果到了门口，但是t不满足条件，需要直接回溯
         */
        if (count == t && mazes[i][j] == 'D'){
            return true;
        }else if (count == t || mazes[i][j] == 'D'){
            return false;
        }

        /*
        设置为墙壁防止二次访问
         */
        mazes[i][j] = 'X';
        for (int k = 0; k < direction.length; ++k){
            int nxtI = i + direction[k][0], nxtJ = j + direction[k][1];
            if (nxtI < 0 || nxtJ < 0 || nxtI >= mazes.length || nxtJ >= mazes[0].length){
                continue;
            }
            if (mazes[nxtI][nxtJ] != 'X') {
                if (dfs(mazes, nxtI, nxtJ, t, count + 1)) {
                    return true;
                }
            }
        }
        /*
        如果到了这里，肯定该点是不符合条件的，需要进行现场恢复，并回溯
         */
        mazes[i][j] = '.';
        return false;
    }

    static boolean isDoor(char ch){
        return ch == 'D';
    }
}
