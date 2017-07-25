/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "handleUpdatePatient", urlPatterns = {"/handleUpdatePatient"})
public class handleUpdatePatient extends HttpServlet {

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
            
            POJOs.Patient cPatient = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(request.getParameter("id")))).uniqueResult();
            POJOs.User cUser = (POJOs.User) s.createCriteria(POJOs.User.class).add(Restrictions.eq("iduser", Integer.parseInt(request.getParameter("userid")))).uniqueResult();                           
            
            cUser.setPassword(request.getParameter("password"));
         //   cUser.setUsertype(request.getParameter(""));
            
            cPatient.setPatientFname(request.getParameter("firstname"));
            cPatient.setPatientMname(request.getParameter("middlename"));
            cPatient.setPatientLname(request.getParameter("lastname"));
            cPatient.setPatientPhoneno(request.getParameter("phone"));
            cPatient.setPatientEmail(request.getParameter("email"));
            cPatient.setPatientNic(request.getParameter("nic"));
            cPatient.setPatientAddress(request.getParameter("address"));
            s.update(cPatient);
            tf.commit();
            s.close();
            tf = null;
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
