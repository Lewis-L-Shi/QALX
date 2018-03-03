package uwb.css553.qalx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uwb.css553.qalx.models.Patient;
import uwb.css553.qalx.models.PatientInfo;
import uwb.css553.qalx.repositories.PatientInfoRepository;
import uwb.css553.qalx.repositories.PatientRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/patients")
public class PatientController {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientInfoRepository patientInfoRepository;


    @RequestMapping(method= RequestMethod.POST)
    public @ResponseBody
    Patient addPatient(@RequestParam String lastname, @RequestParam String firstname, @RequestParam String dob) {
        Date birthDate = new Date();
        try {
            birthDate = (new SimpleDateFormat("MM-dd-yyyy")).parse(dob);
        } catch (ParseException e) {
            System.out.println("Date parse exception");
        }


        Patient newPatient = new Patient(birthDate);
        PatientInfo patInfo = new PatientInfo(firstname, lastname, "","", "");

        newPatient.setPatientInfo(patInfo);
        patInfo.setPatient(newPatient);

        return patientRepository.save(newPatient);
    }

    @GetMapping
    public @ResponseBody
    List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @GetMapping(path="/{id}")
    public @ResponseBody PatientInfo getPatientById(@PathVariable(value = "id") Long id) {
        return patientRepository.getOne(id).getPatientInfo();
    }

}
