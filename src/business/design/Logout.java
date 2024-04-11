package business.design;

import business.entity.Users;

public interface Logout {
    Users login(String username, String password);
    void logout();
}


