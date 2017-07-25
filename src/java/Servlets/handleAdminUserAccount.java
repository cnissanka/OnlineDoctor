/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chirath Nissanka
 */
@WebServlet(name = "handleAdminUserAccount", urlPatterns = {"/handleAdminUserAccount"})
public class handleAdminUserAccount extends HttpServlet {

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
            System.out.println("servlet content is "+getServletContext());
            
            String path = "uploads/flag_access.com";
            BufferedReader br = new BufferedReader(new FileReader(request.getServletContext().getRealPath("") + "/" + path));
            
            String text = br.readLine();
            
            System.out.println("mello "+br.readLine());
            
            Session s = Connection.Connector.getSessionFactory().openSession();
            
            if (text.equals("false")){
                new Model.AdminHandler().addComponents();
                
                BufferedWriter bw = new BufferedWriter(new FileWriter(request.getServletContext().getRealPath("") + "/" + path));
                bw.write("ok its done don't you worry don't you worry chaaaaaaaaaaaaild");
                bw.flush();
                bw.close();
                br.close();
            }
            
            boolean isUsernameDuplicate = s.createCriteria(POJOs.User.class).add(Restrictions.eq("username", request.getParameter("username"))).list().isEmpty();
            
            
            if (!request.getParameter("password").equals(request.getParameter("confirmpassword"))){
                response.sendRedirect("admin/admin_misc.jsp?res=validation&validation_msg=Passwords don't match!");
            }else if (!(request.getParameter("password").toCharArray().length >= 6)){
                response.sendRedirect("admin/admin_misc.jsp?res=validation&validation_msg=Password too short!");
            }else if (!isUsernameDuplicate){
                response.sendRedirect("admin/admin_misc.jsp?res=validation&validation_msg=Username is already exists!");
            }else{
            
            boolean result = new Model.AdminHandler().addAdmin(request.getParameter("username"), request.getParameter("password"), request.getParameter("firstname"), request.getParameter("middlename"), request.getParameter("lastname"));
            response.sendRedirect("admin/admin_misc.jsp?res=success");
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
