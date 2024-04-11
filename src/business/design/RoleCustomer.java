package business.design;

public interface RoleCustomer extends Logout {
    void viewPersonalInfo();
    void viewAccountInfo(String username);
    void changePassword(String username);
    void viewContract();
    void viewProject();
}
