package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.List;
import org.hibernate.Session;

public final class virtual_005fdoc_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <title>Online Doctor - Virtual Doctor Service</title>\n");
      out.write("        <!-- bootstrap css-->\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap.min.css\"/>\n");
      out.write("\n");
      out.write("        <script src=\"js/jquery-2.1.4.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap.min.js\"></script>\n");
      out.write("        <script src=\"bootstrap-3.3.6-dist/js/bootstrap-timepicker.min.js\"></script>\n");
      out.write("        <script src=\"js/bootstrap-datepicker.js\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"js/bootstrap-datepicker.min.css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"bootstrap-3.3.6-dist/css/bootstrap-timepicker.min.css\"/>\n");
      out.write("        <script>\n");
      out.write("            var array = [];\n");
      out.write("            var symptomList = \"\";\n");
      out.write("            var counter = 0;\n");
      out.write("            var subcount = 0;\n");
      out.write("\n");
      out.write("            function addtotable() {\n");
      out.write("                var table = document.getElementById(\"tb_symptoms\");\n");
      out.write("                var inTable = false;\n");
      out.write("                var data = \"\";\n");
      out.write("                console.log(array);\n");
      out.write("                for (var i = 0; i < array.length; i++) {\n");
      out.write("                    if (array[i] === document.getElementById('symptom').value) {\n");
      out.write("                        inTable = true;\n");
      out.write("                        break;\n");
      out.write("                    }\n");
      out.write("                }\n");
      out.write("\n");
      out.write("                console.log(inTable);\n");
      out.write("                if (!inTable) {\n");
      out.write("                    data = \"<tr><td>\" + (counter + 1) + \"</td><td>\" + document.getElementById('symptom').value + \"</td></tr>\";\n");
      out.write("                    table.innerHTML += data;\n");
      out.write("                    array [counter] = document.getElementById('symptom').value;\n");
      out.write("                    symptomList += document.getElementById('symptom').value + \"#\";\n");
      out.write("                    counter += 1;\n");
      out.write("                } else {\n");
      out.write("                    data = \"\";\n");
      out.write("                }\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function removefromtable(trid) {}\n");
      out.write("\n");
      out.write("            function getSymptom(id) {\n");
      out.write("                //alert('hello');\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"handleSymptomGet\",\n");
      out.write("                    type: 'POST',\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": id\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        //alert(data);\n");
      out.write("                        if (counter === 0) {\n");
      out.write("                            counter += 1;\n");
      out.write("                        }\n");
      out.write("                        document.getElementById('tb_symptoms').innerHTML += '<tr><td>' + counter + '</td><td>' + data + '</td></tr>';\n");
      out.write("                        counter += 1;\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        alert('erro you are not awesome');\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function findDisease() {\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"handleGetLikelyDisease\",\n");
      out.write("                    type: 'POST',\n");
      out.write("                    data: {\n");
      out.write("                        \"symtomlist\": symptomList\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        var result = data.split(\"#\");\n");
      out.write("                        console.log(data);\n");
      out.write("                        var table = document.getElementById('tb_res');\n");
      out.write("                        table.innerHTML = \"<thead><tr><th>Disease Name</th><th>Likely Chance of infection</th></tr></thead>\";\n");
      out.write("                        for (var i = 0; i < (result.length - 1); i++) {\n");
      out.write("                            var precentage = (parseInt(result[i].split(';')[1]) / (result.length - 1)) * 100;\n");
      out.write("                            table.innerHTML += \"<tr><td>\" + result[i].split(';')[0] + \"</td><td>\" + precentage + \"%</td></tr>\";\n");
      out.write("                        }\n");
      out.write("\n");
      out.write("                        $('#createAppointments').css({'visibility': 'visible'});\n");
      out.write("\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function showAppointmentForm() {\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function updateDoctorID(id){\n");
      out.write("                alert('hello');\n");
      out.write("                document.getElementById('doctorid').value = selectBox.options[selectBox.selectedIndex].value;\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

            POJOs.User uCustomer = (POJOs.User) request.getSession().getAttribute("user");
            boolean isCustomer = false;
            if (uCustomer != null) {
                isCustomer = uCustomer.getUsertype().equals("CUSTOMER");
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
      out.write("            <h2 style=\"padding-top: 50px;\">Virtual Doctor Service <small>Enter your symptoms, we give you what disease you might have.</small></h2>\n");
      out.write("            <div  class=\"form-inline\">\n");
      out.write("                <div class=\"form-group form-group-lg\" style=\"margin-top: 50px;\">\n");
      out.write("                    <label>Select Symptom from list :- </label>\n");
      out.write("                    <select class=\"form-control\" id=\"symptom\">\n");
      out.write("                        ");

                            Session s = Connection.Connector.getSessionFactory().openSession();
                            List<POJOs.Symptom> symList = s.createCriteria(POJOs.Symptom.class).list();
                            for (POJOs.Symptom sy : symList) {
      out.write("\n");
      out.write("                        <option>");
      out.print(sy.getSymptomName());
      out.write("</option>\n");
      out.write("                        ");
}
                        
      out.write("\n");
      out.write("                    </select>\n");
      out.write("                    <button type=\"button\" class=\"btn btn-primary\" onclick=\"addtotable()\"><span class=\"glyphicon glyphicon-check\"></span> Select</button> \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            <table class=\"table table-hover\" id=\"tb_symptoms\">\n");
      out.write("                <caption>Your Symptom List</caption>\n");
      out.write("                <thead>\n");
      out.write("                    <tr>\n");
      out.write("                        <th>Symptom #</th>\n");
      out.write("                        <th>Symptom Name</th>\n");
      out.write("                    </tr>\n");
      out.write("                </thead>\n");
      out.write("            </table>\n");
      out.write("\n");
      out.write("            <button class=\"btn btn-success\" onclick=\"findDisease()\">Find my disease <span class=\"glyphicon glyphicon-search\"></span></button>        \n");
      out.write("\n");
      out.write("            <div class=\"well-lg\">\n");
      out.write("                <h3>Your Results <small>Warning! These are computer-generated results, based on symptoms and not 100% accurate.</small></h3>\n");
      out.write("                <table class=\"table table-hover\" id=\"tb_res\">\n");
      out.write("                    <thead>\n");
      out.write("                        <tr>\n");
      out.write("                            <th>Disease Name</th>\n");
      out.write("                            <th>Likely Chance of infection</th>\n");
      out.write("                        </tr>\n");
      out.write("                    </thead>\n");
      out.write("                </table>\n");
      out.write("                <button class=\"btn btn-info\" id=\"btnA\" onclick=\"showAppointmentForm()\" style=\"visibility: hidden\">Create A Appointment <span class=\"glyphicon glyphicon-star\"></span></button>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("            ");

                if (isCustomer && uCustomer != null) {
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"row\">\n");
      out.write("                <div class=\"col-lg-6\" id=\"createAppointments\" style=\"visibility: hidden\">\n");
      out.write("                    <div class=\"well\">\n");
      out.write("                        <h4>Create Appointment</h4>\n");
      out.write("                        <form action=\"handleAppointmentCreate\" method=\"POST\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <input type=\"hidden\" id=\"doctorid\" name=\"doctor\"/>\n");
      out.write("                                <input type=\"hidden\" name=\"customer\" id=\"customer\" value=\"");
      out.print(uCustomer.getPatient().getIdpatient());
      out.write("\" />\n");
      out.write("                                <label>Select Doctor :- </label>\n");
      out.write("                                <select name=\"doctor\" class=\"form-control\" onchange=\"updateDoctorID('a')\" id=\"selectBox\">\n");
      out.write("                                    ");
                                        
                                        List <POJOs.Doctor> doctorList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Doctor.class).list();
                                        for (POJOs.Doctor d : doctorList){
                                            if (d.getIddoctor() != -1){
                                        
      out.write("\n");
      out.write("                                        <option value=\"");
      out.print(d.getIddoctor());
      out.write("\">Dr. ");
      out.print(d.getDoctorFname() +" " + d.getDoctorLname());
      out.write("</option>\n");
      out.write("                                    ");
}}
                                    
      out.write("\n");
      out.write("                                </select>\n");
      out.write("                            </div>\n");
      out.write("                            <label>Select Date :- </label>\n");
      out.write("                            <div class=\"input-group date\" data-provide=\"datepicker\" style=\"margin-bottom: 40px;\">\n");
      out.write("                                <input type=\"text\" class=\"form-control\" name=\"date\">\n");
      out.write("                                <div class=\"input-group-addon\">\n");
      out.write("                                    <span class=\"glyphicon glyphicon-th\"></span>\n");
      out.write("                                </div>\n");
      out.write("                            </div>                            \n");
      out.write("                            <button type=\"submit\" class=\"btn btn-success\">Create New Appointment <span class=\"glyphicon glyphicon-ok-circle\"></span></button>\n");
      out.write("                        </form>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div class=\"col-lg-6\">\n");
      out.write("                    <div class=\"well\">\n");
      out.write("                        <h4>Your Appointments</h4>\n");
      out.write("                        <table class=\"table table-hover\">\n");
      out.write("                            <thead>\n");
      out.write("                                <tr>\n");
      out.write("                                    <th>Doctor's Name</th>\n");
      out.write("                                    <th>Appointment Date</th>\n");
      out.write("                                </tr>\n");
      out.write("                            </thead>\n");
      out.write("                        </table>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
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
