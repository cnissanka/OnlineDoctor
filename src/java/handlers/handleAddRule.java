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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author codeguy
 */
@WebServlet(name = "handleAddRule", urlPatterns = {"/handleAddRule"})
public class handleAddRule extends HttpServlet {

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
            
            POJOs.Diesease disease = (POJOs.Diesease) s.createCriteria(POJOs.Diesease.class).add(Restrictions.eq("dieseaseName", request.getParameter("disease"))).uniqueResult();
            POJOs.Symptom symptom = (POJOs.Symptom) s.createCriteria(POJOs.Symptom.class).add(Restrictions.eq("symptomName", request.getParameter("symptom"))).uniqueResult();
            
            boolean isDuplicate = s.createCriteria(POJOs.DieseaseHasSymptom.class).add(Restrictions.eq("diesease", disease)).add(Restrictions.eq("symptom", symptom)).list().isEmpty();
           
            if (!isDuplicate){
                response.sendRedirect("admin/admin_knowledge.jsp?res=validation&validation_msg=Rule Entered is already there!");
            }else{
            
            POJOs.DieseaseHasSymptom rule = new POJOs.DieseaseHasSymptom();
            rule.setDiesease(disease);
            rule.setSymptom(symptom);
            
            s.save(rule);
            tf.commit();
            
            response.sendRedirect("admin/admin_knowledge.jsp?res=success");
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
