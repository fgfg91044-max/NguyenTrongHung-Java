package VDmoi.com;

public class Vd {

    public static void main(String[] args) {
        String path = "D:/music/bolero/longme.mp3";
        
        try {
            System.out.println("Đường dẫn: " + path);
            
            // 1. Lấy longme.mp3
            int viTriGachCuoi = path.lastIndexOf("/");
            String tenFile = path.substring(viTriGachCuoi + 1);
            System.out.println("Tên file là: " + tenFile);
            
            // 2. Lấy longme
            int viTriDauCham = path.lastIndexOf(".");
            String tenBaiHat = path.substring(viTriGachCuoi + 1, viTriDauCham);
            System.out.println("Tên bài hát là: " + tenBaiHat);
            
        } catch (Exception ex) {
            System.out.println("Có lỗi khi xử lý chuỗi!");
            ex.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }
}