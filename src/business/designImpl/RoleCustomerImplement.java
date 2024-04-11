package business.designImpl;

import business.design.RoleCustomer;
import business.entity.Contract;
import business.entity.Project;
import business.entity.Users;
import business.utils.IOFile;

import business.utils.InputMethods;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

import java.util.List;

public class RoleCustomerImplement implements RoleCustomer {
    static List<Project> projectList;
    static List<Contract> contractList;
    public static List<Users> usersList;
    static {
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
        projectList = IOFile.readFromFile(IOFile.PROJECT_PATH);
        contractList = IOFile.readFromFile(IOFile.CONTRACT_PATH);
        // kiem tra khoi tao khi userList null
        if (usersList == null){
            usersList = new ArrayList<>();
        }
        if (projectList == null){
            projectList = new ArrayList<>();
        }
        if (contractList == null){
            contractList = new ArrayList<>();
        }
    }
//    @Override
//    public Users login(String username, String password) {
//        Users userLogin  = getUserFormUsername(username);
//        if (userLogin==null){
//            return null;
//        }
//        // Kiểm tra xem mật khẩu đã lưu trữ có null hoặc không được định dạng chính xác không
//        if (userLogin.getPassword() == null || !userLogin.getPassword().matches("\\$2[ayb]\\$.{56}")) {
//            System.err.println("Mật khẩu đã lưu trữ không được định dạng chính xác");
//            return null;
//        }
//        boolean checkLogin = BCrypt.checkpw(password,userLogin.getPassword()); // kiem tra mat khau khop hay khong
//        if (checkLogin){
//            return userLogin;
//        }
//        return null;
//    }


    @Override
    public Users login(String username, String password) {
        Users userLogin  = getUserFormUsername(username);
        if (userLogin==null){
            return null;
        }
        boolean checkLogin = BCrypt.checkpw(password,userLogin.getPassword()); // kiem tra mat khau khop hay khong
        if (checkLogin){
            return userLogin;
        }
        return null;
    }

    @Override
    public void logout(Users currentUser) {
        usersList.remove(currentUser);
        IOFile.writeToFile(IOFile.USER_PATH,usersList );
        System.out.println("Đã đăng xuất");
    }

    @Override
    public void viewPersonalInfo() {

    }

    @Override
    public void viewAccountInfo(String username) {
        for (Users user : usersList) {
            if (user.getUsername().equals(username)) {
                System.out.println("User ID: " + user.getUserId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println("Status: " + user.isStatus());
                break;
            }
        }
    }


    @Override
    public void changePassword(String username) {
        for (Users user : usersList) {
            if (user.getUsername().equals(username)) {
                System.out.println("Nhập mật khẩu mới:");
                String newPassword = InputMethods.getString();
                // Mã hóa mật khẩu mới trước khi lưu trữ
                String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                user.setPassword(hashedPassword);
                System.out.println("Đổi mật khẩu thành công");
                break;
            }
        }
        // Ghi lại danh sách người dùng sau khi thay đổi mật khẩu
        IOFile.writeToFile(IOFile.USER_PATH, usersList);
    }


    @Override
    public void viewContract() {
        if (contractList==null || contractList.isEmpty()){
            System.err.println("Không có hợp đồng nào để hiển thị");
            return;
        }
        System.out.println(" Danh sách hợp đồng ");
        for (Contract contract : contractList){
            contract.displayData();
        }
    }

    @Override
    public void viewProject() {
        if (projectList==null || projectList.isEmpty()){
            System.err.println("Không có dự án nào để hiển thị");
            return;
        }
        System.out.println(" Danh sách dự án ");
        for (Project project : projectList){
            project.displayData();
        }
    }
    private Users getUserFormUsername(String username){
        return usersList.stream().filter(users -> users.getUsername().equals(username)).findFirst().orElse(null);
    }

}
