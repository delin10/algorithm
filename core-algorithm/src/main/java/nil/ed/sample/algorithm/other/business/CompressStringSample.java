package nil.ed.sample.algorithm.other.business;

/*
通过键盘输入一串小写字母(a~z)组成的字符串。
请编写一个字符串压缩程序， 将字符串中连续出席的重复字母进行压缩，并输出压缩后的字符串。
压缩规则： 1、仅压缩连续重复出现的字符。
2、压缩字段的格式为"字符+字符重复的次数"。
比如： cccddecc 压缩完后为 c3d2ec2 adef 压缩完后为 adef pppppppp 压缩后 p8 abbccffffeeee 压缩后为 ab2c2f4e4 实现 func compress(str string) string 方法

 */
public class CompressStringSample {
    public static String func(String str){
        if (str.isEmpty() || str.length() == 1){
            return new String(str);
        }
        StringBuilder result = new StringBuilder(str.length());
        int counter = 1;
        char prev, cur;
        prev = cur = str.charAt(0);
        for (int i = 1;i<str.length();++i){
            cur = str.charAt(i);
            if (cur == prev){
                counter++;
            }else{
                result.append(prev);
                result.append(counter == 1 ? "" : String.valueOf(counter));
                counter = 1;
                prev = cur;
            }
        }

        result.append(prev);
        result.append(counter == 1 ? "" : String.valueOf(counter));

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(func("abbccffffeeee"));
    }
}
