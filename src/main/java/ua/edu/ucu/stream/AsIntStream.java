package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import java.util.ArrayList;
import java.util.List;

public class AsIntStream implements IntStream {
    private List<Integer> asIntStreamArr;

    private AsIntStream(int... arr){
        this.asIntStreamArr = new ArrayList<>();
        for (int i : arr) {
            this.asIntStreamArr.add(i);
        }
    }

    public AsIntStream(List<Integer> lst) {
        this.asIntStreamArr = lst;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        if (count() == 0) throw new IllegalArgumentException("Empty Stream!");
        return (double) sum() / count();
    }

    @Override
    public Integer max() {
        if (count() == 0) {
            throw new IllegalArgumentException("Empty Stream!");
        }
        int max = asIntStreamArr.get(0);
        for (int i : asIntStreamArr) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

    @Override
    public Integer min() {
        if (count() == 0) {
            throw new IllegalArgumentException("Empty Stream!");
        }
        int min = asIntStreamArr.get(0);
        for (int i : asIntStreamArr) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    @Override
    public long count() {
        return asIntStreamArr.size();
    }

    @Override
    public Integer sum() {
        if (count() == 0) {
            throw new IllegalArgumentException("Empty Stream!");
        }
        int sum = 0;
        for (int i: asIntStreamArr) {
            sum += i;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream filteredStreamArr = new AsIntStream();
        for (int i : asIntStreamArr) {
            if (predicate.test(i)) filteredStreamArr.asIntStreamArr.add(i);
        }
        return filteredStreamArr;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i : asIntStreamArr) {
            action.accept(i);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        AsIntStream mappedStreamArr = new AsIntStream();
        for (int i : asIntStreamArr) {
            mappedStreamArr.asIntStreamArr.add(mapper.apply(i));
        }
        return mappedStreamArr;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        List<Integer> intArray = new ArrayList<>();
        List<IntStream> streamArr = new ArrayList<>();
        for (int i : asIntStreamArr) {
            streamArr.add(func.applyAsIntStream(i));
        }
        for (IntStream stream : streamArr) {
            for (int i : stream.toArray()) {
                intArray.add(i);
            }
        }
        return new AsIntStream(intArray);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (int i : asIntStreamArr) {
            identity = op.apply(identity, i);
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] streamToArr = new int[asIntStreamArr.size()];
        for (int i = 0;i < asIntStreamArr.size(); i++) {
            streamToArr[i] = asIntStreamArr.get(i);
        }
        return streamToArr;
    }

}
