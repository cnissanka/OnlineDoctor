<%-- 
    Document   : admin_patient
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
        <script src="../js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="../js/bootstrap-datepicker.min.css"/>
        <script src="../js/phoneValidation.js"></script>
        <script>
            var searchType = "patientFname";
            function changeSearchType(name) {
                if (name === "Patient First Name") {
                    searchType = "patientFname";
                } else if (name === "Patient Middle Name") {
                    searchType = "patientMname";
                } else if (name === "Patient Last Name") {
                    searchType = "patientLname";
                } else if (name === "Patient NIC") {
                    searchType = "patientNic";
                }

                console.log(searchType);

            }

            function showTodaysAppointment() {
                window.location.replace('admin_patient.jsp?todayappointment=ff');
            }

            function searchData() {

                var search = document.getElementById("txtSearch").value;
                //  window.navigate
                window.location.replace('admin_patient.jsp?search=' + search + "&searchType=" + searchType);
            }

            function fillPatientDetails(id) {
                $.ajax({
                    type: 'POST',
                    url: "../handleLoadPatient",
                    data: {
                        "id": id
                    },
                    success: function (data, textStatus, jqXHR) {
                        var dataset = data.split("#");
                        $('#patientid').val(dataset[11]);
                        $('#pusername').val(dataset[0]);
                        $('#ppassword').val(dataset[1]);
                        $('#pfname').val(dataset[2].split('.')[1]);
                        $('#pmname').val(dataset[3]);
                        $('#plname').val(dataset[4]);
                        $('#salutation').val(dataset[2].split('.')[0]);
                        $('#gender').val(dataset[5]);
                        $('#timepicker2').val(dataset[6]);
                        $('#nic').val(dataset[7]);
                        $('#phone').val(dataset[8]);
                        $('#address').val(dataset[9]);
                        $('#email').val(dataset[10]);

                        $('#btnupdate').css({"visibility": "visible"});
                        $('#btndelete').css({"visibility": "visible"});
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

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
                if (a.getAdminAccess().getIdadminAccess() == 3) {
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
                        <li class="active"><a href="admin_patient.jsp">Patient Details</a></li>
                        <li><a href="admin_doctor.jsp">Doctor Details</a></li>
                        <li><a href="admin_pharamacy.jsp">Pharmacy Details</a></li>
                        <li><a href="admin_knowledge.jsp">Knowledge Base</a></li>
                        <li><a href="admin_medicalservice.jsp">Medical Services</a></li>                        
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <div class='input-group' style="margin-bottom: 15px;">
                        <input type="text" class="form-control" placeholder="Search Patient Records" id="txtSearch"/>
                        <div class="input-group-btn" >
                            <button type="button" class="btn btn-default 
                                    dropdown-toggle" data-toggle="dropdown">
                                Search By
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right">
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Patient First Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Patient Middle Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Patient Last Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Patient NIC</a></li> 
                            </ul>
                        </div><!-- /btn-group -->
                        <span class='input-group-btn'>
                            <button class='btn btn-default' onclick="searchData()">Search!</button>
                        </span>  
                    </div>
                    <table class="table table-hover">
                        <caption>Patient Details</caption>
                        <thead>
                            <tr>
                                <th>First Name</th>
                                <th>Middle Name</th>
                                <th>Last Name</th>
                                <th>Date Of Birth</th>
                                <th>Gender</th>
                                <th>NIC</th>
                                <th>Phone #</th>
                                <th>Email</th>
                                <th>Address</th>
                            </tr>
                        </thead>
                        <%
                            List<POJOs.Patient> listPatient = null;
                            if (request.getParameter("search") == null) {
                                listPatient = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Patient.class).list();
                            } else {
                                String search = request.getParameter("search");
                                String searchType = request.getParameter("searchType");
                                listPatient = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Patient.class).add(Restrictions.like(searchType, search + "%")).list();
                            }

                            for (POJOs.Patient p : listPatient) {
                                if (p.getIdpatient() != -1) {%>

                        <tr>
                            <td><%=p.getPatientFname()%></td>
                            <td><%=p.getPatientMname()%></td>
                            <td><%=p.getPatientLname()%></td>
                            <td><%=p.getPatientDob()%></td>
                            <td><%=p.getPatientGender()%></td>
                            <td><%=p.getPatientNic()%></td>
                            <td><%=p.getPatientPhoneno()%></td>
                            <td><%=p.getPatientEmail()%></td>
                            <td><%=p.getPatientAddress()%></td>
                            <td>
                                <button class="btn btn-warning" onclick="fillPatientDetails(<%=p.getIdpatient()%>)"><span class="glyphicon glyphicon-check"></span> Update this</button>
                            </td>
                        </tr>

                        <%}
                            }
                        %>
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
                    <%} else if (request.getParameter("res").equals("validation")) {%>
                    <div id="doctor_valid" class="alert alert-warning">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <strong>Validation Error Occurred</strong> <%=request.getParameter("validation_msg")%>
                    </div>
                    <%}
                        }
                    %>

                    <form for='form' method="POST" action='../handleCustomerCreate'>
                        <div class="form-group" style="margin-top: 50px;">
                            <input type="hidden" value="" id="patientid" name="patientid" />
                            <label for="pusername">Username :- </label>
                            <input type="text" value="" id="pusername" placeholder="Enter username" class="form-control" name='username' required/>
                        </div>
                        <div class="form-group">
                            <label for="ppassword">Password :- </label>
                            <input type="password" value="" id="ppassword" placeholder="Enter username" class="form-control" name='password' required/>
                        </div>
                        <div class="form-group">
                            <label for="ppassword_confirm">Confirm Password:- </label>
                            <input type="password" value="" id="ppassword_confirm" placeholder="Enter Password Again" class="form-control" name="confirmpassword" required/>
                        </div>
                        <div class="form-group">
                            <label for="psalutate">Select Salutation :- </label>
                            <select class="form-control" name='salutation' id='salutation' required>
                                <option>Mr.</option>
                                <option>Master.</option>
                                <option>Mrs.</option>
                                <option>Miss.</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="pfname">Enter First Name :- </label>
                            <input type="text" value="" id="pfname" placeholder="Enter First Name" class="form-control" name='firstname' required/>
                        </div>
                        <div class="form-group">
                            <label for="pmname">Enter Middle Name :- </label>
                            <input type="text" value="" id="pmname" placeholder="Enter Middle Name" class="form-control" name='middlename' required/>
                        </div>
                        <div class="form-group">
                            <label for="plname">Enter Last Name :- </label>
                            <input type="text" value="" id="plname" placeholder="Enter Last Name" class="form-control" name='lastname' required/>
                        </div>
                        <div class="form-group">
                            <label>Select Gender :- </label>
                            <select class="form-control" name='gender' id='gender' required>
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Select Date of Birth :- </label>
                            <div class="input-group bootstrap-datepicker datepicker">
                                <input id="timepicker2" type="text" class="form-control input-small" name="dob" disabled="true">                    
                                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                            </div>
                            <script type="text/javascript">

                                $('#timepicker2').datepicker();
                            </script>
                        </div>
                        <div class="form-group">
                            <label>Enter your NIC :- </label>
                            <input type="text" value="" class="form-control" name='nic' id='nic' placeholder="Enter National Identity No, If any" onblur="validateNic(this)" required/>
                        </div>
                        <div class="form-group">
                            <label>Enter your Phone # :- </label>
                            <div class="input-group">
                                <span class="input-group-addon">+94</span>
                                <input type="text" value="" class="form-control input-small" id='phone' name='phone' placeholder="Enter Phone Number" onkeypress="return validatePhone(this,event)" required/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="paddress">Enter your Address :- </label>
                            <textarea class="form-control" name='address' id='address' required></textarea>
                        </div>
                        <div class="form-group">
                            <label for="pemail">Enter your email address</label>
                            <input type="email" class="form-control" name='email' id='email' required/>
                        </div>
                        <input value="Save Patient" type="submit" name="btnsub" class="btn btn-info">
                        <input value="Update Patient" type="submit" id="btnupdate" name="btnsub" class="btn btn-info" style="visibility: hidden">
                        <input value="Delete Patient" type="submit" id="btndelete" name="btnsub" class="btn btn-info" style="visibility: hidden"> 
                    </form>

                    <div class='input-group' style="margin-top: 60px;">                    
                        <span class='input-group-btn'>
                            <button class='btn btn-primary' onclick="showTodaysAppointment()">Today's Appointments</button>
                        </span>                    
                    </div>
                    <form method="POST" action="../handlePatientAppointmentDelete">
                        <table class='table table-hover'>
                            <caption>Patient Appointments</caption>
                            <thead>
                                <tr>
                                    <th>Patient Name</th>
                                    <th>Appointment Date</th>                            
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    List<POJOs.Appointment> appointmentList = s.createCriteria(POJOs.Appointment.class).list();
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
                                        appointmentList = s2.createCriteria(POJOs.Appointment.class).add(Restrictions.eq("appointmentDate", fullDate)).list();
                                        s2.close();
                                    }

                                    for (POJOs.Appointment cAppointment : appointmentList) {%>
                                <tr>
                                    <td><%=cAppointment.getPatient().getPatientFname() + " " + cAppointment.getPatient().getPatientLname()%></td>
                                    <td><%=cAppointment.getAppointmentDate()%></td>                                
                                    <td>
                                        <input type="hidden" name="isadmin" value="isadmin" />
                                        <button class='btn btn-danger' name="id" value="<%=cAppointment.getIdappointment()%>">Remove</button>
                                    </td>
                                </tr>
                                <%}
                                %>
                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
