package presentation;

import business.design.RoleCustomer;
import business.designImpl.RoleCustomerImplement;
import business.utils.InputMethods;

public class MenuCustomer {
    private static RoleCustomer roleCustomer = new RoleCustomerImplement();
    public void displayMenuCustomer (){
     do {
         System.out.println("================");
         System.out.println("***Menu Khách hàng***");
         System.out.println("===================");
         System.out.println("1.Quản lý hợp đồng");
         System.out.println("2. Quản lý tài khoản ");
         System.out.println("3. Đăng xuất");
         System.out.println("Vui lòng chọn từ 1-3");
         byte choice = InputMethods.getByte();
         switch (choice){
             case 1:
                 displayContractCustomer();
                 break;
             case 2:
                 displayAccountCustomer();
                 break;
             case 3:
                 Main.login();
                 break;
             default:
                 System.out.print("Nhập không hợp lệ , vui lòng nhập từ 1-3 : ");
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
                    break;
                case 2:
                    System.out.println("Nhập tên đăng nhập:");
                    String username = InputMethods.getString();
                    roleCustomer.viewAccountInfo(username);
                    break;
                    
                case 3:
                    System.out.println("Nhập tên đăng nhập:");
                    String usernameChangePass = InputMethods.getString();
                    roleCustomer.changePassword(usernameChangePass);
                    break;
                case 4:
                    System.out.println("Thoát chương trình");
                    return;
            }
        }while (true);
    }
}
