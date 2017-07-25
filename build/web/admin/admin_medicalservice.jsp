<%-- 
    Document   : admin_medicalservice
    Created on : Dec 20, 2015, 1:49:10 PM
    Author     : codeguy
--%>

<%@page import="java.util.Date"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.hibernate.criterion.Restrictions"%>
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

        <script>
            var serviceID = 0;
            function todayAppointment() {
                window.location.replace('admin_medicalservice.jsp?todayappointment=23');
            }
            function updateId(serviceId) {

                serviceID = serviceId;

            }

            function getServiceDetails() {
                $.ajax({
                    type: 'POST',
                    url: "../handleGetServicedDetails",
                    data: {
                        "id": serviceID
                    },
                    success: function (data, textStatus, jqXHR) {
                        var dataset = data.split("#");
                        $('#txtServiceName').val(dataset[0]);
                        $('#txtServiceDescription').val(dataset[1]);
                        $('#txtServicePrice').val(dataset[2]);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function updateService() {
                $.ajax({
                    type: 'POST',
                    url: "../handleUpdateMedicalService",
                    data: {
                        "id": serviceID,
                        "name": $('#txtServiceName').val(),
                        "description": $('#txtServiceDescription').val(),
                        "price": $('#txtServicePrice').val()
                    },
                    success: function (data, textStatus, jqXHR) {
                        alert('Updated Successfully - change me!');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert('Error occurred - change me!');
                    }
                });
            }

            function search() {
                window.location.replace('admin_medicalservice.jsp?search=' + $('#search').val());
            }

            function deleteService() {
                $.ajax({
                    type: 'POST',
                    url: "../handleDeleteMedicalService",
                    data: {
                        "id": serviceID
                    },
                    success: function (data, textStatus, jqXHR) {
                        alert('Deleted successfully');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert('error occurred!');
                    }
                });
            }
        </script>
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
                if (a.getAdminAccess().getIdadminAccess() == 6) {
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
                        <li class='active'><a href="admin_medicalservice.jsp">Medical Services</a></li>
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <div class='input-group'>
                        <input type='text' placeholder="Search Medical Services" class='form-control' id="search"/>
                        <span class='input-group-btn'>
                            <button class='btn btn-default' onclick="search()">Search!</button>
                        </span>
                    </div>
                    <table class="table table-hover">
                        <caption>Medical Services Details</caption>
                        <thead>
                            <tr>
                                <th>Service Name</th>
                                <th>Service Description</th>
                                <th>Service Image</th>
                                <th>Service Price</th>
                            </tr>
                        </thead>
                        <%
                            List<POJOs.MedicalServices> medicalServiceList = null;
                            if (request.getParameter("search") != null) {
                                medicalServiceList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.MedicalServices.class).add(Restrictions.like("serviceName", request.getParameter("search") + "%")).list();
                            } else {
                                medicalServiceList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.MedicalServices.class).list();
                            }
                        %>

                        <%for (POJOs.MedicalServices mService : medicalServiceList) {%>
                        <tr>
                            <td><%=mService.getServiceName()%></td>
                            <td><%=mService.getServiceDescription()%></td>
                            <td>
                                <img src="../<%=mService.getServiceImage()%>" width="64px" height="64px" />
                            </td>
                            <td>Rs.<%=mService.getServicePrice()%>0</td>
                            <td>
                                <button class="btn btn-warning" data-toggle="modal" data-target="#medicalViewer" onclick="updateId(<%=mService.getIdmedicalServices()%>)">Edit Details</button>
                            </td>
                        </tr>
                        <%}%>
                    </table>

                    <%  if (request.getParameter("res") != null) {
                            if (request.getParameter("res").equals("success")) {%>
                    <div id="doctor_success" class="alert alert-success" style="visibility: visible">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Success!</strong> Doctor's data was updated successfully!
                    </div>

                    <%} else if (request.getParameter("res").equals("fail")) {%>

                    <div id="doctor_fail" class="alert alert-danger" style="visibility: visible">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Oops!</strong> Something went wrong while saving data!
                    </div>
                    <%}
                        }%>

                    <form method="POST" action="../handleMedicineService" enctype="multipart/form-data">
                        <div class='form-group'>
                            <label>Medical Service Name :- </label>
                            <input type='text' class='form-control' placeholder="Medical Service Name" name="servicename" required/>
                        </div>
                        <div class='form-group'>
                            <label>Medical Service Description :- </label>
                            <textarea placeholder="Medical Service Description" class='form-control' name="description" required></textarea>
                        </div>
                        <div class='form-group'>
                            <label>Medical Service Image :- </label>
                            <span class="btn btn-primary btn-file" id="pre" > 
                                Browse for Medical Service Image (png, jpg)<input type="file" name="txtfile">
                            </span>
                        </div>
                        <label>Medical Service Price :- </label>
                        <div class='input-group' style="margin-bottom: 50px;">
                            <span class='input-group-addon'>Rs.</span>
                            <input type='number' value="1000" placeholder="Medical Service Price Here" class="form-control" name="price" required/>
                            <span class='input-group-addon'>.00</span>
                        </div>
                        <input type="submit" class="btn btn-info" name="btn" value="Save Service" />

                    </form>

                    <div class="input-group" style="margin-top: 50px;">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" onclick="todayAppointment()">Today's Appointments!</button>
                        </span>
                    </div>
                       <form action="../handleRemoveMedicalServiceAppointment" method="POST">
                    <table class="table table-hover">
                        <caption>Medical Services History <span class='glyphicon glyphicon-time'></span></caption>
                        <thead>
                            <tr>
                                <th>Medical Service #</th>
                                <th>Medical Service Name</th>
                                <th>Patient Name</th>
                                <th>Patient Mobile</th>
                                <th>Patient Email Address</th>                          
                                <th>Date</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<POJOs.TimeTableServices> doctorMedicalService = s.createCriteria(POJOs.TimeTableServices.class).list();
                                int counter = 1;

                                if (request.getParameter("todayappointment") != null) {
                                    System.out.println("here");
                                    String datex = new Date().toString();

                                    System.out.println(datex);

                                    String dateArray[] = datex.split(" ");

                                    String month = "";

                                    if (dateArray[1].equals("Jan")) {
                                        month = "01";
                                    }

                                    if (dateArray[1].equals("Feb")) {
                                        month = "02";
                                    }

                                    if (dateArray[1].equals("Mar")) {
                                        month = "03";
                                    }

                                    if (dateArray[1].equals("Apr")) {
                                        month = "04";
                                    }
                                    if (dateArray[1].equals("May")) {
                                        month = "05";
                                    }
                                    if (dateArray[1].equals("Jun")) {
                                        month = "06";
                                    }
                                    if (dateArray[1].equals("Jul")) {
                                        month = "07";
                                    }
                                    if (dateArray[1].equals("Aug")) {
                                        month = "08";
                                    }
                                    if (dateArray[1].equals("Sep")) {
                                        month = "09";
                                    }
                                    if (dateArray[1].equals("Oct")) {
                                        month = "10";
                                    }
                                    if (dateArray[1].equals("Nov")) {
                                        month = "11";
                                    }
                                    if (dateArray[1].equals("Dec")) {
                                        month = "12";
                                    }

                                    //System.out.println(month);
                                    String day = dateArray[2];

                                    String year = dateArray[5];

                                    String fullDate = month + "/" + day + "/" + year;
                                    Session s2 = Connection.Connector.getSessionFactory().openSession();
                                    doctorMedicalService = s2.createCriteria(POJOs.TimeTableServices.class).add(Restrictions.eq("day", fullDate)).list();

                                }

                                for (POJOs.TimeTableServices record : doctorMedicalService) {%>
                            <tr>
                                <td><%=counter%></td>
                                <td><%=record.getMedicalServices().getServiceName()%></td>
                                <td><%=record.getPatient().getPatientFname() + " " + record.getPatient().getPatientLname()%></td>
                                <td><%=record.getPatient().getPatientPhoneno()%></td>
                                <td><%=record.getPatient().getPatientEmail()%></td>                             
                                <td><%=record.getDay()%>
                                    <input type="hidden" name="isadmin" value="isadmin"/>
                                </td>
                                <td>
                                   <button type='submit' name='btn' class="btn btn-danger" value="<%=record.getIdTimeTableServices()%>"><span class="glyphicon glyphicon-remove"></span></button>
                                </td>
                            </tr>                        
                            <%}
                            %>
                        </tbody>
                    </table>
                        
                        </form>

                    <!--Modal for qualification viewer -->
                    <div class='modal fade' id='medicalViewer' tabindex="-1" role='dialog' aria-labelledby='medicalViewerLabel' aria-hidden='true' onshow="getServiceDetails()">
                        <div class='modal-dialog'>
                            <div class='modal-content'>
                                <div class='modal-header'>
                                    <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                        &times;
                                    </button>
                                    <h4 class='modal-title' id='medicalViewerLabel'>
                                        Medical Data Editor
                                    </h4>
                                </div>
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label>Service Name :-</label>
                                        <input type="text" id="txtServiceName" class="form-control" placeholder="Medical Service Name Here"/>
                                    </div>
                                    <div class="form-group">
                                        <label>Service Description :- </label>
                                        <textarea class="form-control" id="txtServiceDescription" placeholder="Medical Service Description Here"></textarea>
                                    </div>
                                    <label>Service Price :-</label>
                                    <div class="input-group">
                                        <span class='input-group-addon'>Rs.</span>
                                        <input type='number' value="1000" placeholder="Medical Service Price Here" class="form-control" id="txtServicePrice"/>
                                        <span class='input-group-addon'>.00</span>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-info" onclick="updateService()">Update Service</button>
                                    <button type="button" class="btn btn-info" onclick="deleteService()">Delete Service</button>
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End Modal -->
                </div>
            </div>
        </div>
    </body>
</html>
