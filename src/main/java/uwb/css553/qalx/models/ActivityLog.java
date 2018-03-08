package uwb.css553.qalx.models;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is to model users' activity log.
 * @author Trang Quang
 */
@Entity
public class ActivityLog {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Integer userId;
    private Date dateTime;
    private Integer type;
    private String log;

    public ActivityLog(){}

    public ActivityLog(Integer id, Integer type, String log) {
        this.userId = id;
        this.dateTime = new Date();
        this.type = type;
        this.log = log;
    }


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
