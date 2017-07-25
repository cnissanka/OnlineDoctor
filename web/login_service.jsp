<%-- 
    Document   : login_service
    Created on : Dec 18, 2015, 6:03:23 PM
    Author     : codeguy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login to your account</title>
        <!-- bootstrap css-->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>

        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <script src="js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="js/bootstrap-datepicker.min.css"/>
        <script src="js/phoneValidation.js"></script>
    </head>
    <body>
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
            <h2 style="padding-top: 50px;">Login to your account</h2>
            <div class="row">
                <%  if (request.getParameter("res") != null) {
                        if (request.getParameter("res").equals("success")) {%>
                <div id="doctor_success" class="alert alert-success" style="visibility: visible">
                    <a href="#" class="close" data-dismiss="alert">&times;</a>
                    <strong>Success!</strong> Data was updated successfully!
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

            </div>
            <%
                if (request.getSession().getAttribute("user") != null){
                    POJOs.User user = (POJOs.User) request.getSession().getAttribute("user");
                    if (user.getUsertype().equals("DOCTOR")){
                        response.sendRedirect("doctor/doctor_home.jsp");
                    }else if (user.getUsertype().equals("ADMINISTRATOR")){
                        response.sendRedirect("admin/admin_home.jsp");
                    }
                }
            %>
            <div class="row" style="margin-top: 70px;">
                <div class="col-md-5">
                    <div class="well">
                        <h3>Sign Up <small> to unlock personal services!</small></h3>
                        <form action="handleLoginUser" method="POST">
                            <div class="form-group" style="margin-top: 50px;">
                                <label for="username">Username :- </label>
                                <input type="text" id="username" class="form-control" placeholder="Username here" name="username"/>
                            </div>
                            <div class="form-group">
                                <label for="password">Password :- </label>
                                <input type="password" id="password" class="form-control"  placeholder="Password here" name="password"/>
                            </div>
                            <button class="btn btn-primary" type="sumbit"><span class="glyphicon glyphicon-log-in"></span>  Login!</button>
                        </form>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="well">
                       
                             <h3>New Here? <small>Just create a account!</small></h3>
                            <form method="POST" action="handlePatientUserCreate">
                            <div class="form-group" style="margin-top: 50px;">
                                <label for="pusername">Username :- </label>
                                <input type="text" value="" id="pusername" name="username" placeholder="Enter username" class="form-control"/>
                            </div>
                            <div class="form-group">
                                <label for="ppassword">Password :- </label>
                                <input type="password" value="" id="ppassword" placeholder="Enter Password" class="form-control" name="password"/>
                            </div>
                            <div class="form-group">
                                <label for="ppassword_confirm">Confirm Password:- </label>
                                <input type="password" value="" id="ppassword_confirm" placeholder="Enter Password Again" class="form-control" name="confirmpassword"/>
                            </div>
                            <div class="form-group">
                                <label for="psalutate" name="salutation">Select Salutation :- </label>
                                <select class="form-control" name="saluation">
                                    <option>Mr.</option>
                                    <option>Master.</option>
                                    <option>Mrs.</option>
                                    <option>Miss.</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pfname">Enter First Name :- </label>
                                <input type="text" value="" id="pfname" placeholder="Enter First Name" class="form-control" name="firstname"/>
                            </div>
                            <div class="form-group">
                                <label for="pmname">Enter Middle Name :- </label>
                                <input type="text" value="" id="pmname" placeholder="Enter Middle Name" class="form-control" name="middlename"/>
                            </div>
                            <div class="form-group">
                                <label for="plname">Enter Last Name :- </label>
                                <input type="text" value="" id="plname" placeholder="Enter Last Name" class="form-control" name="lastname"/>
                            </div>
                            <div class="form-group">
                                <label>Select Gender :- </label>
                                <select class="form-control" name="gender">
                                    <option>Male</option>
                                    <option>Female</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Select Date of Birth :- </label>
                                <div class="input-group bootstrap-datepicker datepicker">
                                    <input id="timepicker2" type="text" class="form-control input-small" name="dob" >                    
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-time"></i></span>
                                </div>
                                <script type="text/javascript">

                                    $('#timepicker2').datepicker();
                                </script>
                            </div>
                            <div class="form-group">
                                <label>Enter your NIC :- </label>
                                <input type="text" value="" class="form-control" placeholder="Enter National Identity No, If any" name="nic" onblur="validateNic(this)"/>
                            </div>
                            <div class="form-group">
                                <label>Enter your Phone # :- </label>
                                <div class="input-group">
                                    <span class="input-group-addon">+94</span>
                                    <input type="text" value="" class="form-control input-small" placeholder="Enter Phone Number" name="phone" onkeypress="return validatePhone(this,event)"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="paddress">Enter your Address :- </label>
                                <textarea id="paddress" class="form-control" name="address"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="pemail">Enter your email address</label>
                                <input type="email" class="form-control" id="pemail" name="email"/>
                            </div>

                            <button class="btn btn-primary" type="submit" name="btnsub"><span class="glyphicon glyphicon-user"></span>  Create My Account!</button>
                        </form>
                        
                       
                    </div> <!-- end of well -->
                </div> <!--end of patient data end of col -->
            </div> <!-- end of row -->
        </div>
    </body>
</html>
