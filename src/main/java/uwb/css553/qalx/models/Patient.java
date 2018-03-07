package uwb.css553.qalx.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String patientId;
    private Date birthDate;

    // one-to-one mapping to profile
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "pinfo_id", nullable = false)
    private PatientInfo patientInfo;

//    @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {
//                    CascadeType.PERSIST,
//                    CascadeType.MERGE
//            })
//    @JoinTable(name = "patients_doctors",
//            joinColumns = { @JoinColumn(name = "pat_id") },
//            inverseJoinColumns = { @JoinColumn(name = "doc_id") })
//    private Set<Doctor> tags = new HashSet<>();



    public Patient() {}

    public Patient(Date birthDate){
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public PatientInfo getPatientInfo() {
        return patientInfo;
    }

    public void setPatientInfo(PatientInfo patientInfo) {
        this.patientInfo = patientInfo;
    }
}
