/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
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
@WebServlet(name = "handleDoctorDelete", urlPatterns = {"/handleDoctorDelete"})
public class handleDoctorDelete extends HttpServlet {

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
            
            POJOs.Doctor doc = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("iddoctor")))).uniqueResult();
            POJOs.User user = (POJOs.User) doc.getUsers().iterator().next();
            s.delete(user);
            
            
            
            Iterator <POJOs.TimeTableServices> iTimeTable = doc.getTimeTableServiceses().iterator();
            while (iTimeTable != null && iTimeTable.hasNext()){
                POJOs.TimeTableServices time = (POJOs.TimeTableServices) iTimeTable.next();
                s.delete(time);
            }
            
            Iterator <POJOs.Appointment> iAppointment = doc.getAppointments().iterator();
            while (iAppointment != null && iAppointment.hasNext()){
               POJOs.Appointment app = (POJOs.Appointment) iAppointment.next();
               s.delete(app);
            }
            
            Iterator <POJOs.Prescription> iPrescription = doc.getPrescriptions().iterator();
            while (iPrescription != null && iPrescription.hasNext()){
                POJOs.Prescription pre = (POJOs.Prescription) iPrescription.next();
                s.delete(pre);
            }
           
            List <POJOs.DoctorHasQualifications> qualificationList = s.createCriteria(POJOs.DoctorHasQualifications.class).add(Restrictions.eq("doctor", doc)).list();
            
            for (POJOs.DoctorHasQualifications dq : qualificationList){
                s.delete(dq);
            }
            
            s.delete(doc);
            tf.commit();
            
            
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
