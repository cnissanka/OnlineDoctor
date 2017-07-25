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
import org.hibernate.Transaction;

/**
 *
 * @author codeguy
 */
@WebServlet(name = "handlePatientUpdate_User", urlPatterns = {"/handlePatientUpdate_User"})
public class handlePatientUpdate_User extends HttpServlet {

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
            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            POJOs.Patient patient = user.getPatient();
            
            Session s = Connection.Connector.getSessionFactory().openSession();
            
            Transaction tf = s.beginTransaction();
            
            patient.setPatientFname(request.getParameter("fname"));
            patient.setPatientMname(request.getParameter("mname"));
            patient.setPatientLname(request.getParameter("lname"));
            patient.setPatientDob(request.getParameter("dob"));
            patient.setPatientAddress(request.getParameter("address"));
            patient.setPatientNic(request.getParameter("nic"));
            patient.setPatientPhoneno(request.getParameter("phone"));
            patient.setPatientEmail(request.getParameter("email"));
            
            if (!request.getParameter("password").equals("ppassword")){
                response.sendRedirect("login_service.jsp?res=validation&validation_msg=Passwords Dont' Match!");
            }else{
                user.setPassword(request.getParameter("password"));
                s.update(user);
                s.update(patient);
                tf.commit();
                s.close();
                request.getSession().setAttribute("user", user);
                response.sendRedirect("login_service.jsp?res=success");
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
