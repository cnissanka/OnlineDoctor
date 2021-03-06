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
@WebServlet(name = "handleGetDoctorDetails", urlPatterns = {"/handleGetDoctorDetails"})
public class handleGetDoctorDetails extends HttpServlet {

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
            POJOs.Doctor selectedDoctor = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("id")))).uniqueResult();
            String dataset = "";
            
            POJOs.User selectedUser = (POJOs.User) selectedDoctor.getUsers().iterator().next();
            POJOs.DoctorFieldHasDoctor selectedField = (POJOs.DoctorFieldHasDoctor) selectedDoctor.getDoctorFieldHasDoctors().iterator().next();
            
            dataset += selectedUser.getUsername() + "#" + selectedUser.getPassword() + "#";
            dataset += selectedDoctor.getDoctorFname() + "#" + selectedDoctor.getDoctorMname() + "#" + selectedDoctor.getDoctorLname() + "#";
            
            
            dataset += selectedDoctor.getDoctorDob() + "#" + selectedDoctor.getDoctorGender() + "#" + selectedDoctor.getDoctorMobile() + "#" + selectedDoctor.getDoctorLand() + "#" + selectedDoctor.getDoctorAveragePay() + "#" + selectedDoctor.getDoctorAppointmentCharge() + "#" + selectedDoctor.getDoctorAddress() + "#" + selectedField.getDoctorField().getFieldName() + "#" + selectedDoctor.getDoctorNic() + "#" + selectedDoctor.getDoctorEmail() + "#" + selectedDoctor.getIddoctor();
            out.write(dataset);
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
