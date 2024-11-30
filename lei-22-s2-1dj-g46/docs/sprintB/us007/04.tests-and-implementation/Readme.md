# US 007 - Sign Up 

# 4. Tests 

**Test 1:** Check that an empty UserRepository is indeed empty. 
```java
@Test void ensureEmptyRepositoryIsEmpty() {
    UserRepository userRepository = Repositories.getInstance().getUserRepository();
    userRepository.isEmpty();
}
```


**Test 2:** Check that a User is successfully added to the User Repository. 
```java
@Test void ensureUserIsAddedToList(){
    UserRepository userRepository = new UserRepository();
    User user = new User("john doe", 5555, 3333, "john.doe@this.company.com", "8888-JOHNDOE-444", "password", "here");
    userRepository.add(user);
    userRepository.getUsers().contains(user);
}
```
**Test 3:** Check that a User without Address is successfully added to the User Repository.
```java
@Test void ensureUserIsAddedToList(){
    UserRepository userRepository = new UserRepository();
    User user = new User("john doe", 5555, 3333, "john.doe@this.company.com", "8888-JOHNDOE-444", "password");
    userRepository.add(user);
    userRepository.getUsers().contains(user);
}  
 ```


# 5. Construction (Implementation)


## Class SignUpController 

```java
public void createUser(String name, int ccNumber, int taxNumber, String email, String phone, String address) {
    User newUser(name, ccNumber, taxNumber, email, phone, address);
    if(verifyIfNewUser(user)){
        GenerateAndSavePassword generateAndSavePassword = new GenerateAndSavePassword();
        generateAndSavePassword.sendPassword(user);
        UserRepository userRepository = Repositories.getInstance().getUserRepository();
    --> userRepository.add(new User(user, password));
        AuthenticationRepository authenticationRepository = Repositories.getInstance().getAuthenticationRepository();
        authenticationRepository.addUserRole(AuthenticationController.ROLE_USER, AuthenticationController.USER);
        authenticationRepository.addUserWithRole(user.getName(), user.getEmail(), password, AuthenticationController.ROLE_USER);
    --> System.out.println("Operation Successful!");
    } else {
    --> System.out.println("Operation failed. Please try again");
    }
}
```

# 6. Integration and Demo 

* A new option on the Main Menu options was added.
* Some demo purposes some tasks are bootstrapped while system starts.
* Other users must be logged in in order to perform their funcionalities.


# 7. Observations

The user must be able to log in without any specific role (outside of user's).





