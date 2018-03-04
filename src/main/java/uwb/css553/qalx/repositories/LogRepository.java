package uwb.css553.qalx.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uwb.css553.qalx.models.ActivityLog;
import uwb.css553.qalx.models.HighchartActivityLogSeries;

import java.util.*;

import static java.util.stream.Collectors.toList;

public interface LogRepository extends JpaRepository<ActivityLog, Long> {

    // Use Entity name, not table name in query definition
    @Query("select s from ActivityLog s where s.userId = ?1 and s.type = ?2")
    List<ActivityLog> findByUserIdAndType(Integer userId, Integer type);

//    @Query("select temp.Hour as hour, SUM(temp.Count) as totalCount from" +
//            " (select FUNC('HOUR', s.dateTime) as Hour, CAST(s.log UNSIGNED) as Count from ActivityLog s" +
//            " where s.userId = ?1 and s.type = ?2 and date(s.dateTime) = ?3) as temp" +
//            " GROUP BY temp.Hour")
    @Query("select s from ActivityLog s" +
            " where s.userId = ?1 and s.type = ?2 and date(s.dateTime) = ?3")
    List<ActivityLog> findByUserIdAndTypeAndDate(Integer userId, Integer type, Date date);

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
