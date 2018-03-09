package uwb.css553.qalx.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uwb.css553.qalx.models.ActivityLog;
import uwb.css553.qalx.models.HighchartActivityLogSeries;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * Provide additional queries to ActivityLog Table
 * @author Trang Quang
 */
public interface LogRepository extends JpaRepository<ActivityLog, Long> {

    // Use Entity name, not table name in query definition
    @Query("select s from ActivityLog s where s.userId = ?1 and s.type = ?2")
    List<ActivityLog> findByUserIdAndType(Integer userId, Integer type);

    /**
     * Return ActivityLog records for specific user, specific type and date.
     * @param userId Integer, e.g. 1234
     * @param type Integer, e.g. 1
     * @param date Date
     * @return
     */
    @Query("select s from ActivityLog s" +
            " where s.userId = ?1 and s.type = ?2 and date(s.dateTime) = ?3")
    List<ActivityLog> findByUserIdAndTypeAndDate(Integer userId, Integer type, Date date);

    /**
     * Method to convert List of ActivityLog into List of HighchartActivityLogSeries.
     * aggregate Log data per hour for specific date, activity type and user.
     * (default provide method implementation in interface class, only allowed since Java 8)
     * @param userId user ID
     * @param type activity type
     * @param date specific date
     * @return List of HighchartActivityLogSeries
     */
    default List<HighchartActivityLogSeries> getLog(int userId, int type, Date date) {
        List<ActivityLog> logs = this.findByUserIdAndTypeAndDate(userId, type, date);
        final Map<Integer, Integer> hourMap = new HashMap<Integer, Integer>();

        logs.stream().forEach(activityLog -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(activityLog.getDateTime());
            int hourOfDay = cal.get(Calendar.HOUR_OF_DAY);
            int curLog = Integer.parseInt(activityLog.getLog());
            if (!hourMap.containsKey(hourOfDay)) {
                hourMap.put(hourOfDay, curLog);
            } else {
                hourMap.put(hourOfDay, curLog+hourMap.get(hourOfDay));
            }
        });

        return hourMap
                .entrySet()
                .stream()
                .map(entry -> new HighchartActivityLogSeries(entry.getKey(), entry.getValue()))
                .collect(toList());
    }

}
