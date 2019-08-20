package nil.ed.sample.algorithm.core.sorter;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author lidelin
 * @date 2019/08/16 20:55
 */
public class RandomDataSource implements DataSource {
    private static final int length = 10000;
    private static final int min = 0;
    private static final int mid = 1;

    @Override
    public int[] getData() {
        Random random = new Random();
        return IntStream.generate(random::nextInt).limit(length).toArray();
    }
}
