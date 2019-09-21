package nil.ed.sample.algorithm.core.fibHeap;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 斐波那契堆
 */
public class FibHeap {
    private boolean debug = true;
    private Node min;
    private int n;

    /**
     * 往斐波那契堆插入一个数据
     * @param key 结点key
     * @return 插入后的结点对象
     */
    public Node insert(int key){
        Node node = new Node(key);
        insertNode(node, true);
        return node;
    }

    /**
     * 合并两个斐波那契堆
     * @param heap2 另一个斐波那契堆
     */
    public void unionHeap(FibHeap heap2){
        if (heap2 == null || heap2.min == null){
            return;
        }

        if (min == null){
            this.min = heap2.min;
            this.n = heap2.n;
            return;
        }

        Node thisHead = this.min, thisTail = thisHead.left;
        Node thatHead = heap2.min, thatTail = thatHead.left;


        linkAfter(thisTail, thatHead);
        linkAfter(thatTail, thisHead);
    }

    public Node extractMin(){
        Node minNode = min;
        if (min != null) {

            processChildNodeWhenExtract(min);
            /*
            删除min
             */
            removeMin();

            if (minNode.right != minNode) {
                /*
                合并根结点
                 */
                consolidate();
            }
        }
        this.n--;

        return minNode;
    }

    public void deleteNode(Node node){
        decreaseKey(node, null);
        extractMin();
    }

    private void insertNode(Node node, boolean increase){
        if (Objects.isNull(min)){
            node.setLeft(node);
            node.setRight(node);
            min = node;
        }

        linkAfter(min, node);
        min = min.key > node.key ? node : min;
        if (increase){
            this.n++;
        }
    }

    private void decreaseKey(Node node, Integer key){
        if (min == null){
            throw new IllegalStateException("Fib-Heap is Empty");
        }

        if (compareKey(node.key, key) == 0){
            return;
        }

        if (compareKey(node.key, key) < 0){
            throw new IllegalArgumentException("key cannot be greater than previous");
        }

        if (node == null){
            throw new IllegalArgumentException("node cannot be null");
        }

        cut(node);
        cutCascade(node);
        printRoot(min);
        node.setKey(key);

        if (compareKey(min.key, key) > 0){
            this.min = node;
        }
    }

    private void cut(Node node){
        if (node == this.min){
            return;
        }
        remove(node);
        insertNode(node, false);
    }

    private void cutCascade(Node node){
        Node parentNode = node.parent;
        if (parentNode == null){
            return;
        }

        if (parentNode.mark){
            cut(parentNode);
            cutCascade(parentNode);
        } else {
            parentNode.setMark(true);
        }
    }

    private boolean isNegativeInfinite(Integer key){
        return key == null;
    }

    private int compareKey(Integer a, Integer b){
        if (isNegativeInfinite(a) && isNegativeInfinite(b)){
            return 0;
        }

        if (isNegativeInfinite(a)){
            return -1;
        }

        if (isNegativeInfinite(b)){
            return 1;
        }

        return a - b;
    }

    private void processChildNodeWhenExtract(Node node){
        Node childHead = node.down, cursor = childHead, rightNode;
        /*
        处理子结点
         */
        if (childHead != null) {
            do {
                rightNode = cursor.right;
                linkAfter(node, cursor);
                cursor.setParent(null);
                cursor = rightNode;
            } while (cursor != childHead);
        }
    }

    /**
     * 删除min
     */
    private void removeMin(){
        if (min == min.right){
            min = null;
            return;
        }
        relink(min.left, min.right);
    }

    /**
     * 合并根结点的树
     */
    private void consolidate(){
        Node[] bucket = new Node[this.n];

        /*
        合并根结点
         */
        /*
        为什么不从开始head进行遍历？
        我们在这里把head作为循环链表尾的锚，如果从这里开始，需要额外一个变量进行标识head.left
         */
        Node head = this.min.right, cur = head.right, less, greater, old/* 用于遍历循环链表 */;
        do{
            int curDegree = cur.degree;
            less = cur;
            old = cur;
            cur = cur.right;

            while((greater = bucket[curDegree]) != null){
                if (bucket[curDegree].key < less.key){
                    greater = less;
                    less = bucket[curDegree];
                }
                try {
                    remove(greater);
                    addChild(less, greater);
                    bucket[curDegree++] = null;
                }catch (IllegalArgumentException e){
                    System.out.println(curDegree);
                    throw e;
                }
            }
            bucket[curDegree] = less;
        }while(old != head);


        /*
        重新扫描最小结点
         */
        /*
        min = traverseForMinNode(head);
        如果head结点是需要移动到作为其他结点的子结点
        这时候，其左右结点已经发生变化
         */
        min = Arrays.stream(bucket).filter(Objects::nonNull).min(Comparator.comparingInt(Node::getKey)).get();
    }

