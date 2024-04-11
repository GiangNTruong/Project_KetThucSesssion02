package presentation;

import business.design.Logout;
import business.designImpl.RoleCustomerImplement;
import business.entity.RoleName;
import business.entity.Users;
import business.utils.IOFile;
import business.utils.InputMethods;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class Main {
    private static MenuCustomer menuCustomer = new MenuCustomer();
    private static MenuManger menuManger = new MenuManger();
    private static Logout loginAccount = new RoleCustomerImplement();
    public static Users user = null;
    public static void main(String[] args) {
        List<Users> users = IOFile.readFromFile(IOFile.USER_PATH);

        // Kiểm tra xem tài khoản admin đã tồn tại chưa
        boolean adminExists = users.stream().anyMatch(user -> user.getUsername().equals("admin123"));

        // Nếu tài khoản admin chưa tồn tại, tạo mới và thêm vào danh sách
        if (!adminExists) {
            Users admin = new Users();
            admin.setUserId(1);
            admin.setUsername("admin123");
            admin.setPassword(BCrypt.hashpw("admin123", BCrypt.gensalt(5)));
            admin.setRole(RoleName.ADMIN);
            users.add(admin);
            IOFile.writeToFile(IOFile.USER_PATH, users);
        }

        while (true){
            System.out.println("=================MENU=====================");
            System.out.println("1. Dang nhap");
            System.out.println("2. Thoat");
            System.out.println("Nhap chuc nang");
            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Thoát chương trình");
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng nhập lại");
            }
        }
    }

    public static void login() {
        Users userLogin = null;
        // Vòng lặp do-while sẽ tiếp tục cho đến khi người dùng nhập đúng thông tin đăng nhập hoặc chọn thoát
        do {
            System.out.println("============Đang nhập ================");
            System.out.println("Nhập username : ");
            String username = InputMethods.getString();
            System.out.println("Nhập password : ");
            String password = InputMethods.getString();

            // Thử đăng nhập với username và password đã nhập
            userLogin = loginAccount.login(username, password);
            // Nếu đăng nhập không thành công (userLogin == null)
            if (userLogin == null) {
                System.err.println("Tên đăng nhập hoặc mật khẩu không chính xác");
                System.out.println("1. Tiếp tục đăng nhập");
                System.out.println("2. Thoát");
                System.out.println("------Nhập lựa chọn--------");
                byte choice = InputMethods.getByte();
                switch (choice) {
                    case 1:
                      login();
                      break;
                    case 2:
                       System.exit(0);
                    default:
                        System.err.println("Lựa chọn không hợp lệ");
                }
            }
        } while (userLogin == null);

        // Nếu đăng nhập thành công, kiểm tra vai trò của người dùng và thực hiện hành động tương ứng
        if (userLogin.getRole().equals(RoleName.ADMIN)){
            user = userLogin;
            MenuAdmin.getInstance().displayMenuAdmin();
        }else if (userLogin.getRole().equals(RoleName.USER)){
            if (!userLogin.isStatus()){
                System.err.println("Tài khoản đã bị khóa, vui lòng liên hệ admin(0966666688)");
            }else {
                user =userLogin;
                menuCustomer.displayMenuCustomer();
            }
        }else if (userLogin.getRole().equals(RoleName.MANAGER)){
            if (!userLogin.isStatus()){
                System.err.println("Tài khoản đã bị khóa, vui lòng liên hệ admin(0966666688)");
            }else {
                user = userLogin;
                menuManger.displayMenuManager();
            }
        }else {
            System.err.println("Ko có quyền truy cập");
        }
    }
}
