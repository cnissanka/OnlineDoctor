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
@WebServlet(name = "handlePatient_Edit", urlPatterns = {"/handlePatient_Edit"})
public class handlePatient_Edit extends HttpServlet {

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
            String type = request.getParameter("btnDelete");
            Session s = Connection.Connector.getSessionFactory().openSession();
            Transaction tf = s.beginTransaction();
                POJOs.Patient cPatient = (POJOs.Patient) s.createCriteria(POJOs.Patient.class).add(Restrictions.eq("idpatient", Integer.parseInt(request.getParameter("txtid")))).uniqueResult();
            if (type.equals("Update Details")){
                cPatient.setPatientFname(request.getParameter("txtFname"));
                cPatient.setPatientMname(request.getParameter("txtMName"));
                cPatient.setPatientLname(request.getParameter("txtLName"));
                cPatient.setPatientAddress(request.getParameter("txtAddress"));
                cPatient.setPatientNic(request.getParameter("txtNIC"));
                cPatient.setPatientEmail(request.getParameter("txtEmailAddress"));
                cPatient.setPatientDob(request.getParameter("txtDay") + " - " + request.getParameter("txtMonth") + " - " + request.getParameter("txtYear"));
                cPatient.setPatientPhoneno(request.getParameter("txtPhoneNo"));
                s.update(cPatient);
                tf.commit();
                s.close();
                tf = null;
                response.sendRedirect("backend_admin/backend_index.jsp?page=customer");
            }else if (type.equals("Delete Details")){
              POJOs.User cUser = (POJOs.User) s.createCriteria(POJOs.User.class).add(Restrictions.eq("patient", cPatient)).uniqueResult();
              s.delete(cUser);
              s.delete(cPatient);
              tf.commit();
              s.close();
              tf = null;
              response.sendRedirect("backend_admin/backend_index.jsp?page=customer");
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
