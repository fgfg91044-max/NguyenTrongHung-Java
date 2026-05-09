package xaydungtruongtrinh.com;

public class Hourly extends Employee {
    private int hoursWorked;

    public Hourly(String name, String address, String phone, String socialSecurityNumber, double payRate) {
        super(name, address, phone, socialSecurityNumber, payRate);
        this.hoursWorked = 0; // Khởi tạo số giờ làm việc là 0
    }

    public void addHours(int moreHours) {
        this.hoursWorked += moreHours;
    }

    @Override
    public double pay() {
        // Lương = mức lương theo giờ * số giờ làm việc
        double payment = payRate * hoursWorked;
        hoursWorked = 0; // Reset lại số giờ làm việc sau khi đã trả lương
        return payment;
    }

    @Override
    public String toString() {
        return super.toString() + "\nCurrent hours: " + hoursWorked;
    }
}