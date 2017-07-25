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
 * @author codeguy
 */
public class AppointmentHandler {

    public boolean insertAppointment(String doctor, String patient, String service, String timeslot, String dayofweek) {
        boolean result = false;
        try {
            
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            POJOs.Doctor selectedDoctor = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(doctor))).uniqueResult();
            POJOs.Patient selectedPatient = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(patient))).uniqueResult();
            POJOs.MedicalServices selectedService = (POJOs.MedicalServices) s.createCriteria(POJOs.MedicalServices.class).add(Restrictions.eq("idmedicalServices", Integer.parseInt(service))).uniqueResult();
            
            POJOs.TimeTableServices timeRecord = new POJOs.TimeTableServices();
            int id_appointments = s.createCriteria(POJOs.TimeTableServices.class).list().size();
            
            timeRecord.setIdTimeTableServices(id_appointments);
            timeRecord.setDoctor(selectedDoctor);
            timeRecord.setPatient(selectedPatient);
            timeRecord.setTimeSlot(timeslot);
            timeRecord.setDay(dayofweek);
            timeRecord.setMedicalServices(selectedService);

            s.save(timeRecord);
            tf.commit();
            
            s.close();
            tf = null;
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
            
        return result;
    }

    public boolean deleteAppointment(String id) {
        boolean result = false;
        try {
            
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            POJOs.TimeTableServices cTime = (POJOs.TimeTableServices) s.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("idTimeTableServices", Integer.parseInt(id))).uniqueResult();
            
            
            s.delete(cTime);
            tf.commit();
            
            s.close();
            tf = null;
            result = true;
            
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }
    
}
