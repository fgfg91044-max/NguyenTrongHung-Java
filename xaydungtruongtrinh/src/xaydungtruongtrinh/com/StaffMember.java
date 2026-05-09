package xaydungtruongtrinh.com;

public abstract class StaffMember {
    protected String name;
    protected String address;
    protected String phone;

    // Hàm tạo (Constructor)
    public StaffMember(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nAddress: " + address + "\nPhone: " + phone;
    }

    // Phương thức trừu tượng, các lớp con sẽ tự định nghĩa cách tính lương
    public abstract double pay();
}