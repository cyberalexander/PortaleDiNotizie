package by.leonovich.notizieportale.services;

import by.leonovich.notizieportale.domain.User;

import java.util.List;

/**
 * Created by alexanderleonovich on 29.04.15.
 */
public interface IUserService {

    boolean checkUser(String email, String password);

    User authenticationProcess(String nameOfColum, String cretery);

    boolean registerNewUser(User user);

    User getUserByEmail(String email);

    void updateUserInformation(User user);
}
