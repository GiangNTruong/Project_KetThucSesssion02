package business.design;

public interface RoleAdmin extends Loginout {
    void addAccount();
    void viewAccountList();
    void toggleAccountStatus();
    void viewStatistics();
    void viewProjectByContract();
}
