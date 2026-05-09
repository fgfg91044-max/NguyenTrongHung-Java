package xaydungtruongtrinh.com;

public class Executive extends Employee {
    private double bonus;

    public Executive(String name, String address, String phone, String socialSecurityNumber, double payRate) {
        super(name, address, phone, socialSecurityNumber, payRate);
        this.bonus = 0.0; // Khởi tạo tiền thưởng ban đầu là 0
    }

    public void awardBonus(double execBonus) {
        this.bonus = execBonus;
    }

    @Override
    public double pay() {
        // Lương của quản lý bằng lương cơ bản + tiền thưởng
        double payment = super.pay() + bonus;
        bonus = 0; // Reset lại tiền thưởng sau khi đã trả lương
        return payment;
    }
}