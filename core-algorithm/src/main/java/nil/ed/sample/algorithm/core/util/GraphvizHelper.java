package nil.ed.sample.algorithm.core.util;

import nil.ed.sample.algorithm.core.tree.binary.Node;

import java.text.MessageFormat;
import java.util.Objects;

/**
 * @author lidelin
 * @date 2019/08/19 13:59
 */
public class GraphvizHelper {
    public static void constructEdge(Node<?> node, StringBuilder builder) {
        if (Objects.nonNull(node.getLeft())) {
            builder.append(MessageFormat.format("\"{0}\" -- \"{1}\";\n", node, node.getLeft()));
        }
        if (Objects.nonNull(node.getRight())) {
            builder.append(MessageFormat.format("\"{0}\" -- \"{1}\";\n", node, node.getRight()));
        }
    }

}
