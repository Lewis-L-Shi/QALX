package uwb.css553.qalx.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import uwb.css553.qalx.models.Doctor;

public interface DoctorRepository  extends JpaRepository<Doctor, Long> {

//    @Query("select doc from doctor doc where doc.id = ?1")
//    Doctor findById(Integer id);

}

