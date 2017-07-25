<%-- 
    Document   : admin_blog
    Created on : Dec 20, 2015, 1:49:10 PM
    Author     : codeguy
--%>

<%@page import="java.util.Iterator"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Panel</title>
        <meta name="viewport" content="width=device-width,
              initial-scale=1.0,
              maximum-scale=1.0,
              user-scalable=no">
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

        <script>
            function getBlogDetails(blogid) {

                $.ajax({
                    url: "../handlegetblog",
                    type: 'POST',
                    data: {
                        "blogid": blogid
                    },
                    success: function (data, textStatus, jqXHR) {
                        $('#title').val(data.split("#")[0]);
                        $('#content').val(data.split("#")[1]);
                        $('#summary').val(data.split("#")[2]);
                        $('#btnupdate').css({"visibility": "visible"});
                        $('#btndelete').css({"visibility": "visible"});
                        $('#selectedblog_id').val(data.split("#")[3]);
                        $('#selectedblog').val(data.split("#")[0]);
                        $('#idblog').val(data.split("#")[3]);
                        updateImageTable($('#selectedblog_id').val());
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });

            }

            function deleteImage(id) {

                $.ajax({
                    type: 'POST',
                    url: "../handleDeleteBlogImage",
                    data: {
                        "id": id
                    },
                    success: function (data, textStatus, jqXHR) {
                        window.location.replace('admin_blog.jsp');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });

            }

            function updateImageTable(id) {
                $.ajax({
                    type: 'POST',
                    url: "../handlegetblogimages",
                    data: {
                        "id": id
                    },
                    success: function (data, textStatus, jqXHR) {
                        //table code : $('#myTable tr:last').after('<tr>...</tr><tr>...</tr>');
                        imagearr = data.split("#");
                        //   alert(imagearr);
                        $("#imagetable").find("tr:gt(0)").remove();
                        for (var i = 0; i < imagearr.length - 1; i++) {
                            imd = imagearr[i].split(";");
                            $('#imagetable tr:last').after("<tr><td>" + (i + 1) + "</td><td><img style='width:64px;height:64px;' src='../" + imd[1] + "'/></td><td><button class='btn btn-danger' onclick='deleteImage(" + imd[0] + ")'>Delete</button></td></tr>");
                        }

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function search() {
                window.location.replace('admin_blog.jsp?search=' + document.getElementById("search").value);
            }
        </script>

        <style>
            .btn-file {
                position: relative;
                overflow: hidden;
            }
            .btn-file input[type=file] {
                position: absolute;
                top: 0;
                right: 0;
                min-width: 100%;
                min-height: 100%;
                font-size: 100px;
                text-align: right;
                filter: alpha(opacity=0);
                opacity: 0;
                outline: none;
                background: white;
                cursor: inherit;
                display: block;
            }
        </style>
    </head>
    <body>
        <%
            POJOs.User user = null;
            if (request.getSession() == null) {
                response.sendRedirect("../login_service.jsp");
            } else {
                user = (POJOs.User) request.getSession().getAttribute("user");
                if (user == null) {
                    response.sendRedirect("../login_service.jsp");
                } else if (user.getUsertype().equals("ADMINISTRATOR") == false) {
                    response.sendRedirect("../login_service.jsp");
                }
            }
            //security mechanism applied for the blog.           
            boolean isAccessRighed = false;
            Session s = Connection.Connector.getSessionFactory().openSession();
            List<POJOs.AdminAccessRight> accessRights = s.createCriteria(POJOs.AdminAccessRight.class).add(Restrictions.eq("administrator", user.getAdministrator())).list();

            for (POJOs.AdminAccessRight a : accessRights) {
                if (a.getAdminAccess().getIdadminAccess() == 7) {
                    System.out.println("I AM AWESOME!!!");
                    isAccessRighed = true;
                    break;
                }
            }

            if (!isAccessRighed) {
                response.sendRedirect("admin_home.jsp");
            }
        %>
        <div class="container-fluid">
            <div class="row">
                <div class="jumbotron">
                    <div class="center-block">
                        <center>
                            <h2>Welcome <%=user.getAdministrator().getAdminFname() + " " + user.getAdministrator().getAdminLname()%>! <small>to the Control Panel of OnlineDoctor!</small></h2> 
                        </center>
                    </div>
                    <div class="pull-right">
                        <form action="../handlelogout" method="POST">            
                            <button class="btn btn-danger" style="margin-right: 15px;" type="submit">Sign out <span class="glyphicon glyphicon-user"></span></button>
                        </form>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 40px;">
                <div class="col-lg-2 " >
                    <ul class="nav nav-pills nav-stacked">
                        <li><a href="admin_home.jsp">Home and Notifications</a></li>
                        <li><a href="admin_patient.jsp">Patient Details</a></li>
                        <li><a href="admin_doctor.jsp">Doctor Details</a></li>
                        <li><a href="admin_pharamacy.jsp">Pharmacy Details</a></li>
                        <li><a href="admin_knowledge.jsp">Knowledge Base</a></li>
                        <li><a href="admin_medicalservice.jsp">Medical Services</a></li>
                        <li class="active"><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <div class='input-group'>
                        <input type='text' placeholder="Search Blog" class="form-control" id="search"/>
                        <span class='input-group-btn'>
                            <butto class='btn btn-default' onclick="search()">Search!</butto>
                        </span>
                    </div>
                    <table class="table table-hover">
                        <caption>Blog Details </caption>
                        <thead>
                            <tr>
                                <th>Blog Title</th>
                                <th>Blog Creation Date</th>
                                <th>Blog Views Count</th>
                                <th>Blog Summary</th>
                            </tr>
                        </thead>
                        <%
                            List<POJOs.Blog> blogList = null;
                            if (request.getParameter("search") == null) {
                                blogList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Blog.class).list();
                            } else {
                                //search code here
                                blogList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Blog.class).add(Restrictions.like("blogTitle", request.getParameter("search") + "%")).list();
                            }

                            for (POJOs.Blog b : blogList) {%>

                        <tr>
                            <td><%=b.getBlogTitle()%></td>
                            <td><%=b.getBlogCreation()%></td>
                            <td><%=b.getBlogViews()%></td>
                            <td><%=b.getBlogSummary()%></td>
                            <td>
                                <button class="btn btn-warning" onclick="getBlogDetails(<%=b.getIdblog()%>)"><span class="glyphicon glyphicon-edit"></span> Update Details</button>
                            </td>
                        </tr>

                        <%}
                        %>
                    </table>
                    <form method="POST" action="../handleblogadd">
                        <div class='form-group'>
                            <input type="hidden" id="idblog" name="idblog" />
                            <label>Blog Title :- </label>
                            <input type='text' class='form-control' placeholder='Blog Title Here' name="title" id="title" required/>
                        </div>
                        <div class='form-group'>
                            <label>Blog Content :- </label>
                            <textarea class="form-control" placeholder='Blog Context Here' name="content" id="content" required></textarea>
                        </div>
                        <div class='form-group'>
                            <label>Blog Summary :- </label>
                            <textarea class='form-control' placeholder='Blog Summary Here' name="summary" id="summary"></textarea>
                        </div> 
                        <input type="submit" class="btn btn-info" id="btnsave" name="btn" value="Save Blog">
                        <input type="submit" class="btn btn-info" id="btnupdate" name="btn" style="visibility: hidden" value="Update Blog">
                        <input type="submit" class="btn btn-info" id="btndelete" name="btn" style="visibility: hidden" value="Delete Blog">
                    </form>

                    <form style="margin-top: 50px;" method="POST" action="../handleblogimages" enctype="multipart/form-data">
                        <h4>Blog Images</h4>
                        <div class='form-group'>
                            <label>Blog Selected :- </label>
                            <input type="hidden" name="blogid" id="selectedblog_id" value=""/>
                            <input type='text' class='form-control' disabled placeholder='Blog Selected Here - After selecting from table..' id="selectedblog"/>

                        </div>
                        <div class='form-group'>
                            <label>Blog Image :- </label>
                            <span class="btn btn-primary btn-file" id="pre" > 
                                Browse for Blog Image (png, jpg)<input type="file" name="txtfile" >
                            </span>
                        </div>
                        <button class='btn btn-info' type="submit">Add Image</button>
                    </form>

                    <table class="table table-hover" id="imagetable">
                        <caption>Images in Selected Blog</caption>
                        <thead>
                            <tr>
                                <th>Image No</th>
                                <th>Image Preview</th>
                            </tr>
                        </thead>

                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
