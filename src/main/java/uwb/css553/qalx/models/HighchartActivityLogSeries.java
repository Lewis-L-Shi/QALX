package uwb.css553.qalx.models;

public class HighchartActivityLogSeries {
    private int hour;
    private int totalCount;

    public HighchartActivityLogSeries(int hour, int totalCount) {
        this.hour = hour;
        this.totalCount = totalCount;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int count) {
        this.totalCount = count;
    }
}
