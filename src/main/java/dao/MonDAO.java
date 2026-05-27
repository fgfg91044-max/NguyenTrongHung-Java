package dao;

import data.JavaConnection;
import domain.Mon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MonDAO {

    public int insert(Mon mon) {
        String sql = "INSERT INTO MON(MAMH, TENMH, SOTIET) VALUES (?, ?, ?)";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, mon.getMaMH());
            stmt.setString(2, mon.getTenMH());
            stmt.setInt(3, mon.getSoTiet());

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