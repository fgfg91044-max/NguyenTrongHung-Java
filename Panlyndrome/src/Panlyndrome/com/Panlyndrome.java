package Panlyndrome.com;

import java.util.Scanner;

public class Panlyndrome {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
            System.out.print("Nhập chuỗi cần kiểm tra: ");
            String input = sc.nextLine();
            
            if (kiemTraPanlyndrome(input)) {
                System.out.println("=> '" + input + "' là chuỗi đối xứng (True)");
            } else {
                System.out.println("=> '" + input + "' KHÔNG phải chuỗi đối xứng (False)");
            }
            
        } catch (Exception ex) {
            System.out.println("Có lỗi xảy ra rồi thím ơi!");
            ex.printStackTrace();
        } finally {
            System.out.println("finally");
            sc.close();
        }
    }

    /**
     * Phương thức thực hiện logic so sánh i với n-1-i
     */
    public static boolean kiemTraPanlyndrome(String s) {
        int n = s.length();
        // i chạy từ 0 đến n/2 (giống hệt hình minh họa của thím)
        for (int i = 0; i < n / 2; i++) {
            // So sánh M[i] với M[n-1-i]
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false; // Chỉ cần 1 cặp khác nhau là loại luôn
            }
        }
        return true; // Chạy hết mà không sai cặp nào thì là đối xứng
    }
}