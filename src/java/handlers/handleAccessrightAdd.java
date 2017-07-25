/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "handleAccessrightAdd", urlPatterns = {"/handleAccessrightAdd"})
public class handleAccessrightAdd extends HttpServlet {

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
            
            Vector <Integer> adminIdsStore = new Vector<>();
            
            String accessRights [] = request.getParameter("dataset").split("#");
            
            for (String access : accessRights){
                switch (access) {
                    case "patient":
                        adminIdsStore.add(3);
                        break;
                    case "doctor":
                        adminIdsStore.add(2);
                        break;
                    case "pharmacy":
                        adminIdsStore.add(1);
                        break;
                    case "medical":
                        adminIdsStore.add(6);
                        break;
                    case "blog":
                        adminIdsStore.add(7);
                        break;
                    case "user":
                        adminIdsStore.add(8);
                        break;
                    case "knowledge":
                        adminIdsStore.add(5);
                        break;
                    default:
                        break;
                }
            }
            POJOs.Administrator selectedAdmin = (POJOs.Administrator) s.createCriteria(POJOs.Administrator.class).add(Restrictions.eq("idadministrator", Integer.parseInt(request.getParameter("id")))).uniqueResult();
            for (Integer i : adminIdsStore){
                POJOs.AdminAccess access = (POJOs.AdminAccess) s.createCriteria(POJOs.AdminAccess.class).add(Restrictions.eq("idadminAccess", i)).uniqueResult();
                POJOs.AdminAccessRight accessRight = new POJOs.AdminAccessRight();
                accessRight.setAdministrator(selectedAdmin);
                accessRight.setAdminAccess(access);
                
                s.save(accessRight);
            }
            
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
