package nil.ed.sample.algorithm.core.random;

import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.IntStream;

/**
 * 自定义百分比概率生成器
 * @param <R> 获取一个值
 */
public class GodRandomBox<R> {
    private TreeMap<Integer, Function<Integer,R>> box;

    public GodRandomBox() {
        this.box = new TreeMap<>();
        box.put(0, i -> null);
    }

    public GodRandomBox<R> addBorder(Integer border, Function<Integer, R> supplier){
        if (border < 0 || border > 100){
            throw new IllegalArgumentException("error param");
        }

        box.put(border, supplier);

        return this;
    }

    public R randomGet(){
        Random random = new Random();
        final int randomInt = random.nextInt(100);
        Map.Entry<Integer, Function<Integer,R>> old = null, cur = null;
        for (Map.Entry<Integer, Function<Integer,R>> entry : box.entrySet()){
            if (old == null){
                old = entry;
            }

            cur = entry;

            if (cur.getKey() > randomInt){
                break;
            }
            old = cur;
        }
        return Optional.ofNullable(old.getValue()).orElse(i -> null).apply(randomInt);
    }

    public static void main(String[] args) {
        GodRandomBox<Integer> godRandomBox = new GodRandomBox<>();
        godRandomBox.addBorder(10, i -> 10)
                .addBorder(2, i -> 2)
                .addBorder(0, i -> 0);

        IntStream.range(0, 100).forEach(i -> {
            System.out.println(godRandomBox.randomGet());
        });
    }
}
