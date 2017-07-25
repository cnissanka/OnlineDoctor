package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import org.hibernate.Session;
import javax.swing.JOptionPane;
import org.hibernate.criterion.Restrictions;
import java.util.List;

public final class admin_005fknowledge_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Admin Panel</title>\n");
      out.write("        <link rel=\"stylesheet\" href=\"../bootstrap-3.3.6-dist/css/bootstrap.min.css\" />\n");
      out.write("        <script src=\"../js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"../bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            var searchType = \"symptomName\";\n");
      out.write("            var diseaseName = 0;\n");
      out.write("            var symptomName = 0;\n");
      out.write("\n");
      out.write("            function changeSearchType(changeVal) {\n");
      out.write("                if (changeVal === \"Symptom Name\") {\n");
      out.write("                    searchType = \"symptomName\";\n");
      out.write("                } else if (changeVal === \"Disease Name\") {\n");
      out.write("                    searchType = \"dieseaseName\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function changeDisease(d) {\n");
      out.write("                //  alert(d);\n");
      out.write("                diseaseName = d;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function changeSymptom(s) {\n");
      out.write("                //   alert(s);\n");
      out.write("                symptomName = s;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function searchRule() {\n");
      out.write("                window.location.replace('admin_knowledge.jsp?searchType=' + searchType + \"&search=\" + $('#search').val());\n");
      out.write("            }\n");
      out.write("\n");
      out.write("\n");
      out.write("            function updateSymptoms() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handlegetSymptoms\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": symptomName\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        //   alert(data);\n");
      out.write("                        var dataset = data.split(\"#\");\n");
      out.write("\n");
      out.write("                        $('#symtomName').val(dataset[0]);\n");
      out.write("                        $('#symtomDesc').val(dataset[1]);\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        //  alert(error);\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function updateDisease() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handlegetDisease\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": diseaseName\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        var dataset = data.split('#');\n");
      out.write("                        $('#diseasename').val(dataset[0]);\n");
      out.write("                        $('#disease_desc').val(dataset[1]);\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        //   alert(errorThrown + \" \" + textStatus + \" \" + jqXHR.toString());\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function deleteRules(a) {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleRuleDelete\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": a\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container-fluid\">\n");
      out.write("            <div class=row>\n");
      out.write("                <div class=\"jumbotron\">\n");
      out.write("                    <div class=\"center-block\">\n");
      out.write("                        <center>\n");
      out.write("                            <h2>Welcome Admin! <small>to the Control Panel of OnlineDoctor!</small></h2>\n");
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
      out.write("                        <li class=\"active\"><a href=\"admin_knowledge.jsp\">Knowledge Base</a></li>\n");
      out.write("                        <li><a href=\"admin_medicalservice.jsp\">Medical Services</a></li>\n");
      out.write("                        <li><a href='admin_blog.jsp'>Blog Details</a></li>\n");
      out.write("                        <li><a href=\"admin_misc.jsp\">Misc and User Account Mgt</a></li>                       \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-lg-10\">\n");
      out.write("                    <div class='input-group'>\n");
      out.write("                        <input type='text' placeholder=\"Search Medical Services\" class='form-control' id=\"search\"/>\n");
      out.write("                        <div class=\"input-group-btn\" >\n");
      out.write("                            <button type=\"button\" class=\"btn btn-default \n");
      out.write("                                    dropdown-toggle\" data-toggle=\"dropdown\">\n");
      out.write("                                Search By\n");
      out.write("                                <span class=\"caret\"></span>\n");
      out.write("                            </button>\n");
      out.write("                            <ul class=\"dropdown-menu pull-right\">\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\" href=\"#\">Symptom Name</a></li>\n");
      out.write("                                <li><a onclick=\"changeSearchType(this.innerHTML)\" href=\"#\">Disease Name</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </div><!-- /btn-group -->\n");
      out.write("                        <span class='input-group-btn'>\n");
      out.write("                            <button class='btn btn-default' onclick=\"searchRule()\">Search!</button>\n");
      out.write("                        </span>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("                    <table class=\"table table-hover\">\n");
      out.write("                        <caption>Knowledge Base Information List</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Symptom Name</th>\n");
      out.write("                                <th>Disease Name</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        ");


                            List<POJOs.Symptom> symList2 = null;
                            List<POJOs.Diesease> disList2 = null;
                            Session session1 = Connection.Connector.getSessionFactory().openSession();
                            List<POJOs.DieseaseHasSymptom> ruleList = session1.createCriteria(POJOs.DieseaseHasSymptom.class).list();
                            if (request.getParameter("search") != null && request.getParameter("searchType") != null) {

                                if (request.getParameter("searchType").equals("symptomName")) {
                                    System.out.println("hekki");
                                    symList2 = session1.createCriteria(POJOs.Symptom.class).add(Restrictions.like("symptomName", request.getParameter("search") + "%")).list();
                                    System.out.println("fed");
                                } else {
                                    System.out.println("mem");
                                    disList2 = session1.createCriteria(POJOs.Diesease.class).add(Restrictions.like("dieseaseName", request.getParameter("search") + "%")).list();
                                    System.out.println("fed");
                                }

                                if (symList2 != null) {
                                    System.out.println("symptom here");
                                    for (POJOs.Symptom s : symList2) {
                                        System.out.println("goinggg");
                                        List<POJOs.DieseaseHasSymptom> temp = session1.createCriteria(POJOs.DieseaseHasSymptom.class).add(Restrictions.eq("symptom", s)).list();
                                        System.out.println("mmm >> " + temp.size());
                                        ruleList.clear();
                                        ruleList.addAll(temp);

                                    }
                                } else if (disList2 != null) {
                                    System.out.println("diseae here");
                                    for (POJOs.Diesease d : disList2) {
                                        List<POJOs.DieseaseHasSymptom> temp = session1.createCriteria(POJOs.DieseaseHasSymptom.class).add(Restrictions.eq("diesease", d)).list();
                                        ruleList.clear();
                                        ruleList.addAll(temp);
                                    }
                                }
                                System.out.println(ruleList);
                            } else {
                                ruleList = session1.createCriteria(POJOs.DieseaseHasSymptom.class).list();

                            }

                            for (POJOs.DieseaseHasSymptom rule : ruleList) {
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print(rule.getSymptom().getSymptomName());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(rule.getDiesease().getDieseaseName());
      out.write("</td>\n");
      out.write("                            <td>\n");
      out.write("                                <button class=\"btn btn-danger\" onclick=\"deleteRules(");
      out.print(rule.getIdRule());
      out.write(")\"><span class=\"glyphicon glyphicon-remove\"></span> Delete Rule</button>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>                        \n");
      out.write("                        ");
}
                        
      out.write("\n");
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
      out.write("\n");
      out.write("                    <table class=\"table table-hover\">\n");
      out.write("                        <caption>Symptom Details</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Symptom Name</th>\n");
      out.write("                                <th>Symptom Description</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");

                                List<POJOs.Symptom> symList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Symptom.class).list();
                                for (POJOs.Symptom s : symList) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(s.getSymptomName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(s.getSymptomDescription());
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    <button class=\"btn btn-warning\" data-target='#symptomViewer' data-toggle='modal' onclick=\"changeSymptom(");
      out.print(s.getIdsymptom());
      out.write(")\"><span class=\"glyphicon glyphicon-wrench\" ></span> Update Details</button>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
                            
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("\n");
      out.write("                    <form method=\"POST\" action=\"../handleSymptomAdd\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Symptom Name :- </label>\n");
      out.write("                            <input type=\"text\" required name=\"sym_name\" class=\"form-control\" />\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Symptom Description :- </label>\n");
      out.write("                            <textarea class=\"form-control\" name=\"sym_desc\" required></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <button class=\"btn btn-info\"><span class=\"glyphicon glyphicon-plus\"></span> Save Details</button>\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    <table class=\"table table-hover\">\n");
      out.write("                        <caption>Disease Details</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Disease Name</th>\n");
      out.write("                                <th>Disease Description</th>\n");
      out.write("                            </tr>                         \n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");

                                List<POJOs.Diesease> diseaseList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Diesease.class).list();
                                for (POJOs.Diesease d : diseaseList) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td>");
      out.print(d.getDieseaseName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(d.getDieseaseDescription());
      out.write("</td>\n");
      out.write("                                <td>\n");
      out.write("                                    <button class=\"btn btn-warning\" data-target='#diseaseViewer' data-toggle='modal' onclick=\"changeDisease(");
      out.print(d.getIddiesease());
      out.write(")\"><span class=\"glyphicon glyphicon-wrench\"></span> Update Details</button>\n");
      out.write("                                </td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
                            
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("\n");
      out.write("                    <form style=\"margin-top:15px;\" method=\"POST\" action=\"../handleDiseaseAdd\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Disease Name :- </label>\n");
      out.write("                            <input type=\"text\" required name=\"dis_name\" class=\"form-control\" />\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Disease Description :- </label>\n");
      out.write("                            <textarea class=\"form-control\" name=\"dis_desc\" class=\"form-control\" required></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <button class=\"btn btn-info\"><span class=\"glyphicon glyphicon-plus\"></span> Save Details</button>\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    <h3>Create A Rule! <small>Specify the Symptom name and the Disease name...</small></h3>\n");
      out.write("\n");
      out.write("                    <form method=\"POST\" action=\"../handleAddRule\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select Symptom From List :- </label>\n");
      out.write("                            <select class=\"form-control\" name=\"symptom\">\n");
      out.write("                                ");

                                    for (POJOs.Symptom s : symList) {
      out.write("\n");
      out.write("                                <option>");
      out.print(s.getSymptomName());
      out.write("</option>\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <label>Select Disease From List :- </label>\n");
      out.write("                            <select class=\"form-control\" name=\"disease\">\n");
      out.write("                                ");

                                    for (POJOs.Diesease d : diseaseList) {
      out.write("\n");
      out.write("                                <option>");
      out.print(d.getDieseaseName());
      out.write("</option>\n");
      out.write("                                ");
}
                                
      out.write("\n");
      out.write("                            </select>\n");
      out.write("                        </div>\n");
      out.write("                        <button class=\"btn btn-info\"><span class=\"glyphicon glyphicon-save\"></span> Add Rule!</button>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <!-- Symptom Viewer -->\n");
      out.write("            <div class='modal fade' id='symptomViewer' tabindex=\"-1\" role='dialog' aria-labelledby='symptomViewerLabel' aria-hidden='true' onshow=\"updateSymptoms()\">\n");
      out.write("                <div class='modal-dialog'>\n");
      out.write("                    <div class='modal-content'>\n");
      out.write("                        <div class='modal-header'>\n");
      out.write("                            <button type='button' class=\"close\" data-dismiss='modal' aria-hidden='true'>\n");
      out.write("                                &times;\n");
      out.write("                            </button>\n");
      out.write("                            <h4 class='modal-title' id='symptomViewerLabel'>\n");
      out.write("                                Symptom Editor\n");
      out.write("                            </h4>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("                            <div class='form-group'>\n");
      out.write("                                <label>Symptom Name :- </label>\n");
      out.write("                                <input type='text' class='form-control' id='symtomName' />\n");
      out.write("                            </div>\n");
      out.write("                            <div class='form-group'>\n");
      out.write("                                <label>Symptom Description :- </label>\n");
      out.write("                                <textarea class='form-control' id='symtomDesc'></textarea>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button class='btn btn-warning' onclick=\"symptomUpdate()\">Update Details</button>\n");
      out.write("                            <button class='btn btn-danger' onclick=\"symptomDelete()\">Delete Details</button>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("            <!-- Disease Viewer -->\n");
      out.write("            <div class='modal fade' id='diseaseViewer' tabindex=\"-1\" role='dialog' aria-labelledby='diseaseViewer' aria-hidden='true' onshow=\"updateDisease()\">\n");
      out.write("                <div class='modal-dialog'>\n");
      out.write("                    <div class='modal-content'>\n");
      out.write("                        <div class='modal-header'>\n");
      out.write("                            <button type='button' class=\"close\" data-dismiss='modal' aria-hidden='true'>\n");
      out.write("                                &times;\n");
      out.write("                            </button>\n");
      out.write("                            <h4 class='modal-title' id='symptomViewerLabel'>\n");
      out.write("                                Disease Editor\n");
      out.write("                            </h4>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-body\">\n");
      out.write("                            <div class='form-group'>\n");
      out.write("                                <label>Disease Name :- </label>\n");
      out.write("                                <input type='text' class='form-control' id='diseasename' />\n");
      out.write("                            </div>\n");
      out.write("                            <div class='form-group'>\n");
      out.write("                                <label>Disease Description :- </label>\n");
      out.write("                                <textarea class='form-control' id='disease_desc' ></textarea>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"modal-footer\">\n");
      out.write("                            <button class='btn btn-warning' onclick=\"diseaseUpdate()\">Update Details</button>\n");
      out.write("                            <button class='btn btn-danger' onclick=\"diseaseDelete()\">Delete Details</button> </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <script>\n");
      out.write("            function diseaseUpdate() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleDiseaseUpdate\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": diseaseName,\n");
      out.write("                        \"name\": $('#diseasename').val(),\n");
      out.write("                        \"desc\": $('#disease_desc').val()\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function diseaseDelete() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleDiseaseDelete\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": diseaseName\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function symptomUpdate() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleSymptomUpdate\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": symptomName,\n");
      out.write("                        \"name\": $('#symtomName').val(),\n");
      out.write("                        \"desc\": $('#symtomDesc').val()\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function symptomDelete() {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleSymptomDelete\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": symptomName\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </script>\n");
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
