package nil.ed.sample.algorithm.core.sorter;

import nil.ed.sample.algorithm.core.util.ArrayHelper;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author lidelin
 * @date 2019/08/16 19:33
 */
public class SortRunnerImpl implements ISortRunner {
    private Sorter sorter;
    private DataSource dataSource;

    public SortRunnerImpl(Sorter sorter, DataSource dataSource) {
        this.sorter = sorter;
        this.dataSource = dataSource;
    }

    @Override
    public void run() {
        int[] arr = dataSource.getData();
        System.out.println(ArrayHelper.format(arr));
        sorter.sort(arr);
        System.out.println(ArrayHelper.format(arr));
    }

}
