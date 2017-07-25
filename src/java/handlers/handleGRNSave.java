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
@WebServlet(name = "handleGRNSave", urlPatterns = {"/handleGRNSave"})
public class handleGRNSave extends HttpServlet {

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
            
            POJOs.Grn grn = new POJOs.Grn();
            grn.setDate(request.getParameter("date"));
            
            
            POJOs.Medicine med = (POJOs.Medicine) s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("idmedicine", Integer.parseInt(request.getParameter("medid")))).uniqueResult();
            POJOs.Supplier sup = (POJOs.Supplier) s.createCriteria(POJOs.Supplier.class).add(Restrictions.eq("idsupplier", Integer.parseInt(request.getParameter("supid")))).uniqueResult();
            
            int id = s.createCriteria(POJOs.Grn.class).list().size();
            
            grn.setIdGrn(id);
            
            grn.setSupplier(sup);
            grn.setMedicine(med);
            grn.setQtySupplied(request.getParameter("qty"));
            grn.setNotes(request.getParameter("comments"));
            
            s.save(grn);
            
            
            int cQty = Integer.parseInt(med.getMedicineQty());
            int nQty = Integer.parseInt(request.getParameter("qty")) + cQty;
            
            med.setMedicineQty(nQty+"");
            s.update(med);
            
            
            tf.commit();
            s.close();
            tf =null;
            response.sendRedirect("admin/admin_pharamacy.jsp?res=success");
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
