package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1



/**
 * DoctorFieldHasDoctor generated by hbm2java
 */
public class DoctorFieldHasDoctor  implements java.io.Serializable {


     private Integer idDocFieldHasDoctor;
     private Doctor doctor;
     private DoctorField doctorField;

    public DoctorFieldHasDoctor() {
    }

    public DoctorFieldHasDoctor(Doctor doctor, DoctorField doctorField) {
       this.doctor = doctor;
       this.doctorField = doctorField;
    }
   
    public Integer getIdDocFieldHasDoctor() {
        return this.idDocFieldHasDoctor;
    }
    
    public void setIdDocFieldHasDoctor(Integer idDocFieldHasDoctor) {
        this.idDocFieldHasDoctor = idDocFieldHasDoctor;
    }
    public Doctor getDoctor() {
        return this.doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public DoctorField getDoctorField() {
        return this.doctorField;
    }
    
    public void setDoctorField(DoctorField doctorField) {
        this.doctorField = doctorField;
    }




}

