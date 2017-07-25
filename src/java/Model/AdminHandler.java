/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chirath Nissanka
 */
public class AdminHandler {

    public boolean addAdmin(String username, String password, String fname, String mname, String lname) {
        try {
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            
            POJOs.Administrator admin = new POJOs.Administrator();
            admin.setAdminFname(fname);
            admin.setAdminMname(mname);
            admin.setAdminLname(lname);
            
            POJOs.Doctor user_doc = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", -1)).uniqueResult();
            POJOs.Patient user_pat = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", -1)).uniqueResult();
            
            POJOs.User user = new POJOs.User();
            user.setDoctor(user_doc);
            user.setPatient(user_pat);
            user.setUsername(username);
            user.setPassword(password);
            user.setAdministrator(admin);
            user.setUsertype("ADMINISTRATOR");
           
            s.save(admin);
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
    
    public void addComponents (){
        try {
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            POJOs.AdminAccess access1 = new POJOs.AdminAccess();
            access1.setAccessComp("pharamacy");
            
            POJOs.AdminAccess access2 = new POJOs.AdminAccess();
            access2.setAccessComp("doctor");
            
            POJOs.AdminAccess access3 = new POJOs.AdminAccess();
            access3.setAccessComp("customer");
            
            POJOs.AdminAccess access4 = new POJOs.AdminAccess();
            access4.setAccessComp("appointment");
            
            POJOs.AdminAccess access5 = new POJOs.AdminAccess();
            access5.setAccessComp("knowledge");
            
            POJOs.AdminAccess access6 = new POJOs.AdminAccess();
            access6.setAccessComp("service");
            
            POJOs.AdminAccess access7 = new POJOs.AdminAccess();
            access7.setAccessComp("blog");
            
            POJOs.AdminAccess access8 = new POJOs.AdminAccess();
            access8.setAccessComp("misc");
            
            s.save(access1);
            s.save(access2);
            s.save(access3);
            s.save(access4);
            s.save(access5);
            s.save(access6);
            s.save(access7);
            s.save(access8);
            
            tf.commit();
            
            System.out.println("Init of components done - success");
            
            s.close();
            tf = null;
        } catch (Exception e) {
            e.printStackTrace();;
            System.out.println("Init of components done - failed!");
        }
    }
    
}
