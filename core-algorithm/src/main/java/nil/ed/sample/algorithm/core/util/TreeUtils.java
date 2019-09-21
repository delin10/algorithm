package nil.ed.sample.algorithm.core.util;

public class TreeUtils {
    public static int parent(int i, int start){
        return start + ((i - start -1) >> 1);
    }

    public static int left(int i, int start){
        /*
        需要注意
         */
        return start + ((i - start + 1) << 1) - 1;
    }

    public static int right(int i, int start){
        return start + ((i - start + 1) << 1);
    }
}
