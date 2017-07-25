package Servlets;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author codeguy
 */
@WebServlet(urlPatterns = {"/handleblogimages"})
public class handleblogimages extends HttpServlet {

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
            String id = request.getParameter("blogid");
            
            System.out.println(id);
            FileItemFactory fip = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fip);
            List<FileItem> items = upload.parseRequest(request);
            String path = "uploads/blog/";
            for (FileItem item : items) {
                if (item.getFieldName().equals("blogid")){
                    id = item.getString();
                }
                
                if (!item.isFormField()) {
                    System.out.println("here");
                    path += System.currentTimeMillis() + item.getName();
                    File saved_file = new File(request.getServletContext().getRealPath("") + "/" + path);
                    item.write(saved_file);
                    
                    Session s = Connection.Connector.getSessionFactory().openSession();
                    POJOs.Blog selectedBlog = (POJOs.Blog) s.createCriteria(POJOs.Blog.class).add(Restrictions.eq("idblog", Integer.parseInt(id))).uniqueResult();
                    
                    POJOs.BlogImages blogImage = new POJOs.BlogImages();
                    int id_bi = s.createCriteria(POJOs.BlogImages.class).list().size();
                    blogImage.setIdblogImages(id_bi);
                    blogImage.setBlog(selectedBlog);
                    blogImage.setBlogImage(path);
                    
                    s.save(blogImage);
                    s.beginTransaction().commit();
                    s.close();
                    
                }
            }
            
            response.sendRedirect("admin/admin_blog.jsp");
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
