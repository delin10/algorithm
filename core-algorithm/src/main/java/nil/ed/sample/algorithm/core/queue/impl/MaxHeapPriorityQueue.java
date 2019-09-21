package nil.ed.sample.algorithm.core.queue.impl;

import nil.ed.sample.algorithm.core.queue.IPriorityQueue;
import nil.ed.sample.algorithm.core.util.ArrayHelper;
import nil.ed.sample.algorithm.core.util.TreeUtils;

import java.util.ArrayList;
import java.util.Random;

public class MaxHeapPriorityQueue implements IPriorityQueue {
    private ArrayList<Integer> heap;

    public MaxHeapPriorityQueue() {
        this.heap = new ArrayList<>();
    }

    @Override
    public int extractMax() {
        if (heap.size() == 1){
            return heap.remove(0);
        }

        int maxValue = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));

        keepMaxHeap(0, heap.size());
        return maxValue;
    }

    @Override
    public void insert(int e) {
        heap.add(Integer.MIN_VALUE);
        increaseKey(heap.size() - 1, e);
        System.out.println(heap);
    }

    @Override
    public int maximum() {
        return heap.get(0);
    }

    @Override
    public void increaseKey(int i, int expectKey) {
        if (heap.get(i) > expectKey){
            throw new IllegalArgumentException("不能小于原键值");
        }

        heap.set(i, expectKey);
        int parent = TreeUtils.parent(i, 0);
        while(parent >= 0) {
            keepMaxHeap(parent, heap.size());
            parent = TreeUtils.parent(parent, 0);
        }
    }

    private void keepMaxHeap(int i, int end){
        int l = TreeUtils.left(i, 0), r = TreeUtils.right(i, 0), largest = i;

        if (l < end && heap.get(l) > heap.get(largest)){
            largest = l;
        }

        if (r < end && heap.get(r) > heap.get(largest)){
            largest = r;
        }

        if (largest != i){
            ArrayHelper.swap(heap, largest, i);
            keepMaxHeap(largest, end);
        }
    }

    public static void main(String[] args) {
        IPriorityQueue queue = new MaxHeapPriorityQueue();
        Random random = new Random();
        for (int i = 0; i < 100; ++i){
            queue.insert(random.nextInt(100));
        }

        for (int i = 0; i < 100; ++i){
            System.out.println(queue.extractMax());
        }

    }
}
