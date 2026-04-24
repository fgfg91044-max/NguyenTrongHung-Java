package baitap2.com;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class baitap2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Khởi tạo ArrayList để lưu trữ các số nguyên
        ArrayList<Integer> list = new ArrayList<>();
        int choice;

        do {
            System.out.println("\n========= MENU QUẢN LÝ ARRAYLIST =========");
            System.out.println("1. Thêm số nguyên vào danh sách");
            System.out.println("2. Sửa số nguyên (theo vị trí index)");
            System.out.println("3. Xóa số nguyên (theo vị trí index)");
            System.out.println("4. Tìm kiếm một số trong danh sách");
            System.out.println("5. Sắp xếp danh sách (Tăng dần)");
            System.out.println("6. Xuất toàn bộ danh sách");
            System.out.println("0. Thoát");
            System.out.print("Mời bạn chọn chức năng: ");
            
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Nhập số nguyên muốn thêm: ");
                    int value = scanner.nextInt();
                    list.add(value);
                    System.out.println("Đã thêm " + value + " vào danh sách.");
                    break;

                case 2:
                    if (list.isEmpty()) {
                        System.out.println("Danh sách đang trống!");
                    } else {
                        System.out.print("Nhập vị trí index cần sửa (0 đến " + (list.size() - 1) + "): ");
                        int editIndex = scanner.nextInt();
                        if (editIndex >= 0 && editIndex < list.size()) {
                            System.out.print("Nhập giá trị mới: ");
                            int newValue = scanner.nextInt();
                            list.set(editIndex, newValue);
                            System.out.println("Cập nhật thành công!");
                        } else {
                            System.out.println("Vị trí không hợp lệ!");
                        }
                    }
                    break;

                case 3:
                    if (list.isEmpty()) {
                        System.out.println("Danh sách đang trống!");
                    } else {
                        System.out.print("Nhập vị trí index cần xóa: ");
                        int deleteIndex = scanner.nextInt();
                        if (deleteIndex >= 0 && deleteIndex < list.size()) {
                            int removedVal = list.remove(deleteIndex);
                            System.out.println("Đã xóa phần tử " + removedVal + " tại vị trí " + deleteIndex);
                        } else {
                            System.out.println("Vị trí không hợp lệ!");
                        }
                    }
                    break;

                case 4:
                    System.out.print("Nhập số nguyên cần tìm: ");
                    int searchValue = scanner.nextInt();
                    if (list.contains(searchValue)) {
                        System.out.println("Tìm thấy " + searchValue + " tại vị trí index: " + list.indexOf(searchValue));
                    } else {
                        System.out.println("Không tìm thấy " + searchValue + " trong danh sách.");
                    }
                    break;

                case 5:
                    Collections.sort(list);
                    System.out.println("Danh sách đã được sắp xếp tăng dần.");
                    break;

                case 6:
                    System.out.println("Danh sách hiện tại: " + list);
                    break;

                case 0:
                    System.out.println("Kết thúc chương trình bài 2.");
                    break;

                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
                    break;
            }
        } while (choice != 0);

        scanner.close();
    }
}