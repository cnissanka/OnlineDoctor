package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Symptom generated by hbm2java
 */
public class Symptom  implements java.io.Serializable {


     private Integer idsymptom;
     private String symptomName;
     private String symptomDescription;
     private Set dieseaseHasSymptoms = new HashSet(0);

    public Symptom() {
    }

    public Symptom(String symptomName, String symptomDescription, Set dieseaseHasSymptoms) {
       this.symptomName = symptomName;
       this.symptomDescription = symptomDescription;
       this.dieseaseHasSymptoms = dieseaseHasSymptoms;
    }
   
    public Integer getIdsymptom() {
        return this.idsymptom;
    }
    
    public void setIdsymptom(Integer idsymptom) {
        this.idsymptom = idsymptom;
    }
    public String getSymptomName() {
        return this.symptomName;
    }
    
    public void setSymptomName(String symptomName) {
        this.symptomName = symptomName;
    }
    public String getSymptomDescription() {
        return this.symptomDescription;
    }
    
    public void setSymptomDescription(String symptomDescription) {
        this.symptomDescription = symptomDescription;
    }
    public Set getDieseaseHasSymptoms() {
        return this.dieseaseHasSymptoms;
    }
    
    public void setDieseaseHasSymptoms(Set dieseaseHasSymptoms) {
        this.dieseaseHasSymptoms = dieseaseHasSymptoms;
    }




}

