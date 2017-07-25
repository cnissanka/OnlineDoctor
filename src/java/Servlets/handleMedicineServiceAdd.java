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

/**
 *
 * @author Chirath Nissanka
 */
@WebServlet(name = "handleMedicineServiceAdd", urlPatterns = {"/handleMedicineServiceAdd"})
public class handleMedicineServiceAdd extends HttpServlet {

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

            String path = "uploads/services/";

            FileItemFactory fip = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fip);
            String serviceName = "";String serviceDescription = "";String servicePrice = ""; 
            List<FileItem> items = upload.parseRequest(request);

            for (FileItem item : items) {
                if (item.isFormField()) {
                    if (item.getFieldName().equals("txtServiceName")){
                        serviceName = item.getString();
                    }
                    
                    if (item.getFieldName().equals("txtServiceDescription")){
                        serviceDescription = item.getString();
                    }
                    
                    if (item.getFieldName().equals("txtServicePrice")){
                        servicePrice = item.getString();
                    }
                } else {
                    System.out.println("Content Type : " + item.getContentType());
                    System.out.println("Real Path : " + request.getServletContext().getRealPath("/uploads/medicine/"));

                    path += System.currentTimeMillis() + item.getName();
                    File saved_file = new File(request.getServletContext().getRealPath("") + "/" + path);
                    item.write(saved_file);
                }
            }
          
            POJOs.MedicalServices medServices = new POJOs.MedicalServices();
            medServices.setServiceName(serviceName);
            medServices.setServiceDescription(serviceDescription);
            medServices.setServicePrice(Double.parseDouble(servicePrice));
            medServices.setServiceImage(path);
            session.save(medServices);
            tf.commit();;
            session.close();
            tf = null;
            response.sendRedirect("backend_admin/backend_index.jsp?page=service&res=success");
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
