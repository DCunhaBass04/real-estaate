package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.ImportLegacySystemController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ImportLegacySystemUI implements Runnable{
    public ImportLegacySystemUI(){}

    /**
     * This method prints the specific format the legacy system file must have if the user desires it. It also gets the file's full path and tells the controller to read it
     */
    @Override
    public void run() {
        VerifyInput verifyInput = new VerifyInput();
        Scanner scanner = new Scanner(System.in);
        boolean answer = verifyInput.verifyBoolean(scanner, "Print requested format of the .csv file (not necessary)");
        if(answer) {
            System.out.printf("The file must be in the following format:" +
                    "%n-Each line is a different property (except the first one) and must consist of" +
                    ":%n  -Owner's name%n  -Owner's Passport Number%n  -Owner's SSN%n  -Owner's Phone Number%n  -Property's Type" +
                    "%n  -Property's Area (in square feet)%n  -Property's Location%n  -Distance from City Centre (in miles)" +
                    "%n  -Number of Bedrooms ('NA' if property is Land)%n  -Number of Bathrooms ('NA' if property is Land)" +
                    "%n  -Number of Parking Spaces ('NA' if property is Land)%n  -Central Heating (Y/N) ('NA' if property is Land)" +
                    "%n  -Air Conditioning (Y/N) ('NA' if property is Land)%n  -Basement (Y/N) ('NA' if property is Land)" +
                    "%n  -Loft (Y/N) ('NA' if property is Land)%n  -Sun Exposure Direction (N/S/E/W) ('NA' if property is Land)" +
                    "%n  -Requested Price (in USD)%n  -Price (in USD)%n  -Commission Value (in %%)" +
                    "%n  -Contraction Duration (in months) ('NA' if property is 'for sale')%n  -Date of Request (DD/MM/YYYY)" +
                    "%n  -Date of Sale Announcement (DD/MM/YYYY)%n  -Store's ID%n  -Store's Name%n  -Store's Location" +
                    "%n  -Store's Phone Number%n  -Store's Email Address%n%n%n");
        }
        String fileName = verifyInput.verifyComplexString(scanner, "Full Path to .csv file");
        try {
            File fileToImport = new File(fileName);
            ImportLegacySystemController importLegacySystemController = new ImportLegacySystemController();
            if (importLegacySystemController.verifyFile(fileName)) {
                Scanner fileReader = new Scanner(fileToImport);
                boolean operationSuccessful = importLegacySystemController.importFile(fileReader);
                fileReader.close();
                if (operationSuccessful) {
                    System.out.println("Operation Successful");
                } else {
                    System.out.println("Operation Failed, file format is incorrect.");
                }
            } else {
                System.out.println("Given file is not a .csv. Please insert a valid file.");
            }
        } catch (IOException e){
            System.out.println("File does not exist");
        }
        System.out.print("\n\nPress ENTER to continue");
        scanner.nextLine();
    }
}
