/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.Vector;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chirath Nissanka
 */
public class DoctorHandler {

    public boolean createDoctor(String username, String password, String fname, String mname, String lname, String mobile, String land, String address, String date, String month, String year, String nic, String email, String qualification, String category) {
        
        try {
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            POJOs.Doctor doctor = new POJOs.Doctor();
            int id = s.createCriteria(POJOs.Doctor.class).list().size();
            doctor.setIddoctor(id);
            doctor.setDoctorFname(fname);
            doctor.setDoctorMname(mname);
            doctor.setDoctorLname(lname);
            doctor.setDoctorMobile(mobile);
            doctor.setDoctorLand(land);
            doctor.setDoctorAddress(address);
            s.save(doctor);
            POJOs.DoctorField dField = (POJOs.DoctorField) s.createCriteria(POJOs.DoctorField.class).add(Restrictions.eq("fieldName", category)).uniqueResult();
            
            POJOs.User dUser = new POJOs.User();
            dUser.setDoctor(doctor);
            
            dUser.setAdministrator((POJOs.Administrator) s.createCriteria(POJOs.Administrator.class).add(Restrictions.eq("idadministrator", -1)).uniqueResult());
            dUser.setPatient((POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", -1)).uniqueResult());
            dUser.setUsertype("DOCTOR");
            dUser.setPassword(password);
            dUser.setUsername(username);
            
            s.save(dUser);
            
           
          
            POJOs.DoctorFieldHasDoctor dfd = new POJOs.DoctorFieldHasDoctor();
      
          
            dfd.setDoctorField(dField);
            dfd.setDoctor(doctor);
            
            s.save(dfd);
            
            String arr_qual [] = qualification.split(",");
            
            for (String cQual : arr_qual){
                
                POJOs.DoctorQualifications dQual = (POJOs.DoctorQualifications) s.createCriteria(POJOs.DoctorQualifications.class).add(Restrictions.eq("qualificationName", cQual)).uniqueResult();
                
                
                POJOs.DoctorHasQualifications doctorQualification = new POJOs.DoctorHasQualifications();
                doctorQualification.setDoctor(doctor);
               
                doctorQualification.setDoctorQualifications(dQual);
                s.save(doctorQualification);
            }
            
            
            
            String dob = date + " - " + month + " - " + year;
            doctor.setDoctorDob(dob);
            doctor.setDoctorNic(nic);
            doctor.setDoctorEmail(email);
            
            s.save(doctor);
            tf.commit();
            s.close();
            tf = null;
            
           
            
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    public boolean createQualification(String name, String description) {
        try {
            //System.out.println("hello");
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            POJOs.DoctorQualifications qual = new POJOs.DoctorQualifications();
            qual.setQualificationName(name);
            qual.setQualificationDescription(description);
            
            s.save(qual);
            tf.commit();
            
            s.close();
            tf = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createField(String name, String field) {
        try {
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            POJOs.DoctorField docfield = new POJOs.DoctorField();
            docfield.setFieldName(name);
            docfield.setFieldDescription(field);
            
            s.save(docfield);
            tf.commit();
            
            s.close();
            tf = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;           
        }
    }
    
}
