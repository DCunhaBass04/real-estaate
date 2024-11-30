package pt.ipp.isep.dei.esoft.project.ui.console;

import java.util.Scanner;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class DevTeamUI implements Runnable {

    public DevTeamUI() {

    }

    public void run() {
        Scanner ler = new Scanner(System.in);
        System.out.println("\n");
        System.out.print("Development Team:\n");
        System.out.print("\t Ana Oliveira - 1220752@isep.ipp.pt \n");
        System.out.print("\t António Costa - 1221285@isep.ipp.pt \n");
        System.out.print("\t Diogo Cunha - 1221071@isep.ipp.pt \n");
        System.out.print("\t João Resende - 1211389@isep.ipp.pt \n");
        System.out.print("\t Tomás Peixoto - 1221948@isep.ipp.pt \n");
        System.out.print("\nPress ENTER to continue");
        ler.nextLine();
    }
}
