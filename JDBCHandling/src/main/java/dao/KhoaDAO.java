package dao;

import data.JavaConnection;
import domain.Khoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KhoaDAO {

    public int insert(Khoa khoa) {
        String sql = "INSERT INTO KHOA(MAKHOA, TENKHOA, NGTLAP, TRGKHOA) VALUES (?, ?, ?, ?)";

        try (
                Connection conn = JavaConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, khoa.getMaKhoa());
            stmt.setString(2, khoa.getTenKhoa());
            stmt.setDate(3, khoa.getNgtLap());
            stmt.setString(4, khoa.getTrgKhoa());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int update(Khoa khoa) {
        String sql = "UPDATE KHOA SET TENKHOA = ?, NGTLAP = ?, TRGKHOA = ? WHERE MAKHOA = ?";

        try (
                Connection conn = JavaConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, khoa.getTenKhoa());
            stmt.setDate(2, khoa.getNgtLap());
            stmt.setString(3, khoa.getTrgKhoa());
            stmt.setString(4, khoa.getMaKhoa());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int delete(String maKhoa) {
        String sql = "DELETE FROM KHOA WHERE MAKHOA = ?";

        try (
                Connection conn = JavaConnection.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, maKhoa);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}