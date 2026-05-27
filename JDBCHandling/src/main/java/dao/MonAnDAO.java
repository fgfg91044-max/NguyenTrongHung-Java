package dao;

import data.JavaConnection;
import domain.MonAn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MonAnDAO {

    public ArrayList<MonAn> layDanhSachMonAn() {
        ArrayList<MonAn> list = new ArrayList<MonAn>();

        String sql = "SELECT MAMON, TENMON, DONGIA FROM MON_AN";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                MonAn mon = new MonAn(
                        rs.getInt("MAMON"),
                        rs.getString("TENMON"),
                        rs.getDouble("DONGIA")
                );

                list.add(mon);
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