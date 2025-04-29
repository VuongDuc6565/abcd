package hus.oop.sorteddatastructure;

public class MySortedLinkedList extends MySortedAbstractList {
    private Node head;
    private int size;

    /**
     * Hàm dựng khởi tạo list để chứa dữ liệu.
     */
    public MySortedLinkedList() {
        /* TODO */
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        /* TODO */
        return size;
    }

    @Override
    public void clear() {
        /* TODO */
        head = null;
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
        return getNodeByIndex(index).data;
    }

    /**
     * Thêm phần phần tử vào danh sách.
     * @param value giá trị của phần tử dữ liệu được thêm vào.
     */
    @Override
    public void add(int value) {
        /* TODO */
        Node newNode = new Node(value);
        if (head == null || head.data >= value) {
            newNode.next = head;
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null && current.next.data < value) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    /**
     * Xóa phần tử dữ liệu tại vị trí index.
     * Chỉ xóa được nếu index nằm trong đoạn [0 - (size() - 1)]
     * @param index
     */
    @Override
    public void remove(int index) {
        /* TODO */
        checkBound(index);
        if (index == 0) {
            head = head.next;
        } else {
            Node prev = getNodeByIndex(index - 1);
            prev.next = prev.next.next;
        }
        size--;
    }

    @Override
    public int binarySearch(int value) {
        /* TODO */
        Node current = head;
        int index = 0;
        while (current != null) {
            if (current.data == value) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public boolean contains(int value) {
        /* TODO */
        return binarySearch(value) >= 0;
    }

    /**
     * Phương thức lấy Node ở vị trí index.
     * @param index
     * @return
     */
    private Node getNodeByIndex(int index) {
        /* TODO */
        checkBound(index);
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * Lấy ra dữ liệu được lưu theo cấu trúc dữ liệu kiểu mảng.
     * @return
     */
    @Override
    public int[] toArray() {
        /* TODO */
        int[] array = new int[size];
        Node current = head;
        for (int i = 0; i < size; i++) {
            array[i] = current.data;
            current = current.next;
        }
        return array;
    }
    private void checkBound(int index) {
        if (!checkBoundaries(index, size - 1)) {
            throw new IndexOutOfBoundsException("Index " + index + " out of bounds for size " + size);
        }
    }

}
