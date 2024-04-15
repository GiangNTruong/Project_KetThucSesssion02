package presentation;

import business.design.Loginout;
import business.designImpl.RoleCustomerImplement;
import business.entity.RoleName;
import business.entity.Users;
import business.utils.IOFile;
import business.utils.InputMethods;

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
    public static final String YELLOW_BG = "\u001B[43m";
    private static MenuCustomer menuCustomer = new MenuCustomer();
    private static MenuManger menuManger = new MenuManger();
    private static Loginout loginAccount = new RoleCustomerImplement();
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

        // Đọc thông tin người dùng đã lưu từ tệp
        while (true) {
            System.out.println(ANSI_BLACK + WHITE_BG+" =================MENU====================="+ANSI_RESET);
            System.out.println(ANSI_BLACK +WHITE_BG+" 1. Đăng nhập \u001B[0m"+ANSI_RESET);
            System.out.println(ANSI_BLACK +WHITE_BG+" 2. Thoát \u001B[0m"+ANSI_RESET);
            System.out.println(ANSI_BLACK +WHITE_BG+"Nhập chức năng : "+ANSI_RESET);

            byte choice = InputMethods.getByte();
            switch (choice){
                case 1:
                    userPrincipal = IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
                    if (userPrincipal != null) {
                        handleUser(userPrincipal);
                    } else {
                        login();
                    }
                    break;
                case 2:
                    System.out.println("Thoát chương trình");
                    return;
                default:
                    System.err.println("Lựa chọn không hợp lệ, vui lòng nhập lại ");
            }
        }
    }
    public static void handleUser(Users userPrincipal) {
        if (userPrincipal.getRole().equals(RoleName.ADMIN)){
            MenuAdmin.getInstance().displayMenuAdmin();
        } else if (userPrincipal.getRole().equals(RoleName.USER)){
            if (!userPrincipal.isStatus()){
                System.err.println("Tài khoản đã bị khóa, vui lòng liên hệ admin()");
            } else {
                menuCustomer.displayMenuCustomer();
            }
        } else if (userPrincipal.getRole().equals(RoleName.MANAGER)){
            if (!userPrincipal.isStatus()){
                System.err.println("Tài khoản đã bị khóa, vui lòng liên hệ admin()");
            } else {
                menuManger.displayMenuManager();
            }
        } else {
            System.err.println("Ko có quyền truy cập");
        }

    }
    public static void login() {
        do {
            System.out.println(ANSI_PURPLE+"============Đang nhập ================\u001B[0m");
            System.out.println(ANSI_BLUE+"Nhập username : \u001B[0m");
            String username = InputMethods.getString();
            System.out.println(ANSI_BLUE+"Nhập password : \u001B[0m");
            String password = InputMethods.getString();

            // Thử đăng nhập với username và password đã nhập
            userPrincipal = loginAccount.login(username, password);

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

                IOFile.writetoUserLogin(IOFile.USERLOGIN_PATH,userPrincipal);
            }
        } while (userPrincipal == null);


        // kiểm tra vai trò của người dùng và thực hiện hành động tương ứng
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