package dao;

import data.JavaConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OrderDAO {

    public int taoOrderMoi(int maBan) {
        int maDH = -1;

        String sql = "INSERT INTO DON_HANG(MABAN, TRANGTHAI) VALUES (?, 'DANG_ORDER')";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1, maBan);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();

                if (rs.next()) {
                    maDH = rs.getInt(1);
                }

                rs.close();
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return maDH;
    }

    public int themMonVaoOrder(int maDH, int maMon, int soLuong, String ghiChu) {
        String sql = "INSERT INTO CHI_TIET_DON_HANG(MADH, MAMON, SOLUONG, GHICHU) "
                + "VALUES (?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE SOLUONG = SOLUONG + VALUES(SOLUONG), GHICHU = VALUES(GHICHU)";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, maDH);
            stmt.setInt(2, maMon);
            stmt.setInt(3, soLuong);
            stmt.setString(4, ghiChu);

            int rows = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ArrayList<String> xemChiTietOrder(int maDH) {
        ArrayList<String> list = new ArrayList<String>();

        String sql = "SELECT dh.MADH, ba.TENBAN, dh.NGAYLAP, dh.TRANGTHAI, "
                + "ma.TENMON, ct.SOLUONG, ma.DONGIA, "
                + "(ct.SOLUONG * ma.DONGIA) AS THANHTIEN, ct.GHICHU "
                + "FROM DON_HANG dh "
                + "JOIN BAN_AN ba ON dh.MABAN = ba.MABAN "
                + "JOIN CHI_TIET_DON_HANG ct ON dh.MADH = ct.MADH "
                + "JOIN MON_AN ma ON ct.MAMON = ma.MAMON "
                + "WHERE dh.MADH = ?";

        double tongTien = 0;
        boolean coDuLieu = false;

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, maDH);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (coDuLieu == false) {
                    list.add("Mã order: " + rs.getInt("MADH"));
                    list.add("Bàn: " + rs.getString("TENBAN"));
                    list.add("Ngày lập: " + rs.getString("NGAYLAP"));
                    list.add("Trạng thái: " + rs.getString("TRANGTHAI"));
                    list.add("--------------------------------------");

                    coDuLieu = true;
                }

                String dong = rs.getString("TENMON")
                        + " | SL: " + rs.getInt("SOLUONG")
                        + " | Đơn giá: " + rs.getDouble("DONGIA")
                        + " | Thành tiền: " + rs.getDouble("THANHTIEN")
                        + " | Ghi chú: " + rs.getString("GHICHU");

                list.add(dong);

                tongTien = tongTien + rs.getDouble("THANHTIEN");
            }

            if (coDuLieu == true) {
                list.add("--------------------------------------");
                list.add("Tổng tiền: " + tongTien + " VND");
            } else {
                list.add("Không tìm thấy order hoặc order chưa có món.");
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int sendOrder(int maDH) {
        String sql = "UPDATE DON_HANG SET TRANGTHAI = 'DA_GUI_BEP' "
                + "WHERE MADH = ? AND TRANGTHAI = 'DANG_ORDER'";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, maDH);

            int rows = stmt.executeUpdate();

            stmt.close();
            conn.close();

            return rows;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public ArrayList<String> xemOrderDaGui() {
        ArrayList<String> list = new ArrayList<String>();

        String sql = "SELECT dh.MADH, ba.TENBAN, dh.NGAYLAP, dh.TRANGTHAI "
                + "FROM DON_HANG dh "
                + "JOIN BAN_AN ba ON dh.MABAN = ba.MABAN "
                + "WHERE dh.TRANGTHAI = 'DA_GUI_BEP' "
                + "ORDER BY dh.NGAYLAP DESC";

        try {
            Connection conn = JavaConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String dong = "Mã order: " + rs.getInt("MADH")
                        + " | Bàn: " + rs.getString("TENBAN")
                        + " | Ngày lập: " + rs.getString("NGAYLAP")
                        + " | Trạng thái: " + rs.getString("TRANGTHAI");

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