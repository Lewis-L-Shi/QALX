package uwb.css553.qalx.models;

/**
 * This class is to model the payload sending to Highcharts.js for visualization.
 * e.g. doctor view
 * @author Trang Quang
 */
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
