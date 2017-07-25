/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handlers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
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
@WebServlet(name = "handleblogadd", urlPatterns = {"/handleblogadd"})
public class handleblogadd extends HttpServlet {

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
            System.out.println("here!");
            System.out.println(request.getParameter("btn") + " is awesome");
            switch (request.getParameter("btn")) {
                case "Save Blog":
                    
                    Session s = Connection.Connector.getSessionFactory().openSession();
                    Transaction tf = s.beginTransaction();
                    POJOs.Blog blog = new POJOs.Blog();
                    int idd = s.createCriteria(POJOs.Blog.class).list().size();
                    blog.setBlogTitle(request.getParameter("title"));
                    blog.setIdblog(idd);
                    blog.setBlogContent(request.getParameter("content"));
                    blog.setBlogSummary(request.getParameter("summary"));
                    String currentDate = new Date().toString();
                    blog.setBlogCreation(currentDate.split(" ")[2] + " " + currentDate.split(" ")[0] + " " + currentDate.split(" ")[1]);
                    blog.setBlogViews("0");
                    s.save(blog);
                    tf.commit();
                    s.close();
                    tf = null;
                    break;
                case "Update Blog":
                    Session s1 = Connection.Connector.getSessionFactory().openSession();
                    Transaction tf1 = s1.beginTransaction();
                    POJOs.Blog blog1 = (POJOs.Blog) s1.createCriteria(POJOs.Blog.class).add(Restrictions.eq("idblog", Integer.parseInt(request.getParameter("idblog")))).uniqueResult();
                    
                    blog1.setBlogTitle(request.getParameter("title"));
                    blog1.setBlogContent(request.getParameter("content"));
                    blog1.setBlogSummary(request.getParameter("summary"));
                    
                    s1.update(blog1);
                    tf1.commit();;
                    
                    s1.close();
                    tf1 = null;
                    break;
                case "Delete Blog":
                    Session s2 = Connection.Connector.getSessionFactory().openSession();
                    Transaction tf2 = s2.beginTransaction();
                    POJOs.Blog blog2 = (POJOs.Blog) s2.createCriteria(POJOs.Blog.class).add(Restrictions.eq("idblog", Integer.parseInt(request.getParameter("idblog")))).uniqueResult();
                    
                    Iterator <POJOs.BlogImages> blogimages = blog2.getBlogImageses().iterator();
                    while (blogimages.hasNext()){
                        s2.delete(blogimages.next());
                    }
                    
                    Iterator <POJOs.BlogCaptions> blogCommments = blog2.getBlogCaptionses().iterator();
                    while (blogCommments.hasNext()){
                        POJOs.BlogCaptions caption = blogCommments.next();
                        Iterator <POJOs.BlogReply> replies = caption.getBlogReplies().iterator();
                        while (replies.hasNext()){
                            s2.delete(replies.next());
                        }
                        s2.delete(caption);
                    }
                    s2.delete(blog2);
                    tf2.commit();
                    s2.clear();s2.close();
                    break;
                default:
                    break;
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
