/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chirath Nissanka
 */
public class PatientHandler {
    
    private Session s;
    private Transaction tf;
    private int PatientID;
    
    public PatientHandler() {
        
    }
    
    public boolean insertPatient(String cUsername, String cPassword, String cPasswordConfirm, String cEmail, String cFirstName, String cLastName, String cMiddleName, String cNIC, String cDobDate, String cDobMonth, String cDobYear, String cMobile, String cAddress) {
        
        try {
            s = Connection.Connector.getSessionFactory().openSession();
            tf = s.beginTransaction();
            POJOs.Patient patient = new POJOs.Patient();
            
            patient.setPatientFname(cFirstName);
            patient.setPatientMname(cMiddleName);
            patient.setPatientLname(cLastName);
            patient.setPatientNic(cNIC);
            patient.setPatientPhoneno(cMobile);
            patient.setPatientEmail(cEmail);
            patient.setPatientAddress(cAddress);
            patient.setPatientDob(cDobDate + " - " + cDobMonth + " - " + cDobYear);
            s.save(patient);
            
            POJOs.User user = new POJOs.User();
            
            POJOs.Doctor dummyDoc = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", -1)).uniqueResult();
            POJOs.Administrator dummyAdmin = (POJOs.Administrator) s.createCriteria(POJOs.Administrator.class).add(Restrictions.eq("idadministrator", -1)).uniqueResult();
            user.setUsername(cUsername);
            user.setPassword(cPassword);
            user.setPatient(patient);
            user.setDoctor(dummyDoc);
            user.setAdministrator(dummyAdmin);
            user.setUsertype("CUSTOMER");
            
            s.save(user);
            
            tf.commit();
            s.close();
            tf = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    boolean output = true;
   // boolean flag = true;
    public boolean insertPatient(String cUsername, String cPassword, String cPasswordConfirm, String cEmail, String cFirstName, String cLastName, String cMiddleName, String cNIC, String cDob, String cMobile, String cGender, String cAddress) {
        try {
            s = Connection.Connector.getSessionFactory().openSession();
            tf = s.beginTransaction();
            POJOs.Patient patient = new POJOs.Patient();
            
            boolean hasEmailDup = s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("patientEmail", cEmail)).list().size() != 0;
            boolean confirmPasswordRes = cPassword.equals(cPasswordConfirm);
            if (hasEmailDup){
                output = false;
                return false;
            }
            
            if (!confirmPasswordRes){
                output = false;
                return false;
            }
            patient.setPatientFname(cFirstName);
            patient.setPatientMname(cMiddleName);
            patient.setPatientLname(cLastName);
            patient.setPatientDob(cDob);
            patient.setPatientAddress(cAddress);
            patient.setPatientNic(cNIC);
            patient.setPatientGender(cMiddleName);
            patient.setPatientPhoneno(cMobile);
            patient.setPatientGender(cGender);
            patient.setPatientEmail(cEmail);
            
            POJOs.User user = new POJOs.User();
            POJOs.Doctor dummyDoc = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", -1)).uniqueResult();
            POJOs.Administrator dummyAdmin = (POJOs.Administrator) s.createCriteria(POJOs.Administrator.class).add(Restrictions.eq("idadministrator", -1)).uniqueResult();
            user.setUsername(cUsername);
            user.setPassword(cPassword);
            user.setPatient(patient);
            user.setDoctor(dummyDoc);
            user.setAdministrator(dummyAdmin);
            user.setUsertype("CUSTOMER");
            s.save(patient);
            s.save(user);
            
            tf.commit();
            
            s.close();
            tf = null;
            return output;
        } catch (Exception e) {
            return false;
        }
        
    }
    
}
