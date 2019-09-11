package nil.ed.sample.algorithm.core.finder;

public interface IFinder {
    /**
     * 搜索
     * @param arr 被搜索的数组
     * @param key 被搜索的关键字
     * @return 如果搜索无结果，返回-1；否则返回关键字出现的第一个下标
     */
    int find(int[] arr, int key);
}
