package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.criterion.Restrictions;
import org.omg.PortableServer.POA;
import java.util.List;
import org.hibernate.Session;

public final class medical_005fservices_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap-timepicker.min.css\"/>\n");
      out.write("        <script src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap-timepicker.min.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap-datepicker.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"js/bootstrap-datepicker.min.css\"/>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            function getMedicalService(id, name) {\n");
      out.write("\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function selectMedicicalService(id) {\n");
      out.write("                window.location.replace('medical_services.jsp?medicine=' + id);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function getDoctorID() {\n");
      out.write("                var e = document.getElementById('doctors');\n");
      out.write("                var iddoctor = e.value;\n");
      out.write("\n");
      out.write("                document.getElementById('txtDoctorID').value = iddoctor;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            String medicineID = "";
            String medicineName = "";
            if (request.getParameter("medID") != null && request.getParameter("medName") != null) {
                medicineID = request.getParameter("medID");
                medicineName = request.getParameter("medName");
            }
        
      out.write("\n");
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
      out.write("            <!--end of navbar code-->\n");
      out.write("            <h2 style=\"padding-top: 50px;\">Medical Services <small>Medical Services we offer</small></h2>\n");
      out.write("            <table class=\"table table-hover\">\n");
      out.write("                <caption>Medical Services</caption>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Medical Service Name</th>\n");
      out.write("                        <th>Medical Service Description</th>\n");
      out.write("                        <th>Medical Service Image</th>\n");
      out.write("                        <th>Medical Service Price</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("                ");

                    Session s = Connection.Connector.getSessionFactory().openSession();
                    List<POJOs.MedicalServices> medicalServiceList = s.createCriteria(POJOs.MedicalServices.class).list();

                    for (POJOs.MedicalServices ms : medicalServiceList) {
      out.write("\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>");
      out.print(ms.getServiceName());
      out.write("</td>\n");
      out.write("                    <td>");
      out.print(ms.getServiceDescription());
      out.write("</td>\n");
      out.write("                    <td>\n");
      out.write("                        <img src=\"");
      out.print(ms.getServiceImage());
      out.write("\" width=\"64px\" height=\"64px\"/>\n");
      out.write("                    </td>\n");
      out.write("                    <td>");
      out.print(ms.getServicePrice());
      out.write("</td>\n");
      out.write("                    <td>\n");
      out.write("                        ");
if (user != null && user.getUsertype().equals("CUSTOMER")) {
      out.write("\n");
      out.write("                        <button class=\"btn btn-primary\" onclick=\"selectMedicicalService(");
      out.print(ms.getIdmedicalServices());
      out.write(")\">Add to Appointments</button>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    </td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("                ");
}
                
      out.write("\n");
      out.write("\n");
      out.write("            </table>\n");
      out.write("            ");
if (user != null && user.getUsertype().equals("CUSTOMER")) {
      out.write("\n");
      out.write("\n");
      out.write("            ");
  if (request.getParameter("res") != null) {
                     if (request.getParameter("res").equals("success")) {
      out.write("\n");
      out.write("            <div id=\"doctor_success\" class=\"alert alert-success\" style=\"visibility: visible\">\n");
      out.write("                <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                <strong>Success!</strong> Created an Medical Service Appointment Successfully!\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");
} else if (request.getParameter("res").equals("fail")) {
      out.write("\n");
      out.write("\n");
      out.write("            <div id=\"doctor_fail\" class=\"alert alert-danger\" style=\"visibility: visible\">\n");
      out.write("                <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                <strong>Oops!</strong> Something went wrong while saving data!\n");
      out.write("            </div>\n");
      out.write("            ");
} else if (request.getParameter("res").equals("validation")) {
      out.write("\n");
      out.write("            <div id=\"doctor_valid\" class=\"alert alert-warning\">\n");
      out.write("                <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                <strong>Validation Error Occurred</strong> ");
      out.print(request.getParameter("validation_msg"));
      out.write("\n");
      out.write("            </div>\n");
      out.write("            ");
}
                }
            
      out.write("\n");
      out.write("            <form for=\"form\" action=\"handleAddMedicalService\" method=\"POST\">\n");
      out.write("\n");
      out.write("                <input type=\"hidden\" id=\"medicalSerivceID\" name=\"medicalServiceID\" value=\"");
      out.print(medicineID);
      out.write("\"/>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"medicalname\">Medical Service Name :- </label>\n");
      out.write("                    ");
 if (request.getParameter("medicine") != null) {
      out.write("\n");
      out.write("                    ");

                        System.out.print("hello");
                        POJOs.MedicalServices med = (POJOs.MedicalServices) s.createCriteria(POJOs.MedicalServices.class).add(Restrictions.eq("idmedicalServices", Integer.parseInt(request.getParameter("medicine")))).uniqueResult();
                        System.out.println(med);
                    
      out.write("\n");
      out.write("                    <input type='hidden' name='patient' value=\"");
      out.print(user.getPatient().getIdpatient());
      out.write("\" />\n");
      out.write("                    <input type='hidden' name='medicine' value=\"");
      out.print(request.getParameter("medicine"));
      out.write("\" />\n");
      out.write("                    <input type='hidden' id='txtDoctorID' name='doctor' />\n");
      out.write("                    <input type=\"text\" class=\"form-control\" id=\"medicalname\" value='");
      out.print(med.getServiceName());
      out.write("' disabled/>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label for=\"combodoctor\">Select Doctor :- </label>\n");
      out.write("                    <select class=\"form-control\" onchange=\"getDoctorID()\" id='doctors'>                        ");

                        List<POJOs.Doctor> docList = s.createCriteria(POJOs.Doctor.class).list();
                        for (POJOs.Doctor d : docList) {
                            if (d.getIddoctor() != -1) {
      out.write("\n");
      out.write("                        <option value=\"");
      out.print(d.getIddoctor());
      out.write('"');
      out.write('>');
      out.print("Dr. " + d.getDoctorFname() + "  " + d.getDoctorLname());
      out.write("</option>\n");
      out.write("\n");
      out.write("                        ");
}
                            }
                        
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                </div>\n");
      out.write("                <label>Date :- </label>\n");
      out.write("                <div class=\"input-group date\" data-provide=\"datepicker\">\n");
      out.write("                    <input type=\"text\" class=\"form-control\" name=\"date\">\n");
      out.write("                    <div class=\"input-group-addon\">\n");
      out.write("                        <span class=\"glyphicon glyphicon-th\"></span>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <br/>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <button type=\"submit\" class=\"btn btn-primary\" style=\"margin-top: 20px;\"><span class=\"glyphicon glyphicon-pencil\"></span> Save My Appointment</button>\n");
      out.write("            </form>\n");
      out.write("                    <form action=\"handleRemoveMedicalServiceAppointment\" method=\"POST\">\n");
      out.write("                <table class=\"table table-hover\" style=\"margin-top: 40px;\">\n");
      out.write("                    <caption>Your Current Appointments</caption>\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Doctor In charge</th>\n");
      out.write("                            <th>Appointment Selected</th>\n");
      out.write("                            <th>Day Allocation</th>                            \n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    ");

                        List<POJOs.TimeTableServices> appointments = s.createCriteria(POJOs.TimeTableServices.class).list();
                        for (POJOs.TimeTableServices a : appointments) {
      out.write("\n");
      out.write("\n");
      out.write("                    <tr>\n");
      out.write("                        <td>Dr. ");
      out.print(a.getDoctor().getDoctorFname() + a.getDoctor().getDoctorLname());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(a.getMedicalServices().getServiceName());
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(a.getDay());
      out.write("</td>\n");
      out.write("                        <td>\n");
      out.write("                            <button type='submit' name='btn' class=\"btn btn-danger\" value=\"");
      out.print(a.getIdTimeTableServices());
      out.write("\"><span class=\"glyphicon glyphicon-remove\"></span></button>\n");
      out.write("                        </td>\n");
      out.write("                        <td>\n");
      out.write("                            <a href=\"invoice_medService.jsp?id=");
      out.print(a.getIdTimeTableServices());
      out.write("\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-print\"></span> Print Invoice</a>\n");
      out.write("                        </td>\n");
      out.write("                    </tr>\n");
      out.write("\n");
      out.write("                    ");
}
                    
      out.write("\n");
      out.write("                </table>\n");
      out.write("            </form>\n");
      out.write("            ");
}
      out.write("\n");
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
