package nil.ed.sample.algorithm.core.tree.binary;

import java.util.stream.IntStream;

/**
 * @author lidelin
 * @date 2019/08/19 13:47
 */
public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.createTree(IntStream.range(0, 100).mapToObj(Integer::new).toArray(len -> new Integer[len]));
        tree.preTraverse();
        tree.traverseWithConstSpace();
    }
}
