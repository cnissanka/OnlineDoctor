<%-- 
    Document   : doctor_careers
    Created on : Feb 2, 2016, 9:20:09 AM
    Author     : codeguy
--%>

<%@page import="org.hibernate.criterion.Restrictions"%>
<%@page import="org.hibernate.Session"%>
<%@page import="org.omg.PortableServer.POA"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Doctor Careers</title>
        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="js/bootstrap-datepicker.js"></script>
        <link rel="stylesheet" href="js/bootstrap-datepicker.min.css"/>
        <script src="js/phoneValidation.js"></script>
        <script>
            var package;
            var cButton;
            function selectPackage(cBtn, packageid) {
                package = packageid;

                if (cButton !== undefined) {
                    cButton.className = "btn btn-primary";
                    cButton.innerHTML = "Select";
                }

                cButton = cBtn;
                cBtn.className = "btn btn-danger";
                cBtn.innerHTML = "Selected";

              //  document.getElementById('doctor_form').style = "visibility:visible";
                document.getElementById('txtpackage').value = package;
             //   alert(document.getElementById('txtpackage').value);
            }
        </script>
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
                        <li ><a href="">Home</a></li>
                        <li><a href="virtual_doc.jsp">Ask Virtual Doctor</a></li>
                        <li><a href="pharmacy_service.jsp">Pharmacy Services</a></li>
                        <li><a href="medical_services.jsp">Medical Services</a></li>
                        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">More Stuff...   <span class="glyphicon glyphicon-triangle-bottom"></span>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="our_blog.jsp">Our Blog</a></li>
                                <li><a href="#">Contact Us</a></li>
                                <li><a href="doctor_careers.jsp">Our Careers</a></li>
                                <li><a href="login_service.jsp">Your Account!</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </ul>

            <div class="jumbotron">
                <div class="container">
                    <h1>Welcome to our careers!</h1>
                    <p>If you are a doctor, we got a career for you!</p>
                </div>
            </div>

            <div class="row">
                <%
                    Session s = Connection.Connector.getSessionFactory().openSession();
                    List<POJOs.DoctorClasses> listPackages = s.createCriteria(POJOs.DoctorClasses.class).list();
                    // int count = 0;
                    for (POJOs.DoctorClasses c : listPackages) {%>
                <div class="col-sm-6 col-md-3">
                    <div class="thumbnail">
                        <img src="<%=c.getClassImage()%>"
                             alt="Generic placeholder thumbnail">
                    </div>
                    <div class="caption">
                        <h3><%=c.getClassName()%></h3>
                        <p><%=c.getClassDescr()%></p>
                        <p>
                            <button class="btn btn-primary" onclick="selectPackage(this, <%=c.getIddoctorClasses()%>)">Select</button>
                        </p>
                    </div>
                </div>
                <%}
                    //  count += 1;
                %>
            </div>

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
            <form method="POST" action="handleCreateTempDoctor" enctype="multipart/form-data">
                <input type="hidden" id="txtpackage" name="package" />
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
                    <label>Doctor NIC :- </label>
                    <input type='text' value='' placeholder="Enter Doctor NIC Here" class="form-control" required name="nic" id="nic"/>
                </div>
                <div class='form-group'>
                    <label>Doctor Mobile # :- </label>
                    <div class="input-group">                        
                        <span class="input-group-addon">+94</span>
                        <input type="text" class="form-control" placeholder="Enter Doctor Mobile #" name='mobile1' required id="mobile" onkeypress="return validatePhone(this, event)"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label>Doctor Land # :- </label>
                    <div class="input-group">

                        <span class="input-group-addon">+94</span>
                        <input type="text" class="form-control" placeholder="Enter Doctor Land #" name='land1' required id="land" onkeypress="return validatePhone(this, event)"/>
                    </div>
                </div>
                <div class='form-group'>
                    <label>Doctor Email Address :- </label>
                    <input type='text' value="" placeholder="Enter Doctor Email Here" class='form-control' required name='email' id="email"/>
                </div>
                <div class='form-group'>
                    <label>Doctor Address :- </label>
                    <textarea class="form-control" placeholder="Enter Doctor Address" required name="address1" id="address"></textarea>
                </div>
                <div class="form-group">
                    <label>Submit CV : </label>
                    <input type="file" name="cv"/>
                </div>
                <button class="btn btn-primary">Save Details</button>
            </form>
        </div>
    </body>
</html>
