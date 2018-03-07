package uwb.css553.qalx.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwb.css553.qalx.models.Patient;
import uwb.css553.qalx.models.PatientInfo;
import uwb.css553.qalx.repositories.DoctorRepository;
import uwb.css553.qalx.repositories.PatientInfoRepository;
import uwb.css553.qalx.repositories.PatientRepository;

import java.util.List;

@Service
public class PatientService {
//    @Autowired
//    private DoctorRepository doctorRepository;
//
    @Autowired
    private PatientRepository patientRepository;
    public List<Patient> getPatients(Long doctorId) {
        // test- hardcoded
        return patientRepository.findAll();
    }

    private PatientInfoRepository patientInfoRepository;
    public List<PatientInfo> getPatientInfo() {
        return patientInfoRepository.findAll();
    }
}
