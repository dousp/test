package com.dou.test;

import java.util.Arrays;

public class SortTest {

    public static void main(String[] args) {

        int[] numbers = {3, 4, 1, 5, 0, 6, 9, 8, 7, 2,};

        // bubbleSort(numbers);
        // selectSort(numbers);
        // insertSort(numbers);
        // shellSort(numbers);

        // for (int i : shellSort2(numbers)) {
        //     System.out.println(i);
        // }

        // for (int i : quickSort(numbers, 0, numbers.length-1)) {
        //     System.out.println(i);
        // }

    }

    public static int[] bubbleSort(int[] numbers) {
        int size = numbers.length;
        int temp;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 1; j < size; j++) {
                if (numbers[i] > numbers[j]) {
                    temp = numbers[i];
                    numbers[i] = numbers[j];
                    numbers[j] = temp;
                }
            }
        }
        return numbers;
    }

    public static int[] selectSort(int[] numbers) {
        int size = numbers.length;
        int temp;
        for (int i = 0; i < size - 1; i++) {
            int k = i;
            for (int j = size - 1; j > i; j--) {
                if (numbers[k] > numbers[j]) {
                    k = j;
                }
            }
            temp = numbers[i];
            numbers[i] = numbers[k];
            numbers[k] = temp;
        }
        return numbers;
    }

    public static int[] insertSort(int[] a) {
        int length = a.length;
        int temp = 0;
        for (int i = 1; i < length; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                temp = a[j];
                a[j] = a[j - 1];
                a[j - 1] = temp;
            }
        }
        return a;
    }

    public static int[] shellSort(int[] a) {
        int length = a.length;
        int temp;
        for (int increment = length / 2; increment > 0; increment = increment / 2) {
            for (int i = increment; i < length; i++) {
                for (int j = i - increment; j >= 0; j -= increment) {
                    if (a[j] > a[ j + increment ]) {
                        temp = a[ j + increment ];
                        a[ j + increment ] = a[ j ];
                        a[ j ] = temp;
                    }else{
                        break;
                    }
                }
            }
        }
        return a;
    }

    public static int[] sort(int[] data) {
        int j = 0;
        int temp = 0;
        for (int increment = data.length / 2; increment > 0; increment /= 2) {
            System.out.println("increment: " + increment);
            for (int i = increment; i < data.length; i++) {
                temp = data[i];

                for (j = i - increment; j >= 0; j -= increment) {
                    if (data[j] > temp) {
                        data[j + increment] = data[j];
                    } else {
                        break;
                    }
                }
                data[j + increment] = temp;
                System.out.println(Arrays.toString(data) + " : " + i);
            }

        }
        return data;
    }

    public static int[] sort2(int[] arr) {
        // i表示希尔排序中的第n/2+1个元素（或者n/4+1）
        // j表示希尔排序中从0到n/2的元素（n/4）
        // r表示希尔排序中n/2+1或者n/4+1的值
        int i, j, r, tmp;
        // 划组排序
        for (r = arr.length / 2; r >= 1; r = r / 2) {
            for (i = r; i < arr.length; i++) {
                tmp = arr[i];
                j = i - r;
                // 一轮排序
                while (j >= 0 && tmp < arr[j]) {
                    arr[j + r] = arr[j];
                    j -= r;
                }
                arr[j + r] = tmp;
            }
            System.out.println(i + ":" + Arrays.toString(arr));
        }
        return arr;
    }

    public static int[] shellSort2(int[] arr){
        if(arr.length<2){
            return arr;
        }
        int temp, k, len = arr.length;
        for (int gap = len/2; gap >=1 ; gap = gap / 2) {
            for (int h = gap; h < len; h++) {
                temp = arr[h];
                for (k = h - gap; k >=0 ; k = k-gap) {
                    if( arr[k] > temp ){
                        arr[k+gap] = arr[k];
                    }else {
                        break;
                    }
                }
                arr[k+gap] = temp;
            }
        }
        return arr;
    }

    public static int[] quickSort(int[] arr, int low,int high){

        if(low>high){
            return arr;
        }

        int t;
        int m=low, n=high;
        int temp = arr[low];

        while (m < n){
            //先看右边，依次往左递减
            while (temp<=arr[n]&&m<n) {
                n--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[m]&&m<n) {
                m++;
            }

            if(m<n){
                t = arr[m];
                arr[m] = arr[n];
                arr[n] = t;
            }
        }
        //最后将基准为与m和n相等位置的数字交换
        arr[low] = arr[m];
        arr[m] = temp;

        arr = quickSort(arr, low, n-1);
        arr = quickSort(arr, n+1, high);

        return arr;
    }



}
