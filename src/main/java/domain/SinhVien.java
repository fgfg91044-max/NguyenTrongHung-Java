package domain;

import java.sql.Date;

public class SinhVien {

    private int maSo;
    private String hoTen;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String diaChi;
    private String dienThoai;
    private String maKhoa;

    public SinhVien(int maSo, String hoTen, Date ngaySinh, boolean gioiTinh,
                    String diaChi, String dienThoai, String maKhoa) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.dienThoai = dienThoai;
        this.maKhoa = maKhoa;
    }

    public int getMaSo() {
        return maSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public String getMaKhoa() {
        return maKhoa;
    }
}