package business.designImpl;

import business.design.RoleAdmin;
import business.entity.*;
import business.utils.IOFile;
import business.utils.InputMethods;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static business.designImpl.ManageContractImpl.contractList;
import static business.designImpl.ManageCustomerImpl.customerList;
import static business.designImpl.ManageProjectImpl.projectList;
import static business.designImpl.RoleCustomerImplement.usersLogin;


public class RoleAdminImplement implements RoleAdmin {
    static List<Users>usersList;

    static {
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
        // kiem tra khoi tao khi userList null
        if (usersList == null){
            usersList = new ArrayList<>();
        }

    }
    @Override
    public Users login(String username, String password) {
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
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
        IOFile.writetoUserLogin(IOFile.USERLOGIN_PATH,usersLogin );
        System.out.println("Đã đăng xuất ");
    }

    @Override
    public void addAccount() {
        System.out.println("Nhập bao nhiều tài khoản thêm mới");
        byte n = InputMethods.getByte();
        for (int i = 0; i < n; i++) {
            Users newUser = new Users();
            newUser.setUserId(getNewId());
            System.out.println("Nhập thông tin cho tài khoản mới:");
            System.out.println("Nhập tên đăng nhập:");
            newUser.inputUsername();
            System.out.println("Nhập mật khẩu:");
            String password = InputMethods.getString();
            // Mã hóa mật khẩu trước khi lưu trữ
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            newUser.setPassword(hashedPassword);
            System.out.println("Nhập vai trò (ADMIN, MANAGER, USER):");
            RoleName role = newUser.inputRole(); // Lấy vai trò từ người dùng
            newUser.setRole(role); // Thiết lập vai trò cho người dùng mới
            System.out.println("Nhập trạng thái (true hoặc false):");
            newUser.inputStatus();
            usersList.add(newUser);
        }
        IOFile.writeToFile(IOFile.USER_PATH,usersList);
        System.out.println("Thêm mới thành công");
    }


    @Override
    public void viewAccountList() {
        usersList = IOFile.readFromFile(IOFile.USER_PATH);
        if (usersList==null){
            System.out.println("Khong có tai khoan nào để hiển thị");
        }
        System.out.println("Danh sách tài khoản");
        for (Users users: usersList){
            users.displayData();
        }
    }

    @Override
    public void toggleAccountStatus() {
        viewAccountList();
        System.out.println("Nhập mã tài khoản cần thay đổi trạng thái : ");
        byte idStatus = InputMethods.getByte();
        for (Users users: usersList){
            if (users.getUserId()==idStatus){
                users.setStatus(!users.isStatus());
                System.out.println("Trạng thái tài khoản đã cập nhật");
                break;
            }
        }
        IOFile.writeToFile(IOFile.USER_PATH,usersList);
    }

    @Override
    public void viewStatistics() {
        System.out.println("Số lượng khách hàng: " + customerList.size());
        System.out.println("Số lượng hợp đồng: " + contractList.size());
        System.out.println("Số lượng dự án: " + projectList.size());
    }

    public void viewProjectByContract() {
        System.out.println("Danh sách hợp đồng:");
        for (Contract contract : contractList) {
            System.out.println("ID: " + contract.getContractId() + ", Tên: " + contract.getContractName());
        }
        System.out.println("Nhập ID của hợp đồng bạn muốn xem danh sách dự án:");
        Integer id = InputMethods.getInteger();
        Contract contract = findContractById(id);
        if (contract != null) {
            boolean check = false; // kiểm tra xem có dự án nào được tìm thấy hay không
            System.out.println("Danh sách dự án theo hợp đồng:");
            for (Project project : projectList) {
                if (project.getContractId() == contract.getContractId()) {
                    System.out.println("ID : " + project.getProjectId()+" | Name Project : " + project.getProjectName());
                    check = true;
                }
            }
            if (!check) {
                System.err.println("Không có dự án nào theo hợp đồng này.");
            }
        } else {
            System.err.println("Không tìm thấy hợp đồng với ID đã cung cấp.");
        }
    }




    private Contract findContractById(Integer id){
        return contractList.stream().filter(contract -> contract.getContractId() == id).findFirst().orElse(null);
    }

    private Users getUserFormUsername(String username){

        return usersList.stream().filter(users -> users.getUsername().equals(username)).findFirst().orElse(null);
    }
    private int getNewId(){
        int max = usersList.stream().map(Users::getUserId).max(Comparator.naturalOrder()).orElse(0);
        return max+1;
    }

}
