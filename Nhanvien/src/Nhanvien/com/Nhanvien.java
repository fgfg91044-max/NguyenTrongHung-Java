package Nhanvien.com;

import java.util.Scanner;

public class Nhanvien {

    // 1. Khai báo các thuộc tính
    private String ho;
    private String ten;
    private int soSP;

    // 2. Hàm khởi tạo Nhanvien(String, String, int)
    public Nhanvien(String ho, String ten, int soSP) {
        this.ho = ho;
        this.ten = ten;
        // Kiểm tra số sản phẩm, nếu < 0 thì gán bằng 0
        if (soSP < 0) {
            this.soSP = 0;
        } else {
            this.soSP = soSP;
        }
    }

    // 3. Viết các hàm lấy và gán giá trị (get/set)
    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getSoSP() {
        return soSP;
    }

    public void setSoSP(int soSP) {
        if (soSP < 0) {
            this.soSP = 0;
        } else {
            this.soSP = soSP;
        }
    }

    // 4. Hàm getLuong() tính lương theo điều kiện bảng
    public double getLuong() {
        double donGia = 0;
        
        if (soSP >= 1 && soSP <= 199) {
            donGia = 0.5;
        } else if (soSP >= 200 && soSP <= 399) {
            donGia = 0.55;
        } else if (soSP >= 400 && soSP <= 599) {
            donGia = 0.6;
        } else if (soSP >= 600) {
            donGia = 0.65;
        }
        
        return this.soSP * donGia;
    }

    // 5. Hàm LonHon(Nhanvien nv2) trả về true/false
    public boolean LonHon(Nhanvien nv2) {
        return this.soSP > nv2.getSoSP();
    }

    // =========================================================
    // Hàm main để chạy chương trình theo yêu cầu cuối bài
    // =========================================================
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập thông tin Nhân viên 1
        System.out.println("--- NHAP THONG TIN NHAN VIEN 1 ---");
        System.out.print("Nhap Ho: ");
        String ho1 = scanner.nextLine();
        System.out.print("Nhap Ten: ");
        String ten1 = scanner.nextLine();
        System.out.print("Nhap So san pham: ");
        int sp1 = Integer.parseInt(scanner.nextLine());
        Nhanvien nv1 = new Nhanvien(ho1, ten1, sp1);

        // Nhập thông tin Nhân viên 2
        System.out.println("\n--- NHAP THONG TIN NHAN VIEN 2 ---");
        System.out.print("Nhap Ho: ");
        String ho2 = scanner.nextLine();
        System.out.print("Nhap Ten: ");
        String ten2 = scanner.nextLine();
        System.out.print("Nhap So san pham: ");
        int sp2 = Integer.parseInt(scanner.nextLine());
        Nhanvien nv2 = new Nhanvien(ho2, ten2, sp2);

        // Tính và xuất ra lương của từng nhân viên
        System.out.println("\n--- THONG TIN LUONG ---");
        System.out.println("Luong cua nhan vien " + nv1.getHo() + " " + nv1.getTen() + " la: " + nv1.getLuong());
        System.out.println("Luong cua nhan vien " + nv2.getHo() + " " + nv2.getTen() + " la: " + nv2.getLuong());

        // So sánh và xuất thông báo theo 2 cách
        System.out.println("\n--- KET QUA SO SANH ---");
        
        // CÁCH 1: DÙNG HÀM LonHon()
        System.out.println("* Cach 1 (Su dung ham LonHon):");
        if (nv1.LonHon(nv2)) {
            int chenhLech = nv1.getSoSP() - nv2.getSoSP();
            System.out.println("-> Nhan vien " + nv1.getTen() + " lam nhieu hon nhan vien " + nv2.getTen() + " " + chenhLech + " san pham.");
        } else if (nv2.LonHon(nv1)) {
            int chenhLech = nv2.getSoSP() - nv1.getSoSP();
            System.out.println("-> Nhan vien " + nv2.getTen() + " lam nhieu hon nhan vien " + nv1.getTen() + " " + chenhLech + " san pham.");
        } else {
            System.out.println("-> Hai nhan vien lam duoc so san pham bang nhau.");
        }

        // CÁCH 2: KHÔNG DÙNG HÀM LonHon() (So sánh trực tiếp qua getSoSP)
        System.out.println("\n* Cach 2 (Khong su dung ham LonHon):");
        if (nv1.getSoSP() > nv2.getSoSP()) {
            int chenhLech = nv1.getSoSP() - nv2.getSoSP();
            System.out.println("-> Nhan vien " + nv1.getTen() + " lam nhieu hon nhan vien " + nv2.getTen() + " " + chenhLech + " san pham.");
        } else if (nv1.getSoSP() < nv2.getSoSP()) {
            int chenhLech = nv2.getSoSP() - nv1.getSoSP();
            System.out.println("-> Nhan vien " + nv2.getTen() + " lam nhieu hon nhan vien " + nv1.getTen() + " " + chenhLech + " san pham.");
        } else {
            System.out.println("-> Hai nhan vien lam duoc so san pham bang nhau.");
        }

        scanner.close();
    }
}