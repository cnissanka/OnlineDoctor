package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * DoctorClassRequirements generated by hbm2java
 */
public class DoctorClassRequirements  implements java.io.Serializable {


     private Integer iddoctorClassRequirements;
     private String requirementDesc;
     private Set doctorClassRequirementsHasDoctorClasseses = new HashSet(0);

    public DoctorClassRequirements() {
    }

    public DoctorClassRequirements(String requirementDesc, Set doctorClassRequirementsHasDoctorClasseses) {
       this.requirementDesc = requirementDesc;
       this.doctorClassRequirementsHasDoctorClasseses = doctorClassRequirementsHasDoctorClasseses;
    }
   
    public Integer getIddoctorClassRequirements() {
        return this.iddoctorClassRequirements;
    }
    
    public void setIddoctorClassRequirements(Integer iddoctorClassRequirements) {
        this.iddoctorClassRequirements = iddoctorClassRequirements;
    }
    public String getRequirementDesc() {
        return this.requirementDesc;
    }
    
    public void setRequirementDesc(String requirementDesc) {
        this.requirementDesc = requirementDesc;
    }
    public Set getDoctorClassRequirementsHasDoctorClasseses() {
        return this.doctorClassRequirementsHasDoctorClasseses;
    }
    
    public void setDoctorClassRequirementsHasDoctorClasseses(Set doctorClassRequirementsHasDoctorClasseses) {
        this.doctorClassRequirementsHasDoctorClasseses = doctorClassRequirementsHasDoctorClasseses;
    }




}


