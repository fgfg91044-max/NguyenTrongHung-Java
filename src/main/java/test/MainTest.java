package test;

import dao.KetQuaDAO;
import dao.KhoaDAO;
import dao.MonDAO;
import dao.SinhVienDAO;
import domain.Khoa;
import domain.Mon;
import domain.SinhVien;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTest {

    private static Scanner sc = new Scanner(System.in);

    private static KhoaDAO khoaDAO = new KhoaDAO();
    private static MonDAO monDAO = new MonDAO();
    private static SinhVienDAO sinhVienDAO = new SinhVienDAO();
    private static KetQuaDAO ketQuaDAO = new KetQuaDAO();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n========== QUAN LY SINH VIEN ==========");
            System.out.println("1. Them khoa");
            System.out.println("2. Them mon hoc");
            System.out.println("3. Them sinh vien");
            System.out.println("4. Nhap diem sinh vien");
            System.out.println("5. Tra cuu diem sinh vien");
            System.out.println("6. Xem danh sach sinh vien va diem");
            System.out.println("7. Xem sinh vien theo khoa");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    themKhoa();
                    break;

                case 2:
                    themMon();
                    break;

                case 3:
                    themSinhVien();
                    break;

                case 4:
                    nhapDiem();
                    break;

                case 5:
                    traCuuDiemSinhVien();
                    break;

                case 6:
                    xemDanhSachSinhVienVaDiem();
                    break;

                case 7:
                    xemSinhVienTheoKhoa();
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;

                default:
                    System.out.println("Chon sai.");
                    break;
            }

        } while (choice != 0);
    }

    private static void themKhoa() {
        System.out.print("Nhap ma khoa: ");
        String maKhoa = sc.nextLine();

        System.out.print("Nhap ten khoa: ");
        String tenKhoa = sc.nextLine();

        Khoa khoa = new Khoa(maKhoa, tenKhoa);

        int rows = khoaDAO.insert(khoa);

        if (rows > 0) {
            System.out.println("Them khoa thanh cong.");
        } else {
            System.out.println("Them khoa that bai.");
        }
    }

    private static void themMon() {
        System.out.print("Nhap ma mon hoc: ");
        String maMH = sc.nextLine();

        System.out.print("Nhap ten mon hoc: ");
        String tenMH = sc.nextLine();

        System.out.print("Nhap so tiet: ");
        int soTiet = Integer.parseInt(sc.nextLine());

        Mon mon = new Mon(maMH, tenMH, soTiet);

        int rows = monDAO.insert(mon);

        if (rows > 0) {
            System.out.println("Them mon hoc thanh cong.");
        } else {
            System.out.println("Them mon hoc that bai.");
        }
    }

    private static void themSinhVien() {
        System.out.print("Nhap ma so sinh vien: ");
        int maSo = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap ho ten: ");
        String hoTen = sc.nextLine();

        System.out.print("Nhap ngay sinh yyyy-mm-dd: ");
        Date ngaySinh = Date.valueOf(sc.nextLine());

        System.out.print("Nhap gioi tinh 1-Nam, 0-Nu: ");
        int gt = Integer.parseInt(sc.nextLine());
        boolean gioiTinh = gt == 1;

        System.out.print("Nhap dia chi: ");
        String diaChi = sc.nextLine();

        System.out.print("Nhap dien thoai: ");
        String dienThoai = sc.nextLine();

        System.out.print("Nhap ma khoa: ");
        String maKhoa = sc.nextLine();

        SinhVien sv = new SinhVien(maSo, hoTen, ngaySinh, gioiTinh, diaChi, dienThoai, maKhoa);

        int rows = sinhVienDAO.insert(sv);

        if (rows > 0) {
            System.out.println("Them sinh vien thanh cong.");
        } else {
            System.out.println("Them sinh vien that bai.");
        }
    }

    private static void nhapDiem() {
        System.out.print("Nhap ma so sinh vien: ");
        int maSo = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap ma mon hoc: ");
        String maMH = sc.nextLine();

        System.out.print("Nhap diem: ");
        int diem = Integer.parseInt(sc.nextLine());

        int rows = ketQuaDAO.insert(maSo, maMH, diem);

        if (rows > 0) {
            System.out.println("Nhap diem thanh cong.");
        } else {
            System.out.println("Nhap diem that bai.");
        }
    }

    private static void traCuuDiemSinhVien() {
        System.out.print("Nhap ma so sinh vien can tra cuu: ");
        int maSo = Integer.parseInt(sc.nextLine());

        ArrayList<String> list = ketQuaDAO.xemDiemSinhVien(maSo);

        System.out.println("\n----- DIEM SINH VIEN -----");

        for (String dong : list) {
            System.out.println(dong);
        }
    }

    private static void xemDanhSachSinhVienVaDiem() {
        ArrayList<String> list = ketQuaDAO.xemDanhSachSinhVienVaDiem();

        System.out.println("\n----- DANH SACH SINH VIEN VA DIEM -----");

        if (list.isEmpty()) {
            System.out.println("Khong co du lieu.");
        } else {
            for (String dong : list) {
                System.out.println(dong);
            }
        }
    }

    private static void xemSinhVienTheoKhoa() {
        System.out.print("Nhap ma khoa can xem: ");
        String maKhoa = sc.nextLine();

        ArrayList<String> list = sinhVienDAO.xemSinhVienTheoKhoa(maKhoa);

        System.out.println("\n----- SINH VIEN THEO KHOA -----");

        if (list.isEmpty()) {
            System.out.println("Khong co sinh vien trong khoa nay.");
        } else {
            for (String dong : list) {
                System.out.println(dong);
            }
        }
    }
}