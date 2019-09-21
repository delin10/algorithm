package nil.ed.sample.algorithm.core.queue;

public interface IPriorityQueue {
    int extractMax();

    void insert(int e);

    int maximum();

    void increaseKey(int i,int expectKey);
}
