package presentation;

import business.design.Logout;
import business.designImpl.RoleAdminImplement;
import business.designImpl.RoleCustomerImplement;
import business.entity.RoleName;
import business.entity.Users;
import business.utils.IOFile;
import business.utils.InputMethods;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String BLACK_BG = "\u001B[40m";
    public static final String WHITE_BG = "\u001B[47m";
    private static MenuCustomer menuCustomer = new MenuCustomer();
    private static MenuManger menuManger = new MenuManger();
    private static Logout loginAccount = new RoleCustomerImplement();
    public static Users userPrincipal = null;
    public static void main(String[] args) {
//        List<Users> users = IOFile.readFromFile(IOFile.USER_PATH);
//
//        // Kiểm tra xem tài khoản admin đã tồn tại chưa
//        boolean adminExists = users.stream().anyMatch(user -> user.getUsername().equals("admin123"));
//
//        // Nếu tài khoản admin chưa tồn tại, tạo mới và thêm vào danh sách
//        if (!adminExists) {
//            Users admin = new Users();
//            admin.setUserId(1);
//            admin.setUsername("admin123");
//            admin.setPassword(BCrypt.hashpw("admin123", BCrypt.gensalt(5)));
//            admin.setRole(RoleName.ADMIN);
//            users.add(admin);
//            IOFile.writeToFile(IOFile.USER_PATH, users);
//        }

        while (true){
            System.out.println(ANSI_BLACK + WHITE_BG+" =================MENU====================="+ANSI_RESET);
            System.out.println(ANSI_BLACK +WHITE_BG+" 1. Đăng nhập \u001B[0m"+ANSI_RESET);
            System.out.println(ANSI_BLACK +WHITE_BG+" 2. Thoát \u001B[0m"+ANSI_RESET);
            System.out.println(ANSI_BLACK +WHITE_BG+"Nhập chức năng : "+ANSI_RESET);

            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    login();
                    break;
                case 2:
                    System.out.println("Thoát chương trình");
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng nhập lại ");
            }
        }
    }

    public static void login() {
        // Vòng lặp do-while sẽ tiếp tục cho đến khi người dùng nhập đúng thông tin đăng nhập hoặc chọn thoát
        do {
            System.out.println(ANSI_PURPLE+"============Đang nhập ================\u001B[0m");
            System.out.println(ANSI_BLUE+"Nhập username : \u001B[0m");
            String username = InputMethods.getString();
            System.out.println(ANSI_BLUE+"Nhập password : \u001B[0m");
            String password = InputMethods.getString();

            // Thử đăng nhập với username và password đã nhập
            userPrincipal = loginAccount.login(username, password);
            // Nếu đăng nhập không thành công (userPrincipal == null)
            if (userPrincipal == null) {
                System.err.println("Tên đăng nhập hoặc mật khẩu không chính xác");
                System.out.println("1. Tiếp tục đăng nhập");
                System.out.println("2. Thoát");
                System.out.println("------Nhập lựa chọn--------");
                byte choice = InputMethods.getByte();
                switch (choice) {
                    case 1:
                        continue;
                    case 2:
                        System.exit(0);
                    default:
                        System.err.println("Lựa chọn không hợp lệ");
                }
            } else {
                // Nếu đăng nhập thành công, lưu thông tin người dùng đã đăng nhập
                IOFile.writetoUserLogin(IOFile.USERLOGIN_PATH,userPrincipal);
            }
        } while (userPrincipal == null);

        // Kiểm tra vai trò của người dùng và thực hiện hành động tương ứng
        if (userPrincipal.getRole().equals(RoleName.ADMIN)){
            MenuAdmin.getInstance().displayMenuAdmin();
        } else if (userPrincipal.getRole().equals(RoleName.USER)){
            if (!userPrincipal.isStatus()){
                System.err.println("Tài khoản đã bị khóa, vui lòng liên hệ admin(0966666688)");
            } else {
                menuCustomer.displayMenuCustomer();
            }
        } else if (userPrincipal.getRole().equals(RoleName.MANAGER)){
            if (!userPrincipal.isStatus()){
                System.err.println("Tài khoản đã bị khóa, vui lòng liên hệ admin(0966666688)");
            } else {
                menuManger.displayMenuManager();
            }
        } else {
            System.err.println("Ko có quyền truy cập");
        }
    }

}
