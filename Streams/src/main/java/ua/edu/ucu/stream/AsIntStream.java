package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;
import java.util.ArrayList;
import java.util.List;

public class AsIntStream implements IntStream {
    private ArrayList<Integer> array;

    private AsIntStream(){
        this.array = new ArrayList<>();
    }

    public static IntStream of(int... values) {
        AsIntStream intStream = new AsIntStream();
        for (int i : values) {
            intStream.array.add(i);
        }
        return intStream;
    }

    @Override
    public Double average() {
        return (double) sum() / count();
    }

    @Override
    public Integer max() {
        if (count() == 0) {
            throw new IllegalArgumentException("Empty Stream!");
        }
        int max = array.get(0);
        for (int i : array) {
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
        int min = array.get(0);
        for (int i : array) {
            if (i < min) {
                min = i;
            }
        }
        return min;
    }

    @Override
    public long count() {
        long streamLength;
        streamLength = array.size();
        return streamLength;
    }

    @Override
    public Integer sum() {
        if (count() == 0) {
            throw new IllegalArgumentException("Empty Stream!");
        }
        int sum = 0;
        for (int i: array) {
            sum += i;
        }
        return sum;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        AsIntStream filteredStreamArr = new AsIntStream();
        for (int i : array) {
            if (predicate.test(i)) filteredStreamArr.array.add(i);
        }
        return filteredStreamArr;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int i : array) {
            action.accept(i);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        AsIntStream mappedStreamArr = new AsIntStream();
        for (int i : array) {
            mappedStreamArr.array.add(mapper.apply(i));
        }
        return mappedStreamArr;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        AsIntStream flatMappedArr = new AsIntStream();
        List<IntStream> lst = new ArrayList<>();
        for (int i : array) {
            lst.add(func.applyAsIntStream(i));
        }
        for (IntStream stream : lst) {
            flatMappedArr.of(stream.toArray());
        }
        return flatMappedArr;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (int i : array) {
            identity = op.apply(identity, i);
        }
        return identity;
    }

    @Override
    public int[] toArray() {
        int[] streamToArr = new int[array.size()];
        for (int i : array) {
            streamToArr[i] = array.get(i);
        }
        return streamToArr;
    }

}
