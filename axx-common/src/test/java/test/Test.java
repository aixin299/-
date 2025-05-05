package test;

import java.util.Arrays;

public class Test {
    // 冒泡排序
    @org.junit.Test
    public void T1() {
        int[] arr = {6, 5, 4, 3, 2, 1, 4, 5, 12, 4, 87, 21, 3, 12, 11, 2};
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    @org.junit.Test
    public void T2() {
        int[] arr = {6, 5, 4, 3, 2, 1, 4, 5, 12, 4, 87, 21, 3, 12, 11, 2};
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @org.junit.Test
    public void T3() {
        int[] arr = {6,2,5,8,1,3};
        int[] sort = this.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(sort));
    }

    public int[] sort(int[] arr, int l, int r) {
        //先判断数组是否需要排序
        if (arr.length == 1) {
            return arr;
        }
        int pivot = arr[0];
        while (l != r) {
            //先看右边
            while (arr[r] > pivot ) {
                r--;
            }
            arr[l] = arr[r];
            ++l;
//            if (l==r){
//                break;
//            }
            //看左边
            while (arr[l] < pivot ) {
                l++;
            }
            arr[r] = arr[l];
            --r;
//            if (l==r){
//                break;
//            }
        }
        arr [l] =pivot;
        return arr;
    }


}
