package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Medicine generated by hbm2java
 */
public class Medicine  implements java.io.Serializable {


     private Integer idmedicine;
     private Supplier supplier;
     private String medicineName;
     private String medicineDescription;
     private String medicineImage;
     private Double medicinePrice;
     private String medicineQty;
     private Set grns = new HashSet(0);
     private Set prescriptionHasMedicines = new HashSet(0);

    public Medicine() {
    }

	
    public Medicine(Supplier supplier) {
        this.supplier = supplier;
    }
    public Medicine(Supplier supplier, String medicineName, String medicineDescription, String medicineImage, Double medicinePrice, String medicineQty, Set grns, Set prescriptionHasMedicines) {
       this.supplier = supplier;
       this.medicineName = medicineName;
       this.medicineDescription = medicineDescription;
       this.medicineImage = medicineImage;
       this.medicinePrice = medicinePrice;
       this.medicineQty = medicineQty;
       this.grns = grns;
       this.prescriptionHasMedicines = prescriptionHasMedicines;
    }
   
    public Integer getIdmedicine() {
        return this.idmedicine;
    }
    
    public void setIdmedicine(Integer idmedicine) {
        this.idmedicine = idmedicine;
    }
    public Supplier getSupplier() {
        return this.supplier;
    }
    
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    public String getMedicineName() {
        return this.medicineName;
    }
    
    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
    public String getMedicineDescription() {
        return this.medicineDescription;
    }
    
    public void setMedicineDescription(String medicineDescription) {
        this.medicineDescription = medicineDescription;
    }
    public String getMedicineImage() {
        return this.medicineImage;
    }
    
    public void setMedicineImage(String medicineImage) {
        this.medicineImage = medicineImage;
    }
    public Double getMedicinePrice() {
        return this.medicinePrice;
    }
    
    public void setMedicinePrice(Double medicinePrice) {
        this.medicinePrice = medicinePrice;
    }
    public String getMedicineQty() {
        return this.medicineQty;
    }
    
    public void setMedicineQty(String medicineQty) {
        this.medicineQty = medicineQty;
    }
    public Set getGrns() {
        return this.grns;
    }
    
    public void setGrns(Set grns) {
        this.grns = grns;
    }
    public Set getPrescriptionHasMedicines() {
        return this.prescriptionHasMedicines;
    }
    
    public void setPrescriptionHasMedicines(Set prescriptionHasMedicines) {
        this.prescriptionHasMedicines = prescriptionHasMedicines;
    }




}


