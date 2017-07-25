<%-- 
    Document   : doctor_account_settings
    Created on : Dec 21, 2015, 9:57:10 AM
    Author     : codeguy
--%>

<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Panel</title>
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <script src="../js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="../js/bootstrap-datepicker.min.css"/>

        <script>
            function removeQualifications(id1,id2) {
             alert(id1);
                $.ajax({
                    url: "../removeDoctorQualification",
                    type: 'POST',
                    data: {
                        "id1": id1
                        
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        alert("failed");
                    }
                });
            }
        </script>
    </head>
    <body>
        <%

            POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
            POJOs.Doctor doctor = null;
            System.out.println((user == null) + " then user is null");
            if (request.getSession() != null) {
                if (user.getUsertype().equals("DOCTOR")) {
                    doctor = user.getDoctor();
                } else {
                    response.sendRedirect("../homepage.jsp");
                }
            } else {
                System.out.print("here!!!");
                response.sendRedirect("../homepage.jsp");
            }
            Session s = Connection.Connector.getSessionFactory().openSession();
        %>
        <div class="jumbotron">
            <div class="center-block">
                <center>
                    <h2>Welcome Dr. <%=user.getDoctor().getDoctorFname() + " " + user.getDoctor().getDoctorLname()%> <small>to your own workspace!</small></h2> 
                </center>
            </div>
            <div class="pull-right">
                <form action="../handlelogout" method="POST">            
                    <button class="btn btn-danger" style="margin-right: 15px;" type="submit">Sign out <span class="glyphicon glyphicon-user"></span></button>
                </form>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-2" style="margin-top: 40px;">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="doctor_home.jsp">Home and Notifications</a></li>
                    <li><a href="doctor_prescriptions.jsp">Prescription Editor</a></li>
                    <li><a href="doctor_medical_appointments.jsp">Your Medical Appointments</a></li>
                    <li><a href="doctor_patient_appointments.jsp">Your Patient Appointments</a></li>
                    <li class="active"><a href="doctor_account_settings.jsp">Account Settings and Preferences</a></li>
                </ul>
            </div>
            <div class="col-lg-10">
                <%  if (request.getParameter("res") != null) {
                         if (request.getParameter("res").equals("success")) {%>
                <div id="doctor_success" class="alert alert-success" style="visibility: visible">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>Success!</strong> Your data was updated successfully!
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
                <div>
                    <form method="POST" action="../handleDoctorDoctorUpdate">
                        <input type="hidden" value="<%=doctor.getIddoctor()%>" name="doctor" />
                        <div class="form-group">
                            <label>Password :- </label>
                            <input type="password" placeholder="Enter Password Here" required class="form-control" value="<%=user.getPassword()%>" name="password"/>
                        </div>
                        <div class="form-group">
                            <label>Confirm Password :- </label>
                            <input type="password" placeholder="Enter Password Here" required class="form-control" name="confirmapssword"/>
                        </div>
                        <div class='form-group'>
                            <label>Doctor First Name :- </label>
                            <input type="text" class="form-control" placeholder="Enter Doctor First Name Here" value="<%=doctor.getDoctorFname()%>" name="firstname"/>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Middle Name :- </label>
                            <input type="text" class="form-control" placeholder="Enter Doctor Middle Name Here" value="<%=doctor.getDoctorMname()%>" name="middlename"/>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Last Name :- </label>
                            <input type="text" class="form-control" placeholder="Enter Doctor Last Name Here" value="<%=doctor.getDoctorLname()%>" name="lastname"/>
                        </div>
                        <div class="form-group">
                            <label>Select Date of Birth :- </label>
                            <div class="input-group bootstrap-datepicker datepicker">
                                <input id="timepicker2" type="text" class="form-control input-small" name="dob">                    
                                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                            </div>
                            <script type="text/javascript">

                                $('#timepicker2').datepicker();
                            </script>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Mobile # :- </label>
                            <div class="input-group">                        
                                <span class="input-group-addon">+94</span>
                                <input type="text" class="form-control" placeholder="Enter Doctor Mobile Here" value="<%=doctor.getDoctorMobile()%>" name="mobile"/>
                            </div>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Land # :- </label>
                            <div class="input-group">

                                <span class="input-group-addon">+94</span>
                                <input type="text" class="form-control" placeholder="Enter Doctor Land Here" value="<%=doctor.getDoctorLand()%>" name="land"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label>Doctor NIC :- </label>
                            <input type="text" class="form-control" placeholder="Enter NIC Here" value="<%=doctor.getDoctorNic()%>" name="nic" />
                        </div>

                        <div class="form-group">
                            <label>Doctor Email Address :- </label>
                            <input type="email" class="form-control" placeholder="Enter Doctor Email Here" value="<%=doctor.getDoctorEmail()%>" name="email"/>
                        </div>

                        <div class='form-group'>
                            <label>Doctor Address :- </label>
                            <textarea class="form-control" placeholder="Enter Doctor Address Here" name="address"><%=doctor.getDoctorAddress()%></textarea>
                        </div>

                        <table class="table table-hover">
                            <caption>Doctor Qualification List</caption>
                            <thead>
                                <tr>
                                    <th>Qualification ID</th>
                                    <th>Qualification Name</th>
                                </tr>
                            </thead>
                            <%
                                Iterator<POJOs.DoctorHasQualifications> qualificationsList = doctor.getDoctorHasQualificationses().iterator();
                                while (qualificationsList.hasNext()) {%>
                            <tr>
                                <%
                                    POJOs.DoctorHasQualifications dhq = qualificationsList.next();
                                    POJOs.DoctorQualifications qual = dhq.getDoctorQualifications();

                                %>
                                <td><%=qual.getIddoctorQualifications()%></td>
                                <td><%=qual.getQualificationName()%></td>
                                <td>
                                    <button class="btn btn-danger" type="button" onclick="removeQualifications(<%=dhq.getIdDoctorHasQualification()%>,<%=user.getDoctor().getIddoctor()%>)"><span class="glyphicon glyphicon-remove"></span> Remove!</button>
                                </td>
                            </tr>
                            <%}
                            %>
                        </table>

                        <div class="form-group">
                            <label>Select Qualifications to add :- </label>
                            <select class="form-control" name="qualification">
                                <%
                                    List<POJOs.DoctorQualifications> qualList = s.createCriteria(POJOs.DoctorQualifications.class).list();
                                    for (POJOs.DoctorQualifications q : qualList) {%>
                                <option><%=q.getQualificationName()%></option>
                                <%}
                                %>
                            </select>                      
                        </div>

                        <div class="form-group">
                            <label>Select New Category :- </label>
                            <select class="form-control" name="category">
                                <%
                                    List<POJOs.DoctorField> fieldList = s.createCriteria(POJOs.DoctorField.class).list();
                                    for (POJOs.DoctorField f : fieldList) {%>
                                <option><%=f.getFieldName()%></option>
                                <%}
                                %>
                            </select>
                        </div>

                        <div class="form-group">
                            <%
                                POJOs.DoctorFieldHasDoctor doctorField = (POJOs.DoctorFieldHasDoctor) doctor.getDoctorFieldHasDoctors().iterator().next();

                            %>
                            <label>Current Category : <%=doctorField.getDoctorField().getFieldName()%></label>
                        </div>
                        <button class="btn btn-info" type="submit"><span class="glyphicon glyphicon-edit"></span> Update My Details</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
