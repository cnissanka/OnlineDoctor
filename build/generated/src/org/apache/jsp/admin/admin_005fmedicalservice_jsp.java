package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public final class admin_005fmedicalservice_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <style>\n");
      out.write("            .btn-file {\n");
      out.write("                position: relative;\n");
      out.write("                overflow: hidden;\n");
      out.write("            }\n");
      out.write("            .btn-file input[type=file] {\n");
      out.write("                position: absolute;\n");
      out.write("                top: 0;\n");
      out.write("                right: 0;\n");
      out.write("                min-width: 100%;\n");
      out.write("                min-height: 100%;\n");
      out.write("                font-size: 100px;\n");
      out.write("                text-align: right;\n");
      out.write("                filter: alpha(opacity=0);\n");
      out.write("                opacity: 0;\n");
      out.write("                outline: none;\n");
      out.write("                background: white;\n");
      out.write("                cursor: inherit;\n");
      out.write("                display: block;\n");
      out.write("            }\n");
      out.write("        </style>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var serviceID = 0;\n");
      out.write("            function updateId(serviceId) {\n");
      out.write("\n");
      out.write("                serviceID = serviceId;\n");
      out.write("\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function getServiceDetails() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleGetServicedDetails\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": serviceID\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        var dataset = data.split(\"#\");\n");
      out.write("                        $('#txtServiceName').val(dataset[0]);\n");
      out.write("                        $('#txtServiceDescription').val(dataset[1]);\n");
      out.write("                        $('#txtServicePrice').val(dataset[2]);\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function updateService() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleUpdateMedicalService\",\n");
      out.write("                    \n");
      out.write("                    data: {\n");
      out.write("                        \"id\":serviceID,\n");
      out.write("                        \"name\": $('#txtServiceName').val(),\n");
      out.write("                        \"description\": $('#txtServiceDescription').val(),\n");
      out.write("                        \"price\": $('#txtServicePrice').val()\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        alert('Updated Successfully - change me!');\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        alert('Error occurred - change me!');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function search (){\n");
      out.write("                window.location.replace('admin_medicalservice.jsp?search='+$('#search').val());\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function deleteService() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleDeleteMedicalService\",\n");
      out.write("                    \n");
      out.write("                    data: {\n");
      out.write("                        \"id\":serviceID\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        alert('Deleted successfully');\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        alert('error occurred!');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("        </script>\n");
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
      out.write("                        <li><a href=\"admin_patient.jsp\">Patient Details</a></li>\n");
      out.write("                        <li><a href=\"admin_doctor.jsp\">Doctor Details</a></li>\n");
      out.write("                        <li><a href=\"admin_pharamacy.jsp\">Pharmacy Details</a></li>\n");
      out.write("                        <li><a href=\"admin_knowledge.jsp\">Knowledge Base</a></li>\n");
      out.write("                        <li class='active'><a href=\"admin_medicalservice.jsp\">Medical Services</a></li>\n");
      out.write("                        <li><a href='admin_blog.jsp'>Blog Details</a></li>\n");
      out.write("                        <li><a href=\"admin_misc.jsp\">Misc and User Account Mgt</a></li>                       \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-lg-10\">\n");
      out.write("                    <div class='input-group'>\n");
      out.write("                        <input type='text' placeholder=\"Search Medical Services\" class='form-control' id=\"search\"/>\n");
      out.write("                        <span class='input-group-btn'>\n");
      out.write("                            <button class='btn btn-default' onclick=\"search()\">Search!</button>\n");
      out.write("                        </span>\n");
      out.write("                    </div>\n");
      out.write("                    <table class=\"table table-hover\">\n");
      out.write("                        <caption>Medical Services Details</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Service Name</th>\n");
      out.write("                                <th>Service Description</th>\n");
      out.write("                                <th>Service Image</th>\n");
      out.write("                                <th>Service Price</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        ");

                            List<POJOs.MedicalServices> medicalServiceList = null;
                            if (request.getParameter("search") != null) {
                                medicalServiceList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.MedicalServices.class).add(Restrictions.like("serviceName", request.getParameter("search") + "%")).list();
                            } else {
                                medicalServiceList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.MedicalServices.class).list();
                            }
                        
      out.write("\n");
      out.write("\n");
      out.write("                        ");
