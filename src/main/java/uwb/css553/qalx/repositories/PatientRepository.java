package uwb.css553.qalx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uwb.css553.qalx.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{
}
