package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.util.Scanner;

public class SetCommissionValue {
    public String defineCommissionValue(Scanner sc, VerifyInput verifyInput, float price){
        boolean valid = false;
        int response;
        String commissionValue = "";
        System.out.printf("%nInsert the type of commission value you want.%n1 - Percentage%n2 - Fixed Value%n");
        do{
            response = verifyInput.verifyInt(sc, "Response");
            switch(response){
                case 1:
                    commissionValue = setCommissionValueInSpecifiedRange(100, "(in percentage, from 0 to 100)", sc, verifyInput) + "%";
                    valid = true;
                    break;
                case 2:
                    commissionValue = "" + setCommissionValueInSpecifiedRange(price, "(fixed value in €, from 0 to the requested price)", sc, verifyInput) + "$";
                    valid = true;
            }
        }while(!valid);
        return commissionValue;
    }
    /**
     * This method is for setting the comission value.
     *
     * @param finalValue The highest value that the user can input (100% in percentage or the total price for a fixed value).
     * @param wantedRange The range of the value
     * @param input The Scanner to scan input from the keyboard.
     * @param verifyInput The VerifyInput class Object that will call the verifyInt method.
     * @return The commission value.
     */
    public float setCommissionValueInSpecifiedRange(float finalValue, String wantedRange, Scanner input, VerifyInput verifyInput){
        boolean valid = false;
        float value;
        do{
            value = verifyInput.verifyFloat(input, "a value " + wantedRange);
            if(value >= 0 && value <= finalValue){
                valid = true;
            } else {
                System.out.println("Please insert a value in the correct range.");
            }
        }while(!valid);
        return value;
    }
}
