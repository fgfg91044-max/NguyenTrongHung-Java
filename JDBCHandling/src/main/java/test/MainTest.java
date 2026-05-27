package test;

import dao.GiaoVienDAO;
import dao.KhoaDAO;
import domain.GiaoVien;
import domain.Khoa;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class MainTest {

    private static final Scanner sc = new Scanner(System.in);
    private static final KhoaDAO khoaDAO = new KhoaDAO();
    private static final GiaoVienDAO giaoVienDAO = new GiaoVienDAO();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n========== MENU ==========");
            System.out.println("1. Thêm khoa");
            System.out.println("2. Sửa khoa");
            System.out.println("3. Xóa khoa");
            System.out.println("4. Thêm giáo viên");
            System.out.println("5. Sửa giáo viên");
            System.out.println("6. Xóa giáo viên");
            System.out.println("7. Liệt kê giáo viên Nam của khoa CNTT");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
            case 1:
                themKhoa();
                break;
            case 2:
                suaKhoa();
                break;
            case 3:
                xoaKhoa();
                break;
            case 4:
                themGiaoVien();
                break;
            case 5:
                suaGiaoVien();
                break;
            case 6:
                xoaGiaoVien();
                break;
            case 7:
                lietKeNamCNTT();
                break;
            case 0:
                System.out.println("Thoát chương trình.");
                break;
            default:
                System.out.println("Chọn sai!");
                break;
        }
        } while (choice != 0);
    }

    private static void themKhoa() {
        Khoa khoa = nhapKhoa();

        int rows = khoaDAO.insert(khoa);

        if (rows > 0) {
            System.out.println("Thêm khoa thành công!");
        } else {
            System.out.println("Thêm khoa thất bại!");
        }
    }

    private static void suaKhoa() {
        Khoa khoa = nhapKhoa();

        int rows = khoaDAO.update(khoa);

        if (rows > 0) {
            System.out.println("Sửa khoa thành công!");
        } else {
            System.out.println("Sửa khoa thất bại!");
        }
    }

    private static void xoaKhoa() {
        System.out.print("Nhập mã khoa cần xóa: ");
        String maKhoa = sc.nextLine();

        int rows = khoaDAO.delete(maKhoa);

        if (rows > 0) {
            System.out.println("Xóa khoa thành công!");
        } else {
            System.out.println("Xóa khoa thất bại!");
        }
    }

    private static void themGiaoVien() {
        GiaoVien gv = nhapGiaoVien();

        int rows = giaoVienDAO.insert(gv);

        if (rows > 0) {
            System.out.println("Thêm giáo viên thành công!");
        } else {
            System.out.println("Thêm giáo viên thất bại!");
        }
    }

    private static void suaGiaoVien() {
        GiaoVien gv = nhapGiaoVien();

        int rows = giaoVienDAO.update(gv);

        if (rows > 0) {
            System.out.println("Sửa giáo viên thành công!");
        } else {
            System.out.println("Sửa giáo viên thất bại!");
        }
    }

    private static void xoaGiaoVien() {
        System.out.print("Nhập mã giáo viên cần xóa: ");
        String maGV = sc.nextLine();

        int rows = giaoVienDAO.delete(maGV);

        if (rows > 0) {
            System.out.println("Xóa giáo viên thành công!");
        } else {
            System.out.println("Xóa giáo viên thất bại!");
        }
    }

    private static void lietKeNamCNTT() {
        List<GiaoVien> list = giaoVienDAO.listNamKhoaCNTT();

        System.out.println("\nDanh sách giáo viên Nam của khoa CNTT:");

        if (list.isEmpty()) {
            System.out.println("Không có dữ liệu.");
        } else {
            for (GiaoVien gv : list) {
                System.out.println(gv);
            }
        }
    }

    private static Khoa nhapKhoa() {
        System.out.print("Mã khoa: ");
        String maKhoa = sc.nextLine();

        System.out.print("Tên khoa: ");
        String tenKhoa = sc.nextLine();

        System.out.print("Ngày thành lập yyyy-mm-dd: ");
        Date ngtLap = Date.valueOf(sc.nextLine());

        System.out.print("Trưởng khoa, bỏ trống nếu chưa có: ");
        String trgKhoa = sc.nextLine();

        if (trgKhoa.isBlank()) {
            trgKhoa = null;
        }

        return new Khoa(maKhoa, tenKhoa, ngtLap, trgKhoa);
    }

    private static GiaoVien nhapGiaoVien() {
        System.out.print("Mã giáo viên: ");
        String maGV = sc.nextLine();

        System.out.print("Họ tên: ");
        String hoTen = sc.nextLine();

        System.out.print("Học vị: ");
        String hocVi = sc.nextLine();

        System.out.print("Học hàm, bỏ trống nếu không có: ");
        String hocHam = sc.nextLine();

        if (hocHam.isBlank()) {
            hocHam = null;
        }

        System.out.print("Giới tính: ");
        String gioiTinh = sc.nextLine();

        System.out.print("Hệ số: ");
        double heSo = Double.parseDouble(sc.nextLine());

        System.out.print("Mức lương: ");
        double mucLuong = Double.parseDouble(sc.nextLine());

        System.out.print("Mã khoa: ");
        String maKhoa = sc.nextLine();

        return new GiaoVien(maGV, hoTen, hocVi, hocHam, gioiTinh, heSo, mucLuong, maKhoa);
    }
}