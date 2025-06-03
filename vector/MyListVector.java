package hus.oop.vector;

import java.util.List;
import java.util.*;
public class MyListVector extends MyAbstractVector {
    private List<Double> data;

    /**
     * Khởi tạo mặc định cho vector.
     */
    public MyListVector() {
        data = new ArrayList<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public double coordinate(int index) {
        return data.get(index);
    }

    @Override
    public double[] coordinates() {
        double[] array = new double[data.size()];
        for (int i = 0; i < data.size(); i++) array[i] = data.get(i);
        return array;
    }

    @Override
    public MyListVector insert(double value) {
        data.add(value);
        return this;
    }

    @Override
    public MyListVector insert(double value, int index) {
        data.add(index, value);
        return this;
    }

    @Override
    public MyListVector remove(int index) {
        data.remove(index);
        return this;
    }

    @Override
    public MyListVector extract(int[] indices) {
        MyListVector result = new MyListVector();
        for (int index : indices) {
            if (index < 0 || index >= data.size()) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            result.insert(data.get(index));
        }
        return result;
    }


    @Override
    public void set(double value, int index) {
        if (index < 0 || index >= data.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        data.set(index, value);
    }


    @Override
    public MyListVector add(double value) {
        for (int i = 0; i < data.size(); i++) data.set(i, data.get(i) + value);
        return this;
    }

    @Override
    public MyListVector add(MyVector another) {
        if (size() != another.size()) throw new IllegalArgumentException();
        for (int i = 0; i < size(); i++) data.set(i, data.get(i) + another.coordinate(i));
        return this;
    }

    @Override
    public MyListVector addTo(double value) {
        for (int i = 0; i < data.size(); i++) {
            data.set(i, data.get(i) + value);
        }
        return this;
    }


    @Override
    public MyListVector addTo(MyVector another) {
        return (MyListVector) another.add(this);
    }

    @Override
    public MyListVector minus(double value) {
        return add(-value);
    }

    @Override
    public MyListVector minus(MyVector another) {
        if (size() != another.size()) throw new IllegalArgumentException();
        for (int i = 0; i < size(); i++) data.set(i, data.get(i) - another.coordinate(i));
        return this;
    }

    @Override
    public MyListVector minusFrom(double value) {
        for (int i = 0; i < data.size(); i++) {
            data.set(i, value - data.get(i));
        }
        return this;
    }


    @Override
    public MyListVector minusFrom(MyVector another) {
        MyListVector result = new MyListVector();
        for (int i = 0; i < size(); i++) result.insert(another.coordinate(i) - data.get(i));
        return result;
    }

    @Override
    public double dot(MyVector another) {
        if (size() != another.size()) throw new IllegalArgumentException();
        double result = 0;
        for (int i = 0; i < size(); i++) result += data.get(i) * another.coordinate(i);
        return result;
    }

    @Override
    public MyListVector pow(double power) {
        for (int i = 0; i < data.size(); i++) data.set(i, Math.pow(data.get(i), power));
        return this;
    }

    @Override
    public MyListVector scale(double value) {
        for (int i = 0; i < data.size(); i++) data.set(i, data.get(i) * value);
        return this;
    }

    @Override
    public double norm() {
        double sum = 0;
        for (double v : data) sum += v * v;
        return Math.sqrt(sum);
    }
}
