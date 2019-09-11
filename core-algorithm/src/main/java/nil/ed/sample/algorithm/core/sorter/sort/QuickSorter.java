package nil.ed.sample.algorithm.core.sorter.sort;

import nil.ed.sample.algorithm.core.sorter.Sorter;

public class QuickSorter implements Sorter {
    @Override
    public void sort(int[] arr, int start, int end) {
        quickSort(arr, start, end - 1);
    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    public static void quickSort(int[] arr, int start, int end){
        if (start >= end){
            return;
        }
        int i = start, j = end;
        int key = arr[start];
        while(true){
            while(i < j && arr[i] <= key){
                ++i;
            }

            if (i == j){
                break;
            }

            while(i < j && arr[j] > key){
                --j;
            }

            if (i == j){
                break;
            }

            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        if (arr[i]<=key){
            arr[start] = arr[i];
            arr[i] = key;
        }else{
            arr[start] = arr[i-1];
            arr[i-1] = key;
        }
        quickSort(arr, start, i-1);
        quickSort(arr, i, end);
    }
}
