package hus.oop.statistics;

import java.util.Random;
import java.util.Scanner;

public class TestStatistics {
    private Statistics statistics;

    public TestStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public static void main(String[] args) {
        TestStatistics tester = new TestStatistics(null);

        System.out.println("Test với MyArrayList");
        tester.testMyArrayList();

        System.out.println("\n Test với MyLinkedList");
        tester.testMyLinkedList();
    }

    public void testMyArrayList() {
        Random rand = new Random();
        int length = rand.nextInt(21) + 30; // [30, 50]
        MyArrayList list = new MyArrayList();

        for (int i = 0; i < length; i++) {
            double val = 1 + rand.nextDouble() * 19; // [1, 20]
            list.add(val);
        }

        this.statistics = new Statistics(list);

        System.out.println("Dữ liệu ban đầu (MyArrayList):");
        System.out.println(list);

        System.out.println("Dữ liệu sau khi sắp xếp tăng dần:");
        System.out.println(list.sortIncreasing());

        System.out.printf("Giá trị lớn nhất: %.2f%n", statistics.max());
        System.out.printf("Giá trị nhỏ nhất: %.2f%n", statistics.min());
        System.out.printf("Kỳ vọng (mean): %.2f%n", statistics.mean());
        System.out.printf("Phương sai (variance): %.2f%n", statistics.variance());

        double[] ranks = statistics.rank();
        System.out.println("Thứ hạng (rank) của các phần tử:");
        for (double r : ranks) {
            System.out.printf("%.0f ", r);
        }
        System.out.println();

        double searchVal = list.iterator(0).next().doubleValue(); // Tìm phần tử đầu tiên
        int pos = statistics.search(searchVal);
        System.out.printf("Tìm kiếm giá trị %.2f → vị trí: %d%n", searchVal, pos);
    }

    public void testMyLinkedList() {
        Random rand = new Random();
        int length = rand.nextInt(21) + 30; // [30, 50]
        MyLinkedList list = new MyLinkedList();

        for (int i = 0; i < length; i++) {
            double val = 1 + rand.nextDouble() * 19; // [1, 20]
            list.add(val);
        }

        this.statistics = new Statistics(list);

        System.out.println("Dữ liệu ban đầu (MyLinkedList):");
        System.out.println(list);

        System.out.println("Dữ liệu sau khi sắp xếp tăng dần:");
        System.out.println(list.sortIncreasing());

        System.out.printf("Giá trị lớn nhất: %.2f%n", statistics.max());
        System.out.printf("Giá trị nhỏ nhất: %.2f%n", statistics.min());
        System.out.printf("Kỳ vọng (mean): %.2f%n", statistics.mean());
        System.out.printf("Phương sai (variance): %.2f%n", statistics.variance());

        double[] ranks = statistics.rank();
        System.out.println("Thứ hạng (rank) của các phần tử:");
        for (double r : ranks) {
            System.out.printf("%.0f ", r);
        }
        System.out.println();

        double searchVal = list.iterator(0).next().doubleValue(); // Tìm phần tử đầu tiên
        int pos = statistics.search(searchVal);
        System.out.printf("Tìm kiếm giá trị %.2f → vị trí: %d%n", searchVal, pos);
    }
}
