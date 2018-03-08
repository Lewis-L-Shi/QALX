package uwb.css553.qalx.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

//import static rest.DateTimeAdapter.DATE_FORMAT;

@Entity // This tells Hibernate to make a table out of this class
public class PillBoxRecord {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer primaryKey;
    private Integer pid;
    @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy")
    protected Date date;
    private Integer pillMissed;
    private Integer psid;
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;


    public Integer getPrimaryKey() {return this.pid; }
    public Integer getPid() {return this.pid; }
    public Date getdate() {
//        Date date2 = this.date;
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        return df.format(date2);
        return this.date;
    }
    //public Integer getPillMissed() {return this.pillMissed; }
    public Integer getPsid() {return this.psid; }
    public Integer getStatus() {return this.status; }

    public void setPrimaryKey(Integer primaryKey) {this.primaryKey = primaryKey; }
    public void setPid(Integer pid) {this.pid = pid;}
    //public void setDate(@DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date date){this.date = date;}
    public void setDate(@DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date date){this.date = date;}
    //public void setPillMissed(Integer pillMissed){this.pillMissed = pillMissed;}
    public void setPsid(Integer psid){this.psid = psid;}
    public void setStatus(Integer status){this.status = status;}

}