package pt.ipp.isep.dei.esoft.project.ui.console.utils;

import pt.ipp.isep.dei.esoft.project.domain.Users.Client;
import pt.ipp.isep.dei.esoft.project.domain.Users.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateAndSavePassword {

    /**
     * This method generates a password for the user
     *
     * @return The password.
     */
    public String generatePassword(){
        String password = "";
        int length = 7, position;
        char[] randomPassword = new char[length];
        List<Integer> allPositions = new ArrayList<Integer>();
        for (int i = 0; i < length; i++) {
            allPositions.add(i);
        }
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "1234567890";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String alphaCharacters = capitalLetters+digits+lowerCase;
        Random random = new Random();

        List<Integer> capitalPositions = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) { //add Capital letters
            position = allPositions.get(random.nextInt(allPositions.size()));
            randomPassword[position] = capitalLetters.charAt(random.nextInt(capitalLetters.length()));
            capitalPositions.add(position);
            allPositions.remove(Integer.valueOf(position));
        }

        List<Integer> digitPositions = new ArrayList<Integer>();
        for (int i = 0; i < 2; i++) { //add digits
            position = allPositions.get(random.nextInt(allPositions.size()));
            randomPassword[position] = digits.charAt(random.nextInt(digits.length()));
            digitPositions.add(position);
            allPositions.remove(Integer.valueOf(position));
        }
        for (int i = 0; i < length; i++) {
            if (randomPassword[i] == 0) {
                randomPassword[i] = alphaCharacters.charAt(random.nextInt(alphaCharacters.length()));
            }
            password += randomPassword[i];
        }
        return password;
    }
    /**
     * This method stores the login information to a .txt file
     *
     * @param employee The employee.
     */
    public void sendEmployeePassword(Employee employee){
        String password = employee.getPassword(), email = employee.getEmail(), role = employee.getRole();
        String filename = "Passwords.txt";
        String data = String.format("Role: %s Email: %s Password: %s%n",role, email, password);
        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(data);
            writer.close();
            System.out.println("Data saved to file successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving data to file.");
            e.printStackTrace();
        }
    }

    public void sendClientPassword(Client client){
        String password = client.getPassword(), email = client.getEmail(), role = "Client";
        String filename = "Passwords.txt";
        String data = String.format("Role: %s Email: %s Password: %s%n",role, email, password);
        try {
            FileWriter writer = new FileWriter(filename, true);
            writer.write(data);
            writer.close();
            System.out.println("Data saved to file successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving data to file.");
            e.printStackTrace();
        }
    }

    public boolean verifyPassword (String password){
        boolean valid = true;
        int length = 7;
        int numberOfCapitalLetters = 0;
        int numberOfDigits = 0;
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String digits = "1234567890";
        char[] passwordCharacters = password.toCharArray();
        char[] capitalLettersArray = capitalLetters.toCharArray();
        char[] digitsArray = digits.toCharArray();
        if (passwordCharacters.length > length){
            valid = false;
        }
        for (int i = 0; i < passwordCharacters.length; i++) {
            for (int j = 0; j < capitalLettersArray.length; j++) {
                if (passwordCharacters[i] == capitalLettersArray[j]){
                    numberOfCapitalLetters++;
                }
            }
            for (int j = 0; j < digitsArray.length; j++) {
                if (passwordCharacters[i] == digitsArray[j]){
                    numberOfDigits++;
                }
            }
        }
        if (numberOfCapitalLetters < 3){
            valid = false;
            System.out.printf("Not enough capital letters.%n");
        }
        if (numberOfDigits < 2){
            valid = false;
            System.out.printf("Not enough digits.%n");
        }
        return valid;
    }
}
