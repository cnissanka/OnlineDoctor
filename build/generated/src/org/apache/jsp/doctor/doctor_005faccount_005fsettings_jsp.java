package org.apache.jsp.doctor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.Session;
import java.util.List;
import java.util.Iterator;

public final class doctor_005faccount_005fsettings_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Doctor Panel</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("        <script src=\"../js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"../bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"../js/bootstrap-datepicker.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../js/bootstrap-datepicker.min.css\"/>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            function removeQualifications(id,id2) {\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"../removeDoctorQualification\",\n");
      out.write("                    type: 'POST',\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": id,\n");
      out.write("                        \"id2\" : id2\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        alert(\"failed\");\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");


            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            POJOs.Doctor doctor = null;
            System.out.println((user == null) + " then user is null");
            if (user != null) {
                if (user.getUsertype().equals("DOCTOR")) {
                    doctor = user.getDoctor();
                } else {
                    response.sendRedirect("../homepage.jsp");
                }
            } else {
                System.out.print("here!!!");
                response.sendRedirect("../homepage.jsp");
            }
            Session s = Connection.Connector.getSessionFactory().openSession();
        
      out.write("\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <div class=\"center-block\">\n");
      out.write("                <center>\n");
      out.write("                    <h2>Welcome Dr. ");
      out.print(user.getDoctor().getDoctorFname() + " " + user.getDoctor().getDoctorLname());
      out.write(" <small>to your own workspace!</small></h2> \n");
      out.write("                </center>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"pull-right\">\n");
      out.write("                <form action=\"../handleDoctorSignOut\" method=\"POST\">                    \n");
      out.write("                    <button class=\"btn btn-danger\" style=\"margin-right: 15px;\" type=\"submit\">Sign out <span class=\"glyphicon glyphicon-user\"></span></button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-lg-2\" style=\"margin-top: 40px;\">\n");
      out.write("                <ul class=\"nav nav-pills nav-stacked\">\n");
      out.write("                    <li><a href=\"doctor_home.jsp\">Home and Notifications</a></li>\n");
      out.write("                    <li><a href=\"doctor_prescriptions.jsp\">Prescription Editor</a></li>\n");
      out.write("                    <li><a href=\"doctor_medical_appointments.jsp\">Your Medical Appointments</a></li>\n");
      out.write("                    <li><a href=\"doctor_patient_appointments.jsp\">Your Patient Appointments</a></li>\n");
      out.write("                    <li class=\"active\"><a href=\"doctor_account_settings.jsp\">Account Settings and Preferences</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-lg-10\">\n");
      out.write("                ");
  if (request.getParameter("res") != null) {
                         if (request.getParameter("res").equals("success")) {
      out.write("\n");
      out.write("                <div id=\"doctor_success\" class=\"alert alert-success\" style=\"visibility: visible\">\n");
      out.write("                    <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                    <strong>Success!</strong> Your data was updated successfully!\n");
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
      out.write("                <div>\n");
      out.write("                    <form method=\"POST\" action=\"../handleDoctorDoctorUpdate\">\n");
      out.write("                        <input type=\"hidden\" value=\"");
      out.print(doctor.getIddoctor());
      out.write("\" name=\"doctor\" />\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Password :- </label>\n");
      out.write("                            <input type=\"password\" placeholder=\"Enter Password Here\" required class=\"form-control\" value=\"");
      out.print(user.getPassword());
      out.write("\" name=\"password\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Confirm Password :- </label>\n");
      out.write("                            <input type=\"password\" placeholder=\"Enter Password Here\" required class=\"form-control\" name=\"confirmapssword\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Doctor First Name :- </label>\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Enter Doctor First Name Here\" value=\"");
      out.print(doctor.getDoctorFname());
      out.write("\" name=\"firstname\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Doctor Middle Name :- </label>\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Enter Doctor Middle Name Here\" value=\"");
      out.print(doctor.getDoctorMname());
      out.write("\" name=\"middlename\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Doctor Last Name :- </label>\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Enter Doctor Last Name Here\" value=\"");
      out.print(doctor.getDoctorLname());
      out.write("\" name=\"lastname\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select Date of Birth :- </label>\n");
      out.write("                            <div class=\"input-group bootstrap-datepicker datepicker\">\n");
      out.write("                                <input id=\"timepicker2\" type=\"text\" class=\"form-control input-small\" name=\"dob\">                    \n");
      out.write("                                <span class=\"input-group-addon\"><i class=\"glyphicon glyphicon-time\"></i></span>\n");
      out.write("                            </div>\n");
      out.write("                            <script type=\"text/javascript\">\n");
      out.write("\n");
      out.write("                                $('#timepicker2').datepicker();\n");
      out.write("                            </script>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Doctor Mobile # :- </label>\n");
      out.write("                            <div class=\"input-group\">                        \n");
      out.write("                                <span class=\"input-group-addon\">+94</span>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Enter Doctor Mobile Here\" value=\"");
      out.print(doctor.getDoctorMobile());
      out.write("\" name=\"mobile\"/>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Doctor Land # :- </label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("\n");
      out.write("                                <span class=\"input-group-addon\">+94</span>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" placeholder=\"Enter Doctor Land Here\" value=\"");
      out.print(doctor.getDoctorLand());
      out.write("\" name=\"land\"/>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Doctor NIC :- </label>\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Enter NIC Here\" value=\"");
      out.print(doctor.getDoctorNic());
      out.write("\" name=\"nic\" />\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Doctor Email Address :- </label>\n");
      out.write("                            <input type=\"email\" class=\"form-control\" placeholder=\"Enter Doctor Email Here\" value=\"");
      out.print(doctor.getDoctorEmail());
      out.write("\" name=\"email\"/>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Doctor Address :- </label>\n");
      out.write("                            <textarea class=\"form-control\" placeholder=\"Enter Doctor Address Here\" name=\"address\">");
      out.print(doctor.getDoctorAddress());
      out.write("</textarea>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <table class=\"table table-hover\">\n");
      out.write("                            <caption>Doctor Qualification List</caption>\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>Qualification ID</th>\n");
      out.write("                                    <th>Qualification Name</th>\n");
      out.write("                                </tr>\n");
      out.write("                            </thead>\n");
      out.write("                            ");

                                Iterator<POJOs.DoctorHasQualifications> qualificationsList = doctor.getDoctorHasQualificationses().iterator();
                                while (qualificationsList.hasNext()) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                ");

                                    POJOs.DoctorQualifications qual = qualificationsList.next().getDoctorQualifications();

                                
      out.write("\n");
      out.write("                                <td>");
      out.print(qual.getIddoctorQualifications());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(qual.getQualificationName());
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    <button class=\"btn btn-danger\" type=\"button\" onclick=\"removeQualifications(");
      out.print(qual.getIddoctorQualifications());
      out.write(',');
      out.print(user.getDoctor().getIddoctor());
      out.write(")\"><span class=\"glyphicon glyphicon-remove\"></span> Remove!</button>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
                            
      out.write("\n");
      out.write("                        </table>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select Qualifications to add :- </label>\n");
      out.write("                            <select class=\"form-control\" name=\"qualification\">\n");
      out.write("                                ");

                                    List<POJOs.DoctorQualifications> qualList = s.createCriteria(POJOs.DoctorQualifications.class).list();
                                    for (POJOs.DoctorQualifications q : qualList) {
      out.write("\n");
      out.write("                                <option>");
      out.print(q.getQualificationName());
      out.write("</option>\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                            </select>                      \n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select New Category :- </label>\n");
      out.write("                            <select class=\"form-control\" name=\"category\">\n");
      out.write("                                ");

                                    List<POJOs.DoctorField> fieldList = s.createCriteria(POJOs.DoctorField.class).list();
                                    for (POJOs.DoctorField f : fieldList) {
      out.write("\n");
      out.write("                                <option>");
      out.print(f.getFieldName());
      out.write("</option>\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            ");

                                POJOs.DoctorFieldHasDoctor doctorField = (POJOs.DoctorFieldHasDoctor) doctor.getDoctorFieldHasDoctors().iterator().next();

                            
      out.write("\n");
      out.write("                            <label>Current Category : ");
      out.print(doctorField.getDoctorField().getFieldName());
      out.write("</label>\n");
      out.write("                        </div>\n");
      out.write("                        <button class=\"btn btn-info\" type=\"submit\"><span class=\"glyphicon glyphicon-edit\"></span> Update My Details</button>\n");
      out.write("                    </form>\n");
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
