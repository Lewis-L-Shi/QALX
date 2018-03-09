package uwb.css553.qalx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uwb.css553.qalx.models.PillBoxRecord;
import uwb.css553.qalx.repositories.PillBoxRecordRepository;
import uwb.css553.qalx.util.DateFilterHelper;
import uwb.css553.qalx.util.PillBoxTableEntry;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/pillBoxRecord") // This means URL's start with /demo (after Application path)
public class PillBoxRecordController {
    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private PillBoxRecordRepository pillBoxRepository;
    private final String NOT_TAKEN = "background: yellow; color: white; text-shadow: 2px 0 0 #000, -2px 0 0 #000, 0 2px 0 #000, 0 -2px 0 #000, 1px 1px #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000; text-align: center;";
    private final String TAKEN = "background: green; color: white; text-shadow: 2px 0 0 #000, -2px 0 0 #000, 0 2px 0 #000, 0 -2px 0 #000, 1px 1px #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000; text-align: center;";
    private final String CRITICAL = "background: red; color: white; text-shadow: 2px 0 0 #000, -2px 0 0 #000, 0 2px 0 #000, 0 -2px 0 #000, 1px 1px #000, -1px -1px 0 #000, 1px -1px 0 #000, -1px 1px 0 #000; text-align: center;";

    @RequestMapping(method=RequestMethod.POST) // Map ONLY GET Requests
    public @ResponseBody String addNewUser (
            @RequestParam Integer pid,
            @RequestParam("date") @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date date,
            //@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime date,
            //@RequestParam Integer pillMissed,
            @RequestParam Integer psid,
            @RequestParam Integer status

    ) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        PillBoxRecord n = new PillBoxRecord();
        n.setPid(pid);
        n.setDate(date);
        //n.setPillMissed(pillMissed);
        n.setPsid(psid);
        n.setStatus(status);
        pillBoxRepository.save(n);
        return "Saved";
    }

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody Iterable<PillBoxRecord> findAll() {
        // This returns a JSON or XML with the users
        return pillBoxRepository.findAll();
    }
    @RequestMapping(method=RequestMethod.GET, params="pid")
    public @ResponseBody List<PillBoxRecord> findByPidContainsAllIgnoreCase(Integer pid) {
        // This returns a JSON or XML with the users
        return pillBoxRepository.findByPidContainsAllIgnoreCase(pid);
    }
    @RequestMapping(method=RequestMethod.GET, params="psid")
    public @ResponseBody List<PillBoxRecord> findByPsidContainsAllIgnoreCase(Integer psid) {
        // This returns a JSON or XML with the users
        return pillBoxRepository.findByPsidContainsAllIgnoreCase(psid);
    }

    @RequestMapping(method=RequestMethod.GET, params={"startDate", "endDate"})
    public @ResponseBody List<PillBoxRecord> findByDateBetween(
            @RequestParam(value="startDate") @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date startDate,
            @RequestParam(value="endDate") @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date endDate) {
        return pillBoxRepository.findByDateBetween(startDate, endDate);
    }

    @RequestMapping(method=RequestMethod.GET, params={"pid", "startDate", "endDate"})
    public @ResponseBody List<PillBoxRecord> findByPidAndDateBetween(
            @RequestParam(value="pid") Integer pid,
            @RequestParam(value="startDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date startDate,
            @RequestParam(value="endDate") @DateTimeFormat(pattern = "MM/dd/yyyy") Date endDate) {
        return pillBoxRepository.findByDateBetween(startDate, endDate);
    }
    //Prcoess GET request for specific PatientPillBox record.
    @RequestMapping(method=RequestMethod.GET,path="/SinglePatientRecord")
    public String getPatientRecords(@RequestParam Integer id, @RequestParam String fname, @RequestParam String lname,
                              Model model) {
        List<PillBoxRecord> list = findByPidContainsAllIgnoreCase(id);
        List<PillBoxTableEntry> tableEntries = parseTableEntry(list);
        String patientName = fname+ " " + lname;
        model.addAttribute("tableEntries", tableEntries);
        model.addAttribute("patientName", patientName);
        model.addAttribute("user_id",list.get(0).getPid());
        model.addAttribute("dateFilter", new DateFilterHelper());
        return "SinglePatientRecord";    //return view name
    }
    //Process POST request from Date Filter form.
    @RequestMapping(method=RequestMethod.POST,path="/SinglePatientRecord")
    public String filterPatientRecordsByDate(@ModelAttribute DateFilterHelper dateFilter, @RequestParam Integer pid,
                                             @RequestParam String name, Model model) {
        List<PillBoxRecord> list = findByPidAndDateBetween(pid,dateFilter.getStartDate(),dateFilter.getEndDate());
        List<PillBoxTableEntry> tableEntries = parseTableEntry(filterByPid(list,pid));
        String patientName = name;
        model.addAttribute("tableEntries", tableEntries);
        model.addAttribute("patientName", patientName);
        model.addAttribute("user_id", pid);
        model.addAttribute("dateFilter", new DateFilterHelper());
        return "SinglePatientRecord";    //return view name
    }
    //Processes the queried list for the specific values needed in the view table.
    private List<PillBoxTableEntry> parseTableEntry(List<PillBoxRecord> list) {
        List<PillBoxTableEntry> result = new LinkedList();
        DateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        DateFormat time = new SimpleDateFormat("hh:mm:ss a");
        String psid;
        String d;
        String t;
        String style;
        String text;
        for(PillBoxRecord rec: list) {
            psid = rec.getPsid().toString();
            d = date.format(rec.getdate());
            t = time.format(rec.getdate());
            if(rec.getStatus() == 0) {
                style = NOT_TAKEN;
                text = "Not Taken";
            }
            else if(rec.getStatus() == 1) {
                style = TAKEN;
                text = "Taken";
            }
            else {
                style = CRITICAL;
                text = "Critical";
            }
            result.add(new PillBoxTableEntry(psid,d,t,style,text));
        }
        return result;
    }

    //Filter the date query for a specific PID.
    private List<PillBoxRecord> filterByPid(List<PillBoxRecord> list, Integer pid) {
        List<PillBoxRecord> result = new LinkedList<PillBoxRecord>();
        for(PillBoxRecord rec: list) {
            if(rec.getPid() == pid)
                result.add(rec);
        }
        return result;
    }
}