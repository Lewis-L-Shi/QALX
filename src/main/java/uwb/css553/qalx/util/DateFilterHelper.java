package uwb.css553.qalx.util;

import java.util.Date;
import java.text.SimpleDateFormat;

//Utility class for Filtering records by date.
public class DateFilterHelper {
    private Date startDate;
    private Date endDate;
    private final SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
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
}
