package Baitoan.com; // 1. Khai báo package nằm ở dòng đầu tiên

import java.util.Scanner; // 2. Lệnh import nằm ngoài class

public class Baitoan1 { // 3. Tên class khớp với tên file

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Nhập các hệ số a, b, c
        System.out.print("Nhập a: ");
        double a = sc.nextDouble();
        System.out.print("Nhập b: ");
        double b = sc.nextDouble();
        System.out.print("Nhập c: ");
        double c = sc.nextDouble();

        // Kiểm tra a = 0
        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("PTVSN (Phương trình vô số nghiệm)");
                } else {
                    System.out.println("PTVN (Phương trình vô nghiệm)");
                }
            } else {
                double x = -c / b;
                System.out.println("Phương trình bậc nhất x = " + x);
            }
        } else {
            // Tính delta và biện luận
            double delta = b * b - 4 * a * c;

            if (delta < 0) {
                System.out.println("PTVN (Phương trình vô nghiệm)");
            } else if (delta == 0) {
                double x = -b / (2 * a);
                System.out.println("Phương trình có nghiệm kép x = " + x);
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("Phương trình có 2 nghiệm phân biệt:");
                System.out.println("x1 = " + x1);
                System.out.println("x2 = " + x2);
            }
        }
        sc.close();
    }
}