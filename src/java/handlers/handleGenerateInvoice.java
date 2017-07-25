/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author codeguy
 */
@WebServlet(name = "handleGenerateInvoice", urlPatterns = {"/handleGenerateInvoice"})
public class handleGenerateInvoice extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
            HashMap<Integer, Integer> cart = (HashMap<Integer, Integer>) request.getSession().getAttribute("cart");
            if (cart.isEmpty()) {
               out.write("empty cart");
            } else {
                int presId = (Integer) request.getSession().getAttribute("pres");
                POJOs.Prescription cPres = (POJOs.Prescription) s.createCriteria(POJOs.Prescription.class).add(Restrictions.eq("idprescription", presId)).uniqueResult();

                //find net price and gross price
                double grossPrice = 0;
                double netPrice;
               
                Iterator<Integer> medicineList = cart.keySet().iterator();
                
                while (medicineList.hasNext()) {
                    int medid = medicineList.next();
                    POJOs.Medicine cMed = (POJOs.Medicine) s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("idmedicine", medid)).uniqueResult();
                    int qty = cart.get(medid);
                    int cQty = Integer.parseInt(cMed.getMedicineQty());
                    cMed.setMedicineQty((cQty - qty) + "");
                    grossPrice += (qty * cMed.getMedicinePrice());
                    s.update(cMed);
                }

                netPrice = grossPrice;

                if (cPres.getPrescriptionStatus().equals("VALID")) {
                    netPrice += cPres.getDoctor().getDoctorAppointmentCharge();
                    cPres.setPrescriptionStatus("INVALID"); // doctor appointment fee is not charged if you buy the goods again.
                    s.update(cPres);
                }
                
                
                //creating invoice obj
                POJOs.Invoice invoice = new POJOs.Invoice();
                invoice.setGrossPrice(grossPrice);
                

                SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
                String cDate = df.format(new Date());

                POJOs.Shipping shipping = (POJOs.Shipping) s.createCriteria(POJOs.Shipping.class).add(Restrictions.eq("idshipping", Integer.parseInt(request.getParameter("shipping")))).uniqueResult();
                netPrice += shipping.getPrice();
                invoice.setShipping(shipping);
                POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
                invoice.setPatient(user.getPatient());
                invoice.setInvoiceDate(cDate);
                invoice.setNetPrice(netPrice);
                int invoiceid = s.createCriteria(POJOs.Invoice.class).list().size() + 1;
                
                invoice.setIdinvoice(invoiceid);
                s.save(invoice);

                //delete all medicine selected from prescription obj
                Iterator<POJOs.PrescriptionHasMedicine> iPresMed = cPres.getPrescriptionHasMedicines().iterator();
                while (iPresMed.hasNext()) {
                    POJOs.PrescriptionHasMedicine presMed = iPresMed.next();
                    if (cart.get(presMed.getMedicine().getIdmedicine()) != null) {
                        s.delete(presMed);
                    }
                }

                //delete prescription if it has no medicine left
                iPresMed = cPres.getPrescriptionHasMedicines().iterator();
                if (!iPresMed.hasNext()){
                    
                    s.delete(cPres);
                }
                
                tf.commit();
                
                out.write(invoiceid + "");}
            
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
