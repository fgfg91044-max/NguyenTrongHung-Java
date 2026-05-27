package dao;

import data.JavaConnection;
import domain.SinhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SinhVienDAO {

    public int insert(SinhVien sv) {
        String sql = "INSERT INTO SINHVIEN(MASO, HOTEN, NGAYSINH, GIOITINH, DIACHI, DIENTHOAI, MAKHOA) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, sv.getMaSo());
            stmt.setString(2, sv.getHoTen());
            stmt.setDate(3, sv.getNgaySinh());
            stmt.setBoolean(4, sv.isGioiTinh());
            stmt.setString(5, sv.getDiaChi());
            stmt.setString(6, sv.getDienThoai());
            stmt.setString(7, sv.getMaKhoa());

            int rows = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ArrayList<String> xemSinhVienTheoKhoa(String maKhoa) {
        ArrayList<String> list = new ArrayList<String>();

        String sql = "SELECT sv.MASO, sv.HOTEN, sv.NGAYSINH, sv.GIOITINH, sv.DIACHI, sv.DIENTHOAI, k.TENKHOA "
                + "FROM SINHVIEN sv "
                + "JOIN KHOA k ON sv.MAKHOA = k.MAKHOA "
                + "WHERE sv.MAKHOA = ?";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, maKhoa);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String gioiTinh = rs.getBoolean("GIOITINH") ? "Nam" : "Nu";

                String dong = "Ma so: " + rs.getInt("MASO")
                        + " | Ho ten: " + rs.getString("HOTEN")
                        + " | Ngay sinh: " + rs.getDate("NGAYSINH")
                        + " | Gioi tinh: " + gioiTinh
                        + " | Dia chi: " + rs.getString("DIACHI")
                        + " | Dien thoai: " + rs.getString("DIENTHOAI")
                        + " | Khoa: " + rs.getString("TENKHOA");

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