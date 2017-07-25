package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class admin_005fhome_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Admin Panel</title>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width,\n");
      out.write("              initial-scale=1.0,\n");
      out.write("              maximum-scale=1.0,\n");
      out.write("              user-scalable=no\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"../bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("        <script src=\"../js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"../bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container-fluid\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"jumbotron\">\n");
      out.write("                    <div class=\"center-block\">\n");
      out.write("                        <center>\n");
      out.write("                            <h2>Welcome Admin! <small>to the Control Panel of OnlineDoctor!</small></h2> \n");
      out.write("                        </center>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"pull-right\">\n");
      out.write("                        <form>\n");
      out.write("                            <button class=\"btn btn-danger\">Sign out <span class=\"glyphicon glyphicon-user\"></span></button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"row\" style=\"margin-top: 40px;\">\n");
      out.write("                <div class=\"col-lg-2 \" >\n");
      out.write("                    <ul class=\"nav nav-pills nav-stacked\">\n");
      out.write("                        <li class='active'><a href=\"admin_home.jsp\">Home and Notifications</a></li>\n");
      out.write("                        <li><a href=\"admin_patient.jsp\">Patient Details</a></li>\n");
      out.write("                        <li><a href=\"admin_doctor.jsp\">Doctor Details</a></li>\n");
      out.write("                        <li><a href=\"admin_pharamacy.jsp\">Pharmacy Details</a></li>\n");
      out.write("                        <li><a href=\"admin_medicalservice.jsp\">Medical Services</a></li>\n");
      out.write("                        <li><a href='admin_blog.jsp'>Blog Details</a></li>\n");
      out.write("                        <li><a href=\"admin_misc.jsp\">Misc and User Account Mgt</a></li>                       \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-lg-10\">\n");
      out.write("                    <div class=\"center-block\">\n");
      out.write("                        <center>\n");
      out.write("                            <h4>Notification Panel <small>Gives overall statistics</small></h4>\n");
      out.write("                        </center>\n");
      out.write("                    </div>\n");
      out.write("                    <noscript>\n");
      out.write("                    <div class=\"alert alert-warning alert-dismissable\">\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"alert\"\n");
      out.write("                                aria-hidden=\"true\">\n");
      out.write("                            &times;\n");
      out.write("                        </button>\n");
      out.write("                        Warning! Javascript is not enabled, for maximum site preformance enable javascript.\n");
      out.write("                    </div>\n");
      out.write("                    </noscript>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
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
