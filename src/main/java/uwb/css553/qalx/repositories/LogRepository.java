package uwb.css553.qalx.repositories;


import org.springframework.data.repository.CrudRepository;
import uwb.css553.qalx.models.ActivityLog;

public interface LogRepository extends CrudRepository<ActivityLog, Long>{

}
