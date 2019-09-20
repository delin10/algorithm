package nil.ed.sample.algorithm.Q00002;

public class Q00002 {
    public static int countBinaryOnes(int num){
        int count = 0;
        while (num != 0){
            count += num & 1;
            num = num >> 1;
        }

        return count;
    }

    public static void main(String[] args) {
        System.out.println(countBinaryOnes(255));
    }
}
