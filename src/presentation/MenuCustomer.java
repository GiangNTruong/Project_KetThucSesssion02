package presentation;

import business.design.RoleCustomer;
import business.designImpl.RoleCustomerImplement;
import business.utils.InputMethods;

public class MenuCustomer {
    private static RoleCustomer roleCustomer = new RoleCustomerImplement();
    public void displayMenuCustomer (){
     do {
         System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"================"+Main.ANSI_RESET);
         System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"***Menu Khách hàng***"+Main.ANSI_RESET);
         System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"==================="+Main.ANSI_RESET);
         System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"1.Quản lý hợp đồng"+Main.ANSI_RESET);
         System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"2. Quản lý tài khoản "+Main.ANSI_RESET);
         System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"3. Đăng xuất"+Main.ANSI_RESET);
         System.out.println(Main.ANSI_CYAN+Main.BLACK_BG+"Vui lòng chọn từ 1-3"+Main.ANSI_RESET);
         byte choice = InputMethods.getByte();
         switch (choice){
             case 1:
                 displayContractCustomer();
                 break;
             case 2:
                 displayAccountCustomer();
                 break;
             case 3:
                 roleCustomer.logout();
                 break;
             default:
                 System.out.print("Nhập không hợp lệ , vui lòng nhập từ 1-3 : ");
         }
         if (choice==3){
             break;
         }
     }while (true);
    }
    private void displayContractCustomer (){
        do {
            System.out.println("================");
            System.out.println("***Quản lý hợp đồng***");
            System.out.println("===================");
            System.out.println("1.Xem hợp đồng");
            System.out.println("2. Xem dự án ");
            System.out.println("3. QUay lại menu chính");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    roleCustomer.viewContract();
                    break;
                case 2:
                    roleCustomer.viewProject();
                    break;
                case 3:
                    System.out.println("Thoát chương trình");
                    return;
            }
        }while (true);
    }
    private void displayAccountCustomer (){
        do {
            System.out.println("================");
            System.out.println("***Quản lý tài khoản***");
            System.out.println("===================");
            System.out.println("1.Xem thông tin cá nhân");
            System.out.println("2. Xem thông tin tài khoản ");
            System.out.println("3. Đổi mật khẩu ");
            System.out.println("4. Quay lại menu chính");
            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    roleCustomer.viewPersonalInfo();
                    break;
                case 2:
                    roleCustomer.viewAccountInfo();
                    break;
                    
                case 3:
                    roleCustomer.changePassword();
                    break;
                case 4:
                    System.out.println("Thoát chương trình");
                    return;
            }
        }while (true);
    }
}
