package com.walor.javatest;

/**
 * <pre>
 *     author : walorwang
 *     time   : 2020/07/14
 *     desc   : 真题案例（一）：算法思维训练
 * </pre>
 */
class AlgoThinking {

    /**
     * 例题 1：斐波那契数列
     * 写个函数，输入 x，输出斐波那契数列中第 x 位的元素。
     * 要求出某个位置 x 的数字，需要先求出 x-1 的位置是多少和 x-2 的位置是多少。
     * 终止条件：就是起始两个元素，分别为 0 和 1。
     */
//    public static void main(String[] args) {
//        int x = 20;
//        System.out.println(fun(x));
//    }
    private static int fun(int n) {
        if (n == 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        return fun(n - 1) + fun(n - 2);
    }

    /**
     * 例题2：判断一个数组中是否存在某个数
     * 给定一个经过任意位数的旋转后的排序数组，判断某个数是否在里面。
     * 这就是一个查找问题。
     */
//    public static void main(String[] args) {
//        int[] arr = {4, 5, 6, 7, 0, 1, 2};
//        int target = 7;
//        System.out.println(bs(arr, target, 0, arr.length - 1));
//    }

    // 针对旋转有序问题，有序为其特殊情况
    private static int bs(int[] arr, int target, int begin, int end) {
        if (begin == end) {
            if (target == arr[begin]) {
                return begin;
            } else {
                return -1;
            }
        }
        // 防止begin+end溢出
        int middle = begin + (end - begin) / 2;
        if (target == arr[middle]) {
            return middle;
        }
        if (arr[begin] <= arr[middle - 1]) {
            // 有序情况
            if (arr[begin] <= target && target <= arr[middle - 1]) {
                return bs(arr, target, begin, middle - 1);
            } else {
                return bs(arr, target, middle + 1, end);
            }
        } else {
            // 旋转有序情况
            if (arr[middle + 1] <= target && target <= arr[end]) {
                return bs(arr, target, middle + 1, end);
            } else {
                return bs(arr, target, begin, middle - 1);
            }
        }
    }

    /**
     * 例题3：求解最大公共子串
     * 输入两个字符串，用动态规划的方法，求解出最大公共子串。
     * 比如sk = "123"，sk+1 = "1234"。
     */
//    public static void main(String[] args) {
//        String a = "13452439";
//        String b = "123456";
//        getCommenStr(a, b);
//    }
    public static void getCommenStr(String a, String b) {
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();
        // 行列分别都+1长度
        int[][] m = new int[c2.length + 1][c1.length + 1];
        for (int i = 1; i <= c2.length; i++) {
            for (int j = 1; j <= c1.length; j++) {
                if (c2[i - 1] == c1[j - 1])
                    m[i][j] = m[i - 1][j - 1] + 1;
            }
        }
        int max = 0;
        int index = 0;
        for (int i = 0; i <= c2.length; i++) {
            for (int j = 0; j <= c1.length; j++) {
                if (m[i][j] > max) {
                    max = m[i][j];
                    index = i;
                }
            }
        }
        String s = "";
        for (int i = index - max; i < index; i++)
            s += b.charAt(i);
        System.out.println(s);
    }

    /**
     * 动态规划：最短路径问题
     * 多阶段决策过程中的最优解
     */
//    public static void main(String[] args) {
//        // 15*16（保证列更长）
//        int[][] m = {
//                {0, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 1, 3, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 8, 7, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 6, 8, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 3, 5, 0, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 8, 4, 0, 0, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 5, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 2, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 6, 0},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4},
//                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3}
//        };
//        System.out.println(minPath(m));
//    }

    public static int minPath(int[][] matrix) {
        return recur(matrix, matrix[0].length - 1);
    }

    // 递归计算最短距离（最优子结构）
    public static int recur(int[][] matrix, int col) {
        // 到达A退出递归
        if (col == 0) {
            return 0;
        } else {
            // 状态转移sk+1 = uk(sk)
            int distance = 999;
            for (int row = 0; row < col; row++) {
                if (matrix[row][col] != 0) {
                    int temp = matrix[row][col] + recur(matrix, row);
                    if (temp < distance) {
                        distance = temp;
                    }
                }
            }
            return distance;
        }
    }
}
