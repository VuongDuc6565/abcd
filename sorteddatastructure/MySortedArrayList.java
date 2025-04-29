package hus.oop.sorteddatastructure;

public class MySortedArrayList extends MySortedAbstractList {
    private static final int DEFAULT_CAPACITY = 16;
    private int[] data;
    private int size;

    /**
     * Hàm dựng để khởi tạo dữ liệu.
     */
    public MySortedArrayList() {
        /* TODO */
        data = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Lấy kích thước của tập dữ liệu.
     * @return
     */
    @Override
    public int size() {
        /* TODO */
        return size;
    }

    @Override
    public void clear() {
        /* TODO */
        data = new int[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Lấy giá trị của phần tử ở vị trí index.
     * @param index
     * @return
     */
    @Override
    public int get(int index) {
        /* TODO */
        checkBound(index);
        return data[index];
    }

    /**
     * Thêm phần tử dữ liệu vào danh sách.
     * Nếu mảng không còn chỗ, mở rộng mảng để có thể chứa thêm dữ liệu.
     * @param value là giá trị của phần tử dữ liệu được thêm vào.
     */
    @Override
    public void add(int value) {
        /* TODO */
        if (size == data.length) {
            allocateMore();
        }
        int i = size - 1;
        while (i >= 0 && data[i] > value) {
            data[i + 1] = data[i];
            i--;
        }
        data[i + 1] = value;
        size++;
    }

    /**
     * Xóa phần tử dữ liệu tại vị trí index.
     * Chỉ xóa được nếu index nằm trong đoạn [0 - (size - 1)]
     * @param index
     */
    @Override
    public void remove(int index) {
        /* TODO */
        checkBound(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
    }

    @Override
    public int binarySearch(int value) {
        /* TODO */
        int left = 0, right = size - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (data[mid] == value) {
                return mid;
            } else if (data[mid] < value) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(int value) {
        /* TODO */
        return binarySearch(value) >= 0;
    }

    /**
     * Mở rộng gấp đôi kích thước mảng nếu hết chỗ để chứa dữ liệu.
     */
    private void allocateMore() {
        /* TODO */
        int[] newData = new int[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Lấy ra dữ liệu được lưu theo cấu trúc dữ liệu kiểu mảng.
     * @return
     */
    @Override
    public int[] toArray() {
        /* TODO */
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = data[i];
        }
        return result;
    }
    private void checkBound(int index) {
        if (!checkBoundaries(index, size - 1)) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
    }

}
