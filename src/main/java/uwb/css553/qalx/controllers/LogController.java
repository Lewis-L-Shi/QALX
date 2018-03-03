package uwb.css553.qalx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uwb.css553.qalx.models.ActivityLog;
import uwb.css553.qalx.repositories.LogRepository;

@Controller
@RequestMapping(path="/log")
public class LogController {
    @Autowired
    private LogRepository logRepository;

    @GetMapping
    public @ResponseBody
    ActivityLog addLog(@RequestParam Integer userId, @RequestParam Integer type, @RequestParam String log) {
        ActivityLog newLog = new ActivityLog(userId, type, log);
        return logRepository.save(newLog);
    }


}
