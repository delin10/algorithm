package nil.ed.sample.algorithm.core.tree.binary;

import nil.ed.sample.algorithm.core.util.GraphvizHelper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

/**
 * @author lidelin
 * @date 2019/08/19 11:38
 */
public class BinaryTree<T> {
    private Node<T> root;

    public void createTree(T[] data) {
        root = new Node<>(data[0]);
        Queue<Node<T>> queue = new LinkedList<>();
        Node<T> cur = null, left = null, right = null;
        int i = 1;
        queue.offer(root);

        while (true) {
            cur = queue.poll();
            if (createAndPushAndSet(i++, data, queue, cur::setLeft)
                    && createAndPushAndSet(i++, data, queue, cur::setRight)) {
                continue;
            }
            break;
        }
    }

    public void preTraverse() {
        StringBuilder builder = new StringBuilder();
        builder.append("graph tree{");
        helpTraverse(root, node -> GraphvizHelper.constructEdge(node, builder));
        builder.append("}");
        try {
            FileUtils.write(new File("D:/temp/dot/tree.dot"), builder, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 常数空间下遍历二叉树
     * 使用Deutsch-Schorr-Waite算法
     */
    public void traverseWithConstSpace() {
        Node<T> prev = null,/* 用于保存前一个结点以便进行回溯 */
                cur = root, /* 当前处理的结点 */
                next = null;/* 在交换结点时充当中间变量作用 */
        boolean forward = true;
        StringBuilder builder = new StringBuilder();
        builder.append("graph tree{");
        do {
            /**
             * 无环无需进行标记判断
             */
            while (cur != null) {
                GraphvizHelper.constructEdge(cur, builder);
                next = cur.getLeft();
                cur.setLeft(prev);
                prev = cur;
                cur = next;
            }

            /**
             * 进行回溯，
             *
             * 如果set被设置，说明该结点下的左右子结点都已经被遍历过，向上回溯
             */
            while (prev != null && prev.getSet()) {
                next = prev.getRight();
                prev.setRight(cur);
                cur = prev;
                prev = next;
            }

            if (prev == null) {
                break;
            } else {
                /**
                 * set - false 表示左子树
                 *     - true 表示右子树
                 */
                prev.setSet(true);
                next = prev.getLeft();
                prev.setLeft(cur);
                cur = prev.getRight();
                prev.setRight(next);
            }
        } while (prev != null);
        builder.append("}");
        try {
            FileUtils.write(new File("D:/temp/dot/tree2.dot"), builder, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void helpTraverse(Node<T> root, Consumer<Node<T>> consumer) {
        if (root == null) {
            return;
        }

        consumer.accept(root);
        helpTraverse(root.getLeft(), consumer);
        helpTraverse(root.getRight(), consumer);
    }

    /**
     * 创建结点，入栈，并设置左/右孩子结点
     *
     * @param i
     * @param data
     * @param queue
     * @param setLeftOrRight
     * @return
     */
    private boolean createAndPushAndSet(int i, T[] data, Queue<Node<T>> queue, Consumer<Node<T>> setLeftOrRight) {
        if (i >= data.length) {
            return false;
        }

        Node<T> node = new Node<>(data[i]);
        queue.offer(node);
        setLeftOrRight.accept(node);
        return true;
    }
}
