package baitap1.com;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class baitap1 {

    // Hàm kiểm tra số nguyên tố
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // 1. Nhập vào một mảng ngẫu nhiên N số
        System.out.print("Nhập số lượng phần tử N: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(100); // Tạo số ngẫu nhiên từ 0 đến 99
        }

        // 2. Xuất toàn bộ mảng
        System.out.println("Mảng vừa tạo: " + Arrays.toString(arr));

        // 3. Tính tổng mảng
        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        System.out.println("Tổng các phần tử trong mảng: " + sum);

        // 4. Với K là 1 số nhập từ bàn phím, hỏi K xuất hiện bao nhiêu lần
        System.out.print("Nhập số K cần tìm: ");
        int k = scanner.nextInt();
        int countK = 0;
        for (int num : arr) {
            if (num == k) countK++;
        }
        System.out.println("Số " + k + " xuất hiện " + countK + " lần trong mảng.");

        // 5 & 6. Tìm phần tử lớn nhất và nhỏ nhất
        int max = arr[0];
        int min = arr[0];
        for (int num : arr) {
            if (num > max) max = num;
            if (num < min) min = num;
        }
        System.out.println("Phần tử lớn nhất: " + max);
        System.out.println("Phần tử nhỏ nhất: " + min);

        // 7. Xuất các số nguyên tố
        System.out.print("Các số nguyên tố trong mảng: ");
        boolean hasPrime = false;
        for (int num : arr) {
            if (isPrime(num)) {
                System.out.print(num + " ");
                hasPrime = true;
            }
        }
        if (!hasPrime) System.out.print("Không có");
        System.out.println();

        // 8. Sắp xếp mảng tăng dần
        int[] arrAsc = arr.clone(); // Clone để không ảnh hưởng mảng gốc
        Arrays.sort(arrAsc);
        System.out.println("Mảng sắp xếp tăng dần: " + Arrays.toString(arrAsc));

        // 9. Sắp xếp mảng giảm dần
        int[] arrDesc = arr.clone();
        Arrays.sort(arrDesc);
        // Đảo ngược mảng đã sắp xếp tăng dần để thành giảm dần
        for (int i = 0; i < arrDesc.length / 2; i++) {
            int temp = arrDesc[i];
            arrDesc[i] = arrDesc[arrDesc.length - 1 - i];
            arrDesc[arrDesc.length - 1 - i] = temp;
        }
        System.out.println("Mảng sắp xếp giảm dần: " + Arrays.toString(arrDesc));

        scanner.close();
    }
}