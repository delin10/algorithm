package nil.ed.sample.algorithm.core.highaccuracy;

/**
 * 大整数四则运算
 */
public class SuperInteger {
    private char[] value;

    public SuperInteger(char[] value) {
        checkValue(value);
        this.value = value;
    }

    public SuperInteger(String intStr){
        this(intStr.toCharArray());
    }

    public SuperInteger(StringBuilder intStr){
        /*
        为什么不用SuperInteger(char[])
        因为this(...)和super(...)必须放在函数开头
         */
        char[] tmp = new char[intStr.length()];
        intStr.getChars(0, intStr.length(), tmp, 0);
        checkValue(tmp);
        this.value = tmp;
    }

    public SuperInteger(int value) {
        /*
        不需要检查，本身为数字转换
         */
        this.value = String.valueOf(value).toCharArray();
    }

    public SuperInteger add(SuperInteger other){
        char[] a = this.value;
        char[] b = other.value;

        int aLen = a.length;
        int bLen = b.length;

        int minLen = Math.min(aLen,bLen);
        int maxLen = aLen + bLen - minLen;

        StringBuilder result = new StringBuilder(maxLen + 1);
        /*
        存储进位
         */
        int rest = 0;
        for (int i = 1; i <= minLen; ++i){
            int r = (a[aLen - i] - '0') + (b[bLen - i] - '0') + rest;
            rest = r / 10;
            result.insert(0, (char)(r % 10 + '0'));
        }

        /*
        位数较多的需要额外处理，比如
         100001
         +    1
         ------
         100002
         */
        char[] maxLenArr = aLen > bLen ? a : b;
        for (int i = minLen + 1;i <= maxLen; ++i){
            int r = (maxLenArr[maxLen - i] - '0') + rest;
            rest = r / 10;
            result.insert(0, (char)(r % 10 + '0'));
        }

        /*
        处理最后的进位,比如
         99999999
         +      9
         ---------
        100000008
         */
        if (rest != 0){
            result.insert(0, '1');
        }

        return new SuperInteger(result);
    }

    public SuperInteger multi(SuperInteger other){
        return null;

    }

    public SuperInteger sub(SuperInteger other){
        return null;
    }

    public SuperInteger divide(SuperInteger other){
        return null;
    }

    @Override
    public String toString() {
        return new String(value);
    }

    private void checkValue(char[] value){
        if (value == null || value.length == 0){
            throw new IllegalArgumentException("value is null or empty");
        }

        for (char ch : value){
            if (!Character.isDigit(ch)){
                throw new IllegalArgumentException("value contains invalid character");
            }
        }
    }

    public static void main(String[] args) {
        SuperInteger a = new SuperInteger("qweq");
        SuperInteger b = new SuperInteger("1242235423");
        System.out.println(a.add(b));
    }
}
