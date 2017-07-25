/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import POJOs.Medicine;
import POJOs.Prescription;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;
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
@WebServlet(name = "handleAddtoCart", urlPatterns = {"/handleAddtoCart"})
public class handleAddtoCart extends HttpServlet {

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
            HashMap<Integer, Integer> cart = null;
         //  Integer prescriptions = null;
            System.out.println(request.getParameter("id"));
            if (request.getSession().getAttribute("cart") != null && request.getSession().getAttribute("pres") != null){
                cart = (HashMap<Integer, Integer>) request.getSession().getAttribute("cart");
                System.out.println(cart);
                boolean isDuplicate = false;
              //  POJOs.Medicine medicine = (POJOs.Medicine) s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("idmedicine", Integer.parseInt(request.getParameter("id")))).uniqueResult();
                Integer qty = Integer.parseInt(request.getParameter("qty"));
                System.out.println(qty + " is qty");
                if (cart.get(Integer.parseInt(request.getParameter("id"))) == null){ //if medicine is already there
//                    int qty_previous = cart.get(Integer.parseInt(request.getParameter("id")));
                    cart.put(Integer.parseInt(request.getParameter("id")), qty);
                }
             
            //    prescriptions = (Integer) request.getSession().getAttribute("pres");
                
              /*  POJOs.Prescription pr = (POJOs.Prescription) s.createCriteria(POJOs.Prescription.class).add(Restrictions.eq("idprescription", Integer.parseInt(request.getParameter("pres")))).uniqueResult();
               
                if (!prescriptions.contains(pr)){
                  prescriptions.add(pr);
                }*/
           //  request.getSession().setAttribute("pres", prescriptions);
             request.getSession().setAttribute("cart", cart);
            }else{
                cart = new HashMap<>();
             //   prescriptions = new Vector<>();
               // POJOs.Medicine medicine = (POJOs.Medicine) s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("idmedicine", Integer.parseInt(request.getParameter("id")))).uniqueResult();
                 Integer qty = Integer.parseInt(request.getParameter("qty"));
                cart.put(Integer.parseInt(request.getParameter("id")),qty);
                 POJOs.Prescription pr = (POJOs.Prescription) s.createCriteria(POJOs.Prescription.class).add(Restrictions.eq("idprescription", Integer.parseInt(request.getParameter("pres")))).uniqueResult();
               
            //     prescriptions.add(pr);
                
                 request.getSession().setAttribute("pres", Integer.parseInt(request.getParameter("pres")));
                request.getSession().setAttribute("cart", cart);
            }
            System.out.println("Cart objs : "+ cart.entrySet().iterator().hasNext());
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
