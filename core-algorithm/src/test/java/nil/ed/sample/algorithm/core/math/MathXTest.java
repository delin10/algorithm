package nil.ed.sample.algorithm.core.math;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

public class MathXTest {
    long basic = 2;
    long exponent = 1000000;
    static long fastPow;
    static long normalPow;
    static long streamPow;
    static long jdkPow;
    @Test
    public void fastPow() {
        fastPow = MathX.fastPow(basic, exponent);
    }

    @Test
    public void normalPow() {
        normalPow = MathX.normalPow(basic, exponent);
    }

    @Test
    public void streamPow() {
        streamPow = MathX.streamPow(basic, exponent);
    }

    @Test
    public void jdkPow(){
        jdkPow = (long)Math.pow(basic, exponent);
    }

    @AfterClass
    public static void endResult(){
        System.out.printf("fastPow = %d, normalPow = %d, streamPow = %d, jdkPow = %d",
                fastPow, normalPow, streamPow, jdkPow);
    }
}
