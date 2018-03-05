package uwb.css553.qalx.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import uwb.css553.qalx.models.Caregiver;
import uwb.css553.qalx.models.Doctor;
import uwb.css553.qalx.repositories.CaregiverRepository;
import uwb.css553.qalx.repositories.DoctorRepository;

@Controller
@RequestMapping(path="/caregiver")
public class CaregiverController {

    @Autowired
    private CaregiverRepository caregiverRepository;

    @GetMapping(path="/addCaregiver")
    public @ResponseBody
    String addNewCaregiver (@RequestParam String name, @RequestParam String email, @RequestParam String address, @RequestParam String tel) {
        Caregiver cg = new Caregiver();
        cg.setName(name);
        cg.setEmail(email);
        cg.setAddress(address);
        cg.setTel(tel);
        caregiverRepository.save(cg);
        return "new caregiver saved";
    }


    @GetMapping(path="/listAll")
    public @ResponseBody Iterable<Caregiver> getAllCaregivers() {
        return caregiverRepository.findAll();
    }
}
