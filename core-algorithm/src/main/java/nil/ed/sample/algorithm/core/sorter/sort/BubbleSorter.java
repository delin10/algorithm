package nil.ed.sample.algorithm.core.sorter.sort;

import nil.ed.sample.algorithm.core.sorter.Sorter;
import nil.ed.sample.algorithm.core.util.ArrayHelper;

/**
 * 冒泡排序
 *
 * @author lidelin
 * @date 2019/08/16 19:24
 */
public class BubbleSorter implements Sorter {
    @Override
    public void sort(int[] arr, int start, int end) {
        checkArgument(start, end);

        for (int i = start; i < end; ++i) {
            boolean processed = false;
            for (int j = i + 1; j < end; ++j) {
                if (arr[i] > arr[j]) {
                    ArrayHelper.swap(arr, i, j);
                    processed = true;
                }
            }
            /**
             * 如果为false，说明内层循环未处理，数组已然有序
             */
            if (!processed) {
                break;
            }
        }
    }

    @Override
    public void sort(int[] arr) {
        sort(arr, 0, arr.length);
    }
}
