package baitap3.com;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class baitap3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Khởi tạo HashMap để lưu trữ Mã sách và Tên sách
        HashMap<Integer, String> mapSach = new HashMap<>();
        int choice;

        do {
            System.out.println("\n========= MENU QUẢN LÝ SÁCH (HASHMAP) =========");
            System.out.println("1. Thêm cuốn sách mới");
            System.out.println("2. Xuất toàn bộ danh sách sách");
            System.out.println("3. Sửa tên sách (theo mã)");
            System.out.println("4. Xóa cuốn sách (theo mã)");
            System.out.println("5. Tìm kiếm sách (theo mã)");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng: ");
            
            choice = scanner.nextInt();
            scanner.nextLine(); // Xử lý trôi lệnh sau khi nhập số

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã số sách: ");
                    int maSach = scanner.nextInt();
                    scanner.nextLine(); // Đọc bỏ dòng trống
                    if (mapSach.containsKey(maSach)) {
                        System.out.println("Lỗi: Mã sách này đã tồn tại!");
                    } else {
                        System.out.print("Nhập tên cuốn sách: ");
                        String tenSach = scanner.nextLine();
                        mapSach.put(maSach, tenSach);
                        System.out.println("Đã thêm sách thành công.");
                    }
                    break;

                case 2:
                    if (mapSach.isEmpty()) {
                        System.out.println("Danh sách sách hiện đang trống.");
                    } else {
                        System.out.println("--- Danh sách các cuốn sách ---");
                        // Duyệt qua HashMap bằng EntrySet
                        for (Map.Entry<Integer, String> entry : mapSach.entrySet()) {
                            System.out.println("Mã: " + entry.getKey() + " | Tên sách: " + entry.getValue());
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nhập mã sách cần sửa tên: ");
                    int maSua = scanner.nextInt();
                    scanner.nextLine(); 
                    if (mapSach.containsKey(maSua)) {
                        System.out.print("Nhập tên sách mới: ");
                        String tenMoi = scanner.nextLine();
                        mapSach.put(maSua, tenMoi); // Ghi đè tên mới vào mã cũ
                        System.out.println("Đã cập nhật tên sách thành công.");
                    } else {
                        System.out.println("Không tìm thấy mã sách " + maSua);
                    }
                    break;

                case 4:
                    System.out.print("Nhập mã sách cần xóa: ");
                    int maXoa = scanner.nextInt();
                    if (mapSach.containsKey(maXoa)) {
                        String tenDaXoa = mapSach.remove(maXoa);
                        System.out.println("Đã xóa cuốn sách: " + tenDaXoa);
                    } else {
                        System.out.println("Mã sách không tồn tại.");
                    }
                    break;

                case 5:
                    System.out.print("Nhập mã sách cần tìm: ");
                    int maTim = scanner.nextInt();
                    if (mapSach.containsKey(maTim)) {
                        System.out.println("Kết quả tìm kiếm: " + mapSach.get(maTim));
                    } else {
                        System.out.println("Không tìm thấy cuốn sách có mã số này.");
                    }
                    break;

                case 0:
                    System.out.println("Kết thúc chương trình bài 3. Tạm biệt!");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn lại.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}