package pt.ipp.isep.dei.esoft.project.repository;


import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.Serializable;

public class AuthenticationRepository implements Serializable {
    private static final AuthFacade authenticationFacade = new AuthFacade();

    public boolean doLogin(String email, String pwd) {
        return authenticationFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        authenticationFacade.doLogout();
    }

    public UserSession getCurrentUserSession() {
        return authenticationFacade.getCurrentUserSession();
    }

    /**
     * This method adds an available role.
     *
     */
    public boolean addUserRole(String id, String description) {
        return authenticationFacade.addUserRole(id, description);
    }

    /**
     * This method adds a user with a role.
     *
     * @param name The name of the user.
     * @param email The email of the user.
     * @param pwd The password of the user.
     * @param roleId The role of the user.
     */
    public boolean addUserWithRole(String name, String email, String pwd, String roleId) {
        return authenticationFacade.addUserWithRole(name, email, pwd, roleId);
    }

    public AuthFacade getAuthenticationFacade(){
        return authenticationFacade;
    }
}
