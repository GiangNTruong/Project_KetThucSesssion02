package business.designImpl;

import business.design.RoleCustomer;
import business.entity.Contract;
import business.entity.Customer;
import business.entity.Project;
import business.entity.Users;
import business.utils.IOFile;

import business.utils.InputMethods;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;

import java.util.List;

import static business.designImpl.ManageContractImpl.contractList;
import static business.designImpl.ManageCustomerImpl.customerList;
import static business.designImpl.ManageProjectImpl.projectList;

public class RoleCustomerImplement implements RoleCustomer {

    public static List<Users> usersList;

    public static Users usersLogin;
    static {
        usersLogin =  IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
        usersList = IOFile.readFromFile(IOFile.USER_PATH);

        // kiem tra khoi tao khi userList null
        if (usersList == null){
            usersList = new ArrayList<>();
        }

    }


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
    public void logout() {
       usersLogin=null;
     IOFile.writetoUserLogin(IOFile.USERLOGIN_PATH,usersLogin);
        System.out.println("Đã đăng xuất");
    }
        @Override
        public void viewPersonalInfo() {
            // Tìm kiếm thông tin cá nhân của người dùng đã đăng nhập
            for (Customer customer : customerList) {
                if (customer.getUserID() == usersLogin.getUserId()) {
                    System.out.println("Customer ID: " + customer.getCustomerId());
                    System.out.println("Name: " + customer.getCustomerName());
                    System.out.println("Birthday: " + customer.getBirthday());
                    System.out.println("Sex: " + (customer.isSex() ? "Male" : "Female"));
                    System.out.println("Address: " + customer.getAddress());
                    System.out.println("Email: " + customer.getEmail());
                    System.out.println("Phone Number: " + customer.getPhoneNumber());
                    System.out.println("Note: " + customer.getNote());
                    System.out.println("Priority: " + customer.getPriority());
                    break;
            }
        }

    }

    @Override
    public void viewAccountInfo() {
        usersLogin =  IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
        if (usersLogin != null) {
            System.out.println("User ID: " + usersLogin.getUserId());
            System.out.println("Username: " + usersLogin.getUsername());
            System.out.println("Role: " + usersLogin.getRole());
            System.out.println("Status: " + usersLogin.isStatus());
        } else {
            System.out.println("Không có thông tin người dùng để hiển thị.");
        }
    }

    @Override
    public void changePassword() {
        usersLogin =  IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
        if (usersLogin != null) {
            while (true) {
                System.out.println("Nhập mật khẩu cũ:");
                String oldPassword = InputMethods.getString();
                // Kiểm tra mật khẩu cũ
                if (oldPassword.isEmpty() || !BCrypt.checkpw(oldPassword, usersLogin.getPassword())) {
                    System.out.println("Mật khẩu cũ không hợp lệ. Vui lòng nhập lại.");
                } else {
                    System.out.println("Nhập mật khẩu mới:");
                    String newPassword = InputMethods.getString();
                    // Kiểm tra mật khẩu mới phải khác mật khẩu cũ
                    if (newPassword.equals(oldPassword)) {
                        System.out.println("Mật khẩu mới phải khác mật khẩu cũ. Vui lòng nhập lại.");
                    } else {
                        // Mã hóa mật khẩu mới trước khi lưu trữ
                        String hashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                        usersLogin.setPassword(hashedPassword);
                        System.out.println("Đổi mật khẩu thành công");
                        for (Users user : usersList) {
                            if (user.getUserId() == usersLogin.getUserId()) {
                                user.setPassword(hashedPassword);
                                break;
                            }
                        }

                        IOFile.writetoUserLogin(IOFile.USERLOGIN_PATH, usersLogin);
                        IOFile.writeToFile(IOFile.USER_PATH, usersList);
                        break;
                    }
                }
            }
        } else {
            System.out.println("Không có người dùng đã đăng nhập để thay đổi mật khẩu.");
        }
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
        usersLogin = IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
        return usersList.stream().filter(users -> users.getUsername().equals(username)).findFirst().orElse(null);
    }

}
