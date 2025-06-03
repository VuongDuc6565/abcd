package hus.oop.statistics;

public class MyLinkedList extends MyAbstractList {
    private MyNode top;

    /**
     * Khởi tạo dữ liệu mặc định.
     */
    public MyLinkedList() {
        top = null;
    }

    @Override
    public int size() {
        int count = 0;
        MyNode current = top;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    @Override
    public void add(double data) {
        if (top == null) {
            top = new MyNode(data);
        } else {
            MyNode current = top;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new MyNode(data, null, current);
        }
    }

    @Override
    public void insert(double data, int index) {
        if (index < 0 || index > size()) throw new IndexOutOfBoundsException();

        if (index == 0) {
            MyNode newNode = new MyNode(data, top, null);
            if (top != null) top.previous = newNode;
            top = newNode;
        } else {
            MyNode prev = getNodeByIndex(index - 1);
            MyNode next = prev.next;
            MyNode newNode = new MyNode(data, next, prev);
            prev.next = newNode;
            if (next != null) next.previous = newNode;
        }
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size()) throw new IndexOutOfBoundsException();
        if (index == 0) {
            if (top != null) top = top.next;
            if (top != null) top.previous = null;
        } else {
            MyNode toRemove = getNodeByIndex(index);
            MyNode prev = toRemove.previous;
            MyNode next = toRemove.next;
            if (prev != null) prev.next = next;
            if (next != null) next.previous = prev;
        }
    }

    @Override
    public MyLinkedList sortIncreasing() {
        MyLinkedList sorted = new MyLinkedList();
        double[] array = new double[size()];
        MyIterator it = iterator(0);
        int i = 0;
        while (it.hasNext()) array[i++] = it.next().doubleValue();
        java.util.Arrays.sort(array);
        for (double v : array) sorted.add(v);
        return sorted;
    }

    @Override
    public int binarySearch(double data) {
        MyLinkedList sorted = sortIncreasing();
        int left = 0, right = sorted.size() - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            double midVal = sorted.getNodeByIndex(mid).data;
            if (midVal == data) return mid;
            else if (midVal < data) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    @Override
    public MyIterator iterator(int start) {
        return new MyLinkedListIterator(start);
    }

    private MyNode getNodeByIndex(int index) {
        MyNode current = top;
        for (int i = 0; i < index; i++) {
            if (current == null) throw new IndexOutOfBoundsException();
            current = current.next;
        }
        return current;
    }


    private class MyLinkedListIterator implements MyIterator {
        /*
         * Vị trí hiện tại của iterator trong list.
         */
        private int currentPosition;

        /**
         * Khởi tạo cho iterator ở vị trí position trong MyLinkedList.
         * @param position
         */
        public MyLinkedListIterator(int position) {
            this.currentPosition = position;
        }

        @Override
        public boolean hasNext() {
            return currentPosition < size();
        }

        @Override
        public Number next() {
            return getNodeByIndex(currentPosition++).data;
        }

        @Override
        public void remove() {
            MyLinkedList.this.remove(--currentPosition);
        }

    }
}
