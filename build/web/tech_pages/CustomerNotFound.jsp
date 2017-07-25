<%-- 
    Document   : CustomerNotFound
    Created on : Jan 2, 2016, 9:04:14 AM
    Author     : codeguy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Not Logged</title>
        <script src="../js/jquery-2.1.4.min.js"></script>
        <link rel="stylesheet" href="../bootstrap-3.3.6-dist/css/bootstrap.min.css"/>
        <script src="../bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
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
            
            
        </div>
    </body>
</html>
