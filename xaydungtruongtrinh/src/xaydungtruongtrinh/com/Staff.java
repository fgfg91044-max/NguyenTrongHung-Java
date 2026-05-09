package xaydungtruongtrinh.com;

import java.util.ArrayList;

public class Staff {
    private ArrayList<StaffMember> staffList;

    public Staff() {
        staffList = new ArrayList<StaffMember>();
    }

    // Phương thức phụ thêm vào để dễ dàng thêm nhân sự vào danh sách
    public void addNewStaff(StaffMember member) {
        staffList.add(member);
    }

    public void payday() {
        for (StaffMember member : staffList) {
            System.out.println("-----------------------------------");
            System.out.println(member.toString());
            System.out.printf("Paid: %.2f\n", member.pay());
        }
    }
}