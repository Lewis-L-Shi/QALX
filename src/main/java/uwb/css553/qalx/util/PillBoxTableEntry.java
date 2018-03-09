package uwb.css553.qalx.util;

//Utility class for formatting Patient PillBox record tables.
public class PillBoxTableEntry {
    private String psid;
    private String date;
    private String time;
    private String statusStyle;
    private String statusText;

    public PillBoxTableEntry(String psid,String date, String time, String statusStyle, String statusText) {
        this.psid = psid;
        this.date = date;
        this.time = time;
        this.statusStyle = statusStyle;
        this.statusText = statusText;
    }

    public String getPsid() {
        return psid;
    }

    public String getDate() {
        return date;
    }
    public String getStatusStyle() {
        return statusStyle;
    }

    public String getStatusText() {
        return statusText;
    }

    public String getTime() {
        return time;
    }

    public void setPsid(String psid) {
        this.psid = psid;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStatusStyle(String statusStyle) {
        this.statusStyle = statusStyle;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
