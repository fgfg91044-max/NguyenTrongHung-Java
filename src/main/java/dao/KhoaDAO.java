package dao;

import data.JavaConnection;
import domain.Khoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class KhoaDAO {

    public int insert(Khoa khoa) {
        String sql = "INSERT INTO KHOA(MAKHOA, TENKHOA) VALUES (?, ?)";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, khoa.getMaKhoa());
            stmt.setString(2, khoa.getTenKhoa());

            int rows = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}