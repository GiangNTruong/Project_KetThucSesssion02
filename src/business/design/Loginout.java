package business.design;

import business.entity.Users;

public interface Loginout {
    Users login(String username, String password);
    void logout();
}


