package nil.ed.sample.algorithm.Q00001;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
["200.00","201.15","1015","200001010200"]
["贰百元整", "贰百零壹元壹角伍分", "壹千零壹十伍元整", "贰千亿零壹百零壹万零贰百元整"]
 */
public class Q00001_0 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] nums = Arrays.stream(input.substring(1,input.length()-1).split("\"|,\""))
                .filter(str -> !str.isEmpty())
                .toArray(String[]::new);

        System.out.println(trans(nums));
    }

    private static String trans(String[] nums){
        return Arrays.stream(nums)
                .map(num -> num.split("\\."))
                .map(num -> {
                    StringBuilder r = processNum(num);
                    r.insert(0, "\"");
                    r.append("\"");
                    return r;
                })
                .collect(Collectors.joining("," , "[", "]"));
    }

    private static StringBuilder processNum(String[] num){
        StringBuilder builder;
        builder = processInteger(num[0]);
        StringBuilder afterDot = null;
        if (num.length > 1){
            afterDot = processAfterDot(num[1]);
        }

        builder.append("元");
        if (num.length == 1 || afterDot.length() == 0){
            builder.append("整");
        }else if (afterDot.length() > 0){
            builder.append(afterDot);
        }
        return builder;
    }

    private static String[] numMapper = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    private static String[] unitMapper = {"","十","百","千","万"};
    private static StringBuilder processInteger(String num){
        StringBuilder builder = new StringBuilder();
        if (num.length() < 6){
            builder.append(blockCalculate(num, 0, num.length()));
        }else if (num.length() < 9){
            int wEnd = num.length() - 4;
            builder.append(blockCalculate(num, 0, wEnd));
            builder.append("万");
            StringBuilder restStr = blockCalculate(num, wEnd, num.length());
            if (restStr.length() != 0 && num.charAt(wEnd) == '0'){
                builder.append("零");
            }
            builder.append(restStr);
        }else {
            int yEnd = num.length() - 8;
            builder.append(blockCalculate(num, 0, yEnd));
            builder.append("亿");
            int wEnd = num.length() - 4;
            StringBuilder wStr = blockCalculate(num, yEnd, wEnd);
            if (wStr.length() != 0 && num.charAt(yEnd) == '0'){
                builder.append("零");
            }
            builder.append(wStr);
            if (wStr.length() != 0) {
                builder.append("万");
            }
            StringBuilder restStr = blockCalculate(num, wEnd, num.length());
            if (builder.charAt(builder.length() - 1) != '零' && restStr.length() != 0 && num.charAt(wEnd) == '0'){
                builder.append("零");
            }
            builder.append(restStr);
        }

        return builder;
    }

    private static StringBuilder blockCalculate(String num, int start, int end){
        StringBuilder builder = new StringBuilder();
        int i=start;
        while (i < end && num.charAt(i) == '0'){
            ++i;
        }
        for (;i < end;){
            boolean conZero = false;
            while (i < end && num.charAt(i) == '0'){
                ++i;
                conZero = true;
            }

            if (i == end){
                break;
            }
            if (conZero) {
                builder.append(numMapper[0]);
                continue;
            }
            int realDigit=num.charAt(i) - '0';
            builder.append(numMapper[realDigit]);
            builder.append(unitMapper[end - i - 1]);
            i++;
        }

        return builder;
    }

    private static StringBuilder processAfterDot(String num){
        StringBuilder builder = new StringBuilder();
        char num0 = num.charAt(0),
                num1 = num.charAt(1);
        if (num0 == '0' && num1 == '0'){
            return builder;
        }

        builder.append(numMapper[num0 - '0']);
        if (num0 != '0'){
            builder.append("角");
        }

        if (num1 == '0'){
            return builder;
        }

        builder.append(numMapper[num1 - '0']);
        builder.append("分");
        return builder;
    }

}
