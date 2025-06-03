package hus.oop.fraction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.ArrayList;

public class TestFraction {
    private MyDataSet myDataSet;
    private static final Random random = new Random();

    public TestFraction(MyDataSet myDataSet) {
        this.myDataSet = myDataSet;
    }

    public static void main(String[] args) {
        String fileName = "NguyenTuanKiet_22000100_MyFractions.txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            writer.println("Test MyArrayDataSet");
            TestFraction testArray = new TestFraction(new MyArrayDataSet());
            testArray.testMyArrayDataSet(writer);

            writer.println("\n Test MyListDataSet");
            TestFraction testList = new TestFraction(new MyListDataSet());
            testList.testMyListDataSet(writer);

            System.out.println("Chương trình đã ghi kết quả vào file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testMyArrayDataSet(PrintWriter writer) {
        int numbers = random.nextInt(21) + 30; // từ 30 đến 50
        writer.println("Số lượng phân số: " + numbers);

        MyArrayDataSet arrayDataSet = new MyArrayDataSet();
        for (int i = 0; i < numbers; i++) {
            int numerator = random.nextInt(100) + 1;
            int denominator = random.nextInt(100) + 1;
            arrayDataSet.append(new MyFraction(numerator, denominator));
        }

        writer.println("Tập dữ liệu gốc:");
        writer.println(arrayDataSet.myDataSetToString());

        writer.println("Sắp xếp tăng dần:");
        writer.println(arrayDataSet.sortIncreasing().myDataSetToString());

        writer.println("Sắp xếp giảm dần:");
        writer.println(arrayDataSet.sortDecreasing().myDataSetToString());

        writer.println("Phân số tối giản theo thứ tự ban đầu:");
        writer.println(arrayDataSet.toSimplify().myDataSetToString());

        writer.println("Tối giản và sắp xếp tăng dần:");
        writer.println(arrayDataSet.toSimplify().sortIncreasing().myDataSetToString());

        writer.println("Tối giản và sắp xếp giảm dần:");
        writer.println(arrayDataSet.toSimplify().sortDecreasing().myDataSetToString());
    }

    public void testMyListDataSet(PrintWriter writer) {
        int numbers = random.nextInt(21) + 30; // từ 30 đến 50
        writer.println("Số lượng phân số: " + numbers);

        MyListDataSet listDataSet = new MyListDataSet();
        for (int i = 0; i < numbers; i++) {
            int numerator = random.nextInt(100) + 1;
            int denominator = random.nextInt(100) + 1;
            listDataSet.append(new MyFraction(numerator, denominator));
        }

        writer.println("Tập dữ liệu gốc:");
        writer.println(listDataSet.myDataSetToString());

        writer.println("Sắp xếp tăng dần:");
        writer.println(listDataSet.sortIncreasing().myDataSetToString());

        writer.println("Sắp xếp giảm dần:");
        writer.println(listDataSet.sortDecreasing().myDataSetToString());

        writer.println("Phân số tối giản theo thứ tự ban đầu:");
        writer.println(listDataSet.toSimplify().myDataSetToString());

        writer.println("Tối giản và sắp xếp tăng dần:");
        writer.println(listDataSet.toSimplify().sortIncreasing().myDataSetToString());

        writer.println("Tối giản và sắp xếp giảm dần:");
        writer.println(listDataSet.toSimplify().sortDecreasing().myDataSetToString());
    }
}
