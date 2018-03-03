package uwb.css553.qalx.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ActivityLog")
public class ActivityLog {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer UserId;
    private Date DateTime;
    private Integer ActivityType;
    private String Log;

    public ActivityLog(){}

    public ActivityLog(Integer id, Integer type, String log) {
        UserId = id;
        DateTime = new Date();
        ActivityType = type;
        Log = log;
    }


    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
        DateTime = dateTime;
    }

    public Integer getActivityType() {
        return ActivityType;
    }

    public void setActivityType(Integer activityType) {
        ActivityType = activityType;
    }

    public String getLog() {
        return Log;
    }

    public void setLog(String log) {
        Log = log;
    }
}
