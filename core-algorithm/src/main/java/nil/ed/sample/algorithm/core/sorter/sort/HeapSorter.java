package nil.ed.sample.algorithm.core.sorter.sort;

import nil.ed.sample.algorithm.core.sorter.Sorter;
import nil.ed.sample.algorithm.core.util.ArrayHelper;

public class HeapSorter implements Sorter {
    @Override
    public void sort(int[] arr, int start, int end) {
        buildMaxHeap(arr, start, end);

        for (int i = end - 1; i > start; --i){
            ArrayHelper.swap(arr, start, i);
            keepMaxHeap(arr, start, start, i);
        }
    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }

    public void buildMaxHeap(int[] arr, int start, int end){
        for (int i = (end + start) / 2 - 1; i >= start; --i){
            keepMaxHeap(arr, i, start, end);
        }
    }

    public void keepMaxHeap(int[] arr, int i, int start, int end){
        int l = left(i, start), r = right(i, start), largest = i;

        if (l < end && arr[l] > arr[largest]){
            largest = l;
        }

        if (r < end && arr[r] > arr[largest]){
            largest = r;
        }

        if (largest != i){
            ArrayHelper.swap(arr, largest, i);
            keepMaxHeap(arr, largest, start, end);
        }
    }

    private int left(int i, int start){
        /*
        需要注意
         */
        return start + ((i - start + 1) << 1) - 1;
    }

    private int right(int i, int start){
        return start + ((i - start + 1) << 1);
    }
}
