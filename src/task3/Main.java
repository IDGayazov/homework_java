package task3;

// Задача 1 *
/*
* Дана матрица mxn найти размер самого большого острова
* */

// Задача 2 *
/*
* Поиск медианы графа - вершина - сумма расстояний
* до других вершин которого минимальна
* */


// Задача 3 *
/*
* Даны координаты точек на плоскости в порядке
* их обхода по часовой стрелке найти площадь многоугольника
* */

// Задача 4 *
/*
* Найти все целочисленные корни уравнения степени n
* */

import java.util.Arrays;

public class Main {

    // Задача 1
    public static int traverseIsland(int[][] arr, int i, int j){
        final int n = arr.length;
        final int m = arr[0].length;
        if(i < 0 || j < 0 || i >=n || j >= m || arr[i][j] != 1){
            return 0;
        }else{
            arr[i][j] = 2;
            return 1 + traverseIsland(arr, i, j + 1) + traverseIsland(arr, i + 1, j)
                    + traverseIsland(arr, i - 1, j) + traverseIsland(arr, i, j - 1);
        }
    }

    public static int largestIsland(int[][] arr){
        final int n = arr.length;
        final int m = arr[0].length;
        int max = 0;
        for(int i = 0; i < n; ++i) {
            for (int j = 0; j < m; ++j) {
                if (arr[i][j] == 1) {
                    max = Math.max(max, traverseIsland(arr, i, j));
                }
            }
        }
        return max;
    }

    // Задача 2
    public static int getMedianOfGraph(int[][] mat){
        final int n = mat.length;
        for(int i = 0; i < n; ++i){
            for(int j = 0; j < n; ++j){
                for(int k = 0; k < n; ++k){
                    if(mat[i][k] == Integer.MAX_VALUE || mat[k][j] == Integer.MAX_VALUE){
                        continue;
                    }
                    if(mat[i][j] > mat[i][k] + mat[k][j]){
                        mat[i][j] = mat[i][k] + mat[k][j];
                    }
                }
            }
        }
        int min = Integer.MAX_VALUE;
        int res = -1;
        for(int i = 0; i < n; ++i){
            int sum = 0;
            for(int j = 0; j < n; ++j){
                if(mat[i][j] == Integer.MAX_VALUE){sum = Integer.MAX_VALUE; break;}
                sum += mat[i][j];
            }
            if(min > sum){
                min = sum;
                res = i;
            }
        }
        return res;
    }

    // Задача 3
    public static double square(int[][] x){
        final int n = x.length;
        double sum = 0;
        for(int i = 0; i < n - 1; ++i){
            sum += x[i][0]*x[i + 1][1];
        }
        sum += x[n - 1][0]*x[0][1];
        for(int i = 0; i < n - 1; ++i){
            sum -= x[i][1]*x[i + 1][0];
        }
        sum -= x[n - 1][1]*x[0][0];
        return Math.abs(sum) / 2;
    }


    // Задача 4
    public static boolean isRoot(int[] p, int x){
        int sum = 0;
        int t = 1;
        for(int i = 0; i < p.length; ++i) {
            sum += p[i] * t;
            t *= x;
        }
        return sum == 0;
    }

    public static int[] integralRoots(int[] p){
        int a = p[0] >= 0 ?p[0]:(-p[0]);
        int n = p.length;
        int[] res = new int[n];
        int j = 1;
        for(int i = 1; i <= a; ++i){
            if(a % i == 0){
                if(isRoot(p, i)){
                    res[0]++;
                    res[j++] = i;
                }
                if(isRoot(p, -i)){
                    res[0]++;
                    res[j++] = -i;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {

        // Задание 1
        int[][] arr = {
                {0, 1, 0, 1, 0},
                {1, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {1, 0, 1, 0, 1},
                {0, 0, 1, 1, 1},
                {0, 0, 0, 1, 1}
        };
        System.out.println(largestIsland(arr));

        // Задание 2
        int[][] mat = {
                {0, 2, Integer.MAX_VALUE, 5, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, 0, 4, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 0, 1, Integer.MAX_VALUE, 7},
                {-3, 4, 6, 0, 5, Integer.MAX_VALUE},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, 8, Integer.MAX_VALUE, 0, -1},
                {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
        };
        System.out.println(getMedianOfGraph(mat));

        // Задание 3
        int[][] x = { {5, 7}, {4, -3}, {-1, -4}, {-4, 2}, {1, 1}};
        System.out.println("square = " + square(x));

        // Задание 4
        int[] p = {48, 2, -9, 1};
        System.out.println(Arrays.toString(integralRoots(p)));
    }
}
