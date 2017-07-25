package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1



/**
 * DoctorTemp generated by hbm2java
 */
public class DoctorTemp  implements java.io.Serializable {


     private Integer iddoctor;
     private DoctorClasses doctorClasses;
     private String doctorFname;
     private String doctorMname;
     private String doctorLname;
     private String doctorMobile;
     private String doctorLand;
     private String doctorNic;
     private String doctorDob;
     private String doctorAddress;
     private String doctorEmail;
     private Double doctorAveragePay;
     private Double doctorAppointmentCharge;
     private String doctorGender;
     private String doctorCv;

    public DoctorTemp() {
    }

	
    public DoctorTemp(DoctorClasses doctorClasses) {
        this.doctorClasses = doctorClasses;
    }
    public DoctorTemp(DoctorClasses doctorClasses, String doctorFname, String doctorMname, String doctorLname, String doctorMobile, String doctorLand, String doctorNic, String doctorDob, String doctorAddress, String doctorEmail, Double doctorAveragePay, Double doctorAppointmentCharge, String doctorGender, String doctorCv) {
       this.doctorClasses = doctorClasses;
       this.doctorFname = doctorFname;
       this.doctorMname = doctorMname;
       this.doctorLname = doctorLname;
       this.doctorMobile = doctorMobile;
       this.doctorLand = doctorLand;
       this.doctorNic = doctorNic;
       this.doctorDob = doctorDob;
       this.doctorAddress = doctorAddress;
       this.doctorEmail = doctorEmail;
       this.doctorAveragePay = doctorAveragePay;
       this.doctorAppointmentCharge = doctorAppointmentCharge;
       this.doctorGender = doctorGender;
       this.doctorCv = doctorCv;
    }
   
    public Integer getIddoctor() {
        return this.iddoctor;
    }
    
    public void setIddoctor(Integer iddoctor) {
        this.iddoctor = iddoctor;
    }
    public DoctorClasses getDoctorClasses() {
        return this.doctorClasses;
    }
    
    public void setDoctorClasses(DoctorClasses doctorClasses) {
        this.doctorClasses = doctorClasses;
    }
    public String getDoctorFname() {
        return this.doctorFname;
    }
    
    public void setDoctorFname(String doctorFname) {
        this.doctorFname = doctorFname;
    }
    public String getDoctorMname() {
        return this.doctorMname;
    }
    
    public void setDoctorMname(String doctorMname) {
        this.doctorMname = doctorMname;
    }
    public String getDoctorLname() {
        return this.doctorLname;
    }
    
    public void setDoctorLname(String doctorLname) {
        this.doctorLname = doctorLname;
    }
    public String getDoctorMobile() {
        return this.doctorMobile;
    }
    
    public void setDoctorMobile(String doctorMobile) {
        this.doctorMobile = doctorMobile;
    }
    public String getDoctorLand() {
        return this.doctorLand;
    }
    
    public void setDoctorLand(String doctorLand) {
        this.doctorLand = doctorLand;
    }
    public String getDoctorNic() {
        return this.doctorNic;
    }
    
    public void setDoctorNic(String doctorNic) {
        this.doctorNic = doctorNic;
    }
    public String getDoctorDob() {
        return this.doctorDob;
    }
    
    public void setDoctorDob(String doctorDob) {
        this.doctorDob = doctorDob;
    }
    public String getDoctorAddress() {
        return this.doctorAddress;
    }
    
    public void setDoctorAddress(String doctorAddress) {
        this.doctorAddress = doctorAddress;
    }
    public String getDoctorEmail() {
        return this.doctorEmail;
    }
    
    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }
    public Double getDoctorAveragePay() {
        return this.doctorAveragePay;
    }
    
    public void setDoctorAveragePay(Double doctorAveragePay) {
        this.doctorAveragePay = doctorAveragePay;
    }
    public Double getDoctorAppointmentCharge() {
        return this.doctorAppointmentCharge;
    }
    
    public void setDoctorAppointmentCharge(Double doctorAppointmentCharge) {
        this.doctorAppointmentCharge = doctorAppointmentCharge;
    }
    public String getDoctorGender() {
        return this.doctorGender;
    }
    
    public void setDoctorGender(String doctorGender) {
        this.doctorGender = doctorGender;
    }
    public String getDoctorCv() {
        return this.doctorCv;
    }
    
    public void setDoctorCv(String doctorCv) {
        this.doctorCv = doctorCv;
    }




}


