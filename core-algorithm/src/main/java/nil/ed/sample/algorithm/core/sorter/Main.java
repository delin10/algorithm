package nil.ed.sample.algorithm.core.sorter;

import nil.ed.sample.algorithm.core.sorter.sort.BubbleSorter;

/**
 *
 * @author lidelin
 * @date 2019/08/16 19:23
 */
public class Main {
    public static void main(String[] args) {
        ISortRunner runner = new SortRunnerImpl(new BubbleSorter(), new RandomDataSource());
        runner.run();
    }
}
