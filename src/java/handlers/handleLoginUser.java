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
@WebServlet(name = "handleLoginUser", urlPatterns = {"/handleLoginUser"})
public class handleLoginUser extends HttpServlet {

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
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Session s = Connection.Connector.getSessionFactory().openSession();

            POJOs.User user = (POJOs.User) s.createCriteria(POJOs.User.class).add(Restrictions.eq("username", username)).uniqueResult();

            if (user != null) {
                String usertype = user.getUsertype();

                if (user.getPassword().equals(request.getParameter("password"))) {
                    
                    request.getSession().setAttribute("user", user);
                    switch (usertype) {
                        case "CUSTOMER":
                            response.sendRedirect("homepage.jsp");
                            break;
                        case "DOCTOR":
                            response.sendRedirect("doctor/doctor_home.jsp");
                            break;
                        case "ADMINISTRATOR":
                            response.sendRedirect("admin/admin_home.jsp");
                            break;
                        default:
                            break;
                    }
                } else {
                    response.sendRedirect("login_service.jsp?res=validation&validation_msg=Login Details are invalid, please try again.");
                }
            } else {
                response.sendRedirect("login_service.jsp?res=validation&validation_msg=Login Details are invalid, please try again.");
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
