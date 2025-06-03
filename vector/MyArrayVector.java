package hus.oop.vector;
import java.util.Arrays;

public class MyArrayVector extends MyAbstractVector {
    private static final int DEFAULT_CAPACITY = 16;
    private double[] data;
    private int size;

    /**
     * Khởi tạo mặc định cho vector.
     */
    public MyArrayVector() {
        data = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public double coordinate(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return data[index];
    }


    @Override
    public double[] coordinates() {
        return java.util.Arrays.copyOf(data, size);
    }



    @Override
    public MyArrayVector insert(double value) {
        if (size == data.length) allocateMore();
        data[size++] = value;
        return this;
    }


    @Override
    public MyArrayVector insert(double value, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (size == data.length) allocateMore();
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = value;
        size++;
        return this;
    }

    @Override
    public MyArrayVector remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
        return this;
    }

    @Override
    public MyArrayVector extract(int[] indices) {
        MyArrayVector result = new MyArrayVector();
        for (int index : indices) {
            if (index < 0 || index >= size) {
                throw new IndexOutOfBoundsException("Invalid index: " + index);
            }
            result.insert(data[index]);
        }
        return result;
    }


    @Override
    public void set(double value, int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        data[index] = value;
    }


    @Override
    public MyArrayVector add(double value) {
        for (int i = 0; i < size; i++) data[i] += value;
        return this;
    }
    @Override
    public MyArrayVector add(MyVector another) {
        if (this.size != another.size()) {
            throw new IllegalArgumentException("Vector sizes do not match.");
        }
        for (int i = 0; i < size; i++) {
            this.data[i] += another.coordinate(i);
        }
        return this;
    }



    @Override
    public MyArrayVector addTo(double value) {
        for (int i = 0; i < size; i++) {
            data[i] += value;
        }
        return this;
    }


    @Override
    public MyArrayVector addTo(MyVector another) {
        return (MyArrayVector) another.add(this);
    }

    @Override
    public MyArrayVector minus(double value) {
        return add(-value);
    }

    @Override
    public MyArrayVector minus(MyVector another) {
        if (size != another.size()) throw new IllegalArgumentException();
        for (int i = 0; i < size; i++) data[i] -= another.coordinate(i);
        return this;
    }

    @Override
    public MyArrayVector minusFrom(double value) {
        for (int i = 0; i < size; i++) {
            data[i] = value - data[i];
        }
        return this;
    }


    @Override
    public MyArrayVector minusFrom(MyVector another) {
        MyArrayVector result = new MyArrayVector();
        for (int i = 0; i < size; i++) result.insert(another.coordinate(i) - data[i]);
        return result;
    }

    @Override
    public double dot(MyVector another) {
        if (size != another.size()) throw new IllegalArgumentException();
        double result = 0;
        for (int i = 0; i < size; i++) result += data[i] * another.coordinate(i);
        return result;
    }

    @Override
    public MyArrayVector pow(double power) {
        for (int i = 0; i < size; i++) data[i] = Math.pow(data[i], power);
        return this;
    }

    @Override
    public MyArrayVector scale(double scalar) {
        for (int i = 0; i < size; i++) data[i] *= scalar;
        return this;
    }

    @Override
    public double norm() {
        double sum = 0;
        for (double v : data) sum += v * v;
        return Math.sqrt(sum);
    }

    /**
     * Mở rộng kích thước vector lên gấp đôi khi cần thiết.
     */
    private void allocateMore() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