    private void linkAfter(Node cur, Node newNode){
        /*
            <---
        cur      right
            --->
            <---        <---
        cur     newNode     right
            --->        --->
         */
        newNode.setRight(cur.getRight());
        newNode.getRight().setLeft(newNode);
        cur.setRight(newNode);
        newNode.setLeft(cur);
    }

    private Node traverseForMinNode(Node head){
        Node cur = head.right, minNode = head;
        do{
            if (cur.key < minNode.key){
                minNode = cur;
            }

            cur = cur.right;
        }while (cur != head);

        return minNode;
    }

    private void remove(Node node){
        if (node.right != node){
            relink(node.left, node.right);
        }

        Node parentNode = node.parent;
        /*
        无父结点，说明在根节点列表中
         */
        if (parentNode == null){
            return;
        }

        /*
        如果存在父亲结点
        删除的结点是入口结点，需要更改down域
         */
        if (parentNode.down == node){
            /*
            对于删除后孩子链表为空，直接将down设置为null
             */
            if (node.right == node){
                parentNode.down = null;
                return;
            }

            parentNode.down = node.right;
        }
        parentNode.setDegree(parentNode.degree - 1);
    }

    private void relink(Node left, Node right){
        left.setRight(right);
        right.setLeft(left);
    }

    private void addChild(Node parent, Node node){
        if (parent.down == null){
            linkSelf(node);
            parent.down = node;
        }else{
            linkAfter(parent.down, node);
        }

        node.setParent(parent);
        parent.setDegree(parent.degree + 1);
    }

    private void linkSelf(Node node){
        node.setLeft(node);
        node.setRight(node);
    }

    private void printRoot(Node head){
        if (debug) {
            System.out.println("------------- debug start ----------------");
            Node cursor = head;
            System.out.println("大小:" + n);
            if (head == null){
                return;
            }
            do {
                System.out.print(cursor + "==>");
                cursor = cursor.right;
            } while (cursor != head && cursor != cursor.right);
            System.out.println();
            System.out.println("-------------- debug end -----------------");
        }
    }

    private void printBucket(Node[] bucket){
        System.out.println("#####################bucket start");
        System.out.println(Arrays.stream(bucket)
                .filter(Objects::nonNull)
                .map(Objects::toString)
                .collect(Collectors.joining(",","[","]")));
        System.out.println("#####################bucket end");

    }

    public static void main(String[] args) {
        FibHeap heap = new FibHeap();
        Random random = new Random();
        List<Node> nodeList = new LinkedList<>();
        for (int i = 0;i < 20; ++i){
            Node node = heap.insert(i);

            if (i % 5 == 0){
                nodeList.add(node);
            }
        }

        for (Node n : nodeList){
            System.out.println("delete --- " + n);
            heap.deleteNode(n);
        }

        for (int i = 0;i < 20; ++i){
            System.out.println();
            System.out.println("extract result: **** = "+heap.extractMin());
            System.out.println();
        }
    }

    public static class Node{
        private Integer key;
        private Integer value;
        private Integer degree;
        private boolean mark;
        private Node left, right, parent, down;

        public Node(Integer key) {
            this.key = key;
            this.degree = 0;
        }

        public int getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public int getDegree() {
            return degree;
        }

        public void setDegree(Integer degree) {
            this.degree = degree;
        }

        public boolean isMark() {
            return mark;
        }

        public void setMark(boolean mark) {
            this.mark = mark;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            return super.toString()+"{key=" + key
                    + ",degree=" + degree+"}";
        }

        public String getString(){
            return toString() + "["
                    +  "\n\tparent="+parent
                    + ",\n\tleft="+left
                    +",\n\tright="+right
                    +"\n]";
        }
    }
}
