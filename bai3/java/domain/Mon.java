package domain;

public class Mon {

    private String maMH;
    private String tenMH;
    private int soTiet;

    public Mon(String maMH, String tenMH, int soTiet) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTiet = soTiet;
    }

    public String getMaMH() {
        return maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public int getSoTiet() {
        return soTiet;
    }
}