package dao;

import data.JavaConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KetQuaDAO {

    public int insert(int maSo, String maMH, int diem) {
        String sql = "INSERT INTO KETQUA(MASO, MAMH, DIEM) VALUES (?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE DIEM = VALUES(DIEM)";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, maSo);
            stmt.setString(2, maMH);
            stmt.setInt(3, diem);

            int rows = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ArrayList<String> xemDiemSinhVien(int maSo) {
        ArrayList<String> list = new ArrayList<String>();

        String sql = "SELECT sv.MASO, sv.HOTEN, m.MAMH, m.TENMH, m.SOTIET, kq.DIEM "
                + "FROM SINHVIEN sv "
                + "JOIN KETQUA kq ON sv.MASO = kq.MASO "
                + "JOIN MON m ON kq.MAMH = m.MAMH "
                + "WHERE sv.MASO = ?";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, maSo);

            ResultSet rs = stmt.executeQuery();

            boolean coDuLieu = false;

            while (rs.next()) {
                if (coDuLieu == false) {
                    list.add("Ma so: " + rs.getInt("MASO"));
                    list.add("Ho ten: " + rs.getString("HOTEN"));
                    list.add("--------------------------------------");
                    coDuLieu = true;
                }

                String dong = "Ma mon: " + rs.getString("MAMH")
                        + " | Ten mon: " + rs.getString("TENMH")
                        + " | So tiet: " + rs.getInt("SOTIET")
                        + " | Diem: " + rs.getInt("DIEM");

                list.add(dong);
            }

            if (coDuLieu == false) {
                list.add("Khong tim thay diem cua sinh vien nay.");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<String> xemDanhSachSinhVienVaDiem() {
        ArrayList<String> list = new ArrayList<String>();

        String sql = "SELECT sv.MASO, sv.HOTEN, k.TENKHOA, m.TENMH, kq.DIEM "
                + "FROM SINHVIEN sv "
                + "JOIN KHOA k ON sv.MAKHOA = k.MAKHOA "
                + "LEFT JOIN KETQUA kq ON sv.MASO = kq.MASO "
                + "LEFT JOIN MON m ON kq.MAMH = m.MAMH "
                + "ORDER BY sv.MASO";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tenMon = rs.getString("TENMH");
                int diem = rs.getInt("DIEM");

                String dong = "Ma so: " + rs.getInt("MASO")
                        + " | Ho ten: " + rs.getString("HOTEN")
                        + " | Khoa: " + rs.getString("TENKHOA")
                        + " | Mon: " + tenMon
                        + " | Diem: " + diem;

                list.add(dong);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}