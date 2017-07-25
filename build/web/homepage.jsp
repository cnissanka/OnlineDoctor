<%-- 
    Document   : homepage
    Created on : Dec 18, 2015, 4:18:39 PM
    Author     : codeguy
--%>

<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Homepage - OnlineDoctor</title>
        <!-- bootstrap css-->
        <link rel="stylesheet" href="bootstrap-3.3.6-dist/css/bootstrap.min.css"/>

        <script src="js/jquery-2.1.4.min.js"></script>
        <script src="bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            POJOs.User uCustomer = (POJOs.User) request.getSession().getAttribute("user");
        %>
        <!-- main container-->
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
                                <li><a href="login_service.jsp">Your Account!</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </ul>
            <!--end of navbar code-->
             
            <%
                if (uCustomer != null){%>
                <h3 style="margin-top: 120px;padding-bottom: 20px;">Welcome <%=uCustomer.getUsername()%>! <small>Greetings, from Online Doctor!</small></h3>
            <%}
            %>
                
            <div id='myCarousel' class='carousel slide'>
                <ol class='carousel-indicators'>
                    <%
                        Session s = Connection.Connector.getSessionFactory().openSession();
                        List<POJOs.Misc> misc = s.createCriteria(POJOs.Misc.class).list();

                        int counter = 0;
                        for (POJOs.Misc m1 : misc) {%>
                    <%
                        if (counter == 0) {%>
                    <li data-target="#myCarousel" data-slide-to="<%=counter%>" class="active"></li>
                        <%} else {%>
                    <li data-target="#myCarousel" data-slide-to="<%=counter%>"></li>
                        <%}
                        %>
                        <%counter += 1;
                            }%>  
                </ol>
                <div class="carousel-inner">
                    <%
                        counter = 0;
                        for (POJOs.Misc m : misc) {%>
                    <%
                        if (counter == 0) {%>
                    <div class="item active">
                        <img src="<%=m.getImagePath()%>" alt="Slide of Carousel"/>
                        <div class="carousel-caption">Online Doctor Features</div>
                    </div>   
                    <%} else {%>
                    <div class="item">
                        <img src="<%=m.getImagePath()%>" alt="Slide of Carousel"/>
                        <div class="carousel-caption">Online Doctor Features</div>
                    </div>
                    <%}
                    %>
                    <%counter += 1;
                        }%>  
                </div>
                <!-- carousel controls -->
                <a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
                <a class="carousel-control right" href="#myCarousel" data-slide="next">&lsaquo;</a>
            </div>
               
            <div class="well well-lg" style="margin-top: 100px;">
                <h2>About Us</h2>
                <p>
                    <%
                        String jsp_path = this.getServletConfig().getServletContext().getRealPath("/js");
                        String txtPath = jsp_path + "/aboutus.txt";
                        BufferedReader br = new BufferedReader(new FileReader(txtPath));

                        String s1 = br.readLine();
                        while (s1 != null) {
                    %>

                    <%out.println(s1);%>
                    <%s1 = br.readLine();
                        }
                    %>
                </p>
            </div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>Our Location <small>So you know right where to find us.</small></h3>
                </div>
                <div class="panel-body">
                    <%
                        jsp_path = this.getServletConfig().getServletContext().getRealPath("/uploads");
                        String locationPath = jsp_path + "/location.txt";
                        BufferedReader br2 = new BufferedReader(new FileReader(locationPath));
                        String s2 = br2.readLine();
                        System.out.println(s2);
                    %>
                    <iframe src="<%=s2%>" width="100%" height="300" frameborder="0" style="border:0" allowfullscreen></iframe>
                </div>
                <div class="panel-footer">
                    <p>See you soon!</p>
                </div>
            </div>
        </div>

    </body>
</html>
