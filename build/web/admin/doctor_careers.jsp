<%-- 
    Document   : doctor_careers
    Created on : Feb 4, 2016, 9:16:02 PM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
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
                } else if (!user.getUsertype().equals("ADMINISTRATOR")) {
                    response.sendRedirect("../login_service.jsp");
                }
            }
            //security mechanism applied for the blog.           
            boolean isAccessRighed = false;
            Session s = Connection.Connector.getSessionFactory().openSession();
            List<POJOs.AdminAccessRight> accessRights = s.createCriteria(POJOs.AdminAccessRight.class).add(Restrictions.eq("administrator", user.getAdministrator())).list();

            for (POJOs.AdminAccessRight a : accessRights) {
                if (a.getAdminAccess().getIdadminAccess() == 2) {
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
                        <li class="active"><a href="doctor_careers.jsp">Doctor Careers</a></li>
                        <li><a href="admin_pharamacy.jsp">Pharmacy Details</a></li>
                        <li><a href="admin_knowledge.jsp">Knowledge Base</a></li>
                        <li><a href="admin_medicalservice.jsp">Medical Services</a></li>
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <table class="table table-hover">
                        <caption>Doctor's who are requesting to get registered</caption>
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Middle Name</th>
                                <th>Last Name</th>
                                <th>Date of Birth</th>
                                <th>Doctor NIC</th>
                                <th>Doctor Land</th>
                                <th>Doctor Mobile</th>
                                <th>Doctor Email</th>
                                <th>Selected Package</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List <POJOs.DoctorTemp> tempDoctorList = s.createCriteria(POJOs.DoctorTemp.class).list();
                                for (POJOs.DoctorTemp temp : tempDoctorList){%>
                                <tr>
                                    <td><%=temp.getDoctorFname()%></td>
                                    <td><%=temp.getDoctorMname()%></td>
                                    <td><%=temp.getDoctorLname()%></td>
                                    <td><%=temp.getDoctorDob()%></td>
                                    <td><%=temp.getDoctorNic()%></td>
                                    <td><%=temp.getDoctorLand()%></td>
                                    <td><%=temp.getDoctorMobile()%></td>
                                    <td><%=temp.getDoctorEmail()%></td>
                                    <td><%=temp.getDoctorClasses().getClassName()%></td>
                                    <td>
                                        <a download href="../<%=temp.getDoctorCv()%>" class="btn btn-primary">Download CV</a>
                                    </td>
                                    <td>
                                        <button class="btn btn-success"><span class="glyphicon glyphicon-plus"></span></button>
                                    </td>
                                    <td>
                                        <button class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span></button>
                                    </td>
                                </tr>
                            <%}
                            %>
                        </tbody>
                    </table>

                    <form action="../handleDeletePackage" method="POST">
                    <table class='table table-hover'>
                        <caption>Package List for Doctor Careers</caption>
                        <thead>
                            <tr>
                                <th>Package Name</th>
                                <th>Package Description</th>
                                <th>Package Image</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<POJOs.DoctorClasses> classesList = s.createCriteria(POJOs.DoctorClasses.class).list();
                                for (POJOs.DoctorClasses c : classesList) {%>
                            <tr>
                                <td><%=c.getClassName()%></td>
                                <td><%=c.getClassDescr()%></td>
                                <td>
                                    <img src='../<%=c.getClassImage()%>' width="64px" height="64px" />
                                </td>
                                <td>
                                    <button class="btn btn-danger" name="class" value="<%=c.getIddoctorClasses()%>">Remove</button>
                                </td>
                            </tr>
                            <%}
                            %>
                        </tbody>
                    </table>
                    </form>
                    <!--Save packages-->
                    <form action="../handlePackageSave" method="POST" enctype="multipart/form-data">
                        <div class='form-group'>
                            <label>Doctor Package Name : </label>
                            <input type='text' class='form-control' name='pname' />
                        </div>
                        <div class='form-group'>
                            <label>Package Description : </label>
                            <textarea class='form-control' name='pdescription'></textarea>                             
                        </div>
                        <div class='form-group'>
                            <label>Package Image : </label>
                            <input type='file' class='btn-file' name='pimage'/>
                        </div>
                        <button type="submit" class="btn btn-primary">Save Package</button>
                    </form>
                </div>
            </div>
    </body>
</html>
