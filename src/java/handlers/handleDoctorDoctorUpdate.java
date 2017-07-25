/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "handleDoctorDoctorUpdate", urlPatterns = {"/handleDoctorDoctorUpdate"})
public class handleDoctorDoctorUpdate extends HttpServlet {

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
            
            POJOs.Doctor doctor = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("doctor")))).uniqueResult();
            doctor.setDoctorFname(request.getParameter("firstname"));
            doctor.setDoctorMname(request.getParameter("middlename"));
            doctor.setDoctorLname(request.getParameter("lastname"));
            doctor.setDoctorAddress(request.getParameter("address"));
            doctor.setDoctorNic(request.getParameter("nic"));
            doctor.setDoctorMobile(request.getParameter("mobile"));
            doctor.setDoctorLand(request.getParameter("land"));
            doctor.setDoctorDob(request.getParameter("dob"));
            
           
            
            POJOs.DoctorQualifications qual = (POJOs.DoctorQualifications) s.createCriteria(POJOs.DoctorQualifications.class).add(Restrictions.eq("qualificationName", request.getParameter("qualification"))).uniqueResult();
            
            Iterator<POJOs.DoctorHasQualifications> qualArray = doctor.getDoctorHasQualificationses().iterator();
            boolean isQualDuplicate = false;
            
            while (qualArray.hasNext()) {
                POJOs.DoctorHasQualifications rec = qualArray.next();
                if (rec.getDoctorQualifications().equals(qual)) {
                    isQualDuplicate = true;
                    break;
                }
            }
            
            if (isQualDuplicate) {
                response.sendRedirect("doctor_account_settings.jsp?res=validation&validation_msg=Qualification Already Exists!");
            } else {
                
                POJOs.DoctorHasQualifications doctorQual = new POJOs.DoctorHasQualifications();
                doctorQual.setDoctor(doctor);
                int id = s.createCriteria(POJOs.DoctorHasQualifications.class).list().size();
                doctorQual.setDoctorQualifications(qual);
                doctorQual.setIdDoctorHasQualification(id);
                s.save(doctorQual);
                POJOs.DoctorFieldHasDoctor docField = (POJOs.DoctorFieldHasDoctor) s.createCriteria(POJOs.DoctorFieldHasDoctor.class).add(Restrictions.eq("doctor", doctor)).uniqueResult();
                POJOs.DoctorField field = (POJOs.DoctorField) s.createCriteria(POJOs.DoctorField.class).add(Restrictions.eq("fieldName", request.getParameter("category"))).uniqueResult();
                
                System.out.println(field);
         
                docField.setDoctorField(field);
                docField.setDoctor(doctor);
                s.update(docField);
                s.update(doctor);
                tf.commit();
                s.close();
                tf = null;
                response.sendRedirect("doctor/doctor_account_settings.jsp?res=success");
            }
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
