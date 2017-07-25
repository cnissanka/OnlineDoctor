<%-- 
    Document   : admin_home
    Created on : Dec 20, 2015, 1:49:10 PM
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
        <title>Admin Panel</title>
        <meta name="viewport" content="width=device-width,
              initial-scale=1.0,
              maximum-scale=1.0,
              user-scalable=no">
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

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
                        <li class='active'><a href="admin_home.jsp">Home and Notifications</a></li>
                        <li><a href="admin_patient.jsp">Patient Details</a></li>
                        <li><a href="admin_doctor.jsp">Doctor Details</a></li>
                        <li><a href="admin_pharamacy.jsp">Pharmacy Details</a></li>
                        <li><a href="admin_knowledge.jsp">Knowledge Base</a></li>
                        <li><a href="admin_medicalservice.jsp">Medical Services</a></li>
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <div class="center-block">
                        <center>
                            <h4>Notification Panel <small>Gives overall statistics</small></h4>
                        </center>
                    </div>
                    <noscript>
                    <div class="alert alert-warning alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">
                            &times;
                        </button>
                        Warning! Javascript is not enabled, for maximum site preformance enable javascript.
                    </div>
                    </noscript>

                    <%
                        Session s = Connection.Connector.getSessionFactory().openSession();

                        int medicalServiceCount = s.createCriteria(POJOs.MedicalServices.class).list().size();
                        int patientCount = s.createCriteria(POJOs.Patient.class).list().size();
                        int doctorCount = s.createCriteria(POJOs.Doctor.class).list().size();
                        int pharamacyCount = s.createCriteria(POJOs.Medicine.class).list().size();
                        int blogCount = s.createCriteria(POJOs.Blog.class).list().size();
                        int userCount = s.createCriteria(POJOs.User.class).list().size();

                        List<POJOs.Medicine> medicineDanger = s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("medicineQty", "10")).list();
                        for (int i = 0; i < 10; i++) {
                            medicineDanger.addAll(s.createCriteria(POJOs.Medicine.class).add(Restrictions.eq("medicineQty", String.valueOf(i))).list());
                        }
                    %>

                    <div class="alert alert-info alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">
                            &times;
                        </button>
                        There are <strong><%=medicalServiceCount%></strong> Medical Services, <strong><%=patientCount%></strong> Patients,
                        <strong><%=doctorCount%></strong> Doctors, <strong><%=pharamacyCount%></strong> Medicine, <strong><%=blogCount%></strong> Blog entries and <strong><%=userCount%></strong> Users in the Database.
                    </div>

                    <%
                        for (POJOs.Medicine med : medicineDanger) {%>

                    <div class="alert alert-danger alert-dismissable">
                        <button type="button" class="close" data-dismiss="alert"
                                aria-hidden="true">
                            &times;
                        </button>
                        Medicine Product <%=med.getMedicineName()%> has low quantity in stock. Please contact <%=med.getSupplier().getSupplierOrganizationName()%> for re-stock. Call <%=med.getSupplier().getSupplierPhone()%> or the address : <%=med.getSupplier().getSupplierAddress()%>
                    </div>
                    <%}
                    %>


                </div>
            </div>
        </div>
    </body>
</html>
