package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Users.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type User repository.
 */
public class UserRepository implements Serializable {
    private List<User> users = new ArrayList<>();

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty(){return users.isEmpty();}

    /**
     * Add optional.
     *
     * @param user the user
     * @return the optional
     */
    public Optional<User> add(User user){
        Optional<User> newUser = Optional.empty();
        boolean operationSuccess = false;

        if (validateUser(user)) {
            newUser = Optional.of(user.clone());
            operationSuccess = users.add(newUser.get());
        }

        if (!operationSuccess) {
            newUser = Optional.empty();
        }

        return newUser;
    }

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the user by email
     */
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        return null;
    }
    private boolean validateUser(User user) {return !users.contains(user);}

    /**
     * Remove.
     *
     * @param o the o
     */
    public void remove(Object o){users.remove(o);}

    /**
     * Get users list.
     *
     * @return the list
     */
    public List<User> getUsers(){return List.copyOf(users);}
}
