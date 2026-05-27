package domain;

public class GiaoVien {

    private String maGV;
    private String hoTen;
    private String hocVi;
    private String hocHam;
    private String gioiTinh;
    private double heSo;
    private double mucLuong;
    private String maKhoa;

    public GiaoVien(String maGV, String hoTen, String hocVi, String hocHam,
                    String gioiTinh, double heSo, double mucLuong, String maKhoa) {
        this.maGV = maGV;
        this.hoTen = hoTen;
        this.hocVi = hocVi;
        this.hocHam = hocHam;
        this.gioiTinh = gioiTinh;
        this.heSo = heSo;
        this.mucLuong = mucLuong;
        this.maKhoa = maKhoa;
    }

    public String getMaGV() {
        return maGV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getHocVi() {
        return hocVi;
    }

    public String getHocHam() {
        return hocHam;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public double getHeSo() {
        return heSo;
    }

    public double getMucLuong() {
        return mucLuong;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    @Override
    public String toString() {
        return "GiaoVien{" +
                "maGV='" + maGV + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", hocVi='" + hocVi + '\'' +
                ", hocHam='" + hocHam + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", heSo=" + heSo +
                ", mucLuong=" + mucLuong +
                ", maKhoa='" + maKhoa + '\'' +
                '}';
    }
}