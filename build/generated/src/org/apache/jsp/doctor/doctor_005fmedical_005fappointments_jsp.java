package org.apache.jsp.doctor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Date;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Session;

public final class doctor_005fmedical_005fappointments_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Doctor Panel</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("        <script src=\"../js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"../bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("        \n");
      out.write("        <script>\n");
      out.write("            function todayAppointment (){\n");
      out.write("                window.location.replace('doctor_medical_appointments.jsp?todayappointment=23');\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            Session s = Connection.Connector.getSessionFactory().openSession();

            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            if (user == null) {
                response.sendRedirect("../homepage.jsp");
            } else if (!user.getUsertype().equals("DOCTOR")) {
                response.sendRedirect("../homepage.jsp");
            }

            POJOs.Doctor doctor = user.getDoctor();
        
      out.write("\n");
      out.write("        <div class=\"jumbotron\">\n");
      out.write("            <div class=\"center-block\">\n");
      out.write("                <center>\n");
      out.write("                    <h2>Welcome Dr. ");
      out.print(user.getDoctor().getDoctorFname() + " " + user.getDoctor().getDoctorLname());
      out.write(" <small>to your own workspace!</small></h2> \n");
      out.write("                </center>\n");
      out.write("            </div>                  \n");
      out.write("            <div class=\"pull-right\">\n");
      out.write("                <form action=\"../handlelogout\" method=\"POST\">\n");
      out.write("                    <button class=\"btn btn-danger\" style=\"margin-right: 15px;\">Sign out <span class=\"glyphicon glyphicon-user\"></span></button>\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <div class=\"row\">\n");
      out.write("            <div class=\"col-lg-2\" style=\"margin-top: 40px;\">\n");
      out.write("                <ul class=\"nav nav-pills nav-stacked\">\n");
      out.write("                    <li><a href=\"doctor_home.jsp\">Home and Notifications</a></li>\n");
      out.write("                    <li><a href=\"doctor_prescriptions.jsp\">Prescription Editor</a></li>\n");
      out.write("                    <li class=\"active\"><a href=\"doctor_medical_appointments.jsp\">Your Medical Appointments</a></li>\n");
      out.write("                    <li><a href=\"doctor_patient_appointments.jsp\">Your Patient Appointments</a></li>\n");
      out.write("                    <li><a href=\"doctor_account_settings.jsp\">Account Settings and Preferences</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"col-lg-10\">\n");
      out.write("                <div class=\"input-group\">\n");
      out.write("                    <input type=\"text\" value=\"\" class=\"form-control\" placeholder=\"Search Medical Services\"/>                    \n");
      out.write("                    <span class=\"input-group-btn\">\n");
      out.write("                        <button class=\"btn btn-primary\" onclick=\"todayAppointment()\">Today's Appointments!</button>\n");
      out.write("                    </span>\n");
      out.write("                    \n");
      out.write("                </div>\n");
      out.write("                <table class=\"table table-hover\">\n");
      out.write("                    <caption>Medical Services List that concern's you</caption>\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Medical Service #</th>\n");
      out.write("                            <th>Medical Service Name</th>\n");
      out.write("                            <th>Patient Name</th>\n");
      out.write("                            <th>Patient Mobile</th>\n");
      out.write("                            <th>Patient Email Address</th>                          \n");
      out.write("                            <th>Date</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                    <tbody>\n");
      out.write("                        ");

                            List<POJOs.TimeTableServices> doctorMedicalService = s.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("doctor", doctor)).list();
                            int counter = 1;

                            if (request.getParameter("todayappointment") != null) {
                                System.out.println("here");
                                String datex = new Date().toString();

                                System.out.println(datex);

                                String dateArray[] = datex.split(" ");

                                String month = "";

                                if (dateArray[1].equals("Jan")) {
                                    month = "01";
                                }

                                if (dateArray[1].equals("Feb")) {
                                    month = "02";
                                }

                                if (dateArray[1].equals("Mar")) {
                                    month = "03";
                                }

                                if (dateArray[1].equals("Apr")) {
                                    month = "04";
                                }
                                if (dateArray[1].equals("May")) {
                                    month = "05";
                                }
                                if (dateArray[1].equals("Jun")) {
                                    month = "06";
                                }
                                if (dateArray[1].equals("Jul")) {
                                    month = "07";
                                }
                                if (dateArray[1].equals("Aug")) {
                                    month = "08";
                                }
                                if (dateArray[1].equals("Sep")) {
                                    month = "09";
                                }
                                if (dateArray[1].equals("Oct")) {
                                    month = "10";
                                }
                                if (dateArray[1].equals("Nov")) {
                                    month = "11";
                                }
                                if (dateArray[1].equals("Dec")) {
                                    month = "12";
                                }

                                //System.out.println(month);
                                String day = dateArray[2];

                                String year = dateArray[5];

                                String fullDate = month + "/" + day + "/" + year;
                                Session s2 = Connection.Connector.getSessionFactory().openSession();
                               doctorMedicalService = s2.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("doctor", doctor)).add(Restrictions.eq("day", fullDate)).list();

                            }

                            for (POJOs.TimeTableServices record : doctorMedicalService) {
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print(counter);
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(record.getMedicalServices().getServiceName());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(record.getPatient().getPatientFname() + " " + record.getPatient().getPatientLname());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(record.getPatient().getPatientPhoneno());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(record.getPatient().getPatientEmail());
      out.write("</td>                             \n");
      out.write("                            <td>");
      out.print(record.getDay());
      out.write("</td>\n");
      out.write("                        </tr>                        \n");
      out.write("                        ");
}
                        
      out.write("\n");
      out.write("                    </tbody>\n");
      out.write("                </table>\n");
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
