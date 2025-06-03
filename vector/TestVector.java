package hus.oop.vector;
import java.util.Random;

public class TestVector {
    private MyVector vector;

    public TestVector(MyVector vector) {
        this.vector = vector;
    }

    public static void main(String[] args) {
        System.out.println("=== Test MyArrayVector ===");
        new TestVector(null).testArrayVector();

        System.out.println("\n=== Test MyListVector ===");
        new TestVector(null).testListVector();
    }
    

    public void testArrayVector() {
        Random rand = new Random();
        int n = rand.nextInt(10) + 5; // Tạo số tự nhiên từ 5 đến 14
        MyArrayVector vec1 = new MyArrayVector();
        for (int i = 0; i < n; i++) {
            vec1.insert(rand.nextDouble() * 10); // Thêm phần tử ngẫu nhiên 0–10
        }

        System.out.println("Vector ban đầu:");
        System.out.println(vec1);

        // Thêm phần tử
        vec1.insert(9.99);
        System.out.println("Sau khi thêm phần tử 9.99:");
        System.out.println(vec1);

        // Xoá phần tử
        vec1.remove(0);
        System.out.println("Sau khi xoá phần tử ở index 0:");
        System.out.println(vec1);

        // Sửa phần tử
        vec1.set(8.88, 1);
        System.out.println("Sau khi sửa phần tử ở index 1 thành 8.88:");
        System.out.println(vec1);

        // Nhân vô hướng
        vec1.scale(2.0);
        System.out.println("Sau khi nhân vector với 2.0:");
        System.out.println(vec1);

        // Tính chuẩn (norm)
        System.out.println("Chuẩn (norm) của vector:");
        System.out.println(vec1.norm());

        // Cộng 2 vector
        MyArrayVector vec2 = new MyArrayVector();
        for (int i = 0; i < n; i++) {
            vec2.insert(rand.nextDouble() * 5);
        }
        System.out.println("Vector thứ hai:");
        System.out.println(vec2);

        vec1.add(vec2);
        System.out.println("Sau khi cộng vec1 + vec2:");
        System.out.println(vec1);

        // Tính tích vô hướng
        double dot = vec1.dot(vec2);
        System.out.println("Tích vô hướng vec1 . vec2:");
        System.out.println(dot);
    }


    public void testListVector() {
        Random rand = new Random();
        int n = rand.nextInt(10) + 5;
        MyListVector vec1 = new MyListVector();
        for (int i = 0; i < n; i++) {
            vec1.insert(rand.nextDouble() * 10);
        }

        System.out.println("Vector ban đầu:");
        System.out.println(vec1);

        vec1.insert(7.77);
        System.out.println("Sau khi thêm phần tử 7.77:");
        System.out.println(vec1);

        vec1.remove(1);
        System.out.println("Sau khi xoá phần tử ở index 1:");
        System.out.println(vec1);

        vec1.set(3.33, 2);
        System.out.println("Sau khi sửa phần tử ở index 2 thành 3.33:");
        System.out.println(vec1);

        vec1.scale(1.5);
        System.out.println("Sau khi nhân vector với 1.5:");
        System.out.println(vec1);

        System.out.println("Chuẩn (norm) của vector:");
        System.out.println(vec1.norm());

        MyListVector vec2 = new MyListVector();
        for (int i = 0; i < n; i++) {
            vec2.insert(rand.nextDouble() * 5);
        }
        System.out.println("Vector thứ hai:");
        System.out.println(vec2);

        vec1.add(vec2);
        System.out.println("Sau khi cộng vec1 + vec2:");
        System.out.println(vec1);

        double dot = vec1.dot(vec2);
        System.out.println("Tích vô hướng vec1 . vec2:");
        System.out.println(dot);
    }

}
