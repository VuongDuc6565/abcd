package hus.oop.fraction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyListDataSet implements MyDataSet {
    private List<MyFraction> fractions;

    public MyListDataSet() {
        this.fractions = new ArrayList<>();
    }

    public MyListDataSet(List<MyFraction> fractions) {
        this.fractions = new ArrayList<>();
        for (MyFraction f : fractions) {
            this.fractions.add(new MyFraction(f));
        }
    }

    @Override
    public boolean insert(MyFraction fraction, int index) {
        if (index < 0 || index > fractions.size()) return false;
        fractions.add(index, new MyFraction(fraction));
        return true;
    }

    @Override
    public boolean append(MyFraction fraction) {
        fractions.add(new MyFraction(fraction));
        return true;
    }

    @Override
    public MyListDataSet toSimplify() {
        List<MyFraction> simplified = new ArrayList<>();
        for (MyFraction f : fractions) {
            MyFraction copy = new MyFraction(f);
            copy.simplify();
            simplified.add(copy);
        }
        return new MyListDataSet(simplified);
    }

    @Override
    public MyListDataSet sortIncreasing() {
        List<MyFraction> sorted = new ArrayList<>(fractions);
        sorted.sort((a, b) -> {
            int cmp = a.compareTo(b);
            return cmp != 0 ? cmp : Integer.compare(a.getDenominator(), b.getDenominator());
        });
        return new MyListDataSet(sorted);
    }

    @Override
    public MyListDataSet sortDecreasing() {
        List<MyFraction> sorted = new ArrayList<>(fractions);
        sorted.sort((a, b) -> {
            int cmp = -a.compareTo(b);
            return cmp != 0 ? cmp : -Integer.compare(a.getDenominator(), b.getDenominator());
        });
        return new MyListDataSet(sorted);
    }

    @Override
    public String myDataSetToString() {
        return fractions.toString();
    }

    @Override
    public void print() {
        System.out.println(myDataSetToString());
    }
}
