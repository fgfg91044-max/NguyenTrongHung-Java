package domain;

public class MonAn {

    private int maMon;
    private String tenMon;
    private double donGia;

    public MonAn(int maMon, String tenMon, double donGia) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.donGia = donGia;
    }

    public int getMaMon() {
        return maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public double getDonGia() {
        return donGia;
    }

    @Override
    public String toString() {
        return maMon + ". " + tenMon + " - " + donGia + " VND";
    }
}