package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.criterion.Restrictions;
import java.util.List;
import org.hibernate.Session;

public final class admin_005fpharamacy_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Admin Panel</title>\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width,\n");
      out.write("              initial-scale=1.0,\n");
      out.write("              maximum-scale=1.0,\n");
      out.write("              user-scalable=no\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"../bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("        <script src=\"../js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"../bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"../js/bootstrap-datepicker.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../js/bootstrap-datepicker.min.css\"/>\n");
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
      out.write("            var supplierID;\n");
      out.write("            var medicineID;\n");
      out.write("            var searchType = \"\";\n");
      out.write("            function changeSupplierID(x) {\n");
      out.write("                supplierID = x;\n");
      out.write("                $('#txtSupplierID').val(supplierID);\n");
      out.write("                $('#supplierID').val(supplierID);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function changeSearchType(x) {\n");
      out.write("                if (x === \"Medicine Name\") {\n");
      out.write("                    searchType = \"medicineName\";\n");
      out.write("                } else if (x === \"Medicine Qty\") {\n");
      out.write("                    searchType = \"medicineQty\";\n");
      out.write("                } else if (x === \"Medicine Price\") {\n");
      out.write("                    searchType = \"medicinePrice\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function searchMedicine(x) {\n");
      out.write("                window.location.replace('admin_pharamacy.jsp?search=' + x + '&searchType=' + searchType);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            var test = \"\";\n");
      out.write("            function getSupplierDetails(x) {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleGetSupplierGet\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": x\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        $('#btnupdate').css({\"visibility\": \"visible\"});\n");
      out.write("                        $('#btndelete').css({\"visibility\": \"visible\"});\n");
      out.write("\n");
      out.write("                        var dataset = data.split('#');\n");
      out.write("                        test = dataset;\n");
      out.write("                        $('#fname').val(dataset[0]);\n");
      out.write("                        $('#mname').val(dataset[1]);\n");
      out.write("                        $('#lname').val(dataset[2]);\n");
      out.write("\n");
      out.write("                        $('#address').val(dataset[3]);\n");
      out.write("                        $('#orgname').val(dataset[4]);\n");
      out.write("\n");
      out.write("                        $('#land').val(dataset[5]);\n");
      out.write("                        $('#phone1').val(dataset[6]);\n");
      out.write("                        $('#nic').val(dataset[7]);\n");
      out.write("\n");
      out.write("                        $('#idsupplier').val(dataset[8]);\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        alert('error');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function changeMedicineID(x) {\n");
      out.write("                medicineID = x;\n");
      out.write("                $('#medicineID').val(medicineID);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("          ");

            POJOs.User user = null;
            if (request.getSession() == null) {
                response.sendRedirect("../login_service.jsp");
            } else {
                user = (POJOs.User) request.getSession().getAttribute("user");
                if (user == null) {
                    response.sendRedirect("../login_service.jsp");
                } else if (!user.getUsertype().equals("ADMINISTRATOR")) {
                    response.sendRedirect("../login_service.jsp");
                }
            }
            //security mechanism applied for the blog.           
            boolean isAccessRighed = false;
            Session s3 = Connection.Connector.getSessionFactory().openSession();
            List<POJOs.AdminAccessRight> accessRights = s3.createCriteria(POJOs.AdminAccessRight.class).add(Restrictions.eq("administrator", user.getAdministrator())).list();

            for (POJOs.AdminAccessRight a : accessRights) {
                if (a.getAdminAccess().getIdadminAccess() == 4) {
                    System.out.println("I AM AWESOME!!!");
                    isAccessRighed = true;
                    break;
                }
            }

            if (!isAccessRighed) {
                response.sendRedirect("admin_home.jsp");
            }
        
      out.write("\n");
      out.write("        <div class=\"container-fluid\">\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"jumbotron\">\n");
      out.write("                    <div class=\"center-block\">\n");
      out.write("                        <center>\n");
      out.write("                            <h2>Welcome Admin! <small>to the Control Panel of OnlineDoctor!</small></h2> \n");
      out.write("                        </center>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"pull-right\">\n");
      out.write("                        <form action=\"../handlelogout\" method=\"POST\">            \n");
      out.write("                            <button class=\"btn btn-danger\" style=\"margin-right: 15px;\" type=\"submit\">Sign out <span class=\"glyphicon glyphicon-user\"></span></button>\n");
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
      out.write("                        <li class='active'><a href=\"admin_pharamacy.jsp\">Pharmacy Details</a></li>\n");
      out.write("                        <li><a href=\"admin_knowledge.jsp\">Knowledge Base</a></li>\n");
      out.write("                        <li><a href=\"admin_medicalservice.jsp\">Medical Services</a></li>\n");
      out.write("                        <li><a href='admin_blog.jsp'>Blog Details</a></li>\n");
      out.write("                        <li><a href=\"admin_misc.jsp\">Misc and User Account Mgt</a></li>                       \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-lg-10\">\n");
      out.write("                    <div class='input-group' style='margin-bottom: 15px;'>\n");
      out.write("                        <input type='text' placeholder='Search Products' class=\"form-control\" id=\"searchMed\"/>\n");
      out.write("                        <div class=\"input-group-btn\" >\n");
      out.write("                            <button type=\"button\" class=\"btn btn-default \n");
      out.write("                                    dropdown-toggle\" data-toggle=\"dropdown\">\n");
      out.write("                                Search By\n");
      out.write("                                <span class=\"caret\"></span>\n");
      out.write("                            </button>\n");
      out.write("                            <ul class=\"dropdown-menu pull-right\">\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\">Medicine Name</a></li>\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\">Medicine Qty</a></li>\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\">Medicine Price</a></li>                                 \n");
      out.write("                            </ul>\n");
      out.write("                        </div><!-- /btn-group -->\n");
      out.write("                        <span class='input-group-btn'>\n");
      out.write("                            <button class='btn btn-default' onclick=\"searchMedicine(document.getElementById('searchMed').value)\">Search!</button>\n");
      out.write("                        </span>\n");
      out.write("                    </div>\n");
      out.write("                    <table class='table table-hover'>\n");
      out.write("                        <caption>Medicine Details</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Medicine Name</th>\n");
      out.write("                                <th>Medicine Description</th>\n");
      out.write("                                <th>Medicine Price</th>\n");
      out.write("                                <th>Medicine Qty</th>\n");
      out.write("                                <th>Medicine Supplier</th>\n");
      out.write("                                <th>Medicine Image</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");

                                Session s2 = Connection.Connector.getSessionFactory().openSession();
                                List<POJOs.Medicine> medicineList = s2.createCriteria(POJOs.Medicine.class).list();

                                if (request.getParameter("search") != null && request.getParameter("searchType") != null) {
                                    if (!(request.getParameter("searchType").equals("medicineName"))) {
                                        if (request.getParameter("searchType").equals("medicinePrice")) {
                                            medicineList = s2.createCriteria(POJOs.Medicine.class).add(Restrictions.eq(request.getParameter("searchType"), Double.parseDouble(request.getParameter("search")))).list();
                                        } else {
                                            medicineList = s2.createCriteria(POJOs.Medicine.class).add(Restrictions.eq(request.getParameter("searchType"), request.getParameter("search"))).list();
                                        }
                                    } else {
                                        medicineList = s2.createCriteria(POJOs.Medicine.class).add(Restrictions.like(request.getParameter("searchType"), request.getParameter("search") + "%")).list();
                                    }
                                }

                                for (POJOs.Medicine med : medicineList) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(med.getMedicineName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(med.getMedicineDescription());
      out.write("</td>\n");
      out.write("                                <td>Rs.");
      out.print(med.getMedicinePrice());
      out.write("0</td>\n");
      out.write("                                <td>");
      out.print(med.getMedicineQty());
      out.write(" Units Avaliable</td>\n");
      out.write("                                <td>");
      out.print(med.getSupplier().getSupplierOrganizationName());
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    <img src=\"../");
      out.print(med.getMedicineImage());
      out.write("\" width=\"64px\" height=\"64px;\" />\n");
      out.write("                                </td>\n");
      out.write("                                <td>\n");
      out.write("                                    <button class=\"btn btn-info\" onclick=\"changeMedicineID(");
      out.print(med.getIdmedicine());
      out.write(")\" data-target=\"#medicineViewer\" data-toggle=\"modal\"><span class=\"glyphicon glyphicon-wrench\"></span> Update Details</button>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
                            
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("\n");
      out.write("                    ");
  if (request.getParameter("res") != null) {
                            if (request.getParameter("res").equals("success")) {
      out.write("\n");
      out.write("                    <div id=\"doctor_success\" class=\"alert alert-success\" style=\"visibility: visible\">\n");
      out.write("                        <a href=\"#\" class=\"close\" data-dismiss=\"alert\">&times;</a>\n");
      out.write("                        <strong>Success!</strong> Data was updated successfully!\n");
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
      out.write("                    <form method=\"POST\" action=\"../handleMedicineAdd\" enctype=\"multipart/form-data\"> \n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <input type=\"hidden\" id=\"txtSupplierID\" name=\"supplier\" />\n");
      out.write("                            <label>Medicine Name :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder=\"Medicine Name Here\" name=\"medicineName\" required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Medicine Description :- </label>\n");
      out.write("                            <textarea class='form-control' placeholder='Medicine Description Here' name=\"medicineDesc\" required></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <label>Medicine Price :- </label>\n");
      out.write("                        <div class='input-group'>\n");
      out.write("                            <span class='input-group-addon'>Rs.</span>\n");
      out.write("                            <input type='number' value='1000' class=\"form-control\" placeholder='Medicine Price Here' name=\"medicinePrice\" required/>\n");
      out.write("                            <span class='input-group-addon'>.00</span>\n");
      out.write("                        </div>\n");
      out.write("                        <br/>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Medicine Qty :- </label>\n");
      out.write("                            <input type='number' value=\"10\" class=\"form-control\" placeholder=\"Medince Quantity Here\" name=\"medicineQty\" required/>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label for=\"pre\">Insert Medicine Image :- </label>\n");
      out.write("                            <span class=\"btn btn-primary btn-file\" id=\"pre\" > \n");
      out.write("                                Browse for Medicine Image (png, jpg)<input type=\"file\" name=\"txtfile\" required>\n");
      out.write("                            </span>\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Select Supplier :- </label>\n");
      out.write("                            <select class='form-control' required>\n");
      out.write("                                ");

                                    Session s = Connection.Connector.getSessionFactory().openSession();
                                    List<POJOs.Supplier> supList = s.createCriteria(POJOs.Supplier.class).list();

                                    for (POJOs.Supplier supplier : supList) {
                                        if (!(supplier.getSupplierFname().equals("") && supplier.getSupplierLname().equals(""))) {
      out.write("\n");
      out.write("                                <option onclick=\"changeSupplierID(");
      out.print(supplier.getIdsupplier());
      out.write(")\">");
      out.print(supplier.getSupplierFname() + " " + supplier.getSupplierLname());
      out.write("</option>\n");
      out.write("                                ");
} else {
      out.write("\n");
      out.write("                                <option onclick=\"changeSupplierID(");
      out.print(supplier.getIdsupplier());
      out.write(")\">");
      out.print(supplier.getSupplierOrganizationName());
      out.write("</option>\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        <input type=\"submit\" class=\"btn btn-info\" value=\"Save Medicine\" name=\"jj\" />\n");
      out.write("\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <table class='table table-hover' style=\"margin-top: 15px;\">\n");
      out.write("                        <caption>Supplier Details</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Supplier Name</th>\n");
      out.write("                                <th>Supplier Organization</th>\n");
      out.write("                                <th>Supplier Address</th>\n");
      out.write("                                <th>Supplier Land</th>\n");
      out.write("                                <th>Supplier Phone</th>\n");
      out.write("                                <th>Supplier NIC</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");

                                List<POJOs.Supplier> supplierList = s2.createCriteria(POJOs.Supplier.class).list();
                                for (POJOs.Supplier supplier : supplierList) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(supplier.getSupplierFname() + " " + supplier.getSupplierMname() + " " + supplier.getSupplierLname());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(supplier.getSupplierOrganizationName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(supplier.getSupplierAddress());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(supplier.getSupplierLand());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(supplier.getSupplierPhone());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(supplier.getSupplierNic());
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    <button class=\"btn btn-info\" onclick=\"getSupplierDetails(");
      out.print(supplier.getIdsupplier());
      out.write(")\"><span class=\"glyphicon glyphicon-wrench\"></span> Update Details</button>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
                            
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                    <form action=\"../handleSupplierAdd\" method=\"POST\">\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <input type=\"hidden\" id=\"idsupplier\" name=\"id\" />\n");
      out.write("                            <label>Supplier First Name :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder=\"Supplier First Name Here\" name=\"fname\" id=\"fname\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Supplier Middle Name :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder=\"Supplier Middle Name Here\" name=\"mname\" id=\"mname\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Supplier Last Name :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder=\"Supplier Last Name Here\" name=\"lname\" id=\"lname\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Supplier Address :- </label>\n");
      out.write("                            <textarea class='form-control' placeholder=\"Supplier Address Here\" name=\"address\" id=\"address\"></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Supplier Organization Name :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder=\"Supplier Organization Here\"  name=\"orgname\" id=\"orgname\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <label>Supplier Land # :- </label>\n");
      out.write("                        <div class='input-group'>\n");
      out.write("                            <span class='input-group-addon'>+94</span>\n");
      out.write("                            <input type='text' class='form-control' placeholder=\"Supplier Land Here\" name=\"land\" id=\"land\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <label>Supplier Phone # :- </label>\n");
      out.write("                        <div class='input-group'>\n");
      out.write("                            <span class='input-group-addon'>+94</span>\n");
      out.write("                            <input type='text' class='form-control' placeholder='Supplier Phone Here' name=\"mobile\" id=\"mobile1\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Supplier NIC :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder='Supplier NIC' name=\"nic\" id=\"nic\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <input type=\"submit\" name=\"btn\" class=\"btn btn-info\" name=\"btn\" value=\"Save Supplier\" />\n");
      out.write("                        <input type=\"submit\" name=\"btn\" class=\"btn btn-info\" name=\"btn\" value=\"Update Supplier\" style=\"visibility:hidden;\" id=\"btnupdate\"/>\n");
      out.write("                        <input type=\"submit\" name=\"btn\" class=\"btn btn-info\" name=\"btn\" value=\"Delete Supplier\" style=\"visibility: hidden\" id=\"btndelete\"/>\n");
      out.write("                    </form>\n");
      out.write("                    <h3>Goods Receive Note (GRN) <small>Mange GRN Data Here</small></h3>\n");
      out.write("                    <table class=\"table table-hover\">\n");
      out.write("                        <caption>GRNs So Far</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Date</th>\n");
      out.write("                                <th>Medicine</th>\n");
      out.write("                                <th>Supplier</th>\n");
      out.write("                                <th>Qty Supplied</th>\n");
      out.write("                                <th>Comments/Notes</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");

                                List<POJOs.Grn> grnList = s2.createCriteria(POJOs.Grn.class).list();
                                for (POJOs.Grn g : grnList) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(g.getDate());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(g.getMedicine().getMedicineName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(g.getSupplier().getSupplierFname() + " " + g.getSupplier().getSupplierLname());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(g.getQtySupplied());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(g.getNotes());
      out.write("</td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
                            
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                    <form method=\"POST\" action=\"../handleGRNSave\">\n");
      out.write("\n");
      out.write("                        <input type=\"hidden\" id=\"medicineID\" name=\"medid\"/>\n");
      out.write("                        <input type=\"hidden\" id=\"supplierID\" name=\"supid\" />\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select Medicine Product :- </label>\n");
      out.write("                            <select class=\"form-control\">\n");
      out.write("                                ");

                                    Session s5 = Connection.Connector.getSessionFactory().openSession();
                                    List<POJOs.Medicine> medicineList2 = s5.createCriteria(POJOs.Medicine.class).list();

                                    for (POJOs.Medicine m : medicineList2) {
      out.write("\n");
      out.write("                                <option onclick=\"changeMedicineID(");
      out.print(m.getIdmedicine());
      out.write(")\">");
      out.print(m.getMedicineName());
      out.write("</option>\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select Supplier :- </label>\n");
      out.write("                            <select class=\"form-control\">\n");
      out.write("                                ");

                                    for (POJOs.Supplier su : supplierList) {
      out.write("\n");
      out.write("                                <option onclick=\"changeSupplierID(");
      out.print(su.getIdsupplier());
      out.write(")\">");
      out.print(su.getSupplierOrganizationName());
      out.write("</option>\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        <label>Date :- </label>\n");
      out.write("                        <div class=\"input-group date\" data-provide=\"datepicker\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" name=\"date\">\n");
      out.write("                            <div class=\"input-group-addon\">\n");
      out.write("                                <span class=\"glyphicon glyphicon-th\"></span>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Comments/Notes :- </label>\n");
      out.write("                            <textarea class=\"form-control\" name=\"comments\"></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Medicine Qty :- </label>\n");
      out.write("                            <input type='number' value=\"10\" name=\"qty\" class=\"form-control\" placeholder=\"Medince Quantity Here\" name=\"medicineQty\" required/>\n");
      out.write("                        </div>\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-primary\"><span class=\"glyphicon glyphicon-plus\"></span> Apply GRN</button>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- Medicine Viewer -->\n");
      out.write("            <div class='modal fade' id='medicineViewer' tabindex=\"-1\" role='dialog' aria-labelledby='medicineViewerLabel' aria-hidden='true' onshow=\"getMedicineDetails()\">\n");
      out.write("                <div class='modal-dialog'>\n");
      out.write("                    <div class='modal-content'>\n");
      out.write("                        <div class='modal-header'>\n");
      out.write("                            <button type='button' class=\"close\" data-dismiss='modal' aria-hidden='true'>\n");
      out.write("                                &times;\n");
      out.write("                            </button>\n");
      out.write("                            <h4 class='modal-title' id='medicineViewerLabel'>\n");
      out.write("                                Medicine Details Editor\n");
      out.write("                            </h4>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Medicine Name :- </label>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"medicineName\" />\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Medicine Description :- </label>\n");
      out.write("                                <textarea class=\"form-control\" id=\"medicineDescription\"></textarea>\n");
      out.write("                            </div>\n");
      out.write("                            <div class='input-group'>\n");
      out.write("                                <span class='input-group-addon'>Rs.</span>\n");
      out.write("                                <input type='number' value='1000' class=\"form-control\" placeholder='Medicine Price Here' id=\"medicinePrice\" required/>\n");
      out.write("                                <span class='input-group-addon'>.00</span>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label>Medicine Qty :- </label>\n");
      out.write("                                <input type='number' value=\"10\" class=\"form-control\" placeholder=\"Medince Quantity Here\" id=\"medicineQty\" required/>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button class='btn btn-warning' onclick=\"medicineUpdate()\">Update Details</button>\n");
      out.write("                            <button class='btn btn-danger' onclick=\"medicineDelete()\">Delete Details</button> </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div> \n");
      out.write("\n");
      out.write("            <script>\n");
      out.write("                function getMedicineDetails() {\n");
      out.write("                    $.ajax({\n");
      out.write("                        url: \"../handleMedicineDetailsGet\",\n");
      out.write("                        type: 'POST',\n");
      out.write("                        data: {\n");
      out.write("                            \"id\": medicineID\n");
      out.write("                        },\n");
      out.write("                        success: function (data, textStatus, jqXHR) {\n");
      out.write("                            dataset = data.split('#');\n");
      out.write("                            $('#medicineName').val(dataset[0]);\n");
      out.write("                            $('#medicineDescription').val(dataset[1]);\n");
      out.write("                            $('#medicinePrice').val(dataset[2]);\n");
      out.write("                            $('#medicineQty').val(dataset[3]);\n");
      out.write("\n");
      out.write("                        },\n");
      out.write("                        error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                function medicineUpdate() {\n");
      out.write("                    $.ajax({\n");
      out.write("                        url: \"../handleMedicineUpdate\",\n");
      out.write("                        type: 'POST',\n");
      out.write("                        data: {\n");
      out.write("                            \"id\": medicineID,\n");
      out.write("                            \"name\": $('#medicineName').val(),\n");
      out.write("                            \"desc\": $('#medicineDescription').val(),\n");
      out.write("                            \"price\": $('#medicinePrice').val(),\n");
      out.write("                            \"qty\": $('#medicineQty').val()\n");
      out.write("                        },\n");
      out.write("                        success: function (data, textStatus, jqXHR) {\n");
      out.write("\n");
      out.write("                        },\n");
      out.write("                        error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                function medicineDelete() {\n");
      out.write("                    $.ajax({\n");
      out.write("                        url: \"../handleMedicineDelete\",\n");
      out.write("                        type: 'POST',\n");
      out.write("                        data: {\n");
      out.write("                            \"id\": medicineID\n");
      out.write("                        },\n");
      out.write("                        success: function (data, textStatus, jqXHR) {\n");
      out.write("                            window.location.replace('admin_pharamacy.jsp?res=success');\n");
      out.write("                        },\n");
      out.write("                        error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                        }\n");
      out.write("                    });\n");
      out.write("                }\n");
      out.write("            </script>\n");
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
