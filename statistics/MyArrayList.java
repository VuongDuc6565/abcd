package hus.oop.statistics;

public class MyArrayList extends MyAbstractList {
    private static final int DEFAULT_CAPACITY = 16;
    private double[] data;
    private int size;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyArrayList() {
        data = new double[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(double data) {
        if (size == this.data.length) allocateMore();
        this.data[size++] = data;
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (size == this.data.length) allocateMore();
        for (int i = size; i > index; i--) this.data[i] = this.data[i - 1];
        this.data[index] = data;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        for (int i = index; i < size - 1; i++) data[i] = data[i + 1];
        size--;
    }

    @Override
    public MyArrayList sortIncreasing() {
        MyArrayList sorted = new MyArrayList();
        for (int i = 0; i < size; i++) sorted.add(data[i]);
        java.util.Arrays.sort(sorted.data, 0, size);
        return sorted;
    }

    @Override
    public int binarySearch(double value) {
        MyArrayList sorted = sortIncreasing();
        int left = 0, right = sorted.size - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sorted.data[mid] == value) return mid;
            else if (sorted.data[mid] < value) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    @Override
    public MyIterator iterator(int start) {
        return new MyArrayListIterator(start);
    }

    private void allocateMore() {
        double[] newData = new double[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }


    private class MyArrayListIterator implements MyIterator {
        /**
         * Vị trí hiện tại của iterator trong MyArrayList.
         */
        private int currentPosition;

        /**
         * Khởi tạo dữ liệu cho iterator tại vị trí position của list.
         */
        public MyArrayListIterator(int position) {
            this.currentPosition = position;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size;
        }

        @Override
        public Number next() {
            return data[currentPosition++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--currentPosition);
        }

    }
}
