/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Vector;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chirath Nissanka
 */
public class RuleBaseHandler {
    
    public boolean addSymptom(String name, String description) {
        try {
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            POJOs.Symptom sym = new POJOs.Symptom();
            sym.setSymptomName(name);
            sym.setSymptomDescription(description);
            
            s.save(sym);
            tf.commit();
            s.close();
            tf = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public boolean addDiesease(String name, String description) {
        try {
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            POJOs.Diesease dis = new POJOs.Diesease();
            dis.setDieseaseName(name);
            dis.setDieseaseDescription(description);
            
            s.save(dis);
            tf.commit();
            s.close();
            tf = null;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean addRule(String dataset) {
        boolean result = false;
        try {
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            
            String rules[] = dataset.split(";");
            System.out.println(dataset);
            for (int i = 0; i < rules.length; i++) {
                String cRule[] = rules[i].split(",");
                
                POJOs.Symptom cSym = (POJOs.Symptom) s.createCriteria(POJOs.Symptom.class).add(Restrictions.eq("symptomName", cRule[0])).uniqueResult();
                POJOs.Diesease cDis = (POJOs.Diesease) s.createCriteria(POJOs.Diesease.class).add(Restrictions.eq("dieseaseName", cRule[1])).uniqueResult();
                
          
                
                POJOs.DieseaseHasSymptom pRule = new POJOs.DieseaseHasSymptom();
              
                pRule.setDiesease(cDis);
                pRule.setSymptom(cSym);
                
                s.save(pRule);
                tf.commit();
               
            }
            s.close();
            tf = null;
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public void deleteRule(String id) {
        try {
            
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            System.out.println("id is "+id);
         
         //   POJOs.DieseaseHasSymptom cRule = (POJOs.DieseaseHasSymptom) s.createCriteria(POJOs.DieseaseHasSymptom.class).add(Restrictions.eq("id", Integer.parseInt(id))).uniqueResult();
            
         POJOs.DieseaseHasSymptom cRule = (POJOs.DieseaseHasSymptom) s.createCriteria(POJOs.DieseaseHasSymptom.class).add(Restrictions.eq("idRule", Integer.parseInt(id))).uniqueResult();
         s.delete(cRule);
          //  s.delete(cRule);
            tf.commit();
            s.close();
            tf = null;
            
        } catch (Exception e) {
            e.printStackTrace();
        
        }
    }
    
}
