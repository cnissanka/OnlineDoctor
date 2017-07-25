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
@WebServlet(name = "handlePrescriptionCreate", urlPatterns = {"/handlePrescriptionCreate"})
public class handlePrescriptionCreate extends HttpServlet {

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
            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            
            POJOs.Patient patient = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(request.getParameter("patientid")))).uniqueResult();
            
            String medicineQtys[] = request.getParameter("medicalList").split("#");
           
            //saving prescription
            SimpleDateFormat df = new SimpleDateFormat("MM-dd-yyyy");
            String cDate = df.format(new Date());

            System.out.println(cDate);

            long theFuture = System.currentTimeMillis() + (86400 * 7 * 1000);
            Date nextWeek = new Date(theFuture);
            
            String cExpire = df.format(nextWeek);
            System.out.println(cExpire);
            
            POJOs.Prescription prescription = new POJOs.Prescription();
            prescription.setPrescriptionStartdate(cDate);
            prescription.setPrescriptionEnddate(cExpire);
            prescription.setPrescriptionStatus("VALID");
            prescription.setDoctor(user.getDoctor());
            prescription.setPatient(patient);
            
            s.save(prescription);
            //finish saving prescription
            
            //saving medicine and the prescription they belong to 
            
            for (String rec : medicineQtys){
                System.out.println("hello");
                if (rec.equals("")){break;}
                String array [] = rec.split(";");
                POJOs.PrescriptionHasMedicine medicinePrescription = new POJOs.PrescriptionHasMedicine();
                medicinePrescription.setDirectionUse(array[0]);
                System.out.println(array[0] + " " + array[1]);
                POJOs.Medicine medicine = (POJOs.Medicine) s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("idmedicine", Integer.parseInt(array[1]))).uniqueResult();
                medicinePrescription.setMedicine(medicine);
                medicinePrescription.setPrescription(prescription);
                s.save(medicinePrescription);
            }
            
            tf.commit();
            s.close();

        } catch (Exception e) {
            throw new ServletException(e);
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
