package uwb.css553.qalx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uwb.css553.qalx.models.PatientInfo;

@Repository
public interface PatientInfoRepository extends JpaRepository<PatientInfo, Long>{
}
