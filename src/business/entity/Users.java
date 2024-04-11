package business.entity;

import business.utils.InputMethods;

import java.io.Serializable;


public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    private int userId;
    private String username;
    private String password;
    private RoleName role = RoleName.USER;
    private boolean status;

    public Users() {
    }

    public Users(int userId, String username, String password, RoleName role, boolean status) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void inputUsername(){
        String usernamePattern = "^[a-zA-Z0-9]{6,}$";
        String input = InputMethods.getString();
        while (!input.matches(usernamePattern)) {
            System.out.println("Tên đăng nhập phải có ít nhất 6 kí tự, không có kí tự đặc biệt, không để trống và không trùng lặp. Hãy thử lại.");
            input = InputMethods.getString();
        }
        this.username = input;
    }

    public void inputPassword(){
        String passwordPattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        String input = InputMethods.getString();
        while (!input.matches(passwordPattern)) {
            System.out.println("Mật khẩu phải có ít nhất 8 kí tự, bao gồm cả chữ và số. Hãy thử lại.");
            input = InputMethods.getString();
        }
        this.password = input;
    }

    public void inputStatus(){
        Boolean input = InputMethods.getBoolean();
        while (input == null) {
            System.out.println("Nhập lại do không hợp lệ. Hãy nhập true (hoạt động) hoặc false (không hoạt động).");
            input = InputMethods.getBoolean();
        }
        this.status = input;
    }

    public RoleName inputRole() {
        String input = InputMethods.getString();
        while (!input.matches("ADMIN|MANAGER|USER")) {
            System.out.println("Vai trò không hợp lệ. Hãy nhập ADMIN, MANAGER hoặc USER.");
            input = InputMethods.getString();
        }
        return RoleName.valueOf(input);
    }


    public void displayData(){
        System.out.println("Users{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", status=" + (status?"Hoạt động":"Không hoạt động") +
                '}');
    }


}
