package nil.ed.sample.algorithm.core.math;

import java.util.stream.LongStream;

/**
 * 关于数学优化的算法
 *
 * @author delin10
 * @since 2019/10/06
 * @version 0.0.1
 */
public class MathX {
    /**
     * 快速幂
     *
     * 时间复杂度：O(lgn)
     * @param basic 底数
     * @param exponent 指数
     * @return 求幂结果
     */
    public static long fastPow(long basic, long exponent){
        long result = 1, exponentTemp = exponent, basicTemp = basic;

        while(exponentTemp != 0){
            if ((exponentTemp & 1) == 1){
                result*=basicTemp;
            }
            basicTemp *=basicTemp;
            exponentTemp = exponentTemp >> 1;
        }
        return result;
    }

    public static long normalPow(long basic, long exponent){
        long result = 1L;
        for (int i = 0; i < exponent; ++i){
            result *= basic;
        }
        return result;
    }

    public static long streamPow(long basic, long exponent){
        return LongStream.generate(() -> basic)
                .limit(exponent)
                .reduce(1, (acc, ba) -> acc * ba);
    }

    public static void main(String[] args) {
        long basic = 2L, exponent = 31L;
        System.out.printf("%d, %d", fastPow(basic, exponent), (long)Math.pow(basic, exponent));
    }
}
