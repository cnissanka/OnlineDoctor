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
@WebServlet(name = "handlePatientUserCreate", urlPatterns = {"/handlePatientUserCreate"})
public class handlePatientUserCreate extends HttpServlet {

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
            //Gather the user input
            String cUsername = request.getParameter("username");
            String cPassword = request.getParameter("password");
            String cPasswordConfirm = request.getParameter("confirmpassword");
            String cEmail = request.getParameter("email");
            String cFirstName = request.getParameter("firstname");
            String cLastName = request.getParameter("lastname");
            String cMiddleName = request.getParameter("middlename");
            String cNIC = request.getParameter("nic");
            String cDobDate = request.getParameter("dob_date");
            String cDobMonth = request.getParameter("dob_month");
            String cDobYear = request.getParameter("dob_year");
            String cSalutation = request.getParameter("saluation");
            String cGender = request.getParameter("gender");
            String cDob = request.getParameter("dob");
            String cMobile = request.getParameter("phone");
            String cAddress = request.getParameter("address");

            Session s = Connection.Connector.getSessionFactory().openSession();

            boolean isUsernameDuplicate = s.createCriteria(POJOs.User.class).add(Restrictions.eq("username", cUsername)).list().isEmpty();

            if (!(cPassword.equals(cPasswordConfirm))) {
                response.sendRedirect("login_service.jsp?res=validation&validation_msg=Passwords Don't Match!");
            } else if (!isUsernameDuplicate) {
                response.sendRedirect("login_service.jsp?res=validation&validation_msg=Username " + cUsername + " is not unique!");
            } else {

                cFirstName = cSalutation + cFirstName;
                boolean output = false;
                output = new Model.PatientHandler().insertPatient(cUsername, cPassword, cPasswordConfirm, cEmail, cFirstName, cLastName, cMiddleName, cNIC, cDob, cMobile, cGender, cAddress);

                if (output) {
                    response.sendRedirect("login_service.jsp?res=success");
                } else {
                    response.sendRedirect("login_service.jsp?res=fail");
                }

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
