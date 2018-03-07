package uwb.css553.qalx.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import uwb.css553.qalx.models.PillBoxRecord;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PillBoxRecordRepository extends CrudRepository<PillBoxRecord, Long> {

    List<PillBoxRecord> findByPidContainsAllIgnoreCase(Integer pid);
    List<PillBoxRecord> findByPsidContainsAllIgnoreCase(Integer psid);

    List<PillBoxRecord> findByDateBetween(
            @RequestParam(value="startDate") @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date startDate,
            @RequestParam(value="endDate") @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date endDate);

    List<PillBoxRecord> findByPidAndDateBetween(
            @RequestParam(value="pid") Integer pid,
            @RequestParam(value="startDate") @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date startDate,
            @RequestParam(value="endDate") @DateTimeFormat(pattern = "EEE MMM dd HH:mm:ss z yyyy") Date endDate);
}