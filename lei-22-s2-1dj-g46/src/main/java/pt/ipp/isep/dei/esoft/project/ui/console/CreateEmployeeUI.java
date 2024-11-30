package pt.ipp.isep.dei.esoft.project.ui.console;
import pt.ipp.isep.dei.esoft.project.application.controller.CreateEmployeeController;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.GenerateAndSavePassword;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.*;

/**
 * Register Employee UI (console). This option is only available for administrators.
 */

public class CreateEmployeeUI implements Runnable{
    private Employee employee;
    private final CreateEmployeeController controller = new CreateEmployeeController();
    /**
     * This method runs the ui.
     *
     */
    public void run() {
        System.out.println("\n\nRegister new employee.");
        registerNewEmployee();
    }
    /**
     * This method registers a new employee. (Requests and submits data)
     *
     */
    public void registerNewEmployee() {
        employee = controller.requestData();
        submitData(employee);
    }
    /**
     * This method asks the user to submit the data they input as requested by the system.
     *
     * @param employee The employee who is about to be registered
     */
    public void submitData(Employee employee){
        Scanner input = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        boolean newEmployee;
        String password;
        int option = verifyInput.verifyInt(input, "an option.%nSubmit data?%n1 - Yes.%n(Select any other NUMBER%nto go back to the Administrator menu.)");
        if (option == 1){
            newEmployee = controller.verifyData(employee);
            if (newEmployee){
                System.out.println("Operation success!");
                GenerateAndSavePassword generateAndSavePassword = new GenerateAndSavePassword();
                password = generateAndSavePassword.generatePassword();
                controller.createEmployee(employee, password);
            }
            else {
                System.out.println("Request denied. Operation failed.");
            }
        }
    }

}
