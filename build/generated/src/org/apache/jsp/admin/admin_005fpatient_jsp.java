package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public final class admin_005fpatient_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var searchType = \"patientFname\";\n");
      out.write("            function changeSearchType(name) {\n");
      out.write("                if (name === \"Patient First Name\") {\n");
      out.write("                    searchType = \"patientFname\";\n");
      out.write("                } else if (name === \"Patient Middle Name\") {\n");
      out.write("                    searchType = \"patientMname\";\n");
      out.write("                } else if (name === \"Patient Last Name\") {\n");
      out.write("                    searchType = \"patientLname\";\n");
      out.write("                }else if (name === \"Patient NIC\"){\n");
      out.write("                    searchType = \"patientNic\";\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                console.log(searchType);\n");
      out.write("\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function searchData() {\n");
      out.write("\n");
      out.write("                var search = document.getElementById(\"txtSearch\").value;\n");
      out.write("                //  window.navigate\n");
      out.write("                window.location.replace('admin_patient.jsp?search=' + search + \"&searchType=\" + searchType);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function fillPatientDetails(id) {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleLoadPatient\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": id\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        var dataset = data.split(\"#\");\n");
      out.write("                        $('#patientid').val(dataset[11]);\n");
      out.write("                        $('#pusername').val(dataset[0]);\n");
      out.write("                        $('#ppassword').val(dataset[1]);\n");
      out.write("                        $('#pfname').val(dataset[2].split('.')[1]);\n");
      out.write("                        $('#pmname').val(dataset[3]);\n");
      out.write("                        $('#plname').val(dataset[4]);\n");
      out.write("                        $('#salutation').val(dataset[2].split('.')[0]);\n");
      out.write("                        $('#gender').val(dataset[5]);\n");
      out.write("                        $('#timepicker2').val(dataset[6]);\n");
      out.write("                        $('#nic').val(dataset[7]); \n");
      out.write("                        $('#phone').val(dataset[8]);\n");
      out.write("                        $('#address').val(dataset[9]);\n");
      out.write("                        $('#email').val(dataset[10]);\n");
      out.write("\n");
      out.write("                        $('#btnupdate').css({\"visibility\": \"visible\"});\n");
      out.write("                        $('#btndelete').css({\"visibility\": \"visible\"});\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("\n");
      out.write("        </script>\n");
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
      out.write("                        <li><a href=\"admin_home.jsp\">Home and Notifications</a></li>\n");
      out.write("                        <li class=\"active\"><a href=\"admin_patient.jsp\">Patient Details</a></li>\n");
      out.write("                        <li><a href=\"admin_doctor.jsp\">Doctor Details</a></li>\n");
      out.write("                        <li><a href=\"admin_pharamacy.jsp\">Pharmacy Details</a></li>\n");
      out.write("                        <li><a href=\"admin_knowledge.jsp\">Knowledge Base</a></li>\n");
      out.write("                        <li><a href=\"admin_medicalservice.jsp\">Medical Services</a></li>                        \n");
      out.write("                        <li><a href='admin_blog.jsp'>Blog Details</a></li>\n");
      out.write("                        <li><a href=\"admin_misc.jsp\">Misc and User Account Mgt</a></li>                       \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-lg-10\">\n");
      out.write("                    <div class='input-group' style=\"margin-bottom: 15px;\">\n");
      out.write("                        <input type=\"text\" class=\"form-control\" placeholder=\"Search Patient Records\" id=\"txtSearch\"/>\n");
      out.write("                        <div class=\"input-group-btn\" >\n");
      out.write("                            <button type=\"button\" class=\"btn btn-default \n");
      out.write("                                    dropdown-toggle\" data-toggle=\"dropdown\">\n");
      out.write("                                Search By\n");
      out.write("                                <span class=\"caret\"></span>\n");
      out.write("                            </button>\n");
      out.write("                            <ul class=\"dropdown-menu pull-right\">\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\" href=\"#\">Patient First Name</a></li>\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\" href=\"#\">Patient Middle Name</a></li>\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\" href=\"#\">Patient Last Name</a></li>\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\" href=\"#\">Patient NIC</a></li> \n");
      out.write("                            </ul>\n");
      out.write("                        </div><!-- /btn-group -->\n");
      out.write("                        <span class='input-group-btn'>\n");
      out.write("                            <button class='btn btn-default' onclick=\"searchData()\">Search!</button>\n");
      out.write("                        </span>  \n");
      out.write("                    </div>\n");
      out.write("                    <table class=\"table table-hover\">\n");
      out.write("                        <caption>Patient Details</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>First Name</th>\n");
      out.write("                                <th>Middle Name</th>\n");
      out.write("                                <th>Last Name</th>\n");
      out.write("                                <th>Date Of Birth</th>\n");
      out.write("                                <th>Gender</th>\n");
      out.write("                                <th>NIC</th>\n");
      out.write("                                <th>Phone #</th>\n");
      out.write("                                <th>Email</th>\n");
      out.write("                                <th>Address</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        ");

                            List<POJOs.Patient> listPatient = null;
                            if (request.getParameter("search") == null) {
                                listPatient = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Patient.class).list();
                            } else {
                                String search = request.getParameter("search");
                                String searchType = request.getParameter("searchType");
                                listPatient = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Patient.class).add(Restrictions.like(searchType, search + "%")).list();
                            }
                            for (POJOs.Patient p : listPatient) {
                                if (p.getIdpatient() != -1) {
      out.write("\n");
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print(p.getPatientFname());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientMname());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientLname());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientDob());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientGender());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientNic());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientPhoneno());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientEmail());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(p.getPatientAddress());
      out.write("</td>\n");
      out.write("                            <td>\n");
      out.write("                                <button class=\"btn btn-warning\" onclick=\"fillPatientDetails(");
      out.print(p.getIdpatient());
      out.write(")\"><span class=\"glyphicon glyphicon-check\"></span> Update this</button>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("\n");
      out.write("                        ");
}
                            }
                        
      out.write("\n");
      out.write("                    </table>\n");
      out.write("                   ");
  if (request.getParameter("res") != null) {
                            if (request.getParameter("res").equals("success")) {
      out.write("\n");
      out.write("                    <div id=\"doctor_success\" class=\"alert alert-success\" style=\"visibility: visible\">\n");
      out.write("                        <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                        <strong>Success!</strong> Doctor's data was updated successfully!\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    ");
} else if (request.getParameter("res").equals("fail")) {
      out.write("\n");
      out.write("\n");
      out.write("                    <div id=\"doctor_fail\" class=\"alert alert-danger\" style=\"visibility: visible\">\n");
      out.write("                        <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                        <strong>Oops!</strong> Something went wrong while saving data!\n");
      out.write("                    </div>\n");
      out.write("                    ");
} else if (request.getParameter("res").equals("validation")) {
      out.write("\n");
      out.write("                    <div id=\"doctor_valid\" class=\"alert alert-warning\">\n");
      out.write("                        <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                        <strong>Validation Error Occurred</strong> ");
      out.print(request.getParameter("validation_msg"));
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                    ");
}
                        }
                    
      out.write("\n");
      out.write("\n");
      out.write("                    <form for='form' method=\"POST\" action='../handleCustomerCreate'>\n");
      out.write("                        <div class=\"form-group\" style=\"margin-top: 50px;\">\n");
      out.write("                            <input type=\"hidden\" value=\"\" id=\"patientid\" name=\"patientid\" />\n");
      out.write("                            <label for=\"pusername\">Username :- </label>\n");
      out.write("                            <input type=\"text\" value=\"\" id=\"pusername\" placeholder=\"Enter username\" class=\"form-control\" name='username' required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"ppassword\">Password :- </label>\n");
      out.write("                            <input type=\"password\" value=\"\" id=\"ppassword\" placeholder=\"Enter username\" class=\"form-control\" name='password' required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"ppassword_confirm\">Confirm Password:- </label>\n");
      out.write("                            <input type=\"password\" value=\"\" id=\"ppassword_confirm\" placeholder=\"Enter Password Again\" class=\"form-control\" name=\"confirmpassword\" required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"psalutate\">Select Salutation :- </label>\n");
      out.write("                            <select class=\"form-control\" name='salutation' id='salutation' required>\n");
      out.write("                                <option>Mr.</option>\n");
      out.write("                                <option>Master.</option>\n");
      out.write("                                <option>Mrs.</option>\n");
      out.write("                                <option>Miss.</option>\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"pfname\">Enter First Name :- </label>\n");
      out.write("                            <input type=\"text\" value=\"\" id=\"pfname\" placeholder=\"Enter First Name\" class=\"form-control\" name='firstname' required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"pmname\">Enter Middle Name :- </label>\n");
      out.write("                            <input type=\"text\" value=\"\" id=\"pmname\" placeholder=\"Enter Middle Name\" class=\"form-control\" name='middlename' required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"plname\">Enter Last Name :- </label>\n");
      out.write("                            <input type=\"text\" value=\"\" id=\"plname\" placeholder=\"Enter Last Name\" class=\"form-control\" name='lastname' required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select Gender :- </label>\n");
      out.write("                            <select class=\"form-control\" name='gender' id='gender' required>\n");
      out.write("                                <option>Male</option>\n");
      out.write("                                <option>Female</option>\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                       <label>Select Date of Birth :- </label>\n");
      out.write("                        <div class=\"input-group date\" data-provide=\"datepicker\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" name=\"dob\" id=\"timepicker2\">\n");
      out.write("                            <div class=\"input-group-addon\">\n");
      out.write("                                <span class=\"glyphicon glyphicon-th\"></span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Enter your NIC :- </label>\n");
      out.write("                            <input type=\"text\" value=\"\" class=\"form-control\" name='nic' id='nic' placeholder=\"Enter National Identity No, If any\" required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Enter your Phone # :- </label>\n");
      out.write("                            <div class=\"input-group\">\n");
      out.write("                                <span class=\"input-group-addon\">+94</span>\n");
      out.write("                                <input type=\"text\" value=\"\" class=\"form-control input-small\" id='phone' name='phone' placeholder=\"Enter Phone Number\" required/>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"paddress\">Enter your Address :- </label>\n");
      out.write("                            <textarea class=\"form-control\" name='address' id='address' required></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"pemail\">Enter your email address</label>\n");
      out.write("                            <input type=\"email\" class=\"form-control\" name='email' id='email' required/>\n");
      out.write("                        </div>\n");
      out.write("                        <input value=\"Save Patient\" type=\"submit\" name=\"btnsub\" class=\"btn btn-info\">\n");
      out.write("                        <input value=\"Update Patient\" type=\"submit\" id=\"btnupdate\" name=\"btnsub\" class=\"btn btn-info\" style=\"visibility: hidden\">\n");
      out.write("                        <input value=\"Delete Patient\" type=\"submit\" id=\"btndelete\" name=\"btnsub\" class=\"btn btn-info\" style=\"visibility: hidden\"> \n");
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
