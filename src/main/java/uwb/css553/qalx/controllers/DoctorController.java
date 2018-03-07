package uwb.css553.qalx.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uwb.css553.qalx.models.Doctor;
import uwb.css553.qalx.models.Patient;
import uwb.css553.qalx.repositories.DoctorRepository;
import uwb.css553.qalx.repositories.PatientRepository;
import uwb.css553.qalx.services.PatientService;


import javax.persistence.*;

@Controller
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientService patientService;

    // TODO: move into patientService
    @Autowired
    private PatientRepository patientRepository;

    @RequestMapping(path="/doctors", method= RequestMethod.POST)
    public @ResponseBody
    String addNewDoctor (@RequestParam String firstName, @RequestParam String lastName, @RequestParam String email, @RequestParam String organization, @RequestParam String speciality) {
        Doctor doc = new Doctor();
        doc.setFirstName(firstName);
        doc.setLastName(lastName);
        doc.setEmail(email);
        doc.setOrganization(organization);
        doc.setSpeciality(speciality);
        doc.setStatus("in_service");
        doctorRepository.save(doc);
        return "new doctor saved";
    }

    @GetMapping(path="/doctors")
    public @ResponseBody Iterable<Doctor> getAllDoctors() {
        //doctorRepository.findOne();
        return doctorRepository.findAll();
    }


    @GetMapping(path="/doctor/{id}")
    public @ResponseBody Doctor getDoctorById(@PathVariable long id){
        return doctorRepository.findOne(id);
    }

    @RequestMapping(path = "/addPatient", method = RequestMethod.POST)
    public @ResponseBody String addDocPatRelationship(@RequestParam long docId, @RequestParam long pid){

        Patient myPatient = patientRepository.findOne(pid);
        Doctor myDoctor = doctorRepository.findOne(docId);

        if(false){
            return "DocID or Pid not exist";
        }

        myPatient.getDocs().add(myDoctor);
        myDoctor.getPatients().add(myPatient);

        doctorRepository.save(myDoctor);
        patientRepository.save(myPatient);

        return "Doc-pat relationship added.";
    }

    @RequestMapping(path = "/{docId}/addPatient", method = RequestMethod.POST)
    public @ResponseBody String addDocPatRelationship2(@PathVariable long docId, @RequestParam long pid){

        Patient myPatient = patientRepository.findOne(pid);
        Doctor myDoctor = doctorRepository.findOne(docId);

        if(false){
            return "DocID or Pid not exist";
        }

        myPatient.getDocs().add(myDoctor);
        myDoctor.getPatients().add(myPatient);

        doctorRepository.save(myDoctor);
        patientRepository.save(myPatient);

        return "Doc-pat relationship added.";
    }

    @GetMapping(path="/{docId}/patients")
    public @ResponseBody Iterable<Patient> getPatientsByDocId(@PathVariable long docId){

        Doctor doc = doctorRepository.findOne(docId);
        return doc.getPatients();
    }


    @RequestMapping(value="/doctor/patient")
    public String getPatientHistory(Model model) {
//        model.addAttribute("patient", patientService.getPatients(1L));
        return "monitor"; //return view name
    }

    @RequestMapping(value="/doctor")
    public String getPatients(Model model) {
        model.addAttribute("patients", patientService.getPatients(1L));
        return "doctor";    //return view name
    }
}
