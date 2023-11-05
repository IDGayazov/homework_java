package task1;

import java.util.Arrays;

public class Main {

    // Задание 1 - медиана массива
    public static int median(int[] arr){
        final int n = arr.length;
        int min = Integer.MAX_VALUE;
        int res = 0;
        for(int i = 0; i < n; ++i){
            int countLeft = 0;
            int countRight = 0;
            for(int j = 0; j < n; ++j){
                if(i == j) continue;
                if(arr[i] <= arr[j]){
                    countRight++;
                }else{
                    countLeft++;
                }
            }
            if(Math.abs(countLeft - countRight) < min){
                min = Math.abs(countLeft - countRight);
                res = arr[i];
            }
        }
        return res;
    }

    // Задание 2 - позиция элемента в отсортированном массиве
    public static int[] posElementInSortedArray(int[] arr){
        final int n = arr.length;
        int[] res = new int[n];
        for(int i = 0; i < n; ++i){
            int count = 0;
            for(int j = 0; j < n; ++j){
                if(arr[i] > arr[j]){
                    ++count;
                }
            }
            res[i] = count;
        }
        for(int i = 0; i < n; ++i){
            for(int j = i + 1; j < n; ++j){
                if(res[i] == res[j]){
                    res[j]++;
                }
            }
        }
        return res;
    }

    // Задание 3 - сортировка
    // слияние двух отсортированных подмассивов
    public static void merge(int[] a, int p, int q, int r){
        final int n = q - p + 1;
        final int m = r - q;
        int[] arr1 = new int[n + 1];
        for(int i = 0; i < n; ++i){
            arr1[i] = a[p + i];
        }
        arr1[n] = Integer.MAX_VALUE;
        int[] arr2 = new int[m + 1];
        for(int j = 0; j < m; ++j){
            arr2[j] = a[q + j + 1];
        }
        arr2[m] = Integer.MAX_VALUE;
        int i = 0, j = 0, k = p;
        while(i < n || j < m) {
            if (arr1[i] <= arr2[j]) {
                a[k] = arr1[i];
                i++;
            } else {
                a[k] = arr2[j];
                j++;
            }
            k++;
        }
    }
    // сортировка слиянием
    public static void mergeSort(int[] a, int p, int r){
        if(p < r){
            int q = (p + r) / 2;
            mergeSort(a, p, q);
            mergeSort(a, q + 1, r);
            merge(a, p, q, r);
        }
    }

    // Задание 4 - отсутствие циклов в последовательности станции
    public static boolean isCorrect(int[] arr){
        final int n = arr.length;
        for(int i = 0; i < n; ++i){
            for(int j = i + 1; j < n; ++j){
                if(arr[i] == arr[j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 7, 1, 9, 0, 2, 3, 1, 12, 15, 11};
        System.out.println(median(arr));
        System.out.println(Arrays.toString(posElementInSortedArray(arr)));
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(isCorrect(arr));

    }
}
