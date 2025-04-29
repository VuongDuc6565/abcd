package hus.oop.sorteddatastructure;

import java.util.Random;

public class TestSortedDataStructure {
    public static void main(String[] args) {
        System.out.println("Test SortedArrayList:");
        testSortedArrayList();

        System.out.println("\nTest SortedLinkedList:");
        testSortedLinkedList();
    }

    public static void testSortedArrayList() {
        Random rand = new Random();
        int n = rand.nextInt(11) + 20; // [20, 30]
        System.out.println("Số lượng phần tử: " + n);

        MySortedList list = new MySortedArrayList();

        for (int i = 0; i < n; i++) {
            int value = rand.nextInt(91) + 10; // [10, 100]
            list.add(value);
            System.out.println("Thêm " + value + ": " + list);
        }

        System.out.println("Danh sách cuối cùng: " + list);

        for (int i = 0; i < 5; i++) {
            int value = rand.nextInt(91) + 10;
            boolean exists = list.contains(value);
            int index = list.binarySearch(value);
            System.out.println("Giá trị kiểm tra: " + value +
                    " -> " + (exists ? ("có tại chỉ số " + index) : "không tồn tại"));
        }
    }

    public static void testSortedLinkedList() {
        Random rand = new Random();
        int n = rand.nextInt(11) + 20; // [20, 30]
        System.out.println("Số lượng phần tử: " + n);

        MySortedList list = new MySortedLinkedList();

        for (int i = 0; i < n; i++) {
            int value = rand.nextInt(91) + 10; // [10, 100]
            list.add(value);
            System.out.println("Thêm " + value + ": " + list);
        }

        System.out.println("Danh sách cuối cùng: " + list);

        for (int i = 0; i < 5; i++) {
            int value = rand.nextInt(91) + 10;
            boolean exists = list.contains(value);
            int index = list.binarySearch(value);
            System.out.println("Giá trị kiểm tra: " + value +
                    " -> " + (exists ? ("có tại chỉ số " + index) : "không tồn tại"));
        }
    }
}
