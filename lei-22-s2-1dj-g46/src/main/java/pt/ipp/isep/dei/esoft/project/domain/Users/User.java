package pt.ipp.isep.dei.esoft.project.domain.Users;

public interface User {
    String getName();
    String getEmail();
    String getPassword();
    User clone();
}
