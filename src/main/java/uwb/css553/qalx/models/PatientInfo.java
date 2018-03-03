package uwb.css553.qalx.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="patient_profiles")
public class PatientInfo implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private Gender gender;

    private String firstName;
    private String lastName;
    private String middleInitial;

    private String telephone;
    private String address;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pat_id", nullable = false)
    private Patient patient;

    public PatientInfo() {}
    public PatientInfo(String firstName, String lastName, String middleInitial, String telephone, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleInitial = middleInitial;
        this.telephone = telephone;
        this.address = address;
    }

    @JsonIgnore
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
