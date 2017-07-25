package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_005fservice_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Login to your account</title>\n");
      out.write("        <!-- bootstrap css-->\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap-datepicker.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"js/bootstrap-datepicker.min.css\"/>\n");
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
      out.write("            <h2 style=\"padding-top: 50px;\">Login to your account</h2>\n");
      out.write("            <div class=\"row\">\n");
      out.write("                ");
  if (request.getParameter("res") != null) {
                        if (request.getParameter("res").equals("success")) {
      out.write("\n");
      out.write("                <div id=\"doctor_success\" class=\"alert alert-success\" style=\"visibility: visible\">\n");
      out.write("                    <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                    <strong>Success!</strong> Data was updated successfully!\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                ");
} else if (request.getParameter("res").equals("fail")) {
      out.write("\n");
      out.write("\n");
      out.write("                <div id=\"doctor_fail\" class=\"alert alert-danger\" style=\"visibility: visible\">\n");
      out.write("                    <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                    <strong>Oops!</strong> Something went wrong while saving data!\n");
      out.write("                </div>\n");
      out.write("                ");
} else if (request.getParameter("res").equals("validation")) {
      out.write("\n");
      out.write("                <div id=\"doctor_valid\" class=\"alert alert-warning\">\n");
      out.write("                    <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                    <strong>Validation Error Occurred</strong> ");
      out.print(request.getParameter("validation_msg"));
      out.write("\n");
      out.write("                </div>\n");
      out.write("                ");
}
                    }
                
      out.write("\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            ");

                if (request.getSession().getAttribute("user") != null){
                    POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
                    if (user.getUsertype().equals("DOCTOR")){
                        response.sendRedirect("doctor/doctor_home.jsp");
                    }else if (user.getUsertype().equals("ADMINISTRATOR")){
                        response.sendRedirect("admin/admin_home.jsp");
                    }
                }
            
      out.write("\n");
      out.write("            <div class=\"row\" style=\"margin-top: 70px;\">\n");
      out.write("                <div class=\"col-md-5\">\n");
      out.write("                    <div class=\"well\">\n");
      out.write("                        <h3>Sign Up <small> to unlock personal services!</small></h3>\n");
      out.write("                        <form action=\"handleLoginUser\" method=\"POST\">\n");
      out.write("                            <div class=\"form-group\" style=\"margin-top: 50px;\">\n");
      out.write("                                <label for=\"username\">Username :- </label>\n");
      out.write("                                <input type=\"text\" id=\"username\" class=\"form-control\" placeholder=\"Username here\" name=\"username\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"password\">Password :- </label>\n");
      out.write("                                <input type=\"password\" id=\"password\" class=\"form-control\"  placeholder=\"Password here\" name=\"password\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <button class=\"btn btn-primary\" type=\"sumbit\"><span class=\"glyphicon glyphicon-log-in\"></span>  Login!</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-lg-6\">\n");
      out.write("                    <div class=\"well\">\n");
      out.write("                        <h3>New Here? <small>Just create a account!</small></h3>\n");
      out.write("                        <form method=\"POST\" action=\"handlePatientUserCreate\">\n");
      out.write("                            <div class=\"form-group\" style=\"margin-top: 50px;\">\n");
      out.write("                                <label for=\"pusername\">Username :- </label>\n");
      out.write("                                <input type=\"text\" value=\"\" id=\"pusername\" name=\"username\" placeholder=\"Enter username\" class=\"form-control\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"ppassword\">Password :- </label>\n");
      out.write("                                <input type=\"password\" value=\"\" id=\"ppassword\" placeholder=\"Enter Password\" class=\"form-control\" name=\"password\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"ppassword_confirm\">Confirm Password:- </label>\n");
      out.write("                                <input type=\"password\" value=\"\" id=\"ppassword_confirm\" placeholder=\"Enter Password Again\" class=\"form-control\" name=\"confirmpassword\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"psalutate\" name=\"salutation\">Select Salutation :- </label>\n");
      out.write("                                <select class=\"form-control\" name=\"saluation\">\n");
      out.write("                                    <option>Mr.</option>\n");
      out.write("                                    <option>Master.</option>\n");
      out.write("                                    <option>Mrs.</option>\n");
      out.write("                                    <option>Miss.</option>\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"pfname\">Enter First Name :- </label>\n");
      out.write("                                <input type=\"text\" value=\"\" id=\"pfname\" placeholder=\"Enter First Name\" class=\"form-control\" name=\"firstname\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"pmname\">Enter Middle Name :- </label>\n");
      out.write("                                <input type=\"text\" value=\"\" id=\"pmname\" placeholder=\"Enter Middle Name\" class=\"form-control\" name=\"middlename\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"plname\">Enter Last Name :- </label>\n");
      out.write("                                <input type=\"text\" value=\"\" id=\"plname\" placeholder=\"Enter Last Name\" class=\"form-control\" name=\"lastname\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Select Gender :- </label>\n");
      out.write("                                <select class=\"form-control\" name=\"gender\">\n");
      out.write("                                    <option>Male</option>\n");
      out.write("                                    <option>Female</option>\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Select Date of Birth :- </label>\n");
      out.write("                                <div class=\"input-group bootstrap-datepicker datepicker\">\n");
      out.write("                                    <input id=\"timepicker2\" type=\"text\" class=\"form-control input-small\" name=\"dob\">                    \n");
      out.write("                                    <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-time\"></i></span>\n");
      out.write("                                </div>\n");
      out.write("                                <script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("                                    $('#timepicker2').datepicker();\n");
      out.write("                                </script>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Enter your NIC :- </label>\n");
      out.write("                                <input type=\"text\" value=\"\" class=\"form-control\" placeholder=\"Enter National Identity No, If any\" name=\"nic\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Enter your Phone # :- </label>\n");
      out.write("                                <div class=\"input-group\">\n");
      out.write("                                    <span class=\"input-group-addon\">+94</span>\n");
      out.write("                                    <input type=\"text\" value=\"\" class=\"form-control input-small\" placeholder=\"Enter Phone Number\" name=\"phone\"/>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"paddress\">Enter your Address :- </label>\n");
      out.write("                                <textarea id=\"paddress\" class=\"form-control\" name=\"address\"></textarea>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"pemail\">Enter your email address</label>\n");
      out.write("                                <input type=\"email\" class=\"form-control\" id=\"pemail\" name=\"email\"/>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <button class=\"btn btn-primary\" type=\"submit\" name=\"btnsub\"><span class=\"glyphicon glyphicon-user\"></span>  Create My Account!</button>\n");
      out.write("                        </form>\n");
      out.write("                    </div> <!-- end of well -->\n");
      out.write("                </div> <!--end of patient data end of col -->\n");
      out.write("            </div> <!-- end of row -->\n");
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
