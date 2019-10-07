package nil.ed.sample.algorithm.core.sorter;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ReserveSequenceDataSource implements DataSource {
    @Override
    public int[] getData() {
        int[] arr = new int[20];
        IntStream.range(0, 20).forEach(i -> arr[19 - i] = i);
        return  arr;
    }
}
