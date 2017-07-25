<%-- 
    Document   : medical_services
    Created on : Dec 18, 2015, 6:01:44 PM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.omg.PortableServer.POA"%>
<%@page import="java.util.List"%>
<%@page import="org.hibernate.Session"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- bootstrap css-->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap-timepicker.min.css"/>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap-timepicker.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="js/bootstrap-datepicker.min.css"/>

        <script>
            function getMedicalService(id, name) {

            }

            function selectMedicicalService(id) {
                window.location.replace('medical_services.jsp?medicine=' + id);
            }

            function getDoctorID() {
                var e = document.getElementById('doctors');
                var iddoctor = e.value;

                document.getElementById('txtDoctorID').value = iddoctor;
            }
        </script>

    </head>
    <body>
        <%
            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            String medicineID = "";
            String medicineName = "";
            if (request.getParameter("medID") != null && request.getParameter("medName") != null) {
                medicineID = request.getParameter("medID");
                medicineName = request.getParameter("medName");
            }
        %>
        <div class="container">
            <ul class="navbar navbar-default navbar-fixed-top" role="navigation">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target="#example-navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a href="" class="navbar-brand">OnlineDoctor!</a>
                </div>

                <div class="collapse navbar-collapse" id="example-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li ><a href="homepage.jsp">Home</a></li>
                        <li><a href="virtual_doc.jsp">Ask Virtual Doctor</a></li>
                        <li><a href="pharmacy_service.jsp">Pharmacy Services</a></li>
                        <li><a href="medical_services.jsp">Medical Services</a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">More Stuff...   <span class="glyphicon glyphicon-triangle-bottom"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="our_blog.jsp">Our Blog</a></li>
                                <li><a href="login_service.jsp">Your Account!</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </ul>
            <!--end of navbar code-->
            <h2 style="padding-top: 50px;">Medical Services <small>Medical Services we offer</small></h2>
            <table class="table table-hover">
                <caption>Medical Services</caption>
                <thead>
                    <tr>
                        <th>Medical Service Name</th>
                        <th>Medical Service Description</th>
                        <th>Medical Service Image</th>
                        <th>Medical Service Price</th>
                    </tr>
                </thead>
                <%
                    Session s = Connection.Connector.getSessionFactory().openSession();
                    List<POJOs.MedicalServices> medicalServiceList = s.createCriteria(POJOs.MedicalServices.class).list();

                    for (POJOs.MedicalServices ms : medicalServiceList) {%>

                <tr>
                    <td><%=ms.getServiceName()%></td>
                    <td><%=ms.getServiceDescription()%></td>
                    <td>
                        <img src="<%=ms.getServiceImage()%>" width="64px" height="64px"/>
                    </td>
                    <td><%=ms.getServicePrice()%></td>
                    <td>
                        <%if (user != null && user.getUsertype().equals("CUSTOMER")) {%>
                        <button class="btn btn-primary" onclick="selectMedicicalService(<%=ms.getIdmedicalServices()%>)">Add to Appointments</button>
                        <%}%>
                    </td>
                </tr>

                <%}
                %>

            </table>
            <%if (user != null && user.getUsertype().equals("CUSTOMER")) {%>

            <%  if (request.getParameter("res") != null) {
                     if (request.getParameter("res").equals("success")) {%>
            <div id="doctor_success" class="alert alert-success" style="visibility: visible">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>Success!</strong> Created an Medical Service Appointment Successfully!
            </div>

            <%} else if (request.getParameter("res").equals("fail")) {%>

            <div id="doctor_fail" class="alert alert-danger" style="visibility: visible">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>Oops!</strong> Something went wrong while saving data!
            </div>
            <%} else if (request.getParameter("res").equals("validation")) {%>
            <div id="doctor_valid" class="alert alert-warning">
                <a href="#" class="close" data-dismiss="alert">&times;</a>
                <strong>Validation Error Occurred</strong> <%=request.getParameter("validation_msg")%>
            </div>
            <%}
                }
            %>
            <form for="form" action="handleAddMedicalService" method="POST">

                <input type="hidden" id="medicalSerivceID" name="medicalServiceID" value="<%=medicineID%>"/>
                <div class="form-group">
                    <label for="medicalname">Medical Service Name :- </label>
                    <% if (request.getParameter("medicine") != null) {%>
                    <%
                        System.out.print("hello");
                        POJOs.MedicalServices med = (POJOs.MedicalServices) s.createCriteria(POJOs.MedicalServices.class).add(Restrictions.eq("idmedicalServices", Integer.parseInt(request.getParameter("medicine")))).uniqueResult();
                        System.out.println(med);
                    %>
                    <input type='hidden' name='patient' value="<%=user.getPatient().getIdpatient()%>" />
                    <input type='hidden' name='medicine' value="<%=request.getParameter("medicine")%>" />
                    <input type='hidden' id='txtDoctorID' name='doctor' />
                    <input type="text" class="form-control" id="medicalname" value='<%=med.getServiceName()%>' disabled/>
                    <%}%>
                </div>
                <div class="form-group">
                    <label for="combodoctor">Select Doctor :- </label>
                    <select class="form-control" onchange="getDoctorID()" id='doctors'>                        <%
                        List<POJOs.Doctor> docList = s.createCriteria(POJOs.Doctor.class).list();
                        for (POJOs.Doctor d : docList) {
                            if (d.getIddoctor() != -1) {%>
                        <option value="<%=d.getIddoctor()%>"><%="Dr. " + d.getDoctorFname() + "  " + d.getDoctorLname()%></option>

                        <%}
                            }
                        %>
                    </select>
                </div>
                <label>Date :- </label>
                <div class="input-group date" data-provide="datepicker">
                    <input type="text" class="form-control" name="date">
                    <div class="input-group-addon">
                        <span class="glyphicon glyphicon-th"></span>
                    </div>
                </div>
                <br/>


                <button type="submit" class="btn btn-primary" style="margin-top: 20px;"><span class="glyphicon glyphicon-pencil"></span> Save My Appointment</button>
            </form>
                    <form action="handleRemoveMedicalServiceAppointment" method="POST">
                <table class="table table-hover" style="margin-top: 40px;">
                    <caption>Your Current Appointments</caption>
                    <thead>
                        <tr>
                            <th>Doctor In charge</th>
                            <th>Appointment Selected</th>
                            <th>Day Allocation</th>                            
                        </tr>
                    </thead>
                    <%
                        List<POJOs.TimeTableServices> appointments = s.createCriteria(POJOs.TimeTableServices.class).list();
                        for (POJOs.TimeTableServices a : appointments) {%>

                    <tr>
                        <td>Dr. <%=a.getDoctor().getDoctorFname() + a.getDoctor().getDoctorLname()%></td>
                        <td><%=a.getMedicalServices().getServiceName()%></td>
                        <td><%=a.getDay()%></td>
                        <td>
                            <button type='submit' name='btn' class="btn btn-danger" value="<%=a.getIdTimeTableServices()%>"><span class="glyphicon glyphicon-remove"></span></button>
                        </td>
                        <td>
                            <a href="invoice_medService.jsp?id=<%=a.getIdTimeTableServices()%>" class="btn btn-primary"><span class="glyphicon glyphicon-print"></span> Print Invoice</a>
                        </td>
                    </tr>

                    <%}
                    %>
                </table>
            </form>
            <%}%>
        </div>
    </body>
</html>
