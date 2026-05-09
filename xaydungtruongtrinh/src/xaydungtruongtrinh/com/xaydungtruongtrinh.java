package xaydungtruongtrinh.com;

public class xaydungtruongtrinh {

    public static void main(String[] args) {
        // Tạo đối tượng quản lý danh sách nhân sự
        Staff currentStaff = new Staff();

        // Tạo ra các nhân viên mẫu
        Executive exec = new Executive("Nguyen Van A", "123 Hanoi", "0987654321", "SEC123", 2000.0);
        Employee emp = new Employee("Tran Thi B", "456 HCM", "0912345678", "EMP456", 1200.0);
        Hourly hourly = new Hourly("Le Van C", "789 Danang", "0909090909", "HRL789", 15.0);
        Volunteer vol = new Volunteer("Pham Thi D", "321 Hue", "0933333333");

        // Thêm nhân viên vào danh sách
        currentStaff.addNewStaff(exec);
        currentStaff.addNewStaff(emp);
        currentStaff.addNewStaff(hourly);
        currentStaff.addNewStaff(vol);

        // Thiết lập thêm giờ làm và tiền thưởng
        exec.awardBonus(500.0);
        hourly.addHours(40);

        // Chạy hàm trả lương in ra màn hình
        currentStaff.payday();
    }

}