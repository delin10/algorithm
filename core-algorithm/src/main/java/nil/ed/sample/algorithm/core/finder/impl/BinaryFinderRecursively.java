package nil.ed.sample.algorithm.core.finder.impl;

import nil.ed.sample.algorithm.core.finder.IFinder;

/**
 * 二分查找递归实现
 */
public class BinaryFinderRecursively implements IFinder {
    @Override
    public int find(int[] arr, int key) {
        return binarySearch(arr, 0, arr.length-1,key);
    }

    public static int binarySearch(int[] arr, int l, int h, int key){
        if (arr.length == 0){
            return -1;
        }

        int mid = (l + h)/2;
        if (arr[mid] == key){
            while (mid >= 0 && arr[mid] == key){
                mid--;
            }
            return mid + 1;
        }

        if (l >= h){
            return -1;
        }

        if (arr[mid] < key){
            return binarySearch(arr, mid+1, h, key);
        }else{
            return binarySearch(arr, l, mid-1, key);
        }
    }
}
