/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author codeguy
 */
@WebServlet(name = "handleAddMedicalService", urlPatterns = {"/handleAddMedicalService"})
public class handleAddMedicalService extends HttpServlet {

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
             Session s2 = Connection.Connector.getSessionFactory().openSession();
            int limit = 10;

            POJOs.TimeTableServices mService = new POJOs.TimeTableServices();
            mService.setDay(request.getParameter("date"));
            System.out.println(request.getParameter("doctor"));
            POJOs.Doctor doctor = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("doctor")))).uniqueResult();
            POJOs.MedicalServices medicine = (POJOs.MedicalServices) s.createCriteria(POJOs.MedicalServices.class).add(Restrictions.eq("idmedicalServices", Integer.parseInt(request.getParameter("medicine")))).uniqueResult();
            POJOs.Patient patient = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(request.getParameter("patient")))).uniqueResult();
            
            int appointmentCount = s2.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("doctor", doctor)).list().size();
            int patientCount = s2.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("patient", patient)).add(Restrictions.eq("day", request.getParameter("date"))).add(Restrictions.eq("doctor", doctor)).add(Restrictions.eq("medicalServices", medicine)).list().size();
            int id = s2.createCriteria(POJOs.TimeTableServices.class).list().size();
            if (appointmentCount > limit) {
                response.sendRedirect("medical_services.jsp?res=validation&validation_msg=Too many appointments for Dr." + doctor.getDoctorFname() + " " + doctor.getDoctorLname());
            } else if (patientCount >= 1) {
                response.sendRedirect("medical_services.jsp?res=validation&validation_msg=You already made an appointment for the Medical Service with the doctor!");
            } else {
                mService.setIdTimeTableServices(id);
                mService.setDoctor(doctor);
                mService.setMedicalServices(medicine);
                mService.setPatient(patient);

                s.save(mService);

                s.beginTransaction().commit();
                s.close();
                response.sendRedirect("medical_services.jsp?res=success");

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
