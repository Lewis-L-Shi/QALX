package uwb.css553.qalx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uwb.css553.qalx.services.NotificationService;

/**
 * This controller is used for testing notification service.
 * (aka notify an authenticated user with a provided message)
 * @author Trang Quang
 */
@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping("/test")
    public ResponseEntity<String> alert(@RequestParam String message, @RequestParam String username) throws Exception {
        notificationService.notifyUser(username, message);
        return ResponseEntity.ok("done");
    }
}
