package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1



/**
 * TimeTableServices generated by hbm2java
 */
public class TimeTableServices  implements java.io.Serializable {


     private int idTimeTableServices;
     private Doctor doctor;
     private MedicalServices medicalServices;
     private Patient patient;
     private String day;
     private String timeSlot;

    public TimeTableServices() {
    }

	
    public TimeTableServices(int idTimeTableServices, Doctor doctor, MedicalServices medicalServices, Patient patient) {
        this.idTimeTableServices = idTimeTableServices;
        this.doctor = doctor;
        this.medicalServices = medicalServices;
        this.patient = patient;
    }
    public TimeTableServices(int idTimeTableServices, Doctor doctor, MedicalServices medicalServices, Patient patient, String day, String timeSlot) {
       this.idTimeTableServices = idTimeTableServices;
       this.doctor = doctor;
       this.medicalServices = medicalServices;
       this.patient = patient;
       this.day = day;
       this.timeSlot = timeSlot;
    }
   
    public int getIdTimeTableServices() {
        return this.idTimeTableServices;
    }
    
    public void setIdTimeTableServices(int idTimeTableServices) {
        this.idTimeTableServices = idTimeTableServices;
    }
    public Doctor getDoctor() {
        return this.doctor;
    }
    
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
    public MedicalServices getMedicalServices() {
        return this.medicalServices;
    }
    
    public void setMedicalServices(MedicalServices medicalServices) {
        this.medicalServices = medicalServices;
    }
    public Patient getPatient() {
        return this.patient;
    }
    
    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    public String getDay() {
        return this.day;
    }
    
    public void setDay(String day) {
        this.day = day;
    }
    public String getTimeSlot() {
        return this.timeSlot;
    }
    
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }




}


