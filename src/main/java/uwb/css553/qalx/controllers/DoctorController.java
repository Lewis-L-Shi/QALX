package uwb.css553.qalx.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uwb.css553.qalx.models.Doctor;
import uwb.css553.qalx.repositories.DoctorRepository;


import javax.persistence.*;

@Controller
@RequestMapping(path="/doctors")
public class DoctorController {
    @Autowired
    private DoctorRepository doctorRepository;

    @RequestMapping(method= RequestMethod.POST)
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

    @GetMapping
    public @ResponseBody Iterable<Doctor> getAllDoctors() {
        //doctorRepository.findOne();
        return doctorRepository.findAll();
    }


    @GetMapping(path="/{id}")
    public @ResponseBody Doctor getDoctorById(@PathVariable long id){
        return doctorRepository.findOne(id);
//        return doctorRepository.findById(id);
    }


}
