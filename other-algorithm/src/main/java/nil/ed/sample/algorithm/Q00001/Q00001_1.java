package nil.ed.sample.algorithm.Q00001;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 暴力破解
 */
public class Q00001_1 {
    public static void main(String...args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        Arrays.stream(input.substring(1,input.length()-1).split("\"|,\""))
            .filter(str -> !str.isEmpty())
            .map(str -> str.split("\\."))
            .forEach(num -> {

            });

    }
}
