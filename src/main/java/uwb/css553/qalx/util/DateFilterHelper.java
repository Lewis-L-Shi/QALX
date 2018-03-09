package uwb.css553.qalx.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateFilterHelper {
    private Integer pid;
    private String name;
    private Date startDate;
    private Date endDate;
    private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Integer getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    public void setStartDate(String startDate) {
        try {
            this.startDate = format.parse(startDate);
        }catch(Exception e){System.out.println(e.getStackTrace());}
    }

    public void setEndDate(String endDate) {
        try {
            this.endDate = format.parse(endDate);
        }catch(Exception e){System.out.println(e.getStackTrace());}
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setName(String name) {
        this.name = name;
    }
}
