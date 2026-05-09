package xaydungtruongtrinh.com;

public class Volunteer extends StaffMember {

    public Volunteer(String name, String address, String phone) {
        super(name, address, phone);
    }

    @Override
    public double pay() {
        // Tình nguyện viên không nhận lương
        return 0.0;
    }
}