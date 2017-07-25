/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Chirath Nissanka
 */
@WebServlet(name = "handleMedicineAdd", urlPatterns = {"/handleMedicineAdd"})
public class handleMedicineAdd extends HttpServlet {

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
            Transaction tf = session.beginTransaction();
            //Declare variables
            String path = "uploads/medicine/";

            System.out.println(request.getParameter("jj"));

            FileItemFactory fip = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fip);
            List<FileItem> items = upload.parseRequest(request);

            String medicineName,
            medicineDescription,
            medicinePrice,
            medicineQty = "";
            medicinePrice = "";
            medicineName = "";
            medicineDescription = "";
            String supplierID = "";
            for (FileItem item : items) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("medicineName")) {
                        medicineName = item.getString();
                    } else if (item.getFieldName().equals("medicineDesc")) {
                        medicineDescription = item.getString();
                    } else if (item.getFieldName().equals("medicinePrice")) {
                        medicinePrice = item.getString();
                    } else if (item.getFieldName().equals("medicineQty")) {
                        medicineQty = item.getString();
                    } else if (item.getFieldName().equals("supplier")) {
                        supplierID = item.getString();
                    }
                } else {
                    System.out.println("Content Type : " + item.getContentType());
                    System.out.println("Real Path : " + request.getServletContext().getRealPath("/uploads/medicine/"));

                    path += System.currentTimeMillis() + item.getName();

                    File saved_file = new File(request.getServletContext().getRealPath("") + "/" + path);
                    item.write(saved_file);

                }
            }
            POJOs.Medicine med = new POJOs.Medicine();
            med.setMedicineName(medicineName);
            med.setMedicineDescription(medicineDescription);
            med.setMedicinePrice(Double.parseDouble(medicinePrice));
            med.setMedicineImage(path);
            med.setMedicineQty(medicineQty);
            POJOs.Supplier sSupplier = (POJOs.Supplier) session.createCriteria(POJOs.Supplier.class).add(Restrictions.eq("idsupplier", Integer.parseInt(supplierID))).uniqueResult();
            med.setSupplier(sSupplier);
            session.save(med);
            tf.commit();
            session.close();
            tf = null;

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
