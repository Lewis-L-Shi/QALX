package uwb.css553.qalx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uwb.css553.qalx.models.ActivityLog;
import uwb.css553.qalx.models.HighchartActivityLogSeries;
import uwb.css553.qalx.repositories.LogRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Controller to provide access to ActivityLog entity
 */
@Controller
@RequestMapping(path="/log")
public class LogController {
    @Autowired
    private LogRepository logRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    @RequestMapping(method= RequestMethod.POST)
    public @ResponseBody
    ActivityLog addLog(@RequestParam Integer userId, @RequestParam Integer type, @RequestParam String log) {
        ActivityLog newLog = new ActivityLog(userId, type, log);
        return logRepository.save(newLog);
    }

    @GetMapping
    public @ResponseBody List<ActivityLog> getAllLog() {
        return logRepository.findAll();
    }

    @RequestMapping(method=RequestMethod.POST, path="/{id}")
    public @ResponseBody
    List<ActivityLog> getLog(@PathVariable(value = "id") Integer userId, @RequestParam Integer type)
//    , @RequestParam String datefrom, @RequestParam String dateto)
    {
        return logRepository.findByUserIdAndType(userId, type);
    }

    // use POST instead of GET to avoid browser caching
    @RequestMapping(method=RequestMethod.POST, path="/{id}/{type}")
    public @ResponseBody List<HighchartActivityLogSeries> getLog(@PathVariable(value="id") Integer userId,
                                                                 @PathVariable(value="type") Integer type,
                                                                 @RequestParam String date) throws ParseException {
        Date startDate = dateFormat.parse(date);
        return logRepository.getLog(userId, type, startDate);
    }

}
