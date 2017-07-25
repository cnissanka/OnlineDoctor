/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "handleLogin", urlPatterns = {"/handleLogin"})
public class handleLogin extends HttpServlet {

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
             Session session = Connection.Connector.getSessionFactory().openSession();
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            
            String username_array [] = username.split("");
            boolean isAdminOrDoctor = false;
            
            for (String current : username_array){
                if (current.equals("@")){
                    isAdminOrDoctor = true;
                }
            }
            
            if (isAdminOrDoctor){
               
                username_array = username.split("@");
                
                if (username_array [1].equals("doctor")){
                    List <POJOs.User> doctorUserList = session.createCriteria(POJOs.User.class).add(Restrictions.eq("usertype", "DOCTOR")).list();
                    for (POJOs.User user : doctorUserList){
                        if (user.getUsername().equals(username.split("@")[0]) && user.getPassword().equals(password)){
                            request.getSession().setAttribute("doctor", user.getDoctor());
                            response.sendRedirect("backend_doctor/backend_index.jsp");
                        }
                    }
                }else if (username_array [1].equals("admin")){
                    
                    List <POJOs.User> adminUserList = session.createCriteria(POJOs.User.class).add(Restrictions.eq("usertype", "ADMINISTRATOR")).list();
                    out.write("size is " + adminUserList.size());
                    for (POJOs.User user : adminUserList){
                        out.write(user.getUsername() + " == " + username_array [0] + username_array[1]);
                        if (user.getUsername().equals(username_array [0] +"@"+ username_array[1]) && user.getPassword().equals(password)){
                            out.write("success");
                            request.getSession().setAttribute("admin", user.getAdministrator());
                            response.sendRedirect("backend_admin/backend_index.jsp");
                        }
                        
                    }
                    out.write("no match");
                    
                }
                
            }else{
                List <POJOs.User> patientUserList = session.createCriteria(POJOs.User.class).add(Restrictions.eq("usertype", "CUSTOMER")).list();
                for (POJOs.User cPatient : patientUserList){
                    if (cPatient.getUsername().equals(username) && cPatient.getPassword().equals(password)){
                        request.getSession().setAttribute("patient", cPatient.getPatient());
                        response.sendRedirect("index.jsp");
                    }
                }
            }
            
        }catch(Exception e){
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
