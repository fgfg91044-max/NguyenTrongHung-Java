package domain;

import java.sql.Date;

public class Khoa {

    private String maKhoa;
    private String tenKhoa;
    private Date ngtLap;
    private String trgKhoa;

    public Khoa(String maKhoa, String tenKhoa, Date ngtLap, String trgKhoa) {
        this.maKhoa = maKhoa;
        this.tenKhoa = tenKhoa;
        this.ngtLap = ngtLap;
        this.trgKhoa = trgKhoa;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public Date getNgtLap() {
        return ngtLap;
    }

    public String getTrgKhoa() {
        return trgKhoa;
    }

    @Override
    public String toString() {
        return "Khoa{" +
                "maKhoa='" + maKhoa + '\'' +
                ", tenKhoa='" + tenKhoa + '\'' +
                ", ngtLap=" + ngtLap +
                ", trgKhoa='" + trgKhoa + '\'' +
                '}';
    }
}