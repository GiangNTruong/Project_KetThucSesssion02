package presentation;

import business.design.RoleAdmin;
import business.designImpl.RoleAdminImplement;
import business.entity.Users;
import business.utils.InputMethods;

public class MenuAdmin {
    private static MenuAdmin menuAdmin = new MenuAdmin();
    private static RoleAdmin roleAdmin = new RoleAdminImplement();
    private static Users currentUser; // Biến để lưu trữ người dùng hiện tại
    public static MenuAdmin getInstance(){
        return menuAdmin;
    }
    private MenuAdmin(){

    }
    public static void displayMenuAdmin(){
        while (true) {
            System.out.println("========== MENU ADMIN ==========");
            System.out.println("1. Đăng xuất");
            System.out.println("2. Quản trị tài khoản");
            System.out.println("3. Báo cáo thống kê");
            System.out.println("4. Thoát");
            System.out.println("Vui lòng chọn từ 1-4:");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                   Main.login();
                   break;
                case 2:
                    manageAccounts();
                    break;
                case 3:
                    statistic();
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Nhập ko hợp lệ từ 1-4 mà , nhập lại");
            }
            }
        }
    public static void manageAccounts() {
        while (true){
            System.out.println("========== QUẢN TRỊ TÀI KHOẢN ==========");
            System.out.println("1. Thêm mới tài khoản");
            System.out.println("2. Xem danh sách tài khoản");
            System.out.println("3. Khóa mở tài khoản");
            System.out.println("4. Quay lại menu chính");
            System.out.println("Vui lòng chọn từ 1-4: ");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    roleAdmin.addAccount();
                    break;
                case 2:
                    roleAdmin.viewAccountList();
                    break;
                case 3:
                    roleAdmin.toggleAccountStatus();
                    break;
                case 4:
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }
    public static void statistic(){
        while (true){
            System.out.println("========== QUẢN TRỊ TÀI KHOẢN ==========");
            System.out.println("1. Thống kê số lượng");
            System.out.println("2. Xem danh sách dự án hợp đồng");
            System.out.println("3. QUay lại Menu chính");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    roleAdmin.viewStatistics();
                    break;
                case 2:
                    roleAdmin.viewProjectByContract();
                    break;
                case 3:
                    System.out.println("Quay lại Menu chính");
                    return;
                default:
                    System.out.println("không hợp lệ , nhập lại ");
            }
        }
    }

}
