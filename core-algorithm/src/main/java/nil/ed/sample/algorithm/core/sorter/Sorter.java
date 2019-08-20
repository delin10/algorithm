package nil.ed.sample.algorithm.core.sorter;

/**
 * @author lidelin
 * @date 2019/08/16 19:23
 */
public interface Sorter {
    /**
     * 排序
     *
     * @param arr   需要排序的数组
     * @param start 开始索引
     * @param end   结束索引
     */
    void sort(int[] arr, int start, int end);

    /**
     * 需要排序的数组
     *
     * @param arr
     */
    void sort(int[] arr);

    /**
     * 检查参数
     *
     * @param start 数组开始下标
     * @param end   数组结束下标
     */
    default void checkArgument(int start, int end) {
        if (start > end) {
            throw new IllegalArgumentException("start > end");
        }

        if (start == end) {
            return;
        }
    }
}
