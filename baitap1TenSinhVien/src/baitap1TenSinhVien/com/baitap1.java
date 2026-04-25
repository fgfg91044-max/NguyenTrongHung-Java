package baitap1TenSinhVien.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class baitap1 {

    public static void main(String[] args) {
        ArrayList<String> dsSinhVien = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int chon;

        do {
            System.out.println("\n--- QUẢN LÝ TÊN SINH VIÊN ---");
            System.out.println("1. Thêm Sinh viên (a)");
            System.out.println("2. Xuất danh sách sinh viên (b)");
            System.out.println("3. Sửa Sinh Viên (c)");
            System.out.println("4. Xóa Sinh viên chứa tên bất kỳ (d)");
            System.out.println("5. Tìm Sinh viên mà tên có chữ An (e)");
            System.out.println("6. Sắp xếp Sinh Viên (f)");
            System.out.println("7. Xuất ra số lượng sinh viên (g)");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng: ");
            
            chon = Integer.parseInt(sc.nextLine());

            switch (chon) {
                case 1: // a) Thêm Sinh viên
                    System.out.print("Nhập tên sinh viên mới: ");
                    String tenMoi = sc.nextLine();
                    dsSinhVien.add(tenMoi);
                    System.out.println("Đã thêm thành công.");
                    break;

                case 2: // b) Xuất danh sách
                    System.out.println("Danh sách sinh viên:");
                    for (String s : dsSinhVien) {
                        System.out.println("- " + s);
                    }
                    break;

                case 3: // c) Sửa Sinh Viên
                    System.out.print("Nhập tên sinh viên muốn sửa: ");
                    String tenCanSua = sc.nextLine();
                    int indexSua = dsSinhVien.indexOf(tenCanSua);
                    if (indexSua != -1) {
                        System.out.print("Nhập tên mới: ");
                        dsSinhVien.set(indexSua, sc.nextLine());
                        System.out.println("Sửa thành công.");
                    } else {
                        System.out.println("Không tìm thấy sinh viên này.");
                    }
                    break;

                case 4: // d) Xóa Sinh viên chứa tên bất kỳ
                    System.out.print("Nhập từ khóa cần xóa: ");
                    String tuKhoaXoa = sc.nextLine();
                    // Xóa tất cả sinh viên có chứa chuỗi tuKhoaXoa (không phân biệt hoa thường)
                    boolean checkXoa = dsSinhVien.removeIf(s -> s.toLowerCase().contains(tuKhoaXoa.toLowerCase()));
                    if (checkXoa) {
                        System.out.println("Đã xóa các sinh viên liên quan.");
                    } else {
                        System.out.println("Không tìm thấy kết quả phù hợp để xóa.");
                    }
                    break;

                case 5: // e) Tìm Sinh viên mà tên có chữ An
                    System.out.println("Kết quả tìm kiếm tên có chữ 'An':");
                    for (String s : dsSinhVien) {
                        if (s.toLowerCase().contains("an")) {
                            System.out.println("- " + s);
                        }
                    }
                    break;

                case 6: // f) Sắp xếp Sinh Viên
                    Collections.sort(dsSinhVien);
                    System.out.println("Đã sắp xếp danh sách theo thứ tự A-Z.");
                    break;

                case 7: // g) Xuất ra số lượng sinh viên
                    System.out.println("Tổng số lượng sinh viên: " + dsSinhVien.size());
                    break;

                case 0:
                    System.out.println("Kết thúc chương trình.");
                    break;
            }
        } while (chon != 0);
        
        sc.close();
    }
}