package nil.ed.sample.algorithm.core.finder.impl;

import nil.ed.sample.algorithm.core.finder.IFinder;

/**
 * 迭代二分查找
 */
public class BinaryFinderIteratively implements IFinder {
    @Override
    public int find(int[] arr, int key) {
        /*
        空数组
         */
        if (arr.length == 0){
            return -1;
        }

        int l = 0, h =arr.length - 1, mid;

        while(true){
            mid = (l + h)/2;
            if (arr[mid] == key){
                /*
                获取第一个出现的元素的下标
                 */
                while (mid >= 0 && arr[mid] == key){
                    mid--;
                }
                return mid + 1;
            }

            if (l == h){
                return -1;
            }

            if (arr[mid] < key){
                l = mid + 1;
            } else {
                h = mid;
            }
        }
    }
}
