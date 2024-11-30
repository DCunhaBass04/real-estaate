package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VerifyInput {
    public VerifyInput(){}

    /**
     *Verify if the input entered by the user is a valid integer, and to repeatedly prompt the user to enter a valid integer until one is entered.
     * @param input: a Scanner object
     * @param item: a String object.
     * @return the value of the "verifyInt" method
     */
    public int verifyInt(Scanner input, String item){
        int number = 0;
        boolean valid = false;
        do {
            try {
                System.out.printf("%nPlease insert " + item + ": %n");
                number = input.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.printf("%nInvalid number. Try again.%n");
                input.nextLine();
            }
        } while (!valid);
        input.nextLine();
        return number;
    }

    /**
     *Verify if the input entered by the user is a valid integer, and to repeatedly prompt the user to enter a valid integer until one is entered.
     * @param input: a Scanner object
     * @param item: a String object.
     * @return the value of the "verifyFloat" method
     */
    public float verifyFloat(Scanner input, String item){
        float number = 0;
        boolean valid = false;
        do {
            try {
                System.out.printf("%nPlease insert " + item + ": %n");
                number = input.nextFloat();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.printf("%nInvalid number. Try again.%n");
                input.nextLine();
            }
        } while (!valid);
        input.nextLine();
        return number;
    }

    /**
     *Verify if the input entered by the user is a valid integer, and to repeatedly prompt the user to enter a valid integer until one is entered.
     * @param input: a Scanner object
     * @param item: a String object.
     * @return the value of the "verifyString" method
     */
    public String verifySimpleString(Scanner input, String item){
        boolean valid = false;
        String value = "";
        do{
            try {
                System.out.printf("%nPlease insert " + item + ": %n");
                value = input.next();
                valid = true;
            }catch (NoSuchElementException e){
                System.out.printf("%nYou have not inserted any data.%n");
            }
        } while (!valid);
        input.nextLine();
        return value;
    }

    /**
     * Verify if the input entered by the user is a valid integer, and to repeatedly prompt the user to enter a valid integer until one is entered.
     * @param input: a Scanner object
     * @param item: a String object.
     * @return the value of the "verifyComplexString" method
     */
    public String verifyComplexString(Scanner input, String item){
        boolean valid = false;
        String value = "";
        do {
            try {
                System.out.printf("%nPlease insert " + item + ": %n");
                value = input.nextLine();
                valid = true;
            } catch (NoSuchElementException e){
                System.out.print("%nYou have not inserted any data.%n");
            }
        } while (!valid);
        return value;
    }

    /**
     * Verify if the input entered by the user is a valid integer, and to repeatedly prompt the user to enter a valid integer until one is entered.
     * @param input: a Scanner object
     * @param item: a String object.
     * @return the value of the "verifyBoolean" method
     */
    public boolean verifyBoolean(Scanner input, String item){
        boolean valid = false;
        String value;
        boolean  response = false;
        do{
            try{
                System.out.printf("%n" + item + "? Yes/No%n");
                value = input.nextLine();
                if(value.equalsIgnoreCase("Yes")){
                    response = true;
                    valid = true;
                } else if (value.equalsIgnoreCase("No")){
                    valid = true;
                } else {
                    System.out.printf("%nPlease insert a valid answer.%n");
                }
            } catch (NoSuchElementException e){
                System.out.printf("%nPlease insert an answer.%n");
            }
        }while(!valid);
        return response;
    }

    /**
     *     *Verify if the input entered by the user is a valid integer, and to repeatedly prompt the user to enter a valid integer until one is entered.
     * @param input: a Scanner object
     * @param size: a int object.
     * @return the value of the "verifyFileArray" method
     */
    public File[] verifyFileArray(Scanner input, int size){
        File[] files = new File[size];
        for (int i = 0; i < files.length; i++) {
            boolean valid = false;
            do{
                try {
                    files[i] = new File(verifySimpleString(input, "Path to File (including file name)"));
                    valid = true;
                } catch(Exception e){
                    System.out.println("Please insert a valid file path.");
                }
            }while(!valid);
        }
        return files;
    }
}
