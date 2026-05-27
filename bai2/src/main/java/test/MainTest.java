package test;

import dao.MonAnDAO;
import dao.OrderDAO;
import domain.MonAn;

import java.util.ArrayList;
import java.util.Scanner;

public class MainTest {

    private static Scanner sc = new Scanner(System.in);
    private static MonAnDAO monAnDAO = new MonAnDAO();
    private static OrderDAO orderDAO = new OrderDAO();

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n========== E-ORDER QUAN AN NHANH ==========");
            System.out.println("1. Xem danh sach mon an");
            System.out.println("2. Tao order moi");
            System.out.println("3. Them mon vao order");
            System.out.println("4. Xem chi tiet order");
            System.out.println("5. Send order xuong nha bep");
            System.out.println("6. Xem danh sach order da gui bep");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                    xemDanhSachMonAn();
                    break;

                case 2:
                    taoOrderMoi();
                    break;

                case 3:
                    themMonVaoOrder();
                    break;

                case 4:
                    xemChiTietOrder();
                    break;

                case 5:
                    sendOrder();
                    break;

                case 6:
                    xemOrderDaGui();
                    break;

                case 0:
                    System.out.println("Thoat chuong trinh.");
                    break;

                default:
                    System.out.println("Chon sai, vui long chon lai.");
                    break;
            }

        } while (choice != 0);
    }

    private static void xemDanhSachMonAn() {
        ArrayList<MonAn> list = monAnDAO.layDanhSachMonAn();

        System.out.println("\n----- DANH SACH MON AN -----");

        if (list.isEmpty()) {
            System.out.println("Chua co mon an nao.");
        } else {
            for (MonAn mon : list) {
                System.out.println(mon);
            }
        }
    }

    private static void taoOrderMoi() {
        System.out.print("Nhap ma ban: ");
        int maBan = Integer.parseInt(sc.nextLine());

        int maDH = orderDAO.taoOrderMoi(maBan);

        if (maDH > 0) {
            System.out.println("Tao order thanh cong.");
            System.out.println("Ma order cua ban la: " + maDH);
        } else {
            System.out.println("Tao order that bai. Kiem tra lai ma ban.");
        }
    }

    private static void themMonVaoOrder() {
        System.out.print("Nhap ma order: ");
        int maDH = Integer.parseInt(sc.nextLine());

        xemDanhSachMonAn();

        System.out.print("Nhap ma mon: ");
        int maMon = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap so luong: ");
        int soLuong = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap ghi chu, neu khong co thi bo trong: ");
        String ghiChu = sc.nextLine();

        int rows = orderDAO.themMonVaoOrder(maDH, maMon, soLuong, ghiChu);

        if (rows > 0) {
            System.out.println("Them mon vao order thanh cong.");
        } else {
            System.out.println("Them mon that bai.");
        }
    }

    private static void xemChiTietOrder() {
        System.out.print("Nhap ma order can xem: ");
        int maDH = Integer.parseInt(sc.nextLine());

        ArrayList<String> list = orderDAO.xemChiTietOrder(maDH);

        System.out.println("\n----- CHI TIET ORDER -----");

        for (String dong : list) {
            System.out.println(dong);
        }
    }

    private static void sendOrder() {
        System.out.print("Nhap ma order can gui xuong bep: ");
        int maDH = Integer.parseInt(sc.nextLine());

        int rows = orderDAO.sendOrder(maDH);

        if (rows > 0) {
            System.out.println("Send order thanh cong. Don hang da duoc gui xuong nha bep.");
        } else {
            System.out.println("Send order that bai. Order khong ton tai hoac da gui roi.");
        }
    }

    private static void xemOrderDaGui() {
        ArrayList<String> list = orderDAO.xemOrderDaGui();

        System.out.println("\n----- DANH SACH ORDER DA GUI BEP -----");

        if (list.isEmpty()) {
            System.out.println("Chua co order nao duoc gui bep.");
        } else {
            for (String dong : list) {
                System.out.println(dong);
            }
        }
    }
}