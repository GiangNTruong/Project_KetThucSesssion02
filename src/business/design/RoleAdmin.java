package business.design;

public interface RoleAdmin extends Logout {
    void addAccount();
    void viewAccountList();
    void toggleAccountStatus();
    void viewStatistics();
    void viewProjectByContract();
}
