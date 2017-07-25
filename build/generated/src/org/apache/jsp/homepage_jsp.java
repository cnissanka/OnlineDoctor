package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.FileReader;
import java.io.BufferedReader;
import org.hibernate.Session;
import java.util.List;

public final class homepage_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Homepage - OnlineDoctor</title>\n");
      out.write("        <!-- bootstrap css-->\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <!-- main container-->\n");
      out.write("        <div class=\"container\">\n");
      out.write("\n");
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
      out.write("                        <li ><a href=\"\">Home</a></li>\n");
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
      out.write("            <!--end of navbar code-->\n");
      out.write("\n");
      out.write("            <div id='myCarousel' class='carousel slide'>\n");
      out.write("                <ol class='carousel-indicators'>\n");
      out.write("                    ");

                        Session s = Connection.Connector.getSessionFactory().openSession();
                        List<POJOs.Misc> misc = s.createCriteria(POJOs.Misc.class).list();

                        int counter = 0;
                        for (POJOs.Misc m1 : misc) {
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
      out.write("\"></li>\n");
      out.write("                        ");
}
                        
      out.write("\n");
      out.write("                        ");
counter += 1;
                            }
      out.write("  \n");
      out.write("                </ol>\n");
      out.write("                <div class=\"carousel-inner\">\n");
      out.write("                    ");

                        counter = 0;
                        for (POJOs.Misc m : misc) {
      out.write("\n");
      out.write("                    ");

                        if (counter == 0) {
      out.write("\n");
      out.write("                    <div class=\"item active\">\n");
      out.write("                        <img src=\"");
      out.print(m.getImagePath());
      out.write("\" alt=\"Slide of Carousel\"/>\n");
      out.write("                        <div class=\"carousel-caption\">Online Doctor Features</div>\n");
      out.write("                    </div>   \n");
      out.write("                    ");
} else {
      out.write("\n");
      out.write("                    <div class=\"item\">\n");
      out.write("                        <img src=\"");
      out.print(m.getImagePath());
      out.write("\" alt=\"Slide of Carousel\"/>\n");
      out.write("                        <div class=\"carousel-caption\">Online Doctor Features</div>\n");
      out.write("                    </div>\n");
      out.write("                    ");
}
                    
      out.write("\n");
      out.write("                    ");
counter += 1;}
      out.write("  \n");
      out.write("                </div>\n");
      out.write("                <!-- carousel controls -->\n");
      out.write("                <a class=\"carousel-control left\" href=\"#myCarousel\" data-slide=\"prev\">&lsaquo;</a>\n");
      out.write("                <a class=\"carousel-control right\" href=\"#myCarousel\" data-slide=\"next\">&lsaquo;</a>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <div class=\"well well-lg\" style=\"margin-top: 100px;\">\n");
      out.write("                <h2>About Us</h2>\n");
      out.write("                <p>\n");
      out.write("                    ");

                        String jsp_path = this.getServletConfig().getServletContext().getRealPath("/js");
                        String txtPath = jsp_path + "/aboutus.txt";
                        BufferedReader br = new BufferedReader(new FileReader(txtPath));

                        String s1 = br.readLine();
                        while (s1 != null) {
                    
      out.write("\n");
      out.write("\n");
      out.write("                    ");
out.println(s1);
      out.write("\n");
      out.write("                    ");
s1 = br.readLine();
                        }
                    
      out.write("\n");
      out.write("                </p>\n");
      out.write("            </div>\n");
      out.write("                <div class=\"panel panel-info\">\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <iframe src=\"https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3957.599567219951!2d80.62946231477503!3d7.286317994740951!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae36882b22910bf%3A0x82b71793a1e570c5!2sKandy+General+Hospital!5e0!3m2!1sen!2slk!4v1451627532959\" width=\"400\" height=\"300\" frameborder=\"0\" style=\"border:0\" allowfullscreen></iframe>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("        </div>\n");
      out.write("                <div class=\"navbar navbar-inverse navbar-fixed-bottom\" role=\"navigation\">\n");
      out.write("                    <div class=\"container\">\n");
      out.write("                        <div class=\"navbar-text pull-left\">\n");
      out.write("                            Copyright &copy; Chirath Nissanka | Online Doctor Services\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"navbar-text pull-right\">\n");
      out.write("                            Follow us on <a href=\"www.twitter.com\">Twitter</a> , <a href=\"facebook.com\">Facebook</a>, <a href=\"www.instagram.com\">Instagram</a>, <a href=\"www.googleplus.com\">Google+</a>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
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
