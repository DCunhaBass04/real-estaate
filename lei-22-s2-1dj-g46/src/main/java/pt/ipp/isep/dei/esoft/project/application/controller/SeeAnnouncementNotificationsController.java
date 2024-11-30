package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.AnnouncementNotification;
import pt.ipp.isep.dei.esoft.project.repository.AnnouncementNotificationRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.util.List;

public class SeeAnnouncementNotificationsController {
    public List<AnnouncementNotification> getNotificationsToCurrentOwner(AnnouncementNotificationRepository announcementNotificationRepository) {
        return announcementNotificationRepository.getNotificationsToXOwnerEmail(Repositories.getInstance().getAuthenticationRepository().getCurrentUserSession().getUserId().toString());
    }
}
