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
        // Tìm kiếm customer tương ứng với userId
        usersLogin = IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
        Customer currentUser = customerList.stream().filter(customer -> customer.getUserID() == usersLogin.getUserId()).findFirst().orElse(null);

        if (currentUser == null) {
            System.out.println("Không tìm thấy khách hàng tương ứng với tài khoản này.");
            return;
        }

        // Lọc danh sách hợp đồng dựa trên customerId
        List<Contract> userContracts = contractList.stream()
                .filter(contract -> contract.getCustomerId() == currentUser.getCustomerId())
                .toList();

        // Nếu không tìm thấy hợp đồng nào, in thông báo và thoát
        if (userContracts.isEmpty()) {
            System.out.println("DANH SÁCH hợp đồng của bạn rỗng.");
            return;
        }

        // Hiển thị thông tin các hợp đồng
        userContracts.forEach(Contract::displayData);
    }



    @Override
    public void viewProject() {
        usersLogin = IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
        // tìm kiếm customer tương ứng với userId
        Customer currentUser = customerList.stream()
                .filter(customer -> customer.getUserID() ==  usersLogin.getUserId())
                .findFirst()
                .orElse(null);

        if (currentUser == null) {
            System.out.println("Không tìm thấy khách hàng tương ứng với tài khoản này.");
            return;
        }

        List<Contract> userContracts = contractList.stream()
                .filter(contract -> contract.getCustomerId() == currentUser.getCustomerId())
                .toList();

        // thông báo hợp đồng rỗng
        if (userContracts.isEmpty()) {
            System.out.println("DANH SÁCH hợp đồng của bạn rỗng.");
            return;
        }

        //  danh sách dự án dựa trên contractId và hiển thị thông tin
        userContracts.forEach(contract -> {
            projectList.stream()
                    .filter(project -> project.getContractId() == contract.getContractId())
                    .forEach(Project::displayData);
        });
    }

    private Users getUserFormUsername(String username){
        usersLogin = IOFile.readFromToUser(IOFile.USERLOGIN_PATH);
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
        return usersList.stream().filter(users -> users.getUsername().equals(username)).findFirst().orElse(null);
    }

}
