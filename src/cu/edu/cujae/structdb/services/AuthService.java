package cu.edu.cujae.structdb.services;

import cu.edu.cujae.structdb.dto.UserDTO;
import cu.edu.cujae.structdb.utils.exception.ConnectionFailedException;

public class AuthService {
    public enum LoginResult { correct, wrongUsername, wrongPassword };
    private UserDTO currentUser;

    public LoginResult login(UserDTO credentials) throws ConnectionFailedException {
        UserDTO response = ServicesLocator.userServices().getByUsername(credentials.getUsername());

        if (response == null) {
            return LoginResult.wrongUsername;
        }

        if (!ServicesLocator.userServices().verifyPassword(credentials.getPassword(), response.getPassword())) {
            return LoginResult.wrongPassword;
        }

        credentials.setId(response.getId());
        credentials.setRol(response.getRol());
        currentUser = response;
        return LoginResult.correct;
    }

    public UserDTO getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        currentUser = null;
    }

    public boolean isCurrentUser(int id) {
        if (id == currentUser.getId()) {
            return true;
        }
        return false;
    }
}

