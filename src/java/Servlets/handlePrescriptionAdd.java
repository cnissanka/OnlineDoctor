/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
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
@WebServlet(name = "handlePrescriptionAdd", urlPatterns = {"/handlePrescriptionAdd"})
public class handlePrescriptionAdd extends HttpServlet {

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
            String path = "uploads/prescriptions/";
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();

            POJOs.Patient cPatient = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(request.getParameter("patient")))).uniqueResult();
            POJOs.Doctor cDoctor = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("doctor")))).uniqueResult();

            String currentDate[] = new Date().toGMTString().split(" ");
            String fCurrentDate = currentDate[0] + " " + currentDate[1] + " " + currentDate[2];
            System.out.println(fCurrentDate);

            Date today = new Date();
            SimpleDateFormat formattedDate = new SimpleDateFormat("ddMMyyyy");
            fCurrentDate = formattedDate.format(today);
            
            System.out.println(fCurrentDate);
            
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, 7);  // number of days to add      
            String tomorrow = (String) (formattedDate.format(c.getTime()));
            System.out.println("Tomorrows date is " + tomorrow);
            
            
            
            POJOs.Prescription pPrescription = new POJOs.Prescription();
            pPrescription.setDoctor(cDoctor);
            pPrescription.setPatient(cPatient);
            pPrescription.setPrescriptionStartdate(fCurrentDate);
            pPrescription.setPrescriptionEnddate(tomorrow);
            pPrescription.setPrescriptionStatus("ACTIVE");
            s.save(pPrescription);
            
            path += System.currentTimeMillis() + "prescription.pre";
            File prescriptionFile = new File(request.getServletContext().getRealPath("") +"/"+ path);
            BufferedWriter br = new BufferedWriter(new FileWriter(prescriptionFile));
            
            //Encrypting details
            KeyGenerator keygenerator = KeyGenerator.getInstance("DES");
            SecretKey myDesKey = keygenerator.generateKey();
            
            Cipher desCipher;
            desCipher = Cipher.getInstance("DES");
            
            byte [] text = request.getParameter("medicinelist").getBytes("UTF-8");
            
            desCipher.init(Cipher.ENCRYPT_MODE, myDesKey);
            byte [] textEncrypted = desCipher.doFinal(text);
            
            String sEnc = new String(textEncrypted);
            System.out.println(sEnc);
            
            br.write(sEnc);
            br.flush();
            br.close();;
            tf.commit();
            s.close();
            tf = null;
            out.write(path);
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