for (POJOs.MedicalServices mService : medicalServiceList) {
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print(mService.getServiceName());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(mService.getServiceDescription());
      out.write("</td>\n");
      out.write("                            <td>\n");
      out.write("                                <img src=\"../");
      out.print(mService.getServiceImage());
      out.write("\" width=\"64px\" height=\"64px\" />\n");
      out.write("                            </td>\n");
      out.write("                            <td>Rs.");
      out.print(mService.getServicePrice());
      out.write("0</td>\n");
      out.write("                            <td>\n");
      out.write("                                <button class=\"btn btn-warning\" data-toggle=\"modal\" data-target=\"#medicalViewer\" onclick=\"updateId(");
      out.print(mService.getIdmedicalServices());
      out.write(")\">Edit Details</button>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("                    </table>\n");
      out.write("                    \n");
      out.write("                    ");
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
}}
      out.write("\n");
      out.write("                    \n");
      out.write("                    <form method=\"POST\" action=\"../handleMedicineService\" enctype=\"multipart/form-data\">\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Medical Service Name :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder=\"Medical Service Name\" name=\"servicename\" required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Medical Service Description :- </label>\n");
      out.write("                            <textarea placeholder=\"Medical Service Description\" class='form-control' name=\"description\" required></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Medical Service Image :- </label>\n");
      out.write("                            <span class=\"btn btn-primary btn-file\" id=\"pre\" > \n");
      out.write("                                Browse for Medical Service Image (png, jpg)<input type=\"file\" name=\"txtfile\">\n");
      out.write("                            </span>\n");
      out.write("                        </div>\n");
      out.write("                        <label>Medical Service Price :- </label>\n");
      out.write("                        <div class='input-group' style=\"margin-bottom: 50px;\">\n");
      out.write("                            <span class='input-group-addon'>Rs.</span>\n");
      out.write("                            <input type='number' value=\"1000\" placeholder=\"Medical Service Price Here\" class=\"form-control\" name=\"price\" required/>\n");
      out.write("                            <span class='input-group-addon'>.00</span>\n");
      out.write("                        </div>\n");
      out.write("                        <input type=\"submit\" class=\"btn btn-info\" name=\"btn\" value=\"Save Service\" />\n");
      out.write("\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    <!--Modal for qualification viewer -->\n");
      out.write("                    <div class='modal fade' id='medicalViewer' tabindex=\"-1\" role='dialog' aria-labelledby='medicalViewerLabel' aria-hidden='true' onshow=\"getServiceDetails()\">\n");
      out.write("                        <div class='modal-dialog'>\n");
      out.write("                            <div class='modal-content'>\n");
      out.write("                                <div class='modal-header'>\n");
      out.write("                                    <button type='button' class=\"close\" data-dismiss='modal' aria-hidden='true'>\n");
      out.write("                                        &times;\n");
      out.write("                                    </button>\n");
      out.write("                                    <h4 class='modal-title' id='medicalViewerLabel'>\n");
      out.write("                                        Medical Data Editor\n");
      out.write("                                    </h4>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-body\">\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label>Service Name :-</label>\n");
      out.write("                                        <input type=\"text\" id=\"txtServiceName\" class=\"form-control\" placeholder=\"Medical Service Name Here\"/>\n");
      out.write("                                    </div>\n");
      out.write("                                    <div class=\"form-group\">\n");
      out.write("                                        <label>Service Description :- </label>\n");
      out.write("                                        <textarea class=\"form-control\" id=\"txtServiceDescription\" placeholder=\"Medical Service Description Here\"></textarea>\n");
      out.write("                                    </div>\n");
      out.write("                                    <label>Service Price :-</label>\n");
      out.write("                                    <div class=\"input-group\">\n");
      out.write("                                        <span class='input-group-addon'>Rs.</span>\n");
      out.write("                                        <input type='number' value=\"1000\" placeholder=\"Medical Service Price Here\" class=\"form-control\" id=\"txtServicePrice\"/>\n");
      out.write("                                        <span class='input-group-addon'>.00</span>\n");
      out.write("                                    </div>\n");
      out.write("                                </div>\n");
      out.write("                                <div class=\"modal-footer\">\n");
      out.write("                                    <button type=\"button\" class=\"btn btn-info\" onclick=\"updateService()\">Update Service</button>\n");
      out.write("                                    <button type=\"button\" class=\"btn btn-info\" onclick=\"deleteService()\">Delete Service</button>\n");
      out.write("                                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Close\n");
      out.write("                                    </button>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                    <!-- End Modal -->\n");
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
