package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * DoctorField generated by hbm2java
 */
public class DoctorField  implements java.io.Serializable {


     private Integer iddoctorField;
     private String fieldName;
     private String fieldDescription;
     private Set doctorFieldHasDoctors = new HashSet(0);

    public DoctorField() {
    }

    public DoctorField(String fieldName, String fieldDescription, Set doctorFieldHasDoctors) {
       this.fieldName = fieldName;
       this.fieldDescription = fieldDescription;
       this.doctorFieldHasDoctors = doctorFieldHasDoctors;
    }
   
    public Integer getIddoctorField() {
        return this.iddoctorField;
    }
    
    public void setIddoctorField(Integer iddoctorField) {
        this.iddoctorField = iddoctorField;
    }
    public String getFieldName() {
        return this.fieldName;
    }
    
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldDescription() {
        return this.fieldDescription;
    }
    
    public void setFieldDescription(String fieldDescription) {
        this.fieldDescription = fieldDescription;
    }
    public Set getDoctorFieldHasDoctors() {
        return this.doctorFieldHasDoctors;
    }
    
    public void setDoctorFieldHasDoctors(Set doctorFieldHasDoctors) {
        this.doctorFieldHasDoctors = doctorFieldHasDoctors;
    }




}


