package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Vector;
import java.util.List;
import java.util.Iterator;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Session;

public final class blogpage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        ");

            int blogid = 0;
            if (request.getParameter("blog") == null) {
                response.sendRedirect("our_blog.jsp");
            } else {
                blogid = Integer.parseInt(request.getParameter("blog"));
            }
            Session s = Connection.Connector.getSessionFactory().openSession();
            POJOs.Blog myBlog = (POJOs.Blog) s.createCriteria(POJOs.Blog.class).add(Restrictions.eq("idblog", blogid)).uniqueResult();

            if (request.getSession().getAttribute("user") != null) {
                if (request.getSession().getAttribute("blogSession") != null) {
                    Vector<Integer> viewedBlogs = (Vector<Integer>) request.getSession().getAttribute("blogSession");
                    boolean isViewed = false;
                    for (Integer b : viewedBlogs) {
                        if (blogid == b) {
                            isViewed = true;
                        }
                    }

                    if (!isViewed) {
                        viewedBlogs.add(blogid);
                        int cBlogviews = Integer.parseInt(myBlog.getBlogViews());
                        cBlogviews += 1;
                        myBlog.setBlogViews(cBlogviews + "");
                        s.update(myBlog);
                        s.beginTransaction().commit();
                    }
                } else {
                    Vector<Integer> viewBlog = new Vector<Integer>();
                    viewBlog.add(blogid);
                    request.getSession().setAttribute("blogSession", viewBlog);
                    int cBlogviews = Integer.parseInt(myBlog.getBlogViews());
                    cBlogviews += 1;
                    myBlog.setBlogViews(cBlogviews + "");
                    s.update(myBlog);
                    s.beginTransaction().commit();
                }
            }
        
      out.write("\n");
      out.write("        <title>");
      out.print(myBlog.getBlogTitle());
      out.write("</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("        <script>\n");
      out.write("            var comment = 0;\n");
      out.write("            function changeSelectedComment(commentid) {\n");
      out.write("                comment = commentid;\n");
      out.write("            }\n");
      out.write("            function getSelectedComment() {\n");
      out.write("                document.getElementById('txtblogcomment').value = comment;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <ul class=\"navbar navbar-default navbar-fixed-top\" role=\"navigation\">\n");
      out.write("                <div class=\"navbar-header\">\n");
      out.write("                    <button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\n");
      out.write("                            data-target=\"#example-navbar-collapse\">\n");
      out.write("                        <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                        <span class=\"icon-bar\"></span>\n");
      out.write("                    </button>\n");
      out.write("                    <a href=\"\" class=\"navbar-brand\">OnlineDoctor!</a>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"example-navbar-collapse\">\n");
      out.write("                    <ul class=\"nav navbar-nav\">\n");
      out.write("                        <li ><a href=\"homepage.jsp\">Home</a></li>\n");
      out.write("                        <li><a href=\"virtual_doc.jsp\">Ask Virtual Doctor</a></li>\n");
      out.write("                        <li><a href=\"pharmacy_service.jsp\">Pharmacy Services</a></li>\n");
      out.write("                        <li><a href=\"medical_services.jsp\">Medical Services</a></li>\n");
      out.write("                        <li class=\"dropdown\"><a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">More Stuff...   <span class=\"glyphicon glyphicon-triangle-bottom\"></span>\n");
      out.write("                            </a>\n");
      out.write("                            <ul class=\"dropdown-menu\">\n");
      out.write("                                <li><a href=\"our_blog.jsp\">Our Blog</a></li>\n");
      out.write("                                <li><a href=\"login_service.jsp\">Your Account!</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("            </ul>\n");
      out.write("\n");
      out.write("            <h1 style=\"margin-top: 60px; margin-bottom: 30px;\">");
      out.print(myBlog.getBlogTitle());
      out.write(" <small>Post created on <strong>");
      out.print(myBlog.getBlogCreation());
      out.write("</strong></small></h1>\n");
      out.write("            ");

                Iterator<POJOs.BlogImages> blogimages = myBlog.getBlogImageses().iterator();
                List<POJOs.BlogImages> imageList = s.createCriteria(POJOs.BlogImages.class).list();

                imageList.clear();
                while (blogimages.hasNext()) {
                    imageList.add(blogimages.next());
                }
            
      out.write("\n");
      out.write("            <div id=\"myCarousel\" class=\"carousel slide\">\n");
      out.write("                <ol class=\"carousel-indicators\">\n");
      out.write("                    ");

                        int counter = 0;
                        for (POJOs.BlogImages bi : imageList) {
      out.write("\n");
      out.write("\n");
      out.write("                    ");

                        if (counter == 0) {
      out.write("\n");
      out.write("                    <li data-target=\"#myCarousel\" data-slide-to=\"");
      out.print(counter);
      out.write("\" class=\"active\"></li>\n");
      out.write("                        ");
} else {
      out.write("\n");
      out.write("                    <li data-target=\"#myCarousel\" data-slide-to=\"");
      out.print(counter);
      out.write("\"></li>                                \n");
      out.write("                        ");
}
                        
      out.write("                                \n");
      out.write("                        ");
counter += 1;
                            }
                        
      out.write("\n");
      out.write("                </ol>\n");
      out.write("                <div class=\"carousel-inner\">\n");
      out.write("                    ");

                        counter = 0;
                        for (POJOs.BlogImages bi : imageList) {
      out.write("\n");
      out.write("                    ");

                        if (counter == 0) {
      out.write("\n");
      out.write("                    <div class=\"item active\">\n");
      out.write("                        <img src=\"");
      out.print(bi.getBlogImage());
      out.write("\" alt=\"Blog Slide\" />\n");
      out.write("                    </div>\n");
      out.write("                    ");
} else {
      out.write("\n");
      out.write("                    <div class=\"item\">\n");
      out.write("                        <img src=\"");
      out.print(bi.getBlogImage());
      out.write("\" alt=\"Blog Slide\" />\n");
      out.write("                    </div>\n");
      out.write("                    ");
}
                    
      out.write("\n");
      out.write("                    ");
counter += 1;
                        }
                    
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <a class=\"carousel-control left\" href=\"#myCarousel\"\n");
      out.write("                   data-slide=\"prev\">&lsaquo;</a>\n");
      out.write("                <a class=\"carousel-control right\" href=\"#myCarousel\"\n");
      out.write("                   data-slide=\"next\">&rsaquo;</a>\n");
      out.write("            </div> <!-- end of carousel -->           \n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"well\" style=\"margin-top: 60px;\">\n");
      out.write("                <h4>Total Views on Blog :  <strong>");
      out.print(myBlog.getBlogViews());
      out.write("</strong> Views</h4>\n");
      out.write("                <p>");
      out.print(myBlog.getBlogContent());
      out.write("</p>\n");
      out.write("            </div> <!-- end of blog description -->\n");
      out.write("\n");
      out.write("            <h3>Comments</h3>\n");
      out.write("            ");

                if (request.getSession().getAttribute("user") != null) {
      out.write("\n");
      out.write("            <form method=\"POST\" action=\"handleBlogCommentAdd\">\n");
      out.write("                <div class=\"input-group\" style=\"margin-bottom: 50px;\">\n");
      out.write("                    <input type=\"text\" class=\"form-control\" placeholder=\"Your Comment Here\" name=\"comment\"/>\n");
      out.write("                    <input type=\"hidden\" value=\"");
      out.print(blogid);
      out.write("\" name=\"blogid\" />\n");
      out.write("                    <span class=\"input-group-btn\">\n");
      out.write("                        <button class=\"btn btn-primary\">\n");
      out.write("                            <span class=\"glyphicon glyphicon-comment\"></span>\n");
      out.write("                        </button>\n");
      out.write("                    </span>\n");
      out.write("                </div><!-- /input-group -->\n");
      out.write("            </form>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("            ");

                Iterator<POJOs.BlogCaptions> blogComments = myBlog.getBlogCaptionses().iterator();
                while (blogComments.hasNext()) {
                    POJOs.BlogCaptions cComment = blogComments.next();
                    POJOs.User commentUser = cComment.getUser();
                    String initals = "";
                    String fname = "", mname = "", lname = "";
                    if (commentUser.getUsertype().equals("ADMINISTRATOR")) {
                        fname = commentUser.getAdministrator().getAdminFname();
                        mname = commentUser.getAdministrator().getAdminMname();
                        lname = commentUser.getAdministrator().getAdminLname();
                        initals += fname.split("")[0];
                        initals += mname.split("")[0];
                        initals += lname.split("")[0];
                    } else if (commentUser.getUsertype().equals("DOCTOR")) {
                        fname = commentUser.getDoctor().getDoctorFname();
                        mname = commentUser.getDoctor().getDoctorMname();
                        lname = commentUser.getDoctor().getDoctorLname();
                        initals += fname.split("")[0];
                        initals += mname.split("")[0];
                        initals += lname.split("")[0];
                    } else if (commentUser.getUsertype().equals("CUSTOMER")) {
                        fname = commentUser.getPatient().getPatientFname();
                        mname = commentUser.getPatient().getPatientMname();
                        lname = commentUser.getPatient().getPatientLname();
                        // initals += fname.split("")[0];
                        initals += mname.split("")[0];
                        initals += lname.split("")[0];

                    }

                    initals = initals.toUpperCase();
            
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"media\">\n");
      out.write("                <a class=\"pull-left\" href=\"#\">\n");
      out.write("                    <h4>");
      out.print(initals);
      out.write("</h4>\n");
      out.write("                </a>\n");
      out.write("                <div class=\"media-body\">\n");
      out.write("                    <h4 class=\"media-heading\">");
      out.print(mname + " " + lname);
      out.write("</h4>\n");
      out.write("                    <p>");
      out.print(cComment.getComment());
      out.write("</p>\n");
      out.write("                    ");

                        Iterator<POJOs.BlogReply> iReplies = cComment.getBlogReplies().iterator();
                        while (iReplies.hasNext()) {
                            POJOs.BlogReply cReply = iReplies.next();
                            POJOs.User replyUser = cReply.getUser();
                            String replyInitals = "";

                            if (replyUser.getUsertype().equals("ADMINISTRATOR")) {
                                fname = replyUser.getAdministrator().getAdminFname();
                                mname = replyUser.getAdministrator().getAdminMname();
                                lname = replyUser.getAdministrator().getAdminLname();
                                replyInitals += fname.split("")[0];
                                replyInitals += mname.split("")[0];
                                replyInitals += lname.split("")[0];
                            } else if (replyUser.getUsertype().equals("DOCTOR")) {
                                fname = replyUser.getDoctor().getDoctorFname();
                                mname = replyUser.getDoctor().getDoctorMname();
                                lname = replyUser.getDoctor().getDoctorLname();
                                replyInitals += fname.split("")[0];
                                replyInitals += mname.split("")[0];
                                replyInitals += lname.split("")[0];
                            } else if (replyUser.getUsertype().equals("CUSTOMER")) {
                                fname = replyUser.getPatient().getPatientFname();
                                mname = replyUser.getPatient().getPatientMname();
                                lname = replyUser.getPatient().getPatientLname();
                                // initals += fname.split("")[0];
                                replyInitals += mname.split("")[0];
                                replyInitals += lname.split("")[0];
                            }
                    
      out.write("\n");
      out.write("                    <div class=\"panel panel-default\">\n");
      out.write("                        <div class=\"media\">\n");
      out.write("                            <a class=\"pull-left\" href=\"#\">\n");
      out.write("                                <h4>");
      out.print(replyInitals);
      out.write("</h4>\n");
      out.write("                            </a>\n");
      out.write("                            <div class=\"media-body\">\n");
      out.write("                                <h6 class=\"media-heading\">");
      out.print(mname + " " + lname);
      out.write("</h6>\n");
      out.write("                                <p>");
      out.print(cReply.getReply());
      out.write("</p>\n");
      out.write("                            </div>\n");
      out.write("                        </div></div>\n");
      out.write("\n");
      out.write("                    ");
}
                    
      out.write("\n");
      out.write("                </div>\n");
      out.write("                ");
if (request.getSession().getAttribute("user") != null) {
      out.write("\n");
      out.write("                <a class=\"pull-right\" data-target=\"#myModal\" data-toggle=\"modal\" onclick=\"changeSelectedComment(");
      out.print(cComment.getIdblogCaptions());
      out.write(")\">\n");
      out.write("                    <span class=\"glyphicon glyphicon-retweet\"></span>\n");
      out.write("                </a>\n");
      out.write("                    <a class=\"btn btn-danger\">\n");
      out.write("                        <span class=\"glyphicon glyphicon-remove\"></span>\n");
      out.write("                    </a>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");

                }
            
      out.write("\n");
      out.write("        </div> <!-- end of container -->\n");
      out.write("\n");
      out.write("        <!-- Modal -->\n");
      out.write("        <div class=\"modal fade\" id=\"myModal\" tabindex=\"-1\" role=\"dialog\"\n");
      out.write("             aria-labelledby=\"myModalLabel\" aria-hidden=\"true\" onshow=\"getSelectedComment()\">\n");
      out.write("            <div class=\"modal-dialog\">\n");
      out.write("                <div class=\"modal-content\">\n");
      out.write("                    <div class=\"modal-header\">\n");
      out.write("                        <button type=\"button\" class=\"close\"\n");
      out.write("                                data-dismiss=\"modal\" aria-hidden=\"true\">\n");
      out.write("                            &times;\n");
      out.write("                        </button>\n");
      out.write("                        <h4 class=\"modal-title\" id=\"myModalLabel\">\n");
      out.write("                            Reply to comment\n");
      out.write("                        </h4>\n");
      out.write("                    </div>\n");
      out.write("                    <form method=\"POST\" action=\"handleCommentRelyAdd\">\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Reply Comment : </label>\n");
      out.write("                                <input type=\"hidden\" name=\"cblog\" value=\"");
      out.print(myBlog.getIdblog());
      out.write("\" />\n");
      out.write("                                <input type=\"hidden\" id=\"txtblogcomment\" name=\"blog\"/>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" name=\"reply\" />\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button type=\"button\" class=\"btn btn-default\"\n");
      out.write("                                    data-dismiss=\"modal\">Close\n");
      out.write("                            </button>\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-primary\">\n");
      out.write("                                Reply\n");
      out.write("                            </button>\n");
      out.write("                        </div>\n");
      out.write("                    </form>\n");
      out.write("                </div><!-- /.modal-content -->\n");
      out.write("            </div><!-- /.modal -->\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
