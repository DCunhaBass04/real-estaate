package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.application.controller.authorization.AuthenticationController;
import pt.ipp.isep.dei.esoft.project.domain.Users.Client;

import pt.ipp.isep.dei.esoft.project.domain.Users.User;
import pt.ipp.isep.dei.esoft.project.repository.*;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.GenerateAndSavePassword;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The SignUpController class represents a controller for signing up a new user.
 */
public class SignUpController {
    private AuthenticationRepository authenticationRepository = null;
    private ClientRepository clientRepository = null;
    private UserRepository userRepository = null;

    /**
     * Constructs a SignUpController instance with default repositories.
     */
    public SignUpController() {
        getAuthenticationRepository();
        getOwnerRepository();
        getUserRepository();
    }

    /**
     * Constructs a SignUpController instance with the specified repositories.
     *
     * @param authenticationRepository The authentication repository.
     * @param clientRepository         The client repository.
     */
    public SignUpController(AuthenticationRepository authenticationRepository, ClientRepository clientRepository, UserRepository userRepository) {
        this.authenticationRepository = authenticationRepository;
        this.clientRepository = clientRepository;
        this.userRepository = userRepository;
    }

    private ClientRepository getOwnerRepository() {
        if (clientRepository == null) {
            Repositories repositories = Repositories.getInstance();
            clientRepository = repositories.getClientRepository();
        }
        return clientRepository;
    }

    /**
     * Retrieves the authentication repository instance, lazily initializing it if necessary.
     *
     * @return The authentication repository instance.
     */
    private AuthenticationRepository getAuthenticationRepository() {
        if (authenticationRepository == null) {
            Repositories repositories = Repositories.getInstance();
            authenticationRepository = repositories.getAuthenticationRepository();
        }
        return authenticationRepository;
    }

    private UserRepository getUserRepository() {
        if (userRepository == null) {
            Repositories repositories = Repositories.getInstance();
            userRepository = repositories.getUserRepository();
        }
        return userRepository;
    }
    /**
     * Requests user data for registration.
     *
     * @return The created client object.
     */
    public Client requestData() {
        Client user;
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        System.out.printf("Insert the data in the appropriate format.%nA user cannot be registered more than once.%n");
        System.out.printf("Please insert your data.%n");

        String name = verifyInput.verifyComplexString(input, "Name");

        int passportNumber = verifyInput.verifyInt(input, "Passport Number");

        String socialSecurityNumber = verifyInput.verifySimpleString(input, "Social Security Number");

        String email = getEmail();

        String phoneNumber = verifyInput.verifySimpleString(input, "Phone Number");

        String pwd = getPassword();

        user = new Client(name, passportNumber, socialSecurityNumber, email, phoneNumber, pwd);

        return user;
    }

    /**
     * Requests a password from the user.
     *
     * @return The user-entered password.
     */
    public String getPassword() {
        List<String> options = new ArrayList<String>();
        options.add("Make a password. (manual)");
        options.add("Generate password. (automated)");
        String choice = "";
        VerifyInput verifyInput = new VerifyInput();
        Scanner input = new Scanner(System.in);
        System.out.printf("%nSelect an option.%n");
        int index = 0;
        for (Object o : options) {
            index++;
            System.out.println(index + ". " + o.toString());
        }
        boolean validChoice = false;
        int option;
        do {
            option = verifyInput.verifyInt(input, "a valid option");
            if (option < 1 || option > options.size()) {
                System.out.println("Option unavailable.");
            } else {
                choice = options.get(option - 1);
                System.out.printf("%s", choice);
                validChoice = true;
            }
        } while (!validChoice);
        GenerateAndSavePassword generateAndSavePassword = new GenerateAndSavePassword();
        String password = "XXXXXXX";
        if ("Make a password. (manual)".equals(choice)) {
            boolean validPassword = false;
            do {
                System.out.printf("%nYour password must be 7 alphanumeric characters long, including at least 3 capital letters and 2 digits.%n");
                password = Utils.readLineFromConsole("Password:\n");
                validPassword = generateAndSavePassword.verifyPassword(password);
            } while (!validPassword);
        }
        if ("Generate password. (automated)".equals(choice)) {
            password = generateAndSavePassword.generatePassword();
        }
        return password;
    }

    /**
     * Collects the email address from the user and verifies its validity.
     *
     * @return The valid email address entered by the user.
     */
    public String getEmail() {
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        String emailID = "";
        boolean valid = false;
        do {
            try {
                emailID = verifyInput.verifySimpleString(input, "Email");
                Email email = new Email(emailID);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.printf("Invalid email. Try again.%n");
            }
        } while (!valid);
        return emailID;
    }

    /**
     * Verifies if the provided client data is valid and does not already exist in the repository.
     *
     * @param client The client object to be verified.
     * @return True if the data is valid and unique, false otherwise.
     */
    public boolean verifyData(Client client) {
        boolean valid;
        String email = client.getEmail();
        valid = verifyIfNewUser(email);
        return valid;
    }

    /**
     * Verifies if the provided email address is associated with a new user (not already registered).
     *
     * @param email The email address to be checked.
     * @return True if the email address is associated with a new user, false otherwise.
     */
    public boolean verifyIfNewUser(String email) {
        boolean newUser = true;
        String otherEmail;
        List<Client> ownertList = clientRepository.getClients();
        for (int i = 0; i < ownertList.size() && newUser; i++) {
            otherEmail = ownertList.get(i).getEmail();
            if (otherEmail.equals(email)) {
                newUser = false;
            }
        }
        return newUser;
    }

    /**
     * Creates a new user by adding the client to the repository and setting up their authentication.
     *
     * @param user The client object representing the new user.
     */
    public void createNewUser(Client user) {

        Client copyOfUser = user.clone();

        addAuthentication(copyOfUser);

        clientRepository.add(copyOfUser);
        userRepository.add(copyOfUser);

        GenerateAndSavePassword generateAndSavePassword = new GenerateAndSavePassword();

        generateAndSavePassword.sendClientPassword(user);
    }

    /**
     * Adds authentication information for the new user, granting them the appropriate role.
     *
     * @param copyOfUser The client object for which authentication information is added.
     */
    public void addAuthentication(Client copyOfUser) {
        String name = copyOfUser.getName(), email = copyOfUser.getEmail(), password = copyOfUser.getPassword();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_OWNER, AuthenticationController.ROLE_OWNER);
        authenticationRepository.addUserWithRole(name, email, password, AuthenticationController.ROLE_OWNER);
    }
}