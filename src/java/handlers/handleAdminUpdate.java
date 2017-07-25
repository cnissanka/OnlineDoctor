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
@WebServlet(name = "handleAdminUpdate", urlPatterns = {"/handleAdminUpdate"})
public class handleAdminUpdate extends HttpServlet {

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
            POJOs.Administrator selectedAdmin = (POJOs.Administrator) s.createCriteria(POJOs.Administrator.class).add(Restrictions.eq("idadministrator", Integer.parseInt(request.getParameter("id")))).uniqueResult();
            
            selectedAdmin.setAdminFname(request.getParameter("firstname"));
            selectedAdmin.setAdminMname(request.getParameter("middlename"));
            selectedAdmin.setAdminLname(request.getParameter("lastname"));
           
            if (request.getParameter("password").equals(request.getParameter("confirmpassword")) == false){
                out.write("validation#Passwords Don't Match!");
            }else if (request.getParameter("password").toCharArray().length < 6)
            {
                out.write("validation#Password is too short");
            }else{
                POJOs.User selectedUser = (POJOs.User) selectedAdmin.getUsers().iterator().next();
                selectedUser.setPassword(request.getParameter("password"));
                
                s.update(selectedUser);
            
            
            s.update(selectedAdmin);
            
            tf.commit();
            s.close();
            tf = null;
            out.write("success");
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
