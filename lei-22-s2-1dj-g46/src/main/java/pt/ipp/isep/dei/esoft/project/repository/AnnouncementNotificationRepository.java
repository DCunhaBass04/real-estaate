package pt.ipp.isep.dei.esoft.project.repository;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementNotification;
import pt.ipp.isep.dei.esoft.project.domain.Users.Agent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AnnouncementNotificationRepository implements Serializable {
    private List<AnnouncementNotification> announcementNotificationList = new ArrayList<>();
    public boolean isEmpty(){
        return announcementNotificationList.isEmpty();
    }
    public Optional<AnnouncementNotification> add(AnnouncementNotification notification) {

        Optional<AnnouncementNotification> newNotification = Optional.empty();
        boolean operationSuccess = false;

        if (validateNotification(notification)) {
            newNotification = Optional.of(notification.clone());
            operationSuccess = announcementNotificationList.add(newNotification.get());
        }

        if (!operationSuccess) {
            newNotification = Optional.empty();
        }
        return newNotification;
    }
    public void remove(Object o){
        announcementNotificationList.remove(o);
    }
    /**
     * This method validates the Notification about to be added.
     *
     * @param notification The Notification to be added.
     * @return The Boolean value of the statement "The notification is valid.".
     */
    private boolean validateNotification(AnnouncementNotification notification) {return !announcementNotificationList.contains(notification);}
    public List<AnnouncementNotification> getAnnouncementNotificationList(){return List.copyOf(announcementNotificationList);}
    public List<AnnouncementNotification> getNotificationsToXOwnerEmail(String ownerEmail){
        List<AnnouncementNotification> notificationsToXOwner = new ArrayList<>();
        for(AnnouncementNotification announcementNotification : announcementNotificationList){
            if(announcementNotification.getPropertyOwner().getEmail().equals(ownerEmail)){
                notificationsToXOwner.add(announcementNotification);
            }
        }
        return notificationsToXOwner;
    }
}
