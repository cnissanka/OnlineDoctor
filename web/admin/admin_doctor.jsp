<%-- 
    Document   : admin_doctor
    Created on : Dec 20, 2015, 1:49:10 PM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.Iterator"%>
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
            var array = [];
            var doctorID = 0;
            var searchType = "doctorFname";
            function validateField() {

            }

            function validateQualification() {

            }

            function saveQualfication() {
                //saves the qualifcation data in the database
                var qualification = $('#txtQualification').val();
                var qualificationdesc = $('#txtQualificationDesc').val();
                $.ajax({
                    type: 'POST',
                    url: "../handleQualificationSave",
                    data: {
                        "name": qualification,
                        "description": qualificationdesc
                    },
                    success: function (data, textStatus, jqXHR) {
                        $('#qualification_success').css({"visibility": "visible"});
                        setTimeout(function () {
                            $('#qualification_success').css({"visibility": "hidden"});
                        }, 3000);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $('#qualification_fail').css({"visibility": "visible"});
                        setTimeout(function () {
                            $('#qualification_fail').css({"visibility": "hidden"});
                        }, 3000);
                    }
                });
            }

            function changeID(iddoc) {
                doctorID = iddoc;
            }

            function changeSearchType(searchType1) {
                if (searchType1 === "Doctor First Name") {
                    searchType = "doctorFname";
                } else if (searchType1 === "Doctor Middle Name") {
                    searchType = "doctorMname";
                } else if (searchType1 === "Doctor Last Name") {
                    searchType = "doctorLname";
                } else if (searchType1 === "Doctor NIC") {
                    searchType = "doctorNic";
                }
            }

            function getQualification() {
                $.ajax({
                    type: 'POST',
                    url: "../handleGetDoctorQualifications",
                    data: {
                        "id": doctorID
                    },
                    success: function (data, textStatus, jqXHR) {
                        var dataset = data.split("#");
                        var myList = document.getElementById('qualificationList');

                        var htmlCode = "";
                        for (var i = 0; i < dataset.length - 1; i++) {
                            htmlCode += "<li>" + dataset[i] + "</li>";
                        }

                        myList.innerHTML = htmlCode;
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function saveField() {
                var fieldname = $('#txtfieldname').val();
                var fielddesc = $('#txtfielddesc').val();

                $.ajax({
                    type: 'POST',
                    url: "../handleCategoryAdd",
                    data: {
                        "fieldname": fieldname,
                        "fielddesc": fielddesc
                    },
                    success: function (data, textStatus, jqXHR) {
                        $('#field_success').css({"visibility": "visible"});
                        setTimeout(function () {
                            $('#field_success').css({"visibility": "hidden"});
                        }, 3000);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        $('#field_fail').css({"visibility": "visible"});
                        setTimeout(function () {
                            $('#field_fail').css({"visibility": "hidden"});
                        }, 3000);
                    }
                });
            }

            function refreshQualfication() {
                //refreshes the qualification table to the user
                $.ajax({
                    type: 'POST',
                    url: "../handleGetQualifications",
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }

            function refreshField() {

            }
            var x;
            function selectQualfication(qualID) {
                //selects qualification from table and adds to a array, which is added to a hidden element, sent to the servlet
                var isThereinArray = false;
                for (var i = 0; i < array.length; i++) {
                    if (array[i] === qualID) {
                        isThereinArray = true;
                        break;
                    }
                }

                if (!isThereinArray) {
                    array [array.length] = qualID;
                }
                var arrayText = "";
                for (var i = 0; i < array.length; i++) {
                    arrayText += array[i] + "#";
                }
                x = arrayText;
                document.getElementById("txtQualificationHolder").value = arrayText;
                console.log(arrayText);
            }

            function getDoctorDetails(docID) {

                $.ajax({
                });
            }

            function SearchDoctor() {
                window.location.replace('admin_doctor.jsp?search=' + $('#search').val() + "&searchType=" + searchType);
            }

            function getDoctorDetails(doctorid) {

                $.ajax({
                    url: "../handleGetDoctorDetails",
                    type: 'POST',
                    data: {
                        "id": doctorid
                    },
                    success: function (data, textStatus, jqXHR) {
                        dataset = data.split("#");

                        $('#username').val(dataset[0]);
                        $('#password').val(dataset[1]);
                        $('#fname').val(dataset[2]);
                        $('#mname').val(dataset[3]);
                        $('#lname').val(dataset[4]);
                        $('#timepicker2').val(dataset[5]);
                        $('#gender').val(dataset[6]);
                        $('#mobile').val(dataset[7]);
                        $('#land').val(dataset[8]);
                        $('#avgPay').val(dataset[9]);
                        $('#appCharge').val(dataset[10]);
                        $('#address').val(dataset[11]);
                        $('#field').val(dataset[12]);
                        $('#nic').val(dataset[13]);
                        $('#email').val(dataset[14]);
                        $('#txtDoctorID').val(dataset[15])

                        $('#btn_update').css({"visibility": "visible"});
                        $('#btn_delete').css({"visibility": "visible"});


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
                        <li class="active"><a href="admin_doctor.jsp">Doctor Details</a></li>
                        <li><a href="admin_pharamacy.jsp">Pharmacy Details</a></li>
                        <li><a href="admin_knowledge.jsp">Knowledge Base</a></li>
                        <li><a href="admin_medicalservice.jsp">Medical Services</a></li>
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-10">
                    <div class='input-group' style="margin-bottom: 15px;">
                        <input type='text' placeholder='Search Doctor Details' class="form-control" id="search"/>
                        <div class="input-group-btn" >
                            <button type="button" class="btn btn-default 
                                    dropdown-toggle" data-toggle="dropdown">
                                Search By
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right">
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Doctor First Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Doctor Middle Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Doctor Last Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)" href="#">Doctor NIC</a></li> 
                            </ul>
                        </div><!-- /btn-group -->
                        <span class='input-group-btn'>
                            <button class='btn btn-default' onclick="SearchDoctor()">Search!</button>
                        </span>
                    </div>

                    <table class="table table-hover">
                        <caption>Doctor Details Displayed Here</caption>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Date of Birth</th>
                                <th>Gender</th>
                                <th>Mobile #</th>
                                <th>Land #</th>
                                <th>NIC</th>
                                <th>Avg. Pay </th>
                                <th>Appointment. Pay </th>
                                <th>Qualification List</th>
                                <th>Category</th>
                            </tr>
                        </thead>
                        <%
                            List<POJOs.Doctor> doctorList = null;

                            if (request.getParameter("search") != null) {
                                //search code here
                                doctorList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Doctor.class).add(Restrictions.like(request.getParameter("searchType"), request.getParameter("search") + "%")).list();
                            } else {
                                doctorList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Doctor.class).list();
                            }
                            for (POJOs.Doctor doc : doctorList) {
                                if (doc.getIddoctor() != -1) {%>
                        <%
                            POJOs.DoctorFieldHasDoctor field = (POJOs.DoctorFieldHasDoctor) doc.getDoctorFieldHasDoctors().iterator().next();
                        %>
                        <tr>
                            <td>Dr. <%=doc.getDoctorFname() + " " + doc.getDoctorLname()%></td>
                            <td><%=doc.getDoctorDob()%></td>
                            <td><%=doc.getDoctorGender()%></td>
                            <td><%=doc.getDoctorMobile()%></td>
                            <td><%=doc.getDoctorLand()%></td>
                            <td><%=doc.getDoctorNic()%></td>
                            <td>Rs.<%=doc.getDoctorAveragePay()%>0</td>
                            <td>Rs.<%=doc.getDoctorAppointmentCharge()%>0</td>
                            <td><button class="btn btn-primary" data-toggle="modal" data-target="#qualificationViewer" onclick="changeID(<%=doc.getIddoctor()%>)">See Qualifications</button></td>
                            <td>
                                <%=field.getDoctorField().getFieldName()%>
                            </td>
                            <td>
                                <button class="btn btn-warning" onclick="getDoctorDetails(<%=doc.getIddoctor()%>)">Edit Doctor Details</button>
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
                    <form method="POST" action="../handleAddDoctor">
                        <input type='hidden' value='' id='txtQualificationHolder' name='qualification' />
                        <input type="hidden" value="" id="txtDoctorID" name="id" />
                        <div class="form-group" >
                            <label>Username :- </label>
                            <input type="text" class="form-control" placeholder="Enter Doctor Username" name='username' id="username" required/>
                        </div>
                        <div class='form-group'>
                            <label>Password :- </label>
                            <input type="password" class="form-control" placeholder="Enter Doctor Password" name='password' id="password" required/>
                        </div>
                        <div class='form-group'>
                            <label>Confirm Password :- </label>
                            <input type="password" class="form-control" placeholder="Confirm Doctor Password" required name="confirmpassword"/>
                        </div>
                        <div class='form-group'>
                            <label>Doctor First Name :- </label>
                            <input type="text" class="form-control" placeholder="Enter Doctor First Name" name='fname1' required id="fname"/>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Middle Name :- </label>
                            <input type="text" class="form-control" placeholder="Enter Doctor Middle Name" name='mname1' required id="mname"/>
                        </div> 
                        <div class='form-group'>
                            <label>Doctor Last Name :- </label>
                            <input type="text" class="form-control" placeholder="Enter Doctor Last Name" name='lname1' required id="lname"/>
                        </div>
                        <div class="form-group">
                            <label>Select Date of Birth :- </label>
                            <div class="input-group bootstrap-datepicker datepicker">
                                <input id="timepicker2" type="text" class="form-control input-small" name='dob' required >                    
                                <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                            </div>
                            <script type="text/javascript">
                                $('#timepicker2').datepicker();
                            </script>
                        </div>
                        <div class="form-group">
                            <label>Select Gender :- </label>
                            <select class="form-control" name='gender' id='gender' required id="gender">
                                <option>Male</option>
                                <option>Female</option>
                            </select>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Mobile # :- </label>
                            <div class="input-group">                        
                                <span class="input-group-addon">+94</span>
                                <input type="text" class="form-control" placeholder="Enter Doctor Mobile #" name='mobile1' required id="mobile" onkeypress="return validatePhone(this,event)"/>
                            </div>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Land # :- </label>
                            <div class="input-group">

                                <span class="input-group-addon">+94</span>
                                <input type="text" class="form-control" placeholder="Enter Doctor Land #" name='land1' required id="land" onkeypress="return validatePhone(this,event)"/>
                            </div>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Avg. Pay :- </label>
                            <div class='input-group'>
                                <span class='input-group-addon'>Rs.</span>
                                <input type='number' class='form-control' placeholder="Enter Doctor Average Pay" name='avgPay' required id="avgPay"/>
                                <span class='input-group-addon'>.00</span>
                            </div>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Appointment Charge :- </label>
                            <div class='input-group'>
                                <span class='input-group-addon'>Rs.</span>
                                <input type='number' class='form-control' placeholder="Enter Doctor Average Pay" name='appCharge' required id="appCharge"/>
                                <span class='input-group-addon'>.00</span>
                            </div>
                        </div>
                        <div class='form-group'>
                            <label>Doctor Address :- </label>
                            <textarea class="form-control" placeholder="Enter Doctor Address" required name="address1" id="address"></textarea>
                        </div>

                        <table class="table table-hover">
                            <caption>Select Qualifications for Doctor</caption>
                            <thead>
                                <tr>
                                    <th>Qualification #</th>
                                    <th>Qualification Name</th>
                                    <th>Qualification Description</th>
                                </tr>
                            </thead>
                            <%
                                List<POJOs.DoctorQualifications> listQu = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.DoctorQualifications.class).list();
                                int counter = 1;
                                for (POJOs.DoctorQualifications q : listQu) {%>
                            <tr>
                                <td><%=counter%></td>
                                <td><%=q.getQualificationName()%></td>
                                <td><%=q.getQualificationDescription()%></td>
                                <td>
                                    <a class='btn btn-warning' onclick="selectQualfication(<%=q.getIddoctorQualifications()%>)"><span class='glyphicon glyphicon-check'></span>Select Qualification</a>
                                </td>
                                <%
                                    counter += 1;
                                %>
                            </tr>
                            <%}
                            %>
                        </table>



                        <div class='form-group'>
                            <label>Select Doctor's Field :- </label>
                            <select class='form-control' id='' required name="field" id="field">
                                <%
                                    List<POJOs.DoctorField> listField = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.DoctorField.class).list();
                                    for (POJOs.DoctorField f : listField) {%>
                                <option><%=f.getFieldName()%></option>                                    
                                <%}
                                %>
                            </select>
                        </div>

                        <div class='form-group'>
                            <label>Doctor NIC :- </label>
                            <input type='text' value='' placeholder="Enter Doctor NIC Here" class="form-control" required name="nic" id="nic"/>
                        </div>

                        <div class='form-group'>
                            <label>Doctor Email Address :- </label>
                            <input type='text' value="" placeholder="Enter Doctor Email Here" class='form-control' required name='email'id="email"/>
                        </div>


                        <br />
                        <input type="submit" value="Save Doctor" name="btn"  class="btn btn-info" />
                        <input type="submit" value="Update Doctor" name="btn" class="btn btn-info" style="visibility: hidden" id="btn_update"/>
                        <input type="submit" value="Delete Doctor" name="btn" class="btn btn-info" style="visibility: hidden" id="btn_delete"/>
                        <button class="btn btn-default" data-toggle='modal' data-target='#qualificationModel'><span class="glyphicon glyphicon-plus"></span> Create Qualification</button>
                        <button class="btn btn-default" data-toggle='modal' data-target='#fieldModal'><span class="glyphicon glyphicon-plus"></span> Create Doctor Field</button>
                    </form>
                </div>

                <!--Modal for qualifications -->
                <div class='modal fade' id='qualificationModel' tabindex="-1" role='dialog' aria-labelledby='qualificationModelLabel' aria-hidden='true'>
                    <div class='modal-dialog'>
                        <div class='modal-content'>
                            <div class='modal-header'>
                                <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                    &times;
                                </button>
                                <h4 class='modal-title' id='qualificationModelLabel'>
                                    Qualification Create
                                </h4>
                            </div>
                            <div class="modal-body">
                                <div id="qualification_success" class="alert alert-success" style="visibility: hidden">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <strong>Success!</strong> Doctor Qualification data was saved successfully!
                                </div>

                                <div id="qualification_fail" class="alert alert-danger" style="visibility: hidden">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <strong>Oops!</strong> Something went wrong while saving data!
                                </div>
                                <div class='form-group'>
                                    <label>Qualification Name :- </label>
                                    <input type='text' value='' placeholder='Enter Qualification Name Here' class="form-control" id='txtQualification'/>
                                </div>
                                <div class='form-group'>
                                    <label>Qualification Description :- </label>
                                    <textarea class="form-control" id='txtQualificationDesc' placeholder="Enter Qualfication Description Here"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">Close
                                </button>
                                <button type="button" class="btn btn-primary" onclick="saveQualfication()">
                                    Add New Qualification
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Modal -->

                <!--Modal for field -->
                <div class='modal fade' id='fieldModal' tabindex="-1" role='dialog' aria-labelledby='fieldModelLabel' aria-hidden='true'>
                    <div class='modal-dialog'>
                        <div class='modal-content'>
                            <div class='modal-header'>
                                <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                    &times;
                                </button>
                                <h4 class='modal-title' id='fieldModelLabel'>
                                    Doctor Field Create
                                </h4>
                            </div>
                            <div class="modal-body">

                                <div id="field_success" class="alert alert-success" style="visibility: hidden">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <strong>Success!</strong> Doctor Field data was saved successfully!
                                </div>

                                <div id="field_fail" class="alert alert-danger" style="visibility: hidden">
                                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                                    <strong>Oops!</strong> Something went wrong while saving data!
                                </div>

                                <div class='form-group'>
                                    <label>Field Name :- </label>
                                    <input type='text' value='' placeholder='Enter Field Name Here' class="form-control" id='txtfieldname'/>
                                </div>
                                <div class='form-group'>
                                    <label>Field Description :- </label>
                                    <textarea class="form-control" id='txtfielddesc' placeholder="Enter Field Description Here"></textarea>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default"
                                        data-dismiss="modal">Close
                                </button>
                                <button type="button" class="btn btn-primary" onclick="saveField()">
                                    Add New Doctor Field
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Modal -->

                <!--onclick="getDoctorDetails()" -->

                <!--Modal for qualification viewer -->
                <div class='modal fade' id='qualificationViewer' tabindex="-1" role='dialog' aria-labelledby='qualificationViewerLabel' aria-hidden='true' onshow="getQualification()">
                    <div class='modal-dialog'>
                        <div class='modal-content'>
                            <div class='modal-header'>
                                <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                                    &times;
                                </button>
                                <h4 class='modal-title' id='qualificationModelLabel'>
                                    Qualification Viewer
                                </h4>
                            </div>
                            <div class="modal-body">
                                <ul id="qualificationList">
                                </ul>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Modal -->
            </div>
        </div>

    </body>
</html>
