package bai4.com;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class bai4 {

    public static void main(String[] args) {
        // Khởi tạo mảng M như trong ví dụ
        int[] M = {3, 6, 7, 8, 11, 17, 2, 90, 2, 5, 4, 5, 8};

        List<Integer> soLe = new ArrayList<>();
        List<Integer> soChan = new ArrayList<>();
        List<Integer> soNguyenTo = new ArrayList<>();
        List<Integer> khongPhaiNguyenTo = new ArrayList<>();

        // Phân loại các số trong mảng
        for (int n : M) {
            // Phân loại chẵn/lẻ
            if (n % 2 != 0) {
                soLe.add(n);
            } else {
                soChan.add(n);
            }

            // Phân loại nguyên tố/không nguyên tố
            if (isPrime(n)) {
                soNguyenTo.add(n);
            } else {
                khongPhaiNguyenTo.add(n);
            }
        }

        // In kết quả ra màn hình
        System.out.print("Dòng 1: ");
        inDanhSachVaSoLuong(soLe, "số lẻ");

        System.out.print("Dòng 2: ");
        inDanhSachVaSoLuong(soChan, "số chẵn");

        System.out.print("Dòng 3 (Các số nguyên tố): ");
        inDanhSach(soNguyenTo);

        System.out.print("Dòng 4 (Các số không phải nguyên tố): ");
        inDanhSach(khongPhaiNguyenTo);
    }

    /**
     * Hàm kiểm tra số nguyên tố
     */
    public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }
        return true;
    }

    /**
     * Hàm in danh sách có gộp các số trùng nhau (vd: 5(2)) và in tổng số lượng
     */
    public static void inDanhSachVaSoLuong(List<Integer> list, String tenLoai) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : list) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        StringBuilder sb = new StringBuilder("-> ");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                sb.append(entry.getKey()).append("(").append(entry.getValue()).append("), ");
            } else {
                sb.append(entry.getKey()).append(", ");
            }
        }

        if (!map.isEmpty()) {
            sb.setLength(sb.length() - 2); // Xóa dấu phẩy và khoảng trắng dư ở cuối
        }
        sb.append(" -> ").append(list.size()).append(" ").append(tenLoai);
        System.out.println(sb.toString());
    }

    /**
     * Hàm in danh sách bình thường (dành cho dòng 3 và 4)
     */
    public static void inDanhSach(List<Integer> list) {
        Map<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : list) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        StringBuilder sb = new StringBuilder("-> ");
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                sb.append(entry.getKey()).append("(").append(entry.getValue()).append("), ");
            } else {
                sb.append(entry.getKey()).append(", ");
            }
        }

        if (!map.isEmpty()) {
            sb.setLength(sb.length() - 2); // Xóa dấu phẩy dư
        }
        System.out.println(sb.toString());
    }
}