package nil.ed.sample.algorithm.core.util;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 数组操作工具类
 *
 * @author lidelin
 * @date 2019/08/16 19:27
 */
public class ArrayHelper {
    /**
     * 交换数组中下标为i、j的元素
     *
     * @param arr 目的数组
     * @param i   元素下标
     * @param j   元素下标
     */
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static String format(int[] arr) {
        return Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(",", "[", "]"));
    }
}
