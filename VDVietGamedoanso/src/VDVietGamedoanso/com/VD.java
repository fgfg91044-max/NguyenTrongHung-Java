package VDVietGamedoanso.com;

import java.util.Random;
import java.util.Scanner;

public class VD {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean tiepTuc = true;

        System.out.println("=== CHÀO MỪNG THÍM ĐẾN VỚI GAME ĐOÁN SỐ ===");

        while (tiepTuc) {
            int soCuaMay = random.nextInt(101); // Ngẫu nhiên từ 0 đến 100
            int soLanDoan = 0;
            int phepThuToiDa = 7;
            boolean daThang = false;

            System.out.println("\nMáy đã chọn xong một số từ 0 đến 100.");
            System.out.println("Thím có tối đa " + phepThuToiDa + " lần thử. Bắt đầu nào!");

            while (soLanDoan < phepThuToiDa) {
                soLanDoan++;
                System.out.print("Lần đoán thứ " + soLanDoan + " - Nhập số của thím: ");
                
                // Kiểm tra nếu người dùng nhập không phải là số
                if (!scanner.hasNextInt()) {
                    System.out.println("Nhập số cơ mà! Mất một lượt nhé thím.");
                    scanner.next(); 
                    continue;
                }

                int soNguoiChoi = scanner.nextInt();

                if (soNguoiChoi == soCuaMay) {
                    System.out.println("Chuẩn cơm mẹ nấu rồi! Số đúng là: " + soCuaMay);
                    daThang = true;
                    break;
                } else if (soNguoiChoi < soCuaMay) {
                    System.out.println("Số thím đoán NHỎ hơn số của máy.");
                } else {
                    System.out.println("Số thím đoán LỚN hơn số của máy.");
                }

                if (soLanDoan < phepThuToiDa) {
                    System.out.println("Còn " + (phepThuToiDa - soLanDoan) + " lượt đoán.");
                }
            }

            if (!daThang) {
                System.out.println("\nGAME OVER! Thím đã hết lượt.");
                System.out.println("Số của máy là: " + soCuaMay + ". Gà quá đi!");
            } else {
                System.out.println("WINNER! Thím đoán đúng sau " + soLanDoan + " lần.");
            }

            // Hỏi người chơi có muốn tiếp tục không
            System.out.print("\nThím có muốn phục thù không? (C/K): ");
            String luaChon = scanner.next();
            if (luaChon.equalsIgnoreCase("K")) {
                tiepTuc = false;
                System.out.println("Tạm biệt thím! Hẹn gặp lại.");
            }
        }
        
        scanner.close();
    }
}