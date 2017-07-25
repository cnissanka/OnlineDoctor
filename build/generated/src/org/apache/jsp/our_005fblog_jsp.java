package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.criterion.Restrictions;
import java.util.Iterator;
import org.hibernate.Session;
import java.util.List;

public final class our_005fblog_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("        <!-- bootstrap css-->\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
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
      out.write("            <h2 style=\"padding-top: 50px;\">Our Blog <small>Read what is happening in our Medical Care center!</small></h2>\n");
      out.write("\n");
      out.write("            <form for=\"form\">\n");
      out.write("                <div class=\"input-group\" style=\"margin-top:  50px;\">\n");
      out.write("                    <input type=\"text\" placeholder=\"Search Blog - Enter Blog Topic\" class=\"form-control\"/>\n");
      out.write("                    <span class=\"input-group-btn\">\n");
      out.write("                        <button class=\"btn btn-default\" type=\"button\">\n");
      out.write("                            Go!\n");
      out.write("                        </button>\n");
      out.write("                    </span>\n");
      out.write("                </div>\n");
      out.write("            </form>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                ");
  Session s = Connection.Connector.getSessionFactory().openSession();
                    List<POJOs.Blog> blogList = s.createCriteria(POJOs.Blog.class).list();
                    if (request.getParameter("search") != null) {
                        System.out.println(request.getParameter("search"));
                        blogList = s.createCriteria(POJOs.Blog.class).add(Restrictions.like("blogTitle", request.getParameter("search") + "%")).list();
                    }
                    int elementSize = blogList.size();
                    System.out.print(elementSize);
                      int counter = 0;
                      for (POJOs.Blog b : blogList) {
      out.write("\n");
      out.write("                <div class=\"col-lg-4\">\n");
      out.write("                    <div class=\"thumbnail\">\n");
      out.write("                        ");

                            Iterator<POJOs.BlogImages> blogImages = b.getBlogImageses().iterator();
                            POJOs.BlogImages blogimage = blogImages.next();
                            counter += 1;
                        
      out.write("\n");
      out.write("                        <img src=\"");
      out.print(blogimage.getBlogImage());
      out.write("\" alt=\"Blog image\">\n");
      out.write("                        <div class=\"caption\">\n");
      out.write("                            <h3>");
      out.print(b.getBlogTitle());
      out.write("</h3>\n");
      out.write("                            <p>");
      out.print(b.getBlogSummary());
      out.write("</p>\n");
      out.write("                            <p><a href=\"#\" class=\"btn btn-primary\" role=\"button\">More Details</a> \n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                ");
  if (counter == 3) {
                            counter = 0;
      out.write("\n");
      out.write("            </div><div class=\"row\">\n");
      out.write("                ");
}
                    }
                
      out.write("\n");
      out.write("            </div>\n");
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
