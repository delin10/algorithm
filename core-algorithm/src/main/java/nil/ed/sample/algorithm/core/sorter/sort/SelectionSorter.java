package nil.ed.sample.algorithm.core.sorter.sort;

import nil.ed.sample.algorithm.core.sorter.Sorter;
import nil.ed.sample.algorithm.core.util.ArrayHelper;

public class SelectionSorter implements Sorter {
    @Override
    public void sort(int[] arr, int start, int end) {
        checkArgument(start, end);
        for (int i = start; i < end - 1; ++i){
            int minIndex = i;
            int j = i + 1;
            for (; j < end; ++j){
                if (arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            ArrayHelper.swap(arr, minIndex, i);
        }
    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }
}
