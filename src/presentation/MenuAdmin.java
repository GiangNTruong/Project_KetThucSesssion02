package presentation;

import business.design.RoleAdmin;
import business.designImpl.RoleAdminImplement;
import business.entity.Users;
import business.utils.InputMethods;

import javax.swing.text.StyleConstants;

public class MenuAdmin {
    private static MenuAdmin menuAdmin = new MenuAdmin();
    private static RoleAdmin roleAdmin = new RoleAdminImplement();
    public static MenuAdmin getInstance(){
        return menuAdmin;
    }
    private MenuAdmin(){

    }
    public static void displayMenuAdmin(){
        while (true) {
            System.out.println("\u001B[31m\u001B[40m========== MENU ADMIN ==========\u001B[0m");
            System.out.println("\u001B[31m\u001B[40m 1. Đăng xuất \u001B[0m");
            System.out.println("\u001B[31m\u001B[40m 2. Quản trị tài khoản \u001B[0m");
            System.out.println("\u001B[31m\u001B[40m 3. Báo cáo thống kê\u001B[0m");
            System.out.println("\u001B[31m\u001B[40m Vui lòng chọn từ 1-4:\u001B[0m");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                   roleAdmin.logout();
                   break;
                case 2:
                    manageAccounts();
                    break;
                case 3:
                    statistic();
                    break;
                default:
                    System.err.println("Nhập ko hợp lệ từ 1-4 mà , nhập lại");
            }
            if (choice==1){
                break;
            }
            }
        }
    public static void manageAccounts() {
        while (true){
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"========== QUẢN TRỊ TÀI KHOẢN =========="+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"1. Thêm mới tài khoản"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"2. Xem danh sách tài khoản"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"3. Khóa mở tài khoản"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"4. Quay lại menu chính"+Main.ANSI_RESET);
            System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"Vui lòng chọn từ 1-4: "+Main.ANSI_RESET);
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
            System.out.println(Main.BLACK_BG+"========== QUẢN TRỊ TÀI KHOẢN =========="+Main.ANSI_RESET);
            System.out.println(Main.BLACK_BG+"1. Thống kê số lượng"+Main.ANSI_RESET);
            System.out.println(Main.BLACK_BG+"2. Xem danh sách dự án hợp đồng"+Main.ANSI_RESET);
            System.out.println(Main.BLACK_BG+"3. QUay lại Menu chính"+Main.ANSI_RESET);
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
