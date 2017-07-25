package POJOs;
// Generated Feb 4, 2016 8:25:00 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Shipping generated by hbm2java
 */
public class Shipping  implements java.io.Serializable {


     private int idshipping;
     private String region;
     private Double price;
     private Set invoices = new HashSet(0);

    public Shipping() {
    }

	
    public Shipping(int idshipping) {
        this.idshipping = idshipping;
    }
    public Shipping(int idshipping, String region, Double price, Set invoices) {
       this.idshipping = idshipping;
       this.region = region;
       this.price = price;
       this.invoices = invoices;
    }
   
    public int getIdshipping() {
        return this.idshipping;
    }
    
    public void setIdshipping(int idshipping) {
        this.idshipping = idshipping;
    }
    public String getRegion() {
        return this.region;
    }
    
    public void setRegion(String region) {
        this.region = region;
    }
    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    public Set getInvoices() {
        return this.invoices;
    }
    
    public void setInvoices(Set invoices) {
        this.invoices = invoices;
    }




}

