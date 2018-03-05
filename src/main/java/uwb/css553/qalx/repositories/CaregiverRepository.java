package uwb.css553.qalx.repositories;

import org.springframework.data.repository.CrudRepository;
import uwb.css553.qalx.models.Caregiver;
import uwb.css553.qalx.models.Doctor;

public interface CaregiverRepository  extends CrudRepository<Caregiver, Long> {

}

