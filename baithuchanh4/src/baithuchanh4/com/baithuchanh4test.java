package baithuchanh4.com;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class baithuchanh4test {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Invoice> invoices = new ArrayList<>();

    public static void main(String[] args) {
        mockData();

        int choice;
        do {
            showMenu();
            choice = readInt("Chọn chức năng: ");

            switch (choice) {
                case 1:
                    displayAll();
                    break;
                case 2:
                    displayOneInvoice();
                    break;
                case 3:
                    addInvoice();
                    break;
                case 4:
                    sortMenu();
                    break;
                case 5:
                    searchMenu();
                    break;
                case 6:
                    deleteInvoice();
                    break;
                case 0:
                    System.out.println("Thoát chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
            }

        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("\n========== MENU INVOICE ==========");
        System.out.println("1. Xuất danh sách hóa đơn");
        System.out.println("2. Xuất thông tin một hóa đơn theo mã");
        System.out.println("3. Thêm hóa đơn");
        System.out.println("4. Sắp xếp danh sách hóa đơn");
        System.out.println("5. Tìm kiếm hóa đơn theo mã");
        System.out.println("6. Xóa hóa đơn theo mã");
        System.out.println("0. Thoát");
        System.out.println("==================================");
    }

    public static void mockData() {
        invoices.add(new Invoice("MH001", "Ban phim co", 5, 350000));
        invoices.add(new Invoice("MH002", "Chuot khong day", 8, 180000));
        invoices.add(new Invoice("MH003", "Man hinh Dell", 3, 3200000));
        invoices.add(new Invoice("MH004", "RAM 8GB DDR4", 10, 650000));
        invoices.add(new Invoice("MH005", "SSD 512GB", 7, 950000));
        invoices.add(new Invoice("MH006", "CPU Intel i5", 2, 4200000));
        invoices.add(new Invoice("MH007", "Mainboard ASUS", 4, 2500000));
        invoices.add(new Invoice("MH008", "Nguon 650W", 6, 1200000));
        invoices.add(new Invoice("MH009", "Case Gaming", 5, 900000));
        invoices.add(new Invoice("MH010", "Tai nghe Gaming", 9, 450000));
    }

    public static void addInvoice() {
        String partNumber;

        do {
            partNumber = readString("Nhập mã mặt hàng: ");

            if (isDuplicate(partNumber)) {
                System.out.println("Mã mặt hàng đã tồn tại. Nhập mã khác!");
            }

        } while (isDuplicate(partNumber));

        String partDescription = readString("Nhập mô tả mặt hàng: ");
        int quantity = readInt("Nhập số lượng: ");
        double price = readDouble("Nhập giá mỗi mặt hàng: ");

        invoices.add(new Invoice(partNumber, partDescription, quantity, price));

        System.out.println("Thêm hóa đơn thành công!");
    }

    public static boolean isDuplicate(String code) {
        for (Invoice invoice : invoices) {
            if (invoice.getPartNumber().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    public static void displayAll() {
        if (invoices.isEmpty()) {
            System.out.println("Danh sách hóa đơn rỗng!");
            return;
        }

        printHeader();

        for (Invoice invoice : invoices) {
            printInvoice(invoice);
        }
    }

    public static void displayOneInvoice() {
        String code = readString("Nhập mã mặt hàng cần xem: ");

        int index = linearSearch(code);

        if (index == -1) {
            System.out.println("Không tìm thấy hóa đơn!");
        } else {
            printHeader();
            printInvoice(invoices.get(index));
        }
    }

    public static void printHeader() {
        System.out.printf("%-10s %-25s %10s %15s %15s%n",
                "Mã", "Mô tả", "Số lượng", "Đơn giá", "Thành tiền");
        System.out.println("-------------------------------------------------------------------------------");
    }

    public static void printInvoice(Invoice invoice) {
        System.out.printf("%-10s %-25s %10d %15.2f %15.2f%n",
                invoice.getPartNumber(),
                invoice.getPartDescription(),
                invoice.getQuantity(),
                invoice.getPricePerItem(),
                invoice.getInvoiceAmount());
    }

    public static void sortMenu() {
        System.out.println("\nSắp xếp theo:");
        System.out.println("1. Mã mặt hàng tăng dần");
        System.out.println("2. Số lượng tăng dần");

        int choice = readInt("Chọn: ");

        if (choice == 1) {
            bubbleSortByCode();
            System.out.println("Đã sắp xếp theo mã mặt hàng.");
        } else if (choice == 2) {
            bubbleSortByQuantity();
            System.out.println("Đã sắp xếp theo số lượng.");
        } else {
            System.out.println("Lựa chọn không hợp lệ!");
            return;
        }

        displayAll();
    }

    public static void bubbleSortByCode() {
        for (int i = 0; i < invoices.size() - 1; i++) {
            for (int j = 0; j < invoices.size() - i - 1; j++) {
                if (invoices.get(j).getPartNumber()
                        .compareToIgnoreCase(invoices.get(j + 1).getPartNumber()) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public static void bubbleSortByQuantity() {
        for (int i = 0; i < invoices.size() - 1; i++) {
            for (int j = 0; j < invoices.size() - i - 1; j++) {
                if (invoices.get(j).getQuantity() > invoices.get(j + 1).getQuantity()) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public static void searchMenu() {
        System.out.println("\nTìm kiếm:");
        System.out.println("1. Tìm kiếm tuyến tính");
        System.out.println("2. Tìm kiếm nhị phân");
        System.out.println("3. Tìm bằng hàm thư viện");

        int choice = readInt("Chọn: ");
        String code = readString("Nhập mã mặt hàng cần tìm: ");

        Invoice result = null;

        switch (choice) {
            case 1:
                int index = linearSearch(code);
                if (index != -1) {
                    result = invoices.get(index);
                }
                break;

            case 2:
                invoices.sort(Comparator.comparing(Invoice::getPartNumber, String.CASE_INSENSITIVE_ORDER));
                int binaryIndex = binarySearch(code);
                if (binaryIndex != -1) {
                    result = invoices.get(binaryIndex);
                }
                break;

            case 3:
                result = invoices.stream()
                        .filter(x -> x.getPartNumber().equalsIgnoreCase(code))
                        .findFirst()
                        .orElse(null);
                break;

            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        if (result == null) {
            System.out.println("Không tìm thấy hóa đơn!");
        } else {
            printHeader();
            printInvoice(result);
        }
    }

    public static int linearSearch(String code) {
        for (int i = 0; i < invoices.size(); i++) {
            if (invoices.get(i).getPartNumber().equalsIgnoreCase(code)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(String code) {
        int left = 0;
        int right = invoices.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            int compare = invoices.get(mid).getPartNumber().compareToIgnoreCase(code);

            if (compare == 0) {
                return mid;
            } else if (compare < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static void deleteInvoice() {
        String code = readString("Nhập mã mặt hàng cần xóa: ");

        int index = linearSearch(code);

        if (index == -1) {
            System.out.println("Không tìm thấy hóa đơn!");
        } else {
            invoices.remove(index);
            System.out.println("Đã xóa hóa đơn có mã: " + code);
        }
    }

    public static void swap(int i, int j) {
        Invoice temp = invoices.get(i);
        invoices.set(i, invoices.get(j));
        invoices.set(j, temp);
    }

    public static String readString(String message) {
        String input;

        do {
            System.out.print(message);
            input = sc.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Không được để trống!");
            }

        } while (input.isEmpty());

        return input;
    }

    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số nguyên!");
            }
        }
    }

    public static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Vui lòng nhập số thực!");
            }
        }
    }
}