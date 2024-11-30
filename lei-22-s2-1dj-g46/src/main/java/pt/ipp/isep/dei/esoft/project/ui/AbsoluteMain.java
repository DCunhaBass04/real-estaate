package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.ui.console.utils.VerifyInput;
import pt.ipp.isep.dei.esoft.project.ui.gui.menu.MainFX;

import java.io.IOException;
import java.util.Scanner;

public class AbsoluteMain {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        VerifyInput verifyInput = new VerifyInput();
        int option;
        boolean exit = false;
        do{
            System.out.printf("%nWhich version do you want to use?%n1. Console Version%n2. Graphical Version%n%n0. Exit");
            option = verifyInput.verifyInt(sc, "your desired option");
           switch(option){
               case 1:
                   Main.main(args);
                   exit = true;
                   break;
               case 2:
                   MainFX.main(args);
                   exit = true;
                   break;
               case 0:
                   exit = true;
                   break;
               default:
                   System.out.println("Please insert a valid value");
           }
        }while(!exit);
    }
}
