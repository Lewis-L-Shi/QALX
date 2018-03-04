package uwb.css553.qalx.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import uwb.css553.qalx.models.AlertMessage;

@Controller
public class NotificationController {

    @Autowired
    private SimpMessageSendingOperations messageSendingOperations;

    @RequestMapping("/test")
    public ResponseEntity<AlertMessage> alert(@RequestParam String message) throws Exception {
        AlertMessage newAlertMessage = new AlertMessage(200, message);
        messageSendingOperations.convertAndSend("/topic/alerts", newAlertMessage);
        return ResponseEntity.ok(newAlertMessage);
    }
}
