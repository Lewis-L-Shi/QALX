package uwb.css553.qalx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import uwb.css553.qalx.models.AlertMessage;

/**
 * This service is responsible for pushing messages using WebSocket.
 * @author Trang Quang
 */
@Service
public class NotificationService {
    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    public void notifyUser(String username, String message) {
        messageSendingOperations.convertAndSendToUser(
                username,
                "/topic/alerts",
                new AlertMessage(200, message)
        );
    }
}
