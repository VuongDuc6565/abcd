package hus.oop.fraction;

public class MyArrayDataSet implements MyDataSet {
    private static int DEFAULT_CAPACITY = 16;
    private MyFraction[] fractions;
    private int length;

    public MyArrayDataSet() {
        this.fractions = new MyFraction[DEFAULT_CAPACITY];
        this.length = 0;
    }

    public MyArrayDataSet(MyFraction[] fractions) {
        this.fractions = new MyFraction[fractions.length];
        this.length = fractions.length;
        for (int i = 0; i < fractions.length; i++) {
            this.fractions[i] = new MyFraction(fractions[i]);
        }
    }

    private void allocateMore() {
        MyFraction[] newFractions = new MyFraction[fractions.length * 2];
        System.arraycopy(fractions, 0, newFractions, 0, fractions.length);
        fractions = newFractions;
    }

    private boolean checkBoundaries(int index, int upperBound) {
        return index >= 0 && index <= upperBound;
    }

    @Override
    public boolean insert(MyFraction fraction, int index) {
        if (!checkBoundaries(index, length)) return false;
        if (length == fractions.length) {
            allocateMore();
        }
        System.arraycopy(fractions, index, fractions, index + 1, length - index);
        fractions[index] = new MyFraction(fraction);
        length++;
        return true;
    }

    @Override
    public boolean append(MyFraction fraction) {
        return insert(fraction, length);
    }

    @Override
    public MyArrayDataSet toSimplify() {
        MyFraction[] simplified = new MyFraction[length];
        for (int i = 0; i < length; i++) {
            MyFraction f = new MyFraction(fractions[i]);
            f.simplify();
            simplified[i] = f;
        }
        return new MyArrayDataSet(simplified);
    }

    @Override
    public MyArrayDataSet sortIncreasing() {
        MyFraction[] sorted = new MyFraction[length];
        System.arraycopy(fractions, 0, sorted, 0, length);
        java.util.Arrays.sort(sorted, (a, b) -> {
            int cmp = a.compareTo(b);
            return cmp != 0 ? cmp : Integer.compare(a.getDenominator(), b.getDenominator());
        });
        return new MyArrayDataSet(sorted);
    }

    @Override
    public MyArrayDataSet sortDecreasing() {
        MyFraction[] sorted = new MyFraction[length];
        System.arraycopy(fractions, 0, sorted, 0, length);
        java.util.Arrays.sort(sorted, (a, b) -> {
            int cmp = -a.compareTo(b);
            return cmp != 0 ? cmp : -Integer.compare(a.getDenominator(), b.getDenominator());
        });
        return new MyArrayDataSet(sorted);
    }

    @Override
    public String myDataSetToString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < length; i++) {
            sb.append(fractions[i]);
            if (i != length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public void print() {
        System.out.println(myDataSetToString());
    }
}
