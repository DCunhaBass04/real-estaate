package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.SeeAnnouncementNotificationsController;
import pt.ipp.isep.dei.esoft.project.domain.AnnouncementNotification;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementNotificationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;
import java.util.Scanner;

/**
 * The type See announcement notifications ui.
 */
public class SeeAnnouncementNotificationsUI implements Runnable {
    SeeAnnouncementNotificationsController ctrl = new SeeAnnouncementNotificationsController();

    /**
     * This method runs this UI, seeing if there is any notifications for the current user to see
     */
    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        AnnouncementNotificationRepository announcementNotificationRepository = Repositories.getInstance().getAnnouncementNotificationRepository();
        List<AnnouncementNotification> notifications = ctrl.getNotificationsToCurrentOwner(announcementNotificationRepository);
        if(!notifications.isEmpty()){
            for(AnnouncementNotification notification : notifications){
                System.out.println(notification);
            }
        } else {
            System.out.println("There are no properties of your ownership that have been recently published.");
        }
        System.out.println("Press ENTER to continue.");
        sc.nextLine();
    }
}
