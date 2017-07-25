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
@WebServlet(name = "handleBlogAdd", urlPatterns = {"/handleBlogAdd"})
public class handleBlogAdd extends HttpServlet {

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

            String path = "uploads/blog/";    
            String filePaths [] = new String [4];
            int counter = 0; 
            FileItemFactory fip = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fip);
            List<FileItem> items = upload.parseRequest(request);
            
            String txtTitle = ""; String txtContent = ""; String txtSummary = "";
            
            for (FileItem item : items){
                if (item.isFormField()){
                    if (item.getFieldName().equals("txtHeading")){
                        txtTitle = item.getString();
                    }else if (item.getFieldName().equals("txtContent")){
                        txtContent = item.getString();
                    }else if (item.getFieldName().equals("txtSummary")){
                        txtSummary = item.getString();
                    }
                }else{
                    path += System.currentTimeMillis() + item.getName();
                    File saved_file = new File(request.getServletContext().getRealPath("") + "/" + path);
                    item.write(saved_file);
                    filePaths [counter] = path;
                    
                    path = "uploads/blog/"; 
                    counter += 1;
                }
            }
            
            POJOs.Blog blog = new POJOs.Blog();
            int id1 = session.createCriteria(POJOs.Blog.class).list().size();
            blog.setIdblog(id1+1);
            blog.setBlogTitle(txtTitle);
            blog.setBlogContent(txtContent);
            blog.setBlogSummary(txtSummary);
            
            session.save(blog);
            counter = 0;
            int id = session.createCriteria(POJOs.BlogImages.class).list().size();
            for (String p : filePaths){
               switch(counter){
                   case 0:  System.out.println("as1");POJOs.BlogImages blogImage = new POJOs.BlogImages();
                blogImage.setIdblogImages(id);
                blogImage.setBlog(blog);
                blogImage.setBlogImage(p);
                session.save(blogImage);counter+=1; break;
                       
                   case 1:  System.out.println("as2");POJOs.BlogImages blogImage1 = new POJOs.BlogImages();
                blogImage1.setIdblogImages(id+1);
                   blogImage1.setBlog(blog);
                blogImage1.setBlogImage(p);
                session.save(blogImage1);counter+=1; break;
                       
                   case 2:  System.out.println("as3");POJOs.BlogImages blogImage2 = new POJOs.BlogImages();
                 blogImage2.setIdblogImages(id+2);
                   blogImage2.setBlog(blog);
                blogImage2.setBlogImage(p);
                session.save(blogImage2); counter+=1;break;
                       
                   case 3:  System.out.println("as4");POJOs.BlogImages blogImage3 = new POJOs.BlogImages();
                 blogImage3.setIdblogImages(id+3);
                   blogImage3.setBlog(blog);
                blogImage3.setBlogImage(p);
                session.save(blogImage3);counter+=1; break; 
                   
               }
            }
            tf.commit();
            System.out.println("Success");
            out.write("Success!");
            session.close();
            tf = null;
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
