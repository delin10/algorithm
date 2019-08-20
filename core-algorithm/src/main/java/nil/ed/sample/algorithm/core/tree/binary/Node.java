package nil.ed.sample.algorithm.core.tree.binary;

/**
 * @author lidelin
 * @date 2019/08/19 11:37
 */
public class Node<T> {
    boolean set;
    private T data; // 如果该结点存在范围，以高位作为标识位
    private Node<T> left, right;

    public Node(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public Node<T> setData(T data) {
        this.data = data;
        return this;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> setLeft(Node<T> left) {
        this.left = left;
        return this;
    }

    public Node<T> getRight() {
        return right;
    }

    public Node<T> setRight(Node<T> right) {
        this.right = right;
        return this;
    }

    public boolean getSet() {
        return set;
    }

    public Node<T> setSet(boolean set) {
        this.set = set;
        return this;
    }

    @Override
    public String toString() {
        return super.hashCode() + "[" + data + "]";
    }
}
