package dao;

import data.JavaConnection;
import domain.GiaoVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GiaoVienDAO {

    public int insert(GiaoVien gv) {
    	String sql = "INSERT INTO GIAOVIEN(MAGV, HOTEN, HOCVI, HOCHAM, GIOITINH, HESO, MUCLUONG, MAKHOA) "
    	        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection conn = JavaConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, gv.getMaGV());
            stmt.setString(2, gv.getHoTen());
            stmt.setString(3, gv.getHocVi());
            stmt.setString(4, gv.getHocHam());
            stmt.setString(5, gv.getGioiTinh());
            stmt.setDouble(6, gv.getHeSo());
            stmt.setDouble(7, gv.getMucLuong());
            stmt.setString(8, gv.getMaKhoa());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int update(GiaoVien gv) {
    	String sql = "UPDATE GIAOVIEN "
    	        + "SET HOTEN = ?, HOCVI = ?, HOCHAM = ?, GIOITINH = ?, HESO = ?, MUCLUONG = ?, MAKHOA = ? "
    	        + "WHERE MAGV = ?";
        try (
                Connection conn = JavaConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, gv.getHoTen());
            stmt.setString(2, gv.getHocVi());
            stmt.setString(3, gv.getHocHam());
            stmt.setString(4, gv.getGioiTinh());
            stmt.setDouble(5, gv.getHeSo());
            stmt.setDouble(6, gv.getMucLuong());
            stmt.setString(7, gv.getMaKhoa());
            stmt.setString(8, gv.getMaGV());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int delete(String maGV) {
        String sql = "DELETE FROM GIAOVIEN WHERE MAGV = ?";

        try (
                Connection conn = JavaConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, maGV);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<GiaoVien> listNamKhoaCNTT() {
        List<GiaoVien> list = new ArrayList<>();

        String sql = "SELECT MAGV, HOTEN, HOCVI, HOCHAM, GIOITINH, HESO, MUCLUONG, MAKHOA "
                + "FROM GIAOVIEN "
                + "WHERE GIOITINH = 'Nam' AND MAKHOA = 'CNTT'";

        try (
                Connection conn = JavaConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                GiaoVien gv = new GiaoVien(
                        rs.getString("MAGV"),
                        rs.getString("HOTEN"),
                        rs.getString("HOCVI"),
                        rs.getString("HOCHAM"),
                        rs.getString("GIOITINH"),
                        rs.getDouble("HESO"),
                        rs.getDouble("MUCLUONG"),
                        rs.getString("MAKHOA")
                );

                list.add(gv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}