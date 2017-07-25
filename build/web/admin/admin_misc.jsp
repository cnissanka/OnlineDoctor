<%-- 
    Document   : admin_misc
    Created on : Dec 20, 2015, 1:49:10 PM
    Author     : codeguy
--%>


<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
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

        <script src='../js/misc_js.js'></script>

        <script>
            var adminID = 0;
            var searchType = "adminFname";
            function deleteMisc(id) {
                $.ajax({
                    type: 'POST',
                    url: "../handleMiscDelete",
                    data: {
                        "id": id
                    },
                    success: function (data, textStatus, jqXHR) {

                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }



            function changeSearchType(x) {
                console.log(x);
                if (x === "Admin First Name") {
                    searchType = "adminFname";
                } else if (x === "Admin Middle Name") {
                    searchType = "adminMname";
                } else if (x === "Admin Last Name") {
                    searchType = "adminLname";
                } else if (x === "Admin Username") {
                    searchType = "username";
                }
            }

            function changeAdminID(x) {
                //    alert(x);
                adminID = x;
            }

            function searchAdmin() {
                window.location.replace('admin_misc.jsp?search=' + $('#search').val() + '&searchType=' + searchType);
            }
        </script>
    </head>
    <body>
        <%
            POJOs.User user2 = null;
            if (request.getSession() == null) {
                response.sendRedirect("../login_service.jsp");
            } else {
                user2 = (POJOs.User) request.getSession().getAttribute("user");
                if (user2 == null) {
                    response.sendRedirect("../login_service.jsp");
                } else if (!user2.getUsertype().equals("ADMINISTRATOR")) {
                    response.sendRedirect("../login_service.jsp");
                }
            }
            //security mechanism applied for the blog.           
            boolean isAccessRighed = false;
            Session s5 = Connection.Connector.getSessionFactory().openSession();
            List<POJOs.AdminAccessRight> accessRights = s5.createCriteria(POJOs.AdminAccessRight.class).add(Restrictions.eq("administrator", user2.getAdministrator())).list();

            for (POJOs.AdminAccessRight a : accessRights) {
                if (a.getAdminAccess().getIdadminAccess() == 8) {
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
                            <h2>Welcome <%=user2.getAdministrator().getAdminFname() + " " + user2.getAdministrator().getAdminLname()%>! <small>to the Control Panel of OnlineDoctor!</small></h2> 
                        </center>
                        <div class="pull-right">
                            <form action="../handlelogout" method="POST">            
                                <button class="btn btn-danger" style="margin-right: 15px;" type="submit">Sign out <span class="glyphicon glyphicon-user"></span></button>
                            </form>
                        </div>
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
                        <li><a href='admin_blog.jsp'>Blog Details</a></li>
                        <li class="active"><a href="admin_misc.jsp">Misc and User Account Mgt</a></li>                       
                    </ul>
                </div>
                <div class="col-lg-8">
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
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Slider #</th>
                                <th>Slider Preview Image</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                Session s = Connection.Connector.getSessionFactory().openSession();
                                List<POJOs.Misc> miscList = s.createCriteria(POJOs.Misc.class).list();
                                int counter = 1;
                                for (POJOs.Misc m : miscList) {%>
                            <tr>
                                <td><%=counter%></td>
                                <td>
                                    <img src="../<%=m.getImagePath()%>" width="64px" height="64px" />
                                </td>
                                <td>
                                    <button onclick="deleteMisc(<%=m.getIdmisc()%>)" class="btn btn-danger">Delete Image</button>
                                </td>
                                <%
                                    counter += 1;
                                %>
                            </tr>
                            <%}
                            %>
                        </tbody>
                    </table>


                    <form for="form" action="../handleMiscAdd" method="POST" enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="pre">Insert Slider Image :- </label>
                            <span class="btn btn-primary btn-file" id="pre" >
                                Browse for Slider Image (png, jpg)<input type="file" name="txtfile" required>
                            </span>
                            <button type="submit" class="btn btn-primary">
                                <span class="glyphicon glyphicon-save-file"></span>
                                Add Slider Image
                            </button>
                        </div>
                    </form>

                    <div class="well">
                        <h4>About Us Editor</h4>
                        <form method="POST" action="../handleAboutUsAdd">
                            <div class="form-group">
                                <label>About Us :- </label>
                                <%
                                    String jsp_path = this.getServletConfig().getServletContext().getRealPath("/js");
                                    String txtPath = jsp_path + "/aboutus.txt";
                                    BufferedReader br = new BufferedReader(new FileReader(txtPath));
                                    String output = "";
                                    String current = br.readLine();
                                    while (current != null) {
                                        output += current;
                                        current = br.readLine();
                                    }
                                    br.close();
                                %>
                                <textarea class="form-control" placeholder="Enter About Us Here" name="about"><%=output%></textarea>
                            </div>
                            <button class="btn btn-primary"><span class="glyphicon glyphicon-pencil"></span> Edit About Us</button>
                        </form>
                    </div>

                    <div class="well">
                        <h4>Location Editor <small>Edit the Google Map Location</small></h4>
                        <form method="POST" action="../handleLocationAdd">
                            <div class="form-group">
                                <label>Google Map Location URL : </label>
                                <input type="text" name="location" class="form-control">
                            </div>
                            <input type="submit" class="btn btn-primary" value="Change Location">
                        </form>
                    </div>

                    <div class="input-group" style="padding-top: 20px;padding-bottom: 20px;" >
                        <input type="text" placeholder="Search Admin!" class="form-control" id="search"/>
                        <div class="input-group-btn" >
                            <button type="button" class="btn btn-default 
                                    dropdown-toggle" data-toggle="dropdown">
                                Search By
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu pull-right">
                                <li><a onclick="changeSearchType(this.innerHTML)">Admin First Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)">Admin Middle Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)">Admin Last Name</a></li>
                                <li><a onclick="changeSearchType(this.innerHTML)">Admin Username</a></li>                                
                            </ul>
                        </div><!-- /btn-group -->
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="button" onclick="searchAdmin()">
                                Search!
                            </button>
                        </span>
                    </div>


                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>Administrator First Name</th>
                                <th>Administrator Middle Name</th>
                                <th>Administrator Last Name</th>
                                <th>Administrator Username</th>
                                <th>Administrator Password</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<POJOs.Administrator> adminList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Administrator.class).list();
                                Session se = Connection.Connector.getSessionFactory().openSession();
                                if (request.getParameter("search") != null && request.getParameter("searchType") != null) {
                                    if (request.getParameter("searchType").equals("username")) {
                                        List<POJOs.User> userList = se.createCriteria(POJOs.User.class).add(Restrictions.like("username", request.getParameter("search") + "%")).list();
                                        adminList.clear();
                                        for (POJOs.User user : userList) {
                                            adminList.add(user.getAdministrator());
                                        }
                                    } else {
                                        adminList = se.createCriteria(POJOs.Administrator.class).add(Restrictions.like(request.getParameter("searchType"), request.getParameter("search") + "%")).list();
                                    }
                                } else {
                                    adminList = Connection.Connector.getSessionFactory().openSession().createCriteria(POJOs.Administrator.class).list();
                                }

                                for (POJOs.Administrator admin : adminList) {
                                    if (admin.getIdadministrator() != -1) {
                            %>
                            <tr>
                                <td><%=admin.getAdminFname()%></td>
                                <td><%=admin.getAdminMname()%></td>
                                <td><%=admin.getAdminLname()%></td>
                                <td>
                                    <%
                                        POJOs.User user = (POJOs.User) admin.getUsers().iterator().next();
                                    %>
                                    <%=user.getUsername()%>
                                </td>
                                <td>
                                    <%=user.getPassword()%>
                                </td>
                                <td><button class="btn btn-warning" data-target="#adminViewer" data-toggle="modal" onclick="changeAdminID(<%=admin.getIdadministrator()%>)"><span class="glyphicon glyphicon-wrench"></span> Update Details</button></td>
                                <td>
                                    <button class="btn btn-info" data-target="#accessRightViewer" data-toggle="modal"  onclick="changeAdminID(<%=admin.getIdadministrator()%>)"><span class="glyphicon glyphicon-dashboard"></span> Apply Access Rights</button>
                                </td>

                        <input type="hidden" value="<%=admin.getIdadministrator()%>" id="txtadminid" />
                        </tr>
                        <%}
                            }
                        %>
                        </tbody>
                    </table>

                    <form method="POST" action='../handleAdminUserAccount'>
                        <div class="form-group">
                            <label>Admin First Name :- </label>
                            <input type="text" class="form-control" name="firstname" />
                        </div>
                        <div class='form-group'>
                            <label>Admin Middle Name :- </label>
                            <input type="text" class="form-control" name="middlename" />
                        </div>
                        <div class='form-group'>
                            <label>Admin Last Name :- </label>
                            <input type="text" class="form-control" name="lastname" />
                        </div>
                        <div class='form-group'>
                            <label>Admin Username :- </label>
                            <input type="text" class="form-control" name="username" onkeyup="findPreview(this)"/>
                        </div>                        

                        <div class='form-group'>   
                            <label>Admin Password :- </label>
                            <input type="password" class="form-control" name="password"/>
                        </div>

                        <label>Admin Confirm Password :- </label>
                        <input type="password" class="form-control" name="confirmpassword"/>
                        <br/>
                        <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-plus"></span> Save Administrator</button>

                    </form>
                </div>
            </div>
        </div>
        <!--Modal for admin viewer -->
        <div class='modal fade' id='adminViewer' tabindex="-1" role='dialog' aria-labelledby='adminViewerLabel' aria-hidden='true' onshow="getAdminDetails()">
            <div class='modal-dialog'>
                <div class='modal-content'>
                    <div class='modal-header'>
                        <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                            &times;
                        </button>
                        <h4 class='modal-title' id='adminViewerLabel'>
                            Administrator Editor 
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label>First Name :- </label>
                            <input type="text" class="form-control" id="txtFirstName1" />
                        </div>
                        <div class="form-group">
                            <label>Middle Name :- </label>
                            <input type="text" class="form-control" id="txtMiddleName1" />
                        </div>
                        <div class="form-group">
                            <label>Last Name :- </label>
                            <input type="text" class="form-control" id="txtLastName1" />
                        </div>
                        <div class="form-group">
                            <label>Password :- </label>
                            <input type="password" class="form-control" id="txtPassword1" />
                        </div>
                        <div class="form-group">
                            <label>Confirm Password :- </label>
                            <input type="password" class="form-control" id="txtPassword2" />
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-warning" onclick="updateAdmin()">Update Admin</button>
                        <button class="btn btn-danger" onclick="deleteAdmin()">Delete Admin</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal -->

        <!--Modal for admin access right viewer -->
        <div class='modal fade' id='accessRightViewer' tabindex="-1" role='dialog' aria-labelledby='accessRightLabel' aria-hidden='true' onshow="">
            <div class='modal-dialog'>
                <div class='modal-content'>
                    <div class='modal-header'>
                        <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                            &times;
                        </button>
                        <h4 class='modal-title' id='accessRightLabel'>
                            Administrator Editor 
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div class="checkbox">
                            <label><input type="checkbox" checked id="chkPatient"/> Access Rights for Patient Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" checked id="chkDoctor"/> Access Rights for Doctor Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" checked id="chkPharmacy"/> Access Rights for Pharmacy Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" checked id="chkMedical"/> Access Rights for Medical Services Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" checked id="chkKnowledge"/> Access Rights for Knowledge Base Management </label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" checked id="chkBlog"/> Access Rights for Blog Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox" checked id="chkUser"/> Access Rights for User Account Management </label> 
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" onclick="applyAccessRights()">Apply Access Rights</button>                        
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal -->

        <!--Modal for admin access right viewer -->
        <div class='modal fade' id='updateAccessRightViewer' tabindex="-1" role='dialog' aria-labelledby='updateAccessRightLabel' aria-hidden='true' onshow="findAccessRights()">
            <div class='modal-dialog'>
                <div class='modal-content'>
                    <div class='modal-header'>
                        <button type='button' class="close" data-dismiss='modal' aria-hidden='true'>
                            &times;
                        </button>
                        <h4 class='modal-title' id='updateAccessRightLabel'>
                            Administrator Editor 
                        </h4>
                    </div>
                    <div class="modal-body">
                        <div class="checkbox">
                            <label><input type="checkbox"  id="chkPatient2"/> Access Rights for Patient Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"  id="chkDoctor2"/> Access Rights for Doctor Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"  id="chkPharmacy2"/> Access Rights for Pharmacy Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"  id="chkMedical2"/> Access Rights for Medical Services Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"  id="chkKnowledge2"/> Access Rights for Knowledge Base Management </label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"  id="chkBlog2"/> Access Rights for Blog Management</label>
                        </div>
                        <div class="checkbox">
                            <label><input type="checkbox"  id="chkUser2"/> Access Rights for User Account Management </label> 
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" onclick="updateAccessright()">Update Access Rights</button>                        
                    </div>
                </div>
            </div>
        </div>
        <!-- End Modal -->

        <script>
            function applyAccessRights() {
                var patient = document.getElementById('chkPatient').checked;
                var doctor = document.getElementById('chkDoctor').checked;
                var pharmacy = document.getElementById('chkPharmacy').checked;
                var medical = document.getElementById('chkMedical').checked;
                var blog = document.getElementById('chkBlog').checked;
                var user = document.getElementById('chkUser').checked;
                var knowledge = document.getElementById('chkKnowledge').checked;

                var array = [];
                var count = 0;

                if (patient === true) {
                    array [count] = "patient";
                    count += 1;
                }
                if (knowledge === true) {
                    array [count] = "knowledge";
                    count += 1;
                }
                if (doctor === true) {
                    array [count] = "doctor";
                    count += 1;
                }
                if (pharmacy === true) {
                    array[count] = "pharmacy";
                    count += 1;
                }
                if (medical === true) {
                    array[count] = "medical";
                    count += 1;
                }
                if (blog === true) {
                    array[count] = "blog";
                    count += 1;
                }
                if (user === true) {
                    array[count] = "user";
                    count += 1;
                }
                console.log(array);
                var dataset = "";

                for (var i = 0; i < array.length; i++) {
                    dataset += array[i] + "#";
                }

                console.log(dataset);

                $.ajax({
                    type: 'POST',
                    url: "../handleAccessrightAdd",
                    data: {
                        "id": adminID,
                        "dataset": dataset
                    },
                    success: function (data, textStatus, jqXHR) {
                        window.location.replace('admin_misc.jsp?res=success');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        window.location.replace('admin_misc.jsp?res=fail');
                    }
                });
            }

            function findAccessRights() {
                $.ajax({
                    url: "../handleGetAdminAccessRights",
                    type: 'GET',
                    data: {
                        "id2": adminID
                    },
                    success: function (data, textStatus, jqXHR) {
                        //window.location.replace('admin_misc.jsp?res=success');
                        //  alert(data);
                        accessrightids = data.split('#');
                        var patient = document.getElementById('chkPatient2');
                        var doctor = document.getElementById('chkDoctor2');
                        var pharamacy = document.getElementById('chkPharmacy2');
                        var medical = document.getElementById('chkMedical2');
                        var knowledge = document.getElementById('chkKnowledge2');
                        var blog = document.getElementById('chkBlog2');
                        var user = document.getElementById('chkUser2');

                        patient.checked = false;
                        doctor.checked = false;
                        pharamacy.checked = false;
                        medical.checked = false;
                        knowledge.checked = false;
                        blog.checked = false;
                        user.checked = false;
                        for (var i = 0; i < accessrightids.length; i++) {
                            var accessright = parseInt(accessrightids [i]);
                            switch (accessright) {
                                case 1:
                                    pharamacy.checked = true;
                                    break;
                                case 2:
                                    doctor.checked = true;
                                    break;
                                case 3:
                                    patient.checked = true;
                                    break;
                                case 5:
                                    knowledge.checked = true;
                                    break;
                                case 6:
                                    medical.checked = true;
                                    break;
                                case 7:
                                    blog.checked = true;
                                    break;
                                case 8:
                                    user.checked = true;
                                    break;
                            }
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        // window.location.replace('admin_misc.jsp?res=fail');
                    }
                });
            }

            function updateAccessright() {
                var patient = document.getElementById('chkPatient2').checked;
                var doctor = document.getElementById('chkDoctor2').checked;
                var pharmacy = document.getElementById('chkPharmacy2').checked;
                var medical = document.getElementById('chkMedical2').checked;
                var blog = document.getElementById('chkBlog2').checked;
                var user = document.getElementById('chkUser2').checked;
                var knowledge = document.getElementById('chkKnowledge2').checked;

                var array = [];
                var count = 0;

                if (patient === true) {
                    array [count] = "patient";
                    count += 1;
                }
                if (knowledge === true) {
                    array [count] = "knowledge";
                    count += 1;
                }
                if (doctor === true) {
                    array [count] = "doctor";
                    count += 1;
                }
                if (pharmacy === true) {
                    array[count] = "pharmacy";
                    count += 1;
                }
                if (medical === true) {
                    array[count] = "medical";
                    count += 1;
                }
                if (blog === true) {
                    array[count] = "blog";
                    count += 1;
                }
                if (user === true) {
                    array[count] = "user";
                    count += 1;
                }
                console.log(array);
                var dataset = "";

                for (var i = 0; i < array.length; i++) {
                    dataset += array[i] + "#";
                }

                console.log(dataset);

                $.ajax({
                    type: 'GET',
                    url: "../handleUpdateAdminAccess",
                    data: {
                        "id": adminID,
                        "dataset": dataset
                    },
                    success: function (data, textStatus, jqXHR) {
                        window.location.replace('admin_misc.jsp?res=success');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        window.location.replace('admin_misc.jsp?res=fail');
                    }
                });
            }

            function deleteAdmin() {
                $.ajax({
                    url: "../handleAdminDelete",
                    type: 'POST',
                    data: {
                        "id": adminID
                    },
                    success: function (data, textStatus, jqXHR) {
                        window.location.replace('admin_misc.jsp?res=success');
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        window.location.replace('admin_misc.jsp?res=fail');
                    }
                });
            }

            function updateAdmin() {
                $.ajax({
                    url: "../handleAdminUpdate",
                    type: 'POST',
                    data: {
                        "id": adminID,
                        "firstname": $('#txtFirstName1').val(),
                        "middlename": $('#txtMiddleName1').val(),
                        "lastname": $('#txtLastName1').val(),
                        "password": $('#txtPassword1').val(),
                        "confirmpassword": $('#txtPassword2').val()
                    },
                    success: function (data, textStatus, jqXHR) {
                        if (data === "success") {
                            window.location.replace('admin_misc.jsp?res=success');
                        } else {
                            window.location.replace('admin_misc.jsp?res=validation&validation_msg=' + data.split('#')[1]);
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown) {
                        window.location.replace('admin_misc.jsp?res=fail');
                    }
                });
            }

            function getAdminDetails() {
                $.ajax({
                    type: 'POST',
                    url: "../handleGetAdminDetails",
                    data: {
                        "id": adminID
                    },
                    success: function (data, textStatus, jqXHR) {
                        var dataset = data.split("#");

                        $('#txtFirstName1').val(dataset[0]);
                        $('#txtMiddleName1').val(dataset[1]);
                        $('#txtLastName1').val(dataset[2]);
                    },
                    error: function (jqXHR, textStatus, errorThrown) {

                    }
                });
            }
        </script>
    </div>
</body>
</html>
