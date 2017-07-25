package org.apache.jsp.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import org.hibernate.Session;
import java.util.List;

public final class admin_005fblog_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("        <script>\n");
      out.write("            function getBlogDetails(blogid) {\n");
      out.write("\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"../handlegetblog\",\n");
      out.write("                    type: 'POST',\n");
      out.write("                    data: {\n");
      out.write("                        \"blogid\": blogid\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        $('#title').val(data.split(\"#\")[0]);\n");
      out.write("                        $('#content').val(data.split(\"#\")[1]);\n");
      out.write("                        $('#summary').val(data.split(\"#\")[2]);\n");
      out.write("                        $('#btnupdate').css({\"visibility\": \"visible\"});\n");
      out.write("                        $('#btndelete').css({\"visibility\": \"visible\"});\n");
      out.write("                        $('#selectedblog_id').val(data.split(\"#\")[3]);\n");
      out.write("                        $('#selectedblog').val(data.split(\"#\")[0]);\n");
      out.write("\n");
      out.write("                        updateImageTable($('#selectedblog_id').val());\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function deleteImage(id){\n");
      out.write("                \n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handleDeleteBlogImage\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\":id\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        window.location.replace('admin_blog.jsp');\n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("                        \n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("                \n");
      out.write("            }\n");
      out.write("\n");
      out.write("            function updateImageTable(id) {\n");
      out.write("                $.ajax({\n");
      out.write("                    type: 'POST',\n");
      out.write("                    url: \"../handlegetblogimages\",\n");
      out.write("                    data: {\n");
      out.write("                        \"id\": id\n");
      out.write("                    },\n");
      out.write("                    success: function (data, textStatus, jqXHR) {\n");
      out.write("                        //table code : $('#myTable tr:last').after('<tr>...</tr><tr>...</tr>');\n");
      out.write("                        imagearr = data.split(\"#\");\n");
      out.write("                     //   alert(imagearr);\n");
      out.write("                        for (var i = 0; i < imagearr.length - 1; i++){\n");
      out.write("                            imd = imagearr[i].split(\";\");\n");
      out.write("                            $('#imagetable tr:last').after(\"<tr><td>\"+(i + 1)+\"</td><td><img style='width:64px;height:64px;' src='../\"+imd[1]+\"'/></td><td><button class='btn btn-danger' onclick='deleteImage(\"+imd[0]+\")'>Delete</button></td></tr>\");\n");
      out.write("                        }\n");
      out.write("                        \n");
      out.write("                    },\n");
      out.write("                    error: function (jqXHR, textStatus, errorThrown) {\n");
      out.write("\n");
      out.write("                    }\n");
      out.write("                });\n");
      out.write("            }\n");
      out.write("        </script>\n");
      out.write("\n");
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
      out.write("                        <li><a href=\"admin_medicalservice.jsp\">Medical Services</a></li>\n");
      out.write("                        <li class=\"active\"><a href='admin_blog.jsp'>Blog Details</a></li>\n");
      out.write("                        <li><a href=\"admin_misc.jsp\">Misc and User Account Mgt</a></li>                       \n");
      out.write("                    </ul>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-lg-10\">\n");
      out.write("                    <div class='input-group'>\n");
      out.write("                        <input type='text' placeholder=\"Search Blog\" class=\"form-control\" />\n");
      out.write("                        <span class='input-group-btn'>\n");
      out.write("                            <butto class='btn btn-default'>Search!</butto>\n");
      out.write("                        </span>\n");
      out.write("                    </div>\n");
      out.write("                    <table class=\"table table-hover\">\n");
      out.write("                        <caption>Blog Details </caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Blog Title</th>\n");
      out.write("                                <th>Blog Creation Date</th>\n");
      out.write("                                <th>Blog Views Count</th>\n");
      out.write("                                <th>Blog Summary</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        ");

                            List<POJOs.Blog> blogList = null;
                            if (request.getParameter("search") == null) {
                                blogList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Blog.class).list();
                            } else {
                                //search code here
                            }

                            for (POJOs.Blog b : blogList) {
      out.write("\n");
      out.write("\n");
      out.write("                        <tr>\n");
      out.write("                            <td>");
      out.print(b.getBlogTitle());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(b.getBlogCreation());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(b.getBlogViews());
      out.write("</td>\n");
      out.write("                            <td>");
      out.print(b.getBlogSummary());
      out.write("</td>\n");
      out.write("                            <td>\n");
      out.write("                                <button class=\"btn btn-warning\" onclick=\"getBlogDetails(");
      out.print(b.getIdblog());
      out.write(")\"><span class=\"glyphicon glyphicon-edit\"></span> Update Details</button>\n");
      out.write("                            </td>\n");
      out.write("                        </tr>\n");
      out.write("\n");
      out.write("                        ");
}
                        
      out.write("\n");
      out.write("                    </table>\n");
      out.write("                    <form method=\"POST\" action=\"../handleblogadd\">\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Blog Title :- </label>\n");
      out.write("                            <input type='text' class='form-control' placeholder='Blog Title Here' name=\"title\" id=\"title\" required/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Blog Content :- </label>\n");
      out.write("                            <textarea class=\"form-control\" placeholder='Blog Context Here' name=\"content\" id=\"content\" required></textarea>\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Blog Summary :- </label>\n");
      out.write("                            <textarea class='form-control' placeholder='Blog Summary Here' name=\"summary\" id=\"summary\"></textarea>\n");
      out.write("                        </div> \n");
      out.write("                        <button type=\"submit\" class=\"btn btn-info\" id=\"btnsave\" name=\"btn\">Save Blog</button>\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-info\" id=\"btnupdate\" name=\"btn\" style=\"visibility: hidden\">Update Blog</button>\n");
      out.write("                        <button type=\"submit\" class=\"btn btn-info\" id=\"btndelete\" name=\"btn\" style=\"visibility: hidden\">Delete Blog</button>\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    <form style=\"margin-top: 50px;\" method=\"POST\" action=\"../handleblogimages\" enctype=\"multipart/form-data\">\n");
      out.write("                        <h4>Blog Images</h4>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Blog Selected :- </label>\n");
      out.write("                            <input type=\"hidden\" name=\"blogid\" id=\"selectedblog_id\" value=\"\"/>\n");
      out.write("                            <input type='text' class='form-control' disabled placeholder='Blog Selected Here - After selecting from table..' id=\"selectedblog\"/>\n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("                        <div class='form-group'>\n");
      out.write("                            <label>Blog Image :- </label>\n");
      out.write("                            <span class=\"btn btn-primary btn-file\" id=\"pre\" > \n");
      out.write("                                Browse for Blog Image (png, jpg)<input type=\"file\" name=\"txtfile\" >\n");
      out.write("                            </span>\n");
      out.write("                        </div>\n");
      out.write("                        <button class='btn btn-info' type=\"submit\">Add Image</button>\n");
      out.write("                    </form>\n");
      out.write("\n");
      out.write("                    <table class=\"table table-hover\" id=\"imagetable\">\n");
      out.write("                        <caption>Images in Selected Blog</caption>\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th>Image No</th>\n");
      out.write("                                <th>Image Preview</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("\n");
      out.write("                    </table>\n");
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
