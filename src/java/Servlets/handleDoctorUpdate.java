/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import POJOs.DoctorField;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
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
@WebServlet(name = "handleDoctorUpdate", urlPatterns = {"/handleDoctorUpdate"})
public class handleDoctorUpdate extends HttpServlet {

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
            
            POJOs.Doctor cDoctor = (POJOs.Doctor) s.createCriteria(POJOs.Doctor.class).add(Restrictions.eq("iddoctor", Integer.parseInt(request.getParameter("doctorid")))).uniqueResult();
            System.out.println(cDoctor == null);
            POJOs.User cUser = (POJOs.User) cDoctor.getUsers().iterator().next();
            System.out.println(cUser == null);
            cUser.setPassword(request.getParameter("password"));
            s.update(cUser);
            
            cDoctor.setDoctorFname(request.getParameter("fname"));
            cDoctor.setDoctorMname(request.getParameter("mname"));
            cDoctor.setDoctorLname(request.getParameter("lname"));
            cDoctor.setDoctorAddress(request.getParameter("address"));
            cDoctor.setDoctorNic(request.getParameter("nic"));
            cDoctor.setDoctorLand(request.getParameter("land"));
            cDoctor.setDoctorMobile(request.getParameter("mobile"));
            cDoctor.setDoctorEmail(request.getParameter("email"));
            cDoctor.setDoctorDob(request.getParameter("dob"));
            
            POJOs.DoctorFieldHasDoctor  docField = (POJOs.DoctorFieldHasDoctor) s.createCriteria(POJOs.DoctorFieldHasDoctor.class).add(Restrictions.eq("doctor", cDoctor)).uniqueResult();
            POJOs.DoctorField dFieldSelected = (POJOs.DoctorField) s.createCriteria(POJOs.DoctorField.class).add(Restrictions.eq("fieldName", request.getParameter("category"))).uniqueResult();
            docField.setDoctorField(dFieldSelected);
            s.update(docField);
            
           
            s.update(cDoctor);
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
